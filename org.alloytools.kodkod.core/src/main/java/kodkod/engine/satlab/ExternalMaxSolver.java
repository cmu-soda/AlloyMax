package kodkod.engine.satlab;

import java.io.*;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class ExternalMaxSolver implements MaxSATSolver {

    protected static final String   TOP = "1000000000000";
    protected final List<Clause>    cached = new LinkedList<>();
    protected int                   maxPriority = 0;

    /** This is the CNF file we are generating. */
    protected final RandomAccessFile cnf;

    /**
     * This buffers up the clauses we are writing to the CNF file, to avoid
     * excessive I/O.
     */
    protected final StringBuilder    buffer;

    /** This is the buffer size. */
    protected static final int       capacity = 8192;

    /** The number of variables so far. */
    protected int                    vars     = 0;

    /** The number of clauses so far. */
    protected int                    clauses  = 0;

    protected Boolean   sat;
    protected BitSet    solution;
    protected String[]  options;
    protected String    executable;
    protected String    inTemp;

    public ExternalMaxSolver(String executable, String filename, String... options) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(filename, "rw");
            file.setLength(0);
        } catch (IOException e) {
            close(file);
            throw new SATAbortedException(e);
        }
        this.cnf = file;
        this.buffer = new StringBuilder(capacity);
        this.sat = null;
        this.solution = new BitSet();
        this.executable = executable;
        this.options = options;
        this.inTemp = filename;
    }

    protected static void close(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException ignored) {}
    }

    protected void flush() {
        try {
            cnf.writeBytes(buffer.toString());
            buffer.setLength(0);
        } catch (IOException e) {
            close(cnf);
            throw new SATAbortedException(e);
        }
    }

    @Override
    public Boolean isSAT() {
        return sat;
    }

    @Override
    public void incNumberOfClauses() {
        clauses++;
    }

    @Override
    public List<Clause> getCached() {
        return cached;
    }

    @Override
    public int getMaxPriority() {
        return maxPriority;
    }

    @Override
    public void setMaxPriority(int priority) {
        if (priority > maxPriority)
            maxPriority = priority;
    }

    @Override
    public boolean actualSolve(int[] weights) {
        if (sat != null)
            return sat;
        writeCNF(weights);

        final String[] command = new String[options.length + 2];
        Process p = null;
        BufferedReader out = null;
        command[0] = executable;
        System.arraycopy(options, 0, command, 1, options.length);
        command[command.length - 1] = inTemp;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(drain(p.getErrorStream())).start();
            out = outputReader(p);
            String line = null;
            while ((line = out.readLine()) != null) {
                String[] tokens = line.split("\\s");
                int tlength = tokens.length;
                if (tlength > 0) {
                    if (tokens[0].compareToIgnoreCase("s") == 0) {
                        if (tlength == 2) {
                            if (tokens[1].compareToIgnoreCase("SATISFIABLE") == 0) {
                                sat = Boolean.TRUE;
                                continue;
                            } else if (tokens[1].compareToIgnoreCase("UNSATISFIABLE") == 0) {
                                sat = Boolean.FALSE;
                                continue;
                            }
                        } else if (tlength == 3) {
                            if (tokens[1].compareToIgnoreCase("OPTIMUM") == 0 && tokens[2].compareToIgnoreCase("FOUND") == 0) {
                                sat = Boolean.TRUE;
                                continue;
                            }
                        }
                        throw new SATAbortedException("Invalid " + executable + " output. Line: " + line);
                    } else if (tokens[0].compareToIgnoreCase("v") == 0) {
                        int last = tlength - 1;
                        for (int i = 1; i < last; i++) {
                            updateSolution(Integer.parseInt(tokens[i]));
                        }
                        int lit = Integer.parseInt(tokens[last]);
                        if (lit != 0)
                            updateSolution(lit);
                        else if (sat != null)
                            break;
                    } // not a solution line or a variable line, so ignore
                    // it.
                }
            }
            if (sat == null) {
                throw new SATAbortedException("Invalid " + executable + " output: no line specifying the outcome.");
            }
        } catch (IOException e) {
            throw new SATAbortedException(e);
        } finally {
            close(out);
        }
        return sat;
    }

    protected void writeCNF(int[] weights) {
        try {
            cnf.writeBytes("p wcnf " + vars + " " + clauses + " " + TOP + "\n");
            for (Clause c : cached) {
                if (buffer.length() > capacity)
                    flush();

                if (c.isSoft())
                    buffer.append(weights[c.getPriority()]).append(' ');
                else
                    buffer.append(TOP).append(' ');

                for (int i = 0; i < c.getLits().length; i++)
                    buffer.append(c.getLits()[i]).append(' ');
                buffer.append("0\n");
            }

            flush();
        } catch (IOException e) {
            throw new SATAbortedException(e);
        } finally {
            close(cnf);
        }
    }

    protected void updateSolution(int lit) {
        int abs = StrictMath.abs(lit);
        if (abs <= vars && abs > 0)
            solution.set(abs - 1, lit > 0);
        else
            throw new SATAbortedException("Invalid variable value: |" + lit + "| !in [1.." + vars + "]");
    }

    protected static Runnable drain(final InputStream input) {
        return () -> {
            try {
                final byte[] buffer = new byte[capacity];
                while (true) {
                    int n = input.read(buffer);
                    if (n < 0)
                        break;
                }
            } catch (IOException ignored) {} finally {
                close(input);
            }
        };
    }

    protected BufferedReader outputReader(Process p) throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
        } catch (IOException e) {
            close(p.getInputStream());
            throw e;
        }
    }

    @Override
    public int numberOfVariables() {
        return vars;
    }

    @Override
    public int numberOfClauses() {
        return clauses;
    }

    @Override
    public void addVariables(int numVars) {
        if (numVars < 0)
            throw new IllegalArgumentException("vars < 0: " + numVars);
        vars += numVars;
    }

    @Override
    public boolean valueOf(int variable) {
        if (!Boolean.TRUE.equals(sat))
            throw new IllegalStateException();
        if (variable < 1 || variable > vars)
            throw new IllegalArgumentException(variable + " !in [1.." + vars + "]");
        return solution.get(variable - 1);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        free();
    }

    @Override
    public void free() {
        close(cnf);
        (new File(inTemp)).delete();
    }
}

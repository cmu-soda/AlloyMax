package edu.mit.csail.sdg.translator;

import edu.mit.csail.sdg.alloy4.Util;
import kodkod.engine.satlab.MaxSATSolver;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

class WriteWCNF implements MaxSATSolver {

    private static final String TOP = "1000000000000";
    private final List<Clause> cached = new LinkedList<>();
    private int maxPriority = 0;

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

    /**
     * Helper method that returns a factory for WriteCNF instances.
     */
    public static SATFactory factory(final String filename) {
        return new SATFactory() {

            /** {@inheritDoc} */
            @Override
            public SATSolver instance() {
                return new WriteWCNF(filename);
            }

            /** {@inheritDoc} */
            @Override
            public boolean incremental() {
                return false;
            }
        };
    }

    private WriteWCNF(String filename) {
        try {
            this.cnf = new RandomAccessFile(filename, "rw");
            this.cnf.setLength(0);
            this.buffer = new StringBuilder(capacity);
            for (int i = String.valueOf(Integer.MAX_VALUE).length() * 3 + 20; i > 0; i--) {
                // get enough space into the buffer for the cnf header, which
                // will be written last
                buffer.append(' ');
            }
            buffer.append('\n');
        } catch (Exception ex) {
            throw new RuntimeException("WriteWCNF failed.", ex);
        }
    }

    /** Helper method that flushes the buffer. */
    protected void flush() {
        try {
            cnf.writeBytes(buffer.toString());
            buffer.setLength(0);
        } catch (IOException ex) {
            throw new RuntimeException("WriteWCNF failed.", ex);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        free();
    }

    @Override
    public Boolean isSAT() {
        return null;
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
        try {
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
            cnf.seek(0);
            cnf.writeBytes("p wcnf " + vars + " " + clauses + " " + TOP);
            cnf.close();
        } catch (Exception ex) {
            throw new RuntimeException("WriteCNF failed.", ex);
        }
        throw new WriteCNF.WriteCNFCompleted();
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
        if (numVars >= 0)
            vars += numVars;
    }

    @Override
    public boolean valueOf(int variable) {
        throw new IllegalStateException("This solver just writes the WCNF without solving them.");
    }

    @Override
    public void free() {
        Util.close(cnf);
    }
}

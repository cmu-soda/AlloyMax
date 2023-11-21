package kodkod.engine.satlab;

import java.io.IOException;

public class ExternalPMaxSolver extends ExternalMaxSolver implements PMaxSatSolver {

    private int group = 1;

    public ExternalPMaxSolver(String executable, String filename, String... options) {
        super(executable, filename, options);
    }

    @Override
    public int currentGroup() {
        return group;
    }

    @Override
    public void addGroup() {
        group++;
    }

    @Override
    protected void writeCNF(int[] weights) {
        try {
            cnf.writeBytes("p pwcnf " + vars + " " + clauses + " " + TOP + " " + group + "\n");
            for (Clause c : cached) {
                assert (c instanceof PClause);
                if (buffer.length() > capacity)
                    flush();

                if (c.isSoft())
                    buffer.append(((PClause) c).getGroup()).append(' ').append(weights[c.getPriority()]).append(' ');
                else
                    buffer.append(((PClause) c).getGroup()).append(' ').append(TOP).append(' ');

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
}

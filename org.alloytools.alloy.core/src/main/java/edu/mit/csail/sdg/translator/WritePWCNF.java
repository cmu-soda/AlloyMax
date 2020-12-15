package edu.mit.csail.sdg.translator;

import kodkod.engine.satlab.PMaxSatSolver;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;

public class WritePWCNF extends WriteWCNF implements PMaxSatSolver {

    private int group = 1;

    /**
     * Helper method that returns a factory for WriteCNF instances.
     */
    public static SATFactory factory(final String filename) {
        return new SATFactory() {

            /** {@inheritDoc} */
            @Override
            public SATSolver instance() {
                return new WritePWCNF(filename);
            }

            /** {@inheritDoc} */
            @Override
            public boolean incremental() {
                return false;
            }
        };
    }

    private WritePWCNF(String filename) {
        super(filename);
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
    public boolean actualSolve(int[] weights) {
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
            cnf.close();
        } catch (Exception ex) {
            throw new RuntimeException("WritePWCNF failed.", ex);
        }
        throw new WriteCNF.WriteCNFCompleted();
    }
}

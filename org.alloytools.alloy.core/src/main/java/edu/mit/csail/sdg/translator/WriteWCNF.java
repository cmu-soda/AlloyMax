package edu.mit.csail.sdg.translator;

import kodkod.engine.satlab.MaxSATSolver;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;

class WriteWCNF extends WriteCNF implements MaxSATSolver {

    private static final int TOP = 1000000000;

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
        super(filename);
    }

    @Override
    public boolean addClause(int[] lits) {
        int[] wlits = new int[lits.length+1];
        wlits[0] = TOP;
        for (int i = 0; i < lits.length; i++) {
            wlits[i+1] = lits[i];
        }
        return super.addClause(wlits);
    }

    @Override
    public boolean addSoftClause(int[] lits) {
        int[] wlits = new int[lits.length+1];
        wlits[0] = 1;
        for (int i = 0; i < lits.length; i++) {
            wlits[i+1] = lits[i];
        }
        return super.addClause(wlits);
    }

    @Override
    public boolean solve() {
        try {
            flush();
            cnf.seek(0);
            cnf.writeBytes("p wcnf " + vars + " " + clauses + " " + TOP);
            cnf.close();
        } catch (Exception ex) {
            throw new RuntimeException("WriteCNF failed.", ex);
        }
        throw new WriteCNFCompleted();
    }
}

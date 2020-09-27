package edu.mit.csail.sdg.translator;

import kodkod.engine.satlab.MaxSATSolver;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class WriteWCNF extends WriteCNF implements MaxSATSolver {

    private static final class Clause {
        private final int[] lits;
        private boolean soft;

        Clause(int[] lits, boolean soft) {
            this.lits = lits;
            this.soft = soft;
        }
    }

    private static final int TOP = 1000000000;
    private final List<Clause> cached = new LinkedList<>();

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
        if (lits.length > 0) {
            clauses++;
            cached.add(new Clause(lits.clone(), false));
            return true;
        }
        return false;
    }

    @Override
    public boolean addSoftClause(int[] lits) {
        if (lits.length > 0) {
            clauses++;
            cached.add(new Clause(lits.clone(), true));
            return true;
        }
        return false;
    }

    @Override
    public void setSoftClause(int[] lits) {
        for (Clause c : cached) {
            if (Arrays.equals(c.lits, lits)) {
                c.soft = true;
                return;
            }
        }
    }

    @Override
    public void setHardClause(int[] lits) {
        for (Clause c : cached) {
            if (Arrays.equals(c.lits, lits)) {
                c.soft = false;
                return;
            }
        }
    }

    @Override
    public boolean solve() {
        try {
            for (Clause c : cached) {
                if (buffer.length() > capacity)
                    flush();

                if (c.soft)
                    buffer.append(1).append(' ');
                else
                    buffer.append(TOP).append(' ');

                for (int i = 0; i < c.lits.length; i++)
                    buffer.append(c.lits[i]).append(' ');
                buffer.append("0\n");
            }

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

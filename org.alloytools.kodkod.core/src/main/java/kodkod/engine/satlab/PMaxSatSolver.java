package kodkod.engine.satlab;

import java.util.Arrays;

public interface PMaxSatSolver extends MaxSATSolver {

    class PClause extends Clause {

        private int group;

        public int getGroup() {
            return group;
        }

        PClause(int[] lits) {
            super(lits);
            group = 1;
        }

        PClause(int[] lits, boolean soft, int group) {
            super(lits, soft);
            this.group = group;
        }

        PClause(int[] lits, boolean soft, int priority, int group) {
            super(lits, soft, priority);
            this.group = group;
        }
    }

    /**
     * @return
     */
    int currentGroup();

    /**
     *
     */
    void addGroup();

    @Override
    default boolean addClause(int[] lits) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new PClause(lits.clone()));
            return true;
        }
        return false;
    }

    @Override
    default boolean addSoftClause(int[] lits) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new PClause(lits.clone(), true, currentGroup()));
            return true;
        }
        return false;
    }

    @Override
    default boolean addSoftClause(int[] lits, int priority) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new PClause(lits.clone(), true, priority, currentGroup()));
            setMaxPriority(priority);
            return true;
        }
        return false;
    }

    @Override
    default void setSoftClause(int[] lits) {
        for (Clause c : getCached()) {
            assert (c instanceof PClause);
            if (Arrays.equals(c.lits, lits)) {
                c.soft = true;
                c.priority = 0;
                ((PClause) c).group = currentGroup();
                return;
            }
        }
    }

    @Override
    default void setSoftClause(int[] lits, int priority) {
        assert (priority >= 0);
        for (Clause c : getCached()) {
            assert (c instanceof PClause);
            if (Arrays.equals(c.lits, lits)) {
                c.soft = true;
                c.priority = priority;
                ((PClause) c).group = currentGroup();
                setMaxPriority(priority);
                return;
            }
        }
    }

    @Override
    default void setHardClause(int[] lits) {
        for (Clause c : getCached()) {
            assert (c instanceof PClause);
            if (Arrays.equals(c.lits, lits)) {
                c.soft = false;
                c.priority = -1;
                ((PClause) c).group = 1;
                return;
            }
        }
    }
}

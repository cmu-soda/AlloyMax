package kodkod.engine.satlab;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Changjian Zhang
 */
public interface MaxSATSolver extends SATSolver {

    class Clause {
        protected final int[] lits;
        protected boolean soft;
        protected int priority;

        public int[] getLits() {
            return lits;
        }

        public boolean isSoft() {
            return soft;
        }

        public int getPriority() {
            return priority;
        }

        Clause(int[] lits) {
            this.lits = lits;
            this.soft = false;
            this.priority = -1;
        }

        Clause(int[] lits, boolean soft) {
            this.lits = lits;
            this.soft = soft;
            if (soft)
                this.priority = 0;
            else
                this.priority = -1;
        }

        Clause(int[] lits, boolean soft, int priority) {
            assert (!soft || priority >= 0);
            this.lits = lits;
            this.soft = soft;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return Arrays.toString(lits);
        }
    }

    /**
     *
     * @return
     */
    Boolean isSAT();

    /**
     *
     */
    void incNumberOfClauses();

    /**
     *
     * @return
     */
    List<Clause> getCached();

    /**
     * Default max priority should be 0
     * @return
     */
    int getMaxPriority();

    /**
     *
     */
    void setMaxPriority(int priority);

    @Override
    default boolean addClause(int[] lits) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new Clause(lits.clone()));
            return true;
        }
        return false;
    }

    /**
     *
     * @param lits
     * @return
     */
    default boolean addSoftClause(int[] lits) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new Clause(lits.clone(), true));
            return true;
        }
        return false;
    }

    /**
     *
     * @param lits
     * @param priority
     * @return
     */
    default boolean addSoftClause(int[] lits, int priority) {
        if (!Boolean.FALSE.equals(isSAT())) {
            incNumberOfClauses();
            getCached().add(new Clause(lits.clone(), true, priority));
            setMaxPriority(priority);
            return true;
        }
        return false;
    }

    /**
     * Set an existing clause to soft.
     * @param lits
     * @author Changjian Zhang
     */
    default void setSoftClause(int[] lits) {
        for (Clause c : getCached()) {
            if (Arrays.equals(c.lits, lits)) {
                c.soft = true;
                c.priority = 0;
                return;
            }
        }
    }

    default void setSoftClause(int[] lits, int priority) {
        assert (priority >= 0);
        for (Clause c : getCached()) {
            if (Arrays.equals(c.lits, lits)) {
                c.soft = true;
                c.priority = priority;
                setMaxPriority(priority);
                return;
            }
        }
    }

    /**
     * Set an existing clause to hard.
     * @param lits
     * @author Changjian Zhang
     */
    default void setHardClause(int[] lits) {
        for (Clause c : getCached()) {
            if (Arrays.equals(c.lits, lits)) {
                c.soft = false;
                c.priority = -1;
                return;
            }
        }
    }

    @Override
    default boolean solve() throws SATAbortedException {
        // Compute the actual weight for each priority, the default priority 0 has weight 1
        int[] weights = new int[getMaxPriority()+1];
        Arrays.fill(weights, -1);
        weights[0] = 1;

        // Find all the soft clauses
        List<Clause> softclauses = new LinkedList<>();
        for (Clause c : getCached()) {
            if (c.isSoft())
                softclauses.add(c);
        }

        for (int i = 1; i <= getMaxPriority(); i++) {
            // Compute the total value for the previous priority
            int total = 0;
            for (Clause c : softclauses) {
                if (c.priority == i - 1)
                    total++;
            }
            if (total == 0) // the i-1 priority is empty
                weights[i] = weights[i-1];
            else
                weights[i] = weights[i-1] * total + 1;
            // Test whether integer overflow has happened
            if (weights[i] <= 0) {
                throw new RuntimeException("The cumulative weights overflow the maximum integer");
            }
        }
        return actualSolve(weights);
    }

    boolean actualSolve(int[] weights);
}

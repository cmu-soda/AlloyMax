package kodkod.engine.satlab;

/**
 * @author Changjian Zhang
 */
public interface MaxSATSolver extends SATSolver {

    /**
     *
     * @param lits
     * @return
     */
    boolean addSoftClause(int[] lits);

    /**
     * Set an existing clause to soft.
     * @param lits
     * @author Changjian Zhang
     */
    void setSoftClause(int[] lits);

    /**
     * Set an existing clause to hard.
     * @param lits
     * @author Changjian Zhang
     */
    void setHardClause(int[] lits);
}

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
}

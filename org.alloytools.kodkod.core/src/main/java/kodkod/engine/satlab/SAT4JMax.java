package kodkod.engine.satlab;

import kodkod.util.collections.ReadOnlyIVecInt;
import org.sat4j.maxsat.SolverFactory;
import org.sat4j.maxsat.WeightedMaxSatDecorator;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

/**
 * A wrapper class tha provides access to the basic functionality of SAT4JMaxsat.
 *
 * @author Changjian Zhang
 */
final class SAT4JMax implements MaxSATSolver {

    private WeightedMaxSatDecorator solver;
    private final ReadOnlyIVecInt wrapper;
    private Boolean sat;
    private int vars, clauses;

    /**
     * Construct a wrapper for the default maxsat solver.
     */
    public SAT4JMax() {
        solver = new WeightedMaxSatDecorator(org.sat4j.pb.SolverFactory.newDefaultOptimizer());
        wrapper = new ReadOnlyIVecInt();
        sat = null;
        vars = 0;
        clauses = 0;
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
            throw new IllegalArgumentException("numVars < 0: " + numVars);
        else if (numVars > 0) {
            vars += numVars;
            solver.newVar(vars);
        }
    }

    @Override
    public boolean addClause(int[] lits) {
        try {
            if (!Boolean.FALSE.equals(sat)) {
                clauses++;
                // Clauses are added as hard clauses through the old api
                solver.addHardClause(wrapper.wrap(lits));
                return true;
            }
        } catch (ContradictionException e) {
            sat = Boolean.FALSE;
        }
        return false;
    }

    @Override
    public boolean addSoftClause(int[] lits) {
        try {
            if (!Boolean.FALSE.equals(sat)) {
                clauses++;
                solver.addSoftClause(wrapper.wrap(lits));
                return true;
            }
        } catch (ContradictionException e) {
            sat = Boolean.FALSE;
        }
        return false;
    }

    @Override
    public boolean solve() {
        try {
            if (solver == null) {
                return false;
            }
            if (!Boolean.FALSE.equals(sat)) {
                sat = solver.isSatisfiable();
            }
            return sat;
        } catch (TimeoutException e) {
            throw new RuntimeException("timed out");
        }
    }

    @Override
    public boolean valueOf(int variable) {
        if (!Boolean.TRUE.equals(sat)) {
            throw new IllegalStateException();
        }
        if (variable < 1 || variable > vars) {
            throw new IllegalArgumentException(variable + " !in [1.." + vars + "]");
        }
        return solver.model(variable);
    }

    @Override
    public void free() {
        sat = Boolean.FALSE;
        solver = null;
    }

    public static void main(String[] args) {
        final MaxSATSolver solver = (SAT4JMax) SATFactory.DefaultSAT4JMax.instance();
        // The 15816 A3 problem
        solver.addVariables(6);
        solver.addClause(new int[] {1, 3});
        solver.addClause(new int[] {-1, -3});
        solver.addClause(new int[] {5, 6});
        solver.addClause(new int[] {-5, -6});
        solver.addClause(new int[] {-1, 2});
        solver.addClause(new int[] {-2, 1});
        solver.addClause(new int[] {-2, 4});
        solver.addClause(new int[] {-4, 2});
        solver.addClause(new int[] {-4, 6});
        solver.addClause(new int[] {-6, 4});
        solver.addClause(new int[] {-3, 5});
        solver.addClause(new int[] {-5, 3});

        solver.addSoftClause(new int[] {-1});
        solver.addSoftClause(new int[] {-2});
        solver.addSoftClause(new int[] {-3});
        solver.addSoftClause(new int[] {-4});
        solver.addSoftClause(new int[] {-5});
        solver.addSoftClause(new int[] {-6});

        solver.solve();
        for (int i = 1; i <=6; i++) {
            System.out.println(solver.valueOf(i) ? i : -i);
        }
    }
}

package kodkod.engine.satlab;

import kodkod.util.collections.ReadOnlyIVecInt;
import org.sat4j.maxsat.WeightedMaxSatDecorator;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final List<Clause> cached;
    private int cacheIdx = 0;
    private int maxPriority = 0;

    /**
     * Construct a wrapper for the default maxsat solver.
     */
    public SAT4JMax() {
        solver = new WeightedMaxSatDecorator(org.sat4j.pb.SolverFactory.newDefaultOptimizer());
        wrapper = new ReadOnlyIVecInt();
        sat = null;
        vars = 0;
        clauses = 0;
        cached = new ArrayList<>();
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
    public Boolean isSAT() {
        return sat;
    }

    @Override
    public void incNumberOfClauses() {
        clauses++;
    }

    @Override
    public List<MaxSATSolver.Clause> getCached() {
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
            if (solver == null) {
                return false;
            }
            if (!Boolean.FALSE.equals(sat)) {
                while (cacheIdx < cached.size()) {
                    Clause c = cached.get(cacheIdx);
                    if (c.isSoft())
                        solver.addSoftClause(weights[c.getPriority()], wrapper.wrap(c.getLits()));
                    else
                        solver.addHardClause(wrapper.wrap(c.getLits()));
                    cacheIdx++;
                }
                sat = solver.isSatisfiable();
            }
        } catch (ContradictionException e) {
            sat = Boolean.FALSE;
        } catch (TimeoutException e) {
            throw new RuntimeException("timed out");
        }
        return sat;
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

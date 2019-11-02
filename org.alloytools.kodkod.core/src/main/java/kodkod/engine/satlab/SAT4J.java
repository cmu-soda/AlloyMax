/*
 * Kodkod -- Copyright (c) 2005-present, Emina Torlak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package kodkod.engine.satlab;

import kodkod.util.collections.ReadOnlyIVecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

/**
 * A wrapper class that provides access to the basic funcionality of the MiniSAT
 * solvers (org.sat4j.specs.ISolver) from CRIL.
 *
 * @author Emina Torlak
 */
final class SAT4J implements SATSolver {

    private ISolver               solver;
    private final ReadOnlyIVecInt wrapper;
    private Boolean               sat;
    private int                   vars, clauses;

    /**
     * Constructs a wrapper for the given instance of ISolver.
     *
     * @throws NullPointerException solver = null
     */
    SAT4J(ISolver solver) {
        if (solver == null)
            throw new NullPointerException("solver");
        this.solver = solver;
        this.wrapper = new ReadOnlyIVecInt();
        this.sat = null;
        this.vars = this.clauses = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#numberOfVariables()
     */
    @Override
    public int numberOfVariables() {
        return vars;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#numberOfClauses()
     */
    @Override
    public int numberOfClauses() {
        return clauses;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#addVariables(int)
     */
    @Override
    public void addVariables(int numVars) {
        if (numVars < 0)
            throw new IllegalArgumentException("numVars < 0: " + numVars);
        else if (numVars > 0) {
            vars += numVars;
            solver.newVar(vars);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#addClause(int[])
     */
    @Override
    public boolean addClause(int[] lits) {
        try {
            if (!Boolean.FALSE.equals(sat)) {
                clauses++;
                solver.addClause(wrapper.wrap(lits));
                // for(int lit : lits) {
                // System.out.print(lit + " ");
                // }
                // System.out.println(0);
                return true;
            }

        } catch (ContradictionException e) {
            sat = Boolean.FALSE;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#solve()
     */
    @Override
    public boolean solve() {
        try {
            if (solver == null)
                return false;
            if (!Boolean.FALSE.equals(sat))
                sat = Boolean.valueOf(solver.isSatisfiable());
            return sat;
        } catch (org.sat4j.specs.TimeoutException e) {
            throw new RuntimeException("timed out");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#valueOf(int)
     */
    @Override
    public final boolean valueOf(int variable) {
        if (!Boolean.TRUE.equals(sat))
            throw new IllegalStateException();
        if (variable < 1 || variable > vars)
            throw new IllegalArgumentException(variable + " !in [1.." + vars + "]");
        return solver.model(variable);
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.satlab.SATSolver#free()
     */
    @Override
    public synchronized final void free() {
        sat = Boolean.FALSE;
        solver = null;
    }

    public static void main(String[] args) {
        final SAT4J z = (SAT4J) SATFactory.DefaultSAT4J.instance();
        // z.addVariables(3);
        // int[] clause = {1,2,3};
        // z.addClause(clause);
        // int[] clause1 = {-3};
        // z.addClause(clause1);
        // System.out.println(z.solver.nVars());
        // z.addVariables(4);
        // System.out.println(z.solver.nVars());
        // clause1[0] = 7;
        // z.addClause(clause1);
        z.addVariables(1);
        int[] clause1 = {
                         1
        };
        z.addClause(clause1);
        clause1[0] = -1;
        z.addClause(clause1);

        System.out.println(z.solve());
        // System.out.println(z.variablesThatAre(true, 1, 1));

    }

}

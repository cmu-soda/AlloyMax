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
package kodkod.engine;

import kodkod.ast.*;
import kodkod.ast.operator.Quantifier;
import kodkod.ast.visitor.AbstractCollector;
import kodkod.engine.config.Options;
import kodkod.engine.fol2sat.*;
import kodkod.engine.satlab.MaxSATSolver;
import kodkod.engine.satlab.SATAbortedException;
import kodkod.engine.satlab.SATProver;
import kodkod.engine.satlab.SATSolver;
import kodkod.instance.Bounds;
import kodkod.instance.Instance;
import kodkod.util.ints.IntIterator;
import kodkod.util.ints.IntSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A computational engine for solving relational satisfiability problems. Such a
 * problem is described by a {@link kodkod.ast.Formula formula} in first order
 * relational logic; finite {@link kodkod.instance.Bounds bounds} on the value
 * of each {@link Relation relation} constrained by the formula; and a set of
 * {@link kodkod.engine.config.Options options} specifying, among other global
 * parameters, the length of bitvectors that describe the meaning of
 * {@link IntExpression integer expressions} in the given formula. The options
 * are usually reused between invocations to the
 * {@linkplain #solve(Formula, Bounds) solve} methods, so they are specified as
 * a part of the {@linkplain KodkodSolver solver} state.
 * <p>
 * A {@link Solver} takes as input a relational problem and produces a
 * satisfying model or an {@link Instance instance} of it, if one exists. It can
 * also produce a {@link Proof proof} of unsatisfiability for problems with no
 * models, if the {@link kodkod.engine.config.Options options} specify the use
 * of a {@linkplain SATProver proof logging SAT solver}.
 * </p>
 *
 * @specfield options: Options
 * @author Emina Torlak
 */
public final class Solver implements KodkodSolver {

    /**
     * @author Changjian Zhang
     */
    private static class MaximalityQuantifierFinder extends AbstractCollector<QuantifiedFormula> {

        protected MaximalityQuantifierFinder(Set<Node> cached) {
            super(cached);
        }

        @Override
        protected Set<QuantifiedFormula> newSet() {
            return new LinkedHashSet<>();
        }

        @Override
        public Set<QuantifiedFormula> visit(QuantifiedFormula quantFormula) {
            Set<QuantifiedFormula> ret = lookup(quantFormula);
            if (ret != null)
                return ret;
            ret = newSet();
            if (quantFormula.quantifier() == Quantifier.MAXSOME || quantFormula.quantifier() == Quantifier.MINSOME) {
                ret.add(quantFormula);
            }
            ret.addAll(quantFormula.decls().accept(this));
            ret.addAll(quantFormula.formula().accept(this));
            return cache(quantFormula, ret);
        }
    }

    private final Options options;

    /**
     * Constructs a new Solver with the default options.
     *
     * @ensures this.options' = new Options()
     */
    public Solver() {
        this.options = new Options();
    }

    /**
     * Constructs a new Solver with the given options.
     *
     * @ensures this.options' = options
     * @throws NullPointerException options = null
     */
    public Solver(Options options) {
        if (options == null)
            throw new NullPointerException();
        this.options = options;
    }

    /**
     * Returns the Options object used by this Solver to guide translation of
     * formulas from first-order logic to cnf.
     *
     * @return this.options
     */
    @Override
    public Options options() {
        return options;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.engine.KodkodSolver#free()
     */
    @Override
    public void free() {}

    /**
     * Attempts to satisfy the given {@code formula} and {@code bounds} with respect
     * to {@code this.options} or, optionally, prove the problem's unsatisfiability.
     * If the method completes normally, the result is a {@linkplain Solution
     * solution} containing either an {@linkplain Instance instance} of the given
     * problem or, optionally, a {@linkplain Proof proof} of its unsatisfiability.
     * An unsatisfiability proof will be constructed iff {@code this.options.solver}
     * specifies a {@linkplain SATProver} and
     * {@code this.options.logTranslation > 0}.
     *
     * @return some sol: {@link Solution} | some sol.instance() => sol.instance() in
     *         MODELS(formula, bounds, this.options) else UNSAT(formula, bound,
     *         this.options)
     * @throws NullPointerException formula = null || bounds = null
     * @throws UnboundLeafException the formula contains an undeclared variable or a
     *             relation not mapped by the given bounds
     * @throws HigherOrderDeclException the formula contains a higher order
     *             declaration that cannot be skolemized, or it can be skolemized
     *             but {@code this.options.skolemDepth} is insufficiently large
     * @throws AbortedException this solving task was aborted
     * @see Options
     * @see Solution
     * @see Instance
     * @see Proof
     */
    @Override
    public Solution solve(Formula formula, Bounds bounds) throws HigherOrderDeclException, UnboundLeafException, AbortedException {
        final long startTransl = System.currentTimeMillis();

        try {
            final Translation.Whole translation = Translator.translate(formula, bounds, options);
            final long endTransl = System.currentTimeMillis();

            if (translation.trivial())
                return trivial(translation, endTransl - startTransl);

            final SATSolver cnf = getCNF(formula, translation);

            options.reporter().solvingCNF(translation.numPrimaryVariables(), cnf.numberOfVariables(), cnf.numberOfClauses());
            final long startSolve = System.currentTimeMillis();
            final boolean isSat = cnf.solve();
            final long endSolve = System.currentTimeMillis();

            final Statistics stats = new Statistics(translation, endTransl - startTransl, endSolve - startSolve);
            return isSat ? sat(translation, stats) : unsat(translation, stats);

        } catch (SATAbortedException sae) {
            throw new AbortedException(sae);
        }
    }

    private SATSolver getCNF(Formula formula, Translation.Whole translation) {
        final MaximalityQuantifierFinder finder = new MaximalityQuantifierFinder(new HashSet<>());
        final Set<QuantifiedFormula> quantified = formula.accept(finder);
        if (quantified.size() == 0) {
            return translation.cnf();
        }
        // If contains MAXSOME or MINSOME
        if (!(translation.cnf() instanceof MaxSATSolver)) {
            throw new UnsupportedOperationException("The solver should be a MaxSAT solver!");
        }
        final MaxSATSolver wcnf = (MaxSATSolver) translation.cnf();
        // Add all the primary variables of the corresponding SkolemRelation as soft clauses
        for (QuantifiedFormula q: quantified) {
            for (Decl decl: q.decls()) {
                // Get the primary variables by the name of the relation
                final IntSet vars = translation.primaryVariables("$" + decl.variable().name());
                if (vars == null) {
                    // This is probably caused by the use of MAXSOME or MINSOME quantifier in set comprehension.
                    throw new IllegalArgumentException("No primary variables for declaration: " + decl.variable().name());
                }
                final IntIterator iter = vars.iterator();
                // To reach maximality, let all the primary variables to be true.
                if (q.quantifier() == Quantifier.MAXSOME) {
                    while (iter.hasNext()) {
                        wcnf.addSoftClause(new int[]{iter.next()});
                    }
                }
                // To reach minimality, let all the primary variables to be false.
                else if (q.quantifier() == Quantifier.MINSOME) {
                    while (iter.hasNext()) {
                        wcnf.addSoftClause(new int[] {-iter.next()});
                    }
                }
            }
        }
        return wcnf;
    }

    /**
     * Attempts to find all solutions to the given formula with respect to the
     * specified bounds or to prove the formula's unsatisfiability. If the operation
     * is successful, the method returns an iterator over n Solution objects. The
     * outcome of the first n-1 solutions is SAT or trivially SAT, and the outcome
     * of the nth solution is UNSAT or tirivally UNSAT. Note that an
     * unsatisfiability proof will be constructed for the last solution iff
     * this.options specifies the use of a core extracting SATSolver. Additionally,
     * the CNF variables in the proof can be related back to the nodes in the given
     * formula iff this.options has variable tracking enabled. Translation logging
     * also requires that there are no subnodes in the given formula that are both
     * syntactically shared and contain free variables.
     *
     * @return an iterator over all the Solutions to the formula with respect to the
     *         given bounds
     * @throws NullPointerException formula = null || bounds = null
     * @throws kodkod.engine.fol2sat.UnboundLeafException the formula contains an
     *             undeclared variable or a relation not mapped by the given bounds
     * @throws kodkod.engine.fol2sat.HigherOrderDeclException the formula contains a
     *             higher order declaration that cannot be skolemized, or it can be
     *             skolemized but this.options.skolemize is false.
     * @throws AbortedException this solving task was interrupted with a call to
     *             Thread.interrupt on this thread
     * @throws IllegalStateException !this.options.solver().incremental()
     * @see Solution
     * @see Options
     * @see Proof
     */
    @Override
    public Iterator<Solution> solveAll(final Formula formula, final Bounds bounds) throws HigherOrderDeclException, UnboundLeafException, AbortedException {
        // if (Options.isDebug()) flushFormula(formula, bounds); //[AM]
        if (!options.solver().incremental())
            throw new IllegalArgumentException("cannot enumerate solutions without an incremental solver.");
        return new SolutionIterator(formula, bounds, options);
    }

    // //[AM]
    // private void flushFormula(Formula formula, Bounds bounds) {
    // try {
    // File f = new File(System.getProperty("java.io.tmpdir"), "kk.txt");
    // OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
    // os.write(PrettyPrinter.print(formula, 2).getBytes());
    // os.write("\n================\n".getBytes());
    // os.write(bounds.toString().getBytes());
    // os.flush();
    // os.close();
    // } catch (Exception e) {
    // }
    // }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return options.toString();
    }

    /**
     * Returns the result of solving a sat formula.
     *
     * @param translation the translation
     * @param stats translation / solving stats
     * @return the result of solving a sat formula.
     */
    private static Solution sat(Translation.Whole translation, Statistics stats) {
        final Solution sol = Solution.satisfiable(stats, translation.interpret());
        translation.cnf().free();
        return sol;
    }

    /**
     * Returns the result of solving an unsat formula.
     *
     * @param translation the translation
     * @param stats translation / solving stats
     * @return the result of solving an unsat formula.
     */
    static Solution unsat(Translation.Whole translation, Statistics stats) {
        final SATSolver cnf = translation.cnf();
        final TranslationLog log = translation.log();
        if (cnf instanceof SATProver && log != null) {
            return Solution.unsatisfiable(stats, new ResolutionBasedProof((SATProver) cnf, log));
        } else { // can free memory
            final Solution sol = Solution.unsatisfiable(stats, null);
            cnf.free();
            return sol;
        }
    }

    /**
     * Returns the result of solving a trivially (un)sat formula.
     *
     * @param translation trivial translation produced as the result of
     *            {@code translation.formula} simplifying to a constant with respect
     *            to {@code translation.bounds}
     * @param translTime translation time
     * @return the result of solving a trivially (un)sat formula.
     */
    static Solution trivial(Translation.Whole translation, long translTime) {
        final Statistics stats = new Statistics(0, 0, 0, translTime, 0);
        final Solution sol;
        if (translation.cnf().solve()) {
            sol = Solution.triviallySatisfiable(stats, translation.interpret());
        } else {
            sol = Solution.triviallyUnsatisfiable(stats, trivialProof(translation.log()));
        }
        translation.cnf().free();
        return sol;
    }

    /**
     * Returns a proof for the trivially unsatisfiable log.formula, provided that
     * log is non-null. Otherwise returns null.
     *
     * @requires log != null => log.formula is trivially unsatisfiable
     * @return a proof for the trivially unsatisfiable log.formula, provided that
     *         log is non-null. Otherwise returns null.
     */
    private static Proof trivialProof(TranslationLog log) {
        return log == null ? null : new TrivialProof(log);
    }

    public static Solution solveTranslation(final long translTime, final Translation translation) {
        final Solution solution;
        if (translation.trivial()) {
            final Statistics stats = new Statistics(translation, translTime, 0);
            if (translation.cnf().solve()) {
                solution = Solution.triviallySatisfiable(stats, translation.interpret());
            } else {
                solution = Solution.triviallyUnsatisfiable(stats, null);
            }
        } else {
            final SATSolver cnf = translation.cnf();

            translation.options().reporter().solvingCNF(translation.numPrimaryVariables(), cnf.numberOfVariables(), cnf.numberOfClauses());
            final long startSolve = System.currentTimeMillis();
            final boolean sat = cnf.solve();
            final long endSolve = System.currentTimeMillis();

            final Statistics stats = new Statistics(translation, translTime, endSolve - startSolve);
            if (sat) {
                solution = Solution.satisfiable(stats, translation.interpret());
            } else {
                solution = Solution.unsatisfiable(stats, null);
            }
        }
        return solution;
    }
}

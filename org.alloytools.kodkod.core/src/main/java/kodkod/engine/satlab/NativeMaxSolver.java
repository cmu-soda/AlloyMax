package kodkod.engine.satlab;

import java.util.ArrayList;
import java.util.List;

abstract class NativeMaxSolver implements MaxSATSolver {

    protected long                  peer;
    protected Boolean               sat = null;
    private int                     clauses = 0, vars = 0;
    protected final List<Clause>    cached = new ArrayList<>();
    protected int                   cacheIdx = 0;
    private int                     maxPriority = 0;

    NativeMaxSolver(long peer) {
        this.peer = peer;
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
            addVariables(peer, numVars);
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
    public List<Clause> getCached() {
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
        if (peer == 0)
            return false;
        if (!Boolean.FALSE.equals(sat)) {
            while (cacheIdx < cached.size()) {
                Clause c = cached.get(cacheIdx);
                if (c.isSoft()) {
                    addSoftClauses(peer, weights[c.getPriority()], c.getLits());
                } else {
                    addHardClause(peer, c.getLits());
                }
                cacheIdx++;
            }
            sat = solve(peer);
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
        return valueOf(peer, variable);
    }

    @Override
    public void free() {
        if (peer != 0) {
            free(peer);
            sat = Boolean.FALSE;
            peer = 0;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        free();
    }

    abstract void addVariables(long peer, int numVariables);

    abstract boolean addHardClause(long peer, int[] lits);

    abstract boolean addSoftClauses(long peer, int weight, int[] lits);

    abstract boolean solve(long peer);

    abstract boolean valueOf(long peer, int literal);

    abstract void free(long peer);
}

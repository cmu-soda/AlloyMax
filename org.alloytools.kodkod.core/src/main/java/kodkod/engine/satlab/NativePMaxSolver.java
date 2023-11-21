package kodkod.engine.satlab;

abstract class NativePMaxSolver extends NativeMaxSolver implements PMaxSatSolver {

    private int group = 1;

    NativePMaxSolver(long peer) {
        super(peer);
    }

    @Override
    public int currentGroup() {
        return group;
    }

    @Override
    public void addGroup() {
        group++;
    }

    @Override
    public boolean actualSolve(int[] weights) {
        if (peer == 0)
            return false;
        if (!Boolean.FALSE.equals(sat)) {
            while (cacheIdx < cached.size()) {
                PClause c = (PClause) cached.get(cacheIdx);
                if (c.isSoft()) {
                    addSoftClauses(peer, c.getGroup(), weights[c.getPriority()], c.getLits());
                } else {
                    addHardClause(peer, c.getGroup(), c.getLits());
                }
                cacheIdx++;
            }
            sat = solve(peer);
        }
        return sat;
    }

    abstract boolean addHardClause(long peer, int group, int[] lits);

    abstract boolean addSoftClauses(long peer, int group, int weight, int[] lits);
}

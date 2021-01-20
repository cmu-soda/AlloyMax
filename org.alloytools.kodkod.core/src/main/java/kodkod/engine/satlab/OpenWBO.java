package kodkod.engine.satlab;

final class OpenWBO extends NativeMaxSolver {

    public OpenWBO() {
        super(make());
    }

    static {
        System.loadLibrary("openwbo");
    }

    @Override
    public String toString() {
        return "OpenWBO";
    }

    private static native long make();

    @Override
    native void free(long peer);

    @Override
    native void addVariables(long peer, int numVariables);

    @Override
    native boolean addHardClause(long peer, int[] lits);

    @Override
    native boolean addSoftClauses(long peer, int weight, int[] lits);

    @Override
    native boolean solve(long peer);

    @Override
    native boolean valueOf(long peer, int literal);
}

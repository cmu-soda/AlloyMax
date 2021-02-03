package edu.mit.csail.sdg.alloy4whole;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Expr;
import edu.mit.csail.sdg.ast.ExprVar;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.A4TupleSet;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkMain {

    private final String satFilename;
    private final String maxsatFilename;
//    private final List<String> exprs;
//    private List<Object> evals;
    private volatile int instCount = 0;

    BenchmarkMain(String sat, String maxsat) {
        this.satFilename = sat;
        this.maxsatFilename = maxsat;
//        this.exprs = exprs;

        coldstart();
    }

    private A4Options myOption() {
        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = false;
        options.inferPartialInstance = true;
        return options;
    }

    private void coldstart() {
        A4Reporter rep = new A4Reporter() {};
        Module world = CompUtil.parseEverything_fromString(rep, "");

        A4Options options = myOption();
        options.solver = A4Options.SatSolver.Glucose41JNI;

        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
    }

    public void solveMaxSat(long startTime) {
        A4Reporter rep = new MyRep(startTime);
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsatFilename);

        A4Options options = myOption();
        options.solver = A4Options.SatSolver.OpenWBO;

        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
//        evals = new ArrayList<>(exprs.size());
//        for (ExprVar a : ans.getAllAtoms())
//            world.addGlobal(a.label, a);
//        for (ExprVar a : ans.getAllSkolems())
//            world.addGlobal(a.label, a);
//        for (String s : exprs) {
//            Expr e = CompUtil.parseOneExpression_fromString(world, s);
//            Object r = ans.eval(e);
//            if (!(r instanceof A4TupleSet))
//                throw new Error("Currently only supports comparing TupleSet");
//            System.out.println(s + ": " + ((A4TupleSet) r).size());
//            evals.add(r);
//        }
    }

    public void solveMaxSatPartition(long startTime) {
//        assert (evals != null);

        A4Reporter rep = new MyRep(startTime);
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsatFilename);

        A4Options options = myOption();
        options.solver = A4Options.SatSolver.POpenWBO;

        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
//        for (ExprVar a : ans.getAllAtoms())
//            world.addGlobal(a.label, a);
//        for (ExprVar a : ans.getAllSkolems())
//            world.addGlobal(a.label, a);
//        for (int i = 0; i < exprs.size(); i++) {
//            Expr e = CompUtil.parseOneExpression_fromString(world, exprs.get(i));
//            Object r = ans.eval(e);
//            if (((A4TupleSet) r).size() != ((A4TupleSet) evals.get(i)).size())
//                throw new Error("The result from OpenWBO-Partition does not match with it from OpenWBO");
//        }
    }

    public void solveSatEnum(long startTime) {
//        assert (evals != null);

        A4Reporter rep = new MyRep(startTime);
        Module world = CompUtil.parseEverything_fromFile(rep, null, satFilename);

        A4Options options = myOption();
        options.solver = A4Options.SatSolver.Glucose41JNI;

        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
        while (ans.satisfiable()) {
            ans = ans.next();
            ++instCount;
        }
        System.out.println("Enumeration number: " + instCount);
//        boolean optimal = false;
//        while (!optimal) {
//            for (ExprVar a : ans.getAllAtoms())
//                world.addGlobal(a.label, a);
//            for (ExprVar a : ans.getAllSkolems())
//                world.addGlobal(a.label, a);
//            boolean matched = true;
//            for (int i = 0; i < exprs.size(); i++) {
//                Expr e = CompUtil.parseOneExpression_fromString(world, exprs.get(i));
//                Object r = ans.eval(e);
//                if (((A4TupleSet) r).size() != ((A4TupleSet) evals.get(i)).size()) {
//                    matched = false;
//                    break;
//                }
//            }
//            optimal = matched;
//            if (!optimal) {
//                ans = ans.next();
//            }
//        }
    }

    private static class MyRep extends A4Reporter {
        private long startSolveTime = 0;
        private long startTime = 0;

        MyRep(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void translate(String solver, int bitwidth, int maxseq, int skolemDepth, int symmetry) {
            System.out.println("Solver: " + solver + ", bitwidth: " + bitwidth + ", maxseq: " + maxseq + ", skolemdepth: " + skolemDepth + " symmetry: " + symmetry);
        }

        @Override
        public void warning(ErrorWarning msg) {
            System.out.println(msg.getMessage());
        }

        @Override
        public void solve(int primaryVars, int totalVars, int clauses) {
            long transTime = System.currentTimeMillis() - startTime;
            System.out.println("CNF generated. Primary vars: " + primaryVars + ", Total variables: " + totalVars + ", Total clauses: " + clauses);
            System.out.println("Translation time: " + transTime);
            startSolveTime = System.currentTimeMillis();
        }

        @Override
        public void resultSAT(Object command, long solvingTime, Object solution) {
            long solveTime = System.currentTimeMillis() - startSolveTime;
            System.out.println("Solved: SAT");
            System.out.println("Solve time: " + solveTime);
        }

        @Override
        public void resultUNSAT(Object command, long solvingTime, Object solution) {
            long solveTime = System.currentTimeMillis() - startSolveTime;
            System.out.println("Solved: UNSAT");
            System.out.println("Solve time: " + solveTime);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: -sat=<filename> -maxsat=<filename> -p");
        System.exit(-1);
    }

    public static void main(String[] args) {
        String sat = null;
        String maxsat = null;
        boolean partition = false;
//        List<String> exprs = new LinkedList<>();

        for (String arg : args) {
//            if (arg.startsWith("-e"))
//                exprs.add(arg.substring(3));
//            else
            if (arg.startsWith("-sat"))
                sat = arg.substring(5);
            else if (arg.startsWith("-maxsat"))
                maxsat = arg.substring(8);
            else if (arg.equals("-p"))
                partition = true;
            else
                printUsage();
        }

        if (sat == null && maxsat == null /*|| exprs.isEmpty() */)
            printUsage();

        final BenchmarkMain benchmark = new BenchmarkMain(sat, maxsat);
        if (sat != null) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Enumeration number: " + benchmark.instCount)));
            // Enumerating Sat problem
            long startTime = System.currentTimeMillis();
            System.out.println("=============================\nEnumerate the Sat problem to get all solutions...");
            benchmark.solveSatEnum(startTime);
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
        }

        if (maxsat != null) {
            long startTime = System.currentTimeMillis();
            if (partition) {
                System.out.println("=============================\nSolve the MaxSat problem with partitions...");
                benchmark.solveMaxSatPartition(startTime);
            } else {
                System.out.println("=============================\nSolve the MaxSat problem...");
                benchmark.solveMaxSat(startTime);
            }
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
        }
    }

}

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

    BenchmarkMain(String sat, String maxsat) {
        this.satFilename = sat;
        this.maxsatFilename = maxsat;
//        this.exprs = exprs;

        coldstart();
    }

    private void coldstart() {
        A4Reporter rep = new A4Reporter() {};
        Module world = CompUtil.parseEverything_fromString(rep, "");

        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = true;
        options.inferPartialInstance = false;
        options.solver = A4Options.SatSolver.Glucose41JNI;

        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
    }

    public void solveMaxSat() {
        A4Reporter rep = new A4Reporter() {};
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsatFilename);

        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = true;
        options.inferPartialInstance = false;
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

    public void solveMaxSatPartition() {
//        assert (evals != null);

        A4Reporter rep = new A4Reporter() {};
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsatFilename);

        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = true;
        options.inferPartialInstance = false;
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

    public void solveSatEnum() {
//        assert (evals != null);

        A4Reporter rep = new A4Reporter() {};
        Module world = CompUtil.parseEverything_fromFile(rep, null, satFilename);

        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = true;
        options.inferPartialInstance = false;
        options.solver = A4Options.SatSolver.Glucose41JNI;

        int count = 0;
        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
        while (ans.satisfiable()) {
            ans = ans.next();
            count++;
        }
        System.out.println("Enumeration number: " + count);
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

    private class MyRep extends A4Reporter {
        private long lastTime = 0;
        private long startTime = 0;

        @Override
        public void translate(String solver, int bitwidth, int maxseq, int skolemDepth, int symmetry) {
            System.out.println("Solver: " + solver + ", bitwidth: " + bitwidth + ", maxseq: " + maxseq + ", skolemdepth: " + skolemDepth + " symmetry: " + symmetry);
            startTime = lastTime = System.currentTimeMillis();
        }

        @Override
        public void warning(ErrorWarning msg) {
            System.out.println(msg.getMessage());
        }

        @Override
        public void solve(int primaryVars, int totalVars, int clauses) {
            System.out.println("CNF generated. Primary vars: " + primaryVars + ", Total variables: " + totalVars + ", Total clauses: " + clauses + ". " + (System.currentTimeMillis() - lastTime) + "ms.");
            lastTime = System.currentTimeMillis();
        }

        @Override
        public void resultSAT(Object command, long solvingTime, Object solution) {
            long t = System.currentTimeMillis();
            System.out.println("Solved: SAT, time: " + (t - lastTime) + "ms.");
            System.out.println("Total time: " + (t - startTime) + "ms.");
        }

        @Override
        public void resultUNSAT(Object command, long solvingTime, Object solution) {
            long t = System.currentTimeMillis();
            System.out.println("Solved: UNSAT, time: " + (t - lastTime) + "ms.");
            System.out.println("Total time: " + (t - startTime) + "ms.");
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

        BenchmarkMain benchmark = new BenchmarkMain(sat, maxsat);
        if (sat != null) {
            // Enumerating Sat problem
            long startTime = System.currentTimeMillis();
            System.out.println("=============================\nEnumerate the Sat problem to get all solutions...");
            benchmark.solveSatEnum();
            System.out.println("Enumeration time: " + (System.currentTimeMillis() - startTime));
        }

        if (maxsat != null) {
            long startTime = System.currentTimeMillis();
            if (partition) {
                System.out.println("=============================\nSolve the MaxSat problem with partitions...");
                benchmark.solveMaxSatPartition();
                System.out.println("MaxSat-Partition time: " + (System.currentTimeMillis() - startTime));
            } else {
                System.out.println("=============================\nSolve the MaxSat problem...");
                benchmark.solveMaxSat();
                System.out.println("MaxSat time: " + (System.currentTimeMillis() - startTime));
            }
        }
    }

}

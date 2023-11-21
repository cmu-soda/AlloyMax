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

    public void generateCNF(long startTime, boolean maxsat, boolean partition) {
        A4Reporter rep = new MyRep(startTime);
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsat ? maxsatFilename : satFilename);

        A4Options options = myOption();
        if (maxsat) {
            if (partition)
                options.solver = A4Options.SatSolver.PWCNF;
            else
                options.solver = A4Options.SatSolver.WCNF;
        } else {
            options.solver = A4Options.SatSolver.CNF;
        }

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

    public void solveMaxSatPartition(long startTime, boolean auto) {
//        assert (evals != null);

        A4Reporter rep = new MyRep(startTime);
        Module world = CompUtil.parseEverything_fromFile(rep, null, maxsatFilename);

        A4Options options = myOption();
        if (auto)
            options.solver = A4Options.SatSolver.POpenWBOAuto;
        else
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

    public void solveMaxSatAll(long startTime) {
        generateCNF(startTime, true, false);
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

        @Override
        public void resultCNF(String filename) {
            System.out.println("CNF File: " + filename);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: -sat=<filename> -maxsat=<filename> -all-opt -p -auto -file-only");
        System.exit(-1);
    }

    public static void main(String[] args) {
        String sat = null;
        String maxsat = null;
        boolean partition = false;
        boolean autoPartition = false;
        boolean allopt = false;
        boolean fileOnly = false;
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
            else if (arg.equals("-auto"))
                autoPartition = true;
            else if (arg.equals("-all-opt"))
                allopt = true;
            else if (arg.equals("-file-only"))
                fileOnly = true;
            else
                printUsage();
        }

        if (sat == null && maxsat == null /*|| exprs.isEmpty() */)
            printUsage();

        final BenchmarkMain benchmark = new BenchmarkMain(sat, maxsat);
        if (sat != null) {
            // Enumerating Sat problem
            long startTime = System.currentTimeMillis();
            System.out.println("=============================\nEnumerate the Sat problem to get all solutions...");
            if (fileOnly) {
                benchmark.generateCNF(startTime, false, false);
            } else {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Enumeration number: " + benchmark.instCount)));
                benchmark.solveSatEnum(startTime);
                System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            }
        }

        if (maxsat != null) {
            long startTime = System.currentTimeMillis();
            if (!allopt) {
                if (partition) {
                    System.out.println("=============================\nSolve the MaxSat problem with partitions...");
                    if (fileOnly) {
                        if (autoPartition)
                            benchmark.generateCNF(startTime, true, false);
                        else
                            benchmark.generateCNF(startTime, true, true);
                    } else {
                        benchmark.solveMaxSatPartition(startTime, autoPartition);
                        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
                    }
                } else {
                    System.out.println("=============================\nSolve the MaxSat problem...");
                    if (fileOnly) {
                        benchmark.generateCNF(startTime, true, false);
                    } else {
                        benchmark.solveMaxSat(startTime);
                        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
                    }
                }
            } else {
                System.out.println("=============================\nFind all optimal solutions...");
                benchmark.solveMaxSatAll(startTime);
            }
        }
    }

}

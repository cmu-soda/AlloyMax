package edu.mit.csail.sdg.alloy4whole;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

public class OpenWBOCLI {

    private static void printUsage() {
        System.out.println("Usage: [-p] <filename>");
        System.exit(-1);
    }

    public static void main(String[] args) {
        boolean partitions = false;
        String filename = null;

        if (args.length == 1) {
            filename = args[0];
        } else if (args.length == 2) {
            if (args[0].equals("-p"))
                partitions = true;
            else {
                printUsage();
            }
            filename = args[1];
        } else {
            printUsage();
        }

        A4Reporter rep = new A4Reporter() {

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
        };

        Module world = CompUtil.parseEverything_fromFile(rep, null, filename);
        A4Options options = new A4Options();
        options.skolemDepth = 1;
        options.noOverflow = true;
        options.inferPartialInstance = false;
        if (partitions)
            options.solver = A4Options.SatSolver.POpenWBO;
        else
            options.solver = A4Options.SatSolver.OpenWBO;
        // Get the first command only
        Command c = world.getAllCommands().get(0);
        A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), c, options);
//        try {
//            Field solverField = ans.getClass().getDeclaredField("solver");
//            solverField.setAccessible(true);
//            Solver solver = (Solver) solverField.get(ans);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}

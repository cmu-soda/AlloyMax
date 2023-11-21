package tests;

import kodkod.ast.Relation;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AlloyMaxBug {
    public static void main(String[] args) {
        Relation nodes = Relation.unary("Nodes");
        Relation edges = Relation.binary("Edges");

        List<String> atoms = Arrays.asList("a", "b", "c", "d", "e");
        Universe universe = new Universe(atoms);
        TupleFactory f = universe.factory();

        TupleSet nodesTS = f.setOf(atoms.stream().map(f::tuple).collect(Collectors.toList()));
        Bounds bounds = new Bounds(universe);
        //bounds.bound(nodes, f.noneOf(1), nodesTS);
        bounds.bound(nodes, f.setOf("a"), nodesTS);
        bounds.bound(edges, nodesTS.product(nodesTS));

        Solver solver = new Solver();
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);

        // Breaks with assertions on
        Solution sol = solver.solve(edges.no(), bounds);
        System.out.println(sol.instance());

        // Breaks with a non-empty lower bound
//        Solution sol = solver.solve(nodes.minSome(), bounds);
//        System.out.println(sol.instance());
    }
}

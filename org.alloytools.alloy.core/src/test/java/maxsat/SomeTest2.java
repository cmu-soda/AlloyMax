package maxsat;

import java.util.Arrays;
import java.util.List;
import kodkod.ast.*;
import kodkod.ast.operator.*;
import kodkod.instance.*;
import kodkod.engine.*;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

/*
  ==================================================
    kodkod formula:
  ==================================================
    (all this: this/A |
      (this . this/A.r) in (this/B -> this/C)) &&
    ((this/A.r . univ) . univ) in this/A &&
    (some a: this/A |
      some (a . this/A.r)) &&
    Int/min = Int/min &&
    Int/zero = Int/zero &&
    Int/max = Int/max &&
    Int/next = Int/next &&
    seq/Int = seq/Int &&
    String = String &&
    this/A = this/A &&
    this/B = this/B &&
    this/C = this/C &&
    this/A.r = this/A.r
  ==================================================
*/
public final class SomeTest2 {

    public static void main(String[] args) throws Exception {

        Relation x0 = Relation.unary("Int/min");
        Relation x1 = Relation.unary("Int/zero");
        Relation x2 = Relation.unary("Int/max");
        Relation x3 = Relation.nary("Int/next", 2);
        Relation x4 = Relation.unary("seq/Int");
        Relation x5 = Relation.unary("String");
        Relation x6 = Relation.unary("this/A");
        Relation x7 = Relation.unary("this/B");
        Relation x8 = Relation.unary("this/C");
        Relation x9 = Relation.nary("this/A.r", 3);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "A$0",
                "B$0", "B$1", "C$0", "C$1", "unused0"
        );

        Universe universe = new Universe(atomlist);
        TupleFactory factory = universe.factory();
        Bounds bounds = new Bounds(universe);

        TupleSet x0_upper = factory.noneOf(1);
        x0_upper.add(factory.tuple("-8"));
        bounds.boundExactly(x0, x0_upper);

        TupleSet x1_upper = factory.noneOf(1);
        x1_upper.add(factory.tuple("0"));
        bounds.boundExactly(x1, x1_upper);

        TupleSet x2_upper = factory.noneOf(1);
        x2_upper.add(factory.tuple("7"));
        bounds.boundExactly(x2, x2_upper);

        TupleSet x3_upper = factory.noneOf(2);
        x3_upper.add(factory.tuple("-8").product(factory.tuple("-7")));
        x3_upper.add(factory.tuple("-7").product(factory.tuple("-6")));
        x3_upper.add(factory.tuple("-6").product(factory.tuple("-5")));
        x3_upper.add(factory.tuple("-5").product(factory.tuple("-4")));
        x3_upper.add(factory.tuple("-4").product(factory.tuple("-3")));
        x3_upper.add(factory.tuple("-3").product(factory.tuple("-2")));
        x3_upper.add(factory.tuple("-2").product(factory.tuple("-1")));
        x3_upper.add(factory.tuple("-1").product(factory.tuple("0")));
        x3_upper.add(factory.tuple("0").product(factory.tuple("1")));
        x3_upper.add(factory.tuple("1").product(factory.tuple("2")));
        x3_upper.add(factory.tuple("2").product(factory.tuple("3")));
        x3_upper.add(factory.tuple("3").product(factory.tuple("4")));
        x3_upper.add(factory.tuple("4").product(factory.tuple("5")));
        x3_upper.add(factory.tuple("5").product(factory.tuple("6")));
        x3_upper.add(factory.tuple("6").product(factory.tuple("7")));
        bounds.boundExactly(x3, x3_upper);

        TupleSet x4_upper = factory.noneOf(1);
        x4_upper.add(factory.tuple("0"));
        x4_upper.add(factory.tuple("1"));
        bounds.boundExactly(x4, x4_upper);

        TupleSet x5_upper = factory.noneOf(1);
        bounds.boundExactly(x5, x5_upper);

        TupleSet x6_upper = factory.noneOf(1);
        x6_upper.add(factory.tuple("unused0"));
        x6_upper.add(factory.tuple("A$0"));
        bounds.bound(x6, x6_upper);

        TupleSet x7_upper = factory.noneOf(1);
        x7_upper.add(factory.tuple("B$0"));
        x7_upper.add(factory.tuple("B$1"));
        bounds.bound(x7, x7_upper);

        TupleSet x8_upper = factory.noneOf(1);
        x8_upper.add(factory.tuple("C$0"));
        x8_upper.add(factory.tuple("C$1"));
        bounds.bound(x8, x8_upper);

        TupleSet x9_upper = factory.noneOf(3);
        x9_upper.add(factory.tuple("unused0").product(factory.tuple("B$0")).product(factory.tuple("C$0")));
        x9_upper.add(factory.tuple("unused0").product(factory.tuple("B$0")).product(factory.tuple("C$1")));
        x9_upper.add(factory.tuple("unused0").product(factory.tuple("B$1")).product(factory.tuple("C$0")));
        x9_upper.add(factory.tuple("unused0").product(factory.tuple("B$1")).product(factory.tuple("C$1")));
        x9_upper.add(factory.tuple("A$0").product(factory.tuple("B$0")).product(factory.tuple("C$0")));
        x9_upper.add(factory.tuple("A$0").product(factory.tuple("B$0")).product(factory.tuple("C$1")));
        x9_upper.add(factory.tuple("A$0").product(factory.tuple("B$1")).product(factory.tuple("C$0")));
        x9_upper.add(factory.tuple("A$0").product(factory.tuple("B$1")).product(factory.tuple("C$1")));
        bounds.bound(x9, x9_upper);

        bounds.boundExactly(-8,factory.range(factory.tuple("-8"),factory.tuple("-8")));
        bounds.boundExactly(-7,factory.range(factory.tuple("-7"),factory.tuple("-7")));
        bounds.boundExactly(-6,factory.range(factory.tuple("-6"),factory.tuple("-6")));
        bounds.boundExactly(-5,factory.range(factory.tuple("-5"),factory.tuple("-5")));
        bounds.boundExactly(-4,factory.range(factory.tuple("-4"),factory.tuple("-4")));
        bounds.boundExactly(-3,factory.range(factory.tuple("-3"),factory.tuple("-3")));
        bounds.boundExactly(-2,factory.range(factory.tuple("-2"),factory.tuple("-2")));
        bounds.boundExactly(-1,factory.range(factory.tuple("-1"),factory.tuple("-1")));
        bounds.boundExactly(0,factory.range(factory.tuple("0"),factory.tuple("0")));
        bounds.boundExactly(1,factory.range(factory.tuple("1"),factory.tuple("1")));
        bounds.boundExactly(2,factory.range(factory.tuple("2"),factory.tuple("2")));
        bounds.boundExactly(3,factory.range(factory.tuple("3"),factory.tuple("3")));
        bounds.boundExactly(4,factory.range(factory.tuple("4"),factory.tuple("4")));
        bounds.boundExactly(5,factory.range(factory.tuple("5"),factory.tuple("5")));
        bounds.boundExactly(6,factory.range(factory.tuple("6"),factory.tuple("6")));
        bounds.boundExactly(7,factory.range(factory.tuple("7"),factory.tuple("7")));

        Variable x13=Variable.unary("this");
        Decls x12=x13.oneOf(x6);
        Expression x15=x13.join(x9);
        Expression x16=x7.product(x8);
        Formula x14=x15.in(x16);
        Formula x11=x14.forAll(x12);
        Expression x19=x9.join(Expression.UNIV);
        Expression x18=x19.join(Expression.UNIV);
        Formula x17=x18.in(x6);
        Variable x23=Variable.unary("a");
        Decls x22=x23.oneOf(x6);
        Expression x25=x23.join(x9);
        Formula x24=x25.maxSome();
        Formula x21=x24.forSome(x22);
        Formula x26=x0.eq(x0);
        Formula x27=x1.eq(x1);
        Formula x28=x2.eq(x2);
        Formula x29=x3.eq(x3);
        Formula x30=x4.eq(x4);
        Formula x31=x5.eq(x5);
        Formula x32=x6.eq(x6);
        Formula x33=x7.eq(x7);
        Formula x34=x8.eq(x8);
        Formula x35=x9.eq(x9);
        Formula x10=Formula.compose(FormulaOperator.AND, x11, x17, x21, x26, x27, x28, x29, x30, x31, x32, x33, x34, x35);

        Solver solver = new Solver();
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(1);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x10,bounds);
        System.out.println(sol.toString());
    }
}


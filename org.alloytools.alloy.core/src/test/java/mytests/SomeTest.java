package mytests;

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
      (this . this/A.r) in this/B) &&
    (this/A.r . univ) in this/A &&
    some this/A.r &&
    Int/min = Int/min &&
    Int/zero = Int/zero &&
    Int/max = Int/max &&
    Int/next = Int/next &&
    seq/Int = seq/Int &&
    String = String &&
    this/A = this/A &&
    this/B = this/B &&
    this/A.r = this/A.r
  ==================================================
*/
public final class SomeTest {

    public static void main(String[] args) throws Exception {

        Relation x0 = Relation.unary("Int/min");
        Relation x1 = Relation.unary("Int/zero");
        Relation x2 = Relation.unary("Int/max");
        Relation x3 = Relation.nary("Int/next", 2);
        Relation x4 = Relation.unary("seq/Int");
        Relation x5 = Relation.unary("String");
        Relation x6 = Relation.unary("this/A");
        Relation x7 = Relation.unary("this/B");
        Relation x8 = Relation.nary("this/A.r", 2);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "A$0",
                "B$0", "B$1", "unused0"
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
        x4_upper.add(factory.tuple("2"));
        x4_upper.add(factory.tuple("3"));
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

        TupleSet x8_upper = factory.noneOf(2);
        x8_upper.add(factory.tuple("unused0").product(factory.tuple("B$0")));
        x8_upper.add(factory.tuple("unused0").product(factory.tuple("B$1")));
        x8_upper.add(factory.tuple("A$0").product(factory.tuple("B$0")));
        x8_upper.add(factory.tuple("A$0").product(factory.tuple("B$1")));
        bounds.bound(x8, x8_upper);

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

        Variable x12=Variable.unary("this");
        Decls x11=x12.oneOf(x6);
        Expression x14=x12.join(x8);
        Formula x13=x14.in(x7);
        Formula x10=x13.forAll(x11);
        Expression x16=x8.join(Expression.UNIV);
        Formula x15=x16.in(x6);
        Formula x18=x8.maxSome();
        Formula x19=x0.eq(x0);
        Formula x20=x1.eq(x1);
        Formula x21=x2.eq(x2);
        Formula x22=x3.eq(x3);
        Formula x23=x4.eq(x4);
        Formula x24=x5.eq(x5);
        Formula x25=x6.eq(x6);
        Formula x26=x7.eq(x7);
        Formula x27=x8.eq(x8);
        Formula x9=Formula.compose(FormulaOperator.AND, x10, x15, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27);

        Solver solver = new Solver();
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(1);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x9,bounds);
        System.out.println(sol.toString());
    }
}

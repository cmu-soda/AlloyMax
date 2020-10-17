package maxsat;

import kodkod.ast.*;
import kodkod.ast.operator.FormulaOperator;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.config.Options;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
abstract sig Role {} abstract sig Action {} abstract sig Resource {}

// Define atoms in the universe
one sig Faculty, Student, TA extends Role {}
one sig IntGrade, ExtGrade extends Resource {}
one sig Assign, Receive extends Action {}

pred valid[policy: Role -> Action -> Resource] {
	  // no student can assign external grade
	  Student -> Assign -> IntGrade not in policy
    // no user can both receive and assign external grades
    no r: Role | (Assign + Receive) in r.policy.ExtGrade
}

run {
    maxsome policy: Role -> Action -> Resource | valid[policy]
}

*/

/*
  ==================================================
    kodkod formula:
  ==================================================
    no (this/IntGrade & this/ExtGrade) &&
    no (this/Faculty & this/Student) &&
    no ((this/Faculty + this/Student) & this/TA) &&
    no (this/Assign & this/Receive) &&
    (some valid_policy: set this/Course -> (this/Faculty + this/Student +
     this/TA) -> (this/Assign + this/Receive) -> (this/IntGrade + this/ExtGrade) |
      valid_policy in (this/Course -> (this/Faculty + this/Student + this/TA) ->
      (this/Assign + this/Receive) -> (this/IntGrade + this/ExtGrade)) &&
      (all valid_c: this/Course |
        !((this/Student -> this/Assign -> this/IntGrade) in (valid_c .
          valid_policy)) &&
        (all valid_r: this/Faculty + this/Student + this/TA |
          !((this/Assign + this/Receive) in ((valid_r . (valid_c . valid_policy)
            ) . this/ExtGrade))))) &&
    Int/min = Int/min &&
    Int/zero = Int/zero &&
    Int/max = Int/max &&
    Int/next = Int/next &&
    seq/Int = seq/Int &&
    String = String &&
    this/IntGrade = this/IntGrade &&
    this/ExtGrade = this/ExtGrade &&
    this/Faculty = this/Faculty &&
    this/Student = this/Student &&
    this/TA = this/TA &&
    this/Assign = this/Assign &&
    this/Receive = this/Receive &&
    this/Course = this/Course
  ==================================================
*/
public final class GradeSysTest {

    public static void main(String[] args) throws Exception {

        Relation x0 = Relation.unary("Int/min");
        Relation x1 = Relation.unary("Int/zero");
        Relation x2 = Relation.unary("Int/max");
        Relation x3 = Relation.nary("Int/next", 2);
        Relation x4 = Relation.unary("seq/Int");
        Relation x5 = Relation.unary("String");
        Relation x6 = Relation.unary("this/IntGrade");
        Relation x7 = Relation.unary("this/ExtGrade");
        Relation x8 = Relation.unary("this/Faculty");
        Relation x9 = Relation.unary("this/Student");
        Relation x10 = Relation.unary("this/TA");
        Relation x11 = Relation.unary("this/Assign");
        Relation x12 = Relation.unary("this/Receive");
        Relation x13 = Relation.unary("this/Course");

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "Assign$0",
                "ExtGrade$0", "Faculty$0", "IntGrade$0", "Receive$0", "Student$0", "TA$0",
                "Course$0"
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
        bounds.boundExactly(x4, x4_upper);

        TupleSet x5_upper = factory.noneOf(1);
        bounds.boundExactly(x5, x5_upper);

        TupleSet x6_upper = factory.noneOf(1);
        x6_upper.add(factory.tuple("IntGrade$0"));
        bounds.boundExactly(x6, x6_upper);

        TupleSet x7_upper = factory.noneOf(1);
        x7_upper.add(factory.tuple("ExtGrade$0"));
        bounds.boundExactly(x7, x7_upper);

        TupleSet x8_upper = factory.noneOf(1);
        x8_upper.add(factory.tuple("Faculty$0"));
        bounds.boundExactly(x8, x8_upper);

        TupleSet x9_upper = factory.noneOf(1);
        x9_upper.add(factory.tuple("Student$0"));
        bounds.boundExactly(x9, x9_upper);

        TupleSet x10_upper = factory.noneOf(1);
        x10_upper.add(factory.tuple("TA$0"));
        bounds.boundExactly(x10, x10_upper);

        TupleSet x11_upper = factory.noneOf(1);
        x11_upper.add(factory.tuple("Assign$0"));
        bounds.boundExactly(x11, x11_upper);

        TupleSet x12_upper = factory.noneOf(1);
        x12_upper.add(factory.tuple("Receive$0"));
        bounds.boundExactly(x12, x12_upper);

        TupleSet x13_upper = factory.noneOf(1);
        x13_upper.add(factory.tuple("Course$0"));
        bounds.bound(x13, x13_upper);

        bounds.boundExactly(-8, factory.range(factory.tuple("-8"), factory.tuple("-8")));
        bounds.boundExactly(-7, factory.range(factory.tuple("-7"), factory.tuple("-7")));
        bounds.boundExactly(-6, factory.range(factory.tuple("-6"), factory.tuple("-6")));
        bounds.boundExactly(-5, factory.range(factory.tuple("-5"), factory.tuple("-5")));
        bounds.boundExactly(-4, factory.range(factory.tuple("-4"), factory.tuple("-4")));
        bounds.boundExactly(-3, factory.range(factory.tuple("-3"), factory.tuple("-3")));
        bounds.boundExactly(-2, factory.range(factory.tuple("-2"), factory.tuple("-2")));
        bounds.boundExactly(-1, factory.range(factory.tuple("-1"), factory.tuple("-1")));
        bounds.boundExactly(0, factory.range(factory.tuple("0"), factory.tuple("0")));
        bounds.boundExactly(1, factory.range(factory.tuple("1"), factory.tuple("1")));
        bounds.boundExactly(2, factory.range(factory.tuple("2"), factory.tuple("2")));
        bounds.boundExactly(3, factory.range(factory.tuple("3"), factory.tuple("3")));
        bounds.boundExactly(4, factory.range(factory.tuple("4"), factory.tuple("4")));
        bounds.boundExactly(5, factory.range(factory.tuple("5"), factory.tuple("5")));
        bounds.boundExactly(6, factory.range(factory.tuple("6"), factory.tuple("6")));
        bounds.boundExactly(7, factory.range(factory.tuple("7"), factory.tuple("7")));

        Expression x16 = x6.intersection(x7);
        Formula x15 = x16.no();
        Expression x18 = x8.intersection(x9);
        Formula x17 = x18.no();
        Expression x21 = x8.union(x9);
        Expression x20 = x21.intersection(x10);
        Formula x19 = x20.no();
        Expression x23 = x11.intersection(x12);
        Formula x22 = x23.no();
        Variable x26 = Variable.nary("valid_policy", 4);
        Expression x29 = x21.union(x10);
        Expression x31 = x11.union(x12);
        Expression x32 = x6.union(x7);
        Expression x30 = x31.product(x32);
        Expression x28 = x29.product(x30);
        Expression x27 = x13.product(x28);
        Decls x25 = x26.setOf(x27);
        Expression x37 = x31.product(x32);
        Expression x36 = x29.product(x37);
        Expression x35 = x13.product(x36);
        Formula x34 = x26.in(x35);
        Variable x40 = Variable.unary("valid_c");
        Decls x39 = x40.oneOf(x13);
        Expression x45 = x11.product(x6);
        Expression x44 = x9.product(x45);
        Expression x46 = x40.join(x26);
        Formula x43 = x44.in(x46);
        Formula x42 = x43.not();
        Variable x49 = Variable.unary("valid_r");
        Decls x48 = x49.oneOf(x29);
        Expression x52 = x11.union(x12);
        Expression x55 = x40.join(x26);
        Expression x54 = x49.join(x55);
        Expression x53 = x54.join(x7);
        Formula x51 = x52.in(x53);
        Formula x50 = x51.not();
        Formula x47 = x50.forAll(x48);
        Formula x41 = x42.and(x47);
        Formula x38 = x41.forAll(x39);
        Formula x33 = x34.and(x38);
        Formula x24 = x33.forMaxSome(x25);
        Formula x56 = x0.eq(x0);
        Formula x57 = x1.eq(x1);
        Formula x58 = x2.eq(x2);
        Formula x59 = x3.eq(x3);
        Formula x60 = x4.eq(x4);
        Formula x61 = x5.eq(x5);
        Formula x62 = x6.eq(x6);
        Formula x63 = x7.eq(x7);
        Formula x64 = x8.eq(x8);
        Formula x65 = x9.eq(x9);
        Formula x66 = x10.eq(x10);
        Formula x67 = x11.eq(x11);
        Formula x68 = x12.eq(x12);
        Formula x69 = x13.eq(x13);
        Formula x14 = Formula.compose(FormulaOperator.AND, x15, x17, x19, x22, x24, x56, x57, x58, x59, x60, x61, x62, x63, x64, x65, x66, x67, x68, x69);

        Solver solver = new Solver();
//        solver.options().setSolver(SATFactory.DefaultSAT4J);
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
//    solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(1);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x14, bounds);
        System.out.println(sol.toString());
        TupleSet set = sol.instance().tuples("$valid_policy");
        assertNotNull(set);
        final Iterator<Tuple> iter = set.iterator();
        assertEquals("[Course$0, Faculty$0, Assign$0, ExtGrade$0]", iter.next().toString());
        assertEquals("[Course$0, Faculty$0, Assign$0, IntGrade$0]", iter.next().toString());
        assertEquals("[Course$0, Faculty$0, Receive$0, IntGrade$0]", iter.next().toString());
        assertEquals("[Course$0, Student$0, Receive$0, ExtGrade$0]", iter.next().toString());
        assertEquals("[Course$0, Student$0, Receive$0, IntGrade$0]", iter.next().toString());
        assertEquals("[Course$0, TA$0, Assign$0, ExtGrade$0]", iter.next().toString());
        assertEquals("[Course$0, TA$0, Assign$0, IntGrade$0]", iter.next().toString());
        assertEquals("[Course$0, TA$0, Receive$0, IntGrade$0]", iter.next().toString());
    }
}


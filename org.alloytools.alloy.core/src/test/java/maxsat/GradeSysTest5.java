package maxsat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kodkod.ast.*;
import kodkod.ast.operator.*;
import kodkod.instance.*;
import kodkod.engine.*;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*

abstract sig Role {}
abstract sig Action {}
abstract sig Resource {}
abstract sig Course {
	policy: Role -> Action -> Resource
}

one sig Faculty, Student, TA extends Role {}
one sig IntGrade, ExtGrade extends Resource {}
one sig Assign, Receive extends Action {}
one sig Math, Physics extends Course {}

run {
	all c: Course | Student -> Assign -> IntGrade !in c.policy
	Math -> TA -> Assign -> IntGrade !in policy
	maxsome cs: set Course, rs: set Role | all c: cs, r: rs |
		r -> Assign -> IntGrade in c.policy
}

  ==================================================
    kodkod formula:
  ==================================================
    no (this/Faculty & this/Student) &&
    no ((this/Faculty + this/Student) & this/TA) &&
    no (this/Assign & this/Receive) &&
    no (this/IntGrade & this/ExtGrade) &&
    no (this/Math & this/Physics) &&
    (all this: this/Math + this/Physics |
      (this . this/Course.policy) in ((this/Faculty + this/Student + this/TA) ->
      (this/Assign + this/Receive) -> (this/IntGrade + this/ExtGrade))) &&
    (((this/Course.policy . univ) . univ) . univ) in (this/Math + this/Physics) &&
    (all c: this/Math + this/Physics |
      !((this/Student -> this/Assign -> this/IntGrade) in (c .
        this/Course.policy))) &&
    !((this/Math -> this/TA -> this/Assign -> this/IntGrade) in
      this/Course.policy) &&
    (some cs: set this/Math + this/Physics, rs: set this/Faculty + this/Student +
     this/TA |
      all c: cs, r: rs |
       (r -> this/Assign -> this/IntGrade) in (c . this/Course.policy)) &&
    Int/min = Int/min &&
    Int/zero = Int/zero &&
    Int/max = Int/max &&
    Int/next = Int/next &&
    seq/Int = seq/Int &&
    String = String &&
    this/Faculty = this/Faculty &&
    this/Student = this/Student &&
    this/TA = this/TA &&
    this/Assign = this/Assign &&
    this/Receive = this/Receive &&
    this/IntGrade = this/IntGrade &&
    this/ExtGrade = this/ExtGrade &&
    this/Math = this/Math &&
    this/Physics = this/Physics &&
    this/Course.policy = this/Course.policy
  ==================================================
*/
public final class GradeSysTest5 {

    public static void main(String[] args) throws Exception {

        Relation x0 = Relation.unary("Int/min");
        Relation x1 = Relation.unary("Int/zero");
        Relation x2 = Relation.unary("Int/max");
        Relation x3 = Relation.nary("Int/next", 2);
        Relation x4 = Relation.unary("seq/Int");
        Relation x5 = Relation.unary("String");
        Relation x6 = Relation.unary("this/Faculty");
        Relation x7 = Relation.unary("this/Student");
        Relation x8 = Relation.unary("this/TA");
        Relation x9 = Relation.unary("this/Assign");
        Relation x10 = Relation.unary("this/Receive");
        Relation x11 = Relation.unary("this/IntGrade");
        Relation x12 = Relation.unary("this/ExtGrade");
        Relation x13 = Relation.unary("this/Math");
        Relation x14 = Relation.unary("this/Physics");
        Relation x15 = Relation.nary("this/Course.policy", 4);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "Assign$0",
                "ExtGrade$0", "Faculty$0", "IntGrade$0", "Math$0", "Physics$0", "Receive$0",
                "Student$0", "TA$0"
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
        x6_upper.add(factory.tuple("Faculty$0"));
        bounds.boundExactly(x6, x6_upper);

        TupleSet x7_upper = factory.noneOf(1);
        x7_upper.add(factory.tuple("Student$0"));
        bounds.boundExactly(x7, x7_upper);

        TupleSet x8_upper = factory.noneOf(1);
        x8_upper.add(factory.tuple("TA$0"));
        bounds.boundExactly(x8, x8_upper);

        TupleSet x9_upper = factory.noneOf(1);
        x9_upper.add(factory.tuple("Assign$0"));
        bounds.boundExactly(x9, x9_upper);

        TupleSet x10_upper = factory.noneOf(1);
        x10_upper.add(factory.tuple("Receive$0"));
        bounds.boundExactly(x10, x10_upper);

        TupleSet x11_upper = factory.noneOf(1);
        x11_upper.add(factory.tuple("IntGrade$0"));
        bounds.boundExactly(x11, x11_upper);

        TupleSet x12_upper = factory.noneOf(1);
        x12_upper.add(factory.tuple("ExtGrade$0"));
        bounds.boundExactly(x12, x12_upper);

        TupleSet x13_upper = factory.noneOf(1);
        x13_upper.add(factory.tuple("Math$0"));
        bounds.boundExactly(x13, x13_upper);

        TupleSet x14_upper = factory.noneOf(1);
        x14_upper.add(factory.tuple("Physics$0"));
        bounds.boundExactly(x14, x14_upper);

        TupleSet x15_upper = factory.noneOf(4);
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Student$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Student$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Student$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("Student$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("TA$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("TA$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("TA$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Math$0").product(factory.tuple("TA$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Faculty$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Student$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Student$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Student$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("Student$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("TA$0")).product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("TA$0")).product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("TA$0")).product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x15_upper.add(factory.tuple("Physics$0").product(factory.tuple("TA$0")).product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        bounds.bound(x15, x15_upper);

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

        Expression x18=x6.intersection(x7);
        Formula x17=x18.no();
        Expression x21=x6.union(x7);
        Expression x20=x21.intersection(x8);
        Formula x19=x20.no();
        Expression x23=x9.intersection(x10);
        Formula x22=x23.no();
        Expression x25=x11.intersection(x12);
        Formula x24=x25.no();
        Expression x27=x13.intersection(x14);
        Formula x26=x27.no();
        Variable x30=Variable.unary("this");
        Expression x31=x13.union(x14);
        Decls x29=x30.oneOf(x31);
        Expression x33=x30.join(x15);
        Expression x35=x21.union(x8);
        Expression x37=x9.union(x10);
        Expression x38=x11.union(x12);
        Expression x36=x37.product(x38);
        Expression x34=x35.product(x36);
        Formula x32=x33.in(x34);
        Formula x28=x32.forAll(x29);
        Expression x42=x15.join(Expression.UNIV);
        Expression x41=x42.join(Expression.UNIV);
        Expression x40=x41.join(Expression.UNIV);
        Formula x39=x40.in(x31);
        Variable x46=Variable.unary("c");
        Decls x45=x46.oneOf(x31);
        Expression x50=x9.product(x11);
        Expression x49=x7.product(x50);
        Expression x51=x46.join(x15);
        Formula x48=x49.in(x51);
        Formula x47=x48.not();
        Formula x44=x47.forAll(x45);
        Expression x56=x9.product(x11);
        Expression x55=x8.product(x56);
        Expression x54=x13.product(x55);
        Formula x53=x54.in(x15);
        Formula x52=x53.not();
        Variable x60=Variable.unary("cs");
        Decls x59=x60.setOf(x31);
        Variable x62=Variable.unary("rs");
        Decls x61=x62.setOf(x35);
        Decls x58=x59.and(x61);
        Variable x66=Variable.unary("c");
        Decls x65=x66.oneOf(x60);
        Variable x68=Variable.unary("r");
        Decls x67=x68.oneOf(x62);
        Decls x64=x65.and(x67);
        Expression x71=x9.product(x11);
        Expression x70=x68.product(x71);
        Expression x72=x66.join(x15);
        Formula x69=x70.in(x72);
        Formula x63=x69.forAll(x64);
        Formula x57=x63.forMaxSome(x58);
        Formula x73=x0.eq(x0);
        Formula x74=x1.eq(x1);
        Formula x75=x2.eq(x2);
        Formula x76=x3.eq(x3);
        Formula x77=x4.eq(x4);
        Formula x78=x5.eq(x5);
        Formula x79=x6.eq(x6);
        Formula x80=x7.eq(x7);
        Formula x81=x8.eq(x8);
        Formula x82=x9.eq(x9);
        Formula x83=x10.eq(x10);
        Formula x84=x11.eq(x11);
        Formula x85=x12.eq(x12);
        Formula x86=x13.eq(x13);
        Formula x87=x14.eq(x14);
        Formula x88=x15.eq(x15);
        Formula x16=Formula.compose(FormulaOperator.AND, x17, x19, x22, x24, x26, x28, x39, x44, x52, x57, x73, x74, x75, x76, x77, x78, x79, x80, x81, x82, x83, x84, x85, x86, x87, x88);

        Solver solver = new Solver();
//        solver.options().setSolver(SATFactory.DefaultSAT4J);
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(1);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x16,bounds);
        System.out.println(sol.toString());
        TupleSet set = sol.instance().tuples("$cs");
        assertNotNull(set);
        Iterator<Tuple> iter = set.iterator();
        assertEquals("[Math$0]", iter.next().toString());
        assertEquals("[Physics$0]", iter.next().toString());

        set = sol.instance().tuples("$rs");
        assertNotNull(set);
        iter = set.iterator();
        assertEquals("[Faculty$0]", iter.next().toString());
    }
}


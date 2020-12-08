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
Alloy formula:

abstract sig Student {
	cores: set Course,
	courses: set Course
}

abstract sig Course {
	time: one Time
}

abstract sig Time {}

one sig Alice, Bob extends Student {}
one sig Models, Architecture, Management extends Course {}
one sig Morning, Afternoon extends Time {}

// Set the time for the courses.
fact {
	time = (Models -> Morning) + (Architecture -> Morning) + (Management -> Afternoon)
}


// Set the cores for the students
fact {
	cores = (Alice -> Models) + (Alice -> Architecture) +
					(Bob -> Models) + (Bob -> Management)
}

// Bob only wants to take courses in the afternoon
fact LazyBob {
	Bob.courses.time in Afternoon
}

pred MustTakeCores[s: Student] {
	s.cores in s.courses
}

run {
	all s: Student | MustTakeCores[s]
}

 */

/*
  ==================================================
    kodkod formula:
  ==================================================
    no (this/Alice & this/Bob) &&
    no (this/Models & this/Architecture) &&
    no ((this/Models + this/Architecture) & this/Management) &&
    no (this/Morning & this/Afternoon) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.cores) in (this/Models + this/Architecture +
      this/Management)) &&
    (this/Student.cores . univ) in (this/Alice + this/Bob) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.courses) in (this/Models + this/Architecture +
      this/Management)) &&
    (this/Student.courses . univ) in (this/Alice + this/Bob) &&
    (all this: this/Models + this/Architecture + this/Management |
      one (this . this/Course.time) &&
      (this . this/Course.time) in (this/Morning + this/Afternoon)) &&
    (this/Course.time . univ) in (this/Models + this/Architecture +
    this/Management) &&
    this/Course.time = ((this/Models -> this/Morning) + (this/Architecture ->
    this/Morning) + (this/Management -> this/Afternoon)) &&
    this/Student.cores = ((this/Alice -> this/Models) + (this/Alice ->
    this/Architecture) + (this/Bob -> this/Models) + (this/Bob ->
    this/Management)) &&
    ((this/Bob . this/Student.courses) . this/Course.time) in this/Afternoon &&
    (all s: this/Alice + this/Bob |
      (s . this/Student.cores) in (s . this/Student.courses)) &&
    Int/min = Int/min &&
    Int/zero = Int/zero &&
    Int/max = Int/max &&
    Int/next = Int/next &&
    seq/Int = seq/Int &&
    String = String &&
    this/Alice = this/Alice &&
    this/Bob = this/Bob &&
    this/Models = this/Models &&
    this/Architecture = this/Architecture &&
    this/Management = this/Management &&
    this/Morning = this/Morning &&
    this/Afternoon = this/Afternoon &&
    this/Student.cores = this/Student.cores &&
    this/Student.courses = this/Student.courses &&
    this/Course.time = this/Course.time
  ==================================================
*/
public final class CourseSchedule {

    public static void main(String[] args) throws Exception {

        Relation x0 = Relation.unary("Int/min");
        Relation x1 = Relation.unary("Int/zero");
        Relation x2 = Relation.unary("Int/max");
        Relation x3 = Relation.nary("Int/next", 2);
        Relation x4 = Relation.unary("seq/Int");
        Relation x5 = Relation.unary("String");
        Relation x6 = Relation.unary("this/Alice");
        Relation x7 = Relation.unary("this/Bob");
        Relation x8 = Relation.unary("this/Models");
        Relation x9 = Relation.unary("this/Architecture");
        Relation x10 = Relation.unary("this/Management");
        Relation x11 = Relation.unary("this/Morning");
        Relation x12 = Relation.unary("this/Afternoon");
        Relation x13 = Relation.nary("this/Student.cores", 2);
        Relation x14 = Relation.nary("this/Student.courses", 2);
        Relation x15 = Relation.nary("this/Course.time", 2);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "Afternoon$0",
                "Alice$0", "Architecture$0", "Bob$0", "Management$0", "Models$0", "Morning$0"
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
        x6_upper.add(factory.tuple("Alice$0"));
        bounds.boundExactly(x6, x6_upper);

        TupleSet x7_upper = factory.noneOf(1);
        x7_upper.add(factory.tuple("Bob$0"));
        bounds.boundExactly(x7, x7_upper);

        TupleSet x8_upper = factory.noneOf(1);
        x8_upper.add(factory.tuple("Models$0"));
        bounds.boundExactly(x8, x8_upper);

        TupleSet x9_upper = factory.noneOf(1);
        x9_upper.add(factory.tuple("Architecture$0"));
        bounds.boundExactly(x9, x9_upper);

        TupleSet x10_upper = factory.noneOf(1);
        x10_upper.add(factory.tuple("Management$0"));
        bounds.boundExactly(x10, x10_upper);

        TupleSet x11_upper = factory.noneOf(1);
        x11_upper.add(factory.tuple("Morning$0"));
        bounds.boundExactly(x11, x11_upper);

        TupleSet x12_upper = factory.noneOf(1);
        x12_upper.add(factory.tuple("Afternoon$0"));
        bounds.boundExactly(x12, x12_upper);

        TupleSet x13_upper = factory.noneOf(2);
        x13_upper.add(factory.tuple("Alice$0").product(factory.tuple("Models$0")));
        x13_upper.add(factory.tuple("Alice$0").product(factory.tuple("Architecture$0")));
        x13_upper.add(factory.tuple("Alice$0").product(factory.tuple("Management$0")));
        x13_upper.add(factory.tuple("Bob$0").product(factory.tuple("Models$0")));
        x13_upper.add(factory.tuple("Bob$0").product(factory.tuple("Architecture$0")));
        x13_upper.add(factory.tuple("Bob$0").product(factory.tuple("Management$0")));
        bounds.bound(x13, x13_upper);

        TupleSet x14_upper = factory.noneOf(2);
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Models$0")));
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Architecture$0")));
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Management$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Models$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Architecture$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Management$0")));
        bounds.bound(x14, x14_upper);

        TupleSet x15_upper = factory.noneOf(2);
        x15_upper.add(factory.tuple("Models$0").product(factory.tuple("Morning$0")));
        x15_upper.add(factory.tuple("Models$0").product(factory.tuple("Afternoon$0")));
        x15_upper.add(factory.tuple("Architecture$0").product(factory.tuple("Morning$0")));
        x15_upper.add(factory.tuple("Architecture$0").product(factory.tuple("Afternoon$0")));
        x15_upper.add(factory.tuple("Management$0").product(factory.tuple("Morning$0")));
        x15_upper.add(factory.tuple("Management$0").product(factory.tuple("Afternoon$0")));
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
        Expression x20=x8.intersection(x9);
        Formula x19=x20.no();
        Expression x23=x8.union(x9);
        Expression x22=x23.intersection(x10);
        Formula x21=x22.no();
        Expression x25=x11.intersection(x12);
        Formula x24=x25.no();
        Variable x28=Variable.unary("this");
        Expression x29=x6.union(x7);
        Decls x27=x28.oneOf(x29);
        Expression x31=x28.join(x13);
        Expression x32=x23.union(x10);
        Formula x30=x31.in(x32);
        Formula x26=x30.forAll(x27);
        Expression x34=x13.join(Expression.UNIV);
        Formula x33=x34.in(x29);
        Variable x38=Variable.unary("this");
        Decls x37=x38.oneOf(x29);
        Expression x40=x38.join(x14);
        Formula x39=x40.in(x32);
        Formula x36=x39.forAll(x37);
        Expression x42=x14.join(Expression.UNIV);
        Formula x41=x42.in(x29);
        Variable x45=Variable.unary("this");
        Decls x44=x45.oneOf(x32);
        Expression x48=x45.join(x15);
        Formula x47=x48.one();
        Expression x50=x11.union(x12);
        Formula x49=x48.in(x50);
        Formula x46=x47.and(x49);
        Formula x43=x46.forAll(x44);
        Expression x52=x15.join(Expression.UNIV);
        Formula x51=x52.in(x32);
        Expression x56=x8.product(x11);
        Expression x57=x9.product(x11);
        Expression x55=x56.union(x57);
        Expression x58=x10.product(x12);
        Expression x54=x55.union(x58);
        Formula x53=x15.eq(x54);
        Expression x63=x6.product(x8);
        Expression x64=x6.product(x9);
        Expression x62=x63.union(x64);
        Expression x65=x7.product(x8);
        Expression x61=x62.union(x65);
        Expression x66=x7.product(x10);
        Expression x60=x61.union(x66);
        Formula x59=x13.eq(x60);
        Expression x69=x7.join(x14);
        Expression x68=x69.join(x15);
        Formula x67=x68.in(x12);
        x67.setSoft(true, 0);  // Set ((this/Bob . this/Student.courses) . this/Course.time) in this/Afternoon to soft constraint
        Variable x72=Variable.unary("s");
        Decls x71=x72.oneOf(x29);
        Expression x74=x72.join(x13);
        Expression x75=x72.join(x14);
        Formula x73=x74.in(x75);
        Formula x70=x73.forAll(x71);
        Formula x76=x0.eq(x0);
        Formula x77=x1.eq(x1);
        Formula x78=x2.eq(x2);
        Formula x79=x3.eq(x3);
        Formula x80=x4.eq(x4);
        Formula x81=x5.eq(x5);
        Formula x82=x6.eq(x6);
        Formula x83=x7.eq(x7);
        Formula x84=x8.eq(x8);
        Formula x85=x9.eq(x9);
        Formula x86=x10.eq(x10);
        Formula x87=x11.eq(x11);
        Formula x88=x12.eq(x12);
        Formula x89=x13.eq(x13);
        Formula x90=x14.eq(x14);
        Formula x91=x15.eq(x15);
        Formula x16=Formula.compose(FormulaOperator.AND, x17, x19, x21, x24, x26, x33, x36, x41, x43, x51, x53, x59, x67, x70, x76, x77, x78, x79, x80, x81, x82, x83, x84, x85, x86, x87, x88, x89, x90, x91);

        Solver solver = new Solver();
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setLogTranslation(2);
        solver.options().setCoreGranularity(0);
        solver.options().setBitwidth(4);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(0);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x16,bounds);
        System.out.println(sol.toString());
    }
}

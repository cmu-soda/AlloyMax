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
abstract sig Student {
	cores: set Course,
	courses: set Course,
	afternooncourses: set Course
} {
	afternooncourses = {c : courses | c.time in Afternoon}
}

abstract sig Course {
	time: one Time
}

abstract sig Time {}

one sig Alice, Bob extends Student {}
one sig Models, Architecture, Management, Requirements extends Course {}
one sig Morning, Afternoon extends Time {}

// Set the time for the courses.
fact {
	time = (Models -> Morning) + (Architecture -> Morning) + (Management -> Afternoon) + (Requirements -> Afternoon)
}


// Set the cores for the students
fact {
	cores = (Alice -> Models) + (Alice -> Architecture) +
					(Bob -> Models) + (Bob -> Management)
}

pred MustTakeCores[s: Student] {
	s.cores in s.courses
}

run {
	all s: Student | MustTakeCores[s] and maxsome Bob.afternooncourses
}


  ==================================================
    kodkod formula:
  ==================================================
    no (this/Alice & this/Bob) &&
    no (this/Models & this/Architecture) &&
    no ((this/Models + this/Architecture) & this/Management) &&
    no ((this/Models + this/Architecture + this/Management) & this/Requirements) &&
    no (this/Morning & this/Afternoon) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.cores) in (this/Models + this/Architecture +
      this/Management + this/Requirements)) &&
    (this/Student.cores . univ) in (this/Alice + this/Bob) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.courses) in (this/Models + this/Architecture +
      this/Management + this/Requirements)) &&
    (this/Student.courses . univ) in (this/Alice + this/Bob) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.afternooncourses) in (this/Models + this/Architecture +
      this/Management + this/Requirements)) &&
    (this/Student.afternooncourses . univ) in (this/Alice + this/Bob) &&
    (all this: this/Alice + this/Bob |
      (this . this/Student.afternooncourses) = {c: this . this/Student.courses |
      (c . this/Course.time) in this/Afternoon}) &&
    (all this: this/Models + this/Architecture + this/Management +
     this/Requirements |
      one (this . this/Course.time) &&
      (this . this/Course.time) in (this/Morning + this/Afternoon)) &&
    (this/Course.time . univ) in (this/Models + this/Architecture +
    this/Management + this/Requirements) &&
    this/Course.time = ((this/Models -> this/Morning) + (this/Architecture ->
    this/Morning) + (this/Management -> this/Afternoon) + (this/Requirements ->
    this/Afternoon)) &&
    this/Student.cores = ((this/Alice -> this/Models) + (this/Alice ->
    this/Architecture) + (this/Bob -> this/Models) + (this/Bob ->
    this/Management)) &&
    (all s: this/Alice + this/Bob |
      (s . this/Student.cores) in (s . this/Student.courses) &&
      maxsome (this/Bob . this/Student.afternooncourses)) &&
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
    this/Requirements = this/Requirements &&
    this/Morning = this/Morning &&
    this/Afternoon = this/Afternoon &&
    this/Student.cores = this/Student.cores &&
    this/Student.courses = this/Student.courses &&
    this/Student.afternooncourses = this/Student.afternooncourses &&
    this/Course.time = this/Course.time
  ==================================================
*/
public final class CourseSchedule2 {

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
        Relation x11 = Relation.unary("this/Requirements");
        Relation x12 = Relation.unary("this/Morning");
        Relation x13 = Relation.unary("this/Afternoon");
        Relation x14 = Relation.nary("this/Student.cores", 2);
        Relation x15 = Relation.nary("this/Student.courses", 2);
        Relation x16 = Relation.nary("this/Student.afternooncourses", 2);
        Relation x17 = Relation.nary("this/Course.time", 2);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "Afternoon$0",
                "Alice$0", "Architecture$0", "Bob$0", "Management$0", "Models$0", "Morning$0",
                "Requirements$0"
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
        x11_upper.add(factory.tuple("Requirements$0"));
        bounds.boundExactly(x11, x11_upper);

        TupleSet x12_upper = factory.noneOf(1);
        x12_upper.add(factory.tuple("Morning$0"));
        bounds.boundExactly(x12, x12_upper);

        TupleSet x13_upper = factory.noneOf(1);
        x13_upper.add(factory.tuple("Afternoon$0"));
        bounds.boundExactly(x13, x13_upper);

        TupleSet x14_upper = factory.noneOf(2);
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Models$0")));
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Architecture$0")));
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Management$0")));
        x14_upper.add(factory.tuple("Alice$0").product(factory.tuple("Requirements$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Models$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Architecture$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Management$0")));
        x14_upper.add(factory.tuple("Bob$0").product(factory.tuple("Requirements$0")));
        bounds.bound(x14, x14_upper);

        TupleSet x15_upper = factory.noneOf(2);
        x15_upper.add(factory.tuple("Alice$0").product(factory.tuple("Models$0")));
        x15_upper.add(factory.tuple("Alice$0").product(factory.tuple("Architecture$0")));
        x15_upper.add(factory.tuple("Alice$0").product(factory.tuple("Management$0")));
        x15_upper.add(factory.tuple("Alice$0").product(factory.tuple("Requirements$0")));
        x15_upper.add(factory.tuple("Bob$0").product(factory.tuple("Models$0")));
        x15_upper.add(factory.tuple("Bob$0").product(factory.tuple("Architecture$0")));
        x15_upper.add(factory.tuple("Bob$0").product(factory.tuple("Management$0")));
        x15_upper.add(factory.tuple("Bob$0").product(factory.tuple("Requirements$0")));
        bounds.bound(x15, x15_upper);

        TupleSet x16_upper = factory.noneOf(2);
        x16_upper.add(factory.tuple("Alice$0").product(factory.tuple("Models$0")));
        x16_upper.add(factory.tuple("Alice$0").product(factory.tuple("Architecture$0")));
        x16_upper.add(factory.tuple("Alice$0").product(factory.tuple("Management$0")));
        x16_upper.add(factory.tuple("Alice$0").product(factory.tuple("Requirements$0")));
        x16_upper.add(factory.tuple("Bob$0").product(factory.tuple("Models$0")));
        x16_upper.add(factory.tuple("Bob$0").product(factory.tuple("Architecture$0")));
        x16_upper.add(factory.tuple("Bob$0").product(factory.tuple("Management$0")));
        x16_upper.add(factory.tuple("Bob$0").product(factory.tuple("Requirements$0")));
        bounds.bound(x16, x16_upper);

        TupleSet x17_upper = factory.noneOf(2);
        x17_upper.add(factory.tuple("Models$0").product(factory.tuple("Morning$0")));
        x17_upper.add(factory.tuple("Models$0").product(factory.tuple("Afternoon$0")));
        x17_upper.add(factory.tuple("Architecture$0").product(factory.tuple("Morning$0")));
        x17_upper.add(factory.tuple("Architecture$0").product(factory.tuple("Afternoon$0")));
        x17_upper.add(factory.tuple("Management$0").product(factory.tuple("Morning$0")));
        x17_upper.add(factory.tuple("Management$0").product(factory.tuple("Afternoon$0")));
        x17_upper.add(factory.tuple("Requirements$0").product(factory.tuple("Morning$0")));
        x17_upper.add(factory.tuple("Requirements$0").product(factory.tuple("Afternoon$0")));
        bounds.bound(x17, x17_upper);

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

        Expression x20=x6.intersection(x7);
        Formula x19=x20.no();
        Expression x22=x8.intersection(x9);
        Formula x21=x22.no();
        Expression x25=x8.union(x9);
        Expression x24=x25.intersection(x10);
        Formula x23=x24.no();
        Expression x28=x25.union(x10);
        Expression x27=x28.intersection(x11);
        Formula x26=x27.no();
        Expression x30=x12.intersection(x13);
        Formula x29=x30.no();
        Variable x33=Variable.unary("this");
        Expression x34=x6.union(x7);
        Decls x32=x33.oneOf(x34);
        Expression x36=x33.join(x14);
        Expression x37=x28.union(x11);
        Formula x35=x36.in(x37);
        Formula x31=x35.forAll(x32);
        Expression x39=x14.join(Expression.UNIV);
        Formula x38=x39.in(x34);
        Variable x43=Variable.unary("this");
        Decls x42=x43.oneOf(x34);
        Expression x45=x43.join(x15);
        Formula x44=x45.in(x37);
        Formula x41=x44.forAll(x42);
        Expression x47=x15.join(Expression.UNIV);
        Formula x46=x47.in(x34);
        Variable x50=Variable.unary("this");
        Decls x49=x50.oneOf(x34);
        Expression x52=x50.join(x16);
        Formula x51=x52.in(x37);
        Formula x48=x51.forAll(x49);
        Expression x54=x16.join(Expression.UNIV);
        Formula x53=x54.in(x34);
        Variable x57=Variable.unary("this");
        Decls x56=x57.oneOf(x34);
        Expression x59=x57.join(x16);
        Variable x62=Variable.unary("c");
        Expression x63=x57.join(x15);
        Decls x61=x62.oneOf(x63);
        Expression x65=x62.join(x17);
        Formula x64=x65.in(x13);
        Expression x60=x64.comprehension(x61);
        Formula x58=x59.eq(x60);
        Formula x55=x58.forAll(x56);
        Variable x68=Variable.unary("this");
        Decls x67=x68.oneOf(x37);
        Expression x71=x68.join(x17);
        Formula x70=x71.one();
        Expression x73=x12.union(x13);
        Formula x72=x71.in(x73);
        Formula x69=x70.and(x72);
        Formula x66=x69.forAll(x67);
        Expression x75=x17.join(Expression.UNIV);
        Formula x74=x75.in(x37);
        Expression x80=x8.product(x12);
        Expression x81=x9.product(x12);
        Expression x79=x80.union(x81);
        Expression x82=x10.product(x13);
        Expression x78=x79.union(x82);
        Expression x83=x11.product(x13);
        Expression x77=x78.union(x83);
        Formula x76=x17.eq(x77);
        Expression x88=x6.product(x8);
        Expression x89=x6.product(x9);
        Expression x87=x88.union(x89);
        Expression x90=x7.product(x8);
        Expression x86=x87.union(x90);
        Expression x91=x7.product(x10);
        Expression x85=x86.union(x91);
        Formula x84=x14.eq(x85);
        Variable x94=Variable.unary("s");
        Decls x93=x94.oneOf(x34);
        Expression x97=x94.join(x14);
        Expression x98=x94.join(x15);
        Formula x96=x97.in(x98);
        Expression x100=x7.join(x16);
        Formula x99=x100.maxSome();
        Formula x95=x96.and(x99);
        Formula x92=x95.forAll(x93);
        Formula x101=x0.eq(x0);
        Formula x102=x1.eq(x1);
        Formula x103=x2.eq(x2);
        Formula x104=x3.eq(x3);
        Formula x105=x4.eq(x4);
        Formula x106=x5.eq(x5);
        Formula x107=x6.eq(x6);
        Formula x108=x7.eq(x7);
        Formula x109=x8.eq(x8);
        Formula x110=x9.eq(x9);
        Formula x111=x10.eq(x10);
        Formula x112=x11.eq(x11);
        Formula x113=x12.eq(x12);
        Formula x114=x13.eq(x13);
        Formula x115=x14.eq(x14);
        Formula x116=x15.eq(x15);
        Formula x117=x16.eq(x16);
        Formula x118=x17.eq(x17);
        Formula x18=Formula.compose(FormulaOperator.AND, x19, x21, x23, x26, x29, x31, x38, x41, x46, x48, x53, x55, x66, x74, x76, x84, x92, x101, x102, x103, x104, x105, x106, x107, x108, x109, x110, x111, x112, x113, x114, x115, x116, x117, x118);

        Solver solver = new Solver();
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
        solver.options().setLogTranslation(2);
        solver.options().setCoreGranularity(0);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(0);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = solver.solve(x18,bounds);
        System.out.println(sol.toString());
    }
}

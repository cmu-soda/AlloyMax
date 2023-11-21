package maxsat;

import java.util.Arrays;
import java.util.List;
import kodkod.ast.*;
import kodkod.ast.operator.*;
import kodkod.engine.fol2sat.HigherOrderDeclException;
import kodkod.engine.fol2sat.UnboundLeafException;
import kodkod.instance.*;
import kodkod.engine.*;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*

abstract sig Role {
	rule: Action -> Resource
}
abstract sig Action {}
abstract sig Resource {}

one sig Faculty, Student, TA extends Role {}
one sig IntGrade, ExtGrade extends Resource {}
one sig Assign, Receive extends Action {}

fun rolesAccessResource[resource: Resource]: set Role {
	{r: Role | some a: Action | a -> resource in r.rule}
}

run {
	Faculty in rolesAccessResource[IntGrade]
}


  ==================================================
    kodkod formula:
  ==================================================
    no (this/Faculty & this/Student) &&
    no ((this/Faculty + this/Student) & this/TA) &&
    no (this/Assign & this/Receive) &&
    no (this/IntGrade & this/ExtGrade) &&
    (all this: this/Faculty + this/Student + this/TA |
      (this . this/Role.rule) in ((this/Assign + this/Receive) -> (this/IntGrade +
      this/ExtGrade))) &&
    ((this/Role.rule . univ) . univ) in (this/Faculty + this/Student + this/TA) &&
    this/Faculty in {rolesAccessResource_r: this/Faculty + this/Student +
    this/TA | some rolesAccessResource_a: this/Assign + this/Receive |
     (rolesAccessResource_a -> this/IntGrade) in (rolesAccessResource_r .
     this/Role.rule)} &&
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
    this/Role.rule = this/Role.rule
  ==================================================
*/
public final class GradeSysTest6 {

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
        Relation x13 = Relation.nary("this/Role.rule", 3);

        List<String> atomlist = Arrays.asList(
                "-1", "-2", "-3", "-4", "-5",
                "-6", "-7", "-8", "0", "1", "2",
                "3", "4", "5", "6", "7", "Assign$0",
                "ExtGrade$0", "Faculty$0", "IntGrade$0", "Receive$0", "Student$0", "TA$0"
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

        TupleSet x13_upper = factory.noneOf(3);
        x13_upper.add(factory.tuple("Faculty$0").product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("Faculty$0").product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x13_upper.add(factory.tuple("Faculty$0").product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("Faculty$0").product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x13_upper.add(factory.tuple("Student$0").product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("Student$0").product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x13_upper.add(factory.tuple("Student$0").product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("Student$0").product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        x13_upper.add(factory.tuple("TA$0").product(factory.tuple("Assign$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("TA$0").product(factory.tuple("Assign$0")).product(factory.tuple("ExtGrade$0")));
        x13_upper.add(factory.tuple("TA$0").product(factory.tuple("Receive$0")).product(factory.tuple("IntGrade$0")));
        x13_upper.add(factory.tuple("TA$0").product(factory.tuple("Receive$0")).product(factory.tuple("ExtGrade$0")));
        bounds.bound(x13, x13_upper);

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

        Expression x16=x6.intersection(x7);
        Formula x15=x16.no();
        Expression x19=x6.union(x7);
        Expression x18=x19.intersection(x8);
        Formula x17=x18.no();
        Expression x21=x9.intersection(x10);
        Formula x20=x21.no();
        Expression x23=x11.intersection(x12);
        Formula x22=x23.no();
        Variable x26=Variable.unary("this");
        Expression x27=x19.union(x8);
        Decls x25=x26.oneOf(x27);
        Expression x29=x26.join(x13);
        Expression x31=x9.union(x10);
        Expression x32=x11.union(x12);
        Expression x30=x31.product(x32);
        Formula x28=x29.in(x30);
        Formula x24=x28.forAll(x25);
        Expression x35=x13.join(Expression.UNIV);
        Expression x34=x35.join(Expression.UNIV);
        Formula x33=x34.in(x27);
        Variable x40=Variable.unary("rolesAccessResource_r");
        Decls x39=x40.oneOf(x27);
        Variable x43=Variable.unary("rolesAccessResource_a");
        Decls x42=x43.oneOf(x31);
        Expression x45=x43.product(x11);
        Expression x46=x40.join(x13);
        Formula x44=x45.in(x46);
        Formula x41=x44.forMaxSome(x42);
        Expression x38=x41.comprehension(x39);
        Formula x37=x6.in(x38);
        Formula x47=x0.eq(x0);
        Formula x48=x1.eq(x1);
        Formula x49=x2.eq(x2);
        Formula x50=x3.eq(x3);
        Formula x51=x4.eq(x4);
        Formula x52=x5.eq(x5);
        Formula x53=x6.eq(x6);
        Formula x54=x7.eq(x7);
        Formula x55=x8.eq(x8);
        Formula x56=x9.eq(x9);
        Formula x57=x10.eq(x10);
        Formula x58=x11.eq(x11);
        Formula x59=x12.eq(x12);
        Formula x60=x13.eq(x13);
        Formula x14=Formula.compose(FormulaOperator.AND, x15, x17, x20, x22, x24, x33, x37, x47, x48, x49, x50, x51, x52, x53, x54, x55, x56, x57, x58, x59, x60);

        Solver solver = new Solver();
//        solver.options().setSolver(SATFactory.DefaultSAT4J);
        solver.options().setSolver(SATFactory.DefaultSAT4JMax);
        solver.options().setBitwidth(4);
//        solver.options().setFlatten(false);
        solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
        solver.options().setSymmetryBreaking(20);
        solver.options().setSkolemDepth(0);
        System.out.println("Solving...");
        System.out.flush();
        Solution sol = null;
        try {
            sol = solver.solve(x14,bounds);
        } catch (Exception e) {
            assertEquals("No primary variables for declaration: rolesAccessResource_a", e.getMessage());
            return;
        }
        fail("Expected: Exception in thread \"main\" java.lang.IllegalArgumentException: No primary variables for declaration: rolesAccessResource_a");
    }
}

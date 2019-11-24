package kodkod.engine.fol2sat;

import kodkod.ast.MultiplicityFormula;
import kodkod.ast.Node;
import kodkod.ast.operator.Multiplicity;
import kodkod.ast.visitor.AbstractCollector;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO
 * @authoer Changjian Zhang
 */
public class MaximalityMultifierFinder extends AbstractCollector<MultiplicityFormula> {

    public MaximalityMultifierFinder(Set<Node> cached) {
        super(cached);
    }

    @Override
    protected Set<MultiplicityFormula> newSet() {
        return new LinkedHashSet<>();
    }

    @Override
    public Set<MultiplicityFormula> visit(MultiplicityFormula multFormula) {
        Set<MultiplicityFormula> ret = lookup(multFormula);
        if (ret != null)
            return ret;
        ret = newSet();
        if (multFormula.multiplicity() == Multiplicity.MAXSOME || multFormula.multiplicity() == Multiplicity.MINSOME) {
            ret.add(multFormula);
        }
        ret.addAll(multFormula.expression().accept(this));
        return cache(multFormula, ret);
    }
}

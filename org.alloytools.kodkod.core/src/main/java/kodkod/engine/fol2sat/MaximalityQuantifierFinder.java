package kodkod.engine.fol2sat;

import kodkod.ast.Node;
import kodkod.ast.QuantifiedFormula;
import kodkod.ast.operator.Quantifier;
import kodkod.ast.visitor.AbstractCollector;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO
 * @author Changjian Zhang
 */
public class MaximalityQuantifierFinder extends AbstractCollector<QuantifiedFormula> {

    public MaximalityQuantifierFinder(Set<Node> cached) {
        super(cached);
    }

    @Override
    protected Set<QuantifiedFormula> newSet() {
        return new LinkedHashSet<>();
    }

    @Override
    public Set<QuantifiedFormula> visit(QuantifiedFormula quantFormula) {
        Set<QuantifiedFormula> ret = lookup(quantFormula);
        if (ret != null)
            return ret;
        ret = newSet();
        if (quantFormula.quantifier() == Quantifier.MAXSOME || quantFormula.quantifier() == Quantifier.MINSOME) {
            ret.add(quantFormula);
        }
        ret.addAll(quantFormula.decls().accept(this));
        ret.addAll(quantFormula.formula().accept(this));
        return cache(quantFormula, ret);
    }
}

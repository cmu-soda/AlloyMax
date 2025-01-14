/*
 * Kodkod -- Copyright (c) 2005-present, Emina Torlak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package kodkod.ast;

import kodkod.ast.operator.Quantifier;
import kodkod.ast.visitor.ReturnVisitor;
import kodkod.ast.visitor.VoidVisitor;

/**
 * A quantified formula.
 *
 * @specfield decls: Declarations
 * @specfield formula: Formula
 * @specfield quantifier: Quantifier
 * @invariant children = 0->decls + 1->formula
 * @author Emina Torlak
 */
public final class QuantifiedFormula extends Formula {

    private final Quantifier quantifier;
    private final Decls      decls;
    private final Formula    domain;
    private final Formula    body;

    /** This field is used to identify the priority for minsome/maxsome quantifier */
    private int somePriority = -1;

    public int getSomePriority() {
        return somePriority;
    }

    public void setSomePriority(int somePriority) {
        assert (somePriority >= 0);
        this.somePriority = somePriority;
    }

    /**
     * Constructs a new quantified formula: quantifier decls | formula
     *
     * @ensures this.quantifier' = quantifier && this.decls' = decls &&
     *          this.formula' = formula
     * @throws NullPointerException quantifier = null || decls = null || formula =
     *             null
     */
    QuantifiedFormula(Quantifier quantifier, Decls declarations, Formula domain, Formula body) {
        if (quantifier == null || declarations == null || domain == null || body == null) {
            throw new NullPointerException("null arg");
        }
        this.quantifier = quantifier;
        this.decls = declarations;
        this.domain = domain;
        this.body = body;
    }

    /**
     * Returns this.formula.
     *
     * @return this.formula
     */
    public Formula domain() {
        return domain;
    }

    /**
     * Returns this.body.
     *
     * @return this.body
     */
    public Formula body() {
        return body;
    }

    public Formula formula() {
        if (domain == Formula.TRUE)
            return body;
        if (quantifier == Quantifier.ALL)
            return domain.implies(body);
        else
            return domain.and(body);
    }

    /**
     * Returns this.decls.
     *
     * @return this.decls
     */
    public Decls decls() {
        return decls;
    }

    /**
     * Returns this.quantifier.
     *
     * @return this.quantifier
     */
    public Quantifier quantifier() {
        return quantifier;
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.ast.Formula#accept(kodkod.ast.visitor.ReturnVisitor)
     */
    @Override
    public <E, F, D, I> F accept(ReturnVisitor<E,F,D,I> visitor) {
        return visitor.visit(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.ast.Node#accept(kodkod.ast.visitor.VoidVisitor)
     */
    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see kodkod.ast.Node#toString()
     */
    @Override
    public String toString() {
        if (domain != Formula.TRUE)
            return "(" + quantifier + " " + decls + " | " + domain + " | " + body + ")";
        else
            return "(" + quantifier + " " + decls + " | " + formula() + ")";
    }

}

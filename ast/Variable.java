package ast;

import java.math.BigDecimal;
import parser.Token;
import machine.PCodeVisitor;

public class Variable extends PCodeArgument {
    private String var;

    public Variable(Token var) {
        super(var);
        this.var = var.image;
    }

    public String getValue() {
        return this.var;
    }

    public String toString() {
        return this.var;
    }

    public BigDecimal accept(PCodeVisitor visitor) {
        return visitor.visit(this);
    }
}
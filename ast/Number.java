package ast;

import java.math.BigDecimal;
import parser.Token;
import machine.PCodeVisitor;

public class Number extends PCodeArgument {
    private BigDecimal val;

    public Number(Token num) {
        super(num);

        this.val = new BigDecimal(num.image);
    }

    public BigDecimal getValue() {
        return this.val;
    }

    public String toString() {
        return this.val.toString();
    }

    public BigDecimal accept(PCodeVisitor visitor) {
        return visitor.visit(this);
    }
}
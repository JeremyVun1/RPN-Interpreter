package ast;

import java.math.BigDecimal;
import parser.Token;
import machine.PCodeVisitor;

public abstract class PCodeArgument extends Position {
    public PCodeArgument(Token token) {
        super(token);
    }

    public PCodeArgument(Token start, Token end) {
        super(start, end);
    }

    public abstract String toString();

    public abstract BigDecimal accept(PCodeVisitor visitor);
}
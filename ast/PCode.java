package ast;

import parser.Token;
import machine.PCodeVisitor;

public abstract class PCode extends Position {
    public PCode(Token instruction) {
        super(instruction);
    }

    public PCode(Token instruction, Position end) {
        super(instruction, end);
    }

    public PCode(Token start, Token end) {
        super(start, end);
    }

    public abstract String toString();

    public abstract void accept(PCodeVisitor visitor);
}
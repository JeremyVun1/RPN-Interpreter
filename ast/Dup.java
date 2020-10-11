package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Dup extends PCode {
    public Dup(Token instruction) {
        super(instruction);
    }

    public String toString() {
        return "dup";
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
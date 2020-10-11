package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Div extends PCode {
    public Div(Token instruction) {
        super(instruction);
    }

    public String toString() {
        return "div";
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
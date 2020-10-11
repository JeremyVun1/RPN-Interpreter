package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Add extends PCode {
    public Add(Token instruction) {
        super(instruction);
    }

    public String toString() {
        return "add";
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
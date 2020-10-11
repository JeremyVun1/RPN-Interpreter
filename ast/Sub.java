package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Sub extends PCode {
    public Sub(Token instruction) {
        super(instruction);
    }

    public String toString() {
        return "sub";
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
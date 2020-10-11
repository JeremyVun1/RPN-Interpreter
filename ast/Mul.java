package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Mul extends PCode {
    public Mul(Token instruction) {
        super(instruction);
    }

    public String toString() {
        return "mul";
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
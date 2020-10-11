package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Store extends PCode {
    private String var;

    public Store(Token instruction, Token var) {
        super(instruction);
        this.var = var.image;
    }

    public String getVariableName() {
        return this.var;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("store\t");
        sb.append(this.var);
        
        return sb.toString();
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Store extends PCode {
    private String varName;

    public Store(Token instruction, Token varName) {
        super(instruction);
        this.varName = varName.image;
    }

    public String getVariableName() {
        return this.varName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("store\t");
        sb.append(this.varName);
        
        return sb.toString();
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
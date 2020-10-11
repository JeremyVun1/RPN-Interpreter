package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Load extends PCode {
    private PCodeArgument arg;

    public Load(Token instruction, PCodeArgument arg) {
        super(instruction);
        this.arg = arg;
    }

    public PCodeArgument getArgument() {
        return this.arg;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("load\t");
        sb.append(this.arg.toString());
        
        return sb.toString();
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
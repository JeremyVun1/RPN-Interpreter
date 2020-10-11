package ast;

import parser.Token;
import machine.PCodeVisitor;

public class Printf extends PCode {
    private int argCount;
    private String msg;

    public Printf(Token instruction, Token argCount, Token msg) {
        super (instruction, msg);

        this.argCount = Integer.parseInt(argCount.image);
        this.msg = msg.image.substring(1, msg.image.length() -1); // everything but first char (??)
    }

    public int getArgCount() {
        return argCount;
    }

    public String getMessage() {
        return msg;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("printf\t");
        sb.append(argCount);
        sb.append(" \"");
        sb.append(msg);
        sb.append("\"");

        return sb.toString();
    }

    public void accept(PCodeVisitor visitor) {
        visitor.visit(this);
    }
}
package ast;

import parser.Token;

public abstract class Position {
    protected int beginLine;
    protected int beginColumn;
    protected int endLine;
    protected int endColumn;

    public Position(Token token) {
        this(token, token);
    }

    public Position(Token start, Token end) {
        beginLine = start.beginLine;
        beginColumn = start.beginColumn;
        endLine = end.endLine;
        endColumn = end.endColumn;
    }

    public Position(Token start, Position end) {
        beginLine = start.beginLine;
        beginColumn = start.beginColumn;
        endLine = end.endLine;
        endColumn = end.endColumn;
    }

    public int getLine() {
        return beginLine;
    }

    public int getColumn() {
        return beginColumn;
    }
}
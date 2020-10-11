package machine;

import java.math.BigDecimal;
import ast.Add;
import ast.Div;
import ast.Dup;
import ast.Load;
import ast.Mul;
import ast.Number;
import ast.Printf;
import ast.Store;
import ast.Sub;
import ast.Variable;

public interface PCodeVisitor {
    public void visit(Add instruction);
    public void visit(Sub instruction);
    public void visit(Mul instruction);
    public void visit(Div instruction);
    public void visit(Dup instruction);
    public void visit(Printf instruction);
    public void visit(Load instruction);
    public void visit(Store instruction);

    public BigDecimal visit(Variable arg);
    public BigDecimal visit(Number arg);
}
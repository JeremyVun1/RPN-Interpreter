package machine;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.Hashtable;

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


public class PCodeMachine implements PCodeVisitor {
    private Stack<BigDecimal> stack;
    private Hashtable<String, BigDecimal> memory;

    public PCodeMachine() {
        stack = new Stack<BigDecimal>();
        memory = new Hashtable<String, BigDecimal>();
    }

    private String buildFormattedString(int stackValues, String formatString) {
        // fetch required stack arguments
        BigDecimal printArguments[] = new BigDecimal[stackValues];
        for (int i=stackValues; i>0;) {
            printArguments[--i] = stack.pop();
        }

        StringBuilder formattedString = new StringBuilder();

        int length = formatString.length();
        int j=0;
        while (j < length) {
            switch (formatString.charAt(j)) {
                case '%':
                    // test for applicable format specification
                    j++;
                    StringBuffer stackIndex = new StringBuffer();
                    while (j < length) {
                        char c = formatString.charAt(j);    
                        if (Character.isDigit(c)) {
                            stackIndex.append(c);
                            j++;
                        }
                        else break;
                    }

                    if (stackIndex.length() > 0) { // is a format specifier
                        int index = Integer.parseInt(stackIndex.toString());
                        if (index > printArguments.length) {
                            new RuntimeException("Value reference out of bounds.");
                        }
                        else {
                            formattedString.append(printArguments[index]);
                        }
                    }
                    else {
                        formattedString.append('%');
                    }
                    break;
                
                case '\\':
                    j++;
                    if (j < length) {
                        char c = formatString.charAt(j);
                        j++;

                        switch(c) {
                            case 'n':
                                formattedString.append('\n');
                                break;
                            case 't':
                                formattedString.append('\t');
                                break;
                            default:
                                formattedString.append('\\');
                                formattedString.append(c);
                        }
                    }
                    break;

                default:
                    formattedString.append(formatString.charAt(j));
                    j++;
            }
        }

        return formattedString.toString();
    }

    public void visit(Add instruction) {
        // may error if stack is not deep enough
        BigDecimal operand_a = stack.pop();
        BigDecimal operand_b = stack.pop();
        stack.push(operand_a.add(operand_b));
    }

    public void visit(Sub instruction) {
        
    }

    public void visit(Mul instruction) {
        
    }

    public void visit(Div instruction) {
        
    }

    public void visit(Dup instruction) {
        
    }

    public void visit(Printf instruction) {
        
    }

    public void visit(Load instruction) {
        BigDecimal val = instruction.getArgument().accept(this);
        stack.push(val);
    }

    public void visit(Store instruction) {
        
    }

    public BigDecimal visit(Variable arg) {
        return new BigDecimal(arg.getValue());
    }

    public BigDecimal visit(Number arg) {
        return arg.getValue();
    }

    public void printStackTrace() {
        System.out.println("STACK TRACE:");
        System.out.println(this.stack);
        //print contents of the value stack to console
    }

    public void printMemoryTrace() {
        System.out.println("MEMORY TRACE:");
        System.out.println(this.memory);
        // print content of memory to the system console
    }
}
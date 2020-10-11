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

    public void visit(Add instruction) {
        if (stack.size() < 2)
            throw new RuntimeException("Not enough numbers to add");

        BigDecimal operand_a = stack.pop();
        BigDecimal operand_b = stack.pop();
        stack.push(operand_a.add(operand_b));
    }

    public void visit(Sub instruction) {
        if (stack.size() < 2)
            throw new RuntimeException("Not enough numbers to subtract");

        BigDecimal operand_a = stack.pop();
        BigDecimal operand_b = stack.pop();
        stack.push(operand_a.subtract(operand_b));
    }

    public void visit(Mul instruction) {
        if (stack.size() < 2)
            throw new RuntimeException("Not enough numbers to multiply");

        BigDecimal operand_a = stack.pop();
        BigDecimal operand_b = stack.pop();
        stack.push(operand_a.multiply(operand_b));
    }

    public void visit(Div instruction) {
        if (stack.size() < 2)
            throw new RuntimeException("Not enough numbers to divide");

        BigDecimal operand_a = stack.pop();
        BigDecimal operand_b = stack.pop();
        stack.push(operand_a.divide(operand_b));
    }

    public void visit(Dup instruction) {
        if (stack.size() < 1)
            throw new RuntimeException("No number to duplicate");

        BigDecimal operand = stack.peek();
        stack.push(operand);
    }

    public void visit(Printf instruction) {
        if (stack.size() < instruction.getArgCount())
            throw new RuntimeException("Not enough numbers to print");

        String x = buildFormattedString(instruction.getArgCount(), instruction.getMessage());
        System.out.print(x);
    }

    public void visit(Load instruction) {
        BigDecimal val = instruction.getArgument().accept(this);
        stack.push(val);
    }

    public void visit(Store instruction) {
        if (stack.size() < 1)
            throw new RuntimeException("Not enough numbers to store");

        BigDecimal operand = stack.pop();
        String varName = instruction.getVariableName();
        memory.put(varName, operand);
    }

    public BigDecimal visit(Variable arg) {
        String varName = arg.getValue();
        if (!memory.containsKey(varName))
            throw new RuntimeException("No variable found with name " + varName);

        return memory.get(varName);
    }

    public BigDecimal visit(Number arg) {
        return arg.getValue();
    }

    public void printStackTrace() {
        System.out.println("Stack:");
        int i = 1;
        while (!stack.empty()) {
            System.out.println(i + ":\t" + stack.pop());
            i++;
        }
    }

    public void printMemoryTrace() {
        System.out.println("Memory:");
        memory.forEach((varName, value) -> {
            System.out.println(varName + ":\t" + value);
        });
    }

    private String buildFormattedString(int stackValues, String formatString)
	{
		BigDecimal printArguments[] = new BigDecimal[stackValues];

		StringBuilder formattedString = new StringBuilder();
		int length = formatString.length();

		// fetch required stack arguments	
		for (int i=stackValues; i > 0;) {
			printArguments[--i] = stack.pop();
		}
		
		// traverse the format string
		int j = 0;
		while (j < length)
		{
			switch (formatString.charAt(j))
			{
			case '%':
				// test for applicable format specification
				j++;
				StringBuffer stackIndex = new StringBuffer();

				while (j < length)
				{
					char c = formatString.charAt(j);
					if (Character.isDigit(c))
					{
						stackIndex.append(c);
						j++;
					}
					else break;
				}
				
				if (stackIndex.length() > 0)	// is a format specifier
				{
					int index = Integer.parseInt(stackIndex.toString()) - 1;
					
					if (index > printArguments.length)
					{
						new RuntimeException("Value reference out of bounds.");
					}
					else
					{
						formattedString.append(printArguments[index]);
					}
				}
				else
				{
					formattedString.append('%');
				}
				break;
			
			case '\\':
				j++;
				
				if (j < length)
				{
					char c = formatString.charAt(j);
					j++;
					
					switch (c)
					{
					case 'n':
						formattedString.append( '\n' );
						break;
					case 't':
						formattedString.append( '\t' );
						break;
					default:
						formattedString.append( '\\' );
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
}
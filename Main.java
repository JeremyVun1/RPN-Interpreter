import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.text.ParseException;

import parser.PCodeParser;
import ast.PCode;
import machine.PCodeMachine;


class Main {
    public static void main(String[] args) {
        try
		{
			String filename = "pcode\\" + args[0];
			PCodeParser parser = new PCodeParser(new FileInputStream(filename));

			// Parse and build the AST
			ArrayList<PCode> instructions = parser.Parse();
			System.out.println("PCode accepted:");
			for (PCode pc : instructions) {
				System.out.println(pc);
			}

			// Evaluate the AST
			PCodeMachine machine = new PCodeMachine();
			System.out.println("Running program: ");
			for (PCode pc: instructions) {
				pc.accept(machine);
			}

			machine.printStackTrace();
			machine.printMemoryTrace();
		}
		catch(Exception e)
		{
			System.err.println("error: " + e.getMessage());
		}
    }
}
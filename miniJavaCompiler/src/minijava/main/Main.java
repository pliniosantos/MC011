package minijava.main;

import minijava.lexer.Lexer;
import minijava.node.Start;
import minijava.parser.Parser;

import java.io.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
	         try {
	            /* Form our AST */
	            Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(args[0])), 1024));
	            Parser parser = new Parser(lexer);
	            Start ast = parser.parse() ;
	 
	            /* Get our Interpreter going. */
//	            Interpreter interp = new Interpreter () ;
//	            ast.apply(interp) ;
	         }
	         catch (Exception e) {
	            System.out.println (e) ;
	         }
	      } else {
	         System.err.println("usage: java simpleAdder inputFile");
	         System.exit(1);
	      } 
	}

}

package analisadores.syntaxtree;

import java.util.*;
import java.io.PrintStream;
import minijava.node.*;
import minijava.analysis.*;

public class PrettyPrint extends DepthFirstAdapter
{
    PrintStream out;
    
    private int ident;
    private boolean printSpace;
	
    private void beginNest()
    {
	ident += 4;
    }
	
    private void endNest()
    {
	ident -= 4;
    }
	
    private void print(String s)
    {
	if ( printSpace )
	    for (int i = 0; i < ident; i++ )
		out.print(" ");
	out.print(s);
		
	printSpace = false;
    }
	
    private void println(String s)
    {
	if ( printSpace )
	    for (int i = 0; i < ident; i++ )
		out.print(" ");
	out.println(s);
		
	printSpace = true;
    }
	
    public PrettyPrint(PrintStream p)
    {
	super();
        out = p;
	ident = 0;
	printSpace = true;
    }
    
    public PrettyPrint()
    {
        this(System.out);
    }

    // program
    public void inAProgram(AProgram node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAProgram(AProgram node) {
        endNest();
    }
    
    // mainclass
    public void inAMainclass(AMainclass node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("class name: "+node.getCn().toString());
        print(", ");
        println("args name: "+node.getAn().toString());
        beginNest();
    }

    public void outAMainclass(AMainclass node) {
        endNest();
    }

    // nextclass {ext}
    public void inAExtNextclass(AExtNextclass node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("class name: "+node.getName().toString());
        print(", ");
        print("extends: "+node.getExt().toString());
//        if (!node.getVar().isEmpty()) {
//            print(", fields: {");
//            for(PVar var: node.getVar()) {
//                print("["+var.toString()+"] ");
//            }
//            print("} ");
//        }
//        if (!node.getMethod().isEmpty()) {
//            print(", methods: { ");
//            for(PMethod method: node.getMethod()) {
//                print("["+method.toString()+"] ");
//            }
//            print("} ");
//        }
        println("");
        beginNest();
    }

    public void outAExtNextclass(AExtNextclass node) {
        endNest();
    }    

    // nextclass {noext}
    public void inANonextNextclass(ANonextNextclass node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("class name: "+node.getId().toString());
//        if (!node.getVar().isEmpty()) {
//            print(", fields: {");
//            for(PVar var: node.getVar()) {
//                print("["+var.toString()+"] ");
//            }
//            print("} ");
//        }
//        if (!node.getMethod().isEmpty()) {
//            print(", methods: { ");
//            for (PMethod method: node.getMethod()) {
//                print("["+method.toString()+"] ");
//            }
//            print("} ");
//        }
        println("");
        beginNest();
    }

    public void outANonextNextclass(ANonextNextclass node) {
        endNest();
    }

    // var
    public void inAVar(AVar node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("type: "+node.getType().toString());
        println(", id: "+node.getId().toString());
    }

    public void outAVar(AVar node) {
        // Sempre sera folha da AST
    }

    // method
    public void inAMethod(AMethod node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("return type: "+node.getType().toString());
        print(", id: "+node.getId().toString());
//        if (!node.getParam().isEmpty()) {
//            print(", formal list: ");
//            for (PVar param: node.getParam()) {
//                
//            }
//        }
        println("");
        beginNest();
    }

    public void outAMethod(AMethod node) {
        endNest();
    }

    // statement if
    public void inAIfStatement(AIfStatement node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAIfStatement(AIfStatement node) {
        endNest();
    }
    
    // statement { vatb }
    public void inAVatbStatement(AVatbStatement node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
//        print(", left expresion: "+node.getI().toString());
//        println(", right expresion: "+node.getV().toString());
        beginNest();
    }

    public void outAVatbStatement(AVatbStatement node) {
        endNest();
    }

    // statement { atb }
    public void inAAtbStatement(AAtbStatement node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    public void outAAtbStatement(AAtbStatement node) {
        endNest();
    }
    
    // statement { while }
    public void inAWhileStatement(AWhileStatement node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAWhileStatement(AWhileStatement node) {
        endNest();
    }
    
    // statement { print }
    public void inAPrintStatement(APrintStatement node) {
        println("<"+node.getClass().getSimpleName()+"> ");
//        println("expression: "+node.getExpression().toString());
        beginNest();
    }

    public void outAPrintStatement(APrintStatement node) {
        endNest();
    }
    
    // statement { many }
    public void inAManyStatement(AManyStatement node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAManyStatement(AManyStatement node) {
        endNest();
    }
    
    // expression { mcall }
    public void inAMcallExpression(AMcallExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    public void outAMcallExpression(AMcallExpression node) {
        endNest();
    }
    
    // expression { plus }
    public void inAPlusExpression(APlusExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAPlusExpression(APlusExpression node) {
        endNest();
    }
    
    // expression { minus }
    public void inAMinusExpression(AMinusExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAMinusExpression(AMinusExpression node) {
        endNest();
    }
    
    // expression { mult }
    public void inAMultExpression(AMultExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAMultExpression(AMultExpression node) {
        endNest();
    }
    
    // expression { and }
    public void inAAndExpression(AAndExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAAndExpression(AAndExpression node) {
        endNest();
    }
    
    // expression { gthan }
    public void inAGthanExpression(AGthanExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAGthanExpression(AGthanExpression node) {
        endNest();
    }
    
    // expression { lthan }
    public void inALthanExpression(ALthanExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outALthanExpression(ALthanExpression node) {
        endNest();
    }
    
    // expression { vector }
    public void inAVectorExpression(AVectorExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outAVectorExpression(AVectorExpression node) {
        endNest();
    }
    
    // expression { length }
    public void inALengthExpression(ALengthExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outALengthExpression(ALengthExpression node) {
        endNest();
    }
    
    // expression { not }
    public void inANotExpression(ANotExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outANotExpression(ANotExpression node) {
        endNest();
    }
    
    // expression { newvec }
    public void inANewvecExpression(ANewvecExpression node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void outANewvecExpression(ANewvecExpression node) {
    }
    
    // expression { var }
    public void inAVarExpression(AVarExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    public void outAVarExpression(AVarExpression node) {
        endNest();
    }
    
    // expression { newobj }
    public void inANewobjExpression(ANewobjExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    public void outANewobjExpression(ANewobjExpression node) {
        endNest();
    }
    
    // expression { number }
    public void inANumberExpression(ANumberExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getNumber().toString());
        beginNest();
    }

    public void outANumberExpression(ANumberExpression node) {
        endNest();
    }
    
    // expression { btrue }
    public void inABtrueExpression(ABtrueExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getTrue().toString());
        beginNest();
    }

    public void outABtrueExpression(ABtrueExpression node) {
        endNest();
    }
    
    // expression { bfalse }
    public void inABfalseExpression(ABfalseExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getFalse().toString());
        beginNest();
    }

    public void outABfalseExpression(ABfalseExpression node) {
        endNest();
    }
    
    // expression { self }
    public void inASelfExpression(ASelfExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getThis().toString());
        beginNest();
    }

    public void outASelfExpression(ASelfExpression node) {
        endNest();
    }
    
    // type { intt }
    public void inAInttType(AInttType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getIntt().toString());
        beginNest();
    }

    public void outAInttType(AInttType node) {
        endNest();
    }
    
    // type { intv }
    public void inAIntvType(AIntvType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getIntv().toString());
        beginNest();
    }

    public void outAIntvType(AIntvType node) {
        endNest();
    }
    
    // type { bool }
    public void inABoolType(ABoolType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getBool().toString());
        beginNest();
    }

    public void outABoolType(ABoolType node) {
        endNest();
    }
    
    // type { class }
    public void inAClassType(AClassType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    public void outAClassType(AClassType node) {
        endNest();
    }
}









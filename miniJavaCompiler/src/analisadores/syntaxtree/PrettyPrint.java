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

    public void defaultIn(@SuppressWarnings("unused") Node node) {
        println("<"+node.getClass().getSimpleName()+"> ");
        beginNest();
    }

    public void defaultOut(@SuppressWarnings("unused") Node node) {
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

    // nextclass {ext}
    public void inAExtNextclass(AExtNextclass node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("class name: "+node.getName().toString());
        print(", ");
        println("extends: "+node.getExt().toString());
        beginNest();
    }

    // nextclass {noext}
    public void inANonextNextclass(ANonextNextclass node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("class name: "+node.getId().toString());
        beginNest();
    }

    // var
    public void inAVar(AVar node) {
        print("<"+node.getClass().getSimpleName()+"> ");
//        print("type: "+node.getType().toString());
        println(", id: "+node.getId().toString());
        beginNest();
    }

    // method
    public void inAMethod(AMethod node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        print("return type: "+node.getType().toString());
        println(", id: "+node.getId().toString());
        beginNest();
    }
    
    // statement { vatb }
    public void inAVatbStatement(AVatbStatement node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    // statement { atb }
    public void inAAtbStatement(AAtbStatement node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    // expression { mcall }
    public void inAMcallExpression(AMcallExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }

    // expression { var }
    public void inAVarExpression(AVarExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }
    
    // expression { newobj }
    public void inANewobjExpression(ANewobjExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }
    
    // expression { number }
    public void inANumberExpression(ANumberExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getNumber().toString());
        beginNest();
    }
    
    // expression { btrue }
    public void inABtrueExpression(ABtrueExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getTrue().toString());
        beginNest();
    }
    
    // expression { bfalse }
    public void inABfalseExpression(ABfalseExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getFalse().toString());
        beginNest();
    }
    
    // expression { self }
    public void inASelfExpression(ASelfExpression node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getThis().toString());
        beginNest();
    }
    
    // type { intt }
    public void inAInttType(AInttType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getIntt().toString());
        beginNest();
    }
    
    // type { intv }
    public void inAIntvType(AIntvType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getIntv().toString());
        beginNest();
    }
    
    // type { bool }
    public void inABoolType(ABoolType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getBool().toString());
        beginNest();
    }
    
    // type { class }
    public void inAClassType(AClassType node) {
        print("<"+node.getClass().getSimpleName()+"> ");
        println("id: "+node.getId().toString());
        beginNest();
    }
}

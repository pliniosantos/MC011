package analisadores.visitor;

import minijava.node.AAndExpression;
import minijava.node.AAtbStatement;
import minijava.node.ABfalseExpression;
import minijava.node.ABoolType;
import minijava.node.ABtrueExpression;
import minijava.node.AClassType;
import minijava.node.AExtNextclass;
import minijava.node.AGthanExpression;
import minijava.node.AIfStatement;
import minijava.node.AInttType;
import minijava.node.AIntvType;
import minijava.node.ALengthExpression;
import minijava.node.ALthanExpression;
import minijava.node.AMainclass;
import minijava.node.AManyStatement;
import minijava.node.AMcallExpression;
import minijava.node.AMethod;
import minijava.node.AMinusExpression;
import minijava.node.AMultExpression;
import minijava.node.ANewobjExpression;
import minijava.node.ANewvecExpression;
import minijava.node.ANonextNextclass;
import minijava.node.ANotExpression;
import minijava.node.ANumberExpression;
import minijava.node.APlusExpression;
import minijava.node.APrintStatement;
import minijava.node.AProgram;
import minijava.node.ASelfExpression;
import minijava.node.AVar;
import minijava.node.AVarExpression;
import minijava.node.AVatbStatement;
import minijava.node.AVectorExpression;
import minijava.node.AWhileStatement;

public interface Visitor
{
    public void visit(AProgram node);
    public void visit(AMainclass node);
    public void visit(AExtNextclass node);
    public void visit(ANonextNextclass node);
    public void visit(AVar node);
    public void visit(AMethod node);
    public void visit(AIfStatement node);
    public void visit(AVatbStatement node);
    public void visit(AAtbStatement node);
    public void visit(AWhileStatement node);
    public void visit(APrintStatement node);
    public void visit(AManyStatement node);
    public void visit(AMcallExpression node);
    public void visit(APlusExpression node);
    public void visit(AMinusExpression node);
    public void visit(AMultExpression node);
    public void visit(AAndExpression node);
    public void visit(AGthanExpression node);
    public void visit(ALthanExpression node);
    public void visit(AVectorExpression node);
    public void visit(ALengthExpression node);
    public void visit(ANotExpression node);
    public void visit(ANewvecExpression node);
    public void visit(AVarExpression node);
    public void visit(ANewobjExpression node);
    public void visit(ANumberExpression node);
    public void visit(ABtrueExpression node);
    public void visit(ABfalseExpression node);
    public void visit(ASelfExpression node);
    public void visit(AInttType node);
    public void visit(AIntvType node);
    public void visit(ABoolType node);
    public void visit(AClassType node);
}
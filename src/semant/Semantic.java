package semant;

import errors.ErrorEchoer;
import util.List;
import symbol.ClassInfo;
import symbol.MethodInfo;
import symbol.Symbol;
import symbol.VarInfo;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDecl;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Equal;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;
import visitor.Visitor;

public class Semantic implements Visitor {
    private Env env;
    private ClassInfo classInfoAtual;
    private MethodInfo methodInfoAtual;
    private ErrorEchoer err;

    public Semantic(Env envirom, Program prog, ErrorEchoer err) {
        this.env = envirom;
        this.err = err;
        prog.accept(this);
    }

    public void visit(Program node) {
        // adiciona o ID da mainClass no Env
        Symbol mainClassSymbol = Symbol.symbol(node.mainClass.className.toString());
        ClassInfo classInfo = new ClassInfo(mainClassSymbol);

        // mantém o acesso a classInfo que está sendo analisada
        this.classInfoAtual = classInfo;

        if (env.addClass(classInfo)) {
            node.mainClass.accept(this);
        } else {
            System.out.println("Que erro?");
        }

        Symbol classSymbol;

        // adiciona o ID de cada class no Env
        for (List<ClassDecl> aux = node.classList; aux != null; aux = aux.tail) {
            classSymbol = Symbol.symbol(aux.head.name.toString());
            classInfo = new ClassInfo(classSymbol);

            // mantém o acesso a classInfo que está sendo analisada
            this.classInfoAtual = classInfo;

            if (env.addClass(classInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node, new Object[] { "Classe '"
                        + aux.head.name.toString() + "' jah existe" });
            }
        }
    }

    public void visit(MainClass node) {
        Symbol name = Symbol.symbol("main");
        Symbol parent = Symbol.symbol(node.className.toString());
        MethodInfo methodInfo = new MethodInfo(null, name, parent);

        env.addClassMethod(this.classInfoAtual, methodInfo);
        this.methodInfoAtual = methodInfo;

        // adiciona o ID de cada formal no Env do método
        name = Symbol.symbol(node.mainArgName.toString());
        VarInfo varInfo = new VarInfo(null, name);

        if (env.addMethodFormal(this.methodInfoAtual, varInfo)) {
            node.mainArgName.accept(this);
        } else {
            err.Error(node, new Object[] { "Formal '" + varInfo.name.toString()
                    + "' no metodo '" + methodInfoAtual.name.toString()
                    + "', class '" + classInfoAtual.name.toString()
                    + "' jah existe" });
        }
    }

    public void visit(ClassDeclSimple node) {
        Symbol name;
        Symbol parent;
        VarInfo varInfo;

        // adiciona o ID de cada variável no Env da sua classe
        for (List<VarDecl> aux = node.varList; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            varInfo = new VarInfo(aux.head.type, name);

            if (env.addClassVar(this.classInfoAtual, varInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node,
                        new Object[] { "Redeclaracao da variavel '"
                                + varInfo.name.toString() + "' na classe '"
                                + this.classInfoAtual.name.toString()
                                + "' jah existe" });
            }
        }

        MethodInfo methodInfo;

        // adiciona o ID de cada método no Env da sua classe
        for (List<MethodDecl> aux = node.methodList; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            parent = Symbol.symbol(node.name.toString());
            methodInfo = new MethodInfo(aux.head.returnType, name, parent);

            // mantém o acesso a MethodInfo que está sendo analisada
            this.methodInfoAtual = methodInfo;

            if (env.addClassMethod(this.classInfoAtual, methodInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node, new Object[] { "Metodo '"
                        + methodInfo.name.toString() + "' na classe '"
                        + this.classInfoAtual.name.toString()
                        + "' com mesmo nome " });
            }
        }
    }

    public void visit(ClassDeclExtends node) {
        // adiciona a herança no hash
        env.hashHeranca.put(this.classInfoAtual, node.superClass.toString());
        env.classHeranca.add(this.classInfoAtual);
        env.declHeranca.add(node);

        Symbol name;
        Symbol parent;
        VarInfo varInfo;

        // adiciona o ID de cada variável no Env da sua classe
        for (List<VarDecl> aux = node.varList; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            varInfo = new VarInfo(aux.head.type, name);

            if (env.addClassVar(this.classInfoAtual, varInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node,
                        new Object[] { "Redeclaracao da variavel '"
                                + varInfo.name.toString() + "' na classe '"
                                + this.classInfoAtual.name.toString()
                                + "' jah existe" });
            }
        }

        MethodInfo methodInfo;

        // adiciona o ID de cada método no Env da sua classe
        for (List<MethodDecl> aux = node.methodList; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            parent = Symbol.symbol(node.name.toString());
            methodInfo = new MethodInfo(aux.head.returnType, name, parent);

            // mantém o acesso a MethodInfo que está sendo analisada
            this.methodInfoAtual = methodInfo;

            if (env.addClassMethod(this.classInfoAtual, methodInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node, new Object[] { "Metodo '"
                        + methodInfo.name.toString() + "' na classe '"
                        + this.classInfoAtual.name.toString()
                        + "' com mesmo nome" });
            }
        }

    }

    public void visit(VarDecl node) {
    }

    public void visit(MethodDecl node) {

        Symbol name;
        VarInfo varInfo;

        // adiciona o ID de cada formal no Env do método
        for (List<Formal> aux = node.formals; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            varInfo = new VarInfo(aux.head.type, name);

            if (env.addMethodFormal(this.methodInfoAtual, varInfo)) {
                Symbol s = Symbol.symbol(this.methodInfoAtual.name.toString()+varInfo.type.toString());
                aux.head.accept(this);
            } else {
                err.Error(node, new Object[] { "Formal '"
                        + varInfo.name.toString() + "' no metodo '"
                        + methodInfoAtual.name.toString() + "', class '"
                        + classInfoAtual.name.toString() + "' jah existe" });
            }
        }

        // adiciona o ID de cada variável local no Env do método
        for (List<VarDecl> aux = node.locals; aux != null; aux = aux.tail) {
            name = Symbol.symbol(aux.head.name.toString());
            varInfo = new VarInfo(aux.head.type, name);

            if (env.addMethodLocal(this.methodInfoAtual, varInfo)) {
                aux.head.accept(this);
            } else {
                err.Error(node, new Object[] { "Local '"
                        + varInfo.name.toString() + "' no metodo '"
                        + methodInfoAtual.name.toString() + "', class '"
                        + classInfoAtual.name.toString() + "' jah existe" });
            }
        }
    }

    public void visit(Formal node) {
    }

    public void visit(IntArrayType node) {
    }

    public void visit(BooleanType node) {
    }

    public void visit(IntegerType node) {
    }

    public void visit(IdentifierType node) {
    }

    public void visit(Block node) {
    }

    public void visit(If node) {
    }

    public void visit(While node) {
    }

    public void visit(Print node) {
    }

    public void visit(Assign node) {
    }

    public void visit(ArrayAssign node) {
    }

    public void visit(And node) {
    }

    public void visit(LessThan node) {
    }

    public void visit(Equal node) {
    }

    public void visit(Plus node) {
    }

    public void visit(Minus node) {
    }

    public void visit(Times node) {
    }

    public void visit(ArrayLookup node) {
    }

    public void visit(ArrayLength node) {
    }

    public void visit(Call node) {
    }

    public void visit(IntegerLiteral node) {
    }

    public void visit(True node) {
    }

    public void visit(False node) {
    }

    public void visit(This node) {
    }

    public void visit(NewArray node) {
    }

    public void visit(NewObject node) {
    }

    public void visit(Not node) {
    }

    public void visit(IdentifierExp node) {
    }

    public void visit(Identifier node) {
    }
}

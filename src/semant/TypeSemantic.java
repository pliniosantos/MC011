package semant;

import errors.ErrorEchoer;
import semant.Env;
import symbol.*;
import syntaxtree.*;
import util.List;

import visitor.TypeVisitor;

public class TypeSemantic implements TypeVisitor {
    private Env env;
    public Program prog;
    private ErrorEchoer err;
    private ClassInfo classInfoAtual;
    private MethodInfo methodInfoAtual;
    private ClassInfo classInfoObj;

    public TypeSemantic(Env envirom, Program prog, ErrorEchoer error) {
        this.env = envirom;
        this.prog = prog;
        err = error;
        prog.accept(this);
    }

    public Type visit(Program n) {
        this.classInfoAtual = env.getClass(n.mainClass.className.toString());
        if (this.classInfoAtual == null) {
            err.Error(n, new Object[] { "Main nao encontrada" });
        }

        // mantém o acesso a classInfo que está sendo analisada
        n.mainClass.accept(this);

        // adiciona o ID de cada class no Env
        for (List<ClassDecl> aux = n.classList; aux != null; aux = aux.tail) {
            // mantém o acesso a classInfo que está sendo analisada
            this.classInfoAtual = env.getClass(aux.head.name.toString());
            aux.head.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(MainClass n) {
        n.className.accept(this);
        this.methodInfoAtual = env.getClassMethod(this.classInfoAtual, "main");
        n.mainArgName.accept(this);
        n.s.accept(this);

        return new BooleanType(n.line, n.row);
    }

    public Type visit(ClassDeclSimple n) {
        // percorre os métodos
        for (List<MethodDecl> aux = n.methodList; aux != null; aux = aux.tail) {
            this.methodInfoAtual = env.getClassMethod(this.classInfoAtual,aux.head.name.toString());
            aux.head.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(ClassDeclExtends n) {
        // percorre os métodos
        for (List<MethodDecl> aux = n.methodList; aux != null; aux = aux.tail) {
            this.methodInfoAtual = env.getClassMethod(this.classInfoAtual,aux.head.name.toString());
            env.getClassMethod(this.classInfoAtual, aux.head.name.toString());
            aux.head.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(VarDecl n) {
        return n.name.accept(this);
    }

    public Type visit(MethodDecl n) {
        List<VarInfo> l1, l2;

        // verifica somente para classes com extends
        if (this.classInfoAtual.base != null) {
            // procura método de mesma assinatura da classe pai
            MethodInfo methodInfoPai = env.getClassMethod(this.classInfoAtual.base, n.name.toString());

            // compara valor de retorno se existir redefinição de método
            if (methodInfoPai != null) {
                // verifica o tipo do retorno
                if (!(methodInfoPai.type.toString()).equals(n.returnType.toString())) {
                    err.Error(n, new Object[] { "Tipo de retorno do metodo "
                            + n.name.toString()
                            + " reimplementado diferentemente" });
                }
                
                // verifica o tipo do parâmetro
                if (!(this.methodInfoAtual.formals == null && methodInfoPai.formals == null)) {
                    if (!(this.methodInfoAtual.formals != null && methodInfoPai.formals != null)) {
                        err.Error(n,new Object[] { "Numero de parametros de metodo reimplementado difere" });
                    } else if (this.methodInfoAtual.formals.size() != methodInfoPai.formals.size()) {
                        err.Error(n,new Object[] { "Numero de parametros de metodo reimplementado difere" });
                    } else {
                        for (l1 = this.methodInfoAtual.formals, l2 = methodInfoPai.formals; l1 != null; l1 = l1.tail, l2 = l2.tail) {
                            if (!l1.head.type.toString().equals(l2.head.type.toString())) {
                                err.Error(n,new Object[] { "Tipo de parametro reimplementado difere" });
                            }
                        }
                    }
                }
            }
        }

        Type returnType = n.returnExp.accept(this);

        if (returnType instanceof IdentifierType && !returnType.isComparable(n.returnType)) {
            ClassInfo classe = this.classInfoAtual;
            
            // verifica se o this refere-se a própria classe
            do {
                if (classe == null) {
                    err.Error(n, new Object[] { "Retorno do metodo "
                            + n.name.toString() + " incompativel" });
                } else {
                    if (returnType.toString().equals(classe.name.toString())) {
                        break;
                    } else {
                        classe = classe.base;
                        if (classe == null) {
                            err.Error(n, new Object[] { "Retorno do metodo "
                                    + n.name.toString() + " incompativel" });
                        }
                    }
                }
            } while (classe != null);
        } else if (!returnType.isComparable(n.returnType)) {
            err.Error(n, new Object[] { "Retorno do metodo "
                    + n.name.toString() + " incompativel" });
        }

        for (List<Statement> aux = n.body; aux != null; aux = aux.tail) {
            aux.head.accept(this);
        }

        for (List<Formal> aux = n.formals; aux != null; aux = aux.tail) {
            aux.head.accept(this);
        }

        for (List<VarDecl> aux = n.locals; aux != null; aux = aux.tail) {
            aux.head.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(Formal n) {
        n.name.accept(this);
        return n.type;
    }

    public Type visit(IntArrayType n) {
        return n;
    }

    public Type visit(BooleanType n) {
        return n;
    }

    public Type visit(IntegerType n) {
        return n;
    }

    public Type visit(IdentifierType n) {
        return n;
    }

    public Type visit(Block n) {
        for (List<Statement> aux = n.body; aux != null; aux = aux.tail) {
            aux.head.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(If n) {
        n.condition.accept(this);
        n.thenClause.accept(this);

        if (n.elseClause != null) {
            n.elseClause.accept(this);
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(While n) {
        n.body.accept(this);
        n.condition.accept(this);

        return new BooleanType(n.line, n.row);
    }

    public Type visit(Print n) {
        return n.exp.accept(this);
    }

    public Type visit(Assign n) {
        Type var, exp;

        var = n.var.accept(this);
        exp = n.exp.accept(this);

        if (!var.isComparable(exp)) {
            err.Error(n, new Object[] { "Atribuicao invalida " + var.toString()
                    + " = " + exp.toString() });
        }

        return new IdentifierType(n.line, n.row, n.toString());
    }

    public Type visit(ArrayAssign n) {
        n.index.accept(this);
        n.value.accept(this);
        n.var.accept(this);

        return new IntegerType(n.line, n.row);
    }

    public Type visit(And n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!lhs.isComparable(rhs)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " && " + rhs.toString() });
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(LessThan n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!(lhs instanceof IntegerType)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " deve ser int" });
        }

        if (!(rhs instanceof IntegerType)) {
            err.Error(n, new Object[] { "Operador invalido " + rhs.toString()
                    + " deve ser int" });
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(Plus n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!lhs.isComparable(rhs)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " + " + rhs.toString() });
        }

        return new IntegerType(n.line, n.row);
    }

    public Type visit(Minus n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!lhs.isComparable(rhs)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " - " + rhs.toString() });
        }

        return new IntegerType(n.line, n.row);
    }

    public Type visit(Times n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!lhs.isComparable(rhs)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " * " + rhs.toString() });
        }

        return new IntegerType(n.line, n.row);
    }

    public Type visit(ArrayLookup n) {
        Type array = n.array.accept(this);

        if (!(array instanceof IntArrayType)) {
            err.Error(n, new Object[] { array.toString()
                    + " nao é um IntArrayType" });
        }

        if (!((n.index.accept(this)) instanceof IntegerType)) {
            err.Error(n, new Object[] { "Tipo do index de ArrayLookup errado" });
        }

        return new IntegerType(n.line, n.row);
    }

    public Type visit(ArrayLength n) {
        Type array = n.array.accept(this);

        if (!(array instanceof IntArrayType)) {
            err.Error(n, new Object[] { n.toString()
                    + " nao eh um IntArrayType" });
        }

        return new IntegerType(n.line, n.row);
    }

    public Type visit(Call n) {
        Type obj = n.object.accept(this);
        ClassInfo classInfo = null;
        MethodInfo methodInfo = null;

        // verifica se é um objeto
        if (obj instanceof IdentifierType) {
            // verifica se existe a classe instanciada
            classInfo = env.getClass(obj.toString());

            if (classInfo == null) {
                err.Error(n, new Object[] { "Classe " + obj.toString()
                        + " nao existe" });
            } else {
                // verifica se o método existe na classe
                methodInfo = env.getClassMethod(classInfo, n.method.s);

                if (methodInfo == null) {
                    err.Error(n, new Object[] { "Metodo " + n.method.s
                            + " nao existe" });
                } else {
                    List<Exp> callFormals = n.actuals;
                    List<VarInfo> methodFormals = methodInfo.formals;

                    // verifica se os parâmetros batem
                    if ((callFormals == null) || (methodFormals == null)) {
                        if (callFormals != null && methodFormals == null) {
                            err.Error(n,new Object[] { "Quantidade de parametros a mais na chamada" });
                        } else if (callFormals == null && methodFormals != null) {
                            err.Error(n,new Object[] { "Quantidade de parametros a menos na chamada" });
                        }
                    } else if (callFormals.size() > methodFormals.size()) {
                        err.Error(n,new Object[] { "Quantidade de parametros a mais na chamada" });
                    } else if (callFormals.size() < methodFormals.size()) {
                        err.Error(n,new Object[] { "Quantidade de parametros a menos na chamada" });
                    } else {
                        while (callFormals != null) {
                            Type cF = callFormals.head.accept(this);

                            if (!cF.isComparable(methodFormals.head.type)) {
                                if (cF instanceof IdentifierType) {
                                    Symbol c = Symbol.symbol(((IdentifierType) cF).name);
                                    ClassInfo c1 = env.classes.get(c);
                                    Boolean find = false;

                                    if (c1 == null) {
                                        err.Error(n,new Object[] { "Tipo de parametro difere" });
                                    } else {
                                        c1 = c1.base;
                                    }

                                    while (c1 != null) {
                                        cF = new IdentifierType(n.line, n.row,c1.name.toString());

                                        if (cF.isComparable(methodFormals.head.type)) {
                                            find = true;
                                            break;
                                        } else {
                                            c1 = c1.base;
                                        }
                                    }

                                    if (!find) {
                                        err.Error(n,new Object[] { "Tipo de parametro difere" });
                                    }
                                } else {
                                    err.Error(n,new Object[] { "Tipo de parametro difere" });
                                }
                            }
                            
                            methodFormals = methodFormals.tail;
                            callFormals = callFormals.tail;
                        }
                    }
                }
            }
        } else {
            err.Error(n, new Object[] { "O objeto " + n.object.toString()
                    + "nao eh instancia de nenhuma classe valida" });
        }

        if (methodInfo == null) {
            return new IntegerType(n.line, n.row);
        } else {
            return methodInfo.type;
        }
    }

    public Type visit(IntegerLiteral n) {
        Type type = new IntegerType(n.line, n.row);
        return type;
    }

    public Type visit(True n) {
        return new BooleanType(n.line, n.row);
    }

    public Type visit(False n) {
        return new BooleanType(n.line, n.row);
    }

    public Type visit(IdentifierExp n) {
        return n.name.accept(this);
    }

    public Type visit(This n) {
        if ((this.methodInfoAtual.name.toString()).equals("main")) {
            err.Error(n,
                    new Object[] { "Erro: uso do this em metodo estatico" });
        }

        return new IdentifierType(n.line, n.row, this.classInfoAtual.name.toString());
    }

    public Type visit(NewArray n) {
        if (!(n.size.accept(this)).toString().equals("int ")) {
            err.Error(n, new Object[] { "Tipo do index de Array errado" });
        }

        return new IntArrayType(n.line, n.row);
    }

    public Type visit(NewObject n) {
        this.classInfoObj = env.getClass(n.className.toString());

        if (this.classInfoObj == null) {
            err.Error(n, new Object[] { "Nao foi possivel instanciar a classe "
                    + n.className.toString() });
        }

        return new IdentifierType(n.line, n.row, n.className.toString());
    }

    public Type visit(Not n) {
        Type type = n.exp.accept(this);

        if (!(type instanceof BooleanType)) {
            err.Error(n,new Object[] { "Erro: parametro do operador ! nao eh booleano" });
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(Equal n) {
        Type lhs, rhs;

        lhs = n.lhs.accept(this);
        rhs = n.rhs.accept(this);

        if (!lhs.isComparable(rhs)) {
            err.Error(n, new Object[] { "Operador invalido " + lhs.toString()
                    + " = " + rhs.toString() });
        }

        return new BooleanType(n.line, n.row);
    }

    public Type visit(Identifier n) {
        Symbol id = Symbol.symbol(n.s);
        Type type = new IdentifierType(n.line, n.row, n.s);
        VarInfo varInfo;

        if (methodInfoAtual != null) {
            varInfo = methodInfoAtual.localsTable.get(id);

            if (varInfo == null) {
                varInfo = methodInfoAtual.formalsTable.get(id);

                if (varInfo == null) {
                    varInfo = classInfoAtual.attributes.get(id);

                    if (varInfo == null) {
                        err.Error(n,new Object[] { "erro: identificador não encontrado" });
                    } else {
                        type = varInfo.type;
                    }
                } else {
                    type = varInfo.type;
                }
            } else {
                type = varInfo.type;
            }
        } else {
            // verifica se é o nome de uma classe
            if (this.classInfoAtual.name.toString().equals(n.toString())) {
                return type;
            }

            varInfo = this.classInfoAtual.attributes.get(id);

            if (varInfo == null) {
                err.Error(n,new Object[] { "erro: identificador não encontrado" });
            } else {
                type = varInfo.type;
            }
        }

        return type;
    }
}

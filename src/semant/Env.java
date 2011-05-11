package semant;

import errors.ErrorEchoer;
import java.util.Hashtable;
import java.util.Vector;
import symbol.ClassInfo;
import symbol.MethodInfo;
import symbol.Symbol;
import symbol.Table;
import symbol.VarInfo;
import syntaxtree.ClassDecl;

public class Env {
    public ErrorEchoer err;
    public Table<ClassInfo> classes;
    public Hashtable<ClassInfo, String> hashHeranca;
    public Vector<ClassInfo> classHeranca;
    public Vector<ClassDecl> declHeranca;

    public Env(ErrorEchoer e) {
        super();
        err = e;
        classes = new Table<ClassInfo>();
        hashHeranca = new Hashtable<ClassInfo, String>();
        classHeranca = new Vector<ClassInfo>();
        declHeranca = new Vector<ClassDecl>();
    }

    // método para adicionar na tabela uma classe 
    public boolean addClass(ClassInfo classInfo) {
        return classes.put(classInfo.name, classInfo);
    }
    
    // método para adicionar na tabela uma variável de uma determinada classe
    public boolean addClassVar(ClassInfo classInfo, VarInfo varInfo) {
        return classInfo.addAttribute(varInfo);
    }

    // método para adicionar na tabela um métodos de uma determinada classe
    public boolean addClassMethod(ClassInfo classInfo, MethodInfo methodInfo) {
        return classInfo.addMethod(methodInfo);
    }

    // método para adicionar na tabela um parâmetro de um determinado método
    public boolean addMethodFormal(MethodInfo methodInfo, VarInfo formal) {
        return methodInfo.addFormal(formal);
    }
    
    // método para adicionar na tabela uma variável local de um determinado método
    public boolean addMethodLocal(MethodInfo methodInfo, VarInfo local) {
        return methodInfo.addLocal(local);
    }

    // método para obter da tabela uma classe
    public ClassInfo getClass(String classId) {
        Symbol classSymbol = Symbol.symbol(classId);
        return classes.get(classSymbol);
    }

    // método para obter da tabela o método de uma determinada classe
    public MethodInfo getClassMethod(ClassInfo classInfo, String methodId) {
        Symbol methodSymbol = Symbol.symbol(methodId);
        return classInfo.methods.get(methodSymbol);
    }

    // método para adicionar os atributos e métodos herdados na classe filha
    public void arrumaHeranca() {
        Vector<ClassInfo> heranca = new Vector<ClassInfo>();
        ClassInfo classInfo;

        for (int j = 0; j < classHeranca.size(); j++) {
            heranca.clear();
            ClassInfo classeAnalise = classHeranca.get(j);
            heranca.add(classeAnalise);
            classInfo = getClass(hashHeranca.get(classeAnalise));

            do {
                if (heranca.contains(classInfo)) {
                    err.Error(declHeranca.get(j),new Object[] { "Heranca ciclica identificada na classe "
                            + classeAnalise.name.toString() });
                    break;
                }
                
                heranca.add(classInfo);
                classeAnalise = classInfo;
                
                if (hashHeranca.get(classInfo) != null) {
                    classInfo = getClass(hashHeranca.get(classInfo));
                } else {
                    classInfo = null;
                }
            } while (classInfo != null);

            for (int i = heranca.size() - 2; i >= 0; i--) {
                if (heranca.get(i).base == null) {
                    heranca.get(i).setBase(heranca.get(i + 1));
                }
            }
        }
    }
}
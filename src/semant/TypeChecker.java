package semant;

import errors.ErrorEchoer;
import syntaxtree.Program;

public class TypeChecker {
    private TypeChecker() {
        super();
    }

    public static Env TypeCheck(ErrorEchoer err, Program p) {
        Env envirom = new Env(err);

        // Primeira passada - construção da árvore e algumas análises semânticas
        Semantic semantic = new Semantic(envirom, p, err);

        envirom.arrumaHeranca();

        // Segunda passada - análise semântica com dependências de visão
        TypeSemantic typeSemantic = new TypeSemantic(envirom, p, err);

        return null;
    }
}

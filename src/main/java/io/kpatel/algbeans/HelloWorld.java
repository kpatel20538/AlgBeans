package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.AlgBeanClass;
import io.kpatel.algbeans.entity.TypeConstructor;
import io.kpatel.algbeans.entity.TypeConstructorParameter;
import io.kpatel.algbeans.parser.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class HelloWorld {

    public static void main(String[] args) throws IOException {
        InputStream is = HelloWorld.class.getResourceAsStream("example.alg");
        CharStream cs = CharStreams.fromStream(is);
        AlgBeansLexer lexer = new AlgBeansLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        AlgBeansParser parser = new AlgBeansParser(tokens);
        ParseTree tree = parser.document();
        ParseTreeWalker walker = new ParseTreeWalker();
        AlgBeansFactoryListener listener = new AlgBeansFactoryListener();
        walker.walk(listener, tree);
        for(AlgBeanClass beanClass : listener.getBeans()) {
            System.out.printf("\n\npackage: %s\n",beanClass.getPackageName());

            for(String importPackage : beanClass.getImportPackages()) {
                System.out.printf("import: %s\n", importPackage);
            }
            System.out.printf("class: %s", beanClass.getClassName());

            if (!beanClass.getTypeParameters().isEmpty()) {
                System.out.print('<');
                delimitedPrint(beanClass.getTypeParameters(), ", ", null);
                System.out.print('>');
            }
            System.out.print('\n');
            for (TypeConstructor typeConstructor : beanClass.getTypeConstructors()) {
                System.out.printf("\t%s(",typeConstructor.getConstructorName());
                for (TypeConstructorParameter parameter : typeConstructor.getParameters()) {
                    System.out.print(parameter.getClassName());
                    if (!parameter.getTypeParameters().isEmpty()) {
                        System.out.print('<');
                        delimitedPrint(parameter.getTypeParameters(), ", ", null);
                        System.out.print('>');
                    }
                    System.out.printf(" %s", parameter.getParameterName());
                }
                System.out.print(")\n");
            }
        }
    }

    public static <E> void delimitedPrint(List<E> list, String delimiter, Function<E, String> function) {
        if (function == null) function = Objects::toString;
        if (!list.isEmpty()) {
            System.out.print(function.apply(list.get(0)));
            for(E item : list.subList(1, list.size())) {
                System.out.printf("%s%s", delimiter, function.apply(item));
            }
        }
    }
}

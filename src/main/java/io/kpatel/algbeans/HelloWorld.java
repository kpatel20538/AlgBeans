package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.AlgBeanClass;
import io.kpatel.algbeans.parser.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class HelloWorld {
    public static class MessageExtractor extends SimpleBaseListener {
        private String message = "";

        @Override
        public void enterDocument(SimpleParser.DocumentContext ctx) {
            message = ctx.TEXT().getText();
        }

        public String getMessage() {
            return message;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream is = HelloWorld.class.getResourceAsStream("example.alg");
        CharStream cs = CharStreams.fromStream(is);
        AlgBeansLexer lexer = new AlgBeansLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        AlgBeansParser parser = new AlgBeansParser(tokens);
        ParseTree tree = parser.document();
        ParseTreeWalker walker = new ParseTreeWalker();
        AlgBeanClassBuilder listener = new AlgBeanClassBuilder();
        walker.walk(listener, tree);
        AlgBeanClass beanClass  = listener.getBuilder();


    }
}

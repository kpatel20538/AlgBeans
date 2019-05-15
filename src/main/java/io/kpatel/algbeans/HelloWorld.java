package io.kpatel.algbeans;

import io.kpatel.algbeans.parser.SimpleBaseListener;
import io.kpatel.algbeans.parser.SimpleLexer;
import io.kpatel.algbeans.parser.SimpleParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

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
        InputStream is = HelloWorld.class.getResourceAsStream("simple.alg");
        CharStream cs = CharStreams.fromStream(is);
        SimpleLexer lexer = new SimpleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);
        ParseTree tree = parser.document();
        ParseTreeWalker walker = new ParseTreeWalker();
        MessageExtractor extractor = new MessageExtractor();
        walker.walk(extractor, tree);
        System.out.printf("Message: %s\n", extractor.getMessage());
    }
}

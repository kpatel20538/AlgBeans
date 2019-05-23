package io.kpatel.algbeans;

import io.kpatel.algbeans.antlr.UnionListener;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.parser.AlgBeansLexer;
import io.kpatel.algbeans.parser.AlgBeansParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 *  Actor Object that parses AlgBeans Readers, to produce in-memory Union Types via a generated parser.
 */
public class UnionCollector {
    public List<UnionType> collect(Reader reader) throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        UnionListener listener = new UnionListener();

        CharStream charStream = CharStreams.fromReader(reader);
        AlgBeansLexer lexer = new AlgBeansLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AlgBeansParser parser = new AlgBeansParser(tokens);
        walker.walk(listener, parser.document());
        return listener.getUnions();
    }
}

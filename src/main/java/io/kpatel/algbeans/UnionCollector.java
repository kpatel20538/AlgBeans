package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.parser.AlgBeansLexer;
import io.kpatel.algbeans.parser.AlgBeansParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *  Actor Object that parses AlgBeans Files to produce in-memory Union Types via a generated parser.
 *
 *  Input is specified in the inputFiles Path.
 *
 */
public class UnionCollector {
    public List<UnionType> collect(Path inputFile) throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        UnionCollectorListener listener = new UnionCollectorListener();
        walker.walk(listener, getParseTree(inputFile));
        return listener.getUnions();
    }

    private ParseTree getParseTree(Path inputFile) throws IOException {
        try (FileReader reader = new FileReader(inputFile.toFile())){
            CharStream charStream = CharStreams.fromReader(reader);
            AlgBeansLexer lexer = new AlgBeansLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgBeansParser parser = new AlgBeansParser(tokens);
            return parser.document();
        }
    }

    public List<UnionType> collect(List<? extends Path> inputFiles) throws IOException {
       List<UnionType> unionTypes = new ArrayList<>();
       for (Path inputPath : inputFiles) {
           unionTypes.addAll(collect(inputPath));
       }
       return unionTypes;
    }
}

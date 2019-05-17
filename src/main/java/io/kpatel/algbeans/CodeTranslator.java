package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CodeTranslator {
    public void translate(List<Path> inputPaths, Path outputDirectory) throws IOException {
        UnionCollector unionCollector = new UnionCollector();
        CodeGenerator codeGenerator = new CodeGenerator(outputDirectory);

        for (UnionType union: unionCollector.collect(inputPaths)) {
            codeGenerator.generate(union);
        }
    }
}

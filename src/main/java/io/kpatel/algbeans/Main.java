package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("./example.alg");
        Path outDirectory = Paths.get("./code-gen/");

        UnionCollector unionCollector = new UnionCollector();
        CodeGenerator codeGenerator = new CodeGenerator(outDirectory);

        for (UnionType union: unionCollector.collect(inputPath)) {
            codeGenerator.generate(union);
        }
    }
}

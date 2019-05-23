package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *  Actor Object that takes in IO paths to translate AlgBean Files to Java Source Files
 */
public class CodeTranslator {
    public void translate(List<Path> inputPaths, Path outputDirectory, CleaningPolicy policy) throws IOException {
        PackageResolver packageResolver = new PackageResolver(outputDirectory, policy);

        List<UnionType> unions = collectPhase(inputPaths);
        generatePhase(packageResolver, unions);
    }

    private void generatePhase(PackageResolver packageResolver, List<UnionType> unions) throws IOException {
        for (UnionType union: unions) {
            packageResolver.cleanPackage(union);
        }

        CodeGenerator codeGenerator = new CodeGenerator();
        for (UnionType union: unions) {
            try (Writer writer = packageResolver.makeSourceWriter(union)) {
                codeGenerator.generate(union, writer);
            }
        }
    }

    private List<UnionType> collectPhase(List<Path> inputPaths) throws IOException {
        UnionCollector unionCollector = new UnionCollector();
        List<UnionType> unions = new ArrayList<>();
        for (Path inputPath: inputPaths) {
            try(Reader reader = Files.newBufferedReader(inputPath)) {
                unions.addAll(unionCollector.collect(reader));
            }
        }
        return unions;
    }


}

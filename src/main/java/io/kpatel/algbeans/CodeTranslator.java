package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.entity.java.JavaPackage;
import io.kpatel.algbeans.util.DirectoryUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Actor Object that takes in IO paths to translate AlgBean Files to Java Source Files
 */
public class CodeTranslator {
    public void translate(List<Path> inputPaths, Path outputDirectory) throws IOException {
        String tempName = String.format("temp-%s", outputDirectory.getFileName());
        Path tempDirectory = Files.createTempDirectory(tempName);

        List<UnionType> unions = collectPhase(inputPaths);
        generatePhase(tempDirectory, unions);
        cleanPhase(outputDirectory, unions);
        movePhase(tempDirectory, outputDirectory);
    }

    /** Clean up any previous build files  */
    private void cleanPhase(Path outputDirectory, List<UnionType> unions) throws IOException {
        for (UnionType union : unions) {
            JavaPackage packageLine = union.getPackageLine();
            if (!packageLine.isDefault())  {
                Path relTopLevelPkg = packageLine.toPath().getName(0);
                Path absTopLevelPkg = outputDirectory.resolve(relTopLevelPkg);
                DirectoryUtils.deleteDirectory(absTopLevelPkg);
            }
        }
    }

    /** Move the new source files to the output directory */
    private void movePhase(Path tempDirectory, Path outputDirectory) throws IOException {
        Files.createDirectories(outputDirectory);
        for ( Path p : Files.walk(tempDirectory).collect(Collectors.toList())) {
            if (!Files.isDirectory(p)) {
                Path dest = outputDirectory.resolve(tempDirectory.relativize(p));
                Files.createDirectories(dest.getParent());
                Files.move(p, dest, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    /** Generate Java Source Files for each given Unions */
    private void generatePhase(Path outputDirectory, List<UnionType> unions) throws IOException {
        CodeGenerator codeGenerator = new CodeGenerator();
        for (UnionType union: unions) {
            Path relativePath = union.getPackageLine().toPath();
            Path packagePath = outputDirectory.resolve(relativePath);
            Files.createDirectories(packagePath);

            String fileName = String.format("%s.java", union.getTypeName());
            Path sourcePath = packagePath.resolve(fileName);
            try (Writer writer = Files.newBufferedWriter(sourcePath)) {
                codeGenerator.generate(union, writer);
            }
        }
    }

    /** Collect all Unions from the list of Alg Files files */
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

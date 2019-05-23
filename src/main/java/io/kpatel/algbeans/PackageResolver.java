package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An Actor object that will resolve paths and writers for Union
 */
public class PackageResolver {
    private Path outputDirectory;

    public PackageResolver(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public Writer makeSourceWriter(UnionType union) throws IOException {
        Path packagePath = resolvePackage(union);
        Files.createDirectories(packagePath);
        String fileName = String.format("%s.java", union.getTypeName());
        return Files.newBufferedWriter(packagePath.resolve(fileName));
    }

    public Path resolvePackage(UnionType union) {
        Path outPath = outputDirectory;
        for (String packageName : union.getPackageName().split("\\.")) {
            outPath = outPath.resolve(packageName);
        }
        return outPath;
    }
}

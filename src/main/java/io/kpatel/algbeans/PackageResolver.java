package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An Actor object that will resolvePackage output paths
 *
 * It cleans the output folder of any orphaned files in the output directory.
 *
 */
public class PackageResolver {

    private Path outputDirectory;
    private CleaningPolicy policy;

    public PackageResolver(Path outputDirectory, CleaningPolicy policy) {
        this.outputDirectory = outputDirectory;
        this.policy = policy;
    }

    public Writer makeSourceWriter(UnionType union) throws IOException {
        Path source = resolveSource(union);
        Files.createDirectories(source.getParent());
        return Files.newBufferedWriter(source);
    }

    private Path resolvePackage(UnionType union) {
        Path outPath = outputDirectory;
        for (String packageName : union.getPackageName().split("\\.")) {
            outPath = outPath.resolve(packageName);
        }
        return outPath;
    }

    private Path resolveSource(UnionType union) {
        String fileName = String.format("%s.java", union.getTypeName());
        return resolvePackage(union).resolve(fileName);
    }


    public void cleanPackage(UnionType union) throws IOException {
        Path path = resolvePackage(union);
        if (Files.exists(path) && policy.test(path)) {
            deleteDirectoryContents(path);
        }
    }

    private void delete(Path path) throws IOException {
        deleteDirectoryContents(path);
        Files.delete(path);
    }

    private void deleteDirectoryContents(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    delete(entry);
                }
            }
        }
    }
}

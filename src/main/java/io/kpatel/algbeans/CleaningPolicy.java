package io.kpatel.algbeans;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *   Determines whether a file should be cleaned up before code is generated
 */
public interface CleaningPolicy {
    boolean test(Path path) throws IOException;

    default void clean(Path path) throws IOException {
        if (Files.exists(path) && test(path)) {
            cleanContents(path);
            Files.delete(path);
        }
    }

    default void cleanContents(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    clean(entry);
                }
            }
        }
    }
}

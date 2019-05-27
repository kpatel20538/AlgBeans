package io.kpatel.algbeans.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DirectoryUtils {
    private static class FileTransfer {
        private final Path source;
        private final Path destination;

        private FileTransfer(Path source, Path destination) {
            this.source = source;
            this.destination = destination;
        }

        public Path getSource() {
            return source;
        }

        public Path getDestination() {
            return destination;
        }
    }

    public static void moveDirectory(Path source, Path destination) throws IOException {
        directoryTransfer(source, destination)
            .forEach(ExceptionConsumer.toConsumer(transfer -> {
                Files.createDirectories(transfer.getDestination().getParent());
                Files.move(transfer.getSource(), transfer.getDestination());
            }));
        deleteDirectory(source);
    }

    public static void copyDirectory(Path source, Path destination) throws IOException {
        directoryTransfer(source, destination)
                .forEach(ExceptionConsumer.toConsumer(transfer -> {
                    Files.createDirectories(transfer.getDestination().getParent());
                    Files.copy(transfer.getSource(), transfer.getDestination());
                }));
    }

    public static void deleteDirectory(Path source) throws IOException {
        Files.walk(source)
                .sorted(Comparator.reverseOrder())
                .forEach(ExceptionConsumer.toConsumer(Files::delete));
    }

    private static Stream<FileTransfer> directoryTransfer(Path source , Path destination) throws IOException {
        return Files.walk(source)
                .filter(path -> !Files.isDirectory(path))
                .map(srcFile -> {
                    Path common = source.relativize(srcFile);
                    Path dstFile = destination.resolve(common);
                    return new FileTransfer(srcFile, dstFile);
                });
    }

    public static Stream<Path> rootDirectories() {
        return rootDirectories(FileSystems.getDefault());
    }

    public static Stream<Path> rootDirectories(FileSystem fileSystem) {
        return StreamSupport.stream(fileSystem.getRootDirectories().spliterator(), false);
    }
}

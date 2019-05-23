package io.kpatel.algbeans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public interface CleaningPolicy {
    CleaningPolicy ALWAYS = path -> true;
    CleaningPolicy NEVER = path -> false;
    CleaningPolicy NEVER_CWD = path -> !Files.isSameFile(path, Paths.get("."));

    boolean test(Path path) throws IOException;
}

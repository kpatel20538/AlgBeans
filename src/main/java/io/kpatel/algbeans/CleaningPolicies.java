package io.kpatel.algbeans;

import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Constants Holder and Utility Class for Cleaning Policies
 */
public class CleaningPolicies {
    private CleaningPolicies() { }

    public static final CleaningPolicy NEVER_CWD = path -> !Files.isSameFile(path, Paths.get("."));
    public static final CleaningPolicy ALWAYS = path -> true;
    public static final CleaningPolicy NEVER = path -> false;


}

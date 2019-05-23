package io.kpatel.algbeans.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import io.kpatel.algbeans.CleaningPolicies;
import io.kpatel.algbeans.CleaningPolicy;
import io.kpatel.algbeans.CodeTranslator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  Parse and validates Arguments and defers to an Actor Object to convert AlgBean Files to Java Source Files.
 *
 * @see CodeTranslator
 */
public class CliMain {

    @Parameter(names = "--in", converter = PathConverter.class, variableArity = true, required = true)
    private List<Path> inputPaths = new ArrayList<>();
    @Parameter(names = "--out", converter = PathConverter.class)
    private Path outputDirectory = Paths.get(".");

    @Parameter(names = "--help", help = true)
    private boolean help;

    @Parameter(
            names = "--cleaning-policy",
            converter = CleaningPolicyConverter.class,
            validateWith = CleaningPolicyValidator.class
    )
    private CleaningPolicy cleaningPolicy = CleaningPolicies.NEVER_CWD;


    public static void main(String[] args) throws IOException {
        CliMain main = new CliMain();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws IOException {
        new CodeTranslator().translate(inputPaths, outputDirectory, cleaningPolicy);
    }
}

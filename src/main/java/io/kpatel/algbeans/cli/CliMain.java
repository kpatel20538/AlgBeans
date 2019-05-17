package io.kpatel.algbeans.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import io.kpatel.algbeans.CodeGenerator;
import io.kpatel.algbeans.CodeTranslator;
import io.kpatel.algbeans.UnionCollector;
import io.kpatel.algbeans.entity.UnionType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CliMain {

    @Parameter(names = "--in", converter = PathConverter.class, variableArity = true)
    private List<Path> inputPaths = new ArrayList<>();
    @Parameter(names = "--out", converter = PathConverter.class)
    private Path outputDirectory = Paths.get(".");

    @Parameter(names = "--help", help = true)
    private boolean help;


    public static void main(String[] args) throws IOException {
        CliMain main = new CliMain();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws IOException {
        if (inputPaths.isEmpty()) {
            System.err.println("Specify Input Paths with the '--in FILE ...' parameter");
            return;
        }

        new CodeTranslator().translate(inputPaths, outputDirectory);
    }
}

package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.java.JavaUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *  Actor Object that takes in in-memory Union Types to produce Java Source files via template engine.
 *
 *  Output is put in the outputDirectory Path.
 *
 */

public class CodeGenerator {
    private VelocityEngine velocityEngine;
    private Template templateUnion;
    private VelocityContext context;
    private Path outputDirectory;

    public CodeGenerator(Path outputDirectory) throws IOException {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();

        templateUnion = velocityEngine.getTemplate("UnionTemplate.vm");

        context = new VelocityContext();
        context.put("util", new JavaUtil());

        this.outputDirectory = outputDirectory;
    }

    public void generate(UnionType union) throws IOException {
        context.put("union", union);
        try (FileWriter writer = new FileWriter(toOutPath(union).toFile())) {
            templateUnion.merge( context, writer );
        }
    }

    private Path toOutPath(UnionType union) throws IOException {
        Path outPath = outputDirectory;
        for (String packageName : union.getPackageName().split("\\.")) {
            outPath = outPath.resolve(packageName);
        }
        String fileName = String.format("%s.java", union.getTypeName());
        Files.createDirectories(outPath);
        return outPath.resolve(fileName);
    }
}

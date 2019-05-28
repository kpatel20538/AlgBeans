package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.util.JavaUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.Writer;

/**
 *  Actor Object that takes in in-memory Union Types to produce Java Source files via template engine.
 */
public class CodeGenerator {
    private VelocityEngine velocityEngine;
    private Template templateUnion;
    private VelocityContext context;

    public CodeGenerator() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();

        templateUnion = velocityEngine.getTemplate("UnionTemplate.vm");

        context = new VelocityContext();
        context.put("util", new JavaUtil());
    }

    public void generate(UnionType union, Writer writer) {
        context.put("union", union);
        templateUnion.merge( context, writer );
    }
}

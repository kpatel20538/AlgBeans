import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.java.JavaUtil;
import io.kpatel.algbeans.java.element.JavaField;
import io.kpatel.algbeans.java.element.JavaImport;
import io.kpatel.algbeans.java.type.JavaReferenceType;
import io.kpatel.algbeans.java.type.JavaTypeArgument;
import io.kpatel.algbeans.java.type.JavaTypeParameter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();

        Template t = velocityEngine.getTemplate("UnionTemplate.vm");

        VelocityContext context = new VelocityContext();
        context.put("util", new JavaUtil());
        context.put("union", new UnionType(
                "com.example",
                Arrays.asList(new JavaImport("java.util.List"), new JavaImport("java.time.LocalDate")),
                "Person",
                Collections.singletonList(new JavaTypeParameter("A", Arrays.asList(new JavaReferenceType("Serializable"), new JavaReferenceType("Comparable", Collections.singletonList(new JavaTypeArgument("A")))))),
                Arrays.asList(
                        new ProductType("Manager", Arrays.asList(
                                new JavaField(new JavaReferenceType("String"), "name"),
                                new JavaField(new JavaReferenceType("List", Collections.singletonList(new JavaTypeArgument(JavaTypeArgument.Bound.SUPER, new JavaReferenceType("A")))), "actions")
                        )),
                        new ProductType("Supervisor", Arrays.asList(
                                new JavaField(new JavaReferenceType("String"), "name"),
                                new JavaField(new JavaReferenceType("LocaleDate"), "startingDate"),
                                new JavaField(new JavaReferenceType("List", Collections.singletonList(new JavaTypeArgument("A"))), "actions")
                        )),
                        new ProductType("Employee", Arrays.asList(
                                new JavaField(new JavaReferenceType("String"), "name"),
                                new JavaField(new JavaReferenceType("LocaleDate"), "birthday"),
                                new JavaField(new JavaReferenceType("List", Collections.singletonList(new JavaTypeArgument(JavaTypeArgument.Bound.EXTENDS, new JavaReferenceType("A")))), "actions")
                        ))
                )
        ));

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println(writer.toString());
    }


}

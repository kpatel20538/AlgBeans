package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.JavaAnnotation;
import io.kpatel.algbeans.entity.java.type.JavaType;
import io.kpatel.algbeans.parser.AlgBeansParser;

/**
 *  A Factory function that generates Java Annotation from ParseTrees.
 */
public class AnnotationVisitor {
    private AnnotationVisitor() { }

    public static JavaAnnotation visit(AlgBeansParser.AnnotationContext ctx) {
        JavaType javaType = new TypeVisitor().visit(ctx.typeName());
        if (ctx.annotationElements() != null) {
            return new JavaAnnotation(javaType, ctx.annotationElements().getText().trim());
        } else {
            return new JavaAnnotation(javaType);
        }
    }
}

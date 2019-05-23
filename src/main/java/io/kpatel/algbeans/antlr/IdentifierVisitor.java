package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.parser.AlgBeansParser;

/**
 *  A Factory function that generates Java Identifiers from ParseTrees.
 */
public class IdentifierVisitor {
    public static JavaIdentifier visit(AlgBeansParser.IdentifierContext ctx) {
        return new JavaIdentifier(ctx.JAVA_IDENTIFIER().getText());
    }
}
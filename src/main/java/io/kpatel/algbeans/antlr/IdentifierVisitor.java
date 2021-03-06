package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.parser.AlgBeansParser;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

/**
 *  A Factory Object that generates Java Identifiers from ParseTrees.
 */
public class IdentifierVisitor {
    public JavaIdentifier visit(AlgBeansParser.IdentifierContext ctx) {

        return new JavaIdentifier(ctx.JAVA_IDENTIFIER().getText());
    }
}

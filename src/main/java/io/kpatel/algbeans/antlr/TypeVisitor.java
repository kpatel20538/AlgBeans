package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.*;
import io.kpatel.algbeans.parser.AlgBeansBaseVisitor;
import io.kpatel.algbeans.parser.AlgBeansParser;

/**
 *  A Visitor that supplies JavaType and its implementers, JavaPrimitiveType, and JavaReferenceType
 *
 *  A Visitor was selected over A Listener as Java Types has self-referential structure.
 *  e.g. List&lt; List&lt; List&lt; ... &gt; &gt; &gt;
 *
 * @see JavaType
 * @see JavaPrimitiveType
 * @see JavaReferenceType
 */
public class TypeVisitor extends AlgBeansBaseVisitor<JavaType> {

    @Override
    public JavaType visitTypeName(AlgBeansParser.TypeNameContext ctx) {
        JavaType type = ctx.primitiveType() != null
                ? visit(ctx.primitiveType())
                : visit(ctx.referenceType());
        return ctx.arraySuffix() != null
                ? new JavaArrayType(type)
                : type;
    }

    @Override
    public JavaPrimitiveType visitPrimitiveType(AlgBeansParser.PrimitiveTypeContext ctx) {
        switch (ctx.PRIMITIVE().getText().trim()) {
            case "float"   : return JavaPrimitiveType.FLOAT;
            case "double"  : return JavaPrimitiveType.DOUBLE;
            case "byte"    : return JavaPrimitiveType.BYTE;
            case "short"   : return JavaPrimitiveType.SHORT;
            case "int"     : return JavaPrimitiveType.INT;
            case "long"    : return JavaPrimitiveType.LONG;
            case "char"    : return JavaPrimitiveType.CHAR;
            case "boolean" : return JavaPrimitiveType.BOOLEAN;
            default: throw new IllegalStateException("Malformed Grammar");
        }
    }

    @Override
    public JavaReferenceType visitReferenceType(AlgBeansParser.ReferenceTypeContext ctx) {
        JavaReferenceType referenceType = new JavaReferenceType();
        for (AlgBeansParser.TypeDeclContext typeDeclContext: ctx.typeDecl()) {
            referenceType.addTypeDecls(toTypeDecl(typeDeclContext));
        }

        return referenceType;
    }


    private JavaTypeDecl toTypeDecl(AlgBeansParser.TypeDeclContext ctx) {
        JavaIdentifier typeName = new IdentifierVisitor().visit(ctx.identifier());
        JavaTypeDecl typeDecl = new JavaTypeDecl(typeName);
        if (ctx.typeArguments() != null) {
            for (AlgBeansParser.TypeArgumentContext typeArgument : ctx.typeArguments().typeArgument()) {
                typeDecl.addTypeArgument(toTypeArgument(typeArgument));
            }
        }
        return typeDecl;
    }

    private JavaTypeArgument toTypeArgument(AlgBeansParser.TypeArgumentContext ctx) {
        if (ctx.referenceType() != null) {
            JavaReferenceType referenceType = visitReferenceType(ctx.referenceType());
            if (ctx.EXTENDS() != null) {
                return new JavaTypeArgument(JavaTypeArgument.Bound.EXTENDS, referenceType);
            } else if (ctx.SUPER() != null) {
                return new JavaTypeArgument(JavaTypeArgument.Bound.SUPER, referenceType);
            } else {
                return new JavaTypeArgument(JavaTypeArgument.Bound.SPECIFICALLY, referenceType);
            }
        } else {
            return new JavaTypeArgument(JavaTypeArgument.Bound.UNBOUNDED);
        }

    }

}

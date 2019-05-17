package io.kpatel.algbeans;

import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.java.JavaField;
import io.kpatel.algbeans.java.JavaImport;
import io.kpatel.algbeans.java.type.*;
import io.kpatel.algbeans.parser.AlgBeansBaseListener;
import io.kpatel.algbeans.parser.AlgBeansParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Traversal Object that traverses a Parse Tree and constructs a list of Union Types
 *
 *  This object follows the Listener Pattern when constructing AlgBean types.
 *    and the Visitor Pattern when constructing Java Types, as the latter has
 *    self-recurring structure (List&lt; List&lt; List&lt; ... &gt; &gt; &gt;),
 *    while the former does not.
 *
 */
public class UnionCollectorListener extends AlgBeansBaseListener {
    private String packageName;
    private List<JavaImport> imports;
    private List<UnionType> unions;
    private UnionType currentUnion;
    private JavaTypeParameter currentJtp;
    private ProductType currentProduct;


    public UnionCollectorListener() {
        packageName = "";
        imports = new ArrayList<>();
        unions = new ArrayList<>();
        currentUnion = null;
        currentJtp = null;
        currentProduct = null;
    }

    public List<UnionType> getUnions() {
        return Collections.unmodifiableList(unions);
    }

    @Override
    public void enterPackageLine(AlgBeansParser.PackageLineContext ctx) {
        packageName = ctx.packageName().getText();
    }

    @Override
    public void enterImportLine(AlgBeansParser.ImportLineContext ctx) {
        boolean isStatic = ctx.STATIC() != null;
        String pattern = ctx.packagePattern().getText();
        imports.add(new JavaImport(pattern, isStatic));
    }

    @Override
    public void enterUnionLine(AlgBeansParser.UnionLineContext ctx) {
        currentUnion = new UnionType();
        currentUnion.setPackageName(packageName);
        for (JavaImport importLine : imports) {
            currentUnion.addImport(importLine);
        }

    }

    @Override
    public void enterUnionType(AlgBeansParser.UnionTypeContext ctx) {
        currentUnion.setTypeName(ctx.JAVA_IDENTIFIER().getText());
    }

    @Override
    public void exitUnionLine(AlgBeansParser.UnionLineContext ctx) {
        unions.add(currentUnion);
        currentUnion = null;
    }

    @Override
    public void enterTypeParameter(AlgBeansParser.TypeParameterContext ctx) {
        currentJtp = new JavaTypeParameter();
        currentJtp.setTypeName(ctx.typeVariable().getText());
    }

    @Override
    public void exitTypeParameter(AlgBeansParser.TypeParameterContext ctx) {
        currentUnion.addTypeParameter(currentJtp);
        currentJtp = null;
    }

    @Override
    public void enterTypeBounds(AlgBeansParser.TypeBoundsContext ctx) {
         for(AlgBeansParser.ReferenceTypeContext referenceType : ctx.referenceType()) {
             currentJtp.addBound(toReferenceType(referenceType));
         }
    }

    @Override
    public void enterProductType(AlgBeansParser.ProductTypeContext ctx) {
        currentProduct = new ProductType();
        currentProduct.setTypeName(ctx.JAVA_IDENTIFIER().getText());
    }

    @Override
    public void exitProductType(AlgBeansParser.ProductTypeContext ctx) {
        currentUnion.addProductType(currentProduct);
        currentProduct = null;
    }

    @Override
    public void enterFieldDeclaration(AlgBeansParser.FieldDeclarationContext ctx) {
        JavaField field = new JavaField(toType(ctx.typeName()), ctx.JAVA_IDENTIFIER().getText());
        currentProduct.addField(field);
    }

    private JavaType toType(AlgBeansParser.TypeNameContext ctx) {
        JavaType type;
        if (ctx.PRIMITIVE() != null) {
            switch (ctx.PRIMITIVE().getText().trim()) {
                case "float": type = JavaPrimitiveType.FLOAT; break;
                case "double": type = JavaPrimitiveType.DOUBLE; break;
                case "byte": type = JavaPrimitiveType.BYTE; break;
                case "short": type = JavaPrimitiveType.SHORT; break;
                case "int": type = JavaPrimitiveType.INT; break;
                case "long": type = JavaPrimitiveType.LONG; break;
                case "char": type = JavaPrimitiveType.CHAR; break;
                case "boolean": type = JavaPrimitiveType.BOOLEAN; break;
                default: throw new IllegalStateException("Malformed ANTLR Grammar");
            }
        } else {
            type = toReferenceType(ctx.referenceType());
        }
        return ctx.arraySuffix() == null ? type : new JavaArrayType(type);
    }

    private JavaReferenceType toReferenceType(AlgBeansParser.ReferenceTypeContext ctx) {
        JavaReferenceType referenceType = new JavaReferenceType();
        for (AlgBeansParser.TypeDeclContext typeDeclContext: ctx.typeDecl()) {
            referenceType.addTypeDecls(toTypeDecl(typeDeclContext));
        }
        return referenceType;
    }

    private JavaTypeDecl toTypeDecl(AlgBeansParser.TypeDeclContext ctx) {
        JavaTypeDecl typeDecl = new JavaTypeDecl();
        typeDecl.setTypeName(ctx.JAVA_IDENTIFIER().getText());
        if (ctx.typeArguments() != null) {
            for (AlgBeansParser.TypeArgumentContext typeArgument : ctx.typeArguments().typeArgument()) {
                typeDecl.addTypeArgument(toTypeArgument(typeArgument));
            }
        }
        return typeDecl;
    }

    private JavaTypeArgument toTypeArgument(AlgBeansParser.TypeArgumentContext ctx) {
        JavaTypeArgument typeArgument = new JavaTypeArgument();
        if (ctx.referenceType() != null) {
            typeArgument.setType(toReferenceType(ctx.referenceType()));
            typeArgument.setBound(JavaTypeArgument.Bound.SPECIFICALLY);
        } else if (ctx.wildcard().wildcardBounds() == null) {
            typeArgument.setBound(JavaTypeArgument.Bound.UNBOUNDED);
        } else {
            AlgBeansParser.WildcardBoundsContext bounds = ctx.wildcard().wildcardBounds();
            typeArgument.setType(toReferenceType(bounds.referenceType()));
            if (bounds.EXTENDS() != null) {
                typeArgument.setBound(JavaTypeArgument.Bound.EXTENDS);
            } else {
                typeArgument.setBound(JavaTypeArgument.Bound.SUPER);
            }
        }
        return typeArgument;
    }
}

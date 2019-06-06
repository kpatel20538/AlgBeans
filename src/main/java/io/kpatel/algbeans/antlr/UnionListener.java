package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.JavaImport;
import io.kpatel.algbeans.entity.java.JavaPackage;
import io.kpatel.algbeans.entity.java.type.JavaAnnotation;
import io.kpatel.algbeans.entity.java.type.JavaType;
import io.kpatel.algbeans.entity.java.type.JavaTypeParameter;
import io.kpatel.algbeans.parser.AlgBeansBaseListener;
import io.kpatel.algbeans.parser.AlgBeansParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Listener Object that traverses a Parse Tree and constructs a list of Union Types
 *
 *  This object will defer to A Visitor Object when parsing a JavaType.
 *
 * @see TypeVisitor
 */
public class UnionListener extends AlgBeansBaseListener {
    private JavaPackage packageLine;
    private List<JavaImport> importLines;
    private List<UnionType> unions;
    private UnionType currentUnion;
    private JavaTypeParameter currentJtp;
    private ProductType currentProduct;


    public UnionListener() {
        packageLine = new JavaPackage();
        importLines = new ArrayList<>();
        unions = new ArrayList<>();
        currentUnion = null;
        currentJtp = null;
        currentProduct = null;
    }

    public List<UnionType> getUnions() {
        return Collections.unmodifiableList(unions);
    }

    public JavaPackage getPackageLine() {
        return packageLine;
    }

    public List<JavaImport> getImportLines() {
        return Collections.unmodifiableList(importLines);
    }

    @Override
    public void enterPackageLine(AlgBeansParser.PackageLineContext ctx) {
        packageLine = new JavaPackage(ctx.packageName().getText().trim());
    }

    @Override
    public void enterImportLine(AlgBeansParser.ImportLineContext ctx) {
        String importPattern = ctx.packagePattern().getText().trim();
        JavaImport importLine = new JavaImport(importPattern);
        importLine.setStatic(ctx.STATIC() != null);
        importLines.add(importLine);
    }

    @Override
    public void exitUnionLine(AlgBeansParser.UnionLineContext ctx) {
        unions.add(currentUnion);
        currentUnion = null;
    }

    @Override
    public void enterUnionType(AlgBeansParser.UnionTypeContext ctx) {
        JavaIdentifier typeName = new IdentifierVisitor().visit(ctx.identifier());
        currentUnion = new UnionType(typeName);
        currentUnion.setPackageLine(packageLine);
        for (JavaImport importLine : importLines) {
            currentUnion.addImport(importLine);
        }
        if (ctx.annotation() != null) {

            for (AlgBeansParser.AnnotationContext annotationCtx : ctx.annotation()) {
                currentUnion.addAnnotation( new JavaAnnotation(ParseTreeUtil.toString(annotationCtx)));
            }
        }
    }

    @Override
    public void enterTypeParameter(AlgBeansParser.TypeParameterContext ctx) {
        JavaIdentifier typeName = new IdentifierVisitor().visit(ctx.identifier());
        currentJtp = new JavaTypeParameter(typeName);
    }

    @Override
    public void exitTypeParameter(AlgBeansParser.TypeParameterContext ctx) {
        currentUnion.addTypeParameter(currentJtp);
        currentJtp = null;
    }

    @Override
    public void enterTypeBounds(AlgBeansParser.TypeBoundsContext ctx) {
        TypeVisitor typeVisitor = new TypeVisitor();
        for(AlgBeansParser.ReferenceTypeContext referenceType : ctx.referenceType()) {
            currentJtp.addBound(typeVisitor.visitReferenceType(referenceType));
        }
    }

    @Override
    public void enterProductType(AlgBeansParser.ProductTypeContext ctx) {
        JavaIdentifier typeName = new IdentifierVisitor().visit(ctx.identifier());
        currentProduct = new ProductType(typeName);
    }

    @Override
    public void exitProductType(AlgBeansParser.ProductTypeContext ctx) {
        currentUnion.addProductType(currentProduct);
        currentProduct = null;
    }

    @Override
    public void enterFieldDeclaration(AlgBeansParser.FieldDeclarationContext ctx) {
        TypeVisitor typeVisitor = new TypeVisitor();
        JavaType type = typeVisitor.visit(ctx.typeName());
        JavaIdentifier name = new IdentifierVisitor().visit(ctx.identifier());
        JavaField field = new JavaField(type, name);
        for (TerminalNode node: ctx.MODIFIER()) {
            switch (node.getText().trim()) {
                case "final": field.enableFinal(); break;
                case "transient": field.enableTransient(); break;
                case "volatile": field.enableVolatile(); break;
                case "synchronized": field.enableSynchronized(); break;
            }
        }
        if (ctx.fieldInit() != null) {
            field.setInitializer(ParseTreeUtil.toString(ctx.fieldInit()));
        }
        currentProduct.addField(field);
    }


}

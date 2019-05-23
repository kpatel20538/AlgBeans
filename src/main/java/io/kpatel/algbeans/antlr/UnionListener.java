package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.JavaImport;
import io.kpatel.algbeans.entity.java.type.*;
import io.kpatel.algbeans.parser.AlgBeansBaseListener;
import io.kpatel.algbeans.parser.AlgBeansParser;

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
    private String packageName;
    private List<JavaImport> imports;
    private List<UnionType> unions;
    private UnionType currentUnion;
    private JavaTypeParameter currentJtp;
    private ProductType currentProduct;


    public UnionListener() {
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

    public String getPackageName() {
        return packageName;
    }

    public List<JavaImport> getImports() {
        return Collections.unmodifiableList(imports);
    }

    @Override
    public void enterPackageLine(AlgBeansParser.PackageLineContext ctx) {
        packageName = ctx.packageName().getText().trim();
    }

    @Override
    public void enterImportLine(AlgBeansParser.ImportLineContext ctx) {
        String importPattern = ctx.packagePattern().getText().trim();
        JavaImport importLine = new JavaImport(importPattern);
        importLine.setStatic(ctx.STATIC() != null);
        imports.add(importLine);
    }

    @Override
    public void exitUnionLine(AlgBeansParser.UnionLineContext ctx) {
        unions.add(currentUnion);
        currentUnion = null;
    }

    @Override
    public void enterUnionType(AlgBeansParser.UnionTypeContext ctx) {
        JavaIdentifier typeName = IdentifierVisitor.visit(ctx.identifier());
        currentUnion = new UnionType(typeName);
        currentUnion.setPackageName(packageName);
        for (JavaImport importLine : imports) {
            currentUnion.addImport(importLine);
        }
    }

    @Override
    public void enterTypeParameter(AlgBeansParser.TypeParameterContext ctx) {
        JavaIdentifier typeName = IdentifierVisitor.visit(ctx.identifier());
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
        JavaIdentifier typeName = IdentifierVisitor.visit(ctx.identifier());
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
        JavaIdentifier name = IdentifierVisitor.visit(ctx.identifier());
        JavaField field = new JavaField(type, name);
        currentProduct.addField(field);
    }


}

package io.kpatel.algbeans.parser;

import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.UnionType;
import io.kpatel.algbeans.java.element.JavaImport;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnionCollector extends AlgBeansBaseListener {
    private String packageName;
    private List<JavaImport> imports;
    private List<UnionType> unions;
    private UnionType currentUnion;
    private ProductType currentProduct;

    public UnionCollector() {
        packageName = "";
        imports = new ArrayList<>();
        unions = new ArrayList<>();
        currentUnion = null;
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


}

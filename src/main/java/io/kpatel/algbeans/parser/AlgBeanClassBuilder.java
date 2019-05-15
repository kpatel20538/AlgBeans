package io.kpatel.algbeans.parser;

import io.kpatel.algbeans.entity.AlgBeanClass;

public class AlgBeanClassBuilder extends AlgBeansBaseListener {
    private AlgBeanClass builder;

    public AlgBeanClassBuilder() {
        builder = new AlgBeanClass();
    }

    @Override
    public void enterPackageLine(AlgBeansParser.PackageLineContext ctx) {
        String packageName = ctx.QUALIFIED_NAME().getText();
        builder.setPackageName(packageName);
    }

    @Override
    public void enterImportLine(AlgBeansParser.ImportLineContext ctx) {
        String importName = ctx.QUALIFIED_NAME().getText();
        builder.addImportPackage(importName);
    }

    public AlgBeanClass getBuilder() {
        return builder;
    }
}

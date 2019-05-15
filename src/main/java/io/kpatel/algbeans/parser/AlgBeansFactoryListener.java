package io.kpatel.algbeans.parser;

import io.kpatel.algbeans.entity.AlgBeanClass;
import io.kpatel.algbeans.entity.TypeConstructor;
import io.kpatel.algbeans.entity.TypeConstructorParameter;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Consider Spiting into multiple Visitors
// TODO: Split Type Parameters into TP Declarations and TP Instances
// TODO: Add Annotation Support
// TODO: Add final and transient Support
// TODO: Add Java Poet Plugins Support (to mimic deriving from Haskell)
public class AlgBeansFactoryListener extends AlgBeansBaseListener {
    private List<AlgBeanClass> beans;
    private AlgBeanClass currentBean;
    private TypeConstructor currentConstructor;
    private String packageName;
    private Map<String, String> importMap;

    public AlgBeansFactoryListener() {
        beans = new ArrayList<>();
        currentBean = new AlgBeanClass();
        currentConstructor = new TypeConstructor();
        packageName = "";
        importMap = new HashMap<>();
    }

    @Override
    public void enterPackageLine(AlgBeansParser.PackageLineContext ctx) {
        packageName = ctx.packageName().getText();
    }

    @Override
    public void enterImportLine(AlgBeansParser.ImportLineContext ctx) {
        String className = ctx.packageName().JAVA_IDENTIFIER().getText();
        String qualifiedName = ctx.packageName().getText();
        importMap.put(className, qualifiedName);
    }

    @Override
    public void enterTypeLine(AlgBeansParser.TypeLineContext ctx) {
        String className = ctx.type().JAVA_IDENTIFIER().getText();
        currentBean.setClassName(className);
        currentBean.setPackageName(packageName);
        if (ctx.type().typeParams() != null) {
            for (TerminalNode typeParam : ctx.type().typeParams().JAVA_IDENTIFIER()) {
                currentBean.addTypeParameters(typeParam.getText());
            }
        }
    }

    @Override
    public void exitTypeLine(AlgBeansParser.TypeLineContext ctx) {
        beans.add(currentBean);
        currentBean = new AlgBeanClass();
    }

    @Override
    public void enterConstructor(AlgBeansParser.ConstructorContext ctx) {
        String constructorName = ctx.JAVA_IDENTIFIER().getText();
        currentConstructor.setConstructorName(constructorName);
    }

    @Override
    public void exitConstructor(AlgBeansParser.ConstructorContext ctx) {
        currentBean.addTypeConstructor(currentConstructor);
        currentConstructor = new TypeConstructor();
    }

    @Override
    public void enterParameter(AlgBeansParser.ParameterContext ctx) {
        TypeConstructorParameter parameter = new TypeConstructorParameter();
        String className = ctx.type().JAVA_IDENTIFIER().getText();
        parameter.setClassName(className);
        if (importMap.containsKey(className)) {
            currentBean.addImportPackage(importMap.get(className));
        }
        if (ctx.type().typeParams() != null) {
            for (TerminalNode typeParam : ctx.type().typeParams().JAVA_IDENTIFIER()) {
                parameter.addTypeParameter(typeParam.getText());
            }
        }
        String parameterName = ctx.JAVA_IDENTIFIER().getText();
        parameter.setParameterName(parameterName);
        currentConstructor.addParameter(parameter);
    }

    public List<AlgBeanClass> getBeans() {
        return beans;
    }
}

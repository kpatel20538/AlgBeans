package io.kpatel.algbeans.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgBeanClass {
    private String packageName;
    private ArrayList<String> importPackages;
    private String className;
    private ArrayList<String> typeParameters;
    private ArrayList<TypeConstructor> typeConstructors;

    public AlgBeanClass() {
        packageName = "";
        importPackages = new ArrayList<>();
        className = "";
        typeParameters = new ArrayList<>();
        typeConstructors = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public AlgBeanClass setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public List<String> getImportPackages() {
        return Collections.unmodifiableList(importPackages);
    }

    public AlgBeanClass addImportPackage(String importPackage) {
        this.importPackages.add(importPackage);
        return this;
    }

    public String getClassName() {
        return className;
    }

    public AlgBeanClass setClassName(String className) {
        this.className = className;
        return this;
    }

    public List<String> getTypeParameters() {
        return Collections.unmodifiableList(typeParameters);
    }

    public AlgBeanClass addTypeParameters(String typeParameter) {
        this.typeParameters.add(typeParameter);
        return this;
    }

    public List<TypeConstructor> getTypeConstructors() {
        return Collections.unmodifiableList(typeConstructors);
    }

    public AlgBeanClass addTypeConstructor(TypeConstructor typeConstructor) {
        this.typeConstructors.add(typeConstructor);
        return this;
    }
}

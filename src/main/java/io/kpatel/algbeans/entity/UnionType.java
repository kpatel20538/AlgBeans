package io.kpatel.algbeans.entity;

import io.kpatel.algbeans.java.JavaImport;
import io.kpatel.algbeans.java.type.JavaTypeParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *  POJO that represents a Sum type, which is abstract construct that can be one of a predefined Product Type from a closed list.
 *
 */
public class UnionType {
    private String packageName;
    private List<JavaImport> imports;
    private String typeName;
    private List<JavaTypeParameter> typeParameters;
    private List<ProductType> productTypes;

    public UnionType(
            String packageName,
            Collection<? extends JavaImport> imports,
            String typeName,
            Collection<? extends JavaTypeParameter> typeParameters,
            Collection<? extends ProductType> productTypes) {
        this.packageName = packageName;
        this.imports = new ArrayList<>(imports);
        this.typeName = typeName;
        this.typeParameters = new ArrayList<>(typeParameters);
        this.productTypes = new ArrayList<>(productTypes);
    }

    public UnionType() {
        this.packageName = "";
        this.imports = new ArrayList<>();
        this.typeName = "";
        this.typeParameters = new ArrayList<>();
        this.productTypes = new ArrayList<>();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<JavaTypeParameter> getTypeParameters() {
        return Collections.unmodifiableList(typeParameters);
    }

    public void addTypeParameter(JavaTypeParameter typeParameter) {
        typeParameters.add(typeParameter);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<JavaImport> getImports() {
        return Collections.unmodifiableList(imports);
    }

    public void addImport(JavaImport importLine) {
        imports.add(importLine);
    }

    public List<ProductType> getProductTypes() {
        return Collections.unmodifiableList(productTypes);
    }

    public void addProductType(ProductType productType) {
        productTypes.add(productType);
    }
}

package io.kpatel.algbeans.entity;

import io.kpatel.algbeans.entity.java.JavaImport;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.JavaTypeParameter;

import java.util.*;

/**
 *  POJO that represents a Sum type, which is abstract construct that can be one of a predefined Product Type from a closed list.
 *
 */
public class UnionType {
    private String packageName;
    private List<JavaImport> imports;
    private JavaIdentifier typeName;
    private List<JavaTypeParameter> typeParameters;
    private List<ProductType> productTypes;

    public UnionType(JavaIdentifier typeName) {
        this.packageName = "";
        this.imports = new ArrayList<>();
        this.typeName = Objects.requireNonNull(typeName);
        this.typeParameters = new ArrayList<>();
        this.productTypes = new ArrayList<>();
    }

    public UnionType(
            String packageName,
            Collection<? extends JavaImport> imports,
            JavaIdentifier typeName,
            Collection<? extends JavaTypeParameter> typeParameters,
            Collection<? extends ProductType> productTypes) {
        this.packageName = Objects.requireNonNull(packageName);
        this.imports = new ArrayList<>(Objects.requireNonNull(imports));
        this.typeName = Objects.requireNonNull(typeName);
        this.typeParameters = new ArrayList<>(Objects.requireNonNull(typeParameters));
        this.productTypes = new ArrayList<>(Objects.requireNonNull(productTypes));
    }

    public JavaIdentifier getTypeName() {
        return typeName;
    }

    public void setTypeName(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
    }

    public List<JavaTypeParameter> getTypeParameters() {
        return Collections.unmodifiableList(typeParameters);
    }

    public void addTypeParameter(JavaTypeParameter typeParameter) {
        typeParameters.add(Objects.requireNonNull(typeParameter));
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = Objects.requireNonNull(packageName);
    }

    public List<JavaImport> getImports() {
        return Collections.unmodifiableList(imports);
    }

    public void addImport(JavaImport importLine) {
        imports.add(Objects.requireNonNull(importLine));
    }

    public List<ProductType> getProductTypes() {
        return Collections.unmodifiableList(productTypes);
    }

    public void addProductType(ProductType productType) {
        productTypes.add(Objects.requireNonNull(productType));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnionType unionType = (UnionType) o;
        return getPackageName().equals(unionType.getPackageName()) &&
                getImports().equals(unionType.getImports()) &&
                getTypeName().equals(unionType.getTypeName()) &&
                getTypeParameters().equals(unionType.getTypeParameters()) &&
                getProductTypes().equals(unionType.getProductTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPackageName(), getImports(), getTypeName(), getTypeParameters(), getProductTypes());
    }
}

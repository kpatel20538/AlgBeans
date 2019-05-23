package io.kpatel.algbeans.entity;

import io.kpatel.algbeans.entity.java.JavaImport;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.JavaPackage;
import io.kpatel.algbeans.entity.java.type.JavaAnnotation;
import io.kpatel.algbeans.entity.java.type.JavaTypeParameter;

import java.util.*;

/**
 *  POJO that represents a Sum type, which is abstract construct that can be one of a predefined Product Type from a closed list.
 */
public class UnionType {
    private JavaPackage packageLine;
    private List<JavaImport> importLines;
    private List<JavaAnnotation> annotations;
    private JavaIdentifier typeName;
    private List<JavaTypeParameter> typeParameters;
    private List<ProductType> productTypes;

    public UnionType(JavaIdentifier typeName) {
        this.packageLine = new JavaPackage();
        this.importLines = new ArrayList<>();
        this.annotations = new ArrayList<>();
        this.typeName = Objects.requireNonNull(typeName);
        this.typeParameters = new ArrayList<>();
        this.productTypes = new ArrayList<>();
    }

    public UnionType(
            JavaPackage packageLine,
            Collection<? extends JavaImport> importLines,
            Collection<? extends JavaAnnotation> annotations,
            JavaIdentifier typeName,
            Collection<? extends JavaTypeParameter> typeParameters,
            Collection<? extends ProductType> productTypes) {
        this.packageLine = Objects.requireNonNull(packageLine);
        this.importLines = new ArrayList<>(Objects.requireNonNull(importLines));
        this.annotations = new ArrayList<>(Objects.requireNonNull(annotations));
        this.typeName = Objects.requireNonNull(typeName);
        this.typeParameters = new ArrayList<>(Objects.requireNonNull(typeParameters));
        this.productTypes = new ArrayList<>(Objects.requireNonNull(productTypes));
    }

    public List<JavaAnnotation> getAnnotations() {
        return Collections.unmodifiableList(annotations);
    }

    public void addAnnotation(JavaAnnotation annotation) {
        annotations.add(Objects.requireNonNull(annotation));
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

    public JavaPackage getPackageLine() {
        return packageLine;
    }

    public void setPackageLine(JavaPackage packageLine) {
        this.packageLine = Objects.requireNonNull(packageLine);
    }

    public List<JavaImport> getImportLines() {
        return Collections.unmodifiableList(importLines);
    }

    public void addImport(JavaImport importLine) {
        importLines.add(Objects.requireNonNull(importLine));
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
        return getPackageLine().equals(unionType.getPackageLine()) &&
                getImportLines().equals(unionType.getImportLines()) &&
                getTypeName().equals(unionType.getTypeName()) &&
                getTypeParameters().equals(unionType.getTypeParameters()) &&
                getProductTypes().equals(unionType.getProductTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPackageLine(), getImportLines(), getTypeName(), getTypeParameters(), getProductTypes());
    }


}

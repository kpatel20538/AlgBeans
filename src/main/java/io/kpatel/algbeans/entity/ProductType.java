package io.kpatel.algbeans.entity;

import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;

import java.util.*;

/**
 *  POJO that represents a Product type, which is Pseudo-POJO that represent one possible case of a parent Union type.
 *
 */
public class ProductType {
    private JavaIdentifier typeName;
    private List<JavaField> fields;

    public ProductType(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
        this.fields = new ArrayList<>();
    }

    public ProductType(JavaIdentifier typeName, Collection<? extends JavaField> fields) {
        this.typeName = Objects.requireNonNull(typeName);
        this.fields = new ArrayList<>(Objects.requireNonNull(fields));
    }



    public JavaIdentifier getTypeName() {
        return typeName;
    }

    public void setTypeName(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
    }

    public List<JavaField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void addField(JavaField field) {
        fields.add(Objects.requireNonNull(field));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return getTypeName().equals(that.getTypeName()) &&
                getFields().equals(that.getFields());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeName(), getFields());
    }
}

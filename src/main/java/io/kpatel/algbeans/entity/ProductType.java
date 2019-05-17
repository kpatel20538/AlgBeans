package io.kpatel.algbeans.entity;

import io.kpatel.algbeans.java.element.JavaField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductType {
    private String typeName;
    private List<JavaField> fields;

    public ProductType(String typeName, Collection<? extends JavaField> fields) {
        this.typeName = typeName;
        this.fields = new ArrayList<>(fields);
    }

    public ProductType() {
        this.typeName = "";
        this.fields = new ArrayList<>();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<JavaField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void addField(JavaField field) {
        fields.add(field);
    }
}

package io.kpatel.algbeans.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeConstructorParameter {
    private String className;
    private ArrayList<String> typeParameters;
    private String identifier;

    public TypeConstructorParameter() {
        className = "";
        typeParameters = new ArrayList<>();
        identifier = "";
    }

    public String getClassName() {
        return className;
    }

    public TypeConstructorParameter setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypeConstructorParameter setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public List<String> getTypeParameters() {
        return Collections.unmodifiableList(typeParameters);
    }

    public TypeConstructorParameter setTypeParameters(String typeParameter) {
        this.typeParameters.add(typeParameter);
        return this;
    }
}

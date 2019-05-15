package io.kpatel.algbeans.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeConstructorParameter {
    private String className;
    private ArrayList<String> typeParameters;
    private String parameterName;

    public TypeConstructorParameter() {
        className = "";
        typeParameters = new ArrayList<>();
        parameterName = "";
    }

    public String getClassName() {
        return className;
    }

    public TypeConstructorParameter setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public TypeConstructorParameter setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public List<String> getTypeParameters() {
        return Collections.unmodifiableList(typeParameters);
    }

    public TypeConstructorParameter addTypeParameter(String typeParameter) {
        this.typeParameters.add(typeParameter);
        return this;
    }
}

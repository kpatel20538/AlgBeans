package io.kpatel.algbeans.entity;

import java.util.ArrayList;
import java.util.List;

public class TypeConstructor {
    private String constructorName;
    private ArrayList<TypeConstructorParameter> parameters;

    public TypeConstructor(){
        constructorName = "";
        parameters = new ArrayList<>();
    }

    public String getConstructorName() {
        return constructorName;
    }

    public TypeConstructor setConstructorName(String constructorName) {
        this.constructorName = constructorName;
        return this;
    }

    public List<TypeConstructorParameter> getParameters() {
        return parameters;
    }

    public TypeConstructor addParameter(TypeConstructorParameter parameter) {
        this.parameters.add(parameter);
        return this;
    }
}

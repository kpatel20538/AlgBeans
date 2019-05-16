package io.kpatel.algbeans.java.element;


import io.kpatel.algbeans.java.type.JavaType;

public class JavaParameter implements JavaVariable {
    private JavaType type;
    private String name;

    public JavaParameter(JavaType type, String name) {
        this.type = type;
        this.name = name;
    }

    public JavaParameter(JavaVariable javaVariable) {
        this.type = javaVariable.getType();
        this.name = javaVariable.getName();
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getType(), getName());
    }
}

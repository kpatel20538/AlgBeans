package io.kpatel.algbeans.java;


import io.kpatel.algbeans.java.type.JavaType;

/**
 *  POJO representing a field, a pairing of type and name
 */
public class JavaField {
    private JavaType type;
    private String name;

    public JavaField(JavaType type, String name) {
        this.type = type;
        this.name = name;
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
}

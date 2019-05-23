package io.kpatel.algbeans.entity.java;


import io.kpatel.algbeans.entity.java.type.JavaType;

import java.util.Objects;

/**
 *  POJO representing a field, a pairing of type and name
 */
public class JavaField {
    private JavaType type;
    private JavaIdentifier name;

    public JavaField(JavaType type, JavaIdentifier name) {
        this.type = Objects.requireNonNull(type);
        this.name = Objects.requireNonNull(name);
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = Objects.requireNonNull(type);
    }

    public JavaIdentifier getName() {
        return name;
    }

    public void setName(JavaIdentifier name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", getType(), getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaField javaField = (JavaField) o;
        return getType().equals(javaField.getType()) &&
                getName().equals(javaField.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName());
    }
}

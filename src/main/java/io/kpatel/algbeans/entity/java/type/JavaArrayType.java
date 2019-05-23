package io.kpatel.algbeans.entity.java.type;

import java.util.Objects;

/**
 *  A Pseudo-POJO representing an Array Type.
 *  It wraps a pre-existing types
 */
public class JavaArrayType implements JavaType {
    private JavaType type;

    public JavaArrayType(JavaType type) {
        this.type = Objects.requireNonNull(type);
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = Objects.requireNonNull(type);
    }


    @Override
    public String toString() {
        return String.format("%s[]", getType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaArrayType that = (JavaArrayType) o;
        return getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public String getDefaultValue() {
        return "null";
    }

    @Override
    public Kind getKind() {
        return Kind.ARRAY;
    }
}

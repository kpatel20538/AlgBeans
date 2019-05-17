package io.kpatel.algbeans.java.type;

/**
 *  A Pseudo-POJO representing an Array Type.
 *  It wraps a pre-existing types
 *
 */
public class JavaArrayType implements JavaType {
    private JavaType type;

    public JavaArrayType(JavaType type) {
        this.type = type;
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return String.format("%s[]", getType());
    }
}

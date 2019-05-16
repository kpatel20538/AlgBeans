package io.kpatel.algbeans.java.type;

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
    public boolean isReferenceType() {
        return getType().isReferenceType();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[]", getType());
    }
}

package io.kpatel.algbeans.java.type;

public interface JavaType{
    boolean isReferenceType();
    default boolean isPrimitiveType() { return !isReferenceType(); }
    default boolean isArray() { return false; }
}

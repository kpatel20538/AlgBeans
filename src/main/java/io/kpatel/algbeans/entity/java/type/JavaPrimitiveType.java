package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.entity.java.JavaIdentifier;

/**
 *  A Pseudo-POJOs representing each Primitive Type.
 */
public enum JavaPrimitiveType implements JavaType {
    FLOAT("float", "0.0f"),
    DOUBLE("double", "0.0d"),
    BYTE("byte", "0"),
    SHORT("short", "0"),
    INT("int", "0"),
    LONG("long", "0L"),
    CHAR("char", "'\\u0000'"),
    BOOLEAN("boolean","false");

    private JavaIdentifier typeName;
    private String defaultValue;
    JavaPrimitiveType(String typeName, String defaultValue) {
        this.typeName = new JavaIdentifier(typeName);
        this.defaultValue = defaultValue;
    }

    public JavaIdentifier getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return getTypeName().getId();
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Kind getKind() {
        return Kind.PRIMITIVE;
    }
}

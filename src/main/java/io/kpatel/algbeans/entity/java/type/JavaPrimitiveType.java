package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.entity.java.JavaIdentifier;

/**
 *  A Pseudo-POJOs representing each Primitive Type.
 *
 */
public enum JavaPrimitiveType implements JavaType {
    FLOAT("float"),
    DOUBLE("double"),
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    CHAR("char"),
    BOOLEAN("boolean");

    private JavaIdentifier typeName;
    JavaPrimitiveType(String typeName) {
        this.typeName = new JavaIdentifier(typeName);
    }

    public JavaIdentifier getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return getTypeName().getId();
    }


}

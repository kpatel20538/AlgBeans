package io.kpatel.algbeans.java.type;

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

    private String typeName;
    JavaPrimitiveType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return getTypeName();
    }

}

package io.kpatel.algbeans.java.type;

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

    @Override
    public boolean isReferenceType() {
        return false;
    }
}

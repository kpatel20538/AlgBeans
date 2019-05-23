package io.kpatel.algbeans.entity.java;


import io.kpatel.algbeans.entity.java.type.JavaType;

import java.util.Objects;

/**
 *  POJO representing a field, a pairing of type and name
 */
public class JavaField {
    private JavaType type;
    private JavaIdentifier name;
    private int modifiers;

    private static int FINAL = 1;
    private static int TRANSIENT = 2;
    private static int VOLATILE = 4;
    private static int SYNCHRONIZED = 8;

    public JavaField(JavaType type, JavaIdentifier name) {
        this.type = Objects.requireNonNull(type);
        this.name = Objects.requireNonNull(name);
        this.modifiers = 0;
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

    public boolean isFinal() {
        return (modifiers & FINAL) != 0;
    }

    public void enableFinal() {
        modifiers |= FINAL;
    }

    public void disableFinal() {
        modifiers &= ~FINAL;
    }

    public void setFinal(boolean isFinal) {
        if (isFinal) { enableFinal(); } else { disableFinal(); }
    }


    public boolean isTransient() {
        return (modifiers & TRANSIENT) != 0;
    }

    public void enableTransient() {
        modifiers |= TRANSIENT;
    }

    public void disableTransient() {
        modifiers &= ~TRANSIENT;
    }

    public void setTransient(boolean isTransient) {
        if (isTransient) { enableTransient(); } else { disableTransient(); }
    }


    public boolean isVolatile() {
        return (modifiers & VOLATILE) != 0;
    }

    public void enableVolatile() {
        modifiers |= VOLATILE;
    }

    public void disableVolatile() {
        modifiers &= ~VOLATILE;
    }

    public void setVolatile(boolean isVolatile) {
        if (isVolatile) { enableVolatile(); } else { disableVolatile(); }
    }


    public boolean isSynchronized() {
        return (modifiers & SYNCHRONIZED) != 0;
    }

    public void enableSynchronized() {
        modifiers |= SYNCHRONIZED;
    }

    public void disableSynchronized() {
        modifiers &= ~SYNCHRONIZED;
    }

    public void setSynchronized(boolean isSynchronized) {
        if (isSynchronized) { enableSynchronized(); } else { disableSynchronized(); }
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
        return modifiers == javaField.modifiers &&
                getType().equals(javaField.getType()) &&
                getName().equals(javaField.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName(), modifiers);
    }
}

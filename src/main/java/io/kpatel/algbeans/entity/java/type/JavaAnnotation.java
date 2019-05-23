package io.kpatel.algbeans.entity.java.type;

import java.util.Objects;

/**
 * POJO representing a Java Annotation
 */
public class JavaAnnotation {
    private JavaType type;
    private String elements;

    public JavaAnnotation(JavaType type) {
        this.type = Objects.requireNonNull(type);
        this.elements = "";
    }

    public JavaAnnotation(JavaType type, String elements) {
        this.type = Objects.requireNonNull(type);
        this.elements = Objects.requireNonNull(elements);
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = Objects.requireNonNull(type);
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = Objects.requireNonNull(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaAnnotation that = (JavaAnnotation) o;
        return getType().equals(that.getType()) &&
                getElements().equals(that.getElements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getElements());
    }
}

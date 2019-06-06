package io.kpatel.algbeans.entity.java.type;

import java.util.Objects;

/**
 * POJO representing a Java Annotation
 */
public class JavaAnnotation {
    private String content;

    public JavaAnnotation(String content) {
        this.content = content;
    }

    public JavaAnnotation() {
        this.content = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaAnnotation that = (JavaAnnotation) o;
        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }


}

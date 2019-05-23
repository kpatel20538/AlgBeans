package io.kpatel.algbeans.entity.java;

import java.util.Objects;

/**
 *  POJO representing a package import which may be static or not, or have an on-demand pattern or not
 */
public class JavaImport {
    private String pattern;
    private boolean isStatic;

    public JavaImport(String pattern) {
        this.pattern = Objects.requireNonNull(pattern);
        this.isStatic = false;
    }

    public JavaImport(String pattern, boolean isStatic) {
        this.pattern = Objects.requireNonNull(pattern);
        this.isStatic = isStatic;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = Objects.requireNonNull(pattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaImport that = (JavaImport) o;
        return isStatic() == that.isStatic() &&
                getPattern().equals(that.getPattern());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPattern(), isStatic());
    }
}

package io.kpatel.algbeans.java.element;

public class JavaImport {
    private String pattern;
    private boolean isStatic;

    public JavaImport(String pattern) {
        this.pattern = pattern;
        this.isStatic = false;
    }

    public JavaImport(String pattern, boolean isStatic) {
        this.pattern = pattern;
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
        this.pattern = pattern;
    }
}

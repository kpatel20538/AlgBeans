package io.kpatel.algbeans.java.type;

public class JavaTypeArgument {
    public enum Bound { SPECIFIC, UNBOUNDED, EXTENDS, SUPER }

    private Bound bound;
    private JavaReferenceType type;

    public JavaTypeArgument() {
        this.bound = Bound.UNBOUNDED;
        this.type = null;
    }

    public JavaTypeArgument(Bound bound, JavaReferenceType type) {
        this.bound = bound;
        this.type = type;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public JavaReferenceType getType() {
        return type;
    }

    public void setType(JavaReferenceType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (getBound()) {
            case SPECIFIC:
                return getType().toString();
            case UNBOUNDED:
                return "?";
            case EXTENDS:
                return String.format("? extends %s", getType());
            case SUPER:
                return String.format("? super %s", getType());
            default:
                throw new IllegalStateException("Unexpected value: " + getBound());
        }
    }
}

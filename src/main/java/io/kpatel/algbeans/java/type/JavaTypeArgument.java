package io.kpatel.algbeans.java.type;

/**
 *  A Pseudo-POJO representing an Type argument, which a pairing of a bound and a reference type.
 *
 *  This object is valid only in two cases
 *    - bound == UNBOUNDED
 *    - type != null && bound != UNBOUNDED
 *
 *  This is because only the unbounded wildcard has no dependency on reference types.
 *
 */
public class JavaTypeArgument {
    public enum Bound {SPECIFICALLY, UNBOUNDED, EXTENDS, SUPER }

    private Bound bound;
    private JavaReferenceType type;

    /** Creates an Unbounded Wildcard */
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
            case SPECIFICALLY:
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

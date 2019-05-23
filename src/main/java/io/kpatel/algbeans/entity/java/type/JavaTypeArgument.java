package io.kpatel.algbeans.entity.java.type;

import java.util.Objects;

/**
 *  A Pseudo-POJO representing an Type argument, which a pairing of a bound and a reference type.
 *
 *  This object is valid only in two cases
 *    - bound == UNBOUNDED
 *    - type != null && bound != UNBOUNDED
 *
 *  This is because only the unbounded wildcard has no dependency on reference types.
 */
public class JavaTypeArgument {
    public enum Bound {SPECIFICALLY, UNBOUNDED, EXTENDS, SUPER }

    private Bound bound;
    private JavaReferenceType type;

    /** Creates an Unbounded Wildcard */
    public JavaTypeArgument(Bound bound) {
        this.bound = Objects.requireNonNull(bound);
        this.type = null;
    }

    public JavaTypeArgument(Bound bound, JavaReferenceType type) {
        this.bound = Objects.requireNonNull(bound);
        this.type = type;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = Objects.requireNonNull(bound);
    }

    public JavaReferenceType getType() {
        return type;
    }

    public void setType(JavaReferenceType type) {
        this.type = type;
    }

    public void removeType() {
        this.type = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaTypeArgument that = (JavaTypeArgument) o;
        return getBound() == that.getBound() &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBound(), getType());
    }
}

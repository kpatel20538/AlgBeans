package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.JavaUtil;

import java.util.*;

/**
 *  A Pseudo-POJO representing an Type Parameter, which a pairing of a list of bounds and parameter name.
 *
 *  Bounds list may be empty, in that case, the parameter is unbounded.
 *
 */
public class JavaTypeParameter {
    private JavaIdentifier typeName;
    private List<JavaReferenceType> bounds;

    public JavaTypeParameter(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
        this.bounds = new ArrayList<>();
    }

    public JavaTypeParameter(JavaIdentifier typeName, Collection<? extends JavaReferenceType> bounds) {
        this.typeName = Objects.requireNonNull(typeName);
        this.bounds = new ArrayList<>(Objects.requireNonNull(bounds));
    }

    public JavaIdentifier getTypeName() {
        return typeName;
    }

    public void setTypeName(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
    }

    public List<JavaReferenceType> getBounds() {
        return Collections.unmodifiableList(bounds);
    }

    public void addBound(JavaReferenceType bound) {
        bounds.add(Objects.requireNonNull(bound));
    }

    @Override
    public String toString() {
        if(bounds.isEmpty()) {
            return getTypeName().getId();
        } else {
            return String.format("%s extends %s", getTypeName(), new JavaUtil().toDelimitedList(getBounds(), " & "));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaTypeParameter that = (JavaTypeParameter) o;
        return getTypeName().equals(that.getTypeName()) &&
                getBounds().equals(that.getBounds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeName(), getBounds());
    }
}

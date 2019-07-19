package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.entity.java.JavaIdentifier;

import java.util.*;
import java.util.stream.Collectors;

import io.kpatel.algbeans.util.StreamUtils;

/**
 *  A Pseudo-POJO representing an Type Parameter, which a pairing of a list of bounds and parameter name.
 *
 *  Bounds list may be empty, in that case, the parameter is unbounded.
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

    public JavaTypeArgument toTypeArgument() {
        JavaReferenceType referenceType = new JavaReferenceType();
        JavaTypeDecl typeDecl = new JavaTypeDecl(getTypeName());
        referenceType.addTypeDecls(typeDecl);

        return new JavaTypeArgument(JavaTypeArgument.Bound.SPECIFICALLY, referenceType);
    }

    @Override
    public String toString() {
        if(getBounds().isEmpty()) {
            return getTypeName().getId();
        } else {
            String codeTypeBounds = StreamUtils.strings(getBounds())
                    .collect(Collectors.joining(" & "));
            return String.format("%s extends %s", getTypeName(), codeTypeBounds);
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

package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  A Pseudo-POJO representing an Type Declaration which can been one of the following
 *
 *    - package (no typeArguments)
 *    - a class (optional typeArguments)
 *    - an interface (optional typeArguments)
 *    - an inner class (optional typeArguments)
 *    - an inner interface (optional typeArguments)
 *
 * @see JavaReferenceType
 *
 *  JavaTypeDecl.toString() is implemented for the benefit JavaReferenceType.toString() in VelocityHelper.toDelimitedList
 */
public class JavaTypeDecl {
    private JavaIdentifier typeName;
    private List<JavaTypeArgument> typeArguments;

    public JavaTypeDecl(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
        this.typeArguments = new ArrayList<>();
    }

    public JavaTypeDecl(JavaIdentifier typeName, Collection<? extends JavaTypeArgument> typeArguments) {
        this.typeName = Objects.requireNonNull(typeName);
        this.typeArguments = new ArrayList<>(Objects.requireNonNull(typeArguments));
    }

    public JavaIdentifier getTypeName() {
        return typeName;
    }

    public void setTypeName(JavaIdentifier typeName) {
        this.typeName = Objects.requireNonNull(typeName);
    }

    public List<JavaTypeArgument> getTypeArguments() {
        return Collections.unmodifiableList(typeArguments);
    }

    public void addTypeArgument(JavaTypeArgument typeArgument) {
        typeArguments.add(Objects.requireNonNull(typeArgument));
    }

    @Override
    public String toString() {
        if (getTypeArguments().isEmpty()) {
            return getTypeName().getId();
        } else {
            String codeTypeArgs = StreamUtils.strings(getTypeArguments())
                    .collect(Collectors.joining(", "));
            return String.format("%s<%s>", getTypeName(), codeTypeArgs);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaTypeDecl that = (JavaTypeDecl) o;
        return getTypeName().equals(that.getTypeName()) &&
                getTypeArguments().equals(that.getTypeArguments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeName(), getTypeArguments());
    }
}

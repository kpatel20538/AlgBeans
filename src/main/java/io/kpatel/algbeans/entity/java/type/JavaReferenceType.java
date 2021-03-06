package io.kpatel.algbeans.entity.java.type;

import io.kpatel.algbeans.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 *  A Pseudo-POJOs representing each Reference Type.
 *
 *  Reference Type consists of a sequence of Type-Declarations
 *  @see JavaTypeDecl
 */
public class JavaReferenceType implements JavaType{
    private List<JavaTypeDecl> typeDecls;

    public JavaReferenceType() {
        this.typeDecls = new ArrayList<>();
    }

    public JavaReferenceType(Collection<? extends JavaTypeDecl> typeDecls) {
        this.typeDecls = new ArrayList<>(Objects.requireNonNull(typeDecls));
    }

    public List<JavaTypeDecl> getTypeDecls() {
        return Collections.unmodifiableList(typeDecls);
    }

    public void addTypeDecls(JavaTypeDecl typeDecl) {
        typeDecls.add(typeDecl);
    }

    @Override
    public String toString() {
        return StreamUtils.strings(getTypeDecls()).collect(Collectors.joining("."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaReferenceType that = (JavaReferenceType) o;
        return getTypeDecls().equals(that.getTypeDecls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeDecls());
    }

    @Override
    public String getDefaultValue() {
        return "null";
    }

    @Override
    public Kind getKind() {
        return Kind.REFERENCE;
    }
}

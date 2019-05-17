package io.kpatel.algbeans.java.type;

import io.kpatel.algbeans.java.JavaUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *  A Pseudo-POJOs representing each Reference Type.
 *
 *  Reference Type consists of a sequence of Type-Declarations
 *  @see JavaTypeDecl
 *
 */
public class JavaReferenceType implements JavaType{
    private List<JavaTypeDecl> typeDecls;

    public JavaReferenceType() {
        this.typeDecls = new ArrayList<>();
    }

    public JavaReferenceType(Collection<? extends JavaTypeDecl> typeDecls) {
        this.typeDecls = new ArrayList<>(typeDecls);
    }

    public List<JavaTypeDecl> getTypeDecls() {
        return Collections.unmodifiableList(typeDecls);
    }

    public void addTypeDecls(JavaTypeDecl typeDecl) {
        typeDecls.add(typeDecl);
    }

    @Override
    public String toString() {
        return new JavaUtil().toDelimitedList(getTypeDecls(), ".");
    }

}

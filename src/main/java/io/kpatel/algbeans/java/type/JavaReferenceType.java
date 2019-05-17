package io.kpatel.algbeans.java.type;

import io.kpatel.algbeans.java.JavaUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @Override
    public boolean isReferenceType() {
        return true;
    }
}

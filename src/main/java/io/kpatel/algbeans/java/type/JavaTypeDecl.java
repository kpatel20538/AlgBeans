package io.kpatel.algbeans.java.type;

import io.kpatel.algbeans.java.JavaUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class JavaTypeDecl {
    private String typeName;
    private List<JavaTypeArgument> typeArguments;

    public JavaTypeDecl() {
        this.typeName = "";
        this.typeArguments = new ArrayList<>();
    }

    public JavaTypeDecl(String typeName, Collection<? extends JavaTypeArgument> typeArguments) {
        this.typeName = typeName;
        this.typeArguments = new ArrayList<>(typeArguments);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<JavaTypeArgument> getTypeArguments() {
        return Collections.unmodifiableList(typeArguments);
    }

    public void addTypeArgument(JavaTypeArgument typeArgument) {
        typeArguments.add(typeArgument);
    }


    @Override
    public String toString() {
        if (getTypeArguments().isEmpty()) {
            return getTypeName();
        } else {
            return String.format("%s<%s>", getTypeName(), new JavaUtil().toDelimitedList(getTypeArguments(), ", "));
        }
    }
}

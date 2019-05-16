package io.kpatel.algbeans.java.type;

import io.kpatel.algbeans.java.JavaUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JavaTypeParameter {
    private String typeName;
    private List<JavaReferenceType> bounds;

    public JavaTypeParameter(String typeName) {
        this.typeName = typeName;
        this.bounds = new ArrayList<>();
    }

    public JavaTypeParameter(String typeName, Collection<? extends JavaReferenceType> bounds) {
        this.typeName = typeName;
        this.bounds = new ArrayList<>(bounds);
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<JavaReferenceType> getBounds() {
        return Collections.unmodifiableList(bounds);
    }

    public void addBound(JavaReferenceType bound) {
        bounds.add(bound);
    }

    @Override
    public String toString() {
        if(bounds.isEmpty()) {
            return getTypeName();
        } else {
            return String.format("%s extends %s", getTypeName(), new JavaUtil().toDelimitedList(getBounds(), " & "));
        }
    }
}
package io.kpatel.algbeans.java;

import io.kpatel.algbeans.java.element.JavaField;
import io.kpatel.algbeans.java.element.JavaVariable;
import io.kpatel.algbeans.java.type.*;

import java.util.ArrayList;
import java.util.List;

public class JavaUtil {
    public String toDelimitedList(List<?> objects, String delimiter) {
        if (objects.isEmpty()) return "";
        StringBuilder delimited = new StringBuilder(objects.get(0).toString());
        for (int i = 1; i < objects.size(); i++) {
            delimited.append(delimiter)
                    .append(objects.get(i));
        }
        return delimited.toString();
    }

    public String toParameterList(List<? extends JavaVariable> variables) {
        return toDelimitedList(variables, ", ");
    }

    public String toTypeParameterCode(List<? extends JavaTypeParameter> typeParameters) {
        return toDelimitedList(typeParameters, ", ");
    }

    public String toTypeArgumentsCode(List<? extends JavaTypeParameter> typeParameters) {
        List<JavaTypeArgument> list = new ArrayList<>();
        for (JavaTypeParameter typeParameter : typeParameters) {
            String typeName = typeParameter.getTypeName();
            JavaTypeDecl typeDecl = new JavaTypeDecl();
            typeDecl.setTypeName(typeName);

            JavaReferenceType referenceType = new JavaReferenceType();
            referenceType.addTypeDecls(typeDecl);

            JavaTypeArgument typeArgument = new JavaTypeArgument();
            typeArgument.setBound(JavaTypeArgument.Bound.SPECIFIC);
            typeArgument.setType(referenceType);

            list.add(typeArgument);
        }
        return toDelimitedList(list, ", ");
    }

    public String toGetterName(JavaField field) {
        String template = field.getType() == JavaPrimitiveType.BOOLEAN
                ? "is%s" : "get%s";

        return String.format(template, capitalize(field.getName()));
    }

    public String toSetterName(JavaField field) {
        return String.format("set%s", capitalize(field.getName()));
    }

    public String capitalize(String word) {
        if (word.length() == 0) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

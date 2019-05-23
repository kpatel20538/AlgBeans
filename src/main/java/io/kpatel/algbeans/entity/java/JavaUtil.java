package io.kpatel.algbeans.entity.java;

import io.kpatel.algbeans.entity.java.type.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  A Stateless Utility Class that aids the template engine and JavaType implementers
 *
 * @see JavaType
 */
public class JavaUtil {
    /** Common code for use in template engine and JavaType implementers */
    public String toDelimitedList(List<?> objects, String delimiter) {
        if (objects.isEmpty()) return "";
        StringBuilder delimited = new StringBuilder(objects.get(0).toString());
        for (int i = 1; i < objects.size(); i++) {
            delimited.append(delimiter)
                    .append(objects.get(i));
        }
        return delimited.toString();
    }

    /** For use in the full-arg constructor of Product Types */
    public String toParameterList(List<? extends JavaField> variables) {
        return toDelimitedList(variables, ", ");
    }

    /** Conversion helper: for use in template engine */
    public String toTypeParameterCode(List<? extends JavaTypeParameter> typeParameters) {
        return toDelimitedList(typeParameters, ", ");
    }

    /** Conversion helper: for use in template engine */
    public String toTypeArgumentsCode(List<? extends JavaTypeParameter> typeParameters) {
        List<JavaTypeArgument> list = new ArrayList<>();
        for (JavaTypeParameter typeParameter : typeParameters) {
            JavaIdentifier typeName = typeParameter.getTypeName();
            JavaTypeDecl typeDecl = new JavaTypeDecl(typeName);

            JavaReferenceType referenceType = new JavaReferenceType();
            referenceType.addTypeDecls(typeDecl);

            JavaTypeArgument typeArgument = new JavaTypeArgument(JavaTypeArgument.Bound.SPECIFICALLY);
            typeArgument.setType(referenceType);

            list.add(typeArgument);
        }
        return toDelimitedList(list, ", ");
    }

    /** For use in naming getter methods for fields in Product Types */
    public String toGetterName(JavaField field) {
        String template = field.getType() == JavaPrimitiveType.BOOLEAN
                ? "is%s" : "get%s";

        return String.format(template, capitalize(field.getName().getId()));
    }

    /** For use in naming setter methods for fields in Product Types */
    public String toSetterName(JavaField field) {
        return String.format("set%s", capitalize(field.getName().getId()));
    }

    /** Common code between the naming of getter and setter methods in Product Types */
    public String capitalize(String word) {
        if (word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

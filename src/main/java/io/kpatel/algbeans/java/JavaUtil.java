package io.kpatel.algbeans.java;

import io.kpatel.algbeans.java.element.JavaField;
import io.kpatel.algbeans.java.element.JavaVariable;
import io.kpatel.algbeans.java.type.JavaTypeArgument;
import io.kpatel.algbeans.java.type.JavaTypeParameter;

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
            JavaTypeArgument javaTypeArgument = new JavaTypeArgument(typeParameter.getTypeName());
            list.add(javaTypeArgument);
        }
        return toDelimitedList(list, ", ");
    }

    public String toGetterName(JavaField field) {
        return String.format("get%s", capitalize(field.getName()));
    }

    public String toSetterName(JavaField field) {
        return String.format("set%s", capitalize(field.getName()));
    }

    public String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }


}

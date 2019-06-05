package io.kpatel.algbeans.util;

import io.kpatel.algbeans.entity.ProductType;
import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Stateless Utility Class that aids the template engine and JavaType implementers
 *
 * @see JavaType
 */
public class JavaUtil {

    /** Common code for use in template engine and JavaType implementers */
    public String toDelimitedList(List<?> objects, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object obj: objects) {
            joiner.add(obj.toString());
        }
        return joiner.toString();
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

    /** Conversion helper: for use in template engine */
    public List<? super JavaField> byKind(List<? extends JavaField> fields, int code) {
        List<? super JavaField> filterFields = new ArrayList<>();
        for (JavaField field : fields) {
            if (field.getType().getKind().getCode() == code) {
                filterFields.add(field);
            }
        }
        return filterFields;
    }

    /** Conversion helper: for use in template engine */
    public List<? super JavaField> byNotKind(List<? extends JavaField> fields, int code) {
        List<? super JavaField> filterFields = new ArrayList<>();
        for (JavaField field : fields) {
            if (field.getType().getKind().getCode() != code) {
                filterFields.add(field);
            }
        }
        return filterFields;
    }


    /** For use in naming getter methods for fields in Product Types */
    public String toGetterName(JavaField field) {
        String fieldName = field.getName().getId();
        if (field.getType() != JavaPrimitiveType.BOOLEAN) {
            return String.format("get%s", capitalize(fieldName));
        } else if (fieldName.startsWith("is") && fieldName.length() > 3 && Character.isUpperCase(fieldName.charAt(2))) {
            return fieldName;
        } else {
            return String.format("is%s", capitalize(fieldName));
        }
    }


    /** For use in naming setter methods for fields in Product Types */
    public String toSetterName(JavaField field) {
        String fieldName = field.getName().getId();
        if (field.getType() == JavaPrimitiveType.BOOLEAN && fieldName.startsWith("is") && fieldName.length() > 3 && Character.isUpperCase(fieldName.charAt(2))) {
            return String.format("set%s", fieldName.substring(2));
        } else {
            return String.format("set%s", capitalize(fieldName));
        }
    }

    /** For use in naming getter methods for fields in Product Types */
    public String toWitherName(JavaField field) {
        String fieldName = field.getName().getId();
        if (field.getType() == JavaPrimitiveType.BOOLEAN && fieldName.startsWith("is") && fieldName.length() > 3 && Character.isUpperCase(fieldName.charAt(2))) {
            return String.format("with%s", fieldName.substring(2));
        } else {
            return String.format("with%s", capitalize(fieldName));
        }
    }

    /** For use in naming getter methods for fields in Product Types */
    public String toWitherParameterList(List<JavaField> fields, JavaField targetField) {
        StringJoiner joiner = new StringJoiner(", ");
        for (JavaField field: fields) {
            if (!field.equals(targetField)) {
                joiner.add(String.format("%s()", toGetterName(field)));
            } else {
                joiner.add(field.getName().getId());
            }
        }
        return joiner.toString();
    }



    /** For use in naming getter methods for function-type fields in SwitchBuilders */
    public String toSwitchGetterName(ProductType product) {
        String typeName = product.getTypeName().getId();
        return String.format("getOn%s", capitalize(typeName));
    }

    /** For use in naming setter methods for function-type fields in SwitchBuilders */
    public String toSwitchSetterName(ProductType product) {
        String typeName = product.getTypeName().getId();
        return String.format("on%s", capitalize(typeName));
    }

    /** Common code between the naming of getter and setter methods in Product Types */
    public String capitalize(String word) {
        if (word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

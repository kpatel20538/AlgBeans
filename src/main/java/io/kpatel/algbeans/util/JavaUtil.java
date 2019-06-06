package io.kpatel.algbeans.util;

import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * A Stateless Utility Class that aids the template engine and JavaType implementers
 *
 * @see JavaType
 */
public class JavaUtil {

    public boolean isBoolean(JavaType type) {
        return type == JavaPrimitiveType.BOOLEAN;
    }

    public boolean startsWithIs(JavaIdentifier identifier) {
        return identifier.getId().matches("^is\\p{Upper}[\\p{Alnum}_]*");
    }

    /** Common code for use in template engine and JavaType implementers */
    public String toDelimitedList(List<?> objects, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object obj: objects) {
            joiner.add(obj.toString());
        }
        return joiner.toString();
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
}

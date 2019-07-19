package io.kpatel.algbeans.util;

import io.kpatel.algbeans.entity.java.JavaField;
import io.kpatel.algbeans.entity.java.JavaIdentifier;
import io.kpatel.algbeans.entity.java.type.JavaPrimitiveType;
import io.kpatel.algbeans.entity.java.type.JavaType;

import java.util.ArrayList;
import java.util.List;

/**
 * A Stateless Utility Class that aids the template engine
 */
public class VelocityHelper {

    /** Template helper: Velocity can not perform enum-check without external help */
    public boolean isBoolean(JavaType type) {
        return type == JavaPrimitiveType.BOOLEAN;
    }

    /** Template helper: Velocity can not store/cache the results of boolean test */
    public boolean startsWithIs(JavaIdentifier identifier) {
        return identifier.getId().matches("^is\\p{Upper}[\\p{Alnum}_]*");
    }

    /** Template helper: Generating the hashcode methods requires one to group fields by type
     *  to make use of Arrays.hashCode and Objects.hash correctly */
    public List<? super JavaField> byKind(List<? extends JavaField> fields, int code) {
        List<? super JavaField> filterFields = new ArrayList<>();
        for (JavaField field : fields) {
            if (field.getType().getKind().getCode() == code) {
                filterFields.add(field);
            }
        }
        return filterFields;
    }

    /** Template helper: Generating the hashcode methods requires one to group fields by type
     *  to make use of Arrays.hashCode and Objects.hash correctly */
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

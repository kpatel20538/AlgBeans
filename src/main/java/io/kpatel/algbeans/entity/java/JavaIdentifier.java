package io.kpatel.algbeans.entity.java;

import java.util.Objects;

/**
 *  A POJO representing an identifier,
 *
 *  It constraints its wrapped String to ensure it is a valid Java identifier
 */
public class JavaIdentifier {
    private String id;

    public JavaIdentifier(String id) {
        this.id = validateName(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = validateName(id);
    }

    private String validateName(String name) {
        String trimmedName = Objects.requireNonNull(name).trim();
        if (trimmedName.isEmpty()){
            throw new IllegalArgumentException("Java Identifier must have non-empty id");
        }
        return trimmedName;
    }

    @Override
    public String toString() {
        return getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaIdentifier that = (JavaIdentifier) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

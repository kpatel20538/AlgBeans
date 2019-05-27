package io.kpatel.algbeans.entity.java;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 *  POJO for java packages
 */
public class JavaPackage {
    private String name;

    public JavaPackage() {
        this.name = "";
    }

    public JavaPackage(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path toPath() {
        Path outPath = Paths.get("");
        for (String packageName : getName().split("\\.")) {
            outPath = outPath.resolve(packageName);
        }
        return outPath;
    }

    public boolean isDefault() {
        return getName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaPackage that = (JavaPackage) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

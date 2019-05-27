package io.kpatel.algbeans.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface ExceptionSupplier<T, E extends Exception> {
    T get() throws E;

    static <T> Supplier<T> toSupplier(ExceptionSupplier<T, ?> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

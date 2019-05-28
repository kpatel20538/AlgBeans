package io.kpatel.algbeans.util;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionFunction<U, V, E extends Exception> {
    V apply(U item) throws E;

    static <U,V> Function<U, V> toFunction(ExceptionFunction<U,V,?> function) {
        return item -> {
            try {
                return function.apply(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}

package io.kpatel.algbeans.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ExceptionConsumer<T, E extends Exception> {
    void accept(T item) throws E;

    static <T> Consumer<T> toConsumer(ExceptionConsumer<T, ?> consumer) {
        return item -> {
            try {
                consumer.accept(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

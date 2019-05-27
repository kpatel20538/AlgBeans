package io.kpatel.algbeans.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface ExceptionPredicate<T, E extends Exception> {
    boolean test(T item) throws E;

    static <T> Predicate<T> toPredicate(ExceptionPredicate<T, ?> predicate) {
        return item -> {
            try {
                return predicate.test(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    default ExceptionPredicate<T, E> and(ExceptionPredicate<T, E> other) {
        return item -> test(item) && other.test(item);
    }

    default ExceptionPredicate<T, E> xor(ExceptionPredicate<T, E> other) {
        return item -> test(item) ^ other.test(item);
    }

    default ExceptionPredicate<T, E> or(ExceptionPredicate<T, E> other) {
        return item -> test(item) || other.test(item);
    }

    default ExceptionPredicate<T, E> negate() {
        return item -> !test(item);
    }

    static <T, E extends Exception> ExceptionPredicate<T, E> alwaysTrue() {
        return path -> true;
    }

    static <T, E extends Exception> ExceptionPredicate<T, E> alwaysFalse() {
        return path -> false;
    }
}

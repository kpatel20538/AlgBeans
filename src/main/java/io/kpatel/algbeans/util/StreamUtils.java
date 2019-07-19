package io.kpatel.algbeans.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
    public static <E> Stream<E> walk(Function<E, Stream<E>> neighbors, Supplier<E> root) {
        Stream.Builder<E> builder = Stream.builder();
        ArrayDeque<E> nodes = new ArrayDeque<>();
        nodes.offerLast(root.get());
        while (!nodes.isEmpty()) {
            E node = nodes.pollLast();
            builder.add(node);
            neighbors.apply(node)
                    .forEach(nodes::offerLast);
        }
        return builder.build();
    }

    public static <E> Stream<E> range(IntFunction<E> get, IntSupplier size) {
        return IntStream.range(0, size.getAsInt())
                .mapToObj(get);
    }

    public static <E> Stream<E> reversedRange(IntFunction<E> get, IntSupplier size) {
        return IntStream.iterate(size.getAsInt() - 1, i -> i - 1)
                .limit(size.getAsInt())
                .mapToObj(get);
    }

    public static <E> Stream<E> iterable(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <E> Stream<String> strings(Collection<E> collection) {
        return collection.stream().map(E::toString);
    }
}

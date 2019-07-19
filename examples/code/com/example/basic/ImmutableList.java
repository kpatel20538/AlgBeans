
package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ImmutableList<T> {
    public final static class Node<T> extends ImmutableList<T> {
        private T item;
        private ImmutableList<T> next;

        public Node() {
            this.item = null;
            this.next = null;
        }

        public Node(T item, ImmutableList<T> next) {
            this.item = item;
            this.next = next;
        }
        public T getItem() {
            return item;
        }

        public ImmutableList<T> getNext() {
            return next;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public void setNext(ImmutableList<T> next) {
            this.next = next;
        }

        public Node<T> withItem(T item) {
            return new Node<>(item, getNext());
        }

        public Node<T> withNext(ImmutableList<T> next) {
            return new Node<>(getItem(), next);
        }

        @Override
        public Node<T> copy() {
            return new Node<>(getItem(), getNext());
        }

        @Override
        public String toString() {
            return "Node(" + "item = " +  getItem() + ", next = " +  getNext() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node that = (Node) obj;
            return Objects.equals(getItem(), that.getItem()) && Objects.equals(getNext(), that.getNext());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem(), getNext());
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }
    public final static class Null<T> extends ImmutableList<T> {

        @Override
        public Null<T> copy() {
            return new Null<>();
        }

        @Override
        public String toString() {
            return "Null()";
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && getClass() == obj.getClass();
        }

        @Override
        public int hashCode() {
            return 0;
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T, T> {
        $T is(Node<T> it);
        $T is(Null<T> it);
    }
    public interface SwitchBuilder<$T, T> {
ImmutableList<T> getValue();
        Function<Node<T>,$T> getOnNode();
        Function<Null<T>,$T> getOnNull();

        default Switch<$T, T> build() {
            Function<Node<T>,$T> onNode = getOnNode();
            Function<Null<T>,$T> onNull = getOnNull();

            return new Switch<$T, T>() {
                public $T is(Node<T> it) {
                    return onNode.apply(it);
                }

                public $T is(Null<T> it) {
                    return onNull.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, T> implements SwitchBuilder<$T, T>{
        private final ImmutableList<T> value;
        private Function<Node<T>,$T> onNode;
        private Function<Null<T>,$T> onNull;

        CaseSwitchBuilder(ImmutableList<T> value) {
            this.value = value;
            this.onNode = null;
            this.onNull = null;
        }

        @Override
        public ImmutableList<T> getValue() {
            return value;
        }

        public Function<Node<T>,$T> getOnNode() {
            if (onNode != null) {
                return onNode;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<Null<T>,$T> getOnNull() {
            if (onNull != null) {
                return onNull;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T, T> onNode(Function<Node<T>,$T> onNode) {
            this.onNode = onNode;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNull(Function<Null<T>,$T> onNull) {
            this.onNull = onNull;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNode(Supplier<$T> onNode) {
            this.onNode = it ->onNode.get();
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNull(Supplier<$T> onNull) {
            this.onNull = it ->onNull.get();
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNode($T onNode) {
            this.onNode= it -> onNode;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNull($T onNull) {
            this.onNull= it -> onNull;
            return this;
        }

        public TerminalSwitchBuilder<$T, T> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
        }

        public TerminalSwitchBuilder<$T, T> orElse($T orElse) {
            return new TerminalSwitchBuilder<>(this, () -> orElse);
        }
    }
    public static final class TerminalSwitchBuilder<$T, T> implements SwitchBuilder<$T, T> {
        private final SwitchBuilder<$T, T> switchBuilder;
        private final Supplier<$T> orElse;

        TerminalSwitchBuilder(SwitchBuilder<$T, T> switchBuilder, Supplier<$T> orElse) {
            if (orElse == null) {
                throw new NullPointerException();
            }
            this.switchBuilder = switchBuilder;
            this.orElse = orElse;
        }

        private <$R> Function<$R, $T> ensureFunction(Function<$R, $T> func) {
            return func != null ? func : it -> orElse.get();
        }

        @Override
        public ImmutableList<T> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<Node<T>, $T> getOnNode() {
            return ensureFunction(switchBuilder.getOnNode());
        }

        @Override
        public Function<Null<T>, $T> getOnNull() {
            return ensureFunction(switchBuilder.getOnNull());
        }

    }
    ImmutableList() { }

    public abstract ImmutableList<T> copy();

    public abstract <$T> $T when(Switch<$T, T> cases);

    public <$T> CaseSwitchBuilder<$T, T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
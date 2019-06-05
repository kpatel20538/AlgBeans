package com.example;

import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

@Deprecated
@SuppressWarnings("unchecked")
public abstract class LinkedList<T> {
    public final static class UNode<T> extends LinkedList<T> {
        private final T item;
        private volatile LinkedList<T> next;

        public UNode() {
            this.item = null;
            this.next = null;
        }

        public UNode(T item, LinkedList<T> next) {
            this.item = item;
            this.next = next;
        }

        public T getItem() {
            return item;
        }

        public synchronized LinkedList<T> getNext() {
            return next;
        }


        public synchronized void setNext(LinkedList<T> next) {
            this.next = next;
        }


        @Override
        public String toString() {
            return "UNode(" + "item = " +  getItem() + ", next = " +  getNext() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            UNode that = (UNode) obj;
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
    public final static class EmptyUNode<T> extends LinkedList<T> {




        @Override
        public String toString() {
            return "EmptyUNode()";
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
        $T is(UNode<T> it);
        $T is(EmptyUNode<T> it);
    }
    public interface SwitchBuilder<$T, T> {
        LinkedList<T> getValue();
        Function<UNode<T>,$T> getOnUNode();
        Function<EmptyUNode<T>,$T> getOnEmptyUNode();

        default Switch<$T, T> build() {
            Function<UNode<T>,$T> onUNode = getOnUNode();
            Function<EmptyUNode<T>,$T> onEmptyUNode = getOnEmptyUNode();
            return new Switch<$T, T>() {
                public $T is(UNode<T> it) {
                    return onUNode.apply(it);
                }
                public $T is(EmptyUNode<T> it) {
                    return onEmptyUNode.apply(it);
                }
            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, T> implements SwitchBuilder<$T, T> {
        private final LinkedList<T> value;
        private Function<UNode<T>,$T> onUNode;
        private Function<EmptyUNode<T>,$T> onEmptyUNode;

        CaseSwitchBuilder(LinkedList<T> value) {
            this.value = value;
            this.onUNode = null;
            this.onEmptyUNode = null;
        }

        @Override
        public LinkedList<T> getValue() {
            return value;
        }

        public Function<UNode<T>,$T> getOnUNode() {
            if (onUNode != null) {
                return onUNode;
            } else {
                throw new NullPointerException();
            }
        };
        public Function<EmptyUNode<T>,$T> getOnEmptyUNode() {
            if (onEmptyUNode != null) {
                return onEmptyUNode;
            } else {
                throw new NullPointerException();
            }
        };

        public CaseSwitchBuilder<$T, T> onUNode(Function<UNode<T>,$T> onUNode) {
            this.onUNode = onUNode;
            return this;
        };
        public CaseSwitchBuilder<$T, T> onEmptyUNode(Function<EmptyUNode<T>,$T> onEmptyUNode) {
            this.onEmptyUNode = onEmptyUNode;
            return this;
        };

        public TerminalSwitchBuilder<$T, T> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
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
        public LinkedList<T> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<UNode<T>, $T> getOnUNode() {
            return ensureFunction(switchBuilder.getOnUNode());
        }

        @Override
        public Function<EmptyUNode<T>, $T> getOnEmptyUNode() {
            return ensureFunction(switchBuilder.getOnEmptyUNode());
        }

    }
    LinkedList() { }

    public abstract <$T> $T when(Switch<$T, T> cases);

    public <$T> CaseSwitchBuilder<$T, T> createSwitch() {
        return new CaseSwitchBuilder<>(this);
    }
}
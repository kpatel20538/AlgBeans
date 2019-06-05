package com.example;

import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BinaryTree<T> {
    public final static class BNode<T> extends BinaryTree<T> {
        private final transient T item;
        private volatile BinaryTree<T> left;
        private volatile BinaryTree<T> right;

        public BNode() {
            this.item = null;
            this.left = null;
            this.right = null;
        }

        public BNode(T item, BinaryTree<T> left, BinaryTree<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public BinaryTree<T> getLeft() {
            return left;
        }

        public BinaryTree<T> getRight() {
            return right;
        }


        public void setLeft(BinaryTree<T> left) {
            this.left = left;
        }

        public void setRight(BinaryTree<T> right) {
            this.right = right;
        }


        @Override
        public String toString() {
            return "BNode(" + "item = " +  getItem() + ", left = " +  getLeft() + ", right = " +  getRight() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            BNode that = (BNode) obj;
            return Objects.equals(getItem(), that.getItem()) && Objects.equals(getLeft(), that.getLeft()) && Objects.equals(getRight(), that.getRight());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem(), getLeft(), getRight());
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }
    public final static class EmptyBNode<T> extends BinaryTree<T> {




        @Override
        public String toString() {
            return "EmptyBNode()";
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
        $T is(BNode<T> it);
        $T is(EmptyBNode<T> it);
    }
    public interface SwitchBuilder<$T, T> {
        BinaryTree<T> getValue();
        Function<BNode<T>,$T> getOnBNode();
        Function<EmptyBNode<T>,$T> getOnEmptyBNode();

        default Switch<$T, T> build() {
            Function<BNode<T>,$T> onBNode = getOnBNode();
            Function<EmptyBNode<T>,$T> onEmptyBNode = getOnEmptyBNode();
            return new Switch<$T, T>() {
                public $T is(BNode<T> it) {
                    return onBNode.apply(it);
                }
                public $T is(EmptyBNode<T> it) {
                    return onEmptyBNode.apply(it);
                }
            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, T> implements SwitchBuilder<$T, T> {
        private final BinaryTree<T> value;
        private Function<BNode<T>,$T> onBNode;
        private Function<EmptyBNode<T>,$T> onEmptyBNode;

        CaseSwitchBuilder(BinaryTree<T> value) {
            this.value = value;
            this.onBNode = null;
            this.onEmptyBNode = null;
        }

        @Override
        public BinaryTree<T> getValue() {
            return value;
        }

        public Function<BNode<T>,$T> getOnBNode() {
            if (onBNode != null) {
                return onBNode;
            } else {
                throw new NullPointerException();
            }
        };
        public Function<EmptyBNode<T>,$T> getOnEmptyBNode() {
            if (onEmptyBNode != null) {
                return onEmptyBNode;
            } else {
                throw new NullPointerException();
            }
        };

        public CaseSwitchBuilder<$T, T> onBNode(Function<BNode<T>,$T> onBNode) {
            this.onBNode = onBNode;
            return this;
        };
        public CaseSwitchBuilder<$T, T> onEmptyBNode(Function<EmptyBNode<T>,$T> onEmptyBNode) {
            this.onEmptyBNode = onEmptyBNode;
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
        public BinaryTree<T> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<BNode<T>, $T> getOnBNode() {
            return ensureFunction(switchBuilder.getOnBNode());
        }

        @Override
        public Function<EmptyBNode<T>, $T> getOnEmptyBNode() {
            return ensureFunction(switchBuilder.getOnEmptyBNode());
        }

    }
    BinaryTree() { }

    public abstract <$T> $T when(Switch<$T, T> cases);

    public <$T> CaseSwitchBuilder<$T, T> createSwitch() {
        return new CaseSwitchBuilder<>(this);
    }
}
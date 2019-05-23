package com.example;


public abstract class BinaryTree<T> {
    public final static class BNode<T> extends BinaryTree<T> {
        private final transient T item;
        private volatile BinaryTree<T> left;
        private volatile BinaryTree<T> right;

        public BNode() { }

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

        public void setLeft(BinaryTree<T> left) {
            this.left = left;
        }

        public BinaryTree<T> getRight() {
            return right;
        }

        public void setRight(BinaryTree<T> right) {
            this.right = right;
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }

    public final static class EmptyBNode<T> extends BinaryTree<T> {

        public EmptyBNode() { }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }

    interface Switch<$T, T> {
        $T is(BNode<T> it);
        $T is(EmptyBNode<T> it);
    }

    BinaryTree() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}
package com.example;

import java.util.Objects;
import java.util.Arrays;


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

        @Override
        public String toString() {
            return "BNode(" +"item = " + getItem() +", left = " + getLeft() +", right = " + getRight() +')';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof BNode)) return false;
            BNode that = (BNode) obj;
            return  Objects.equals(getItem(), that.getItem()) && Objects.equals(getLeft(), that.getLeft()) && Objects.equals(getRight(), that.getRight())
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem(), getLeft(), getRight());
        }

    }

    public final static class EmptyBNode<T> extends BinaryTree<T> {

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

        @Override
        public String toString() {
            return return "EmptyBNode()";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof EmptyBNode)) return false;
            EmptyBNode that = (EmptyBNode) obj;
            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

    }

    interface Switch<$T, T> {
        $T is(BNode<T> it);
        $T is(EmptyBNode<T> it);
    }

    BinaryTree() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}
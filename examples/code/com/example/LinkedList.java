package com.example;

import java.util.Objects;
import java.util.Arrays;


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

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

        @Override
        public String toString() {
            return "UNode(" +"item = " + getItem() +", next = " + getNext() +')';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof UNode)) return false;
            UNode that = (UNode) obj;
            return  Objects.equals(getItem(), that.getItem()) && Objects.equals(getNext(), that.getNext())
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem(), getNext());
        }

    }

    public final static class EmptyUNode<T> extends LinkedList<T> {

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

        @Override
        public String toString() {
            return return "EmptyUNode()";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof EmptyUNode)) return false;
            EmptyUNode that = (EmptyUNode) obj;
            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

    }

    interface Switch<$T, T> {
        $T is(UNode<T> it);
        $T is(EmptyUNode<T> it);
    }

    LinkedList() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}
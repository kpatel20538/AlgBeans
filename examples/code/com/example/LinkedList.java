package com.example;


public abstract class LinkedList<T> {
    public final static class UNode<T> extends LinkedList<T> {
        private final T item;
        private volatile LinkedList<T> next;

        public UNode() { }

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

    }

    public final static class EmptyUNode<T> extends LinkedList<T> {

        public EmptyUNode() { }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }

    interface Switch<$T, T> {
        $T is(UNode<T> it);
        $T is(EmptyUNode<T> it);
    }

    LinkedList() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}
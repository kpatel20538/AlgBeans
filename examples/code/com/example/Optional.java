package com.example;


public abstract class Optional<T> {
    public final static class Exists<T> extends Optional<T> {
        private T item;

        public Exists() { }

        public Exists(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }

    public final static class NoExists<T> extends Optional<T> {

        public NoExists() { }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }

    interface Switch<$T, T> {
        $T is(Exists<T> it);
        $T is(NoExists<T> it);
    }

    Optional() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}
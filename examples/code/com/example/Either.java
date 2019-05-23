package com.example;


public abstract class Either<U, V> {
    public final static class Left<U, V> extends Either<U, V> {
        private U item;

        public Left() { }

        public Left(U item) {
            this.item = item;
        }

        public U getItem() {
            return item;
        }

        public void setItem(U item) {
            this.item = item;
        }

        public <$T> $T when(Switch<$T, U, V> cases) {
            return cases.is(this);
        }

    }

    public final static class Right<U, V> extends Either<U, V> {
        private V item;

        public Right() { }

        public Right(V item) {
            this.item = item;
        }

        public V getItem() {
            return item;
        }

        public void setItem(V item) {
            this.item = item;
        }

        public <$T> $T when(Switch<$T, U, V> cases) {
            return cases.is(this);
        }

    }

    interface Switch<$T, U, V> {
        $T is(Left<U, V> it);
        $T is(Right<U, V> it);
    }

    Either() { }

    public abstract <$T> $T when(Switch<$T, U, V> cases);
}
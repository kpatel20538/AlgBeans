package com.example;

import java.util.Objects;
import java.util.Arrays;


public abstract class Either<U, V> {
    public final static class Left<U, V> extends Either<U, V> {
        private U item;

        public Left() {
            this.item = null;
        }

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

        @Override
        public String toString() {
            return "Left(" +"item = " + getItem() +')';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Left)) return false;
            Left that = (Left) obj;
            return  Objects.equals(getItem(), that.getItem())
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

    }

    public final static class Right<U, V> extends Either<U, V> {
        private V item;

        public Right() {
            this.item = null;
        }

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

        @Override
        public String toString() {
            return "Right(" +"item = " + getItem() +')';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Right)) return false;
            Right that = (Right) obj;
            return  Objects.equals(getItem(), that.getItem())
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

    }

    interface Switch<$T, U, V> {
        $T is(Left<U, V> it);
        $T is(Right<U, V> it);
    }

    Either() { }

    public abstract <$T> $T when(Switch<$T, U, V> cases);
}
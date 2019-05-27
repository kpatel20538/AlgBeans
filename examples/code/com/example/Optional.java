package com.example;

import java.util.Objects;
import java.util.Arrays;


public abstract class Optional<T> {
    public final static class Exists<T> extends Optional<T> {
        private T item;

        public Exists() {
            this.item = null;
        }

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

        @Override
        public String toString() {
            return "Exists(" + "item = " +  getItem() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Exists that = (Exists) obj;
            return  Objects.equals(getItem(), that.getItem());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

    }

    public final static class NoExists<T> extends Optional<T> {

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

        @Override
        public String toString() {
            return "NoExists()";
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && getClass() == obj.getClass();
        }

        @Override
        public int hashCode() {
            return 0;
        }

    }

    public interface Switch<$T, T> {
        $T is(Exists<T> it);
        $T is(NoExists<T> it);
    }

    Optional() { }

    public abstract <$T> $T when(Switch<$T, T> cases);
}

package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

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

        public Exists<T> withItem(T item) {
            return new Exists<>(item);
        }

        @Override
        public Exists<T> copy() {
            return new Exists<>(getItem());
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
            return Objects.equals(getItem(), that.getItem());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }
    public final static class NoExists<T> extends Optional<T> {

        @Override
        public NoExists<T> copy() {
            return new NoExists<>();
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

        public <$T> $T when(Switch<$T, T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T, T> {
        $T is(Exists<T> it);
        $T is(NoExists<T> it);
    }
    public interface SwitchBuilder<$T, T> {
Optional<T> getValue();
        Function<Exists<T>,$T> getOnExists();
        Function<NoExists<T>,$T> getOnNoExists();

        default Switch<$T, T> build() {
            Function<Exists<T>,$T> onExists = getOnExists();
            Function<NoExists<T>,$T> onNoExists = getOnNoExists();

            return new Switch<$T, T>() {
                public $T is(Exists<T> it) {
                    return onExists.apply(it);
                }

                public $T is(NoExists<T> it) {
                    return onNoExists.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, T> implements SwitchBuilder<$T, T>{
        private final Optional<T> value;
        private Function<Exists<T>,$T> onExists;
        private Function<NoExists<T>,$T> onNoExists;

        CaseSwitchBuilder(Optional<T> value) {
            this.value = value;
            this.onExists = null;
            this.onNoExists = null;
        }

        @Override
        public Optional<T> getValue() {
            return value;
        }

        public Function<Exists<T>,$T> getOnExists() {
            if (onExists != null) {
                return onExists;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<NoExists<T>,$T> getOnNoExists() {
            if (onNoExists != null) {
                return onNoExists;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T, T> onExists(Function<Exists<T>,$T> onExists) {
            this.onExists = onExists;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNoExists(Function<NoExists<T>,$T> onNoExists) {
            this.onNoExists = onNoExists;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onExists(Supplier<$T> onExists) {
            this.onExists = it ->onExists.get();
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNoExists(Supplier<$T> onNoExists) {
            this.onNoExists = it ->onNoExists.get();
            return this;
        }

        public CaseSwitchBuilder<$T, T> onExists($T onExists) {
            this.onExists= it -> onExists;
            return this;
        }

        public CaseSwitchBuilder<$T, T> onNoExists($T onNoExists) {
            this.onNoExists= it -> onNoExists;
            return this;
        }

        public TerminalSwitchBuilder<$T, T> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
        }

        public TerminalSwitchBuilder<$T, T> orElse($T orElse) {
            return new TerminalSwitchBuilder<>(this, () -> orElse);
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
        public Optional<T> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<Exists<T>, $T> getOnExists() {
            return ensureFunction(switchBuilder.getOnExists());
        }

        @Override
        public Function<NoExists<T>, $T> getOnNoExists() {
            return ensureFunction(switchBuilder.getOnNoExists());
        }

    }
    Optional() { }

    public abstract Optional<T> copy();

    public abstract <$T> $T when(Switch<$T, T> cases);

    public <$T> CaseSwitchBuilder<$T, T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
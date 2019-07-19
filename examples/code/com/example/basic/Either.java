
package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

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

        public Left<U, V> withItem(U item) {
            return new Left<>(item);
        }

        @Override
        public Left<U, V> copy() {
            return new Left<>(getItem());
        }

        @Override
        public String toString() {
            return "Left(" + "item = " +  getItem() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Left that = (Left) obj;
            return Objects.equals(getItem(), that.getItem());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

        public <$T> $T when(Switch<$T, U, V> cases) {
            return cases.is(this);
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

        public Right<U, V> withItem(V item) {
            return new Right<>(item);
        }

        @Override
        public Right<U, V> copy() {
            return new Right<>(getItem());
        }

        @Override
        public String toString() {
            return "Right(" + "item = " +  getItem() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Right that = (Right) obj;
            return Objects.equals(getItem(), that.getItem());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getItem());
        }

        public <$T> $T when(Switch<$T, U, V> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T, U, V> {
        $T is(Left<U, V> it);
        $T is(Right<U, V> it);
    }
    public interface SwitchBuilder<$T, U, V> {
Either<U, V> getValue();
        Function<Left<U, V>,$T> getOnLeft();
        Function<Right<U, V>,$T> getOnRight();

        default Switch<$T, U, V> build() {
            Function<Left<U, V>,$T> onLeft = getOnLeft();
            Function<Right<U, V>,$T> onRight = getOnRight();

            return new Switch<$T, U, V>() {
                public $T is(Left<U, V> it) {
                    return onLeft.apply(it);
                }

                public $T is(Right<U, V> it) {
                    return onRight.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, U, V> implements SwitchBuilder<$T, U, V>{
        private final Either<U, V> value;
        private Function<Left<U, V>,$T> onLeft;
        private Function<Right<U, V>,$T> onRight;

        CaseSwitchBuilder(Either<U, V> value) {
            this.value = value;
            this.onLeft = null;
            this.onRight = null;
        }

        @Override
        public Either<U, V> getValue() {
            return value;
        }

        public Function<Left<U, V>,$T> getOnLeft() {
            if (onLeft != null) {
                return onLeft;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<Right<U, V>,$T> getOnRight() {
            if (onRight != null) {
                return onRight;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T, U, V> onLeft(Function<Left<U, V>,$T> onLeft) {
            this.onLeft = onLeft;
            return this;
        }

        public CaseSwitchBuilder<$T, U, V> onRight(Function<Right<U, V>,$T> onRight) {
            this.onRight = onRight;
            return this;
        }

        public CaseSwitchBuilder<$T, U, V> onLeft(Supplier<$T> onLeft) {
            this.onLeft = it ->onLeft.get();
            return this;
        }

        public CaseSwitchBuilder<$T, U, V> onRight(Supplier<$T> onRight) {
            this.onRight = it ->onRight.get();
            return this;
        }

        public CaseSwitchBuilder<$T, U, V> onLeft($T onLeft) {
            this.onLeft= it -> onLeft;
            return this;
        }

        public CaseSwitchBuilder<$T, U, V> onRight($T onRight) {
            this.onRight= it -> onRight;
            return this;
        }

        public TerminalSwitchBuilder<$T, U, V> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
        }

        public TerminalSwitchBuilder<$T, U, V> orElse($T orElse) {
            return new TerminalSwitchBuilder<>(this, () -> orElse);
        }
    }
    public static final class TerminalSwitchBuilder<$T, U, V> implements SwitchBuilder<$T, U, V> {
        private final SwitchBuilder<$T, U, V> switchBuilder;
        private final Supplier<$T> orElse;

        TerminalSwitchBuilder(SwitchBuilder<$T, U, V> switchBuilder, Supplier<$T> orElse) {
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
        public Either<U, V> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<Left<U, V>, $T> getOnLeft() {
            return ensureFunction(switchBuilder.getOnLeft());
        }

        @Override
        public Function<Right<U, V>, $T> getOnRight() {
            return ensureFunction(switchBuilder.getOnRight());
        }

    }
    Either() { }

    public abstract Either<U, V> copy();

    public abstract <$T> $T when(Switch<$T, U, V> cases);

    public <$T> CaseSwitchBuilder<$T, U, V> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
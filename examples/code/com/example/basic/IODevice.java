
package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class IODevice<RA extends Readable & Appendable, R extends Readable, A extends Appendable> {
    public final static class JoinedDevices<RA extends Readable & Appendable, R extends Readable, A extends Appendable> extends IODevice<RA, R, A> {
        private RA readerAppender;

        public JoinedDevices() {
            this.readerAppender = null;
        }

        public JoinedDevices(RA readerAppender) {
            this.readerAppender = readerAppender;
        }
        public RA getReaderAppender() {
            return readerAppender;
        }

        public void setReaderAppender(RA readerAppender) {
            this.readerAppender = readerAppender;
        }

        public JoinedDevices<RA, R, A> withReaderAppender(RA readerAppender) {
            return new JoinedDevices<>(readerAppender);
        }

        @Override
        public JoinedDevices<RA, R, A> copy() {
            return new JoinedDevices<>(getReaderAppender());
        }

        @Override
        public String toString() {
            return "JoinedDevices(" + "readerAppender = " +  getReaderAppender() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JoinedDevices that = (JoinedDevices) obj;
            return Objects.equals(getReaderAppender(), that.getReaderAppender());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getReaderAppender());
        }

        public <$T> $T when(Switch<$T, RA, R, A> cases) {
            return cases.is(this);
        }

    }
    public final static class SeparateDevices<RA extends Readable & Appendable, R extends Readable, A extends Appendable> extends IODevice<RA, R, A> {
        private R reader;
        private A appender;

        public SeparateDevices() {
            this.reader = null;
            this.appender = null;
        }

        public SeparateDevices(R reader, A appender) {
            this.reader = reader;
            this.appender = appender;
        }
        public R getReader() {
            return reader;
        }

        public A getAppender() {
            return appender;
        }

        public void setReader(R reader) {
            this.reader = reader;
        }

        public void setAppender(A appender) {
            this.appender = appender;
        }

        public SeparateDevices<RA, R, A> withReader(R reader) {
            return new SeparateDevices<>(reader, getAppender());
        }

        public SeparateDevices<RA, R, A> withAppender(A appender) {
            return new SeparateDevices<>(getReader(), appender);
        }

        @Override
        public SeparateDevices<RA, R, A> copy() {
            return new SeparateDevices<>(getReader(), getAppender());
        }

        @Override
        public String toString() {
            return "SeparateDevices(" + "reader = " +  getReader() + ", appender = " +  getAppender() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SeparateDevices that = (SeparateDevices) obj;
            return Objects.equals(getReader(), that.getReader()) && Objects.equals(getAppender(), that.getAppender());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getReader(), getAppender());
        }

        public <$T> $T when(Switch<$T, RA, R, A> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T, RA extends Readable & Appendable, R extends Readable, A extends Appendable> {
        $T is(JoinedDevices<RA, R, A> it);
        $T is(SeparateDevices<RA, R, A> it);
    }
    public interface SwitchBuilder<$T, RA extends Readable & Appendable, R extends Readable, A extends Appendable> {
IODevice<RA, R, A> getValue();
        Function<JoinedDevices<RA, R, A>,$T> getOnJoinedDevices();
        Function<SeparateDevices<RA, R, A>,$T> getOnSeparateDevices();

        default Switch<$T, RA, R, A> build() {
            Function<JoinedDevices<RA, R, A>,$T> onJoinedDevices = getOnJoinedDevices();
            Function<SeparateDevices<RA, R, A>,$T> onSeparateDevices = getOnSeparateDevices();

            return new Switch<$T, RA, R, A>() {
                public $T is(JoinedDevices<RA, R, A> it) {
                    return onJoinedDevices.apply(it);
                }

                public $T is(SeparateDevices<RA, R, A> it) {
                    return onSeparateDevices.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T, RA extends Readable & Appendable, R extends Readable, A extends Appendable> implements SwitchBuilder<$T, RA, R, A>{
        private final IODevice<RA, R, A> value;
        private Function<JoinedDevices<RA, R, A>,$T> onJoinedDevices;
        private Function<SeparateDevices<RA, R, A>,$T> onSeparateDevices;

        CaseSwitchBuilder(IODevice<RA, R, A> value) {
            this.value = value;
            this.onJoinedDevices = null;
            this.onSeparateDevices = null;
        }

        @Override
        public IODevice<RA, R, A> getValue() {
            return value;
        }

        public Function<JoinedDevices<RA, R, A>,$T> getOnJoinedDevices() {
            if (onJoinedDevices != null) {
                return onJoinedDevices;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<SeparateDevices<RA, R, A>,$T> getOnSeparateDevices() {
            if (onSeparateDevices != null) {
                return onSeparateDevices;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T, RA, R, A> onJoinedDevices(Function<JoinedDevices<RA, R, A>,$T> onJoinedDevices) {
            this.onJoinedDevices = onJoinedDevices;
            return this;
        }

        public CaseSwitchBuilder<$T, RA, R, A> onSeparateDevices(Function<SeparateDevices<RA, R, A>,$T> onSeparateDevices) {
            this.onSeparateDevices = onSeparateDevices;
            return this;
        }

        public CaseSwitchBuilder<$T, RA, R, A> onJoinedDevices(Supplier<$T> onJoinedDevices) {
            this.onJoinedDevices = it ->onJoinedDevices.get();
            return this;
        }

        public CaseSwitchBuilder<$T, RA, R, A> onSeparateDevices(Supplier<$T> onSeparateDevices) {
            this.onSeparateDevices = it ->onSeparateDevices.get();
            return this;
        }

        public CaseSwitchBuilder<$T, RA, R, A> onJoinedDevices($T onJoinedDevices) {
            this.onJoinedDevices= it -> onJoinedDevices;
            return this;
        }

        public CaseSwitchBuilder<$T, RA, R, A> onSeparateDevices($T onSeparateDevices) {
            this.onSeparateDevices= it -> onSeparateDevices;
            return this;
        }

        public TerminalSwitchBuilder<$T, RA, R, A> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
        }

        public TerminalSwitchBuilder<$T, RA, R, A> orElse($T orElse) {
            return new TerminalSwitchBuilder<>(this, () -> orElse);
        }
    }
    public static final class TerminalSwitchBuilder<$T, RA extends Readable & Appendable, R extends Readable, A extends Appendable> implements SwitchBuilder<$T, RA, R, A> {
        private final SwitchBuilder<$T, RA, R, A> switchBuilder;
        private final Supplier<$T> orElse;

        TerminalSwitchBuilder(SwitchBuilder<$T, RA, R, A> switchBuilder, Supplier<$T> orElse) {
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
        public IODevice<RA, R, A> getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<JoinedDevices<RA, R, A>, $T> getOnJoinedDevices() {
            return ensureFunction(switchBuilder.getOnJoinedDevices());
        }

        @Override
        public Function<SeparateDevices<RA, R, A>, $T> getOnSeparateDevices() {
            return ensureFunction(switchBuilder.getOnSeparateDevices());
        }

    }
    IODevice() { }

    public abstract IODevice<RA, R, A> copy();

    public abstract <$T> $T when(Switch<$T, RA, R, A> cases);

    public <$T> CaseSwitchBuilder<$T, RA, R, A> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
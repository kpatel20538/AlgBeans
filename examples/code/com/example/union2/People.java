
package com.example.union2;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.DoubleFunction;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class People {
    public final static class Person extends People {
        private String name;
        private LocalDate birthday;
        private Locale prefLocale;
        private Contact contactMethod;

        public Person() {
            this.name = null;
            this.birthday = null;
            this.prefLocale = null;
            this.contactMethod = null;
        }

        public Person(String name, LocalDate birthday, Locale prefLocale, Contact contactMethod) {
            this.name = name;
            this.birthday = birthday;
            this.prefLocale = prefLocale;
            this.contactMethod = contactMethod;
        }
        public String getName() {
            return name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public Locale getPrefLocale() {
            return prefLocale;
        }

        public Contact getContactMethod() {
            return contactMethod;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public void setPrefLocale(Locale prefLocale) {
            this.prefLocale = prefLocale;
        }

        public void setContactMethod(Contact contactMethod) {
            this.contactMethod = contactMethod;
        }

        public Person withName(String name) {
            return new Person(name, getBirthday(), getPrefLocale(), getContactMethod());
        }

        public Person withBirthday(LocalDate birthday) {
            return new Person(getName(), birthday, getPrefLocale(), getContactMethod());
        }

        public Person withPrefLocale(Locale prefLocale) {
            return new Person(getName(), getBirthday(), prefLocale, getContactMethod());
        }

        public Person withContactMethod(Contact contactMethod) {
            return new Person(getName(), getBirthday(), getPrefLocale(), contactMethod);
        }

        @Override
        public Person copy() {
            return new Person(getName(), getBirthday(), getPrefLocale(), getContactMethod());
        }

        @Override
        public String toString() {
            return "Person(" + "name = " +  getName() + ", birthday = " +  getBirthday() + ", prefLocale = " +  getPrefLocale() + ", contactMethod = " +  getContactMethod() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person that = (Person) obj;
            return Objects.equals(getName(), that.getName()) && Objects.equals(getBirthday(), that.getBirthday()) && Objects.equals(getPrefLocale(), that.getPrefLocale()) && Objects.equals(getContactMethod(), that.getContactMethod());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getBirthday(), getPrefLocale(), getContactMethod());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T> {
        $T is(Person it);
    }
    public interface SwitchBuilder<$T> {
People getValue();
        Function<Person,$T> getOnPerson();

        default Switch<$T> build() {
            Function<Person,$T> onPerson = getOnPerson();

            return new Switch<$T>() {
                public $T is(Person it) {
                    return onPerson.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T> implements SwitchBuilder<$T>{
        private final People value;
        private Function<Person,$T> onPerson;

        CaseSwitchBuilder(People value) {
            this.value = value;
            this.onPerson = null;
        }

        @Override
        public People getValue() {
            return value;
        }

        public Function<Person,$T> getOnPerson() {
            if (onPerson != null) {
                return onPerson;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T> onPerson(Function<Person,$T> onPerson) {
            this.onPerson = onPerson;
            return this;
        }

        public CaseSwitchBuilder<$T> onPerson(Supplier<$T> onPerson) {
            this.onPerson = it ->onPerson.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onPerson($T onPerson) {
            this.onPerson= it -> onPerson;
            return this;
        }

        public TerminalSwitchBuilder<$T> orElse(Supplier<$T> orElse) {
            return new TerminalSwitchBuilder<>(this, orElse);
        }

        public TerminalSwitchBuilder<$T> orElse($T orElse) {
            return new TerminalSwitchBuilder<>(this, () -> orElse);
        }
    }
    public static final class TerminalSwitchBuilder<$T> implements SwitchBuilder<$T> {
        private final SwitchBuilder<$T> switchBuilder;
        private final Supplier<$T> orElse;

        TerminalSwitchBuilder(SwitchBuilder<$T> switchBuilder, Supplier<$T> orElse) {
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
        public People getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<Person, $T> getOnPerson() {
            return ensureFunction(switchBuilder.getOnPerson());
        }

    }
    People() { }

    public abstract People copy();

    public abstract <$T> $T when(Switch<$T> cases);

    public <$T> CaseSwitchBuilder<$T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
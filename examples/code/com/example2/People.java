package com.example2;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Arrays;


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

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public Locale getPrefLocale() {
            return prefLocale;
        }

        public void setPrefLocale(Locale prefLocale) {
            this.prefLocale = prefLocale;
        }

        public Contact getContactMethod() {
            return contactMethod;
        }

        public void setContactMethod(Contact contactMethod) {
            this.contactMethod = contactMethod;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
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
            return  Objects.equals(getName(), that.getName()) && Objects.equals(getBirthday(), that.getBirthday()) && Objects.equals(getPrefLocale(), that.getPrefLocale()) && Objects.equals(getContactMethod(), that.getContactMethod());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getBirthday(), getPrefLocale(), getContactMethod());
        }

    }

    public interface Switch<$T> {
        $T is(Person it);
    }

    People() { }

    public abstract <$T> $T when(Switch<$T> cases);
}
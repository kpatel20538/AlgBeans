package com.example2;

import java.time.LocalDate;
import java.util.Locale;

public abstract class People {
    public final static class Person extends People {
        private String name;
        private LocalDate birthday;
        private Locale prefLocale;
        private Contact contactMethod;

        public Person() { }

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

    }

    interface Switch<$T> {
        $T is(Person it);
    }

    People() { }

    public abstract <$T> $T when(Switch<$T> cases);
}
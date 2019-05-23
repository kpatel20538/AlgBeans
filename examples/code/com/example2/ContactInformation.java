package com.example2;

import java.time.LocalDate;
import java.util.Locale;

public abstract class ContactInformation {
    public final static class Mail extends ContactInformation {
        private String address;
        private String region;
        private String city;
        private String country;
        private String postalCode;

        public Mail() { }

        public Mail(String address, String region, String city, String country, String postalCode) {
            this.address = address;
            this.region = region;
            this.city = city;
            this.country = country;
            this.postalCode = postalCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }

    public final static class Email extends ContactInformation {
        private String addr;

        public Email() { }

        public Email(String addr) {
            this.addr = addr;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }

    public final static class Phone extends ContactInformation {
        private int[] number;

        public Phone() { }

        public Phone(int[] number) {
            this.number = number;
        }

        public int[] getNumber() {
            return number;
        }

        public void setNumber(int[] number) {
            this.number = number;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }

    public final static class Fax extends ContactInformation {
        private boolean isCallable;

        public Fax() { }

        public Fax(boolean isCallable) {
            this.isCallable = isCallable;
        }

        public boolean isIsCallable() {
            return isCallable;
        }

        public void setIsCallable(boolean isCallable) {
            this.isCallable = isCallable;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }

    interface Switch<$T> {
        $T is(Mail it);
        $T is(Email it);
        $T is(Phone it);
        $T is(Fax it);
    }

    ContactInformation() { }

    public abstract <$T> $T when(Switch<$T> cases);
}
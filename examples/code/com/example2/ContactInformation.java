package com.example2;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Arrays;


public abstract class ContactInformation {
    public final static class Mail extends ContactInformation {
        private String address;
        private String region;
        private String city;
        private String country;
        private String postalCode;

        public Mail() {
            this.address = null;
            this.region = null;
            this.city = null;
            this.country = null;
            this.postalCode = "34344";
        }

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

        @Override
        public String toString() {
            return "Mail(" + "address = " +  getAddress() + ", region = " +  getRegion() + ", city = " +  getCity() + ", country = " +  getCountry() + ", postalCode = " +  getPostalCode() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Mail that = (Mail) obj;
            return  Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getRegion(), that.getRegion()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPostalCode(), that.getPostalCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAddress(), getRegion(), getCity(), getCountry(), getPostalCode());
        }

    }

    public final static class Email extends ContactInformation {
        private String addr;

        public Email() {
            this.addr = null;
        }

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

        @Override
        public String toString() {
            return "Email(" + "addr = " +  getAddr() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Email that = (Email) obj;
            return  Objects.equals(getAddr(), that.getAddr());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAddr());
        }

    }

    public final static class Phone extends ContactInformation {
        private int[] number;

        public Phone() {
            this.number = new int [ ] { 1 , 2 , 3 , 4 };
        }

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

        @Override
        public String toString() {
            return "Phone(" + "number = " +  Arrays.toString(getNumber()) + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Phone that = (Phone) obj;
            return  Arrays.equals(getNumber(), that.getNumber());
        }

        @Override
        public int hashCode() {
            int code = 0;
            code = 37 * code + Arrays.hashCode(getNumber());
            return code;
        }

    }

    public final static class Fax extends ContactInformation {
        private boolean isCallable;

        public Fax() {
            this.isCallable = false && ( Boolean ) true || false && ( 34.0 > 60.0 );
        }

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

        @Override
        public String toString() {
            return "Fax(" + "isCallable = " +  isIsCallable() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Fax that = (Fax) obj;
            return  isIsCallable() == that.isIsCallable();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isIsCallable());
        }

    }

    public interface Switch<$T> {
        $T is(Mail it);
        $T is(Email it);
        $T is(Phone it);
        $T is(Fax it);
    }

    ContactInformation() { }

    public abstract <$T> $T when(Switch<$T> cases);
}

package com.example.union2;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.DoubleFunction;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ContactInformation {
    public final static class Mail extends ContactInformation {
        private double address;
        private String region;
        private String city;
        private String country;
        private String postalCode;

        public Mail() {
            this.address = ( ( DoubleFunction < Integer > ) d -> Double . valueOf ( Double . compare ( 10 , 20 ) * 10.0 ) . intValue ( ) ) . apply ( new int [ ] { 4 , 5 } [ 1 ] ) * 10.0;
            this.region = null;
            this.city = null;
            this.country = null;
            this.postalCode = "34344";
        }

        public Mail(double address, String region, String city, String country, String postalCode) {
            this.address = address;
            this.region = region;
            this.city = city;
            this.country = country;
            this.postalCode = postalCode;
        }

        public double getAddress() {
            return address;
        }

        public String getRegion() {
            return region;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public String getPostalCode() {
            return postalCode;
        }


        public void setAddress(double address) {
            this.address = address;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }


        public Mail withAddress(double address) {
            return new Mail(address, getRegion(), getCity(), getCountry(), getPostalCode());
        }

        public Mail withRegion(String region) {
            return new Mail(getAddress(), region, getCity(), getCountry(), getPostalCode());
        }

        public Mail withCity(String city) {
            return new Mail(getAddress(), getRegion(), city, getCountry(), getPostalCode());
        }

        public Mail withCountry(String country) {
            return new Mail(getAddress(), getRegion(), getCity(), country, getPostalCode());
        }

        public Mail withPostalCode(String postalCode) {
            return new Mail(getAddress(), getRegion(), getCity(), getCountry(), postalCode);
        }

        @Override
        public Mail copy() {
            return new Mail(getAddress(), getRegion(), getCity(), getCountry(), getPostalCode());
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
            return getAddress() == that.getAddress() && Objects.equals(getRegion(), that.getRegion()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPostalCode(), that.getPostalCode());
        }
        @Override
        public int hashCode() {
            return Objects.hash(getAddress(), getRegion(), getCity(), getCountry(), getPostalCode());
        }


        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
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


        public Email withAddr(String addr) {
            return new Email(addr);
        }

        @Override
        public Email copy() {
            return new Email(getAddr());
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
            return Objects.equals(getAddr(), that.getAddr());
        }
        @Override
        public int hashCode() {
            return Objects.hash(getAddr());
        }


        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
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


        public Phone withNumber(int[] number) {
            return new Phone(number);
        }

        @Override
        public Phone copy() {
            return new Phone(getNumber());
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
            return Arrays.equals(getNumber(), that.getNumber());
        }
        @Override
        public int hashCode() {
            int code = 0;
            code = 37 * code + Arrays.hashCode(getNumber());
            return code;
        }


        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class Fax extends ContactInformation {
        private boolean callable;

        public Fax() {
            this.callable = false && ( Boolean ) true || false && ( 34.0 > 60.0 && 5 > 4 <<  444 );
        }

        public Fax(boolean callable) {
            this.callable = callable;
        }

        public boolean isCallable() {
            return callable;
        }


        public void setCallable(boolean callable) {
            this.callable = callable;
        }


        public Fax withCallable(boolean callable) {
            return new Fax(callable);
        }

        @Override
        public Fax copy() {
            return new Fax(isCallable());
        }

        @Override
        public String toString() {
            return "Fax(" + "callable = " +  isCallable() + ")";
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Fax that = (Fax) obj;
            return isCallable() == that.isCallable();
        }
        @Override
        public int hashCode() {
            return Objects.hash(isCallable());
        }


        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T> {
        $T is(Mail it);
        $T is(Email it);
        $T is(Phone it);
        $T is(Fax it);
    }
    public interface SwitchBuilder<$T> {
ContactInformation getValue();
        Function<Mail,$T> getOnMail();
        Function<Email,$T> getOnEmail();
        Function<Phone,$T> getOnPhone();
        Function<Fax,$T> getOnFax();

        default Switch<$T> build() {
            Function<Mail,$T> onMail = getOnMail();
            Function<Email,$T> onEmail = getOnEmail();
            Function<Phone,$T> onPhone = getOnPhone();
            Function<Fax,$T> onFax = getOnFax();

            return new Switch<$T>() {
                public $T is(Mail it) {
                    return onMail.apply(it);
                }

                public $T is(Email it) {
                    return onEmail.apply(it);
                }

                public $T is(Phone it) {
                    return onPhone.apply(it);
                }

                public $T is(Fax it) {
                    return onFax.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T> implements SwitchBuilder<$T>{
        private final ContactInformation value;
        private Function<Mail,$T> onMail;
        private Function<Email,$T> onEmail;
        private Function<Phone,$T> onPhone;
        private Function<Fax,$T> onFax;

        CaseSwitchBuilder(ContactInformation value) {
            this.value = value;
            this.onMail = null;
            this.onEmail = null;
            this.onPhone = null;
            this.onFax = null;
        }

        @Override
        public ContactInformation getValue() {
            return value;
        }

        public Function<Mail,$T> getOnMail() {
            if (onMail != null) {
                return onMail;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<Email,$T> getOnEmail() {
            if (onEmail != null) {
                return onEmail;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<Phone,$T> getOnPhone() {
            if (onPhone != null) {
                return onPhone;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<Fax,$T> getOnFax() {
            if (onFax != null) {
                return onFax;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T> onMail(Function<Mail,$T> onMail) {
            this.onMail = onMail;
            return this;
        }

        public CaseSwitchBuilder<$T> onEmail(Function<Email,$T> onEmail) {
            this.onEmail = onEmail;
            return this;
        }

        public CaseSwitchBuilder<$T> onPhone(Function<Phone,$T> onPhone) {
            this.onPhone = onPhone;
            return this;
        }

        public CaseSwitchBuilder<$T> onFax(Function<Fax,$T> onFax) {
            this.onFax = onFax;
            return this;
        }

        public CaseSwitchBuilder<$T> onMail(Supplier<$T> onMail) {
            this.onMail = it ->onMail.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onEmail(Supplier<$T> onEmail) {
            this.onEmail = it ->onEmail.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onPhone(Supplier<$T> onPhone) {
            this.onPhone = it ->onPhone.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onFax(Supplier<$T> onFax) {
            this.onFax = it ->onFax.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onMail($T onMail) {
            this.onMail= it -> onMail;
            return this;
        }

        public CaseSwitchBuilder<$T> onEmail($T onEmail) {
            this.onEmail= it -> onEmail;
            return this;
        }

        public CaseSwitchBuilder<$T> onPhone($T onPhone) {
            this.onPhone= it -> onPhone;
            return this;
        }

        public CaseSwitchBuilder<$T> onFax($T onFax) {
            this.onFax= it -> onFax;
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
        public ContactInformation getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<Mail, $T> getOnMail() {
            return ensureFunction(switchBuilder.getOnMail());
        }

        @Override
        public Function<Email, $T> getOnEmail() {
            return ensureFunction(switchBuilder.getOnEmail());
        }

        @Override
        public Function<Phone, $T> getOnPhone() {
            return ensureFunction(switchBuilder.getOnPhone());
        }

        @Override
        public Function<Fax, $T> getOnFax() {
            return ensureFunction(switchBuilder.getOnFax());
        }

    }
    ContactInformation() { }

    public abstract ContactInformation copy();

    public abstract <$T> $T when(Switch<$T> cases);

    public <$T> CaseSwitchBuilder<$T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
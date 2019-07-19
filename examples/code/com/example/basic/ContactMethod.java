
package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ContactMethod {
    public final static class Email extends ContactMethod {
        private String emailAdr;

        public Email() {
            this.emailAdr = null;
        }

        public Email(String emailAdr) {
            this.emailAdr = emailAdr;
        }
        public String getEmailAdr() {
            return emailAdr;
        }

        public void setEmailAdr(String emailAdr) {
            this.emailAdr = emailAdr;
        }

        public Email withEmailAdr(String emailAdr) {
            return new Email(emailAdr);
        }

        @Override
        public Email copy() {
            return new Email(getEmailAdr());
        }

        @Override
        public String toString() {
            return "Email(" + "emailAdr = " +  getEmailAdr() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Email that = (Email) obj;
            return Objects.equals(getEmailAdr(), that.getEmailAdr());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getEmailAdr());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class Phone extends ContactMethod {
        private String phoneNum;
        private String country;
        private boolean canText;

        public Phone() {
            this.phoneNum = null;
            this.country = null;
            this.canText = false;
        }

        public Phone(String phoneNum, String country, boolean canText) {
            this.phoneNum = phoneNum;
            this.country = country;
            this.canText = canText;
        }
        public String getPhoneNum() {
            return phoneNum;
        }

        public String getCountry() {
            return country;
        }

        public boolean isCanText() {
            return canText;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCanText(boolean canText) {
            this.canText = canText;
        }

        public Phone withPhoneNum(String phoneNum) {
            return new Phone(phoneNum, getCountry(), isCanText());
        }

        public Phone withCountry(String country) {
            return new Phone(getPhoneNum(), country, isCanText());
        }

        public Phone withCanText(boolean canText) {
            return new Phone(getPhoneNum(), getCountry(), canText);
        }

        @Override
        public Phone copy() {
            return new Phone(getPhoneNum(), getCountry(), isCanText());
        }

        @Override
        public String toString() {
            return "Phone(" + "phoneNum = " +  getPhoneNum() + ", country = " +  getCountry() + ", canText = " +  isCanText() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Phone that = (Phone) obj;
            return Objects.equals(getPhoneNum(), that.getPhoneNum()) && Objects.equals(getCountry(), that.getCountry()) && isCanText() == that.isCanText();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPhoneNum(), getCountry(), isCanText());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class Fax extends ContactMethod {
        private String faxNum;
        private String country;

        public Fax() {
            this.faxNum = null;
            this.country = null;
        }

        public Fax(String faxNum, String country) {
            this.faxNum = faxNum;
            this.country = country;
        }
        public String getFaxNum() {
            return faxNum;
        }

        public String getCountry() {
            return country;
        }

        public void setFaxNum(String faxNum) {
            this.faxNum = faxNum;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Fax withFaxNum(String faxNum) {
            return new Fax(faxNum, getCountry());
        }

        public Fax withCountry(String country) {
            return new Fax(getFaxNum(), country);
        }

        @Override
        public Fax copy() {
            return new Fax(getFaxNum(), getCountry());
        }

        @Override
        public String toString() {
            return "Fax(" + "faxNum = " +  getFaxNum() + ", country = " +  getCountry() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Fax that = (Fax) obj;
            return Objects.equals(getFaxNum(), that.getFaxNum()) && Objects.equals(getCountry(), that.getCountry());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFaxNum(), getCountry());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T> {
        $T is(Email it);
        $T is(Phone it);
        $T is(Fax it);
    }
    public interface SwitchBuilder<$T> {
ContactMethod getValue();
        Function<Email,$T> getOnEmail();
        Function<Phone,$T> getOnPhone();
        Function<Fax,$T> getOnFax();

        default Switch<$T> build() {
            Function<Email,$T> onEmail = getOnEmail();
            Function<Phone,$T> onPhone = getOnPhone();
            Function<Fax,$T> onFax = getOnFax();

            return new Switch<$T>() {
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
        private final ContactMethod value;
        private Function<Email,$T> onEmail;
        private Function<Phone,$T> onPhone;
        private Function<Fax,$T> onFax;

        CaseSwitchBuilder(ContactMethod value) {
            this.value = value;
            this.onEmail = null;
            this.onPhone = null;
            this.onFax = null;
        }

        @Override
        public ContactMethod getValue() {
            return value;
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
        public ContactMethod getValue() {
            return switchBuilder.getValue();
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
    ContactMethod() { }

    public abstract ContactMethod copy();

    public abstract <$T> $T when(Switch<$T> cases);

    public <$T> CaseSwitchBuilder<$T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
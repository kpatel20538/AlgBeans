
package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Json {
    public final static class JsonString extends Json {
        private String value;

        public JsonString() {
            this.value = "";
        }

        public JsonString(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public JsonString withValue(String value) {
            return new JsonString(value);
        }

        @Override
        public JsonString copy() {
            return new JsonString(getValue());
        }

        @Override
        public String toString() {
            return "JsonString(" + "value = " +  getValue() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JsonString that = (JsonString) obj;
            return Objects.equals(getValue(), that.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class JsonNumber extends Json {
        private double value;

        public JsonNumber() {
            this.value = 0.0;
        }

        public JsonNumber(double value) {
            this.value = value;
        }
        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public JsonNumber withValue(double value) {
            return new JsonNumber(value);
        }

        @Override
        public JsonNumber copy() {
            return new JsonNumber(getValue());
        }

        @Override
        public String toString() {
            return "JsonNumber(" + "value = " +  getValue() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JsonNumber that = (JsonNumber) obj;
            return getValue() == that.getValue();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class JsonBoolean extends Json {
        private boolean value;

        public JsonBoolean() {
            this.value = false;
        }

        public JsonBoolean(boolean value) {
            this.value = value;
        }
        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public JsonBoolean withValue(boolean value) {
            return new JsonBoolean(value);
        }

        @Override
        public JsonBoolean copy() {
            return new JsonBoolean(isValue());
        }

        @Override
        public String toString() {
            return "JsonBoolean(" + "value = " +  isValue() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JsonBoolean that = (JsonBoolean) obj;
            return isValue() == that.isValue();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isValue());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class JsonNull extends Json {

        @Override
        public JsonNull copy() {
            return new JsonNull();
        }

        @Override
        public String toString() {
            return "JsonNull()";
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && getClass() == obj.getClass();
        }

        @Override
        public int hashCode() {
            return 0;
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class JsonArray extends Json {
        private List<Json> listing;

        public JsonArray() {
            this.listing = Collections . emptyList ( );
        }

        public JsonArray(List<Json> listing) {
            this.listing = listing;
        }
        public List<Json> getListing() {
            return listing;
        }

        public void setListing(List<Json> listing) {
            this.listing = listing;
        }

        public JsonArray withListing(List<Json> listing) {
            return new JsonArray(listing);
        }

        @Override
        public JsonArray copy() {
            return new JsonArray(getListing());
        }

        @Override
        public String toString() {
            return "JsonArray(" + "listing = " +  getListing() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JsonArray that = (JsonArray) obj;
            return Objects.equals(getListing(), that.getListing());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getListing());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public final static class JsonObject extends Json {
        private Map<JsonString, Json> mapping;

        public JsonObject() {
            this.mapping = Collections . emptyMap ( );
        }

        public JsonObject(Map<JsonString, Json> mapping) {
            this.mapping = mapping;
        }
        public Map<JsonString, Json> getMapping() {
            return mapping;
        }

        public void setMapping(Map<JsonString, Json> mapping) {
            this.mapping = mapping;
        }

        public JsonObject withMapping(Map<JsonString, Json> mapping) {
            return new JsonObject(mapping);
        }

        @Override
        public JsonObject copy() {
            return new JsonObject(getMapping());
        }

        @Override
        public String toString() {
            return "JsonObject(" + "mapping = " +  getMapping() + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            JsonObject that = (JsonObject) obj;
            return Objects.equals(getMapping(), that.getMapping());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getMapping());
        }

        public <$T> $T when(Switch<$T> cases) {
            return cases.is(this);
        }

    }
    public interface Switch<$T> {
        $T is(JsonString it);
        $T is(JsonNumber it);
        $T is(JsonBoolean it);
        $T is(JsonNull it);
        $T is(JsonArray it);
        $T is(JsonObject it);
    }
    public interface SwitchBuilder<$T> {
Json getValue();
        Function<JsonString,$T> getOnJsonString();
        Function<JsonNumber,$T> getOnJsonNumber();
        Function<JsonBoolean,$T> getOnJsonBoolean();
        Function<JsonNull,$T> getOnJsonNull();
        Function<JsonArray,$T> getOnJsonArray();
        Function<JsonObject,$T> getOnJsonObject();

        default Switch<$T> build() {
            Function<JsonString,$T> onJsonString = getOnJsonString();
            Function<JsonNumber,$T> onJsonNumber = getOnJsonNumber();
            Function<JsonBoolean,$T> onJsonBoolean = getOnJsonBoolean();
            Function<JsonNull,$T> onJsonNull = getOnJsonNull();
            Function<JsonArray,$T> onJsonArray = getOnJsonArray();
            Function<JsonObject,$T> onJsonObject = getOnJsonObject();

            return new Switch<$T>() {
                public $T is(JsonString it) {
                    return onJsonString.apply(it);
                }

                public $T is(JsonNumber it) {
                    return onJsonNumber.apply(it);
                }

                public $T is(JsonBoolean it) {
                    return onJsonBoolean.apply(it);
                }

                public $T is(JsonNull it) {
                    return onJsonNull.apply(it);
                }

                public $T is(JsonArray it) {
                    return onJsonArray.apply(it);
                }

                public $T is(JsonObject it) {
                    return onJsonObject.apply(it);
                }

            };
        }

        default $T apply() {
            return getValue().when(build());
        }
    }
    public static final class CaseSwitchBuilder<$T> implements SwitchBuilder<$T>{
        private final Json value;
        private Function<JsonString,$T> onJsonString;
        private Function<JsonNumber,$T> onJsonNumber;
        private Function<JsonBoolean,$T> onJsonBoolean;
        private Function<JsonNull,$T> onJsonNull;
        private Function<JsonArray,$T> onJsonArray;
        private Function<JsonObject,$T> onJsonObject;

        CaseSwitchBuilder(Json value) {
            this.value = value;
            this.onJsonString = null;
            this.onJsonNumber = null;
            this.onJsonBoolean = null;
            this.onJsonNull = null;
            this.onJsonArray = null;
            this.onJsonObject = null;
        }

        @Override
        public Json getValue() {
            return value;
        }

        public Function<JsonString,$T> getOnJsonString() {
            if (onJsonString != null) {
                return onJsonString;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<JsonNumber,$T> getOnJsonNumber() {
            if (onJsonNumber != null) {
                return onJsonNumber;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<JsonBoolean,$T> getOnJsonBoolean() {
            if (onJsonBoolean != null) {
                return onJsonBoolean;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<JsonNull,$T> getOnJsonNull() {
            if (onJsonNull != null) {
                return onJsonNull;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<JsonArray,$T> getOnJsonArray() {
            if (onJsonArray != null) {
                return onJsonArray;
            } else {
                throw new NullPointerException();
            }
        }

        public Function<JsonObject,$T> getOnJsonObject() {
            if (onJsonObject != null) {
                return onJsonObject;
            } else {
                throw new NullPointerException();
            }
        }

        public CaseSwitchBuilder<$T> onJsonString(Function<JsonString,$T> onJsonString) {
            this.onJsonString = onJsonString;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNumber(Function<JsonNumber,$T> onJsonNumber) {
            this.onJsonNumber = onJsonNumber;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonBoolean(Function<JsonBoolean,$T> onJsonBoolean) {
            this.onJsonBoolean = onJsonBoolean;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNull(Function<JsonNull,$T> onJsonNull) {
            this.onJsonNull = onJsonNull;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonArray(Function<JsonArray,$T> onJsonArray) {
            this.onJsonArray = onJsonArray;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonObject(Function<JsonObject,$T> onJsonObject) {
            this.onJsonObject = onJsonObject;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonString(Supplier<$T> onJsonString) {
            this.onJsonString = it ->onJsonString.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNumber(Supplier<$T> onJsonNumber) {
            this.onJsonNumber = it ->onJsonNumber.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonBoolean(Supplier<$T> onJsonBoolean) {
            this.onJsonBoolean = it ->onJsonBoolean.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNull(Supplier<$T> onJsonNull) {
            this.onJsonNull = it ->onJsonNull.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonArray(Supplier<$T> onJsonArray) {
            this.onJsonArray = it ->onJsonArray.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonObject(Supplier<$T> onJsonObject) {
            this.onJsonObject = it ->onJsonObject.get();
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonString($T onJsonString) {
            this.onJsonString= it -> onJsonString;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNumber($T onJsonNumber) {
            this.onJsonNumber= it -> onJsonNumber;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonBoolean($T onJsonBoolean) {
            this.onJsonBoolean= it -> onJsonBoolean;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonNull($T onJsonNull) {
            this.onJsonNull= it -> onJsonNull;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonArray($T onJsonArray) {
            this.onJsonArray= it -> onJsonArray;
            return this;
        }

        public CaseSwitchBuilder<$T> onJsonObject($T onJsonObject) {
            this.onJsonObject= it -> onJsonObject;
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
        public Json getValue() {
            return switchBuilder.getValue();
        }

        @Override
        public Function<JsonString, $T> getOnJsonString() {
            return ensureFunction(switchBuilder.getOnJsonString());
        }

        @Override
        public Function<JsonNumber, $T> getOnJsonNumber() {
            return ensureFunction(switchBuilder.getOnJsonNumber());
        }

        @Override
        public Function<JsonBoolean, $T> getOnJsonBoolean() {
            return ensureFunction(switchBuilder.getOnJsonBoolean());
        }

        @Override
        public Function<JsonNull, $T> getOnJsonNull() {
            return ensureFunction(switchBuilder.getOnJsonNull());
        }

        @Override
        public Function<JsonArray, $T> getOnJsonArray() {
            return ensureFunction(switchBuilder.getOnJsonArray());
        }

        @Override
        public Function<JsonObject, $T> getOnJsonObject() {
            return ensureFunction(switchBuilder.getOnJsonObject());
        }

    }
    Json() { }

    public abstract Json copy();

    public abstract <$T> $T when(Switch<$T> cases);

    public <$T> CaseSwitchBuilder<$T> switchBuilder() {
        return new CaseSwitchBuilder<>(this);
    }
}
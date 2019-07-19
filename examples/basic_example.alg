package com.example.basic;

import java.util.Map;
import java.util.List;
import java.util.Collections;

ContactMethod = Email(String emailAdr) | Phone(String phoneNum, String country, boolean canText)
    | Fax(String faxNum, String country);

// Simple Parameter Types
Optional<T> = Exists(T item) | NoExists();
Either<U, V> = Left(U item) | Right(V item);

// Constraints on Parameter Types
IODevice<RA extends Readable & Appendable, R extends Readable, A extends Appendable> =
    JoinedDevices(RA readerAppender) | SeparateDevices(R reader, A appender);

//  Self-Referential Structure and read-only fields
ImmutableList<T> = Node(final T item, final ImmutableList<T> next) | Null();

// Default Initializer and Self-Referential Structure
Json = JsonString(String value = "")
    | JsonNumber(double value = 0.0)
    | JsonBoolean(boolean value = false)
    | JsonNull()
    | JsonArray(List<Json> listing = Collections.emptyList())
    | JsonObject(Map<JsonString, Json> mapping = Collections.emptyMap()) ;

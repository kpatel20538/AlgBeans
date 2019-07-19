# AlgBeans

AlgBeans is code generator that converts Haskell-like Type specification into JVM-compliant Tagged Unions.


## Build and Run

```bash
$ ./gradlew build
$ unzip ./build/distributions/AlgBeans-1.0-SNAPSHOT.zip
$ ./AlgBeans-1.0-SNAPSHOT/bin/AlgBeans --in ./examples/basic_example.alg
```

## Quick Start

Given the following *.alg file

**ContactMethod.alg**
```
package com.example.unions;

ContactMethod = Email(String emailAdr)
    | Phone(String phoneNum, String country, boolean canText)
    | Fax(String faxNum, String country);

```

Invoke the following commands to generate a java package.

```bash
$ mkdir ./gen
$ AlgBeans --in ContactMethod.alg --out ./gen
```

After moving the package to the desired location, you can import it like in the below example.

```java
import com.example.unions.ContactMethod;

//...

ContactMethod cm = new ContactMethod.Fax("232-435-4345", "US");
String document = cm.<String>switchBuilder()
    .onEmail(email -> "<div class='email'>" + email.getEmailAdr() + "</div>")
    .onPhone(phone -> "<div class='phone'>" + phone.getPhoneNum() + "</div>")
    .onFax(fax -> "<div class='fax'>" + fax.getFaxNum() + "</div>")
    .apply();

System.out.println(document);
```

See more in the examples folder.

## Features

The Following is generated for each Type Union Set.
* Default Constructors
* A Constructor that  accepts all fields
* Getter methods for all fields
* 'With' methods for all fields (A shallow copy replacing the given field)
* Setter methods for non-final fields
* A Shallow Copy method
* hashCode, equals, and toString
* A Pattern Matcher Interface (Switch), a 'when' method that accepts pattern matchers,
* A Pattern Matcher Builder (SwitchBuilder)

The Type Specification Language can also describe the following
* Generic Unions (like Optional<T>)
    * Individual Case Types can not be generic
    * e.g. Union = Case<T>(T t) | Case2<U>(U u); is not permitted
    * Union<T, U> = Case(T t) | Case2(U u); is permitted
* Self Referential Types
    * e.g. LList<T> = Null() | Node(final T t, final LList<T> list = Null());


## Gradle Setup

To include this library in your gradle project, add the following to your build.gradle file.

*build.gradle*

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.kpatel20538:AlgBeans:-SNAPSHOT'
}
```

# AlgBeans

AlgBeans is code generator that converts Haskell-like Type specification into JVM-compliant Tagged Unions.


## Build and Run

```bash
$ ./gradlew build
$ unzip ./build/distributions/AlgBeans-1.0-SNAPSHOT.zip
$ ./AlgBeans-1.0-SNAPSHOT/bin/AlgBeans --in ./examples/basic_example.alg
```

One can move the generated package and use it as follows

```java
import com.example.basic.ContactMethod;

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

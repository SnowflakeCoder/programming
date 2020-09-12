# Java versions and new features

Note: Preview features are features that aren't complete, but are made available on a "preview" basis so customers can get early access and provide feedback.

JDK 15 will be a **short-term feature release** (non-LTS release), supported for six months according to Oracle’s **six-month release cadence**. The next **long-term support (LTS) release**, which will receive several years of support, will be JDK 17. The **current LTS release is JDK 11**.

## Java 15 Features

Highlights of JDK 15 include text blocks, **garbage collectors**, **hidden classes**, a **foreign-memory access API**, and previews of sealed classes and records.

- A second incubator of a **foreign-memory access API**.
- A preview of **sealed classes**.
- **Records** included in a second preview version in JDK 15. 
- **Reimplementing the legacy DatagramSocket API**.
- **Hidden classes**
- The **Z Garbage Collector (ZGC)**, would graduate from an **experimental feature to a product feature**.
- The **Shenandoah low-pause-time garbage collector**, would graduate from an **experimental feature to a product feature**.
- **Removal of Nashorn**, made obsolete by technologies such as **GraalVM**. 

## Java 14 Features

**Switch Expression (Standard)**

The switch expressions that were *preview* in versions 12 and 13, are **now standardized**.

```
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY                -> 7;
    default      -> {
      String s = day.toString();
      int result = s.length();
      yield result;
    }
};
```

**Records (Preview)**

There are now record classes, which help alleviate the pain of writing a lot of boilerplate with Java.

Have a look at this pre Java 14 class, which only contains data, (potentially) getters/setters, equals/hashcode, toString.

```
final class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
    // state-based implementations of equals, hashCode, toString
    // nothing else
```

With records, it can now be written like this:

```
record Point(int x, int y) { }
```

Again, this is a preview feature and subject to change in future releases.

#### Helpful NullPointerExceptions

Finally NullPointerExceptions describe *exactly* which variable was null.

```
author.age = 35;
---
Exception in thread "main" java.lang.NullPointerException:
     Cannot assign field "age" because "author" is null
```

#### Pattern Matching For InstanceOf (Preview)

Whereas previously you had to (cast) your objects inside an instanceof like this:

```
if (obj instanceof String) {
    String s = (String) obj;
    // use s
}
```

You can now do this, effectively dropping the cast.

```
if (obj instanceof String s) {
    System.out.println(s.contains("hello"));
}
```

#### Packaging Tool (Incubator)

There’s an incubating *jpackage* tool, which allows to package your Java application into platform-specific packages, including all necessary dependencies.

- Linux: deb and rpm
- macOS: pkg and dmg
- Windows: msi and exe

#### Garbage Collectors

The Concurrent Mark Sweep (CMS) Garbage Collector has been removed, and the experimental Z Garbage Collector collector has been added.



## Java 13 Features



## Java 12 Features

- **Collectors.teeing()** in Stream API
- String API Changes
- Files.mismatch(Path, Path)
- Compact Number Formatting
- Support for Unicode 11
- Switch Expressions (Preview)

## Java 11 Features

- In this version, **JRE or Server JRE is no longer offered**. Only JDK is offered. Auto-update has been removed from JRE installations.
- **Type Inference** for Lambda Parameters
- **Epsilon Garbage Collector**
  - Epsilon is a <u>passive or "no-op" GC</u>. It handles memory allocation but **doesn't recycle it** when objects are no longer used. When your application exhausts the Java heap, the JVM shuts down. In other words, <u>Epsilon will allow your application to run **out of memory and crash**</u>. Epsilon GC is also helpful for **cost-benefit comparison** of other garbage collectors and performance testing.
- **Z Garbage Collector**
  - It is a **scalable low-latency garbage collector**. The Z garbage collector ensures that <u>pause times do not go beyond 10ms</u>. It also ensures that pause times do not increase with the size of the heap or live-set. Finally, ZGC also manages heaps of varying sizes from 100 megabytes to multi terabytes.
- **Dynamic Allocation of Compiler Threads**
  Dynamic control of compiler threads is possible now in Java 11 with a new command line flag. The VM starts numerous compiler threads on systems with multiple CPUs in the tiered compilation mode. There is no concern for the number of compilation requests or available memory with this command line flag. However, this can lead to inefficient use of resources as idle threads could also consume memory. So, with the new command line flag, implementation has changed. Now, only one compiler thread of each type initiates at startup, and then the starting and shutdown of subsequent threats are subject to dynamic management.

HTTP Client API
Launch Single-File Programs Without Compilation
String API Changes
Collection.toArray(IntFunction)
Files.readString() and Files.writeString()
Optional.isEmpty()



## 

The <u>deployment stack required for running applets and web applications has been removed</u> from JDK which was deprecated in JDK 9.
Entire section of <u>supported browsers has been removed</u> from list of supported configurations due to unavailability of deployment stack.
**Auto-update has been removed from JRE installations** in Windows and MacOS.
In this version, **JRE or Server JRE is no longer offered**. Only JDK is offered.

**New String methods:**

**isBlank()** - returns true when a string is empty

**lines()** -  return a collection of strings which are divided by line terminators.

**repeat(int n)** - Result is the concatenated string of original string repeated the number of times in the argument.

**Epsilon Garbage Collector:**

This handles memory allocation but does not have actual memory reclamation mechanism. Once the available Java heap is exhausted, JVM will shut down.
Its goals are:-

- Performance testing
- Memory pressure testing
- last drop latency improvements



**Removal of thread functions:** **stop(Throwable obj)** and **destroy()** objects have been removed from the JDK 11 because they only throw **UnSupportedOperation** and **NoSuchMethodError** respectively. Other than that, they were of no use.

**Local-Variable Type Inference (var) for Lambda Parameters**: JDK 11 allows ‘var’ to be used in lambda expressions. This was introduced to be consistent with local ‘var’ syntax of Java 10.

```java
 IntStream.of(1, 2, 3, 5, 6, 7) 
               .filter((var i) -> i % 2 == 0) 
               .forEach(System.out::println); 
```

#### Run Source Files

Starting with Java 10, you can run Java source files *without* having to compile them first. A step towards scripting.

```
ubuntu@DESKTOP-168M0IF:~$ java MyScript.java
```

#### HttpClient

The HttpClient from Java 9 in its final, non-preview version.

#### Other stuff

**Flight Recorder**, **No-Op Garbage Collector**, Nashorn-Javascript-Engine deprecated etc.



## Java 10 Features

JEP 286: Local Variable Type Inference
JEP 322: Time-Based Release Versioning
JEP 304: Garbage-Collector Interface
JEP 307: Parallel Full GC for G1
JEP 316: Heap Allocation on Alternative Memory Devices
JEP 296: Consolidate the JDK Forest into a Single Repository
JEP 310: Application Class-Data Sharing
JEP 314: Additional Unicode Language-Tag Extensions
JEP 319: Root Certificates
JEP 317: Experimental Java-Based JIT Compiler
JEP 312: Thread-Local Handshakes
JEP 313: Remove the Native-Header Generation Tool
New Added APIs and Options
Removed APIs and Options

## Java 9 Features

Java 9, the biggest change is the modularization i.e. Java modules.

Java platform module system
Interface Private Methods
HTTP 2 Client
JShell – REPL Tool
Platform and JVM Logging
Process API Updates
Collection API Updates
Improvements in Stream API
Multi-Release JAR Files
@Deprecated Tag Changes
Stack Walking
Java Docs Updates
Miscellaneous Other Features
Please see the updated release info here.

## Java 8 Features

Code name culture is dropped. Included features were:

- **Lambda expression support in APIs**,
- Method references,
- **Functional interfaces**,
- **Stream API**,
- Interface changes: **Default and static methods**,
- **Arrays Parallel Sort**,
- Type and Repating Annotations,
- IO Enhancements,
- 



Optionals
Nashorn – JavaScript runtime which allows developers to embed JavaScript code within applications
Annotation on Java Types
Unsigned Integer Arithmetic
Repeating annotations
New Date and Time API
Statically-linked JNI libraries
Launch JavaFX applications from jar files
Remove the permanent generation from GC

### 

Java 8 was a massive release and you can find a list of all features at [the Oracle website](https://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html). There’s two main feature sets I’d like to mention here, though:

#### Language Features: Lambdas etc.

Before Java 8, whenever you wanted to instantiate, for example, a new Runnable, you had to write an anonymous inner class like so:

```
 Runnable runnable = new Runnable(){
       @Override
       public void run(){
         System.out.println("Hello world !");
       }
     };
```

With lambdas, the same code looks like this:

```
Runnable runnable = () -> System.out.println("Hello world two!");
```

You also got method references, repeating annotations, default methods for interfaces and a few other language features.

#### Collections & Streams

In Java 8 you also got functional-style operations for collections, also known as the Stream API. A quick example:

```
List<String> list = Arrays.asList("franz", "ferdinand", "fiel", "vom", "pferd");
```

Now pre-Java 8 you basically had to write for-loops to do something with that list.

With the Streams API, you can do the following:

```
list.stream()
    .filter(name -> name.startsWith("f"))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
```

#### If you want more Java 8 practice

Obviously, I can only give a quick overview of each newly added Stream, Lambda or Optional method in Java 8 in the scope of this guide.

If you want a more detailed, thorough overview - including exercises - you can have a look at my [Java 8 core features](https://www.marcobehler.com/courses/32-core-java-features-version-8-12?utm_campaign=java_features_guide&utm_medium=java_features_guide&utm_source=java_features_guide) course.

## Java SE 7 Features

This release was called “Dolphin”. Included features were:

JVM support for dynamic languages
Compressed 64-bit pointers
Strings in switch
Automatic resource management in try-statement
The diamond operator
Simplified varargs method declaration
Binary integer literals
Underscores in numeric literals
Improved exception handling
ForkJoin Framework
NIO 2.0 having support for multiple file systems, file metadata and symbolic links
WatchService
Timsort is used to sort collections and arrays of objects instead of merge sort
APIs for the graphics features
Support for new network protocols, including SCTP and Sockets Direct Protocol

## Java SE 6 Features

This release was called “**Mustang**”. Sun dropped the “.0” from the version number and version became Java SE 6. Included features were:

Scripting Language Support
Performance improvements
JAX-WS
JDBC 4.0
Java Compiler API
JAXB 2.0 and StAX parser
Pluggable annotations
New GC algorithms

## J2SE 5.0 Features

This release was called “Tiger”. Version was also called 5.0 rather than 1.5. 

Generics
Annotations
Autoboxing/unboxing
Enumerations
Varargs
Enhanced for each loop
Static imports
New concurrency utilities in java.util.concurrent
Scanner class for parsing data from various input streams and buffers.

## J2SE 1.4 Features

This release was called “Merlin”. 

assert keyword
Regular expressions
Exception chaining
Internet Protocol version 6 (IPv6) support
New I/O; NIO
Logging API
Image I/O API
Integrated XML parser and XSLT processor (JAXP)
Integrated security and cryptography extensions (JCE, JSSE, JAAS)
Java Web Start
Preferences API (java.util.prefs)

J2SE 1.3 Features
This release was called “Kestrel”. Included features were:

HotSpot JVM
Java Naming and Directory Interface (JNDI)
Java Platform Debugger Architecture (JPDA)
JavaSound
Synthetic proxy classes

J2SE 1.2 Features
This release was called “Playground”. This was a major release in terms of number of classes added (almost trippled the size). “J2SE” term was introduced to distinguish the code platform from J2EE and J2ME. Included features were:

strictfp keyword
Swing graphical API
Sun’s JVM was equipped with a JIT compiler for the first time
Java plug-in
Collections framework

JDK 1 Features
This was the initial release and was originally called Oak. This had very unstable APIs and one java web browser named WebRunner.

The first stable version, JDK 1.0.2, was called Java 1.

On February 19, 1997, JDK 1.1 was released havind a list of big features such as:

AWT event model
Inner classes
JavaBeans
JDBC
RMI
Reflection which supported Introspection only, no modification at runtime was possible.
JIT (Just In Time) compiler for Windows.



## 







## Java Features 8-14

As mentioned at the very beginning of this guide: Essentially *all* (don’t be picky now) Java 8 language features also work in Java 14. The same goes for all other Java versions in between.

Which in turns means that all language features from Java 8 serve as very good Java base knowledge and everything else (Java 9-14) is pretty much additional features *on top* of that baseline.

Here’s a quick overview of what the specific versions have to offer:



### - Java 9 -

Java 9 also was a fairly big release, with a couple of additions:

#### Collections

Collections got a couple of new helper methods, to easily construct Lists, Sets and Maps.

```
List<String> list = List.of("one", "two", "three");
Set<String> set = Set.of("one", "two", "three");
Map<String, String> map = Map.of("foo", "one", "bar", "two");
```

#### Streams

Streams got a couple of additions, in the form of takeWhile,dropWhile,iterate methods.

```
Stream<String> stream = Stream.iterate("", s -> s + "s")
  .takeWhile(s -> s.length() < 10);
```

#### Optionals

Optionals got the sorely missed ifPresentOrElse method.

```
user.ifPresentOrElse(this::displayAccount, this::displayLogin);
```

#### Interfaces

Interfaces got private methods:

```
public interface MyInterface {

    private static void myPrivateMethod(){
        System.out.println("Yay, I am private!");
    }
}
```

#### Other Language Features

And a couple of other improvements, like an improved try-with-resources statement or diamond operator extensions.

#### JShell

Finally, Java got a shell where you can try out simple commands and get immediate results.

```
% jshell
|  Welcome to JShell -- Version 9
|  For an introduction type: /help intro

jshell> int x = 10
x ==> 10
```

#### HTTPClient

Java 9 brought the initial preview version of a new HttpClient. Up until then, Java’s built-in Http support was rather low-level, and you had to fall back on using third-party libraries like Apache HttpClient or OkHttp (which are great libraries, btw!).

With Java 9, Java got its own, modern client - although in preview mode, which means subject to change in later Java versions.

#### Project Jigsaw: Java Modules and Multi-Release Jar Files

Java 9 got the [Jigsaw Module System](https://www.oracle.com/corporate/features/understanding-java-9-modules.html), which somewhat resembles the good old [OSGI specification](https://en.wikipedia.org/wiki/OSGi). It is not in the scope of this guide to go into full detail on Jigsaw, but have a look at the previous links to learn more.

Multi-Release .jar files made it possible to have one .jar file which contains different classes for different JVM versions. So your program can behave differently/have different classes used when run on Java 8 vs. Java 10, for example.

#### If you want more Java 9 practice

Again, this is just a quick overview of Java 9 features and if you want more thorough explanations and exercises, have a look at the [Java 9 core features](https://www.marcobehler.com/courses/32-core-java-features-version-8-12?utm_campaign=java_features_guide&utm_medium=java_features_guide&utm_source=java_features_guide) course.

### - Java 10 -

There have been a few changes to Java 10, like Garbage Collection etc. But the only real change you as a developer will likely see is the introduction of the "var"-keyword, also called local-variable type inference.

#### Local-Variable Type Inference: var-keyword

```
// Pre-Java 10

String myName = "Marco";

// With Java 10

var myName = "Marco"
```

Feels Javascript-y, doesn’t it? It is still strongly typed, though, and only applies to variables *inside methods* (thanks, [dpash](https://www.reddit.com/user/dpash), for pointing that out again).



### - Java 12 -

Java 12 got a couple [new features and clean-ups](https://www.oracle.com/technetwork/java/javase/12-relnote-issues-5211422.html), but the only ones worth mentioning here are Unicode 11 support and a preview of the new switch expression, which you will see covered in the next section.

### - Java 13 -

You can find a complete feature list [here](https://www.oracle.com/technetwork/java/13-relnote-issues-5460548.html), but essentially you are getting Unicode 12.1 support, as well as two new or improved preview features (subject to change in the future):

#### Switch Expression (Preview)

Switch expressions can now return a value. And you can use a lambda-style syntax for your expressions, without the fall-through/break issues:

Old switch statements looked like this:

```
switch(status) {
  case SUBSCRIBER:
    // code block
    break;
  case FREE_TRIAL:
    // code block
    break;
  default:
    // code block
}
```

Whereas with Java 13, switch statements can look like this:

```
boolean result = switch (status) {
    case SUBSCRIBER -> true;
    case FREE_TRIAL -> false;
    default -> throw new IllegalArgumentException("something is murky!");
};
```

#### Multiline Strings (Preview)

You can *finally* do this in Java:

```
String htmlBeforeJava13 = "<html>\n" +
              "    <body>\n" +
              "        <p>Hello, world</p>\n" +
              "    </body>\n" +
              "</html>\n";

String htmlWithJava13 = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
```



# Details

## Foreign-memory access API

A **second incubator of a foreign-memory access API**, which would let Java programs safely and efficiently **access foreign memory outside of the Java heap**. The API should be able to operate on **various kinds of foreign memory**, such as **native, persistent, and managed heap**. Many Java programs access foreign memory, such as **Ignite and MapDB**. The API would help **avoid the cost and unpredictability associated with garbage collection**, **share memory across processes**, and serialize and deserialize memory content by mapping files onto memory. This capability is going through an earlier incubator phase in JDK 14, with refinements offered in JDK 15. 

## Sealed classes

Along with interfaces, **sealed classes restrict which other classes or interfaces may extend or implement them**. Goals of this feature include **allowing the author of a class or interface to control which code is responsible for implementing it**, provide a more declarative way than access modifiers to **restrict the use of a superclass,** and support future directions in pattern matching by underpinning the exhaustive analysis of patterns.

## Records

**Records**, which are **classes that act as transparent carriers for immutable data**. Goals of the plan include **devising an object-oriented construct** that expresses a simple aggregation of values, helping <u>programmers focus on modeling immutable data</u> rather than extensible behavior, <u>automatically implementing data-driven methods such as equals and assessors</u>, and preserving longstanding Java principles such as **nominal typing** and **migration compatibility**. Records can be thought of as nominal tuples. Included in a second preview version in JDK 15, after debuting as an early preview in JDK 14

## New DatagramSocket API

**Reimplementing the legacy DatagramSocket API** by replacing the underlying implementations of the java.net.datagram.Socket and java.net.MulticastSocket APIs with simpler and more modern implementations that 1. are easy to debug and maintain and 2. work with virtual threads.

## Hidden classes

**Hidden classes**, i.e. classes that <u>cannot be used directly by the bytecode of other classes</u>, are intended for **use by frameworks that generate classes at runtime** and that use them indirectly through reflection. A hidden class can be defined as a member of an access control nest and can be unloaded independently of other classes. The proposal would **improve the efficiency of all languages on the JVM** by enabling a standard API to define hidden classes that are not discoverable and have a limited lifecycle. Frameworks inside and outside the JDK would be able to dynamically generate classes that could instead define hidden classes. <u>Many languages built on the JVM rely on dynamic class generation for flexibility and efficiency</u>. Goals of this proposal include: allowing frameworks to define classes as non-discoverable implementation details of the framework, so they cannot be linked against by other classes nor discovered through reflection; support for extending an access control nest with non-discoverable classes; and support for aggressive unloading of non-discoverable classes, so frameworks have the flexibility to define as many as needed.

## Z Garbage Collector (ZGC)

The **Z Garbage Collector (ZGC)**, Integrated into JDK 11, would graduate from an **experimental feature to a product** under this proposal. ZGC is a **scalable, low-latency garbage collector**.

## Shenandoah GC

The **Shenandoah low-pause-time garbage collector**, Integrated into JDK 12, would graduate from an **experimental feature to a product feature ** under this proposal.

## Nashorn

The OpenJDK 15 proposal calls for removing Nashorn APIs, **Nashorn** the **JavaScript engine for JVM**, which **debuted in JDK 8**, but has since been made obsolete by technologies such as **GraalVM**. It has boosted compatibility between Java and JavaScript while offering performance benefits.







Highlights include **flight recorder event streaming**, **switch expressions**, **NVM support**, and **records**.

The upgrade includes new capabilities such as JDK Flight Recorder event streaming, pattern matching, and switch expressions. 

## JFR Event Streaming

JFR ( JDK Flight Recorder) Event Streaming provides an API for the **continuous consumption of JFR data** from both in-process and out-of-process applications. JFR is a tool for **collecting profiling and diagnostic data** about a Java application and the JVM as they’re running. The event streaming proposal records the same set of events as for the non-streaming case, with overhead of less than one percent if possible. Event streaming must co-exist with non-streaming recordings, both disk-based and memory-based. Motivating this proposal is a situation in which the HotSpot VM emits more than 500 data points using JFR, most of them available only by parsing log files. Currently, a user must start a recording, stop it, dump the contents to disk, and then parse the recording file. This works well for application profiling, but not for monitoring purposes. An example of monitoring usage is a dashboard that displays dynamic updates to data. There is overhead with creating a recording, such as copying data from the disk repository to a separate recording file. If there were a way to read data being recorded from the disk repository without creating a new recording file, much of the overhead could be avoided.

## Improvement to NullPointerExceptions

The planned **improvement to NullPointerExceptions** pertains to improving the usability of the exceptions generated by the JVM by describing exactly which variable was null. Authors of the proposal are looking to provide helpful information to developers and support staff about the premature termination of a program and improve program understanding by more clearly associating a dynamic exception with static program code. One goal is to reduce confusion and concern developers have about NullPointerExceptions.

## Switch expressions

Switch expressions simplify coding by extending switch so that it can be used as either a statement or an expression. Switch expressions are expected to be a permanent feature in JDK 14, after being previewed in both JDK 12 and JDK 13. Switch expressions also prepare for the use of pattern matching in switch. Pattern matching allows developers to conditionally extract components from objects more concisely and safely. 

**NUMA-aware memory allocation** for the G1 garbage collector, intended to improve G1 performance on large machines. 

**Removal of the Concurrent Mark Sweep (CMS) garbage collector**, which previously was deprecated and slated for removal. Successors to CMS have arisen including ZGC and Shenandoah. 

Records, which would provide a compact syntax for declaring classes that are transparent holders for shallowly immutable data. Records make it easy to create classes that are essentially data carriers without having to write a lot of boilerplate. The proposal states it should be easy and concise to declare shallowly immutable, well-behaved, nominal data aggregates.
A packaging tool, in an incubator phase of development, for packaging self-contained Java applications. The tool would be based on the JavaFX javapackager. Such a tool had been included in Java but was cut from JDK 11 as part of the removal of JavaFX.
Enhance the language with pattern matching for the instanceof operator. This would be a preview feature in JDK 14. Pattern matching allows common logic in a program, principally the conditional extraction of components from objects, to be more concisely and safely expressed. Code can be made brief and type-safe.
A second preview of text blocks, a multi-line string literal that avoids the need for most escape sequences and automatically formats the string in a predictable way. Text blocks would give the developer control over the format when desired, simplify the writing of Java programs, and enhance the readability of strings. Text blocks was previewed in JDK 13; the JDK 14 iteration would add escape sequences for managing explicit white spaces and newline control.
Deprecating the combination of the Parallel Scavenge and Serial Old garbage collection algorithms. Java maintainers believe this combination is used very little but requires a lot of maintenance.
Porting of the ZGC (Z Garbage Collector) to Windows. This feature has once again moved to the officially targeted list, after having been reverted back to the proposed-for-targeting list.

Foreign-memory access API, with the introduction of an API for Java programs to safely and efficiently access foreign memory outside of the Java heap. This API should serve as an alternative to the main avenues by which Java programs access memory, including nio.ByteBuffer and sun.misc.Unsafe. The new API should be able to operate on various kinds of memory including native, persistent memory, and managed heap. It should not be possible for the API to undermine the safety of the JVM. Memory deallocation should be explicit in the source code. The API is expected to aid in the development of the native interoperation support that is the goal of Project Panama.



## Java 8 – Arrays Parallel Sort

Java 8 introduced a new method **parallelSort()** in the Arrays class to support the parallel sorting of array elements. 

Algorithm of parallel sorting:

1. The given array is divided into the sub arrays and the sub arrays are further divided into the their sub arrays, this happens until the <u>sub array reaches a **minimum granularity**</u>.
2. The sub arrays are <u>sorted individually by **multiple threads**</u>. The parallel sort uses **Fork/Join Framework** for sorting sub arrays parallelly.
3. The sorted sub arrays are merged.

**Advantage Over Simple Sort:** The parallelSort() method uses the concept of multithreading which makes it **much faster** compared to the normal sort when there are lot of elements.

## Java 8 - Functional Interfaces

Also known as **Single Abstract Method interfaces (SAM Interfaces)**.

**Rules** of defining a functional interface : The functional interface should have **Only one abstract method**. Along with the one abstract method, they can have any number of default and static methods.

While creating your own functional interface, mark it with <u>**@FunctionalInterface** annotation</u>, this annotation is introduced in Java 8. Although its optional, you should use it so that you <u>get a **compilation error** if the interface you marked is not following the rules</u> of functional interfaces.

**To use lambda expression** in Java, you need to either create <u>your own functional interface</u> or use the <u>pre defined functional interface</u> provided by Java. 

## Java 8 - Method References 

Method reference is a <u>**shorthand notation** of a lambda expression to call a method</u>. The **:: operator** is used in method reference to <u>separate the class or object from the method name</u>.

```java
str -> System.out.println(str)
//then you can replace it with a method reference like this:
System.out::println
```

**Four types of Method references**

1. Method reference to an **Instance method** of an object – object::instanceMethod

2. Method reference to a **Static method** of a class – Class::staticMethod

3. an instance method of an arbitrary object of a particular type – Class::instanceMethod

   1. ```java
      String[] strArray = { "Steve", "Rick", "Aditya", "Negan", "Lucy", "Sansa", "Jon"};
      //Method reference to an instance method of an arbitrary object of a particular type (String).
      Arrays.sort(stringArray, String::compareToIgnoreCase);
      ```

4. Method reference to a **constructor – Class::new**

## Java 8 - Optional Class

Java 8 introduced Optional class, to **avoid NullPointerException** that we frequently encounters if we do not perform null checks in our code. Using this class we can **easily check whether a variable has null value** or not and by doing this we can avoid the NullPointerException. **Optional.ofNullable()** method returns a **Non-empty Optional** if the given object has a value, otherwise it returns an **empty Optional**.
We can check whether the returned Optional value is empty or non-empty using the **isPresent()** method.

```java
String[] str = new String[10];     
Optional<String> isNull = Optional.ofNullable(str[9]);        
if(isNull.isPresent()){     
    //Getting the substring              
}     
else{      
    System.out.println("Cannot get the substring from an empty string");     
}
```








# Java Design Patterns

Design patterns are just convenient ways of **reusing object-oriented code**. The idea behind design patterns is simple -- `write down and catalog common interactions between objects` that programmers have frequently found useful. Design patterns describe `how objects communicate without become entangled in each other’s data models and methods`.  Keep **objects minding their own business**. Design patterns are not just about the design of objects, but also **the design of simple, but elegant, methods of communication** between objects.

 **Model-View- Controller framework** divided the user interface problem into three parts. The parts were referred to as a **data model** which contain the computational parts of the program, the **view**, which presented the user interface, and the **controller**, which interacted between the user and the view. Each of these parts is a separate object and each has its own rules for managing its data. Three objects `talking to each other using this restrained set of connections` is an example of a powerful design pattern.

The fundamental reason for using varies design patterns is to keep classes separated and prevent them from having to know too much about one another. There are a number of strategies that OO programmers use to achieve this separation, among them **encapsulation** and **inheritance**. Design Patterns suggests that you always Program to an interface and not to an implementation. The other major concept you should recognize is that of **object composition**. This is simply the construction of objects that contain others: encapsulation of several objects inside another one. Design Patterns favors object composition over inheritance. The new object can have the interface that is best for what you want to accomplish without having all the methods of the parent classes.

The fundamental reason for using varies design patterns is to `keep classes separated and prevent them from having to know too much about one another`. Few strategies that we used to achieve this separation are **encapsulation** and **inheritance**. Design Patterns also suggests that you always Program to an interface and not to an implementation. Another major concept is **object composition**. This is simply the construction of objects that contain others: encapsulation of several objects inside another one. `Design Patterns favors object composition over inheritance`. The new object can have the interface that is best for what you want to accomplish without having all the methods of the parent classes. 

------

Design patterns are divided into three types **creational, structural and behavioral**.

➢  **Creational patterns** `create objects` for you, rather than having you instantiate objects directly. This gives your program more flexibility in deciding which objects need to be created for a given case.

➢  Structural patterns help you `compose groups of objects into larger structures`, such as complex user interfaces or accounting data.

➢  Behavioral patterns help you define the `communication between objects` in your system and how the flow is controlled in a complex program.

------

------

# Creational Patterns

Creational patterns deal with the **best way to create instances of objects** because your **program should not depend on how objects are created and arranged**. In many cases, the exact nature of the object that is created could vary with the needs of the program and **abstracting the creation process** into a special “creator” class can make your program more flexible and general.

- The **Factory Method** provides a simple decision making class that `return an instance of a several possible subclasses of an abstract base class depending on the data that are provided`.

- The **Abstract Factory Method** provides an interface to `create and return one of several families of related objects`.

- The **Builder Pattern** `separates the construction of a complex object` from its representation, so that several different representations can be created depending on the needs of the program.

- The **Prototype Pattern** starts with an initialized and instantiated class and `copies or clones it to make new instances rather than creating new instances`.

- The **Singleton Pattern** is a class of which there can be `no more than one instance`. It provides a single global point of access to that instance.


**SUMMARY OF CREATIONAL PATTERNS**

- The Factory Pattern is used to choose and return an instance of a class from a number of similar classes based on data you provide to the factory.

- The Abstract Factory Pattern is used to return one of several groups of classes. In some cases it actually returns a Factory for that group of classes.

- The Builder Pattern assembles a number of objects to make a new object, based on the data with which it is presented. Frequently, the choice of which way the objects are assembled is achieved using a Factory.

- The Prototype Pattern copies or clones an existing class rather than creating a new instance when creating new instances is more expensive.

- The Singleton Pattern is a pattern that insures there is one and only one instance of an object, and that it is possible to obtain global access to that one instance.

------

------

### THE FACTORY PATTERN 

A Factory pattern is one that returns an instance of one of several possible classes depending on the data provided to it. Usually all of the classes it returns have a common parent class and common methods, but each of them performs a task differently and is optimized for different kinds of data.

The Factory class decides which of these subclasses to return depending on the arguments you give it. Which one it returns doesn't matter to the programmer since they all have the same methods, but different implementations. How it decides which one to return is entirely up to the factory. 

The fundamental principle of Factory patterns is you create an abstraction which decides which of several possible classes to return and returns one. Then you call the methods of that class instance without ever knowing which derived class you are actually using. This approach keeps the issues of data dependence separated from the classes’ useful methods. 

**When to Use a Factory Pattern**

➢  A class can’t anticipate which kind of class of objects it must create.

➢  A class uses its subclasses to specify which objects it creates.

➢  You want to localize the knowledge of which class gets created.

------

------

### THE ABSTRACT FACTORY PATTERN

The Abstract Factory pattern is one level of abstraction higher than the factory pattern. You can use this pattern when you want to return one of several related classes of objects, each of which can return several different objects on request. In other words, the Abstract Factory is a factory object that returns one of several factories.

One classic application of the abstract factory is the case where your system needs to support multiple “look-and-feel” user interfaces, such as Windows-9x, Motif or Macintosh. You tell the factory that you want your program to look like Windows and it returns a GUI factory which returns Windows-like objects. Then when you request specific objects such as buttons, check boxes and windows, the GUI factory returns Windows instances of these UI components.

**Consequences of Abstract Factory**

One of the main purposes of the Abstract Factory is that it isolates the concrete classes that are generated. The actual class names of these classes are hidden in the factory and need not be known at the client level at all. While all of the classes that the Abstract Factory generates have the same base class, there is nothing to prevent some derived classes from having additional methods that differ from the methods of other classes. This presents a problem because the client doesn't know whether it can call a class method unless you know the derived class is one that allows those methods. This problem has two solutions : you can either define all of the methods in the base class, even if they don’t always have a actual function, or you can test to see which kind of class you have (using instance of).

------

------

### THE SINGLETON PATTERN

 The Singleton pattern is a case where you need to make sure that there can be **one and only one instance** of a class. For example, your system can have only one window manager or a **single point of access to a database engine**.

**Creating Singleton Using a Static Method**

The easiest way is using a static method to issue and keep track of instances. To prevent instantiating the class more than once, we make the constructor private so an instance can only be created from within the static method of the class. 

**Static Classes as Singleton Patterns**

There already is a kind of Singleton class in the standard Java class libraries: the **Math class**. This is a class that is **declared final and all methods are declared static**. The purpose of the Math class is to wrap a number of common mathematical functions such as sin and log in a class-like structure, since the Java language does not support functions that are not methods in a class. You can use the same approach to a Singleton pattern, making it a final class. You can’t create any instance of classes like Math, and can only call the static methods directly in the existing final class. 

The **disadvantage of final class approach** is that if you would like to drop the restrictions of Singleton status, this is easier to do it in the static method approach. But in static approach we’d have a lot of reprogramming to do to make it allow multiple instances. 

------

------

### THE BUILDER PATTERN

This is an **extension of Factory pattern**, because here we aren’t returning objects which are simple descendants of a base class. The Builder Pattern **assembles a number of objects in various ways depending on the data**. The main difference is that while the Abstract Factory returns a family of related classes (using data decide **which class** to return), the **Builder constructs a complex object step by step depending on the data** presented to it (decide **how to** make using the data). Some cases we **use builder class inside a factory class** to return a complex object. Here factory decide which builder to call and builder decide how to make a complex object using the data.

For example We can use our Builder class to generate a UI that vary depends on the number of items to be displayed, and yet have the same methods for returning the results (getUI()).

**Consequences of the Builder Pattern**

➢  A Builder lets you vary the internal representation of the product it builds. It also **hides the details of how the product is assembled**.

➢  Each specific builder is independent of the others and of the rest of the program. This **improves modularity** and makes the **addition of other builders relatively simple**.

➢  Because each builder constructs the final product step-by-step, depending on the data, you have **more control over each final product that a Builder constructs**. 

------

------

### THE PROTOTYPE PATTERN

The Prototype pattern is used **when creating an instance of a class is very time-consuming or complex** in some way. Then, rather than creating more instances, you make **copies of the original instance**, modifying them as appropriate. Prototypes can also be used whenever you **need classes that differ only in the type of processing** they offer. We clone the data because creating a new class instance would be much slower, and we want to keep the data in both forms.

In Java the clone method is a **shallow copy** of the original class. In other words, the references to the data objects are copies, but they refer to the same underlying data. Thus, any operation we perform on the copied data will also occur on the original data in the Prototype class. If you want to make a **deep copy** of the data, use the serializable interface. A class is said to be serializable if you can write it out as a stream of bytes and read those bytes back in to reconstruct the class. This is how Java **remote method invocation (RMI)** is implemented.

**Consequences of the Prototype Pattern**

Using the Prototype pattern, you can add and remove classes at run time by cloning them as needed. You can revise the internal data representation of a class at run time based on program conditions. You can also specify new objects at run time without creating a proliferation of classes and inheritance structures.

The idea of having prototype classes to copy implies that you have **sufficient access to the data or methods** in these classes to change them after cloning. This may **require adding data access methods** to these prototype classes so that you can modify the data once you have cloned the class. One difficulty in implementing the Prototype pattern in Java is that if the classes already exist, you may not be able to change them to **add the required clone or deepClone methods**. The deepClone method can be particularly difficult if all of the class objects contained in a class cannot be declared to implement Serializable. In addition, **classes that have circular references to other classes cannot really be cloned**. 

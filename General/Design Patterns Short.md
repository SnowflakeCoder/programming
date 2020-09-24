# Design Patterns

- convenient ways of **reusing object-oriented code** + objects minding their own business
- how objects communicate without become entangled in each other’s data models and methods.
- keep **classes separated** and prevent them from having to **know too much** about one another.
- Program to an interface and not to an implementation.

Design Patterns `favors object composition over inheritance`.**Object composition** is simply the construction of objects that contain others: **encapsulation** of several objects inside another one.

## Creational patterns

- gives more flexibility in `deciding which objects need to be created` for a given case.
- program should not depend on how objects are created and arranged + `abstracting the creation process` + make your program `more flexible and general`.

### Factory Pattern

- provides a simple decision making class that `return an instance of a several possible subclasses of an abstract base class depending on the data that are provided`.
- decides which of these subclasses to return `depending on the arguments` you give it.
- create an abstraction which decides which of several possible classes to return and returns one.
- hide the implementation logic and makes `client code focus on usage rather then initialization` new objects.
- **keeping all objects creation in one place** and avoid of spreading 'new' key across codebase.
- Use case : Create a post using different fields (contains image/video/text etc). 

### Abstract Factory Pattern

- one level of abstraction higher than the factory pattern.
- provides an interface to `create and return one of several families of related objects`. In some cases it returns a Factory for that group of classes
- use cases: returns **GUI factory** => UI tools for different OS + render a post using different templates based on mobile types and OS + system needs to support multiple “look-and-feel” UI for each OS.
- **Consequences** : Creates a **lot of class hierarchy**(inheritance) which is difficult to maintain. issues like client cannot use some specific methods available in the sub classes. Two solutions: 
  - define all the methods in the base class, even if they don’t always have a actual function.
  - see which kind of class you have (using instance of) 

### Builder Pattern

- `separates the construction of a complex object` from its representation.

- Each builder is independent of the others and of the rest of the program. This **improves modularity** and makes the **addition of other builders relatively simple**.

- `assembles a number of objects step by step` to make a new object, based on the data provided. So program will have **more control over final product** that a Builder constructs. Frequently, the choice of which way the objects are assembled is achieved using a Factory.

- use case : creates a complex object using inputs coming from various places and times.

  a `character generator for a game`, let the computer create the character from user inputs. 

  Generate a UI that vary depends on the number of items to be displayed.

### Prototype Pattern

- Specify the kinds of objects to create using a **prototypical instance**, and create new objects by copying this prototype.
- starts with an **initialized and instantiated class** and `copies or clones it to make new instances rather than creating new instances`.
- used **when creating an instance of a class is very time-consuming or complex or more expensive compared to cloning**.
- When the classes to instantiate are **specified at run-time**, for example, by **dynamic loading**.
- When instances of a class can have one of only a **few different combinations of state**. It may be more convenient to install a corresponding **number of prototypes** and clone them rather than instantiating the class manually, each time with the appropriate state.

### Singleton Pattern

- a class of which there can be `no more than one instance` and provides a `single global point of access` to that instance.

- Use case: your system can have only one window manager or a **single point of access to a database engine**.

- Creating Singleton **Using a Static Method**: make the `constructor private` to prevent instantiating the class more than once. (Eager / Lazy initialization)

- **Static Classes** as Singleton Patterns : Like **java.lang.Math** class + `declared final and all methods are declared static`

  - **disadvantage** of static final class approach : if you would like to drop the restrictions of Singleton status, this is easier to do it in the static method approach, but in static approach we need more reprogramming to `make it allow multiple instances`.

- `Bill Pugh Singleton Implementation` - using **inner static class**.

  - When the singleton class is loaded, inner class is not loaded and hence `doesn’t create object when loading the class`. Inner class is created only when getInstance() method is called. So it is **lazy initialization** and doesn’t use synchronization.

  - ```java
    public class GFG  
    { 
      private GFG()  
      {     // private constructor   } 
      // Inner class to provide instance of class 
      private static class BillPughSingleton 
      {     private static final GFG INSTANCE = new GFG();   } 
      public static GFG getInstance()  
      {     return BillPughSingleton.INSTANCE;   } 
    }
    ```

    

## Structural patterns

- how `classes and objects can be combined` to form larger structures, such as complex user interfaces or accounting data.
- **class patterns**: how `inheritance` can be used. **Object patterns**: how objects can be composed into larger structures using `object composition`.

### Adapter pattern

- is used to `change the interface of one class to that of another one`, to `make one class interface match another` to make programming easier
- use adapters whenever we `want unrelated classes to work together` in a single program.
- **Implementation** : write a class that has the desired interface and then `make it communicate with the class that has a different interface`.
- `using inheritance / Class adapter` : derive a new class from the nonconforming one and <u>add the methods to make the new derived class match the desired interface</u>.
  - Won’t work if we want to <u>adapt a class and all of its subclasses</u>, since you define the class it derives from when you create it
  - `Two Way Adapters`: allows an object to be viewed by different classes. But shouldn't have any methods in common / you do not override any of the base class’s methods.
- `using object composition / Object adapter`: include the original class inside the new one and create the methods to translate calls within the new class
- **Pluggable Adapters** : adapts dynamically to one of several classes. adapter decides which class it is adapting based on differing constructors or setParameter methods.

### Bridge pattern

- **separates interface from its implementation**, so you can `vary them separately`. 
- The Bridge pattern intended to `keep the interface to your client program constant` while allowing you to change the underlying class (implementation class).
- Implementation wise bridge pattern looks **much like the Adapter pattern**
  - Adapter pattern : make one classes’ interfaces look the same as that of a particular class. 
  - Bridge pattern : separate a class’s interface from its implementation, so that you can vary or `replace the implementation without changing the client code`.

### Façade pattern

- is used to make a **single class represent an entire subsystem**. 
- The Façade pattern hides a **complex object hierarchy** and provides a new, simpler interface to access those data.
- allows you to make changes in the underlying subsystems without changing the client code
- **Use cases** : Notification services / any SOA.

### Composite pattern

- is a **composition of objects**, each of which may be either simple or itself a composite object (collection of objects), designed to accommodate both cases.
- construct data representations of trees, distinguish between **nodes and leaves**.
- Use case: Post & Sub Posts + employee and Manager.

### Proxy pattern

- a simple object that **takes the place of a more complex object** that may be invoked later, for example when the program runs in a network environment. 
- provides a **simple place-holder class** for a more complex class which is expensive to instantiate.
- allows you to **postpone the creation** of a  complex object until you need the actual object.
- once the object is loaded, it passes on the method calls from the Proxy to the actual object.
- Use case : a large image/object on a remote machine, takes **time to load** over the network or object has **limited access rights**, the proxy can validate the access permissions for that user.
- **Copy-on-Write** : proxies can keep copies of large objects that may or may not change. If you create a second instance, a Proxy can <u>simply uses the original object</u> till program **makes a write in the new copy**, then the Proxy can create the new instance. This is useful <u>when objects do not always change after they are instantiated</u>.
- Adapter provides a different interface for an object, while the Proxy provides the same interface for the object. A Decorator also has the same interface as the object, but its `purpose is to add additional function to the original object`. A proxy, by contrast, **controls access** to the contained class.

### Decorator pattern

- a **wrapper class** that used to **add new responsibilities/capabilities to objects dynamically**.
- provides a way to modify the behavior of individual objects **without having to create a new derived class** - `object composition is favored over object inheritance`.
- The decorator `contains the object` it is decorating. It `may intercept some method calls`, <u>perform some additional computation</u> and then may pass them on to the underlying object.
- **FilterInputStream** class is a Decorator that can be wrapped around any **inputstream** class. an abstract class that doesn’t do any processing, but relevant methods have been duplicated.
- **BufferedInputStream** :adds <u>buffering to stream</u> so **every call does not cause I/O** to occur.

### Flyweight pattern

- a pattern **for sharing objects**, where each instance **does not contain its own state, but stores it externally**. 
- This allows efficient `sharing of objects to save space`, when there are **many instances, but only a few different types**. 
- provides a way to limit the **proliferation** (rapid increase in number) of small, similar class instances.
- If you can recognize that the `instances are fundamentally the same` except for a few parameters and if you can `move those variables outside the class instance` and pass them in as part of a method call, the number of separate instances can be greatly reduced.
- FlyweightFactory class decides the number of unique instances that are allocated. This class usually is a **Singleton**, since it needs to `keep track of whether to returns a new instance` or a reference to one it has already generated.
- <u>Use case</u> :appropriate for fine-grained classes like individual characters or icons on the screen.

## Behavioral patterns

- help you define the `communication between objects` in your system and how the flow is controlled in a complex program.

- The **Observer pattern** defines the way a number of classes can be **notified of a change**.
- The **Mediator** defines how **communication between classes can be simplified** by using another class to keep all classes from having to know about each other.
- The **Chain of Responsibility** allows an even further **decoupling between classes**, by **passing a request** between classes until it is recognized.

### Template pattern

- formalizes the idea of <u>defining an algorithm in a class</u>, but leaving `some of the details to be implemented in subclasses`.
- provides an **abstract definition** of an algorithm, some parts of an algorithm are well defined and can be implemented in the base class, while `other parts may have several implementations` and are best left to derived classes.

A Template has four kinds of methods:

- **Concrete** methods : Complete methods defined in base class that subclasses want to use.
- Abstract Methods : added in base class, must be implemented in derived classes.
- **Hook methods** : Methods contain a default implementation, may be overridden in derived classes. <u>Hook methods are intended to be overridden, while Concrete methods are not</u>.
- **Template methods**: describe an algorithm(template) structure without actually implementing its details. A Template class will call any combination of abstract, hook and concrete methods. These methods are <u>not intended to be overridden</u>.

### Interpreter

- The **Interpreter** provides a definition of how to **include language elements** in a program.

### Strategy pattern

- consists of a <u>number of related algorithms</u> encapsulated in a driver class called the **Context**.
- client program can `select one of these algorithms` or in some cases the Context might select the best one for you and then asking it to carry out the operation.
- Use cases: Save files in different formats, Compress files using different algorithms, <u>Plot the same data in different formats</u>: line graph, bar chart or pie chart.

#### State Vs Strategy

- User generally <u>chooses which of several strategies to apply</u> and that only one strategy at a time is likely to be instantiated and active within the Context class. By contrast, it is likely that **all of the different States will be active at once** and switching may occur frequently between them. 
- Strategy encapsulates several algorithms that do more or less the same thing, while State encapsulates related classes that **each do something somewhat different**. 
- Concept of <u>transition between different states</u> is completely missing in the Strategy pattern.

### Visitor pattern

- The **Visitor pattern** adds function to a class.
- The **State pattern** provides a memory for a class’s instance variables.
- The **Command pattern** provides a simple way to **separate execution of a command** from the interface environment that produced it.
- The **Iterator pattern** formalizes the way we **move through a list of data** within a class. 






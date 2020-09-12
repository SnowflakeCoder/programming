# Structural Design Patterns

Structural patterns describe **how classes and objects can be combined to form larger structures**. The difference between **class patterns** and **object patterns** is that class patterns describe how inheritance can be used to provide more useful program interfaces. Object patterns, on the other hand, describe how objects can be composed into larger structures using object composition, or the inclusion of objects within other objects.

- **Adapter pattern** is used to **change the interface of one class to that of another one**, to make **one class interface match another** to make programming easier
- **Composite pattern** is a **composition of objects**, each of which may be either simple or itself a composite object.
- **Proxy pattern** is a simple object that **takes the place of a more complex object** that may be invoked later, for example when the program runs in a network environment. The **Proxy pattern** provides a **simple place-holder class for a more complex class** which is expensive to instantiate.
- **Flyweight pattern** is a pattern **for sharing objects**, where each instance **does not contain its own state, but stores it externally**. This allows efficient sharing of objects to save space, when there are **many instances, but only a few different types**. The **Flyweight pattern** provides a way to limit the proliferation of small, similar class instances by **moving some of the class data outside the class** and passing it in during various execution methods.
- **Façade pattern** is used to make a **single class represent an entire subsystem**. The **Façade pattern** groups a complex object hierarchy and provides a new, simpler interface to access those data.
- **Bridge pattern** separates an object’s interface from its implementation, so you can vary them separately. The **Bridge pattern** intended to **keep the interface to your client program constant** while allowing you to change the underlying class (implementation class). 
- **Decorator pattern** can be used to **add responsibilities to objects dynamically**. The **Decorator pattern**, a class that **surrounds a given class (wrapper class), adds new capabilities** to it, and passes all the unchanged methods to the underlying class. 

------

------

## THE ADAPTER PATTERN 

The Adapter pattern is used to convert the programming interface of one class into that of another. We use adapters whenever we want unrelated classes to work together in a single program. The concept of an adapter is thus pretty simple; we **write a class that has the desired interface** and then make it communicate with the class that has a different interface.

 

There are two ways to do this: by **inheritance**, and by **object composition**. **In both case we write a class that implements the desired interface**. In the first case, we derive a new class from the nonconforming one and add the methods we need to make the new derived class match the desired interface. The other way is to include the original class inside the new one and create the methods to translate calls within the new class. These two approaches, termed **class adapters** and **object adapters** are both fairly easy to implement in Java. In class based adapter some of the methods refer to the enclosing class instead of an encapsulated class.

 

There are also some differences between the class and the object adapter approaches.

 

**The Class adapter**

➢  Won’t work when we want to adapt a class and all of its subclasses, since you define the class it derives from when you create it.

➢  Lets the adapter change some of the adapted class’s methods but still allows the others to be used unchanged.

**An Object adapter**

➢  Could allow subclasses to be adapted by simply passing them in as part of a constructor.

➢  Requires that you specifically bring any of the adapted object’s methods to the surface that you wish to make available.

 

**Two Way Adapters**

 

The two-way adapter is a clever concept that allows an object to be viewed by different classes. This is most easily carried out using a **class adapter**, since all of the methods of the base class are automatically available to the derived class. However, this can only work if you do not override any of the base class’s methods with ones that behave differently. For an ideal two-way adapter the two classes shouldn't have any methods in common.

 

**Pluggable Adapters**

 

A pluggable adapter is one that adapts dynamically to one of several classes. Of course, the adapter can only adapt to classes it can recognize, and usually the adapter decides which class it is adapting based on differing constructors or setParameter methods.

 

Java has yet another way for adapters to recognize which of several classes it must adapt to: reflection. You can use reflection to discover the names of public methods and their parameters for any class. For any arbitrary object you can use the **getClass()** method to obtain its class and the **getMethods()** method to obtain an array of the method names and then it is easier if you know the name of the method you are looking for and simply want to find out which arguments that method requires. From that method signature, you can then deduce the adapting you need to carry out. However, since Java is a strongly typed language, it is more likely that you would simply invoke the adapter using one of several constructors, where each constructor is tailored for a specific class that needs adapting.

------

------

## THE BRIDGE PATTERN

 

The Bridge pattern is used to separate the interface of a class from its implementation, so that either can be varied separately. At first sight, the bridge pattern looks much like the Adapter pattern, in that a class is used to convert one kind of interface to another. However, the intent of the Adapter pattern is to make one or more classes’ interfaces look the same as that of a particular class. The Bridge pattern is designed to **separate a class’s interface from its implementation**, so that you can vary or replace the implementation without changing the client code.

 

**Example**

Suppose that we have a program that displays a list of products and we need to produce two kinds of displays from our product data, a customer view that is just the list of products (list view class), and an executive view which also shows the number of units shipped (table view class).

 

Now suppose that we need to make some changes in the way these lists display the data. For example, you might want to have the products displayed in alphabetical order. In order to continue with this approach, you’d need to either modify or subclass both of these display classes. This can quickly get to be a maintenance nightmare, especially if more than two such displays eventually are needed. So rather than deriving new classes whenever we need to change these displays further, let’s build a single bridge that does this work for us. We want the bridge class to return an appropriate visual component from our product data.

 

When we design a bridge class, we have to decide how the bridge will determine which of the several classes it is to instantiate. It could decide based on the values or quantity of data to be displayed, or it could decide based on some simple constants.

 

**Consequences of the Bridge Pattern**

➢  The Bridge pattern is intended to keep the interface to your client program constant while allowing you to change the actual kind of class you display or use. This can prevent you from recompiling a complicated set of user interface modules, and only require that you recompile the bridge itself and the actual end display class.

➢  You can extend the implementation class and the bridge class separately, and usually without much interaction with each other.

➢  You can hide implementation details from the client program much more easily.  

------

------

## THE FAÇADE PATTERN

 

In design pattern terminology, the Façade is a way of hiding a complex system inside a simpler interface, while Decorator adds function by wrapping a class. The Façade pattern allows you to simplify the complexity by providing a simplified interface to the subsystems. This simplification may in some cases reduce the flexibility of the underlying classes, but usually provides all the function needed for all but the most sophisticated users. These users can still, of course, access the underlying classes and methods.

 

**Consequences of the Façade**

The Façade pattern shields clients from complex subsystem components and provides a simpler programming interface for the general user. However, it does not prevent the advanced user from going to the deeper, more complex classes when necessary. In addition, the Façade allows you to make changes in the underlying subsystems without requiring changes in the client code, and reduces compilation dependencies.

------

------

## THE COMPOSITE PATTERN

A component can be an individual object or it may represent a collection of objects. The Composite pattern is designed to accommodate both cases. You can use the Composite to build part-whole hierarchies or to construct data representations of trees. In summary, a composite is a collection of objects, any one of which may be either a composite, or just a primitive object.

 

Design Patterns suggests that each element should have the same interface, whether it is a composite or a primitive element. The problem is to have a single, simple interface to access all the objects in a composite, and the ability to distinguish between nodes and leaves. For example, every node or leaf can return an Enumeration of the children. If there are no children, the hasMoreElements() method returns false at once.

 

**Consequences of the Composite Pattern**

The Composite pattern allows you to define a class hierarchy of simple objects and more complex composite objects so that they appear to be the same to the client program. Because of this simplicity, the client can be that much simpler, since nodes and leaves are handled in the same way. The Composite pattern also makes it easy for you to add new kinds of components to your collection, as long as they support a similar programming interface. On the other hand, this has the disadvantage of making your system overly general. You might find it harder to restrict certain classes, where this would normally be desirable. For example, Nodes have children and can have children added to them, while leaves do not have children, and in some implementations may be prevented from having children added to them.

 

The composite is essentially a singly-linked tree, in which any of the objects may themselves be additional composites. Normally, these objects do not remember their parents and only know their children. However, it is perfectly possible for any composite element to have its parent also. This simplifies searching for particular members and moving up the tree when needed.

------

------

## THE PROXY PATTERN

The Proxy pattern is used when you need to represent a complex object by a simpler one. If creating an object is expensive in time or computer resources, Proxy allows you to postpone this creation until you need the actual object. A Proxy usually has the same methods as the object it represents, and once the object is loaded, it passes on the method calls from the Proxy to the actual object. There are several cases where a Proxy can be useful:

➢  If an object, such as a large image, takes a long time to load.

➢  If the object is on a remote machine and loading it over the network may be slow, especially during peak network load periods.

➢  If the object has limited access rights, the proxy can validate the access permissions for that user.

 

Proxies can also be used to distinguish between requesting an instance of an object and the actual need to access it. For example, program initialization may set up a number of objects which may not all be used right away. In that case, the proxy can load the real object only when it is needed. Let’s consider the case of a large image that a program needs to load and display. When the program starts, there must be some indication that an image is to be displayed so that the screen lays out correctly, but the actual image display can be postponed until the image is completely loaded. An image proxy can note the image and begin loading it in the background, while drawing a simple rectangle or other symbol to represent the image’s extent on the screen before it appears. The proxy can even delay loading the image at all until it receives a paint request, and only then begin the process.

 

**Copy-on-Write**

You can also use proxies to keep copies of large objects that may or may not change. If you create a second instance of an expensive object, a Proxy can decide there is no reason to make a copy yet. It simply uses the original object. Then, if the program makes a change in the new copy, the Proxy can copy the original object and make the change in the new instance. This can be a great time and space saver when objects do not always change after they are instantiated.

 

**Comparison with Related Patterns**

Both the Adapter and the Proxy constitute a thin layer around an object. However, Adapter provides a different interface for an object, while the Proxy provides the same interface for the object, but interposes itself where it can save processing effort. A Decorator also has the same interface as the object it surrounds, but its purpose is to add additional function to the original object. A proxy, by contrast, controls access to the contained class.

------

------

## THE DECORATOR PATTERN

The Decorator pattern provides us with a way to modify the behavior of individual objects **without having to create a new derived class**. For example, we wanted to draw a special border around a button. So we create a Decorator class that decorates the buttons. Then we derive any number of specific Decorators from the main Decorator class, each of which performs a specific kind of decoration.

 

In order to decorate a button, the Decorator has to receive the paint method calls and forward calls to other useful graphic methods to the object that it is decorating. This is another case where object containment is favored over object inheritance. The decorator contains the object it is decorating. It may intercept some method calls, perform some additional computation and then may pass them on to the underlying object it is decorating.

 

The class **FilterInputStream** itself simply overrides all methods of InputStream with versions that pass all requests to the underlying input stream. Subclasses of FilterInputStream may further override some of these methods as well as provide additional methods and fields. The FilterInputStream class is thus a Decorator that can be wrapped around any input stream class. It is essentially an abstract class that doesn’t do any processing, but provides a layer where the relevant methods have been duplicated. It normally forwards these method calls to the enclosed parent stream class. The classes derived from FilterInputStream include

➢  **BufferedInputStream** : Adds buffering to stream so that every call does not cause I/O to occur.

➢  **CheckedInputStream** : Maintains a checksum of bytes as they are read.

➢  **DataInputStream** : Reads primitive types (Long, Boolean, Float, etc.) from the input stream.

➢  **DigestInputStream** : Computes a MessageDigest of any input stream.

➢  **InflaterInputStream** : Implements methods for uncompressing data.

➢  **PushbackInputStream** Provides a buffer where data can be “unread,” if during parsing you discover you need to back up.

 

These **decorators can be nested**, so that a pushback, buffered input stream is quite possible.

 

**Decorators, Adapters and Composites**

Adapters also seem to “decorate” an existing class. However, their function is to change the interface of one or more classes to one that is more convenient for a particular program. Decorators add methods to particular instances of classes, rather than to all of them. You could also imagine that a composite consisting of a single item is essentially a decorator. Once again, however, the intent is different

 

**Consequences of the Decorator Pattern**

The Decorator pattern provides a more flexible way to add responsibilities to a class than by using inheritance, since it can add these responsibilities to selected instances of the class. It also allows you to customize a class without creating subclasses high in the inheritance hierarchy. Design Patterns points out **two disadvantages** of the Decorator pattern.

➢  A Decorator and its enclosed component are not identical. Thus tests for object type will fail.

➢  Decorators can lead to a system with “lots of little objects” that all look alike to the programmer trying to maintain the code. This can be a maintenance headache.

------

------

## THE FLYWEIGHT PATTERN

There are cases in programming where you need to generate a very large number of small class instances to represent data. Sometimes you can greatly reduce the number of different classes that you need to instantiate if you can recognize that the instances are fundamentally the same except for a few parameters. If you can move those variables outside the class instance and pass them in as part of a method call, the number of separate instances can be greatly reduced.

 

The Flyweight design pattern provides an approach for handling such classes. It refers to the instance’s **intrinsic data** that makes the instance unique, and the **extrinsic data** which is passed in as arguments. The Flyweight is appropriate for small, fine-grained classes like individual characters or icons on the screen.

 

Flyweights are sharable instances of a class. The number of instances that are allocated must be decided as the class instances are needed, and this is usually accomplished with a FlyweightFactory class. This factory class usually is a Singleton, since it needs to keep track of whether or not a particular instance has been generated yet. It then either returns a new instance or a reference to one it has already generated. To decide if some part of your program is a candidate for using Flyweights, consider whether it is possible to remove some data from the class and make it extrinsic. If this makes it possible to reduce greatly the number of different class instances your program needs to maintain, this might be a case where Flyweights will help.

 

For example, if you are drawing a series of icons on the screen in a folder window, where each represents a person or data file, it does not make sense to have an individual class instance for each of them that remembers the person’s name and the icon’s screen position. Typically these icons are one of a few similar images and the position where they are drawn is calculated dynamically based on the window’s size in any case.

 

In another example in Design Patterns, each character in a font is represented as a single instance of a character class, but the positions where the characters are drawn on the screen are kept as external data so that there needs to be only one instance of each character, rather than one for each appearance of that character.

 

**Sharable Objects**

 

Sharable objects are much like Flyweights, although the purpose is somewhat different. When you have a very large object containing a lot of complex data, such as tables or bitmaps, you would want to minimize the number of instances of that object. Instead, in such cases, you’d return one instance to every part of the program that asked for it and avoid creating other instances.

 

A problem with such sharable objects occurs when one part of a program wants to change some data in a shared object. You then must decide whether to change the object for all users, prevent any change, or create a new instance with the changed data. If you change the object for every instance, you may have to notify them that the object has changed.

 

Sharable objects are also useful when you are referring to large data systems outside of Java, such as databases. The Database class could be a candidate for a sharable object. We might not want a number of separate connections to the database from different program modules, preferring that only one be instantiated. However, should several modules in different threads decide to make queries simultaneously, the Database class might have to queue the queries or spawn extra connections.


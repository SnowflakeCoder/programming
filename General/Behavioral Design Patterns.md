# Behavioral Design Patterns

Behavioral patterns are patterns that are specifically concerned with **communication between objects**.

- The **Observer pattern** defines the way a number of classes can be **notified of a change**.

- The **Mediator** defines how **communication between classes can be simplified** by using another class to keep all classes from having to know about each other.

- The **Chain of Responsibility** allows an even further **decoupling between classes**, by **passing a request** between classes until it is recognized.

- The **Template pattern** provides an **abstract definition** of an algorithm.

- The **Interpreter** provides a definition of how to **include language elements** in a program.

- The **Strategy pattern** encapsulates an algorithm inside a class.

- The **Visitor pattern** adds function to a class.

- The **State pattern** provides a memory for a class’s instance variables.

- The **Command pattern** provides a simple way to **separate execution of a command** from the interface environment that produced it.

- The **Iterator pattern** formalizes the way we **move through a list of data** within a class. 


------

------

## CHAIN OF RESPONSIBILITY

 

The Chain of Responsibility pattern allows a number of classes to attempt to handle a request, without any of them knowing about the capabilities of the other classes. It provides a loose coupling between these classes; the only common link is the request that is passed between them. The request is passed along until one of the classes can handle it.

 

The request or message passed along the Chain of Responsibility may include various data types or a complete object with a number of methods. Since various classes along the chain may use different properties of such a request object, you might end up designing an abstract Request type and any number of derived classes with additional methods.

 

There are two significant points we can observe:

➢  The chain is organized from most specific to most general

➢  There is no guarantee that the request will produce a response in all cases (none of the classes can handle the request).

 

We use the Chain of Responsibility when

➢  You have more than one handler that can handle a request and there is no way to know which handler to use. The handler must be determined automatically by the chain.

➢  You want to issue a request to one of several objects without specifying which one explicitly.

➢  You want to be able to modify the set of objects dynamically that can handle requests.

 

**A Chain or a Tree?**

 

A Chain of Responsibility does not have to be linear. It can be a tree structure with a number of specific entry points all pointing upward to the most general node. However, this sort of structure seems to imply that each handler knows where to enter the chain. This can complicate the design in some cases, and may preclude the need for the chain at all. Another way of handling a tree-like structure is to have a single entry point that branches to the specific handle (button, menu or other widget types) , and then “un-branches” as above to more general help cases. There is little reason for that complexity -- you could align the classes into a single chain, starting at the bottom, and going left to right and up a row at a time until the entire system had been traversed, as shown below.

 

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image002.jpg)**Examples : Class Inheritance in Java**

 

The most obvious example of the Chain of Responsibility is the class inheritance structure itself. If you call for a method to be executed in a deeply derived class, that method is passed up the inheritance chain until the first parent class containing that method is found. The fact that further parents contain other implementations of that method does not come into play.

 

**Consequences of the Chain of Responsibility**

 

➢  The main purpose for this pattern, like a number of others, is to reduce coupling between objects. An object only needs to know how to forward the request to other objects.

➢  This approach also gives you added flexibility in distributing responsibilities between objects. Any object can satisfy some or all of the requests, and you can change both the chain and the responsibilities at run time.

➢  An advantage is that there may not be any object that can handle the request, however, the last object in the chain may simply discard any requests it can’t handle.

➢  Finally, since Java can not provide multiple inheritance, the basic Chain class needs to be an interface rather than an abstract class, so that the individual objects can inherit from another useful hierarchy. The disadvantage of this approach is that you often have to implement the linking, sending and forwarding code in each module separately.

------

------

## THE COMMAND PATTERN

 

The Chain of Responsibility forwards requests along a chain of classes, but the Command pattern forwards a request only to a specific module. It encloses a request for a specific action inside an object and gives it to a known public interface. It lets you give the client the ability to make requests without knowing anything about the actual action that will be performed, and allows you to change that action without affecting the client program in any way.

 

For example, when you build a user interface, you provide menu items, buttons, and checkboxes and so forth to allow the user to tell the program what to do. When a user selects one of these controls, the program receives an ActionEvent. When you have dozens of menu items and several buttons, the actionPerformed code can get pretty unwieldy. We need a long series of if statements in actionPerformed method to identify the selected object. Instead, we'd like to find a way to have each object receive its commands directly.

 

One way to assure that every object receives its own commands directly is to use the **Command object approach**. A Command object always has an Execute() method that is called when an action occurs on that object. Then we can provide an Execute method for each object which carries out the desired action, thus keeping the knowledge of what to do inside the object where it belongs, instead of having another part of the program make these decisions. This certainly simplify the calls made in the actionPerformed method, but it does require that we create and instantiate a new class for each action we want to execute.

 

One important purpose of the Command pattern is to keep the program and user interface objects completely separate from the actions that they initiate. In other words, these program objects should be completely separate from each other and should not have to know how other objects work. The user interface receives a command and tells a Command object to carry out whatever duties it has been instructed to do. The UI does not and should not need to know what tasks will be executed.

 

The Command object can also be used when you need to tell the program to execute the command when the resources are available rather than immediately. In such cases, you are queuing commands to be executed later. Finally, you can use Command objects to remember operations so that you can **support Undo requests**.

 

**Consequences of the Command Pattern**

 

The main disadvantage of the Command pattern is a proliferation of little classes that either clutters up the main class if they are inner or clutters up the program namespace if they are outer classes. We can reduce the clutter of our name space by creating Anonymous Inner Classes (unnamed inner classes) where we need it. However, this does not really improve the number of run-time classes since the compiler generates a class file even for these unnamed classes.

 

**Providing Undo**

 

Another of the main reasons for using Command design patterns is that they provide a convenient way to store and execute an Undo function. Each command object can remember what it just did and restore that state when requested to do so if the computational and memory requirements are not too overwhelming.

------

------

## THE INTERPRETER PATTERN

Some programs benefit from **having a language to describe operations** they can perform. The Interpreter pattern generally describes **defining a grammar for that language** and using that grammar to interpret statements in that language. When a program presents a number of different, but somewhat similar cases it can deal with, it can be advantageous to use a simple language to describe these cases and then have the program interpret that language.

One of the problems we must deal with is how to recognize when a language can be helpful. There are two general places where languages are applicable:

1. When the program must parse an algebraic string. The program is asked to carry out its operations based on a computation where the user enters an equation of some sort. This frequently occurs in mathematical-graphics programs, where the program renders a curve or surface based on any equation it can evaluate.
2. When the program must produce varying kinds of output. Consider a program that can display columns of data in any order and sort them in various ways based on simple user inputs.

**Interpreting the Language**

 

Interpreting the language takes place in three steps

\1. Parsing the language symbols into tokens.

\2. Reducing the tokens into actions.

\3. Executing the actions.

 

We parse the language into tokens by simply scanning each statement with a StringTokenizer and then substituting a number for each word. Usually parsers push each parsed token onto a stack. This entire process is carried out by creating a Parser class (that is a Command object) for each token and execute its action.

 

**Consequences of the Interpreter Pattern**

 

Whenever you introduce an interpreter into a program, you need to provide a simple way for the program user to enter commands in that language. Introducing a language and its accompanying grammar also requires fairly extensive error checking for misspelled terms or misplaced grammatical elements. This can easily consume a great deal of programming effort unless some template code is available for implementing this checking. Further, effective methods for notifying the users of these errors are not easy to design and implement.

 

When you have to have a way to specify the order of sequential operations, a language is a good way to do so, even if the language is generated from the user interface. The Interpreter pattern has the advantage that you can extend or revise the grammar fairly easily one you have built the general parsing and reduction tools. You can also add new verbs or variables quite easily once the foundation is constructed. As the syntax of the grammar becomes more complex, you run the risk of creating a hard to maintain program. While interpreters are not all that common in solving general

programming problems.



------

------

## THE ITERATOR PATTERN

 

The Iterator pattern allows you to move through a list or collection of data using a standard interface without having to know the details of the internal representations of that data. In addition you can also define special iterators that perform some special processing and return only specified elements of the data collection. The Iterator is useful because it provides a defined way to move through a set of data elements without exposing how it does it. Since the Iterator is an interface, you can implement it in any way that is convenient for the data you are returning.

 

**Enumerations in Java**

 

The Enumeration type is built into the Vector and Hashtable classes. Rather than the Vector and Hashtable implementing the two methods of the Enumeration directly, both classes contain an elements method that returns an Enumeration of that class’s data. This elements() method is kind of a Factory method that produces instances of an Enumeration class.

 

**public Enumeration elements();**

 

In addition, the Hashtable also has the keys method, which returns an enumeration of the keys to each element in the table: **public Enumeration keys();** This is the preferred style for implementing Enumerations in Java and has the advantage that you can have any number of simultaneous active enumerations of the same data.

 

**Filtered Iterators**

You can also define filtered Enumerations that perform some computation on the data before returning it. For example, you could return the data ordered in some particular way, or only those objects that match a particular criterion. Then, rather than have a lot of very similar interfaces for these filtered enumerations, you simply provide a method which returns each type of enumeration, with each one of these enumerations having the same methods.

 

**Consequence of the Iterator Pattern**

 

➢  **Data modification**: It is possible that an element might be added or deleted from the underlying collection while you are moving through it.

➢  **Privileged access**: Enumeration classes may need to have some sort of privileged access to the underlying data structures of the original container class, so they can move through the data. Alternatively, you could make the Iterator a derived class of the containment class and access the data directly. However, classes defined in the same module as the containing class also do have access to the containing classes variables.

➢  **External versus Internal Iterators** : The Design Patterns describes two types of iterators: external and internal. **Internal iterators** are methods that move through the entire collection, performing some operation on each element directly, without any specific requests from the user. These are less common in Java. In general, external iterators give you more control, because the calling program accesses each element directly and can decide whether to perform an operation on.

 

**Composites and Iterators**

Iterators, or in our case Enumerations, are also an excellent way to move through Composite structures. In the Composite of an employee hierarchy, each Employee contains a Vector whose elements() method allows you to continue to enumerate down that chain. If that Employee has no subordinates, the hasMoreElements() method correctly returns false.

 

 

------

------

## THE MEDIATOR PATTERN

 

When a program is made up of many number of classes, communication between these classes become more complex. The more each class needs to know about the methods of another class, the more tangled the class structure can become. This makes the program harder to read and harder to maintain. Further, it can become more difficult to change the program, since any change may affect code in several other classes. The Mediator pattern addresses this problem by promoting looser coupling between these classes. Mediators accomplish this by being the only class that has detailed knowledge of the methods of other classes. Classes send inform the mediator when changes occur and the Mediator passes them on to any other classes that need to be informed.

 

The Mediator pattern simplifies the system by being the only class that is aware of the other classes in the system. Each of the controls that the Mediator communicates with is called a **Colleague**. Each Colleague informs the Mediator when it has received a user event, and the Mediator decides which other classes should be informed of this event. The advantage of the Mediator is clear-- it is the only class that knows of the other classes, and thus the only one that would need to be changed if one of the other classes changes or if other interface control classes are added.

 

The main difference in writing a program using a Mediator class is that each class needs to be aware of the existence of the Mediator. You start by creating an instance of the Mediator and then pass the instance of the Mediator to each class. All the classes knows about the Mediator and tells the Mediator of its existence so the Mediator can send commands to it when appropriate.

 

**Initialization of the System**

One further operation that is best delegated to the Mediator is the initialization of all the controls to the desired state. When we launch the program, each control must be in a known, default state, and since these states may change as the program evolves, we simply create an init method in the Mediator, which sets them all to the desired state.

 

**Consequences of the Mediator Pattern**

➢  The Mediator makes loose coupling possible between objects in a program. It also localizes the behavior that otherwise would be distributed among several objects.

➢  You can change the behavior of the program by simply changing or subclassing the Mediator. The Mediator approach makes it possible to add new Colleagues to a system without having to change any other part of the program.

➢  The Mediator solves the problem of each Command object needing to know too much about the objects and methods in the rest of a user interface.

➢  The Mediator can become **monolithic** in complexity, making it hard to change and maintain. Sometimes you can improve this situation by revising the responsibilities you have given the Mediator. Each object should carry out it’s own tasks and the Mediator should only manage the interaction between objects.

➢  Each Mediator is a custom-written class that has methods for each Colleague to call and knows what methods each Colleague has available. This makes it difficult to reuse Mediator code in different projects. On the other hand, most Mediators are quite simple and writing this code is far easier than managing the complex object interactions any other way.

 

**Implementation Issues**

The Mediator pattern acts as a kind of **Observer pattern**, observing changes in the Colleague elements. Another approach is to have a single interface to your Mediator, and pass that method various constants or objects which tell the Mediator which operations to perform. In the same fashion, you could have a single Colleague interface that each Colleague would implement, and each Colleague would then decide what operation it was to carry out.

 

 

------

------

## THE MEMENTO PATTERN

Suppose you would like to save the internal state of an object so you can restore it later. Ideally, it should be possible to save and restore this state without making the object itself take care of this task, and **without violating encapsulation**. This is the purpose of the Memento pattern. This sort of information **saving and restoration** is common in systems that need to **support Undo commands**.

We usually expect data inside an object to be private and encapsulated from the outside world. The Memento pattern attempts to solve this problem by having privileged access to the state of the object you want to save. Other objects have only a more restricted access to the object, thus preserving their encapsulation.

This pattern defines three roles for objects:

➢  The **Originator** is the object whose state we want to save.

➢  The **Memento** is another object that saves the state of the Originator.

➢  The **Caretaker** manages the timing of the saving of the state, saves the Memento and, if needed, uses the Memento to restore the state of the Originator (undo an operation).

 

**Mediator** is an ideal place to **manage the Undo action**. It can keep a list of the last n operations so that they can be undone. Thus, the Mediator also functions as the Caretaker object. In fact, since there could be any number of actions to save and undo in such a program, a Mediator is virtually required so that there is a single place where these commands can be stored for undoing later.

 

In Java, this privileged access is possible using **default protection mode**. Variables within a Java class can be declared as Private, Protected, Public or default protected. A class in the same module can access protected or default-protected variables. We can use this feature to build Memento objects.

 

When we create an instance of the Memento class, we pass the Originator instance we want to save. It copies the parameters and saves a copy of the instance of the Originator itself. Later, when we want to restore these parameters, the Memento knows which instance it has to restore them to and can do it directly. The rest of the activity takes place in the Mediator class, where we save the Memento instance (each state of the Originator instance) on the undo list after each action.

 

**Consequences of the Memento**

 

The Memento provides a way to preserve the state of an object while preserving encapsulation. Thus, data that only the Originator class should have access to effectively remains private. It also preserves the simplicity of the Originator class by delegating the saving and restoring of information to the Memento class. On the other hand, the amount of information that a Memento has to save might be quite large, thus taking up fair amounts of storage. This further has an effect on the Caretaker class (here the Mediator) which may have to design strategies to limit the number of objects for which it saves state. In cases where objects change in a predictable manner, each Memento may be able to get by with saving only incremental changes of an object’s state.

 

**Other Kinds of Mementos**

 

While supporting undo/redo operations is one significant use of the Memento pattern, you will also see Mementos used in database transactions. Here they save the state of data in a transaction where it is necessary to restore the data if the transaction fails or is incomplete.

 

  

 

------

------

## THE OBSERVER PATTERN

 

The Observer pattern assumes that the object containing the data is separate from the objects that display the data, and that these display objects observe changes in that data. When we implement the Observer pattern, we usually refer to the data as the **Subject** and each of the displays as **Observers**. Each of these observers registers its interest in the data by calling a public method in the Subject. Then, each observer has a known interface that the subject calls when the data change.

 

//notify the Observers that a change has taken place

abstract interface Observer

{

public void sendNotify(String s);

}

 

//tell the Subject you are interested in changes

abstract interface Subject

{

public void registerInterest(Observer obs);

}

 

In more complicated systems, especially if the observers could also be used to observe other data objects, we might have observers that demand specific, but different, kinds of data. Rather than have each observer convert the message to the right data type, we could use an intermediate Adapter class to perform this conversion. Another problem observers may have to deal with is the case where the data of the central subject class can change in several ways. We could delete points from a list of data, edit their values, or change the scale of the data we are viewing. In these cases we either need to send different change messages to the observers or send a single message and then have the observer ask which sort of change has occurred.

 

**The MVC Architecture as an Observer**

In Model-View-Controller (MVC) architecture, the data are represented by the Model, and the View by the visual component. The Controller is the communication between the Model and View objects, and may be a separate class or it may be inherent in either the model or the view. They are all examples of the Observer pattern.

 

 

**Consequences of the Observer Pattern**

Observers promote abstract coupling to Subjects. A subject doesn’t know the details of any of its observers. However, this has the potential disadvantage of successive or repeated updates to the Observers when there are a series of incremental changes to the data. If the cost of these updates is high, it may be necessary to introduce some sort of change management, so that the Observers are not notified too soon or too frequently.

When one client makes a change in the underlying data, you need to decide which object will initiate the notification of the change to the other observers. If the Subject notifies all the observers when it is changed, each client is not responsible for remembering to initiate the notification. On the other hand, this can result in a number of small successive updates being triggered. If the clients tell the Subject when to notify the other clients, this cascading notification can be avoided, but the clients are left with the responsibility of telling the Subject when to send the notifications. If one client “forgets,” the program simply won’t work properly.

Finally, you can specify the kind of notification you choose to send by defining a number of update methods for the Observers to receive depending on the type or scope of change. In some cases, the clients will thus be able to ignore some of these notifications.

  

 

------

------

## THE STATE PATTERN

The State pattern is used when you want to have an enclosing class switch between a number of related contained classes, and pass method calls on to the current contained class. Design Patterns suggests that the State pattern switches between internal classes in such a way that the enclosing object appears to change its class. Many programmers have had the experience of creating a class which performs slightly different computations or displays different information based on the arguments passed into the class. This frequently leads to some sort of switch or if-else statements inside the class that determine which behavior to carry out. It is this inelegance that the State pattern seeks to replace.

 

**Sample Code**

Let’s consider the case of a drawing program, which will have 5 buttons for Select, Rectangle, Fill, Circle and Clear. Each buttons does something when it is selected and you click or drag your mouse across the screen. Thus, the state of the editor affects the behavior the program should exhibit. Initially we might design our program with a Mediator managing the actions of 5 command buttons. However, this initial design puts the entire burden of maintaining the state of the program on the Mediator, and we know that the main purpose of a Mediator is to coordinate activities between various controls, such as the buttons. Keeping the state of the buttons and the desired mouse activity inside the Mediator can make it unduly complicated as well as leading to a set of if or switch tests which make the program difficult to read and maintain. Further, this set of large, monolithic conditional statements might have to be repeated for each action the Mediator interprets, such as mouseUp, mouseDrag, rightClick and so forth. This makes the program very hard to read and maintain.

 

Instead, let’s analyze the expected behavior for each of the buttons. Four of them use the mouse click event to cause actions. One uses the mouse drag event to cause an action. Thus, we really want to create a system that can help us redirect these events based on which button is currently selected. Since none of the cases we’ve described need all of these events, we’ll give our base class (state) empty methods rather than creating an abstract base class. Then we’ll create 4 derived State classes for Pick, Rect, Circle and Fill and put instances of all of them inside a StateManager class which sets the current state (a state variable) and executes methods on that state object. In Design Patterns, this StateManager class is referred to as a **Context**. A typical State object simply overrides those event methods that it must handle specially. For example, the RectState object simply tells the Mediator to add a rectangle drawing to the drawing list. Similarly, the Circle state object.

 

**Switching Between States**

Now that we have defined how each state behaves when mouse events are sent to it. The StateManager switches between states by simply set the currentState variable to the state indicated by the button that is selected. Note that in this version of the StateManager, we create an instance of each state during the constructor and copy the correct one into the state variable (currentState) when the set methods are called. We can also use a Factory to create these states on demand. The remainder of the state manager code simply calls the methods of whichever state object is current. There is no conditional testing. Instead, the correct state is already in place and its methods are ready to be called.

 

**How the Mediator Interacts with the State Manager**

It is clearer to separate the state management from the Mediator’s controller activities and mouse event management. The Mediator is the critical class, however, since it tells the StateManager (context) when the current program state changes. Mediater communicates with context and context executes on current state.

 

**State Transitions**

The transition between states can be specified internally or externally. In our example, the Mediator tells the StateManager when to switch between states. However, it is also possible that each state can decide automatically what each successor state will be. For example, when a rectangle or circle drawing object is created, the program could automatically switch back to the Arrow-object State.

 

 

**Consequences of the State Pattern**

➢  The State pattern localizes state-specific behavior in an individual class for each state, and puts all the behavior for that state in a single object. It eliminates the necessity for a set of long, look-alike conditional statements scattered through the program’s code.

➢  It makes transition explicit. Rather than having a constant that specifies which state the program is in, and that may not always be checked correctly, this makes the change explicit by copying one of the states to the state variable.

➢  State objects can be shared if they have no instance variables. Here only the Fill object has instance variables, and that color could easily be made an argument instead.

➢  This approach generates a number of small class objects, but in the process, simplifies and clarifies the program.

 

------

------

## THE TEMPLATE PATTERN

 

The Template pattern formalizes the idea of defining an algorithm in a class, but leaving some of the details to be implemented in subclasses. In other words, if your base class is an abstract class you are using a simple form of the Template pattern. The idea behind the Template pattern is that some parts of an algorithm are well defined and can be implemented in the base class, while other parts may have several implementations and are best left to derived classes. Another main theme is recognizing that there are some basic parts of a class that can be factored out and put in a base class so that they do not need to be repeated in several subclasses.

 

A Template has four kinds of methods:

➢  Complete methods that carry out some basic function that all the subclasses will want to use, such as calcx and calcy. These are called **Concrete methods**.

➢  Methods that are not filled in at all (abstract) and must be implemented in derived classes.

➢  Methods that contain a default implementation of some operations, but which may be overridden in derived classes. These are called **Hook methods**. Hook methods are intended to be overridden, while Concrete methods are not.

➢  Finally, a Template class may contain methods which themselves call any combination of abstract, hook and concrete methods. These methods are not intended to be overridden, but describe an algorithm without actually implementing its details. Design Patterns refers to these as **Template methods**.

 

The first significant point is that your base class may only define some of the methods it will be using, leaving the rest to be implemented in the derived classes. The second major point is that there may be methods in the base class which call a sequence of methods, some implemented in the base class and some implemented in the derived class. This Template method defines a general algorithm, although the details may not be worked out completely in the base class. Template classes will frequently have some abstract methods that you must override in the derived classes, and may also have some classes with a simple “place-holder” implementation that you are free to override where this is appropriate. If these place-holder classes are called from another method in the base class, then we refer to these overridable methods are “Hook” methods.

 

 

 

------

------

## THE STRATEGY PATTERN

 

The Strategy pattern consists of a number of related algorithms encapsulated in a driver class called the **Context**. Your client program can select one of these differing algorithms or in some cases the Context might select the best one for you. The intent, like the State pattern, is to switch easily between algorithms without any monolithic conditional statements. The difference between State and Strategy is that the user generally chooses which of several strategies to apply and that only one strategy at a time is likely to be instantiated and active within the Context class. By contrast, it is likely that all of the different States will be active at once and switching may occur frequently between them. In addition, Strategy encapsulates several algorithms that do more or less the same thing, while State encapsulates related classes that each do something somewhat different. Finally, the concept of transition between different states is completely missing in the Strategy pattern.

 

A program which requires a particular service or function and has several ways of carrying out that function is a candidate for the Strategy pattern. Programs choose between these algorithms based on computational efficiency or user choice. There can be any number of strategies and more can be added and any of them can be changed at any time. Examples are

➢  Save files in different formats.

➢  Compress files using different algorithms

➢  Use different line-breaking strategies to display text data.

➢  Plot the same data in different formats: line graph, bar chart or pie chart.

 

In each case we could imagine the client program telling a driver module (**Context**) which of these strategies to use and then asking it to carry out the operation. The idea behind Strategy is to encapsulate the various strategies in a single module and provide a simple interface to allow choice between these strategies. Each of them should have the same programming interface, although they need not all be members of the same class hierarchy. However, they do have to implement the same programming interface.

 

**The Context**

The Context class is the traffic cop that decides which strategy is to be called. The decision is usually based on a request from the client program, and all that the Context needs to do is to set a variable to refer to one concrete strategy or another. The Context class is also responsible for handling the data. Either it obtains the data from a file or database or it is passed in when the Context is created. Depending on the magnitude of the data, it can either be passed on to the plot strategies or the Context can pass an instance of itself into the plot strategies and provide a public method to fetch the data.

 

**Consequences of the Strategy Pattern**

Strategy allows you to select one of several algorithms dynamically. These algorithms can be related in an inheritance hierarchy or they can be unrelated as long as they implement a common interface. Since the Context switches between strategies at your request, you have more flexibility than if you simply called the desired derived class. This approach also avoids the sort of condition statements that can make code hard to read ad maintain. On the other hand, strategies don’t hide everything. The client code must be aware that there are a number of alternative strategies and have some criteria for choosing among them. This shifts an algorithmic decision to the client programmer or the user. Since there are a number of different parameters that you might pass to different algorithms, you have to develop a Context interface and strategy methods that are broad enough to allow for passing in parameters that are not used by that particular algorithm. For example the setPenColor method in our PlotStrategy is actually only used by the LineGraph strategy. It is ignored by the BarGraph strategy, since it sets up its own list of colors for the successive bars it draws. But your Context interface and strategy interface should have this method. Here Mediater call the context fuctions and Context calls the current strategy functions.

 

------

------

## THE VISITOR PATTERN

The Visitor pattern **creates an external class to act on data in other classes**. This is useful if there are a fair number of instances of a small number of classes and you want to perform some operation that involves all or most of them.

**What does visiting mean?**

In the Visitor case, visiting each class means that you are calling a method(**accept** method) already installed for this purpose. The accept method has **one argument: the instance of the visitor**, and in return, it **calls the visit method** of the Visitor, **passing itself as an argument**. In this way, the Visitor object receives a reference to each of the instances, one by one, and can then call its public methods to obtain data, perform the required actions. Every object that you want to visit must have the following method:

```java
public void accept(Visitor v)
{
	v.visit(this);  //call visitor method
}
```

**When to Use the Visitor Pattern**

You should consider using a Visitor pattern when you want to **perform an operation on the data contained in a number of objects that have different interfaces**. Visitors are also valuable if you have to **perform a number of unrelated operations** on these classes. On the other hand, Visitors are a good choice only when you do not expect many new classes to be added to your program. Notice that there is no indication what the Visitor does with each class in either the client classes or the abstract Visitor class. We can in fact write a whole lot of visitors that do different things to the classes in our program.

**Sample Code**

We have an Employee object which maintains a record of the employee’s name, salary, vacation taken and number of sick days taken. Now we want to prepare a report of the number of vacation that all employees have taken so far this year. We could write some code in the client to sum the results of calls to each Employee’s getVacDays function, or we could put this function into a Visitor. But your base Visitor class needs to have a suitable abstract visit method for each kind of class in your program. In this example, we only have Employees, so our basic abstract Visitor class is just

```java
public abstract class Visitor
{ 
	public abstract void visit(Employee emp);
}
```

Let’s reiterate what happens for each visit:

1. We move through a loop of all the Employees.

2. The Visitor calls each Employee’s accept method.
3. That instance of Employee calls the Visitor’s visit method.
4. The Visitor fetches the vacation days and adds them into the total for each employee.

5. The main program when the loop is complete, ask the Visitor for the total and prints out the total.


**Visiting Several Classes**

The Visitor becomes <u>more useful, when there are a number of different classes with different interfaces</u> and we want to encapsulate how we get data from these classes. Let’s introduce a new Employee type called Boss and suppose that, Bosses are rewarded with bonus vacation days. <u>When we add a class to our program, we have to add it to our Visitor as well</u>. This says that any concrete Visitor classes we write must provide polymorphic visit methods for both Employee and Boss class. In the case of our vacation day counter, we need to ask the Bosses for both regular and bonus days taken, so the visits are now different.

```java
public abstract class Visitor
{
	public abstract void visit(Employee emp);
	public abstract void visit(Boss emp);
}
```

Note that while in this case Boss is derived from Employee, it need not be related at all as long as it has an accept method for the Visitor class. It is quite important, however, that you implement a visit method in the Visitor for every class you will be visiting and not count on inheriting this behavior, since the visit method from the parent class is an Employee rather than a Boss visit method. Likewise, each of your derived classes (Boss, Employee, etc.) must have its own accept method rather than calling one in its parent class. The VacationVisitor will just treat Bosses as Employees and get only their vacation data. The bVacationVisitor will get both.

VacationVisitor vac = new VacationVisitor();

bVacationVisitor bvac = new bVacationVisitor();

for (int i = 0; i < employees.length; i++)

{

employees[i].accept(vac);

employees[i].accept(bvac);

}

total.setText(new Integer(vac.getTotalDays()).toString());

btotal.setText(new Integer(bvac.getTotalDays()).toString());

 

**Double Dispatching**

You are really dispatching a method twice for the Visitor to work. The Visitor calls the polymorphic accept method of a given object, and the accept method calls the polymorphic visit method of the Visitor. It is this bidirectional calling that allows you to add more operations on any class that has an accept method, since each new Visitor class we write can carry out whatever operations we might think of using the data available in these classes.

**Traversing a Series of Classes**

The calling program that passes the class instances to the Visitor must know about all the existing instances of classes to be visited and must keep them in a simple structure such as an array or Vector. Another possibility would be to create an Enumeration of these classes and pass it to the Visitor. Finally, the Visitor itself could keep the list of objects that it is to visit.

**Consequence of the Visitor Pattern**

The Visitor pattern is useful when you want to **encapsulate fetching data** from a number of instances of several classes. Design Patterns suggests that the Visitor can provide additional functionality to a class without changing it. We prefer to say that a Visitor can add functionality to a collection of classes and encapsulate the methods it uses. The Visitor cannot obtain private data from classes: it is limited to the data available from public methods. This might force you to provide public methods that you would otherwise not have provided. However, it can obtain data from a disparate collection of unrelated classes and utilize it to present the results of a global calculation to the user program.

It is easy to add new operations to a program using Visitors, since the Visitor contains the code instead of each of the individual classes. Further, Visitors can gather related operations into a single class rather than forcing you to change or derive classes to add these operations. This can make the program simpler to write and maintain. Visitors are less helpful during a program’s growth stage, since each time you add new classes which must be visited, you have to add an abstract visit operation to the abstract Visitor class, and you must add an implementation for that class to each concrete Visitor you have written. Visitors can be powerful additions when the program reaches the point where many new classes are unlikely. Visitors can be used very effectively in a Composite systems (like boss-employee system).

------

------



## Design Pattern By Arul Kumar

 

➢  Follow the design principle of code to an interface not to an implementation.

➢  Instead of using inheritance, we can use object composition to achieve code reuse.

 

| ![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image004.png) | ![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image006.jpg) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

If you were to use object composition instead of inheritance, you would have classes Book, CD and Cosmetics implementing the Item interface directly (Goods class would not be required), and make use of a GoodsHelper class to achieve code reuse through composition.

 

**Factory method pattern**: The factory method design pattern instantiates a class in a more flexible way than directly calling the constructor. It loosely couples your calling code from the Items it creates like CD, Book, etc. Let’s look at why factory method pattern is more flexible:

➢  Sometimes factory methods have to return a single instance of a class instead of creating new objects each time or return an instance from a pool of objects. Create an object cache or object pool of our items instead of creating a new instance every time without making any changes to the calling class.

➢  Factory methods have to return a subtype of the type requested. It also can request the caller to refer to the returned object by its interface rather than by its implementation, which enables objects to be created without making their implementation classes public.

➢  Sometimes old ways of creating objects can be replaced by new ways of creating the same objects or new classes can be added using polymorphism without changing any of the existing code which uses these objects. For example: Say you have a Fruit abstract class with Mango and Orange as its concrete subclasses, later on you can add an Apple subclass without breaking the code which uses these objects.

 

**Abstract factory pattern**: This pattern is one level of abstraction higher than the factory method pattern because you have an abstract factory (or factory interface) and have multiple concrete factories. Abstract factory pattern usually has a specific method for each concrete type being returned (e.g. createCircle(), createSquare() etc). Alternatively you can have a single method e.g. createShape(...).

 

**Singleton pattern**: Ensures that a class has only one instance and provides a global point of access to it. E.g. a DataSource should have only a single instance where it will supply multiple connections from its single DataSource pool.

 

**Builder pattern**: To simplify complex object creation by defining a class whose purpose is to build instances of another class. The subtle difference between the builder pattern and the factory pattern is that in builder pattern, the user is given the **choice to create the type of object** (one object type) he/she wants but the construction process is the same. But with the factory method pattern the factory decides how to create **one of several possible classes** (one object type from many) based on data provided to it.

 

**Strategy pattern**: The Strategy pattern lets you build software as a loosely coupled collection of interchangeable parts, in contrast to a monolithic, tightly coupled system. Loose coupling makes your software much more extensible, maintainable, and reusable. The main attribute of this pattern is that each strategy encapsulates algorithms i.e. it is not data based but **algorithm based**.

 

Example: You can draw borders around almost all Swing components, including panels, buttons, lists, and so on. Swing provides numerous border types (or **different strategies**) for its components: bevel, etched, line, titled, and even compound. JComponent class, which acts as the base class for all Swing components by implementing functionality common to all Swing components, draws borders for Swing components, using strategy pattern.

 

**Decorator design pattern**: The decorator design pattern can be used to provide additional functionality to an object of some kind. The key to a decorator is that a decorator "wraps" the object decorated and looks to a client exactly the same as the object wrapped. This means that the **decorator implements the same interface as the object it decorates**.

 

You can think of a decorator as a shell around the object decorated. The decorator catches any message that a client sends to the object instead. The decorator may apply some action and then pass the message it received on to the decorated object. That object probably returns a value to the decorator which may again apply an action to that result, finally sending the (perhaps-modified) result to the original client. To the client the decorator is invisible. It just sent a message and got a result. However the decorator had two chances to enhance the result returned.

 

There is a subtle difference between the decorator pattern and the **proxy pattern** is that, the main intent of the decorator pattern is to enhance the functionality of the target object whereas the main intent of the proxy pattern is to control access to the target object. A decorator object’s interface must conform to the interface of the component it decorates.

 

**Adapater pattern** : Adapter class provides default implementations (not to use), so that it can be extended to provide specific implementation and facilitates its subclasses to be adapted to each other. Throws an unchecked exception if required to indicate improper use (if any one use the default implentations).

 

Sometimes a library cannot be used because its interface is not compatible with the interface required by an application. This is where you can use an adapter design pattern. Adapter lets classes work together that could not otherwise because of incompatible interfaces. This pattern is also known as a **wrapper**.

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image007.png)

 

 

 

 

 

 

 

 

 

 

 

**Visitor pattern**: The visitor pattern makes adding new operations easy and all the related operations are localized in a visitor. The visitor pattern allows you to manipulate a **collection of polymorphic objects** without the messy and unmaintainable **typecasts** and **instanceof** operations. Visitor pattern allows you to add new operations, which **affect a class hierarchy** without having to change any of the classes in the hierarchy. For example we can add a GoodsDebugVisitor class to have the visitor just print out some debug information about each item (cd, book or cosmetics) visited etc. In fact you can write any number of visitor classes for the Goods hierarchy e.g. GoodsLabellingVisitor, GoodsXXXXVisitor etc. These visitor methods should never belongs to the item classes (independent).

 

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image009.jpg)

 

**Iterator pattern**: Provides a way to access the elements of an aggregate object without exposing its underlying implementation.

 

 

**Template method pattern**: When you have a sequence of steps to be processed within a method and you want to defer some of the steps to its subclass then you can use a template method pattern. So the template method lets the subclass to redefine some of the steps. Good example of this is the process() method in the Struts RequestProcessor class, which executes a sequence of processXXXX(...) methods allowing the subclass to override some of the methods when required.

 

 

 

 

 

 

 

 

 

 

 

 

**Composite design pattern**: The composite design pattern composes objects into **tree structures** where individual objects like sales staff and composite objects like managers are handled uniformly.

 

In a hierarchy, the manager has subordinates. The sales staffs have no subordinates and they report to their immediate manager. The company needs functionality to calculate salary at different levels of the hierarchy. You can apply the composite design pattern to represent the company employee hierarchy. Recursive method call is required to calculate the sum of salary of a manager, which means sum of salary of a manager and his subordinates (any employees who themselves will have any subordinates and so on).

 

 

**Façade pattern**: The façade pattern provides an interface to large subsystems of classes. A common design goal is to minmize the communication and dependencies between subsystems. One way to achieve this goal is to introduce a façade object that provides a single, simplified interface.

 

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image011.jpg)

 

**Observer pattern**: defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. (aka publish-subscribe pattern).

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image013.jpg) 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

**Command pattern**: The Command pattern is an object behavioural pattern that allows you to achieve complete decoupling between the **sender** and the **receiver**. (A sender is an object that invokes an operation, and a receiver is an object that receives the request to execute a certain operation. With decoupling, the sender has no knowledge of the Receiver's interface.) The term request here refers to the command that is to be executed. The Command pattern also allows you to vary when and how a request is fulfilled. At times it is necessary to issue requests to objects without knowing anything about the operation being requested or the receiver of the request. In procedural languages, this type of communication is accomplished via a **call-back**: a function that is registered somewhere to be called at a later point. Commands are the object-oriented equivalent of call-backs and encapsulate the call-back function.

![img](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image015.jpg)

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

**Proxy pattern**: Provides a surrogate or placeholder for another object to control access to it. Proxy object acts as an intermediary between the client and the target object. The proxy object has the same interface as the target object. The proxy object holds reference to the target object. There are different types of proxies:

➢  **Remote Proxy**: provides a reference to an object, which resides in a separate address space. e.g. EJB, RMI, CORBA etc (RMI stubs acts as a proxy for the skeleton objects.)

➢  **Virtual Proxy**: Allows the creation of memory intensive objects on demand. The target object will not be created until it is really needed.

➢  **Access Proxy**: Provides different clients with different access rights to the target object.

 

In **Hibernate framework lazy loading** of persistent objects are facilitated by virtual proxy pattern. Let’s say that Employee objects are lazy loaded. If you make a call department.getEmployees() then Hibernate will load only the employeeIDs and the version numbers of the Employee objects, thus saving loading of individual objects until later. So what you really have is a collection of proxies not the real objects. The reason being, if you have hundreds of employees for a particular department then chances are good that you will only deal with a few of them. So, why unnecessarily instantiate all the Employee objects? This can be a big performance issue in some situations. So when you make a call on a particular employee i.e. employee.getName() then the proxy loads up the real object from the database.

 

**Dynamic proxy :** Dynamic proxies were introduced in J2SE 1.3, and provide an alternate dynamic mechanism for implementing many common design patterns like Façade, Bridge, Decorator, Proxy (remote proxy and virtual proxy), and Adapter. While all of these patterns can be written using ordinary classes instead of dynamic proxies, in many situations dynamic proxies are more compact and can **eliminate the need for a lot of handwritten classes**. Dynamic proxies are **reflection-based** and allow you to intercept method calls so that you can interpose additional behaviour between a class caller and its callee. Dynamic proxies are not always appropriate because this code simplification comes at a performance cost due to reflection overhead. Dynamic proxies illustrate the basics of **Aspect Oriented Programming (AOP)** which complements your Object Oriented Programming.

 

Where can you use dynamic proxies? Dynamic proxies can be **used to add crosscutting concerns** like logging, performance metrics, memory logging, retry semantics, test stubs, caching etc. **InvocationHandler interface** (java.lang.reflect.InvocationHandler) is the heart of a proxy mechanism.

 

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;

/**

\* Handles logging and invocation of target method

*/

public class LoggingHandler implements InvocationHandler {

protected Object actual;

public LoggingHandler(Object actual) {

this.actual = actual;

}

public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

try {

System.out.println(">>>>>>start executing method: " + method.getName());

Object result = method.invoke(actual, args);

return result;

} catch (InvocationTargetException ite) {

throw new RuntimeException(ite.getMessage());

} finally {

System.out.println("<<<finished executing method: " + method.getName());

}

}

}

 

/**

\* CalculatorFactory uses the dynamic proxies for logging.

*/

 

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Proxy;

 

public class CalculatorFactory {

public Calculator getCalculator(boolean withLogging) {

Calculator c = new CalculatorImpl();

//use dynamic proxy if logging is required, which logs your method calls

if (withLogging) {

//invoke the handler, which logs and invokes the target method

InvocationHandler handler = new LoggingHandler(c);

 

//**create a proxy**

c = (Calculator) Proxy.newProxyInstance(c.getClass().getClassLoader(), c.getClass().getInterfaces(), handler);

}

return c;

}

}

 
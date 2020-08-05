# Java Principles

## Clean Coding in Java

### What Is Clean Code?

Clean code can be summarized as a code that any developer can **read and change easily**.

> **Martin Fowler** - Any fool can write code that a computer can understand. Good programmers write code that humans can understand.

**Why should we invest in developing Clean Code?**

Other developers will probably find it **easier to read** our code. Also **Clean coding principles** help us achieve a lot of desirable goals related to the software we intend to produce. 

- **Maintainable Codebase**: Clean code can help develop software that is **easy to change and maintain** over time.
- **Easier Troubleshooting**: Software can exhibit **unintended/unknown behavior** due to a variety of internal or external factors. Software developed with clean coding principles is **easier to troubleshoot** for problems (bug fixes).
- **Faster Onboarding**: Software development will see **many developers** create, update, and maintain it, with developers joining at different points in time. This requires a **quicker onboarding to keep productivity high**, and clean code helps achieve this goal.

Note that the benefits clean code principles provide is **proportional to the size and complexity** of the codebase. So we must access our codebase before adopting any principle. More importantly, we must remain **consistent with them**. **Code reviews** is a great way to maintain consistency and help the developers grow through **constructive feedback**.

**Characteristics of Clean Code**
Software developed with clean coding principles exhibit several characteristics. It's beneficial to **start developing with these characteristics** in mind compared to refactor later. This leads to a lower total cost of ownership for the software lifecycle.

- **Focused**: A piece of code should be written to **solve a specific problem**, it should not do anything else other than that. This applies to all levels of abstraction like method, class, package, or module.
- **Simple**: This is the most important characteristic of clean code. The software design and implementation must be as simple as possible. Increasing complexity makes software **error-prone and difficult to read and maintain**.
- **Testable**: Clean code must be easy to test, preferably in an **automated manner**. This helps **establish the baseline behavior** of the software and makes it easier to change it without breaking anything.

### Clean Coding in Java

Java offers a lot of **best practices** that can help us write clean code.

**Project Structure**

It's always useful to **follow a consistent pattern** (or a convention) to organize our source files, tests, configurations, data, and other code artifacts. For example Maven, a popular build tool for Java, prescribes a particular project structure.

**Naming Convention**

Following naming conventions make the code more **readable and maintainable** and also conveys a lot about the **intention of the code**. Java prescribes a set of rules to adhere to when it comes to naming. 

- **Classes**: Class is a **blueprint for objects** which often represent real-world objects. Hence it's meaningful to **use nouns to name classes** describing them sufficiently.
- **Variables**: Variables capture the **state of the object**, so the name of the variable should **describe the intent of the variable** clearly.
- **Methods**: Methods in Java generally **represent an action on the state** of the object, hence useful to name methods using **verbs**.

There are other best practices like **camel casing**, which helps readability and more related to naming interfaces, enums, constants as well.

**Source File Structure**
A source file can contain different elements and there are many good conventions, and the idea should be **decided one and then followed consistently**, can significantly improve code readability. Let's see how should a typical ordering of elements in a source file look:

```pseudocode
            Package statement
            Import statements
                All static imports
                All non-static imports
            Exactly one top-level class
                Class variables
                Instance variables
                Constructors
                Methods
```

Apart from the above, methods can be grouped based on their **functionality or scope**. 

**Whitespaces**

**Well-placed and consistent whitespaces and blank lines** can enhance the readability of the code. The idea here is to **introduce logical groupings** in the code which can help **organize thought processes** while trying to read it through.

**Method Parameters**

A **long list of parameters** can make it difficult for someone to read and understand the code, three parameters can be one good choice. **Consider refactoring the method** if it needs more than recommended parameters, typically a long parameter list also indicate that the method may be **doing multiple things**. We may consider **bundling parameters into custom-types** but must be careful **not to dump unrelated parameters into a single type**.

**Hardcoding**

Hardcoding values in code can often lead to **duplication**, which makes **change more difficult**. It can often lead to undesirable behavior if the values need to be dynamic. In most of the cases, hardcoded values can be refactored in one of the following ways:

- Consider **replacing with constants or enums** defined within Java
- Or else, **replace with constants** defined at the class level or in a separate class file
- If possible, replace with **values which can be picked from configuration** or environment

**Code Comments**

Code comments can be beneficial while reading code **to understand the non-trivial aspects**. At the same time should **not include obvious things** in the comments, this can bloat comments making it **difficult to read the relevant parts**. Java allows **two types of comments**: Implementation comments and documentation comments. They have different purposes and different formats, as well.

| Documentation/JavaDoc Comments                               | Implementation/Block Comments                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| The audience here is the **users of the codebase**.          | The audience here is the **developers** working on the codebase |
| The details here are typically **implementation free**, focusing more on the **specification**. | The details here are **implementation-specific**             |
| Typically useful independent of the codebase.                | Typically useful **together with the codebase**              |

Comments should **only complement a code**, if we are not able to understand the code without comments, perhaps we need to refactor the code. We should use **block comments rarely**, possibly to **describe non-trivial design decisions**. But we should use JavaDoc comments for most of our classes, interfaces, public and protected methods.

**Logging**

best practices:

- **Avoid excessive logging**, think about what information might be of **help in troubleshooting**
- **Choose log levels wisely**, we may want to enable log levels selectively on **production**
- Be very **clear and descriptive with contextual data** in the log message
- Use **external tools for tracing, aggregation, filtering** of log messages for faster analytics

## Clean Code Principles

Bad code is **difficult to understand**, **more complex**, **not easy to test**, and difficult to maintain. It might take longer to write clean code still clean code will save everyone time, effort, and money.

- **KISS**: Keep It Simple Stupid. It states that **most systems should be kept as simple as possible**. Unnecessary complexity should be avoided and makes it easy to understand and maintain over time. The question to ask when you're writing code is "can this be **written in a simpler way**?". So if we keep our classes and methods focussed and small, this will lead to simpler code.

- **DRY**: Don't Repeat Yourself. It states that every piece of knowledge/code must have a **single, unambiguous, authoritative representation within a system** (codebase). This principle states that a piece of code **should not be repeated across the software**. This will **reduce duplication and increase reusability**. Please note that some duplication can actually improve code readability and maintainability. 

  **Violations of DRY** are referred to as **WET**: We Enjoy Typing, Write Everything Twice, Waste Everyone's Time.

- **YAGNI**: You Aren't Gonna Need It. A developer **should not add functionality unless deemed necessary**. YAGNI is part of the **Extreme Programming (XP) methodology**, which wants to improve software quality and increase responsiveness to customer requirements. YAGNI should be used in conjunction with continuous refactoring, unit testing, and integration.

- **Composition over inheritance**: It's a principle where you **design your types over what they do instead of over what they are**. Composition is favored over inheritance, because inheritance forces you to build a taxonomy of objects early on in a project, making the **code inflexible for changes later on**.

- **Favor readability**: when working with multiple people on a project, always **favor readability over conciseness**. There's no point in having concise code if people don't understand it. There are many ways to make your code more readable. Two examples are using well-named **constants instead of common numbers or strings** and creating **long names instead of shorter ones** (e.g. userHasFormAccess over canAccess, which doesn't tell as much).

- **Practice consistency**: This is arguably the **overarching (comprehensive) principle of all clean code principles**. If you decide to do something a certain way, stick to it **throughout the entire project**. If you have no choice but to move away from your original choice, explain why in the comments.

### Composition over Inheritance

Inheritance is when you **design your types around what they are**, while composition is **when you design your types around what they do**. We should favor **object composition over inheritance** so **design your types over what they do instead of over what they are** 

**Limitations of Inheritance** and how to solve them using composition.

Adding more inheritance classes makes the code very complex and extremely difficult to fit new classes (behavior) easily into this inheritance hierarchy. 

Using composition

- A dog is a pooper and a barker.
- A cat is a pooper and meower.
- A cleaning robot is a driver and a cleaner.
- A murder robot dog is a driver and a killer and Barker. 

So instead of **creating their own state internally** they **accept their state as a function parameter**. So that they can share the same state. 

#### Object assign 

what object design does is that it takes an object and assigns the properties from the other objects into it. So in this case it creates a marker, a driver, a killer and then **merges them all into the new object** and returns that object.

#### Favor composition over inheritance

If something is an **is a relationship** then it should **use inheritance** and if the relationship is more of a has a nature then you should use composition. but that reasoning is very complex because you can **interpret pretty much any concept both ways**. 

The problem with inheritance is that it **encourages you to go predicting the future**. Inheritance encourages you to **build this taxonomy of objects very early on project** and you are most likely going to **make big design mistakes** while doing that. And once you build yourself into that **inheritance taxonomy** it's **really hard to get out of it** so I think it's just **better to use composition from the start**. It's more flexible, more powerful and really easy to do so.

------

------

## SOLID design principles

Developing a successful application depends on   

- **Architecture** : choosing an architecture is the first step in designing application.
- **Design Principles** : Need to follow design principles to make the application **Robust and performant**.
- **Design Patterns** : We need to choose correct design patterns to build the software.

We need to account the below factors during the development cycle.  

- **Maintainability** : Maintainable systems are very important to the organisations.
- **Testability** : Test driven development (TDD) is required when we design and develop.
- **Flexibility and Extensibility** : We should design the application to make it flexible so that it can be adapt to work in different ways and extensible so that we can add new features easily.
- **Parallel Development** : It is not practical to have the entire development team working simultaneously on the same feature or component.
- **Loose Coupling** : We can address many of the above requirements by ensuring that our design results in an application that loosely couples many parts that makes up the application. 

SOLID principles are the **design principles** that enable us **manage most of the software design problems**. The term SOLID is an acronym for **five design principles** intended to make software designs more **understandable, flexible and maintainable**. 

If we don’t follow SOLID Principles we    

1. End up with **tight coupling of the code** with many other modules/applications 
2. Tight coupling causes **time to implement** any new requirement, features or any bug fixes. 
3. End up with a code which is **not testable** and **duplication of code**.

Following SOLID Principles helps us to

1. Achieve **reduction in complexity** of code, **Better testability**. 
2. Increase **readability, extensibility and maintenance** 
3. **Reduce tight coupling**, error and implement **Reusability**.

### SOLID Acronym

- S : Single Responsibility Principle (SRP)
- O : Open closed Principle (OCP)
- L :  Liskov substitution Principle (LSP)
- I  :  Interface Segregation Principle (ISP)
- D : Dependency Inversion Principle (DIP)

#### Single Responsibility Principle

"A class should have only **one reason to change**”, which means Every module or class should have **responsibility over a single part** of the functionality provided by the software, and that **responsibility should be entirely encapsulated** by the class. With SRP, Every module, interface, class, or method we define should **focus on a single task** at a time. Everything or every members in the class should be related to that single purpose. In essence, it should ideally do one thing and do that well. With SRP, classes become **smaller and cleaner** (smaller methods and classes), so code is less fragile and easy to test. 

Let's say we need to create an application that performs a user's login and registration. It also should be able to send an email depending on the logic status and log any exceptions that may occur in this process. So normally we **create an interface with all these four methods (login, registration, logError, sendEmail)**. A user object can perform only login and registration or one at a time and it **should not be concerned about log error or send email**. So we don't need to have logger and send email part of user interface. so we need to create two mores interfaces to handle logError and sendEmail. Having these three interfaces makes more sense and these three interfaces are **performing their own responsibilities**. So we have segregated the single responsibility principle **using these multiple interfaces**. 

#### Open/Closed Principle

“Software entities such as classes, modules, functions etc should be **open for extension**, but **closed for modification**”. The design and writing of the code should be done in a way that **new functionality should be added with minimum changes** in the existing code. The design should be done in a way to allow the **adding of new functionality as new classes**, keeping as much as possible **existing code unchanged**. So a class should be written in a manner that there **should not be any need to modify it**. However, it should **allow for changes through inheritance or composition**.

**Implementation guidelines** of OCP: The simplest way is to implement the new functionality on **new derive sub classes** that inherit the original class, instead of changing the current ones or existing ones. So **create new classes  to support new requirements** and leave the original implementation untouched. 

**What if I do not follow open-closed principle?**

If a class or a function **always allows the addition of new logic** for supporting any new requirement then **we end up testing the entire functionality** along with the requirement. Not following the open closer principle also **breaks the single responsibility principle** as well. Since the class or function might end up doing multiple tasks. So both SRP and OCP are **highly dependent on each other**.

#### Liskov Substitution Principle

“objects in a program should be replaceable with instances of their sub-types **without altering the correctness** of that program”. If a program module is using a Base class, then the reference to the Base class can be replaced with a Derived class without affecting the functionality of the program. In a computer program **S is a subtype of T** then objects of type T may be replaced with objects of type S. This principle is an **extension of the open close principle**. This helps in **reducing coupling** in the codebase and hence **improve reusability** across.

##### Implementation Guidelines

- In the process of development we should ensure that "**No new exceptions can be thrown by the subtype** unless they are part of the existing exception hierarchy".
- Clients should not worry about which specific subtype they are calling. The client should behave the same regardless of the subtype instance that it is given.
- New derived classes just extend without replacing the functionality of old classes.

#### Interface Segregation Principle

“Many **client-specific interfaces are better** than one general-purpose interface”. Which means we should not enforce clients to **implement interfaces that they don't use**. So Instead of creating one big interface we can break down it to smaller, more **focused interfaces**. Segregating (separating) one big interface into many small interfaces also help us to achieve **single responsibility principle**.

#### Dependency Inversion Principle

One should “**depend upon abstractions, [not] concretions** (implementations)". Abstractions should not depend on the details whereas the **details should depend on abstractions**. High-level modules should not depend on low level modules.

During the process of the application design, **lower-level components are designed to be consumed by higher-level components**. In this Process, **higher-level components depend directly upon lower-level components** to achieve some task. This dependency **limits the reuse opportunities of the higher-level components** and ends up in a bad design. Hence we should **apply an Abstraction to decouple these layers**. **Adapter Design pattern** can be seen as an example which is applying the dependency inversion principle. The high-level class defines its own **adapter interface** which is the abstraction that the other high-level classes depend on. **Other implementations of DIP** are using Inversion of Control, Service Locater, unity containers etc.

## Test Driven Development (TDD)

This is a programming practice that asks us to **write any code only if an automated test is failing**. Hence, we've to start with the design development of automated tests. In Java, there are several frameworks to write automated unit tests like **JUnit** and **TestNG**. This leads to software that always works as expected. As we always start with tests, we incrementally **add working code in small chunks**. Also, we only add code if the new or any of the old tests fail. Which means that it leads to reusability as well.

## Reference

https://www.baeldung.com/java-clean-code#overview

https://www.youtube.com/watch?v=gnKx1RW_2Rk

https://x-team.com/blog/principles-clean-code/








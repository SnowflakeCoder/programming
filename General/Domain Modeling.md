# Domain Modeling

DDD (**Domain Driven Design**)

## What is a domain model?

- “The Domain Model is your **organized and structured knowledge of the problem**. The Domain Model should represent the vocabulary and key concepts of the problem domain and it should **identify the relationships among all of the entities** within the scope of the domain.”
- “A domain model is a visual representation of conceptual classes or real-world objects in a domain of interest.”
- “A Domain Model creates a web of **interconnected objects**, where each object represents some meaningful individual, whether as large as a corporation or as small as a single line on an order form.” 
- “… a conceptual model of the domain that **incorporates both behavior and data**.”
- Domain model is a **structured visual representation of interconnected concepts** or real-world objects that incorporates vocabulary, **key concepts**, **behavior**, and **relationships** of all of its entities.

A domain model is illustrated with a **set of class diagrams** which may show:

- domain objects or conceptual classes
- associations between conceptual classes
- attributes of conceptual classes

Domain Modeling is understood as **abstract modeling**. It additionally **captures the apparent relationships** among these objects. The objects known throughout domain analysis is classified into 3 types:

- ## **Boundary objects**:

  The boundary objects area unit those with that the actors move. These embrace screens, menus, forms, dialogs, etc. The boundary objects area unit is **chiefly answerable for user interaction**. Therefore, they ordinarily **don’t embrace any process logic**. However, they will be answerable for **confirming inputs, formatting, outputs, etc**. The boundary objects were earlier being known as **interface objects**. A recommendation for the initial identification of the boundary categories is to outline one boundary category per actor/use case try.

- ## Entity objects:

  These ordinarily **hold info like information tables and files** that require to outlast use case execution,  they’re ordinarily **answerable for storing information**, winning information, and performing some elementary styles of operation that don’t amendment usually.

- ## Controller objects:

  The controller objects **coordinate the activities** of a collection of entity objects and interface with the boundary objects to **produce the general behavior of the system**. The responsibilities appointed to a controller object area unit closely associated with the belief of a particular use case. The controller objects effectively **decouple the boundary and entity objects from each other** creating the system **tolerant to changes** of the computer program and process logic.

# Service-Oriented Architecture

Service-Oriented Architecture (SOA) is a style of software design where **services are provided to the other components by application components**, through a **communication protocol** over a network. In service oriented architecture, a number of services communicate with each other, in one of **two ways**: through passing data or through two or more services coordinating an activity.

## SOA building blocks

There are **three roles** in each of the Service-Oriented Architecture building blocks: 

- service provider; 
- service broker, service registry, service repository; 
- service requester/consumer.

The service provider works in conjunction with the service registry, debating the **whys and hows of the services being offered**, such as security, availability, what to charge, and more. This role also **determines the service category** and if there need to be any trading agreements.

The service broker **makes information regarding the service available to those requesting it**. The scope of the broker is determined by whoever implements it.

The service requester **locates entries in the broker registry** and then binds them to the service provider. They may or may not be able to access multiple services; that depends on the capability of the service requester.

Implementing Service-Oriented Architecture
Typically, Service-Oriented Architecture is **implemented with web services**, which makes the “**functional building blocks accessible over standard internet protocols**.”

An example of a web service standard is **SOAP**, which stands for **Simple Object Access Protocol**. SOAP is a **messaging protocol specification** for exchanging structured information in the implementation of web services in computer networks. Other options for implementing Service-Oriented Architecture include **Jini**, **COBRA**, or **REST**.

## Why SOA Is Important

- Use Service-Oriented Architecture to **create reusable code**: This cut down on time spent on the development process. Service-Oriented Architecture also **allows for using multiple coding languages** because everything runs through a central interface.
- Use Service-Oriented Architecture to **promote interaction**: With Service-Oriented Architecture, a standard form of communication is put in place, allowing the various systems and platforms to **function independent of each other**.
- Use Service-Oriented Architecture **for scalability**: It’s important to be able to scale a business to meet the needs of the client, however certain dependencies can get in the way of that scalability. Using Service-Oriented Architecture cuts back on the client-service interaction, which allows for greater scalability.
- Use Service-Oriented Architecture to **reduce costs**: With Service-Oriented Architecture, it’s possible to reduce costs while still “maintaining a desired level of output.” Using Service-Oriented Architecture allows businesses to limit the amount of analysis required when developing custom solutions.


The Difference Between Service-Oriented Architecture and SaaS
Image for post
We’ve talked quite a bit about what Service-Oriented Architecture is and how it can be used to advance your business. But there’s also SaaS (Software as a Service), which can also be used to advance your business. You may be wondering what SaaS is and how it differs from Service-Oriented Architecture. In brief, the resources available through SaaS are software applications. A key component is that the SaaS infrastructure is “available to, but hidden, from users.” An advantage of SaaS is that users don’t have to both install and maintain software, which eliminates any complex requirements. With SaaS, the customer also doesn’t require any up-front licensing, which leads to lower costs because providers are only maintaining a single application.
Differences Between Service-Oriented Architecture and Microservices
Image for post
Microservices, also known as Microservice Architecture, is an “architectural style that structures an application as a collection of small autonomous services, modeled around a business domain.”
While microservices and Service-Oriented Architecture are similar in some ways, the key differences come in their functionality. Services is, obviously, the main component of both. There are four basic types of services:
Functional service: these define core business operations
Enterprise service: these implement the functionality defined by the functional services
Application service: these are confined to specific application content
Infrastructure service: implements non-functional tasks such as authentication, auditing, security, and logging
As you can see, each of these services builds on the one before it, creating a system that is not only easy to use, but provides you with a variety of ways to manage your business. As with any functionality, it’s a matter of figuring out what works best for you and your business.
You can read more about service-oriented architecture and microservices here.

# multi-tenancy

Multi-tenancy is an architecture in which a **single instance of a software application serves multiple customers**. Each customer is called a tenant. Tenants may be given the ability to customize some parts of the application, such as the color of the user interface (UI) or business rules, but they cannot customize the application's code.

In a **multi-tenant architecture**, multiple instances of an application operate in a shared environment. This architecture is able to work because **each tenant is integrated physically**, but logically separated; meaning that a single instance of the software will run on one server and then serve multiple tenants. In this way, a software application in a multi-tenant architecture can share a dedicated instance of configurations, data, user management and other properties. Multi-tenancy applications can share the same users, displays, rules -- although users can customize these to an extent -- and database schemas, which tenants can also customize.

## Spring Boot Internals

### Spring MVC Application

A typical Spring MVC Application basically need 3 types of jars.

- Spring Core jars
- View Resolvers
- Tomcat Server Jars

If you go into details you can see a Spring MVC Application generally need below libraries.

- Spring core jar file
- AOP jar
- Web jar
- MVC jar
- Servlet container jar
- Server jars for deployment like tomcat, jetty
- JDBC jar - if you want to interact with the DB.
- ORM jar
- Spring transaction jar - if you are managing the transactions
- Logging jar - for debugging purposes.

You need to put all these jars and create the instances and put that into spring context, so that when container starts it can take those into account. So if you want any other things also you need to put libraries of that and you need to put that also into context. These are the things that you **need to do manually**.

Apart from this you also need to take care of the configurations like

- Which port application is running.
- Properties
- Profiling - dev, stage and prod environments.

Every developer who is developing an application has to do all these things (**repetitive things**) manually. Ideally most of the time of a developer should **spend around the business logic** to build the application. Instead every developer spending a good amount of time in adding these jars and then putting these into context and then in the container. To avoid this we need the **spring boot concept**.

### Spring boot concept

**Starters and AutoConfigration** are the two important components of spring boot. Most of the libraries and configurations mentioned above are **common for all developers** for a specific type applications. So if we can put all these libraries into a single jar (**starter**) and add that single jar, all of these gets downloaded automatically. Similarly we can also do above configurations automatically (**AutoConfigration**) by putting some default configurations and if the developer want to change something he can always go ahead and change that thing. But these are common requirements and most of the developers are going to follow that. So to achieve this we need spring boot concept.

Main thing that spring boot does is all the libraries that you were doing it manually, spring boot use **starters**. So if you want to add DB specific libraries you can use **spring JDBC starter jar**. When you add that jar all **related dependencies get downloaded automatically** because those are configured into that jar. If you are developing a web MVC application you can use **spring starter web**. Similarly for the configuration part Spring boot use **Auto configuration**. They automatically configure the required things. So if you want to use tomcat, you **don't have to download the tomcat** and deploy the application there. Spring boot automatically embedded the tomcat into the starter and configure that (def port 8080) when you start your application. So with spring boot, you don't have to **worry anything about framework level things** and the developer can spend more time on building the business logic.

So **Spring boot starter** reduces build's dependencies and **AutoConfigurator** reduces the spring configuration. Spring Boot makes it **easy to create** Spring-powered, production-grade applications and services **with absolute minimum fuss**. It takes an **opinionated view of the Spring platform** so that new and existing users can quickly get to the bits they need. You can use Spring Boot to create **stand-alone Java applications** that can be started using java -jar or more traditional WAR deployments. 

Our primary goals are:

- Provide a **radically faster and widely accessible** getting started experience for all Spring development
- Be opinionated out of the box, but get out of the way quickly as requirements start to diverge from the defaults.
- Provide a **range of non-functional features** that are **common to large classes of projects** (e.g. embedded servers, security, metrics, health checks, externalized configuration)
- Absolutely no code generation and no requirement for XML configuration

### Spring boot starter

Spring Boot Starters are a set of **convenient dependency descriptors** that you can include in your application. You get a one-stop-shop for all the Spring and related technology that you need without having to hunt through sample code and copy paste loads of dependency descriptors. For example, if you want to get started using Spring and JPA for database access just include the **spring-boot-starter-data-jpa** dependency in your project, and you are good to go.

The starters contain a lot of the dependencies that you need to **get a project up and running quickly** and with a consistent, supported set of managed transitive dependencies. When you add a starter the spring boot will automatically download and add all the related dependencies. These individual dependencies may **include other starters** also so its related dependencies will also get downloaded. All these related dependencies are mentioned in the starter **dependency section in the pom/gradle** file.

### AutoConfigurator

How spring boot doing the autoconfiguration part when you add the dependencies?



Spring boot is known as opinionated view of the Spring platform because it comes with default configuration that works when you start your application. If you want to customize it then you can go and change that. Spring boot starter will contain spring boot **autoconfigure dependency.**

Spring.factories

Any autoconfigure dependency that we use should have **spring.factories** and spring boot look for these spring.factories file, find the list of dependencies in **key value pairs** and loads that into the context. Spring boot will loads the files mentioned in the key value pairs.

Spring.factories file contains **different categories of key-value pairs(configurations).**

- Initializers
- Application listeners
- Auto configuration import listeners
- Auto configuration import filters
- Auto configure - should be there for any starter.
  - First four are very specific to spring boot framework, but Auto configure should be there for all the starter. Here you need to mention the autoconfiguration class where you are autoconfiguring the things for your application.
- Failure analyzers
  - When starting/using an application there is an issue - how to analyze those failures and show the proper message to the user.
- Template availability providers
  - Adding the presentation part of applications like HTML, ThymeLeaf etc.

Auto configuration is about how spring boot framework is loading all of these files into the context.

SpringFactoriesLoader class

Spring boot framework uses SpringFactoriesLoader class to  looks into every jar, looks for **Meta-INF/spring.factories** file for the auto configuration part and load whatever inside there.

Spring-configuration-metadata.json

This file contains the configurations for the properties that you are adding in your application.properties and application.yaml. Those are also loaded by the application framework. This needs to be given into the starter.

This file s in json format and contains a json array of groups with fields **name, type, sourceType, sourceMethod (optional), default values**. This will define the default configuration like server.port = 8080. When creating custom starter we need to specify this file.



Annotations

Annotations that are available in the library are processed by the ASM. ASM (ASM -> assembly) is java bytecode engineering library (provides low-level bytecode manipulation capabilities)

Categories of condition annotations are 

Class conditions

Bean Conditions

Property Conditions

Resource Conditions

Web application conditions

SpEL (Spring Expression Language) expression conditions









### References

https://www.youtube.com/watch?v=3eA8AiCV5oE

https://www.youtube.com/watch?v=oSVwNqwkw-M

https://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/html/boot-features-developing-auto-configuration.html


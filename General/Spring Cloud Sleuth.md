# Distributed Tracing

Distributed tracing, also called distributed request tracing, is a method used to **profile and monitor applications**, especially those built using a microservices architecture. Distributed tracing helps **pinpoint where failures occur and what causes poor performance**. It is particularly well-suited to debugging and monitoring modern distributed software architectures, such as microservices. Developers can use distributed tracing to help debug and optimize their code. Distributed tracing requires that software developers **add instrumentation to the code** of an application, or to the frameworks used in the application.

## Greenfield and Brownfield Applications

Greenfield is a **term from the construction industry** that refers to **undeveloped land**. In the IT world, greenfield describes a software project that is **developed from scratch** rather than built from an existing program. It is often contrasted with "brownfield," which describes **software built from an existing program**. Greenfield software development is generally **more flexible** since a new program does not need to fit a specific mold.  Additionally, greenfield software **does not need to be backwards compatible** with older versions of a program. There is <u>no need to support legacy file formats</u> or include previous features to meet end user expectations.

While greenfield projects are open ended, <u>developing software from scratch involves inherent risk</u>. The **interface may not be well-received** and may need to be altered or redesigned to be more user-friendly. It make **take several updates** before a greenfield application is successful in the marketplace. 

## Microservices

Microservices have become the **default choice for greenfield applications**. Microservices provide the type of **decoupling required** for a full digital transformation, allowing individual teams to innovate at a far greater speed than ever before. Microservices are just like regular distributed systems, so they <u>exacerbate the well-known problems that any distributed system faces</u>, like lack of visibility into a business transaction across process boundaries. We are talking about **hundreds of services**, it's almost **impossible to map the interdependencies and understand the path of a business transaction** across services and their versions. Also it's extremely <u>common to have multiple versions of a single service</u> running in production at the same time.

### Observability

A system is said to be observable when we can **understand its state based on the metrics, logs, and traces** it emits. We need to be able to **aggregate the metrics for all instances** of a given service, perhaps grouped by version. Metrics solutions like **Prometheus** are very popular in tackling this aspect of the **observability problem**. Similarly, we need **logs to be stored in a central location**, as it's impossible to analyze the logs from the individual instances of each service. **Logstash** is usually applied here, in combination with a backing storage like **Elasticsearch**. And finally, we need to **get end-to-end traces to understand the path** a given transaction has taken. This is where distributed tracing solutions come into play.

## Distributed tracing

In **monolithic web applications, logging frameworks provide enough** capabilities to do a **basic root-cause analysis when something fails**. A developer just needs to place log statements in the code. Information like <u>"context" (usually "thread") and "timestamp" are automatically added</u> to the log entry, making it <u>easier to understand the execution of a given request</u> and correlate the entries.


In microservices architectures, <u>logging alone fails to deliver the complete picture</u>. A common strategy is **creating an identifier at the very first building block** of our transaction and propagating this identifier across all the calls, probably by **sending it as an HTTP header** whenever a remote call is made. This technique is one of the core concepts of any modern distributed tracing solution. In distributed tracing the <u>data structure that holds tracing data is more specialized</u>, so we can also **identify causality**. Having a dedicated data structure also allows distributed tracing to record not only a message in a single point in time but also the start and end time of a given procedure.

**Jaeger** is an **open source distributed tracing solution**,  marks each service with a color to make it easier to see the process boundaries. 

https://opensource.com/article/18/9/distributed-tracing-microservices-world

Tools like **Prometheus, Logstash, OpenTracing, and Jaeger** provide the pieces to bring observability to your application. 



# Spring Cloud Sleuth

Spring Cloud Sleuth provides **Spring Boot auto-configuration for distributed tracing**. Underneath, Spring Cloud Sleuth is a layer over a **Tracer library named Brave**.

Sleuth configures everything you need to get started. This includes where trace data (spans) are reported to, how many traces to keep (sampling), if remote fields (baggage) are sent, and which libraries are traced.

We maintain an example app where two Spring Boot services collaborate on an HTTP request. Sleuth configures these apps, so that timing of these requests are recorded into Zipkin, a distributed tracing system. Tracing UIs visualize latency, such as time in one service vs waiting for other services.

https://docs.spring.io/spring-cloud-sleuth/docs/current-SNAPSHOT/reference/html/
## General

- **Greenfield Applications** => a software project that is **developed from scratch**
  - more flexible + does not need to be backwards compatible + no need to support legacy.
- **brownfield Applications** => built from an existing program.
- A system is said to be **observable** when we can **understand its state based on the metrics, logs, and traces** it emits. Metrics solutions like **Prometheus** are very popular in tackling the **observability problem**. Tools like **Prometheus, Logstash, OpenTracing, and Jaeger** provide the pieces to bring observability to your application. 

## Microservices

- default choice for greenfield applications.
- lack of visibility into a business transaction across process boundaries (disadvantage)
- **Due to hundreds of micro-services**, impossible to map the interdependencies and understand the path of a business transaction.
- common to have multiple versions of a single service running in production at the same time

## Distributed Tracing

- a method used to **profile and monitor applications** built using a microservices architecture.
- helps **pinpoint where failures occur and what causes poor performance**.
- Developers can use distributed tracing to help `debug and optimize` their code.
- requires to **add instrumentation to the code** of an application.
- **request key**: creating an identifier at the very first building block of our transaction and <u>propagating this identifier across all the calls</u> (sending it as an HTTP header).
- **Jaeger** is an **open source distributed tracing solution**,  marks each service with a color to make it <u>easier to see the process boundaries</u>.

## Spring Cloud Sleuth

- provides **Spring Boot auto-configuration for distributed tracing**.
  - Using Tracer library named **Brave**.
- **Zipkin**, a distributed tracing system.
- 
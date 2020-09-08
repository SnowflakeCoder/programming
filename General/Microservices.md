# Microservices

## Digital Transformation

Digital Transformation (DT or DX) is the **adoption of digital technology** to transform services or businesses, through **replacing non-digital or manual processes with digital processes** or replacing **older digital technology with newer** digital technology. Digital solutions may enable - in addition to **efficiency via automation** - new types of innovation and creativity, rather than simply enhancing and supporting traditional methods. Digital transformation is the cultural, organizational and operational change of an organization through a **smart integration of digital technologies, processes** and competencies across all levels and functions in a staged and strategic way.

Digital transformation marks a **radical rethinking of how an organization uses technology, people and processes** to fundamentally change business performance. Such sweeping changes are typically undertaken in **pursuit of new business models and new revenue streams**, **driven by changes in customer expectations around products and services**.

## Service-oriented architecture (SOA)

Service-oriented architecture (SOA) is an **enterprise-wide approach to software development** that takes advantage of **reusable software components, or services**. <u>Each service is comprised of the code and data integrations required to execute a specific business function</u>. The **service interfaces provide loose coupling**, which means that they can be **called with little or no knowledge** of how the integration is implemented underneath. Because of this loose coupling and the way the services are published, developers can **save time by reusing components** in other applications across the enterprise.

## Microservices

**Martin Fowler**, describes microservice architecture as "an approach to **developing a single application as a suite of small services**, each running in its own process and **communicating with lightweight mechanisms**, often an HTTP resource application programming interface (API)."

Monolithic applications **require complete duplication onto new servers to scale**, eating up a lot of computing resources. Microservice architecture **allows small, independent components to be deployed to different servers**, where they can be scaled or replicated as needed.

Like SOA, microservices architectures are made up of **loosely coupled, reusable, and specialized components**. However, **rather than being adopted enterprise-wide**, microservices are typically used to **build individual applications** in a way that makes them **more agile, scalable, and resilient**.

Microservices are a **true cloud native architectural approach**, and by using them, teams can **update code more easily**, use **different stacks** for different components, and **scale the component independently** of one another, reducing the waste and cost associated with having to scale entire applications because a single feature might be facing too much load.

## SOA vs. Microservices

https://www.ibm.com/cloud/blog/soa-vs-microservices

The main distinction between the two approaches comes down to **scope**, SOA has an **enterprise scope**, while the microservices architecture has an **application scope**.

### Reuse

In SOA, **reuse of integrations is the primary goal**, and at an enterprise level, striving for some level of reuse is essential. 

In microservices architecture, creating a microservices component that is reused at runtime throughout an application **results in dependencies that reduce agility and resilience**. Microservices components generally **prefer to reuse code** by copy and **accept data duplication to help improve decoupling**.

### Synchronous calls

The reusable services in SOA are available across the enterprise using predominantly **synchronous protocols such as RESTful APIs**.

However, within a microservice application, **synchronous calls introduce real-time dependencies**, resulting in a **loss of resilience**. It may also **cause latency**, which impacts performance. Within a microservices application, <u>interaction patterns based on asynchronous communication are preferred,</u> such as **event sourcing**, in which a publish/subscribe model is used to enable a microservices component to remain up to date on changes happening to the data in another component.

### Data duplication

A clear aim of providing services in an SOA is for all applications to synchronously get hold of and make **changes to data directly at its primary source**, which reduces the need to maintain complex data synchronization patterns.

In microservices applications, each **microservice ideally has local access to all the data** it needs to ensure its independence from other microservices, and indeed from other applications, even if this means some duplication of data in other systems. Of course, this duplication adds complexity, so it must be balanced against the gains in agility and performance, but this is accepted as a reality of microservices design.



## Key Tools

Key tools in the microservices ecosystem include:

Containers
Container orchestrators
API Gateways
CI/CD
Load balancers
Service Registry & Service Discovery



https://www.ibm.com/cloud/learn/microservices

https://www.techrepublic.com/article/microservices-a-cheat-sheet/


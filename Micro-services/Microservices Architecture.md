## Microservices Architecture

The microservices architecture is not a new approach; its soul was always there in the form of **SOA (Service Oriented Architecture)**, web services, and in a **modular and layered architecture**. Big companies like Netflix, Amazon have used microservices architecture to **scale and ease out the continuous delivery of their services**. Microservices gaining momentum primarily due to the following factors:

1. **Not getting the desired output** from monolith architecture.
2. Availability of tools and technologies to develop and deploy microservices applications with ease.
3. Wide adaptation of Infrastructure as a Service (IaaS), like AWS, GCP for **easy DevOps operations**.
4. Microservices is more suited to applications, which are very **large and complex**. Big technology product company adaptation for microservices architecture.

### Disadvantages of monolithic applications

In **monolithic architectural design** we create a big cumbersome application with **all modules tightly coupled inside a single executable**, which is typically deployed on a web or application server. There are some disadvantages to this architectural design:

- **No frequent and easy releases** - Due to <u>tight coupling between components</u>, its difficult to do easy and frequent releases. <u>**Release planning** takes a lot of time</u> from various groups. Frequent release is discouraged for making sure the application should not break due to the newly released feature.
- **Problem in continuous delivery** - In case of bigger monolithic applications, <u>**deployment times** can be frustratingly long and slow</u>. If a <u>single change to the application would require the entire application to be redeployed,</u> then this could become an obstacle to frequent deployments and to continuous delivery.
- **Difficult to manage team and project** - Even a modularized application has **interdependency** in terms of deployment and release. It takes a toll in terms of **time and effort to plan the release** and **manage tightly coupled interdependent** modular development.
- **Expensive scalability & performance** - Scale a monolithic application, **cost is very high**.
- **Lack of technology diversity** - When we choose a technology stack for a monolithic application, we consider a **balanced stack** which can serve well for all of our requirements. We **cannot employ specific technology for specialized needs**.
- **Not easy to replace components** - It is very difficult to replace any component with a better design or performance **without affecting the whole architecture**.

### What is Microservices?

Microservices architectural style defines a setup, where **application components are standalone** applications of their own. These independent application **components talk to each other either** using **RMI** (Remote Method Invocation), **Restful** Web Services or **Push Messaging**. While designing systems in microservices architecture, we should be **identifying independent components/modules** appropriately. These components will be **developed separately**. They will follow **their own development and deployment lifecycle**. If we need data from various components for a single request then we will have a **API gateway** or **front controller** which will aggregate data from these components and give it back. 

Characteristics of microservices architecture based application are as follows:

- **Service enabled**, independently running components.
- Product mentality over project.
- Smart components using **simple communication channels** like simple REST API protocol or lightweight messaging queue.
- **Decentralize standards**. Each independent component can use their <u>exclusive standard for development and deployment</u>.
- **Decentralized data management**. Individual components have their own data storage.
- **Automated infrastructure management**. For deployment of independent components, we need to rely on automated infrastructure management to reduce the complexity.
- **Application design considering failure in mind**. There are several **independent moving parts** in applications. In the event of the receiver not getting a response, it should be handled gracefully.
- Evolutionary design for getting the **best possible decomposed system**, which can be replaced and upgraded without affecting its collaborator.

### Bad Parts / additional work

- **Team communication overhead** - Need for team communication. Teams need to make sure that <u>updates in one team's service does not break another's team functionality</u>.
- **Formal documentation overhead** - Every individual component needs to keep updated schemas and interface documents all the time. It <u>helps other teams who are using the service</u>.
- **Non uniform application** - We can choose a different technology stack for different components. It leads to a problem of non-uniform application design and architecture. It **can increase maintenance costs** in the long run.
- **Dev-Ops complexity** - We need to have a **mature DevOps team** to handle the complexity involved in maintaining microservices based applications. Due to several moving parts of the application, it becomes complex and requires a level of expertise.
- **Increased resource use** - **Initial investment** to run these applications is high because all the independently running components need <u>their own runtime containers with more memory and CPU</u>.
- **Increase network communication** - Independently running components interact with each other using the network. Such systems <u>require reliable and fast network connections</u>.
- **Marshalling and unmarshalling** - When one component needs data, the sender marshals the data from its internal representation, while the receiver unmarshals data in its own representation before use. This definitely **requires more processing** in comparison to conventional application architecture.
- **Network security** - Inter service communication needs to be secured to avoid any security breach. Due to several moving parts, these applications are **more prone to security vulnerabilities**.
- **Testing** - Testing of such applications is definitely harder in comparison to monolith applications.
- **Production monitoring Cost** is very high for microservices based applications.
- **High upfront cost** - Running multiple applications will incur more cost in comparison to monolith applications.

### When and How Should We Use It?

 We should use a microservices architecture for any product/project in these two approaches:

- Monolith only or monolith first approach
- Microservices first approach

#### Monolith Only or First Approach

We should be opting for monolith only or first approach in the following circumstances:

- Business is **not ready to invest in the upfront cost** that microservices based applications incur.
- Inability for business to **foresee the value provided by the microservices** first approach.
- Unavailability of suitable **manpower** to build and run microservices-based applications.
- Software delivery with **tight time constraints**: Sometimes monolith helps in entering the market very quickly.
- When state of **tools and technologies available** to support smooth deployment of microservices application, is a concern.

When a monolith application become successful or needs <u>serious help for scale and performance</u>, we may opt for microservices. Sometimes it’s best to **use monolith for just prototyping**. We can opt for microservices in two ways:

- Extend the well designed modular components from the monolith: Usually we find business people supporting monolith first design, thinking it will be easy if required, to convert a modularly designed, monolith application to microservices at a later stage. In fact, they opt for a **modular monolith application**, to reduce the cost they may incur to develop microservices should the need arise. But this is a distant dream, often far from reality. 
- Recreate the microservices application from scratch and dump the existing monolith application: Most of the time microservices applications are developed from scratch **because of bad modularity in monolith applications**.

#### Microservices First Approach

When we start developing applications we always wish to keep them modular. Each <u>module should have a distinct set of responsibilities</u>. We try to do so to **reduce the complexity of applications** to achieve extensionality and maintenance. But in monolith applications, we are always tempted to develop things very quickly and we get the opportunity to do so because there is **no hard boundary defined**. So we may **lose its modularity**. Due to these overlapped modules we face a **hard time optimizing and scaling applications**. 

Even idle modular monolith applications will have a **centralized database for all of its modules**. But microservices applications provides a **hard line between modules** along with **decentralized data storage**. By design it is **difficult for developers to cross the line** and it helps in **enforcing real modular application**. We should therefore be opting for the microservices first approach when:

- Modularity and decentralization is an important aspect from the beginning of any project.
- The application in focus will have **high volume transaction or traffic**.
- **Preference for long term benefits** in comparison to those in the short term.
- Availability of the right set of people to design, develop and deploy applications quickly in the initial phase: **Initial effort to start a microservices based project is more** in comparison to a monolith one.
- **Commitment to use cutting edge tools and technologies**: Microservices are very young architectural approaches; tools and technology required to support it are very new or in rapid change mode.

## Designing a Microservices Architecture for Failure

A Microservices architecture makes it possible to **isolate failures through well-defined service boundaries**. But like in every distributed system, there is a higher chance for **network, hardware or application level issues**. To minimize the impact of partial outages we need to build **fault tolerant services** that can **gracefully respond to certain types of outages**.

### The Risk of the Microservices Architecture

- **Network layer to communication**: The microservices architecture <u>moves application logic to services</u> and <u>uses a network layer to communicate</u> between them. Communicating over a network instead of in-memory calls brings **extra latency** and **complexity** to the system. The increased complexity of the distributed system leads to a **higher chance of particular network failures**. 
- **No control over service dependencies**: One of the main advantages of a microservices architecture is <u>teams can independently design, develop and deploy their services</u>. They have **full ownership** over their service's lifecycle. It also means that teams have **no control over their service dependencies** as it's more likely managed by a different team. So **provider services can be temporarily unavailable** by broken releases, configurations, and other changes as they are controlled by someone else.

## Graceful Service Degradation (Failover Logics)

One of the best advantages of a microservices architecture is that you can **isolate failures** and achieve **graceful service degradation** as **components fail separately**. It's hard to implement this kind of graceful service degradation as applications in a distributed system depend on each other, and you need to **apply several failover logics** to prepare for <u>temporary glitches and outages</u>.

You can also create a shared library for your company that contains **standard reliability solutions**.

### Change management

Roughly 70% of the outages are caused by **changes in a live system**. When you change something in your service (deploy a new version or change some configuration) there is always a <u>chance for failure or the introduction of a new bug</u>. In a microservices architecture, services depend on each other. This is why you should <u>minimize failures and limit their negative effect</u>. To deal with issues from changes, you can implement **change management strategies** and **automatic rollouts**.

- When you deploy new code, or change some configuration, you should <u>apply these changes to a subset of your instances gradually</u>, monitor them and even **automatically revert the deployment** if you see that it has a negative effect on your key metrics. Always revert your changes when it’s necessary.
- Another solution could be that you **run two production environments**. You always deploy to only one of them, and you only point your load balancer to the new one after you verified that the new version works as it is expected. This is called **blue-green**, or **red-black deployment**.

### Health-check and Load Balancing

Instances continuously start, restart and stop because of failures, deployments or autoscaling. It makes them temporarily or permanently unavailable. To avoid issues, your **load balancer should skip unhealthy instances** from the routing as they cannot serve your customers' or sub-systems' need. Application instance health can be determined via external observation. You can do it with repeatedly calling a **GET /health endpoint** or via **self-reporting**. Modern service discovery solutions continuously **collect health information from instances** and configure the load-balancer to route traffic only to healthy components.

### Self-healing

Self-healing can help to recover an application. We can talk about self-healing when an application can do the **necessary steps to recover from a broken state**. In most of the cases, it is **implemented by an external system** that <u>watches the instances health and restarts</u> them when they are in a broken state for a longer period. However, in certain situations it can cause <u>trouble by continuously restarting the application</u>. This might happen when your application cannot give positive health status because it is overloaded or its database connection times out. Implementing an **advanced self-healing solution** which is prepared for a delicate situation - like a lost database connection - can be tricky. In this case, you need to add extra logic to your application to **handle edge cases** and let the external system know that the instance is **not needed to restart immediately**.

### Failover Caching

Services usually fail because of network issues and changes in our system. However, most of these outages are temporary thanks to self-healing and advanced load-balancing. But we should find a solution to <u>make our service work during these glitches</u>. This is where failover caching can help and provide the necessary data to our application. Failover caches usually use **two different expiration dates**; a shorter that tells how long you can use the cache in a normal situation, and a longer one that says how long can you use the cached data during failure. It’s important that you can only use failover caching when it <u>serves the outdated data better than nothing</u>.

To **set cache and failover cache**, you can use standard **response headers in HTTP**. For example, with the **max-age** header you can specify the maximum amount of <u>time a resource will be considered fresh</u>. With the **stale-if-error** header, you can determine <u>how long should the resource be served from a cache</u> in the case of a failure.

![Description: Microservices Failover Caching](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image002.png)

### Retry Logic

There are certain situations when we cannot cache our data or we want to make changes to it, so we can retry our action as we can expect that the resource will recover after some time or our load-balancer sends our request to a healthy instance. You should be careful with adding retry logic, as a **larger amount of retries can make things even worse** or even **prevent the application from recovering**. In distributed system, a microservices system retry can <u>trigger multiple other requests</u> or retries and start a **cascading effect**. To minimize the impact of retries, you should **limit the number** of them and use an **exponential backoff algorithm** to continually increase the delay between retries until you reach the maximum limit.

As a retry is initiated by the client and the client doesn't know that the operation failed before or after handling the request, you should prepare your application to handle **idempotency**. For example, when you retry a purchase operation, you shouldn't double charge the customer. Using a **unique idempotency-key** for each of your transactions can help to handle retries.

### Rate Limiters and Load Shedders

A rate limiter can hold back traffic peaks. You can also **hold back lower-priority traffic** to give enough resources to critical transactions. Rate limiting is the technique of defining how many requests can be received or processed during a timeframe. With rate limiting, you can **filter out customers and microservices who are responsible for traffic peaks**, or you can ensure that your application doesn’t overload until autoscaling can’t come to rescue. A different type of rate limiter is called the **concurrent request limiter**. It can be useful when you have expensive endpoints that shouldn’t be called more than a specified times, while you still want to serve traffic.

A **fleet usage load shedder** can ensure that there are always enough resources available to serve critical transactions. It **keeps some resources for high priority requests** and doesn’t allow for low priority transactions to use all of them. A load shedder makes its decisions based on the whole state of the system, rather than based on a single user’s request bucket size. Load shedders **help your system to recover**, since they **keep the core functionalities working** while you have an ongoing incident.

### Fail Fast and Independently

In a microservices architecture we want to prepare our services to **fail fast and separately**. We also want our components to fail fast as we don't want to wait for broken instances until they timeout. Nothing is more disappointing than a hanging request and an unresponsive UI. It's not just wasting resources but also screwing up the user experience. Our **services are calling each other in a chain**, so we should pay an extra attention to **prevent hanging operations** before these delays sum up.

The first idea would be applying timeouts for each service calls. The problem with this approach is that you cannot really know what's a good timeout value as there are certain situations when network glitches and other issues happen that only affect one-two operations. In this case, you probably don’t want to reject those requests if there’s only a few of them timeouts. Achieving the **fail fast paradigm in microservices by using timeouts is an anti-pattern** and you should avoid it. Instead of timeouts, you can apply the **circuit-breaker pattern** that depends on the **success / fail statistics of operations**.

### Bulkheads

Bulkhead is used in the industry to partition a ship into sections, so that <u>sections can be sealed off if there is a hull breach</u>. **To isolate issues on service level, we can use the bulkhead pattern**. The concept of bulkheads can be applied in software development **to segregate resources**. By applying the bulkheads pattern, we can **protect limited resources from being exhausted**. For example, we can use **two connection pools instead of a shared** on if we have two kinds of operations that communicate with the same database instance where we have limited number of connections. As a result of this **client - resource separation**, the operation that timeouts or overuses the pool won't bring all of the other operations down.

### Circuit Breakers

To limit the duration of operations, we can use timeouts. Timeouts can prevent hanging operations and keep the system responsive. However, using static, fine tuned timeouts in microservices communication is an anti-pattern as it's impossible to come up with the right timing limitations that work well in every case.

Instead we can use circuit breakers to deal with errors. You can protect resources and help them to recover with circuit breakers in a distributed system where a **repetitive failure can lead to a snowball effect and bring the whole system down**. A circuit breaker opens when a particular type of error occurs multiple times in a short period. An open circuit breaker prevents further requests to be made. Circuit breakers usually **close after a certain amount of time, giving enough space for underlying services to recover**.

Keep in mind that not all errors should trigger a circuit breaker. For example, you probably want to skip client side issues like requests with 4xx response codes, but include 5xx server-side failures. Some circuit breakers can have a **half-open state** as well. In this state, the service sends the first request to check system availability, while letting the other requests to fail. If this first request succeeds, it restores the circuit breaker to a closed state and lets the traffic flow. Otherwise, it keeps it open.

![Description: Microservices Circuit Breakers](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image004.jpg)

### Testing for Failures

You should continually test your system against common issues to make sure that your services can survive various failures. You should **test for failures frequently** to **keep your team prepared for incidents**. For testing, you can use an external service that identifies groups of instances and randomly terminates one of the instances in this group. With this, you can prepare for a single instance failure, but you can even shut down entire regions to simulate a **cloud provider outage**. One of the most popular testing solutions is the **ChaosMonkey resiliency tool** by Netflix.

### Key Takeways

Implementing and running a reliable service takes a lot of effort and also costs money to your company. Reliability has many levels and aspects, so it is important to find the best solution for your team. You should make reliability a factor in your business decision processes and allocate enough budget and time for it.

- Dynamic environments and distributed systems, like microservices lead to a **higher chance of failures**.
- Services should **fail separately**, achieve graceful degradation to improve user experience.
- 70% of the outages are caused by changes, reverting code is not a bad thing.
- Fail fast and independently. Teams have no control over their service dependencies.
- **Architectural patterns and techniques** like caching, bulkheads, circuit breakers and rate-limiters help to build reliable microservices.

 

 

# References

https://dzone.com/articles/microservices-architecture-what-when-how

https://blog.risingstack.com/designing-microservices-architecture-for-failure/#:~:text=One%20of%20the%20best%20advantages,degradation%20as%20components%20fail%20separately.&text=Services%20depend%20on%20each%20other%20and%20fail%20together%20without%20failover%20logics.
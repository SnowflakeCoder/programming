## Java Concurrency - Part1

- [Multithreading](#multithreading)
  * [Benefits](#benefits)
  * [Multithreading vs. Multitasking](#multithreading-vs-multitasking)
  * [Concurrency Models](#concurrency-models)
  * [Costs](#costs)
- [Concurrency Models](#concurrency-models-1)
  * [Concurrency Models and Distributed System Similarities](#concurrency-models-and-distributed-system-similarities)
  * [Shared State vs. Separate State](#shared-state-vs-separate-state)
  * [Parallel Workers](#parallel-workers)
  * [Same-threading](#same-threading)
    + [Same-threading: Single-threading Scaled Out](#same-threading--single-threading-scaled-out)
    + [No Shared State](#no-shared-state)
    + [Load Distribution](#load-distribution)
      - [Single-threaded Microservices](#single-threaded-microservices)
      - [Services With Sharded Data](#services-with-sharded-data)
    + [Thread Communication](#thread-communication)
    + [Thread Ops for Java](#thread-ops-for-java)
  * [Concurrency vs. Parallelism](#concurrency-vs-parallelism)
    + [Concurrency](#concurrency)
    + [Parallelism](#parallelism)
  * [Threads](#threads)
  * [Race Conditions and Critical Sections](#race-conditions-and-critical-sections)
    + [Preventing Race Conditions](#preventing-race-conditions)
  * [Thread Safety and Shared Resources](#thread-safety-and-shared-resources)
    + [Local Variables](#local-variables)
    + [Local Object References](#local-object-references)
    + [Object Member Variables](#object-member-variables)
    + [The Thread Control Escape Rule](#the-thread-control-escape-rule)
    + [Immutability](#immutability)
  * [Java Memory Model](#java-memory-model)
    + [Thread stack](#thread-stack)
    + [Hardware Memory Architecture](#hardware-memory-architecture)
    + [Visibility issues of Shared Objects](#visibility-issues-of-shared-objects)
    + [Race Condition issues](#race-condition-issues)
  * [Java Synchronized Blocks](#java-synchronized-blocks)
    + [Synchronized and Data Visibility](#synchronized-and-data-visibility)
    + [What Objects to Synchronize On](#what-objects-to-synchronize-on)
    + [Synchronized Block Limitations and Alternatives](#synchronized-block-limitations-and-alternatives)
    + [Synchronized Block Reentrance](#synchronized-block-reentrance)
    + [Synchronized Blocks in Cluster Setups](#synchronized-blocks-in-cluster-setups)
  * [Java Volatile Keyword](#java-volatile-keyword)
    + [Variable Visibility Problems](#variable-visibility-problems)
    + [volatile Visibility Guarantee](#volatile-visibility-guarantee)
    + [Full volatile Visibility Guarantee](#full-volatile-visibility-guarantee)
    + [Instruction Reordering Challenges](#instruction-reordering-challenges)
    + [volatile is Not Always Enough](#volatile-is-not-always-enough)
    + [Performance Considerations of volatile](#performance-considerations-of-volatile)
  * [ThreadLocal](#threadlocal)
    + [Using a ThreadLocal](#using-a-threadlocal)
    + [Generic ThreadLocal](#generic-threadlocal)
    + [Initial ThreadLocal Value](#initial-threadlocal-value)
    + [Using a ThreadLocal](#using-a-threadlocal-1)
    + [InheritableThreadLocal](#inheritablethreadlocal)
  * [Thread Signaling](#thread-signaling)
    + [Busy Wait](#busy-wait)
    + [wait(), notify() and notifyAll()](#wait----notify---and-notifyall--)
    + [Missed Signals](#missed-signals)
    + [Spurious Wakeups](#spurious-wakeups)
    + [Multiple Threads Waiting for the Same Signals](#multiple-threads-waiting-for-the-same-signals)
- [References](#references)

### Multithreading

Multithreading means that you have **multiple threads of execution** inside the same application. A thread is like a separate CPU executing your application. Thus, a multithreaded application is like an application that has **multiple CPUs executing different parts of the code** at the same time. Usually **a single CPU will share its execution time among multiple threads**, switching between executing each of the threads for a given amount of time. It is also possible to have the threads of an application be **executed by different CPUs**. We need to use multiple threads for an application to be able to <u>utilize all of the CPUs or CPU cores</u>.

#### Benefits

- Better utilization of a single CPU.
  - If one thread is waiting for the response to a request sent over the network, then another thread could use the CPU in the meantime to do something else.
- Better utilization of multiple CPUs or CPU cores.
- Better user experience with regards to responsiveness.
  - If you click on a button and this results in a request being sent over the network, if you use the same thread that is also updating the GUI, then the user might experience the GUI "hanging" while the GUI update thread is waiting for the response for the request. Instead, such a request could be <u>performed by a background thread</u> so the GUI thread is free to respond to other user requests in the meantime.
- Better user experience with regards to fairness.
  - Imagine a server that receives requests from clients, and only has one thread to execute these requests. If a client sends a requests that takes a long time to process, then all other client's requests would have to wait until that one request has finished. 

#### Multithreading vs. Multitasking

- **Multitasking** - which meant that computers could execute multiple programs (AKA tasks or processes) at the same time. It **wasn't really "at the same time"** though. The <u>single CPU was shared between the programs</u>. The operating system would switch between the programs running, executing each of them for a little while before switching.
- **Multithreading** - which mean that you could have **multiple threads of execution inside the same program**. A thread of execution can be thought of as a CPU executing the program. When you have multiple threads executing the same program, it is like having multiple CPUs execute within the same program. So multithreading can <u>increase the performance of some types of programs</u>.

#### Multithreading Costs

- **More complex design** - Code executed by multiple threads **accessing shared data** need special attention. Errors arising from **incorrect thread synchronization** can be very hard to detect, reproduce and fix.
- **Context Switching Overhead** - When a CPU **switches from executing one thread to executing another**, the CPU needs to save the local data, program pointer etc. of the current thread, and load the local data, program pointer etc. of the next thread to execute. This switch is called a "context switch". The CPU switches from executing in the context of one thread to executing in the context of another. Context switching may slow your application.
- **Increased Resource Consumption** - A thread needs some resources from the computer in order to run. Besides CPU time a thread needs some memory to keep its local stack. It may also take up some resources inside the operating system, needed to manage the thread.

### Concurrency Models

Concurrent systems can be <u>implemented using different concurrency models</u>. A concurrency model specifies **how threads in the the system collaborate to complete the tasks** they are are given. Different concurrency models split the tasks in different ways, and the threads may communicate and collaborate in different ways.

#### Concurrency Models and Distributed System Similarities

**Threads and processes are quite similar** to each other in nature. In a concurrent system different threads communicate with each other. In a distributed system different processes communicate with each other (possibly on different computers). That is why the <u>different concurrency models often look similar to different distributed system architectures</u>. Because of this similarity, they can often borrow ideas from each other. For instance, **models for distributing work among workers** (threads) are often similar to **models of load balancing in distributed systems**. The same is true of error handling techniques like logging, fail-over, idempotency of tasks etc.

Of course distributed systems have the extra challenge that the network may fail, or a remote computer or process is down etc. But a concurrent system running on a big server may experience similar problems if a CPU fails, a network card fails, a disk fails etc. The probability of failure may be lower, but it can theoretically still happen.

#### Shared State vs. Separate State

The first Java concurrency model assumed that multiple threads executing within the same application would also share objects. This type of concurrency model is typically referred to as a "**shared state concurrency model**". **Shared state** means that the different threads in the system will <u>share some state(data) among them</u>. When threads share state, problems like **race conditions** and **deadlock** etc. may occur. It depends on how the threads use and access the shared objects, of course. This model causes a lot of concurrency problems which can be hard to solve elegantly. 

Therefore, an alternative concurrency model referred to as **"shared nothing" or "separate state"** has gained popularity. In this model the threads do not share any state (objects or data). In case the different threads need to communicate, they do so either by **exchanging immutable objects** among them, or by **sending copies of objects** (or data) among them. Thus, when <u>no two threads write to the same object</u> (data / state), you can avoid most of the common concurrency problems. Using a separate state concurrency design can often make some parts of the code **easier to implement** and easier to reason about, since you know that only one thread will ever write to a given object. You don't have to worry about concurrent access to that object. 

#### Parallel Workers

The <u>parallel worker model is a concurrency model</u> where a **delegator** distributes the incoming jobs to different workers. Each worker completes the full job from start to end. The workers work in parallel, running in different threads, and possibly on different CPUs. This is the <u>most commonly used concurrency model</u> in Java applications. Many of the concurrency utilities in the java.util.concurrent Java package are designed for use with this model.

**Advantages**
Parallel worker concurrency model is **easy to understand**. To increase the parallelization of the application you just add more workers. For instance, if you were implementing a web crawler and web crawling is an **IO intensive job** so you will probably end up with a **few threads per CPU / core** in your computer. One thread per CPU would be too little, since it would be idle a lot of the time while waiting for data to download (IO operation).

**Disadvantages**

**Shared State Can Get Complex**

Sometimes the shared workers need access to some kind of shared data, either in memory(data cache) or in a shared database. The threads need to access the shared data in a way that makes sure that changes by one thread are visible to the others. Threads need to <u>avoid race conditions, deadlock</u> and many other shared state concurrency problems.

**Blocked concurrent data structures**

Additionally, <u>part of the parallelization is lost when threads are waiting for each other</u> when accessing the shared data structures. <u>Many concurrent data structures are blocking</u>, meaning one or a limited set of threads can access them at any given time. This may <u>lead to contention on these shared data structures</u>. High contention will essentially lead to a degree of serialization of execution of the part of the code that access the shared data structures. Modern **non-blocking concurrency algorithms** may decrease contention and increase performance, but <u>non-blocking algorithms are hard to implement</u>.

**Persistent data structures** are another alternative. A persistent data structure always preserves the previous version of itself when modified. Thus, if multiple threads point to the same persistent data structure and one thread modifies it, the modifying thread gets a reference to the new structure. All other threads keep a reference to the old structure which is still unchanged and thus consistent. <u>The Scala programming contains several persistent data structures.</u> While persistent data structures are an elegant solution, they tend not to perform that well. For instance, a persistent list will add all new elements to the head of the list, and return a reference to the newly added element. All other threads still keep a reference to the previously first element in the list, and to these threads the list appear unchanged. They cannot see the newly added element.

Such a persistent list is implemented as a linked list. Unfortunately <u>linked lists don't perform very well on modern hardware. Each element in the list is a separate object, and these objects can be spread out all over the computer's memory</u>. <u>Modern CPUs are much faster at accessing data sequentially</u>, so on modern hardware you will get a lot higher performance out of a list implemented on top of an array. The **CPU caches** can load bigger chunks of the array into the cache at a time, and have the CPU access the data directly in the CPU cache once loaded. This is <u>not really possible with a linked list where **elements are scattered all over the RAM**</u>.

**Stateless Workers**
Shared state can be modified by other threads in the system. Therefore workers must re-read the state every time it needs it, to make sure it is working on the latest copy. A worker that <u>does not keep state internally (but re-reads it every time</u> it is needed) is called stateless. Re-reading data every time you need it can get slow. Especially if the state is stored in an external database.

**Job Ordering is Nondeterministic**
Another disadvantage is that the <u>job execution order is nondeterministic</u>. There is no way to guarantee which jobs are executed first or last. Job A may be given to a worker before job B, yet job B may be executed before job A. The nondeterministic nature of the parallel worker model makes it <u>hard to reason about the state</u> of the system at any given point in time. It also <u>makes it harder (if not impossible) to guarantee that one jobs happens before another</u>.

#### Assembly Line

Here the **workers are organized** like workers at an assembly line in a factory. Each worker **only performs a part of the full job**. When that part is finished the worker forwards the job to the next worker. Each worker is running in its own thread, and **shares no state with other workers**. This is also sometimes referred to as a shared nothing concurrency model.

Assembly line concurrency model are usually designed to **use non-blocking IO**. Non-blocking IO means that when a worker starts an IO operation (e.g. reading a file or data from a network connection) the <u>worker does not wait for the IO call to finish</u>. IO operations are slow, so waiting for IO operations to complete is a waste of CPU time. When the IO operation finishes, the result of the IO operation ( e.g. data read or status of data written) is passed on to another worker. With non-blocking IO, the <u>IO operations determine the boundary between workers</u>. A worker does as much as it can until it has to start an IO operation. Then it gives up control over the job. When the IO operation finishes, the next worker in the assembly line continues working on the job, until that too has to start an IO operation etc.

![Assembly_Line-concurrency-models.png](https://github.com/SnowflakeCoder/programming/blob/master/Java/images/Assembly_Line-concurrency-models.png?raw=true)

In reality, the <u>jobs may not flow along a single assembly line</u>. Since most systems can perform more than one job, **jobs flows from worker to worker** depending on the job that needs to be done. In reality there could be <u>multiple different virtual assembly lines going on at the same time</u>. **Jobs may even be forwarded to more than one worker** for concurrent processing. For instance, a job may be <u>forwarded to both a job executor and a job logger</u>. 

##### Reactive, Event Driven Systems

Systems using an assembly line concurrency model are **also called reactive model, or event driven model**. The system's **workers react to events** occurring in the system, either received from the outside world or emitted by other workers. Examples of events could be an incoming HTTP request, or that a certain file finished loading into memory etc. Some of the **popular reactive / event driven platforms** are : Vert.x, Akka, Node.JS (JavaScript)

##### Actors vs. Channels

Actors and channels are two similar **examples of assembly line models**.

<img src="https://github.com/SnowflakeCoder/programming/blob/master/Java/images/actors_and_channels.png?raw=true" alt="actors_and_channels.png" style="zoom:50%;" />

In the actor model **each worker is called an actor**. Actors can send messages directly to each other. Messages are sent and processed asynchronously. Actors can be used to implement one or more job processing assembly lines.
In the channel model, **workers do not communicate directly with each other**. Instead they <u>publish their messages (events) on different channels</u>. Other workers can then listen for messages on these channels without the sender knowing who is listening. Channel model is **more flexible**. A worker does not need to know about what workers will process the job later in the assembly line. It just **needs to know what channel** to forward the job to (or send the message to etc.). Listeners on channels can subscribe and unsubscribe without affecting the workers writing to the channels. This **allows looser coupling between workers**.

##### Assembly Line Advantages

- ##### No Shared State

  Workers share no state with other workers so they can be implemented without having to think about all the concurrency problems. This makes it much easier to implement workers.

- ##### Stateful Workers

  Since workers know that no other threads modify their data, the workers can be stateful. So that they can keep the data in memory, only writing changes back the eventual external storage systems. A <u>stateful worker can therefore often be faster than a stateless worker</u>.

- ##### Better Hardware Conformity

  Singlethreaded code has the advantage that it often <u>benefits from how the underlying hardware works</u>. Some developers call this **mechanical sympathy**. You can **create more optimized data structures and algorithms** when you can assume the code is executed in single threaded mode.

  Second, singlethreaded stateful workers can cache data in memory. When data is cached in memory there is also a higher probability that this data is also cached in the CPU cache of the CPU executing the thread. This makes accessing cached data even faster.

- ##### Job Ordering is Possible

  It is possible to implement an assembly line concurrency model in a way that **guarantees job ordering**. Job ordering makes it much easier to reason about the **state of a system** at any given point in time. You also could write **all incoming jobs to a log** and this log could then be used to **rebuild the state of the system from scratch** in case any part of the **system fails**. The jobs are written to the log in a certain order, and this order becomes the guaranteed job order. Implementing a guaranteed job order is possible by using single worker reading and writing each channel.

##### Assembly Line Disadvantages

- The main disadvantage is that the **execution of a job is often spread out** over multiple workers, and thus over multiple classes in your project. Thus it becomes **harder to see exactly what code is being executed** for a given job.
- It may also be **harder to write the code**. Worker code is sometimes written as callback handlers and may result in **callback hell**. Callback hell simply means that it gets hard to track <u>what the code is really doing</u> across all the callbacks, as well as making sure that each <u>callback has access to the data</u> it needs.
- With parallel worker model this is easier, you can open the worker code and read the code executed pretty much from **start to finish**. Parallel worker code may also be spread over many different classes, but the <u>execution sequence is often easier to read from the code</u>.

#### Functional Parallelism

Functional parallelism is another concurrency model where the basic idea is that you **implement your program using function calls**. Functions can be seen as "agents" or "actors" that send messages to each other, just like in the assembly line concurrency model. When one function calls another, that is similar to sending a message. All <u>parameters passed to the function are copied</u>, so no entity outside the receiving function can manipulate the data. This **copying is essential to avoiding race conditions** on the shared data. This makes the function execution similar to an atomic operation. **Each function call can be executed independently** of any other function call, so each function call can be executed on separate CPUs. That means, that an algorithm implemented functionally can be executed in parallel, on multiple CPUs.

With Java 7 we got **ForkAndJoinPool** which can help you implement something similar to functional parallelism. With Java 8 we got **parallel streams** which can help you parallelize the iteration of large collections.

The **hard part** about functional parallelism is **knowing which function calls to parallelize**. Coordinating function calls across CPUs comes with an overhead. The unit of work completed by a function needs to be of a certain size to be worth this overhead. <u>If the function calls are very small, attempting to parallelize them may actually be slower than a singlethreaded</u>, single CPU execution.

You can implement an algorithm using an reactive, event driven model and achieve a breakdown of the work which is similar to that achieved by functional parallelism. With an event driven model **you just get more control** of exactly what and how much to parallelize.

Additionally, splitting a task over multiple CPUs with the **overhead the coordination** of that incurs, only makes sense <u>if that task is currently the only task</u> being executed by the the program. However, if the system is concurrently executing multiple other tasks (like e.g. web servers), there is no point in trying to parallelize a single task. The other CPUs in the computer are anyways going to be busy working on other tasks, so there is not reason to try to disturb them with a slower, functionally parallel task. You are most likely **better off with an assembly line** concurrency model, because it has less overhead (executes sequentially in singlethreaded mode) and conforms better with how the underlying hardware works.

#### Which Concurrency Model is Best?

It **depends on what your system is supposed to do**. 

- If your **jobs are naturally parallel**, independent and with no shared state necessary, you might be able to implement your system using the parallel worker model. 
- Many jobs are not naturally parallel and independent though. For these kinds of systems assembly line concurrency model can be used. It has more advantages than disadvantages, and more advantages than the parallel worker model. Modern platforms like Vert.x has implemented all that **assembly line infrastructure** so you don't even have to code yourself. 

#### Same-threading


Same-threading is a concurrency model where a <u>single-threaded systems are scaled out to N single-threaded systems</u>. The result is **N single-threaded systems running in parallel**. A same-threaded system is not a purely single-threaded system, because it contains of multiple threads. Hence the term **same-threaded instead of single-threaded**.

Why Single-threaded Systems?
Single-threaded system's concurrency models are **much simpler** than multi-threaded systems because it **do not share any state** (objects / data) with other threads. This enables the single thread to **use non-concurrent data structures**, and utilize the CPU and CPU caches better. Unfortunately, single-threaded systems **do not fully utilize modern CPUs**. A modern CPU often comes with 2, 4, 6, 8 more cores. Each core functions as an individual CPU. **A single-threaded system can only utilize one of the cores**.

- ##### Same-threading: Single-threading Scaled Out

  In order to **utilize all the cores in the CPU**, a single-threaded system can be scaled out to utilize the whole computer. Same-threaded systems usually has **1 thread running per CPU** in the computer. If a computer contains 4 CPUs, or a CPU with 4 cores, then it would be normal to **run 4 instances of the same-threaded system** (4 single-threaded systems).

- ##### No Shared State

  The threads in a same-threaded system **do not share state**. The lack of shared state is what makes each thread behave as it if was a single-threaded system. However, since a same-threaded system can contain more than a single thread. Same-threaded basically means that **data processing stays within the same thread**, and that no threads in a same-threaded system share data concurrently. This is also referred to as **no shared state concurrency**, or **separate state concurrency**.

##### Load Distribution

A same-threaded system needs to <u>share the work load between the single-threaded instances</u> running and how you distribute the load over the different threads depend on the design of your system.

- ##### Single-threaded Microservices

  If your system consists of multiple microservices, <u>each microservice can run in single-threaded mode</u>. When you deploy multiple single-threaded microservices to the same machine, each microservice can run a single thread on a single CPU. **Microservices do not share any data** by nature, so **microservices is a good use case** for a same-threaded system.

- ##### Services With Sharded Data

  If your system does actually **need to share data**, or at least a database, you may be able to **shard the database**. Sharding means that the **data is divided among multiple databases**. The data is typically divided so that **all data related to each other is located together** in the same database. For instance, all data belonging to some "owner" entity will be inserted into the same database. 

##### Thread Communication

If the threads in a same-threaded system need to communicate, they do so by **message passing**. If Thread A wants to send a message to Thread B, Thread A can do so by generating a message (a byte sequence). Thread B can then **copy that message** (byte sequence) and read it. By copying the message Thread B makes sure that **Thread A cannot modify the message while Thread B reads it**. Once copied, the message copy is inaccessible for Thread A. The thread communication can take place via <u>queues, pipes, unix sockets, TCP sockets etc</u>. Whatever fits your system.

**A multi-threaded system** is where the threads share data and a same-threaded system is where the threads with separate data, communicating by passing messages to each other.

##### Thread Ops for Java

Thread Ops for Java is an **open source toolkit** designed to help you **implement separate state same-threaded systems** more easily. Thread Ops contains tools for starting and stopping individual threads, as well as achieving some level of concurrency within a single thread.

#### Concurrency vs. Parallelism

- ##### Concurrency

  Concurrency means that an **application is making progress on more than one task at the same time** (concurrently). Well, if the computer only has one CPU the application may not make progress on more than one task at exactly the same time, but more than one task is being processed at a time inside the application because the CPU switches between the different tasks until the tasks are complete.

- ##### Parallelism

  Parallelism means that an **application splits its tasks up into smaller subtasks which can be processed in parallel**, for instance on multiple CPUs at the exact same time. To achieve true parallelism your **application must have more than one thread running**, or at least be able to schedule tasks for execution in other threads, processes, CPUs, graphics cards etc.

Concurrency vs. Parallelism

- **Concurrency** is related to **how an application handles multiple tasks** it works on. An application can work on multiple tasks at the same time (concurrently). **Parallelism** is related to **how an application handles each individual task**. An application may process the task serially from start to end, or split the task up into subtasks which can be completed in parallel.
- **An application can be concurrent, but not parallel**. It is <u>possible to have a concurrent application even though it only has a single thread running inside it</u>. This means that it processes more than one task at the same time, but the **thread is only executing on one task at a time**. 
- **An application can also be parallel but not concurrent**. This means that the application only works on **one task at a time**, and this task is broken down into subtasks which can be processed in parallel. However, **each task (+ subtask) is completed before the next task is split up** and executed in parallel.
- Additionally, an application can be neither concurrent nor parallel. This means that it works on only one task at a time, and the task is never broken down into subtasks for parallel execution.
- Finally, an application can also be both concurrent and parallel, in that it both works on multiple tasks at the same time, and also breaks each task down into subtasks for parallel execution. However, some of the **benefits of concurrency and parallelism may be lost in this scenario**, as the CPUs in the computer are already kept reasonably busy with either concurrency or parallelism alone. Combining it may **lead to only a small performance gain** or **even performance loss**.

#### Threads

A Java Thread is like a **virtual CPU** that can execute your Java code, inside your Java application

- **Main Thread** - When a Java application is started its **main() method is executed by the main thread** - a special thread that is **created by the Java VM to run your application**. 
- **Thread.currentThread()** method returns a reference to the Thread instance executing currentThread().
- A thread can pause itself by calling the static method Thread.sleep() .
- **Stop a Thread** : The Thread class contains a **stop()** method, but it is **deprecated**. This stop() method **would not provide any guarantees** about in what state the thread was stopped. Instead of calling the stop() method you will have to implement your thread code so it can be stopped. For example implement an extra method say **doStop()** which signals to the Thread to stop (exit run method). 

#### Race Conditions and Critical Sections

A **critical section** is a section of code that is **executed by multiple threads** and where the **sequence of execution for the threads makes a difference in the result** of the concurrent execution of the critical section. When the result of multiple threads executing a critical section may **differ depending on the sequence** in which the threads execute, the critical section is said to contain a **race condition**. A **race condition** is a special condition that may <u>occur inside a **critical section**</u> when multiple threads execute the critical section. 

Running more than one thread inside the same application does not by itself cause problems. The problems **only arise if one or more of the threads write to the same resources**. Here is a critical section Java code example that **may fail if executed by multiple threads simultaneously**:

```java
  public class Counter {
     protected long count = 0;
     public void add(long value){
         this.count = this.count + value;
     }
  }
```

The code in the **add() method contains a critical section**. **When multiple threads execute this critical section, race conditions occur**. The situation where two threads compete for the same resource, where **the sequence** in which the resource is accessed is significant, is called **race conditions**. <u>A code section that leads to race conditions is called a critical section</u>.

##### Preventing Race Conditions

To prevent race conditions from occurring you must make sure that the <u>critical section is executed as an **atomic instruction**</u>. That means that once a single thread is executing it, no other threads can execute it until the first thread has left the critical section. Race conditions can be <u>avoided by proper **thread synchronization** in critical sections</u>. Thread synchronization can be achieved using a synchronized block or using other synchronization constructs like locks or atomic variables.


For larger critical sections it may be beneficial to **break the critical section into smaller critical sections**, to allow multiple threads to execute each a smaller critical section. This may **decrease contention on the shared resource**, and thus increase throughput of the total critical section.

```java
//With this implementation only a single thread can ever execute the summing at the same time.
public void add(int val1, int val2){
    synchronized(this){
        this.sum1 += val1;   
        this.sum2 += val2;
    }
}
//However, since the "two sum variables are independent of each other", you could split their summing up into two separate synchronized blocks.
public void add(int val1, int val2){
    synchronized(this.lock1Obj){
        this.sum1 += val1;   
    }
    synchronized(this.lock2Obj){
        this.sum2 += val2;
    }
}
```

#### Thread Safety and Shared Resources


Code that is <u>safe to call by multiple threads simultaneously</u> is called **thread safe** (that means it contains no race conditions). Race condition only occur when **multiple threads update shared resources**. 

##### Local Variables

Local variables are stored in **each thread's own stack**. That means that local variables are **never shared between threads** so all local primitive variables are thread safe.

##### Local Object References

Here the reference itself is not shared. The object referenced however, is not stored in each threads's local stack. **All objects are stored in the shared heap**. If an object created locally <u>never escapes the method it was created in</u>, it is thread safe. In fact you can also pass it on to other methods and objects as long as <u>none of these methods or objects make the passed object available to other threads</u>.

```java
public void someMethod(){
  LocalObject localObject = new LocalObject();
  localObject.callMethod();
  method2(localObject);
}

public void method2(LocalObject localObject){
  localObject.setValue("value");
}
```

The LocalObject instance is not returned from the created method. So each thread executing the someMethod() method will create its own instance and assign it to the localObject reference. Therefore the use of the LocalObject here is thread safe. In fact, the whole method someMethod() is thread safe. Even if the LocalObject instance is passed as parameter to other methods the use of it is thread safe.

The **only exception** is of course, if one of the methods called with the LocalObject as parameter, <u>stores the LocalObject instance in a way (like saving it in a global cache) that allows access to it from other threads</u>.

##### Object Member Variables

Object member variables (fields) are **stored on the heap along with the object**. Therefore, if two threads call a method on the same object instance and this method updates object member variables, the method is not thread safe. 

##### The Thread Control Escape Rule

```
If a resource is created, used and disposed within the control of the same thread,
and never escapes the control of this thread, the use of that resource is thread safe.
```

Even if the use of an object is thread safe, if that **object points to a shared resource** like a file or database, your application as a whole may not be thread safe.  For instance, if thread 1 and thread 2 each create their own database connections and the use of each connection itself is thread safe. But the use of the database the connections point to may not be thread safe.

##### Immutability

We can make sure that objects shared between threads are <u>never updated by any of the threads</u> by making the shared objects **immutable**, and thereby thread safe. Once an instance is created you cannot change its value. It is immutable, you can read it however.

**Note** that even if an object is immutable, the reference to this object may not be thread safe. 

```java
public class Calculator{
  private ImmutableValue currentValue = null;
  public void setValue(ImmutableValue newValue){
    this.currentValue = newValue;
  }
}
```

Here, even if the Calculator class uses an immutable object internally, **it is not itself immutable**, and therefore not thread safe. In other words: The Immutable object is thread safe, but the use of it is not. 

#### Java Memory Model


The Java memory model specifies **how the Java virtual machine works** with the computer's memory (RAM). The Java memory model specifies how and when different threads can see values written to shared variables by other threads, and how to synchronize access to shared variables when necessary.

The Java memory model used internally in the JVM **divides memory between thread stacks and the heap**. 

##### Thread stack

Each thread running in the JVM has **its own thread stack**. The thread stack contains information about **what methods the thread has called** to reach the current point of execution, known as **call stack**. As the thread executes its code, the call stack changes. The thread stack also **contains all local variables for each method** being executed (all methods on the call stack). A thread can only access it's own thread stack. Local variables created by a thread are invisible to all other threads. Even if two threads are executing the exact same code, each thread has its own version of each local variable in their own thread stack.

The **heap** contains **all objects** created in your Java application, **regardless of what thread created the object**. This includes the object versions of the primitive types (e.g. Byte, Integer, Long etc.). 

- A local variable may be of a primitive type, in which case it is totally kept on the thread stack. 
- A local variable may also be a reference to an object. In that case the **reference (the local variable) is stored on the thread stack**, but the object itself if stored on the heap. 
- An **object may contain methods** and these methods may contain local variables. These local variables are also stored on the thread stack, even if the object the method belongs to is stored on the heap. 
- An **object's member variables are stored on the heap** along with the object itself whether its of a primitive type or a reference to an object. 
- **Static class variables** are also stored on the heap along with the class definition.
- Objects on the heap can be accessed by all **threads that have a reference to the object**. If two threads call a method on the same object at the same time, they will both have access to the object's member variables, but each thread will have **its own copy of the local variables**.

![The Java Memory Model showing references from local variables to objects, and from object to other objects.](http://tutorials.jenkov.com/images/java-concurrency/java-memory-model-3.png)

The two threads each have a different reference to the same object. Their references are local variables  (Local Variable 2) and are thus stored in each thread's thread stack. The two different references point to the same object on the heap (Object 3), though. The shared object (Object 3) has a reference to Object 2 and Object 4 as member variables (illustrated by the arrows). Via these member variables the two threads can access Object 2 and Object 4.

##### Hardware Memory Architecture

![Modern hardware memory architecture.](http://tutorials.jenkov.com/images/java-concurrency/java-memory-model-4.png)

A modern computer often has 2 or more CPUs in it. Some of these CPUs may have multiple cores too. **Each CPU is capable of running one thread** at any given time. That means that if your Java application is multithreaded, one thread per CPU may be running simultaneously (concurrently) inside your Java application.

Each CPU contains a **set of registers** which are essentially **in-CPU memory**. The CPU can perform operations much faster on these registers than it can perform on variables in main memory. That is because the CPU can access these registers much faster than it can access main memory.

Each CPU also have a **CPU cache memory layer**. The CPU can <u>access its cache memory much faster than main memory</u>, but typically not as fast as it can access its internal registers. So, the CPU cache memory is somewhere in between the speed of the internal registers and main memory. Some CPUs may have **multiple cache layers** (Level 1 and Level 2).

A computer also contains a **main memory area (RAM)**. All CPUs can access the main memory. The main memory area is typically <u>much bigger than the cache memories</u> of the CPUs.

Typically, when a CPU needs to access main memory it will **read part of main memory into its CPU cache**. It may even **read part of the cache into its internal registers** and then perform operations on it. When the CPU needs to write the result back to main memory it will <u>flush the value from its internal register to the cache memory</u>, and at some point <u>flush the value back to main memory</u>.

The values stored in the cache memory is typically flushed back to main memory when the **CPU needs to store something else** in the cache memory. The CPU cache can have data written to part of its memory at a time, and flush part of its memory at a time. It does not have to read / write the full cache each time it is updated. Typically the cache is updated in **smaller memory blocks** called "**cache lines**". One or more cache lines may be read into the cache memory, and one or more cache lines may be flushed back to main memory again.

The hardware memory architecture **does not distinguish between thread stacks and heap**. On the hardware, both the thread stack and the heap are located in main memory. Parts of the thread stacks and heap may sometimes be present in CPU caches and in internal CPU registers. 


When objects and variables can be stored in various different memory areas, certain problems may occur. 

- Visibility of thread updates (writes) to shared variables.
- Race conditions when reading, checking and writing shared variables.

##### Visibility issues of Shared Objects

If two or more threads are sharing an object, without the proper use of either **volatile** declarations or **synchronization**, updates to the shared object made by one thread **may not be visible to other threads**.

Imagine that the shared object is initially stored in main memory. A thread running on CPU one then <u>reads the shared object into its CPU cache</u>. There it makes a change to the shared object. As long as the CPU cache has not been flushed back to main memory, the changed version of the shared object is not visible to threads running on other CPUs. This way each thread may end up with its own copy of the shared object, each copy sitting in a different CPU cache.

To solve this problem you can use **volatile keyword**. The volatile keyword can **make sure that a given variable is read directly from main memory**, and always written back to main memory when updated.

##### Race Condition issues

If two or more threads share an object, and **more than one thread updates variables in that shared object,** race conditions may occur.

Imagine if thread A reads the variable count of a shared object into its CPU cache. Imagine too, that thread B does the same, but into a different CPU cache. Now thread A adds one to count, and thread B does the same. Now <u>var1 has been incremented two times, once in each CPU cache.</u>


To solve this problem you can use a Java **synchronized block**. A synchronized block guarantees that <u>only one thread can enter a given critical section of the code at any given time</u>. Synchronized blocks also guarantee that <u>all variables accessed inside the synchronized block will be **read in from main memory**</u>, and when the <u>thread exits the synchronized block, all updated variables will be **flushed back to main memory**</u> again, regardless of whether the variable is declared volatile or not.

#### Java Synchronized Blocks

A Java synchronized block marks a method or a block of code as synchronized (only be executed a single thread at a time). Java synchronized blocks can thus be used to avoid race conditions. A synchronized block in Java is **synchronized on some object**. All synchronized blocks synchronized on the same object can only have one thread executing inside them at the same time. The synchronized keyword can be used to mark four different types of blocks:

- Instance methods
  - A synchronized instance method is **synchronized on the instance** owning the method. Only **one thread per instance** can execute inside a synchronized instance method. This is true across all synchronized instance methods for the same object (instance).

- Static methods
  - Synchronized static methods are **synchronized on the class object** of the class the synchronized static method belongs to. Since **only one class object exists in the Java VM per class**, only one thread can execute inside a static synchronized method in the same class. In case a class contains more than one static synchronized method, only one thread can execute any of these methods at the same time. 

- Code blocks inside instance methods
  - Sometimes it is preferable to synchronize only part of a method. Here we uses the **synchronized block construct** to mark a block of code as synchronized. The synchronized block **construct takes an object** in parentheses, called a **monitor object**. The code is said to be synchronized on the monitor object. A synchronized instance method uses the object it belongs to as monitor object. Only one thread can execute inside a Java code block **synchronized on the same monitor object**.

- Code blocks inside static methods
  - Synchronized blocks can also be used inside of static methods.  MyClass.class can be used to synchronize on the class object of the class the method belong to.


##### Synchronized and Data Visibility

When a thread enters a synchronized block it will **refresh the values of all variables visible** to the thread. When a thread exits a synchronized block **all changes to variables visible to the thread will be committed to main memory**. This is similar to how the **volatile keyword** works.

##### What Objects to Synchronize On

You can actually choose any object to synchronize on, but it is recommended that you do **not synchronize on String objects**, or any **primitive type wrapper objects**, as the compiler **might optimize** those, so that you end up using the same instances in different places in your code. If you call **Integer.valueOf(1)** multiple times, it might actually return the same wrapper object for the same input parameter values. 

```java
synchronized(Integer.valueOf(1)) {
   //do something in here.
}
```

##### Synchronized Block Limitations and Alternatives

- What if two threads just wanted to read a shared value, and not update it? As alternative to a synchronized block you could guard the code with a **Read / Write Lock** which as more advanced locking semantics than a synchronized block. 
- What if you want to <u>allow N threads to enter a synchronized block</u>, and not just one? You could use a **Semaphore** to achieve that behaviour.
- Synchronized blocks **do not guarantee in what order** threads waiting to enter them are granted access to the synchronized block. If you need to guarantee that threads trying to enter a synchronized block get access in the **exact sequence they requested access** to it, you need to implement **Fairness**.
- What if you just have one thread writing to a shared variable, and other threads only reading that variable? Then you might be able to just **use a volatile variable** without any synchronization around.

##### Synchronized Block Reentrance

Once a thread has entered a synchronized block the thread is said to "**hold the lock**" on the monitoring object. If the thread calls another method which calls back to the first method with the synchronized block inside, the thread holding the lock can **reenter the synchronized block**. It is not blocked just because the **thread (itself) is holding the lock**.

##### Synchronized Blocks in Cluster Setups

A synchronized block **only blocks threads within the same Java VM** from entering that code block. If you have the same Java application running on multiple Java VMs - in a cluster - then it is possible for one thread within each Java VM to enter that synchronized block at the same time.

#### Java Volatile Keyword

The Java volatile keyword is used to mark a Java variable as "**being stored in main memory**". So that every read of a volatile variable will be <u>read from the computer's main memory</u>, and not from the CPU cache, and that every write to a volatile variable will be <u>written to main memory</u>, and not just to the CPU cache.

##### Variable Visibility Problems

In a multithreaded application where the threads operate on non-volatile variables, each thread may **copy variables from main memory into a CPU cache** while working on them, for performance reasons. With non-volatile variables there are **no guarantees** about when the JVM reads data from main memory into CPU caches, or writes data from CPU caches to main memory. This can cause several problems. The problem with **threads not seeing the latest value of a variable** because it has not yet been written back to main memory by another thread, is called a "**visibility**" problem. The updates of one thread are not visible to other threads.

##### volatile Visibility Guarantee

The Java volatile keyword **guarantees visibility of changes** to variables across threads. By declaring the variable volatile all writes to the variable will be written back to main memory immediately. Also, all reads of the variable will be read directly from main memory.

##### Full volatile Visibility Guarantee

Actually, the <u>visibility guarantee of Java volatile **goes beyond the volatile variable**</u> itself. 

- If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then <u>all variables visible to Thread A before writing the volatile variable, will also be visible to Thread B</u> after it has read the volatile variable.
- If Thread A reads a volatile variable, then <u>all variables visible to Thread A when reading the volatile variable will also be re-read from main memory.</u>

```java
public class MyClass {
    private int years;
    private int months
    private volatile int days;

    public int totalDays() {
        int total = this.days;
        total += months * 30;
        total += years * 365;
        return total;
    }    
    public void update(int years, int months, int days){
        this.years  = years;
        this.months = months;
        this.days   = days;
    }
}
```

udpate() method : The full volatile visibility guarantee means, that when a volatile value is written, then **all variables visible to the thread are also written to main memory**. That means, that when a value is written to days, the values of years and months are also written to main memory.


Notice the totalDays() method starts by reading the value of days into the total variable. When reading the value of days, the values of months and years are also read into main memory. Therefore you are **guaranteed to see the latest values** of days, months and years with the above **read sequence**.

##### Instruction Reordering Challenges

The Java VM and the CPU are **allowed to reorder instructions** in the program for performance reasons, as long as the semantic meaning of the instructions remain the same. However, <u>instruction reordering present a challenge when one of the variables is a volatile variable</u>.

For example above update() method writes a value to days, the newly written values to years and months are also written to main memory. But, what if the Java VM reordered the instructions, like this:

```java
public void update(int years, int months, int days){
    this.days   = days;
    this.months = months;
    this.years  = years;
}
```

The values of months and years are still written to main memory when the days variable is modified, but **this time it happens before the new values have been written** to months and years. The <u>new values are thus not properly made visible to other threads</u>. The semantic meaning of the reordered instructions has changed. To address the instruction reordering challenge, the Java volatile keyword gives a **"happens-before" guarantee**, in addition to the visibility guarantee. The happens-before guarantee guarantees that:

- Reads from and writes to other variables **cannot be reordered to occur after a write to a volatile** variable, if the reads / writes originally occurred before the write to the volatile variable. The reads / writes before a write to a volatile variable are guaranteed to "happen before" the write to the volatile variable. **Notice that** it is still possible for reads / writes of other variables <u>located after a write to a volatile to be reordered to occur before that write to the volatile</u>. Just not the other way around. From **after to before is allowed**, but from **before to after is not allowed**.
- Reads from and writes to other variables **cannot be reordered to occur before a read of a volatile variable**, if the reads / writes originally occurred after the read of the volatile variable. Notice that it is possible for reads of other variables that occur before the read of a volatile variable can be reordered to occur after the read of the volatile. Just not the other way around. From **before to after is allowed**, but from **after to before is not allowed**.

The above **happens-before guarantee** assures that the **visibility guarantee** of the volatile keyword are being enforced.

##### volatile is Not Always Enough

When multiple threads writing to a shared volatile variable, and the new value written to the variable depends on its previous value, then a volatile variable is **no longer enough to guarantee correct visibility**. The short time gap in between the reading of the volatile variable and the writing of its new value, creates an **race condition** where multiple threads might read the same value of the volatile variable, generate a new value, and when writing the value back to main memory - overwrite each other's values.

Reading or writing a volatile variable does not block threads reading or writing. For this to happen you need to **use a synchronized** in that case to **guarantee that the reading and writing of the variable is atomic**.   As an alternative to a synchronized block you could also use one of the many atomic data types (like AtomicLong or AtomicReference).

In case only one thread reads and writes the value of a volatile variable and other threads only read the variable, then the reading threads are guaranteed to see the latest value written to the volatile variable. 

##### Performance Considerations of volatile

Reading and writing of volatile variables causes the variable to be read or written to main memory. Reading from and writing to **main memory is more expensive** than accessing the CPU cache. Accessing volatile variables also **prevent instruction reordering** which is a normal performance enhancement technique. Thus, you should only use volatile variables when you really need to enforce visibility of variables.

#### ThreadLocal

The Java ThreadLocal class <u>enables you to create variables that can **only be read and written by the same thread**</u>. Thus, even if two threads are executing the same code, and the code has a reference to the same ThreadLocal variable, the **two threads cannot see each other's ThreadLocal variables**. Thus, the Java ThreadLocal class provides a **simple way to make code thread safe** that would not otherwise be so.

##### Using a ThreadLocal

You create a ThreadLocal instance just like you create any other Java object - via the new operator. This only **needs to be done once per thread**. Multiple threads can now get and set values inside this ThreadLocal, and each thread will only see the value it set itself.

It is possible to **remove a value set in a ThreadLocal** variable by calling the ThreadLocal remove() method. 

```java
private ThreadLocal threadLocal = new ThreadLocal();
threadLocal.set("A thread local value");
String threadLocalValue = (String) threadLocal.get();
threadLocal.remove();
```

**Note**: **ThreadLocal instance will be same for all the threads** it uses. No need to create different instances for each thread. ThreadLocal instance **uses a map which is stored inside each Thread object**. Set method keeps the value inside this map with **ThreadLocal instance as key** so it wont be accessible to other threads.

##### Generic ThreadLocal

You can create a ThreadLocal with a generic type. Using a generic type only objects of the generic type can be set as value on the ThreadLocal. Additionally, you do not have to typecast the value returned by get(). 

##### Initial ThreadLocal Value

You have two options for specifying an initial value for a ThreadLocal:

- Create a ThreadLocal **subclass that overrides the initialValue()** method.

  - ```java
    private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
        @Override protected String initialValue() {
            return String.valueOf(System.currentTimeMillis());
        }
    };
    ```

- Create a ThreadLocal with a **Supplier interface implementation**.

  - Creating a ThreadLocal using its static factory method **withInitial(Supplier)** passing a Supplier interface implementation as parameter. This **Supplier implementation supplies the initial value** for the ThreadLocal.

  - ```java
    ThreadLocal<String> threadLocal = ThreadLocal.withInitial(new Supplier<String>() {
        @Override
        public String get() {
            return String.valueOf(System.currentTimeMillis());
        }
    });
    ```

Since **Supplier is a functional interface**, it can be implemented using a Java Lambda Expression. 

```java
ThreadLocal threadLocal = ThreadLocal.withInitial(() -> { return "Lambda Expression"; });
```

##### Using a ThreadLocal

The SimpleDateFormat class is not thread safe, so multiple threads cannot use it at the same time. To solve this problem, create a ThreadLocal variable with SimpleDateFormat and this creates a SimpleDateFormat per thread, so each thread calling the format() method will use its own SimpleDateFormat instance.

##### InheritableThreadLocal

The InheritableThreadLocal class is a **subclass of ThreadLocal**. Instead of each thread having its own value inside a ThreadLocal, the InheritableThreadLocal **grants access to values to a thread and all child threads** created by that thread.

#### Thread Signaling

The purpose of thread signaling is to **enable threads to send signals to each other**. Additionally, thread signaling **enables threads to wait for signals** from other threads. A simple way for threads to send signals to each other is by **setting the signal values in some shared object** variable. 

##### Busy Wait

A thread waiting for a signal from another thread is called **busy waiting**. The thread is busy while waiting. Busy waiting is **not a very efficient utilization of the CPU** running the waiting thread, except if the average waiting time is very small. Else, it would be smarter if the waiting thread could somehow sleep or become inactive until it receives the signal it is waiting for.

```java
//Notice how the while loop keeps executing until hasSignal() returns true.
while(!sharedSignal.hasSignal()){
  //do nothing... busy waiting
}
```

##### wait(), notify() and notifyAll()

Java has a **builtin wait mechanism** that enable threads to **become inactive while waiting for signals**. The java.lang.Object class defines three methods, wait(), notify(), and notifyAll(), to facilitate this.

**A thread that calls wait() on any object becomes inactive until another thread calls notify() on that object**. In order to call either wait() or notify() the **calling thread must first obtain the lock on that object**. In other words, the calling thread must call wait() or notify() from **inside a synchronized block**. When a thread calls **notify()** on an object, **one of the threads waiting on that object are awakened** and allowed to execute. There is also a **notifyAll()** method that will wake all threads waiting on a given object.

```java
public class MyWaitNotify{
  MonitorObject myMonitorObject = new MonitorObject();
  public void doWait(){
    synchronized(myMonitorObject){
      try{
        myMonitorObject.wait();
      } catch(InterruptedException e){...}
    }
  }
  public void doNotify(){
    synchronized(myMonitorObject){
      myMonitorObject.notify();
    }
  }
}
```

A thread **cannot call wait(), notify() or notifyAll() without holding the lock** on the object the method is called on. If it does, an **IllegalMonitorStateException** is thrown. Once a thread **calls wait() it releases the lock it holds on the monitor object**. This **allows other threads to call** wait() or notify() too, since these methods must be called from inside a synchronized block. 

Once a **thread is awakened** it cannot exit the wait() call **until the thread calling notify() has left** its synchronized block. The **awakened thread must reobtain the lock** on the monitor object before it can exit the wait() call, because the **wait call is nested inside a synchronized block**. If multiple threads are awakened using **notifyAll()** only **one awakened thread at a time can exit the wait()** method, since each thread must obtain the lock on the monitor object in turn before exiting wait().

##### Missed Signals

The methods **notify() and notifyAll() do not save the method calls** to them in case no threads are waiting when they are called. The notify signal is then just lost. Therefore, if a thread calls notify() before the thread to signal has called wait(), the **signal will be missed by the waiting thread**. In some cases this may result in the **waiting thread waiting forever**, because the signal to wake up was missed.

To avoid losing signals they should be **stored inside a variable** (signal variable) . The doNotify() method now sets the signal variable to true before calling notify(). The doWait() method now checks the signal variable before calling wait() and it **only calls wait() if no signal was received** in between the previous doWait() call and this.

```java
public class MyWaitNotify2{
  MonitorObject myMonitorObject = new MonitorObject();
  boolean signal = false;

  public void doWait(){
    synchronized(myMonitorObject){
      if(!signal){
        try{
          myMonitorObject.wait();
         } catch(InterruptedException e){...}
      }
      //clear signal and continue running.
      signal = false;
    }
  }

  public void doNotify(){
    synchronized(myMonitorObject){
      signal = true;
      myMonitorObject.notify();
    }
  }
}
```

##### Spurious Wakeups

For inexplicable reasons it is **possible for threads to wake up even if notify() and notifyAll() has not been called**. This is known as spurious wakeups. **Wakeups without any reason**. If a spurious wakeup occurs the waiting thread may continue processing without having received a proper signal to do so! This could cause serious problems in your application.

To **guard against spurious wakeups** the signal member variable is **checked inside a while loop** instead of inside an if-statement. Such a while loop is also called a **spin lock**. The thread awakened spins around until the condition in the spin lock (while loop) becomes false. 

```java
public void doWait(){
    synchronized(myMonitorObject){
      //wait() call is now nested inside a while loop instead of an if-statement
      while(!signal){
        try{
          myMonitorObject.wait();
         } catch(InterruptedException e){...}
      }
      //clear signal and continue running.
      signal = false;
    }
  }
```


Notice how the wait() call is now **nested inside a while loop instead of an if-statement**. If the waiting thread wakes up without having received a signal, the signal variable will still be false, and the while loop will execute once more, **causing the awakened thread to go back to waiting**.

##### Multiple Threads Waiting for the Same Signals

The **while loop is also a nice solution** if you have multiple threads waiting, which are all **awakened using notifyAll()**, but only one of them should be allowed to continue. Only one thread at a time will be able to obtain the lock on the monitor object, meaning **only one thread can exit the wait() call and clear the signal flag**. Once this thread then exits the synchronized block in the doWait() method, the other threads can exit the wait() call and check the signal variable inside the while loop. However, this **flag was cleared by the first thread waking up**, so the rest of the awakened threads go back to waiting, until the next signal arrives.

You may be tempted to always call notifyAll() instead notify(), but this is a **bad idea performance wise**. There is no reason to wake up all threads waiting when only one of them can respond to the signal.

**Don't use global objects, string constants** etc. for wait() / notify() mechanisms. The problem is the JVM/Compiler **internally translates constant strings into the same object**.

### References

http://tutorials.jenkov.com/java-concurrency/index.html


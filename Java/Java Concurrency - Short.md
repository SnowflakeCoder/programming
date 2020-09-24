#### Multithreading

- Multithreading => multiple threads of execution inside the same program
- Multitasking => OS switch between the programs, executing each of them for a little time.
- Multithreading Costs => complex design, Switching Overhead, more Resource Consumption

#### Concurrency Models

- Shared State => problems like race conditions and deadlock + more complex + Blocked concurrent data structures
- Separate State / shared nothing => communicate by exchanging immutable objects or copies of objects + easier to implement
- Parallel Workers => **delegator** distributes jobs to different workers. Each worker completes the full job from start to end + easy to understand and code - worker deals start to finish.
  - Disadvantage
    - Shared State Can Get Complex + Blocked concurrent data structures
    - Persistent data structures => preserves the previous version of itself when modified + Scala + cannot see the new changes.
    - Stateless Workers
    - Job Ordering is Nondeterministic
- Assembly Line
  - Each worker **only performs a part of the full job** + shares no state with other workers 
  - use non-blocking IO (IO operations determine the boundary between workers)
  - Jobs may even be forwarded to more than one worker (executor and a logger)
  - also called **reactive model**, or **event driven model** => workers react to events (outside/inside events) 
  - **Actors and channels** => examples of assembly line models
    - each worker is called an actor + Actors send messages directly to each other
    - channel model => workers do not communicate directly + publish messages on different channels + more flexible + looser coupling between workers
  - Assembly Line Advantages => No Shared State + Stateful Workers + Better Hardware Conformity (**mechanical sympathy**) + Job Ordering
  - Disadvantages => execution of a job is spread out - more complex + difficult to know the state + harder to see exactly what code is being executed + harder to write the code + **callback hell**(hard to track code across all callbacks)
- Functional Parallelism => implement using multiple function calls.
  - Functions can be seen as "actors" that send messages to each other
  - parameters passed to the function are copied + avoiding race conditions
  - similar to functional parallelism => Java 7 - ForkAndJoinPool + Java 8 - parallel streams
  - smaller functions, attempting to parallelize may actually be slower 
- Same-threading => N single-threaded systems running in parallel
  - do not share any state + use non-concurrent data structures
  - run multiple instances for to **utilize all the cores in the CPU** 
  - Load Distribution => Single-threaded Microservices + Services With Sharded Data
  - Communication => copy message (queue / pipes / TCP sockets)

#### Concurrency & Parallelism

- Concurrency => application is making progress on more than one task at the same time (CPU switches between the different tasks)
- Parallelism => application splits its tasks up into smaller subtasks which can be processed in parallel start to end + must have more than one thread running

#### Threads

- a **virtual CPU** that can execute your Java code, inside your Java application
-  [stop() =>no guarantees + deprecated] + [doStop() signals Thread to stop (exit run method)]
- Race Conditions and Critical Sections => **race condition** occur inside a **critical section**.
- thread safe => Local Variables(thread stack) + Local Object References [refernce (stack) + object (heap)]
- Immutable object is thread safe, but the use of it is not (same as volatile)

#### Java Memory Model

RAM => [[Thread stack, call stack], Heap]

##### Hardware Memory Architecture

- CPU => [CPU registers, CPU cache memory layer [smaller memory blocks called "cache lines"]]
- Computer=> Main Memory(RAM)

##### Visibility issues of Shared Objects

[Volatile, synchronized block]

#### Java Happens Before Guarantee

- [Instruction Reordering, [Instruction Reordering Problems in Multi CPU Computers]
- volatile Read & Write Visibility Guarantee, Volatile Happens Before Guarantee for Read & Write variables
- Synchronized Visibility Guarantee [Entry & Exit], Synchronized Happens Before Guarantee]

#### Java Synchronized Blocks

- four different types of blocks: [Instance methods, Static methods, Code blocks inside instance methods, Code blocks inside static methods], 
- Synchronized and Data Visibility/Instruction Reordering, 
- **do not synchronize** on String & primitive type wrapper objects.
- Synchronized Block Limitations and Alternatives => Read / Write Lock, Semaphore (allow N threads), **Fairness** (guarantee threads waiting order), volatile (if only one variable write)
- Synchronized Block Performance Overhead
- Synchronized Block Reentrance => not blocked because **thread is holding the same lock**.
- Synchronized Blocks in Cluster Setups => only blocks threads within the same JVM

#### Java Volatile Keyword

- mark a Java variable as "**being stored in main memory**"
- **Full** volatile Visibility Guarantee
- Instruction Reordering Challenges => happens-before guarantee + visibility guarantee
- volatile is **Not Always Enough** if new value written depends on its previous value
- Performance Considerations => main memory is expensive + prevent instruction reordering

#### ThreadLocal

- ThreadLocal instance will be one for all threads. 
- enables you to create variables that can **only be read and written by the same thread**.
- threadLocal.**remove()**; - remove a value set in a ThreadLocal variable
- uses a map which is stored inside each Thread object with **ThreadLocal instance as key**.
- **InheritableThreadLocal** class => subclass, grants access to a thread and all its child threads.

#### Thread Signaling

- **Busy Wait** => thread is busy while waiting for a signal from another thread
- **wait(), notify() and notifyAll()** => threads to become inactive while waiting + first obtain the lock on that object (inside a synchronized block) + IllegalMonitorStateException.
- If multiple threads are awakened using **notifyAll()** only **one awakened thread at a time can exit the wait()**, since each thread must obtain the lock on the monitor before exiting wait().
- Missed Signals => notify signal lost + may result in the thread waiting forever + store the signal inside a variable.
- Spurious Wakeups => wake up without notify()/notifyAll() 
  - **spin lock** => guard against spurious wakeups + checked inside a while loop
- **Don't use global objects, string constants** etc. for wait() / notify() mechanisms.
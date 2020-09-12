# JVM Fibers and Threads

Fibers are threads are sequential processes that we can spawn and synchronize with others. Threads are implemented by the operating systems, while fibers (AKA **lightweight threads or user-mode threads**) are implemented in user mode.

OS threads can be used in any language but **require a lot of RAM** and are slow to synchronize and to spawn, while fibers are specific to a certain language or runtime, but are very lightweight, and are synchronized with virtually no overhead. 

**Quasar fibers** are the JVM-specific implementation of lightweight threads. A Quasar fiber is used exactly like a Java thread, and **it implements the thread API**. Quasar abstracts both thread implementations -- **Java’s Thread and Quasar’s Fiber** --  into Strand. This allows fibers and threads to interoperate seamlessly.



A thread is <u>a continuation scheduled to run on a CPU core</u> at the appropriate time by a scheduler. A continuation is nothing more than a **program counter**, marking our point in the sequence of instructions, and a stack, storing the value of our variables. Quasar fibers are implemented just like OS threads, only in JVM bytecode rather than in the OS kernel.





Threads are generally considered to be **preemptive** (although this may not always be true, depending on the operating system) while fibers are considered to be **light-weight, cooperative threads**. Both are **separate execution paths** for your application.

With threads: the current execution path may be **interrupted or preempted at any time** (note: this statement is a generalization and may not always hold true depending on OS/threading package/etc.). This means that for threads, data integrity is a big issue because one thread may be stopped in the middle of updating a chunk of data, leaving the integrity of the data in a bad or incomplete state. This also means that the operating system can take advantage of multiple CPUs and CPU cores by running more than one thread at the same time and leaving it up to the **developer to guard data access**.

With fibers: the <u>current execution path is only interrupted when the fiber yields execution</u> (same note as above). This means that **fibers always start and stop in well-defined places**, so data integrity is much less of an issue. Also, because **fibers are often managed in the user space**, expensive context switches and CPU state changes need not be made, making changing from one fiber to the next extremely efficient. On the other hand, since **no two fibers can run at exactly the same time**, just using fibers alone will not take advantage of multiple CPUs or multiple CPU cores.



Threads use **pre-emptive** scheduling, whereas fibers use **cooperative** scheduling.


With a thread, the control flow could get interrupted at any time, and another thread can take over. With multiple processors, you can have multiple threads all running at the same time (*simultaneous* multithreading, or SMT). As a result, you have to be *very* careful about concurrent data access, and protect your data with mutexes, semaphores, condition variables, and so on. It is often very tricky to get right.

With a fiber, control only switches when you tell it to, typically with a function call named something like `yield()`. This makes concurrent data access easier, since you don't have to worry about atomicity of data structures or mutexes. As long as you don't yield, there's no danger of being *preempted* and having another fiber trying to read or modify the data you're working with. As a result, though, if your fiber gets into an infinite loop, no other fiber can run, since you're not yielding.

You can also mix threads and fibers, which gives rise to the problems faced by both. Not recommended, but it can sometimes be the right thing to do if done carefully.









# Golang | Goroutine vs Thread

**Goroutine:** A [Goroutine](https://www.geeksforgeeks.org/goroutines-concurrency-in-golang/) is a function or method which <u>executes independently and simultaneously in connection with any other Goroutines present in your program</u>. Or in other words, every concurrently executing activity in Go language is known as a Goroutines.

**Thread:** A process is a part of an operating system which is responsible for executing an application. Every program that executes on your system is a process and to run the code inside the application a process uses a term known as a thread. **A thread is a lightweight process**, or in other words, a thread is a unit which executes the code under the program. So every program has logic and a thread is responsible for executing this logic.

Here are some of the differences between Goroutine and Thread:



|                          GOROUTINE                           |                            THREAD                            |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|        Goroutines are managed by the **go runtime**.         |       Operating system threads are managed by kernal.        |
|            Goroutine are not hardware dependent.             |             Threads are **hardware dependent**.              |
| Goroutines have **easy communication medium known as channel**. |        Thread doesnot have easy communication medium.        |
| Due to the presence of channel one goroutine can communicate with other goroutine with low latency. | Due to lack of easy communication medium inter-threads **communicate takes place with high latency**. |
| Goroutine doesnot have ID because go doesnot have Thread Local Storage. | Threads have their own unique ID because they have Thread Local Storage. |
|             Goroutines are cheaper than threads.             |        The cost of threads are higher than goroutine.        |
|              They are cooperatively scheduled.               |               They are preemptively scheduled.               |
|         They have fasted startup time than threads.          |         They have slow startup time than goroutines.         |
|           Goroutine has growable segmented stacks.           |       Threads doesnot have growable segmented stacks.        |

The cost of threads are higher than goroutine


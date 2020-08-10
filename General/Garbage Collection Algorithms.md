## Java Garbage Collection

### Object Life Cycle

A java’s object life cycle can be seen in 3 stages:

1. #### Object creation

   When an object is created, a specific amount of memory is allocated for storing that object. The amount of memory allocated can <u>differ based on architecture and JVM</u>.

2. #### Object in use

   Till the time, object is used by application’s other objects (other live objects have references pointing to it). During its usage, object reside in memory and may contain references to other objects.

3. #### Object destruction

   The garbage collection system monitors objects and, as feasible, **counts the number of references to each object**. When there are no references to an object, there is no way to get to it with the currently running code, marks it as eligible for GC (to de-allocate the associated memory).

### Memory management in Java

Memory management in Java is the responsibility of garbage collector. GC is responsible for –

- allocating memory
- ensuring that any referenced objects remain in memory, and
- recovering memory used by objects that are no longer reachable from references in executing code.

During application runtime, objects that are referenced by other objects are said to be **live objects**. Objects that are no longer referenced by any live object are considered **dead objects** and are termed **garbage**. The process of finding and freeing (also known as reclaiming) the space used by these objects is known as garbage collection. Garbage collection is a complex task which takes time and resources of its own. It is run on space which is commonly allocated from a large pool of memory referred to as the **heap**. <u>The timing of garbage collection is up to the garbage collector</u>. Typically, the entire heap or a sub-part of it is collected either when it fills up or when it reaches a **threshold percentage of occupancy**.

How to improve memory utilization in Java

- **Do not allocate excessive memory**. Allocate memory only just as much needed. This is specially applicable to **Java arrays**.
- **Don’t hold on to references**. Once the object is used and no longer needed, **assign null reference** to it.
- Find and **resolve memory leaks**
- Do **system profiling** on each release to verify **memory bumps**.
- Do not rely on System.gc() to run garbage collection.

### Reference counting mechanism

In reference counting technique, each object has **count of number of pointers to it** from other objects and from the stack. Every time a new object reference it, counter increment by one. Similarly, when any object loses its reference, the counter decrement by one. When count reaches ‘0’, object can be de-allocated by garbage collector.

The main advantage of reference counting algorithm has been **small amount of work per memory write when allocating to new object**. But, it has very **critical problem with data cycles**. It means when first object was referred with second object, and second is referred with first object (**cyclic references**), then **count never comes to zero**, hence they never get garbage collected.

### GC Algorithms

**Garbage collection** (GC) is the mechanism used in Java to <u>deallocate unused memory</u> (or dereference the objects). Essentially, it is **tracking down all the objects that are still used and marks the rest as garbage** and is an <u>automatic memory management schema</u> (done at JVM level by the **garbage collector**). The garbage collection always runs on <u>low-priority threads</u>.

#### Mark and sweep

It is the initial and very basic algorithm which **runs in two stages**:

1. **Marking live objects** – find out all objects that are still alive.
2. **Removing unreachable objects** – get rid of everything else; the supposedly dead and unused objects.

**Marking live objects** - GC defines some specific objects as **Garbage Collection Roots**. e.g. local variable and input parameters of the currently executing methods, active threads, static field of the loaded classes and JNI references. Now GC traverses the whole **object graph** in your memory, starting from those roots and following references from the roots to other objects. Every object the GC visits is marked as alive.

> The application threads need to be stopped for the marking to happen as it cannot really traverse the graph if it keeps changing. It is called **Stop The World pause**.
>

**Removing unreachable objects** -  Getting rid of unused objects to free-up memory. This can be done in variety of ways.

- **Normal deletion** – Normal deletion removes unreferenced objects to free space. The **memory allocator** (kind of hashtable) holds references to blocks of free space where new object can be allocated. It is often referred as **mark-sweep algorithm**.

- **Deletion with compacting** – Only removing unused objects is not efficient because <u>blocks of free memory is scattered across storage area</u> and cause OutOfMemoryError, if created object big enough and does not find large enough memory block. To solve this issue, after deleting unreferenced objects, <u>compacting is done on the remaining referenced objects</u>. Here compacting refer the **process of moving referenced objects together**. This makes new memory allocation much easier and faster. It is often referred as **mark-sweep-compact algorithm**.

  ![Deletion with compacting](https://raw.githubusercontent.com/SnowflakeCoder/programming/master/General/images/Deletion-with-compacting.png)

- **Deletion with copying** – It is very similar to mark and compacting approach as they too relocate all live objects. The important difference is that the **target of relocation is a different memory region**. It is often referred as **mark-copy algorithm**.

![Deletion with copying - Mark and Sweep](https://raw.githubusercontent.com/SnowflakeCoder/programming/master/General/images/Deletion-with-copying-Mark-and-Sweep.png)

#### Mark and sweep mechanism

The mark-and-sweep algorithm was the **first garbage collection algorithm** to be developed that is able to **reclaim cyclic data structures**. In this algorithm, GC will first identify some objects as **default reachable** which are generally global variables and local variables in stack. There are called **live objects**. In next step, algorithm start tracing the objects from these live objects and mark them live also. This procedure goes on until all the objects are examined and marked as live. The objects not marked live after full tracing are assumed dead objects.

When using mark-and-sweep, **un-referenced objects are not reclaimed immediately**. Instead, garbage collection is allowed to accumulate until all available memory has been exhausted. When that happens, the execution of the **program is suspended temporarily** (It is called **stop the world**) while the mark-and-sweep algorithm collects all the garbage. Once all un-referenced objects have been reclaimed, the normal execution of the program can resume.

This technique, apart from pausing the application for some duration, also need to do **de-fragmentation of memory address space** frequently which is another overhead.

#### Stop and copy GC

Like “mark and sweep”, this algorithm also depends on **identifying the live objects and marking them**. The **difference lies in how it handles live objects**. Stop and copy technique devises the whole heap in **two semi-spaces**. Only one semispace is active at a time, and memory allocation for newly created objects takes place only single semispace, while the other remain calm.

When GC run, it starts marking live objects in current semispace and when its done, it copies all live objects to other semispace. All the remaining objects in current semispace are considered dead and they are garbage collected.

Main disadvantages of this approach is it only touches live objects. Additionally, **no fragmentation is required** because while switching semispaces, memory contraction is done. Main disadvantages of this approach is the **need to twice the size of memory needed**, because only half is used at a given point of time. Other than this, it also required to **stop the world** while switching the semi spaces.

#### Generational stop and copy

Like “stop and copy” technique, it also divides the memory in semispaces but they are now **three semispaces**. These semispaces are called here **generations**. So, memory in this technique is organized into three generations- **young generation**, **old generation**, and **permanent generation**.

Most objects are initially allocated in the young generation. The old generation contains objects that have **survived some number of young generation collections**, as well as some **large objects** that may be **allocated directly in the old generation**. The permanent generation holds objects that the JVM finds convenient to have the garbage collector manage, such as **objects describing classes and methods**.

When the young generation fills up, a young generation garbage collection (a.k.a **minor collection**) of just that generation is performed. When the old or permanent generation fills up, what is known as a **full garbage collection** (a.k.a **major collection**) is typically done. That is, all generations are collected.

Commonly, the **young generation is collected first**, using the garbage collection algorithm designed specifically for that generation, because it is usually the **most efficient algorithm** for identifying garbage in the young generation. Objects that **survive GC traces, get pushed into older generations**. The older generations are collected less often for obvious reasons i.e. they are there because they will be for longer time. Apart from above if **fragmentation/compaction** occurs, each generation is compacted separately.

The main advantages of this technique is to **reclaim dead objects early in younger generation** itself and do not need to scan whole memory everytime to identify dead objects. Older generation objects have already passed some GC cycles so they are assumed to be in system for longer time, so **no need to scan them frequently**.

Disadvantages are need to **defragment memory areas** and **need to stop the world** (application) while the GC is running full scan.

#### Concurrent mark sweep (CMS) GC

CMS garbage collection is essentially an **upgraded mark and sweep method**. It scans heap memory using **multiple threads**. It was modified to take advantage of faster systems and had performance enhancements.

It attempts to minimize the pauses due to garbage collection by doing most of the garbage collection work **concurrently** with the application threads. It uses the **parallel stop-the-world mark-copy algorithm** in the Young Generation and the mostly concurrent mark-sweep algorithm in the Old Generation.

To use CMS GC, use below **JVM argument**: -XX:+UseConcMarkSweepGC

##### CMS GC Optimization Options

| FLAG                              | DESCRIPTION                                                  |
| --------------------------------- | ------------------------------------------------------------ |
| -XX:CMSTriggerRatio=70            | This is the percentage of `MinHeapFreeRatio` in CMS generation that is allocated prior to a CMS cycle starts. |
| -XX:CMSTriggerPermRatio=90        | Sets the percentage of `MinHeapFreeRatio` in the CMS permanent generation that is allocated before starting a CMS collection cycle. |
| -XX:CMSWaitDuration=2000          | Use the parameter to specify how long the CMS is allowed to wait for young collection. |
| -XX:+UseParNewGC                  | Elects to use the parallel algorithm for young space collection. |
| -XX:+CMSConcurrentMTEnabled       | Enables the use of multiple threads for concurrent phases.   |
| -XX:ConcGCThreads=2               | Sets the number of parallel threads used for the concurrent phases. |
| -XX:ParallelGCThreads=2           | Sets the number of parallel threads you want used for *stop-the-world* phases. |
| -XX:+CMSClassUnloadingEnabled     | If this is not enabled, CMS will **not clean permanent space**. |
| -XX:+ExplicitGCInvokes\Concurrent | This allows `System.gc()` to **trigger concurrent collection** instead of a full garbage collection cycle. |

#### Serial garbage collection

This algorithm uses **mark-copy for the Young Generation** and **mark-sweep-compact for the Old Generation**. It works on a **single thread**. When executing, it freezes all other threads until garbage collection operations have concluded. Due to the **thread-freezing nature** of serial garbage collection, it is only feasible for very small programs. 

#### Parallel garbage collection

Similar to serial GC, It uses mark-copy in the Young Generation and mark-sweep-compact in the Old Generation. Multiple concurrent threads are **used for marking and copying / compacting phases**. You can configure the number of threads using -XX:ParallelGCThreads=N option.

Parallel Garbage Collector is suitable on **multi-core machines** in cases where your primary goal is to increase throughput by efficient usage of existing system resources. Using this approach, GC cycle times can be considerably reduced. **Till Java 8**, we have seen Parallel GC as default garbage collector. **Java 9** onwards, **G1 is the default garbage collector** on 32- and 64-bit server configurations.

#### G1 garbage collection

The G1 collector is a **parallel, concurrent**, and incrementally compacting **low-pause garbage collector**. This approach involves **segmenting the memory heap into multiple small regions** (typically 2048). Each region is marked as either young generation (further divided into **eden regions** or **survivor regions**) or old generation. This allows the GC to **avoid collecting the entire heap at once**, and instead approach the problem incrementally. It means that only a subset of the regions is considered at a time.

G1 keep tracking of the **amount of live data that each region** contains. This information is used in determining the **regions that contain the most garbage**; so they are collected first. That’s why it is name **garbage-first collection**.

Just like other algorithms, unfortunately, the compacting operation takes place using the **Stop the World approach**. But as per it’s design goal, you can **set specific performance goals** to it. You can configure the **pauses duration** e.g. no more than 10 milliseconds. Garbage-First GC will do its best to meet this goal with high probability (but not with certainty, that would be hard real-time due to **OS level thread management**).

| G1 Optimization FLAG     | DESCRIPTION                                                  |
| ------------------------ | ------------------------------------------------------------ |
| -XX:G1HeapRegionSize=16m | Size of the heap region. The value will be a power(2) and can range from 1MB to 32MB. The goal is to have around 2048 regions based on the minimum Java heap size. |
| -XX:MaxGCPauseMillis=200 | Sets desired maximum pause duration  time. The default value is 200 milliseconds. |

#### GC configuration flags

| FLAG                    | DESCRIPTION                                                  |
| ----------------------- | ------------------------------------------------------------ |
| -Xms2048m -Xmx3g        | Sets the initial and maximum heap size (young space plus tenured space). |
| -XX:+DisableExplicitGC  | This will cause the JVM to **ignore any System.gc()** method invocations by an application. |
| -XX:+UseGCOverheadLimit | This is the use policy used to limit the time spent in garbage collection before an OutOfMemory error is thrown. |
| -XX:GCTimeLimit=95      | This limits the proportion of time spent in garbage collection before an `OutOfMemory` error is thrown. This is used with `GCHeapFreeLimit`. |
| -XX:GCHeapFreeLimit=5   | This sets the **minimum percentage of free space** after a full garbage collection before an `OutOfMemory` error is thrown. This is used with `GCTimeLimit`. |
| -XX:InitialHeapSize=3g  | Sets the initial heap size (young space plus tenured space). |
| -XX:MaxHeapSize=3g      | Sets the maximum heap size (young space plus tenured space). |
| -XX:NewSize=128m        | Sets the initial size of young space.                        |
| -XX:MaxNewSize=128m     | Sets the maximum size of young space.                        |
| -XX:SurvivorRatio=15    | Sets the size of single survivor space as a portion of Eden space size. |
| -XX:PermSize=512m       | Sets the initial size of the permanent space.                |
| -XX:MaxPermSize=512m    | Sets the maximum size of the permanent space.                |
| -Xss512k                | Sets the **size of the stack area dedicated to each thread** in bytes. |

#### GC logging flags

| FLAG                             | DESCRIPTION                                                  |
| -------------------------------- | ------------------------------------------------------------ |
| -verbose:gc or -XX:+PrintGC      | This prints the **basic** garbage collection information.    |
| -XX:+PrintGCDetails              | This will print **more detailed** garbage collection information. |
| -XX:+PrintGCTimeStamps           | You can print **timestamps for each GC event**. The seconds are sequential and begin from the JVM start time. |
| -XX:+PrintGCDateStamps           | You can print **date stamps** for each GC event.             |
| -Xloggc:                         | Using this you can redirect garbage collection **output to a file** instead of the console. |
| -XX:+Print\TenuringDistribution  | You can print detailed information regarding **young space** following each collection cycle. |
| -XX:+PrintTLAB                   | You can use this flag to print **TLAB allocation statistics**. |
| -XX:+PrintReferenceGC            | Using this flag, you can print the times for reference processing (that is, weak, soft, and so on) during stop-the-world pauses. |
| -XX:+HeapDump\OnOutOfMemoryError | This creates a **heap dump file** in an out-of-memory condition. |



choose the best garbage collector



Trade-offs

When we talk about garbage collection we generally have to consider **three things**.

**Memory** : This is the amount of memory that is assigned to the program, this is also called as the heap. 

**Throughput**: throughput is how much **amount of time that a code is run** compared to how much amount of time your garbage collection is run. So for example if the throughput is 99% that means that 99% of the time your code was running and 1% of the time that garbage collection was running. So ideally you would want as much **higher throughput** as possible.

**Latency**: latency is whenever garbage collection runs how much **amount of time our program stops** for the garbage collection to run properly. These are generally measured in milliseconds but they can go up to a few seconds depending on the size of the memory and the garbage collection algorithm that issues. Ideally we would want the **latency to be as low as possible** or as predictable as possible.

![img](https://raw.githubusercontent.com/SnowflakeCoder/programming/master/General/images/gc-%20trade-offs.png)



Generational hypothesis

This hypothesis says that **most objects die young** and that is why many of the GC algorithms split the heap size into a **young generation** and an **old generation**. So whenever you first create the objects they are kept in to the young generation. Most of them die young (eligible for garbage collection very quickly) that's why you have a **lot of garbage collection run only on this young generation** and that collection is called a **minor collection**. 

Objects like **class level variables** or instance level variables will have a much longer lifetime and that is why even after a lot of minor collections when the objects are still not eligible for garbage collection they are **promoted into this old generation**. Whenever there are a lot of objects in the old generation so let's say if the amount of space occupied goes beyond the threshold then there is a **major collection** triggered because now you have to clean up the old generation also and in this case generally a different kind of algorithm is run on the older generation.

mark and copy algorithm

So within the young generation generally the space is divided as **Eden space** and **two survivor spaces**. So all the **new objects are allocated to the Eden space**. Whenever time comes for a **minor collection** only the live objects of this Eden space is **copied over to the survivor space**. This involves both things, it will first mark all the objects which are live (still being used) and not eligible for garbage collection and in second stage it will copy over all those live objects in one of the survivor space. Once it copies all the live objects now this **whole Eden space is wiped out** (an empty space) and the allocation of new objects starts again.  Take a look at the below steps to understand why there are two survivor spaces.

![young generation.png](https://github.com/SnowflakeCoder/programming/blob/master/General/images/young%20generation.png?raw=true)

Mark-sweep and compact algorithm

This is generally run on the **old generation**. First will mark only the live objects then we will **sweep and remove** all the objects which are eligible for GC. Technically speaking we do not remove it we just update a data structure saying that these spaces are now empty. Now we'll move all these live objects which are still being used, on to the left side and we'll cluster them together.

The advantage of this compaction is afterwards when you when you want to allocate new objects all you have to do is keep a pointer to the end of the last live object. So that everything on the left is being utilized everything on the right is free. Whenever you want to allocate a new object just put that object here and move the pointer to the end of that object so again everything on the left side is being utilized and everything on the right side is free.

garbage collection algorithms 

serial collector

serial collector has the **smallest footprint** of any of the collectors. So the amount of data structures the footprint required for this garbage collector to run is very **very minimal**. This collector **uses single thread** for both minor as well as major collections. This serial collector also uses a ponter to indentify live objects as explained above while allocate any objects and that is why allocation is much faster. This collector is generally best for devices which are very **restrained restricted memory** or if your application is being run on a **shared CPU**.

Let's imagine we have a quad-core CPU and we have four applications running on it. If your garbage collector was not single threaded (multi-threaded) then at some point in time your garbage collector will start all four threads on four cores of the CPU and **utilize that entire CPU for its own garbage collection** and that is when the other applications running on the CPU will suffer. So if there are **multiple applications running on a single CPU** then you can **use serial collector** to ensure that your garbage collection **does not affect other cores / applications**.

parallel collector

we generally only talk about **parallel old collector** which uses multiple threads for both minor GC as well as major GC. This collector **does not run concurrently** with the application. It's named parallel because it has **multiple threads** of the garbage collection it's self and all of those threads run parallelly. but while the GC is running **all the application threads are stopped**. So if your application is deployed on a multi-core from multiprocessor systems then this collector will give you the **greatest throughput** (shortest amount of time it will be able to collect the highest amount of garbage possible). Since it **stops the entire application** for some time it is **best only for batch applications**. So in the batch application you do not care about the **users the response time** because there is no user on the front end. It's a batch application and it's running behind the scenes so you want the program to run as efficiently as possible. So **for batch applications** the best collector to use is a parallel collector.

CMS (concurrent mark and sweep) collector

This collector runs **concurrently with the application** to mark all the live objects right so the amount of time that the application has to stop is less. So the latency of the application is less. But of course during the actual collection it still has **STW (stop the world) pauses**. That means it stops the application for a very small amount of time to do its actual garbage collection. But it's **not as bad as the parallel collector** but it **requires more footprint** than parallel collector. So it has more data structures that it needs to take care and has **less throughput** than the parallel collector. But the advantage of this is it has **smaller pause times than the parallel collector**. That is why it is best used for **general applications**.

G1 Collector

The **improvement over the CMS collector** is called g1 collector. g1 collector is  **garbage first collector**. So instead of having a specific young generation and old generation this collector **uses the entire heap** and **divides it into multiple regions** and it itself assigns whether this region is **young generation region** or an **old generation region**. Compared to the previous collector CMS it has **more footprint**, so it has even more requirement for data structures. 

But the advantage of this is it this has **more predictable latency** and this is the **best feature of this collector**. So when you start the application you can pass a variable **maxTargetPauseTime** that the **maximum pause time** that my application can withstand is say 10 milliseconds. My application cannot handle more than 10 milliseconds of pause time. Of course this is **not a very hard target** it's a soft target. The g1 collector will try to ensure that the garbage collection is done only for 10 milliseconds and even **if there is some garbage left then it will take care of it in the next cycle** and will allow the application to run after 10 milliseconds. That is why G1 collector is best **for applications which need predictable latency**. So CMS was great for general applications, it has lower pause times but if you want predictable pause times (**predictable latency**) then you can use the G1 collector.

Shenondoah Collector

Shenondoah is an **improvement upon G1 collector** wherein it requires a little **higher footprint** (takes more data structures) but it has even **lower latency than G1**.

![GC table.png](https://github.com/SnowflakeCoder/programming/blob/master/General/images/GC%20table.png?raw=true)

- **Serial collector** is for small devices or when you want to ensure that the GC doesn't affect other CPUs.
- **Parallel collector** is best for batch applications. 
- **CMS collector** is best for general applications. 
- **G1 collector** is best if you want predictable latency.
- Shenondoah is an **improvement upon G1 collector**. 

https://howtodoinjava.com/java/garbage-collection/all-garbage-collection-algorithms/

https://www.geeksforgeeks.org/garbage-collection-java/

https://www.geeksforgeeks.org/mark-and-sweep-garbage-collection-algorithm/

https://www.youtube.com/watch?v=2AZ0KKeXJSo

https://www.geeksforgeeks.org/types-of-jvm-garbage-collectors-in-java-with-implementation-details/?ref=rp
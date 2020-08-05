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

### Reference counting mechanism

In reference counting technique, each object has **count of number of pointers to it** from other objects and from the stack. Every time a new object reference it, counter increment by one. Similarly, when any object loses its reference, the counter decrement by one. When count reaches ‘0’, object can be de-allocated by garbage collector.

The main advantage of reference counting algorithm has been **small amount of work per memory write when allocating to new object**. But, it has very critical problem with **data cycles**. It means when first object was referred with second object, and second is referred with first object (**cyclic references**), then **count never comes to zero**, hence they never get garbage collected.

### GC Algorithms

**Garbage collection** (GC) is the mechanism used in Java to <u>deallocate unused memory</u> (or dereference the objects). Essentially, it is **tracking down all the objects that are still used and marks the rest as garbage** and is an <u>automatic memory management schema</u> (done at JVM level by the **garbage collector**). The garbage collection always runs on <u>low-priority threads</u>.

#### Mark and sweep

It is initial and very basic algorithm which **runs in two stages**:

1. **Marking live objects** – find out all objects that are still alive.
2. **Removing unreachable objects** – get rid of everything else; the supposedly dead and unused objects.

**Marking live objects** - GC defines some specific objects as **Garbage Collection Roots**. e.g. local variable and input parameters of the currently executing methods, active threads, static field of the loaded classes and JNI references. Now GC traverses the whole **object graph** in your memory, starting from those roots and following references from the roots to other objects. Every object the GC visits is marked as alive.

> The application threads need to be stopped for the marking to happen as it cannot really traverse the graph if it keeps changing. It is called **Stop The World pause**.
>

**Removing unreachable objects** -  Getting rid of unused objects to free-up memory. This can be done in variety of ways.

- **Normal deletion** – Normal deletion removes unreferenced objects to free space. The **memory allocator** (kind of hashtable) holds references to blocks of free space where new object can be allocated. It is often referred as **mark-sweep algorithm**.

- **Deletion with compacting** – Only removing unused objects is not efficient because <u>blocks of free memory is scattered across storage area</u> and cause OutOfMemoryError, if created object big enough and does not find large enough memory block. To solve this issue, after deleting unreferenced objects, <u>compacting is done on the remaining referenced objects</u>. Here compacting refer the **process of moving referenced objects together**. This makes new memory allocation much easier and faster. It is often referred as **mark-sweep-compact algorithm**.

  ![Deletion with compacting](https://howtodoinjava.com/wp-content/uploads/2018/04/Deletion-with-compacting.png)

- **Deletion with copying** – It is very similar to mark and compacting approach as they too relocate all live objects. The important difference is that the **target of relocation is a different memory region**. It is often referred as **mark-copy algorithm**.

![Deletion with copying - Mark and Sweep](https://howtodoinjava.com/wp-content/uploads/2018/04/Deletion-with-copying-Mark-and-Sweep.png)

#### Mark and sweep mechanism

The mark-and-sweep algorithm was the first garbage collection algorithm to be developed that is able to **reclaim cyclic data structures**. In this algorithm, GC will first identify some objects as **default reachable** which are generally global variables and local variables in stack. There are called **live objects**.

In next step, algorithm start tracing the objects from these live objects and mark them live also. This procedure goes on until all the objects are examined and marked as live. The objects not marked live after full tracing are assumed dead objects.

When using mark-and-sweep, **un-referenced objects are not reclaimed immediately**. Instead, garbage collection is allowed to accumulate until all available memory has been exhausted. When that happens, the execution of the **program is suspended temporarily** (It is called **stop the world**) while the mark-and-sweep algorithm collects all the garbage. Once all un-referenced objects have been reclaimed, the normal execution of the program can resume.

This technique, apart from pausing the application for some duration, need to do **de-fragmentation of memory address space** frequently which is another overhead.

#### Stop and copy GC

Like “mark and sweep”, this algorithm also depends on **identifying the live objects and marking them**. The **difference lies in how it handles live objects**. Stop and copy technique devises the whole heap in **two semi-spaces**. Only one semispace is active at a time, and memory allocation for newly created objects takes place only single semispace, while the other remain calm.

When GC run, it starts marking live objects in current semispace and when its done, it copies all live objects to other semispace. All the remaining objects in current semispace are considered dead and they are garbage collected.

Main disadvantages of this approach is it only touches live objects. Additionally, no fragmentation is required because while switching semispaces, memory contraction is done. Main disadvantages of this approach is the **need to twice the size of memory needed**, because only half is used at a given point of time. Other than this, it also required to **stop the world** while switching the semi spaces.

#### Generational stop and copy

Like “stop and copy” technique, it also divides the memory in semispaces but they are now **three semispaces**. These semispaces are called here **generations**. So, memory in this technique is organized into three generations- **young generation**, **old generation**, and **permanent generation**.

Most objects are initially allocated in the young generation. The old generation contains objects that have **survived some number of young generation collections**, as well as some **large objects** that may be **allocated directly in the old generation**. The permanent generation holds objects that the JVM finds convenient to have the garbage collector manage, such as **objects describing classes and methods**, as well as the classes and methods themselves.

When the young generation fills up, a young generation garbage collection (a.k.a **minor collection**) of just that generation is performed. When the old or permanent generation fills up, what is known as a **full garbage collection** (a.k.a **major collection**) is typically done. That is, all generations are collected.

Commonly, the **young generation is collected first**, using the garbage collection algorithm designed specifically for that generation, because it is usually the **most efficient algorithm** for identifying garbage in the young generation. Objects that **survive GC traces, get pushed into older generations**. The older generations are collected less often for obvious reasons i.e. they are there because they will be for longer time. Apart from above if **fragmentation/compaction** occurs, each generation is compacted separately.

The main advantages of this technique is to **reclaim dead objects early in younger generation** itself and do not need to scan whole memory everytime to identify dead objects. Older generation objects have already passed some GC cycles so they are assumed to be in system for longer time, so **no need to scan them frequently**.

Disadvantages are need to **defragment memory areas** and **need to stop the world** (application) while the GC is running full scan.

How to improve memory utilization in Java

- **Do not allocate excessive memory**. Allocate memory only just as much needed. This is specially applicable to **Java arrays**.
- **Don’t hold on to references**. Once the object is used and no longer needed, **assign null reference** to it.
- Find and **resolve memory leaks**
- Do **system profiling** on each release to verify memory bumps
- Do not rely on System.gc() to run garbage collection.

#### Concurrent mark sweep (CMS) GC

CMS garbage collection is essentially an **upgraded mark and sweep method**. It scans heap memory using **multiple threads**. It was modified to take advantage of faster systems and had performance enhancements.

It attempts to minimize the pauses due to garbage collection by doing most of the garbage collection work **concurrently** with the application threads. It uses the **parallel stop-the-world mark-copy algorithm** in the Young Generation and the mostly concurrent mark-sweep algorithm in the Old Generation.

To use CMS GC, use below JVM argument: -XX:+UseConcMarkSweepGC

##### CMS GC Optimization Options

| FLAG                                   | DESCRIPTION                                                  |
| -------------------------------------- | ------------------------------------------------------------ |
| -XX:+UseCMSInitiating\OccupancyOnly    | Indicates that you want to solely use occupancy as a criterion for starting a CMS collection operation. |
| -XX:CMSInitiating\OccupancyFraction=70 | Sets the percentage CMS generation occupancy to start a CMS collection cycle. |
| -XX:CMSTriggerRatio=70                 | This is the percentage of `MinHeapFreeRatio` in CMS generation that is allocated prior to a CMS cycle starts. |
| -XX:CMSTriggerPermRatio=90             | Sets the percentage of `MinHeapFreeRatio` in the CMS permanent generation that is allocated before starting a CMS collection cycle. |
| -XX:CMSWaitDuration=2000               | Use the parameter to specify how long the CMS is allowed to wait for young collection. |
| -XX:+UseParNewGC                       | Elects to use the parallel algorithm for young space collection. |
| -XX:+CMSConcurrentMTEnabled            | Enables the use of multiple threads for concurrent phases.   |
| -XX:ConcGCThreads=2                    | Sets the number of parallel threads used for the concurrent phases. |
| -XX:ParallelGCThreads=2                | Sets the number of parallel threads you want used for *stop-the-world* phases. |
| -XX:+CMSIncrementalMode                | Enable the incremental CMS (iCMS) mode.                      |
| -XX:+CMSClassUnloadingEnabled          | If this is not enabled, CMS will not clean permanent space.  |
| -XX:+ExplicitGCInvokes\Concurrent      | This allows `System.gc()` to trigger concurrent collection instead of a full garbage collection cycle. |

#### Serial garbage collection

This algorithm uses **mark-copy for the Young Generation** and **mark-sweep-compact for the Old Generation**. It works on a **single thread**. When executing, it freezes all other threads until garbage collection operations have concluded. Due to the **thread-freezing nature** of serial garbage collection, it is only feasible for very small programs. 

#### Parallel garbage collection

Simimar to serial GC, It uses mark-copy in the Young Generation and mark-sweep-compact in the Old Generation. Multiple concurrent threads are **used for marking and copying / compacting phases**. You can configure the number of threads using -XX:ParallelGCThreads=N option.

Parallel Garbage Collector is suitable on **multi-core machines** in cases where your primary goal is to increase throughput by efficient usage of existing system resources. Using this approach, GC cycle times can be considerably reduced. Till Java 8, we have seen Parallel GC as default garbage collector. Java 9 onwards, G1 is the default garbage collector on 32- and 64-bit server configurations.

#### G1 garbage collection

The G1 collector is a **parallel, concurrent**, and incrementally compacting **low-pause garbage collector**. This approach involves **segmenting the memory heap into multiple small regions** (typically 2048). Each region is marked as either young generation (further divided into **eden regions** or **survivor regions**) or old generation. This allows the GC to **avoid collecting the entire heap at once**, and instead approach the problem incrementally. It means that only a subset of the regions is considered at a time.

G1 keep tracking of the **amount of live data that each region** contains. This information is used in determining the **regions that contain the most garbage**; so they are collected first. That’s why it is name garbage-first collection.

Just like other algorithms, unfortunately, the compacting operation takes place using the **Stop the World approach**. But as per it’s design goal, you can **set specific performance goals** to it. You can configure the pauses duration e.g. no more than 10 milliseconds in any given second. Garbage-First GC will do its best to meet this goal with high probability (but not with certainty, that would be hard real-time due to **OS level thread management**).

##### G1 Optimization Options

| FLAG                          | DESCRIPTION                                                  |
| ----------------------------- | ------------------------------------------------------------ |
| -XX:G1HeapRegionSize=16m      | Size of the heap region. The value will be a power of two and can range from 1MB to 32MB. The goal is to have around 2048 regions based on the minimum Java heap size. |
| -XX:MaxGCPauseMillis=200      | Sets a target value for desired maximum pause time. The default value is 200 milliseconds. The specified value does not adapt to your heap size. |
| -XX:G1ReservePercent=5        | This determines the minimum reserve in the heap.             |
| -XX:G1ConfidencePercent=75    | This is the confidence coefficient pause prediction heuristics. |
| -XX:GCPauseIntervalMillis=200 | This is the pause interval time slice per MMU in milliseconds. |

#### GC configuration flags

| FLAG                    | DESCRIPTION                                                  |
| ----------------------- | ------------------------------------------------------------ |
| -Xms2048m -Xmx3g        | Sets the initial and maximum heap size (young space plus tenured space). |
| -XX:+DisableExplicitGC  | This will cause the JVM to ignore any System.gc() method invocations by an application. |
| -XX:+UseGCOverheadLimit | This is the use policy used to limit the time spent in garbage collection before an OutOfMemory error is thrown. |
| -XX:GCTimeLimit=95      | This limits the proportion of time spent in garbage collection before an `OutOfMemory` error is thrown. This is used with `GCHeapFreeLimit`. |
| -XX:GCHeapFreeLimit=5   | This sets the minimum percentage of free space after a full garbage collection before an `OutOfMemory` error is thrown. This is used with `GCTimeLimit`. |
| -XX:InitialHeapSize=3g  | Sets the initial heap size (young space plus tenured space). |
| -XX:MaxHeapSize=3g      | Sets the maximum heap size (young space plus tenured space). |
| -XX:NewSize=128m        | Sets the initial size of young space.                        |
| -XX:MaxNewSize=128m     | Sets the maximum size of young space.                        |
| -XX:SurvivorRatio=15    | Sets the size of single survivor space as a portion of Eden space size. |
| -XX:PermSize=512m       | Sets the initial size of the permanent space.                |
| -XX:MaxPermSize=512m    | Sets the maximum size of the permanent space.                |
| -Xss512k                | Sets the size of the stack area dedicated to each thread in bytes. |

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





how to choose the best garbage collector for your use case before we do that let's try to understand some of the garbage collection concepts we will talk about trade-offs their generational GC and two algorithms called mark and copy and mark and sweep so let's start with trade-offs when we talk about garbage collection we generally have to consider three things the first thing is the memory this is the amount of memory that is assigned to the program this is also called as the heap the second thing that we need to understand is the true put throughput is how much amount of time that a code is run compared to how much amount of time your garbage collection is run so for example if the throughput is 99% that means that 99% of the time your code was running and 1% of the time that garbage collection was running so of course ideally you would want as much higher throughput as possible and the third aspect that we need to understand is the latency latency is whenever garbage collection runs how much amount of time our program stops for the garbage collection to run properly these are generally measured in milliseconds but they can go up to a few seconds depending on the size of the memory and the garbage collection algorithm that issues ok of course ideally we would want the latency to be as low as possible or as predictable as possible the second concept that we need to learn is called generational hypothesis this hypothesis says that most objects die young so whenever you use any object or variable whenever you create object or variable within a function or within a for loop and that does not escape the for loop or function so it is limited only to that scope then of course as soon as the core comes out of the for loop or comes out of the function that object is eligible for garbage collection so this hypothesis says that most of our variables are of this kind they die young and that is why many of the garbage collection algorithms split your heap size into what is called a young generation and an origin so whenever you first create the objects they are kept in to the young generation of course most of them die young or they are eligible for garbage collection very quickly that's why you have a lot of garbage collection run only on this young generation and that collection is called a minor collection if there are objects like your class level variables which are also called instance level variables then they of course remain that any or or the lifetime remains much longer and that is why even after a lot of minor collections when the objects are still not eligible for garbage collection they are promoted into this old generation whenever there are a lot of objects in the old generation so let's say if the amount of space occupied in the old generation goes beyond the threshold for example 60% or 70% then there is a major collection triggered because now you have to clean up the old generation also and in this case generally a different kind of algorithm is run on the older generation now the third concept that we need to understand is the mark and copy algorithm so within the young generation generally the space is divided as hidden space and to survivor spaces so all the new objects are allocated to the hidden space whenever time comes for a mine collection only the live objects of this leaden space is copied over to the cyber survivor space and this involves both things so it will first mark all the objects which are live that means which are still being used and not eligible for garbage collection and in second stage it will copy over all those live objects in this survivor space either one or two once it copies all the live objects now this Eden space consists of objects which are you have already copied and the objects which are eligible for garbage collection and that is why this whole Eden space is wiped out or it is just considered that it is an empty space there is nothing of elements there and the allocation of new objects starts again and you can take a look at these steps to understand why there are two spaces survivor space one and two the second algorithm and the fourth concept that we need to understand is the algorithm called mark-sweep and compact algorithm this is generally done on the old generation here let's say we have a lot of allocated objects some of them on life some of them are eligible for garbage collection first will mark only the live objects right so we'll mark this object which is live which is this object and this object second is we will sweep and we'll remove all the objects which are eligible for garbage collection so we'll remove all the spaces here at the top and make it black generally of course technically speaking we do not remove it we just update a data structure behind the scene saying that these spaces are now empty and then the third aspect of this is compaction so we'll move all these live objects which are still being used and we'll move them on to the left side and we'll fluster them together the advantage of this compaction is afterwards when you when you want to allocate new objects all you have to do is keep a pointer here right keep a reference that everything on the left is being utilized everything on the right is free and whenever you want to allocate a new object just put that object here and bump the pointer to the end of that object so again everything on the left side is being utilized and everything on the right side is free now that we understand all these four concepts let's take a look at the actual garbage collection algorithms serial collector has the smallest footprint of any of the collectors so then the amount of data structures the footprint required for this garbage collector to run is very very minimal this collector uses single thread for both minor as well as miniature collections and as we saw in the earlier slide about compactions and bump the pointer technique the serial collector uses that technique to allocate any objects and that is why allocation is much faster this collector is generally best for devices which are very restrained restricted memory or if you have your application is being run on a shared CPU so let's imagine we have a CPU of say quad-core CPU and we have four applications applications running on it if your garbage collector was not single threaded and it was multi-threaded then it is possible that at some point in time your garbage collector will start all full threads on four cores of the CPU and utilize that entire CPU for its own garbage collection and that is when the other applications running on the CPU will suffer so if there are multiple applications running on a single CPU and you want to ensure that your garbage collection does not affect other course or other applications then you can use serial collector the next collector to understand is called the parallel collector we generally only talk about parallel old collector which uses multiple threads for both minor GC as well as major DC this collector does not run concurrently with the application okay even though its name suggests its paddle it's named parallel because it has multiple threads of the garbage collection it's and all of those threats are unparalleled but while the garbage collection is running all the application threads are stopped and that is why if your application is deployed on a multi-core from multiprocessor systems then this collector will give you the greatest throughput that is in the shortest amount of time it will be able to collect the highest amount of garbage possible of course since it stops the entire application and it could stop it for some time it is best only for batch applications so in the batch application you do not care about the users the response time because there is no user on the front end right it's a batch application it's running behind the scenes so what you want is you want the program to run as efficiently as possible so for batch applications the best collector to use is a parallel collector the third algorithm is called a CMS collector CMS the full form of CMS is concurrent mark and sweep and you already saw mark sweep and compact algorithm earlier and this is the same thing but it says it's concurrent moppet mark and sweep that means it runs concurrently with the application to mark all the live objects right so the amount of time that the application has to stop is less so the latency of the application is less but of course during the actual collection it still has STW pauses STW stop the world forces that means it stops the application for a very small amount of time to do its actual garbage collection but it's not as bad as the parallel collector of course there are trade-offs so it requires more footprint than parallel collector so it has more data structures that it needs to take care of behind the scenes it has less throughput than the parallel collector but the advantage of this is it has smaller pause times than the final collector right and that is why it is best used for general applications the improvement over the CMS collector is called g1 collector g1 collected garbage post so instead of having and specific young generation and old generation this collector uses the entire heap and divides it into multiple regions and it itself assigns whether this region is young generation region or an old generation region now compared to the previous collector CMS it has more footprint so it has even more requirement for data structures but the advantage of this is it this has more predictable latency and this is the best feature of this connector so when you start their application you can pass on this variable that the maximum cost time that my application can withstand is say 10 milliseconds I cannot handle my application cannot handle more than 10 milliseconds of cost time of course this is not a very hard target it's a soft target so garbage collector the g1 collector will try to ensure that the garbage collection is done only for 10 milliseconds and even if there is some garbage left then it will take care of it in the next cycle and will allow the application to run after 10 milliseconds and that is why this garbage collector the chip and collector is best for applications which need predictable latency so CMS was great for general applications it has lower pastimes but if you want predictable pastimes predictable latency which you can set using this variable Max target post and then you can use the cheaper collector there is one more collector which is coming up which is not yet as a default in JDK is called Shannon Dora Shannon dower is an improvement upon g1 collector wherein it requires a little higher footprint so it takes more data structures behind the scenes but it has even lower latency than g1 it's going to come in a few versions of Java so here's the table complete able to understand the pros and cons so take a look in general I would say the serial collector is for small devices or when you want to ensure that the GC doesn't affect other CPUs the parallel collector is best for batch applications the CMS application CMS collector is best for general applications g1 collector is best if you want predictable latency and in Shanan Tovah is an improvement upon g1 which you will be able to use it as default in few versions of Java that's it for this video if you have any comments or questions please let me know and see you in the next video thank you 







https://howtodoinjava.com/java/garbage-collection/all-garbage-collection-algorithms/

https://www.geeksforgeeks.org/garbage-collection-java/

https://www.geeksforgeeks.org/mark-and-sweep-garbage-collection-algorithm/

https://www.youtube.com/watch?v=2AZ0KKeXJSo

https://www.geeksforgeeks.org/types-of-jvm-garbage-collectors-in-java-with-implementation-details/?ref=rp
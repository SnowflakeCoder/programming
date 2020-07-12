## Java Garbage Collection

### Object Life Cycle

A java’s object life cycle can be seen in 3 stages:

1. #### Object creation

   When an object is created, a specific amount of memory is allocated for storing that object. The amount of memory allocated can <u>differ based on architecture and JVM</u>.

2. #### Object in use

   Till the time, object is used by application’s other objects (other live objects have references pointing to it). During its usage, object reside in memory and may contain references to other objects.

3. #### Object destruction

   The garbage collection system monitors objects and, as feasible, **counts the number of references to each object**. When there are no references to an object, there is no way to get to it with the currently running code, marks it as eligible for GC (to de-allocate the associated memory).

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

- **Deletion with compacting** – Only removing unused objects is not efficient because <u>blocks of free memory is scattered across storage area</u> and cause OutOfMemoryError, if created object big enough and does not find large enough memory block. To solve this issue, after deleting unreferenced objects, <u>compacting is done on the remaining referenced objects</u>. Here compacting refer the **process of moving referenced object together**. This makes new memory allocation much easier and faster. It is often referred as **mark-sweep-compact algorithm**.

  ![Deletion with compacting](https://howtodoinjava.com/wp-content/uploads/2018/04/Deletion-with-compacting.png)

- **Deletion with copying** – It is very similar to mark and compacting approach as they too relocate all live objects. The important difference is that the **target of relocation is a different memory region**. It is often referred as **mark-copy algorithm**.

![Deletion with copying - Mark and Sweep](https://howtodoinjava.com/wp-content/uploads/2018/04/Deletion-with-copying-Mark-and-Sweep.png)

























https://howtodoinjava.com/java/garbage-collection/all-garbage-collection-algorithms/

https://www.geeksforgeeks.org/garbage-collection-java/

https://www.geeksforgeeks.org/mark-and-sweep-garbage-collection-algorithm/
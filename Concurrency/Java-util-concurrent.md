## Java Concurrency Utilities

### Java BlockingQueue

The Java BlockingQueue (java.util.concurrent.BlockingQueue) is an **interface**, represents a **thread safe queue** that is capable of `blocking the threads`. A BlockingQueue is typically used to have one thread produce objects, which another thread consumes. The producing thread will keep inserting new objects until the queue reaches its limit. If the blocking queue reaches its upper limit, the producing thread is blocked while trying to insert the new object. `It remains blocked until a consuming thread takes an object out of the queue`. Similarly if the consuming thread tries to take an object out of an empty queue, the `consuming thread is blocked until a producing thread puts an object into the queue`. It is **not possible to insert null** into a BlockingQueue. If you try to insert null, the BlockingQueue will throw a **NullPointerException**.

#### BlockingQueue Methods

The Java BlockingQueue interface has **4 different sets of methods**. Each set of methods <u>**behaves differently** in case the requested operation cannot be carried out immediately</u>.

|             | **Throws Exception** | **Special Value** | **Blocks** | **Times Out**                 |
| ----------- | -------------------- | ----------------- | ---------- | ----------------------------- |
| **Insert**  | `add(o)`             | `offer(o)`        | `put(o)`   | `offer(o, timeout, timeunit)` |
| **Remove**  | `remove(o)`          | `poll()`          | `take()`   | `poll(timeout, timeunit)`     |
| **Examine** | `element()`          | `peek()`          | ` `        | ` `                           |

**Throws Exception**: If the attempted operation is not possible immediately, an exception is thrown.
**Special Value**: If the attempted operation is not possible immediately, a special value is returned (often true / false/null).
**Blocks**: If the attempted operation is not possible immediately, the method call blocks until it is.
**Times Out**: If the attempted operation is not possible immediately, the method call blocks until it is, but waits no longer than the given timeout. Returns a special value telling whether the operation succeeded or not (typically true / false/null).

#### BlockingQueue Implementations

##### ArrayBlockingQueue


The ArrayBlockingQueue class implements the BlockingQueue interface. ArrayBlockingQueue is a **bounded**, blocking queue that stores the elements internally in an array. Bounded means there is an upper bound on the number of elements it can store at the same time (cannot store unlimited elements). You **set the upper bound at instantiation time**, and after that `it cannot be changed`.

##### DelayQueue

DelayQueue class implements the BlockingQueue interface. The DelayQueue **blocks the elements internally until a certain delay has expired**. <u>The elements must implement the interface **java.util.concurrent.Delayed**</u>.

```
public interface Delayed extends Comparable<Delayed< {
 public long getDelay(TimeUnit timeUnit);
}
```

The value returned by the getDelay() method should be the delay remaining before this element can be released. If <u>0 or a negative value is returned, the delay will be considered expired</u>, and the element released at the next take() etc. call on the DelayQueue. The TimeUnit instance passed to the getDelay() method is an Enum that tells which time unit the delay should be returned in. 

The <u>Delayed interface also extends the java.lang.Comparable interface</u>. This is probably used internally in the DelayQueue to <u>order the elements in the queue</u>, so they are **released ordered by their expiration time**.

##### LinkedBlockingQueue

The LinkedBlockingQueue class implements the BlockingQueue interface. The LinkedBlockingQueue keeps the <u>elements internally in a linked structure (linked nodes)</u>. This linked structure can **optionally have an upper bound if desired**. If no upper bound is specified, Integer.MAX_VALUE is used as the upper bound.

```java
BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
```

##### PriorityBlockingQueue

The PriorityBlockingQueue class implements the BlockingQueue interface. The PriorityBlockingQueue is an **unbounded concurrent queue**. It <u>uses the same ordering rules as the java.util.PriorityQueue</u> class. You cannot insert null into this queue.

All elements inserted into the PriorityBlockingQueue **must implement the java.lang.Comparable** interface. The elements thus order themselves according to whatever priority you decide in your Comparable implementation. **Note:** In case you obtain an Iterator from a PriorityBlockingQueue, the <u>Iterator does not guarantee to iterate the elements in priority order</u>.

##### SynchronousQueue


The SynchronousQueue class implements the BlockingQueue interface. The SynchronousQueue is a queue that can **only contain a single element internally**. A thread inserting an element into the queue is <u>blocked until another thread takes that element from the queue</u>. Likewise, if a thread tries to take an element and no element present, that thread is blocked until a thread insert an element into the queue.

------

### BlockingDeque

The BlockingDeque **interface** represents a **thread safe deque** and if the deque is currently full, the inserting thread will be blocked until a removing thread takes an element out of the deque. If the deque is currently empty, a removing thread will be blocked until an inserting thread inserts an element into the deque. The **BlockingDeque interface extends the BlockingQueue interface**.

#### BlockingDeque methods

A BlockingDeque also has 4 different sets of methods similar to BlockingQueue and each set of methods behaves differently in case the requested operation cannot be carried out immediately.

**Throws Exception**: If the attempted operation is not possible immediately, an exception is thrown.
**Special Value**: If the attempted operation is not possible immediately, a special value is returned.
**Blocks**: If the attempted operation is not possible immediately, the method call blocks until it is.
**Times Out**: If the attempted operation is not possible immediately, the method call blocks until it is, but waits no longer than the given timeout. Returns a special value telling whether operation succeeded or not.

##### LinkedBlockingDeque

The LinkedBlockingDeque class implements the BlockingDeque interface. 





### ConcurrentMap

The java.util.concurrent.ConcurrentMap interface represents a Map which is <u>capable of handling concurrent access</u> (puts and gets) to it.

#### ConcurrentHashMap

ConcurrentHashMap implements ConcurrentMap. The ConcurrentHashMap is very similar to the java.util.**HashTable** class, except that <u>ConcurrentHashMap offers better concurrency than HashTable</u> does. <u>ConcurrentHashMap **does not lock the Map while you are reading** from it</u>. Additionally, <u>ConcurrentHashMap **does not lock the entire Map when writing** to it</u>. It **only locks the part of the Map** that is being written to, internally.

Another difference is that ConcurrentHashMap <u>does not throw **ConcurrentModificationException**</u> if the ConcurrentHashMap is changed while being iterated. The Iterator is **not designed to be used by more than one thread** though.

### ConcurrentNavigableMap


The java.util.concurrent.ConcurrentNavigableMap class is a java.util.**NavigableMap** with <u>support for concurrent access</u>, and which has <u>concurrent access enabled for its submaps</u>. The "submaps" are the maps returned by various methods like **headMap**(), **subMap**() and **tailMap**().

#### headMap()

The **headMap(T toKey)** method returns a view of the map containing the keys which are **strictly less than the given key**. If you make changes to the original map, these changes are reflected in the head map.

#### tailMap()

The tailMap(T fromKey) method returns a view of the map containing the keys which are **greater than or equal to the given fromKey**. If you make changes to the original map, these changes are reflected in the tail map.

#### subMap()

The subMap() method returns a view of the original map which **contains all keys from (including), to (excluding)** two keys given as parameters to the method. 

#### Example

```java
ConcurrentNavigableMap map = new ConcurrentSkipListMap();
map.put("1", "one");
map.put("2", "two");
map.put("3", "three");

ConcurrentNavigableMap headMap = map.headMap("2");
//The headMap will only contains the key "1" - the only key strictly less than "2".

ConcurrentNavigableMap tailMap = map.tailMap("2");
//The tailMap will contain the keys "2" and "3" - keys are greather than or equal to "2".

ConcurrentNavigableMap subMap = map.subMap("2", "3");
//The submap contains only the key "2", because only this key is greater than or equal to "2", and smaller than "3".
```

### CountDownLatch

A java.util.concurrent.CountDownLatch is a **concurrency construct** that <u>allows one or more threads to wait for a given set of operations to complete</u>. A CountDownLatch is <u>initialized with a given count</u>. This count is decremented by calls to the **countDown()** method. Threads waiting for this count to reach zero can call one of the **await()** methods. <u>Calling **await()** blocks the thread</u> until the count reaches zero.

### CyclicBarrier

The java.util.concurrent.CyclicBarrier class is a **synchronization mechanism** that can <u>synchronize threads progressing through some algorithm</u>. In other words, it is **a barrier that all threads must wait at, until all threads reach it**, before any of the threads can continue. When you create a CyclicBarrier you **specify how many threads are to wait at it**, before releasing them. The threads wait for each other by calling the **await()** method on the CyclicBarrier. Once N threads are waiting at the CyclicBarrier, all threads are released and can continue running. You can also specify a timeout for the waiting thread. When the timeout has passed the thread is also released, even if not all N threads are waiting at the CyclicBarrier.

```java
CyclicBarrier barrier = new CyclicBarrier(2);
//Here is how a thread waits at a CyclicBarrier:
barrier.await();
barrier.await(10, TimeUnit.SECONDS);
```


The waiting threads waits at the CyclicBarrier until either:

- The last thread arrives (calls await() )
- The thread is interrupted by another thread (another thread calls its interrupt() method)
- Another waiting thread is interrupted
- Another waiting thread times out while waiting at the CyclicBarrier
- The CyclicBarrier.reset() method is called by some external thread.

#### CyclicBarrier Action

The CyclicBarrier supports a barrier action, which is a Runnable that is **executed once the last thread arrives**. You pass the Runnable barrier action to the CyclicBarrier in its constructor, like this:

```java
Runnable      barrierAction = ... ;
CyclicBarrier barrier       = new CyclicBarrier(2, barrierAction);
```

### Exchanger


The java.util.concurrent.Exchanger class represents a kind of **rendezvous point where two threads can exchange objects**. Exchanging objects is done via one of the two exchange() methods.

```java
Exchanger exchanger = new Exchanger();
ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

new Thread(exchangerRunnable1).start();
new Thread(exchangerRunnable2).start();

//Here is the ExchangerRunnable code:
public class ExchangerRunnable implements Runnable{
    Exchanger exchanger = null;
    Object    object    = null;
    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() +
                    " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

This example prints out this:

`Thread-0 exchanged A for B`
`Thread-1 exchanged B for A`

### Semaphore

The java.util.concurrent.Semaphore class is a **counting semaphore**. That means that it has two main methods: **acquire()** and **release()**. The counting semaphore is **initialized with a given number of "permits"**. For each call to acquire() a permit is taken by the calling thread. For each call to release() a permit is returned to the semaphore. Thus, <u>at most N threads can pass the acquire() method without any release() calls</u>, where N is the number of permits the semaphore was initialized with. 

#### Semaphore Usage

As semaphore typically has two uses:

##### Guarding Critical Sections

To guard a critical section against entry by more than N threads at a time. If you use a semaphore to guard a critical section, the thread trying to enter the critical section will typically first try to acquire a permit, enter the critical section, and then release the permit again after.

##### Sending Signals Between Threads

If you use a semaphore to send signals between threads, then you would typically have <u>one thread call the acquire() method, and the other thread to call the release() method</u>. If <u>no permits are available, the acquire() call will block</u> until a permit is released by another thread. Similarly, a **release() calls is blocked if no more permits can be released** into this semaphore.

For instance, if acquire was called after Thread 1 had inserted an object in a shared list, and Thread 2 had called release() just before taking an object from that list, you had essentially created a blocking queue. The number of permits available in the semaphore would correspond to the maximum number of elements the blocking queue could hold.

#### Fairness

**No guarantees are made about fairness** of the threads acquiring permits from the Semaphore. That is, there is <u>no guarantee that the first thread to call acquire() is also the first thread to obtain a permit</u>. If the first thread is blocked waiting for a permit, then a second thread checking for a permit just as a permit is released, may actually obtain the permit ahead of thread 1. If you want **to enforce fairness**, the Semaphore class has a constructor that takes a <u>boolean telling if the semaphore should enforce fairness</u>. **Enforcing fairness comes at a performance / concurrency penalty**, so don't enable it unless you need it.

### Java ExecutorService

The Java ExecutorService **interface**, java.util.concurrent.ExecutorService, represents an **asynchronous execution mechanism** which is **capable of executing tasks concurrently** in the background. Once a thread has delegated the task to the ExecutorService, the thread continues its own execution independent of the execution of that task. The ExecutorService then executes the task concurrently, independently of the thread that submitted the task. 

#### Implementations

The ExecutorService has the following **implementations**:

- ThreadPoolExecutor
- ScheduledThreadPoolExecutor

However, you can use the <u>**Executors** factory class to create ExecutorService instances</u> too. 

`ExecutorService executorService1 = Executors.newSingleThreadExecutor();`

There are a few different **ways to delegate tasks** for execution to an ExecutorService:

```java
execute(Runnable)
//returns a Future object, can be used to check if the Runnable has finished executing
submit(Runnable)
submit(Callable) //returns a Future object
invokeAny(...)
invokeAll(...)
```

#### invokeAny()

The invokeAny() method **takes a collection of Callable objects**, or subinterfaces of Callable. Invoking this method <u>returns the **result of one** of the Callable objects</u>. You have no guarantee about which of the Callable's results you get. Just one of the ones that finish. If one of the tasks complete (or throws an exception), the **rest of the Callable's are cancelled**.

`String result = executorService.invokeAny(callables);`

#### invokeAll()

The invokeAll() method invokes all of the Callable objects you pass to it in the collection passed as parameter and **returns a list of Future objects** via which you can obtain the results of the executions of each Callable. Keep in mind that a <u>task might finish due to an exception, so it may not have "succeeded"</u>. There is no way on a Future to tell the difference.

`List<Future<String>> futures = executorService.invokeAll(callables);`

#### Java Callable

The Java Callable interface, java.util.concurrent.Callable, represents an <u>asynchronous task which can be executed by a separate thread</u>. 

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

The call() method can **return a result**. If the task is executed asynchronously, the result is typically propagated back to the creator of the task via a Java **Future**. This is the case when a Callable is submitted to an ExecutorService for concurrent execution. The call() method can also thrown an Exception in case the task fails during execution.

#### Runnable vs. Callable

Both interfaces <u>represents a task that can be executed concurrently</u> by a separate thread or an ExecutorService. Both interfaces <u>only has a single method</u>.

```java
public interface Runnable {
    public void run();
}
public interface Callable{
    public Object call() throws Exception;
}
```

The main difference is that the Callable **call() method can return an Object**. Another difference is that **call() can throw an exception**, whereas run() cannot (**except for unchecked exceptions** - subclasses of RuntimeException). If you need to submit a task to a Java ExecutorService and you <u>need a result from the task</u>, then you need to make your task implement the Callable interface. 

Additionally, a Runnable was originally <u>designed for long running concurrent execution</u>, e.g. running a network server concurrently, or watching a directory for new files. The Callable interface is <u>more designed for one-off tasks that return a single result</u>.

#### Cancel Task

You can cancel a task (Runnable or Callable) submitted to a Java ExecutorService **by calling the cancel() method on the Future** returned when the task is submitted. Cancelling the task is **only possible if the task has not yet started executing**. 

`future.cancel();`

#### ExecutorService Shutdown

When you are done using the Java ExecutorService you should shut it down, so the **threads do not keep running**. If your application is started via a main() method and your main thread exits your application, the <u>**application will keep running** if you have an active ExexutorService in your application</u>. The active threads inside this ExecutorService prevents the JVM from shutting down.

##### shutdown()

To terminate the threads inside the ExecutorService you call its shutdown() method. The ExecutorService **will not shut down immediately**, but it will **no longer accept new tasks**, and <u>once all threads have finished current tasks, the ExecutorService shuts down</u>. All tasks submitted to the ExecutorService before shutdown() is called, are executed. 

##### shutdownNow()

If you want to **shut down the ExecutorService immediately**, you can call the shutdownNow() method. This will **attempt to stop all executing tasks right away**, and **skips all submitted but non-processed tasks**. There are no guarantees given about the executing tasks. Perhaps they stop, perhaps they execute until the end. It is a best effort attempt. 

##### awaitTermination()

The ExecutorService awaitTermination() method will **block the thread calling it** until either the ExecutorService has shutdown completely, or until a given time out occurs. The awaitTermination() method is **typically called after calling shutdown() or shutdownNow()**.

### Java Future


A Java Future, java.util.concurrent.Future, represents the **result of an asynchronous computation (task)**. When the asynchronous task is created, a Java Future object is returned. This Future object **functions as a handle to the result of the asynchronous task**. Once the asynchronous task completes, the **result can be accessed via the Future object** returned when the task was started.

```java
public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning)
    V       get();
    V       get(long timeout, TimeUnit unit);
    boolean isCancelled();
    boolean isDone();
}
```

#### Get Result From Future

To obtain the result from Future object, you call one of the two get() methods on the Future. The get() methods both return an Object, but the return type can also be a generic return type. If you <u>call the get() method before the asynchronous task has completed, the **get() method will block**</u> until the result is ready. Second get() method can time out after an amount of time has passed which you can specify via method parameters. The thread will waits for a given time for the result to be available in the Future. If no result is available a TimeoutException is thrown.

```java
Object result = future.get();
Object result = future.get(1000, TimeUnit.MILLISECONDS);
```
#### future.cancel()

You can cancel the asynchronous task represented by a Java Future instance by calling the Future cancel() method. The asynchronous task execution **must be implemented in to support cancellation**. Without such support, calling cancel() will have no effect. 

#### future.isDone()

You can check if the asynchronous task is done (and a result available) by calling the Future isDone().

```java
if(future.isDone()) {
    Object result = future.get();
}
```

#### future.isCancelled()

You can check if the asynchronous task is cancelled by calling the Future isCancelled() method. 

### ThreadPoolExecutor

The java.util.concurrent.ThreadPoolExecutor is an **implementation of the ExecutorService interface**. The ThreadPoolExecutor executes the given task (Callable or Runnable) using one of its internally pooled threads. The number of threads in the pool is determined by these variables:

- corePoolSize
- maximumPoolSize

If less than **corePoolSize** threads are created in the the thread pool when a task is delegated to the thread pool, then a new thread is created, **even if idle threads exist** in the pool. **If the internal queue of tasks is full**, and corePoolSize threads or more are running, but less than maximumPoolSize threads are running, then a new thread is created to execute the task.

### ScheduledExecutorService

The java.util.concurrent.ScheduledExecutorService is an ExecutorService which can **schedule tasks to run after a delay**, or to execute repeatedly with a **fixed interval of time in between each execution**. Tasks are executed asynchronously by **a worker thread**, and not by the thread handing the task to the ScheduledExecutorService. **ScheduledThreadPoolExecutor** implements ScheduledExecutorService. However, you can also use the **Executors** factory class to create ScheduledExecutorService instances. 

#### Example

```java
// ScheduledExecutorService is created with 5 threads in
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//The two last parameters specify that the Callable should be executed after 5 seconds.
ScheduledFuture scheduledFuture =
    scheduledExecutorService.schedule(new Callable() {
        public Object call() throws Exception {
            System.out.println("Executed!");
            return "Called!";
        }
    }, 5, TimeUnit.SECONDS);
System.out.println("result = " + scheduledFuture.get());
scheduledExecutorService.shutdown();
```

#### Methods

**schedule (Callable task, long delay, TimeUnit timeunit)**

This method schedules the given Callable for execution after the given delay. The method **returns a ScheduledFuture** which you can use to either **cancel the task** before it has started executing, or **obtain the result** once it is executed.

**schedule (Runnable task, long delay, TimeUnit timeunit)**
This method accept a Runnable object, but a Runnable cannot return a value, so the **ScheduledFuture.get() method returns null** when the task is finished.

**scheduleAtFixedRate (Runnable, long initialDelay, long period, TimeUnit timeunit)**
This method **schedules a task to be executed periodically**. The task is executed the first time after the initialDelay, and then recurringly every time the period expires. If any execution of the given **task throws an exception, the task is no longer executed**. If no exceptions are thrown, the task will continue to be executed until the ScheduledExecutorService is shut down. In this method the period is interpreted as a **delay between the start of the previous execution**, until the start of the next execution. But If a task takes longer to execute than the period between its scheduled executions, the **next execution will start after the current** execution finishes. The scheduled task will **not be executed by more than one thread** at a time.

**scheduleWithFixedDelay (Runnable, long initialDelay, long period, TimeUnit timeunit)**
In this method the period is interpreted as the **delay between the end of the previous execution**, until the start of the next. The delay is thus between finished executions, not between the beginning of executions.

### Fork and Join using ForkJoinPool

The ForkJoinPool makes it **easy for tasks to split their work** up into smaller tasks which are then submitted to the ForkJoinPool too. Tasks can keep splitting their work into smaller subtasks for as long as it makes to split up the task. 

#### Fork and Join

The fork and join principle consists of two steps which are performed recursively. These two steps are the fork step and the join step.

**Fork** is a task that can **fork (split) itself into smaller subtasks** which can be executed concurrently. Splitting tasks into smaller tasks is referred to as forking tasks. A task only splits itself up into subtasks if the work the task was given is large enough for this to make sense. There is an overhead to splitting up a task into subtasks, so for small amounts of work its better to run by a single thread. The limit for when it makes sense to fork a task into subtasks is also called a **threshold**. It is up to each task to decide on a **sensible threshold**.

Once the subtasks have finished executing, the main task may **join (merge) all the results** into one result. Of course, not all types of tasks may return a result. If the tasks do not return a result then a task just **waits for its subtasks to complete**. No result merging takes place then.

#### The ForkJoinPool

The ForkJoinPool is a special thread pool which is **designed to work well with fork-and-join task splitting**. You can submit **two types of tasks** to a ForkJoinPool . A task that **does not return any result** (an "action"), and a task which does **return a result** (a "task").

```java
// creates a ForkJoinPool with a parallelism level of 4.
ForkJoinPool forkJoinPool = new ForkJoinPool(4); 
```

#### RecursiveAction

A RecursiveAction is a task which **does not return any value**. It just does some work, e.g. writing data to disk, and then exits. A RecursiveAction may **still need to break up its work** into smaller chunks which can be executed in parallel by different CPUs, or different threads on the same CPU. You **implement a RecursiveAction by subclassing** it. 

```java
public class MyRecursiveAction extends RecursiveAction {
    private long workLoad = 0;
    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }
    @Override
    protected void compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();
            subtasks.addAll(createSubtasks());
            for(RecursiveAction subtask : subtasks){
                subtask.fork(); //subtasks scheduled for execution
            }
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }
    
    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }
}
```

The MyRecursiveAction takes workLoad as a parameter to its constructor. If the workLoad is above a certain threshold, the work is split into subtasks which are also **scheduled for execution (via the .fork() method** of the subtasks). If the workLoad is below a certain threshold then the work is carried out by the MyRecursiveAction itself. You can schedule a MyRecursiveAction for execution like this:

```java
MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
forkJoinPool.invoke(myRecursiveAction);
```

#### RecursiveTask

A RecursiveTask is a task that **returns a result**. It may split its work up into smaller tasks, and **merge the result of these smaller tasks into a collective result**. The splitting and merging may **take place on several levels**. 

```java
public class MyRecursiveTask extends RecursiveTask<Long> {
	// only compute() method is different from RecursiveAction. Rest all same
    protected Long compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
            subtasks.addAll(createSubtasks());
            for(MyRecursiveTask subtask : subtasks){
                subtask.fork();
            }
            long result = 0;
            for(MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }
}
```

The class MyRecursiveTask extends RecursiveTask<Long> which means that the **result returned from the task is a Long** . Additionally, this example then <u>receives the **result returned** by each subtask by calling the **join()** method</u> of each subtask. The subtask results are merged into a bigger result which is then returned. This kind of joining / mergining of subtask results may occur recursively for several levels of recursion.

```java
MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
long mergedResult = forkJoinPool.invoke(myRecursiveTask);
System.out.println("mergedResult = " + mergedResult);  
```

### Lock


A java.util.concurrent.locks.Lock is a **thread synchronization mechanism** just like **synchronized blocks**. A Lock is, however, more flexible and more sophisticated than a synchronized block.

#### Example

```java
Lock lock = new ReentrantLock();
lock.lock();
//critical section
lock.unlock();
```

First a Lock is created. Then it's lock() method is called. Now the **Lock instance is locked**. Any other thread calling lock() will be blocked until the thread that locked the lock calls unlock(). Finally unlock() is called, and the Lock is now unlocked so other threads can lock it. Since **Lock is an interface**, you need to use one of its implementations : **ReentrantLock**.

Main Differences Between **Locks and Synchronized Blocks**

- A synchronized block makes **no guarantees about the sequence** in which threads waiting to entering it are granted access.
- You **cannot pass any parameters** to the entry of a synchronized block. Thus, having a **timeout** trying to get access to a synchronized block is not possible.
- The synchronized block **must be fully contained within a single method**. A Lock can have it's calls to **lock() and unlock() in separate methods**.

#### Lock Methods

- The **lock()** method locks the Lock instance if possible. If the Lock instance is **already locked, the thread calling lock() is blocked** until the Lock is unlocked.
- The **lockInterruptibly()** method locks the Lock **unless the thread calling the method has been interrupted**. Additionally, if a thread is blocked waiting to lock the Lock via this method, and it is interrupted, it exits this method calls.
- The **tryLock()** method attempts to lock the Lock instance immediately. It returns true if the locking succeeds, false if Lock is already locked. This method **never blocks**.
- The tryLock(long timeout, TimeUnit timeUnit) works like the tryLock() method, except it **waits up the given timeout before giving up** trying to lock the Lock.
- The **unlock()** method unlocks the Lock instance. Typically, a Lock implementation will **only allow the thread that has locked the Lock** to call this method. Other threads calling this method may result in an **unchecked exception (RuntimeException)**.

#### ReadWriteLock


A java.util.concurrent.locks.ReadWriteLock is an **advanced thread lock mechanism**. It allows **multiple threads to read a certain resource, but only one to write it**, at a time. The idea is, that multiple threads can read from a shared resource without causing concurrency errors. The concurrency errors first occur when **reads and writes to a shared resource occur concurrently**, or if **multiple writes take place concurrently**.

##### ReadWriteLock Locking Rules

- **Read Lock** => If no threads have locked the ReadWriteLock for writing, and no thread have requested a write lock (but not yet obtained it). Thus, **multiple threads can lock the lock for reading**.
- **Write Lock** => If no threads are reading or writing. Thus, only one thread at a time can lock for writing.

ReadWriteLock is an interface, to use a ReadWriteLock use the implementation: **ReentrantReadWriteLock**

##### Example

```java
ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
readWriteLock.readLock().lock();
// multiple readers can enter this section if not locked for writing, and not writers waiting to lock for writing.
readWriteLock.readLock().unlock();

readWriteLock.writeLock().lock();
// only one writer can enter this section, and only if no threads are currently reading.
readWriteLock.writeLock().unlock();
```


Notice how the ReadWriteLock actually internally keeps two Lock instances. One guarding read access, and one guarding write access.

### AtomicBoolean

The AtomicBoolean class provides you with a boolean variable which can be **read and written atomically**

```java
//  creates a new AtomicBoolean with the value false
AtomicBoolean atomicBoolean = new AtomicBoolean();
// set an explicit initial value for the AtomicBoolean instance
AtomicBoolean atomicBoolean = new AtomicBoolean(true);
boolean value = atomicBoolean.get(); // get the value of an AtomicBoolean
atomicBoolean.set(false); //  set the value of an AtomicBoolean to false.
```

#### getAndSet()

You can **swap the value of an AtomicBoolean** using the getAndSet() method. The getAndSet() method returns the AtomicBoolean's current value, and then sets a new value for it. 

#### compareAndSet()

The method compareAndSet() allows you to compare the current value of the AtomicBoolean to an expected value, and if current value is equal to the expected value, a new value can be set on the AtomicBoolean. The **compareAndSet() method is atomic**, so only a single thread can execute it at the same time. Thus, the **compareAndSet() method can be used to implemented simple synchronizers like locks**.

```java
AtomicBoolean atomicBoolean = new AtomicBoolean(true);
// the oldValue will contain true, and the AtomicBoolean will contain the value false.
boolean oldValue = atomicBoolean.getAndSet(false);
/*
compares the current value of the AtomicBoolean to expectedValue (true) and if the two values are equal, then sets the new value (false). expectedValue = true; newValue= false;
*/
boolean wasNewValueSet = atomicBoolean.compareAndSet(true, false);
```

### AtomicInteger

The AtomicInteger class provides you with **a int variable which can be read and written atomically**.

```java
// Creating an AtomicInteger with the initial value 0.
AtomicInteger atomicInteger = new AtomicInteger();  
atomicInteger = new AtomicInteger(123); //create AtomicInteger with an initial value 123.
int theValue = atomicInteger.get();
atomicInteger.set(234);
atomicInteger.compareAndSet(123, 234); // expectedValue = 123; newValue      = 234;
```

#### compareAndSet()

The AtomicInteger class has an **atomic** compareAndSet() method, compares the current value of the AtomicInteger instance to an expected value, and if the **two values are equal, sets a new value** for the AtomicInteger instance. 

#### Add a value

The AtomicInteger class contains a few methods you can use to **add a value to the AtomicInteger** and get its value returned. 

- **addAndGet()** - adds a number to the AtomicInteger and **returns its value after the addition**. 
- **getAndAdd()** - The second method also adds a number to the AtomicInteger but returns the value the AtomicInteger had before the value was added. You can **also add negative numbers** to the AtomicInteger via these two methods. The result is effectively a subtraction.
-  **getAndIncrement()** and **incrementAndGet()** - These methods works like getAndAdd() and addAndGet() but **just add 1** to the value of the AtomicInteger.

```java
AtomicInteger atomicInteger = new AtomicInteger();
System.out.println(atomicInteger.getAndAdd(10)); // prints the value 0
System.out.println(atomicInteger.addAndGet(10)); // prints the value 20
```

#### Subtract a value

- **decrementAndGet()** - subtracts 1 from the AtomicInteger and returns its value after the subtraction. 
- **getAndDecrement()** - also subtracts 1 from the AtomicInteger value but returns the value the AtomicInteger had before the subtraction.

### AtomicLong

The AtomicLong class provides you with **a long variable which can be read and written atomically**. All methods are same as AtomicInteger.

### AtomicReference


The AtomicReference class provides **an object reference variable which can be read and written atomically**. By atomic is meant that multiple threads attempting to change the same AtomicReference (e.g. with a compare-and-swap operation) will not make the AtomicReference end up in an inconsistent state. 

```java
AtomicReference atomicReference = new AtomicReference();
atomicReference = new AtomicReference("initialReference");
String reference = (String) atomicReference.get();
atomicReference.set("New object referenced");
//Creating a Typed AtomicReference
AtomicReference<String> atomicStringReference = new AtomicReference<String>();
// no need to cast here because the compiler knows it will return a String reference.
String reference = atomicStringReference.get();
```

#### compareAndSet()

The compareAndSet() method can compare the reference stored in the AtomicReference instance with an expected reference, and if they **two references are the same (not equal as in equals() but same as in ==)**, then a new reference can be set on the AtomicReference instance. If compareAndSet() sets a new reference in the AtomicReference the compareAndSet() method returns true. Otherwise compareAndSet() returns false.

```java
String initialReference = "initial value referenced";
AtomicReference<String> reference = new AtomicReference<String>(initialReference);
String newReference = "new value referenced";
boolean exchanged = reference.compareAndSet(initialReference, newReference); // true
```

### AtomicStampedReference

The AtomicStampedReference class also provides an **object reference variable which can be read and written atomically**. The AtomicStampedReference is **different from the AtomicReference** in that the AtomicStampedReference keeps both an **object reference and a stamp** internally. The reference and stamp can be swapped via the compareAndSet() method. The AtomicStampedReference is designed to be able to **solve the A-B-A problem** which is not possible to solve with an AtomicReference alone. 

```java
Object initialRef   = null;
int    initialStamp = 0;
// Creating an AtomicStampedReference
AtomicStampedReference stamped = new AtomicStampedReference(intialRef, initialStamp);
// Creating a Typed AtomicStampedReference
AtomicStampedReference<String> atomicStampedReference =
    new AtomicStampedReference<String>(initialRef, initialStamp);
String reference = atomicStampedReference.getReference(); // Getting the Reference
int stamp = atomicStampedReference.getStamp(); // Getting the Stamp
String newRef = "New object referenced";
int    newStamp = 1;
atomicStampedReference.set(newRef, newStamp); //Setting the AtomicStampedReference

```

#### Getting Reference and Stamp Atomically

You can obtain both reference and stamp from an AtomicStampedReference in a single, atomic operation using the get() method. The get() method returns the reference as return value from the method. The stamp is inserted into an int[] array that is passed as parameter to the get() method. 

```java
int[] stampHolder = new int[1];
String ref = atomicStampedReference.get(stampHolder);

System.out.println("ref = " + ref);
System.out.println("stamp = " + stampHolder[0]);
```

#### compareAndSet()

The compareAndSet() method can compare the reference stored in the AtomicStampedReference instance with an expected reference, and the stored stamp with an expected stamp, and if they **two references and stamps are the same** (not equal as in equals() but same as in ==), then a new reference can be set on the AtomicStampedReference instance. If compareAndSet() sets a new reference the compareAndSet() method returns true. Otherwise compareAndSet() returns false.

```java
String initialRef   = "initial value referenced";
int    initialStamp = 0;

AtomicStampedReference<String> reference =
    new AtomicStampedReference<String>(initialRef, initialStamp);

String newRef   = "new value referenced";
int    newStamp = initialStamp + 1;
boolean exchanged = reference.compareAndSet(initialRef, newRef, initialStamp, newStamp);
System.out.println("exchanged: " + exchanged);  //true
```

### AtomicIntegerArray

The Java AtomicIntegerArray class (java.util.concurrent.atomic.AtomicIntegerArray) represents an array of int . The int elements in the AtomicIntegerArray can be **updated atomically**. 

#### Two constructors

You can create an AtomicIntegerArray using one of its **two constructors**. The first constructor **takes an int as parameter**. This int specifies the length of the AtomicIntegerArray to create. The second constructor **takes an int[] array as parameter**. The AtomicIntegerArray created using this constructor will have the same capacity as the array parameter, and **all elements from the array will be copied** into the AtomicIntegerArray. 

```java
// creates a AtomicIntegerArray with a capacity of 10 ints
AtomicIntegerArray array = new AtomicIntegerArray(10);
int[] ints = new int[10];
ints[5] = 123;
AtomicIntegerArray array = new AtomicIntegerArray(ints);
int value = array.get(5); // get the value of a given element on the AtomicIntegerArray
array.set(5, 999); // set the value of a given element on the AtomicIntegerArray
```

#### compareAndSet()

The **atomic** compareAndSet() method is used to **compare the value of a given element** with a specified value, and if the two values are equal, set a new value for that element. The method returns true if the element had a new value set, and false if not (if the element did not have the expected value).

```java
// compares the element with index 5 to the expected value 999
boolean swapped = array.compareAndSet(5, 999, 123);
// the newValue would contain the value of the element with index 5 with 3 added to it.
int newValue = array.addAndGet(5, 3);
// oldValue would contain the old value of the element with index 5, before 3 was added.
int oldValue = array.getAndAdd(5, 3); 
int newValue = array.incrementAndGet(5);
int oldValue = array.getAndIncrement(5);
```

**addAndGet()** - can be used to add a value to a given element, and return the new value of the element. 

**getAndAdd()** - this method does the same as the addAndGet() method, except the getAndAdd() method returns the value of the element before a value is added to it. 

**incrementAndGet()** - adds 1 to the value of a given element and returns the new value of that element.

**getAndIncrement()** - getAndIncrement() method returns the value of the element before it is incremented. 

**decrementAndGet()** - decrements (subtracts 1) to the value of a given element and returns the new value.

**getAndDecrement()** - returns the value of the element before it is decremented.

### AtomicLongArray

The Java AtomicLongArray class (java.util.concurrent.atomic.AtomicLongArray) represents an **array of long** which can be <u>updated atomically</u>. The constructors and methods are same as the AtomicIntegerArray class. 

### AtomicReferenceArray

The AtomicReferenceArray in the java.util.concurrent.atomic package is an **array of object references** which can be updated atomically.  AtomicReferenceArray also supports two constructors. The constructors and methods are also similar to the AtomicIntegerArray class.

```java
AtomicReferenceArray array = new AtomicReferenceArray(10); // capacity of 10
Object[] source = new Object[10];
source[5] = "Some string";
AtomicReferenceArray array = new AtomicReferenceArray(source);
// creates a generic type AtomicReferenceArray
AtomicReferenceArray<String> array = new AtomicReferenceArray<String>(source);
```

### References

http://tutorials.jenkov.com/java-util-concurrent/index.html


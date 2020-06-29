## Java Concurrency - Part2

- [Deadlock](#deadlock)
- [Deadlock Prevention](#deadlock-prevention)
  * [Lock Ordering](#lock-ordering)
  * [Lock Timeout](#lock-timeout)
  * [Deadlock Detection](#deadlock-detection)
- [Starvation and Fairness](#starvation-and-fairness)
  * [Causes of Starvation in Java](#causes-of-starvation-in-java)
  * [Implementing Fairness in Java](#implementing-fairness-in-java)
    + [Using Locks Instead of Synchronized Blocks](#using-locks-instead-of-synchronized-blocks)
    + [A Fair Lock](#a-fair-lock)
- [Nested Monitor Lockout](#nested-monitor-lockout)
  * [Nested Monitor Lockout vs. Deadlock](#nested-monitor-lockout-vs-deadlock)
- [Slipped Conditions](#slipped-conditions)
- [Locks in Java](#locks-in-java)
- [Read / Write Locks in Java](#read---write-locks-in-java)
- [Reentrance Lockout](#reentrance-lockout)
- [Semaphores](#semaphores)
  * [Bounded Semaphore](#bounded-semaphore)
  * [Using Semaphores as Locks](#using-semaphores-as-locks)
- [Blocking Queues](#blocking-queues)
  * [Blocking Queue Implementation](#blocking-queue-implementation)
- [Thread Pools](#thread-pools)
- [Compare and Swap](#compare-and-swap)
- [Anatomy of a Synchronizer](#anatomy-of-a-synchronizer)
    + [State](#state)
    + [Access Condition](#access-condition)
    + [State Changes](#state-changes)
    + [Notification Strategy](#notification-strategy)
    + [Test and Set Method](#test-and-set-method)
    + [Set Method](#set-method)
- [Non-blocking Algorithms](#non-blocking-algorithms)
  * [Blocking Concurrency Algorithms](#blocking-concurrency-algorithms)
  * [Non-blocking Concurrency Algorithms](#non-blocking-concurrency-algorithms)
  * [Non-blocking vs Blocking Algorithms](#non-blocking-vs-blocking-algorithms)
  * [Concurrent Data Structures](#concurrent-data-structures)
  * [Volatile Variables](#volatile-variables)
  * [Compare and Swap for Optimistic Locking](#compare-and-swap-for-optimistic-locking)
  * [Optimistic Locking](#optimistic-locking)
  * [Non-swappable Data Structures](#non-swappable-data-structures)
  * [Sharing Intended Modifications](#sharing-intended-modifications)
  * [Completable Intended Modifications](#completable-intended-modifications)
  * [The A-B-A Problem](#the-a-b-a-problem)
    + [A-B-A Solutions](#a-b-a-solutions)
  * [Benefit of Non-blocking Algorithms](#benefit-of-non-blocking-algorithms)
    + [Choice](#choice)
    + [No Deadlocks](#no-deadlocks)
    + [No Thread Suspension](#no-thread-suspension)
    + [Reduced Thread Latency](#reduced-thread-latency)
- [Amdahl's Law](#amdahl-s-law)
  * [Optimizing Algorithms](#optimizing-algorithms)
  * [Execution Time vs. Speedup](#execution-time-vs-speedup)
- [References](#references)

### Deadlock


A deadlock is when two or more **threads are blocked, waiting to obtain locks** that some of the other threads in the deadlock are holding. Deadlock can occur when multiple threads need the same locks, at the same time, but **obtain them in different order**.

#### Database Deadlocks

A database transaction may consist of **many SQL update requests**. When a record is updated during a transaction, that **record is locked for updates from other transactions**, until the first transaction completes. Each update request within the same transaction may therefore lock some records in the database. If multiple transactions are running at the same time that need to update the same records, there is a risk of them ending up in a deadlock.

```
Transaction 1, request 1, locks record 1 for update
Transaction 2, request 1, locks record 2 for update
Transaction 1, request 2, tries to lock record 2 for update.
Transaction 2, request 2, tries to lock record 1 for update.
```

Since the **locks are taken in different requests**, and not all locks needed for a given transaction are known ahead of time, it is hard to detect or prevent deadlocks in database transactions.

### Deadlock Prevention

#### Lock Ordering

Deadlock occurs when <u>multiple threads need the same locks but **obtain them in different order**</u>. If you make sure that **all locks are always taken in the same order** by any thread, **deadlocks cannot occur.** Lock ordering can only be used **if you know about all locks needed** ahead of taking any of the locks.

#### Lock Timeout

Another deadlock prevention mechanism is to **put a timeout on lock attempts** meaning a thread trying to obtain a lock will **only try for so long before giving up**. If a thread does not succeed in taking all necessary locks within the given timeout, it will backup, **free all locks taken, wait for a random amount of time and then retry**. The random amount of time waited serves to give other threads trying to take the same locks a chance to take all locks, and thus let the application continue running without locking.

**Note:** that just because a lock times out it does not necessarily mean that the threads had deadlocked. It could also just mean that the thread holding the lock **takes a long time to complete its task**. Additionally, if **enough threads compete for the same resources** they still risk **trying to take the threads at the same time again** and again, even if timing out and backing up. Another problem with the lock timeout mechanism is that it is **not possible to set a timeout for entering a synchronized block** in Java. You will have to create a custom lock class or use one of the concurrency constructs. 

#### Deadlock Detection

Every time a **thread takes a lock it is noted in a data structure** (map, graph etc.) of threads and locks. Additionally, whenever a **thread requests a lock this is also noted** in this data structure.

When a thread requests a lock **but the request is denied**, the thread can traverse the lock graph to check for deadlocks. For instance, if a Thread A requests lock 7, but lock 7 is held by Thread B, then Thread A can check if Thread B has requested any of the locks Thread A holds (if any). If Thread B has requested so, a **deadlock has occurred**. Deadlock scenario can be a lot more complicated also like Thread A waits for Thread B, Thread B waits for Thread C, Thread C waits for Thread D, and Thread D waits for Thread A. In order for Thread A to detect a deadlock it must transitively examine all requested locks by Thread B. From Thread B's requested locks Thread A will get to Thread C, and then to Thread D, from which it finds one of the locks Thread A itself is holding. Then it knows a deadlock has occurred.

One possible action is to **release all locks, backup, wait a random amount of time and then retry**. This is similar to the simpler lock timeout mechanism except **threads only backup when a deadlock has actually occurred**. Not just because their lock requests timed out. However, if a lot of threads are competing for the same locks they may **repeatedly end up in a deadlock even if they back up and wait**. A better option is to **determine or assign a priority of the threads** so that only one (or a few) thread backs up. The rest of the threads continue taking the locks they need as if no deadlock had occurred. If the priority assigned to the threads is fixed, the **same threads will always be given higher priority**. To avoid this you may **assign the priority randomly** whenever a deadlock is detected.

### Starvation and Fairness


If a thread is **not granted CPU time because other threads grab it** all, it is called "**starvation**". The thread is "starved to death". The solution to starvation is called "**fairness**" - that **all threads are fairly granted a chance to execute**.

#### Causes of Starvation in Java

- Threads with high priority swallow all CPU time from threads with lower priority.
  - You can set the thread priority of each thread individually. The higher the priority the more CPU time the thread is granted. For most applications you are better off leaving the priority unchanged.
- Threads are blocked indefinitely waiting to enter a **synchronized block**, because other threads are constantly allowed access before it.
  - **synchronized code blocks can cause starvation**. synchronized code block **makes no guarantee about the sequence** in which threads waiting to enter the synchronized block are allowed to enter. This means that there is a possibility that a thread remains blocked forever trying to enter the block, because other threads are constantly granted access before it. This problem is called "starvation", that a thread is "starved to death" by because other threads are allowed the CPU time instead of it.
- Threads waiting on an object (called wait() on it) remain waiting indefinitely because other threads are constantly awakened instead of it.
  - The notify() method makes **no guarantee about what thread is awakened** if multiple thread have called wait() on the object notify() is called on. It could be any of the threads waiting. Therefore there is a risk that a thread waiting on a certain object is never awakened because other waiting threads are always awakened instead of it.

#### Implementing Fairness in Java

While it is **not possible to implement 100% fairness** in Java we can still **implement our synchronization constructs to increase fairness** between threads.

##### Using Locks Instead of Synchronized Blocks

Synchronized blocks makes no guarantees about what thread is being granted access if more than one thread is waiting to enter. To increase the fairness of waiting threads we will change the code block to be **guarded by a lock rather than a synchronized block**. Notice the doSynchronized() method is no longer declared synchronized, instead the critical section is **guarded by the wait() and notify() calls**.

But **wait() also make any guarantees** about what thread is awakened when notify() is called. So, the current code makes no different guarantees with respect to fairness than synchronized version. The current code calls its own wait() method. Instead **if each thread calls wait() on a separate object**, so that only one thread has called wait() on each object, then we **can decide which of these objects to call notify()** on, thereby **effectively selecting exactly what thread to awaken**.

##### A Fair Lock

**Every thread calling lock() is now queued**, and only the **first thread in the queue is allowed to lock the FairLock** instance, if it is unlocked. All **other threads are parked waiting until they reach the top** of the queue.

```java
public class FairLock {
    private boolean           isLocked       = false;
    private Thread            lockingThread  = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject           = new QueueObject();
    boolean     isLockedForThisThread = true;
    synchronized(this){
        waitingThreads.add(queueObject);
    }

    while(isLockedForThisThread){
      synchronized(this){
        isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
        if(!isLockedForThisThread){
          isLocked = true;
           waitingThreads.remove(queueObject);
           lockingThread = Thread.currentThread();
           return;
         }
      }
      try{
        queueObject.doWait();
      }catch(InterruptedException e){
        synchronized(this) { waitingThreads.remove(queueObject); }
        throw e;
      }
    }
  }

  public synchronized void unlock(){
    if(this.lockingThread != Thread.currentThread()){
      throw new IllegalMonitorStateException("Calling thread has not locked this lock");
    }
    isLocked      = false;
    lockingThread = null;
    if(waitingThreads.size() > 0){
      waitingThreads.get(0).doNotify();
    }
  }
}
------------------------------------------------------------------------------------
public class QueueObject {
  private boolean isNotified = false;
    
  public synchronized void doWait() throws InterruptedException {
    while(!isNotified){
        this.wait();
    }
    this.isNotified = false;
  }

  public synchronized void doNotify() {
    this.isNotified = true;
    this.notify();
  }

  public boolean equals(Object o) {
    return this == o;
  }
}
```


First you might notice that the lock() method is no longer declared synchronized. Instead only the blocks necessary to synchronize are nested inside synchronized blocks. FairLock creates a new instance of QueueObject and **enqueue it for each thread calling lock()**. The thread calling unlock() will **take the top QueueObject in the queue and call doNotify()** on it, to awaken the thread waiting on that object. This way only one waiting thread is awakened at a time, rather than all waiting threads. This part is what governs the fairness of the FairLock. Notice how the **state of the lock is still tested** and set within the same synchronized block to **avoid slipped conditions**.

Also notice that the QueueObject is really a **semaphore**. The doWait() and doNotify() methods store the signal internally in the QueueObject. This is done to **avoid missed signals** caused by a thread being preempted just before calling queueObject.doWait(), by another thread which calls unlock() and thereby queueObject.doNotify(). The queueObject.doWait() call is placed outside the synchronized(this) block to **avoid nested monitor lockout**, so another thread can actually call unlock() when no thread is executing inside the synchronized(this) block in lock() method.

Finally, notice how the queueObject.doWait() is called inside a try - catch block. In case an InterruptedException is thrown the thread leaves the lock() method, and we need to dequeue it.

You will notice that there is more code running inside the lock() and unlock() in the FairLock class. This extra code will cause the **FairLock to be a sligtly slower synchronization mechanism** than Lock. How much impact this will have on your application **depends on how long time the code in the critical section** guarded by the FairLock takes to execute. The longer this takes to execute, the less significant the added overhead of the synchronizer is. It does of course also depend on **how often this code is called**.

### Nested Monitor Lockout

Nested monitor lockout is a **problem similar to deadlock**. A nested monitor lockout occurs like this:

- Thread 1
  - Thread 1 synchronizes on A (synchronized block)
  - Thread 1 synchronizes on B (while synchronized on A)
  - Thread 1 **decides to wait for a signal** from another thread before continuing. Thread 1 calls **B.wait()** thereby **releasing the lock on B**, but not A.
- Thread 2
  - Thread 2 needs to lock both A and B (in that sequence) to send Thread 1 the signal.
  - **Thread 2 cannot lock A**, since Thread 1 still holds the lock on A.
  - Thread 2 remain blocked indefinitely waiting for Thread1 to release the lock on A
- Nested monitor lockout
  - Thread 1 blocked indefinitely **waiting for the signal from Thread 2**, thereby **never releasing the lock on A**, that must be released to make it possible for **Thread 2 to send the signal** to Thread 1.

#### Nested Monitor Lockout vs. Deadlock

The result of nested monitor lockout and deadlock are **pretty much the same**: The threads involved end up blocked forever waiting for each other. The two **situations are not equal** though. 

A deadlock occurs when two threads **obtain locks in different order**. So deadlocks can be avoided by always locking the locks in the same order (Lock Ordering). However, a nested monitor lockout occurs **exactly by two threads taking the locks in the same order**. Thread 1 locks A and B, then releases B and waits for a signal from Thread 2. Thread 2 needs both A and B to send Thread 1 the signal. 

In deadlock, **two threads are waiting for each other to release locks**. In nested monitor lockout, Thread 1 is holding a lock A, and waits for a signal from Thread 2. Thread 2 needs the lock A to send the signal to Thread 1. So, **one thread is waiting for a signal**, and **another for a lock to be released**.

### Slipped Conditions

Slipped conditions means, that from **the time a thread has checked a certain condition** until it acts upon it, the **condition has been changed by another thread** so that it is **erroneous for the first thread to act**. 

```java
public class Lock {
    private boolean isLocked = true;

    public void lock(){
      synchronized(this){
        while(isLocked){
          try{
            this.wait();
          } catch(InterruptedException e){
            //do nothing, keep waiting
          }
        }
      }
      synchronized(this){
        isLocked = true;
      }
    }
    
    public synchronized void unlock(){
      isLocked = false;
      this.notify();
    }
}
```


Notice the lock() method contains two synchronized blocks. The first block waits until isLocked is false. The second block sets isLocked to true, to lock the Lock instance for other threads. Imagine that isLocked is false, and two threads call lock() at the same time. If the first thread entering the first synchronized block and saw isLocked as false. If the second thread is now allowed to execute, this thread too will see isLocked as false. Then both threads will enter the second synchronized block, set isLocked to true, and continue.

This situation is an **example of slipped conditions**. Both threads test the condition, then exit the synchronized block, thereby allowing other threads to test the condition, **before any of the two first threads change the conditions** for subsequent threads. In other words, the **condition has slipped from the time the condition was checked until the threads change it** for subsequent threads.

To avoid slipped conditions the **testing and setting of the conditions must be done atomically** by the thread doing it, meaning that **no other thread can check the condition in between** the testing and setting of the condition by the first thread.

The solution is simple, just move the line isLocked = true; up into the first synchronized block, right after the while loop. Now the **testing and setting of the isLocked condition is done atomically** from inside the same synchronized block.



A More Realistic Example


A more realistic example would be during the implementation of a fair lock, as discussed in the text on Starvation and Fairness. If we look at the naive implementation from the text Nested Monitor Lockout, and try to remove the nested monitor lock problem it, it is easy to arrive at an implementation that suffers from slipped conditions. First I'll show the example from the nested monitor lockout text:

//Fair Lock implementation with nested monitor lockout problem

public class FairLock {
  private boolean           isLocked       = false;
  private Thread            lockingThread  = null;
  private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject = new QueueObject();

    synchronized(this){
      waitingThreads.add(queueObject);
    
      while(isLocked || waitingThreads.get(0) != queueObject){
    
        synchronized(queueObject){
          try{
            queueObject.wait();
          }catch(InterruptedException e){
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
      waitingThreads.remove(queueObject);
      isLocked = true;
      lockingThread = Thread.currentThread();
    }
  }

  public synchronized void unlock(){
    if(this.lockingThread != Thread.currentThread()){
      throw new IllegalMonitorStateException(
        "Calling thread has not locked this lock");
    }
    isLocked      = false;
    lockingThread = null;
    if(waitingThreads.size() > 0){
      QueueObject queueObject = waitingThread.get(0);
      synchronized(queueObject){
        queueObject.notify();
      }
    }
  }
}
public class QueueObject {}
Notice how the synchronized(queueObject) with its queueObject.wait() call is nested inside the synchronized(this) block, resulting in the nested monitor lockout problem. To avoid this problem the synchronized(queueObject) block must be moved outside the synchronized(this) block. Here is how that could look:

//Fair Lock implementation with slipped conditions problem

public class FairLock {
  private boolean           isLocked       = false;
  private Thread            lockingThread  = null;
  private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject = new QueueObject();

    synchronized(this){
      waitingThreads.add(queueObject);
    }
    
    boolean mustWait = true;
    while(mustWait){
    
      synchronized(this){
        mustWait = isLocked || waitingThreads.get(0) != queueObject;
      }
    
      synchronized(queueObject){
        if(mustWait){
          try{
            queueObject.wait();
          }catch(InterruptedException e){
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
    }
    
    synchronized(this){
      waitingThreads.remove(queueObject);
      isLocked = true;
      lockingThread = Thread.currentThread();
    }
  }
}
Note: Only the lock() method is shown, since it is the only method I have changed.

Notice how the lock() method now contains 3 synchronized blocks.

The first synchronized(this) block checks the condition by setting mustWait = isLocked || waitingThreads.get(0) != queueObject.

The second synchronized(queueObject) block checks if the thread is to wait or not. Already at this time another thread may have unlocked the lock, but lets forget that for the time being. Let's assume that the lock was unlocked, so the thread exits the synchronized(queueObject) block right away.

The third synchronized(this) block is only executed if mustWait = false. This sets the condition isLocked back to true etc. and leaves the lock() method.

Imagine what will happen if two threads call lock() at the same time when the lock is unlocked. First thread 1 will check the isLocked conditition and see it false. Then thread 2 will do the same thing. Then neither of them will wait, and both will set the state isLocked to true. This is a prime example of slipped conditions.

Removing the Slipped Conditions Problem
To remove the slipped conditions problem from the example above, the content of the last synchronized(this) block must be moved up into the first block. The code will naturally have to be changed a little bit too, to adapt to this move. Here is how it looks:

//Fair Lock implementation without nested monitor lockout problem,
//but with missed signals problem.

public class FairLock {
  private boolean           isLocked       = false;
  private Thread            lockingThread  = null;
  private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject = new QueueObject();

    synchronized(this){
      waitingThreads.add(queueObject);
    }
    
    boolean mustWait = true;
    while(mustWait){


​        
​        synchronized(this){
​            mustWait = isLocked || waitingThreads.get(0) != queueObject;
​            if(!mustWait){
​                waitingThreads.remove(queueObject);
​                isLocked = true;
​                lockingThread = Thread.currentThread();
​                return;
​            }
​        } 
​    
​      synchronized(queueObject){
​        if(mustWait){
​          try{
​            queueObject.wait();
​          }catch(InterruptedException e){
​            waitingThreads.remove(queueObject);
​            throw e;
​          }
​        }
​      }
​    }
  }
}
Notice how the local variable mustWait is tested and set within the same synchronized code block now. Also notice, that even if the mustWait local variable is also checked outside the synchronized(this) code block, in the while(mustWait) clause, the value of the mustWait variable is never changed outside the synchronized(this). A thread that evaluates mustWait to false will atomically also set the internal conditions (isLocked) so that any other thread checking the condition will evaluate it to true.

The return; statement in the synchronized(this) block is not necessary. It is just a small optimization. If the thread must not wait (mustWait == false), then there is no reason to enter the synchronized(queueObject) block and execute the if(mustWait) clause.

The observant reader will notice that the above implementation of a fair lock still suffers from a missed signal problem. Imagine that the FairLock instance is locked when a thread calls lock(). After the first synchronized(this) block mustWait is true. Then imagine that the thread calling lock() is preempted, and the thread that locked the lock calls unlock(). If you look at the unlock() implementation shown earlier, you will notice that it calls queueObject.notify(). But, since the thread waiting in lock() has not yet called queueObject.wait(), the call to queueObject.notify() passes into oblivion. The signal is missed. When the thread calling lock() right after calls queueObject.wait() it will remain blocked until some other thread calls unlock(), which may never happen.

The missed signals problems is the reason that the FairLock implementation shown in the text Starvation and Fairness has turned the QueueObject class into a semaphore with two methods: doWait() and doNotify(). These methods store and react the signal internally in the QueueObject. That way the signal is not missed, even if doNotify() is called before doWait().

### Locks in Java


A lock is a **thread synchronization mechanism** like synchronized blocks except **locks can be more sophisticated** than synchronized blocks. Locks (and other more advanced synchronization mechanisms) are also **created using synchronized blocks**.

A Simple Lock
Let's start out by looking at a synchronized block of Java code:

public class Counter{

  private int count = 0;

  public int inc(){
    synchronized(this){
      return ++count;
    }
  }
}
Notice the synchronized(this) block in the inc() method. This block makes sure that only one thread can execute the return ++count at a time. The code in the synchronized block could have been more advanced, but the simple ++count suffices to get the point across.

The Counter class could have been written like this instead, using a Lock instead of a synchronized block:

public class Counter{

  private Lock lock = new Lock();
  private int count = 0;

  public int inc(){
    lock.lock();
    int newCount = ++count;
    lock.unlock();
    return newCount;
  }
}
The lock() method locks the Lock instance so that all threads calling lock() are blocked until unlock() is executed.

Here is a simple Lock implementation:

public class Lock{

  private boolean isLocked = false;

  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked = true;
  }

  public synchronized void unlock(){
    isLocked = false;
    notify();
  }
}
Notice the while(isLocked) loop, which is also called a "spin lock". Spin locks and the methods wait() and notify() are covered in more detail in the text Thread Signaling. While isLocked is true, the thread calling lock() is parked waiting in the wait() call. In case the thread should return unexpectedly from the wait() call without having received a notify() call (AKA a Spurious Wakeup) the thread re-checks the isLocked condition to see if it is safe to proceed or not, rather than just assume that being awakened means it is safe to proceed. If isLocked is false, the thread exits the while(isLocked) loop, and sets isLocked back to true, to lock the Lock instance for other threads calling lock().

When the thread is done with the code in the critical section (the code between lock() and unlock()), the thread calls unlock(). Executing unlock() sets isLocked back to false, and notifies (awakens) one of the threads waiting in the wait() call in the lock() method, if any.

Lock Reentrance
Synchronized blocks in Java are reentrant. This means, that if a Java thread enters a synchronized block of code, and thereby take the lock on the monitor object the block is synchronized on, the thread can enter other Java code blocks synchronized on the same monitor object. Here is an example:

public class Reentrant{

  public synchronized outer(){
    inner();
  }

  public synchronized inner(){
    //do something
  }
}
Notice how both outer() and inner() are declared synchronized, which in Java is equivalent to a synchronized(this) block. If a thread calls outer() there is no problem calling inner() from inside outer(), since both methods (or blocks) are synchronized on the same monitor object ("this"). If a thread already holds the lock on a monitor object, it has access to all blocks synchronized on the same monitor object. This is called reentrance. The thread can reenter any block of code for which it already holds the lock.

The lock implementation shown earlier is not reentrant. If we rewrite the Reentrant class like below, the thread calling outer() will be blocked inside the lock.lock() in the inner() method.

public class Reentrant2{

  Lock lock = new Lock();

  public outer(){
    lock.lock();
    inner();
    lock.unlock();
  }

  public synchronized inner(){
    lock.lock();
    //do something
    lock.unlock();
  }
}
A thread calling outer() will first lock the Lock instance. Then it will call inner(). Inside the inner() method the thread will again try to lock the Lock instance. This will fail (meaning the thread will be blocked), since the Lock instance was locked already in the outer() method.

The reason the thread will be blocked the second time it calls lock() without having called unlock() in between, is apparent when we look at the lock() implementation:

public class Lock{

  boolean isLocked = false;

  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked = true;
  }

  ...
}
It is the condition inside the while loop (spin lock) that determines if a thread is allowed to exit the lock() method or not. Currently the condition is that isLocked must be false for this to be allowed, regardless of what thread locked it.

To make the Lock class reentrant we need to make a small change:

public class Lock{

  boolean isLocked = false;
  Thread  lockedBy = null;
  int     lockedCount = 0;

  public synchronized void lock()
  throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    while(isLocked && lockedBy != callingThread){
      wait();
    }
    isLocked = true;
    lockedCount++;
    lockedBy = callingThread;
  }


  public synchronized void unlock(){
    if(Thread.curentThread() == this.lockedBy){
      lockedCount--;

      if(lockedCount == 0){
        isLocked = false;
        notify();
      }
    }
  }

  ...
}
Notice how the while loop (spin lock) now also takes the thread that locked the Lock instance into consideration. If either the lock is unlocked (isLocked = false) or the calling thread is the thread that locked the Lock instance, the while loop will not execute, and the thread calling lock() will be allowed to exit the method.

Additionally, we need to count the number of times the lock has been locked by the same thread. Otherwise, a single call to unlock() will unlock the lock, even if the lock has been locked multiple times. We don't want the lock to be unlocked until the thread that locked it, has executed the same amount of unlock() calls as lock() calls.

The Lock class is now reentrant.

Lock Fairness
Java's synchronized blocks makes no guarantees about the sequence in which threads trying to enter them are granted access. Therefore, if many threads are constantly competing for access to the same synchronized block, there is a risk that one or more of the threads are never granted access - that access is always granted to other threads. This is called starvation. To avoid this a Lock should be fair. Since the Lock implementations shown in this text uses synchronized blocks internally, they do not guarantee fairness. Starvation and fairness are discussed in more detail in the text Starvation and Fairness.

Calling unlock() From a finally-clause
When guarding a critical section with a Lock, and the critical section may throw exceptions, it is important to call the unlock() method from inside a finally-clause. Doing so makes sure that the Lock is unlocked so other threads can lock it. Here is an example:

lock.lock();
try{
  //do critical section code, which may throw exception
} finally {
  lock.unlock();
}
This little construct makes sure that the Lock is unlocked in case an exception is thrown from the code in the critical section. If unlock() was not called from inside a finally-clause, and an exception was thrown from the critical section, the Lock would remain locked forever, causing all threads calling lock() on that Lock instance to halt indefinately.



### Read / Write Locks in Java

Read / Write Lock Java Implementation
Read / Write Lock Reentrance
Read Reentrance
Write Reentrance
Read to Write Reentrance
Write to Read Reentrance
Fully Reentrant ReadWriteLock
Calling unlock() From a finally-clause
Jakob Jenkov	
Jakob Jenkov
Last update: 2014-06-23


A read / write lock is more sophisticated lock than the Lock implementations shown in the text Locks in Java. Imagine you have an application that reads and writes some resource, but writing it is not done as much as reading it is. Two threads reading the same resource does not cause problems for each other, so multiple threads that want to read the resource are granted access at the same time, overlapping. But, if a single thread wants to write to the resource, no other reads nor writes must be in progress at the same time. To solve this problem of allowing multiple readers but only one writer, you will need a read / write lock.

Java 5 comes with read / write lock implementations in the java.util.concurrent package. Even so, it may still be useful to know the theory behind their implementation.

Read / Write Lock Java Implementation
First let's summarize the conditions for getting read and write access to the resource:

Read Access   	If no threads are writing, and no threads have requested write access.
Write Access   	If no threads are reading or writing.
If a thread wants to read the resource, it is okay as long as no threads are writing to it, and no threads have requested write access to the resource. By up-prioritizing write-access requests we assume that write requests are more important than read-requests. Besides, if reads are what happens most often, and we did not up-prioritize writes, starvation could occur. Threads requesting write access would be blocked until all readers had unlocked the ReadWriteLock. If new threads were constantly granted read access the thread waiting for write access would remain blocked indefinately, resulting in starvation. Therefore a thread can only be granted read access if no thread has currently locked the ReadWriteLock for writing, or requested it locked for writing.

A thread that wants write access to the resource can be granted so when no threads are reading nor writing to the resource. It doesn't matter how many threads have requested write access or in what sequence, unless you want to guarantee fairness between threads requesting write access.

With these simple rules in mind we can implement a ReadWriteLock as shown below:

public class ReadWriteLock{

  private int readers       = 0;
  private int writers       = 0;
  private int writeRequests = 0;

  public synchronized void lockRead() throws InterruptedException{
    while(writers > 0 || writeRequests > 0){
      wait();
    }
    readers++;
  }

  public synchronized void unlockRead(){
    readers--;
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;

    while(readers > 0 || writers > 0){
      wait();
    }
    writeRequests--;
    writers++;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writers--;
    notifyAll();
  }
}
The ReadWriteLock has two lock methods and two unlock methods. One lock and unlock method for read access and one lock and unlock for write access.

The rules for read access are implemented in the lockRead() method. All threads get read access unless there is a thread with write access, or one or more threads have requested write access.

The rules for write access are implemented in the lockWrite() method. A thread that wants write access starts out by requesting write access (writeRequests++). Then it will check if it can actually get write access. A thread can get write access if there are no threads with read access to the resource, and no threads with write access to the resource. How many threads have requested write access doesn't matter.

It is worth noting that both unlockRead() and unlockWrite() calls notifyAll() rather than notify(). To explain why that is, imagine the following situation:

Inside the ReadWriteLock there are threads waiting for read access, and threads waiting for write access. If a thread awakened by notify() was a read access thread, it would be put back to waiting because there are threads waiting for write access. However, none of the threads awaiting write access are awakened, so nothing more happens. No threads gain neither read nor write access. By calling noftifyAll() all waiting threads are awakened and check if they can get the desired access.

Calling notifyAll() also has another advantage. If multiple threads are waiting for read access and none for write access, and unlockWrite() is called, all threads waiting for read access are granted read access at once - not one by one.

Read / Write Lock Reentrance
The ReadWriteLock class shown earlier is not reentrant. If a thread that has write access requests it again, it will block because there is already one writer - itself. Furthermore, consider this case:

Thread 1 gets read access.

Thread 2 requests write access but is blocked because there is one reader.

Thread 1 re-requests read access (re-enters the lock), but is blocked because there is a write request
In this situation the previous ReadWriteLock would lock up - a situation similar to deadlock. No threads requesting neither read nor write access would be granted so.

To make the ReadWriteLock reentrant it is necessary to make a few changes. Reentrance for readers and writers will be dealt with separately.

Read Reentrance
To make the ReadWriteLock reentrant for readers we will first establish the rules for read reentrance:

A thread is granted read reentrance if it can get read access (no writers or write requests), or if it already has read access (regardless of write requests).
To determine if a thread has read access already a reference to each thread granted read access is kept in a Map along with how many times it has acquired read lock. When determing if read access can be granted this Map will be checked for a reference to the calling thread. Here is how the lockRead() and unlockRead() methods looks after that change:

public class ReadWriteLock{

  private Map<Thread, Integer> readingThreads =
      new HashMap<Thread, Integer>();

  private int writers        = 0;
  private int writeRequests  = 0;

  public synchronized void lockRead() throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    while(! canGrantReadAccess(callingThread)){
      wait();                                                                   
    }

    readingThreads.put(callingThread,
       (getAccessCount(callingThread) + 1));
  }


  public synchronized void unlockRead(){
    Thread callingThread = Thread.currentThread();
    int accessCount = getAccessCount(callingThread);
    if(accessCount == 1){ readingThreads.remove(callingThread); }
    else { readingThreads.put(callingThread, (accessCount -1)); }
    notifyAll();
  }


  private boolean canGrantReadAccess(Thread callingThread){
    if(writers > 0)            return false;
    if(isReader(callingThread) return true;
    if(writeRequests > 0)      return false;
    return true;
  }

  private int getReadAccessCount(Thread callingThread){
    Integer accessCount = readingThreads.get(callingThread);
    if(accessCount == null) return 0;
    return accessCount.intValue();
  }

  private boolean isReader(Thread callingThread){
    return readingThreads.get(callingThread) != null;
  }

}
As you can see read reentrance is only granted if no threads are currently writing to the resource. Additionally, if the calling thread already has read access this takes precedence over any writeRequests.

Write Reentrance
Write reentrance is granted only if the thread has already write access. Here is how the lockWrite() and unlockWrite() methods look after that change:

public class ReadWriteLock{

    private Map<Thread, Integer> readingThreads =
        new HashMap<Thread, Integer>();
    
    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(hasReaders())             return false;
    if(writingThread == null)    return true;
    if(!isWriter(callingThread)) return false;
    return true;
  }

  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }
}
Notice how the thread currently holding the write lock is now taken into account when determining if the calling thread can get write access.

Read to Write Reentrance
Sometimes it is necessary for a thread that have read access to also obtain write access. For this to be allowed the thread must be the only reader. To achieve this the writeLock() method should be changed a bit. Here is what it would look like:

public class ReadWriteLock{

    private Map<Thread, Integer> readingThreads =
        new HashMap<Thread, Integer>();
    
    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(isOnlyReader(callingThread))    return true;
    if(hasReaders())                   return false;
    if(writingThread == null)          return true;
    if(!isWriter(callingThread))       return false;
    return true;
  }

  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }

  private boolean isOnlyReader(Thread thread){
      return readers == 1 && readingThreads.get(callingThread) != null;
      }

}
Now the ReadWriteLock class is read-to-write access reentrant.

Write to Read Reentrance
Sometimes a thread that has write access needs read access too. A writer should always be granted read access if requested. If a thread has write access no other threads can have read nor write access, so it is not dangerous. Here is how the canGrantReadAccess() method will look with that change:

public class ReadWriteLock{

    private boolean canGrantReadAccess(Thread callingThread){
      if(isWriter(callingThread)) return true;
      if(writingThread != null)   return false;
      if(isReader(callingThread)  return true;
      if(writeRequests > 0)       return false;
      return true;
    }

}
Fully Reentrant ReadWriteLock
Below is the fully reentran ReadWriteLock implementation. I have made a few refactorings to the access conditions to make them easier to read, and thereby easier to convince yourself that they are correct.

public class ReadWriteLock{

  private Map<Thread, Integer> readingThreads =
       new HashMap<Thread, Integer>();

   private int writeAccesses    = 0;
   private int writeRequests    = 0;
   private Thread writingThread = null;


  public synchronized void lockRead() throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    while(! canGrantReadAccess(callingThread)){
      wait();
    }

    readingThreads.put(callingThread,
     (getReadAccessCount(callingThread) + 1));
  }

  private boolean canGrantReadAccess(Thread callingThread){
    if( isWriter(callingThread) ) return true;
    if( hasWriter()             ) return false;
    if( isReader(callingThread) ) return true;
    if( hasWriteRequests()      ) return false;
    return true;
  }


  public synchronized void unlockRead(){
    Thread callingThread = Thread.currentThread();
    if(!isReader(callingThread)){
      throw new IllegalMonitorStateException("Calling Thread does not" +
        " hold a read lock on this ReadWriteLock");
    }
    int accessCount = getReadAccessCount(callingThread);
    if(accessCount == 1){ readingThreads.remove(callingThread); }
    else { readingThreads.put(callingThread, (accessCount -1)); }
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    if(!isWriter(Thread.currentThread()){
      throw new IllegalMonitorStateException("Calling Thread does not" +
        " hold the write lock on this ReadWriteLock");
    }
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(isOnlyReader(callingThread))    return true;
    if(hasReaders())                   return false;
    if(writingThread == null)          return true;
    if(!isWriter(callingThread))       return false;
    return true;
  }


  private int getReadAccessCount(Thread callingThread){
    Integer accessCount = readingThreads.get(callingThread);
    if(accessCount == null) return 0;
    return accessCount.intValue();
  }


  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isReader(Thread callingThread){
    return readingThreads.get(callingThread) != null;
  }

  private boolean isOnlyReader(Thread callingThread){
    return readingThreads.size() == 1 &&
           readingThreads.get(callingThread) != null;
  }

  private boolean hasWriter(){
    return writingThread != null;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }

  private boolean hasWriteRequests(){
      return this.writeRequests > 0;
  }

}
Calling unlock() From a finally-clause
When guarding a critical section with a ReadWriteLock, and the critical section may throw exceptions, it is important to call the readUnlock() and writeUnlock() methods from inside a finally-clause. Doing so makes sure that the ReadWriteLock is unlocked so other threads can lock it. Here is an example:

lock.lockWrite();
try{
  //do critical section code, which may throw exception
} finally {
  lock.unlockWrite();
}
This little construct makes sure that the ReadWriteLock is unlocked in case an exception is thrown from the code in the critical section. If unlockWrite() was not called from inside a finally-clause, and an exception was thrown from the critical section, the ReadWriteLock would remain write locked forever, causing all threads calling lockRead() or lockWrite() on that ReadWriteLock instance to halt indefinately. The only thing that could unlock the ReadWriteLockagain would be if the ReadWriteLock is reentrant, and the thread that had it locked when the exception was thrown, later succeeds in locking it, executing the critical section and calling unlockWrite() again afterwards. That would unlock the ReadWriteLock again. But why wait for that to happen, if it happens? Calling unlockWrite() from a finally-clause is a much more robust solution.





### Reentrance Lockout

Jakob Jenkov	
Jakob Jenkov
Last update: 2014-06-23


Reentrance lockout is a situation similar to deadlock and nested monitor lockout. Reentrance lockout is also covered in part in the texts on Locks and Read / Write Locks.

Reentrance lockout may occur if a thread reenters a Lock, ReadWriteLock or some other synchronizer that is not reentrant. Reentrant means that a thread that already holds a lock can retake it. Java's synchronized blocks are reentrant. Therefore the following code will work without problems:

public class Reentrant{

  public synchronized outer(){
    inner();
  }

  public synchronized inner(){
    //do something
  }
}
Notice how both outer() and inner() are declared synchronized, which in Java is equivalent to a synchronized(this) block. If a thread calls outer() there is no problem calling inner() from inside outer(), since both methods (or blocks) are synchronized on the same monitor object ("this"). If a thread already holds the lock on a monitor object, it has access to all blocks synchronized on the same monitor object. This is called reentrance. The thread can reenter any block of code for which it already holds the lock.

The following Lock implementation is not reentrant:

public class Lock{

  private boolean isLocked = false;

  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked = true;
  }

  public synchronized void unlock(){
    isLocked = false;
    notify();
  }
}
If a thread calls lock() twice without calling unlock() in between, the second call to lock() will block. A reentrance lockout has occurred.

To avoid reentrance lockouts you have two options:

Avoid writing code that reenters locks
Use reentrant locks
Which of these options suit your project best depends on your concrete situation. Reentrant locks often don't perform as well as non-reentrant locks, and they are harder to implement, but this may not necessary be a problem in your case. Whether or not your code is easier to implement with or without lock reentrance must be determined case by case.



### Semaphores

A Semaphore is a **thread synchronization construct** that can be used either to **send signals between threads** to avoid missed signals, or **to guard a critical section**.

#### Bounded Semaphore

The take() method **blocks if the number of signals is equal to the upper bound** and is blocked until a thread has called release().

```java
public class BoundedSemaphore {
  private int signals = 0;
  private int bound   = 0;

  public BoundedSemaphore(int upperBound){
    this.bound = upperBound;
  }

  public synchronized void take() throws InterruptedException{
    while(this.signals == bound) wait();
    this.signals++;
    this.notify();
  }

  public synchronized void release() throws InterruptedException{
    while(this.signals == 0) wait();
    this.signals--;
    this.notify();
  }
}
```

#### Using Semaphores as Locks

It is possible to use a bounded semaphore as a lock by setting the upper bound to 1, and have the call to take() and release() guard the critical section.

```java
BoundedSemaphore semaphore = new BoundedSemaphore(1);
...
semaphore.take();
try{
  //critical section
} finally {
  semaphore.release();
}
```


The methods take() and release() are now **called by the same thread**. Since only one thread is allowed to take the semaphore, all other threads calling take() will be blocked until release() is called. The call to release() will never block since there has always been a call to take() first. The relase() method is called from **inside a finally-block to make sure it is called** even if an exception is thrown from the critical section. You can also use a bounded semaphore to **limit the number of threads allowed into a section of code**. 

### Blocking Queues

A blocking queue is a queue that **blocks when you try to dequeue from an empty queue** and is blocked until some other thread inserts an item into the queue, or if you try to **enqueue an item in a full queue** and is blocked until some other thread makes space in the queue, by dequeuing one or more items.

#### Blocking Queue Implementation

The implementation of a blocking queue looks similar to a **Bounded Semaphore**. 

```java
public class BlockingQueue {

  private List queue = new LinkedList();
  private int  limit = 10;
  public BlockingQueue(int limit){
    this.limit = limit;
  }

  public synchronized void enqueue(Object item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    this.queue.add(item);
    if(this.queue.size() == 1) {
      notifyAll();
    }
  }

  public synchronized Object dequeue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    if(this.queue.size() == this.limit){
      notifyAll();
    }
    return this.queue.remove(0);
  }
}
```


Notice how notifyAll() is only called from enqueue() and dequeue() if the queue size is equal to the size bounds (0 or limit). If the queue size is not equal to either bound when enqueue() or dequeue() is called, there can be no threads waiting to either enqueue or dequeue items.

### Thread Pools


Thread Pools are useful when you need to **limit the number of threads running in your application** at the same time. There is a **performance overhead associated with starting a new thread**, and each thread is also allocated some memory for its stack etc.

**Instead of starting a new thread for every task** to execute concurrently, the task can be passed to a thread pool. As soon as the pool has any idle threads the task is assigned to one of them and executed. Internally the **tasks are inserted into a Blocking Queue**. When a new task is inserted into the queue one of the idle threads will dequeue it successfully and execute it. The rest of the idle threads in the pool will be **blocked waiting to dequeue tasks**.

```java
public class PoolThread extends Thread {
    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;
    public PoolThread(BlockingQueue queue){
        taskQueue = queue;
    }
    public void run(){
        while(!isStopped()){
            try{
                //threads in the pool will be blocked waiting to dequeue tasks
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception, but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }
    public synchronized boolean isStopped(){
        return isStopped;
    }
}
```

The thread pool implementation consists of two parts. A ThreadPool class and a PoolThread class. To execute a task the method ThreadPool.execute(Runnable r) is called. The Runnable is enqueued in the blocking queue internally. The Runnable will be dequeued by an idle PoolThread and executed. After execution the PoolThread loops and tries to dequeue a task again, until stopped.

To stop the ThreadPool, ThreadPool.stop() is called. Each thread in the pool is stopped by calling doStop() on each thread. The execute() method will throw an **IllegalStateException if execute() is called again after stop()**. The threads will stop after finishing any task they are currently executing. The **interrupt()** call in PoolThread.doStop() makes sure that a thread blocked in a wait() call inside the taskQueue.dequeue() call breaks out of the wait() call, and **leaves the dequeue() method call with an InterruptedException** thrown. This exception is caught in the PoolThread.run() method, reported, and then the isStopped variable is checked. Since isStopped is now true, the PoolThread.run() will exit and the thread dies.

### Compare and Swap

Compare and swap is a **technique used when designing concurrent algorithms**. Basically, compare and swap **compares an expected value to the concrete value** of a variable, and if the concrete value of the variable is equals to the expected value, swaps the value of the variable with a new value. To work properly in a multithreaded application,compare and swap operations must be atomic.

Modern CPUs have **built-in support for atomic compare and swap** operations. You can access these functions in the CPU via the new atomic classes like AtomicBoolean. This class has a **compareAndSet()** function which will compare the value of the AtomicBoolean instance to an expected value, and if has the expected value, it swaps the value with a new value. The compareAndSet() method **returns true if the value was swapped**, and false if not. The advantage of using the compare and swap features is that it lets you utilize the **underlying compare and swap features of the CPU** your application is running on. This makes your compare and swap code faster.

### Anatomy of a Synchronizer

Even if many synchronizers (locks, semaphores, blocking queue etc.) are **different in function**, they are often **similar in their internal design** and they consist of the **same (or similar) basic parts** internally. The purpose of most (if not all) synchronizers is to **guard some area of the code (critical section)** from concurrent access by threads. To do this the following parts are **often needed in a synchronizer**:

##### State

The state of a synchronizer is **used by the access condition** to determine if a thread can be **granted access**. In a Lock the state is **kept in a boolean** saying whether the Lock is locked or not. In a Bounded Semaphore the **internal state is kept in a counter (int) and an upper bound (int)** which state the current number of "takes" and the maximum number of "takes". In a Blocking Queue the **state is kept in the List of elements in the queue and the maximum queue size** (int) member (if any).

##### Access Condition

The access conditions is **what determines if a thread calling a test-and-set-state method can be allowed to set the state or not**. The access condition is typically **based on the state** of the synchronizer. The access condition is typically checked in a while loop to guard against **Spurious Wakeups**. When the access condition is evaluated it is either true or false.

In a Lock the access condition simply checks the value of the isLocked member variable. In a Bounded Semaphore there are actually **two access conditions** depending on whether you are trying to "take" or "release" the semaphore. If a thread tries to take the semaphore the signals variable is checked against the upper bound. If a thread tries to release the semaphore the signals variable is checked against 0.

##### State Changes

Once a thread gains access to the critical section it has to **change the state of the synchronizer to (possibly) block other threads** from entering it. In other words, the state needs to reflect the fact that a thread is now executing inside the critical section. This should **affect the access conditions of other threads** attempting to gain access. 

- In a Lock, the state change is the code setting isLocked = true. 
- In a semaphore it is either the code signals-- or signals++;

##### Notification Strategy

Once a thread has **changed the state** of a synchronizer it may sometimes need to **notify other waiting threads about the state change**. Perhaps this state change **might turn the access condition true** for other threads. Notification Strategies typically fall into three categories.

- Notify all waiting threads.
  - This is pretty easy. All waiting threads **call wait() on the same object**. Once a thread want to notify the waiting threads it calls **notifyAll()** on the object the waiting threads called wait() on.
- Notify 1 random of N waiting threads.
  - The notifying thread call **notify()** on the object the waiting threads have called wait() on. Calling notify makes no guarantee about which of the waiting threads (random) will be notified.
- Notify 1 specific of N waiting thread.
  - Sometimes you may need to **notify a specific rather than a random** waiting thread, you need to guarantee that **waiting threads are notified in a specific order**. To achieve this each waiting thread must **call wait() on its own, separate object**. When the notifying thread wants to notify a specific waiting thread it will call notify() on the object this specific thread has called wait() on. 

##### Test and Set Method

Synchronizer most often have **two types of methods** of which **test-and-set is the first type** (**set is the other**). Test-and-set means that the thread calling this method **tests the internal state of the synchronizer against the access condition**. If the condition is met the thread **sets the internal state** of the synchronizer to reflect that the thread has gained access.

The state transition usually results in the **access condition turning false for other threads** trying to gain access. But not always, for instance, in a **Read - Write Lock** a thread gaining read access will not block other threads requesting read access as long as no threads has requested write access. The test-and-set operations are **executed atomically** meaning no other threads are allowed to execute in between the test and the setting of the state. The program flow of a test-and-set method contains:

- Set state before test if necessary
- Test state against access condition
- If access condition is not met, wait
- If access condition is met, set state, and notify waiting threads if necessary

Threads calling lockWrite() of a ReadWriteLock class first sets the state before the test (writeRequests++). Then it tests the internal state against the access condition in the canGrantWriteAccess() method. If the test succeeds the internal state is set again before the method is exited.

```java
public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
        wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
}    
```

The BoundedSemaphore class has two test-and-set methods: take() and release(). Both methods test and sets the internal state.

##### Set Method

The set method just **sets the internal state of the synchronizer without testing it first**. An example of a set method is the **unlock() method of a Lock class**. A thread holding the lock can **always unlock it without having to test** if the Lock is unlocked. The program flow of a set method contains :

- Set internal state
- Notify waiting threads

### Non-blocking Algorithms


Non-blocking algorithms in the context of concurrency are **algorithms that allows threads to access shared state** (or otherwise collaborate or communicate) **without blocking** the threads involved. In more general terms, an algorithm is said to be non-blocking if the **suspension of one thread cannot lead to the suspension of other threads** involved in the algorithm.

#### Blocking Concurrency Algorithms

A blocking concurrency algorithm is an algorithm which either:

- A: Performs the action requested by the thread - OR
- B: Blocks the thread until the action can be performed safely

Many types of algorithms and concurrent data structures are blocking. For instance, the <u>implementations of the BlockingQueue interface are all blocking data structures</u>. If a thread attempts to insert an element into a BlockingQueue and the queue does not have space, the inserting thread is blocked (suspended) until the BlockingQueue has space for the new element.

#### Non-blocking Concurrency Algorithms

A non-blocking concurrency algorithm is an algorithm which either:

- A: Performs the action requested by the thread - OR
- B: **Notifies the requesting thread that the action could not be performed**

Java contains several non-blocking data structures too. The AtomicBoolean, AtomicInteger, AtomicLong and AtomicReference are all examples of non-blocking data structures.

#### Non-blocking vs Blocking Algorithms

The main difference between blocking and non-blocking algorithms lies in what the **algorithms do when the requested action cannot be performed**. Blocking algorithms **block the thread** until the requested action can be performed. Non-blocking algorithms **notify the thread requesting thread** that the action cannot be performed.

With a blocking algorithm a **thread may become blocked until it is possible** to perform the requested action. Usually it will be the **actions of another thread that makes it possible** for the first thread to perform the requested action. If for some reason that other thread is suspended (blocked) somewhere else in the application, and thus cannot perform the action the first thread remains blocked - either indefinitely, or until the other thread finally performs the necessary action. For instance, if a thread tries to **insert an element into a full BlockingQueue** the thread will block until another thread has taken an element from the BlockingQueue. 

#### Concurrent Data Structures

In a multithreaded system, **threads usually communicate via some kind of data structure**. Such data structures can be anything from simple variables to more advanced data structures like queues, maps, stacks etc. To facilitate **correct, concurrent access to the data structures** by multiple threads, the data structures must be **guarded by some concurrent algorithm**. The guarding algorithm is what makes the data structure a concurrent data structure.

- If the algorithm guarding a concurrent data structure is blocking (uses thread suspension), it is said to be a **blocking algorithm**. The data structure is thus said to be a **blocking, concurrent data structure**.
- If the algorithm guarding a concurrent data structure is non-blocking, it is said to be a **non-blocking algorithm**. The data structure is thus said to be a **non-blocking, concurrent data structure**.

#### Volatile Variables

Java volatile variables are **variables that are always read directly from main memory**. When a new value is assigned to a volatile variable the **value is always written immediately to main memory**. This guarantees that the latest value of a volatile variable is always visible to other threads running on other CPUs. Other threads will **read the value of the volatile from main memory every time**, instead of from say the CPU cache.

**Volatile variables are non-blocking**. The **writing of a value to a volatile variable is an atomic operation**. It cannot be interrupted. However, a **read-update-write sequence** performed on a volatile variable is **not atomic**. So below may still lead to race conditions if performed by more than one thread.

```java
volatile myVar = 0;
int temp = myVar;
temp++;
myVar = temp;
```

In practice the above code is equivalent to this:  **myVar++;**
When executed, the value of myVar is read into a CPU register or the local CPU cache, one is added, and then the value from the CPU register or CPU cache is written back to main memory. So a volatile variable in Java thread-safe, you can read from it and write to it safely - but you **can't do anything compound** such as **incrementing** it safely, as that's a **read/modify/write cycle**.

- **The Single Writer Case**
  - **No race conditions** can occur when only a single thread (**same thread always**) is updating a shared variable, no matter how many threads are reading it. Therefore, whenever you have only a single writer of a shared variable you can **use a volatile variable**. All other threads only perform a read operation, you have no race conditions.
- **Multiple Writer Case**
  - Here we create a data structures that use **combinations of volatile variables**, where **each volatile variable is only written by a single thread**, and read by multiple threads. Each volatile variable may be written by a different thread (but only one thread). Using such a data structure multiple threads may be able to **send information (communicate) to each other in a non-blocking way**, using the volatile variables. E.g. two threads communicating and the two counts could be tasks produced and tasks consumed. You could have achieved the same effect using two separate SingleWriter instances also.

#### Compare and Swap for Optimistic Locking

If you have more than one thread to write to the same, shared variable, a volatile variable will not be sufficient. You will need an **exclusive access to the variable** using a synchronized block or wait() - notify() calls etc.

```java
public class SynchronizedCounter {
    long count = 0;
    public void inc() {
        synchronized(this) {
            count++;
        }
    }
    public long count() {
        synchronized(this) {
            return this.count;
        }
    }
}
```

Instead of the two synchronized blocks we can **use one of the atomic variables** like the AtomicLong. 

```java
public class AtomicCounter {
    private AtomicLong count = new AtomicLong(0);
    public void inc() {
        boolean updated = false;
        while(!updated){
            long prevCount = this.count.get();
            updated = this.count.compareAndSet(prevCount, prevCount + 1);
        }
    }
    public long count() {
        return this.count.get();
    }
}
```

This version is just as thread-safe as the previous one. The inc() method **no longer contains a synchronized block**. But inc() method is not an atomic operation. That means, that it is possible for two different threads to call the inc() method and execute the long prevCount = this.count.get() statement, and thus **both obtain the previous count for the counter**. Yet, the above code does not contain any race conditions. Because the **compareAndSet() method call is an atomic operation**. It compares the internal value of the AtomicLong to an expected value, and if the two values are equal, sets a new internal value for the AtomicLong. The compareAndSet() method is typically **supported by compare-and-swap instructions directly in the CPU**. Therefore **no synchronization is necessary**, and no thread suspension is necessary. This saves the thread suspension overhead.

Imagine that the internal value of the AtomicLong is 20. Then two threads read that value, and both tries to call compareAndSet(20, 20 + 1). Since compareAndSet() is an atomic operation, the **threads will execute this method sequentially (one at a time)**. The first thread will compare the expected value of 20 (the previous value of the counter) to the internal value of the AtomicLong. Since the two values are equal, the AtomicLong will update its internal value to 21. The updated variable will be set to true and the while loop will stop. Now the second thread calls compareAndSet(20, 20 + 1). Since the internal value of the AtomicLong is no longer 20, this call will fail. The updated variable will be set to false, and the thread will spin one more time around the while loop. This time it will read the value 21 and attempt to update it to 22. If no other thread has called inc() in the meantime, the second iteration will succeed.

#### Optimistic Locking

Optimistic locking is different from **traditional locking** (or called **pessimistic locking**) . Traditional locking **blocks the access to the shared memory** with a synchronized block or a lock of some kind, may result in threads being suspended.

Optimistic locking allows all threads to **create a copy of the shared memory** without any blocking. The threads may then make modifications to their copy, and attempt to write their modified version back into the shared memory. If no other thread has made any modifications to the shared memory, the compare-and-swap operation allows the thread to write its changes to shared memory else the thread will have to obtain a new copy, make its changes and attempt to write them to shared memory again.

The **reason this is called optimistic locking** is that threads obtain a copy of the data they want to change and apply their changes, under the **optimistic assumption that no other thread will have made changes** to the shared memory in the meantime. When this optimistic assumption holds true, the thread just managed to update shared memory without locking. When this assumption is false, the work was wasted, but still no locking was applied.

**Optimistic locking tends to work best** with **low to medium contention** on the shared memory. If the content is very high on the shared memory, threads will **waste a lot of CPU cycles** copying and modifying the shared memory only to fail writing the changes back to the shared memory. But, if you have a lot of content on shared memory, you should consider **redesigning your code to lower the contention**.

#### Non-swappable Data Structures

The simple **compare-and-swap optimistic locking** works for shared data structures where the **whole data structure can be swapped** (exchanged) with a new data structure in a single compare-and-swap operation.  Swapping the whole data structure with a modified copy **may not always be possible** or feasible, though. Imagine if the shared data structure is a queue. Each thread trying to either insert or take elements from the queue would have to copy the whole queue and make the desired modifications to the copy. This could be **achieved via an AtomicReference**. Copy the reference, copy and modify the queue, and try to swap the reference pointed to in the AtomicReference to the newly created queue.

However, a big data structure may require a **lot of memory and CPU cycles** to copy, make your application spend a lot more memory, and waste a lot of time on the copying. This will impact the performance, especially if contention on the data structure is high. Furthermore, the longer time it takes for a thread to copy and modify the data structure, the bigger the probability is that some other thread will have modified the data structure in between. Now all other threads have to **restart their copy-modify operations**. This will increase the impact on performance and memory consumption even more.

#### Sharing Intended Modifications

To implement a non-blocking data structures which can be updated concurrently, instead of copying and modifying the whole shared data structure, a **thread can share its intended modification** of the shared data structure. The process for a thread wanting to make a modification to the shared data structure then becomes:

- Check if another thread has submitted an intended modification to the data structure.
- If no other thread has submitted an intended modification, **create an intended modification** (represented by an object) and **submit that intended modification to the data structure** (using a compare-and-swap operation).
- Carry out the modification of the shared data structure.
- **Remove the reference to the intended modification** to signal to other threads that the intended modification has been carried out.

If one thread successfully submits an intended modification, no other thread can submit an intended modification **until the first intended modification is carried out**. Thus, effectively works as a lock of the shared data structure. The shared data structure does not directly block the other threads using the data structure. The other threads can detect that they cannot submit an intended modification and decide to something else.

#### Completable Intended Modifications

To avoid that a submitted intended modification can lock the shared data structure, a submitted intended modification object must **contain enough information for another thread** to complete the modification. Thus, if the thread submitting the intended modification never completes the modification, another thread can complete the modification on its behalf, and keep the **shared data structure available for other threads** to use.


The modifications must be **carried out as one or more compare-and-swap operations**. Thus, if two threads try to complete the intended modification, only one thread will be able to carry out any of the compare-and-swap operations. As soon as a compare-and-swap operation has been completed, further attempts to complete that compare-and-swap operation will fail.

#### The A-B-A Problem

The A-B-A problem refers to the situation where a **variable is changed from A to B and then back to A again**. For another thread it is thus **not possible to detect that the variable was indeed changed**.

If thread A checks for ongoing updates, copies data and is suspended by the thread scheduler, a thread B may be able to access the shared data structure in the meanwhile. If thread B performs a full update of the data structure, and removes its intended modification, it will **look to thread A as if no modification has taken place** since it copied the data structure. However, a modification did take place. When thread A continues to perform its update based on its now out-of-date copy of the data structure, the data structure will have thread B's modification undone.

##### A-B-A Solutions

A common solution to the A-B-A problem is to not just swap a pointer to an intended modification object, but to **combine the pointer with a counter**, and swap pointer + counter using a single compare-and-swap operation. Thus, even if the current modification pointer is set back to point to "no ongoing modification", the <u>counter part of the pointer + counter will have been incremented</u>, making the update visible to other threads. In Java <u>you cannot merge a reference and a counter together into a single variable</u>, but possible in languages that support pointers like C and C++.  So Java provides the **AtomicStampedReference** class which can swap a reference and a stamp atomically using a compare-and-swap operation.

#### Benefit of Non-blocking Algorithms

##### Choice

The first benefit of non-blocking algorithms is, that threads are **given a choice about what to do** when their requested action cannot be performed. **Instead of just being blocked**, the request thread has a choice about what to do. On a single CPU system perhaps it makes **sense to suspend a thread** that cannot perform a desired action, and let other threads run on the CPU. But even on a single CPU system blocking algorithms may lead to problems like deadlock, starvation and other concurrency problems.

##### No Deadlocks

The second benefit is that the **suspension of one thread cannot lead to the suspension of other threads**. This means that **deadlock cannot occur**. Since threads are not blocked when they cannot perform their requested action, they cannot be blocked waiting for each other. Non-blocking algorithms **may still result in live lock**, where two threads keep attempting some action, but it is not possible for longer duration (because of the actions of the other thread).

##### No Thread Suspension

Suspending and **reactivating a thread is costly**. Since threads are **not suspended by non-blocking algorithms**, this overhead does not occur. So the **CPUs can potentially spend more time** performing actual business logic instead of context switching. On a multi CPU system blocking algorithms can have more significant impact on the overall performance. A thread running on CPU A can be blocked waiting for a thread running on CPU B. This lowers the **level of parallelism** the application is capable of achieving. 

##### Reduced Thread Latency

Latency in this context means the <u>time between a requested action becomes possible and the thread actually performs it</u>. Since threads are not suspended in non-blocking algorithms they **do not have to pay the expensive, slow reactivation overhead**. That means that when a requested action becomes possible threads can respond faster and thus **reduce their response latency**. The non-blocking algorithms often **obtain the lower latency by "busy-waiting"** until the requested action becomes possible. In a system with high thread contention on the non-blocking data structure, CPUs may end up **burning a lot of cycles during these busy waits**. Non-blocking algorithms **may not be the best** if your data structure has **high thread contention**. However, there are ways to redesign your application to have less thread contention.

### Amdahl's Law

Amdahl's law can be used to **calculate how much a computation can be sped up** by running part of it in parallel. A program (or algorithm) which can be parallelized can be split up into two parts:

- A part which cannot be parallelized
- A part which can be parallelized

The total time taken to execute the program in serial (not in parallel) is called **T**. The time T includes the <u>time of both the non-parallelizable and parallelizable parts</u>. The non-parallelizable part is called B then the parallizable part is referred to as T - B.  This parallelizable part, T - B, that can be sped up by executing it in parallel. The number of threads or CPUs is called N. The fastest the the parallelizable part can be executed is thus:  **(T - B) / N**

According to Amdahl's law, the total execution time of the program when the parallelizable part is executed using N threads or CPUs is thus:   **T(N) = B + (T - B) / N**
T(N) means total execution with with a parallelization factor of N. Thus, T could be written T(1) , meaning the total execution time with a parallelization factor of 1. Amdahl's law looks like this:

T(N) = B + ( T(1) - B ) / N

#### Optimizing Algorithms

From Amdahl's law it follows naturally, that the **parallelizable part can be executed faster by throwing hardware** at it. More threads / CPUs. The non-parallelizable part, however, can only be **executed faster by optimizing the code**. Thus, you can even change the algorithm to have a **smaller non-parallelizable part** in general, by moving some of the work into the parallelizable part (if possible).

If you optimize the sequential (non-parallelizable) part of a program you can also use Amdahl's law to calculate the execution time of the program after the optimization. If the non-parallelizable part B is optimized by a factor of O, then it takes B / O time, so the parallelizable part takes 1 - B / O time. So Amdahl's law looks like this:

​		**T(O,N) = B / O + (1 - B / O) / N**

#### Execution Time vs. Speedup

We can use Amdahl's law to calculate the **execution time of a program after optimization** or parallelization. We can also use Amdahl's law to **calculate the speedup** (how much faster the new algorithm or program is than the old version).

```
If the time of the old version of the program or algorithm is T, then the speedup will be
Speedup = T / T(O,N)

If we set T to 1 the equation then looks like this: Speedup = 1 / T(O,N)
If we insert the Amdahl's law calculation instead of T(O,N):
Speedup = 1 / ( B / O + (1 - B / O) / N )
```

In practice, many other factors may come into play like speed of memory, CPU cache memory, disks, network cards etc. Sometimes a highly serialized sequential (single CPU) algorithm may outperform a parallel algorithm, simply because the sequential version has **no coordination overhead** (**breaking down work and building the total again**), and because a single CPU algorithm may conform better with **how the underlying hardware works** (CPU pipelines, CPU cache etc).



### References

http://tutorials.jenkov.com/java-concurrency/index.html




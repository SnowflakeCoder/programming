## Dynamic Programming 

Dynamic Programming solves a complex problem by <u>breaking it into sub-problems</u> and <u>stores the results of sub-problems</u> to <u>avoid computing the same results</u> again. Following are the **two main properties** that suggest that the given problem can be solved using Dynamic programming.

### 1) Overlapping Subproblems:

Dynamic Programming is mainly used when **solutions of same subproblems are needed again** and again. These <u>computed solutions of subproblems are stored in a table</u> so that these don’t have to recomputed. So Dynamic Programming is not useful when there are **no common (overlapping) subproblems** because there is no point storing the solutions if they are not needed again. For example, **Binary Search doesn’t have common subproblems**. But if you see the recursive program for Fibonacci Numbers, there are many subproblems which are solved again and again.

```java
/* recursive program for Fibonacci numbers */
int fib(int n)
{ 
    if ( n <= 1 )
        return n; 
    return fib(n-1) + fib(n-2);
}
```

### 2) Optimal Substructure

A given problems has Optimal Substructure Property if <u>optimal solution of the given problem can be obtained by using optimal solutions of its subproblems</u>. For example, the **Shortest Path problem** has optimal substructure property. If a node x lies in the shortest path from a source node u to destination node v then the shortest path from u to v is combination of shortest path from u to x and shortest path from x to v. The standard All Pair Shortest Path algorithms like **Floyd–Warshall and Bellman–Ford** are typical examples of Dynamic Programming. On the other hand, the <u>**Longest Path problem** doesn’t have the Optimal Substructure property</u>. For example, There are two longest paths from q to t: q→r→t and q→s→t, but the longest path q→r→t is not a combination of longest path from q to r and longest path from r to t, because the longest path from q to r is q→s→t→r and the longest path from r to t is r→q→s→t.

![img](https://media.geeksforgeeks.org/wp-content/cdn-uploads/LongestPath.gif)

### Memoizatation Vs Tabulation

In dynamic programming, there are **two different ways to store the values** so that these values can be reused:

a) **Memoization (Top Down)**: The memoized program for a problem is **similar to the recursive version** with a small modification that it looks into a <u>lookup table before computing solutions</u>. We initialize a lookup array with all initial values as NIL. Whenever we need solution to a subproblem, we first look into the lookup table. If the precomputed value is there then we return that value, otherwise we calculate the value and put the result in lookup table so that it can be reused later.

Here, we start our journey from the top most destination state and compute its answer by taking in count the values of states that can reach the destination state, till we reach the bottom most base state. So instead of starting from the base state that i.e dp[0] we ask our answer from the states that can reach the destination state dp[n] following the state transition relation, then it is the top-down fashion of DP. As we can see we are storing the most recent cache up to a limit so that if next time we got a call for the same state we simply return it from the memory. So, this is why we call it **memoization** as we are <u>storing the most recent state values</u>.

b) **Tabulation (Bottom Up)**: The tabulated program for a given problem <u>builds a table in bottom up fashion</u> and returns the last entry from table. For example, for Fibonacci number, we first calculate fib(0) then fib(1) then fib(2) then fib(3) and so on. So we are building the solutions of subproblems bottom-up. This starts from the bottom and cumulating answers to the top and reached the top most desired state. It starts its transition from the bottom most base case dp[0] and reaches it destination state dp[n]. Here the dp table is being populated sequentially and we are directly accessing the calculated states from the table itself and hence, we call it **tabulation method**.

![img](https://media.geeksforgeeks.org/wp-content/cdn-uploads/Tabulation-vs-Memoization-1.png)

Both Tabulated and Memoized store the solutions of subproblems. In Memoized version, table is filled on demand while in Tabulated version, starting from the first entry, all entries are filled one by one. Unlike the Tabulated version, all entries of the lookup table are not necessarily filled in Memoized version. For example, Memoized solution of the **LCS (Longest Common Subsequence)** problem doesn’t necessarily fill all entries.









## Fail Fast and Fail Safe Iterators

First of all, there is no term as fail-safe. Its only Fail fast and Non fail-fast iterators.

**Concurrent Modification**: modify an object concurrently when another task is already running over it. 

**Fail-Fast iterators**

**Fail-Fast iterators** immediately throw **ConcurrentModificationException** if there is structural modification of the collection. **Structural modification** means adding, removing or updating any element from collection while a thread is iterating over that collection. If you remove an element via Iterator remove() method, exception will not be thrown. However, in case of removing via a collection remove() method, ConcurrentModificationException will be thrown. Iterator on HashMap, Vector, ArrayList, HashSet classes are some examples of fail-fast Iterator. According to Oracle docs , Fail-fast iterators throw ConcurrentModificationException on a **best-effort basis**. Therefore, it would be wrong to write a program that depended on this exception for its correctness.

**How Fail Fast Iterator works?**

To know whether the collection is structurally modified or not, fail-fast iterators use an internal flag called **modCount** which is updated whenever there is a structural modification. Fail-fast iterators checks the modCount flag when it gets the next value (i.e. using next(), remove(),forEachRemaining() method), and if it finds that the modCount has been modified after this iterator has been created, it throws ConcurrentModificationException.

ArrayList iterator is fail-fast by design. Here modCount holds the modification count and every time we use add, remove or trimToSize method, it increments. **expectedModCount** is the iterator variable that is initialized when we create iterator with same value as modCount. This explains why we don’t get exception if we use set method to replace any existing element. Basically iterator throws ConcurrentModificationException if list size is changed.

**Fail-Safe iterators**

**Fail-Safe iterators** don’t throw any exceptions if a collection is structurally modified while iterating over it. This is because, they operate on the **clone** of the collection, not on the original collection and that’s why they are called fail-safe iterators. Fail Safe Iterator makes copy of the internal data structure (object array) and iterates over the copied data structure. Any structural modification done to the iterator affects the copied collection, not original collection. So, original collection remains structurally unchanged. Iterator on CopyOnWriteArrayList, ConcurrentHashMap classes are examples of fail-safe Iterator.

**Note:** In case of **ConcurrentHashMap**, it does not operate on a separate copy although it is not fail-fast. Instead, the iterators returned by ConcurrentHashMap is **weakly consistent**. This means that this iterator can tolerate concurrent modification, traverses elements as they existed when iterator was constructed and may (but not guaranteed to) reflect modifications to the collection after the construction of the iterator.

Two issues associated with Fail Safe Iterator are :

Ø Overhead of maintaining the copied data structure i.e memory.

Ø Fail safe iterator does not guarantee that the data being read is the data currently in the original data structure. 

According to Oracle docs , fail safe iterator is ordinarily too costly, but may be more efficient than alternatives when traversal operations vastly outnumber mutations, and is useful when you cannot or don’t want to synchronize traversals. The **"snapshot" style iterator** method uses a reference to the state of the array at the point that the iterator was created. This array never changes during the lifetime of the iterator, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException. The iterator will not reflect additions, removals, or changes to the list since the iterator was created. Element-changing operations on iterators themselves (remove(), set(), and add()) are not supported. These methods throw **UnsupportedOperationException**.


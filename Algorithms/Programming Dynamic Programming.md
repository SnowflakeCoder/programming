## Dynamic Programming 

Dynamic Programming is an algorithmic paradigm that solves a complex problem by <u>breaking it into sub-problems</u> and <u>stores the results of sub-problems</u> to <u>avoid computing the same results</u> again. Following are the two main properties of a problem that suggest that the given problem can be solved using Dynamic programming.

### 1) Overlapping Subproblems:

Dynamic Programming is mainly used when **solutions of same subproblems are needed again** and again. In dynamic programming, computed solutions of subproblems are stored in a table so that these don’t have to recomputed. So Dynamic Programming is not useful when there are no common (overlapping) subproblems because there is no point storing the solutions if they are not needed again. For example, **Binary Search doesn’t have common subproblems**. But if we see the recursive program for Fibonacci Numbers, there are many subproblems which are solved again and again.

```
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

### Problems

#### Fibonacci (Memoized version)

```
/* simple Java program recursive program for Fibonacci numbers  {1, 1, 2, 3, 5 …}*/
public class FibonacciMemo {
  private static int[] cache;
  
  public static void main(String[] args){
   int n= 30;
   cache = new int[n+1];
   System.out.println("Result :: " + fibonacci(n));
  }
  
  private static int fibonacci(int n){
    if(n <= 1){
      return n;
    }
    if(cache[n] == 0){
      cache[n] = fibonacci(n-1) + fibonacci(n-2);
    }
    return cache[n]; 
  }
}

```

#### Fibonacci (Tabulated version)

```
public class FibonacciTab {
  
  public static void main(String[] args){
    int n = 30;
    int[] cache = new int[n+1];
    
    cache[0] = 0;
    if(n > 0){
      cache[1] = 1;
    }
    
    for(int index = 2; index <= n; index++)
    {
      cache[index] = cache[index-1] + cache[index-2]; 
    }
    System.out.println("Result :: " + cache[n]);
  }
}
```

#### Ugly numbers

```
Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By convention, 1 is included. Given a number n, the task is to find n’th Ugly number.

public class UgliNumber {
  
  public static void main(String args[]){
    int n = 11;
    int[] cache = new int[n+1];
    cache[1] = 1;

	int index2 = 1, index3 = 1, index5 = 1;
    int val2 = cache[index2] * 2;
    int val3 = cache[index3] * 3;
    int val5 = cache[index5] * 5;
    
    for(int index = 2; index <= n; index++){
      cache[index] =Math.min(val2,val3); 
      cache[index] =Math.min(cache[index], val5);
   
      if(cache[index] == val2){
        index2++;
        val2 = cache[index2] * 2;
      }
      if(cache[index] == val3){
        index3++;
        val3 = cache[index3] * 3;
      }
      if(cache[index] == val5){
        index5++;
        val5 = cache[index5] * 5;
      }
    } // end for loop
    
    System.out.println("Result ::" + cache[n]);
  } // end main
  
}
```

```
Given 3 numbers {1, 3, 5}, we need to tell the total number of ways we can form a number 'N'  using the sum of the given three numbers. (allowing repetitions and different arrangements). Total number of ways to form 5 is: 5 {1+1+1+1+1, 1+1+3, 1+3+1, 3+1+1, 5}

state(n) = state(n-1) + state(n-3) + state(n-5)
int solve(int n)
{ 
   // base case
   if (n < 0) 
      return 0;
   if (n == 0)  
      return 1;  
   return solve(n-1) + solve(n-3) + solve(n-5);
}   
```

## 
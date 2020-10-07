# Dynamic Programming

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





/* simple recursive program for **Fibonacci** numbers  {0, 1, 1, 2, 3, 5 …}*/

| Memoized version for nth Fibonacci Number.     public class Fibonacci  {   final int MAX = 100;   final int NIL = -1;   int lookup[] = new  int[MAX];      /* Function to initialize  NIL values in lookup table */   void _initialize()   {    for (int i = 0; i <  MAX; i++)      lookup[i] = NIL;   }      /* function for nth  Fibonacci number */   int fib(int n)   {    if (lookup[n] == NIL)    {     if (n <= 1)       lookup[n] = n;     else       lookup[n] =  fib(n-1) + fib(n-2);    }    return lookup[n];   }      public static void  main(String[] args)   {    Fibonacci f = new  Fibonacci();    int n = 40;    f._initialize();      System.out.println("Number is" + " " + f.fib(n));   }   } | Tabulated version for nth Fibonacci Number.     C/C++JavaPython  /* Java program for Tabulated version */  public class Fibonacci  {   int fib(int n)   {    int f[] = new int[n+1];    f[0] = 0;    f[1] = 1;    for (int i = 2; i <=  n; i++)       f[i] = f[i-1] +  f[i-2];    return f[n];   }      public static void  main(String[] args)   {    Fibonacci f = new  Fibonacci();    int n = 9;      System.out.println("Fibonacci number is" + " " +  f.fib(n));   }     } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**1. Problem2**

| **Ugly numbers** are numbers whose only prime factors are 2, 3 or 5. The sequence  1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By  convention, 1 is included. Given a number n, the task is to find n’th Ugly  number.     import java.lang.Math;     class UglyNumber  {    /* Function to get the  nth ugly number*/    int getNthUglyNo(int n)    {      int ugly[] = new  int[n]; // To store ugly numbers      int i2 = 0, i3 = 0,  i5 = 0;      int  next_multiple_of_2 = 2;      int  next_multiple_of_3 = 3;      int  next_multiple_of_5 = 5;      int next_ugly_no =  1;             ugly[0] = 1;       for(int i = 1; i  < n; i++)      {        next_ugly_no =  Math.min(next_multiple_of_2,                   Math.min(next_multiple_of_3,                        next_multiple_of_5));                 ugly[i] =  next_ugly_no;        if (next_ugly_no  == next_multiple_of_2)        {          i2 = i2+1;           next_multiple_of_2 = ugly[i2]*2;        }        if (next_ugly_no  == next_multiple_of_3)        {          i3 = i3+1;           next_multiple_of_3 = ugly[i3]*3;        }        if (next_ugly_no  == next_multiple_of_5)        {          i5 = i5+1;           next_multiple_of_5 = ugly[i5]*5;        }      } /*End of for loop  (i=1; i<n; i++) */      return next_ugly_no;    }   } | Given 3 numbers {1, 3, 5}, we need to tell the total number of  ways we can form a number 'N' using  the sum of the given three numbers. (allowing repetitions and different  arrangements).  Total number of ways to form 5 is : 5 {1+1+1+1+1, 1+1+3,   1+3+1, 3+1+1, 5}     state(n) = state(n-1) + state(n-3) + state(n-5)     int solve(int n)  {     // base case    if (n < 0)      return 0;    if (n == 0)      return 1;        return solve(n-1) +  solve(n-3) + solve(n-5);  } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 
# Dynamic Programming

## Fibonacci (Memoized version)

```java
/*recursive program for Fibonacci numbers  {1, 1, 2, 3, 5 …}*/
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

## Fibonacci (Tabulated version)

```java
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

## Ugly numbers

```java
/*Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10,… the ugly numbers. By convention, 1 is included. Given a number n, the task is to find n’th Ugly number.*/
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

## No of ways to Form a number 

```java
Given 3 numbers {1, 3, 5}, we need to tell the total number of ways we can form a number 'N'  using the sum of the given three numbers. (allowing repetitions and different arrangements). Total number of ways to form 5 is: 5 {1+1+1+1+1, 1+1+3, 1+3+1, 3+1+1, 5}
Solution =>	state(n) = state(n-1) + state(n-3) + state(n-5)
    
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

 

 
# Mathematical Algorithms

## a % x = b

Given two numbers a and b find all x such that a % x = b

```mathematica
Input : a = 21, b = 5
Output : 2
The answers of the Modular Equation are 8 and 16 since 21 % 8 = 21 % 16 = 5
```

**Cases**

- If **( a < b )**  => No solution possible.
- If **( a = b )**  => Infinite Solution possible. All the numbers greater than a are the answers.
- If **( a > b )** and a % x = b then => (a – b) % x == 0  and b < x
  - So the answer is <u>number of divisors of a – b which are strictly greater than b</u> which can be solved in **O(sqrt( a-b ))**. Here one corner case is when **(a-b) is perfect square** then we will add its square root two times so we have to subtract one times, if this case arises.

```java
static void modularEquation(int a, int b) 
{ 
	if (a < b) { 
		System.out.println("No solution possible "); return; 
	} 
	if (a == b) { 
		System.out.println("Infinite Solution possible "); return; 
	} 
	int count = 0; 	// count variable store the number of values possible. 
	int n = a - b; 
	int y = (int)Math.sqrt(a - b); 
	for (int i = 1; i <= y; ++i) {  // max value of i expected is sqrt(a - b).
		if (n % i == 0) { 
			if (n / i > b) 
				count++; 
			if (i > b) 
				count++; 
		} 
	} 
	// Here y is added twice in the last iteration so 1 should be decremented 
	if (y * y == n && y > b) 
		count--; 
	System.out.println(count); 
} 
```

## a % b = 0

Given an integer **n**, the task is to find two integers **a** and **b** which satisfy the below conditions:

1. **a % b = 0**
2. **a \* b > n**
3. **a / b < n**

If no pair satisfies the above conditions then print **-1**.

**Approach:** Let’s suppose `b = n` and `a / b = n – 1` which is **< n**.

```java
if(n <= 1){
	print(-1); // no pair satisfies a % b = 0 && a / b < 1. so print(-1).
}
b = n; a / b = n – 1;
a = n * (n - 1) // this also satisfy (a % b = 0)
```

## A + reverse(A) = 10^N – 1

Given an integer **N**,  count all possible **N digit numbers** such that **A + reverse(A) = 10^N – 1** where **A** is an N digit number and reverse(A) is reverse of A. **A** shouldn’t have any leading 0s.

**Approach**

- **If N is odd** then there is no number which will satisfy the given condition, because middle value A and reverse(A) are same. So 2d = 9 i.e. d = 4.5 which is wrong.

  - ```mathematica
    d1d2d3 + d3d2d1 = 999 // (if N = 3)
    so d3+d1=9 and d2+d2 =9.
    d2=4.5 //which is impossible as it is a floating point number.
    ```

- **If N is even** then 

  - ```mathematica
    d1d2d3d4 + d4d3d2d1 = 9999 // (if N = 3)
    so d4+d1=9 and d2+d3 =9.
    //if x + y = 9 then number of pairs which satisfy this condition are 10.
    (0, 9),(1, 8) .....(8, 1), (9, 0)
    Now, 1st and Nth digit cannot have the pair (0, 9) as there shouldn’t be any leading 0s in A. but for all the remaining N/2-1 pairs there can be 10 pairs. So the answer is 9*10^(N/2-1) => print 9 followed by N/2-1 of 0s.
    ```

## x*(x+1)

find total number of numbers that satisfy x*(x+1) that fall within a and b (a, b, x all integers and a<=b.

Cases

- a <= x*(x+1) <= b    then  =>  sqrt(a) <= x <= sqrt(b)







References

https://www.tutorialspoint.com/data_structures_algorithms/data_structures_basics.htm
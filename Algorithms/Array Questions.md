# Array Questions

## High and Low Partitions

Given a array of integers find the index which partitions the array to two with high numbers and low numbers. For example [5, -1, 3, 8,6] the index 3 will partition the array to [5,-1,3] and [8,6] all the numbers in the second partition are greater than first. The solution has to work in **O(n)**.



Given an unsorted array of integers. Find an element such that all the elements to its left are smaller and to its right are greater. Print -1 if no such element exists.

**Approach**

1. Create an auxiliary array ‘GE[]’. GE[] should **store the element which is greater than A[i]** and is on left side of A[i].
2. Create an another Auxliary array ‘SE[]’. SE[i] should store the element which is smaller than A[i] and is on right side of A[i].
3. Find element in array that hold condition GE[i-1] < A[i] < SE[i+1].




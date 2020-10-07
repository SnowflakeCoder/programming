package com.snowflake.array.partition;

/**
 * Simple java program to find a partition point in an array.
 * Returns an element that has all the element to its left smaller and to its right greater 
 * 
 * 
 * 1. Create an auxiliary array ‘GE[]’. GE[] should **store the element which is greater than A[i]** and is on left side of A[i].
 * 2. Create an another Auxliary array ‘SE[]’. SE[i] should store the element which is smaller than A[i] and is on right side of A[i].
 * 3. Find element in array that hold condition GE[i-1] < A[i] < SE[i+1].
 * 
 * @author arun
 *
 */
public class HighLowPartions {
	

	static int FindElement(int[] A, int n) 
	{ 
		// Create an array 'SE[]' that will store smaller element on right side. 
		int[] SE = new int[n]; 

		// Create an another array 'GE[]' that will store greatest element on left side. 
		int[] GE = new int[n]; 

		// initialize first and last index of SE[], GE[] 
		GE[0] = A[0]; 
		SE[n - 1] = A[n - 1]; 

		// store greatest element from left to right 
		for (int i = 1; i < n; i++) 
		{ 
			if (GE[i - 1] < A[i]) 
				GE[i] = A[i]; 
			else
				GE[i] = GE[i - 1]; 
		} 

		// store smallest element from right to left 
		for (int i = n - 2; i >= 0; i--) 
		{ 
			if (A[i] < SE[i + 1]) 
				SE[i] = A[i]; 
			else
				SE[i] = SE[i + 1]; 
		} 

		// Now find a number which is greater then all 
		// elements at it's left and smaller the all 
		// then elements to it's right 
		for (int j = 0; j < n; j++) 
		{ 
			if ((j == 0 && A[j] < SE[j + 1]) || 
				(j == n - 1 && A[j] > GE[j - 1]) || 
				(A[j] < SE[j + 1] && A[j] > GE[j - 1])) 
				return A[j]; 
		} 

		return -1; 
	} 

	static public void main(String[] args) 
	{ 
		int[] A = {4, 3, 2, 5, 8, 6, 7}; 
		int n = A.length; 
		System.out.println(FindElement(A, n)); 
	} 


}

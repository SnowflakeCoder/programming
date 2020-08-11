package com.snowflake.test;

//java program to get total XOR 
//of all subarray xors 

public class XORArray {
		
	// Returns XOR of all subarray xors 
	static int getTotalXorOfSubarrayXors( 
						int arr[], int N) 
	{ 
		
		// initialize result by 
		// 0 as (a xor 0 = a) 
		int res = 0; 
		
		// select the starting element 
		for (int i = 0; i < N; i++) 
		
			// select the eNding element 
			for (int j = i; j < N; j++) 
		
			// Do XOR of elements 
			// in current subarray 
			for (int k = i; k <= j; k++) {
				res = res ^ arr[k]; 
				System.out.print(arr[k] + " ^ " );
			}
	
		return res; 
	} 
	
	// Driver code 
	public static void main(String args[]) 
	{ 
		int arr[] = {3, 5, 2, 4, 6}; 
		int N = arr.length; 
		
		System.out.println( 
			getTotalXorOfSubarrayXors(arr, N)); 
	} 
} 

//This code is contributed by Sam007. 


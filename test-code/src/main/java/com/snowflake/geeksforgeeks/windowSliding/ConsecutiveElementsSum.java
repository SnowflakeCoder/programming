package com.snowflake.geeksforgeeks.windowSliding;

public class ConsecutiveElementsSum {

	/*
	 * Window Sliding Technique
	 * 
	 * Given an array of integers of size ‘n’ calculate the maximum sum of ‘k’ consecutive elements in the array.
	 * 
	 * https://www.geeksforgeeks.org/window-sliding-technique/?ref=rp
	 * 
	 */
	
	public static void main(String[] args) {
		int arr[] = {100, 200, 300, 400};
		System.out.println(maxSum(arr, 2));
	}
	
	private static int maxSum(int arr[], int num) {
		int maxSum = 0;
		int startIndex = 0;
		
		if(num > arr.length) {
			return -1;
		}
		
		for(int index = 0; index <num; index++ ) {
			maxSum += arr[index];
		}
		
		for(int index = num; index <arr.length; index++, startIndex++ ) {
			int diff = arr[index] - arr[startIndex];
			if(diff > 0) {
				maxSum += diff;
			}
		}
		
		return maxSum;
	}
}

package com.snowflake.array;

public class LongestPeak {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
		System.out.println(longestPeak(arr));
	}
	
	private static int longestPeak(int[] arr) {
		int i =0;
		int maxPeak = 0;
		while(i < arr.length-1) {

			if(arr[i] >= arr[i+1]) {
				i++;
				continue;
			}
			
			int startIndex = i;
			while(i < arr.length-1 && arr[i] < arr[i+1] ) {
				i++;
			}
			
			if( i < arr.length-1 && arr[i] > arr[i+1]) {
				while(i < arr.length-1 && arr[i] > arr[i+1]) {
					i++;
				}
				if(maxPeak < (i - startIndex + 1)) {
					maxPeak = (i - startIndex + 1);
				}
				i--;
			}
		
		}
		return maxPeak;
	}

}

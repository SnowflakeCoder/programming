package com.snowflake.geeksforgeeks.twoPointers;

public class SearchPairSumSortedArray {

	/*
	 * Given a sorted array A (sorted in ascending order), having N integers, find
	 * if there exists any pair of elements (A[i], A[j]) such that their sum is
	 * equal to X.
	 * 
	 * https://www.geeksforgeeks.org/two-pointers-technique/
	 * 
	 */

	public static void main(String[] args) {
		int A[] = {1, 2, 3};
		System.out.println(findNearestSmall(A, 0, A.length - 1, 4));
		System.out.println(isPairSum(A, 6));
	}

	private static boolean isPairSum(int arr[], int sum) {
		
		int max = findNearestSmall(arr, 0, arr.length -1 , sum);
		
		for(int i =0; i < max;) {
			if(arr[i] + arr[max] == sum) {
				System.out.println("Min :: " + i + ", Max :: " + max);
				return true;
			}
			else if(arr[i] + arr[max] < sum) {
				i++;
			}
			else {
				max --;
			}
		}
		
		return false;
	}
	
	private static int findNearestSmall(int arr[], int start, int end, int num) {
		if (start == end) {
			return start;
		}
		else {
			int mid = start + (end - start + 1)/2;
			if(arr[mid] == num) {
				return mid;
			}
			else if(arr[mid] > num) {
				return findNearestSmall(arr, start, mid, num);
			}
			return findNearestSmall(arr, mid, end, num);
		}
	}
	
}

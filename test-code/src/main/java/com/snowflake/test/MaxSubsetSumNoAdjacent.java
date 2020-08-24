package com.snowflake.test;

public class MaxSubsetSumNoAdjacent {

	public static void main(String[] args) {
		System.out.println(maxSubsetSumNoAdj(new int[] { 3, 2, 7, 10 }) + "::" + FindMaxSum(new int[] { 3, 2, 7, 10 }));
		System.out.println(
				maxSubsetSumNoAdj(new int[] { 3, 2, 5, 10, 7 }) + "::" + FindMaxSum(new int[] { 3, 2, 5, 10, 7 }));
		System.out.println(
				maxSubsetSumNoAdj(new int[] { 3, 200, 5, 10, 7 }) + "::" + FindMaxSum(new int[] { 3, 200, 5, 10, 7 }));
		System.out.println(
				maxSubsetSumNoAdj(new int[] { 3 }) + "::" + maxSubsetSumNoAdj1(new int[] { 3})+ "::" + FindMaxSum(new int[] { 3}));
	}

	private static int maxSubsetSumNoAdj(int arr[]) {
		if (arr == null || arr.length <= 0) {
			return 0;
		}

		return Math.max(maxSubsetSumNoAdj(arr, 0), maxSubsetSumNoAdj(arr, 1));
	}

	private static int maxSubsetSumNoAdj(int arr[], int index) {
		if (arr.length <= index) {
			return 0;
		}

		return arr[index] + Math.max(maxSubsetSumNoAdj(arr, index + 2), maxSubsetSumNoAdj(arr, index + 3));
	}

	/*
	 * for loop logic
	 */

	private static int maxSubsetSumNoAdj1(int arr[]) {
		if (arr == null || arr.length <= 0) {
			return 0;
		}
		int inclusive = 0;
		int exclusive = 0;

		for (int index = 0; index < arr.length; index++) {
			int newExclusive = inclusive + arr[index];
			inclusive = exclusive;
			exclusive = Math.max(newExclusive, exclusive);
		}

		return exclusive;
	}

	private static int FindMaxSum(int arr[]) {
		int incl = arr[0];
		int excl = 0;
		int excl_new;
		int i;

		for (i = 1; i < arr.length; i++) {
			/* current max excluding i */
			excl_new = (incl > excl) ? incl : excl;

			/* current max including i */
			incl = excl + arr[i];
			excl = excl_new;
		}

		/* return max of incl and excl */
		return ((incl > excl) ? incl : excl);
	}

}

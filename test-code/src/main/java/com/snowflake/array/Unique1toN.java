package com.snowflake.array;

public class Unique1toN {

	/*
	 * given an array of size N make sure it contains 1-n numbers
	 */
	public static void main(String[] args) {
		int[] arr = {2,3,1,5,4};
		System.out.println(uniqueArray(arr));
	}

	public static boolean uniqueArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i+1) {
				if (!swap(arr, i)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean swap(int[] arr, int i) {
		System.out.println("SWAP");
		if (arr[i] > arr.length) { // exceeds the arr.length
			System.out.println("exceeds the arr.length");
			return false;
		}
		int temp1 = arr[i];
		if (arr[temp1-1] == temp1) { // temp1 came twice. arr[i] & arr[temp1]
			System.out.println(temp1 + " came twice.");
			return false;
		}
		// else swap and run swap again.
		int temp2 = arr[temp1 - 1];
		arr[i] = temp2;
		arr[temp1 - 1] = temp1;
		if (arr[i] == i+1) {
			return true;
		} else {
			return swap(arr, i);
		}
	}

}

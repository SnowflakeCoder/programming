package com.snowflake.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MonotonicArray {

	public static void main(String[] args) {

		System.out.println(isMonotonic(new int[] { 0, 1, 2, 3 }));

		System.out.println(isMonotonic(new int[] { 0, 1, 2, 3, 2 }));

		System.out.println(isMonotonic(new int[] { 0, 1, 1, 2, 3 }));

		getFrequency(new int[] { 0, 1, 1, 2, 3 });
	}

	private static void getFrequency(int[] arr) {
		Map<Integer, Integer> countMap = new HashMap<>();

		for (int index = 0; index < arr.length; index++) {
			Optional<Integer> count = Optional.ofNullable(countMap.get(arr[index]));

			if (count.isPresent()) {
				countMap.put(arr[index], 1);
			} else {
				countMap.put(arr[index], count.get() + 1);
			}
		}

		System.out.println(countMap);

	}

	private static boolean isMonotonic(int[] arr) {
		boolean ascending = true, descending = true;

		for (int index = 1; index < arr.length; index++) {
			if (arr[index] < arr[index - 1]) {
				ascending = false;
			}
			if (arr[index] > arr[index - 1]) {
				descending = false;
			}

			if (!(ascending || descending)) {
				return false;
			}

		}

		return (ascending || descending);
	}

	private static void bubbleSort(int arr[]) {
		int n = arr.length;
		boolean swapped = false;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap temp and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			// IF no two elements were swapped by inner loop, then break
			if (swapped == false)
				break;
		}
	}

}

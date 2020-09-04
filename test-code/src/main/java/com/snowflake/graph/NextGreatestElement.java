package com.snowflake.graph;

import java.util.Stack;

public class NextGreatestElement {

	public static void main(String[] args) {

		solution(new int[] { 3, 5, 2, 10 });
		solution3(new int[] { 3, 5, 2, 10 });

	}

	private static void solution2(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		stack.push(arr[0]);

		for (int index = 0; index < arr.length; index++) {

		}

	}

	private static void solution3(int arr[]) {
		Stack<Integer> s = new Stack<Integer>();

		int arr1[] = new int[arr.length];

		// iterating from n-1 to 0
		for (int i = arr.length - 1; i >= 0; i--) {
			/*
			 * We will pop till we get the greater element on top or stack gets empty
			 */
			while (!s.isEmpty() && s.peek() <= arr[i])
				s.pop();

			/*
			 * if stack gots empty means there is no element on right which is greater than
			 * the current element. if not empty then the next greater element is on top of
			 * stack
			 */
			if (s.empty())
				arr1[i] = -1;
			else
				arr1[i] = s.peek();

			s.push(arr[i]);
		}

		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i] + " ---> " + arr1[i]);

	}

	private static void solution(int[] arr) {

		Integer[] output = new Integer[arr.length];

		for (int index = 0; index < arr.length; index++) {
			for (int index2 = index + 1; index2 < arr.length; index2++) {
				if (arr[index] < arr[index2]) {
					if (output[index] == null || output[index].intValue() < arr[index2]) {
						output[index] = arr[index2];
					}
				}
			}
		}

		for (int index = 0; index < arr.length; index++) {
			System.out.println(arr[index] + " :: " + (output[index] == null ? -1 : output[index].intValue()));
		}

	}

}

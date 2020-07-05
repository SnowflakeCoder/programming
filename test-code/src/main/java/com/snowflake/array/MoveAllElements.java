package com.snowflake.array;

import java.util.Arrays;

public class MoveAllElements {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(moveElements(new int[] {2, 1, 2, 2, 2, 3, 4, 2}, 2)));
	}
	
	private static int[] moveElements(int[] arr, int value) {
		
		int left=0, right = arr.length-1;
		while(true) {
			while(arr[left] != value && left < right) {
				left++;
			}
			
			while(arr[right] == value && left < right) {
				right--;
			}
			
			if(left >= right) {
				break;
			}
			else {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
		
		return arr;
	}

}

package com.snowflake.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeNumberSum {

	public static void main(String[] args) {
		threeNumberSum(new int[]{12,3,1,2,-6,5,-8,6}, 0);
		System.out.println("Second");
		threeNumberSumV2(new int[]{12,3,1,2,-6,5,-8,6}, 0);
		System.out.println("END");
	}
	
	
	
	private static void threeNumberSum(int[] numbers, int sum) {
		Arrays.sort(numbers);
		for(int i = 0; i < numbers.length -2; i++) {
			List<int[]> twoNumList = twoNumberSum(numbers, sum-numbers[i], i+ 1);
			if(twoNumList != null && !twoNumList.isEmpty()) {
				for(int[] twoNum : twoNumList) {
					System.out.println(numbers[i] + "," + twoNum[0] + "," + twoNum[1]);
				}
			}
		}
	}
	
	private static List<int[]> twoNumberSum(int[] numbers, int sum, int startIndex) {
		List<int[]> twoNumList = new ArrayList<>();
		int left = startIndex, right = numbers.length -1;
		while(left < right) {
			if(numbers[left] + numbers[right] == sum) {
				twoNumList.add(new int[] {numbers[left], numbers[right] });
				left++;
			} else if(numbers[left] + numbers[right] > sum) {
				right--;
			}
			else {
				left++;
			}
		}
		return twoNumList;
	}


	
	private static void threeNumberSumV2(int[] numbers, int sum) {
		for(int i = 0; i < numbers.length -2; i++) {
			List<int[]> twoNumList = twoNumberSumV2(numbers, sum-numbers[i], i+ 1);
			if(twoNumList != null && !twoNumList.isEmpty()) {
				for(int[] twoNum : twoNumList) {
					System.out.println(numbers[i] + "," + twoNum[0] + "," + twoNum[1]);
				}
			}
		}
	}
	
	private static List<int[]> twoNumberSumV2(int[] numbers, int sum, int startIndex) {
		Map<Integer, Boolean> map = new HashMap<>();
		List<int[]> twoNumList = new ArrayList<>();
		for(int i = startIndex; i < numbers.length; i++) {
			if(map.remove(numbers[i]) != null) {
				twoNumList.add(new int[] {numbers[i], sum-numbers[i]});
			}
			else {
				map.put(sum-numbers[i], true);
			}
		}
		return twoNumList;
	}
}

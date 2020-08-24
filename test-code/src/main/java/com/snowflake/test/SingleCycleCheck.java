package com.snowflake.test;

public class SingleCycleCheck {

	public static void main(String[] args) {
		System.out.println(singleCycleCheck1(new int[] {2,3,1,-4,-4,2}));
		System.out.println(singleCycleCheck1(new int[] {2,3,1,-4,-3,2}));
	}
	
	private static boolean singleCycleCheck(int[] arr) {
		boolean[] visit = new boolean[arr.length];
		
		int currentIndex = 0;
		for(int index =0; index < arr.length; index++) {
			if(!visit[currentIndex]) {
				visit[currentIndex] = true;
				currentIndex += arr[currentIndex];
				
				while(currentIndex < 0) {
					currentIndex += arr.length;
				}
				while(currentIndex >= arr.length) {
					currentIndex -= arr.length;
				}
			}
			else {
				return false;
			}
		}
		
		return(currentIndex == 0);
	}
	
	private static boolean singleCycleCheck1(int[] arr) {
		int currentIndex = 0;
		for(int index =0; index < arr.length; index++) {
			if(index >0 && currentIndex == 0) {
				return false;
			}
			currentIndex = (currentIndex + arr[currentIndex]) % arr.length;
			if(currentIndex < 0) {
				currentIndex += arr.length;
			}
		}
		return(currentIndex == 0);
	}

}

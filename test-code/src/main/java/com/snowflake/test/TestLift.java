package com.snowflake.test;

public class TestLift {
	public static void main(String[] args) {
		int arr[] = {3, 2, 4, 6, 5};
		sort(arr);
		getMinTime(3, 2, arr);
	}
	
	
	private static void getMinTime(int N, int k, int a[]) {
		for(int index1 = 0; index1 < a.length -1; index1++) {
			if(a[index1] < a[index1+1]) {
				int temp = a[index1+1];
				a[index1+1] = a[index1];
				int index2 = index1-1;
				
				for(;index2 >= 0; index2--) {
					if(a[index2] < temp) {
						a[index2 + 1] = a[index2];
					}
					else {
						break;
					}
				}
				a[index2 + 1] = temp;
			}
		}
		
		int total = 0;
		for(int index1 = 0; index1 < a.length; index1 = index1+k) {
			total += a[index1];
		}
		
		System.out.println(total);
	}
	

	private static void sort(int a[]) {
		for(int index1 = 0; index1 < a.length -1; index1++) {
			if(a[index1] < a[index1+1]) {
				int temp = a[index1+1];
				a[index1+1] = a[index1];
				int index2 = index1-1;
				
				for(;index2 >= 0; index2--) {
					if(a[index2] < temp) {
						a[index2 + 1] = a[index2];
					}
					else {
						break;
					}
				}
				a[index2 + 1] = temp;
			}
		}
	}

	
}

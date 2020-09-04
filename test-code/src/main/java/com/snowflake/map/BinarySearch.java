package com.snowflake.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		BinarySearch ob = new BinarySearch(); 
        int arr[] = { 2, 3, 4, 10, 40 }; 
        int n = arr.length; 
        int x = 5; 
        
        int result = ob.binarySearch(arr, 0, n - 1, x); 
        
        if (result == -1) 
            System.out.println("Element not present :: "+ result); 
        else
            System.out.println("Element found at index " + result); 
        
        List<Integer> list = getList(arr);
        
        int result1 = Collections.binarySearch(list, x, (a, b) -> Integer.compare(a, b)); 
        
        if (result1 == -1) 
            System.out.println("Element not present :: "+ result1); 
        else
            System.out.println("Element found at index " + result1); 
	}
	
	private static List<Integer> getList(int[] arr){
		List<Integer> list = new ArrayList<>();
		for(int a : arr) {
			list.add(a);
		}
		return list;
	}
	
	private int binarySearch(int[] arr, int start, int end, int val) {
		if(start > end) {
			return -(start)-1;
		}
		
		int mid = (start + end) / 2;
		
		if(arr[mid] == val) {
			return mid;
		}
		
		if(arr[mid] > val) {
			return binarySearch(arr, start, mid-1, val);
		}
		return binarySearch(arr, mid+1, end, val);
	}

}

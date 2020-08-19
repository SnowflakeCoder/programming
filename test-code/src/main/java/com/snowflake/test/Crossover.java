package com.snowflake.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crossover {

	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(feature(new int[] {1, 2, 3}, new int[] {3, 4}, new int[][] {{1, 5},{0, 0, 1},{1, 5}})));
		
		System.out.println(Arrays.toString(feature(new int[] {1, 2, 2}, new int[] {2, 3}, new int[][] {{1, 4},{0, 0, 3},{1, 5}})));
		

	}
	
	private static int[] feature(int[] a, int[] b, int[][] query) {
		List<Integer> sumArr = new ArrayList<>();
		
		for(int index = 0; index < query.length; index++) {
			if(query[index].length == 2) {
				sumArr.add(numberOfPair(a, b, query[index][1]));
			}
			else if(query[index].length == 3) {
				b[query[index][1]]=query[index][2];
			}
		}
		
		return sumArr.stream().mapToInt(i->i).toArray();
	}
	
	
	private static int numberOfPair(int[] a, int[] b, int sum) {
		int count = 0;
		
		for(int indexa =0; indexa < a.length; indexa++) {
			for(int indexb =0; indexb < b.length; indexb++) {
				if(a[indexa] + b[indexb] == sum) {
					count++;
				}
			}
		}
		
		return count;
	}
	

}

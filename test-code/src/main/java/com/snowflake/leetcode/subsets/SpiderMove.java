package com.snowflake.leetcode.subsets;

public class SpiderMove {

	public static void main(String[] args) {
		System.out.println(spiderMove(-3, 0));
	}
	
	private static int spiderMove(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		
		int[] n = new int[x + 1];
		for(int i = 0; i < x+1; i++) {
			n[i] = 1;
		}
		
		for(int i =0; i < y; i++) {
			for(int j = 1; j < x+1; j++) {
				n[j] = n[j-1] + n[j];
			}
		}
		
		return 0;
	}

}

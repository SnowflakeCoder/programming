package com.snowflake.test;

public class MathematicalAlgorithm {

	public static void main(String[] args) {
		System.out.println(algorithm1(101, 200));
	}
	
	/**
	 * find total number of numbers that satisfy x*(x+1) that fall within a and b (a, b, x all integers and a<=b.
	 */
	
	public static int algorithm1(int a, int b) {
		
		int start = (int) Math.sqrt(a);
		int end = (int) Math.sqrt(b);
		int count = 0;
		
		for(int startX = start; startX < end; startX++) {
			int value = startX * (startX + 1);
			if(value > a && value < b) {
				System.out.println("Value :"+ startX);
				count++;
			}
		}
		
		return count;
	}
	
	

}

package com.snowflake.test;

public class TestCombinationLock {

	public static void main(String[] args) {
		
		System.out.println(getNumberOfRotations(5, 3));
		System.out.println(getNumberOfRotations(5, 1));
		System.out.println(getNumberOfRotations(9, 2));
		
		System.out.println(numberOfTry("123", "725"));
		
		
	}
	
	private static int numberOfTry(String input, String unlock) {
		int rotation = 0;

		   for (int i = 0; i < input.length(); i++) {

		       int inputDigit = input.charAt(i);
		       int unlockDigit = unlock.charAt(i);
		       
		       int diff = Math.abs(inputDigit - unlockDigit);
		       rotation += Math.min(diff, 10 - diff);
		      
		   }
		   return rotation;
		
	}
	
	
	
	
	private static int getNumberOfRotations(int pos1, int pos2) {
		
		if(pos1 == pos2) {
			return 0;
		}
		
		int min = Math.min(pos1, pos2);
		int max = Math.max(pos1, pos2);
		
		
		int diff1 = max - min;
		int diff2 = min + 10 - max;
		
		return (Math.min(diff1, diff2));
	}

}

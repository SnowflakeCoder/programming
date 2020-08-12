package com.snowflake.test;

import java.math.BigDecimal;
import java.util.Arrays;

public class Toptal {

	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(getChange(3.14, 1.99))); // should return [0,1,1,0,0,1]
		System.out.println(Arrays.toString(getChange(4, 3.14))); // should return [1,0,1,1,1,0]
		System.out.println(Arrays.toString(getChange(0.45, 0.34))); // should return [1,0,1,0,0,0]
		
		System.out.println(Arrays.toString(getChange(5, 0.99)));
	}
	
	private static int[] denominations = {1, 5, 10, 25, 50, 100};
	
	private static int[] getChange(double moneyInserted, double paid) {
		
		BigDecimal returnAmountBD = BigDecimal.valueOf(moneyInserted).subtract(BigDecimal.valueOf(paid));
		
		int returnAmount = returnAmountBD.multiply(BigDecimal.valueOf(100)).intValue();
		
		int[] returnArray = new int[6];
		
		
		int maxIndex = returnArray.length-1;
		int currentIndex = denominations.length -1;
		int currentValue = 0;
		
		while(returnAmount > 0) {
			if(returnAmount >= denominations[currentIndex]) {
				currentValue++;
				returnAmount = returnAmount - denominations[currentIndex];
			}
			else {
				if(currentValue > 0) {
					returnArray[maxIndex] = currentValue;
					currentValue = 0;
					maxIndex --;
					currentIndex--;
				}
				else {
					maxIndex --;
					currentIndex--;
				}
			}
		}
		if(currentValue > 0) {
			returnArray[maxIndex] = currentValue;
			currentValue = 0;
			maxIndex --;
			currentIndex--;
		}
		
		return returnArray;
	}

}

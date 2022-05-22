package com.snowflake.test;

public class Zalando2 {

	/**
	 * 
	 * 100 => 3 (4/2 =2, 2/2 = 1, 1-1 = 0)
	 * 
	 * 01100 => 5 (12/2 = 6, 6/2 = 3, 3-1 = 2, 2/2 = 1, 1-1 = 0)
	 * 
	 * Number of division = (StringLength - 1 - (first index of 1))
	 * 
	 * Number of subtraction = number of 1 in the String.
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println(solution("01100"));
	}
	
	
	public static int solution(String S) {
        // write your code in Java SE 8
        
        int divIndex = S.indexOf('1');
        
        if(divIndex == -1) {
            return 0; //handle 000 
        }
        int numberOfDivision = S.length() - divIndex - 1;
        
        int subIndex = (int) S.chars().filter(ch -> ch == '1').count();
        
        return numberOfDivision + subIndex;
    }
}

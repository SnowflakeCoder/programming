package com.snowflake.test;

public class Zalando1 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {4, -1, 0, 3}, new int[] {-2, 5, 0, 3}));
		System.out.println(solution(new int[] {2, -2, -3, 3}, new int[] {0, 0, 4, -4}));
		System.out.println(solution(new int[] {4, -1, 0, 3}, new int[] {-2, 6, 0, 4}));
		System.out.println(solution(new int[] {3, 2, 6}, new int[] {4, 1, 6}));
		System.out.println(solution(new int[] {1, 4, 2, -2, 5}, new int[] {7, -2, -2, 2, 5}));
		
	}
	
	private static int solution(int[] A, int[] B) {
		int[] SumA = new int[A.length + 1];
		int[] SumB = new int[B.length + 1];
		
		for(int i =1; i <= A.length; i++) {
			SumA[i] = SumA[i-1] + A[i-1];
		}
		
		for(int i =1; i <= B.length; i++) {
			SumB[i] = SumB[i-1] + B[i-1];
		}
		
		int count =0;
		
		for(int i =1; i < A.length; i++) {
			if(SumA[i] == SumB[i] && SumA[SumA.length-1] == 2 * SumA[i] && SumB[SumB.length-1] == 2 * SumB[i]) {
				System.out.println("Value ::"+ i + ", sum::" + SumA[i]);
				count++;
			}
		}
		return count;
	}

}

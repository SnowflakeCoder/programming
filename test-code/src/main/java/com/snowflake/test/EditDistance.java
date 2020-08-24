package com.snowflake.test;

import java.util.Arrays;

public class EditDistance {

	public static void main(String[] args) {
		
//		System.out.println(minDistance("horse", "ros") + " :: " +minDistance2("horse", "ros"));
//		System.out.println(minDistance("h", "") + " :: " +minDistance2("h", ""));
		System.out.println(minDistance("plasma","altruism") + " :: " +minDistance2("plasma","altruism"));
//		System.out.println(minDistance("dinitrophenylhydrazine", "benzalphenylhydrazone") + " :: " +minDistance2("dinitrophenylhydrazine", "benzalphenylhydrazone"));
	}

	public static int minDistance2(String word1, String word2) {
		char[] str1, str2 =null;
		int[] firstArr, secondArr = null;
		
		if(word1.length() > word2.length()) {
			str1 = word1.toCharArray();
			str2 = word2.toCharArray();
		}
		else {
			str2 = word1.toCharArray();
			str1 = word2.toCharArray();
		}
		
		firstArr = new int[str2.length+1];
		secondArr = new int[str2.length+1];
		
		for (int index2 = 1; index2 <= str2.length; index2++) {
			firstArr[index2] = 1 + firstArr[index2 - 1];
		}
		
		for (int index1 = 1; index1 <= str1.length; index1++) {
			secondArr[0] = firstArr[0] + 1;
			for (int index2 = 1; index2 <= str2.length; index2++) {
				if (index1 == 0 && index2 == 0) {
					continue;
				} else {
					if (str1[index1 - 1] == str2[index2 - 1]) {
						secondArr[index2] = firstArr[index2 - 1];
					} else {
						int min = Math.min(firstArr[index2], secondArr[index2 - 1]);
						secondArr[index2] = 1 + Math.min(min, firstArr[index2 - 1]);
					}
				} 
			}
			int[] temp = firstArr;
			firstArr = secondArr;
			secondArr = temp;
		}
		return firstArr[firstArr.length-1];
	}
	
	public static int minDistance(String word1, String word2) {
		char[] str1 =null;
		char[] str2 = null;
		int[] firstArr, secondArr = null;
		
		if(word1.length() > word2.length()) {
			str1 = word1.toCharArray();
			str2 = word2.toCharArray();
		}
		else {
			str2 = word1.toCharArray();
			str1 = word2.toCharArray();
		}
		
		firstArr = new int[str2.length+1];
		secondArr = new int[str2.length+1];
		
		for (int index1 = 0; index1 <= str1.length; index1++) {
			for (int index2 = 0; index2 <= str2.length; index2++) {
				if (index1 == 0 && index2 == 0) {
					continue;
				} else if (index1 > 0 && index2 > 0) {
					if (str1[index1 - 1] == str2[index2 - 1]) {
						secondArr[index2] = firstArr[index2 - 1];
					} else {
						int min = Math.min(firstArr[index2], secondArr[index2 - 1]);
						secondArr[index2] = 1 + Math.min(min, firstArr[index2 - 1]);
					}
				} else if (index1 == 0) {
					secondArr[index2] = 1 + secondArr[index2 - 1];
				} else {
					secondArr[index2] = 1 + firstArr[index2];
				}
			}
			int[] temp = firstArr;
			firstArr = secondArr;
			secondArr = temp;
			System.out.println(Arrays.toString(firstArr));
		}
		return firstArr[firstArr.length-1];
		
	}
	
	public static int minDistanceDP(char[] str1, char[] str2, int size1, int size2) {
		if (size1 == 0 && size2 == 0) {
			return 0;
		} else if (size1 > 0 && size2 > 0) {
			if (str1[size1 - 1] == str2[size2 - 1]) {
				return minDistance(str1, str2, size1 - 1, size2 - 1);
			} else {
				int min = Math.min(minDistance(str1, str2, size1 - 1, size2),
						minDistance(str1, str2, size1, size2 - 1));
				return 1 + Math.min(min, minDistance(str1, str2, size1 - 1, size2 - 1));
			}
		} else if (size1 == 0) {
			return  1 + minDistance(str1, str2, size1, size2 - 1);
		} else {
			return 1 + minDistance(str1, str2, size1 - 1, size2);
		}
	}

	public static int minDistance(char[] str1, char[] str2, int size1, int size2) {
		if (size1 == 0 && size2 == 0) {
			return 0;
		} else if (size1 > 0 && size2 > 0) {
			if (str1[size1 - 1] == str2[size2 - 1]) {
				return minDistance(str1, str2, size1 - 1, size2 - 1);
			} else {
				int min = Math.min(minDistance(str1, str2, size1 - 1, size2),
						minDistance(str1, str2, size1, size2 - 1));
				return 1 + Math.min(min, minDistance(str1, str2, size1 - 1, size2 - 1));
			}
		} else if (size1 == 0) {
			return  1 + minDistance(str1, str2, size1, size2 - 1);
		} else {
			return 1 + minDistance(str1, str2, size1 - 1, size2);
		}
	}

	public static int minDistanceByMtrx(String word1, String word2) {
		// create the matrix
		int[][] mtrx = new int[word1.length() + 1][];

		for (int index = 0; index <= word1.length(); index++) {
			mtrx[index] = new int[word2.length() + 1];
		}
		char[] str1 = word1.toCharArray();
		char[] str2 = word2.toCharArray();

		for (int index1 = 0; index1 <= word1.length(); index1++) {
			for (int index2 = 0; index2 <= word2.length(); index2++) {
				if (index1 == 0 && index2 == 0) {
					continue;
				} else if (index1 > 0 && index2 > 0) {
					if (str1[index1 - 1] == str2[index2 - 1]) {
						mtrx[index1][index2] = mtrx[index1 - 1][index2 - 1];
					} else {
						int min = Math.min(mtrx[index1 - 1][index2], mtrx[index1][index2 - 1]);
						mtrx[index1][index2] = 1 + Math.min(min, mtrx[index1 - 1][index2 - 1]);
					}
				} else if (index1 == 0) {
					mtrx[index1][index2] = 1 + mtrx[index1][index2 - 1];
				} else {
					mtrx[index1][index2] = 1 + mtrx[index1 - 1][index2];

				}
			}
		}
		return mtrx[word1.length()][word2.length()];
	}

}

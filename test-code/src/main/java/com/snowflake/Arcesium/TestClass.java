package com.snowflake.Arcesium;


public class TestClass {
	
	
	public static void main(String[] args) {
		int M[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
		
		int M1[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
		
//		System.out.println("Number of islands is: " + countIslands(M));
	}
	
	
	private static final int VISITED_COLOR = Integer.MIN_VALUE;
	private int countCountries(int[][] A) {
	       int numberOfCountries = 0;
	       for (int x = 0; x < A.length; x++) {
	           for (int y = 0; y < A[x].length; y++) {
	               int countryColor = A[x][y];
	               if (countryColor != VISITED_COLOR) {
	                   numberOfCountries++;
	                   countCountries(A, x, y, countryColor, VISITED_COLOR);
	               }
	           }
	       }
	       return numberOfCountries;
	   }

	   private void countCountries(int[][] map, int x, int y, int colorToReplace, int colorToUse) {
	       if (x < 0 || x >= map.length) return;
	       if (y < 0 || y >= map[x].length) return;
	       int tileColor = map[x][y];
	       if (tileColor == colorToUse) return;
	       if (tileColor != colorToReplace) return;
	       map[x][y] = colorToUse;
	       countCountries(map, x, y - 1, colorToReplace, colorToUse); 
	       countCountries(map, x - 1, y, colorToReplace, colorToUse); 
	       countCountries(map, x + 1, y, colorToReplace, colorToUse); 
	       countCountries(map, x, y + 1, colorToReplace, colorToUse); 
	   }



	
//	private static int countIslands(int[][] matrix) {
//		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
//		int count = 0;
//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				if(countIslands(matrix, visited, i, j)) {
//					count++;
//				}
//			}
//		}
//		return count;
//	}
//
//	private static boolean countIslands(int[][] matrix, boolean[][] visited, int i, int j) {
//		if(visited[i][j]) {
//			return false;
//		}
//		visited[i][j] = true;
//		
//		if(matrix[i][j] == 1) {
//			if(i > 0) {
//				countIslands(matrix, visited, i-1, j);
//			}
//			if(j > 0) {
//				countIslands(matrix, visited, i, j-1);
//			}
//			if(i+1 < matrix.length) {
//				countIslands(matrix, visited, i+1, j);
//			}
//			if(j+1 < matrix[i].length) {
//				countIslands(matrix, visited, i, j+1);
//			}
//			return true;
//		}
//		return false;
//	}
	
	
	
	
	
//	public static void main(String[] args) {
//		System.out.println(solution(new int[] {3,2,3,2,3}));
//		System.out.println(solution(new int[] {7, 4, -2, 4, -2, -9}));
//		System.out.println(solution(new int[] {7, -5, -5, -5, 7, -1, 7}));
//	}
//	
//	
//	public static int solution(int[] A) {
//		if(A.length <3) {
//			return A.length;
//		}
//		
//		int maxCount = 2, count = 2;
//		int oddValue = A[0];
//		int evenValue = A[1];
//		
//		for(int i = 2; i < A.length; i++) {
//			
//			if(i % 2 == 0) {
//				if(A[i] == oddValue) {
//					count++;
//				}
//				else {
//					oddValue = A[i];
//					maxCount = Math.max(count, maxCount);
//					count = 2;
//				}
//			}
//			else {
//				if(A[i] == evenValue) {
//					count++;
//				}
//				else {
//					evenValue = A[i];
//					maxCount = Math.max(count, maxCount);
//					count = 2;
//				}
//			}
//		}
//		maxCount = Math.max(count, maxCount);
//		return maxCount;
//	}
//	
//	
		
	
//	public static void main(String[] args) {
//		System.out.println(solution(new int[] {1, 2, 3 , 5, 6, 7, 8, 9}));
//		System.out.println(solution(new int[] {1, 2, 3 , 10, 11, 15}));
//		System.out.println(solution(new int[] {5, 4, 2, 1}));
//	}
//	
//	
//	public static int solution(int[] A) {
//		int count =1, maxCount = 1;
//		
//		for(int i=0; i<A.length-1; i++) {
//			if(Math.abs(A[i] - A[i+1]) == 1) {
//				count++;
//			}else if(maxCount < count){
//				maxCount = count;
//				count =1;
//			}
//		}
//		
//		if(maxCount < count){
//			maxCount = count;
//		}
//		
//		return maxCount;
//	}
	
	
//	public static void main(String[] args) {
//		
//		System.out.println(compress("ABBBCCDDCCC"));
//		
////		remove("ABBBCCDDCCC", 2);
//		
//		
//		System.out.println(compress("ABCDDDEFG"));
//		String finalString = compress("ABBBCCDDCCC");
//
//		
//	}
//	
//	
//	//AAABXAAA
//	
//	private static void remove(String str, int k) {
////		List<String> stringList = new ArrayList<String>();
//		for(int i=0, j=k; j < str.length(); j++,i++) {
//			if(i==0 || j == str.length()-1) {
//				System.out.println(str.substring(i, i+k+1));
//			}
//			else {
//				System.out.println(str.substring(i-1, i+k+1));
//			}
//		}
//		
//		
//	}
//	
//	private static String compress(String str) {
//	   StringBuilder result = new StringBuilder();
//	   int i = 0;
//	   int count = 0;
//	   while (i < str.length() - 1) {
//	       count++;
//	       if (str.charAt(i) != str.charAt(i + 1)) {
//	           if (count > 1) {
//	               result.append(count).append(str.charAt(i));
//	           } else {
//	               result.append(str.charAt(i));
//	           }
//	           count = 0;
//	       }
//	       i++;
//	   }
//	   if (count > 1) {
//           result.append(count).append(str.charAt(i));
//       } else {
//           result.append(str.charAt(i));
//       }
//	   return result.toString();
//	}

	
	
	
//	
//
//	public static void main(String[] args) {
////		System.out.println(solution("babaa"));
//		System.out.println(solution("ababa"));
////		System.out.println(solution("aba"));
//		System.out.println(solution("bbbb"));
//		System.out.println(solution("bbbbb"));
//	}
//	
//	public static int solution(String S) {
//        // write your code in Java SE 8
//        int aSize = 0;
//        for(int i=0; i< S.length(); i++){
//            if(S.charAt(i) == 'a'){
//                aSize++;
//            }
//        }
//        
//        if(aSize == 0){
//            if(S.length() < 3){
//                return 0;
//            }
//            if(S.length() == 3){
//                return 1;
//            }
//            else{
//                int count = S.length() -3;    
//                return 3 * (count);
//            }
//        }
//        
//        if(aSize % 3 != 0){
//            return 0;
//        }
//        
//        aSize = aSize/3;
//        
//        int count = 1;
//        int elementCount = 1;
//        int currentASize = aSize;
//        for(int i=0; i< S.length(); i++){
//            
//            if(currentASize == 0){
//                if(S.charAt(i) == 'a'){
//                	currentASize = aSize;
//                	count = count * elementCount;
//                	elementCount = 1;
//                    currentASize--;    
//                }
//                else {
//                	elementCount++;
//                }
//            }
//            else if(S.charAt(i) == 'a'){
//                currentASize--;    
//            }
//        }
//        
//        return count;
//        
//    }
//		
	
	
	
	
	
	
//	
//
//	public static void main(String[] args) {
//		int A[] = {2, 1, 1, 0, 1};
//		System.out.println(solution(3, 2, A));
//	}
//	
//	public static String solution(int U, int L, int[] C) {
//		StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
//		
//		int diff = U - L, sum = 0;
//		for(int i = 0; i < C.length; i++) {
//			sum += C[i];
//			if(C[i] % 2 == 0) {
//				sb1.append(C[i]/2);
//				sb2.append(C[i]/2);
//			}
//			else {
//				if(diff >= 0) {
//					sb1.append(1);
//					sb2.append(0);
//					diff --;
//				}
//				else {
//					sb1.append(0);
//					sb2.append(1);
//					diff++;
//				}
//			}
//		}
//		
//		if(diff != 0 || U +L != sum ) {
//			return "IMPOSSIBLE";
//		}
//		
//		return sb1.toString() + "," +sb2.toString();
//		
//	}
//	
//	
//	
	

	
	
	
//	
//	public static void main(String[] args) {
//		
//		int[] A = {2, -2, -3, 3};
//		int[] B = {0, 0, 4,-4};
//		
////		int[] A = {4, -1, 0, 3};
////		int[] B = {-2, 5, 0, 3};
//		
//		System.out.println("Ans" +solution(A, B));
//		
//	}
//	
//	private static int solution(int[] A, int[] B) {
//		int size = A.length;
//		
//		int[] sumA = new int[size + 1];
//		int[] sumB = new int[size + 1];
//		
//		for(int i = 1; i <= size; i++ ) {
//			sumA[i] = sumA[i-1] + A[i-1];
//			sumB[i] = sumB[i-1] + B[i-1];
//		}
//		
//		int count = 0;
//		for(int i = 1; i < size; i++) {
//			
//			if((sumA[i] == sumB[i]) 
//					&& (sumA[i] == sumA[size] - sumA[i]) 
//					&& (sumB[i] == sumB[size] - sumB[i])){
//				count++;
//			}
//		}
//		
//		return count;
//		
//	}
//	
	
	
	
	
	
	
	
	
	
	
//
//	public static void main(String[] args) {
//		int n= 36;
//		int sum = getSum(n);
//		while(n < Integer.MAX_VALUE) {
//			n++;
//			if(getSum(n) == sum) {
//				System.out.println(n);
//				break;
//			}
//		}
//		
//	}
//	
//	private static int getSum(int n) {
//		int sum = 0;
//		while(n > 0) {
//			sum += n % 10;
//			n /= 10;
//		}
//		return sum;
//	}

}




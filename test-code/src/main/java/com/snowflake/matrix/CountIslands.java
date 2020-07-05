package com.snowflake.matrix;

public class CountIslands {

	public static void main(String[] args) {
		int M[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
		System.out.println("Number of islands is: " + countIslands(M));
	}

	private static int countIslands(int[][] matrix) {
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(countIslands(matrix, visited, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean countIslands(int[][] matrix, boolean[][] visited, int i, int j) {
		if(visited[i][j]) {
			return false;
		}
		visited[i][j] = true;
		
		if(matrix[i][j] == 1) {
			if(i > 0) {
				countIslands(matrix, visited, i-1, j);
			}
			if(j > 0) {
				countIslands(matrix, visited, i, j-1);
			}
			if(i+1 < matrix.length) {
				countIslands(matrix, visited, i+1, j);
			}
			if(j+1 < matrix[i].length) {
				countIslands(matrix, visited, i, j+1);
			}
			return true;
		}
		return false;
	}

}

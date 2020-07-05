package com.snowflake.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverse {

	public static void main(String[] args) {

		int arr[][] = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

		spiralTraverse(arr);
		System.out.println("End");
		
	}
	
	public static void spiralTraverse(int arr[][]) {
		List<Integer> result = new ArrayList<>(arr.length*arr[0].length);
		int startRow = 0, endRow = arr.length -1, startCol = 0, endCol = arr[0].length-1;
		while(startRow < endRow && startCol < endCol){
		    for(int i = startCol; i < endCol; i++){
		        result.add(arr[startRow][i]); // adding first Row from the perimeter.
		    }
		    for(int i = startRow; i < endRow; i++){
		        result.add(arr[i][endCol]); // adding last column from the perimeter.
		    }
		    for(int i = endCol; i > startCol; i--){
		        result.add(arr[endRow][i]); // adding last row in reverse order
		    }
		    for(int i = endRow; i > startRow; i--){
		        result.add(arr[i][startCol]); // adding first column in reverse order
		    }
		    startRow++; startCol++; endRow--; endCol--;
		}
		System.out.println(result);
	}
	
}

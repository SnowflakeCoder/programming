package com.snowflake.test;

public class DeliveryHero {

	public static void main(String[] args) throws Exception {
		System.out.println(maxSum(new int[]{1, 2, 1, 4, 5, 1, 0}, 2));
	}
	
	private static int maxSum(int[] arr, int N) throws Exception{
		if(arr.length < N) {
			throw new Exception("Invalid partition size");
		}
		int sum =0;
		
		for(int index = 0; index < N; index++) {
			sum += arr[index];
		}
		
		int maxSum = sum;
		
		for(int index = N, start =0; index < arr.length; index++, start++) {
			sum += (arr[index] - arr[start]);
			if(maxSum < sum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}
	
	private static int maxSum1(int[] a, int k) throws Exception{
		int left = 0;
        int right = 1;
        int end = a.length;
        int max = 0;
        int sum = 0;
        while (right <= end) {
            sum += a[right - 1];
            if ((right - left) < k) {
                right++;
            } else {
                max = Math.max(max, sum);
                sum -= a[left];
                left++;
                right++;
            }
        }
        return max;
	}
	
}

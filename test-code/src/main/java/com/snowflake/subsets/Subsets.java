package com.snowflake.subsets;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		(new Solution3()).subsets(nums);
		System.out.println((new Solution1()).subsets(nums));
	}

	static class Solution1 {
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> subsets = new ArrayList<>();
			subsets.add(new ArrayList<>());
			for (int i = 0; i < nums.length; i++) {
				int size = subsets.size();
				for (int j = 0; j < size; j++) {
					List<Integer> subset = new ArrayList<>(subsets.get(j));
					subset.add(nums[i]);
					subsets.add(subset);
				}
			}
			return subsets;
		}
	}

	class Solution2 {
		public List<List<Integer>> subsets(int[] nums) {
			return subsets(nums, nums.length);
		}

		public List<List<Integer>> subsets(int[] nums, int index) {
			if (index == 0) {
				List<List<Integer>> subsets = new ArrayList<>();
				subsets.add(new ArrayList<>());
				return subsets;
			}

			List<List<Integer>> subsets = subsets(nums, index - 1);
			int size = subsets.size();
			for (int j = 0; j < size; j++) {
				List<Integer> subset = new ArrayList<>(subsets.get(j));
				subset.add(nums[index - 1]);
				subsets.add(subset);
			}
			return subsets;
		}

	}
	
	static class Solution3 {
		
		  List<List<Integer>> output = new ArrayList<>();
		  int n, k;

		  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
		    // if the combination is done
		    if (curr.size() == k) {
		    	output.add(new ArrayList<>(curr));
		    	System.out.println(curr);
		    }

		    for (int i = first; i < n; ++i) {
		      // add i into the current combination
		      curr.add(nums[i]);
		      // use next integers to complete the combination
		      backtrack(i + 1, curr, nums);
		      // backtrack
		      curr.remove(curr.size() - 1);
		    }
		  }

		  public List<List<Integer>> subsets(int[] nums) {
		    n = nums.length;
		    for (k = 0; k < n + 1; ++k) {
		      backtrack(0, new ArrayList<Integer>(), nums);
		    }
		    return output;
		  }
		}

}

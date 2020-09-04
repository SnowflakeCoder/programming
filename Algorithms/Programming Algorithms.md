## Programming Algorithms

### Two Pointers Technique

Two pointers is an easy and effective technique typically used for searching pairs in a sorted array.

**Example** : Given a sorted array A (sorted in ascending order), having N integers, find if there <u>exists any pair of elements (A[i], A[j]) such that their sum is equal to X</u>.

We start the **sum of extreme values** (smallest and largest) and conditionally move both pointers. We take two pointers, one representing the first element and other representing the last element of the array, and then we add the values kept at both the pointers. If their sum is smaller than X then we shift the left pointer to right or if their sum is greater than X then we shift the right pointer to left, in order to get closer to the sum. We keep moving the pointers until we get the sum as X or small pointer (i) greater than large pointer (j). Later case no such pairs available.

Note: Above example can be faster if we find maxNumber near to X using binary search. This will reduce the array size.

### Window Sliding Technique

This technique shows how a nested for loop in some problems can be converted to a single for loop to reduce the time complexity.

**Example** - Given an integer array of size ‘n’, calculate the maximum sum of ‘k’ consecutive elements.

1. Compute the <u>sum of first k elements</u> using a linear loop and store the sum in variable window_sum.
2. Then we will graze linearly over the array till it reaches the end and simultaneously <u>keep track of maximum sum</u>.
3. To get the current sum of block of k elements just subtract the first element from the previous block and add the last element of the current block.

We can use the same technique to find max/min k-subarray, XOR, product, sum, etc.

### Subsets Problem

There are generally four strategies to do it:

1. Dynamic Programming (Bottom Up)
2. Recursion
3. Backtracking
4. **Lexicographic generation based** on the mapping between binary bitmasks and the corresponding
   permutations / combinations / subsets.

The last method simplifies the problem to the **generation of binary numbers**, therefore it is easy to implement and verify that no solution is missing. Besides, this method has the **best time complexity**, and as a bonus, it generates lexicographically sorted output for the sorted inputs.

#### Dynamic Programming (Bottom Up)

```java
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
```

**Complexity Analysis**

Time complexity: O(N×2^N) to generate all subsets and then copy them into output list.

Space complexity: O(2 ^ N). 

#### Recursive

```java
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
```

#### Backtracking

Find the **Power set is all possible combinations** of **all possible lengths**, from 0 to n. So, this time we loop over the length of combination, rather than the candidate numbers, and **generate all combinations for a given length** with the help of backtracking technique.

**Backtracking** is an algorithm for finding all solutions by exploring all potential candidates. If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it by making some changes on the previous step, i.e. backtracks and then try again.

**Algorithm**

We define a backtrack function named **backtrack**(first, curr) which takes the index of first element to add and a current combination as arguments.

- If the current combination is done, we add the combination to the final output.
- Otherwise, we iterate over the indexes i from first to the length of the entire sequence n.
  - Add integer nums[i] into the current combination curr.
  - Proceed to add more integers into the combination : backtrack(i + 1, curr).
  - Backtrack by removing nums[i] from curr.

```java
	static class Solution3 {
		  List<List<Integer>> output = new ArrayList<>();
		  int n, k;
		  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
		    // if the combination is done
		    if (curr.size() == k) {
		    	output.add(new ArrayList<>(curr));
		    	System.out.println(curr);
                return;
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
```

**Complexity Analysis**

Time complexity: O(N×2^N) to generate all subsets and then copy them into output list.

Space complexity: O(N×2^N) to keep all the subsets of length N, since each of N elements could be present or absent.

#### Lexicographic (Binary Sorted) Subsets

The idea is that we <u>map each subset to a bitmask of length n</u>, where 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence. For instance, the bitmask 0..00 (all zeros) corresponds to an empty subset, and the bitmask 1..11 (all ones) corresponds to the entire input array nums.

Hence to solve the initial problem, we just need to generate n bitmasks from 0..00 to 1..11. For that one could use standard bit manipulation trick:

**Solution1:**

```java
int nthBit = 1 << n;
for (int i = 0; i < (int)Math.pow(2, n); ++i) {
    // generate bitmask, from 0..00 to 1..11
    String bitmask = Integer.toBinaryString(i | nthBit).substring(1);
```

**Solution2:**

```java
for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
  // generate bitmask, from 0..00 to 1..11
  String bitmask = Integer.toBinaryString(i).substring(1);
```

Algorithm

- Generate all possible binary bitmasks of length n.
- Map a subset to each bitmask: 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.
- Return output list.

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> output = new ArrayList();
    int n = nums.length;
    for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
      // generate bitmask, from 0..00 to 1..11
      String bitmask = Integer.toBinaryString(i).substring(1);
      // append subset corresponding to that bitmask
      List<Integer> curr = new ArrayList();
      for (int j = 0; j < n; ++j) {
        if (bitmask.charAt(j) == '1') curr.add(nums[j]);
      }
      output.add(curr);
    }
    return output;
  }
```

**Complexity Analysis**

Time complexity: O(N×2 ^ N ) to generate all subsets and then copy them into output list.

Space complexity: O(N×2 ^ N) to keep all the subsets of length N, since each of N elements could be present or absent.

 




# Array

## Unique1toN

- Given an array of size N make sure it contains 1-n numbers.

  - if index and value are not same **swap a[index] and a[value]**. Validate a[index] again.
  - check for value > n and duplicate values.

- ```java
  public static boolean uniqueArray(int[] arr) {
   		for (int i = 0; i < arr.length; i++) {
   			if (arr[i] != i+1) { // if index and value are not same swap.
   				if (!swap(arr, i)) {
   					return false;
   				}
   			}
   		}
   		return true;
   	}
   	private static boolean swap(int[] arr, int i) {
   		if (arr[i] > arr.length) { // exceeds the arr.length
   			return false;
   		}
   		int temp1 = arr[i];
   		if (arr[temp1-1] == temp1) { // temp1 came twice. arr[i] & arr[temp1]
   			return false;
   		}
   		// else swap and run swap again.
   		int temp2 = arr[temp1 - 1];
   		arr[i] = temp2;
   		arr[temp1 - 1] = temp1;
   		if (arr[i] == i+1) {
   			return true;
   		} else {
   			return swap(arr, i);
   		}
   	}
  ```

- Time Complexity - O(n)

- Space Complexity - O(1)

------

## 3 Number Sum

**[-8, -6, 1, 2, 3, 5, 6, 12] ,	0	=>	Ans: [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]**

- Select one number from the beginning and find other 2 numbers from remaining array using **Two Number Sum**. We can use either **Map/Set** approach or **Array Sort** approach for Two Number Sum.

- ```java
  //Using Two Number Sum(Map/Set approach)
  private static void threeNumberSum(int[] numbers, int sum) {
      for(int i = 0; i < numbers.length -2; i++) {
          List<int[]> twoNumList = twoNumberSum(numbers, sum-numbers[i], i+ 1);
          if(twoNumList != null && !twoNumList.isEmpty()) {
              for(int[] twoNum : twoNumList) {
                  System.out.println(numbers[i] + "," + twoNum[0] + "," + twoNum[1]);
              }
          }
      }
  }
  private static List<int[]> twoNumberSum(int[] numbers, int sum, int startIndex) {
      Map<Integer, Boolean> map = new HashMap<>();
      List<int[]> twoNumList = new ArrayList<>();
      for(int i = startIndex; i < numbers.length; i++) {
          if(map.remove(numbers[i]) != null) { //if map already contains, we got a pair.
              twoNumList.add(new int[] {numbers[i], sum-numbers[i]});
          }
          else { map.put(sum-numbers[i], true);  }//Dif is adding to Map.
      }
      return twoNumList;
  }
  ```

- ```java
  private static void threeNumberSum(int[] numbers, int sum) {
      Arrays.sort(numbers); // Number Sum Array Sort Approach. 
      for(int i = 0; i < numbers.length -2; i++) {
          List<int[]> twoNumList = twoNumberSum(numbers, sum-numbers[i], i+ 1);
          if(twoNumList != null && !twoNumList.isEmpty()) {
              for(int[] twoNum : twoNumList) {
                  System.out.println(numbers[i] + "," +twoNum[0] + "," + twoNum[1]);
              }
          }
      }
  }
  private static List<int[]> twoNumberSum(int[] numbers, int sum, int startIndex) {
      //Two Number Sum for sorted Array using left and right pointer.
      List<int[]> twoNumList = new ArrayList<>();
      int left = startIndex, right = numbers.length -1;
      while(left < right) {
          if(numbers[left] + numbers[right] == sum) {
              twoNumList.add(new int[] {numbers[left], numbers[right] });
              left++;
          } 
          else if(numbers[left] + numbers[right] > sum) {	right--;	}
          else {	left++;	}
      }
      return twoNumList;
  }
  ```

- Time Complexity - O(n2)

- Space Complexity - O(n) - because we have to save the **answer as a separate list** and it varies based on n. First approach also need Map/Set of size n.

------

## Smallest Diff b/w 2 Arrays.

**A) [-1, 5, 10, 20, 28, 2]		B) [26, 134, 135, 15, 17]	=>	Ans: [28, 26]**

- **Sort both array** and start **two pointers** from left. Move the pointer in each array based on result.
- Time Complexity - O(Nlog(N) + Mlog(M))
- Space Complexity - O(1)

------

## Move All Elements to End

Find all instances of the given integer in the array and move them to the end of the array.

**[2, 1, 2, 2, 2, 3, 4, 2],	2	=>	[1, 3, 4, 2, 2, 2, 2, 2]**

- **Using 2 pointers** left and right and **swap the values** when you get arr[left] == 2 and arr[right] != 2.
- Time Complexity - O(N)
- Space Complexity - O(1)

------

## Monotonic Array

Two ways an array can be monotonic, either **entirely non increasing** or either **entirely non decreasing**. Non is used because Monotonic array **can have duplicate values** next to next.

**[1, 2, 3, 3, 6, 8]	=>	True**

- **Find the direction** (increasing or decreasing) and then make sure rest of the array keep that direction.

  - ```java
    int direction = 0;
    for(int i= 0; i < arr.length - 1; i++){
        if(direction == 0){
            // run the loop till we find a proper direction. eliminate duplicates.
            direction  = arr[i+1] - arr[i];
            continue;
        }
        // find the new direction and compare with first direction.
        int newDirection = arr[i+1] - arr[i]; 
        if(direction > 0 && newDirection < 0){
            return false;
        }
        if(direction < 0 newDirection > 0){
            return false;
        }
    }
    return true;
    ```

- **Assume array is entirely non increasing or decreasing** and try to prove it wrong. **simple solution**.

  - ```java
    boolean increasing = true;
    boolean decreasing = true; // assume array is either increasing or decreasing
    for(int i= 0; i < arr.length - 1; i++){
        if(arr[i] < arr[i+1]){
            decreasing = false; // decreasing is proved wrong
        }else if (arr[i] > arr[i+1]){
            increasing = false; // increasing is proved wrong
        }
        if(!(increasing || decreasing){ // if both are wrong then its not monotonic.
            return false;
        }
    }
    ```

- Time Complexity - O(N)

- Space Complexity - O(1)

## Longest Peak

- Peak should have atleast length 3 and they are strictly increasing till they reach the peak and then they strictly decreasing.
- Same numbers are not considered and peak cannot start from top and then decreasing.
- Define a peak - A peak should be strictly greater than its adjacent numbers.



## Max Subset Sum No Adjacent

Given an array, find the **maximum sum of a subset with no 2 numbers are adjacent**. 

So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7). 

- **Time Complexity** - O(N)
- **Space Complexity** - O(N).
  - **O(1) - if we use for loop**.

```java
	// Recursive approach (We can also use dynamic programming)
	private static int maxSubsetSumNoAdj(int arr[]) {
		if (arr == null || arr.length <= 0) {
			return 0;
		}
		return Math.max(maxSubsetSumNoAdj(arr, 0), maxSubsetSumNoAdj(arr, 1));
	}
	private static int maxSubsetSumNoAdj(int arr[], int index) {
		if (arr.length <= index) {
			return 0;
		}
		return arr[index] + Math.max(maxSubsetSumNoAdj(arr, index + 2), 			maxSubsetSumNoAdj(arr, index + 3));
	}
******************************************************************************
	// for-loop approach with inclusive and exclusive
	private static int maxSubsetSumNoAdj1(int arr[]) {
    	if (arr == null || arr.length <= 0) {
			return 0;
		}
		int inclusive = 0;
		int exclusive = 0;

		for (int index = 0; index < arr.length; index++) {
			int newExclusive = inclusive + arr[index];
			inclusive = exclusive;
			exclusive = Math.max(newExclusive, exclusive);
		}
		return exclusive;
	}
```

## Kadane's Algorithm / MaxSubArray

Find the contiguous subarray (containing at least 1 number) which has the largest sum and return its sum.

https://leetcode.com/problems/maximum-subarray/

- **Time Complexity** - O(N) 
- **Space Complexity** - O(1)

```java
public int maxSubArray(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int maxSum = nums[0], sum = nums[0]; // initialize with first element not minNumber.
    for(int index = 1; index < nums.length; index++){
        sum = Math.max(sum + nums[index], nums[index]);
        maxSum = Math.max(sum, maxSum);
    }
    return maxSum;
}
```

## Single Cycle Check

- **Time Complexity** - O(N) 
- **Space Complexity** - O(1) - No need to track all elements, Check only the **starting position repeated**.

```java
private static boolean singleCycleCheck(int[] arr) {
    int currentIndex = 0;
    for(int index =0; index < arr.length; index++) {
        if(index >0 && currentIndex == 0) { // No need to track all elements
            return false;
        }
        currentIndex = (currentIndex + arr[currentIndex]) % arr.length;// Note this !!!
        if(currentIndex < 0) {
            currentIndex += arr.length;
        }
    }
    return(currentIndex == 0); // it should reach where it started.
}
```



------

------

# Coin Problems

## Number Of Ways To Make Change

Write a function to compute the **number of combinations** that make up that amount. You may assume that you have infinite number of each kind of coin.

https://leetcode.com/problems/coin-change-2/

- **Time Complexity** - O(M*N) where N is the number of denominations and  M is the target amount.
- **Space Complexity** - O(M) 

```java
public int change(int amount, int[] coins) {
		int ways[] = new int[amount + 1];
		ways[0] = 1; // initialize first element with value 1
		for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
			for (int index = 0; index <= amount; index++) {
				if(index < coins[coinIndex]) {
					continue; // No need to continue if amount < coin denomination.
				}
				ways[index] = ways[index - coins[coinIndex]] + ways[index];
			}
		}

		return ways[amount];
	}
```



## Min Number Of Coins For Change

Write a function to compute the **fewest number of coins** that you need to make up that amount M. If that amount of money cannot be made up by any combination of the coins, return -1.

https://leetcode.com/problems/coin-change/

- **Time Complexity** - O(M*N) where N is the number of denominations and  M is the target amount.
- **Space Complexity** - O(M) 

```java
public int coinChange(int[] coins, int amount) {
    int ways[] = new int[amount + 1];
    for (int index = 1; index <= amount; index++) {
        ways[index] = -1; //Cant use 0 so initialize with -1 as No value.
    }
    for (int CIndex = 0; CIndex < coins.length; CIndex++) {
        for (int index = 0; index <= amount; index++) {
            if (index < coins[CIndex]) {
                continue;
            }
            if(ways[index - coins[CIndex]] != -1) {
                if(ways[index] == -1) {
                    ways[index] = ways[index - coins[CIndex]] + 1;					
                }else {
                    ways[index] = Math.min(ways[index - coins[CIndex]] + 1, ways[index]);                }
            }
        }
    }
    return ways[amount];
}
```

# String problems

## Levenshtein Distance /Edit Distance

Find the minimum number of operations required to convert str1 to str2.

https://leetcode.com/problems/edit-distance/

- **Time Complexity** - O(M*N) where N is str1.lenght and  M is str2.length.
- **Space Complexity** - O(M*N) 
  - - O(min(N,M)) - if we use 2 array approach as defined below.

```java
/*if characters are same then no change reqd else (1 + min of 3 prev steps)
if(str1[r-1]) == str2[c-1] then Mtrx[r][c] = Mtrx[r-1][c-1]
	else Mtrx[r][c] = 1 + Min(Mtrx[r1][c-1], Mtrx[r1-1][c], Mtrx[r1-1][c-1]
	Below solving it with 2 arrays of above matrix. No need to save entire matrix.
*/
public static int minDistance2(String word1, String word2) {
		char[] str1, str2 =null;
		int[] firstArr, secondArr = null;
		// find the smallest column array.
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
		// intialize [0] array as 0, 1, 2, 3 etc 		
		for (int index2 = 1; index2 <= str2.length; index2++) {
			firstArr[index2] = 1 + firstArr[index2 - 1];
		}
		for (int index1 = 1; index1 <= str1.length; index1++) {
            //initialize [0] column !!!
			secondArr[0] = firstArr[0] + 1;
			for (int index2 = 1; index2 <= str2.length; index2++) {
				if (str1[index1 - 1] == str2[index2 - 1]) {
                    secondArr[index2] = firstArr[index2 - 1];
                } else {
                    int min = Math.min(firstArr[index2], secondArr[index2 - 1]);
                    secondArr[index2] = 1 + Math.min(min, firstArr[index2 - 1]);
                } 
			}
			int[] temp = firstArr;// swich the rows at the end of forloop.
			firstArr = secondArr;
			secondArr = temp;
		}
		return firstArr[firstArr.length-1];
	}
```

## Word Wrap Problem (DP)

https://www.geeksforgeeks.org/word-wrap-problem-dp-19/





------

------

# Tree

## Inverting a BTree/Mirror Image.

- **Time Complexity** - O(N)
- **Space Complexity** - O(N) - Need a queue with all leaf nodes for each level.
  - O(d) / **O(log N)** - if we use **recursive** method, longest recursive call = depth of the tree.

```java
public TreeNode invertTree(TreeNode root) {
    if(root == null){ // Dont miss !!!
        return root;
    }

    TreeNode temp = root.right;
    root.right = invertTree(root.left);
    root.left = invertTree(temp);
    return root;
}
```

## Breadthfirst Search / Level order traversal

https://leetcode.com/problems/binary-tree-level-order-traversal/

- **Time Complexity** - O(N)
- **Space Complexity** - O(N)

```java
// Pass the list of nodes at each level to next level.
public List<List<Integer>> levelOrder(List<TreeNode> nodes, List<List<Integer>> list) {
    List<Integer> values = new ArrayList<>();
    TreeNode[] nodeArr = nodes.toArray(new TreeNode[0]);
    nodes.clear(); // clearing current level
    for(TreeNode node : nodeArr){
        values.add(node.val);
        if(node.left != null){
            nodes.add(node.left); // adding for next level
        }
        if(node.right != null){
            nodes.add(node.right); // adding for next level
        }
    }
    list.add(values);
    if(!nodes.isEmpty()){
        return levelOrder(nodes, list);
    }
    return list;
}
```

## Youngest Common Ancestor

Note: Below code only works **if both nodes are there in the tree**. Else we can use a Map which keep node as key and **value as {parent node, level}** then traverse from bottom to top to find the common ancestor.

- **Time Complexity** - O(N)
- **Space Complexity** - O(d) - No of recursive call will be proportional to Depth of the tree.

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root;
}
```



```java
class Solution {

    private TreeNode ans;

    public Solution() {
        // Variable to store LCA node.
        this.ans = null;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
}
```





------

------

# Matrix

## Number of Islands // River sizes

- https://leetcode.com/problems/number-of-islands/

- Input : Matrix

     - **1,	1**,	0,	0,	0
     - 0,	**1**,	0,	0,	**1**
     - **1**,	0,	0,	**1,	1**
     - 0,	0,	0,	0,	0
     - **1**,	0,	**1**,	0,	**1**

- Output : 6

- Use a **boolean matrix** with same size to **keep track of visited nodes**.

  - run countIslands() method on each node.
  - return if the node is already visited. **if not mark the nodes**.
  - if the **node is an island run same on all neighboring nodes**.

- ```java
  private static int countIslands(int[][] matrix) {
      boolean[][] visited = new boolean[matrix.length][matrix[0].length];
      int count = 0;
      for (int i = 0; i < matrix.length; i++) {
          for (int j = 0; j < matrix[i].length; j++) {
              if(countIslands(matrix, visited, i, j)) { //run countIslands on each node.
                  count++;
              }
          }
      }
      return count;
  }
  private static boolean countIslands(int[][] matrix, boolean[][] visited, int i, int j) {
      if(visited[i][j]) { return false; } // if visited no need to check again.
      visited[i][j] = true; // mark as visisted irrespective of value 0 or 1.
      if(matrix[i][j] == 1) { // if its an island check on neighboring nodes.
          if(i > 0) { countIslands(matrix, visited, i-1, j); }
          if(j > 0) { countIslands(matrix, visited, i, j-1); }
          if(i+1 < matrix.length) { countIslands(matrix, visited, i+1, j); }
          if(j+1 < matrix[i].length) { countIslands(matrix, visited, i, j+1); }
          return true;
      }
      return false;
  }
  ```

- Time Complexity - O(n). Every node has max 4 neighboring nodes so its a constant.

- Space Complexity - O(n). need one extra matrix for tracking visited nodes.

------

## Spiral Traverse

- Input Matrix => 
  
  - 1,	2,	3,	4
  - 5,	6,	7,	8 
  - 9,	10,	11,	12
  - 13,	14,	15,	16
  
- **Output = [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]**
  
- Can be solved using **for loop / recursive** way.

  - recursive function will have all the 4 corner node values for traversing that perimeter.

- **Traverse the perimeter using four corner** nodes and then go to inner perimeter.

- ```java
  List<Integer> result = new ArrayList<>(arr.length*arr[0].length);
  int startRow = 0, endRow = arr.length -1, startCol = 0, endCol = arr[0].length-1;
  while(startRow < endRow && startCol < endCol){ // adding each perimeter.
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
          result.add(arr[i][startCOl]); // adding first column in reverse order
      }
      startRow++; startCol++; endRow--; endCol--;
  }
  ```

- Time Complexity - O(n). Need to traverse all the nodes once.

- Space Complexity - O(n). Result will include all the nodes.

------

------










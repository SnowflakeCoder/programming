

## Array

### Unique1toN

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

### 3 Number Sum

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

### Smallest Diff b/w 2 Arrays.

**A) [-1, 5, 10, 20, 28, 2]		B) [26, 134, 135, 15, 17]	=>	Ans: [28, 26]**

- **Sort both array** and start **two pointers** from left. Move the pointer in each array based on result.
- Time Complexity - O(Nlog(N) + Mlog(M))
- Space Complexity - O(1)

------

### Move All Elements to End

Find all instances of the given integer in the array and move them to the end of the array.

**[2, 1, 2, 2, 2, 3, 4, 2],	2	=>	[1, 3, 4, 2, 2, 2, 2, 2]**

- **Using 2 pointers** left and right and **swap the values** when you get arr[left] == 2 and arr[right] != 2.
- Time Complexity - O(N)
- Space Complexity - O(1)

------

### Monotonic Array

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

------

------

## Matrix

### Number of Islands // River sizes

- https://leetcode.com/problems/number-of-islands/

- Input : Matrix

     - **1, 1**, 0, 0, 0
     - 0, **1**, 0, 0, **1**
     - **1**, 0, 0, **1, 1**
     - 0, 0, 0, 0, 0
     - **1**, 0, **1**, 0, **1**

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

### Spiral Traverse

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
          result.add(arr[i][startCOl]); // adding first column in reverse order
      }
      startRow++; startCol++; endRow--; endCol--;
  }
  ```

- Time Complexity - O(n). Need to traverse all the nodes once.

- Space Complexity - O(n). Result will include all the nodes.
















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

     ![img](data:image/png;base64,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)
     
     ​									Output : 6
     
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

![Spiral Order Traversal of a Matrix](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAASwAAACoCAMAAABt9SM9AAABklBMVEX///8AAACBS//f1f+IV//n3//18f+Uaf/Crf+DTv+EUP/azf8yAP9DAP+Rev/49f+zlv+Yb//v7+/x6/+PYv97e3txcXEMDAzc3NwvAKrs7OwtLS1XV1fn5+fAwMD4+Pg7OzuMjIzPz8+pqam8vLw6AMoWFhYAAC0AAEYAACdkZGSioqIrAKCYmJhBQUFNTU0cHBwpAJVbLNQAAFoAACYxMTEAABkUAE0AADh5TuJqamobAG7Jy8O6nv+xp8xLSGIjIyEYGhXl4fAjIS0AABDFwc+fmqzLx9UtLDQTDiBfO8ZWLcAzDJ5AHKmHYO/MxeF6P/9xP+xJHMErALQ+ANy+p/8qF26fi9yTe9RRPpFnVqEYAH8hIxgrLiAVGgBVWE1FSDubjr1tZ4Q7Nkw+KIA/Ml86KmMhE0dfUIVHNm9rX4o6KGwjCmmdkMNjIf8uEXuDeafUxf9YPqWwn+d+fImmoLwwKkttb2EgJABtTr7Etfagi/9fNdWnqZpZXEQqLwCGa9C9s9tzboemmc1+cqZTT1yQPVYCAAAI5UlEQVR4nO2c+1cbxxXHdYWEYOUaWD1Hr9ULSUgroQdCQoCMwWlSBxNWwqR17Domie24BNOmuEnttsF2/u/O7Arnl2pmtseOtDn3c46FfPieu+xXM7OjuTPX5UIQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQZDrQNCmVGlOlhAx5rSZ39SkhG6pDWEKWBoB4TipkdJVqoRyTkKppiFSlgk4DFXZfkBXq4qYOpG4sYWnrEtIaEyZlgk4DCUmzwulKjjUYVSJmbrWSC1FtXhzVvHpUIuZUkA3ndmTMYiQlzTKpS5iVA6jVHWQWHWKlzcoA7EgNx7FqtQdwU6TNsi5400HdcHZ2d+8WfLQwK6YKt+D3MsKFj6gL6/CxQLv7CY3n+sMt2J20CZIEgj7ffqOx4RNzu9FofLokIfT5NvR9GvQOX7T0aaOxf3Cg06t/Nmkb5PAezs8TQgxlno+iDKis6RHpTJjKoPomV60MaUR9eHQ01IvO6Iizd+fuHp+2Pp8Tcdw6Pf5eqLL4Iwv3p1brHl92r0X5Ynn5i9a6Y4Z49T7Alki0RUeh+w8ePPizzNMQ4OE/vhRPyrQ85ZFO1ipyz5dpQCtIPA2zMELmvgqWNC1zdb9O1p3RCS1y5Z64G1QyJlWZqUMss9pulytSF/c3Bycy34t+y0h/PfYrbu8H/Ut+S6BZNkCzbIBm2QDNsgGaZQM0ywZolg3QLBugWTZAs1wLc3f9cko0yxXwuO/KKdEs14JPmZNT+j2Kc8xKJtKUtng9RWWZQEjJxEylv9IHX6fjQnG0DEV96JAVeNe77PGqSBfbMXX3ZZZeVuFWg7QAagJxlAbsErIuTsZOCQVq1DePHz/y8gncKHafPH327TOBztSeP/7LYHAKUBZcu0ev/d2AFIWf1LQAsLe75AkGPXwMXdepSiR7x9nhqTgjTVt1zmuQYu1XudP3AMDzV0dNxePmMm8QMmgOBk1F4QtHcvfZcgsiomvTUfDBd0OyFvo1bvQ9oAFsEtIpvfXO8PCelzqdEv3XueTqrvirMjwV77hR0+aYdcMxO7QS1KwGHWgFsgx7CLA9LxmpqLO39VMQepCtA3QIWZFLbUwDWy89GwXhnKACkDAt60kF3dVJS7xDrgYQ8jdJ8aZTcmEpbdtzsCc0KwXQ1uTNytCpgzC9lqQNKzvjJl3JTTwTJwqRvzX3afcS9BmN3tgqm5ZKdRl1r0HuCVUaHbJqb+jUwSkbtKJgjVnCwThlTV5F80yLLDT078Wy3GhSKjcOTgGph8/JfkJiDp0tx+vpjOSD6+/7A5nvhqnETokML+RiTgWL8wcLUsJYUvoZPxucl/sirV7OO2rVYVFZkjPLBgFZs5y2RINm2QDNsgGaZQM0ywZolg3QLBugWTZAs2yAZtkAzbIBmmWDbWXpvceU/iJNzXJQRlrLPCd3MvxDJtFqLWGuz+Vq7XZItFIXyydWL3zKXDRTC3PiJqksbx4aeNFOSCW6J47aNhf/2pxDDpp5NjdM35TN9b8I/85y7DBKMej5GrjpjVyE/roQc13oZIW+c0Q2LAOwOdjnJo+jpkUPXa4qwA/sANMP3GWtL01DN4zWyOIxmLsBIlFq1uAk7Yxz0mwZ/MfgRoR39vmfy4zm9etN+vrygL4/uM6hacrPDo8frvLMysR7EWaW33AH1DiAXBGEicLOR//oOfiEl93Z/t3/w+EcSzjz0mFRs2XRp2EgKbs9Z8LQYWjz1R3gfbKL166xu7derl394MEkZ9JmKZ/R5i1M9U8D2VF2h7+J418A/zbPZ1rv/8OPqbIxS5E3q+uYOhjR3vPB7T3BoUszHx21zAoJN3PRXrXiccuaZZAipJ3hFWXRTN9zJaZZsYg5stSEiVZq1vrn2345s/o6KYYcsjNES1R/Yhlp/jzH2ukQYpV7euI+kxxJpMzqElLshXtlJ2TwRxnpNP+zZWZpVzslhRn82GjaJDaLylaoWdIVgSZN7yY1KyQ4Ux8tFNiGkGSZ9te2OHu9WqizlpWqF3gNVksU4lHXOSEv2u32jhNaFn12/WRIfJEeuRnLSs20R5XGNMFnwGR+jxKQCTklfIglGnkctkSDZtkAzbIBmmUDNMsGaJYN0CwboFk2QLNsgGbZAM2yAZplg8UPkJGWhyUsJnh5ebRoJpynZm2Ey+UwZ+VFzYevctb5UEiQh1FT4ZC5QGOWvRu/9hXLhdkRBL9inNCrT31yx6pEHttuNoCbEK1G3qWMU8IaiaxSM1tWvaqoOG5JJzNKWPubZkYapr3un3WWfIuaVW9HxqfurIx0gi1RxUB0Aj9pKuIqS1+P3v1PLC+pqZc6KRbSBfHhxAlThXSdmaUcuMyD5WNWdtUC1Mz7crnSAIKjiVqNialFtOXkYsmxB1hUiFhBvyHkNf2/A9I7Wtw0a2k3meV2r5xlVo/21bDM0URqVgigks+P90BjbS/h0lYI6WdClWlvWBTVNMuzAYKUgZXdoa/3Wbl7QblcZpY2KovPOyWcYUG3ioSsgSOyrNSsUv+62yiVSp3S+fjj5I9Knaczl1TzxvusVOrzD5O/KXUez3jfspCdzngxO6P+1NvfHA43j9fFNSAmjxpfI8bZ2ZnxqkmI3pwfV3mA/nbAXsiQVdomQ2OcclTVgLjditH0sErfA25QxRgeHbmD5+IT7ZNHja/rxuHZWTAYfKXrg7ElLcxfNvV3NAX1Mgj76QsGB8KgHmqW4us7wyxYu3i5PDjvP3rSLZ4ExhVMuVgp3vB6T25QnnS7X/3Mra7SX+u+8NLu+rZ//qS78npc0MBrGjTg/XY4vNf/WbJq9USxBnjSEBXkroymDi5zq4MgH5pnA7xWs2JGxg/cmat5ljkpdUDxngQzy2iI5prZX06Sl4VTBzZzVUezzjbH2JS5HcdvkHXu9rApIqm6Fg99H6dS/Eyz9ksDSYrL51viaCrFnw+YQf2H7suUM3L3DO/MzASvPuv3T3CBCEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQxAb/BT/ILE5DM8h9AAAAAElFTkSuQmCC)

​						**Output = [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]**

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














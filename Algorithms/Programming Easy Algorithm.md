



## General

### [E]nth Fibonacci number

Fibonacci numbers are 0, 1, 1, 2, 3, 5, 8, 13, 21, ……..

- **Recursive Algorithm**

  - ```java
    if (n < 3) {
        return (n-1) 
    } else {
        return fib(n-1) + fib(n-2)
    }
    ```

  - **Time Complexity = O(2^n)**

  - Space complexity = O(n)

- **Dynamic Programming Top to Bottom (memoization) **

  - ```java
    mem[1] = 0; mem[2] = 1; // initialize 1 and 2 to avoid if(n > 3) check everytime. 
    if(mem[n] == 0 && n > 1){ //if n is not defined in the memory(array).
        mem[n] = fib(n-1) + fib(n-2)   
    }
    return m[n]
    ```

  - Time Complexity = O(n)

  - Space complexity = O(n)

- **Dynamic Programming Bottom to Top (tabulation)  [ANSWER]**

  - ```java
    if (n < 3) {
        return (n-1) 
    } else {
        first = 0;
    	second = 1;
    	for(int i = 3; i <=n; i++){
            temp = first + second;
            first = second;
            second = temp;
        }
        return second
    }
    ```

  - Time Complexity = O(n)
  - **Space complexity = O(1)**

------

------

## Array

### [E]2 number of Sum

**[3,5,-4,8,11,1,-1,6] ,  10         =>          [11,-1]**

- **Two for loop solution**

  - ```java
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
        	if(arr[i] + arr[j] == sum){
                return new int[]{i,j};
            }
    	}
    }
    ```

  - Time Complexity = O(n^2)

  - Space complexity = O(1)

- **Sort array and use first and last pointer**

  - ```java
    arr.sort();
    first =0;
    last = n-1;
    while(first < last){
        tempSum = arr[first] + arr[last]
        if(tempSum == sum){
            return new int[]{first,last};
        }
        if(tempSum > sum){
            last--;
        }
    	if(tempSum < sum){
            first--;
        }
    }
    return null;
    ```

  - Time Complexity = O(nlogn)

  - Space complexity = O(1)

- **Using Hashmap / hashset  [ANSWER]** => keep the (sum - current array value) in map and keep **checking map key available in rest of array**.

  - ```java
    for(int i=0; i<n; i++){
    	if(map.get(arr[i]) == null){
            map.put(sum-arr[i], arr[i]);
        }else {
            return new int[]{arr[i],map.get(arr[i])};
        }
        // using hashset
    	if(set.contains(arr[i])){
            set.put(sum-arr[i]);
        }else {
            return new int[]{arr[i],sum-arr[i]};
        }     
    }
    ```

  - **Time Complexity = O(n)**

  - **Space complexity = O(n)**

------

------

## BST (Binary Search Tree)

### [E]Closest value in BST

- **Using single for loop [ANSWER]**

  - ```java
    int returnVal =0, diff = Interger.Max
    while(root != null){  // using loop
        currentDiff = math.abs(expectedVal - root.value())
        if (diff < currentDiff){
            return returnVal;
        }
        diff = currentDiff
        returnVal = root.value();
        if(root.value() < expectedVal){
            root = root.right;
        }
        else{
            root = root.left;
        }
    }
    return returnVal;
    ```

  - ```java
    return closest(root, expectedVal, Integer.Max)
    // using recursive
    closest(root, expectedVal, currentVal){
        if(root == null || math.abs(currentVal - expectedVal) < math.abs(root.value() - expectedVal)){
            return currentVal;
        }
        if(root.value() > expectedVal){
            return closest(root.left(), expectedVal, root.value())
        } else {
            return closest(root.right(), expectedVal, root.value())
        }    
    }    
    ```

  - Time Complexity for **both solutions**  = O(log n) - **best case**  

    - O(n) - **worst case** if tree is a list (each node contain 1 child node).

  - Space complexity = O(1) - **because using loop** here 

    - if solve **recursively** then **SC is same as TC** (i.e. O(log n) - **best case** **//** O(n) - **worst case** )

------

------

### [E]Branch sums of a BST

- **Recursive** - Calculate branch sum for each child nodes.

  - ```java
    List<Integer> branchSum(Node root){
        if(root.left == null && root.right == null){
            List childList = new ArrayList();
            childList.add(root.value());
            return childList;
        }else {
            List childList = null;
            if(root.left != null){
                childList = branchSum(root.left);            
            }
            if(root.right != null){
                if(childList == null){
                	childList = branchSum(root.right);    
                }
                else{
                    childList.addAll(branchSum(root.left));
                }
            }
            for(int i =0; i < n ; i++){
                int oldVal = childList.get(i);
                childList.set(i, oldVal + root.value())
            }
            return childList;
        }    
    }
    ```

- **Recursive** - pass the list **to avoid creating multiple list**

  - ```java
    List childList = new ArrayList();
    branchSum(root, childList)
    return childList;
    -----------------------------------------------------
    void branchSum(Node root, List childList){
        if(root != null){
            return;
        }
        if(root.left == null && root.right == null){
            childList.add(root.value());
        }else {
            branchSum(root.left, childList);
            branchSum(root.right, childList); 
            
            for(int i =0; i < n ; i++){
                int oldVal = childList.get(i);
                childList.set(i, oldVal + root.value())
            }
        }    
    }
    ```

- **Best answer** - **Recursive** - pass the list and total sum of each node to avoid **extra traversal** too.

  - ```java
    List childList = new ArrayList();
    branchSum(root, 0, childList)
    return childList;
    -----------------------------------------------------
    void branchSum(Node root,int totalsum, List childList){
        if(root != null){
            return;
        }
        totalsum = totalsum + root.value();
        if(root.left == null && root.right == null){
            childList.add(totalsum);
        }else {
            branchSum(root.left, totalsum, childList); 
            branchSum(root.right, totalsum, childList);  
        }    
    }
    ```

- **Time Complexity = O(n)** - recursive function for each node

- **Space complexity = O(n)** space needed will be **equal to how many leaf nodes** (~n/2).

------

------

## Tree

### [E]Depth First Search

- **Recursive** - pass the list.

  - ```java
    List childList = new ArrayList();
    branchSum(root, childList)
    return childList;
    -----------------------------------------------------
    void branchSum(Node root, List childList){
        if(root == null){
            return;
        }
        for(node child : root.getChilds()){
        	branchSum(child, childList);    
        }
    }
    ```

  - **Time Complexity = O(n)** - recursive function for each node

  - **Space complexity = O(n)**.




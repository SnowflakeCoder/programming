## Dynamic Programming 

Dynamic Programming is an algorithmic paradigm that solves a given complex problem by breaking it into sub-problems and stores the results of sub-problems to avoid computing the same results again. Following are the two main properties of a problem that suggest that the given problem can be solved using Dynamic programming.

### 1) Overlapping Subproblems:

Like Divide and Conquer, Dynamic Programming combines solutions of sub-problems. Dynamic Programming is mainly used when solutions of same subproblems are needed again and again. In dynamic programming, computed solutions of subproblems are stored in a table so that these don’t have to recomputed. So Dynamic Programming is not useful when there are no common (overlapping) subproblems because there is no point storing the solutions if they are not needed again. For example, Binary Search doesn’t have common subproblems. If we see the recursive program for Fibonacci Numbers, there are many subproblems which are solved again and again.

### 2) Optimal Substructure

A given problems has Optimal Substructure Property if optimal solution of the given problem can be obtained by using optimal solutions of its subproblems. For example, the Shortest Path problem has optimal substructure property. If a node x lies in the shortest path from a source node u to destination node v then the shortest path from u to v is combination of shortest path from u to x and shortest path from x to v. The standard All Pair Shortest Path algorithms like **Floyd–Warshall and Bellman–Ford** are typical examples of Dynamic Programming. On the other hand, the Longest Path problem doesn’t have the Optimal Substructure property. 

### Memoizatation Vs Tabulation

In dynamic programming, there are two different ways to store the values so that these values can be reused:

a) **Memoization (Top Down)**: The memoized program for a problem is similar to the recursive version with a small modification that it looks into a lookup table before computing solutions. We initialize a lookup array with all initial values as NIL. Whenever we need solution to a subproblem, we first look into the lookup table. If the precomputed value is there then we return that value, otherwise we calculate the value and put the result in lookup table so that it can be reused later.

Here, we start our journey from the top most destination state and compute its answer by taking in count the values of states that can reach the destination state, till we reach the bottom most base state. So instead of starting from the base state that i.e dp[0] we ask our answer from the states that can reach the destination state dp[n] following the state transition relation, then it is the top-down fashion of DP. As we can see we are storing the most recent cache up to a limit so that if next time we got a call for the same state we simply return it from the memory. So, this is why we call it **memoization** as we are storing the most recent state values.

b) **Tabulation (Bottom Up)**: The tabulated program for a given problem builds a table in bottom up fashion and returns the last entry from table. For example, for Fibonacci number, we first calculate fib(0) then fib(1) then fib(2) then fib(3) and so on. So literally, we are building the solutions of subproblems bottom-up. This starts from the bottom and cumulating answers to the top. We call it Bottom Up approach as it is quite clear that we started our transition from the bottom base state and reached the top most desired state.

It starts its transition from the bottom most base case dp[0] and reaches it destination state dp[n]. Here the dp table is being populated sequentially and we are directly accessing the calculated states from the table itself and hence, we call it **tabulation method**.

![tabulation-vs-memoization](file:///C:/Users/arun0/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)Both Tabulated and Memoized store the solutions of subproblems. In Memoized version, table is filled on demand while in Tabulated version, starting from the first entry, all entries are filled one by one. Unlike the Tabulated version, all entries of the lookup table are not necessarily filled in Memoized version. For example, Memoized solution of the **LCS (Longest Common Subsequence)** problem doesn’t necessarily fill all entries.

/* simple recursive program for **Fibonacci** numbers  {0, 1, 1, 2, 3, 5 …}*/

| Memoized version for nth Fibonacci Number.     public class Fibonacci  {   final int MAX = 100;   final int NIL = -1;   int lookup[] = new  int[MAX];      /* Function to initialize  NIL values in lookup table */   void _initialize()   {    for (int i = 0; i <  MAX; i++)      lookup[i] = NIL;   }      /* function for nth  Fibonacci number */   int fib(int n)   {    if (lookup[n] == NIL)    {     if (n <= 1)       lookup[n] = n;     else       lookup[n] =  fib(n-1) + fib(n-2);    }    return lookup[n];   }      public static void  main(String[] args)   {    Fibonacci f = new  Fibonacci();    int n = 40;    f._initialize();      System.out.println("Number is" + " " + f.fib(n));   }   } | Tabulated version for nth Fibonacci Number.     C/C++JavaPython  /* Java program for Tabulated version */  public class Fibonacci  {   int fib(int n)   {    int f[] = new int[n+1];    f[0] = 0;    f[1] = 1;    for (int i = 2; i <=  n; i++)       f[i] = f[i-1] +  f[i-2];    return f[n];   }      public static void  main(String[] args)   {    Fibonacci f = new  Fibonacci();    int n = 9;      System.out.println("Fibonacci number is" + " " +  f.fib(n));   }     } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**1. Problem2**

| **Ugly numbers** are numbers whose only prime factors are 2, 3 or 5. The sequence  1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By  convention, 1 is included. Given a number n, the task is to find n’th Ugly  number.     import java.lang.Math;     class UglyNumber  {    /* Function to get the  nth ugly number*/    int getNthUglyNo(int n)    {      int ugly[] = new  int[n]; // To store ugly numbers      int i2 = 0, i3 = 0,  i5 = 0;      int  next_multiple_of_2 = 2;      int  next_multiple_of_3 = 3;      int  next_multiple_of_5 = 5;      int next_ugly_no =  1;             ugly[0] = 1;       for(int i = 1; i  < n; i++)      {        next_ugly_no =  Math.min(next_multiple_of_2,                   Math.min(next_multiple_of_3,                        next_multiple_of_5));                 ugly[i] =  next_ugly_no;        if (next_ugly_no  == next_multiple_of_2)        {          i2 = i2+1;           next_multiple_of_2 = ugly[i2]*2;        }        if (next_ugly_no  == next_multiple_of_3)        {          i3 = i3+1;           next_multiple_of_3 = ugly[i3]*3;        }        if (next_ugly_no  == next_multiple_of_5)        {          i5 = i5+1;           next_multiple_of_5 = ugly[i5]*5;        }      } /*End of for loop  (i=1; i<n; i++) */      return next_ugly_no;    }   } | Given 3 numbers {1, 3, 5}, we need to tell the total number of  ways we can form a number 'N' using  the sum of the given three numbers. (allowing repetitions and different  arrangements).  Total number of ways to form 5 is : 5 {1+1+1+1+1, 1+1+3,   1+3+1, 3+1+1, 5}     state(n) = state(n-1) + state(n-3) + state(n-5)     int solve(int n)  {     // base case    if (n < 0)      return 0;    if (n == 0)      return 1;        return solve(n-1) +  solve(n-3) + solve(n-5);  } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

## Data Structures

A data structure is a particular way of organizing data in an application so that it can be used effectively. The idea is to reduce the space and time complexities of different tasks. 

### Linear data structures

Arrays, Linked Lists, Stack and queues are linear data structures,

**Array** :- used to store homogeneous elements at contiguous locations.

**Linked List**

A linked list is a linear data structure where each element is a separate object, comprising of two items – the data and a reference to the next node.

Types of Linked List

\1. Singly Linked List : Every node stores reference of next node in list.

\2. Doubly Linked List : Every node stores references of next and previous node.

\3. Circular Linked List : Here all nodes are connected to form a circle. There is no NULL at the end. The next pointer of last node is pointing to the first. A circular linked list can be a singly circular linked list or doubly circular linked list. Advantage of this data structure is that any node can be made as starting node.

**Stack**

A stack or LIFO (last in, first out) is an abstract data type with two principal operations: push and pop. Both the operations takes place at the same end that is top of the stack. It can be implemented by using both array and linked list.

**Queue**

A queue or FIFO (first in, first out) is an abstract data type with two principal operations: enqueue (adding from the rear side) and dequeue (removing from the front side). It can be implemented by using both array and linked list.

**Circular Queue** The advantage of this data structure is that it reduces wastage of space in case of array implementation, As the insertion of the (n+1)’th element is done at the 0’th index if it is empty.

 

 

### Hierarchical data structures

**Tree**

Trees are hierarchical data structures. Main uses of trees is to store hierarchical data. For example, the file system on a computer. A tree is represented by a pointer to the topmost node(root) in tree. If the tree is empty, then value of root is NULL. 

### Binary Tree

A binary tree is a tree data structure in which each node has at most two children (left child and the right child). It is implemented mainly using **Links**. A Binary Tree node contains data, Pointer to left child and Pointer to right child.

A Binary Tree can be traversed in two ways:

**Depth First Traversal**: Inorder (Left-Root-Right), Preorder (Root-Left-Right) and Postorder (Left-Right-Root)

**Breadth First Traversa**l: Level Order Traversal

**Properties of Binary Tree**

·    The maximum number of nodes at level ‘l’ of a binary tree is 2^(l-1).

·    Maximum number of nodes in a binary tree of height ‘h’ is 2^h – 1.

·    In a Binary Tree with N nodes, minimum possible height or minimum number of levels is ⌈ Log2(N+1) ⌉  

·    A Binary Tree with L leaves has at least  ⌈ Log2L ⌉ + 1  levels

·    In Binary tree, number of leaf nodes is always one more than nodes with two children.

**Types of Binary Tree**

·    **Full Binary Tree**: A Binary Tree is full if every node has 0 or 2 children. We can also say a full binary tree is a binary tree in which all nodes except leaves have two children.

·    **Complete Binary Tree**: A Binary Tree is complete Binary Tree if all levels are completely filled except possibly the last level and the last level has all keys as left as possible. Practical example of Complete Binary Tree is **Binary Heap**.

·    **Perfect Binary Tree**: A Binary tree is Perfect Binary Tree in which all internal nodes have two children and all leaves are at same level. A Perfect Binary Tree of height h (where height is number of nodes on path from root to leaf) has 2h – 1 node.

·    **Balanced Binary Tree:** A binary tree is balanced if height of the tree is O(Log n) where n is number of nodes. 

·    **Degenerate (or pathological or skewed) tree:** A Tree where every internal node has one child. Such trees are performance-wise same as linked list.

### Binary Search Tree

Binary Search Tree is a Binary Tree with following additional properties:

\1.    The left subtree of a node contains only nodes with keys less than the node’s key.

\2.    The right subtree of a node contains only nodes with keys greater than the node’s key.

\3.    The left and right subtree each must also be a binary search tree.

BST provide moderate access/search (quicker than Linked List and slower than arrays). BST provide moderate insertion/deletion (quicker than Arrays and slower than Linked Lists). Its main use is in search application where data is constantly entering/leaving and data needs to printed in sorted order. For example in implementation in E- commerce websites where a new product is added or product goes out of stock and all products are lised in sorted order.

**Time Complexities** (h: Height of BST, n: Number of nodes in BST) :      

Search : O(h)

Insertion : O(h)

Deletion : O(h)

Extra Space : O(n) for pointers

If Binary Search Tree is Height Balanced, then h = O(Log n) 

**Self-Balancing BSTs** such as AVL Tree, Red-Black Tree and Splay Tree make sure that height of BST remains O(Log n). **AVL tree** maintain O(Log n) height by making sure that the difference between heights of left and right subtrees is 1. **Red-Black trees** maintain O(Log n) height by making sure that the number of Black nodes on every root to leaf paths are same and there are no adjacent red nodes. Balanced Binary Search trees are performance wise good as they provide O(log n) time for search, insert and delete.

### Binary Heap

A Binary Heap is a complete Binary Tree. This property of Binary Heap makes them suitable to be stored in an array. A Binary Heap is either **Min Heap** or **Max Heap**. In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to Min Heap. It is mainly implemented using array.

Used in implementing efficient priority-queues. The Heap data structure can be used to efficiently find the k’th smallest (or largest) element in an array. Heap is a special data structure and it cannot be used for searching of a particular element.

Get Minimum in Min Heap: O(1) [Or Get Max in Max Heap]

Extract Minimum Min Heap: O(Log n) [Or Extract Max in Max Heap]

Decrease Key in Min Heap: O(Log n) [Or Extract Max in Max Heap]

Insert: O(Log n) 

Delete: O(Log n)

### Hashing / Hash Function

A function that converts a given big input key to a small practical integer value. The mapped integer value is used as an index in hash table. A good hash function should have following properties

1) Efficiently computable.

2) Should uniformly distribute the keys (Each table position equally likely for each key)

Hash Table: An array that stores pointers to records corresponding to a given phone number. An entry in hash table is NIL if no existing phone number has hash function value equal to the index for the entry.

Collision Handling: Since a hash function gets us a small number for a key which is a big integer or string, there is possibility that two keys result in same value. The situation where a newly inserted key maps to an already occupied slot in hash table is called collision and must be handled using some collision handling technique. Following are the ways to handle collisions:

Chaining: The idea is to make each cell of hash table point to a linked list of records that have same hash function value. Chaining is simple, but requires additional memory outside the table.

Open Addressing: In open addressing, all elements are stored in the hash table itself. Each table entry contains either a record or NIL. When searching for an element, we one by one examine table slots until the desired element is found or it is clear that the element is not in the table.

Space : O(n)

Search  : O(1) [Average]  O(n) [Worst case]

Insertion : O(1) [Average]  O(n) [Worst Case]

Deletion : O(1) [Average]  O(n) [Worst Case]

Hashing seems better than BST for all the operations. But in hashing, elements are unordered and in BST elements are stored in an ordered manner. Also BST is easy to implement but hash functions can sometimes be very complex to generate. In BST, we can also efficiently find floor and ceil of values.

**BFS vs DFS for Binary Tree**

A Tree is typically traversed in two ways:

**Breadth First Traversal** (Or Level Order Traversal)

**Depth First Traversals**

·    Inorder Traversal (Left-Root-Right)

·    Preorder Traversal (Root-Left-Right)

·    Postorder Traversal (Left-Right-Root)

| ![Example Tree](file:///C:/Users/arun0/AppData/Local/Temp/msohtmlclip1/01/clip_image003.gif) | Breadth First Traversal : 1 2 3 4 5     Depth First Traversals:     Preorder Traversal : 1  2 4 5 3      Inorder Traversal : 4  2 5 1 3      Postorder Traversal :  4 5 2 3 1 |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**Time and space complexity**

All four traversals require O(n) time as they visit every node exactly once.

Extra Space required for Level Order Traversal is O(w) where w is maximum width of Binary Tree. In level order traversal, queue one by one stores nodes of different level. Maximum Width of a Binary Tree at depth (or height) h can be 2^h where h starts from 0. So the maximum number of nodes can be at the last level. And worst case occurs when Binary Tree is a perfect Binary Tree with numbers of nodes like 1, 3, 7, 15, …etc. In worst case, value of 2h is Ceil(n/2).

Extra Space required for Depth First Traversals is O(h) where h is maximum height of Binary Tree. In Depth First Traversals, stack (or function call stack) stores all ancestors of a node. Height for a Balanced Binary Tree is O(Log n). Worst case occurs for skewed tree and worst case height becomes O(n).

**How to Pick One?**

·    The extra space required for Level order traversal is likely to be more when tree is more balanced and extra space for Depth First Traversal is likely to be more when tree is less balanced.

·    Depth First Traversals are typically recursive and recursive code requires function call overheads.

·    The most important points is, BFS starts visiting nodes from root while DFS starts visiting nodes from leaves. So if our problem is to search something that is more likely to closer to root, we would prefer BFS. And if the target node is close to a leaf, we would prefer DFS.

*******************************************************

### Graph

Graph consists of two components:

·    A finite set of vertices also called as **nodes**.

·    A finite set of ordered pair of the form (u, v) called as **edge**. The pair is ordered because (u, v) is not same as (v, u) in case of directed graph(di-graph). The pair of form (u, v) indicates that there is an edge from vertex u to vertex v. The edges may contain weight/value/cost.

Graph classifications

·    Undirected Graph : The graph in which all the edges are bidirectional.

·    Directed Graph : The graph in which all the edges are unidirectional.

·    Weighted Graph : The Graph in which weight is associated with the edges.

·    Unweighted Graph : The Graph in which there is no weight associated to the edges.

### Trie

Trie is an efficient data structure for searching words in dictionaries, search complexity with Trie is linear in terms of word (or key) length to be searched. If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree. Using trie, we can search the key in O(M) time. So it is much faster than BST.

Hashing also provides word search in O(n) time on average. But the advantages of Trie are there are no collisions (like hashing) so worst case time complexity is O(n). Also, the most important thing is Prefix Search. With Trie, we can find all words beginning with a prefix (This is not possible with Hashing). The only problem with Tries is they require a lot of extra space. Tries are also known as **radix tree** or **prefix tree**.

The Trie structure can be defined as follows :

struct trie_node

{

  int value; /* Used to mark leaf nodes */

  trie_node_t *children[ALPHABET_SIZE];

};

Insert time : O(M) where M is the length of the string.

Search time : O(M) where M is the length of the string.

Space : O(ALPHABET_SIZE * M * N) where N is number of     keys in trie, ALPHABET_SIZE is 26 if we are     only considering upper case Latin characters.

Deletion time : O(M)

Example : The most common use of Tries is to implement dictionaries due to prefix search capability. Tries are also well suited for implementing approximate matching algorithms, including those used in spell checking. It is also used for searching Contact from Mobile Contact list OR Phone Directory.

 

Advantages of Trie Data Structure

 

Tries is a tree that stores strings. Maximum number of children of a node is equal to size of alphabet. Trie supports search, insert and delete operations in O(L) time where L is length of key.

 

Hashing:- In Hashing, we convert key to a small value and the value is used to index data. Hashing supports search, insert and delete operations in O(L) time on average.

 

Self Balancing BST : The time complexity of search, insert and delete operations in a self-balancing Binary Search Tree (BST) (like Red-Black Tree, AVL Tree, Splay Tree, etc) is O(L Log n) where n is total number words and L is length of word. The advantage of Self balancing BSTs is that they maintain order which makes operations like minimum, maximum, closest (floor or ceiling) and k-th largest faster. 

 

Why Trie? :-

 

With Trie, we can insert and find strings in O(L) time where L represent the length of a single word. This is obviously faster that BST. This is also faster than Hashing because of the ways it is implemented. We do not need to compute any hash function. No collision handling is required (like we do in open addressing and separate chaining)

Another advantage of Trie is, we can easily print all words in alphabetical order which is not easily possible with hashing.

We can efficiently do prefix search (or auto-complete) with Trie.

Issues with Trie :-

The main disadvantage of tries is that they need lot of memory for storing the strings. For each node we have too many node pointers(equal to number of characters of the alphabet), If space is concern, then Ternary Search Tree can be preferred for dictionary implementations. In Ternary Search Tree, time complexity of search operation is O(h) where h is height of the tree. Ternary Search Trees also supports other operations supported by Trie like prefix search, alphabetical order printing and nearest neighbor search.

 

The final conclusion is regarding tries data structure is that they are faster but require huge memory for storing the strings.

 

 

 

### Segment Tree

This data structure is usually implemented when there are a lot of queries on a set of values. It is used when we need to find Maximum/Minumum/Sum/Product of numbers in a range. Queries also involve updation of values in given set. Segment Trees are implemented using array.

Construction of segment tree : O(N)

Query : O(log N)

Update : O(log N)

Space : O(N) [Exact space = 2*N-1]

### Suffix Tree

Suffix Tree is mainly used to search a pattern in a text. The idea is to preprocess the text so that search operation can be done in time linear in terms of pattern length. The pattern searching algorithms like KMP, Z, etc take time proportional to text length. This is really a great improvement because length of pattern is generally much smaller than text. But using Suffix Tree may not be a good idea when text changes frequently like text editor, etc.

Suffix Tree is compressed trie of all suffixes, so following are very abstract steps to build a suffix tree from given text.

1) Generate all suffixes of given text.

2) Consider all suffixes as individual words and build a compressed trie.

**Example** : Used to find find all occurrences of the pattern in string. It is also used to find the longest repeated substring (when test doesn’t change often), the longest common substring and the longest palindrome in a string.

### Abstract Data Types

Abstract Data type (ADT) is a type (or class) for objects whose behavior is defined by a set of value and a set of operations. **Three main ADTs** are List ADT, Stack ADT, Queue ADT. ADT only mentions what operations are to be performed but not how these operations will be implemented. It does not specify how data will be organized in memory and what algorithms will be used for implementing the operations. It is called “abstract” because it gives an implementation independent view. The process of providing only the essentials and hiding the details is known as abstraction. There can be different ways to implement an ADT, for example, the List ADT can be implemented using arrays, or singly linked list or doubly linked list.

**List ADT**

A list contains elements of same type arranged in sequential order and following operations can be performed on the list : get(), insert(), remove(), removeAt(), replace(), size(), isEmpty(), isFull()

**Stack ADT**

A Stack contains elements of same type arranged in sequential order. All operations takes place at a single end that is top of the stack and following operations can be performed: push(), pop(), peek(), size(), isEmpty(), isFull() 

**Queue ADT**

A Queue contains elements of same type arranged in sequential order. Operations takes place at both ends, insertion is done at end and deletion is done at front. Following operations can be performed: 

enqueue(), dequeue() , peek(), size(), isEmpty(), isFull()

## **Search Algorithms**

### Linear Search & Binary Search

A **linear search** scans one item at a time, without jumping to any item. The worst case complexity is O(n).

A **binary search** is a searching algorithm for sorted arrays. It cut down the search to half as soon as you find middle of a sorted list. The middle element is looked to check if it is greater than or less than the value to be searched. Accordingly, search is done to either half of the given list.

**Auxiliary Space**: O(1) in case of iterative implementation. In case of recursive implementation, O(Logn) recursion call stack space.

**Differences**

·    Input data needs to be sorted in Binary Search and not in Linear Search

·    Linear search does the sequential access whereas Binary search access data randomly.

·    Time complexity of linear search - O(n) , Binary search - O(log n).

### Jump Search or block search

Like Binary Search, Jump Search is a searching algorithm for sorted arrays. The basic idea is to check fewer elements (than linear search) by jumping ahead by fixed steps or skipping some elements in place of searching all elements. For example, suppose we have an array arr[] of size n and **block size** (to be jumped) m. Then we search at the indexes arr[0], arr[m], arr[2m]…..arr[km] and so on. Once we find the interval (arr[km] < x < arr[(k+1)m]), we perform a linear search operation from the index km to find the element x.

What is the **optimal block size** to be skipped?

In the worst case, we have to do n/m jumps and if the last checked value is greater than the element to be searched for, we perform m-1 comparisons more for linear search. Therefore the total number of comparisons in the worst case will be ((n/m) + m-1). The value of the function ((n/m) + m-1) will be minimum when m = √n. Therefore, the best step size is m = √n.

Auxiliary Space : O(1)

The optimal size of a block to be jumped is O(√ n). This makes the time complexity of Jump Search O(√ n).

The time complexity of Jump Search is between Linear Search ( ( O(n) ) and Binary Search ( O (Log n) ).

Binary Search is better than Jump Search, but Jump search has an advantage that <u>we traverse back only once</u> (Binary Search may require up to O(Log n) jumps). So <u>in a system where jumping back is costly, we use Jump Search</u>.

### Interpolation Search

Interpolation Search works on a sorted array of uniformly distributed values. The **Interpolation Search is an improvement over Binary Search** for instances, where the values in a sorted array are uniformly distributed. Binary Search always goes to middle element to check. On the other hand interpolation search may go to different locations according the value of key being searched. For example if the value of key is closer to the last element, interpolation search is likely to start search toward the end side.

Algorithm

Step1: In a loop, calculate the value of “pos” using the **probe position formula**.

Step2: If it is a match, return the index of the item, and exit.

Step3: If the item is less than arr[pos], calculate the probe position of the left sub-array. Otherwise calculate the same in the right sub-array.

Step4: Repeat until a match is found or the sub-array reduces to zero.

**Find the position to be searched**

To find the position to be searched, it uses following formula. Formula returns higher value of pos when element to be searched is closer to arr[hi]. And smaller value when closer to arr[lo]

**pos = lo + [ (x-arr[lo])\*(hi-lo) / (arr[hi]-arr[Lo]) ]**

arr[] ==> Array where elements need to be searched

x   ==> Element to be searched

lo  ==> Starting index in arr[]

hi  ==> Ending index in arr[]

**Time Complexity** : If elements are uniformly distributed, then O (log log n)). In the worst case (for instance where the numerical values of the keys increase exponentially) it can make up to O(n) comparisons.

**Auxiliary Space** : O(1)

### Exponential Search

Exponential search involves two steps:

·    Find range where element is present

The idea is to start with subarray size 1 compare its last element with x, then try size 2, then 4 and so on until last element of a subarray is greater. Once we find an index i (after repeated doubling of i), we know that the element must be present between i/2 and i.

·    Do Binary Search in above found range.

**Time Complexity** : O(Log n)

**Auxiliary Space** : Binary Search is recursive and requires O()Log n) space. With **iterative Binary Search**, we need only O(1) space.

Exponential Binary Search is particularly useful for **unbounded searches**, where size of array is infinite. Here we can’t directly apply Binary Search as we don’t have an upper limit or ‘high’ index. The idea is to do repeated doubling until we find a positive value. We can apply Binary Search after finding high index. We can use ‘high/2’ as low and ‘high’ as high indexes in binary search. The result n must lie between ‘high/2’ and ‘high’. Number of steps for finding ‘high’ is O(Logn). So we can find ‘high’ in O(Logn) time. The value of ‘high’ must be less than 2*n. The number of elements between high/2 and high must be O(n). Therefore, time complexity of Binary Search is O(Logn) and overall time complexity is 2*O(Logn) which is O(Logn).

It works better than Binary Search for bounded arrays also when the element to be searched is closer to the first element.

### Ternary Search

In binary search, the sorted array is divided into two parts while in ternary search, it is divided into 3 parts and then you determine in which part the element exists. Ternary search, like binary search, is a divide-and-conquer algorithm. It is mandatory for the array to be sorted before you begin the search. In this search, after each iteration it neglects ⅔ part of the array and repeats the same operations on the remaining ⅓.

**Why is Binary Search preferred over Ternary Search?**

From the first look, it seems the ternary search does less number of comparisons as it makes **Log3n** recursive calls, but binary search makes **Log2n** recursive calls. But If you apply binary search, you have **log2(n)+O(1)** many comparisons. If you apply ternary search, you have **2\*log3(n)+O(1)** many comparisons, as in each step, you need to perform 2 comparisons to cut the search space into three parts. Now if you do the math, you can observe that:

2⋅log3(n)+O(1)=2⋅(log(2)/log(3))log2(n)+O(1)

Since we know that 2⋅(log(2)/log(3))>1 we actually get more comparisons with ternary search.

 

 

 

 

## Sorting

**What is in-place sorting?**

An in-place sorting algorithm uses constant extra space even for producing the output (modifies the given array only). For example, Insertion Sort and Selection Sorts are in-place sorting algorithms and a typical implementation of Merge Sort is not in-place.

**What are Internal and External Sortings?**

When all data that needs to be sorted cannot be placed in-memory at a time, the sorting is called external sorting. External Sorting is used for massive amount of data. Merge Sort and its variations are typically used for external sorting. When all data is placed in-memory, then sorting is called internal sorting.

External sorting is a term for a class of sorting algorithms that can handle massive amounts of data. External sorting is required when the data being sorted do not fit into the main memory (usually RAM) and instead they must reside in the slower external memory (usually a hard drive). External sorting typically uses a **hybrid sort-merge strategy**. In the sorting phase, chunks of data small enough to fit in main memory are read, sorted, and written out to a temporary file. In the merge phase, the sorted sub-files are combined into a single larger file.

One example of external sorting is the **external merge sort algorithm**, which sorts chunks that each fit in RAM, then merges the sorted chunks together. We first divide the file into **runs** (a run is part of file that is small enough to fit in main memory). Then sort each run in main memory using merge sort sorting algorithm. Finally merge the resulting runs together into successively bigger runs, until the file is sorted.

**Stability in sorting algorithms**

A sorting algorithm is said to be stable if two objects with equal keys appear in the same order in sorted output as they appear in the input array to be sorted. Here stability means that equivalent elements retain their relative positions, after sorting. 

Sorting Algorithms like Insertion Sort, Merge Sort, Count Sort etc. are stable. Comparison based stable sorts such as Merge Sort and Insertion Sort, maintain stability by ensuring that-

Element A[j] comes before A[i] if and only if A[j] < A[i], here i, j are indices and i < j.

Since i<j, the relative order is preserved if A[i]\equiv A[j] i.e. A[i] comes before A[j].

Other non-comparison based sorts such as **Counting Sort** maintain stability by ensuring that the Sorted Array is filled in a reverse order so that elements with equivalent keys have the same relative position. Some sorts such as **Radix Sort** depend on another sort, with the only requirement that the other sort should be stable.

**Can we make any sorting algorithm stable?**

Quick Sort, Heap Sort etc. are unstable. But this can be made stable by also taking the position of the elements into consideration. Sometimes this may compromise on the performance or takes some extra space. Any given sorting algo which is not stable can be modified to be stable. There can be sorting algo specific ways to make it stable, but in general, any comparison based sorting algorithm which is not stable by nature can be modified to be stable by changing the key comparison operation so that the **comparison of two keys considers position as a factor for objects with equal keys**.

### Quadratic and Linearithmic Comparison-based Sorting Algorithms

A **Comparison-based sorting algorithm** is a sorting algorithm whose final order is determined by a comparison between the value of it’s input elements. The comparison algorithm reads the list of elements through a single abstract comparison operation that determines which of two elements should occur first in the final sorted list. There are two general categories of comparison algorithms, as defined by Big-O notation, in use today **quadratic** and **linearithmic**. Three very popular quadratic algorithms are Bubble sort, Insertion sort and Selection sort and three linearithmic Heapsort, Merge sort and Quicksort. Quicksort has a Quadratic worst case, but since it is often implemented in ways that make that very unlikely it deserves the linearithmic category. 

**Terms**

Ø **Stable**: Does not change the relative order of elements with equal keys

Ø **In-place**: Space complexity – only requires a constant amount O(1) of additional memory space

Ø **Online**: Can sort a list as it receives it, piece-by-piece in a serial fashion

Ø **Adaptive**: Takes advantage of existing order in its input

Ø **Lower bound**: A mathematical argument saying you can’t hope to go faster than a certain amount

Ø **Inversions**: Unordered subsets within a data set

Ø **Locality**: It says that some data, or the place where it is stored is accessed often.

Ø **Big-O Notation**: The approximate time it takes an algorithm to complete as a function of the size of it’s data set. Also described as **Time complexity**.

Ø **Time complexity**: A measure of the number of operations given an input size

Ø **Asymptotic**: Refers to a limiting behavior based on a single variable and a desired measure.[1]

**Quadratic Algorithms O(n^2)**

The O(n2) family of algorithms are conceptually the simplest, and in some cases very fast, but their quadratic time complexity limits their scalability. The swap operation is fundamental to both the bubble sort and the selection sort. Insertion sort works by selecting the smallest values and inserting them in the proper order by shifting the higher values right. A common characteristic of quadratic algorithms is **nested looping**, where each item in the list is compared to every other item in the list, (n * n) or (n2).

### Bubble Sort

The algorithm starts at the beginning of the dataset. It compares the first two elements, and if the first is greater than the second, it swaps them. It continues doing this for each pair of adjacent elements to the end of the dataset. It then starts again with the first two elements, repeating again until no more swaps have occurred. If the list is already sorted the algorithm will perform with a Big-O time complexity of O(n). Large values at the beginning of list are known as **rabbits** while small values at the end of list are known as **turtles** because of the speed that they move through the list. Rabbits and turtles can have a large effect of the performance of bubble sort.

Bubble Sort works by repeatedly swapping the adjacent elements if they are in wrong order. The algorithm needs one whole pass without any swap to know it is sorted. So bubble can be **optimized** by stopping the algorithm if inner loop didn’t cause any swap. In computer graphics it is popular for its capability to detect a very small error (like swap of just two elements) in almost-sorted arrays and fix it with just linear complexity (2n).

```java
//Worst and Average Case Time Complexity : O(n*n). Worst case occurs when  array is reverse sorted.
//Best Case Time Complexity : O(n). Bubble sort takes minimum time  when array is already sorted.
//Auxiliary Space: O(1)  Sorting In Place: Yes  Stable: Yes 
public static void bubbleSort(int[] a) {
    boolean sorted = false;
    int temp;
    int end = 0;
    while(!sorted) {
        sorted = true;
        for (int i = 0; i < array.length - 1 - end; i++) {
            if (a[i] > a[i+1]) {
                temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                sorted = false;
            }
        }
        end++;
    }
}
```

#### Recursive Bubble Sort

In Bubble Sort, we can notice that in first pass, we move (bubble) largest element to end. In second pass, we move second largest element to second last position and so on.

1)   Base Case: If array size is 1, return.

2)   Do One Pass of normal Bubble Sort. After this pass, the largest element is moved (or bubbled) to end.

3)   Now largest element is fixed, Recur for all elements except last of current subarray.

Note: Recursive Bubble Sort has no performance/implementation advantages.

### Selection Sort

The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order) from unsorted part and putting it at the beginning. The algorithm maintains two subarrays in a given array.

1) The subarray which is already sorted.

2) Remaining subarray which is unsorted.

The algorithm divides the input array into two parts, the sorted part which begins at index[0] and builds right as the data is sorted, and the unsorted items occupying the remainder of the array. Initially the sorted part is empty and the unsorted part occupies the full array. It then finds the smallest element in the array and exchanges it with the element in the first position, then find the second smallest element and exchanges it with the element in the second position, and continues until the entire array is sorted.

 

| void sort(int arr[])  {         int n =  arr.length;    for (int i = 0; i <  n-1; i++)         {           int min_idx = i;                 for  (int j = i+1; j < n; j++)                        if  (arr[j] < arr[min_idx])                               min_idx = j;            // Swap the  minimum element with the first element                 int  temp = arr[min_idx];                 arr[min_idx]  = arr[i];                 arr[i]  = temp;         }  } | In each iteration of selection sort, the minimum element from the unsorted subarray is  picked and moved to the sorted subarray (swap the minimum element with  first element of unsorted array).  **Time Complexity**:   **Worst Case & Best  Case:** O(n^2) as there are two nested loops.  **Auxiliary Space**: O(1)  **Sorting In Place: Yes**  **Stable: No** |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

An in-place and non-stable sorting algorithm that generally performs worse than the similar insertion sort. The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a costly operation.

**Why selection sort is unstable?**

The definition of stable is that the relative order of elements with the same value is maintained. Selection sort works by finding the 'least' value in a set of values, then swap it with the first value. So in selection sort, swap that occurs at the end of each "round" can change the relative order of items having the same value. To make this stable, instead of swapping values, insert the 'least' value instead.

Example: Sort 4 2 3 4 1 with selection sort.

### Insertion Sort

An in-place, stable and **on-line** sorting algorithm that is relatively efficient for small datasets and mostly sorted sets. Like Selection sort the algorithm divides the input array into two parts, the sorted part which begins at index[0] and builds right as the data is sorted, and the unsorted items occupying the remainder of the array. Initially the sorted part is empty and the unsorted part occupies the full array. It then take elements from the unsorted set one by one and inserting them in their correct position into a new sorted set. Insertion is expensive, requiring shifting all following elements over by one. While insertion sort typically makes fewer comparisons than selection sort, it requires more writes because the inner loop can require shifting large sections of the sorted portion of the array. In general, insertion sort will write to the array O(n2) times, whereas selection sort will write only O(n) times. Insertion sort is efficient, passing through the array only once, it’s also **adaptive** taking advantage of datasets that are already substantially sorted: the time complexity is O(n + d), where d is the number of inversions.

| void sort(int arr[])  {         int n =  arr.length;         for (int i=1;  i<n; ++i)         {                 int  key = arr[i];                 int  j = i-1;     /*   Note the nested loop **iterate  right to left**, compare ‘value’ with element to the left. Move element  right one index per iteration to make room for ‘value’ as it moves left. Move  elements of arr[0..i-1], that are greater than key, to one position ahead of  their current position */                 while  (j>=0 && arr[j] > key)                 {                        arr[j+1]  = arr[j];                        **j = j-1;**                 }                 arr[j+1]  = key;         }  } | **Time Complexity**:   Worst Case :   O(n^2)  Best Case : O(n)     **Boundary Cases**: Insertion sort takes maximum time to sort if elements are sorted  in reverse order. And it takes minimum time (Order of n) when elements are  already sorted.     **Auxiliary Space**: O(1)     **Sorting In Place**: Yes  **Stable**: Yes  **Online** : Yes |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**Binary Insertion Sort**

We can use binary search to reduce the number of comparisons in normal insertion sort. Binary Insertion Sort use binary search to find the proper location to insert the selected item at each iteration. In normal insertion, sort it takes O(i) (at ith iteration) in worst case. We can reduce it to O(logi) by using binary search. The algorithm as a whole still has a running worst case running time of O(n2) because of the series of swaps required for each insertion.

**Recursive Insertion Sort**

If we take a closer look at Insertion Sort algorithm, we keep processed elements sorted and insert new elements one by one in the inserted array.

Base Case: If array size is 1 or smaller, return.

Recursively sort first n-1 elements.

Insert last element at its correct position in sorted array.

Recursive Insertion Sort has no performance/implementation advantages.

**Quadratic Summary**

Among simple O(n2) algorithms, selection sort almost always **outperforms** bubble sort. Insertion sort is very similar to selection sort, has an **advantage** in that it only scans as many elements as needed to determine the correct location of the k+1st element, while selection sort must scan all remaining elements to find the absolute smallest element. Insertion sort will usually perform about half as many comparisons as Selection sort. If the input data is already sorted, Insertion sort performs as few as n-1 comparisons, thus making Insertion sort more efficient when given sorted, or nearly sorted, datasets. While Insertion sort typically makes fewer comparisons than Selection sort, it requires more writes because the inner loop can require shifting large sections of the sorted portion of the dataset. In general, Insertion sort perform O(n2) writes, whereas Selection sort will only perform O(n) writes. For this reason Selection sort may be **preferable** in cases where writing to memory is more expensive than reading.

 

**Linearithmic Algorithms O(n log n)**

There are fundamental limits on the performance of comparison sorts. A comparison sort must have a lower bound of Ω(n log n) comparison operations.[2] In this sense, mergesort, heapsort, and introsort are asymptotically optimal in terms of the number of comparisons they must perform for each n (log n). Merge sort and Quicksort both employ **divide-and-conquer techniques** which are crucial for their O(n log n) time complexity. Divide-and-conquer technique recursively separate a problem into one or more smaller related subproblems, until these become simple enough to be solved directly. The solutions to the subproblems are then combined to give a solution to the original problem. Heapsort achieves O(n log n) by first organizing the data set into a **heap data structure**. Because of their time complexity Linearithmic algorithms are capable of scaling to large data sets.

### Heapsort

Is an in-place, non-recursive, **unstable** algorithm, but considered by many to be the de-facto sorting algorithm for guaranteed O(n log n) time complexity. Heapsort is the O(n log n) version of **selection sort**. It also works by determining the largest (or smallest) element of the dataset, placing that at the end (or beginning) of the set, then continuing with the rest of the dataset. But Heap sort is a comparison based sorting technique based on Binary Heap data structure to improve time complexity. Once the dataset has been made into a heap, the root node is guaranteed to be the largest (or smallest) element. When it is removed and placed at the end of the datset, the heap is rearranged so the largest element remaining moves to the root. Using the heap, finding the next largest element takes **O(log n)** time, instead of O(n) for a linear scan as in selection sort. This allows Heapsort to run in **O(n log n)** time.

Heapsort is generally appreciated because it is trustworthy: There aren’t any “pathological” cases which would cause it to be unacceptably slow.

Heap Sort Algorithm for sorting in increasing order:

\1.    Build a max heap from the input data.

\2.    At this point, the largest item is stored at the root of the heap. Replace it with the last item of the heap followed by reducing the size of heap by 1. Finally, heapify the root of tree.

\3.    Repeat above steps while size of heap is greater than 1.

**How to build the heap?**

Heapify procedure can be applied to a node only if its children nodes are heapified. So the heapification must be performed in the bottom up order. The heapify procedure calls itself recursively to build heap in top down manner.

Applications of HeapSort

\1. Sort a nearly sorted (or K sorted) array

\2. k largest(or smallest) elements in an array

Heap sort algorithm has limited uses because Quicksort and Mergesort are better in practice. Nevertheless, the Heap data structure itself is enormously used. 

 

| public void heapSort(int arr[])  {         int n =  arr.length;         // Build heap  (rearrange array)         for (int i = n  / 2 - 1; i >= 0; i--)                 heapify(arr,  n, i);            // One by one  extract an element from heap         for (int i=n-1;  i>=0; i--)         {                 //  Move current root to end                 int  temp = arr[0];                 arr[0]  = arr[i];                 arr[i]  = temp;            // call max  heapify on the reduced heap                 heapify(arr,  i, 0);         }  } | Big-O Time Complexity  Worst Case & Best Case :       O(n  log n)     Time complexity of heapify is O(Logn). Time complexity of  createAndBuildHeap() is O(n) and hence overall time complexity of Heap Sort  is O(nLogn).     Heap sort is an in-place algorithm.     Its not stable, but can be made stable. |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**Heapify**

Heapify is the process of converting a binary tree into a Heap data structure. A binary tree being a tree data structure where each node has at most two child nodes. A Heap must be a complete binary tree, which is each level of the tree is completely filled, except possibly the bottom level. At this level, it is filled from left to right. A Heap must also satisfy the **heap-order property**, the value stored at each node is greater than or equal to it’s children.

A Binary Heap is a Complete Binary Tree where items are stored in a special order such that value in a parent node is greater(or smaller) than the values in its two children nodes. The former is called as **max heap** and the latter is called **min heap**. The heap can be represented by binary tree or array.

**Why array based representation for Binary Heap?**

Since a Binary Heap is a Complete Binary Tree, it can be easily represented as array and array based representation is space efficient. If the parent node is stored at index I, the left child can be calculated by 2 * I + 1 and right child by 2 * I + 2 (assuming the indexing starts at 0).

**How to build the heap?**

Heapify procedure can be applied to a node only if its children nodes are heapified. So the heapification must be performed in the bottom up order. The heapify procedure calls itself recursively to build heap in top down manner.

| /* To heapify a subtree rooted with node i which is    an index in arr[]. n is  size of heap */  void heapify(int arr[], int n, int i)  {         int largest =  i; // Initialize largest as root         int l = 2*i +  1; // left = 2*i + 1         int r = 2*i +  2; // right = 2*i + 2            // If left  child is larger than root         if (l < n  && arr[l] > arr[largest])                 largest  = l;            // If right  child is larger than largest so far         if (r < n  && arr[r] > arr[largest])                 largest  = r;            // If largest  is not root         if (largest !=  i)         {                 int  swap = arr[i];                 arr[i]  = arr[largest];                 arr[largest]  = swap;            // Recursively  heapify the affected sub-tree                 heapify(arr,  n, largest);         }  } |      |
| ------------------------------------------------------------ | ---- |
|                                                              |      |

### Merge Sort

A comparison-based, stable, divide and conquer algorithm that requires additional memory. The algorithm starts by comparing every two elements (i.e., 1 with 2, then 3 with 4…) and swapping them if the first should come after the second. It then merges each of the resulting datasets of two into sets of four, then merges those sets of four, and so on; until at last two sets are merged into the final sorted dataset. Due to the required copying of the collection Merge sort is in the average case slower than Quicksort.

It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves. The merge() function is used for merging two halves. 

| **MergeSort(arr[], l, r)**     If r > l  1.     Find the middle point to  divide the array into two halves:   middle m = (l+r)/2  2.     Call mergeSort for first  half:    Call mergeSort(arr, l, m)  3.     Call mergeSort for second  half:  Call mergeSort(arr, m+1, r)  4.     Merge the two halves sorted  in step 2 and 3:  Call merge(arr, l, m, r)     The merge(arr, l, m, r) is key process that assumes that arr[l..m]  and arr[m+1..r] are sorted and merges the two sorted sub-arrays into one.     **Big-O Time Complexity**  Worst Case & Best Case : O(n log n)  Time complexity of Merge Sort is O(nLogn) in all 3 cases (worst,  average and best) as merge sort always divides the array in two halves and  take linear time to merge two halves.  **Auxiliary Space**: O(n)  **Algorithmic Paradigm**: Divide and Conquer  **Sorting In Place**: No in a typical implementation  **Stable**: Yes     MergeSort     // Main function that sorts arr[l..r] using  // merge()  void sort(int arr[], int l, int r)  {         if (l < r)         {                 //  Find the middle point                 int  m = (l+r)/2;                    //  Sort first and second halves                 sort(arr,  l, m);                 sort(arr  , m+1, r);                    //  Merge the sorted halves                 merge(arr,  l, m, r);         }  } | /* Merges two subarrays of arr[]. First subarray is arr[l..m]  Second subarray is arr[m+1..r] */  void merge(int arr[], int l, int m, int r)  {         // Find sizes  of two subarrays to be merged         int n1 = m - l  + 1;         int n2 = r - m;            /* Create temp  arrays */         int L[] = new  int [n1];         int R[] = new  int [n2];            /*Copy data to  temp arrays*/         for (int i=0;  i<n1; ++i)                 L[i]  = arr[l + i];         for (int j=0;  j<n2; ++j)                 R[j]  = arr[m + 1+ j];            /* Merge the  temp arrays */         // Initial  indexes of first and second subarrays         int i = 0, j =  0;            // Initial  index of merged subarry array         int k = l;         while (i <  n1 && j < n2)         {                 if  (L[i] <= R[j])                 {                        arr[k]  = L[i];                        i++;                 }                 else                 {                        arr[k]  = R[j];                        j++;                 }                 k++;         }         /* Copy  remaining elements of L[] if any */         while (i <  n1)         {                 arr[k]  = L[i];                 i++;                 k++;         }         /* Copy  remaining elements of R[] if any */         while (j <  n2)         {                 arr[k]  = R[j];                 j++;                 k++;         }  } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

Applications of Merge Sort

Ø Merge Sort is useful for sorting linked lists in O(nLogn) time. In case of linked lists the case is different mainly due to difference in memory allocation of arrays and linked lists. Unlike arrays, linked list nodes may not be adjacent in memory. Unlike array, in linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge operation of merge sort can be implemented without extra space for linked lists.

In arrays, we can do random access as elements are continuous in memory. Quick Sort requires a lot of this kind of access. In linked list to access i’th index, we have to travel each and every node from the head to i’th node. Therefore, the overhead increases for quick sort. Merge sort accesses data sequentially and the need of random access is low.

Ø **Inversion Count Problem**

Ø Used in External Sorting

### Quicksort

An in-place, **unstable**, divide and conquer algorithm. Quicksort can be implemented as a stable sort but at a performance penalty. Quicksort relies on a partition, called a **pivot**, to divide the data into two parts. The objective being to select the median value of the dataset as the pivot (which can’t actually be known until after the data is sorted). So it picks an element as pivot and partitions the given array around the picked pivot. All elements, with values, smaller than the pivot are moved before it and all greater elements are moved after it. This is done in **linear time O(n)** and in-place. The lesser and greater subsets are then recursively sorted, with each recursion selecting a new pivot. 

**Partition Algorithm**

There are many different versions of quickSort that pick pivot in different ways.

Ø Always pick first element as pivot.

Ø Always pick last element as pivot

Ø Pick a random element as pivot.

Ø Pick median as pivot.

Target of partitions is, given an array and an element x of array as pivot, put x(pivot) at its **correct position** in sorted array and put all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. There can be many ways to do partition. We start from the leftmost element and keep track of index of smaller (or equal to) elements as i. While traversing, if we find a smaller element (< pivot), we swap current element with arr[i]. Otherwise we ignore current element. 

| Pseudo Code for recursive QuickSort function :     /* low   --> Starting index,   high --> Ending index */  quickSort(arr[], low, high)  {      if (low < high)      {        /* pi is partitioning index, arr[pi] is now at right place */        pi = partition(arr, low, high);           quickSort(arr, low, **pi - 1**); // Before pi        quickSort(arr, **pi + 1**,  high); // After pi      }  }  Big-O Time Complexity  Worst Case    O(n2)  Avg Case       O(n log  n)  Best Case      O(n log  n) | Pseudo code for partition()  /* This function takes last element as pivo.*/     partition (arr[], low, high)  {  // pivot (Element to be placed at right position)  pivot = arr[high];   i = (low - 1) // Index of  smaller element  for (j = low; j <= high- 1; j++)  {  // If current element is smaller than or equal to pivot         if (arr[j]  <= pivot)         {         i++;  // increment index of smaller element         swap arr[i] and  arr[j]         }  }  swap arr[i + 1] and arr[high])  return (i + 1)  } |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

 

**Analysis of QuickSort**

The time taken by QuickSort depends upon the input array and partition strategy. Following are three cases.

**Worst Case**: The worst case occurs when the partition process always picks greatest or smallest element as pivot. If we consider above partition strategy where last element is always picked as pivot, the worst case would occur when the array is already sorted in increasing or decreasing order. Here Big-O Time Complexity is O(n2)

**Best Case**: The best case occurs when the partition process always picks the middle element as pivot. Here Big-O Time Complexity is O(n log n)

**Average Case**: We can get an idea of average case by considering the case when partition puts O(n/9) elements in one set and O(9n/10) elements in other set. Solution of above recurrence is also O(nLogn)

Although the worst case time complexity of QuickSort is O(n2) which is more than many other sorting algorithms like Merge Sort and Heap Sort, **QuickSort is faster in practice**, because its inner loop can be efficiently implemented on most architectures, and in most real-world data. QuickSort can be implemented in different ways by changing the choice of pivot, so that the worst case rarely occurs for a given type of data. However, **merge sort** is generally considered better when **data is huge** and stored in external storage.

The most complex issue in quicksort is choosing a good pivot element; consistently poor choices of pivots can result in drastically slower O(n²) performance, if at each step the median is chosen as the pivot then the algorithm works in O(n log n). Finding the pivot however, is an O(n) operation on unsorted data-sets and therefore exacts its own penalty. A very good partition splits a dataset into two equal subsets. A bad partition, on other hand, splits a dataset into two sets of very different sizes. The worst partition puts only one element in one dataset and all other elements in the other set, preventing divide-and-conquer. If the partitioning is balanced, Quicksort runs asymptotically as fast as Merge sort. If, on the other hand, the partitioning is unbalanced, Quicksort runs asymptotically as slow as insertion sort.

**3-Way QuickSort**

In simple QuickSort algorithm, we select an element as pivot, partition the array around pivot and recur for subarrays on left and right of pivot. Consider an array which has many redundant (duplicate) elements. In 3 Way QuickSort, an array arr[l..r] is divided in 3 parts:

a) arr[l..i] elements less than pivot.

b) arr[i+1..j-1] elements equal to pivot.

c) arr[j..r] elements greater than pivot.

**Why Quick Sort is preferred over MergeSort for sorting Arrays**

Quick Sort in its general form is an in-place sort (i.e. it doesn’t require any extra storage) whereas merge sort requires O(N) extra storage, N denoting the array size which may be quite expensive. Allocating and de-allocating the extra space used for merge sort increases the running time of the algorithm. Comparing average complexity we find that both type of sorts have O(NlogN) average complexity but the constants differ. For arrays, merge sort loses due to the use of extra O(N) storage space.

Most practical implementations of Quick Sort use randomized version. The randomized version has expected time complexity of O(nLogn). The worst case is possible in randomized version also, but worst case doesn’t occur for a particular pattern (like sorted array) and randomized Quick Sort works well in practice.

Quick Sort is also a cache friendly sorting algorithm as it has good locality of reference when used for arrays.

Quick Sort is also tail recursive, therefore tail call optimizations is done.

**Why MergeSort is preferred over QuickSort for Linked Lists?**

In case of linked lists the case is different mainly due to difference in memory allocation of arrays and linked lists. Unlike arrays, linked list nodes may not be adjacent in memory. Unlike array, in linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge operation of merge sort can be implemented without extra space for linked lists.

In arrays, we can do random access as elements are continuous in memory. Let us say we have an integer (4-byte) array A and let the address of A[0] be x then to access A[i], we can directly access the memory at (x + i*4). Unlike arrays, we can not do random access in linked list. Quick Sort requires a lot of this kind of access. In linked list to access i’th index, we have to travel each and every node from the head to i’th node as we don’t have continuous block of memory. Therefore, the overhead increases for quick sort. Merge sort accesses data sequentially and the need of random access is low.

**How to optimize QuickSort so that it takes O(Log n) extra space in worst case?**

**QuickSort Tail Call Optimization** (Reducing worst case space to Log n )

In QuickSort, partition function is in-place, but we need extra space for recursive function calls. A simple implementation of QuickSort makes two calls to itself and in worst case requires O(n) space on function call stack.

The worst case happens when the selected pivot always divides the array such that one part has 0 elements and other part has n-1 elements. For example, if we choose last element as pivot, we get worst case for sorted arrays

Can we reduce the **auxiliary space for function call stack**?

We can limit the auxiliary space to O(Log n). The idea is based on **tail call elimination**. We can convert the code so that it makes one recursive call. For example, in the below code, we have converted the above code to use a while loop and have reduced the number of recursive calls. We have optimize the above code to make a recursive call only for the smaller part after partition. 

/* This QuickSort requires O(Log n) auxiliary space in worst case. */

void quickSort(int arr[], int low, int high)

{

  while (low < high)

  {

​    /* pi is partitioning index, arr[p] is now at right place */

​    int pi = partition(arr, low, high);

 

​    // If left part is smaller, then recur for left part and handle right part iteratively

​    if (pi - low < high - pi)

​    {

​      quickSort(arr, low, pi - 1);

​      low = pi + 1;

​    }

 

​    // Else recur for right part

​    else

​    {

​      quickSort(arr, pi + 1, high);

​      high = pi - 1;

​    }

  }

}

In the above code, if left part becomes smaller, then we make recursive call for left part. Else for the right part. In worst case (for space), when both parts are of equal sizes in all recursive calls, we use O(Log n) extra space.

### Introsort

Introsort, or introspective sort is a sorting algorithm that initially uses **Quicksort**, but switches to **Heapsort** when the recursion depth exceeds a level based on, the logarithm of, the number of elements being sorted, and uses **Insertion sort** for small cases because of its good locality of reference, i.e. when the data most likely resides in memory and easily referenced.

Big-O Time Complexity

Worst Case    O(n log n)

Avg Case       O(n log n)

Best Case      O(n)

### Timsort

A stable, adaptive, hybrid sorting algorithm, derived from **Merge sort** and **Insertion sort**. The algorithm finds subsets of the data that are already ordered, and uses the subsets to sort the data more efficiently. This is done by merging an identified subset, called a run, with existing runs until certain criteria are fulfilled. Timsort was designed to take advantage of partial orderings that already exist in most real-world data.

Big-O Time Complexity

Worst Case    O(n log n)

Avg Case       O(n log n)

Best Case      O(n)

**Linearithmic Summary**

Linearithmic algorithms are important because they scale to very large datasets. **Quicksort** is generally considered the **fastest**, **Heapsort** the most **trustworthy** for it’s guaranteed worst-case and **Merge sort** is appreciated for it’s **stability** when sorting objects. Introsort and Timsort are more complex hybrids of Quicksort, Heapsort, Merge sort and Insertion sort respectively that minimizes or eliminates quadratic worst-case time complexity. 

**Java JDK / JRE**

Prior to Java 1.7 the JDK/JRE has used **Merge sort** for sorting objects and a “tuned” Quicksort for sorting primitives. Merge sort provides a stable sort, which is often crucial for sorting objects, and guarantees O(n log n) which means it is scalable. The “tuned” Quicksort has been modified so that it reduces the likelihood of quadratic time O(n2) by improving the selection of the partition pivot, in what is called the **Bentley-McIlroy adaptive pivot selection**. The approach works by using a ternary comparison operator (less-than, equal-to, greater-than) to enable a “fat pivot,” and insertion sort for small sub-arrays. This Quicksort also uses a **fast swap** that adapts to the size of the data elements. The adaptive pivot selection uses the middle element for small arrays, the median of the first, middle and last elements for medium-sized arrays, and the median of nine equally-spaced elements for larger arrays.

Beginning with Java 1.7 the JDK/JRE has begun using **Timsort** for object sorts and a three-way partition (dual pivot) Quicksort for sorting primitives. One issue that’s improved with a three-way partition is duplicate values. Quicksort has been well documented to run at quadratic time with datasets containing many duplicate values. A three-way partition can significantly increase performance by grouping the duplicate values with a pivot of the same value. This allows the other two partitions to still benefit from the divide-and-conquer nature of the algorithm that produces O(n log n) that we desire.

Time Complexities of all Sorting Algorithms

![img](file:///C:/Users/arun0/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

 

## Fail Fast and Fail Safe Iterators in Java

First of all, there is no term as fail-safe. Its only Fail fast and Non fail-fast iterators.

**Concurrent Modification**: modify an object concurrently when another task is already running over it. 

### Fail-Fast iterators

**Fail-Fast iterators** immediately throw **ConcurrentModificationException** if there is structural modification of the collection. **Structural modification** means adding, removing or updating any element from collection while a thread is iterating over that collection. If you remove an element via Iterator remove() method, exception will not be thrown. However, in case of removing via a collection remove() method, ConcurrentModificationException will be thrown. Iterator on HashMap, Vector, ArrayList, HashSet classes are some examples of fail-fast Iterator. According to Oracle docs , Fail-fast iterators throw ConcurrentModificationException on a **best-effort basis**. Therefore, it would be wrong to write a program that depended on this exception for its correctness.

#### How Fail Fast Iterator works?

To know whether the collection is structurally modified or not, fail-fast iterators use an internal flag called **modCount** which is updated whenever there is a structural modification. Fail-fast iterators checks the modCount flag when it gets the next value (i.e. using next(), remove(),forEachRemaining() method), and if it finds that the modCount has been modified after this iterator has been created, it throws ConcurrentModificationException.

ArrayList iterator is fail-fast by design. Here modCount holds the modification count and every time we use add, remove or trimToSize method, it increments. **expectedModCount** is the iterator variable that is initialized when we create iterator with same value as modCount. This explains why we don’t get exception if we use set method to replace any existing element. Basically iterator throws ConcurrentModificationException if list size is changed.

### Fail-Safe iterators

**Fail-Safe iterators** don’t throw any exceptions if a collection is structurally modified while iterating over it. This is because, they operate on the **clone** of the collection, not on the original collection and that’s why they are called fail-safe iterators. Fail Safe Iterator makes copy of the internal data structure (object array) and iterates over the copied data structure. Any structural modification done to the iterator affects the copied collection, not original collection. So, original collection remains structurally unchanged. Iterator on CopyOnWriteArrayList, ConcurrentHashMap classes are examples of fail-safe Iterator.

**Note:** In case of **ConcurrentHashMap**, it does not operate on a separate copy although it is not fail-fast. Instead, the iterators returned by ConcurrentHashMap is **weakly consistent**. This means that this iterator can tolerate concurrent modification, traverses elements as they existed when iterator was constructed and may (but not guaranteed to) reflect modifications to the collection after the construction of the iterator.

Two issues associated with Fail Safe Iterator are :

Ø Overhead of maintaining the copied data structure i.e memory.

Ø Fail safe iterator does not guarantee that the data being read is the data currently in the original data structure. 

According to Oracle docs , fail safe iterator is ordinarily too costly, but may be more efficient than alternatives when traversal operations vastly outnumber mutations, and is useful when you cannot or don’t want to synchronize traversals. The **"snapshot" style iterator** method uses a reference to the state of the array at the point that the iterator was created. This array never changes during the lifetime of the iterator, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException. The iterator will not reflect additions, removals, or changes to the list since the iterator was created. Element-changing operations on iterators themselves (remove(), set(), and add()) are not supported. These methods throw **UnsupportedOperationException**.

## CopyOnWriteArrayList in Java

 

A **thread-safe variant of ArrayList** in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array. This is ordinarily too costly, but may be more efficient than alternatives when traversal operations vastly outnumber mutations, and is useful when you cannot or don't want to synchronize traversals. CopyOnWriteArrayList allows the modification of list, but it doesn’t change the iterator values and we get same elements from iterator as it was on original list. The iterator will not reflect additions, removals, or changes to the list since the iterator was created. Element-changing operations on iterators themselves (remove, set, and add) are not supported. These methods throw UnsupportedOperationException.

In CopyOnWriteArrayList all elements are permitted, including null.

 

Memory consistency effects: As with other concurrent collections, actions in a thread prior to placing an object into a CopyOnWriteArrayList happen-before actions subsequent to the access or removal of that element from the CopyOnWriteArrayList in another thread.

## Skip list

 

Skip list is a data structure that allows fast search within an ordered sequence of elements. Fast search is made possible by maintaining a linked hierarchy of subsequences, with each successive subsequence skipping over fewer elements than the previous one. Searching starts in the sparsest subsequence until two consecutive elements have been found, one smaller and one larger than or equal to the element searched for. Via the linked hierarchy, these two elements link to elements of the next sparsest subsequence, where searching is continued until finally we are searching in the full sequence. The elements that are skipped over may be chosen probabilistically [2] or deterministically,[3] with the former being more common.

For a Balanced Binary Search Tree, we skip almost half of the nodes after one comparison with root. For a sorted array, we have random access and we can apply Binary Search on arrays.

Can we augment sorted linked lists to make the search faster? The answer is Skip List. We create multiple layers so that we can skip some nodes. The upper layer works as an “**express lane**” which connects only main outer stations, and the lower layer works as a “normal lane” which connects every station. Suppose we want to search for 50, we start from first node of “express lane” and keep moving on “express lane” till we find a node whose next is greater than 50. Once we find such a node on “express lane”, we move to “normal lane” using pointer from this node, and linearly search for 50 on “normal lane”. 

What is the **time complexity** with two layers? The worst case time complexity is number of nodes on “express lane” plus number of nodes in a segment of “normal lane”. So if we have n nodes on “normal lane”, √n (square root of n) nodes on “express lane” and we equally divide the “normal lane”, then there will be √n nodes in every segment of “normal lane” . √n is actually optimal division with two layers. With this arrangement, the number of nodes traversed for a search will be O(√n). Therefore, with O(√n) extra space, we are able to reduce the time complexity to O(√n).

The time complexity of skip lists can be reduced further by adding more layers. In fact, the time complexity of search, insert and delete can become **O(Logn)** in average case. 

 

**Double Ended Queue** - Queue is a particular kind of abstract data type or collection in which the entities in the collection are kept in order and the principal (or only) operations on the collection are the addition of entities to the rear terminal position and removal of entities from the front terminal position. This makes queue a First-In-First-Out (FIFO) data structure. However in a double ended queue addition and removal of entities can be performed at both ends.

**Difference between the add and offer methods in a Queue in Java?**

The two functions come from **two different interfaces** that PriorityQueue implements. add() comes from Collection and offer() comes from Queue. For a capacity-constrained queue, the difference is that add() always returns true and throws an exception if it can't add the element, whereas offer() is allowed to return false if it can't add the element. However, this doesn't apply to PriorityQueue; the two functions are synonymous.

Implementation of PriorityQueue.add:

public boolean add(E e) {

  return offer(e);

}

For AbstractQueue there actually is a difference:

public boolean add(E e) {

  if (offer(e))

​    return true;

  else

​    throw new IllegalStateException("Queue full");

}

## HashMap

 

HashMap uses Hashing technique. Hashing is a technique of converting a large String to small String that represents same String. A shorter value helps in indexing and faster searches. HashSet also uses HashMap internally. HashMap internally uses **link list** to store key-value pairs. HashMap allows null key also but only once and multiple null values. 

Internally HashMap contains an array of Node. and a node is represented as a class which contains 4 fields :

Ø int hash

Ø K key

Ø V value

Ø Node next

### Time complexity of HashMap

HashMap provides constant time complexity for basic operations, get and put, if hash function is properly written and it disperses the elements properly among the buckets. Iteration over HashMap depends on the capacity of HashMap and number of key-value pairs. Basically it is directly proportional to the capacity + size. Capacity is the number of buckets in HashMap. So it is not a good idea to keep high number of buckets in HashMap initially.

Performance of HashMap depends on 2 parameters:

Ø Initial Capacity

Ø Load Factor

Initial Capacity is the capacity of HashMap instance when it is created. The Load Factor is a measure that when rehashing should be done. In HashMap capacity is multiplied by 2. Load Factor is also a measure that what fraction of the HashMap is allowed to fill before rehashing. When the number of entries in HashMap greater than the product of current capacity and load factor the capacity then rehashing is required. If we keep the initial capacity higher then rehashing will never be done. But by keeping it higher it increases the time complexity of iteration. Most generally preffered load factor value is 0.75 which provides a good deal between time and space costs.

### Synchronized HashMap

Collections.synchronizedMap() make HashMap synchronized:

Map m = Collections.synchronizedMap(new HashMap(...));

### How HashMap Works in Java

 

It works based on the hashing principle. Hashing is the mechanism of assigning unique code to a variable or attribute using an algorithm to enable easy retrieval. A true hashing mechanism should always return the same hashCode() when it is applied to the same object. 

HashMap stores the Objects as Node (Entry) instances, not as key and value. HashMap has an inner class called a Node Class which holds the key and values. And there is also something called next.

transient Node<K,V>[] table;

static class Node<K,V> implements Map.Entry<K,V> 

 {

   final K key;

   V value;

   Entry<K,V> next;

   final int hash;

   ........

 }

#### How Does Put() Method Work Internally?

 First, it checks if the key given is null or not. If the given key is null, it will be stored in the zero position, as the hashcode of null will be zero. If two different object has the same hashcode and will it be stored in the same bucket. The next attribute in the Entry class points to the next object. Using this different objects with the same hashcode will be placed next to each other.In the case of the Collision, the HashMap checks for the value of the next attribute if it is null it inserts the Entry object in that location, if next attribute is not null then it keeps the loop running till next attribute is null then stores the Entry object there.

HashMap doesn't allow duplicate keys, even though when we insert the same key with different values, only the latest value is returned. All the Entry Objects in the LinkedList will have the same hashcode, but HashMap uses equals() . This method checks the equality, so if key.equals(k) is true, then it will replace the value object inside the Entry class and not the key. So this way it prevents the duplicate key from being inserted.

#### How Does Get() Method Work Internally?

 

First, it gets the hash code of the key object, which is passed, and finds the bucket location. If the correct bucket is found, it returns the value (e.value) If no match is found, it returns null. If Two Keys Have the Same Hashcode The same collision resolution mechanism will be used here. key.equals(k) will check until it is true, and if it is true, it returns the value of it.

### HashMap and TreeMap in Java

 

Ø TreeMap always keeps the elements in a sorted(increasing) order, while the elements in a HashMap have no order. TreeMap also provides some cool methods for first, last, floor and ceiling of keys.

Ø HashMap implements Map interface while TreeMap implements SortedMap interface. 

Ø HashMap implements Hashing, while TreeMap implements Red-Black Tree(a Self Balancing Binary Search Tree) which makes sure that there are no duplicates; it also maintains the elements in a sorted order. 

Ø Both HashMap and TreeMap have their counterparts HashSet and TreeSet. HashSet and TreeSet implement Set interface. 

Ø Complexity: get/put/containsKey() operations are O(1) for hashmap in average case but we can’t guarantee that since it all depends on how much time does it take to compute the hash. TreeMap has more time complexity O(nLogn) compared to previous one which has O(n). The advantage of this method is, we get elements in sorted order. So operations like add, remove, containsKey, time complexity is O(log n) where n is number of elements present in TreeMap.

### Advantages of BST over Hash Table

Ø Hash Table supports Search, Insert, Delete operations in Θ(1) time.  The time complexity of above operations in a self-balancing Binary Search Tree (BST) (like Red-Black Tree, AVL Tree, Splay Tree, etc) is O(Logn).

Ø We can get all keys in sorted order by just doing Inorder Traversal of BST. This is not a natural operation in Hash Tables and requires extra efforts.

Ø Doing order statistics, finding closest lower and greater elements, doing range queries are easy to do with BSTs. Like sorting, these operations are not a natural operation with Hash Tables.

Ø BSTs are easy to implement compared to hashing, we can easily implement our own customized BST. To implement Hashing, we generally rely on libraries provided by programming languages.

Ø With Self-Balancing BSTs, all operations are guaranteed to work in O(Logn) time. But with Hashing, Θ(1) is average time and some particular operations may be costly, especially when table resizing happens.

### Internal Working of HashMap in Java

 

Hashing

Hashing is a process of converting an object into integer form by using the method hashCode(). 

As HashMap also allows null key, so hash code of null will always be 0.

 

hashCode() method is used to get the hash Code of an object. hashCode() method of object class returns the memory reference of object in integer form. Definition of hashCode() method is public native hashCode(). It indicates the implementation of hashCode() is native because there is not any direct method in java to fetch the reference of object. In HashMap, hashCode() is used to calculate the bucket and therefore calculate the index.

HashMap uses equals() to compare the key whether the are equal or not. If equals() method return true, they are equal otherwise not equal.

A bucket is one element of HashMap array. It is used to store nodes. Two or more nodes can have the same bucket. In that case link list structure is used to connect the nodes. Buckets are different in capacity. A relation between bucket and capacity is as follows:

capacity = number of buckets * load factor

A single bucket can have more than one nodes, it depends on hashCode() method. The better your hashCode() method is, the better your buckets will be utilized.

#### Index Calculation in Hashmap

Hash code of key may be large enough to create an array. hash code generated may be in the range of integer and if we create arrays for such a range, then it will easily cause **outOfMemoryException**. So we generate index to minimize the size of array. Basically following operation is performed to calculate index.

 

**index = hashCode(key) & (n-1)**. // where n is number of buckets or the size of array. 

Place this object at index 6, if no other object is presented there.

 

 

If there is a collision, check via hashCode() and equals() method that if both the keys are same.

If keys are same, replace the value with current value.

Otherwise connect this node object to the previous node object via linked list and both are stored at index 6.

**Using get method()**

Now lets try some get method to get a value. get(K key) method is used to get a value by its key. If you don’t know the key then it is not possible to fetch a value.

 

Fetch the data for key sachin:

map.get(new Key("sachin"));

Steps:

 

Calculate hash code of Key {“sachin”}. It will be generated as 115.

Calculate index by using index method it will be 3.

Go to index 3 of array and compare first element’s key with given key. If both are equals then return the value, otherwise check for next element if it exists.

In our case it is found as first element and returned value is 30.

Fetch the data for key vaibahv:

map.get(new Key("vaibhav"));

Steps:

 

Calculate hash code of Key {“vaibhav”}. It will be generated as 118.

Calculate index by using index method it will be 6.

Go to index 6 of array and compare first element’s key with given key. If both are equals then return the value, otherwise check for next element if it exists.

In our case it is not found as first element and next of node object is not null.

If next of node is null then return null.

If next of node is not null traverse to the second element and repeat the process 3 until key is not found or next is not null.

// Java program to illustrate

// internal working of HashMap

import java.util.HashMap;

 

class Key

{

  String key;

  Key(String key)

  {

​    this.key = key;

  }

 

  @Override

  public int hashCode()

  {

​    int hash = (int)key.charAt(0);

​    System.out.println("hashCode for key: "

​              \+ key +" = "+ hash);

​    return hash;

  }

 

  @Override

  public boolean equals(Object obj)

  {

​    return key.equals(((Key)obj).key);

  }

}

 

// Driver class

public class GFG

{

  public static void main(String[] args)

  {

​    HashMap map = new HashMap();

​    map.put(new Key("vishal"), 20);

​    map.put(new Key("sachin"), 30);

​    map.put(new Key("vaibhav"), 40);

 

​    System.out.println();

​    System.out.println("Value for key sachin: " +

​              map.get(new Key("sachin")));

​    System.out.println("Value for key vaibhav: " +

​              map.get(new Key("vaibhav")));

  }

}

Run on IDE

Output:

 

hashCode for key: vishal = 118

hashCode for key: sachin = 115

hashCode for key: vaibhav = 118

 

hashCode for key: sachin = 115

Value for key sachin: 30

hashCode for key: vaibhav = 118

Value for key vaibhav: 40

Important Points

 

Time complexity is almost constant for put and get method until rehashing is not done.

In case of collision, i.e. index of two or more nodes are same, nodes are joined by link list i.e. second node is referenced by first node and third by second and so on.

If key given already exist in HashMap, the value is replaced with new value.

hash code of null key is 0.

When getting an object with its key, the linked list is traversed until the key matches or null is found on next field.

This article is contributed by Vishal Garg. If you like GeeksforGeeks and would like to contribute, you can also write an article using contribute.geeksforgeeks.org or mail your article to contribute@geeksforgeeks.org. See your article appearing on the GeeksforGeeks main page and help other Geeks.

 

Please write comments if you find anything incorrect, or you want to share more information about the topic discussed above.

## Hashmap & Hashtable

 

Ø HashMap is non synchronized. It is not-thread safe whereas Hashtable is synchronized. It is thread-safe.

Ø HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.

Ø HashMap is generally preferred over HashTable if thread synchronization is not needed

### Why HashTable doesn’t allow null and HashMap does?

Hashtable is the older class. Perhaps they saw the need for a null key, and more importantly - null values, and added it in the HashMap implementation. HashMap is newer, and has more advanced capabilities, an improvement on the Hashtable functionality. **Neither Hashtable nor ConcurrentHashMap support null keys or values. HashMap does**.

To successfully store and retrieve objects from a HashTable, the objects used as keys must implement the hashCode method and the equals method. Since null is not an object, it can’t implement these methods, so the Hashtable can't compute a hash to use it as a key.

**Can multiple threads read from the Hashtable concurrently ?**

No multiple threads can not read simultaneously from Hashtable. Reason, the get() method of Hashtable is synchronized. As a result , at a time only one thread can access the get() method. ConcurrentHashMap achieve full concurrency for reads using **volatile** keyword.

## ConcurrentHashMap

A hash table supporting full concurrency of retrievals and adjustable expected concurrency for updates. This class obeys the same functional specification as Hashtable. However, even though all operations are thread-safe, retrieval operations do not entail locking, and there is not any support for locking the entire table in a way that prevents all access. This class is fully interoperable with Hashtable in programs that rely on its thread safety but not on its synchronization details.

Retrieval operations (including get) generally do not block, so may overlap with update operations (including put and remove). Retrievals reflect the results of the most recently completed update operations holding upon their onset. For aggregate operations such as putAll and clear, concurrent retrievals may reflect insertion or removal of only some entries. Similarly, Iterators and Enumerations return elements reflecting the state of the hash table at some point at or since the creation of the iterator/enumeration. They do not throw ConcurrentModificationException. However, iterators are designed to be used by only one thread at a time.

The allowed concurrency among update operations is guided by the optional **concurrencyLevel constructor argument** (default 16), which is used as a hint for internal sizing. The table is internally partitioned to try to permit the indicated number of concurrent updates without contention. Because placement in hash tables is essentially random, the actual concurrency will vary. Ideally, you should choose a value to accommodate as many threads as will ever concurrently modify the table. Using a significantly higher value than you need can waste space and time, and a significantly lower value can lead to thread contention. A value of one is appropriate when it is known that only one thread will modify and all others will only read. Also, resizing this or any other kind of hash table is a relatively slow operation, so, when possible, it is a good idea to provide estimates of expected table sizes in constructors.

Like Hashtable but unlike HashMap, this class does not allow null to be used as a key or value

### why concurrenthashmap does not allow null

The main reason that nulls aren't allowed in ConcurrentMaps (ConcurrentHashMaps, ConcurrentSkipListMaps) is that **ambiguities** that may be just barely tolerable in non-concurrent maps can't be accommodated. The main one is that if map.get(key) returns null, you can't detect whether the key explicitly maps to null vs the key isn't mapped. In a non-concurrent map, you can check this via map.contains(key), but in a concurrent one, the map might have changed between calls.

If the map can hold nulls, there is no way to tell if get is returning a null because there was no key for that value, or just because the value was null.

if (m.containsKey(k)) {

  return m.get(k);

} else {

  throw new KeyNotPresentException();

}

Since m is a concurrent map, key k may be deleted between the containsKey and get calls, causing this snippet to return a null that was never in the table, rather than the desired KeyNotPresentException.

## ConcurrentSkipListMap

A scalable concurrent ConcurrentNavigableMap implementation. The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, depending on which constructor is used.

This class implements a concurrent variant of SkipLists providing expected average log(n) time cost for the containsKey, get, put and remove operations and their variants. Insertion, removal, update, and access operations safely execute concurrently by multiple threads. Iterators are weakly consistent, returning elements reflecting the state of the map at some point at or since the creation of the iterator. They do not throw ConcurrentModificationException, and may proceed concurrently with other operations. Ascending key ordered views and their iterators are faster than descending ones.

All Map.Entry pairs returned by methods in this class and its views represent snapshots of mappings at the time they were produced. They do not support the Entry.setValue method. (Note however that it is possible to change mappings in the associated map using put, putIfAbsent, or replace, depending on exactly which effect you need.)

 

Beware that, unlike in most collections, the size method is not a constant-time operation. Because of the asynchronous nature of these maps, determining the current number of elements requires a traversal of the elements, and so may report inaccurate results if this collection is modified during traversal. Additionally, the bulk operations putAll, equals, toArray, containsValue, and clear are not guaranteed to be performed atomically. For example, an iterator operating concurrently with a putAll operation might view only some of the added elements.

This class and its views and iterators implement all of the optional methods of the Map and Iterator interfaces. Like most other concurrent collections, this class does not permit the use of null keys or values because some null return values cannot be reliably distinguished from the absence of elements.

 

 

 

 
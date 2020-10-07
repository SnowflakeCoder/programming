# Data Structures

A data structure is a particular **way of organizing data** in an application so that it can be used effectively. The idea is to reduce the space and time complexities of different tasks. 

## Linear data structures

Arrays, Linked Lists, Stack and queues are linear data structures,

- **Array** :- used to <u>store homogeneous elements at contiguous locations</u>.

- **Linked List** :- A linked list is a linear data structure where each element is a separate object, comprising of two items – the data and a reference to the next node.

  Types of Linked List

  1. **Singly Linked List** : Every node stores reference of next node in list.
  2. **Doubly Linked List** : Every node stores references of next and previous node.
  3. **Circular Linked List** : Here all nodes are connected to form a circle. There is no NULL at the end. The next pointer of last node is pointing to the first. A circular linked list can be a singly circular linked list or doubly circular linked list. **Advantage** of this data structure is that **any node can be made as starting node**.

- **Stack** :- A stack or LIFO (last in, first out) is an abstract data type with two principal operations: push and pop. <u>Both the operations takes place at the same end</u> that is top of the stack. It can be implemented by using both array and linked list.

- **Queue** :- A queue or FIFO (first in, first out) is an abstract data type with two principal operations: enqueue (adding from the rear side) and dequeue (removing from the front side). It can be implemented by using both array and linked list.

- **Circular Queue** :- This data structure <u>reduces wastage of space in case of array implementation</u>, As the insertion of the (n+1)’th element is done at the 0’th index if it is empty.

## Hierarchical data structures

### Tree

Trees are hierarchical data structures used to store hierarchical data. For example, the file system on a computer. <u>A tree is represented by a pointer to the topmost node(root) in tree</u>. If the tree is empty, then value of root is NULL. 

### Binary Tree

A binary tree is a tree data structure in which **each node has at most two children** (left child and the right child). It is implemented mainly using **Links**. A Binary Tree node contains data, Pointer to left child and Pointer to right child.

**Properties of Binary Tree**

- The maximum number of nodes at level ‘l’ of a binary tree is `2^(l-1)`.
- Maximum number of nodes in a binary tree of height ‘h’ is `2^h – 1`.
- In a Binary Tree with N nodes, minimum possible height or minimum number of levels is `Log2(N+1)`

·    A Binary Tree with L leaves has at least `Log2L+ 1` levels.

·    In Binary tree, number of leaf nodes is always one more than nodes with two children.

#### Types of Binary Tree

- **Full Binary Tree**: A Binary Tree is full if **every node has 0 or 2 children**. We can also say a full binary tree is a binary tree in which <u>all nodes except leaves have two children</u>.
- **Complete Binary Tree**: A Binary Tree is complete Binary Tree if <u>all levels are completely filled except possibly the last level and the last level has all keys as left as possible</u>. Practical example of Complete Binary Tree is **Binary Heap**.
- **Perfect Binary Tree**: A Binary tree is Perfect Binary Tree in which <u>all internal nodes have two children and all leaves are at same level</u>. A Perfect Binary Tree of height h (where height is number of nodes on path from root to leaf) has 2h – 1 node.
- **Balanced Binary Tree:** A binary tree is balanced if height of the tree is O(Log n) where n is number of nodes.
- **Degenerate (or pathological or skewed) tree:** A Tree where every internal node has one child. Such trees are <u>performance-wise same as **linked list**</u>.

#### BFS vs DFS for Binary Tree

<img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms/images/tree%20ds.gif?raw=true" alt="tree ds.gif" style="zoom:67%;" />

A Tree can be traversed in two ways:

- **Depth First Traversal ** (Or Level Order Traversal)
  - Inorder (Left-Root-Right)  =>  `4 2 5 1 3` 
  - Preorder (Root-Left-Right)  =>  `1 2 4 5 3` 
  - Postorder (Left-Right-Root)  =>  `4 5 2 3 1` 
- **Breadth First Traversa**l: Level Order Traversal  => `1 2 3 4 5`

**Time complexity** :- All four traversals require **O(n) time** as they visit every node exactly once.

**Space complexity**

- Extra Space required for BFS is **O(w)** where w is maximum width of Binary Tree. In BFS we need to store nodes of different level. Maximum Width of a Binary Tree at depth (or height) h can be 2^h where h starts from 0. So the maximum number of nodes can be at the last level. And worst case occurs when Binary Tree is a perfect Binary Tree with numbers of nodes (n) like 1, 3, 7, 15, …etc. In worst case, value of 2^h is Ceil(n/2).
- Extra Space required for DFS Traversals is **O(h)** where h is maximum height of Binary Tree. In DFS, stack (or function call stack) stores all ancestors of a node. Height for a Balanced Binary Tree is O(Log n). Worst case occurs for skewed tree and worst case height becomes O(n).

**How to Pick One?**

- The extra space required for BFS is likely to be more when tree is more balanced and extra space for DFS is likely to be more when tree is less balanced.
- DFS are **typically recursive** and recursive code requires function call overheads.
- The most important points is, BFS starts visiting nodes from root while DFS starts visiting nodes from leaves. So if our problem is to search something that is more likely to closer to root, we would prefer BFS. And if the target node is close to a leaf, we would prefer DFS.

### Binary Search Tree

Binary Search Tree is a Binary Tree with following additional properties:

- The left subtree of a node contains only nodes with keys less than the node’s key.
- The right subtree of a node contains only nodes with keys greater than the node’s key.
- The left and right subtree each must also be a binary search tree.

BST provide <u>moderate **access/search** (quicker than Linked List and slower than arrays)</u>. BST provide moderate **insertion/deletion** (quicker than Arrays and slower than Linked Lists). Its main use is in **search application** where data is constantly entering/leaving and data needs to printed in sorted order. For example in implementation in E- commerce websites where a new product is added or product goes out of stock and all products are lised in sorted order.

**Complexity** (h: Height of BST, n: Number of nodes in BST)

- **Time Complexity** for Search, Insertion, Deletion : O(h)
- **Space Complexity** : O(n) for pointers

If Binary Search Tree is Height Balanced, then `h = O(Log n)`. Balanced Binary Search trees are performance wise good as they provide O(log n) time for search, insert and delete. **Self-Balancing BSTs** such as AVL Tree, Red-Black Tree and Splay Tree make sure that height of BST remains O(Log n). **AVL tree** maintain O(Log n) height by making sure that the difference between heights of left and right subtrees is 1. **Red-Black trees** maintain O(Log n) height by making sure that the number of Black nodes on every root to leaf paths are same and there are no adjacent red nodes. 

### Binary Heap

A Binary Heap is a **complete Binary Tree**. This property of Binary Heap makes them <u>suitable to be stored in an **array**</u>. A Binary Heap is either **Min Heap** or **Max Heap**. In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Binary Heap is mainly **implemented using array**.

Binary Heap is used in <u>implementing efficient priority-queues</u>. The Heap data structure can be used to **efficiently find the k’th smallest** (or largest) element in an array.

**Time Complexity**

- Get Minimum in Min Heap: `O(1)` [Or Get Max in Max Heap]
- Extract Minimum Min Heap: `O(Log n)` [Or Extract Max in Max Heap]
- Decrease Key in Min Heap: `O(Log n)` [Or Extract Max in Max Heap]
- Insert / Delete : `O(Log n)` 

### Graph

Graph consists of **two components**:

- A finite set of vertices also called as **nodes**.
- A finite set of ordered pair of the form (u, v) called as **edge**. The pair is ordered because (u, v) is not same as (v, u) in case of **directed graph**(di-graph). The pair of form (u, v) indicates that there is an edge from vertex u to vertex v. The <u>edges may contain weight/value/cost</u>.

Graph classifications

- **Undirected Graph** : The graph in which all the <u>edges are bidirectional</u>.
- **Directed Graph** : The graph in which all the <u>edges are unidirectional</u>.
- **Weighted Graph** : The Graph in which weight is associated with the edges.
- **Unweighted Graph** : The Graph in which there is no weight associated to the edges.

### Trie

Tries is **a tree that stores strings**. Maximum number of children of a node is equal to size of alphabet. Trie supports search, insert and delete operations in O(L) time where L is length of key. Trie is an efficient data structure for <u>searching words in dictionaries</u>. Also, the most important thing is Prefix Search. With Trie, we can find all words beginning with a prefix (This is not possible with Hashing). The only problem with Trie is they **require a lot of extra space**. Trie are also known as **radix tree** or **prefix tree**.

```java
// Trie Node structure
private static class TrieNode {
    private Map<String, TrieNode> children = new HashMap<>();
    private int visitCount = 0; //value or data associated for this node.
    private String domainName; //value or data associated for this node.
}
```

- Time complexity of **Insert, search and delete** : O(M) where M is the length of the string.
- **Space** : O(ALPHABET_SIZE * M * N) where N is number of keys in trie, ALPHABET_SIZE is 26 if we are only considering upper case Latin characters.

Tries are well suited for implementing <u>dictionaries due to **prefix search** capability</u>, approximate matching algorithms (used in spell checking), searching Contact from Contact list OR Phone Directory.

#### Advantages

- Trie is much faster that BST. If we store keys in binary search tree, <u>a well balanced BST will need **M * log N** time</u>, where M is maximum string length and N is number of keys in tree. <u>Using trie, we can search the key in **O(M)** time</u>.
- This is also faster than Hashing because we <u>do not need to compute any hash function</u>. No collision handling is required (like we do in open addressing and separate chaining) Hashing also provides word search in O(n) time on average. But the <u>advantages of Trie are there are **no collisions** (like hashing)</u> so worst case time complexity is O(n). Another advantage is, we can easily print all words in alphabetical order which is not easily possible with hashing.
- We can efficiently do prefix search (or auto-complete) with Trie.

#### Disadvantages

Tries need **lot of memory for storing the strings**. For each node we have too many node pointers(equal to number of characters of the alphabet), <u>If space is concern, then Ternary Search Tree can be preferred for dictionary implementations</u>. In Ternary Search Tree, time complexity of search operation is O(h) where h is height of the tree. Ternary Search Trees also supports other operations supported by Trie like prefix search, alphabetical order printing and nearest neighbor search.

 

 

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


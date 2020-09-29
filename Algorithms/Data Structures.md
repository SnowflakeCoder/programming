# Data Structures

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


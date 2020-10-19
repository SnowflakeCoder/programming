# Data Structures

## Arrays and Strings

Array questions and string questions are often **interchangeable**.

### Hash Tables

A hash table is a data structure that <u>maps keys to values for highly efficient lookup</u>. We use an **array of linked lists** and a hash code function to implement hashtable. To insert a key:

1. First, compute the key's hash code. Note that two different keys could have the same hash code, as there may be an infinite number of keys and a finite number of hash codes.
2. Then, <u>map the hash code to an index in the array</u>, something like `hash(key) % array_len`. <u>Two different hash codes could, of course, map to the same index</u>.
3. At this index, there is a <u>linked list of keys and values</u>. Store the key and value in this index. We must use a linked list because of collisions.

To retrieve the value pair by its key, you repeat this process. Compute the hash code from the key, and then compute the index from the hash code. Then, <u>search through the linked list</u> for the value with this key. If the **number of collisions is very high**, the **worst case runtime is O(N)**, where N is the number of keys. A good implementation can <u>keeps collisions to a minimum</u>, in which case the **lookup time is O(1)**.

Alternatively, we can **implement the hash table with a balanced binary search tree**. This gives us an **O(log N) lookup time**. The advantage of this is potentially **using less space**, since we no longer allocate a large array. We can also **iterate through the keys in order**.

### ArrayList

In Java, arrays are fixed length. The <u>size is defined when you create the array</u>. An ArrayList is an array that resizes itself as needed while still providing **O(1) access**. When the array is full, the array doubles in size. The "resizing factor" is 2. <u>Each doubling takes 0 (n) time</u>, but happens so rarely that its amortized <u>insertion time is still 0(1)</u>.

The **total number of copies to insert N elements** is `N/2 + N/4 + N/8 .......+ 2 + 1` < N (Which is just less than N). Therefore, <u>inserting N elements takes O(N) work total</u>. Each insertion is 0(1) on average, even though <u>some insertions take 0 (N) time in the worst case</u>.

### StringBuilder

Imagine you were concatenating a list of strings, strings are all the same length X and there are n strings. On each concatenation, a new copy of the string is created, character by character. The first iteration requires us to copy X characters. The second iteration requires copying 2X characters, and so on. The total time therefore is `O(x + 2x + ....... + nx)` = `O(x*n^2)`. Because `1 + 2 + ••• + n` = `n(n+1)/2`, or `O(n^2)`.
StringBuilder solves this problem by simply **creates a resizable array** of all the strings, copying them back to a string only when necessary.

**Additional Reading**: Hash Table Collision Resolution (pg 636), Rabin-Karp Substring Search.

## Linked Lists

A linked list is a data structure that represents a **sequence of nodes**. Unlike an array, a linked list **does not provide constant time access** to a particular "index" within the list. So if you'd like to find the Kth element in the list, you will need to iterate through K elements. The benefit of a linked list is that you can <u>add and remove items from the beginning of the list in constant time</u>.

When you implement linklist **make sure none of the methods return a node**. It should always be data, returning node can create problems while delete nodes. Few linked list problems rely on **recursion**. However, recursive algorithms take at least **O(n) space**, where n is the depth of the recursive call. All recursive algorithms can be implemented iteratively, although its more complex.

### The "Runner"Technique

The "runner" (or second pointer) technique means that you <u>iterate through the linked list with two pointers simultaneously</u>, with one ahead of the other. For example:

`a1->a2...->an->b1->b2....->bn` you wanted to rearrange it into `a1->b1->a2->b2...->an->bn`. But <u>you do not know the length of the linked list</u> (but you do know that the length is an even number). You could have one pointer p1 (the fast pointer) move every two elements for everyone move that p2 makes. <u>When p1 hits the end of the linked list, p2 will be at the midpoint</u>. Then, move p1 back to the front and begin "weaving" the elements. On each iteration, p2 selects an element and inserts it after p1.

## Stack

Stack uses the following operations: `pop(), push(iterm), peek(), isEmpty()`

A stack uses LIFO (last-in first-out) ordering. Unlike an array, a stack <u>does not offer constant-time access</u> to the ith item. However, it does <u>allow constant time adds and removes</u>, as it **doesn't require shifting elements around**. A stack can be **implemented using a singly linked list**. Note that items were added and removed from the same side. So **only first node is required**.

```java
public T pop(){
    if(top == null) throw new EmptyStackException();
    T data = top.data;
    top = top.next();
    return data;
}
public void push(T data){
    StackNode<T> n = new StackNode<>(data);
    n.next=top;
    top=n;
}
```

Stacks are often <u>useful is in certain recursive algorithms</u>. Sometimes you need to push temporary data onto a stack as you recurse, but then <u>remove them as you backtrack</u>. A stack can also be used to **implement a recursive algorithm iteratively**.

## Queue

A queue implements FIFO (first-in first-out) ordering. It uses the operations: `add(item), remove(), peek(), isEmpty()`. A queue can also be implemented with a linked list, but items are added and removed from opposite sides. **So both first and last node are required**.

```java
public void push(T data){
    QueueNode<T> n = new QueueNode<>(data);
    if(first == null){
        first = last = n;
    }
    else{
        last.next = n;
        last = n;
    }
}
public T remove(){
    if(first == null) throw new EmptyQueueException();
    T data = first.data;
    first = first.next();
    if(first == null) last = null;
    return data;
}
```

Queues are often used is in **breadth-first search** or in implementing a **cache**. In breadth-first search, we used a queue to store a <u>list of the nodes that we need to process</u>. Each time we process a node, we add its child nodes to the back of the queue. This allows us to <u>process</u>
<u>nodes in the order in which they are viewed</u>.

## Trees

A tree is a data structure composed of nodes. Each tree has a root node. The tree **cannot contain cycles**. A tree is a connected graph without cycles.

- A **binary tree** is a tree in which each node has **up to two children**. 
- A **binary search tree** is a binary tree in which every node fits a specific ordering property: `all left descendents <= n < all right descendents`. This must be true for each node n. So in a binary search tree, for each node, <u>all of its left descendents are less than or equal to the current node, which is less than all the right descendents</u>. 
- Balanced trees are balanced enough to <u>ensure O(log n) times for insert and find</u>. Two common types of balanced trees are **red-black trees** and **AVL trees**.
- A complete binary tree is a binary tree in which every level of the tree is **fully filled**, **except for perhaps the last level**. To the extent that the last level is filled, it is **filled left to right**. A binary tree <u>totally filled other than the rightmost elements on the last level</u>.
- A full binary tree is a binary tree in which every node has either zero or two children. That is, **no nodes have only one child**. <u>A full binary tree is not always a complete binary tree</u>.
- A perfect binary tree is one that is **both full and complete** and <u>all leaf nodes will be at the same level</u>, and this level has the maximum number of nodes. A perfect tree must have exactly `2^k-1` nodes (where k is the number of levels).

### Binary Tree Traversal

- When performed on a binary search tree, it visits the nodes in **ascending order** (hence the name "in-order").
- In a pre-order traversal, the root is always the first node visited.
- In a post-order traversal, the root is always the last node visited. 

```java
private void inOrder(TreeNode root){
    if(node == null) return; // make sure to add null check. 
    inOrder(root.left);
    print(root);
    inOrder(root.right);
}
private void preOrder(TreeNode root){
    if(node == null) return; // make sure to add null check. 
    print(root);
    inOrder(root.left);
    inOrder(root.right);
}
private void postOrder(TreeNode root){
    if(node == null) return; // make sure to add null check. 
    inOrder(root.left);
    inOrder(root.right);
    print(root);
}
```

### Binary Heaps (Min-Heaps and Max-Heaps)

A min-heap is a **complete binary tree** where **each node is smaller than its children**(ascending order). The root is the minimum element in the tree. <u>Binary heap is not a binary search tree</u>, parent is smaller than both child for min-heap. There's no inherent ordering between the left and right element except both are bigger than parent. We have two key operations on a min-heap:

- **Insert**: When we insert into a min-heap, we always start by **inserting the element at the bottom rightmost** spot so as to maintain the complete tree property. Then, we **"fix" the tree** by <u>swapping the new element with its parent</u>, until we find an appropriate spot for the element. We essentially **bubble up** the minimum element. This takes **O(log n)** time, where n is the number of nodes in the heap.
- **Extract Minimum Element**
  Minimum element of a min-heap is always at the top (root). The **trickier part is how to remove it**. First, we <u>remove the minimum element and swap it with the last element</u> in the heap (the **bottommost, rightmost element**). Then, we **bubble down** this element, swapping it with one of its children <u>until the minheap property is restored</u>. Note that you **only can take the smaller one** in order to maintain the min-heap ordering. This also takes **O(log n)** time.

### Tries (Prefix Trees)

A trie (sometimes called a prefix tree) is a variant of an n-ary tree in which **characters are stored at each node**. Each path down the tree may represent a word. 

<img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/trie.png?raw=true" alt="trie.png" style="zoom: 50%;" />

The * nodes (sometimes called "**null nodes**") are often used to indicate **complete words**. The actual implementation of these null nodes/TerminatingTrieNode, <u>inherits from TrieNode</u>. Or, we could <u>use just a boolean flag terminates within the "parent" node</u>. For example, there is a * node under MANY indicates that MANY is a complete word.

A **hashtable** can quickly look up whether a string is a valid word, it <u>cannot tell us if a string is a prefix of any valid words</u>. A trie can do this very quickly. A trie can check if a string is a valid prefix in **O(K)** time, where K is the length of the string. This is actually the **same runtime as a hash table will take**. Although hashtable lookups as being O(1) time, this isn't entirely true. A hash table must read through all the characters in the input, which takes O(K) time in the case of a word lookup.

## Graphs

A graph is simply a <u>collection of nodes with edges</u> between (some of) them. Graphs can be either <u>directed or undirected</u>. While directed edges are like a one-way street, undirected edges are like a two-way street. The graph might consist of multiple isolated subgraphs. lf there is a path between every pair of vertices, it is called a "**connected graph**". The graph can also have cycles. An "**acyclic graph**" is one without cycles.

```java
//A simple class definition for a graph node(looks same as a tree node).
class Graph {
    public Node[] nodes;
}
class Node {
    public String name;
    public Node[] children;
}
```

The Graph class is used because, <u>unlike in a tree, you can't necessarily reach all the nodes from a single node</u>. There are <u>two common ways to represent a graph</u>.

- **Adjacency List** : This is the most common way to represent a graph. Every vertex (or node) stores a list of adjacent vertices. In an undirected graph, an edge like (a, b) would be stored twice: once in a's adjacent vertices and once in b's adjacent vertices. An array of lists (or a hash table) can store the adjacency list,

- **Adjacency Matrices**: An adjacency matrix is an NxN boolean matrix (where N is the number of nodes), where a <u>true value at matrix [i] [j ] indicates an edge from node i to node j</u>. (You can also use an integer matrix with 0s and 1s.) In an undirected graph, an **adjacency matrix will be symmetric**. In a directed graph, it will not (necessarily) be. The same graph algorithms that are used on adjacency lists (breadth-first search, etc.) can be performed with adjacency matrices, but they may be somewhat **less efficient**. In the adjacency list representation, you
  can <u>easily iterate through the neighbors of a node</u>. In the adjacency matrix, you will need to <u>iterate through all the nodes (both true and false)</u> to identify a node's neighbors.

  <img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/graph%20representation.png?raw=true" alt="graph representation.png" style="zoom:50%;" />

### Graph Search

The two most common ways to search a graph are **depth-first search** and **breadth-first search**. 

- In depth-first search (DFS), we start at the root (or another arbitrarily selected node) and explore each branch completely before moving on to the next branch. That is, we go deep first (hence the name depthfirst search) before we go wide.

- In breadth-first search (BFS)' we start at the root (or another arbitrarily selected node) and explore each neighbor before going on to any of their children. That is, we go wide (hence breadth-first search) before we go deep.

  <img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/graph%20traversal.png?raw=true" alt="graph traversal.png" style="zoom:50%;" />

DFS is often preferred if we want to visit every node in the graph. Both will work just fine, but depth-first search is a bit simpler. However, if we want to find the **shortest path (or just any path) between two nodes**, BFS is generally better because its searching level by level.

When implementing search algorithm for a graph, we **must check if the node has been visited**. If we don't, we risk getting stuck in an infinite loop. DFS can be implemented using recursive but **BFS is not recursive**, it uses a **queue**.

```java
//DFS pseudocode.
public void dfs(Node root) {
    if(root == null) return;
    visit(root);
    root.visited = true;
    for(Node child : root.children){
        if(!child.visited){
			dfs(child);            
        }
    }
}
//BFS pseudocode.
public void bfs(Node root) {
    Queue q = new Queue();
    //mark it visited before adding to queue to avoid multiple visits.
    root.visited = true; 
    q.add(root); //Add to the end of queue (enqueue())
    while(!q.isEmpty()){
        Node n = q.remove(); // Remove from the front of the queue(dequeue())
        visit(n);
        for(Node child : n.children){
            if(!child.visited){
                child.visited = true;
                queue.add(child);            
            }
        }
    }
}
```

### Bidirectional Search

Bidirectional search is used to <u>find the **shortest path** between a source and destination node</u>. It operates by essentially **running two simultaneous breadth-first searches**, one from each node. When their searches collide, we have found a path. This is <u>faster than running a single BFS</u> but it requires **undirected (two way) graph**. Consider a graph where every node has at most k adjacent nodes and the shortest path from node S to node T has length d.

- In traditional **breadth-first search**, we would search up to k nodes in the first "level" of the search. In the second level, we would search k^2 nodes total (up to k nodes for each of those first k nodes). We would do this d times, so that's **O(k^d )** nodes.
- In **bidirectional search**, we have two searches that <u>collide after approximately d/2 levels</u> (the midpoint of the path). That's approximately `2*k^(d/2)` or `O(k^(d/2))` nodes total. Recall that (k^(d/2)) * (k^(d/2)) = k^d. So bidirectional search is actually **faster by a factor of k^(d/2)**.

**Additional Reading**: Topological Sort, Dijkstra's Algorithm, AVL Trees, RedBlackTrees

## Bit Manipulation

Bit manipulation is used in a variety of problems. Sometimes, the question explicitly calls for bit manipulation.
Other times, it's simply a useful technique to optimize your code. You should be comfortable
doing bit manipulation by hand, as well as with code. Be careful; it's easy to make little mistakes.
~ Bit Manipulation By Hand
If you're rusty on bit manipulation, try the following exercises by hand. The items in the third column can be
solved manually or with "tricks" (described below). For simplicity, assume that these are four-bit numbers.
If you get confused, work them through as a base 10 number. You can then apply the same process to a
binary number. Remember that" indicates an XOR, and", is a NOT (negation).
elle + ee1e ee11 * e1e1 elle + elle
eell + 1313113 ee11 * eell e1ee * ee11
elle - 131311 11131 » 2 11131 " ("'11131)
1eee - elle 11131 " 131131 11311 & ("'13 « 2)
Solutions: line 1 (1000,11 11 , 1100); line 2 (0101 , 1001 , 1100); line 3 (0011 , 0011 , 111 1); line 4 (001 0, 1000, 1000).
The tricks in Column 3 are as follows:

1. elle + elle is equivalent to 131113 * 2, which is equivalent to shifting 131113 left by 1.
2. e1ee equals 4, and multiplying by 4 is just left shifting by 2. So we shift 131311 left by 2 to get 111313.
3. Think about this operation bit by bit. If you XOR a bit with its own negated value, you will always get 1.
   Therefore, the solution to a" ( "'a) will be a sequence of 1 s.
4. ",13 is a sequence of 1 s, so ",13 < < 2 is 1 s followed by two Os. ANDing that with another value will clear
   the last two bits of the value.
   If you didn't see these tricks immediately, think about them logically.
   ~ Bit Facts and Tricks
   The following expressions are useful in bit manipulation. Don't just memorize them, though; think deeply
   about why each of these is true. We use "1 s" and "Os" to indicate a sequence of 1 s or Os, respectively.
   X " 05 = X
   X " 15 = -x
   X " X = 0
   x & 05 = 0
   x & 15 = X
   x & x = x
   112 Cracking the Coding Interview, 6th Edition
   x I es = x
   x I 15 15
   X I x = x
   Chapter 5 I Bit Manipulation
   To understand these expressions, recall that these operations occur bit-by-bit, with what's happening on
   one bit never impacting the other bits. This means that if one of the above statements is true for a single bit,
   then it's true for a sequence of bits.
   ~ Two's Complement and Negative Numbers
   Computers typically store integers in two's complement representation. A positive number is represented
   as itself while a negative number is represented as the two's complement of its absolute value (with a 1 in its
   sign bit to indicate that a negative value). The two's complement of an N-bit number (where N is the number
   of bits used for the number, excluding the sign bit) is the complement of the number with respect to 2N.
   Let's look at the 4-bit integer - 3 as an example. If it's a 4-bit number, we have one bit for the sign and three
   bits for the value. We want the complement with respect to 23, which is 8. The complement of 3 (the absolute
   value of - 3) with respect to 8 is 5. 5 in binary is 1131. Therefore, -3 in binary as a 4-bit number is 1101,
   with the first bit being the sign bit.
   In other words, the binary representation of -K (negative K) as a N-bit number is concat (1 J 2N•1 - K).
   Another way to look at this is that we invert the bits in the positive representation and then add 1. 3 is 1311
   in binary. Flip the bits to get lee, add 1 to get 1131, then prepend the sign bit (1) to get 11131.
   In a four-bit integer, this would look like the following.
   Positive Values Negative Values
   7 ~111 -1 1111
   6 13 1113 -2 1 1113
   5 13 1131 -3 1 1131
   4 ~ lee -4 1 lee
   3 13 1311 -5 1 1311
   2 ~ 13113 -6 1 13113
   1 13 eel -7 1 eel
   13 13 131313
   Observe that the absolute values of the integers on the left and right always sum to 23, and that the binary
   values on the left and right sides are identical, other than the sign bit. Why is that?
   ~ Arithmetic vs. Logical Right Shift
   There are two types of right shift operators. The arithmetic right shift essentially divides by two. The logical
   right shift does what we would visually see as shifting the bits. This is best seen on a negative number.
   In a logical right shift, we shift the bits and put a 13 in the most significant bit. It is indicated with a >>>
   operator. On an 8-bit integer (where the sign bit is the most Significant bit), this would look like the image
   below. The sign bit is indicated with a gray background.
   = -75
   = 90
   CrackingTheCodinglnterview.com 16th Edition 113
   Chapter 5 I Bit Manipulation
   In an arithmetic right shift, we shift values to the right but fill in the new bits with the value of the sign bit.
   This has the effect of (roughly) dividing by two. It is indicated by a > > operator.
   = -75
   = -38
   What do you think these functions would do on parameters x = -93242 and count 4e?
   1 int r epeatedArithmeticShift(int x, int count) {
   2 for (int i = e; i < count; i++) {
   3 x » = 1; II Arithmetic shift by 1
   4 }
   5 return x;
   6 }
   7
   8 int repeatedLogicalShift(int x, int count) {
   9 for (int i = e; i < count; i++) {
   Ie x »>= 1; I I Logical shift by 1
   11 }
   12 return x;
   13 }
   With the logical shift, we would get e because we are shifting a zero into the most significant bit repeatedly.
   With the arithmetic shift, we would get -1 because we are shifting a one into the most significant bit
   repeatedly. A sequence of allis in a (signed) integer represents -1.
   ~ Common Bit Tasks: Getting and Setting
   The following operations are very important to know, but do not simply memorize them. Memorizing leads
   to mistakes that are impossible to recover from. Rather, understand how to implement these methods, so
   that you can implement these, and other, bit problems.
   Get Bit
   This method shifts lover by i bits, creating a value that looks like eeElleeee. By performing an AND with
   num, we clear all bits other than the bit at bit i. Finally, we compare that to e. If that new value is not zero,
   then bit i must have a 1. Otherwise, bit i is a O.
   1 boolean getBit(int num, int i) {
   2 return «num & (1 « i» != 0);
   3 }
   Set Bit
   SetBi t shifts lover by i bits, creating a value like eeeleeee. By performing an OR with num, only the
   value at bit i will change. All other bits of the mask are zero and will not affect num.
   1 int setBit(int num, i nt i) {
   2 return num I (1 « i);
   3 }
   114 Cracking the Coding Interview, 6th Edition
   Chapter 5 I Bit Manipulation
   Clear Bit
   This method operates in almostthe reverse of setBi t . First, we create a number like 11101111 by creating
   the reverse of it (00010000) and negating it. Then, we perform an AND with num. This will clear the ith bit
   and leave the remainder unchanged.
   1 int clearBit(int num, int i) {
   2 int mask = ~(1 « i);
   3 return num & mask;
   4 }
   To clear all bits from the most significant bit through i (inclusive), we create a mask with a 1 at the ith bit (1
   < < i). Then, we subtract 1 from it, giving us a sequence of 0s followed by i 1s. We then AND our number
   with this mask to leave just the last i bits.
   1 int clearBitsMSBthroughI(int num, int i) {
   2 int mask = (1 « i) - 1;
   3 return num & mask;
   4 }
   To clear all bits from i through 0 (inclusive), we take a sequence of all1s (which is -1) and shift it left by i

+ 1 bits. This gives us a sequence of 1 s (in the most significant bits) followed by i 0 bits.
  1 int clearBitslthrough0(int num, int i) {
  2 int mask = (-1 « (i + 1»;
  3 return num & mask;
  4 }
  Update Bit
  To set the ith bit to a value v, we first clear the bit at position i by using a mask that looks like 11101111.
  Then, we shift the intended value, v, left by i bits. This will create a number with bit i equal to v and all
  other bits equal to 0. Finally, we OR these two numbers, updating the ith bit if v is 1 and leaving it as 0
  otherwise.
  1 int updateBit(int num, int i, boolean bitlsl) {
  2 int value = bitlsl ? 1 : 0;
  3 int mask = ~(1 « i);
  4 return (num & mask) I (value « i);
  5 }

## Math and Logic Puzzles

Don't panic when you get a brainteaser. Like algorithm questions, interviewers want to see **how you tackle a problem**; they don't expect you to immediately know the answer. Start talking, and show the interviewer how you approach a problem.

**Prime Number Law**
Every positive integer can be decomposed into a product of primes. Note that many of these primes have an exponent of zero.

For example:  `84 = 2^2 * 3^1 * 5^0 * 7^1 * 11^0 * 13^0 * 17^0 * ...`

**Divisibility**
The prime number law stated above means that, in order for a number x to divide a number y (x%y == 0), <u>all primes in x's prime factorization must be in y 's prime factorization</u>.

```matlab
x = 2^j0 * 3^j1 * 5^j2 * 7^j3 * 11^j4 * ....
y = 2^k0 * 3^k1 * 5^k2 * 7^k3 * 11^k4 * ....

If x%y == 0, then for all i, ji <= ki.

The greatest common divisor of x and y will be:
gcd (x, y) = 2^min(j0, k0) * 3^min(jl, kl) * 5^min(j2, k2) * ...

The least common multiple of x and y will be:
lcm(x, y) = 2^max(j0, k0) * 3^max(jl, kl) * 5^max(j2, k2) * ...

So gcd * lcm = x*y
```

**Checking for Primality**
The naive way is to simply **iterate from 2 through n -1**, checking for divisibility on each iteration. A small improvement is to **iterate only up through the Sqrt(n)**.

```java
boolean prime(int n) {
    if (n < 2) { return false;}
    int sqrt = (int) Math.sqrt(n);
    for (int i = 2; i <= sqrt; i++) {
    	if (n % i == 0) return false;
    }
    return true;
}   
```

In reality, all we really need to do is to **check if n is divisible by a prime number**. This is where the **Sieve of Eratosthenes** comes in.

**Generating a List of Primes: The Sieve of Eratosthenes**
The Sieve of Eratosthenes is a highly efficient way to generate a list of primes. It **works by recognizing that all non-prime numbers are divisible by a prime number**.

We start with a list of all the numbers (2 to max). First, we cross off all numbers divisible by 2. Then, we look for the next prime (the next non-crossed off number) and cross off all numbers divisible by it. By crossing off all numbers divisible by 2, 3, S, 7, 11, and so on, we wind up with a list of prime numbers from 2 through max. One simple optimizations is to **only use odd numbers** in the array, which would allow us to <u>reduce our space usage</u> by half.

```java
private boolean[] sieveOfEratosthenes(int max) {
    boolean[] flags = new boolean[max + 1];
    int count = 0;
    init(flags);// Set all flags to true other than 0 and 1
    int prime = 2;
    while (prime <= Math.sqrt(max) { // till sqrt(max) is enough.
        crossOff(flags, prime); // Cross off remaining multiples of prime 
        prime = getNextPrime(flags, prime);
    }
    return flags;
}
void crossOff(boolean[) flags, int prime) {
/*We can start with (prime*prime), because if we have a k*prime, where k<prime, this value would have already been crossed off in a prior iteration.*/
    for (int i = prime * prime; i < flags. length; i += prime) {
        flags[i) = false;
    }
}
int getNextPrime(boolean[) flags, int prime){
    for (int i = prime + 1; i < flags.length; i += 1) {
        if(flags[i)){ return i;} // if prime then flag is true
    }
}
```

**Probability**
Probability is based in a few basic laws that can be logically derived.

<img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/probability%20diagram.png?raw=true" alt="probability diagram.png" style="zoom:50%;" />

**Probability of A and B**
Imagine you were throwing a dart at this Venn diagram. What is the probability that you would land in the intersection between A and B? If you knew the odds of landing in A, and you also knew the percent of A that's also in B (that is, the odds of being in B given that you were in A), then you could express the probability as:	`p(A and B) = P(B given A) p(A)`

P(A and B) = P(B given A) P(A) = P(A given B) P(B)

So P(A given B) = P(B given A) P(A) / P(B) // This equation is called **Bayes'Theorem**.

**Probability of A or B** : P(A or B) = P(A) + P(B) - P(A and B)

For example, pick a number between 1 and 10 (inclusive). What's the probability of picking an even number and a number between 1 and 5? The odds of picking a number between 1 and 5 is 50%, and the <u>odds of a number between 1 and 5 being even is 40%</u>. So, the odds of doing both are: P(x is even and x <= 5) = P(x is even given x <= 5) * P(x <= 5)  = 50% * 40% = 20%

What's the probability of picking an even number or a number between 1 and 5? We have a 50% probability of picking an even number and a 50% probability of picking a number between 1 and 5. The odds of doing both are 20%. So the odds are: (50 + 50 - 20) = 80%.

**Independence** : 
If A and B are independent (that is, <u>one happening tells you nothing about the other happening</u>), then P (A and B) = P(A) P(B). Because P(B given A) = P(B), since A indicates nothing about B.

**Mutual Exclusivity**
If A and B are mutually exclusive (that is, if one happens, then the other cannot happen), then **P (A or B) = P(A) + P(B)**.This is because **P(A and B) = 0**.

Two events **cannot be both independent and mutually exclusive**, because mutual exclusivity means that if one happens then the other cannot. Independence, however, says that one event happening means absolutely nothing about the other event. Thus, as long as two events have non-zero probabilities, they will never be both mutually exclusive and independent. If one or both events have a <u>probability of zero (that is, it is impossible)</u>. then the events are both independent and mutually exclusive. 

**Develop Rules and Patterns**
You really should **write down "rules" or patterns** that you discover while solving the problem. It will help you remember them as you solve the problem. 

**Two Ropes Example**: You have two ropes, and each takes exactly one hour to burn. How would you use them to time exactly 15 minutes? Note that the ropes are of uneven densities, so half the rope length-wise does not necessarily take half an hour to burn. Write down the steps as below.

1. Light rope 1 at both ends and rope 2 at one end.
2. When the two flames on Rope 1 meet. 30 minutes will have passed. Rope 2 has 30 minutes left of burn time.
3. At that point, light Rope 2 at the other end. In exactly fifteen minutes, Rope 2 will be completely burnt.

**Worst Case Shifting**
Many brainteasers are <u>worst-case minimization problems</u>. A useful technique is to try to **"balance" the worst case**, either by minimizing an action or in doing something at most a specific number of times. .

**"9 balls" question** : You have 9 balls. 8 are of the same weight, and one is heavier. You are given a balance which tells you only whether the left side or the right side is heavier. Find the heavy ball in just <u>two uses of the scale</u>. A first approach is to divide the balls in sets of four, with the ninth ball sitting off to the side. The heavy ball is in the heavier set. This approach result in a worst case of three weighings, one too many. 

This is an **imbalance in the worst case**: the ninth ball takes just one weighing to discover if it's heavy, whereas others take three. If we penalize the ninth ball by putting more balls off to the side, we can lighten the load on the others. This is an example of **"worst case balancing"**. If we divide the balls into sets of three items each, we solve this in two weighings. 

**Additional Reading**: Useful Math (pg 629).

## Object-Oriented Design

These problems give an interviewer <u>insight into your coding style</u>. These questions are about demonstrating that you understand how to create <u>elegant, maintainable object-oriented code</u>.
**How to Approach**

- **Step 1: Handle Ambiguity**: Object-oriented design questions are often intentionally vague in order to test <u>whether you'll make assumptions or if you'll ask clarifying questions</u>. You should inquire who is going to use it and how they are going to use it. Depending on the question, you may even want to go through the "**six Ws**": who, what, where, when, how, why.
- **Step 2: Define the Core Objects**: After understanding what we're designing, we should consider what the "core objects" in a system are.
- **Step 3: Analyze Relationships**: We now want to <u>analyze the relationships between the core</u>
  <u>objects</u>. Which objects are members of which other objects? Do any objects inherit from any others? Are relationships many-to-many or one-to-many?
- **Step 4: Investigate Actions**
  At this point, you should have the <u>basic outline of your object-oriented design</u>. What remains is to <u>consider the key actions that the objects will take</u> and how they relate to each other. You may find that you have forgotten some objects, and you will need to update your design.

## Recursion and Dynamic Programming

A problem is recursive if it can be built off of solutions to subproblems. Recursive algorithms can be very **space inefficient**. Each recursive call adds a new layer to the stack, which means that if your algorithm recurses to a depth of n, it uses at least 0 (n) memory. So it's better to implement a recursive algorithm iteratively. All recursive algorithms can be implemented iteratively. Dynamic programming is just a matter of **taking a recursive algorithm** and finding the overlapping subproblems. You then cache those results for future recursive calls. Three of the most common approaches to divide a problem into subproblems:

- **Bottom-Up Approach**: We start with knowing how to solve the problem for a simple case, like a list with only one element. Then we figure out how to solve the problem for two elements, then for three elements, and so on. The key here is to think about how you can build the solution for one case off of the previous case (or multiple previous cases).
- **Top-Down Approach**: In these problems, we think about how we can divide the problem for case N into subproblems.
- **Half-and-Half Approach**: For example, binary search works with a "half-and-half" approach. 
  Merge sort is also a "half-and-half" approach. 

**Fibonacci Numbers**

```java
int fibonacci(int i) {
    if(i < 2 ) return i;
    return fibonacci(i-1) + fibonacci(i-2);
}
```

What is the **time complexity** of this function? O(n) or O(n2) **are wrong**. Drawing the recursive calls as a tree (**recursion tree**) is a great way to figure out the runtime of a recursive algorithm.

<img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/fibonaccitree.png?raw=true" alt="fibonaccitree.png" style="zoom:67%;" />

Leaves on the tree are all fib(1) and fib(0). The **total number of nodes in the tree will represent the time complexity**, since each call only does 0(1) work outside of its recursive calls. Therefore, the number of calls is the time complexity. We'll have roughly O(2^n) nodes. This gives us a runtime of roughly O( 2^n) .

**Actually, it's slightly better than 0(2^n)** .You might notice that the right subtree of any node (n-2) is always smaller than the left subtree (n-1). If they were the same size, we'd have an O(2^n) runtime. But since the right and left subtrees are not the same size, the true runtime is closer to O(1. 6^n). Saying O(2^n) is still technically correct though as it describes an **upper bound on the runtime**. 

Whats the time complexity (number of nodes) if you solve it using dynamic programming )top-down/bottom-up) = **O(2n) =~ O(n)**. We notice that each node has one child calculated at runtime and other child coming from cache, resulting in roughly 2n children in the tree. This gives us a runtime of 0 (n).

Additional Reading: **Proof by Induction**.

## System Design and Scalability

Despite how intimidating they seem, scalability questions can be among the easiest questions. There
are no "gotchas;' no tricks, and no fancy algorithms-at least not usually. What trips up many people is
that they believe there's something "magic" to these problems- some hidden bit of knowledge.
It's not like that. These questions are simply designed to see how you would perform in the real world. If you
were asked by your manager to design some system, what would you do?
That's why you should approach it just like this. Tackle the problem by doing it just like you would at work.
Ask questions. Engage the interviewer. Discuss the tradeoffs.
We will touch on some key concepts in this chapter, but recognize it's not really about memorizing these
concepts. Yes, understanding some big components of system design can be useful, but it's much more
about the process you take. There are good solutions and bad solutions. There is no perfect solution.
~ Handling the Questions
• Communicate: A key goal of system design questions is to evaluate your ability to communicate. Stay
engaged with the interviewer. Ask them questions. Be open about the issues of your system.
• Go broad first: Don't dive straight into the algorithm part or get excessively focused on one part.
• Use the whiteboard: Using a whiteboard helps your interviewer follow your proposed design. Get up to
the whiteboard in the very beginning and use it to draw a picture of what you're proposing.
• Acknowledge interviewer concerns: Your interviewer will likely jump in with concerns. Don't brush
them off; validate them. Acknowledge the issues your interviewer pOints out and make changes accordingly.
• Be careful about assumptions: An incorrect assumption can dramatically change the problem. For
example, if your system produces analytics / statistics for a dataset, it matters whether those analytics
must be totally up to date.
• State your assumptions explicitly: When you do make assumptions, state them. This allows your interviewer
to correct you if you're mistaken, and shows that you at least know what assumptions you're
making.
Estimate when necessary: In many cases, you might not have the data you need. For example, if you're
designing a web crawler, you might need to estimate how much space it will take to store all the URLs.
You can estimate this with other data you know.
• Drive: As the candidate, you should stay in the driver's seat. This doesn't mean you don't talk to your
interviewer; in fact, you must talk to your interviewer. However, you should be driving through the ques-
CrackingTheCodinglnterview.com 16th Edition 137
Chapter 9 I System Design and Scalability
tion. Ask questions. Be open about tradeoffs. Continue to go deeper. Continue to make improvements.
These questions are largely about the process rather than the ultimate design.
~ Design: Step-By-Step
If your manager asked you to design a system such as TinyURL, you probably wouldn't just say, "Okay'; then
lock yourself in your office to design it by yourself. You would probably have a lot more questions before
you do it. This is the way you should handle it in an interview.
Step 1: Scope the Problem
You can't design a system if you don't know what you're designing. Scoping the problem is important
because you want to ensure that you're building what the interviewer wants and because this might be
something that interviewer is specifically evaluating.
If you're asked something such as "Design TinyURL'; you'll want to understand what exactly you need to
implement. Will people be able to specify their own short URLs? Or will it all be auto-generated? Will you
need to keep track of any stats on the clicks? Should the URLs stay alive forever, or do they have a timeout?
These are questions that must be answered before going further.
Make a list here as well of the major features or use cases. For example, for TinyURL, it might be:
• Shortening a URL to a TinyURL.
Analytics for a URL.
Retrieving the URL associated with a TinyURL.
User accounts and link management.
Step 2: Make Reasonable Assumptions
It's okay to make some assumptions (when necessary), but they should be reasonable. For example, it
would not be reasonable to assume that your system only needs to process 100 users per day, or to assume
that you have infinite memory available.
However, it might be reasonable to design for a max of one million new URLs per day. Making this assumption
can help you calculate how much data your system might need to store.
Some assumptions might take some "product sense" (which is not a bad thing). For example, is it okay for
the data to be stale by a max of ten minutes? That all depends. If it takes 10 minutes for a just-entered URL
to work, that's a deal-breaking issue. People usually want these URLs to be active immediately. However, if
the statistics are ten minutes out of date, that might be okay. Talk to your interviewer about these sorts of
assumptions.
Step 3: Draw the Major Components
Get up out of that chair and go to the whiteboard. Draw a diagram of the major components. You might
have something like a frontend server (or set of servers) that pull data from the backend's data store. You
might have another set of servers that crawl the internet for some data, and another set that process
analytics. Draw a picture of what this system might look like.
Walk through your system from end-to-end to provide a flow. A user enters a new URL. Then what?
138 Crac king the Coding Interview, 6th Edition
Chapter 9 I System Design and Scalability
It may help here to ignore major scalability challenges and just pretend that the simple, obvious approaches
will be okay. You'll handle the big issues in Step 4.
Step 4: Identify the Key Issues
Once you have a basic design in mind, focus on the key issues. What will be the bottlenecks or major challenges
in the system?
For example, if you were designing TinyURL, one situation you might consider is that while some URLs will
be infrequently accessed, others can suddenly peak. This might happen if a URL is posted on Reddit or
another popular forum. You don't necessarily want to constantly hit the database.
Your interviewer might provide some guidance here. If so, take this guidance and use it.
Step 5: Redesign for the Key Issues
Once you have identified the key issues, it's time to adjust your design for it. You might find that it involves
a major redesign or just some minor tweaking (like using a cache).
Stay up at the whiteboard here and update your diagram as your design changes.
Be open about any limitations in your design. Your interviewer will likely be aware of them, so it's important
to communicate that you're aware of them, too .
• Algorithms that Scale: Step-By-Step
In some cases, you're not being asked to design an entire system. You're just being asked to design a single
feature or algorithm, but you have to do it in a scalable way. Or, there might be one algorithm part that is
the "real" focus of a broader design question.
In these cases, try the following approach.
Step 1: Ask Questions
As in the earlier approach, ask questions to make sure you really understand the question. There might
be details the interviewer left out (intentionally or unintentionally). You can't solve a problem if you don't
understand exactly what the problem is.
Step 2: Make Believe
Pretend that the data can all fit on one machine and there are no memory limitations. How would you solve
the problem? The answer to this question will provide the general outline for your solution.
Step 3: Get Real
Now go back to the original problem. How much data can you fit on one machine, and what problems will
occur when you split up the data? Common problems include figuring out how to logically divide the data
up, and how one machine would identify where to look up a different piece of data.
Step 4: Solve Problems
Finally, think about how to solve the issues you identified in Step 2. Remember that the solution for each
issue might be to actually remove the issue entirely, or it might be to simply mitigate the issue. Usually, you
CrackingTheCodinglnterview.com 16th Edition 139
Chapter 9 I System Design and Scalability
can continue using (with modifications) the approach you outlined in Step 1, but occasionally you will need
to fundamentally alter the approach.
Note that an iterative approach is typically useful. That is, once you have solved the problems from Step 3,
new problems may have emerged, and you must tackle those as well.
Your goal is not to re-architect a complex system that companies have spent millions of dollars building,
but rather to demonstrate that you can analyze and solve problems. Poking holes in your own solution is a
fantastic way to demonstrate this.
~ Key Concepts
While system design questions aren't really tests of what you know, certain concepts can make things a lot
easier. We will give a brief overview here. All of these are deep, complex topics, so we encourage you to use
online resources for more research.
Horizontal vs. Vertical Scaling
A system can be scaled one of two ways.
Vertical scaling means increasing the resources of a specific node. For example, you might add additional
memory to a server to improve its ability to handle load changes.
• Horizontal scaling means increasing the number of nodes. For example, you might add additional
servers, thus decreasing the load on anyone server.
Vertical scaling is generally easier than horizontal scaling, but it's limited. You can only add so much memory
or disk space.
load Balancer
Typically, some frontend parts of a scalable website will be thrown behind a load balancer. This allows a
system to distribute the load evenly so that one server doesn't crash and take down the whole system. To
do so, of course, you have to build out a network of cloned servers that all have essentially the same code
and access to the same data.
Database Denormalization and NoSQl
Joins in a relational database such as SQl can get very slow as the system grows bigger. For this reason, you
would generally avoid them.
Denormalization is one part of this. Denormalization means adding redundant information into a database
to speed up reads. For example, imagine a database describing projects and tasks (where a project can have
multiple tasks). You might need to get the project name and the task information. Rather than doing a join
across these tables, you can store the project name within the task table (in addition to the project table).
Or, you can go with a NoSQl database. A NoSQl database does not support joins and might structure data
in a different way. It is designed to scale better.
Database Partitioning (Sharding)
Sharding means splitting the data across multiple machines while ensuring you have a way of figuring out
which data is on which machine.
A few common ways of partitioning include:
140 Cracking the Coding Interview, 6th Edition
Chapter 9 I System Design and Scalability
Vertical Partitioning: This is basically partitioning by feature. For example, if you were building a social
network, you might have one partition for tables relating to profiles, another one for messages, and so
on. One drawback of this is that if one of these tables gets very large, you might need to repartition that
database (possibly using a different partitioning scheme).
Key-Based (or Hash-Based) Partitioning: This uses some part of the data (for example an ID) to partition
it. A very simple way to do this is to allocate N servers and put the data on mod (key J n). One issue
with this is that the number of servers you have is effectively fixed. Adding additional servers means
reallocating all the data-a very expensive task.
Directory-Based Partitioning: In this scheme, you maintain a lookup table for where the data can be
found. This makes it relatively easy to add additional servers, but it comes with two major drawbacks.
First, the lookup table can be a single point of failure. Second, constantly accessing this table impacts
performance.
Many architectures actually end up using multiple partitioning schemes.
Caching
An in-memory cache can deliver very rapid results. It is a simple key-value pairing and typically sits between
your application layer and your data store.
When an application requests a piece of information, it first tries the cache. If the cache does not contain the
key, it will then look up the data in the data store. (At this point, the data might-or might not-be stored
in the data store.)
When you cache, you might cache a query and its results directly. Or, alternatively, you can cache the specific
object (for example, a rendered version of a part of the website, or a list of the most recent blog posts).
Asynchronous Processing & Queues
Slow operations should ideally be done asynchronously. Otherwise, a user might get stuck waiting and
waiting for a process to complete.
In some cases, we can do this in advance (Le., we can pre-process). For example, we might have a queue of
jobs to be done that update some part of the website. If we were running a forum, one of these jobs might
be to re-render a page that lists the most popular posts and the number of comments. That list might end
up being slightly out of date, but that's perhaps okay. It's better than a user stuck waiting on the website
to load simply because someone added a new comment and invalidated the cached version of this page.
In other cases, we might tell the user to wait and notify them when the process is done. You've probably
seen this on websites before. Perhaps you enabled some new part of a website and it says it needs a few
minutes to import your data, but you'll get a notification when it's done.
Networking Metrics
Some ofthe most important metrics around networking include:
• Bandwidth: This is the maximum amount of data that can be transferred in a unit of time. It is typically
expressed in bits per second (or some similar ways, such as gigabytes per second).
Throughput: Whereas bandwidth is the maximum data that can be transferred in a unit of time,
throughput is the actual amount of data that is transferred.
Latency: This is how long it takes data to go from one end to the other. That is, it is the delay between the
sender sending information (even a very small chunk of data) and the receiver receiving it.
(rackingThe(odinglnterview.com 16th Edition 141
Chapter 9 I System Design and Scalability
Imagine you have a conveyor belt that transfers items across a factory. Latency is the time it takes an item to
go from one side to another. Throughput is the number of items that roll off the conveyor belt per second.
Building a fatter conveyor belt will not change latency. It will, however, change throughput and bandwidth.
You can get more items on the belt, thus transferring more in a given unit of time.
Shortening the belt will decrease latency, since items spend less time in transit. It won't change the
throughput or bandwidth. The same number of items will roll off the belt per unit of time.
Making a faster conveyor belt will change all three. The time it takes an item to travel across the factory
decreases. More items will also roll off the conveyor belt per unit of time.
Bandwidth is the number of items that can be transferred per unit of time, in the best possible conditions.
Throughput is the time it really takes, when the machines perhaps aren't operating smoothly.
Latency can be easy to disregard, but it can be very important in particular situations. For example, if you're
playing certain online games, latency can be a very big deal. How can you playa typical online sports game
(like a two-player football game) if you aren't notified very quickly of your opponent's movement? Additionally,
unlike throughput where at least you have the option of speeding things up through data compression,
there is often little you can do about latency.
MapReduce
MapReduce is often associated with Google, but it's used much more broadly than that. A MapReduce
program is typically used to process large amounts of data.
As its name suggests, a MapReduce program requires you to write a Map step and a Reduce step. The rest
is handled by the system.
• Map takes in some data and emits a <key J value> pair.
Reduce takes a key and a set of associated values and "reduces"them in some way, emitting a new key
and value. The results of this might be fed back into the Reduce program for more reducing.
MapReduce allows us to do a lot of processing in parallel, which makes processing huge amounts of data
more scalable.
For more information, see "MapReduce" on page 642.
~ Considerations
In addition to the earlier concepts to learn, you should consider the following issues when designing a
system.
Failures: Essentially any part of a system can fail. You'll need to plan for many or all of these failures.
Availability and Reliability: Availability is a function of the percentage of time the system is operational.
Reliability is a function of the probability that the system is operational for a certain unit of time.
Read-heavy vs. Write-heavy: Whether an application will do a lot of reads or a lot of writes impacts the
design. If it's write-heavy, you could consider queuing up the writes (but think about potential failure
here!). If it's read-heavy, you might want to cache. Other design decisions could change as well.
• Security: Security threats can, of course, be devastating for a system. Think about the types of issues a
system might face and design around those.
This is just to get you started with the potential issues for a system. Remember to be open in your interview
about the tradeoffs.
142 Cracking the Coding Interview, 6th Edition
Chapter 9 I System Design and Scalability
~ There is no "perfect" system.
There is no single design for TinyURL or Google Maps or any other system that works perfectly (although
there are a great number that would work terribly). There are always tradeoffs. Two people could have
substantially different designs for a system, with both being excellent given different assumptions.
Your goal in these problems is to be able to understand use cases, scope a problem, make reasonable
assumptions, create a solid design based on those assumptions, and be open about the weaknesses of your
design. Do not expect something perfect.
~ Example Problem
Given a list of millions of documents, how would you find all documents that contain a list of words? The words
can appear in any order, but they must be complete words. That is, "book" does not match "bookkeeper."
Before we start solving the problem, we need to understand whether this is a one time only operation, or if
this findWords procedure will be called repeatedly. Let's assume that we will be calling findWord s many
times for the same set of documents, and, therefore, we can accept the burden of pre-processing.
Step 1
The first step is to pretend we just have a few dozen documents. How would we implement findWords in
this case? (Tip: stop here and try to solve this yourself before reading on.)
One way to do this is to pre-process each document and create a hash table index. This hash table would
map from a word to a list of the documents that contain that word.
"books" -> {doc2, doc3, doc6, docS}
"many" -> {docl, doc3, doc7, docS, doc9}
To search for "many books;'we would simply do an intersection on the values for "books" and "many'; and
return {doc 3, doc8} as the result.
Step 2
Now go back to the original problem. What problems are introduced with millions of documents? For
starters, we probably need to divide up the documents across many machines. Also, depending on a variety
of factors, such as the number of possible words and the repetition of words in a document, we may not be
able to fit the full hash table on one machine. Let's assume that this is the case.
This division introduces the following key concerns:

1. How will we divide up our hash table? We could divide it up by keyword, such that a given machine
   contains the full document list for a given word. Or, we could divide by document, such that a machine
   contains the keyword mapping for only a subset of the documents.
2. Once we decide how to divide up the data, we may need to process a document on one machine and
   push the results off to other machines. What does this process look like? (Note: if we divide the hash
   table by document, this step may not be necessary.)
3. We will need a way of knowing which machine holds a piece of data. What does this lookup table look
   like, and where is it stored?
   These are just three concerns. There may be many others.
   CrackingTheCodinglnterview.com 16th Edition 143
   Chapter 9 I System Design and Scalability
   Step 3
   In Step 3, we find solutions to each of these issues. One solution is to divide up the words alphabetically by
   keyword, such that each machine controls a range of words (e.g., "after"through "apple").
   We can implement a simple algorithm in which we iterate through the keywords alphabetically, storing as
   much data as possible on one machine. When that machine is full, we can move to the next machine.
   The advantage of this approach is that the lookup table is small and simple (since it must only specify a
   range of values), and each machine can store a copy of the lookup table. However, the disadvantage is that
   if new documents or words are added, we may need to perform an expensive shift of keywords.
   To find all the documents that match a list of strings, we would first sort the list and then send each machine
   a lookup request for the strings that the machine owns. For example, if our string is "after builds
   boat amaze banana", machine 1 would get a lookup request for {"after", "amaze"}.
   Machine 1 looks up the documents containing "after" and "amaze;' and performs an intersection on these
   document lists. Machine 3 does the same for {"banana", "boat", "builds"}, and intersects their
   lists.
   In the final step, the initial machine would do an intersection on the resu lts from Machine 1 and Machine 3.
   The following diagram explains this process.
   l "after builds boat amaze banana" J
   Machine 1: "after amaze" Machine 3: "builds boat banana"
   "after" doc1, doc5, doc7
   "builds" - ) doc3, doc4, doc5

- )
  "boat" -) doc2, doc3, doc5
  "amaze" -) doc2, doc5, doc7
  "banana" - ) doc3, doc4, doc5
  {doc5 doc7} {doc3 doc5}
  I solution = doc5 I



## Sorting and Searching

Understanding the common sorting and searching algorithms is incredibly valuable, as many sorting
and searching problems are tweaks of the well-known algorithms. A good approach is therefore to run
through the different sorting algorithms and see if one applies particularly well.
For example, suppose you are asked the following question: Given a very large array of Person objects,
sort the people in increasing order of age.
We're given two interesting bits of knowledge here:

1. It's a large array, so efficiency is very important.
2. We are sorting based on ages, so we know the values are in a small range.
   By scanning through the various sorting algorithms, we might notice that bucket sort (or radix sort) would
   be a perfect candidate for this algorithm. In fact, we can make the buckets small (just 1 year each) and get
   o (n) running time.
   ~ Common Sorting Algorithms
   Learning (or re-Iearning) the common sorting algorithms is a great way to boost your performance. Of the
   five algorithms explained below, Merge Sort, Quick Sort and Bucket Sort are the most commonly used in
   interviews.
   Bubble Sort I Runtime: 0(n2) average and worst case. Memory: 0(1).
   In bubble sort, we start at the beginning of the array and swap the first two elements if the first is greater
   than the second. Then, we go to the next pair, and so on, continuously making sweeps of the array until it is
   sorted. In doing so, the smaller items slowly"bubble" up to the beginning of the list.
   Selection Sort I Runtime: O( n2) average and worst case. Memory: 0 (1) .
   Selection sort is the child's algorithm: simple, but inefficient. Find the smallest element using a linear scan
   and move it to the front (swapping it with the front element). Then, find the second smallest and move it,
   again doing a linear scan. Continue doing this until all the elements are in place.
   Merge Sort I Runtime: O( n log (n) ) average and worst case. Memory: Depends.
   Merge sort divides the array in half, sorts each of those halves, and then merges them back together. Each
   of those halves has the same sorting algorithm applied to it. Eventually, you are merging just two singleelement
   arrays. It is the "merge" part that does all the heavy lifting.
   146 Cracking the Coding Interview, 6th Edition
   Chapter 10 I Sorting and Searching
   The merge method operates by copying all the elements from the target array segment into a helper array,
   keeping track of where the start of the left and right halves should be (helperLeft and helperRight).
   We then iterate through helper, copying the smaller element from each half into the array. At the end, we
   copy any remaining elements into the target array.
   1 void me rgesort(int[] array) {
   2 int[] helper = new int[array.length]j
   3 mergesort(array, helper, 8, array.length - l)j
   4 }
   5
   6 void mergesort(int[] array, int[] helper, int low, int high) {
   7 if (lOW < high) {
   8 int middle = (low + high) I 2j
   9 me rgesort(array, helper, low, middle)j II Sort left half
   18 mergesort(array, helper, middle+1, high) j II Sort right half
   11 merge(array, helper, low, middle, high)j II Merge them
   12 }
   13 }
   14
   15 void merge(int[] array, int[] helper, int low, int middle, int high) {
   16 1* Copy both halves into a helper array *1
   17 for (int i = lowj i <= highj i++) {
   18 helper[i] = array[i]j
   19 }
   28
   21 int helperLeft = lowj
   22 int helperRight = middle + 1j
   23 int current = lowj
   24
   25 1* Iterate through helper array. Compare the left and right half, copying back
   26 * the smaller element from the two halves into the original array. *1
   27 while (helperLeft <= middle && helperRight <= high) {
   28 if (helper[helperLeft] <= helper[helperRight]) {
   29 array[current] = helper[helperLeft]j
   38 helperLeft++j
   31 } else { II If right element is smaller than left element
   32 array[current] = helper[helperRight]j
   33 helperRight++j
   34 }
   35 current++j
   36 }
   37
   38 1* Copy the rest of the left side of the array into the target array *1
   39 int remaining = middle - helperLeftj
   48 for (int i = 8j i <= remainingj i++) {
   41 array[current + i) = helper[helperLeft + i]j
   42 }
   43 }
   You may notice that only the remaining elements from the left half of the helper array are copied into the
   target array. Why not the right half? The right half doesn't need to be copied because it's already there.
   Consider, for example, an array like [1, 4, 5 II 2, 8, 9] (the" I I "indicates the partition point). Prior
   to merging the two halves, both the helper array and the target array segment will end with [8, 9]. Once
   we copy over four elements (1, 4, 5, and 2) into the target array, the [8, 9] will still be in place in both
   arrays. There's no need to copy them over.
   CrackingTheCodinginterview.com 16th Edition 147
   Chapter 10 I Sorting and Searching
   The space complexity of merge sort is 0 (n) due to the auxiliary space used to merge parts of the array.
   Quick Sort I Runtime: 0 (n log (n)) average, 0 (n2) worst case. Memory: 0 (log (n) ) .
   In quick sort, we pick a random element and partition the array, such that all numbers that are less than the
   partitioning element come before all elements that are greater than it. The partitioning can be performed
   efficiently through a series of swaps (see below).
   If we repeatedly partition the array (and its sub-arrays) around an element, the array will eventually become
   sorted. However, as the partitioned element is not guaranteed to be the median (or anywhere near the
   median), our sorting could be very slow. This is the reason for the 0 (n2) worst case runtime.
   1 void quickSort(int[] arr, int left, int right) {
   2 int index = partition(arr, left, right);
   3 if (left < index - 1) { II Sort left half
   4 quickSort(arr, left, index - 1);
   5 }
   6 if (index < right) { II Sort right half
   7 quickSort(arr, index, right);
   8 }
   9 }
   113
   11 int partition(int[] arr, int left, int right) {
   12 int pivot = arr[(left + right) I 2]; II Pick pivot point
   13 while (left <= right) {
   14 II Find element on left that should be on right
   15 while (arr[left] < pivot) left++;
   16
   17 II Find element on right that should be on left
   18 while (arr[right] > pivot) right- - ;
   19
   213 II Swap elements, and move left and right indices
   21 if (left <= right) {
   22 swap(arr, left, right); II swaps elements
   23 left++;
   24 right--;
   25 }
   26 }
   27 return left;
   28 }
   Radix Sort I Runtime: O( kn) (see below)
   Radix sort is a sorting algorithm for integers (and some other data types) that takes advantage of the
   fact that integers have a finite number of bits. In radix sort, we iterate through each digit of the number,
   grouping numbers by each digit. For example, if we have an array of integers, we might first sort by the
   first digit, so that the Os are grouped together. Then, we sort each of these groupings by the next digit. We
   repeat this process sorting by each subsequent digit. until finally the whole array is sorted.
   Unlike comparison sorting algorithms, which cannot perform better than 0 (n log (n)) in the average
   case, radix sort has a runtime of O( kn), where n is the number of elements and k is the number of passes
   of the sorting algorithm.
   148 Cracking the Coding Interview, 6th Edition
   Chapter 10 I Sorting and Searching
   ~ Searching Algorithms
   When we think of searching algorithms, we generally think of binary search. Indeed, this is a very useful
   algorithm to study.
   In binary search, we look for an element x in a sorted array by first comparing x to the midpoint of the array.
   If x is less than the midpoint, then we search the left half of the array. If x is greater than the midpoint, then
   we search the right half of the array. We then repeat this process, treating the left and right halves as subarrays.
   Again, we compare x to the midpoint of this subarray and then search either its left or right side. We
   repeat this process until we either find x or the subarray has size O.
   Note that although the concept is fairly simple, getting all the details right is far more difficult than you
   might think. As you study the code below, pay attention to the plus ones and minus ones.
   1 int binarySearch(int[] a, int x) {
   2 int low = 8;
   3 int high = a.length - 1;
   4 int mid;
   5
   6
   7
   8
   while (low <= high) {
   mid = (low + high) I 2;
   if (a[mid] < x) {
   9 low = mid + 1;
   18 } else if (a[mid] > x) {
   11 high = mid - 1;
   12 } else {
   13 return mid;
   14 }
   15 }
   16 return -1; II Error
   17 }
   18
   19 int binarySearchRecursive(int[] a, int x, int low, int high) {
   28 if (low> high) return -1; II Error
   21
   22 int mid = (low + high) I 2;
   23 if (a[mid] < x) {
   24 return binarySearchRecursive(a, x, mid + 1, high);
   25 } else if (a[mid] > x) {
   26 return binarySearchRecursive(a, x, low, mid - 1);
   27 } else {
   28 return mid;
   29 }
   38 }
   Potential ways to search a data structure extend beyond binary search, and you would do best not to limit
   yourself to just this option. You might, for example, search for a node by leveraging a binary tree, or by using
   a hash table. Think beyond binary search!


## Java

 **Overloading vs. Overriding**
Overloading is a term used to describe when two methods have the same name but differ in the type or number of arguments. Overriding occurs when a method shares the same name and function signature as another method in its super class.

**Collection Framework**

- **ArrayList**: An ArrayList is a dynamically resizing array, which grows as you insert elements.
- **Vector**: A vector is very similar to an ArrayList, except that it is **synchronized**. Its syntax is almost identical as well.
- **LinkedList**: Java's <u>built-in LinkedList class</u>.
- **HashMap**

## Databases

**Implicit and explicit joins**

```sql
// Explicit join
SELECT CourseName, TeacherName FROM Courses INNER JOIN Teachers ON Courses.TeacherID = Teachers.TeacherID
// Implicit join
SELECT CourseName, TeacherName FROM Courses, Teachers WHERE Courses.TeacherID = Teachers.TeacherID
```

**Denormalized vs. Normalized Databases**
Normalized databases are designed to **minimize redundancy**, while denormalized databases are designed to **optimize read time**. **Data redundancy** is a condition created within a database in which the <u>same piece of data is held in two separate places</u>.

In a traditional normalized database Courses might contain a column called TeacherID, which is a foreign key to Teacher. One benefit of this is that information about the teacher (name, address, etc.) is only stored once in the database. The drawback is that many **common queries will require expensive joins**. Instead, we can **denormalize the database by storing redundant data**. So if we would have to repeat this query often, we might store the teacher's name in the Courses table. <u>Denormalization is commonly used to create highly scalable systems</u>.

**SQL Statements**

Let's walk through a review of basic SOL syntax, using as an example the database that was mentioned
earlier. This database has the following simple structure (* indicates a primary key):
Courses: CourseID*, CourseName, TeacherID
Teachers: TeacherID*, TeacherName
Students: StudentID*, StudentName
CrackingTheCodinglnterview.com 16th Edition 169
Chapter 14 I Databases
StudentCourses: CourseID* , StudentID*
Using the above table, implement the following queries.
Query 1: Student Enrollment
Implement a query to get a list of all students and how many courses each student is enrolled in.
At first, we might try something like this:
1 / * Incorrect Code */
2 SELECT Students.StudentName, count( * )
3 FROM Students I NNER JOIN StudentCourses
4 ON Students.StudentID = StudentCourses.StudentID
5 GROUP BY Students.StudentID
This has three problems:

1. We have excluded students who are not enrolled in any courses, since StudentCourses only includes
   enrolled students. We need to change this to a LEFT JOIN.

2. Even if we changed it to a LEFT JOIN, the query is still not quite right. Doing count ( *) would return
   how many items there are in a given group of Student IDs. Students enrolled in zero courses would still
     have one item in their group. We need to change this to count the number of CourseIDs in each group:
     count(StudentCourses . CourseID) .

3. We've grouped by Students. StudentID, but there are still multiple StudentNames in each group.
   How will the database know which StudentName to return? Sure, they may all have the same value,
     but the database doesn't understand that. We need to apply an aggregate function to this, such as
     first(Students.StudentName).
     Fixing these issues gets us to this query:
     1 / * Solution 1: Wrap with another query * /
     2 SELECT StudentName, Students.StudentID, Cnt
     3 FROM (
     4 SELECT Students.StudentID, count(StudentCourses.CourseID) as [Cnt]
     5 FROM Students LEFT JOIN StudentCourses
     6 ON Students.StudentID = StudentCourses.StudentID
     7 GROUP BY Students . StudentID
     8 T INNER JOIN Students on T.studentID = Students.StudentID
     Looking at this code, one might ask why we don't just select the student name on line 3 to avoid having to
     wrap lines 3 through 6 with another query. This (incorrect) solution is shown below.
     1 / * Incorrect Code */
     1 SELECT StudentName, Students .StudentID, count(StudentCourses.CourseID) as [Cnt]
     2 FROM Students LEFT JOIN StudentCourses
     3 ON Students . StudentID = StudentCourses.StudentID
     4 GROUP BY Students.StudentID
     The answer is that we can't do that - at least not exactly as shown. We can only select values that are in an
     aggregate function or in the GROUP BY clause.
     Alternatively, we could resolve the above issues with either of the following statements:
     1 /* Solution 2: Add StudentName to GROUP BY clause. * /
     2 SELECT StudentName, Students.StudentID, count(StudentCourses.CourseID) as [Cnt]
     3 FROM Students LEFT JOIN StudentCourses
     4 ON Students.StudentID = StudentCourses.StudentID
     5 GROUP BY Students .StudentID, Students.StudentName
     OR
     170 Cracking the Coding Intervi ew, 6th Edition
     Chapter 14 I Databases
     1 /* Solution 3: Wrap with aggregate function. */
     2 SELECT max(StudentName) as [StudentName], Students.StudentID,
     3 count(StudentCourses.CourseID) as [Count]
     4 FROM Students LEFT JOIN StudentCourses
     5 ON Students.StudentID = StudentCourses.StudentID
     6 GROUP BY Students.StudentID
     Query 2: Teacher Class Size
     Implement a query to get a list of all teachers and how many students they each teach. If a teacher teaches
     the same student in two courses, you should double count the student. Sort the list in descending order of
     the number of students a teacher teaches.
     We can construct this query step by step. First, let's get a list of TeacherIDs and how many students are
     associated with each TeacherID. This is very similar to the earlier query.
     1 SELECT TeacherID, count(StudentCourses.CourseID) AS [Number]
     2 FROM Courses INNER JOIN StudentCourses
     3 ON Courses.CourseID = StudentCourses.CourseID
     4 GROUP BY Courses.TeacherID
     Note that this INNER JOIN will not select teachers who aren't teaching classes. We'll handle that in the
     below query when we join it with the list of all teachers.
     1 SELECT TeacherName, isnull(StudentSize.Number, a)
     2 FROM Teachers LEFT JOIN
     3 (SELECT TeacherID, count(StudentCourses.CourseID) AS [Number]
     4 FROM Courses INNER JOIN StudentCourses
     5 ON Courses.CourseID = StudentCourses.CourseID
     6 GROUP BY Courses.TeacherID) StudentSize
     7 ON Teachers . TeacherID = StudentSize.TeacherID
     8 ORDER BY StudentSize.Number DESC
     Note how we handled the NULL values in the SE LECT statement to convert the NULL values to zeros.

   

 **Small Database Design**

Design a system to represent an apartment rental agency.

- ##### Step 1: Handle Ambiguity

  Before you proceed with your design, you must **understand exactly what you need to design**. You will need to know whether this agency has multiple locations or just one. You should also discuss whether to handle "a person rent two apartments in the same building".

- ##### Step 2: Define the Core Objects

  We should look at the core objects of our system. Each of these core objects translates into a table. In this case, core objects are Property, Building, Apartment, Tenant and Manager.

- ##### Step 3: Analyze Relationships

  How do these tables relate to each other? Are they many-to-many? One-to-many? 

  - If Buildings has a **one-to-many** relationship with Apartments (one Building has many Apartments), then Apartments table links back to Buildings with a BuildingID column.
  - If one person rents more than one apartment, then we implement a **many-to-many** relationship (TenantApartments table) that stores relationship between Tenants and Apartments.

- ##### Step 4: Investigate Actions

  Finally, we fill in the details. **Walk through the common actions** that will be taken and understand how to store and retrieve the relevant data. We'll need to handle lease terms, moving out, rent payments, etc. <u>Each of these actions requires new tables and columns</u>.

**Large Database Design**
When designing a large, scalable database, joins are generally very slow. Thus, **you must denormalize your data**. Think carefully about how data will be used-you'll **probably need to duplicate the data in multiple tables**.

## Threads and Locks

Every thread in Java is <u>created and controlled by a unique object of the Thread class</u>. When a standalone application is run, **a user thread is automatically created to execute the main( )** method. This thread is called the main thread. When creating threads, <u>implementing the Runnable interface is preferable to extending the Thread class</u>.

- Java does not support multiple inheritance. Therefore, extending the Thread class means that the **subclass cannot extend any other class**. A class implementing the Runnable interface will be able to extend another class.
- A class might **only be interested in being runnable**, and therefore, <u>inheriting the full overhead of the Thread class would be excessive</u>.

**Synchronization**
<u>Threads within a given process share the same memory space</u>, which enables threads to share data. However, it also creates issues when two threads modify a resource at the same time. Java provides synchronization in order to control access to shared resources.

**Locks**
For more granular control, we can utilize a lock. A lock (or monitor) is used to synchronize access to a shared resource by <u>associating the resource with the lock</u>. A thread gets access to a shared resource by first acquiring the lock associated with the resource. At any given time, at most one thread can hold the lock and, therefore, only one thread can access the shared resource. A **common use case for locks** is **when a resource is accessed from multiple places**, but should be only accessed by one thread at a time. Using a lock will help protect a shared resource from being modified in unexpected ways.

**Deadlocks**

In order for a deadlock to occur, you must have <u>all four of the following conditions</u> met:

1. **Mutual Exclusion**: Only **one process can access** a resource at a given time. (Or there is limited access to a resource. A deadlock could also occur if a resource has limited quantity.)
2. **Hold and Wait**: Processes already holding a resource can **request additional resources**, without relinquishing their current resources.
3. **No Preemption**: One process cannot forcibly remove another process' resource.
4. **Circular Wait**: Two or more processes form a circular chain where each process is waiting on another resource in the chain.

Deadlock prevention entails **removing any of the above conditions**. But many of these conditions are **difficult to satisfy**. Most deadlock prevention algorithms focus on **avoiding condition circular wait**.

### Interview Questions

1. Thread vs. Process: What's the difference between a thread and a process? => Hints: #405

2. Context Switch: How would you measure the time spent in a context switch? => Hints: #403, #407, #415, #441

3. **Dining Philosophers**: In the famous dining philosophers problem, a bunch of philosophers are sitting around a circular table with one chopstick between each of them. A philosopher needs both chopsticks to eat, and always picks up the left chopstick before the right one. A deadlock could potentially occur if all the philosophers reached for the left chopstick at the same time. Using threads and locks, implement a simulation of the dining philosophers problem that prevents deadlocks. => Hints: #419, #437

4. **Deadlock-Free Class**: Design a class which provides a lock only if there are no possible deadlocks. => Hints: #422, #434

5. Call In Order: Suppose we have the following code:

   ```java
   public class Foo {
     public FOO() { ••• }
     public void first() { ••• }
     public void second() { ••• }
     public void third() { ••• }
   }
   ```

   The same instance of Foo will be passed to three different threads. ThreadA will call first, threadB will call second, and threadC will call third. Design a mechanism to ensure that first is called before second and second is called before third. => Hints: #417, #433, #446

6. Synchronized Methods: You are given a class with synchronized method A and a normal method B. lf you have two threads in one instance of a program, can they both execute A at the same time? Can they execute A and B at the same time? => Hints: #429

7. **FizzBuzz**: In the classic problem FizzBuzz, you are told to print the numbers from 1 to n. However, when the number is divisible by 3, print "Fizz': When it is divisible by 5, print "Buzz': When it is divisible by 3 and 5, print "FizzBuzz': In this problem, you are asked to do this in a multithreaded way. Implement a multithreaded version of FizzBuzz with four threads. One thread checks for divisibility of 3 and prints "Fizz': Another thread is responsible for divisibility of 5 and prints "Buzz': A third thread is responsible for divisibility of 3 and 5 and prints "FizzBuzz". A fourth thread does the numbers. => Hints: #414, #439, #447, #458.



# Data Structures

## Arrays and Strings - Questions (p102)

Interview Questions

1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
cannot use additional data structures?
Hints: #44, # 777, # 7 32
1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
other.
Hints: #7, #84, #722, #737
1.3 URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
has sufficient space at the end to hold the additional characters, and that you are given the "true"
length of the string. (Note: If implementing in Java, please use a character array so that you can
perform this operation in place.)
EXAMPLE
Input: "Mr John Smith "J 13
Output: "Mr%20J ohn%20Smith"
Hints: #53, #7 78
90 Cracking the Coding Interview, 6th Edition
Chapter 1 I Arrays and Strings
1.4 Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
A palindrome is a word or phrase that is the same forwards and backwards. A permutation
is a rea rrangement of letters. The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat". "atco cta". etc.)
Hints: #106, #121, #134, #136
1.5 One Away: There are three types of edits that can be performed on strings: insert a character,
remove a character, or replace a character. Given two strings, write a function to check if they are
one edit (or zero edits) away.
EXAMPLE
pale, pIe -> true
pales. pale -> true
pale. bale -> true
pale. bake -> false
Hints: #23, #97, #130
\, , l'
1.6 String Compression: Implement a method to perform basic string compression using the counts
of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
"compressed" string would not become smaller than the original string, your method should return
the original string. You can assume the string has only uppercase and lowercase letters (a - z).
Hints: #92, # 11 0
1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. (an you do this in place?
Hints: #51, #100
1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
column are set to O.
Hints: # 17, #74, #102
1.9 String Rotation: Assume you have a method isSubst ring which checks if one word is a substring
of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one
call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
Hints: #34, #88, #104
j , ~ ')[ t
Additional Questions: Object-Oriented Design (#7.12), Recursion (#8.3), Sorting and Searching (#10.9), (++
(#12.11), Moderate Problems (#16.8, #16.17, #16.22), Hard Problems (#17.4, #17.7, #17.13, #17.22, #17.26).
Hints start on page 653.

## Linked Lists - Questions(106)

2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
Hints: #9, #40
2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
Hints: #8, #25, #47, #67, # 726
2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
that node.
EXAMPLE
Input: the node c from the linked list a - >b- >c - >d - >e- >f
Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
Hints: #72
2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
to be after the elements less than x (see below). The partition element x can appear anywhere in the
"right partition"; it does not need to appear between the left and right partitions.
EXAMPLE
Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5)
Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
Hints: #3, #24
'L
94 Cracking the Coding Interview, 6th Edition
Chapter 2 I Linked Lists
2.S Sum Lists: You have two numbers represented by a linked list, where each node contains a single
digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input: (7-) 1 -) 6) + (5 -) 9 -) 2) .Thatis,617 + 295.
Output: 2 -) 1 -) 9. That is, 912.
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem.
EXAMPLE
Input: (6 -) 1 -) 7) + (2 -) 9 -) 5).Thatis,617 + 295.
Output: 9 -) 1 -) 2. That is, 912.
Hints: #7, #30, #71, #95, #109
2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
Hints: #5, #13, #29, #61, #101
2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
node. Note that the intersection is defined based on reference, not value. That is, if the kth
node of the first linked list is the exact same node (by reference) as the jth node of the second
linked list, then they are intersecting.
Hints: #20, #45, #55, #65, #76, #93, #111, #120, #129
2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
beginning of the loop.
DEFINITION
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
as to make a loop in the linked list.
EXAMPLE
Input: A -) B -) C -) 0 -) E - ) C[thesameCasearlierl
Output: C
Hints: #50, #69, #83, #90
Additional Questions: Trees and Graphs (#4.3), Object-Oriented Design (#7.12), System Design and Scalability
(#9.5), Moderate Problems (#16.25), Hard Problems (#17.12).
Hints start on page 653.

## Stacks and Queues - Questions (p110)

3.1 Three in One: Describe how you could use a single array to implement three stacks.
Hints: #2, #72, #38, #58
, ,
3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
which returns the minimum element? Push, pop and min should all operate in 0(1) time.
Hints: #27, #59, #78
98 Cracking the Coding Interview, 6th Edition
Chapter 3 I Stacks and Queues
3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
composed of several stacks and should create a new stack once the previous one exceeds capacity.
SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack
(that is, pop ( ) should return the same values as it would if there were just a single stack).
FOLLOW UP
Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
Hints: #64, #87
3.4 Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
Hints: #98, #7 74
P9 2l6
3.S Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
an additional temporary stack, but you may not copy the elements into any other data structure
(such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
Hints: # 75, #32, #43
3.6 Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first
out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
that type). They cannot select which specific animal they would like. Create the data structures to
maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
and dequeueCat. You may use the built-in Linked List data structure.
Hints: #22, #56, #63
Additional Questions: Linked Lists (#2.6), Moderate Problems (#16.26), Hard Problems (#17.9).
Hints start on page 653.

## Trees and Graphs - Questions(p121)

- Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

  - Its a directed graph so bidirectional search is not possible. So we can use either BFS or DFS and DFS is easy to implement using recursion. BFS can be useful to **find the shortest path**, whereas DFS may traverse one adjacent node very deeply before ever going onto the immediate neighbors.

- Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a <u>binary search tree with minimal height</u>.

  - A **minimal binary tree (minimal height)** has about the **same number of nodes** on the left of each node as on the right. This means that we want the <u>root to be the middle of the array</u>. Can you divide this problem into **subproblems**? The algorithm is as follows: The middle element of the array becomes the root node. The left half of the array will become left subtree, and right half of the array will become the right subtree. Continue recursion on these nodes.

    ```java
    TreeNode createMinimalBST(int arr[] , int start, int end) {
        if (end < start) { return null;}
        int mid = (start + end) / 2; // find middle of the array.
    	TreeNode n = new TreeNode(arr[mid]);// assign that as root.
    	n.left = createMinimalBST(arr, start, mid - 1);//array left half.
    	n. right = createMinimalBST(arr, mid + 1, end);//array right half.
        return n;
    }
    ```

- List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).

  - This problem doesn't requires a **level-by-Ievel traversal**, we can **traverse the graph any way** (BFS/DFS), provided <u>we know which level (depth from the root) we're on</u> as we do so. So we can solve this using DFS by **passing level to the next recursive call**.

  - A hashmap / array / arraylist that **maps from level number to nodes** can be useful.

  - Both (BFS/DFS) run in **0(N) time**. We might think the second solution is more space efficient. The first solution uses 0(log N) recursive calls (in a balanced tree), each of which adds a new level to the stack. The second solution, which is iterative, does not require this extra space. However, both solutions require returning 0 (N) data. The extra 0 (log N) space usage from the recursive implementation is dwarfed by the O( N) data that must be returned. So while the first solution may actually use more data, they are <u>equally efficient when it comes to **"big 0:"**</u>

  - ```java
    //DFS
    void levelList(TreeNode root, List<List<TreeNode» lists, int level) {
        if (root == nUll) return;
        LinkedList<TreeNode> list = null;
        if (lists.size() == level) { // Level not contained in list
            list = new LinkedList<TreeNode>();
            /*Levels are always traversed in order. So, if this is the first 		time we've visited level i, we must have seen levels 0 through i 		- 1. We can therefore safely add the level at the end.*/
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list .add(root) ;
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }
    ```

    ```java
    /*BFS. With each level i , we will have already fully visited all nodes on level i - 1. This means that to get which nodes are on level i, we can simply look at all children of the nodes of level i - l.*/
    void levelList(TreeNode root) {
        if (root == nUll) return;
        List<List<TreeNode» result = new ArrayList<LinkedList<TreeNode»();
        List<TreeNode> current = new LinkedList<TreeNode>();
        current.add(root);// visit the root.
        while (current.size() > 0) {
    		result.add(current); //Add previous level
            LinkedList<TreeNode> parents = current; //Go to next level
            current = new LinkedList<TreeNode>(); //Initialize next level
            for (TreeNode parent : parents) {
                if (parent.left != nUll) { current.add(parent.left); }
                if (parent.right != nUll){ current.add(parent.right);}
            }
        }
        return result;
    }
    ```

- Check Balanced: Implement a function to check if a binary tree is balanced. 

  - A **balanced tree** is a tree such that the heights of the two subtrees of any node never differ by more than one.

  - **Brute force** - Simply recurse through the entire tree, and for each node, compute the heights of each subtree, check the difference > 1. But it's not very efficient. getHeight() is called repeatedly on the same nodes since each node is "touched" once per node above it. The algorithm is O(N log N) time.

  - Best : getHeight() could actually check if the tree is balanced at the same time as it's checking heights. if discover that the subtree isn't balanced Just return an error code, else return the actual height of the subtree. The height of a null tree is generally defined to be -1, so instead, we'll use Integer. MIN_VALUE as an error code. This code runs in **0(N) time** and **0(H) space**, where H is the height of the tree.

    ```java
    int checkHeight(TreeNode root) {
    	if (root == nUll) return -l;
    	int leftHeight = checkHeight(root.left);
        //if get an error code, immediately return from the current call.
    	if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
    	int right Height checkHeight(root.right)j
    	if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
    	int heightDiff = leftHeight - rightHeightj
    	if (Math.abs(heightDiff) > 1) { return Integer.MIN_VALUE; } 
        else { return Math.max(leftHeight, rightHeight) + l; }
    }
    boolean isBalanced(TreeNode root) {
    	return checkHeight(root) != Integer.MIN_VALUE;
    }
    ```

- Validate BST: Implement a function to check if <u>a binary tree is a binary search tree</u>.

  - **Brute Force** - In Order Traversal : copy the elements to an array, and check if the array is sorted (Ascending order). Actually **array is not necessary**. We never use it other than to compare an element to the previous element. So we can just <u>track the last element and compare it</u> as we go.

    ```java
    Integer last = null;
    boolean checkBST(TreeNode n) {
        if (n == nUll) return true;
        if (!checkBST(n.left)) return false; // check left tree.
    	if (last != null && n.data <= last) { return false; } // current
        last = n.data;
    	if (!checkBST(n.right)) return false; // check right tree.
        return true; // all good.
    }
    ```

  - The only **problem** is that In Order Traversal **can't handle duplicate values in the tree** properly. For example, the algorithm cannot distinguish between the two trees below (one of which is invalid) since they have the **same in-order traversal**.

    <img src="https://github.com/SnowflakeCoder/programming/blob/master/Algorithms%20and%20DS/images/tree%20In-order%20traversal%20issue.png?raw=true" alt="tree In-order traversal issue.png" style="zoom:67%;" />

  - For a BST <u>all left nodes must be less than or equal to the current node</u>, which
    must be less than all the right nodes.

    **Solution 1** : biggest node on the left must be less than or equal to the current node.

    **Solution 2**: Validate the left tree's nodes to ensure that they are smaller than current value. i.e. a recursive function that ensures each node is within an allowable (min, max) range. At first, this range is infinite. When we traverse to the left, the min is negative infinity and the max is root. 

    ```java
    //Start root with (min=NULL) (max=NULL), NULL indicates no min or max.
    boolean checkBST(TreeNode n) { return checkBST(n) null) null); }
    /*When we branch left, max gets updated. When we branch right, min gets updated. If anything fails these checks, we stop and return false.*/
    boolean checkBST(TreeNode n) Integer min) Integer max) {
        if(n == nUll) { return true; }
        if((min != null && n.data <= min)||(max != null && n.data>max)){
            return false;
        }
        if(!checkBST(n.left, min, n.data)||!checkBST(n.right, n.data,max)) {
            return false;
        }
        return true;
    }
    ```

    The time complexity for this solution is **0(N)**, this is the best we can do, since any algorithm **must touch all N nodes**. Due to the use of recursion, the space complexity is 0 (log N) on a balanced tree, since we may recurse up to the depth of the tree.

- Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a binary search tree. You may assume that each node has a link to its parent.
  Hints: #79, #91

- Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
  projects, where the second project is dependent on the first project). All of a project's dependencies
  must be built before the project is. Find a build order that will allow the projects to be built. If there
  is no valid build order, return an error.
  EXAMPLE
  Input:
  projects: a, b, c, d, e, f
  dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
  Output: f, e, a, b, d, c
  Hints: #26, #47, #60, #85, # 125, # 733
  4.8 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
  of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
  necessarily a binary search tree.
  Hints: # 10, #16, #28, #36, #46, #70, #80, #96
  4.9 BST Sequences: A binary search tree was created by traversing through an array from left to right
  and inserting each element. Given a binary search tree with distinct elements, print all possible
  arrays that could have led to this tree.
  EXAMPLE
  Input:
  Output: {2, 1, 3}, {2, 3, 1}
  Hints: #39, #48, #66, #82
  110 Cracking the Coding Interview, 6th Edition
  Chapter 4 I Trees and Graphs
  4.10 Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
  algorithm to determine if T2 is a subtree of Tl.
  A tree T2 is a subtree ofTi if there exists a node n in Ti such that the subtree of n is identical to T2.
  That is, if you cut off the tree at node n, the two trees would be identical.
  Hints: #4, #7 7, #78, #37, #37
  4.11 Random Node: You are implementing a binary tree class from scratch which, in addition to
  insert, find, and delete, has a method getRandomNode() which returns a random node
  from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
  for getRandomNode, and explain how you would implement the rest of the methods.
  Hints: #42, #54, #62, #75, #89, #99, #7 72, #7 79
  4.12 Paths with Sum: You are given a binary tree in which each node contains an integer value (which
  might be positive or negative). Design an algorithm to count the number of paths that sum to a
  given value. The path does not need to start or end at the root or a leaf, but it must go downwards
  (traveling only from parent nodes to child nodes).
  Hints: #6, #74, #52, #68, #77, #87, #94, #703, #708, #115
  fl'1 } l )
  Additional Questions: Recursion (#8.10), System Design and Scalability (#9.2, #9.3), Sorting and Searching
  (#10.10), Hard Problems (#17.7, #17.12, #17.13, #17.14, #17.17, #17.20, #17.22, #17.25).
  Hints start on page 653.

## Bit Manipulation - Questions

5.1 Insertion: You are given two 32-bit numbers, Nand M, and two bit positions, i and
j. Write a method to insert Minto N such that M starts at bit j and ends at bit i. You
can assume that the bits j through i have enough space to fit all of M. That is, if
M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
EXAMPLE
Input: N 10011, i 2, j 6
Output: N 10001001100
Hints: # 137, #169, #215
CrackingTheCodinglnterview.com 16th Edition 115
Chapter 5 I Bit Manipulation
5.2 Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print
the binary representation. If the number cannot be represented accurately in binary with at most 32
characters, print "ERROR:'
Hints: #143, #167, #173, #269, #297
5.3 Flip Bit to Win: You have an integer and you can flip exactly one bit from a 13 to a 1. Write code to
find the length of the longest sequence of ls you could create.
EXAMPLE
Input: 1775 (or: 1113111131111)
Output: 8
Hints: #159, #226, #314, #352
5.4 Next Number: Given a positive integer, print the next smallest and the next largest number that
have the same number of 1 bits in their binary representation.
Hints: #147, # 175, #242, #312, #339, #358, #375, #390
.(
5.5 Debugger: Explain what the following code does: ( (n & (n -1) ) e) .
Hints: #151, #202, #261, #302, #346, #372, #383, #398
5.6 Conversion: Write a function to determine the number of bits you would need to flip to convert
integer A to integer B.
EXAMPLE
Input:
Output:
29 (or: 111131), 15 (or: 131111)
2
Hints: #336, #369
5.7 Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as
possible (e.g., bit 13 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
Hints: #145, #248, #328, #355
5.8 Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive
pixels to be stored in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will
be split across rows). The height of the screen, of course, can be derived from the length of the array
and the width. Implement a function that draws a horizontal line from (xl, y) to (x2 J y) .
The method signature should look something like:
drawLine(byte[] screen, int width, int xl, int x2, int y)
Hints: #366, #381, #384, #391
Additional Questions: Arrays and Strings (#1.1 , #1.4, #1.8), Math and Logic Puzzles (#6.10), Recursion (#8.4,
#8.14), Sorting and Searching (#10.7, #10.8), C++ (#12.10), Moderate Problems (#16.1, #16.7), Hard Problems
(#17.1).
Hints start on page 662.

## Math and Logic Puzzles -Questions

6.1 The Heavy Pill: You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has pills of weight
1.1 grams. Given a scale that provides an exact measurement, how would you find the heavy bottle?
You can only use the scale once.
Hints: # 786, #252, #379, #387
122 Cracking the Coding Interview, 6th Edition
Chapter 6 I Math and Logic Puzzles
6.2 Basketball: You have a basketball hoop and someone says that you can play one of two games.
Game 1: You get one shot to make the hoop.
Game 2: You get three shots and you have to make two of three shots.
If p is the probability of making a particular shot, for which values of p should you pick one game
or the other?
Hints: # 181, #239, #284, #323
I,
6.3 Dominos: There is an 8x8 chessboard in which two diagonally opposite corners have been cut off.
You are given 31 dominos, and a single domino can cover exactly two squares. Can you use the 31
dominos to cover the entire board? Prove your answer (by providing an example or showing why
it's impossible).
Hints: #367, #397
t • ., 1
6.4 Ants on a Triangle: There are three ants on different vertices of a triangle. What is the probability of
collision (between any two or all of them) if they start walking on the sides of the triangle? Assume
that each ant randomly picks a direction, with either direction being equally likely to be chosen, and
that they walk at the same speed.
Similarly, find the probability of collision with n ants on an n-vertex polygon.
Hints: # 157, # 195, #296
6.S Jugs of Water: You have a five-quart jug, a three-quart jug, and an unlimited supply of water (but
no measuring cups). How would you come up with exactly four quarts of water? Note that the jugs
are oddly shaped, such that filling up exactly "half" of the jug would be impossible.
Hints: #149, #379, #400
l' )
6.6 Blue-Eyed Island: A bunch of people are living on an island, when a visitor comes with a strange
order: all blue-eyed people must leave the island as soon as possible. There will be a flight out at
8:00 pm every evening. Each person can see everyone else's eye color, but they do not know their
own (nor is anyone allowed to tell them). Additionally, they do not know how many people have
blue eyes, although they do know that at least one person does. How many days will it take the
blue-eyed people to leave?
Hints: #218, #282, #341, #370
6.7 The Apocalypse: In the new post-apocalyptic world, the world queen is desperately concerned
about the birth rate. Therefore, she decrees that all families should ensure that they have one girl or
else they face massive fines. If all families abide by this policy-that is, they have continue to have
children until they have one girl, at which point they immediately stop-what will the gender ratio
of the new generation be? (Assume that the odds of someone having a boy or a girl on any given
pregnancy is equal.) Solve this out logically and then write a computer simulation of it.
Hints: # 154, #160, #171, #188, #201
CrackingTheCodinglnterview.com 16th Edition 123
Chapter 6 I Math and Logic Puzzles
6.8 The Egg Drop Problem: There is a building of 100 floors. If an egg drops from the Nth floor or
above, it will break. If it's dropped from any floor below, it will not break. You're given two eggs. Find
N, while minimizing the number of drops for the worst case.
Hints: # 7 56, #233, #294, #333, #357, #374, #395
6.9 100 Lockers: There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers.
Next, he closes every second locker. Then, on his third pass, he toggles every third locker (closes it if
it is open or opens it if it is closed). This process continues for 100 passes, such that on each pass i,
the man toggles every ith locker. After his 100th pass in the hallway, in which he toggles only locker
#100, how many lockers are open?
Hints: # 739, # 7 72, #264, #306
6.10 Poison: You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which
can be used to detect poison. A single drop of poison will turn the test strip positive permanently.
You can put any number of drops on a test strip at once and you can reuse a test strip as many times
as you'd like (as long as the results are negative). However, you can only run tests once per day and
it takes seven days to return a result. How would you figure out the poisoned bottle in as few days
as possible?
FOLLOW UP
Write code to simulate your approach.
Hints: #746, #763, #783, #797, #205, #227, #230, #247, #249
Additional Problems: Moderate Problems (#16.5), Hard Problems (#17.19)
Hints start on page 662.

## Object-Oriented Design - Questions

7.1 Deck of Cards: Design the data structures for a generic deck of cards. Explain how you would
subclass the data structures to implement blackjack.
Hints: #153, #275
I'
7.2 Call Center: Imagine you have a call center with three levels of employees: respondent, manager,
and director. An incoming telephone call must be first allocated to a respondent who is free. If the
respondent can't handle the call, he or she must escalate the call to a manager. If the manager is not
free or not able to handle it, then the call should be escalated to a director. Design the classes and
data structures for this problem. Implement a method dispatchCall() which assigns a call to
the first available employee.
Hints: #363
pq 1)7
7.3 Jukebox: Design a musical jukebox using object-oriented principles.
Hints: # 198
7.4 Parking Lot: Design a parking lot using object-oriented principles.
Hints: #258
7.5 Online Book Reader: Design the data structures for an online book reader system.
Hints: #344
r , d
CrackingTheCodinglnterview.com 16th Edition 127
Chapter 7 I Object-Oriented Design
7.6 Jigsaw: Implement an NxN jigsaw puzzle. Design the data structures and explain an algorithm to
solve the puzzle. You can assume that you have a fi tsWi th method which, when passed two
puzzle edges, returns true if the two edges belong together.
Hints: # 192, #238, #283
7.7 Chat Server: Explain how you would design a chat server. In particular, provide details about the
various backend components, classes, and methods. What would be the hardest problems to solve?
Hints: #213, #245, #271
7.8 Othello: Othello is played as follows: Each Othello piece is white on one side and black on the other.
When a piece is surrounded by its opponents on both the left and right sides, or both the top and
bottom, it is said to be captured and its color is flipped. On your turn, you must capture at least one
of your opponent's pieces. The game ends when either user has no more valid moves. The win is
assigned to the person with the most pieces. Implement the object-oriented design for Othello.
Hints: # 179, #228
7.9 Circular Array: Implement a C i rc u la rArray class that supports an array-like data structure which
can be efficiently rotated. If possible, the class should use a generic type (also called a template), and
should support iteration via the standard for (Obj 0 : circularArray) notation.
Hints: #389
128 Cracking the Coding Interview, 6th Edition
Chapter 7 I Object-Oriented Design
7.10 Minesweeper: Design and implement a text-based Minesweeper game. Minesweeper is the classic
single-player computer game where an NxN grid has B mines (or bombs) hidden across the grid. The
remaining cells are either blank or have a number behind them. The numbers reflect the number of
bombs in the surrounding eight cells. The user then uncovers a cell. If it is a bomb, the player loses.
If it is a number, the number is exposed. If it is a blank cell, this cell and all adjacent blank cells (up to
and including the surrounding numeric cells) are exposed. The player wins when all non-bomb cells
are exposed. The player can also flag certain places as potential bombs. This doesn't affect game
play, other than to block the user from accidentally clicking a cell that is thought to have a bomb.
(Tip for the reader: if you're not familiar with this game, please playa few rounds online first.)
This is a fully exposed board with 3
bombs. This is not shown to the user.
1 1 1
1 * 1
2 2 2
1 * 1
1 1 1
1 1 1
1 * 1
Clicking on cell (row = 1, col = 0)
would expose this:
1 ? 7 7. 7 ?
1 ? ? ? ? ?
2 ? 7 ? ? ?
1 ? ? ? ? ?
1 1 1 ? ? ?
1 ? ? ?
1 ? ? ?
Hints: #351, #361, #377, #386, #399
The player initially sees a board with
nothing exposed.
? ? ? ? ? ? ?
? ? ? ? ? ? ?
? ? ? ? ? ? ?
? ? ? ? ? ? ?
? ? ? ? ? ? ?
? ? ? ? ? ? ?
? ? ? ? ? ? ?
The user wins when everything other
than bombs has been exposed.
1 1 1
1 7 1
2 2 2
1 ? 1
1 1 1
1 1 1
1 ? 1
P 1).
7.11 File System: Explain the data structures and algorithms that you would use to design an in-memory
file system. Illustrate with an example in code where possible.
Hints: #141, #276
) ,
7.12 Hash Table: Design and implement a hash table which uses chaining (linked lists) to handle collisions.
Hints: #287, #307
Additional Questions: Threads and Locks (#16.3)
Hints start on page 662.

## Recursion and Dynamic Programming Questions

8.1 Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
steps at a time. Implement a method to count how many possible ways the child can run up the
stairs.
Hints: # 152, # 178, #217, #237, #262, #359
134 Cracking the Coding Interview, 6th Edition
Chapter 8 I Recursion and Dynamic Programming
8.2 Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
The robot can only move in two directions, right and down, but certain cells are "off limits" such that
the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
the bottom right.
Hints: #331, #360, #388
8.3 Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
array A.
FOLLOW UP
What if the values are not distinct?
Hints: # 170, #204, #240, #286, #340
8.4 Power Set: Write a method to return all subsets of a set.
Hints: #273, #290, #338, #354, #373
p~ !40
8.5 Recursive Multiply: Write a recursive function to multiply two positive integers without using the

* operator. You can use addition, subtraction, and bit shifting, but you should minimize the number
  of those operations.
  Hints: # 166, #203, #227, #234, #246, #280
  ('
  8.6 Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
  different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
  of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following
  constraints:
  (1) Only one disk can be moved at a time.
  (2) A disk is slid off the top of one tower onto another tower.
  (3) A disk cannot be placed on top of a smaller disk.
  Write a program to move the disks from the first tower to the last using stacks.
  Hints: # 144, #224, #250, #272, #318
  ['f ..
  8.7 Permutations without Dups: Write a method to compute all permutations of a string of unique
  characters.
  Hints: #150, #185, #200, #267, #278, #309, #335, #356
  ; }
  8.8 Permutations with Dups: Write a method to compute all permutations of a string whose characters
  are not necessarily unique. The list of permutations should not have duplicates.
  Hints: # 161, #190, #222, #255
  CrackingTheCodinglnterview.com 16th Edition 135
  Chapter 8 I Recursion and Dynamic Programming
  8.9 Parens: Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
  of n pairs of parentheses.
  EXAMPLE
  Input: 3
  Output: « () ) ) J «) (», «» () J () ( (», () () ()
  Hints: #138, #174, #187, #209, #243, #265, #295
  8.10 Paint Fill: Implement the "paint nil" function that one might see on many image editing programs.
  That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
  nil in the surrounding area until the color changes from the original color.
  Hints: #364, #382
  8.11 Coins: Given an innnite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
  pennies (1 cent), write code to calculate the number of ways of representing n cents.
  Hints: #300, #324, #343, #380, #394
  8.12 Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
  so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
  diagonals, not just the two that bisect the board.
  Hints: #308, #350, #371
  8.13 Stack of Boxes: You have a stack of n boxes, with widths Wi ' heights hi ' and depths di . The boxes
  cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
  larger than the box above it in width, height, and depth. Implement a method to compute the
  height of the tallest possible stack. The height of a stack is the sum of the heights of each box.
  Hints: #155, #194, #214, #260, #322, #368, #378
  8.14 Boolean Evaluation: Given a boolean expression consisting of the symbols e (false), 1 (true), &
  (AND), I (OR), and" (XOR), and a desired boolean result value result, implement a function to
  count the number of ways of parenthesizing the expression such that it evaluates to result.
  EXAMPLE
  countEval("1"eleI1"J false) -) 2
  countEval("e&e&e&l"lle"J true) -) 1e
  Hints: #148, #168, #197, #305, #327
  Additional Questions: Linked Lists (#2.2, #2.5, #2.6), Stacks and Queues (#3.3), Trees and Graphs (#4.2, #4.3,
  #4.4, #4.5, #4.8, #4.10, #4.11, #4.12), Math and Logic Puzzles (#6.6), Sorting and Searching (#10.5, #10.9,
  #10.10), C++ (#12.8), Moderate Problems (#16.11), Hard Problems (#17.4, #17.6, #17.8, #17.12, #17.13,
  #17.15, #17.16, #17.24, #17.25).
  Hints start on page 662.

## System Design and Scalability Questions

These questions are designed to mirror a real interview, so they will not always be well defined. Think about
what questions you would ask your interviewer and then make reasonable assumptions. You may make
different assumptions than us, and that will lead you to a very different design. That's okay!
9.1 Stock Data: Imagine you are building some sort of service that will be called by up to 1,000 client
applications to get simple end-of-day stock price information (open, close, high, low). You may
assume that you already have the data, and you can store it in any format you wish. How would you
design the client-facing service that provides the information to client applications? You are responsible
for the development, rollout, and ongoing monitoring and maintenance of the feed. Describe
the different methods you considered and why you would recommend your approach. Your service
can use any technologies you wish, and can distribute the information to the client applications in
any mechanism you choose.
Hints: #385, #396
144 Cracking the Coding Interview. 6th Edition
Chapter 9 I System Design and Scalability
9.2 Social Network: How would you design the data structures for a very large social network like Facebook
or Linkedln? Describe how you would design an algorithm to show the shortest path between
two people (e.g., Me -> Bob -> Susan -> Jason -> You).
Hints: #270, #285, #304, #327
••, .Il
9.3 Web Crawler: If you were designing a web crawler, how would you avoid getting into infinite loops?
Hints: #334, #353, #365
9.4 Duplicate URLs: You have 10 billion URLs. How do you detect the duplicate documents? In this
case, assume "duplicate" means that the URLs are identical.
Hints: #326, #347
t· j qr
9.5 Cache: Imagine a web server for a simplified search engine. This system has 100 machines to
respond to search queries, which may then call out using processSearch(string query) to
another cluster of machines to actually get the result. The machine which responds to a given query
is chosen at random, so you cannot guarantee that the same machine will always respond to the
same request. The method processSearch is very expensive. Design a caching mechanism for
the most recent queries. Be sure to explain how you would update the cache when data changes.
Hints: #259, #274, #293, #377
9.6 Sales Rank: A large eCommerce company wishes to list the best-selling products, overall and by
category. For example, one product might be the #1 056th best-selling product overall but the #13th
best-selling product under "Sports Equipment" and the #24th best-selling product under "Safety."
Describe how you would design this system.
Hints: #742, #758, #776, #789, #208, #223, #236, #244
9.7 Personal Financial Manager: Explain how you would design a personal financial manager (like
Mint.com). This system would connect to your bank accounts, analyze your spending habits, and
make recommendations.
Hints: #762, #780, #799, #272, #247, #276
9.8 Pastebin: Design a system like Pastebin, where a user can enter a piece of text and get a randomly
generated URL to access it.
Hints: #765, #784, #206, #232
Additional Questions: Object-Oriented Design (#7.7)
Hints start on page 662.

## Sorting and Searching Questions

10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
end to hold B. Write a method to merge B into A in sorted order.
Hints: #332
, • q
CrackingTheCodinglnterview.com 16th Edition 149
Chapter 10 I Sorting and Searching
10.2 Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
each other.
Hints: #177, #182, #263, #342
) .
10.3 Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown
number of times, write code to find an element in the array. You may assume that the array was
originally sorted in increasing order.
EXAMPLE
Input:findSin{lS, 16, 19, 2a, 25, 1, 3,4,5,7, la, 14}
Output: 8 (the index of 5 in the array)
Hints: #298, #310
10.4 Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size
method. It does, however, have an elementAt (i) method that returns the element at index i in
0(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data
structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
find the index at which an element x occurs. If x occurs multiple times, you may return any index.
Hints: #320, #337, #348
10.5 Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a
method to find the location of a given string.
EXAMPLE
Input: ball, {((at",
((JJ}
Output: 4
Hints: #256
UJJ , UJJ , ((car", teJJ , ((JJ , ((dad", ((JJ ,
10.6 Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you would sort
the file.
Hints: #207
10.7 Missing Int: Given an input file with four billion non-negative integers, provide an algorithm to
generate an integer that is not contained in the file. Assume you have 1 GB of memory available for
this task.
FOLLOW UP
What if you have only 10MB of memory? Assume that all the values are distinct and we now have
no more than one billion non-negative integers.
Hints: #235, #254, #281
1 SO Cracking the Coding Intervi ew, 6th Edition
Chapter 10 I Sorting and Searching
10.8 Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most 32,000. The
array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory
available, how would you print all duplicate elements in the array?
Hints: #289, #315
" .
10.9 Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in
ascending order, write a method to find an element.
Hints: #193, #211, #229, #251, #266, #279, #288, #291, #303, #317, #330
10.10 Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish to be able
to look up the rank of a number x (the number of values less than or equal to x). Implement the data
structures and algorithms to support these operations. That is, implement the method track (int
x), which is called when each number is generated, and the method getRankOfNumber(int
x), which returns the number of values less than or equal to X (not including x itself).
EXAMPLE
Stream(inorderofappearance):5, 1,4,4, 5, 9, 7, 13, 3
getRankOfNumber(l) e
getRankOfNumber(3) 1
getRankOfNumber(4) 3
Hints: #301, #376, #392
10.11 Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal to
the adjacent integers and a "valley" is an element which is less than or equal to the adjacent integers.
For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an array
of integers, sort the array into an alternating sequence of peaks and valleys.
EXAMPLE
Input: {S, 3, 1,2, 3}
Output: {S, 1,3,2, 3}
Hints: # 196, #219, #231, #253, #277, #292, #316
Additional Questions: Arrays and Strings (#1.2), Recursion (#8.3), Moderate (#16.10, #16.16, #16.21, #16.24),
Hard (#17.11 , #17.26).
Hints start on page 662.

## Java Interview Questions

  Please note that because virtually all the solutions in this book are implemented with Java, we have selected
  only a small number of questions for this chapter. Moreover, most of these questions deal with the "trivia" of
  the languages, since the rest of the book is filled with Java programming questions.
  13.1 Private Constructor: In terms of inheritance, what is the effect of keeping a constructor private?
  Hints: #404
  13.2 Return from Finally: In Java, does the finally block get executed if we insert a return statement
  inside the try block of a try-catch-finally?
  Hints: #409
  t qt1)
  13.3 Final, etc.: What is the difference between final, finally, and finalize?
  Hints: #4 12
  P ·U1
  13.4 Generics vs. Templates: Explain the difference between templates in C++ and generics in Java.
  Hints: #4 16, #425
  13.S TreeMap, HashMap, LinkedHashMap: Explain the differences between TreeMap, HashMap, and
  LinkedHashMap. Provide an example of when each one would be best.
  Hints: #420, #424, #430, #454
  CrackingTheCodinglnterview.com 16th Edition 167
  Chapter 13 I Java
  13.6 Object Reflection: Explain what object reflection is in Java and why it is useful.
  Hints: #435
  13.7 Lambda Expressions: There is a class Country that has methods getContinentO and
  getPopulationO. Write a function int get Population (List<Country> countries,
  String continent) that computes the total population of a given continent, given a list of all
  countries and the name of a continent.
  Hints: #448, #461, #464
  13.8 Lambda Random: Using Lambda expressions, write a function List<Integer>
  getRandomSubset (List< Integer> list) that returns a random subset of arbitrary size. All
  subsets (including the empty set) should be equally likely to be chosen.
  Hints: #443, #450, #457
  Additional Questions: Arrays and Strings (#1.3), Object-Oriented Design (#7.12), Threads and Locks (#15.3)
  Hints start on page 676.



## Database Interview Questions

  Questions 1 through 3 refer to the database schema at the end of the chapter. Each apartment can have
  multiple tenants, and each tenant can have multiple apartments. Each apartment belongs to one building,
  and each building belongs to one complex.
  14.1 Multiple Apartments: Write a SQL query to get a list of tenants who are renting more than one
  apartment.
  Hints: #40B
  172 Cracking the Coding Interview, 6th Edition
  Chapter 14 I Databases
  14.2 Open Requests: Write a SQL query to get a list of all buildings and the number of open requests
  (Requests in which status equals 'Open' ).
  Hints:#411
  14.3 Close All Requests: Building #11 is undergoing a major renovation. Implement a query to close all
  requests from apartments in this building.
  Hints: #431
  14.4 Joins: What are the different types of joins? Please explain how they differ and why certain types
  are better in certain situations.
  Hints: #451
  14.5 Denormalization: What is denormalization? Explain the pros and cons.
  Hints: #444, #455
  14.6 Entity-Relationship Diagram: Draw an entity-relationship diagram for a database with companies.
  people, and professionals (people who work for companies).
  Hints: #436
  14.7 Design Grade Database: Imagine a simple database storing information for students' grades.
  Design what this database might look like and provide a SQL query to return a list of the honor roll
  students (top 10%), sorted by their grade point average.
  Hints: #428, #442
  Additional Questions: Object-Oriented Design (#7.7), System Design and Scalability (#9.6)
  Hints start on page 676.
  Apartments Buildings Requests
  AptID int BuildingID int RequestID int
  UnitNumber varchar(18) ComplexID int Status varchar(188)
  BuildingID int BuildingName varchar(188) AptID int
  Address varchar(S88) Description varchar(S88)
  Complexes AptTenants Tenants
  ComplexID lint TenantID l int TenantID lint
  ComplexName I varchar(188) AptID j int TenantName I varchar(188)

1110. nation, try a trie.
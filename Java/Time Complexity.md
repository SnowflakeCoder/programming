# Time Complexity

## List

|                          | **Add** | **Remove**  | **Get** | **Contains** | **Data Structure** |
| ------------------------ | ------- | ----------- | ------- | ------------ | ------------------ |
| **ArrayList**            | O(1)    | **O(n)**    | O(1)    | O(n)         | Array              |
| **LinkedList**           | O(1)    | O(1) / O(n) | O(n)    | O(n)         | Linked List        |
| **CopyonWriteArrayList** | O(n)    | **O(n)**    | O(1)    | O(n)         | Array              |

Note: Removal for a singly-linked list is **only `O(1)` if you already have references to the node** you want to remove and the one before. All this is in contrast to an array-based list where insertions and removal are `O(n)` because you have to **shift elements** along.

Otherwise if you want to delete a specific element from a singly-linked list, the **time complexity is `O(n)`** (where `n` is the number of elements) because you have to find the element first.

| **Operation**         | **LinkedList time complexity** | **ArrayList time complexity**                          | **Preferred** |
| --------------------- | ------------------------------ | ------------------------------------------------------ | ------------- |
| Insert at last index  | O(1)                           | O(1) (If array copy operation is Considered then O(N)) | LinkedList    |
| Insert at given index | O(N)                           | O(N)                                                   | LinkedList    |
| Search by value       | O(N)                           | O(N)                                                   | ArrayList     |
| Get by index          | O(N)                           | O(1)                                                   | ArrayList     |
| Remove by value       | O(N)                           | O(N)                                                   | LinkedList    |
| Remove by index       | O(N)                           | O(N)                                                   | LinkedList    |

https://dzone.com/articles/performance-analysis-of-arraylist-and-linkedlist-i

https://www.bigocheatsheet.com/

## Set

|                         | **Add**  | **Contains** | **Next** | **Data Structure**       |
| ----------------------- | -------- | ------------ | -------- | ------------------------ |
| **HashSet**             | O(1)     | O(1)         | O(h/n)   | Hash Table               |
| **LinkedHashSet**       | O(1)     | O(1)         | O(1)     | Hash Table + Linked List |
| **EnumSet**             | O(1)     | O(1)         | O(1)     | Bit Vector               |
| **TreeSet**             | O(log n) | O(log n)     | O(log n) | Red-black tree           |
| **CopyonWriteArraySet** | O(n)     | O(n)         | O(1)     | Array                    |
| **ConcurrentSkipList**  | O(log n) | O(log n)     | O(1)     | Skip List                |

## Queue

|                             | **Offer** | **Peak** | **Poll** | **Size** | **Data Structure** |
| --------------------------- | --------- | -------- | -------- | -------- | ------------------ |
| **PriorityQueue**           | O(log n ) | O(1)     | O(log n) | O(1)     | Priority Heap      |
| **LinkedList**              | O(1)      | O(1)     | O(1)     | O(1)     | Array              |
| **ArrayDequeue**            | O(1)      | O(1)     | O(1)     | O(1)     | Linked List        |
| **ConcurrentLinkedQueue**   | O(1)      | O(1)     | O(1)     | O(n)     | Linked List        |
| **ArrayBlockingQueue**      | O(1)      | O(1)     | O(1)     | O(1)     | Array              |
| **PriorirityBlockingQueue** | O(log n)  | O(1)     | O(log n) | O(1)     | Priority Heap      |
| **SynchronousQueue**        | O(1)      | O(1)     | O(1)     | O(1)     | None!              |
| **DelayQueue**              | O(log n)  | O(1)     | O(log n) | O(1)     | Priority Heap      |
| **LinkedBlockingQueue**     | O(1)      | O(1)     | O(1)     | O(1)     | Linked List        |

## Map

|                           | **Get**  | **ContainsKey** | **Next** | **Data Structure**       |
| ------------------------- | -------- | --------------- | -------- | ------------------------ |
| **HashMap**               | O(1)     | O(1)            | O(h / n) | Hash Table               |
| **LinkedHashMap**         | O(1)     | O(1)            | O(1)     | Hash Table + Linked List |
| **IdentityHashMap**       | O(1)     | O(1)            | O(h / n) | Array                    |
| **WeakHashMap**           | O(1)     | O(1)            | O(h / n) | Hash Table               |
| **EnumMap**               | O(1)     | O(1)            | O(1)     | Array                    |
| **TreeMap**               | O(log n) | O(log n)        | O(log n) | Red-black tree           |
| **ConcurrentHashMap**     | O(1)     | O(1)            | O(h / n) | Hash Tables              |
| **ConcurrentSkipListMap** | O(log n) | O(log n)        | O(1)     | Skip List                |

http://infotechgems.blogspot.com/2011/11/java-collections-performance-time.html


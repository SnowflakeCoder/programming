# Time Complexity

## List

|                          | **Add** | **Remove** | **Get** | **Contains** | **Data Structure** |
| ------------------------ | ------- | ---------- | ------- | ------------ | ------------------ |
| **ArrayList**            | O(1)    | **O(n)**   | O(1)    | O(n)         | Array              |
| **LinkedList**           | O(1)    | O(1)       | O(n)    | O(n)         | Linked List        |
| **CopyonWriteArrayList** | O(n)    | **O(n)**   | O(1)    | O(n)         | Array              |

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

# Collections.binarySearch()

- public static int binarySearch(List slist, T key)
  - **Returns index** of key in sorted list **sorted in ascending order**
- public static int binarySearch(List slist, T key, Comparator c)
  - Returns index of key in sorted list sorted in order defined by Comparator c.
- If key is not present, the it returns "(-(insertion point) - 1)". 
  The insertion point is defined as the point at which the key would be inserted into the list.

```java
int result1 = Collections.binarySearch(list, x, (a, b) -> Integer.compare(a, b)); 
```

# Hashtable

A Hashtable is an array of list. Each list is known as a bucket. The position of bucket is identified by calling the hashcode() method. A Hashtable contains values based on the key.
It contains only unique elements.
It may have not have any null key or value.
It is synchronized.
It is a legacy class.

- Hashtable is thread-safe.
- Hashtable **doesn’t allow any null key or value**.

# HashMap

- HashMap offers **0(1) lookup and insertion**. If you iterate through the keys, though, the ordering of the keys is essentially arbitrary. It is implemented by an **array of linked lists**.
- A HashMap contains values based on the key.
- It may have **one null key** and **multiple null values**.
- It **maintains no order**.
- HashMap is **not-thread safe** 

Why HashTable doesn’t allow null and HashMap does?
To successfully store and retrieve objects from a HashTable, the objects used as keys **must implement the hashCode method and the equals method**. Since null is not an object, it can’t implement these methods. HashMap is an advanced version and improvement on the Hashtable. HashMap was created later and it **returns 0 if key = null**.

## Internal Structure

Internally HashMap contains an **array of Node** and a node is represented as a class which contains 4 fields.

**HashMap.Node**

```java
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash; 
        final K key;
        V value;
        Node<K,V> next; // to save next value in the linklist.
```

# LinkedHashMap

- LinkedHashMap offers **0(1) lookup and insertion** (get/put/containsKey()). Keys are **ordered by their insertion order**. It is implemented by **doubly-linked buckets**.
- A LinkedHashMap contains values based on the key.
- It may have one null key and multiple null values.
- It is same as HashMap but it **maintains insertion order**.
- Returns **LinkedEntrySet** for keySet() and LinkedEntrySet for entryset(), 

```java
// linked hashmap fields     
transient LinkedHashMap.Entry<K,V> head; // head (eldest) of the doubly linked list.
transient LinkedHashMap.Entry<K,V> tail; // tail (youngest) of the doubly linked list.
// iteration ordering method, true => access-order, false => insertion-order.
final boolean accessOrder; 
```

## How LinkedHashMap work internally?

LinkedHashMap **extends HashMap** **implements Map**

In this class, the **data is stored in the form of nodes**. The implementation of the LinkedHashMap is very **similar to a doubly-linked list**.

```java
 static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
// before and after are used to maintain the order. after give next value in the insertion order but next give next value in the hashed linklist.
```



# TreeMap

- TreeMap offers **O(log N) lookup and insertion**(add, remove, containsKey). **Keys are ordered**, so if you need to iterate through the keys in sorted order, you can. This means that **keys must implement the Comparable interface**. TreeMap is implemented by a **Red-Black Tree**.
- The map is sorted according to the **natural ordering of its keys**, or by a **Comparator provided at map creation time**, depending on which constructor is used. 
- A TreeMap contains values based on the key. It implements the NavigableMap interface and extends AbstractMap class.
- It **cannot have null** key but can have multiple null values.
- It is same as HashMap instead **maintains ascending order**(Sorted using the natural order of its key).
- TreeMap also provides some cool methods for first, last, floor and ceiling of keys.
- TreeMap.entrySet() method returns a collection-view(Set<Map.Entry>) in sorted way.

## floorEntry & floorKey()

- floorEntry() : It returns a key-value mapping associated with the **greatest key less than or equal** to the given key, or null if there is no such key.
- floorKey() : It returns the greatest key less than or equal to the given key, or null if there is no such key.

# HashSet

- HashSet uses HashMap internally.



# Fail-fast and Fail-safe iterations

First of all, there is **no term as fail-safe** as Java SE specifications does not use this term. Non-Fail Fast (a.k.a. Fail-safe) iterators **will not throw any exception** if the collection is modified while iterating over it. Eg: ArrayList, HashMap. Whereas **Fail-fast iterators** throw an exception (ConcurrentModificationException) if the collection is modified while iterating over it. Eg : CopyOnWriteArrayList, ConcurrentHashMap .

## Fail-Fast Iterators internal working:

Every fail fast collection has a **modCount** field, to represent how many times the collection has changed/modified. So at every modification of this collection we increment the modCount value. So the modCount is incremented in below cases:

1. When one or more elements are removed.
2. When one or more elements are added.
3. When the collection is **replaced with other collection**.
4. When the **collection is sorted**.

when we create an iterator, iterator stores the current modCount value (field **expectedModCount**). Now while the iteration is going on, if there is any change made in the collection, the modCOunt will change, and <u>expectedModCount will not be hence equal to the modCount</u>, so **ConcurrentModificationException** will be thrown.

**Note**: If we remove/add the element using the **remove() or add() of iterator** instead of collection, then in that case **no exception will occur**. Because the remove/add methods of iterators call the remove/add method of collection internally, and also it **reasigns the expectedModCount to new modCount value**.

**Note 2**: The fail-fast behavior of an iterator **cannot be guaranteed** as it is, generally speaking, impossible to make any hard guarantees in the presence of **unsynchronized concurrent modification**. Fail-fast iterators throw ConcurrentModificationException on a **best-effort basis**. Therefore, it would be **wrong to write a program that depended on this exception** for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.

## Fail-Safe Iterators internal working:

Unlike the fail-fast iterators, these **iterators traverse over the clone of the collection**. So even if the original collection gets structurally modified, **no exception will be thrown**. E.g. in case of CopyOnWriteArrayList the <u>original collections is passed and is stored in the iterator</u> (field **snapshot**). So all the iterator methods will work on this snapshot instance. So even if there is any change in the original collection, no exception will be thrown. But the **iterator will not reflect the latest state of the collection**. These iterators make a copy of the internal collection (object array) and iterates over the copied collection. So any <u>structural modification done to the iterator affects the copied collection, not original collection</u>. So, original collection remains structurally unchanged.

**Note**: although it does not throw any exception, but the downsides of this iterator are:

1. They will **not reflect the latest state** of the collection.
2. It **requires extra memory** as it clones the collection.

https://www.geeksforgeeks.org/fail-fast-fail-safe-iterators-java/

### ConcurrentHashMap - weakly consistent

**Note:** In case of **ConcurrentHashMap**, it **does not operate on a separate copy** although it is not fail-fast. Instead, it has semantics that is described by the official specification as **weakly consistent(memory consistency properties in Java)**. The iterators returned by ConcurrentHashMap is **weakly consistent**. This means that this iterator can tolerate concurrent modification, traverses elements as they existed when iterator was constructed and **may (but not guaranteed to) reflect modifications to the collection** after the construction of the iterator.

# LinkedList

LinkedList is a **linear data structure** where the elements are **not stored in contiguous locations** and every element is a separate object with a data part and address part. The elements are linked using pointers and addresses. Each element is known as a node. Few disadvantages are the nodes **cannot be accessed directly** instead we need to start from the head and follow through the link to reach to a node.

## Internal Design

Since a LinkedList acts as a dynamic array and we **do not have to specify the size** while creating it, the size of the list **automatically increases** when we dynamically add items. And also, the elements are not stored in a continuous fashion. Therefore, there is **no need to increase the size**. Internally, the LinkedList is implemented using the doubly linked list data structure. The main difference between a normal linked list and a doubly LinkedList is that a doubly linked list contains an extra pointer, typically called the previous pointer, together with the next pointer and data which are there in the singly linked list.
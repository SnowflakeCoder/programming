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




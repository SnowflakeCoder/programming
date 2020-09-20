# Data Structure

https://leetcode.com/tag/design/

## Insert Delete GetRandom O(1)

Design a data structure that supports Insert(val), Delete(val), GetRandom() operations in **average O(1) time**. getRandom(): Returns a random element from current set of elements. GetRandom choose a random index and then retrieve an element with that index. 

**Average insert time - O(1) **

Both Hashmap and ArrayList provides Insert in average constant time.

- **Hashmap**: provides **Insert and Delete in average constant time**, although has **problems with GetRandom**. But there is **no indexes in hashmap**, and hence to get true random value, we first need to **convert hashmap keys in a list**, that would take **linear time**. So the solution is to **build a list of keys aside** and to use this list to compute GetRandom in constant time.

- **ArrayList**: **has indexes** and could provide **Insert and GetRandom in average constant time**, though has **problems with Delete**. To delete a value at arbitrary index takes linear time. The solution here is to **always delete the last value**:

  - Swap the element to delete with the last one.
  - Pop the last element out.

  For that, one has to **compute an index of each element in constant time**, and hence needs a **hashmap which stores element -> its index dictionary**.

### Solution: HashMap + ArrayList

So we use a **combination of data structures**: **HashMap + ArrayList**

- Hashmap =>  {element,  its index}.
- Array List =>  {list of elements}.

> **Insert**
>
> - Add -> {value,  its index} into Map, average O(1) time.
> - Append value to array list, average O(1) time as well.
>
> **Delete**
>
> - Retrieve an index of element to delete from the Map, O(1) time.
> - Move the last element to the place of the element to delete, O(1) time.
> - Pop the last element out, O(1) time.
>
> **GetRandom**
>
> - GetRandom could be implemented in O(1) time using Random object in Java.

```java
class RandomizedSet {
  Map<Integer, Integer> dict; // Initialize inside the constructure.
  List<Integer> list; // Initialize inside the constructure.
  Random rand = new Random();
  
  public boolean insert(int val) {
    if (dict.containsKey(val)) 
    	{return false;} // return false for duplicates
    dict.put(val, list.size());
    list.add(list.size(), val);
    return true;
  }
  public boolean remove(int val) {
    if (! dict.containsKey(val)) 
    	{return false;}
    int lastElement = list.get(list.size() - 1); // find last element
    int idx = dict.get(val); // find index of the element to delete
    list.set(idx, lastElement);//set last element to index of the element to delete
    dict.put(lastElement, idx);// update index in map too.
    list.remove(list.size() - 1);// delete the last element from list.
    dict.remove(val); // delete element from map too.
    return true;
  }

  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}
```

### Complexity Analysis

- **Time complexity** : GetRandom is always O(1). Insert and Delete both have O(1) average time complexity, and **O(N) in the worst-case scenario** when the operation exceeds the capacity of currently allocated array/hashmap and invokes **space reallocation**.
- **Space complexity** : O(N) to store N elements.

------

------

## Insert Delete GetRandom O(1) - Duplicates allowed

Only change is Hashmap =>  {element,  **Set{index}** }. Using **set {indexes} here instead of just one index**.

```java
	public boolean insert(int val) {
        if (!idx.containsKey(val)) 
	        {idx.put(val, new LinkedHashSet<Integer>());}
        idx.get(val).add(lst.size()); // add new index to list of other indexes.
        lst.add(val);
        return idx.get(val).size() == 1;
    }

	public boolean remove(int val) { // find one index and then delete
        if (!idx.containsKey(val) || idx.get(val).size() == 0) {return false;}
        int remove_idx = idx.get(val).iterator().next();
        idx.get(val).remove(remove_idx);
        int last = lst.get(lst.size() - 1);
        lst.set(remove_idx, last);
        idx.get(last).add(remove_idx);
        idx.get(last).remove(lst.size() - 1);
        lst.remove(lst.size() - 1);
        return true;
    }
```

------

------

## LRU Cache

Design and implement a data structure for LRU cache. It should support operations: **get and put in O(1) time complexity**. The cache is initialized with a positive capacity.

- **get(key)** - Get the value (will always be positive) of the key if key exists in the cache, otherwise return -1.
- **put(key, value)** - Set or insert the value if the key is not already present. 
  - **delete** - When the cache reached its capacity, it should **invalidate the least recently used item before inserting a new item**.

### 1: Ordered dictionary

**Hashmap** provides get and put in O(1) time and using **linked list** we can remove least recently used item in O(1) time. There is a structure called **ordered dictionary**, it combines behind both hashmap and linked list. In Python this structure is called OrderedDict and in Java **LinkedHashMap**. Note that **insertion order is not affected** if a key is re-inserted into the LinkedHashMap.

#### Complexity Analysis

- Time complexity : O(1) both for put and get since all operations with ordered dictionary : get/in/set/move_to_end/popitem (get/containsKey/put/remove) are done in a constant time.
- Space complexity : O(capacity) since the space is used only for an **ordered dictionary with at most capacity + 1 elements**. 

### 2: Hashmap + DoubleLinkedList	

The problem can be solved with a **hashmap that keeps track of the keys** and its values in the double linked list. That results in O(1) time for put and get operations and allows to remove the first added node in O(1) time as well. One advantage of double linked list is that the **node can remove itself without other reference**. In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark the boundary, so that we don't need to check the null node during the update.

#### Complexity Analysis

Time complexity : O(1) both for put and get.

Space complexity : O(capacity) since the space is used only for a hashmap and double linked list with at most capacity + 1 elements.

## Time Based Key-Value Store

https://leetcode.com/problems/time-based-key-value-store/

Create a **time based key-value store class** `TimeMap`, that supports two operations.

- set(string key, string value, int timestamp)
  - Stores the `key` and `value`, along with the given `timestamp`.
- get(string key, int timestamp)
  - Returns a value such that `set(key, value, timestamp_prev)` was called previously, with `timestamp_prev <= timestamp`.
  - If there are multiple such values, it returns the one with the largest `timestamp_prev`.
  - If there are no values, it returns the empty string (`""`).


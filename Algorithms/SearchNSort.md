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

 



 

 

 

 
# JAVA API

## String

### split

- public String[] split(String regex) =>  return split(regex, 0);

- public String[] split(String regex, int limit)

  - **limit > 0** : The pattern will be applied **at most limit-1 times**, the resulting <u>array’s length will not be more than limit</u>, and the resulting array’s last entry will contain all input beyond the last matched pattern.
  - **limit < 0** : In this case, the pattern will be applied **as many times as possible**, and the resulting array can be of any size.
  - **limit = 0** : In this case, the pattern will be applied **as many times as possible**, the resulting array can be of any size, and <u>trailing empty strings will be discarded</u>.

- ```java
  //string to be splitted be : geekss@for@geekss
  @	2		{“geekss”, ”for@geekss”}//last entry will contain remaining inputs
  @	5	or 	@	-2		{“geekss”, ”for”, ”geekss”}    
  s	5	or s	-2		{“geek”, ”“, “@for@geek”, “”, “”}
  s	0		{“geek”, ””, ”@for@geek”} //trailing empty strings will be discarded
  ```



## Map<K, V>

### V putIfAbsent(K key, V value)

```java
V v = get(key); // this even cosider if previous value is null.
if (v == null) {
	v = put(key, value);
}
return v;
```

- If the key is not associated with any value or is **mapped to `null` value**, then associates it with the given value and **returns `null`**, else returns <u>previous associated value</u>.
- Returns the previous value associated with the specified key, or `null` if there was no mapping for the key or is **mapped to `null` value**. (if the Map implementation supports null values.)

### V getOrDefault(Object key, V defVal)

```java
V v;
//extra containsKey(key) check is to handle, if key mapped to a "null" value then returns "null".
return (((v = get(key)) != null) || containsKey(key)) ? v : defaultValue;
```

- Returns the value to which the specified key is mapped, or `defaultValue` if this map contains no mapping for the key.




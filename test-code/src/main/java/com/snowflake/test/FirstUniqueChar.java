package com.snowflake.test;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueChar {

	public static void main(String[] args) {
		System.out.println(firstUniqChar("loveleetcode"));
	}
	
/*	
public int firstUniqChar(String s) {
        
        List<Integer> countList = new ArrayList<>(1000);
        char[] charArr = s.toCharArray();
        
        for(int i = 0; i< charArr.length; i++){
            int charIndex = (int)charArr[i];
            
            // System.out.println(charIndex);
            Integer count = null;
            if(countList.size() > charIndex){
                count = countList.get(charIndex);    
            }
            if(count == null){
                countList.set(charIndex, 1);
            }
            else{
                countList.set(charIndex, count++);
            }
        }
        
        for(int i = 0; i< charArr.length; i++){
            int charIndex = (int)charArr[i];
            Integer count = countList.get(charIndex);
            if(count != null && count == 1){
                return i;
            }
        }
        return -1;
        
    }

*/

private static int firstUniqChar(String s) {
    
    Map<Character, Integer> countMap = new HashMap<>();
    char[] charArr = s.toCharArray();
    
    for(int i = 0; i< charArr.length; i++){
        int charIndex = (int)charArr[i];
        Integer count = countMap.get(charArr[i]);    
        
        if(count == null){
            countMap.put(charArr[i], 1);
        }
        else{
            countMap.put(charArr[i], ++count);
        }
    }
    
    for(int i = 0; i< charArr.length; i++){
        Integer count = countMap.get(charArr[i]);
        if(count != null && count == 1){
            return i;
        }
    }
    return -1;
    
}

}

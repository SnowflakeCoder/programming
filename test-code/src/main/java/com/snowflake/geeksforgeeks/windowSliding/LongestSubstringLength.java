package com.snowflake.geeksforgeeks.windowSliding;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Solution - keep adding char and index to a map and if same char come remove all char from map before that index ad also reduce the length.
 * 
 * @author arun.kumar.ms
 *
 */
public class LongestSubstringLength {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("pwwkew"));
		System.out.println(lengthOfLongestSubstring2("pwwkew"));
		System.out.println(lengthOfLongestSubstring2("bbbbb"));
		System.out.println(lengthOfLongestSubstring2(" "));
		System.out.println(lengthOfLongestSubstring2("abcabcbb"));
	}
	
	private static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int currentlength = 0;
        int startIndex = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        
        for(int index = 0; index < s.length(); index++){
            Character c = s.charAt(index);
            
            Integer currentIndex = charMap.get(c);
            if(currentIndex != null){
                if(maxLength < currentlength){
                    maxLength = currentlength;
                }
                
                /*
                 * remove all char from map before that index.
                 */
                while(startIndex <= currentIndex){
                    Character oldChar = s.charAt(startIndex);
                    charMap.remove(oldChar);
                    currentlength --;
                    startIndex++;
                }
            }
            charMap.put(c, index);
            currentlength ++;
        }
        if(maxLength < currentlength){
          maxLength = currentlength;
         }
        return maxLength;
    }
	
	/*
	 * Much improved version
	 */
	
	private static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        int currentlength = 0;
        int startIndex = -1;
        Map<Character, Integer> charMap = new HashMap<>();
        
        for(int index = 0; index < s.length(); index++){
            Character c = s.charAt(index);
            Integer currentIndex = charMap.get(c);
            if(currentIndex != null){
            	currentlength = index - startIndex -1 ;
                if(maxLength < currentlength){
                    maxLength = currentlength;
                }
                if(currentIndex > startIndex) {
                    startIndex = currentIndex;
                }
            }
            charMap.put(c, index);
        }
        currentlength = s.length() - startIndex -1 ;
        if(maxLength < currentlength){
          maxLength = currentlength;
         }
        return maxLength;
    }
}

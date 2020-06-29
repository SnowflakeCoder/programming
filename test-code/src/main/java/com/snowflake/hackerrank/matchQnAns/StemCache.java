package com.snowflake.hackerrank.matchQnAns;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class will index each stemword and is useful for comparing matched stems.
 * 
 * @author arun.kumar.ms
 *
 */

public class StemCache {
	
	private static Map<String, Integer> cache = new HashMap<>();
	private static int currentIndex = 1;
	
	
	public static int getStemIndex(String stemWord) {
		stemWord = stemWord.trim();
		Integer stemIndex = cache.get(stemWord);
		if(stemIndex == null) {
			stemIndex = currentIndex;
			currentIndex++;
			cache.put(stemWord, stemIndex);
		}
		return stemIndex;
	}
	
}

package com.snowflake.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class NonRepeatCharacter {

	public static void main(String[] args) {

		Character nonRepeat = findFirstNonRepeat("aaa", 1);

		if (nonRepeat == null) {
			System.out.println("No non repeat charatcter present");
		}

		nonRepeat = findFirstNonRepeat("aba", 1);

		System.out.println("äctual: " + nonRepeat + " expected : b");


		nonRepeat = findFirstNonRepeat("abaab", 1);

		System.out.println("äctual: " + nonRepeat + " expected : null");


		
		nonRepeat = findFirstNonRepeat("", 1);

		System.out.println("äctual: " + nonRepeat + " expected : null");

		
		nonRepeat = findFirstNonRepeat(null, 1);

		System.out.println("äctual: " + nonRepeat + " expected : null");

		

		nonRepeat = findFirstNonRepeat("abaAB", 1);

		System.out.println("äctual: " + nonRepeat + " expected : null");


		nonRepeat = findFirstNonRepeat("abc a", 2);

		System.out.println("äctual: " + nonRepeat + " expected : c");
		
		
	}

	/**
	 * Cases are
	 * 
	 * 1) str can be null or doesnt contain a Non repeat character then return null
	 * 
	 * 2) else return the first Non repeat character.
	 * 
	 * Time complexity - O(N)
	 * 
	 * Space complexity = O(N)
	 * 
	 * Return null if there is no NonRepeat character
	 * 
	 * @param str
	 * @return
	 */

	public static Character findFirstNonRepeat(String str, int charIndex) {

		if(charIndex < 1) {
			return null;
		}
		
		if (StringUtils.isEmpty(str)) {
			return null;
		}

		Map<Character, Count> characterMap = new HashMap<>();

		for (int index =0 ; index < str.length(); index++) {
			
			Character charCaseInsensitive = Character.toLowerCase(str.charAt(index));
			
			Count count = characterMap.get(charCaseInsensitive);

			if (count == null) {
				count = new Count();
				characterMap.put(charCaseInsensitive, count);
			}
			count.incrementValue();
		}

		int currentcharIndex = 0;
		
		for (int index =0 ; index < str.length(); index++) {
			char character = str.charAt(index);
			Character charCaseInsensitive = Character.toLowerCase(character);
			Count count = characterMap.get(charCaseInsensitive);

			if (count.getValue() == 1) {
				currentcharIndex++;
				if(currentcharIndex == charIndex) {
					return character;
				}
			}
		}

		return null;
	}
	
	private static class Count {
		int value;
		
		public int getValue() {
			return value;
		}
		
		public void incrementValue() {
			value++;
		}
		
	}

}

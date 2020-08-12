package com.snowflake.hackerrank.matchQnAns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stopwords {

	/*
	 * 
	 * make each sentence to lowercase before tokenize.
	 * TODO
	 * We need to remove special chars like ( and ) from (horse family)
	 * 
	 */

	public static String invalidChars = "[(),.-/?]";

	private static String[] stopWords = { "are", "of", "by", "their", "and", "in", "to", "they", "that", "have", "there",
			"the", "is", "an", "it", "while", "them", "a", "as", "such", "however", "had", "on", "for", "but", "which",
			"along", "with", "other", "though"};	
	
	public static List<Integer> stopWordIndexes = Arrays.stream(stopWords).map(word -> StemCache.getStemIndex(word)).sorted().collect(Collectors.toList()); 
	
	public static List<Integer> maxOccuredIndexes = new ArrayList<>();
	
}

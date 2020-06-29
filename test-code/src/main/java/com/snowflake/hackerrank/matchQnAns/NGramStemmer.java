package com.snowflake.hackerrank.matchQnAns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class NGramStemmer {

	public static List<String> stem(String source, int ngramvalue) {
		List<String> wordTokens = new ArrayList<>();
		return wordTokens;
	}

	public static List<Integer> findMostOccuring(String source, int maxOccurance) {

		Map<Integer, Integer> occuranceMap = new HashMap<Integer, Integer>();

		Arrays.stream(source.trim().toLowerCase().split(" "))
		.map(word -> word.replaceAll(Stopwords.invalidChars, ""))
		.filter(word -> !(isBlank(word))).map(word -> StemCache.getStemIndex(word))
		.filter(index -> (Collections.binarySearch(Stopwords.stopWordIndexes, index) < 0)).forEach(index -> {
			Integer currentOccurance = occuranceMap.get(index);
			if (currentOccurance == null) {
				currentOccurance = 1;
			} else {
				currentOccurance++;
			}
			occuranceMap.put(index, currentOccurance);
		});

		List<Integer> mostOccurance = new ArrayList<>();
		for(Entry<Integer, Integer> entry : occuranceMap.entrySet()) 
        { 
            if (maxOccurance <= entry.getValue()) 
            { 
            	mostOccurance.add(entry.getKey());
            } 
        }
		
		return mostOccurance;
	}

	public static List<Integer> findStemIndexes(String source) {
		if (isBlank(source)) {
			return null;
		}

		String[] words = Arrays.stream(source.trim().toLowerCase().split(" "))
				.map(word -> word.replaceAll(Stopwords.invalidChars, "")).filter(word -> !(isBlank(word)))
				.toArray(String[]::new);

		List<Integer> stemIndexes = Arrays.stream(words).map(word -> StemCache.getStemIndex(word))
				.filter(index -> (Collections.binarySearch(Stopwords.stopWordIndexes, index) < 0))
				.collect(Collectors.toList());

		/*
		 * NGramIterator will generate the NGram words (bigram and trigram) and if all
		 * words in ngram are stop words then ignore.
		 */

		List<String> nGrams = getValidNGrams(words, 2);
		nGrams.addAll(getValidNGrams(words, 3));
		nGrams.forEach(word -> stemIndexes.add(StemCache.getStemIndex(word)));
		stemIndexes.sort(null);
		return stemIndexes;

	}

	/**
	 * This will generate the NGram words.
	 * 
	 * @param words
	 * @param n
	 * @return
	 */
	private static List<String> getValidNGrams(String[] words, int n) {
		List<String> nGrams = new ArrayList<>();
		for (int index = 0; index + n <= words.length; index++) {
			String[] nGram = Arrays.copyOfRange(words, index, n + index);
			String word = getValidWord(nGram);
			if (word != null) {
				nGrams.add(word);
			}
		}
		return nGrams;
	}

	private static String getValidWord(String[] words) {

		StringBuilder sb = new StringBuilder();
		boolean stopWord = true;
		for (int index = 0; index < words.length; index++) {
			sb.append((index > 0 ? " " : "") + words[index]);
			int stemIndex = StemCache.getStemIndex(words[index]);
			stopWord = stopWord && Collections.binarySearch(Stopwords.stopWordIndexes, stemIndex) >= 0;
		}

		if (stopWord) {
			return null;
		}
		return sb.toString();
	}

	private static boolean isBlank(String text) {
		int strLen;
		if (text == null || (strLen = text.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(text.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String source = "Zebras are several species of African equids (horse family) united by their distinctive black and white stripes. Their stripes come in different patterns, unique to each individual. They are generally social animals that live in small harems to large herds. Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. There are three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra. The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus. The latter resembles an ass, to which it is closely related, while the former two are more horse-like. All three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them one of the animals most familiar to people. They occur in a variety of habitats, such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills. However, various anthropogenic factors have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction. Grévy's zebra and the mountain zebra are endangered. While plains zebras are much more plentiful, one subspecies - the Quagga - became extinct in the late 19th century. Though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the Quagga, in a process called breeding back.";
		System.out.println(NGramStemmer.findStemIndexes(source));
	}

}

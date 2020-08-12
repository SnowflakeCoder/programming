package com.snowflake.hackerrank.matchQnAns;

import java.util.List;

/**
 * 
 * @author arun.kumar.ms
 *
 */

public class MainApplication {
	

	/**
	 * 
	 * 1. Find wordTokens for each sentences, questions and answers. 
	 * 
	 * 2. Sort the questions in descending order based on wordToken size and Find matching sentence for each question.
	 * 
	 * 3. Sort the matched sentences in descending order based on wordToken size and Find matching answer for each sentence.
	 * 
	 * @param args
	 */
	
	
	
	public static List<String> findMatchingAnswers(String filePath, int numberOfQns) {
		try {
			
			/*
			 * Find wordToken indexes for each sentences, questions and answers.
			 */
			InputVO inputVO = SentenceProcessor.readNProcessInputFile(filePath, numberOfQns);
			
//			System.out.println(inputVO);

			/*
			 * Sort the questions in descending order based on wordToken size and Find matching sentence for each question.
			 * Sort the matched sentences in descending order based on wordToken size and Find matching answer for each sentence.
			 */

			List<String> answers = SentenceProcessor.findMatchingAnswers(inputVO);
			
			return answers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

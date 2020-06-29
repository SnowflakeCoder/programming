package com.snowflake.hackerrank.matchQnAns;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SentenceProcessor {

	public static InputVO readNProcessInputFile(String inputFilePath, int numberOfQuestions) throws Exception {

		File inputFile = new File(inputFilePath);
		if (!inputFile.exists() || !inputFile.isFile() || !inputFile.canRead()) {
			throw new Exception("Something wrong");
		}

		try (BufferedReader bfReader = Files.newBufferedReader(inputFile.toPath())) {
			// read line by line
			String readLine;
			List<String> inputLines = new ArrayList<String>(numberOfQuestions + 2);
			while ((readLine = bfReader.readLine()) != null) {
				inputLines.add(readLine);
			}

			if (inputLines.size() != numberOfQuestions + 2) {
				throw new Exception("Something wrong");
			} else {

				/*
				 * Adding most occured words to stop words.
				 */
				
				List<Integer> maxOccurances = NGramStemmer.findMostOccuring(inputLines.get(0), 5);
				Stopwords.stopWordIndexes.addAll(maxOccurances);
				
				String[] paragraphLines = inputLines.get(0).split("\\.");
				InputVO inputVO = new InputVO(paragraphLines.length, numberOfQuestions);

				int index = 0;
				for (String paragraphLine : paragraphLines) {
					inputVO.addParagraphLine(paragraphLine, NGramStemmer.findStemIndexes(paragraphLine), index++);
				}

				int questionStartIndex = 1;
				for (index = 0; index < numberOfQuestions; index++) {
					String input = inputLines.get(questionStartIndex + index);
					inputVO.addQuestion(input, NGramStemmer.findStemIndexes(input), index);
				}

				index = 0;
				for (String answer : inputLines.get(numberOfQuestions + 1).split(";")) {
					inputVO.addAnswer(answer, NGramStemmer.findStemIndexes(answer), index++);
				}

				return inputVO;
			}

		} catch (IOException e) {
			throw new Exception(e);
		}

	}

	public static List<String> findMatchingAnswers(InputVO inputVO) {

		Map<InputStemMappingVo, InputStemMappingVo> questionSentenceMap = findMatchingVoMap(inputVO.getQuestions(), inputVO.getParagraphLines());
		Map<InputStemMappingVo, InputStemMappingVo> sentenceAnswerMap = findMatchingVoMap(new ArrayList<>(questionSentenceMap.values()), inputVO.getAnswers());

		System.out.println("questionSentenceMap :: " + questionSentenceMap);
		System.out.println("sentenceAnswerMap :: " + sentenceAnswerMap);
		
		List<String> answers = new ArrayList<>(inputVO.getQuestions().size());
		for(InputStemMappingVo question : inputVO.getQuestions()) {
			InputStemMappingVo answer = sentenceAnswerMap.get(questionSentenceMap.get(question));
			if(answer != null) {
				answers.add(answer.getInput());
			}
		}
		
		return answers;
	}

	private static Map<InputStemMappingVo, InputStemMappingVo> findMatchingVoMap(List<InputStemMappingVo> inputList, List<InputStemMappingVo> matchingSentences) {
		Map<InputStemMappingVo, InputStemMappingVo> questionSentenceMap = new HashMap<>(inputList.size());
		List<MatchingScoreVO> matchingScoreList = new ArrayList<>();
		
		for (InputStemMappingVo inputVo : inputList) {
			for (InputStemMappingVo matchedVo : matchingSentences) {
				long score = inputVo.getStemIndexes().stream()
						.filter(stemIndex -> (Collections.binarySearch(matchedVo.getStemIndexes(), stemIndex) >= 0))
						.count();
				if(score != 0) {
					matchingScoreList.add(new MatchingScoreVO(inputVo, matchedVo, score));
				}
			}
		}
		
		matchingScoreList.sort(null);

		for(MatchingScoreVO scoreVO : matchingScoreList) {
			if(questionSentenceMap.containsKey(scoreVO.key) || questionSentenceMap.containsValue(scoreVO.value)) {
				continue;
			}
			questionSentenceMap.put(scoreVO.key, scoreVO.value);
		}
		
		return questionSentenceMap;
	}

}

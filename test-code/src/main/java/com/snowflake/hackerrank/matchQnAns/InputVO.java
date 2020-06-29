package com.snowflake.hackerrank.matchQnAns;

import java.util.ArrayList;
import java.util.List;

/**
 * VO for input file.
 * 
 * @author arun.kumar.ms
 *
 */

public class InputVO {
	
	private List<InputStemMappingVo> paragraphLines;
	private List<InputStemMappingVo> questions;
	private List<InputStemMappingVo> answers;
	
	public InputVO(int numberOfparagraphLines, int numberOfQuestions) {
		paragraphLines = new ArrayList<>(numberOfparagraphLines);
		questions = new ArrayList<>(numberOfQuestions);
		answers = new ArrayList<>(numberOfQuestions);
	}

	public List<InputStemMappingVo> getParagraphLines() {
		return paragraphLines;
	}
	
	public void setParagraphLines(List<InputStemMappingVo> paragraphLines) {
		this.paragraphLines = paragraphLines;
	}
	
	public void addParagraphLine(String paragraphLine, List<Integer> stemWords, int questionIndex) {
		this.paragraphLines.add(new InputStemMappingVo(paragraphLine, stemWords, questionIndex));
	}

	public List<InputStemMappingVo> getQuestions() {
		return questions;
	}

	public void setQuestions(List<InputStemMappingVo> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(String question, List<Integer> stemWords, int questionIndex) {
		this.questions.add(new InputStemMappingVo(question, stemWords, questionIndex));
	}

	public List<InputStemMappingVo> getAnswers() {
		return answers;
	}

	public void setAnswers(List<InputStemMappingVo> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(String answer, List<Integer> stemWords, int questionIndex) {
		this.answers.add(new InputStemMappingVo(answer, stemWords, questionIndex));
	}

	@Override
	public String toString() {
		return "InputVO [paragraphLines=" + paragraphLines + ", questions=" + questions + ", answers=" + answers + "]";
	}

	

}

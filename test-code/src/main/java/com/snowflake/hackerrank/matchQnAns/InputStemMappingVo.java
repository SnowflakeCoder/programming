package com.snowflake.hackerrank.matchQnAns;

import java.util.List;

/**
 * 
 * This class contains the input string and corresponding stem words.
 * 
 * @author arun.kumar.ms
 *
 */

public class InputStemMappingVo {

	private String input;
	private List<Integer> stemIndexes;
	private int inputIndex;

	public InputStemMappingVo(String input, List<Integer> stemIndexes, int inputIndex) {
		super();
		this.input = input;
		this.stemIndexes = stemIndexes;
		this.inputIndex = inputIndex;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public List<Integer> getStemIndexes() {
		return stemIndexes;
	}

	public void setStemIndexes(List<Integer> stemIndexes) {
		this.stemIndexes = stemIndexes;
	}

	public int getInputIndex() {
		return inputIndex;
	}

	public void setInputIndex(int inputIndex) {
		this.inputIndex = inputIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inputIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputStemMappingVo other = (InputStemMappingVo) obj;
		if (inputIndex != other.inputIndex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InputStemMappingVo [input=" + input + ", stemIndexes=" + stemIndexes + ", inputIndex=" + inputIndex
				+ "]";
	}

}

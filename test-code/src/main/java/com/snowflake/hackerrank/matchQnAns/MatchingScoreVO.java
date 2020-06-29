package com.snowflake.hackerrank.matchQnAns;

public class MatchingScoreVO implements Comparable<MatchingScoreVO> {
	
	InputStemMappingVo key;
	InputStemMappingVo value;
	long score;
	
	public MatchingScoreVO(InputStemMappingVo key, InputStemMappingVo value, long score) {
		super();
		this.key = key;
		this.value = value;
		this.score = score;
	}

	public InputStemMappingVo getKey() {
		return key;
	}

	public void setKey(InputStemMappingVo key) {
		this.key = key;
	}

	public InputStemMappingVo getValue() {
		return value;
	}

	public void setValue(InputStemMappingVo value) {
		this.value = value;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(MatchingScoreVO o) {
		return Long.compare(o.score, this.score);
	}
	
}

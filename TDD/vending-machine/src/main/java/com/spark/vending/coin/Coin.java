package com.spark.vending.coin;

public enum Coin {

	Spark_1(1), Spark_5(5), Spark_10(10), Spark_25(25);

	
	private int value;
	
	private Coin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}

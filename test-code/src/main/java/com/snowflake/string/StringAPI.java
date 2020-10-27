package com.snowflake.string;

public class StringAPI {

	public static void main(String[] args) {
		JoinTest("arun:kumar");
		JoinTest("arun");
		JoinTest("arun::kumar");
	}
	
	private static void JoinTest(String input) {
		System.out.println(String.join("_", input.split(":")));
	}

}

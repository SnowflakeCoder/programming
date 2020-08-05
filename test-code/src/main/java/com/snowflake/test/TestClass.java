package com.snowflake.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestClass {

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("F:\\\\Arun\\\\SnowflakeCoder\\\\programming\\\\General\\\\test.srt"));
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=3 ; i < lines.size(); i=i+4) {
			sb.append(lines.get(i)).append(" ");
		}
		
		System.out.println(sb);
		
	}

}

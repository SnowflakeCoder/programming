package com.snowflake.hackerrank.matchQnAns;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AppJunitTest {

	@Test
	public void test() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\inputFile.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[Grévy's zebra and the mountain zebra, aims to breed zebras that are phenotypically similar to the quagga, horses and donkeys, the plains zebra, the Grévy's zebra and the mountain zebra, subgenus Hippotigris]", answers.toString());
	}

	@Test
	public void test1() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\input\\input00.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[Grevy's zebra and the mountain zebra, aims to breed zebras that are phenotypically similar to the quagga, horses and donkeys, the plains zebra, the Grevy's zebra and the mountain zebra, subgenus Hippotigris]", answers.toString());
	}

	@Test
	public void test2() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\input\\input01.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[after the Roman withdrawal from Britain in the 5th century, the growth of socialism and the Labour Party, Two-thirds, the public sector, light and service industries and tourism, 1925]", answers.toString());
	}

	@Test
	public void test3() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\input\\input02.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[Jodhpur, for the bright, sunny weather it enjoys all the year round, due to the vivid blue-painted houses around the Mehrangarh Fort, Abhiras (Ahirs), 5 November 1556]", answers.toString());
	}

	@Test
	public void test4() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\input\\input03.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[1917, the danger of nuclear weapons, more than 300 scientific papers, Institute for Advanced Study in Princeton, principle of relativity could also be extended to gravitational fields]", answers.toString());
	}

	@Test
	public void test5() {
		List<String> answers = MainApplication.findMatchingAnswers("E:\\temp\\sahaj\\input\\input04.txt", 5);
		System.out.println(answers.toString());
		assertEquals("[Coraciiformes, Several fossil birds, Common Kingfisher, by swooping down from a perch, Alcedines]", answers.toString());
	}

}
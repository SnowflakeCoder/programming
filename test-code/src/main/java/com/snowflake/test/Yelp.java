package com.snowflake.test;

public class Yelp {

	public static void main(String[] args) {
		Yelp p = new Yelp();
		System.out.println(p.getClass().getPackage());
		System.out.println(p.getClass().getTypeName());
		System.out.println(p.getClass().getCanonicalName());
		
		System.out.println(int.class.getName());
		System.out.println(int.class.getTypeName());
		System.out.println(int.class.getCanonicalName());
	}
	
	

}

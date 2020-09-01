package com.snowflake.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Yelp2 {

	public static void main(String[] args) {
		List<Business> businesses = new ArrayList<>();
		businesses.add(new Business("A", "N", 100));
		businesses.add(new Business("A", "N", 200));
		businesses.add(new Business("A", "N", 300));
		businesses.add(new Business("A", "M", 100));
		businesses.add(new Business("A", "M", 100));
		businesses.add(new Business("B", "X", 100));
		
		final String givenLoc = "A";
		
		Map<String, Long> map = businesses.stream().filter(loc -> loc.getLocation().equals(givenLoc))
				.collect(Collectors.groupingBy(Business :: getName, Collectors.counting()));
		
		System.out.println(map.toString());
		
		Map<String, Long> map1 = businesses.stream().filter(loc -> loc.getLocation().equals(givenLoc))
				.collect(Collectors.groupingBy(b -> b.name, Collectors.counting()));

		System.out.println(map1.toString());
		
//		Map<String, Long> map2 = businesses.stream().filter(loc -> loc.getLocation().equals(givenLoc))
//				.collect(Collectors.groupingBy(b -> b.name, new Count(Collectors.counting())));
//
//		System.out.println(map2.toString());
		
	}
	
	private static class Count {
		private String name;
		private int count;
		
		public Count(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}
		
		public Count(int count) {
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
	}
	
	private static class Business {
		private String location;
		private String name;
		private int code;
		
		public Business(String location, String name, int code) {
			super();
			this.location = location;
			this.name = name;
			this.code = code;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
		
	}

}

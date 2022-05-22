package com.snowflake.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class Zalando3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 Map<Long, Long> count(Map<String, UserStats>... visits) {
	        
	        Map<Long, Long> result = new HashMap<>();
	        if(visits == null || visits.length == 0){
	            return result;
	        }
	        
	        Consumer<String, UserStats> printConsumer = new Consumer<String, UserStats>() {
	            public void accept(String name, UserStats... visits) {
	                System.out.println(name);
	            };
	        };
	        
	        Arrays.stream(visits).filter(value -> value != null).forEach();
	        
	        for (Map<String, UserStats> visit : visits) {
	            if(visit == null){
	                continue;
	            }
	            Set<Map.Entry<String, UserStats>> entries = visit.entrySet();
	            for (Map.Entry<String, UserStats> entry : entries) {

	                UserStats value = entry.getValue();
	                String key = entry.getKey();
	                Long userId;
	                try {
	                    userId = Long.parseLong(key);
	                } catch (NumberFormatException nfe) {
	                    continue;
	                }
	                if (value == null || !value.getVisitCount().isPresent()) {
	                    continue;
	                }
	                Long aLong = result.get(userId);
	                if (aLong != null) {
	                    result.put(userId, aLong+value.getVisitCount().get());
	                } else {
	                    result.put(userId, value.getVisitCount().get());
	                }
	            }
	        }
	        return result;
	    }
	 
	 private static class UserStats {
		 Optional<Long> visitCount;
		 
		 public Optional<Long> getVisitCount() {
			return visitCount;
		}
	 }

}

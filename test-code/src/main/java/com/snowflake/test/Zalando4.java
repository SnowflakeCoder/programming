package com.snowflake.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Zalando4 {

	public static void main(String[] args) {

	}
	
    Map<Long, Long> count(Map<String, UserStats>... visits) {
        
        Map<Long, Long> result = new HashMap<>();
        if(visits == null || visits.length == 0){
            return result;
        }
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
    
    public static Map<Long, Long> count1(Map<String, UserStats>... visits) {

        if (visits == null) {
            return new HashMap<>();
        }

        Stream<UserStats> statsStream = Arrays.stream(visits)
                .filter(Objects::nonNull)
                .flatMap(m -> m.entrySet().stream())
                .map(e -> {

                    Long userId = -1L;
                    try {
                        userId = Long.parseLong(e.getKey());
                    } catch (NumberFormatException a) {
                    }
                    Long visitCount = -1L;
                    if (e.getValue() != null && e.getValue().getVisitCount().isPresent()) {
                        visitCount = e.getValue().getVisitCount().get();
                    }
                    return new UserStats(userId, visitCount);

                })
                .filter(s -> {
                    return -1L != s.getUserId() && -1L != s.getVisitCount();
                });
        return statsStream.collect(groupingBy(UserStats::getUserId, summingLong(UserStats::getVisitCount)));
    }
    
    private static class UserStats {
		 Optional<Long> visitCount;
		 
		 public Optional<Long> getVisitCount() {
			return visitCount;
		}
	 }



}

package com.snowflake.leetcode.subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindMinNumberOfRooms {

	public static void main(String[] args) {
		
		System.out.println("Ans ::" + minRooms(new int[][] {{1000, 1030}, {1030, 1100},{1045, 1200}, {1200, 1300}}));
		System.out.println("Ans ::" + minRooms(new int[][] {{1000, 1030}, {1030, 1100},{1045, 1200}, {1200, 1300}, {1130, 1230}}));
		System.out.println("Ans ::" + minRooms(new int[][] {{1000, 1030}, {1030, 1100},{1045, 1200}, {1200, 1300}, {1130, 1230}, {1000, 1300}, {1020,1110}}));
	}

	
	private static int minRooms(int[][] meetingArr) {
		
		List<MeetingTime> meetingTimings = new ArrayList<>();
		
		for(int i=0; i<meetingArr.length; i++ ) {
			meetingTimings.add(new MeetingTime(true, meetingArr[i][0]));
			meetingTimings.add(new MeetingTime(false, meetingArr[i][1]));
		}

		
		Collections.sort(meetingTimings, new Comparator<MeetingTime>() {
			@Override
			public int compare(MeetingTime o1, MeetingTime o2) {
				return Integer.compare(o1.time, o2.time);
			}
		});
		
		
		int minRommSize =0, currentMinRoomSize =0;
		for(int timeIndex = 0; timeIndex < meetingTimings.size(); timeIndex++) {
			MeetingTime meetingTime = meetingTimings.get(timeIndex);
			
			if(meetingTime.startTime) {
				minRommSize++;
			}
			else {
				if(currentMinRoomSize < minRommSize) {
					currentMinRoomSize = minRommSize;
				}
				minRommSize--;
			}
		}
		
		
		return currentMinRoomSize;
	}
	
	private static class MeetingTime{
		boolean startTime=false;
		int time;
		
		public MeetingTime(boolean startTime, int time) {
			super();
			this.startTime = startTime;
			this.time = time;
		}

		@Override
		public String toString() {
			return "MeetingTime [startTime=" + startTime + ", time=" + time + "]";
		}
		
		
	}

}

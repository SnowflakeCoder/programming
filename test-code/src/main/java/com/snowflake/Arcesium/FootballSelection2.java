package com.snowflake.Arcesium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FootballSelection2 {

	public static void main(String[] args) {
		

	}
	


    /*
     * Complete the 'getSelectionStatus' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts 2D_STRING_ARRAY applications as parameter.
     * 
     */

    private static final float MIN_HEIGHT = 5.8f;
    private static final int MAX_BMI = 23; 
    private static final int MIN_GOALS_SCORED = 50;
    private static final int MIN_GOALS_DEFENDED = 30;
    
    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {
        if(applications == null || applications.isEmpty()){
            return Collections.emptyList();
        }
        List<List<String>> players = new ArrayList<>();
        int noOfStrikers = 0;
        int noOfDefenders = 0;
        List<Integer> common = new ArrayList<>();
        List<String> playerStatus = new ArrayList<>();
        int index = 0;
        for(List<String> line : applications){
               index++;
               String name = line.get(0);
               float height = Float.parseFloat(line.get(1));
               int bmi = Integer.parseInt(line.get(2));
               int goalsScored = Integer.parseInt(line.get(3));
               int goalsDefended = Integer.parseInt(line.get(4)); 

                if(height < MIN_HEIGHT && bmi < MAX_BMI ){
                    players.add(Arrays.asList(name,"REJECT","NA"));
                    playerStatus.add("REJECT");
                    continue;
                }

                if(goalsScored >= MIN_GOALS_SCORED){
                    if(goalsDefended >= MIN_GOALS_DEFENDED){
                        common.add(index - 1);
                        players.add(Arrays.asList(name,"SELECT","BOTH"));
                        playerStatus.add("BOTH");
                        continue;
                    }
                    players.add(Arrays.asList(name,"SELECT","STRIKER"));
                    playerStatus.add("STRIKER");
                    noOfStrikers++;
                    continue;
                }

                if(goalsDefended >= MIN_GOALS_DEFENDED){
                    players.add(Arrays.asList(name,"SELECT","DEFENDER"));
                    playerStatus.add("DEFENDER");
                    noOfDefenders++;
                    continue;
                }
               players.add(Arrays.asList(name,"REJECT","NA"));
               playerStatus.add("REJECT");
        }
        if(noOfStrikers < noOfDefenders){
            for(int i = 0 ; i < common.size() && noOfStrikers < noOfDefenders; i++){
                players.get(i).set(2, "STRIKER");
                playerStatus.add("STRIKER");
                common.set(i, -1);
                noOfStrikers++;
            }
        }

        if(noOfStrikers > noOfDefenders){
            for(int i = 0 ; i < common.size() && noOfStrikers > noOfDefenders; i++){
                
                players.get(i).set(2, "DEFENDER");
                playerStatus.add("DEFENDER");
                noOfDefenders++;
            }
           
        }
        
        for(int i = 0 ; i < common.size(); ){
                if(common.get(i) == -1){
                    continue;
                }
                if(i + 1 < common.size()){
                   players.get(i).set(2, "DEFENDER");
                   players.get(i + 1).set(2, "STRIKER"); 
                   playerStatus.add(i,"DEFENDER");
                   playerStatus.add(i + 1,"STRIKER");
                   i+=2;
                   continue;    
                }
                players.get(i).set(2, "NA");
                players.get(i).set(1, "REJECT");
                i++;
            }
         if(noOfStrikers > noOfDefenders){
           
            for(int i = playerStatus.size() - 1; i >= 0 && noOfStrikers > noOfDefenders; i--){
                if(playerStatus.get(i).equals("STRIKER")){
                    players.get(i).set(2, "NA");
                    players.get(i).set(1, "REJECT");
                    noOfStrikers--;
                }
            }
            
        }else if(noOfStrikers < noOfDefenders){
           
            for(int i = playerStatus.size() - 1; i >= 0 && noOfStrikers < noOfDefenders; i--){
                if(playerStatus.get(i).equals("DEFENDER")){
                    players.get(i).set(2, "NA");
                    players.get(i).set(1, "REJECT");
                    noOfDefenders--;
                }
            }
            
        }
        return players;
    }

	

}

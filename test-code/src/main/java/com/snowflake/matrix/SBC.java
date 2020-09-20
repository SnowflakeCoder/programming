package com.snowflake.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SBC {

	public static void main(String[] args) {
		
		

	}
	
	
	private static class Player{
		private int id;
		private String name;
		private PlayerType playerType;
		private long cost;
	}
	
	private static interface Constraint {
		public boolean isValid(Player player, Team team);
	}
	
	private static class Team {
		private int id;
		private String name;
		private Map<Integer, Player> players;
		private long vallet;
		private List<Constraint> constraints;
		
		public Team(List<Constraint> constraints, String name, long vallet) {
			this();
			this.constraints = constraints;
			this.name = name;
			this.vallet = vallet;
		}
		
		public Team() {
			constraints = new ArrayList<>();
			players = new HashMap<>(11);
		}
		
		public boolean addPlayer(Player player) {
			if(players.size() == 11 || vallet < player.cost) {
				return false;
			}
			
			for(Constraint constraint : constraints) {
				if(!constraint.isValid(player, this)) {
					return false;
				}
			}
			
			players.put(player.id, player);
			vallet = vallet - player.cost;
			return true;
		}
		
		public boolean replacePlayer(Player newPlayer, Player oldPlayer) {
			for(Constraint constraint : constraints) {
				if(!constraint.isValid(newPlayer, this)) {
					return false;
				}
			}
			
			if(vallet < (newPlayer.cost - oldPlayer.cost)) {
				return false;
			}
			
			players.remove(oldPlayer.id);
			players.put(newPlayer.id, newPlayer);
			vallet = vallet - (newPlayer.cost - oldPlayer.cost);
			return true;
		}
		
	}
	
	
	enum PlayerType{
		Striker, Defender, GoalKeeper
	}
	
	

}

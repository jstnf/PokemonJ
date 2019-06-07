package io.pokemonj2.curtis;

import java.util.ArrayList;
import java.util.Map;

public class Pokemon {
	//sprites?
	
	private String name;
	private int type1;
	private int type2; 
//	private Map<Integer, Move> moveset;
	private int level;
	private int[] baseStats;
	private int[] currStats;//hp is the max HP
	private Move[] currMoves;
	private int currHP;
	private Status currStatus;
	private int[] statChanges;
	boolean isFainted;
	public Pokemon(String name, int type1, int type2, 
//			Map<Integer, Move> moveset, 
			int[] baseStats, Move[] currMoves, int level)
	{
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
//		this.moveset = moveset;
		this.level = level;
		
		this.baseStats = baseStats;
		currStats = new int[6];
		currStats[0] = baseStats[0] / 50 + level + 10;
		for(int i = 1; i < 6; i++)
			currStats[i] = baseStats[i] / 50 + 5;
		
		this.currMoves = currMoves;
		currHP = currStats[0];
		currStatus = null;
		statChanges = new int[8]; //Atk, Def, SpAtk, SpDef, Speed, Accuracy, Evasiveness, Crit
		for(int i = 0; i < 7; i++)
			statChanges[i] = 1;
		isFainted = false;		
	}
	public String getName() {
		return name;
	}

	public int getType1() {
		return type1;
	}
	
	public int getType2() {
		return type2;
	}

//	public Map<Integer, Move> getMoveset() {
//		return moveset;
//	}

	public int[] getCurrStats() {
		return currStats;
	}
	
	public Move[] getCurrMoves() {
		return currMoves;
	}
	public void setCurrMoves(Move[] currMoves) {
		this.currMoves = currMoves;
	}
	
	public int getCurrHP() {
		return currHP;
	}
	public void setCurrHP(int currHP) {
		this.currHP = currHP;
		if(currHP <= 0)
			isFainted = true;
	}
	
	public Status getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(Status currStatus) {
		this.currStatus = currStatus;
	}
	
	public int[] getStatChanges() {
		return statChanges;
	}
	public void setStatChanges(int[] statChanges) {
		this.statChanges = statChanges;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isFainted() {
		return isFainted;
	}
	public void setFainted(boolean isFainted) {
		this.isFainted = isFainted;
	}
	
	
}

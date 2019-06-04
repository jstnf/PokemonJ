package io.pokemonj2.curtis;

import java.util.ArrayList;
import java.util.Map;

public class Pokemon {
	//sprites?
	
	private String name;
	private int type1;
	private int type2; 
	private Map<Integer, Move> moveset; 
	private int[] baseStats;
	private Move[] currMoves;
	private int currHP;
	private Status currStatus;
	private int[] statChanges;
	private int level;
	boolean isFainted;
	public Pokemon(String name, int type1, int type2, Map<Integer, Move> moveset, 
			int[] baseStats, Move[] currMoves, int maxHP, Status currStatus, int level)
	{
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.moveset = moveset;
		this.baseStats = baseStats;
		this.currMoves = currMoves;
		currHP = maxHP;
		this.currStatus = currStatus;
		statChanges = new int[8]; //start at 0
		this.level = level;
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

	public Map<Integer, Move> getMoveset() {
		return moveset;
	}

	public int[] getBaseStats() {
		return baseStats;
	}
	public void setBaseStats(int[] baseStats) {
		this.baseStats = baseStats;
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

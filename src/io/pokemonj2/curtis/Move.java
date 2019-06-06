package io.pokemonj2.curtis;

public class Move {
	
	private int moveNum; //index in array
	private String moveName;
	private int type;
	private int damage;
	private double accuracy; //2 is never miss
	private int currPP;
	private int maxPP;
	private int category; //0 for physical, 1 for special, 2 for status
	private int secondaryEffect; //add later
	public Move(int moveNum, String moveName, int type, int damage, double accuracy, int maxPP, int category, int secondaryEffect)
	{
		this.moveNum = moveNum;
		this.moveName = moveName;
		this.type = type;
		this.damage = damage;
		this.accuracy = accuracy;
		currPP = maxPP;
		this.maxPP = maxPP;
		this.category = category;
		this.secondaryEffect = secondaryEffect;
	}
	
	public int getMoveNum() {
		return moveNum;
	}

	public int getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public int getCurrPP() {
		return currPP;
	}
	public void decreasePP() {
		currPP = currPP - 1;
	}
	
	public int getMaxPP() {
		return maxPP;
	}
	public void increaseMaxPP() {
		maxPP = maxPP + 1;
	}
	
	public int getCategory() {
		return category;
	}

	public int getSecondaryEffect() {
		return secondaryEffect;
	}
}

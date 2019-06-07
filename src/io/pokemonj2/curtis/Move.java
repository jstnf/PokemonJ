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
/*	
	0 - none
	1 - para
	2 - poison
	3 - sleep
	4 - raise atk 1
	5 - raise atk 2
	6 - lower atk 1
	7 - raise atk and satk 1
	8 - raise def 1
	9 - raise def 2
	10 - lower def 1
	11 - lower def 2
	12 - raise sdef 2
	13 - lower speed 1
	14 - raise speed 2
	15 - lower accuracy 1
	16 - raise evasiveness 1
	17 - raise evasiveness 2
	18 - raise crit chance
	19 - remove stat changes
	20 - heal 50%	
*/	
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
	
	public String getMoveName() {
		return moveName;
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

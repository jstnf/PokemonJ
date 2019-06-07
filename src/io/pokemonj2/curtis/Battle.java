package io.pokemonj2.curtis;
//add accuracy, evasion, crit hit, PP
import java.io.File;
import java.util.Scanner;

public class Battle {
	
	private Pokemon myPKMN;
	private Pokemon oppPKMN;
	private double[][] typeChart;
	private Move[] moveDatabase;//size 125
	
	public Battle()
	{
		initializeMoves();
		initializeTypeChart();
		initializePKMN(0);
		initializePKMN(1);
	}
	
	public void runTurn()
	{
		while(!myPKMN.isFainted && !oppPKMN.isFainted)
		{
			System.out.println("Moves: ");
			Move[] moves = myPKMN.getCurrMoves();
			for(int i = 0; i < 4; i++)
				if(moves[i].getCurrPP() != 0)
					System.out.println(i + ") " + moves[i].getMoveName() + " PP: " + moves[i].getCurrPP());
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("USE: ");
			int input = scanner.nextInt(); //GUI will stop errors
			
			int oppMoveNum = (int)(Math.random() * 4);
			
			int myPKMNSpe = myPKMN.getCurrStats()[5];
			int oppPKMNSpe = oppPKMN.getCurrStats()[5];
			if(myPKMN.getCurrStatus() == Status.PARALYSIS)
				myPKMNSpe = myPKMNSpe / 2;
			if(oppPKMN.getCurrStatus() == Status.PARALYSIS)
				oppPKMNSpe = oppPKMNSpe / 2;

			double aSpeedMultiplier = calculateMultiplier(myPKMN.getStatChanges()[5]);
			myPKMNSpe = (int)(myPKMNSpe * aSpeedMultiplier);
			double dSpeedMultiplier = calculateMultiplier(myPKMN.getStatChanges()[5]);
			oppPKMNSpe = (int)(oppPKMNSpe * dSpeedMultiplier);
				
			if(myPKMNSpe >= oppPKMNSpe)//you always win speed tie
			{
				runMove(myPKMN, oppPKMN, moveDatabase[moves[input].getMoveNum()]);
				if(!oppPKMN.isFainted)
					runMove(oppPKMN, myPKMN, moveDatabase[oppPKMN.getCurrMoves()[oppMoveNum].getMoveNum()]);
			}
			else
			{
				runMove(oppPKMN, myPKMN, moveDatabase[moves[oppMoveNum].getMoveNum()]);
				if(!myPKMN.isFainted)
					runMove(myPKMN, oppPKMN, moveDatabase[oppPKMN.getCurrMoves()[input].getMoveNum()]);
			}
			
			runStatus(myPKMN);
			runStatus(oppPKMN);
			
			System.out.println("MY: " + myPKMN.getCurrHP());
			System.out.println("OPP: " + oppPKMN.getCurrHP());
		}
		
		if(myPKMN.isFainted)
			System.out.print("YOU LOST");
		else
			System.out.print("YOU WON!");
	}
	
	public Pokemon getMine()
	{
		return myPKMN;
	}
	
	public Pokemon getOpp()
	{
		return oppPKMN;
	}
	
	private void initializeMoves()
	{
		moveDatabase = new Move[125];
		try
		{
			Scanner scnr = new Scanner(new File("res\\data\\Moves.csv"));
			while(scnr.hasNextLine())
			{
				String moveLine = scnr.nextLine();
				int moveNum = Integer.parseInt(getMoveSubstring(moveLine));
				moveLine = removeToComma(moveLine);
				String moveName = getMoveSubstring(moveLine);
				moveLine = removeToComma(moveLine);
				
				String moveType = getMoveSubstring(moveLine);
				int moveTypeNum = getType(moveType);
				moveLine = removeToComma(moveLine);
				
				int movePower = 0;
				try {
				movePower = Integer.parseInt(getMoveSubstring(moveLine));
				}
				catch(Exception e)
				{ 				}
				moveLine = removeToComma(moveLine);
				
				double moveAcc = 2;
				try {
					moveAcc = Double.parseDouble(getMoveSubstring(moveLine));
					}
					catch(Exception e)
					{ 					}
				moveLine = removeToComma(moveLine);
				
				int movePP = Integer.parseInt(getMoveSubstring(moveLine));
				moveLine = removeToComma(moveLine);
				
				String moveTypeString = getMoveSubstring(moveLine);				
				int moveCategory;
				if(moveTypeString.equals("Physical"))
					moveCategory = 0;
				else if(moveTypeString.equals("Special"))
					moveCategory = 1;
				else
					moveCategory = 2;
				moveLine = removeToComma(moveLine);
				
				int moveSecondary = Integer.parseInt(moveLine);
				
				moveDatabase[moveNum - 1] = new Move(moveNum - 1, moveName, moveTypeNum, movePower, moveAcc,
						movePP, moveCategory, moveSecondary);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void initializeTypeChart()
	{
		typeChart = new double[20][20];
		try
		{
			Scanner scnr = new Scanner(new File("res\\data\\Types Matrix.csv"));
			for(int i = 0; i < typeChart.length; i++)
			{
				String line = scnr.nextLine();
				for(int j = 0; j < typeChart[0].length; j++)
				{
					if(j != typeChart[0].length - 1)
					{
						int index = line.indexOf(",");
						typeChart[i][j] = Double.parseDouble(line.substring(0, index));
						line = line.substring(index + 1);
					}
					else
						typeChart[i][j] = Double.parseDouble(line);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String getMoveSubstring(String moveLine)
	{
		return moveLine.substring(0, moveLine.indexOf(","));
	}
	
	private String removeToComma(String moveLine)
	{
		return moveLine.substring(moveLine.indexOf(",") + 1);
	}
	
	private int getType(String moveType)
	{
		if(moveType.equals("0"))
			return 0;
		else if(moveType.equals("1"))
			return 1;
		if(moveType.equals("Normal"))
			return 2;
		else if(moveType.equals("Fighting"))
			return 3;
		else if(moveType.equals("Flying"))
			return 4;
		else if(moveType.equals("Poison"))
			return 5;
		else if(moveType.equals("Ground"))
			return 6;
		else if(moveType.equals("Rock"))
			return 7;
		else if(moveType.equals("Bug"))
			return 8;
		else if(moveType.equals("Ghost"))
			return 9;
		else if(moveType.equals("Steel"))
			return 10;
		else if(moveType.equals("Fire"))
			return 11;
		else if(moveType.equals("Water"))
			return 12;
		else if(moveType.equals("Grass"))
			return 13;
		else if(moveType.equals("Electric"))
			return 14;
		else if(moveType.equals("Psychic"))
			return 15;
		else if(moveType.equals("Ice"))
			return 16;
		else if(moveType.equals("Dragon"))
			return 17;
		else if(moveType.equals("Dark"))
			return 18;
		else
			return 19;//Fairy
	}
	
	private void initializePKMN(int whichOne)
	{
		
		try
		{
			int num = (int)(Math.random() * 151) + 1;
			String number = Integer.toString(num);
			while(number.length() != 3)
				number = "0" + number;
			Scanner scnr = new Scanner(new File("res\\data\\pokemon\\" + number + ".txt"));
			scnr.nextLine();
			String name = scnr.nextLine();
			int type1 = scnr.nextInt();
			int type2 = scnr.nextInt();
			int[] stats = {scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt()};
			Move[] moves = {moveDatabase[(int)(Math.random() * 125)], moveDatabase[(int)(Math.random() * 125)], moveDatabase[(int)(Math.random() * 125)], moveDatabase[(int)(Math.random() * 125)]};
			
			if(whichOne == 0)
			{
				myPKMN = new Pokemon(name, type1, type2, stats, moves, 50);
				System.out.println("My PKMN is " + name);
			}
			else
			{
				oppPKMN = new Pokemon(name, type1, type2, stats, moves, 50);
				System.out.println("Their PKMN is " + name);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private int damageCalc(Pokemon attack, Pokemon defense, Move move)
	{
		if(move.getCategory() == 2)//status
		{
			return 0;
		}
		else
		{
			double damage;
			if(move.getCategory() == 0)//physical
			{
				double attackMultiplier = calculateMultiplier(attack.getStatChanges()[1]);
				double defenseMultiplier = calculateMultiplier(defense.getStatChanges()[2]);
				damage = (((2 * attack.getLevel() / 5) + 2) * move.getDamage() * attack.getCurrStats()[1] * attackMultiplier / (defense.getCurrStats()[2] * defenseMultiplier))/50 + 2;				
			}
			else//special
			{
				double attackMultiplier = calculateMultiplier(attack.getStatChanges()[3]);
				double defenseMultiplier = calculateMultiplier(defense.getStatChanges()[4]);
				damage = (((2 * attack.getLevel() / 5) + 2) * move.getDamage() * attack.getCurrStats()[3] * attackMultiplier / (defense.getCurrStats()[4] * defenseMultiplier))/50 + 2;
			
			}
			double criticalHit = 1;
			if(Math.random() < .0625)
			{
				criticalHit = 1.5;
				System.out.println("A critical hit!");
			}
			
			double random = (int)(Math.random() * 100 + 1) / 100.0;
			
			double stab = 1;
			if(move.getType() == attack.getType1() || move.getType() == attack.getType2())
				stab = 1.5;
			
			double effective = typeChart[move.getType()][defense.getType1()] * typeChart[move.getType()][defense.getType2()];
			if(effective > 1)
				System.out.println("It's super effective!");
			else if(effective < 0)
				System.out.println("But it had no effect...");
			else if(effective < 1)
				System.out.println("But it was not very effective...");
			
			
			double burn = 1;
			if(attack.getCurrStatus() != null && attack.getCurrStatus().equals("BURN"))
				burn = .5;
			
			double modifier = criticalHit * random * stab * effective * burn;
			
			damage = damage * modifier;
			
			return (int)damage;
		}
	}
	
	private void runMove(Pokemon attack, Pokemon defense, Move move)
	{
		boolean canAttack = true;
		if(attack.getCurrStatus() == Status.SLEEP)//33% chance to wake up (not official)
			if(Math.random() * 100 <= 33)
			{
				attack.setCurrStatus(null);
				System.out.println(attack.getName() + " woke up!");
			}
			else
			{
				canAttack = false;
				System.out.println(attack.getName() + " was asleep!");
			}
		else if(attack.getCurrStatus() == Status.FREEZE)
			if(Math.random() * 100 <= 20)
			{
				attack.setCurrStatus(null);
				System.out.println(attack.getName() + " thawed out!");
			}
			else
			{
				canAttack = false;
				System.out.println(attack.getName() + " was frozen solid!");
			}
		else if(attack.getCurrStatus() == Status.PARALYSIS)
			if(Math.random() * 100 <= 25)
			{
				canAttack = false;
				System.out.println(attack.getName() + " was paralyzed!");
			}
		if(canAttack) //canAttack is true to move on
		{
			System.out.println(attack.getName() + " used " + move.getMoveName() + "!");
			move.decreasePP();
			double acc = move.getAccuracy();
			if(acc != 2 && Math.random() > acc)
			{
				canAttack = false;
				System.out.println("But it missed...");
			}
			if(canAttack)
			{
				if(move.getCategory() != 2)
				{
					int damage = damageCalc(myPKMN, oppPKMN, move);
					System.out.println(defense.getName() + " took " + damage + " damage!");
					defense.setCurrHP(defense.getCurrHP() - damage);
				}
				else
				{
					int secondaryEffect = move.getSecondaryEffect();
					if(secondaryEffect < 4 && secondaryEffect > 0)
					{
						if(defense.getCurrStatus() != null)
							System.out.println("But it failed...");
						else
						{
							if(move.getSecondaryEffect() == 1)
							{
								defense.setCurrStatus(Status.PARALYSIS);
								System.out.println(defense.getName() + " was paralyzed!");
							}
							else if(move.getSecondaryEffect() == 2)
							{
								defense.setCurrStatus(Status.POISON);
								System.out.println(defense.getName() + " was poisoned!");
							}
							else if(move.getSecondaryEffect() == 3)
							{
								defense.setCurrStatus(Status.SLEEP);
								System.out.println(defense.getName() + " fell asleep!");
							}
						}
					}
					else if(secondaryEffect == 4)
					{
						increaseStat(attack, 1, 1);
						System.out.println(attack.getName() + "'s attack rose by " + 1 + "!");
					}
					else if(secondaryEffect == 5)
					{
						increaseStat(attack, 1, 2);
						System.out.println(attack.getName() + "'s attack rose by " + 2 + "!");
					}
					else if(secondaryEffect == 6)
					{
						decreaseStat(defense, 1, 1);
						System.out.println(defense.getName() + "'s attack fell by " + 1 + "!");
					}
					else if(secondaryEffect == 7)
					{
						increaseStat(attack, 1, 1);
						increaseStat(attack, 3, 1);
						System.out.println(attack.getName() + "'s attack and special attack rose by " + 1 + "!");
					}
					else if(secondaryEffect == 8)
					{
						increaseStat(attack, 2, 1);
						System.out.println(attack.getName() + "'s defense rose by " + 1 + "!");
					}
					else if(secondaryEffect == 9)
					{
						increaseStat(attack, 2, 2);
						System.out.println(attack.getName() + "'s defense rose by " + 2 + "!");
					}
					else if(secondaryEffect == 10)
					{
						decreaseStat(defense, 2, 1);
						System.out.println(defense.getName() + "'s defense fell by " + 1 + "!");
					}
					else if(secondaryEffect == 11)
					{
						decreaseStat(defense, 2, 2);
						System.out.println(defense.getName() + "'s defense fell by " + 2 + "!");
					}
					else if(secondaryEffect == 12)
					{
						increaseStat(attack, 4, 2);
						System.out.println(attack.getName() + "'s special defense rose by " + 2 + "!");
					}
					else if(secondaryEffect == 13)
					{
						decreaseStat(defense, 5, 1);
						System.out.println(defense.getName() + "'s speed fell by " + 1 + "!");
					}
					else if(secondaryEffect == 14)
					{
						increaseStat(attack, 5, 2);
						System.out.println(attack.getName() + "'s speed rose by " + 2 + "!");
					}
					else if(secondaryEffect == 15)
					{
						decreaseStat(defense, 6, 1);
						System.out.println(defense.getName() + "'s accuracy fell by " + 1 + "!");
					}
					else if(secondaryEffect == 16)
					{
						increaseStat(attack, 7, 1);
						System.out.println(attack.getName() + "'s evasiveness rose by " + 1 + "!");
					}
					else if(secondaryEffect == 17)
					{
						increaseStat(attack, 7, 2);
						System.out.println(attack.getName() + "'s evasiveness rose by " + 2 + "!");
					}
					else if(secondaryEffect == 18)//critical hit
					{
						if(attack.getStatChanges()[8] < 4)
						{
							attack.getStatChanges()[8] = attack.getStatChanges()[8] + 1;
							if(attack.getStatChanges()[8] > 4)
								attack.getStatChanges()[8] = 4;
							System.out.println(attack.getName() + "'s critical hit chance rose by " + 1 + "!");
						}
						else
							System.out.println(attack.getName() + "'s critical hit chance is maxed out!");
					}
					else if(secondaryEffect == 19) //remove stat changes on field
					{
						for(int i = 0; i < 8; i++)
						{
							attack.getStatChanges()[i] = 0;
							defense.getStatChanges()[i] = 0;
						}
						System.out.println("All stat changes were reset.");
					}
					else if(secondaryEffect == 20)
					{
						if(attack.getCurrHP() == attack.getCurrStats()[0])
							System.out.println(attack.getName() + " is already at full health.");
						else
						{
							attack.setCurrHP((int)(attack.getCurrHP() * 1.5));
							System.out.println(attack.getName() + " healed HP!");
						}
					}
				}
			}
		}
	}
	
	private void runStatus(Pokemon pkmn)
	{
		if(pkmn.getCurrStatus() == Status.BURN)
		{
			int damage = pkmn.getCurrStats()[0] / 8;
			System.out.println(pkmn.getName() + " took " + damage + " from the burn!");
			pkmn.setCurrHP(pkmn.getCurrHP() - damage);
		}
		else if(pkmn.getCurrStatus() == Status.POISON)
		{
			int damage = pkmn.getCurrStats()[0] / 8;
			System.out.println(pkmn.getName() + " took " + damage + " from poison!");
			pkmn.setCurrHP(pkmn.getCurrHP() - damage);
		}
	}
	
	private boolean increaseStat(Pokemon attack, int index, int amt)
	{
		if(attack.getStatChanges()[index] < 6)
		{
			attack.getStatChanges()[index] = attack.getStatChanges()[index] + amt;
			if(attack.getStatChanges()[index] > 6)
				attack.getStatChanges()[index] = 6;
			return true;
		}
		else
			return false;
	}
	
	private boolean decreaseStat(Pokemon defense, int index, int amt)
	{
		if(defense.getStatChanges()[index] > -6)
		{
			defense.getStatChanges()[index] = defense.getStatChanges()[index] - amt;
			if(defense.getStatChanges()[index] < -6)
				defense.getStatChanges()[index] = -6;
			return true;
		}
		else
			return false;
	}
	
	private double calculateMultiplier(int statChange)
	{
		if(statChange > 0)
			return (statChange + 2) / 2;
		else if(statChange < 0)
			return 2 / (statChange + 2);
		else
			return 1;
	}
}
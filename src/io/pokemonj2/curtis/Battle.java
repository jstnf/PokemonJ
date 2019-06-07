package io.pokemonj2.curtis;

import java.io.File;
import java.util.Scanner;

public class Battle {
	
	Pokemon myPKMN;
	Pokemon oppPKMN;
	double[][] typeChart;
	Move[] moveDatabase;//size 128
	
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
					System.out.println(i + ") " + moves[i].getMoveName());
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("USE: ");
			int input = scanner.nextInt(); //GUI will stop errors
			
			int oppMoveNum = (int)(Math.random() * 4);
			
			if(myPKMN.getCurrStats()[5] >= oppPKMN.getCurrStats()[5])//you always win speed tie
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
			
			System.out.println("MY: " + myPKMN.getCurrHP());
			System.out.println("OPP: " + oppPKMN.getCurrHP());
		}
		
		if(myPKMN.isFainted)
			System.out.print("YOU LOST");
		else
			System.out.print("YOU WON!");
	}
	
	private void initializeMoves()
	{
		moveDatabase = new Move[128];
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
			Move[] moves = {moveDatabase[(int)(Math.random() * 128)], moveDatabase[(int)(Math.random() * 128)], moveDatabase[(int)(Math.random() * 128)], moveDatabase[(int)(Math.random() * 128)]};
			
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
				damage = (((2 * attack.getLevel() / 5) + 2) * move.getDamage() * attack.getCurrStats()[1] / defense.getCurrStats()[2])/50 + 2;				
			else//special
				damage = (((2 * attack.getLevel() / 5) + 2) * move.getDamage() * attack.getCurrStats()[3] / defense.getCurrStats()[4])/50 + 2;
			
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
		System.out.println(attack.getName() + " used " + move.getMoveName() + "!");
		int damage = damageCalc(myPKMN, oppPKMN, move);
		if(move.getCategory() != 2)
		{
			System.out.println(defense.getName() + " took " + damage + " damage!");
			defense.setCurrHP(defense.getCurrHP() - damage);
		}
	}

}
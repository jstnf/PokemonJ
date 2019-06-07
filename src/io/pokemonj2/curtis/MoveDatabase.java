package io.pokemonj2.curtis;

import java.io.File;
import java.util.Scanner;

public class MoveDatabase {
	private Move[] moveDatabase;//size 125
	
	public MoveDatabase()
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
	
	public Move get(int ind)
	{
		return moveDatabase[ind];
	}
}

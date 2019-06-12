package io.pokemonj2.curtis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import io.pokemonj2.gfx.Assets;

public class Trainer
{
	private Random r;
	
	// CANNOT CHANGE THESE
	private String trainerClass;
	private int gender;
	private String name;
	private MoveDatabase allMoves;
	
	// THESE CAN CHANGE
	public int money;
	public Pokemon pokemon;
	public int pokemonDexNum; // TEMP STORED HERE BECAUSE POKEMON DOES NOT HAVE DEXNUM IN ITS CLASS

	// Player constructor
	public Trainer(String name, int gender)
	{
		this.name = name;
		this.gender = gender;
		allMoves = new MoveDatabase();
	}
	
	// CPU constructor
	public Trainer(int trainerClass)
	{
		r = new Random();
		this.trainerClass = Assets.trainerClassInfo[trainerClass][0];
		gender = Integer.parseInt(Assets.trainerClassInfo[trainerClass][1]);
		cpuGenerateName(Integer.parseInt(Assets.trainerClassInfo[trainerClass][2]), Integer.parseInt(Assets.trainerClassInfo[trainerClass][3]));
		allMoves = new MoveDatabase();
	}
	
	public void generatePKMN(int dexNum)
	{
		pokemonDexNum = dexNum;
		String number = Integer.toString(dexNum);
		while(number.length() != 3)
			number = "0" + number;
		Scanner scnr;
		try {
			scnr = new Scanner(new File("res\\data\\pokemon\\" + number + ".txt"));
		scnr.nextLine();
		String name = scnr.nextLine();
		int type1 = scnr.nextInt();
		int type2 = scnr.nextInt();
		int[] stats = {scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt()};
		Move[] moves = {allMoves.get((int)(Math.random() * 125)), allMoves.get((int)(Math.random() * 125)), allMoves.get((int)(Math.random() * 125)), allMoves.get((int)(Math.random() * 125))};
		scnr.close();
		pokemon = new Pokemon(name, type1, type2, stats, moves, 50);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cpuGenerateName(int numOfNames, int mainNamesIndex)
	{
		if (mainNamesIndex != 0)
		{
			name = Assets.mainNames[mainNamesIndex];
		}
		else
		{
			if (numOfNames == 1)
			{
				if (gender == 0)
				{
					name = Assets.boyNames[r.nextInt(1000)];
				}
				else
				{
					name = Assets.girlNames[r.nextInt(1000)];
				}
			}
			else
			{
				String name1 = "", name2 = "";
				if (gender == 0)
				{
					name1 = Assets.boyNames[r.nextInt(1000)];
					name2 = Assets.boyNames[r.nextInt(1000)];
				}
				else if (gender == 1)
				{
					name1 = Assets.girlNames[r.nextInt(1000)];
					name2 = Assets.girlNames[r.nextInt(1000)];
				}
				else
				{
					name1 = Assets.girlNames[r.nextInt(1000)];
					name2 = Assets.boyNames[r.nextInt(1000)];
				}
				name = name1 + " and " + name2;
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getGender()
	{
		return gender;
	}
	
	public Pokemon getPokemon()
	{
		return pokemon;
	}

	public int getMoney()
	{
		return money;
	}
	
	public int getDexNum()
	{
		return pokemonDexNum;
	}
	
	public String getTrainerClass()
	{
		return trainerClass;
	}
}
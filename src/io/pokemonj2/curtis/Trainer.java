package io.pokemonj2.curtis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trainer
{
	// CANNOT CHANGE THESE
	private int gender;
	private String name;
	private MoveDatabase allMoves;
	
	// THESE CAN CHANGE
	public int money;
	public Pokemon pokemon;

	public Trainer(String name, int gender)
	{
		this.name = name;
		this.gender = gender;
	}
	
	public void generatePKMN(int dexNum)
	{
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
}
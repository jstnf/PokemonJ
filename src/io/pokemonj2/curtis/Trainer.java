package io.pokemonj2.curtis;

public class Trainer
{
	// CANNOT CHANGE THESE
	private int gender;
	private String name;
	
	// THESE CAN CHANGE
	public int money;
	public Pokemon pokemon;

	public Trainer(String name, int gender)
	{
		this.name = name;
		this.gender = gender;
	}

	public void assignPokemon(Pokemon p)
	{
		pokemon = p;
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
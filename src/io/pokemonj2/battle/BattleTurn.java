package io.pokemonj2.battle;

import io.pokemonj2.curtis.Pokemon;
import io.pokemonj2.curtis.Status;
import io.pokemonj2.curtis.Trainer;

public class BattleTurn
{
	private Trainer attTrainer, defTrainer;
	private int moveIndex;
	private int whosAttacking; // 0 YOU, 1 OPPONENT

	public BattleTurn(Trainer attacker, Trainer defender, int moveIndex, int whosAttacking)
	{
		attTrainer = attacker;
		defTrainer = defender;
		this.moveIndex = moveIndex;
		this.whosAttacking = whosAttacking;
	}

	public int getSpeed()
	{
		Pokemon attPokemon = attTrainer.getPokemon();

		int speed = attPokemon.getCurrStats()[5]; // get base speed
		if (attPokemon.getCurrStatus() == Status.PARALYSIS)
		{
			speed /= 2;
		}

		double multiplier;

		if (attPokemon.getStatChanges()[4] > 0)
			multiplier = (attPokemon.getStatChanges()[4] + 2) / 2.0;
		else if (attPokemon.getStatChanges()[4] < 0)
			multiplier = 2.0 / (-1 * attPokemon.getStatChanges()[4] + 2);
		else
			multiplier = 1;

		return (int) (speed * multiplier);
	}

	public int getWhosAttacking()
	{
		return whosAttacking;
	}

	public Trainer getAttacker()
	{
		return attTrainer;
	}

	public Trainer getDefender()
	{
		return defTrainer;
	}
}
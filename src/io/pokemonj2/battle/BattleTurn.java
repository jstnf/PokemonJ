package io.pokemonj2.battle;

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
		return 0; // temporary!
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
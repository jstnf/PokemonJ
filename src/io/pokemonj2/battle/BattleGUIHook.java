package io.pokemonj2.battle;

import io.pokemonj2.states.BattleState;

import java.util.ArrayList;

public class BattleGUIHook
{
	private BattleState state;

	private ArrayList<BattleTurn> turnBuffer;
	private ArrayList<BattleEvent> eventBuffer;

	public BattleGUIHook(BattleState state)
	{
		this.state = state;
		turnBuffer = new ArrayList<BattleTurn>();
		eventBuffer = new ArrayList<BattleEvent>();
	}

	public boolean hasNextMove()
	{
		return turnBuffer.size() > 0;
	}

	public BattleTurn getNextMove()
	{
		if (turnBuffer.size() == 0)
		{
			return null;
		}
		else
		{
			int greatestSpeed = -1;
			int index = -1;
			for (int i = 0; i < turnBuffer.size(); i++)
			{
				if (turnBuffer.get(i).getSpeed() > greatestSpeed)
				{
					greatestSpeed = turnBuffer.get(i).getSpeed();
					index = i;
				}
			}

			if (index < 0) // SOMETHING WENT WRONG!
			{
				return null;
			}
			else
			{
				return turnBuffer.remove(index);
			}
		}
	}
}
package io.pokemonj2.battle;

import java.util.ArrayList;

public class BattleEvent
{
	private ArrayList<String> text;
	private int myNewHP, oppNewHP;
	private BattleEventType eventType;
	private int effectiveness;

	public BattleEvent(BattleEventType type, int myHP, int oppHP, int effectiveness)
	{
		eventType = type;
		myNewHP = myHP;
		oppNewHP = oppHP;
		text = new ArrayList<String>();

		/**
		 * Effectiveness - for use in playing sounds
		 *
		 * -1 - Not an attack
		 * 0 - Normal attack
		 * 1 - Not very effective
		 * 2 - Super effective
		 * 3 - Immune
		 * 4 - Poison (unused)
		 * 5 - Burn (unused)
		 */
		this.effectiveness = effectiveness;
	}

	public void addLines(String... lines)
	{
		for (String s : lines)
		{
			text.add(s);
		}
	}

	public ArrayList<String> getText()
	{
		return text;
	}

	public int getMyNewHP()
	{
		return myNewHP;
	}

	public int getOppNewHP()
	{
		return oppNewHP;
	}

	public int getEffectiveness()
	{
		return effectiveness;
	}
}
package io.pokemonj2.battle;

import io.pokemonj2.states.BattleState;

import java.awt.Graphics;
import java.util.ArrayList;

public class BattleGUIHook
{
	private BattleState state;

	private ArrayList<BattleTurn> turnBuffer;

	public BattleGUIHook(BattleState state)
	{
		this.state = state;
	}
}
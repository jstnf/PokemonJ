package io.pokemonj2.states;

import java.awt.Color;
import java.awt.Graphics;

import io.pokemonj2.Game;

public class GameMenuState extends State
{
	public GameMenuState(Game game)
	{
		super(game);
	}

	@Override
	public void tick()
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(100, 100, 255));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
	}
}
package io.pokemonj2.states;

import java.awt.Graphics;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;

public class MenuState extends State
{
	public MenuState(Game game)
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
		g.drawImage(Assets.scene, 0, 0, game.getWidth(), game.getHeight(), null);
	}
}
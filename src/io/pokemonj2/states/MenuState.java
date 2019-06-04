package io.pokemonj2.states;

import java.awt.Color;
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
		g.drawImage(Assets.scene, 0, 0, game.getWidth(), game.getHeight() - 180, null); //battle background
		int pokemonToDraw = 150;
		g.drawImage(Assets.battleBackSprite.get(pokemonToDraw), 90, 160 + (int) ((63 - Assets.battleBackPixel.get(pokemonToDraw)) / 63.0 * 300.0), 300, 300, null); // front sprite
		g.setColor(Color.BLACK);
		g.fillRect(0, game.getHeight() - 180, game.getWidth(), 180);
		g.setColor(Color.WHITE);
		g.fillRect(5, game.getHeight() - 175, game.getWidth() - 10, 170);
	}
}
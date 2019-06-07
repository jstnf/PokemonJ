package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;

public class TitleState extends State
{
	private float press_space_alpha;
	private boolean increasing;

	private final int POKEMON_EVERY_X_SECONDS = 3;
	private final int POKEMON_SCROLL_VELOCITY = 4;
	private int scrollGenerateCounter;
	private Random r;
	private ArrayList<Integer> scrolling, position;
	
	private boolean startSequenceInit;
	private float blackFadeTransparency;

	public TitleState(Game game)
	{
		super(game);
		
		startSequenceInit = false;
		blackFadeTransparency = 0;

		scrolling = new ArrayList<Integer>();
		position = new ArrayList<Integer>();
		press_space_alpha = 0;
		increasing = true;
		scrollGenerateCounter = 0;
		r = new Random();
	}

	@Override
	public void tick()
	{
		/* Press space transparency */
		if (increasing)
		{
			press_space_alpha += 0.03f;
			if (press_space_alpha >= 1)
			{
				increasing = !increasing;
				press_space_alpha = 1;
			}
		}
		else
		{
			press_space_alpha -= 0.03f;
			if (press_space_alpha <= 0)
			{
				increasing = !increasing;
				press_space_alpha = 0;
			}
		}

		/* Scroll counter */
		scrollGenerateCounter += 1;
		if (scrollGenerateCounter >= 60 /* FPS */ * POKEMON_EVERY_X_SECONDS)
		{
			scrollGenerateCounter = 0;
			int dex = r.nextInt(game.NUM_OF_POKEMON);
			scrolling.add(dex);
			position.add(-100);
		}

		for (int i = 0; i < position.size(); i++)
		{
			position.set(i, position.get(i) + POKEMON_SCROLL_VELOCITY);
			if (position.get(i) > game.getWidth())
			{
				position.remove(i);
				scrolling.remove(i);
				i--;
			}
		}
		
		/* Execute end */
		if (game.getKeyManager().space && !startSequenceInit)
		{
			startSequenceInit = true;
			System.out.println("Start sequence initiated!");
		}
		else if (startSequenceInit)
		{
			if (blackFadeTransparency < 1.5f)
			{
				blackFadeTransparency += 0.01f;
			}
			else
			{
				State.setState(new GameMenuState(game));
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.titleScreen, 0, 0, game.getWidth(), game.getHeight(), null);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, press_space_alpha);
		((Graphics2D) g).setComposite(ac);
		((Graphics2D) g).drawImage(Assets.pressSpace, 230, 580, 500, 25, null);

		AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		((Graphics2D) g).setComposite(ac2);
		for (int i = 0; i < position.size(); i++)
		{
			if (position.get(i) < 120)
			{
				g.drawImage(Assets.battleFrontSprite.get(scrolling.get(i)), 120, 360, 180, 180, null);
			}
			else
			{
				g.drawImage(Assets.battleFrontSprite.get(scrolling.get(i)), position.get(i), 360, 180, 180, null);
			}
		}
		
		if (startSequenceInit)
		{
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, blackFadeTransparency)));
			((Graphics2D) g).fillRect(0, 0, game.getWidth(), game.getHeight());
		}
	}
}
package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.sfx.AudioManager;

public class WelcomeState extends State
{
	private float bgFadeIn, oakFadeIn;
	private boolean beginDialogue;
	private int welcomeStage;
	
	public WelcomeState(Game game)
	{
		super(game);
		
		bgFadeIn = 0.0f;
		oakFadeIn = 0.0f;
		beginDialogue = false;
		welcomeStage = -1;
		
		AudioManager.playMusic("/sfx/music/welcome-to.wav");
	}

	@Override
	public void tick()
	{
		if (bgFadeIn < 1.0f)
		{
			bgFadeIn += 0.03f;
		}
		else
		{
			if (oakFadeIn < 1.0f)
			{
				oakFadeIn += 0.03f;
			}
			else
			{
				beginDialogue = true;
				welcomeStage = 1;
			}
		}
		
		if (beginDialogue)
		{
			
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, bgFadeIn)));
		g.drawImage(Assets.intro_bg, 0, 0, game.getWidth(), game.getHeight(), null);
		
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, oakFadeIn)));
		g.drawImage(Assets.oak, game.getWidth() / 2 - 100, game.getHeight() / 5, 200, 324, null);
		
		if (beginDialogue)
		{
			
		}
	}
}
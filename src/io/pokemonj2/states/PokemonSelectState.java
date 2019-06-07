package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.FontDrawer;

public class PokemonSelectState extends State
{
	private float fadeIn;
	private boolean selectInit;
	
	private int selectionStage;
	
	private Random r;
	private int pokemon1, pokemon2, pokemon3;
	
	public PokemonSelectState(Game game)
	{
		super(game);
		
		r = new Random();
		
		pokemon1 = r.nextInt(game.NUM_OF_POKEMON);
		pokemon2 = r.nextInt(game.NUM_OF_POKEMON);
		pokemon3 = r.nextInt(game.NUM_OF_POKEMON);
		
		fadeIn = 1.5f;
		selectInit = false;
		selectionStage = 0;
	}

	@Override
	public void tick()
	{
		if (fadeIn > 0.0f && !selectInit)
		{
			fadeIn -= 0.03f;
			if (fadeIn < 0.0f)
			{
				fadeIn = 0.0f;
			}
		}
		else
		{
			selectInit = true;
		}
		
		if (selectInit)
		{
			
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.table, 0, 0, game.getWidth(), game.getHeight(), null);
		
		g.setColor(Color.BLACK);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, fadeIn)));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		if (selectInit)
		{
			drawDialogueBox(g);
			
			if (selectionStage == 0)
			{
				FontDrawer.drawText("Which POKéMON will you choose?", 70, game.getHeight() - 150, 50, g);
			}
			else if (selectionStage == 1)
			{
				
			}
			else if (selectionStage == 2)
			{
				
			}
		}
	}
	
	private void drawDialogueBox(Graphics g)
	{
		g.drawImage(Assets.tl, 20, game.getHeight() - 200, 40, 40, null);
		g.drawImage(Assets.t_ho, 60, game.getHeight() - 200, game.getWidth() - 120, 40, null);
		g.drawImage(Assets.tr, game.getWidth() - 60, game.getHeight() - 200, 40, 40, null);
		g.drawImage(Assets.l_ve, 20, game.getHeight() - 160, 40, 120, null);
		g.drawImage(Assets.r_ve, game.getWidth() - 60, game.getHeight() - 160, 40, 120, null);
		g.drawImage(Assets.bl, 20, game.getHeight() - 40, 40, 40, null);
		g.drawImage(Assets.br, game.getWidth() - 60, game.getHeight() - 40, 40, 40, null);
		g.drawImage(Assets.b_ho, 60, game.getHeight() - 40, game.getWidth() - 120, 40, null);
		g.drawImage(Assets.fill, 60, game.getHeight() - 160, game.getWidth() - 120, 120, null);
	}
}
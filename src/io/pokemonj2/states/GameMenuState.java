package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.ObjectDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

public class GameMenuState extends State
{
	private boolean initSeq;
	private float blackFadeTransparency;
	
	public GameMenuState(Game game)
	{
		super(game);
		
		initSeq = false;
		blackFadeTransparency = 0;
	}

	@Override
	public void tick()
	{
		if (game.getKeyManager().interact && !initSeq)
		{
			/* Start new game! */
			initSeq = true;
			AudioManager.playSound(Sounds.CONFIRM);
		}
		
		if (initSeq)
		{
			if (blackFadeTransparency < 1.5f)
			{
				blackFadeTransparency += 0.01f;
			}
			else
			{
				State.setState(new WelcomeState(game));
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(100, 100, 255));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		// NEW GAME
		g.drawImage(Assets.tl, 20, 20, 40, 40, null);
		g.drawImage(Assets.t_ho, 60, 20, game.getWidth() - 120, 40, null);
		g.drawImage(Assets.tr, game.getWidth() - 60, 20, 40, 40, null);
		g.drawImage(Assets.l_ve, 20, 60, 40, 80, null);
		g.drawImage(Assets.r_ve, game.getWidth() - 60, 60, 40, 80, null);
		g.drawImage(Assets.bl, 20, 140, 40, 40, null);
		g.drawImage(Assets.br, game.getWidth() - 60, 140, 40, 40, null);
		g.drawImage(Assets.b_ho, 60, 140, game.getWidth() - 120, 40, null);
		g.drawImage(Assets.fill, 60, 60, game.getWidth() - 120, 80, null);
		
		ObjectDrawer.drawBigText("NEW GAME", 64, 64, 72, g);
		
		if (initSeq)
		{
			g.setColor(Color.BLACK);
			((Graphics2D) g).setComposite(
					AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, blackFadeTransparency)));
			((Graphics2D) g).fillRect(0, 0, game.getWidth(), game.getHeight());
		}
	}
}
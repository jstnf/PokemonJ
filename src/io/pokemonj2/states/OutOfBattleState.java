package io.pokemonj2.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.FontDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

public class OutOfBattleState extends State
{
	private int selectionIndex;
	private int actionDelay;
	
	public OutOfBattleState(Game game)
	{
		super(game);
		
		selectionIndex = 0;
	}

	@Override
	public void tick()
	{
		actionDelay--;
		if (actionDelay < 0)
		{
			actionDelay = 0;
		}
		
		if (game.getKeyManager().left && actionDelay == 0)
		{
			selectionIndex -= 1;
			if (selectionIndex < 0)
			{
				selectionIndex = 0;
			}
			actionDelay = 20;
			AudioManager.playSound(Sounds.CONFIRM);
		}
		else if (game.getKeyManager().right && actionDelay == 0)
		{
			selectionIndex += 1;
			if (selectionIndex > 1)
			{
				selectionIndex = 1;
			}
			actionDelay = 20;
			AudioManager.playSound(Sounds.CONFIRM);
		}
		else if (game.getKeyManager().interact && actionDelay == 0)
		{
			if (selectionIndex == 0)
			{
				// BATTLE!
			}
			else
			{
				// HEAL!
			}
			actionDelay = 20;
			AudioManager.playSound(Sounds.CONFIRM);
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		g.drawImage(Assets.tl, 20, 0, 20, 20, null);
		g.drawImage(Assets.t_ho, 60, 0, game.getWidth() - 60, 20, null);
		g.drawImage(Assets.tr, game.getWidth() - 40, 0, 20, 20, null);
		g.drawImage(Assets.l_ve, 20, 40, 40, 120, null);
		g.drawImage(Assets.r_ve, game.getWidth() - 60, 40, 40, 120, null);
		g.drawImage(Assets.bl, 20, game.getHeight() - 40, 40, 40, null);
		g.drawImage(Assets.br, game.getWidth() - 60, game.getHeight() - 40, 40, 40, null);
		g.drawImage(Assets.b_ho, 60, game.getHeight() - 40, game.getWidth() - 120, 40, null);
		g.drawImage(Assets.fill, 60, game.getHeight() - 160, game.getWidth() - 120, 120, null);
		
		switch (selectionIndex)
		{
			case 0:
				drawRedBox(50, true, g);
				drawBlueBox(505, false, g);
				break;
			case 1:
				drawRedBox(50, false, g);
				drawBlueBox(505, true, g);
				break;
		}
		
	}
	
	private void drawRedBox(int x, boolean selected, Graphics g)
	{
		BufferedImage tl = Assets.red_tl;
		BufferedImage t_ho = Assets.red_t_ho;
		BufferedImage tr = Assets.red_tr;
		BufferedImage l_ve = Assets.red_l_ve;
		BufferedImage r_ve = Assets.red_r_ve;
		BufferedImage bl = Assets.red_bl;
		BufferedImage br = Assets.red_br;
		BufferedImage b_ho = Assets.red_b_ho;
		BufferedImage fill = Assets.red_fill;

		if (!selected)
		{
			RescaleOp op = new RescaleOp(new float[] { 0.5f, 0.5f, 0.5f, 1f }, new float[] { 0, 0, 0, 0 }, null);
			tl = op.filter(tl, null);
			t_ho = op.filter(t_ho, null);
			tr = op.filter(tr, null);
			l_ve = op.filter(l_ve, null);
			r_ve = op.filter(r_ve, null);
			bl = op.filter(bl, null);
			br = op.filter(br, null);
			b_ho = op.filter(b_ho, null);
			fill = op.filter(fill, null);
		}

		g.drawImage(tl, x, game.getHeight() / 6, 30, 30, null);
		g.drawImage(t_ho, x + 30, game.getHeight() / 6, 345, 30, null);
		g.drawImage(tr, x + 375, game.getHeight() / 6, 30, 30, null);
		g.drawImage(l_ve, x, game.getHeight() / 6 + 30, 30, 345, null);
		g.drawImage(r_ve, x + 375, game.getHeight() / 6 + 30, 30, 345, null);
		g.drawImage(bl, x, game.getHeight() / 6 + 375, 30, 30, null);
		g.drawImage(br, x + 375, game.getHeight() / 6 + 375, 30, 30, null);
		g.drawImage(b_ho, x + 30, game.getHeight() / 6 + 375, 345, 30, null);
		g.drawImage(fill, x + 30, game.getHeight() / 6 + 30, 345, 345, null);
		
		FontDrawer.drawText("BATTLE", x + 125, game.getHeight() / 6 + 150, 50, g);
	}
	
	private void drawBlueBox(int x, boolean selected, Graphics g)
	{
		BufferedImage tl = Assets.blue_tl;
		BufferedImage t_ho = Assets.blue_t_ho;
		BufferedImage tr = Assets.blue_tr;
		BufferedImage l_ve = Assets.blue_l_ve;
		BufferedImage r_ve = Assets.blue_r_ve;
		BufferedImage bl = Assets.blue_bl;
		BufferedImage br = Assets.blue_br;
		BufferedImage b_ho = Assets.blue_b_ho;
		BufferedImage fill = Assets.blue_fill;

		if (!selected)
		{
			RescaleOp op = new RescaleOp(new float[] { 0.5f, 0.5f, 0.5f, 1f }, new float[] { 0, 0, 0, 0 }, null);
			tl = op.filter(tl, null);
			t_ho = op.filter(t_ho, null);
			tr = op.filter(tr, null);
			l_ve = op.filter(l_ve, null);
			r_ve = op.filter(r_ve, null);
			bl = op.filter(bl, null);
			br = op.filter(br, null);
			b_ho = op.filter(b_ho, null);
			fill = op.filter(fill, null);
		}

		g.drawImage(tl, x, game.getHeight() / 6, 30, 30, null);
		g.drawImage(t_ho, x + 30, game.getHeight() / 6, 345, 30, null);
		g.drawImage(tr, x + 375, game.getHeight() / 6, 30, 30, null);
		g.drawImage(l_ve, x, game.getHeight() / 6 + 30, 30, 345, null);
		g.drawImage(r_ve, x + 375, game.getHeight() / 6 + 30, 30, 345, null);
		g.drawImage(bl, x, game.getHeight() / 6 + 375, 30, 30, null);
		g.drawImage(br, x + 375, game.getHeight() / 6 + 375, 30, 30, null);
		g.drawImage(b_ho, x + 30, game.getHeight() / 6 + 375, 345, 30, null);
		g.drawImage(fill, x + 30, game.getHeight() / 6 + 30, 345, 345, null);
		
		FontDrawer.drawText("HEAL", x + 155, game.getHeight() / 6 + 150, 50, g);
	}
}
package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import io.pokemonj2.Game;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.ImageLoader;
import io.pokemonj2.gfx.ObjectDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

public class OutOfBattleState extends State
{
	private int selectionIndex;
	private int actionDelay;
	private int menuState;
	
	private BufferedImage pokemonIcon;
	private boolean pokemonDisplace;
	private int displaceTimer, displaceThreshold;
	private double hpBarRatio;
	
	private int areYouSureBattle;
	
	private boolean initBattleSounds;
	
	public OutOfBattleState(Game game)
	{
		super(game);
		
		menuState = 0;
		
		selectionIndex = 0;
		actionDelay = 0;
		
		String number = Integer.toString(game.getTrainer().getDexNum());
		while(number.length() != 3)
		{
			number = "0" + number;
		}
		pokemonIcon = ImageLoader.loadImage("/textures/sprites/icons/" + number + ".png");
		
		pokemonDisplace = false;
		displaceTimer = 0;
		displaceThreshold = 10;
		
		areYouSureBattle = 1;
		
		initBattleSounds = false;
	}

	@Override
	public void tick()
	{
		actionDelay--;
		if (actionDelay < 0)
		{
			actionDelay = 0;
		}
		
		int[] maxStats = game.getTrainer().getPokemon().getCurrStats();
		int maxHp = maxStats[0];
		double currHp = game.getTrainer().getPokemon().getCurrHP();
		hpBarRatio = (double) currHp / maxHp;
		
		if (hpBarRatio >= 0.5)
		{
			displaceThreshold = 10;
		}
		else if (hpBarRatio < 0.5 && hpBarRatio > 0.2)
		{
			displaceThreshold = 15;
		}
		else if (hpBarRatio < 0.2 && hpBarRatio > 0.0)
		{
			displaceThreshold = 30;
		}
		else if (hpBarRatio == 0.0)
		{
			displaceThreshold = Integer.MAX_VALUE;
		}
		
		displaceTimer++;
		if (displaceTimer > displaceThreshold)
		{
			displaceTimer = 0;
			pokemonDisplace = !pokemonDisplace;
		}
		
		if (menuState == 0)
		{
			if (game.getKeyManager().up && actionDelay == 0)
			{
				selectionIndex -= 1;
				if (selectionIndex < 0)
				{
					selectionIndex = 0;
				}
				else
				{
					AudioManager.playSound(Sounds.CONFIRM);
				}
				actionDelay = 20;
			}
			else if (game.getKeyManager().down && actionDelay == 0)
			{
				selectionIndex += 1;
				if (selectionIndex > 1)
				{
					selectionIndex = 1;
				}
				else
				{
					AudioManager.playSound(Sounds.CONFIRM);
				}
				actionDelay = 20;
			}
			else if (game.getKeyManager().interact && actionDelay == 0)
			{
				AudioManager.playSound(Sounds.CONFIRM);
				if (selectionIndex == 0)
				{
					// BATTLE!
					menuState = 1;
					actionDelay = 30;
				}
				else
				{
					// HEAL!
				}
			}
		}
		else if (menuState == 1) // Are you sure you want to battle?
		{
			if (game.getKeyManager().up && actionDelay == 0)
			{
				areYouSureBattle -= 1;
				if (areYouSureBattle < 0)
				{
					areYouSureBattle = 0;
				}
				else
				{
					AudioManager.playSound(Sounds.CONFIRM);
				}
				actionDelay = 20;
			}
			else if (game.getKeyManager().down && actionDelay == 0)
			{
				areYouSureBattle += 1;
				if (areYouSureBattle > 1)
				{
					areYouSureBattle = 1;
				}
				else
				{
					AudioManager.playSound(Sounds.CONFIRM);
				}
				actionDelay = 20;
			}
			else if (game.getKeyManager().interact && actionDelay == 0)
			{
				AudioManager.playSound(Sounds.CONFIRM);
				if (areYouSureBattle == 0)
				{
					// BATTLE!
					menuState = 2;
					actionDelay = 155;
				}
				else
				{
					// DON'T BATTLE!
					menuState = 0;
					actionDelay = 30;
					areYouSureBattle = 1;
				}
			}
		}
		else if (menuState == 2) // Initiating battle state
		{
			if (!initBattleSounds)
			{
				AudioManager.stopMusic();
				AudioManager.playSound(Sounds.BATTLE_START);
				initBattleSounds = true;
			}
			else
			{
				if (actionDelay == 0)
				{
					AudioManager.playMusic("/sfx/music/battle.wav");
					State.setState(new BattleState(game));
				}
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		g.drawImage(Assets.oob1, 0, 0, game.getWidth(), game.getHeight(), null);
		
		RescaleOp trainerRescale = new RescaleOp(new float[] { 0.1f, 0.1f, 0.1f, 1f }, new float[] { 0, 0, 0, 0 }, null);
		switch (game.getTrainer().getGender())
		{
			case 0: // MALE
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.80f));
				g.drawImage(trainerRescale.filter(Assets.trainer_boy, null), 43, 160, 232, 320, null);
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				g.drawImage(Assets.trainer_boy, 50, 170, 218, 300, null);
				break;
			case 1: // FEMALE
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.80f));
				g.drawImage(trainerRescale.filter(Assets.trainer_girl, null), 43, 160, 232, 320, null);
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				g.drawImage(Assets.trainer_girl, 50, 170, 218, 300, null);
				break;
		}
		
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
		g.drawImage(Assets.pokeballIcon, 320, 185, 180, 180, null);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		if (pokemonDisplace)
		{
			g.drawImage(pokemonIcon, 330, 185, 160, 160, null);
		}
		else
		{
			g.drawImage(pokemonIcon, 330, 195, 160, 160, null);
		}
		g.drawImage(Assets.hpBar, 285, 395, 250, 27, null);
		ObjectDrawer.drawHpBar(hpBarRatio, 346, 403, 181, 11, g);
		
		// STATUS BAR TOP 920 x 120
		g.drawImage(Assets.tl, 20, 10, 20, 20, null);
		g.drawImage(Assets.t_ho, 40, 10, game.getWidth() - 80, 20, null);
		g.drawImage(Assets.tr, game.getWidth() - 40, 10, 20, 20, null);
		g.drawImage(Assets.l_ve, 20, 30, 20, 80, null);
		g.drawImage(Assets.r_ve, game.getWidth() - 40, 30, 20, 80, null);
		g.drawImage(Assets.bl, 20, 110, 20, 20, null);
		g.drawImage(Assets.br, game.getWidth() - 40, 110, 20, 20, null);
		g.drawImage(Assets.b_ho, 40, 110, game.getWidth() - 80, 20, null);
		g.drawImage(Assets.fill, 40, 30, game.getWidth() - 80, 80, null);
		
		// STATUS BAR BOTTOM
		g.drawImage(Assets.tl, 20, game.getHeight() - 130, 20, 20, null);
		g.drawImage(Assets.t_ho, 40, game.getHeight() - 130, game.getWidth() - 80, 20, null);
		g.drawImage(Assets.tr, game.getWidth() - 40, game.getHeight() - 130, 20, 20, null);
		g.drawImage(Assets.l_ve, 20, game.getHeight() - 110, 20, 80, null);
		g.drawImage(Assets.r_ve, game.getWidth() - 40, game.getHeight() - 110, 20, 80, null);
		g.drawImage(Assets.bl, 20, game.getHeight() - 30, 20, 20, null);
		g.drawImage(Assets.br, game.getWidth() - 40, game.getHeight() - 30, 20, 20, null);
		g.drawImage(Assets.b_ho, 40, game.getHeight() - 30, game.getWidth() - 80, 20, null);
		g.drawImage(Assets.fill, 40, game.getHeight() - 110, game.getWidth() - 80, 80, null);
		
		ObjectDrawer.drawBigText(game.getTrainer().getName(), 60, game.getHeight() - 100, 60, g); // Trainer name
		
		switch (selectionIndex)
		{
			case 0:
				drawRedBox(game.getWidth() - 405, true, g);
				drawBlueBox(game.getWidth() - 405, false, g);
				break;
			case 1:
				drawRedBox(game.getWidth() - 405, false, g);
				drawBlueBox(game.getWidth() - 405, true, g);
				break;
		}
		
		if (menuState == 1)
		{
			ObjectDrawer.drawDialogueBox(game, g);
			ObjectDrawer.drawBigText("Are you sure you want to BATTLE?", 70, game.getHeight() - 150, 50, g);
			ObjectDrawer.drawBigText("There is no going back.", 70, game.getHeight() - 90, 50, g);
			
			g.drawImage(Assets.tl, game.getWidth() - 300, game.getHeight() - 400, 40, 40, null);
			g.drawImage(Assets.t_ho, game.getWidth() - 260, game.getHeight() - 400, 200, 40, null);
			g.drawImage(Assets.tr, game.getWidth() - 60, game.getHeight() - 400, 40, 40, null);
			g.drawImage(Assets.l_ve, game.getWidth() - 300, game.getHeight() - 360, 40, 120, null);
			g.drawImage(Assets.r_ve, game.getWidth() - 60, game.getHeight() - 360, 40, 120, null);
			g.drawImage(Assets.bl, game.getWidth() - 300, game.getHeight() - 240, 40, 40, null);
			g.drawImage(Assets.br, game.getWidth() - 60, game.getHeight() - 240, 40, 40, null);
			g.drawImage(Assets.b_ho, game.getWidth() - 260, game.getHeight() - 240, 200, 40, null);
			g.drawImage(Assets.fill, game.getWidth() - 260, game.getHeight() - 360, 200, 120, null);

			ObjectDrawer.drawBigText("YES", game.getWidth() - 210, game.getHeight() - 350, 50, g);
			ObjectDrawer.drawBigText("NO", game.getWidth() - 210, game.getHeight() - 290, 50, g);
			
			if (areYouSureBattle == 0)
			{
				g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 345, 20, 40, null);
			}
			else
			{
				g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 285, 20, 40, null);
			}
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

		g.drawImage(tl, x, 140, 20, 20, null);
		g.drawImage(t_ho, x + 20, 140, 345, 20, null);
		g.drawImage(tr, x + 365, 140, 20, 20, null);
		g.drawImage(l_ve, x, 160, 20, 135, null);
		g.drawImage(r_ve, x + 365, 160, 20, 135, null);
		g.drawImage(bl, x, 295, 20, 20, null);
		g.drawImage(br, x + 365, 295, 20, 20, null);
		g.drawImage(b_ho, x + 20, 295, 345, 20, null);
		g.drawImage(fill, x + 20, 160, 345, 135, null);
		
		ObjectDrawer.drawBigText("BATTLE", x + 125, game.getHeight() / 6 + 150, 50, g);
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

		g.drawImage(tl, x, 325, 20, 20, null);
		g.drawImage(t_ho, x + 20, 325, 345, 20, null);
		g.drawImage(tr, x + 365, 325, 20, 20, null);
		g.drawImage(l_ve, x, 345, 20, 135, null);
		g.drawImage(r_ve, x + 365, 345, 20, 135, null);
		g.drawImage(bl, x, 480, 20, 20, null);
		g.drawImage(br, x + 365, 480, 20, 20, null);
		g.drawImage(b_ho, x + 20, 480, 345, 20, null);
		g.drawImage(fill, x + 20, 345, 345, 135, null);
		
		ObjectDrawer.drawBigText("HEAL", x + 155, game.getHeight() / 6 + 150, 50, g);
	}
}
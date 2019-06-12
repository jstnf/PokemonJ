package io.pokemonj2.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import io.pokemonj2.Game;
import io.pokemonj2.curtis.Trainer;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.ObjectDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

public class BattleState extends State
{
	private int trainerClass;
	private Trainer opponent;
	
	private int actionDelay;
	
	private int battleState;
	private int blackBarHeight;
	private int farCircle_x, closeCircle_x, farTrainer_x, closeTrainer_x;
	
	private int nextDisplacement;
	private boolean nextIncreasing;
	
	private boolean c1Comp, c2Comp;
	
	private boolean drawClosePKMN, drawFarPKMN;
	private int genderToDraw;
	private BufferedImage[] currentThrowFrames;
	private int throwFramesIndex;
	
	private boolean initOppSendOut, initYouSendOut;
	
	private Random r;
	
	public BattleState(Game game)
	{
		super(game);
		
		r = new Random();
		
		trainerClass = r.nextInt(104);
		while (trainerClass == 83 || trainerClass == 89) // invalid trainer classes
		{
			trainerClass = r.nextInt(104);
		}
		
		opponent = new Trainer(trainerClass);
		opponent.generatePKMN(r.nextInt(151) + 1);
		
		nextDisplacement = 0;
		nextIncreasing = true;
		
		actionDelay = 0;
		battleState = 0;
		farCircle_x = 0 - (int) (Assets.battle_scene1_circle1.getWidth() / 240.0 * game.getWidth()) + 30;
		closeCircle_x = game.getWidth() - 10;
		farTrainer_x = farCircle_x + 136;
		closeTrainer_x = closeCircle_x + 200;
		blackBarHeight = game.getHeight() / 2 + 20;
		
		c1Comp = false;
		c2Comp = false;
		
		genderToDraw = game.getTrainer().getGender();
		throwFramesIndex = 0;
		switch (genderToDraw)
		{
			case 0:
				currentThrowFrames = Assets.throwFrames_boy;
				break;
			case 1:
				currentThrowFrames = Assets.throwFrames_girl;
				break;
		}

		initOppSendOut = false;
		initYouSendOut = false;
	}
	
	@Override
	public void tick()
	{
		actionDelay -= 1;
		if (actionDelay < 0)
		{
			actionDelay = 0;
		}
		
		if (nextIncreasing)
		{
			nextDisplacement += 1;
			if (nextDisplacement > 100)
			{
				nextIncreasing = !nextIncreasing;
			}
		}
		else
		{
			nextDisplacement -= 1;
			if (nextDisplacement < 0)
			{
				nextIncreasing = !nextIncreasing;
			}
		}
		
		// Battle is starting, do all the animations
		if (battleState == 0) // Black bars up and down
		{
			if (blackBarHeight > 200)
			{
				blackBarHeight -= 3;
			}
			else
			{
				battleState = 1;
			}
		}
		else if (battleState == 1) // Black bars disappear, circles and trainers come in
		{
			if (blackBarHeight > 0)
			{
				blackBarHeight -= 6;
				if (blackBarHeight < 0)
				{
					blackBarHeight = 0;
				}
			}
			
			if (farCircle_x < 448)
			{
				farCircle_x += 8;
				farTrainer_x += 8;
				if (farCircle_x >= 448)
				{
					farCircle_x = 448;
					farTrainer_x = farCircle_x + 136;
					c1Comp = true;
				}
			}
			
			if (closeCircle_x > 20)
			{
				closeCircle_x -= 8;
				closeTrainer_x -= 8;
				if (closeCircle_x <= 20)
				{
					closeCircle_x = 20;
					closeTrainer_x = closeCircle_x + 200;
					c2Comp = true;
				}
			}
			
			if (c1Comp && c2Comp)
			{
				battleState = 2;
				actionDelay = 20;
			}
		}
		else if (battleState == 2) // ____ would like to battle!
		{
			if (game.getKeyManager().interact && actionDelay == 0)
			{
				AudioManager.playSound(Sounds.CONFIRM);
				actionDelay = 20;
				battleState = 3;
			}
		}
		else if (battleState == 3) // Opponent sends out PKMN
		{
			if (!initOppSendOut)
			{
				if (actionDelay == 0)
				{
					initOppSendOut = true;
					AudioManager.playSound(Sounds.SEND_OUT2);
					actionDelay = 120;
				}
			}
			else
			{
				if (farTrainer_x < game.getWidth())
				{
					farTrainer_x += 10;
				}
				else if (farTrainer_x >= game.getWidth() && !drawFarPKMN)
				{
					AudioManager.playSound(Sounds.NORMAL_HIT);
					AudioManager.playSound("/sfx/cries/" + opponent.getDexNum() + ".wav");
					drawFarPKMN = true;
					actionDelay = 20;
				}
				
				if (game.getKeyManager().interact && actionDelay == 0)
				{
					AudioManager.playSound(Sounds.CONFIRM);
					actionDelay = 25;
					battleState = 4;
				}
			}
		}
		else if (battleState == 4) // You send out Pokemon and go away
		{
			if (closeTrainer_x > -250)
			{
				closeTrainer_x -= 8;
				
				if (closeTrainer_x < -250)
				{
					closeTrainer_x = -250;
				}
				
				if (closeTrainer_x < 220 && closeTrainer_x >= 140)
				{
					throwFramesIndex = 1;
				}
				else if (closeTrainer_x < 140 && closeTrainer_x >= 60)
				{
					throwFramesIndex = 2;
				}
				else if (closeTrainer_x < 60 && closeTrainer_x >= -20)
				{
					throwFramesIndex = 3;
				}
				else if (closeTrainer_x < -20 && closeTrainer_x > -250)
				{
					throwFramesIndex = 4;
				}
				else
				{
					throwFramesIndex = 0;
				}
			}
			
			if (!initYouSendOut)
			{
				if (actionDelay == 0)
				{
					initYouSendOut = true;
					AudioManager.playSound(Sounds.SEND_OUT2);
					actionDelay = 60;
				}
			}
			else
			{
				if (closeTrainer_x < -240 && !drawClosePKMN)
				{
					AudioManager.playSound(Sounds.NORMAL_HIT);
					AudioManager.playSound("/sfx/cries/" + game.getTrainer().getDexNum() + ".wav");
					drawClosePKMN = true;
					actionDelay = 60;
				}
				
				if (game.getKeyManager().interact && actionDelay == 0)
				{
					AudioManager.playSound(Sounds.CONFIRM);
					actionDelay = 120;
					battleState = 5;
				}
			}
		}
		else if (battleState == 5) // Normal battle menu
		{
			
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.battle_scene1, 0, 0, game.getWidth(), game.getHeight() - 200, null);
		g.drawImage(Assets.battle_scene1_circle1, farCircle_x, 188, 512, 126, null);
		g.drawImage(Assets.battle_scene1_circle2, closeCircle_x, game.getHeight() - 243, 480, 43, null);
		
		g.drawImage(Assets.trainerClasses[trainerClass], farTrainer_x, 40, 240, 240, null);
		g.drawImage(currentThrowFrames[throwFramesIndex], closeTrainer_x, game.getHeight() - 450, 250, 250, null);
		
		if (drawClosePKMN)
		{
			drawClosePKMN(game.getTrainer().getDexNum(), 0, g); // replace 0 with displacement later
		}
		
		if (drawFarPKMN)
		{
			drawFarPKMN(opponent.getDexNum(), 0, g); // replace 0 with displacement later
		}
		
		ObjectDrawer.drawBattleBox(game, g);
		
		if (battleState == 0) // Black bars
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, game.getWidth(), blackBarHeight);
			g.fillRect(0, game.getHeight() - blackBarHeight, game.getWidth(), blackBarHeight);
		}
		else if (battleState == 1)
		{
			if (blackBarHeight > 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, game.getWidth(), blackBarHeight);
				g.fillRect(0, game.getHeight() - blackBarHeight, game.getWidth(), blackBarHeight);
			}
		}
		else if (battleState == 2)
		{
			ObjectDrawer.drawBigWhiteText(opponent.getTrainerClass() + " " + opponent.getName(), 40, game.getHeight() - 150, 50, g);
			ObjectDrawer.drawBigWhiteText("would like to battle!", 40, game.getHeight() - 90, 50, g);
			if (actionDelay == 0)
			{
				g.drawImage(Assets.next, game.getWidth() - 70, game.getHeight() - 60 - (nextDisplacement / 10), 30, 20, null);
			}
		}
		else if (battleState == 3)
		{
			ObjectDrawer.drawBigWhiteText(opponent.getTrainerClass() + " " + opponent.getName() + " sent", 40, game.getHeight() - 150, 50, g);
			ObjectDrawer.drawBigWhiteText("out " + opponent.getPokemon().getName().toUpperCase() + "!", 40, game.getHeight() - 90, 50, g);
			if (actionDelay == 0)
			{
				g.drawImage(Assets.next, game.getWidth() - 70, game.getHeight() - 60 - (nextDisplacement / 10), 30, 20, null);
			}
		}
		else if (battleState == 4)
		{
			ObjectDrawer.drawBigWhiteText("Go! " + game.getTrainer().getPokemon().getName().toUpperCase() + "!", 40, game.getHeight() - 150, 50, g);
			if (actionDelay == 0)
			{
				g.drawImage(Assets.next, game.getWidth() - 70, game.getHeight() - 60 - (nextDisplacement / 10), 30, 20, null);
			}
		}
	}
	
	private void drawFarPKMN(int dexNum, int displace, Graphics g)
	{
		g.drawImage(Assets.battleFrontSprite.get(dexNum - 1), 574 + displace, 70, 240, 240, null);
	}
	
	private void drawClosePKMN(int dexNum, int displace, Graphics g)
	{
		g.drawImage(Assets.battleBackSprite.get(dexNum - 1), 110, 145 + (int) ((63 - Assets.battleBackPixel.get(dexNum - 1)) / 63.0 * 300.0) + displace, 300, 300, null);
	}
}
package io.pokemonj2.states;

import io.pokemonj2.Game;
import io.pokemonj2.curtis.Pokenum;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.ObjectDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.Random;

public class PokemonSelectState extends State
{
	private float fadeIn, transitionFade;
	private boolean selectInit;

	private int selectionStage;

	private int actionDelay;
	private int selectionIndex, yesNoIndex;
	
	private boolean initNewMusic;
	private boolean initReceiveSound;
	
	private int nextDisplacement;
	private boolean nextIncreasing;

	private Random r;
	private int pokemon1, pokemon2, pokemon3;
	private int box1_x, box2_x, box3_x;

	public PokemonSelectState(Game game)
	{
		super(game);

		r = new Random();

		pokemon1 = r.nextInt(game.NUM_OF_POKEMON);
		pokemon2 = r.nextInt(game.NUM_OF_POKEMON);
		pokemon3 = r.nextInt(game.NUM_OF_POKEMON);

		fadeIn = 1.5f;
		transitionFade = 0.0f;
		selectInit = false;
		selectionStage = 0;
		actionDelay = 0;
		selectionIndex = 0;
		yesNoIndex = 1;
		initNewMusic = false;
		initReceiveSound = false;
		
		nextDisplacement = 0;
		nextIncreasing = true;

		box1_x = 30;
		box2_x = 340;
		box3_x = 650;
	}

	@Override
	public void tick()
	{
		actionDelay--;
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
			if (selectionStage == 0)
			{
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
					if (selectionIndex > 2)
					{
						selectionIndex = 2;
					}
					actionDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
				else if (game.getKeyManager().interact && actionDelay == 0)
				{
					selectionStage = 1;
					actionDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
			}
			else if (selectionStage == 1)
			{
				if (game.getKeyManager().up && actionDelay == 0)
				{
					yesNoIndex -= 1;
					if (yesNoIndex < 0)
					{
						yesNoIndex = 0;
					}
					actionDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
				else if (game.getKeyManager().down && actionDelay == 0)
				{
					yesNoIndex += 1;
					if (yesNoIndex > 1)
					{
						yesNoIndex = 1;
					}
					actionDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
				else if (game.getKeyManager().interact && actionDelay == 0)
				{
					if (yesNoIndex == 0)
					{
						selectionStage = 2;
						AudioManager.stopMusic();
						actionDelay = 20;
					}
					else
					{
						selectionStage = 0;
						actionDelay = 30;
					}
					AudioManager.playSound(Sounds.CONFIRM);
				}
			}
			else if (selectionStage == 2)
			{
				if (!initReceiveSound && actionDelay == 0)
				{
					initReceiveSound = true;
					
					int pokemonChosen = 1; // Assign Pokemon
					switch (selectionIndex)
					{
						case 0:
							pokemonChosen = pokemon1 + 1;
							break;
						case 1:
							pokemonChosen = pokemon2 + 1;
							break;
						case 2:
							pokemonChosen = pokemon3 + 1;
							break;
					}
					game.getTrainer().generatePKMN(pokemonChosen);
					
					AudioManager.playSound(Sounds.RECEIVE);
					actionDelay = 180;
				}
				if (!initNewMusic && actionDelay == 0)
				{
					initNewMusic = true;
					AudioManager.playMusic("/sfx/music/ever-grande.wav");
				}
				
				if (game.getKeyManager().interact && actionDelay == 0 && initNewMusic)
				{
					AudioManager.playSound(Sounds.CONFIRM);
					selectionStage = 3;
				}
			}
			else if (selectionStage == 3)
			{
				if (transitionFade < 1.5f)
				{
					transitionFade += 0.01f;
				}
				else
				{
					State.setState(new OutOfBattleState(game));
				}
			}
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
			if (selectionStage == 0)
			{
				ObjectDrawer.drawDialogueBox(game, g);
				ObjectDrawer.drawBigText("Which POKéMON will you choose?", 70, game.getHeight() - 150, 50, g);
				
				switch (selectionIndex)
				{
					case 0:
						drawSelectionBox(box1_x, true, g, pokemon1);
						drawSelectionBox(box2_x, false, g, pokemon2);
						drawSelectionBox(box3_x, false, g, pokemon3);
						break;
					case 1:
						drawSelectionBox(box1_x, false, g, pokemon1);
						drawSelectionBox(box2_x, true, g, pokemon2);
						drawSelectionBox(box3_x, false, g, pokemon3);
						break;
					case 2:
						drawSelectionBox(box1_x, false, g, pokemon1);
						drawSelectionBox(box2_x, false, g, pokemon2);
						drawSelectionBox(box3_x, true, g, pokemon3);
						break;
				}
				
			}
			else if (selectionStage == 1)
			{
				ObjectDrawer.drawDialogueBox(game, g);
				int pokemonChosen = 1;
				switch (selectionIndex)
				{
					case 0:
						pokemonChosen = pokemon1 + 1;
						drawSelectionBox(box1_x, true, g, pokemon1);
						drawSelectionBox(box2_x, false, g, pokemon2);
						drawSelectionBox(box3_x, false, g, pokemon3);
						break;
					case 1:
						pokemonChosen = pokemon2 + 1;
						drawSelectionBox(box1_x, false, g, pokemon1);
						drawSelectionBox(box2_x, true, g, pokemon2);
						drawSelectionBox(box3_x, false, g, pokemon3);
						break;
					case 2:
						pokemonChosen = pokemon3 + 1;
						drawSelectionBox(box1_x, false, g, pokemon1);
						drawSelectionBox(box2_x, false, g, pokemon2);
						drawSelectionBox(box3_x, true, g, pokemon3);
						break;
				}
				
				String name = Pokenum.fromDexNum(pokemonChosen).getName();
				ObjectDrawer.drawBigText("Will you choose " + name + "?", 70, game.getHeight() - 150, 50, g);
				
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
				
				if (yesNoIndex == 0)
				{
					g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 345, 20, 40, null);
				}
				else
				{
					g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 285, 20, 40, null);
				}
			}
			else if (selectionStage == 2)
			{
				ObjectDrawer.drawDialogueBox(game, g);
				int pokemonChosen = 1;
				switch (selectionIndex)
				{
					case 0:
						pokemonChosen = pokemon1 + 1;
						break;
					case 1:
						pokemonChosen = pokemon2 + 1;
						break;
					case 2:
						pokemonChosen = pokemon3 + 1;
						break;
				}
				String name = Pokenum.fromDexNum(pokemonChosen).getName();
				ObjectDrawer.drawBigText("You chose " + name + "!", 70, game.getHeight() - 150, 50, g);
				
				if (actionDelay == 0)
				{
					g.drawImage(Assets.next, game.getWidth() - 100, game.getHeight() - 60 - (nextDisplacement / 10), 30,
							20, null);
				}
			}
			else if (selectionStage == 3)
			{
				g.setColor(Color.BLACK);
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, transitionFade)));
				g.fillRect(0, 0, game.getWidth(), game.getHeight());
			}
		}
	}

	private void drawSelectionBox(int x, boolean selected, Graphics g, int pokemon)
	{
		BufferedImage tl = Assets.tl;
		BufferedImage t_ho = Assets.t_ho;
		BufferedImage tr = Assets.tr;
		BufferedImage l_ve = Assets.l_ve;
		BufferedImage r_ve = Assets.r_ve;
		BufferedImage bl = Assets.bl;
		BufferedImage br = Assets.br;
		BufferedImage b_ho = Assets.b_ho;
		BufferedImage fill = Assets.fill;
		BufferedImage poke = Assets.battleFrontSprite.get(pokemon);

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
			poke = op.filter(poke, null);
		}

		g.drawImage(tl, x, game.getHeight() / 6, 30, 30, null);
		g.drawImage(t_ho, x + 30, game.getHeight() / 6, 220, 30, null);
		g.drawImage(tr, x + 250, game.getHeight() / 6, 30, 30, null);
		g.drawImage(l_ve, x, game.getHeight() / 6 + 30, 30, 220, null);
		g.drawImage(r_ve, x + 250, game.getHeight() / 6 + 30, 30, 220, null);
		g.drawImage(bl, x, game.getHeight() / 6 + 250, 30, 30, null);
		g.drawImage(br, x + 250, game.getHeight() / 6 + 250, 30, 30, null);
		g.drawImage(b_ho, x + 30, game.getHeight() / 6 + 250, 220, 30, null);
		g.drawImage(fill, x + 30, game.getHeight() / 6 + 30, 220, 220, null);
		g.drawImage(poke, x + 40, game.getHeight() / 6 + 40, 200, 200, null);
	}
}
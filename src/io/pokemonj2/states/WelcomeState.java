package io.pokemonj2.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import io.pokemonj2.Game;
import io.pokemonj2.Launcher;
import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.FontDrawer;
import io.pokemonj2.sfx.AudioManager;
import io.pokemonj2.sfx.Sounds;

public class WelcomeState extends State
{
	private float bgFadeIn, oakFadeIn, playerFadeIn;
	private boolean beginDialogue;
	private int welcomeStage, pictureStage;
	private String line1, line2;
	private int textDelay, nextDisplacement;
	private boolean nextIncreasing;

	private ArrayList<String> welcome1Text;
	private int textIndex1;

	private ArrayList<String> welcome2bText;
	private int textIndex2b;
	private ArrayList<String> welcome2gText;
	private int textIndex2g;
	private int genderSelIndex;
	
	private float transitionFade;

	public WelcomeState(Game game)
	{
		super(game);

		welcome1Text = new ArrayList<String>();
		welcome2bText = new ArrayList<String>();
		welcome2gText = new ArrayList<String>();

		textDelay = 0;

		bgFadeIn = 0.0f;
		oakFadeIn = 0.0f;
		playerFadeIn = 0.0f;
		transitionFade = 0.0f;
		beginDialogue = false;
		welcomeStage = -1;
		pictureStage = 0;

		nextDisplacement = 0;
		nextIncreasing = true;

		genderSelIndex = 0;

		line1 = "";
		line2 = "";

		textIndex1 = 0;
		textIndex2b = 0;
		textIndex2g = 0;

		try
		{
			InputStream fis1 = Launcher.class.getResourceAsStream("/dialogue/welcome1.txt");
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(fis1));

			String line = reader1.readLine();
			while (line != null)
			{
				welcome1Text.add(line);
				line = reader1.readLine();
			}

			InputStream fis2b = Launcher.class.getResourceAsStream("/dialogue/welcome2b.txt");
			BufferedReader reader2b = new BufferedReader(new InputStreamReader(fis2b));

			String line2b = reader2b.readLine();
			while (line2b != null)
			{
				welcome2bText.add(line2b);
				line2b = reader2b.readLine();
			}

			InputStream fis2g = Launcher.class.getResourceAsStream("/dialogue/welcome2g.txt");
			BufferedReader reader2g = new BufferedReader(new InputStreamReader(fis2g));

			String line2g = reader2g.readLine();
			while (line2g != null)
			{
				welcome2gText.add(line2g);
				line2g = reader2g.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		AudioManager.playMusic("/sfx/music/welcome-to.wav");
	}

	@Override
	public void tick()
	{
		textDelay--;
		if (textDelay < 0)
		{
			textDelay = 0;
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

		if (pictureStage == 0 && !beginDialogue)
		{
			if (bgFadeIn < 1.0f)
			{
				bgFadeIn += 0.03f;
			}
			else
			{
				if (oakFadeIn < 1.5f)
				{
					oakFadeIn += 0.03f;
				}
				else
				{
					beginDialogue = true;
					welcomeStage = 1;
				}
			}
		}
		else if (pictureStage == 1)
		{
			if (oakFadeIn > 0.0f)
			{
				oakFadeIn -= 0.02f;
				if (oakFadeIn < 0.0f)
				{
					oakFadeIn = 0.0f;
				}
			}
			else
			{
				if (playerFadeIn < 1.5f)
				{
					playerFadeIn += 0.02f;
				}
				else
				{
					if (welcomeStage == 3)
					{
						welcomeStage = 4;
					}
				}
			}
		}

		if (beginDialogue)
		{
			if (welcomeStage == 1)
			{
				if (game.getKeyManager().interact && textIndex1 < welcome1Text.size() - 1 && textDelay == 0)
				{
					line1 = welcome1Text.get(textIndex1);
					line2 = welcome1Text.get(textIndex1 + 1);
					textDelay = 30;
					textIndex1 += 1;
					AudioManager.playSound(Sounds.CONFIRM);
				}

				if (textIndex1 == welcome1Text.size() - 1)
				{
					welcomeStage = 2;
					textDelay = 60;
				}
			}
			else if (welcomeStage == 2)
			{
				if (game.getKeyManager().up && textDelay == 0)
				{
					genderSelIndex -= 1;
					if (genderSelIndex < 0)
					{
						genderSelIndex = 0;
					}
					textDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
				else if (game.getKeyManager().down && textDelay == 0)
				{
					genderSelIndex += 1;
					if (genderSelIndex > 1)
					{
						genderSelIndex = 1;
					}
					textDelay = 20;
					AudioManager.playSound(Sounds.CONFIRM);
				}
				else if (game.getKeyManager().interact && textDelay == 0)
				{
					AudioManager.playSound(Sounds.CONFIRM);
					line1 = "";
					line2 = "";
					
					game.setGender(genderSelIndex);
					if (game.getGender() == 0)
					{
						game.setName("RED");
					}
					else
					{
						game.setName("GREEN");
					}
					
					welcomeStage = 3;
					pictureStage = 1;
				}
			}
			else if (welcomeStage == 4)
			{
				if (genderSelIndex == 0) // BOY
				{
					if (game.getKeyManager().interact && textIndex2b < welcome2bText.size() - 1 && textDelay == 0)
					{
						line1 = welcome2bText.get(textIndex2b);
						line2 = welcome2bText.get(textIndex2b + 1);
						textDelay = 30;
						textIndex2b += 1;
						AudioManager.playSound(Sounds.CONFIRM);
					}
				}
				else if (genderSelIndex == 1) // GIRL
				{
					if (game.getKeyManager().interact && textIndex2g < welcome2gText.size() - 1 && textDelay == 0)
					{
						line1 = welcome2gText.get(textIndex2g);
						line2 = welcome2gText.get(textIndex2g + 1);
						textDelay = 30;
						textIndex2g += 1;
						AudioManager.playSound(Sounds.CONFIRM);
					}
				}

				if (textIndex2b == welcome2bText.size() - 1 || textIndex2g == welcome2gText.size() - 1)
				{
					welcomeStage = 5;
					textDelay = 60;
				}
			}
			else if (welcomeStage == 5)
			{
				if (transitionFade < 1.5f)
				{
					transitionFade += 0.01f;
				}
				else
				{
					State.setState(new PokemonSelectState(game));
				}
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());

		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, bgFadeIn)));
		g.drawImage(Assets.intro_bg, 0, 0, game.getWidth(), game.getHeight(), null);

		if (pictureStage == 0)
		{
			((Graphics2D) g)
					.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, oakFadeIn)));
			g.drawImage(Assets.oak, game.getWidth() / 2 - 100, game.getHeight() / 5, 200, 324, null);
		}
		else if (pictureStage == 1)
		{
			((Graphics2D) g)
					.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, oakFadeIn)));
			g.drawImage(Assets.oak, game.getWidth() / 2 - 100, game.getHeight() / 5, 200, 324, null); // OAK

			((Graphics2D) g)
					.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, playerFadeIn)));
			if (genderSelIndex == 0) // BOY
			{
				g.drawImage(Assets.trainer_boy, game.getWidth() / 2 - 109, game.getHeight() / 5 + 24, 218, 300, null);
			}
			else if (genderSelIndex == 1) // GIRL
			{
				g.drawImage(Assets.trainer_girl, game.getWidth() / 2 - 109, game.getHeight() / 5 + 24, 218, 300, null);
			}

			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // RESET ALPHA
		}

		if (beginDialogue)
		{
			if (welcomeStage == 1)
			{
				drawDialogueBox(g);
				FontDrawer.drawText(line1, 70, game.getHeight() - 150, 50, g);
				FontDrawer.drawText(line2, 70, game.getHeight() - 90, 50, g);
				if (textDelay == 0)
				{
					g.drawImage(Assets.next, game.getWidth() - 100, game.getHeight() - 60 - (nextDisplacement / 10), 30,
							20, null);
				}
			}
			else if (welcomeStage == 2)
			{
				drawDialogueBox(g);

				g.drawImage(Assets.tl, game.getWidth() - 300, game.getHeight() - 400, 40, 40, null);
				g.drawImage(Assets.t_ho, game.getWidth() - 260, game.getHeight() - 400, 200, 40, null);
				g.drawImage(Assets.tr, game.getWidth() - 60, game.getHeight() - 400, 40, 40, null);
				g.drawImage(Assets.l_ve, game.getWidth() - 300, game.getHeight() - 360, 40, 120, null);
				g.drawImage(Assets.r_ve, game.getWidth() - 60, game.getHeight() - 360, 40, 120, null);
				g.drawImage(Assets.bl, game.getWidth() - 300, game.getHeight() - 240, 40, 40, null);
				g.drawImage(Assets.br, game.getWidth() - 60, game.getHeight() - 240, 40, 40, null);
				g.drawImage(Assets.b_ho, game.getWidth() - 260, game.getHeight() - 240, 200, 40, null);
				g.drawImage(Assets.fill, game.getWidth() - 260, game.getHeight() - 360, 200, 120, null);

				FontDrawer.drawText(line1, 70, game.getHeight() - 150, 50, g);
				FontDrawer.drawText(line2, 70, game.getHeight() - 90, 50, g);

				FontDrawer.drawText("BOY", game.getWidth() - 210, game.getHeight() - 350, 50, g);
				FontDrawer.drawText("GIRL", game.getWidth() - 210, game.getHeight() - 290, 50, g);

				if (genderSelIndex == 0)
				{
					g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 345, 20, 40, null);
				}
				else
				{
					g.drawImage(Assets.selection, game.getWidth() - 250, game.getHeight() - 285, 20, 40, null);
				}
			}
			else if (welcomeStage == 4)
			{
				drawDialogueBox(g);

				FontDrawer.drawText(line1, 70, game.getHeight() - 150, 50, g);
				FontDrawer.drawText(line2, 70, game.getHeight() - 90, 50, g);
				if (textDelay == 0)
				{
					g.drawImage(Assets.next, game.getWidth() - 100, game.getHeight() - 60 - (nextDisplacement / 10), 30,
							20, null);
				}
			}
			else if (welcomeStage == 5)
			{
				g.setColor(Color.BLACK);
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1.0f, transitionFade)));
				g.fillRect(0, 0, game.getWidth(), game.getHeight());
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
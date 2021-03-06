package io.pokemonj2.gfx;

import io.pokemonj2.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ObjectDrawer
{
	/* for menus, etc. */
	public static void drawBigText(String text, int x, int y, int height, Graphics g)
	{
		if (!text.equals(""))
		{
			double heightRatio = (double) height / 13.0;
			int x_index = x;
			char[] chars = text.toCharArray();
			for (char c : chars)
			{
				if ((int) c < 256 && Assets.charSet[(int) c] != null) // error check c
				{
					int widthCalc = (int) (heightRatio * Assets.charSet[(int) c].getWidth());
					g.drawImage(Assets.charSet[(int) c], x_index, y, widthCalc, height, null);
					x_index += widthCalc;
				}
				else
				{
					System.out.println((int) c + " corresponding to char " + c + " is out of bounds or null!");
				}
			}
		}
	}

	/* for menus, etc. */
	public static void drawBigWhiteText(String text, int x, int y, int height, Graphics g)
	{
		if (!text.equals(""))
		{
			double heightRatio = (double) height / 13.0;
			int x_index = x;
			char[] chars = text.toCharArray();
			for (char c : chars)
			{
				if (Assets.whiteCharSet[(int) c] != null)
				{
					int widthCalc = (int) (heightRatio * Assets.whiteCharSet[(int) c].getWidth());
					g.drawImage(Assets.whiteCharSet[(int) c], x_index, y, widthCalc, height, null);
					x_index += widthCalc;
				}
				else
				{
					System.out.println((int) c + " is null!");
				}
			}
		}
	}

	/* for in-battle stats */
	public static void drawSmallText(String text, int x, int y, int height, Graphics g)
	{
		if (!text.equals(""))
		{
			double heightRatio = height / 10.0;
			int x_index = x;
			char[] chars = text.toCharArray();
			for (char c : chars)
			{
				if (Assets.smallCharSet[(int) c] != null)
				{
					int widthCalc = (int) (heightRatio * Assets.smallCharSet[(int) c].getWidth());
					g.drawImage(Assets.smallCharSet[(int) c], x_index, y, widthCalc, height, null);
					x_index += widthCalc;
				}
				else
				{
					System.out.println((int) c + " is null!");
				}
			}
		}
	}

	public static void drawHpBar(double pct, int x, int y, int height, Graphics g)
	{
		/*
		 * 51-100% GREEN
		 * 21-50% YELLOW
		 * 1-20% RED
		 * 0% GRAY
		 *
		 * HP BAR 48px WIDE
		 * 3px TALL
		 */

		double heightRatio = (double) height / 3.0;
		boolean fractionRemainder = pct % 1.0 > 0.0;
		int pxToShow = (int) (48 * pct);
		if (fractionRemainder)
		{
			pxToShow += 1;
		}

		BufferedImage colorToUse = Assets.greenBar;
		if (pct < 0.5 && pct > 0.2)
		{
			colorToUse = Assets.yellowBar;
		}
		else if (pct < 0.2 && pct > 0.0)
		{
			colorToUse = Assets.redBar;
		}

		g.drawImage(Assets.grayBar, x, y, (int) (48 * heightRatio), height, null); // gray under
		if (pct > 0)
		{
			g.drawImage(colorToUse, x, y, (int) (pxToShow * heightRatio), height, null); // bar overlay
		}
	}

	public static void drawHpBar(double pct, int x, int y, int width, int height, Graphics g)
	{
		/*
		 * 51-100% GREEN
		 * 21-50% YELLOW
		 * 1-20% RED
		 * 0% GRAY
		 */

		boolean fractionRemainder = pct % 1.0 > 0.0;
		int pxToShow = (int) (48 * pct); // snap to pixels
		if (fractionRemainder)
		{
			pxToShow += 1;
		}

		double widthFraction = pxToShow / 48.0;

		BufferedImage colorToUse = Assets.greenBar;
		if (pct < 0.5 && pct > 0.2)
		{
			colorToUse = Assets.yellowBar;
		}
		else if (pct < 0.2 && pct > 0.0)
		{
			colorToUse = Assets.redBar;
		}

		g.drawImage(Assets.grayBar, x, y, width, height, null); // gray under
		if (pct > 0)
		{
			g.drawImage(colorToUse, x, y, (int) (widthFraction * width), height, null); // bar overlay
		}
	}

	public static void drawDialogueBox(Game game, Graphics g)
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

	public static void drawBattleBox(Game game, Graphics g)
	{
		g.drawImage(Assets.battle_tl, 0, game.getHeight() - 200, 30, 30, null);
		g.drawImage(Assets.battle_t_ho, 30, game.getHeight() - 200, game.getWidth() - 60, 30, null);
		g.drawImage(Assets.battle_tr, game.getWidth() - 30, game.getHeight() - 200, 30, 30, null);
		g.drawImage(Assets.battle_l_ve, 0, game.getHeight() - 170, 30, 140, null);
		g.drawImage(Assets.battle_r_ve, game.getWidth() - 30, game.getHeight() - 170, 30, 140, null);
		g.drawImage(Assets.battle_bl, 0, game.getHeight() - 30, 30, 30, null);
		g.drawImage(Assets.battle_br, game.getWidth() - 30, game.getHeight() - 30, 30, 30, null);
		g.drawImage(Assets.battle_b_ho, 30, game.getHeight() - 30, game.getWidth() - 60, 30, null);
		g.drawImage(Assets.battle_fill, 30, game.getHeight() - 170, game.getWidth() - 60, 140, null);
	}
}
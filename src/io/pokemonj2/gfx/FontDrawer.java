package io.pokemonj2.gfx;

import java.awt.Graphics;

public class FontDrawer
{
	public static void drawText(String text, int x, int y, int height, Graphics g)
	{
		if (!text.equals(""))
		{
			double heightRatio = (double) height / 13.0;
//			int bwCharSpace = height / 13;
			int x_index = x;
			char[] chars = text.toCharArray();
			for (char c : chars)
			{
				if (Assets.charSet[(int) c] != null)
				{
					int widthCalc = (int) (heightRatio * Assets.charSet[(int) c].getWidth());
					g.drawImage(Assets.charSet[(int) c], x_index, y, widthCalc, height, null);
					x_index += widthCalc;
				}
				else
				{
					System.out.println((int) c + " is null!");
				}
			}
		}
	}
}
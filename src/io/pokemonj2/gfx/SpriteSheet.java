package io.pokemonj2.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet)
	{
		BufferedImage convertedImg = new BufferedImage(sheet.getWidth(), sheet.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    convertedImg.getGraphics().drawImage(sheet, 0, 0, null);
	    convertedImg.getGraphics().dispose();
	    this.sheet = convertedImg;
	}

	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	public void replaceRGBWithTransparent(int r, int g, int b)
	{
		int rgb = new Color(r, g, b).getRGB();
		for (int i = 0; i < sheet.getWidth(); i++)
		{
			for (int j = 0; j < sheet.getHeight(); j++)
			{
				if (sheet.getRGB(i, j) == rgb)
				{
					sheet.setRGB(i, j, new Color(1.0f, 1.0f, 1.0f, 0.0f).getRGB());
				}
			}
		}
	}
}
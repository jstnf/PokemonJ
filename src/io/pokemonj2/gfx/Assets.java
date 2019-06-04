package io.pokemonj2.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import io.pokemonj2.Game;

public class Assets
{
	public static BufferedImage scene;
	public static ArrayList<BufferedImage> battleBackSprite, battleFrontSprite;
	public static ArrayList<Integer> battleBackPixel;

	public static void init()
	{
		battleBackSprite = new ArrayList<BufferedImage>();
		battleBackPixel = new ArrayList<Integer>();

		SpriteSheet frontSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-back-sprites.png"));
		scene = ImageLoader.loadImage("/textures/battle-grass.png");

		int widthIndex = 0;
		int heightIndex = 0;
		for (int i = 0; i < Game.NUM_OF_POKEMON; i++)
		{
			BufferedImage sprite = frontSprites.crop(widthIndex * 64, heightIndex * 64, 64, 64);
			int pixelTransparencyIndex = 63;
			while (isRowTransparent(sprite, pixelTransparencyIndex) && pixelTransparencyIndex > 0)
			{
				pixelTransparencyIndex -= 1;
			}
			
			System.out.println(i + " @ " + pixelTransparencyIndex);
			battleBackSprite.add(sprite);
			battleBackPixel.add(pixelTransparencyIndex);

			widthIndex += 1;

			if (widthIndex > 24)
			{
				widthIndex = 0;
				heightIndex += 1;
			}
		}
	}

	private static boolean isRowTransparent(BufferedImage img, int y)
	{
		int widthCheckIndex = 0;
		while (widthCheckIndex < img.getWidth())
		{
			int pixel = img.getRGB(widthCheckIndex, y);
			if ((pixel >> 24) != 0x00)
			{
				return false;
			}
			
			widthCheckIndex += 1;
		}
		return true;
	}
}
package io.pokemonj2.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import io.pokemonj2.Game;

public class Assets
{
	public static BufferedImage scene;
	public static ArrayList<BufferedImage> battleBackSprite, battleFrontSprite;
	public static ArrayList<Integer> battleBackPixel, battleFrontPixel;

	public static void init()
	{
		battleBackSprite = new ArrayList<BufferedImage>();
		battleBackPixel = new ArrayList<Integer>();
		battleFrontSprite = new ArrayList<BufferedImage>();
		battleFrontPixel = new ArrayList<Integer>();

		SpriteSheet backSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-back-sprites.png"));
		SpriteSheet frontSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-front-sprites.png"));
		scene = ImageLoader.loadImage("/textures/battle-grass.png");

		int widthIndex = 0;
		int heightIndex = 0;
		for (int i = 0; i < Game.NUM_OF_POKEMON; i++)
		{
			/* Load images */
			BufferedImage back = backSprites.crop(widthIndex * 64, heightIndex * 64, 64, 64);
			BufferedImage front = frontSprites.crop(widthIndex * 64, heightIndex * 64, 64, 64);
			
			/* Determining transparency for back sprites */
			int back_pixelIndex = 63;
			int front_pixelIndex = 63;
			while (isRowTransparent(back, back_pixelIndex) && back_pixelIndex > 0)
			{
				back_pixelIndex -= 1;
			}
			while (isRowTransparent(front, front_pixelIndex) && front_pixelIndex > 0)
			{
				front_pixelIndex -= 1;
			}
			
			/* Add to lists */
			battleBackSprite.add(back);
			battleBackPixel.add(back_pixelIndex);
			battleFrontSprite.add(front);
			battleFrontPixel.add(front_pixelIndex);

			/* Change index */
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
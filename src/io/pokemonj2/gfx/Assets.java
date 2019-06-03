package io.pokemonj2.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	public static BufferedImage scene;
	
	public static void init()
	{
		SpriteSheet frontSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-front-sprites.png"));
		scene = ImageLoader.loadImage("/textures/battle-grass.png");
	}
}
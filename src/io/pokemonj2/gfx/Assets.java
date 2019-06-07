package io.pokemonj2.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import io.pokemonj2.Game;

public class Assets
{
	/* Title Screen Assets */
	public static BufferedImage titleScreen, pressSpace;
	
	/* Frames */
	public static BufferedImage tr, tl, t_ho, b_ho, l_ve, r_ve, br, bl, fill;
	
	/* Font */
	public static BufferedImage[] charSet;
	
	/* Oak and Intro*/
	public static BufferedImage oak;
	public static BufferedImage intro_bg;
	
	/* Battle Assets */
	public static BufferedImage battle_scene_grass;
	public static ArrayList<BufferedImage> battleBackSprite, battleFrontSprite;
	public static ArrayList<Integer> battleBackPixel, battleFrontPixel;

	public static void init()
	{
		// INIT VARS
		battleBackSprite = new ArrayList<BufferedImage>();
		battleBackPixel = new ArrayList<Integer>();
		battleFrontSprite = new ArrayList<BufferedImage>();
		battleFrontPixel = new ArrayList<Integer>();
		charSet = new BufferedImage[128];
		
		/* Title Screen */
		titleScreen = ImageLoader.loadImage("/textures/title/title-screen.png");
		pressSpace = ImageLoader.loadImage("/textures/title/press-space.png");
		
		/* Font */
		SpriteSheet font = new SpriteSheet(ImageLoader.loadImage("/textures/font.png"));
		font.replaceRGBWithTransparent(255, 255, 255);
		charSet[32] = font.crop(301, 0, 4, 13); // SPACE
		charSet[33] = font.crop(395, 43, 4, 13); // !
		charSet[44] = font.crop(306, 58, 5, 13); // ,
		charSet[45] = font.crop(410, 43, 6, 13); // -
		charSet[46] = font.crop(406, 43, 5, 13); // .
		int numStartIndex = 334;
		for (int i = 48; i < 58; i++)
		{
			charSet[i] = font.crop(numStartIndex, 43, 6, 13); // 0 - 9
			numStartIndex += 6;
		}
		int capLetterStartIndex = 326;
		for (int i = 65; i < 91; i++)
		{
			charSet[i] = font.crop(capLetterStartIndex, 57, 6, 13); // A - Z
			capLetterStartIndex += 6;
		}
		int a_eStartIndex = 262;
		for (int i = 97; i < 102; i++)
		{
			charSet[i] = font.crop(a_eStartIndex, 71, 6, 13); // a - e
			a_eStartIndex += 6;
		}
		charSet[102] = font.crop(292, 71, 5, 13); // f
		charSet[103] = font.crop(297, 71, 6, 13); // g
		charSet[104] = font.crop(303, 71, 6, 13); // h
		charSet[105] = font.crop(309, 71, 4, 13); // i
		charSet[106] = font.crop(313, 71, 6, 13); // j
		charSet[107] = font.crop(319, 71, 6, 13); // k
		charSet[108] = font.crop(325, 71, 5, 13); // l
		charSet[109] = font.crop(329, 71, 6, 13); // m
		charSet[110] = font.crop(335, 71, 5, 13); // n
		int o_qStartIndex = 340;
		for (int i = 111; i < 114; i++)
		{
			charSet[i] = font.crop(o_qStartIndex, 71, 6, 13); // o - q
			o_qStartIndex += 6;
		}
		int r_tStartIndex = 358;
		for (int i = 114; i < 117; i++)
		{
			charSet[i] = font.crop(r_tStartIndex, 71, 5, 13); // r - t
			r_tStartIndex += 5;
		}
		int u_xStartIndex = 373;
		for (int i = 117; i < 121; i++)
		{
			charSet[i] = font.crop(u_xStartIndex, 71, 6, 13); // u - x
			u_xStartIndex += 6;
		}
		charSet[121] = font.crop(397, 71, 7, 13); // y
		charSet[122] = font.crop(404, 71, 6, 13); // z
		
		/* Oak */
		SpriteSheet oakSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/prof-oak.png"));
		oakSheet.replaceRGBWithTransparent(0, 128, 128);
		oak = oakSheet.crop(260, 4, 54, 85);
		SpriteSheet introSheet = new SpriteSheet(ImageLoader.loadImage("/textures/intro-screens.png"));
		intro_bg = introSheet.crop(5, 5, 239, 159);
		
		/* Battle Icons */
		SpriteSheet battleIcons = new SpriteSheet(ImageLoader.loadImage("/textures/battle-icon.png"));
		
		/* Menu Frames */
		SpriteSheet menuFrames = new SpriteSheet(ImageLoader.loadImage("/textures/menu-frames.png"));
		menuFrames.replaceRGBWithTransparent(144, 144, 96);
		tl = menuFrames.crop(4, 17, 8, 8);
		tr = menuFrames.crop(22, 17, 8, 8);
		bl = menuFrames.crop(4, 35, 8, 8);
		br = menuFrames.crop(22, 35, 8, 8);
		t_ho = menuFrames.crop(13, 17, 8, 8);
		b_ho = menuFrames.crop(13, 35, 8, 8);
		l_ve = menuFrames.crop(4, 26, 8, 8);
		r_ve = menuFrames.crop(22, 26, 8, 8);
		fill = menuFrames.crop(13, 26, 8, 8);

		/* Battle Sprites */
		SpriteSheet backSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-back-sprites.png"));
		SpriteSheet frontSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/battle-front-sprites.png"));
		battle_scene_grass = ImageLoader.loadImage("/textures/battle-grass.png");

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
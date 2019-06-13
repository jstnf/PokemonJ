package io.pokemonj2.gfx;

import io.pokemonj2.Game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Assets
{
	/* Title Screen Assets */
	public static BufferedImage titleScreen, pressSpace;

	/* Frames */
	public static BufferedImage tr, tl, t_ho, b_ho, l_ve, r_ve, br, bl, fill;
	public static BufferedImage red_tr, red_tl, red_t_ho, red_b_ho, red_l_ve, red_r_ve, red_br, red_bl, red_fill;
	public static BufferedImage blue_tr, blue_tl, blue_t_ho, blue_b_ho, blue_l_ve, blue_r_ve, blue_br, blue_bl, blue_fill;
	public static BufferedImage battle_tr, battle_tl, battle_t_ho, battle_b_ho, battle_l_ve, battle_r_ve, battle_br, battle_bl, battle_fill;
	public static BufferedImage mainBattleSelection, moveSelection;

	/* OutOfBattleMenu */
	public static BufferedImage oob1;
	public static BufferedImage pokeballIcon;
	public static BufferedImage hpBar;

	/* Font */
	public static BufferedImage[] charSet, whiteCharSet, smallCharSet;

	/* Oak and Intro */
	public static BufferedImage oak;
	public static BufferedImage intro_bg;
	public static BufferedImage table;

	/* Battle Assets */
	public static BufferedImage battle_scene_grass;
	public static BufferedImage battle_scene1;
	public static BufferedImage battle_scene1_circle1; // far circle
	public static BufferedImage battle_scene1_circle2; // close circle
	public static BufferedImage oppStatus, myStatus;
	public static BufferedImage greenBar, yellowBar, redBar, grayBar;

	/* Characters */
	public static BufferedImage trainer_boy, trainer_girl;
	public static BufferedImage[] throwFrames_boy, throwFrames_girl;
	public static BufferedImage[] trainerClasses;
	public static String[][] trainerClassInfo;
	public static String[] mainNames, boyNames, girlNames;
	public static ArrayList<BufferedImage> battleBackSprite, battleFrontSprite; // (pokemon)
	public static ArrayList<Integer> battleBackPixel, battleFrontPixel; // (pokemon)

	/* Misc */
	public static BufferedImage next;
	public static BufferedImage selection;

	public static void init()
	{
		// INIT VARS
		battleBackSprite = new ArrayList<BufferedImage>();
		battleBackPixel = new ArrayList<Integer>();
		battleFrontSprite = new ArrayList<BufferedImage>();
		battleFrontPixel = new ArrayList<Integer>();
		throwFrames_boy = new BufferedImage[5];
		throwFrames_girl = new BufferedImage[5];
		charSet = new BufferedImage[256];
		whiteCharSet = new BufferedImage[256];
		smallCharSet = new BufferedImage[256];
		trainerClasses = new BufferedImage[104];
		trainerClassInfo = new String[104][4];
		mainNames = new String[34];
		boyNames = new String[1000];
		girlNames = new String[1000];

		/* Title Screen */
		titleScreen = ImageLoader.loadImage("/textures/title/title-screen.png");
		pressSpace = ImageLoader.loadImage("/textures/title/press-space.png");

		/* Font */
		// bug, letters are actually 14 letters tall, need to subtract y coord of all by 1 and increasing crop height from 13 to 14
		// BLACK FONT
		SpriteSheet font = new SpriteSheet(ImageLoader.loadImage("/textures/font.png"));
		font.replaceRGBWithTransparent(255, 255, 255);
		charSet[32] = font.crop(301, 0, 4, 13); // SPACE
		charSet[33] = font.crop(395, 43, 4, 13); // !
		charSet[36] = font.crop(298, 58, 8, 13); // $ - shows up as PokeDollars sign
		charSet[44] = font.crop(306, 58, 5, 13); // ,
		charSet[45] = font.crop(410, 43, 6, 13); // -
		charSet[46] = font.crop(406, 43, 5, 13); // .
		int numStartIndex = 334;
		for (int i = 48; i < 58; i++)
		{
			charSet[i] = font.crop(numStartIndex, 43, 6, 13); // 0 - 9
			numStartIndex += 6;
		}
		charSet[63] = font.crop(400, 43, 5, 13); // ?
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
		charSet[108] = font.crop(324, 71, 5, 13); // l
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
		charSet[123] = font.crop(379, 29, 8, 13); // { - shows up as PK
		charSet[125] = font.crop(387, 29, 8, 13); // } - shows up as MN
		charSet[130] = font.crop(445, 14, 6, 13); // é
		charSet[233] = font.crop(445, 14, 6, 13); // é

		// WHITE FONT
		font.replaceRGBWithTransparent(0, 0, 0);
		whiteCharSet[32] = font.crop(301, 0, 4, 13); // SPACE
		whiteCharSet[33] = font.crop(133, 43, 4, 13); // !
		whiteCharSet[36] = font.crop(36, 58, 8, 13); // $ - shows up as PokeDollars sign
		whiteCharSet[44] = font.crop(44, 58, 5, 13); // ,
		whiteCharSet[45] = font.crop(148, 43, 6, 13); // -
		whiteCharSet[46] = font.crop(144, 43, 5, 13); // .
		int w_numStartIndex = 72;
		for (int i = 48; i < 58; i++)
		{
			whiteCharSet[i] = font.crop(w_numStartIndex, 43, 6, 13); // 0 - 9
			w_numStartIndex += 6;
		}
		whiteCharSet[63] = font.crop(138, 43, 5, 13); // ?
		int w_capLetterStartIndex = 64;
		for (int i = 65; i < 91; i++)
		{
			whiteCharSet[i] = font.crop(w_capLetterStartIndex, 57, 6, 13); // A - Z
			w_capLetterStartIndex += 6;
		}
		int w_a_eStartIndex = 0;
		for (int i = 97; i < 102; i++)
		{
			whiteCharSet[i] = font.crop(w_a_eStartIndex, 71, 6, 13); // a - e
			w_a_eStartIndex += 6;
		}
		whiteCharSet[102] = font.crop(30, 71, 5, 13); // f
		whiteCharSet[103] = font.crop(35, 71, 6, 13); // g
		whiteCharSet[104] = font.crop(41, 71, 6, 13); // h
		whiteCharSet[105] = font.crop(47, 71, 4, 13); // i
		whiteCharSet[106] = font.crop(51, 71, 6, 13); // j
		whiteCharSet[107] = font.crop(57, 71, 6, 13); // k
		whiteCharSet[108] = font.crop(62, 71, 5, 13); // l
		whiteCharSet[109] = font.crop(67, 71, 6, 13); // m
		whiteCharSet[110] = font.crop(73, 71, 5, 13); // n
		int w_o_qStartIndex = 78;
		for (int i = 111; i < 114; i++)
		{
			whiteCharSet[i] = font.crop(w_o_qStartIndex, 71, 6, 13); // o - q
			w_o_qStartIndex += 6;
		}
		int w_r_tStartIndex = 96;
		for (int i = 114; i < 117; i++)
		{
			whiteCharSet[i] = font.crop(w_r_tStartIndex, 71, 5, 13); // r - t
			w_r_tStartIndex += 5;
		}
		int w_u_xStartIndex = 111;
		for (int i = 117; i < 121; i++)
		{
			whiteCharSet[i] = font.crop(w_u_xStartIndex, 71, 6, 13); // u - x
			w_u_xStartIndex += 6;
		}
		whiteCharSet[121] = font.crop(135, 71, 7, 13); // y
		whiteCharSet[122] = font.crop(142, 71, 6, 13); // z
		whiteCharSet[123] = font.crop(117, 29, 8, 13); // { - shows up as PK
		whiteCharSet[125] = font.crop(125, 29, 8, 13); // } - shows up as MN
		whiteCharSet[130] = font.crop(182, 14, 6, 13); // é
		whiteCharSet[233] = font.crop(182, 14, 6, 13); // é

		/* Oak */
		SpriteSheet oakSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/prof-oak.png"));
		oakSheet.replaceRGBWithTransparent(0, 128, 128);
		oak = oakSheet.crop(260, 4, 55, 86);
		table = oakSheet.crop(119, 58, 68, 49);
		SpriteSheet introSheet = new SpriteSheet(ImageLoader.loadImage("/textures/intro-screens.png"));
		intro_bg = introSheet.crop(5, 5, 239, 159);

		/* Battle Icons */
		SpriteSheet battleIcons = new SpriteSheet(ImageLoader.loadImage("/textures/battle-icon.png"));
		battleIcons.replaceRGBWithTransparent(255, 255, 255);
		next = battleIcons.crop(544, 59, 10, 6);
		selection = battleIcons.crop(269, 4, 5, 10);
		oppStatus = battleIcons.crop(3, 3, 100, 29);
		myStatus = battleIcons.crop(3, 44, 104, 37);
		greenBar = battleIcons.crop(117, 9, 1, 3);
		yellowBar = battleIcons.crop(117, 13, 1, 3);
		redBar = battleIcons.crop(117, 17, 1, 3);
		grayBar = battleIcons.crop(117, 21, 1, 3);

		/* Battle Scene */
		SpriteSheet scene_noCircles = new SpriteSheet(ImageLoader.loadImage("/textures/battle-scenes.png"));
		battle_scene1 = scene_noCircles.crop(6, 6, 240, 112);
		SpriteSheet scene_circles = new SpriteSheet(ImageLoader.loadImage("/textures/battle-scenes-with-circles.png"));
		scene_circles.replaceRGBWithTransparent(224, 224, 224);
		scene_circles.replaceRGBWithTransparent(232, 232, 232);
		battle_scene1_circle1 = scene_circles.crop(118, 54, 128, 32);
		battle_scene1_circle2 = scene_circles.crop(10, 107, 120, 11);

		/* OutOfBattle Menu */
		SpriteSheet pkmnCenter = new SpriteSheet(ImageLoader.loadImage("/textures/pkmn-center.png"));
		oob1 = pkmnCenter.crop(35, 15, 181, 131);
		pokeballIcon = ImageLoader.loadImage("/textures/pokeball-icon-highres.png");
		hpBar = battleIcons.crop(35, 59, 66, 7);

		/* Characters */
		SpriteSheet trainerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/trainer-sprites.png"));
		trainer_boy = trainerSprites.crop(167, 18, 160, 220);
		trainer_girl = trainerSprites.crop(3, 10, 160, 220);
		SpriteSheet throwSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/trainer-backs.png"));
		throwSprites.replaceRGBWithTransparent(128, 120, 160);
		// BOY
		throwFrames_boy[0] = throwSprites.crop(8, 8, 64, 64);
		throwFrames_boy[1] = throwSprites.crop(80, 8, 64, 64);
		throwFrames_boy[2] = throwSprites.crop(152, 8, 64, 64);
		throwFrames_boy[3] = throwSprites.crop(224, 8, 64, 64);
		throwFrames_boy[4] = throwSprites.crop(296, 8, 64, 64);
		// GIRL
		throwFrames_girl[0] = throwSprites.crop(8, 80, 64, 64);
		throwFrames_girl[1] = throwSprites.crop(80, 80, 64, 64);
		throwFrames_girl[2] = throwSprites.crop(152, 80, 64, 64);
		throwFrames_girl[3] = throwSprites.crop(224, 80, 64, 64);
		throwFrames_girl[4] = throwSprites.crop(296, 80, 64, 64);
		SpriteSheet classes = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/trainer-classes.png"));
		classes.replaceRGBWithTransparent(112, 192, 160);
		classes.replaceRGBWithTransparent(112, 168, 64);
		int classes_horizIndex = 0;
		int classes_vertIndex = 0;
		for (int i = 0; i < trainerClasses.length; i++)
		{
			int classes_x = classes_horizIndex + 1 + (classes_horizIndex * 64);
			int classes_y = classes_vertIndex + 1 + (classes_vertIndex * 64);
			trainerClasses[i] = classes.crop(classes_x, classes_y, 64, 64);
			classes_horizIndex += 1;
			if (classes_horizIndex > 9)
			{
				classes_horizIndex = 0;
				classes_vertIndex += 1;
			}
		}
		try
		{
			BufferedReader classInfo_reader = new BufferedReader(
					new InputStreamReader(Assets.class.getResourceAsStream("/data/trainer-classes.txt")));
			String classInfo_line = classInfo_reader.readLine();
			int classInfo_index = 0;
			while (classInfo_line != null)
			{
				String[] parts = classInfo_line.split(",");
				for (int j = 0; j < parts.length; j++)
				{
					trainerClassInfo[classInfo_index][j] = parts[j];
				}
				classInfo_index++;
				classInfo_line = classInfo_reader.readLine();
			}

			BufferedReader mainName_reader = new BufferedReader(
					new InputStreamReader(Assets.class.getResourceAsStream("/data/main-names.txt")));
			String mainName_line = mainName_reader.readLine();
			int mainName_index = 0;
			while (mainName_line != null)
			{
				mainNames[mainName_index] = mainName_line;
				mainName_index++;
				mainName_line = mainName_reader.readLine();
			}

			BufferedReader boyName_reader = new BufferedReader(
					new InputStreamReader(Assets.class.getResourceAsStream("/data/boy-names.txt")));
			String boyName_line = boyName_reader.readLine();
			int boyName_index = 0;
			while (boyName_line != null)
			{
				boyNames[boyName_index] = boyName_line;
				boyName_index++;
				boyName_line = boyName_reader.readLine();
			}

			BufferedReader girlName_reader = new BufferedReader(
					new InputStreamReader(Assets.class.getResourceAsStream("/data/girl-names.txt")));
			String girlName_line = girlName_reader.readLine();
			int girlName_index = 0;
			while (girlName_line != null)
			{
				girlNames[girlName_index] = girlName_line;
				girlName_index++;
				girlName_line = girlName_reader.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		smallCharSet[32] = battleIcons.crop(244, 171, 4, 10); // SPACE
		smallCharSet[33] = battleIcons.crop(171, 174, 2, 10); // !
		smallCharSet[47] = battleIcons.crop(197, 174, 5, 10); // /
		int s_0_9StartIndex = 171;
		for (int i = 48; i < 58; i++)
		{
			smallCharSet[i] = battleIcons.crop(s_0_9StartIndex, 157, 5, 10); // 0 - 9
			s_0_9StartIndex += 7;
		}
		int s_A_ZStartIndex = 171;
		for (int i = 65; i < 91; i++)
		{
			smallCharSet[i] = battleIcons.crop(s_A_ZStartIndex, 122, 5, 10); // A - Z
			s_A_ZStartIndex += 7;
		}
		int s_a_hStartIndex = 171;
		for (int i = 97; i < 105; i++)
		{
			smallCharSet[i] = battleIcons.crop(s_a_hStartIndex, 141, 5, 10); // a - h
			s_a_hStartIndex += 7;
		}
		smallCharSet[105] = battleIcons.crop(228, 141, 2, 10); // i
		int s_j_zStartIndex = 232;
		for (int i = 106; i < 123; i++)
		{
			smallCharSet[i] = battleIcons.crop(s_j_zStartIndex, 141, 5, 10); // j - z
			s_j_zStartIndex += 7;
		}

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

		menuFrames.replaceRGBWithTransparent(112, 200, 160);
		red_tl = menuFrames.crop(64, 98, 8, 8);
		red_tr = menuFrames.crop(82, 98, 8, 8);
		red_bl = menuFrames.crop(64, 116, 8, 8);
		red_br = menuFrames.crop(82, 116, 8, 8);
		red_t_ho = menuFrames.crop(73, 98, 8, 8);
		red_b_ho = menuFrames.crop(73, 116, 8, 8);
		red_l_ve = menuFrames.crop(64, 107, 8, 8);
		red_r_ve = menuFrames.crop(82, 107, 8, 8);
		red_fill = menuFrames.crop(73, 107, 8, 8);

		blue_tl = menuFrames.crop(124, 98, 8, 8);
		blue_tr = menuFrames.crop(142, 98, 8, 8);
		blue_bl = menuFrames.crop(124, 116, 8, 8);
		blue_br = menuFrames.crop(142, 116, 8, 8);
		blue_t_ho = menuFrames.crop(133, 98, 8, 8);
		blue_b_ho = menuFrames.crop(133, 116, 8, 8);
		blue_l_ve = menuFrames.crop(124, 107, 8, 8);
		blue_r_ve = menuFrames.crop(142, 107, 8, 8);
		blue_fill = menuFrames.crop(133, 107, 8, 8);

		battle_tl = battleIcons.crop(297, 56, 8, 7);
		battle_tr = battleIcons.crop(529, 56, 8, 7);
		battle_bl = battleIcons.crop(297, 97, 8, 7);
		battle_br = battleIcons.crop(529, 97, 8, 7);
		battle_t_ho = battleIcons.crop(305, 56, 1, 7);
		battle_b_ho = battleIcons.crop(305, 97, 1, 7);
		battle_l_ve = battleIcons.crop(297, 63, 8, 1);
		battle_r_ve = battleIcons.crop(529, 63, 8, 1);
		battle_fill = battleIcons.crop(305, 63, 1, 1);

		mainBattleSelection = battleIcons.crop(146, 4, 120, 48);
		moveSelection = battleIcons.crop(297, 4, 240, 48);

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

			/* Determining transparency row for back sprites */
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
package io.pokemonj2;

public class Launcher
{
	public static void main(String[] args)
	{
		Game game = new Game("Pokémon Java Version", 960, 640); // 960 x 540 // 720 x 480
		game.start();
		
		for (int i = 0; i < 151; i++)
		{
			System.out.println("(" + i + ", \"\"),");
		}
	}
}
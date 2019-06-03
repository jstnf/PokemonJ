package io.pokemonj2;

public class Launcher
{
	public static void main(String[] args)
	{
		Game game = new Game("Pokémon Java Version", 960, 540); // 960 x 540 // 720 x 480
		game.start();
	}
}
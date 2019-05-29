package io.pokemonj;

public class Game
{
	Window window;

	private static final int WIDTH = 960, HEIGHT = 540;
	private static final String TITLE = "Pokemon Java Version";

	public void run()
	{
		init();
		runGameLoop();
		exit();
	}

	private void init()
	{
		createWindow();
	}

	private void createWindow()
	{
		window = new Window(WIDTH, HEIGHT, TITLE);
		window.create();
	}

	private void runGameLoop()
	{
		while (!window.isCloseRequested())
		{
			update();
			window.swapBuffers();
		}
	}

	private void update()
	{
		window.update();
	}

	private void exit()
	{
		window.stop();
	}
}
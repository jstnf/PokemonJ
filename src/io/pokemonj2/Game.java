package io.pokemonj2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import io.pokemonj2.gfx.Assets;
import io.pokemonj2.gfx.Display;
import io.pokemonj2.input.KeyManager;
import io.pokemonj2.states.State;
import io.pokemonj2.states.TitleState;

public class Game implements Runnable
{
	public static final int NUM_OF_POKEMON = 151;

	private int width, height;
	private String title;
	private boolean running;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private Display display;
	private KeyManager keyManager;
	
	private int gender;
	private String name;

	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;

		keyManager = new KeyManager();
	}

	public void run()
	{
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000)
			{
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		TitleState title = new TitleState(this);
		State.setState(title);
	}

	private void tick()
	{
		keyManager.tick();

		if (State.getState() != null)
			State.getState().tick();
	}

	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		// clear screen

		g.clearRect(0, 0, width, height);

		// draw here

		if (State.getState() != null)
			State.getState().render(g);

		// end drawing
		bs.show();
		g.dispose();
	}

	public synchronized void start()
	{
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop()
	{
		if (!running)
			return;
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public void setGender(int gender)
	{
		this.gender = gender;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getGender()
	{
		return gender;
	}
	
	public String getName()
	{
		return name;
	}
}
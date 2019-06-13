package io.pokemonj2.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean interact, back;
	public boolean space;

	public KeyManager()
	{
		keys = new boolean[256];
	}

	public void tick()
	{
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		interact = keys[KeyEvent.VK_Z];
		back = keys[KeyEvent.VK_X];
		space = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
}
package io.pokemonj2.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener
{
	@Override
	public void mousePressed(MouseEvent e)
	{
		System.out.println("x: " + e.getX() + " y: " + e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("x: " + e.getX() + " y: " + e.getY());
	}
}
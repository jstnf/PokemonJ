package io.pokemonj2.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager
{
	private static Clip loop = null;

	public static void playMusic(String file)
	{
		try
		{
			loop = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(file));
	        loop.open(inputStream);
	        loop.loop(-1); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void stopMusic()
	{
		if (loop == null)
		{
			return;
		}
		else
		{
			loop.stop();
		}
	}

	public static void playSound(String file)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(file));
	        clip.open(inputStream);
	        clip.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void playSound(Sounds sound)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(sound.toString()));
	        clip.open(inputStream);
	        clip.start(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
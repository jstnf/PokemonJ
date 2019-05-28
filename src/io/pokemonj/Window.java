package io.pokemonj;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import io.pokemonj.gfx.Texture;

public class Window
{
	private int width, height;
	private String title;
	private long window;
	private boolean isFullScreen;
	
	Texture tex;
	
	public Window(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public void create()
	{
		if (!glfwInit())
		{
			System.err.println("Failed to initialize GLFW.");
			System.exit(-1);
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		if (isFullScreen)
		{
			window = glfwCreateWindow(videoMode.width(), videoMode.height(), title, glfwGetPrimaryMonitor(), window);
		}
		else
		{
			window = glfwCreateWindow(width, height, title, 0, window);
		}
		
		if (window == 0)
		{
			System.err.println("Failed to create window!");
			System.exit(-1);
		}
		
		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		tex = new Texture("./res/test.png");
	
		glfwShowWindow(window);
	}
	
	public boolean isCloseRequested()
	{
		return glfwWindowShouldClose(this.window);
	}
	
	public void update()
	{
		IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
		IntBuffer heightBuffer = BufferUtils.createIntBuffer(1); // 10:46
		glfwGetWindowSize(window, widthBuffer, heightBuffer);
		width = widthBuffer.get(0);
		height = heightBuffer.get(0);
		GL11.glViewport(0, 0, width, height);
		
		glfwPollEvents();
		glClear(GL_COLOR_BUFFER_BIT);
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(-0.6f, 0.5f);
		glTexCoord2f(0, 1);
		glVertex2f(0.7f, 0.2f);
		glTexCoord2f(1, 1);
		glVertex2f(0.4f, -0.5f);
		glTexCoord2f(1, 0);
		glVertex2f(-0.5f, -0.3f);
		glEnd();
	}
	
	public void swapBuffers()
	{
		glfwSwapBuffers(this.window);
	}
	
	public void stop()
	{
		glfwTerminate();
	}
}

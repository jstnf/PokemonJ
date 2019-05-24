package io.pokemonj;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window
{
	private int width, height;
	private String title;
	private long window;
	private boolean isFullScreen;
	
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
		glfwPollEvents();
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

package com.example.tinyworld4android;

import android.graphics.Canvas;
import android.view.View;

import com.example.res.ResLoader;
import com.example.screens.Screen;

public class GameCanvas implements Runnable{

	private static final long serialVersionUID = 8408780829498109905L;
	
	public static final String screendir = "screens/";
	
	private Thread animator;
	private volatile boolean running = false;
	private BufferStrategy bs;
	private Canvas bg;
	private Screen screen;
	public long fps = 60;
	private Input in;
	
	private boolean pressedF2 = false;
	
	public GameCanvas(View view) {
		ResLoader.loadImgs(view.getResources());
		in = new Input(this);
		screen = new ScreenTitle(this, in);
	}	
	
	public synchronized void start() {
		if (!running && animator == null) {
			animator = new Thread(this);
			animator.start();
		}
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		
		long before = System.currentTimeMillis();
		long takenTime = 0;
		
		long lastFps = System.currentTimeMillis();
		long time = System.currentTimeMillis();
		long frames = 0;
		
		while(running) {
			before = System.currentTimeMillis();
			tick();
			render();
			takenTime = System.currentTimeMillis()-before;
			
			if (16-takenTime > 0) {
				try {
					Thread.sleep(16-takenTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			frames++;
			time = System.currentTimeMillis();
			if (time-lastFps > 999) {
				lastFps = time;
				fps = frames;
				frames = 0;
			}
		}
	}
	
	private void tick() {
		screen.tick();
	}
	
	private void render() {
		bs = getBufferStrategy();
		if (bs == null) { 
			createBufferStrategy(3);
			return;
		}
		bg = (Graphics2D) bs.getDrawGraphics();
		
		bg.clearRect(0, 0, getWidth(), getHeight());
		
		screen.render(bg);
		
		bs.show();
		
		if (in.keys[Input.F2] && !pressedF2) {
			screenshot();
			pressedF2 = true;
		} if (!in.keys[Input.F2] && pressedF2) {
			pressedF2 = false;
		}
	}
	
	public void setScreen(Screen s) {
		screen = s;
	}

}

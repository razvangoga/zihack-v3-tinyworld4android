package com.example.tinyworld4android;

import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.res.ResLoader;
import com.example.screens.Screen;
import com.example.screens.ScreenTitle;

public class GameCanvas implements Runnable{
	
	private ImageView imageView;
	private Thread animator;
	private volatile boolean running = false;
	//private BufferStrategy bs;
	private Canvas bg;
	private Screen screen;
	public long fps = 60;
	//private Input in;
	
	
	public GameCanvas(ImageView imageView2) {
		this.imageView = imageView2;
		
		ResLoader.loadImgs(imageView2.getResources());
		screen = new ScreenTitle(this);
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
		
		this.imageView.post(new Runnable(){
			@Override
            public void run() {
				imageView.setImageBitmap(screen.render());
			}
		});
	}
	
	public void setScreen(Screen s) {
		screen = s;
	}
	
	public void moveNext(){
		this.screen.moveNext();
	}
	public void movePrev(){
		this.screen.movePrev();
	}

}

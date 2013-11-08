package com.example.screens;

import android.graphics.Bitmap;

import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

public class ScreenGameOver extends Screen {
	
	//private Input in;
	private GameCanvas canvas;
	
	public ScreenGameOver(GameCanvas canvas) {
		//this.in = in;
		this.canvas = canvas;
	}
	
	public void tick() {
	}

	@Override
	public Bitmap render() {
		return ResLoader.get(ResLoader.SCREEN_GAMEOVER);
	}

	@Override
	public void moveNext() {
		this.canvas.setScreen(new ScreenTitle(canvas));
		
	}

	@Override
	public void movePrev() {
		this.canvas.setScreen(new ScreenTitle(canvas));
		
	}

}

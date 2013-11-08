package com.example.screens;

import android.graphics.Bitmap;

import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

public class ScreenGameWon extends Screen {
	
	private GameCanvas canvas;
	
	public ScreenGameWon(GameCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void tick() {
	}

	@Override
	public Bitmap render() {
		return ResLoader.get(ResLoader.SCREEN_GAMEWON);
	}
	
	@Override
	public void moveNext() {
		this.canvas.setScreen(new ScreenTitle(canvas));
		
	}
	
	@Override
	public void movePrev() {
		
	}

}

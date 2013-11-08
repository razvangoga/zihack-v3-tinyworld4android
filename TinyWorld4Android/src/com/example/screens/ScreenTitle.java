package com.example.screens;

import android.graphics.Bitmap;

import com.example.res.ResLoader;
import com.example.screens.gui.UserAction;
import com.example.tinyworld4android.GameCanvas;

public class ScreenTitle extends Screen {
	
	private GameCanvas canvas;
	
	public ScreenTitle(GameCanvas canvas) {
		this.canvas = canvas;
	}

	public void tick() {
	}

	public void moveNext(){
		canvas.setScreen(new ScreenTutorial(canvas));
	}
	
	public void movePrev() {
		
	}
	
	public Bitmap render() {
		return ResLoader.get(ResLoader.SCREEN_TITLESCREEN);
	}
	
	@Override
	public void handleUserAction(UserAction userAction) {
		// TODO Auto-generated method stub
		
	}

}

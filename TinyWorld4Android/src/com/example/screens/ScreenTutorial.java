package com.example.screens;

import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

import android.graphics.Bitmap;

public class ScreenTutorial extends Screen {
	
	private GameCanvas canvas;
	private int imgnum;
	
	public ScreenTutorial(GameCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void tick() {
	}
	
	public void moveNext(){
		if(imgnum == 7) {
			canvas.setScreen(new ScreenGame(canvas));
		}
		else {
			imgnum++;
			this.render();
		}
	}
	
	public void movePrev() {
		if(imgnum == 0)
			canvas.setScreen(new ScreenTitle(canvas));
		else {
			imgnum--;
			this.render();
		}
	}	
	
	public Bitmap render() {
		return ResLoader.get(ResLoader.SCREEN_TUT1+imgnum);
	}

}

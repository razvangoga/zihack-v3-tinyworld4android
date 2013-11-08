package com.example.screens;

import com.example.res.ResLoader;

import android.graphics.Canvas;

public class ScreenTutorial extends Screen {
	
	private GameCanvas canvas;
	private Input in;
	private boolean pressedLeft = false;
	private boolean pressedRight = false;
	private int imgnum;
	
	public ScreenTutorial(GameCanvas canvas, Input in) {
		this.canvas = canvas;
		this.in = in;
	}
	
	public void tick() {
		if (in.keys[Input.RIGHT] && !pressedRight) {
			if (imgnum == 7) {
				canvas.setScreen(new ScreenGame(canvas, in));
				return;
			}
			setImg(imgnum+1);
			pressedRight = true;
		}
		if (!in.keys[Input.RIGHT] && pressedRight) {
			pressedRight = false;
		}

		if (in.keys[Input.LEFT] && !pressedLeft) {
			setImg(imgnum-1);
			pressedLeft = true;
		}
		if (!in.keys[Input.LEFT] && pressedLeft) {
			pressedLeft = false;
		}
	}
	
	private void setImg(int num) {
		imgnum = num;
		imgnum = Math.max(0, imgnum); 
		imgnum = Math.min(7, imgnum);
	}
	
	public void render(Canvas g) {
		g.drawImage(ResLoader.get(ResLoader.SCREEN_TUT1+imgnum), 0, 0, null);
	}

}

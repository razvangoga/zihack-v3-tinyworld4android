package com.example.screens;

import android.graphics.Canvas;

import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

public class ScreenTitle extends Screen {
	
	private GameCanvas canvas;
	private Input in;
	
	public ScreenTitle(GameCanvas canvas, Input in) {
		this.canvas = canvas;
		this.in = in;
		for (int i = 0; i < in.keys.length; i++) {
			in.keys[i] = false;
		}
	}

	public void tick() {
		if (in.keys[Input.ENTER]) {
			canvas.setScreen(new ScreenTutorial(canvas, in));
		}
	}

	public void render(Canvas g) {
		g.drawImage(ResLoader.get(ResLoader.SCREEN_TITLESCREEN), 0, 0, null);
	}

}

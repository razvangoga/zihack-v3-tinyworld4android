package com.example.game.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.screens.ScreenGame;

public class TextParticle {
	
	private float x;
	private float y;
	private String text;
	private int time;
	private ScreenGame game;
	private float speed = 2f;
	
	public TextParticle(String text, float x, float y, int time, ScreenGame game) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.time = time;
		this.game = game;
		game.addParticle(this);
	}
	
	public void tick() {
		if (time <= 0) {
			game.removeParticle(this);
		}
		y -= speed;
		speed /= 1.05;
		time--;
	}
	
	public void render(Canvas g, int wx, int wy) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		g.drawText(text, x-wx, y-wy, paint);
	}

}

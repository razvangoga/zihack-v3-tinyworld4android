package com.example.game.entity;

import com.example.res.ResLoader;
import com.example.screens.ScreenGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class EnemyGlob extends Enemy {
	
	private Bitmap[] img;
	private int frame;
	private int count;
	
	public EnemyGlob(int x, int y, ScreenGame game) {
		super(x, y, game);
		health = 50;
		img = ResLoader.anim(ResLoader.ANIM_GLOB, ResLoader.Down);
	}
	
	protected int getSpeed() {
		return 1;
	}
	
	protected int getRadius() {
		return 4;
	}
	
	public int w() {
		return 32;
	}
	
	public int h() {
		return 32;
	}
	
	public void tick(boolean night) {
		super.tick(night);
		count++;
		if (count % 60 == 0) {
			count = 0;
			frame++;
			if (frame == img.length) {
				frame = 0;
			}
		}
	}
	
	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(img[frame], (int)x-wx, (int)y-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}
	
	public void renderEyes(Canvas g, int wx, int wy) {
		g.drawBitmap(ResLoader.anim(ResLoader.ANIM_GLOB_EYES, ResLoader.Down)[frame], (int)x-wx, (int)y-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}

}

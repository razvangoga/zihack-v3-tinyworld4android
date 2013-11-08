package com.example.game.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.res.ResLoader;
import com.example.screens.ScreenGame;

public class EnemyBat extends Enemy {

	private Bitmap[] img;
	private int frame;
	private int count;

	public EnemyBat(int x, int y, ScreenGame game) {
		super(x, y, game);
		health = 30;
		img = ResLoader.anim(ResLoader.ANIM_BAT, ResLoader.Down);
	}

	protected int getSpeed() {
		return 3;
	}

	protected int getRadius() {
		return 3;
	}

	public int w() {
		return 32;
	}

	public int h() {
		return 16;
	}

	public void tick(boolean night) {
		super.tick(night);
		count++;
		if (count % 6 == 0) {
			count = 0;
			frame++;
			if (frame == img.length) {
				frame = 0;
			}
		}
	}

	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(img[frame], (int) x - wx, (int) y - wy, new Paint(
				Paint.FILTER_BITMAP_FLAG));
	}

	public void renderEyes(Canvas g, int wx, int wy) {
		g.drawBitmap(
				ResLoader.anim(ResLoader.ANIM_BAT_EYES, ResLoader.Down)[frame],
				(int) x - wx, (int) y - wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}

}

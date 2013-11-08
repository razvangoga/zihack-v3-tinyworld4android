package com.example.game.building;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.entity.Enemy;
import com.example.game.utils.Util;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;
import com.example.screens.gui.Vec;

public class Bullet {
	
	private static float speed = 6f;
	private float x;
	private float y;
	private ScreenGame game;
	private Vec velocity;
	private Enemy following;
	
	public Bullet(float startx, float starty, Enemy following, ScreenGame game) {
		this.x = startx;
		this.y = starty;
		this.game = game;
		this.following = following;
		velocity = new Vec(startx, startx, following.midx(), following.midy());
		velocity.normalize();
		velocity.mul(speed);
		game.bullets.add(this);
	}
	
	public void tick() {
		if (following != null) {
			velocity.x = following.midx()-x;
			velocity.y = following.midy()-y;
		}
		velocity.normalize();
		velocity.mul(speed);
		x += velocity.x;
		y += velocity.y;

		if (Util.distance(x, y, following.midx(), following.midy()) < 6f) {
			following.hurt(10);
			game.bullets.remove(this);
		}
		if (x < 0 || y < 0 || x > game.getW()*ResLoader.TILE_SIZE || y > game.getH()*ResLoader.TILE_SIZE) {
			game.bullets.remove(this);
		}
	}
	
	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(ResLoader.get(ResLoader.TILE_BULLET), midx()-wx, midy()-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}
	
	private int midx() {
		return (int)x-ResLoader.TILE_SIZE/2;
	}
	
	private int midy() {
		return (int)y-ResLoader.TILE_SIZE/2;
	}

}
package com.example.game.tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.res.ResLoader;

public abstract class Tile {

	protected Bitmap img;
	protected int x;
	protected int y;
	
	public Tile(int x, int y) {
		this.x = x*ResLoader.TILE_SIZE;
		this.y = y*ResLoader.TILE_SIZE;
	}
	
	public boolean walkable() {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(img, x-wx, y-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}
	
	public Bitmap getImg() {
		return img;
	}
}
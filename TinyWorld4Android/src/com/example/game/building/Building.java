package com.example.game.building;

import com.example.res.ResLoader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Building {
	
	protected Bitmap img;
	protected int x;
	protected int y;
	protected int yoffset;
	
	public Building(int x, int y, int yoffset) {
		this.x = x;
		this.y = y;
		this.yoffset = yoffset;
	}
	
	public void tick() {
		
	}
	
	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(img, (x*ResLoader.TILE_SIZE)-wx, ((y*ResLoader.TILE_SIZE)+yoffset)-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}

}


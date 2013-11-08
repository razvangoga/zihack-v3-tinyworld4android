package com.example.game.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.tile.Tile;
import com.example.game.tile.TileWater;
import com.example.game.utils.Util;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;

public class GrabbedTiles {
	
	private Tile[][] tiles;
	private ScreenGame game;
	private int radius;
	
	public GrabbedTiles(int x, int y, int radius, ScreenGame game) {
		tiles = new Tile[radius*2][radius*2];
		this.game = game;
		this.radius = radius;
		grab(x, y, radius);
	}
	
	private void grab(int bx, int by, int radius) {
		int beginx = bx-radius;
		int beginy = by-radius;
		int endx = bx+radius;
		int endy = by+radius;
		beginx = Math.max(0, beginx);
		beginy = Math.max(0, beginy);
		endx = Math.min(game.getW(), endx);
		endy = Math.min(game.getH(), endy);
		for (int x = beginx; x < endx; x++) { 
			for (int y = beginy; y < endy; y++) {
				if (game.getTile(x, y).walkable() && Util.distance(x-beginx, y-beginy, radius, radius) < radius) {
					tiles[x-beginx][y-beginy] = game.getTile(x, y);
					game.setTile(x, y, new TileWater(x, y));
					game.removeBuild(x, y);
				}
			}
		}
	}
	
	public void render(Canvas g, int px, int py, int wx, int wy) {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if (tiles[x][y] != null) {
					g.drawBitmap(tiles[x][y].getImg(), (x-radius)*ResLoader.TILE_SIZE+px-wx, (y-radius)*ResLoader.TILE_SIZE+py-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
					Paint black = new Paint();
					black.setColor(Color.BLACK);
					g.drawRect((x-radius)*ResLoader.TILE_SIZE+px-wx, (y-radius)*ResLoader.TILE_SIZE+py-wy, ResLoader.TILE_SIZE, ResLoader.TILE_SIZE, black);
				}
			}
		}
	}

}

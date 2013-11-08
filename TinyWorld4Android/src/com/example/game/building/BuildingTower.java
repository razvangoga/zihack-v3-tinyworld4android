package com.example.game.building;

import java.util.List;

import com.example.game.entity.Enemy;
import com.example.game.utils.Util;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BuildingTower extends Building {

	private ScreenGame game;
	private static float radius = 120;
	private int count = 0;

	public static float getRadius() {
		return radius;
	}

	public BuildingTower(int x, int y, ScreenGame game) {
		super(x, y, 0);
		this.game = game;
		img = ResLoader.get(ResLoader.TILE_TOWER);
	}

	public void tick() {
		if (game.enemys.size() > 0 && count == 0) {
			Enemy e = getNearest(game.enemys, radius, totalx(), totaly());
			if (e != null) {
				new Bullet(totalx(), totaly(), e, game);
				count = 10;
			}
		}
		if (count > 0) {
			count--;
		}
	}

	public void renderGlow(Canvas g, int wx, int wy) {
		g.drawBitmap(ResLoader.get(ResLoader.TILE_TOWER_GLOW), 
				x*ResLoader.TILE_SIZE-wx, 
				y*ResLoader.TILE_SIZE-wy, new Paint(Paint.FILTER_BITMAP_FLAG));
	}

	private float totalx() {
		return x * ResLoader.TILE_SIZE + (ResLoader.TILE_SIZE / 2);
	}

	private float totaly() {
		return y * ResLoader.TILE_SIZE + (ResLoader.TILE_SIZE / 2);
	}

	private Enemy getNearest(List<Enemy> list, float radius, float x, float y) {
		Enemy e = null;
		float lastDist = radius;
		for (int i = 0; i < list.size(); i++) {
			float distance = Util.distance(x, y, list.get(i).midx(), list
					.get(i).midy());
			if (distance <= radius) {
				if (distance < lastDist) {
					lastDist = distance;
					e = list.get(i);
				}
			}
		}
		return e;
	}

	public static Paint getPerfectOrange() {
		Paint orange = new Paint();
		orange.setARGB(255, 255, 136, 0);

		return orange;
	}

}

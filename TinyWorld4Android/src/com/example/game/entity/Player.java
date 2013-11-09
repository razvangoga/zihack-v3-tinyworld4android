package com.example.game.entity;

import java.util.Random;

import com.example.game.building.Building;
import com.example.game.building.BuildingBase;
import com.example.game.building.BuildingFabric;
import com.example.game.building.BuildingHome;
import com.example.game.building.BuildingTower;
import com.example.game.building.BuildingTree;
import com.example.game.tile.TileGrass;
import com.example.game.tile.TileSand;
import com.example.res.ResLoader;
import com.example.screens.ScreenBase;
import com.example.screens.ScreenFabric;
import com.example.screens.ScreenGame;
import com.example.screens.ScreenGameOver;
import com.example.screens.ScreenGameWon;
import com.example.screens.ScreenHome;
import com.example.screens.gui.Stack;
import com.example.screens.gui.UserAction;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player {

	public static final int frameTime = 6;
	public static final int normaldelay = 6;

	public Stack items = null;
	private Bitmap[] img;
	public int x;
	public int y;
	private int count = 0;
	private int frame = 0;
	private int animDir = 0;
	private ScreenGame map;
	private Random rand;
	
	public Player(int x, int y, ScreenGame game, Random rand) {
		this.x = x;
		this.y = y;
		this.rand = rand;
		map = game;
		img = ResLoader.anim(ResLoader.ANIM_PLAYER, animDir);
	}

	public void tick() {
		if (x == 1 || y == 1 || x == map.getW() - 1 || y == map.getH() - 1) {
			map.getCanvas().setScreen(new ScreenGameWon(map.getCanvas()));
			return;
		}

		count++;

		if (count == frameTime) {
			increaseFrame();
			count = 0;
		}
		
		Building b = map.getBuild(x, y);
		if (b != null) {
			buildingAction(b);
		}
		if (items != null && items.getNumber() == 0) {
			items = null;
		}
		if (!map.getTile(x, y).walkable()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.getCanvas().setScreen(new ScreenGameOver(map.getCanvas()));
		}
	}

	private void removeTreeAction(int x, int y) {
		map.removeBuild(x, y);
		if (items == null) {
			items = new Stack(ResLoader.GUI_WOODITEM,
					Math.abs(rand.nextInt() % 3) + 1);
		} else {
			items.pushItems(Math.abs(rand.nextInt() % 3) + 1);
		}
	}

	private void buildingAction(Building b) {
		if (b instanceof BuildingHome) {
			map.getCanvas().setScreen(new ScreenHome(map.getCanvas(), map,  ((BuildingHome)b).getStorage()));
		} else if (b instanceof BuildingFabric) {
			map.getCanvas().setScreen(new ScreenFabric(map.getCanvas(), map, ((BuildingFabric)b).getStorage()));
		} else if (b instanceof BuildingBase) {
			map.getCanvas().setScreen(new ScreenBase(map.getCanvas(), map, ((BuildingBase)b).getStorage()));
		}
	}

	private void placeGrass(int bx, int by, int radius) {
		if (items != null) {
			int beginx = bx - radius;
			int beginy = by - radius;
			int endx = bx + radius + 1;
			int endy = by + radius + 1;
			beginx = Math.max(0, beginx);
			beginy = Math.max(0, beginy);
			endx = Math.min(map.getW(), endx);
			endy = Math.min(map.getH(), endy);
			for (int x = beginx; x < endx; x++) {
				for (int y = beginy; y < endy; y++) {
					if (getDistance(bx, by, x, y) <= radius
							&& items.getNumber() > 0) {
						if (!map.getTile(x, y).walkable()) {
							switch (items.getType()) {
							case ResLoader.TILE_GRASS:
								map.setTile(x, y,
										new TileGrass(x, y, rand, map));
								break;
							case ResLoader.TILE_SAND:
								map.setTile(x, y, new TileSand(x, y));
								break;
							default:
								return;
							}
							items.popItems(1);
							if (items.getNumber() == 0) {
								items = null;
								return;
							}
						}
					}
				}
			}
		}
	}

	public int getDistance(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
	}

	private void increaseFrame() {
		frame++;
		if (frame >= img.length) {
			frame = 0;
		}
	}

	public void render(Canvas g, int wx, int wy) {
		g.drawBitmap(img[frame], x * ResLoader.TILE_SIZE - wx, y
				* ResLoader.TILE_SIZE - wy, new Paint(Paint.FILTER_BITMAP_FLAG));
//		if (items != null && items.getType() == ResLoader.TILE_TOWER) {
//			Paint orange = BuildingTower.getPerfectOrange();
//
//			g.drawOval(
//					new RectF(
//							(int) (x * ResLoader.TILE_SIZE - 8 - wx - BuildingTower
//									.getRadius()) + ResLoader.TILE_SIZE / 2,
//							(int) (y * ResLoader.TILE_SIZE - 8 - wy - BuildingTower
//									.getRadius()) + ResLoader.TILE_SIZE / 2,
//							(int) (BuildingTower.getRadius() * 2)
//									+ ResLoader.TILE_SIZE / 2,
//							(int) (BuildingTower.getRadius() * 2)
//									+ ResLoader.TILE_SIZE / 2), orange);
//		}
	}

	public void handleUserAction(UserAction userAction) {		
		if (userAction == UserAction.Left && map.isWalkable(x - 1, y)) {
			x--;
			animDir = ResLoader.Left;
			img = ResLoader.anim(ResLoader.ANIM_PLAYER, animDir);
		}
		if (userAction == UserAction.Right && map.isWalkable(x + 1, y)) {
			x++;
			animDir = ResLoader.Right;
			img = ResLoader.anim(ResLoader.ANIM_PLAYER, animDir);
		}
		if (userAction == UserAction.Up && map.isWalkable(x, y - 1)) {
			y--;
			animDir = ResLoader.Up;
			img = ResLoader.anim(ResLoader.ANIM_PLAYER, animDir);
		}
		if (userAction == UserAction.Down && map.isWalkable(x, y + 1)) {
			y++;
			animDir = ResLoader.Down;
			img = ResLoader.anim(ResLoader.ANIM_PLAYER, animDir);
		}

		if (userAction == UserAction.Use) {
			Building b = map.getBuild(x, y);
			if (b instanceof BuildingTree
					&& (items == null || items.getType() == ResLoader.GUI_WOODITEM)) {
				removeTreeAction(x, y);
			} else if (map.getBuild(x, y) instanceof BuildingTower
					&& (items == null || items.getType() == ResLoader.TILE_TOWER)) {
				if (items == null) {
					items = new Stack(ResLoader.TILE_TOWER, 1);
				} else {
					items.pushItems(1);
				}
				map.removeBuild(x, y);
			}
			if (items != null && items.getType() == ResLoader.GUI_FABRICITEM) {
				if (map.fromBuildOkey(x, y)) {
					map.setBuild(x, y,
							new BuildingFabric(x, y, this, rand, map));
					items.popItems(1);
				}
			}
			if (items != null && items.getType() == ResLoader.GUI_BASEICON) {
				if (map.fromBuildOkey(x, y)) {
					map.setBuild(x, y, new BuildingBase(x, y, this, map));
					items.popItems(1);
				}
			}
			if (items != null && items.getType() == ResLoader.TILE_TOWER) {
				if (map.fromBuildOkey(x, y)) {
					map.setBuild(x, y, new BuildingTower(x, y, map));
					items.popItems(1);
				}
			} else {
				placeGrass(x, y, 4);
			}
		}
	}

}

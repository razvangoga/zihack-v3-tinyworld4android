package com.example.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.game.building.*;
import com.example.game.entity.Enemy;
import com.example.game.entity.EnemyBat;
import com.example.game.entity.EnemyGlob;
import com.example.game.entity.Player;
import com.example.screens.gui.Gui;
import com.example.screens.gui.UserAction;
import com.example.game.tile.Tile;
import com.example.game.tile.TileGrass;
import com.example.game.tile.TileSand;
import com.example.game.tile.TileWater;
import com.example.game.utils.Heightmap;
import com.example.game.utils.TextParticle;
import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

public class ScreenGame extends Screen {

	private static int canvas_width = 600;
	private static int canvas_height = 800;

	private static int size = 129;
	private static final long time_cicle_speed = 1L;
	private static final float night_brightness = 0.3f;
	private static final long rise_time = 60 * 40 - 1;
	private static final long day_time = 60 * 60 * 2;
	private static final long set_time = 60 * 40;
	private static final long night_time = 60 * 60 * 1;
	private static final float rise_my = (1f - night_brightness) / (rise_time);
	private static final float set_my = (1f - night_brightness) / (set_time);

	private Tile[][] tiles;
	private Building[][] builds;

	public List<Enemy> enemys = new ArrayList<Enemy>();
	public List<BuildingTree> trees = new ArrayList<BuildingTree>();
	public List<BuildingFabric> fabrics = new ArrayList<BuildingFabric>();
	public List<BuildingBase> bases = new ArrayList<BuildingBase>();
	public List<BuildingTower> towers = new ArrayList<BuildingTower>();
	public List<TextParticle> particles = new ArrayList<TextParticle>();
	public List<Bullet> bullets = new ArrayList<Bullet>();

	private int wx;
	private int wy;
	private GameCanvas canvas;
	private Player p;
	private float brightness = 0;
	private long time = 1;
	private boolean night = false;
	private Random rand;
	private int difficulty = 50;
	private Gui gui;

	public ScreenGame(GameCanvas canvas) {
		tiles = new Tile[size][size];
		builds = new Building[size][size];
		this.canvas = canvas;
		rand = new Random();
		p = new Player(size / 2, size / 2, this, rand);
		gui = new Gui(p, canvas);
		createTiles();
		createBuildings();
	}

	public void tick() {
		tickTiles();
		p.tick();
		if (time < rise_time) {
			brightness = linearGraph(rise_my, (float) time, night_brightness);
		} else if (time < rise_time + day_time) {
			brightness = 1f;
		} else if (time < rise_time + day_time + set_time) {
			brightness = 1f - linearGraph(set_my, time - rise_time - day_time,
					0f);
		} else if (time < rise_time + day_time + set_time + night_time) {
			night = true;
			brightness = night_brightness;
		} else {
			night = false;
			time = 1;
		}
		time += time_cicle_speed;
		if (night) {
			if (rand.nextInt() % difficulty == 0) {
				int dir = Math.abs(rand.nextInt() % 4);
				int randval = Math.abs(rand.nextInt() % size);
				switch (dir) {
				case Enemy.LEFT:
					createRandEnemy(0, randval);
					break;
				case Enemy.RIGHT:
					createRandEnemy(size * ResLoader.TILE_SIZE, randval);
					break;
				case Enemy.DOWN:
					createRandEnemy(randval, 0);
					break;
				case Enemy.UP:
					createRandEnemy(randval, size * ResLoader.TILE_SIZE);
					break;
				}
			}
		}
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).tick(night);
		}
		for (int i = 0; i < trees.size(); i++) {
			trees.get(i).tick();
		}
		for (int i = 0; i < fabrics.size(); i++) {
			fabrics.get(i).tick();
		}
		for (int i = 0; i < bases.size(); i++) {
			bases.get(i).tick();
		}
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).tick();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}

	public Bitmap render() {

		Bitmap gameScreen = Bitmap.createBitmap(canvas_height, canvas_width,
				Bitmap.Config.ARGB_8888);
		Canvas gameScreenCanvas = new Canvas(gameScreen);

		Paint color = new Paint();
		color.setARGB(255, 15, 15, 15);

		gameScreenCanvas.drawRect(0, 0, canvas_width, canvas_height, color);

		renderVisible(gameScreenCanvas);
		wx = p.x * ResLoader.TILE_SIZE - canvas_width / 2;
		wy = p.y * ResLoader.TILE_SIZE - canvas_height / 2;
		wx = Math.max(0, wx);
		wy = Math.max(0, wy);
		wx = Math.min(size * ResLoader.TILE_SIZE - canvas_width, wx);
		wy = Math.min(size * ResLoader.TILE_SIZE - canvas_height, wy);

		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).renderEyes(gameScreenCanvas, wx, wy);
		}
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).renderGlow(gameScreenCanvas, wx, wy);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(gameScreenCanvas, wx, wy);
		}

		gui.render(gameScreenCanvas);

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(gameScreenCanvas, wx, wy);
		}
		gameScreenCanvas
				.drawBitmap(
						ResLoader.anim(ResLoader.ANIM_ARROW, ResLoader.Down)[(time % 30) < 15 ? 0
								: 1], p.x * ResLoader.TILE_SIZE - wx, p.y
								* ResLoader.TILE_SIZE - wy - 24, new Paint(
								Paint.FILTER_BITMAP_FLAG));

		return gameScreen;
	}

	public void removeEnemy(Enemy e) {
		enemys.remove(e);
	}

	private void createRandEnemy(int x, int y) {
		if (rand.nextBoolean()) {
			enemys.add(new EnemyBat(x, y, this));
		} else {
			enemys.add(new EnemyGlob(x, y, this));
		}
	}

	public Point getRandomTile() {
		Point p = new Point();
		do {
			p.x = Math.abs(rand.nextInt() % size);
			p.y = Math.abs(rand.nextInt() % size);
		} while (!tiles[p.x][p.y].walkable());
		return p;
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}

	public float linearGraph(float my, float x, float c) {
		return (my * x) + c;
	}

	public int getW() {
		return size;
	}

	public int getH() {
		return size;
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public void setTile(int x, int y, Tile t) {
		tiles[x][y] = t;
	}

	public void removeBuild(int x, int y) {
		if (builds[x][y] instanceof BuildingTree) {
			BuildingTree t = (BuildingTree) builds[x][y];
			trees.remove(t);
			builds[x][y] = null;
			return;
		}
		if (builds[x - 1][y] instanceof BuildingTree) {
			return;
		}

		if (builds[x][y] instanceof BuildingTower) {
			BuildingTower t = (BuildingTower) builds[x][y];
			towers.remove(t);
			builds[x][y] = null;
		}
		if (builds[x - 1][y] instanceof BuildingTower) {
			return;
		}

		if (getBuild(x, y) instanceof BuildingFabric) {
			fabrics.remove(getBuild(x, y));
		}
		if (getBuild(x, y) instanceof BuildingBase) {
			bases.remove(getBuild(x, y));
		}

		builds[x][y] = null;
		builds[x - 1][y] = null;
	}

	public Building getBuild(int x, int y) {
		if (builds[x][y] != null) {
			return builds[x][y];
		}
		if (builds[x - 1][y] != null
				&& !(builds[x - 1][y] instanceof BuildingTree)) {
			return builds[x - 1][y];
		}
		return null;
	}

	public GameCanvas getCanvas() {
		return canvas;
	}

	public Player getPlayer() {
		return p;
	}

	private void tickTiles() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (tiles[x][y] != null) {
					tiles[x][y].tick();
				}
			}
		}
	}

	public boolean isWalkable(int x, int y) {
		if (tiles[x][y] != null) {
			return tiles[x][y].walkable();
		}
		return false;
	}

	public boolean fromBuildOkey(int x, int y) {
		if (builds[x][y] == null) {
			if (builds[x - 1][y] == null) {
				return true;
			} else {
				if (builds[x - 1][y] instanceof BuildingTree) {
					return true;
				}
				if (builds[x - 1][y] instanceof BuildingTower) {
					return true;
				}
			}
		}
		return false;
	}

	public void setBuild(int x, int y, Building b) {
		if (b instanceof BuildingTree) {
			trees.add((BuildingTree) b);
		}
		if (b instanceof BuildingTower) {
			towers.add((BuildingTower) b);
		}
		if (b instanceof BuildingFabric) {
			fabrics.add((BuildingFabric) b);
		}
		if (b instanceof BuildingBase) {
			bases.add((BuildingBase) b);
		}
		builds[x][y] = b;
	}

	public void addTree(BuildingTree t, int x, int y) {
		builds[x][y] = t;
		trees.add(t);
	}

	public void addParticle(TextParticle p) {
		particles.add(p);
	}

	public void removeParticle(TextParticle p) {
		particles.remove(p);
	}

	private void createTiles() {
		Heightmap hm = new Heightmap(size, 600f, 1.5f, new Random());
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (hm.heightmap[x][y] > 500f) {
					if (hm.heightmap[x][y] < 520f) {
						tiles[x][y] = new TileSand(x, y);
					} else {
						tiles[x][y] = new TileGrass(x, y, rand, this);
					}
				} else {
					tiles[x][y] = new TileWater(x, y);
				}
			}
		}
	}

	@Override
	public void moveNext() {
		return;

	}

	@Override
	public void movePrev() {
		this.canvas.setScreen(new ScreenTutorial(canvas));

	}

	private void createBuildings() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == size / 2 - 1 && y == size / 2 - 1) {
					builds[x][y] = new BuildingHome(x, y, p);
				} else if (x == size / 2 - 3 && y == size / 2) {
					BuildingFabric f = new BuildingFabric(x, y, p, rand, this);
					builds[x][y] = f;
					fabrics.add(f);
				} else if (x == size / 2 + 1 && y == size / 2 + 2) {
					addTree(new BuildingTree(x, y, rand, this), x, y);
				} else if (x == size / 2 + 1 && y == size / 2) {
					BuildingBase b = new BuildingBase(x, y, p, this);
					builds[x][y] = b;
					bases.add(b);
				}
			}
		}
	}

	private void renderVisible(Canvas g) {
		int beginx = wx / ResLoader.TILE_SIZE;
		int beginy = wy / ResLoader.TILE_SIZE;
		int endx = (wx + canvas_width) / ResLoader.TILE_SIZE + 1;
		int endy = (wy + canvas_height) / ResLoader.TILE_SIZE + 1;
		beginx = Math.max(0, beginx / 2);
		beginy = Math.max(0, beginy / 2);
		endx = Math.min(size, endx * 2);
		endy = Math.min(size, endy * 2);
		for (int x = beginx; x < endx; x++) {
			for (int y = beginy; y < endy; y++) {
				if (tiles[x][y] != null) {
					tiles[x][y].render(g, wx, wy);
				}
			}
		}
		p.render(g, wx, wy);
		for (int y = beginy; y < endy; y++) {
			for (int x = beginx; x < endx; x++) {
				if (builds[x][y] != null) {
					builds[x][y].render(g, wx, wy);
				}
			}
		}
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).renderGrab(g, wx, wy);
		}
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).render(g, wx, wy);
		}
	}

	public void handleUserAction(UserAction userAction) {
		p.handleUserAction(userAction);
	}
}

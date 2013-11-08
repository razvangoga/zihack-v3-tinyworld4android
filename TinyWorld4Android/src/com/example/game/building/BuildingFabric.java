package com.example.game.building;

import java.util.Random;

import com.example.game.entity.Player;
import com.example.game.utils.TextParticle;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;
import com.example.screens.gui.GuiStorage;
import com.example.screens.gui.Stack;

public class BuildingFabric extends Building {
	
	private GuiStorage storage;
	private int count = 0;
	private Stack wood;
	private Random rand;
	private ScreenGame game;

	public BuildingFabric(int x, int y, Player p, Random rand, ScreenGame game) {
		super(x, y, -32);
		this.rand = rand;
		this.game = game;
		img = ResLoader.get(ResLoader.BUILD_FABRIC);
		storage = new GuiStorage(232, 128, 9, 9, p);
		wood = new Stack(ResLoader.GUI_WOODITEM, 0);
		count = 120;
	}
	
	public GuiStorage getStorage() {
		return storage;
	}
	
	public void tick() {
		if (count <= 0) {
			if (wood.getNumber() > 0) {
				produceItems();
				wood.popItems(1);
			}
			count = 120;
		}
		count--;
	}
	
	private void produceItems() {
		if (rand.nextBoolean()) {
			storage.addItems(new Stack(ResLoader.TILE_GRASS, 1));
			game.addParticle(new TextParticle("[FABRIC:] Grass Tile Produced!", 
					x*ResLoader.TILE_SIZE-64, 
					y*ResLoader.TILE_SIZE-32, 300, game));
		} else {
			storage.addItems(new Stack(ResLoader.TILE_SAND, 2));
			game.addParticle(new TextParticle("[FABRIC:] 2 Sand Tiles Produced!", 
					x*ResLoader.TILE_SIZE-64, 
					y*ResLoader.TILE_SIZE-32, 300, game));
		}
	}
	
	public Stack getWoodStack() {
		return wood;
	}
	
}
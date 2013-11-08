package com.example.game.tile;

import java.util.Random;

import com.example.game.building.BuildingTree;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;

public class TileGrass extends Tile {
	
	public TileGrass(int x, int y, Random rand, ScreenGame game) {
		super(x, y);
		img = ResLoader.get(ResLoader.TILE_GRASS);
//		if (rand.nextInt()% 100 == 0 && game.fromBuildOkey(x, y)) {
//			game.addTree(new BuildingTree(x, y, rand, game), x, y);
//		}
	}
	
	public boolean walkable() {
		return true;
	}

}

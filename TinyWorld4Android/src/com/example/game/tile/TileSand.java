package com.example.game.tile;

import com.example.res.ResLoader;

public class TileSand extends Tile {

	public TileSand(int x, int y) {
		super(x, y);
		img = ResLoader.get(ResLoader.TILE_SAND);
	}
	
	public boolean walkable() {
		return true;
	}

}

package com.example.game.building;

import com.example.game.entity.Player;
import com.example.res.ResLoader;
import com.example.screens.gui.GuiStorage;
import com.example.screens.gui.Stack;

public class BuildingHome extends Building {
	
	private GuiStorage storage;
	
	public BuildingHome(int x, int y, Player p) {
		super(x, y, -16);
		img = ResLoader.get(ResLoader.BUILD_HOME);
		storage = new GuiStorage(232, 128, 21, 9, p);
		storage.addItems(new Stack(ResLoader.TILE_TOWER, 20));
		storage.addItems(new Stack(ResLoader.GUI_BASEICON, 1));
		storage.addItems(new Stack(ResLoader.GUI_WOODITEM, 99));
	}
	
	public GuiStorage getStorage() {
		return storage;
	}
	
	public void addItems(Stack stack) {
		storage.addItems(stack);
	}
	
	public void addItems(Stack... stacks) {
		for (int i = 0; i < stacks.length; i++) {
			storage.addItems(stacks[i]);
		}
	}

}

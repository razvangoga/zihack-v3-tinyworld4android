package com.example.screens;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.building.Building;
import com.example.game.building.BuildingFabric;
import com.example.res.ResLoader;
import com.example.screens.gui.GuiStorage;
import com.example.screens.gui.Stack;
import com.example.screens.gui.UserAction;
import com.example.tinyworld4android.GameCanvas;

public class ScreenFabric extends ScreenGameBasedMenu {
	
	private Stack wood;

	public ScreenFabric(GameCanvas canvas, ScreenGame gamescreen,
			GuiStorage storage) {
		super(canvas, gamescreen, storage);

		Building b = gamescreen.getBuild(gamescreen.getPlayer().x, gamescreen.getPlayer().y);
		if (b instanceof BuildingFabric) {
			BuildingFabric fabric = ((BuildingFabric) b); 
			wood = fabric.getWoodStack();
		} else {
			System.err.println("ScreenHome invoked wrong!");
			System.exit(-1);
		}
	}

	public void tick() {
		storage.tick();
	}

	public void renderMenu(Canvas g) {
		g.drawBitmap(ResLoader.get(ResLoader.GUI_FABRIC), 200, 100, new Paint(
				Paint.FILTER_BITMAP_FLAG));
		storage.render(g);
		wood.renderIcon(g, 488, 192);
		wood.renderNumber(g, 488, 192);
	}
	
	public boolean customUserActionHandled(UserAction userAction){
		if (userAction == UserAction.Move) {
			if (gamescreen.getPlayer().items != null) {
				if (gamescreen.getPlayer().items.getType() == ResLoader.GUI_WOODITEM) {
					wood.tryAddStack(gamescreen.getPlayer().items);
				}
			}
			if (gamescreen.getPlayer().items != null && gamescreen.getPlayer().items.getNumber() <= 0) {
				gamescreen.getPlayer().items = null;
			}
		}
		
		return false;
	}

}

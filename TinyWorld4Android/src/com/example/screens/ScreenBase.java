package com.example.screens;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.building.Building;
import com.example.game.building.BuildingBase;
import com.example.res.ResLoader;
import com.example.screens.gui.GuiStorage;
import com.example.tinyworld4android.GameCanvas;

public class ScreenBase extends ScreenGameBasedMenu {
	
	private BuildingBase base;

	public ScreenBase(GameCanvas canvas, ScreenGame gamescreen,
			GuiStorage storage) {
		super(canvas, gamescreen, storage);

		Building b = gamescreen.getBuild(gamescreen.getPlayer().x, gamescreen.getPlayer().y);
		if (b instanceof BuildingBase) {
			base = ((BuildingBase) b);
		} else {
			System.err.println("ScreenBase invoked wrong!");
			System.exit(-1);
		}
	}

	public void tick() {
		base.getStorage().tick();
		base.tickMenu();
	}

	public void renderMenu(Canvas g) {
		g.drawBitmap(ResLoader.get(ResLoader.GUI_BASE), 200, 100, new Paint(Paint.FILTER_BITMAP_FLAG));
		storage.render(g);
		base.renderMenu(g, 200, 100);
	}	
}

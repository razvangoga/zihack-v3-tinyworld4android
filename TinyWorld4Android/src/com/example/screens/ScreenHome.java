package com.example.screens;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.building.Building;
import com.example.res.ResLoader;
import com.example.screens.gui.GuiStorage;
import com.example.tinyworld4android.GameCanvas;

public class ScreenHome extends ScreenGameBasedMenu {

	public ScreenHome(GameCanvas canvas, ScreenGame gamescreen,
			GuiStorage storage) {
		super(canvas, gamescreen, storage);

		Building b = gamescreen.getBuild(gamescreen.getPlayer().x,
				gamescreen.getPlayer().y);
	}

	public void tick() {
		storage.tick();
	}

	public void renderMenu(Canvas g) {
		g.drawBitmap(ResLoader.get(ResLoader.GUI_STORAGE), 200, 100, new Paint(
				Paint.FILTER_BITMAP_FLAG));
		storage.render(g);
	}
}

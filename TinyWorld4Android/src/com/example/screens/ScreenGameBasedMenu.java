package com.example.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.screens.gui.GuiStorage;
import com.example.screens.gui.UserAction;
import com.example.tinyworld4android.GameCanvas;

public abstract class ScreenGameBasedMenu extends Screen {

	protected ScreenGame gamescreen;
	protected GuiStorage storage;
	protected GameCanvas canvas;

	public ScreenGameBasedMenu(GameCanvas canvas, ScreenGame gamescreen,
			GuiStorage storage) {
		this.gamescreen = gamescreen;
		this.canvas = canvas;
		this.storage = storage;
	}

	@Override
	public Bitmap render() {
		Bitmap gameScreen = this.gamescreen.render();
		Canvas canvas = new Canvas(gameScreen);

		// gamescreen.setBrightness(0.2f);

		renderMenu(canvas);

		return gameScreen;

	}

	@Override
	public void moveNext() {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePrev() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleUserAction(UserAction userAction) {
		if (userAction == UserAction.Close) {
			canvas.setScreen(gamescreen);
			gamescreen.getPlayer().y += 1;
		} else {
			this.customUserActionHandled(userAction);
			this.storage.handleUserAction(userAction);
		}

	}

	public abstract void renderMenu(Canvas g);

	public void customUserActionHandled(UserAction userAction) {
	}

}

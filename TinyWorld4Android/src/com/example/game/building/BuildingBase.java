package com.example.game.building;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.entity.Player;
import com.example.game.utils.TextParticle;
import com.example.res.ResLoader;
import com.example.screens.ScreenGame;
import com.example.screens.gui.GuiStorage;
import com.example.screens.gui.Stack;
import com.example.screens.gui.UserAction;

public class BuildingBase extends Building {

	public static final Color perfectGray = new Color();

	private GuiStorage storage;
	private Stack wood1;
	private Stack wood2;
	private Stack wood3;
	private Player p;
	private int count = 0;
	private ScreenGame game;

	public BuildingBase(int x, int y, Player p, ScreenGame game) {

		super(x, y, -16);
		this.game = game;
		this.p = p;
		img = ResLoader.get(ResLoader.BUILD_BASE);
		storage = new GuiStorage(232, 128, 4, 9, p);
		wood1 = new Stack(ResLoader.GUI_WOODITEM, 0);
		wood2 = new Stack(ResLoader.GUI_WOODITEM, 0);
		wood3 = new Stack(ResLoader.GUI_WOODITEM, 0);
	}

	public void tick() {
		if (count <= 0) {
			if (availableWood() >= 10) {
				takeWood(10);
				Random rand = new Random();
				int num = Math.abs(rand.nextInt() % 10);
				if (num == 9) {
					storage.addItems(new Stack(ResLoader.GUI_FABRICITEM, 1));
					game.addParticle(new TextParticle(
							"[BUILDER:] U're lucky: Produced a Fabric!", x
									* ResLoader.TILE_SIZE - 64, y
									* ResLoader.TILE_SIZE - 32, 300, game));
				} else if (num == 8) {
					storage.addItems(new Stack(ResLoader.GUI_BASEICON, 1));
					game.addParticle(new TextParticle(
							"[BUILDER:] U're lucky: Produced a Builder!", x
									* ResLoader.TILE_SIZE - 64, y
									* ResLoader.TILE_SIZE - 32, 300, game));
				} else {
					storage.addItems(new Stack(ResLoader.TILE_TOWER, 1));
					game.addParticle(new TextParticle(
							"[BUILDER:] Defensive Tower produced!", x
									* ResLoader.TILE_SIZE - 64, y
									* ResLoader.TILE_SIZE - 32, 300, game));
				}
			}
			count = 600;
		}
		count--;
	}

	public void tickMenu() {
	}

	private void tryAddWood(Stack s) {
		wood1.tryAddStack(s);
		wood2.tryAddStack(s);
		wood3.tryAddStack(s);
	}

	private void takeWood(int num) {
		int totake = num;
		totake = Math.max(0, wood3.popItems(totake));
		totake = Math.max(0, wood2.popItems(totake));
		totake = Math.max(0, wood1.popItems(totake));
	}

	private int availableWood() {
		return wood1.getNumber() + wood2.getNumber() + wood3.getNumber();
	}

	public GuiStorage getStorage() {
		return storage;
	}

	public void renderMenu(Canvas g, int guix, int guiy) {
		wood1.renderIcon(g, guix + 256, guiy + 75);
		wood2.renderIcon(g, guix + 256, guiy + 91);
		wood3.renderIcon(g, guix + 256, guiy + 107);
		wood1.renderNumber(g, guix + 256, guiy + 75);
		wood2.renderNumber(g, guix + 256, guiy + 91);
		wood3.renderNumber(g, guix + 256, guiy + 107);
		float fill = (count / 600f) * 103f;

		g.drawRect(guix + 132, guiy + 96, 105, 8, getPerfectGray());
		g.drawRect(guix + 133 + (int) (fill), guiy + 97, (int) (103 - fill), 6, BuildingTower.getPerfectOrange());
	}

	public static Paint getPerfectGray() {
		Paint gray = new Paint();
		gray.setARGB(255, 42, 42, 42);

		return gray;
	}

	public void handleUserAction(UserAction userAction) {
		if (userAction == UserAction.Move) {
			tryAddWood(p.items);
			if (p.items != null && p.items.getNumber() <= 0) {
				p.items = null;
			}
		}
	}

}

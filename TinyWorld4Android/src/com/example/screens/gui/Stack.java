package com.example.screens.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.res.ResLoader;

public class Stack {

	private int itemtype;
	private int num;

	public Stack(int itemtype, int num) {
		this.itemtype = itemtype;
		this.num = num;
	}

	public int getMaxStackable() {
		switch (itemtype) {
		case ResLoader.TILE_GRASS:
			return 50;
		case ResLoader.TILE_SAND:
			return 99;
		case ResLoader.TILE_TOWER:
			return 99;
		case ResLoader.GUI_WOODITEM:
			return 99;
		case ResLoader.GUI_FABRICITEM:
			return 1;
		case ResLoader.GUI_BASEICON:
			return 1;
		default:
			return 1000;
		}
	}

	public int tryAddStack(Stack s) {
		if (s == null) {
			return 0;
		}
		if (itemtype != s.getType()) {
			return s.getNumber();
		}
		int takenAway = pushItems(s.getNumber());
		s.popItems(s.getNumber() - takenAway);
		return takenAway;
	}

	public boolean isFilled() {
		return num == getMaxStackable();
	}

	public int pushItems(int number) {
		while (number > 0 && num < getMaxStackable()) {
			num++;
			number--;
		}
		return number;
	}

	public int popItems(int number) {
		int prenum = num;
		num = Math.max(0, num - number);
		return number - prenum;
	}

	public int getType() {
		return itemtype;
	}

	public int getNumber() {
		return num;
	}

	public void renderIcon(Canvas g, int x, int y) {
		g.drawBitmap(ResLoader.get(itemtype), x, y, new Paint(
				Paint.FILTER_BITMAP_FLAG));
	}

	public void renderNumber(Canvas g, int x, int y) {
		int color = Color.BLACK;

		if (getType() == ResLoader.GUI_WOODITEM
				|| getType() == ResLoader.GUI_FABRICITEM
				|| getType() == ResLoader.GUI_BASEICON
				|| getType() == ResLoader.TILE_TOWER) {
			color = Color.WHITE;

			Paint paint = new Paint();
			paint.setColor(color);

			g.drawText(Integer.toString(num), x + 1, y + 12, paint);
		}
	}
}

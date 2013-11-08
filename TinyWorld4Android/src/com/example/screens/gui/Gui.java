package com.example.screens.gui;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.entity.Player;
import com.example.res.ResLoader;
import com.example.tinyworld4android.GameCanvas;

public class Gui {
	
	private GameCanvas canvas;
	private Player p;
	
	public Gui(Player p, GameCanvas canvas) {
		this.canvas = canvas;
		this.p = p;
	}
	
	public void render(Canvas g) {
		g.drawBitmap(ResLoader.get(ResLoader.GUI), 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));
		
		Paint white = new Paint();
		white.setColor(color.white);
				
		g.drawText(Long.toString(canvas.fps), 38, 13, white);
		
		if (p.items != null) {
			p.items.renderIcon(g, 58, 15);
			g.drawText(Integer.toString(p.items.getNumber()), 81, 27, white);
		} else {
			g.drawRect(58, 15, ResLoader.TILE_SIZE, ResLoader.TILE_SIZE, white);
		}
	}

}
package com.example.screens;

import android.graphics.Canvas;

public abstract class Screen {
	
	public abstract void tick();
	public abstract void render(Canvas g);

}

package com.example.screens;

import android.graphics.Bitmap;

public abstract class Screen {
	
	public abstract void tick();
	
	public abstract Bitmap render();
	
	public abstract void moveNext();
	public abstract void movePrev();

}

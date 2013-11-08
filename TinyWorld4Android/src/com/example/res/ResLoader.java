package com.example.res;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tinyworld4android.R;

public class ResLoader {

	public static final int TILE_SIZE = 16;

	public static final int TILE_GRASS = 0;
	public static final int TILE_WATER0 = 1;
	public static final int TILE_WATER1 = 2;
	public static final int TILE_SAND = 3;
	public static final int TILE_TOWER = 4;
	public static final int TILE_TOWER_GLOW = 5;
	public static final int TILE_BULLET = 6;

	public static final int SCREEN_GAMEOVER = 16;
	public static final int SCREEN_GAMEWON = 17;
	public static final int SCREEN_TITLESCREEN = 18;
	public static final int SCREEN_TUT1 = 19;
	public static final int SCREEN_TUT2 = 20;
	public static final int SCREEN_TUT3 = 21;
	public static final int SCREEN_TUT4 = 22;
	public static final int SCREEN_TUT5 = 23;
	public static final int SCREEN_TUT6 = 24;
	public static final int SCREEN_TUT7 = 25;
	public static final int SCREEN_TUT8 = 26;

	public static final int BUILD_HOME = 32;
	public static final int BUILD_FABRIC = 33;
	public static final int BUILD_TREE = 34;
	public static final int BUILD_BASE = 35;

	public static final int GUI = 48;
	public static final int GUI_STORAGE = 49;
	public static final int GUI_SELECTOR = 50;
	public static final int GUI_FABRIC = 51;
	public static final int GUI_WOODITEM = 52;
	public static final int GUI_FABRICITEM = 53;
	public static final int GUI_BASEICON = 54;
	public static final int GUI_BASE = 55;

	public static final int ANIM_PLAYER = 0;
	public static final int ANIM_BAT = 1;
	public static final int ANIM_GLOB = 2;
	public static final int ANIM_BAT_EYES = 3;
	public static final int ANIM_GLOB_EYES = 4;
	public static final int ANIM_ARROW = 5;

	public static final int Right = 0;
	public static final int Left = 1;
	public static final int Up = 2;
	public static final int Down = 3;

	private static Bitmap[] imgs;
	private static Bitmap[][][] anims;

	public static void loadImgs(Resources resources) {
		imgs = new Bitmap[64];
		anims = new Bitmap[64][4][];
		
		imgs[TILE_GRASS] =  BitmapFactory.decodeResource(resources, R.drawable.tile_img).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_WATER0] = BitmapFactory.decodeResource(resources, R.drawable.water_img0).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_WATER1] = BitmapFactory.decodeResource(resources, R.drawable.water_img1).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_SAND] = BitmapFactory.decodeResource(resources, R.drawable.sand).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_TOWER] = BitmapFactory.decodeResource(resources, R.drawable.tower).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_TOWER_GLOW] = BitmapFactory.decodeResource(resources, R.drawable.tower_glow).copy(Bitmap.Config.ARGB_8888, true);
		imgs[TILE_BULLET] = BitmapFactory.decodeResource(resources, R.drawable.tower_bullet).copy(Bitmap.Config.ARGB_8888, true);

		imgs[SCREEN_GAMEOVER] = BitmapFactory.decodeResource(resources, R.drawable.loose).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_GAMEWON] = BitmapFactory.decodeResource(resources, R.drawable.win).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TITLESCREEN] = BitmapFactory.decodeResource(resources, R.drawable.titlescreen).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT1] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic1).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT2] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic2).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT3] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic3).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT4] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic4).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT5] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic5).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT6] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic6).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT7] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic7).copy(Bitmap.Config.ARGB_8888, true);
		imgs[SCREEN_TUT8] = BitmapFactory.decodeResource(resources, R.drawable.tutorial_pic8).copy(Bitmap.Config.ARGB_8888, true);

		imgs[BUILD_HOME] = BitmapFactory.decodeResource(resources, R.drawable.home).copy(Bitmap.Config.ARGB_8888, true);
		imgs[BUILD_FABRIC] = BitmapFactory.decodeResource(resources, R.drawable.fabric).copy(Bitmap.Config.ARGB_8888, true);
		imgs[BUILD_TREE] = BitmapFactory.decodeResource(resources, R.drawable.tree).copy(Bitmap.Config.ARGB_8888, true);
		imgs[BUILD_BASE] = BitmapFactory.decodeResource(resources, R.drawable.tower_base).copy(Bitmap.Config.ARGB_8888, true);

		imgs[GUI] = BitmapFactory.decodeResource(resources, R.drawable.gui).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_STORAGE] = BitmapFactory.decodeResource(resources, R.drawable.gui_storage).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_SELECTOR] = BitmapFactory.decodeResource(resources, R.drawable.selector).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_FABRIC] = BitmapFactory.decodeResource(resources, R.drawable.gui_fabric).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_WOODITEM] = BitmapFactory.decodeResource(resources, R.drawable.wood).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_FABRICITEM] = BitmapFactory.decodeResource(resources, R.drawable.fabric_icon).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_BASEICON] = BitmapFactory.decodeResource(resources, R.drawable.tower_base_icon).copy(Bitmap.Config.ARGB_8888, true);
		imgs[GUI_BASE] = BitmapFactory.decodeResource(resources, R.drawable.gui_base).copy(Bitmap.Config.ARGB_8888, true);

		anims[ANIM_PLAYER][Right] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.player_walk_right).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_PLAYER][Left] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.player_walk_left).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_PLAYER][Up] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.player_walk_up).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_PLAYER][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.player_walk_down).copy(Bitmap.Config.ARGB_8888, true), 2);

		anims[ANIM_BAT][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.bat).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_GLOB][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.glob).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_BAT_EYES][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.bat_eyes).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_GLOB_EYES][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.glob_eyes).copy(Bitmap.Config.ARGB_8888, true), 2);
		anims[ANIM_ARROW][Down] = loadAnim(BitmapFactory.decodeResource(resources, R.drawable.arrow).copy(Bitmap.Config.ARGB_8888, true), 2);
	}

	public static Bitmap get(int id) {
		return imgs[id];
	}

	public static Bitmap[] anim(int id, int dir) {
//		Bitmap[] val = anims[id][dir];
//		
//		if(val == null)
//			System.out.println(String.format("%d %s", id, dir ));
		
		return anims[id][dir];
	}

	public static Bitmap[] loadAnim(Bitmap src, int num) {
		Bitmap[] animation = new Bitmap[num];

		int w = src.getWidth() / num;
		int h = src.getHeight();

		for (int i = 0; i < num; i++) {
			animation[i] = Bitmap.createBitmap(src, i * w, 0, w, h);
		}

		return animation;
	}
}

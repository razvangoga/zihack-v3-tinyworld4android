package com.example.scout2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onLoadResourceClick(View view){

    	Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).copy(Bitmap.Config.ARGB_8888, true);
    	Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.home).copy(Bitmap.Config.ARGB_8888, true);
    	
    	Canvas c = new Canvas(bitmap1);
    	Paint p = new Paint(Paint.FILTER_BITMAP_FLAG);
    	
    	c.drawBitmap(bitmap2, 0 ,0, p);
    	
    	ImageView iv = (ImageView)findViewById(R.id.imageView1);    	
    	iv.setImageBitmap(bitmap1);
    	
    }
    
}

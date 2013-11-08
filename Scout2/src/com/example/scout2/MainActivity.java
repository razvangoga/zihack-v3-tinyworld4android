package com.example.scout2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    	ImageView iv = (ImageView)findViewById(R.id.imageView1);
    	
    	iv.setImageBitmap(bitmap);
    	
    }
    
}

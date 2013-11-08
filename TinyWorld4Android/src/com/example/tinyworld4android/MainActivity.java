package com.example.tinyworld4android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private GameCanvas canvas = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView imageViewGame = (ImageView)findViewById(R.id.imageViewGame);
        
        canvas = new GameCanvas(imageViewGame);
        canvas.start();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	
    	canvas.stop();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	canvas.start();
    }
        
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.action_forward: {
			this.canvas.moveNext();
			return true;
		}    	
		case R.id.action_back: {
			this.canvas.movePrev();
			return true;
		}
		case R.id.action_play: {
			this.canvas.play();
			return true;
		}		
		default:
			return false;
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

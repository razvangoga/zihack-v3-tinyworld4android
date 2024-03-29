package com.example.tinyworld4android;

import com.example.screens.gui.UserAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

public class MainActivity extends Activity {

	private GameCanvas canvas = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        setContentView(R.layout.activity_main);
        
        ImageView imageViewGame = (ImageView)findViewById(R.id.imageViewGame);

        canvas = new GameCanvas(imageViewGame);
        canvas.start();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	
    	//canvas.stop();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	//canvas.start();
    }
        
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	    	
    	switch (item.getItemId()) {
		case R.id.action_forward: {
			this.canvas.moveNext();
	    	setButtonsVisibility();
			return true;
		}    	
		case R.id.action_back: {
			this.canvas.movePrev();
	    	setButtonsVisibility();
			return true;
		}
		case R.id.action_play: {
			this.canvas.play();
	    	setButtonsVisibility();
			return true;
		}		
		default:
			return false;
		}
    }
    
    private void setButtonsVisibility() {
    	int tableVisibility = this.canvas.isGameScreen() ? View.VISIBLE : View.INVISIBLE;
    	
    	TableLayout directionsTable = (TableLayout)findViewById(R.id.directionsTable);
    	TableLayout actionsTable = (TableLayout)findViewById(R.id.actionsTable);
    	
    	directionsTable.setVisibility(tableVisibility);
    	actionsTable.setVisibility(tableVisibility);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onUpClick(View view) {
        this.canvas.handleUserAction(UserAction.Up);
    }    
    
    public void onDownClick(View view) {
    	this.canvas.handleUserAction(UserAction.Down);    
    }
    
    public void onLeftClick(View view) {
    	this.canvas.handleUserAction(UserAction.Left);        
    }    
    
    public void onRightClick(View view) {
    	this.canvas.handleUserAction(UserAction.Right);    
    } 
    
    public void onUseClick(View view) {
    	this.canvas.handleUserAction(UserAction.Use);
    } 
    
    public void onCloseClick(View view) {
    	this.canvas.handleUserAction(UserAction.Close);        
    }

    public void onMoveClick(View view) {
    	this.canvas.handleUserAction(UserAction.Move);        
    } 
}

package com.AI.wj2048;

import  com.AI.wj2048.WJ2048;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WJ2048 extends ActionBarActivity {
	
	private int score;
	private TextView tvScore;
	private String orientation;
	private TextView tvOrientation;
	private static WJ2048 instance;
	
	public WJ2048(){
		instance = this;
	}
	
	public static WJ2048 getInstance(){
		return instance;
	}
	
	public void clearScore(){
		this.score = 0;
		tvScore.setText(score+"");
	}
	
	public void addScore(int s){
		this.score+=s;
		showScore();
	}
	
	public void showScore(){
		tvScore.setText(score+"");
	}

	public void showOrientation(){
		tvOrientation.setText(orientation);
	}
	
	public void setOrientation(String string){
    	orientation = string;
    	showOrientation();
    }
	
	public void finishDialog(){
		new AlertDialog.Builder(this)
        .setTitle("Replay Game?")  
        .setPositiveButton("replay",  
                new DialogInterface.OnClickListener() {  
                    @Override  
                    public void onClick(DialogInterface dialog, int which) {  
                        // TODO Auto-generated method stub  
                    	GameView.instance.replay();
                    }  
                })
         .setNegativeButton("exit",
        		 new DialogInterface.OnClickListener() {  
	             @Override  
	             public void onClick(DialogInterface dialog, int which) {  
	                 // TODO Auto-generated method stub  
	            	 finish();
	             }  
         })
         .create()
        .show(); 
	}

	public void reach2048Dialog(){
		new AlertDialog.Builder(this)
        .setTitle("Replay Game?")
        .setMessage("Replay or Continue?")
        .setPositiveButton("replay",  
                new DialogInterface.OnClickListener() {  
                    @Override  
                    public void onClick(DialogInterface dialog, int which) {  
                        // TODO Auto-generated method stub  
                    	GameView.instance.replay();
                    }  
                })
         .setNegativeButton("exit", null)
         .create()
        .show(); 
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wj2048);
        tvScore = (TextView)findViewById(R.id.score);	
		tvOrientation = (TextView)findViewById(R.id.nextOrientation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wj2048, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wj2048, container, false);
            return rootView;
        }
    }

}

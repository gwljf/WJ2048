package com.AI.wj2048;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

@SuppressLint({ "ShowToast"}) 
public class GameView  extends GridLayout {
	
	private Board board = new Board(getContext());;
	public Handler myHandler = new MessageHandler(Looper.myLooper());
	public static GameView instance;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		instance = this;
		initGameView();
	}


	private void initGameView(){
		setRowCount(4);
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		int titleWidth = (Math.min(w, h)-10)/4;
		addTitles(titleWidth,titleWidth);
//		startAIGame();
		startGame();
	}
	
	public void replay(){
		board.replay();
		startGame();
	}
	
	private void startGame(){
		WJ2048.getInstance().clearScore();
		board.initEmptyPoints();
		board.addRamdonNum();
		board.addRamdonNum();
		setOnTouchListener(new OnTouchListener() {
			private float startX,startY,offsetX,offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						startX = event.getX();
						startY = event.getY();
						break;
					case MotionEvent.ACTION_UP:
						offsetX = event.getX()-startX;
						offsetY = event.getY()-startY;
						if(Math.abs(offsetX)>Math.abs(offsetY)){
							if (offsetX<-5) {
								if(board.canSwipeLeft()){
									board.swipeLeft();
									if(board.emptyPoints.size()>0){
										board.addRamdonNum();
									}
								}
							}else if(offsetX>5){
								if(board.canSwipeRight()){
									board.swipeRight();
									if(board.emptyPoints.size()>0){
										board.addRamdonNum();
									}
								}
							}
						}else {
							if (offsetY<-5) {
								if(board.canSwipeUp()){
									board.swipeUp();
									if(board.emptyPoints.size()>0){
										board.addRamdonNum();
									}
								}
							}else if(offsetY>5) {
								if(board.canSwipeDown()){
									board.swipeDown();
									if(board.emptyPoints.size()>0){
										board.addRamdonNum();
									}
								}
							}
						}
						if(board.reach2048()){
							WJ2048.getInstance().reach2048Dialog();
						}
						if(board.isFinished()){
							WJ2048.getInstance().finishDialog();
						}
						break;
					default:
						break;
				}
			return true;
			}
		});
		
	}

	
	public void BoardMoveUP(){
		if(board.canSwipeUp()){
			board.swipeUp();
			if(board.emptyPoints.size()>0){
				board.addRamdonNum();
			}
		}
	}
	
	public void BoardMoveDOWN(){
		if(board.canSwipeDown()){
			board.swipeDown();
			if(board.emptyPoints.size()>0){
				board.addRamdonNum();
			}
		}
	}
	
	public void BoardMoveLEFT(){
		if(board.canSwipeLeft()){
			board.swipeLeft();
			if(board.emptyPoints.size()>0){
				board.addRamdonNum();
			}
		}
	}
	
	public void BoardMoveRIGHT(){
		if(board.canSwipeRight()){
			board.swipeRight();
			if(board.emptyPoints.size()>0){
				board.addRamdonNum();
			}
		}
	}
	
	public void AddRandomNum(){
		if(board.emptyPoints.size()>0){
			board.addRamdonNum();
		}
	}
	
	private void startAIGame(){
		WJ2048.getInstance().clearScore();
		board.initEmptyPoints();
		board.addRamdonNum();
		board.addRamdonNum();
		new Thread(){
			@Override
			synchronized public void run(){
				while(!board.isFinished()){
					Message message = Message.obtain();
					Direction direction = AISolver.findBestMove(board, 1);
					switch(direction){
						case LEFT:
							message.arg1 = 1;
							break;
						case RIGHT:
							message.arg1 = 2;
							break;
						case UP:
							message.arg1 = 3;
							break;
						case DOWN:
							message.arg1 = 4;
							break;
						default:
							break;
					}
					myHandler.sendMessage(message);
				}
			}
		}.start();
	}
	
	private void addTitles(int titleWidth,int titleHeight){
		for(int x = 0;x<4;x++){
			for(int y=0;y<4;y++){
				addView(board.getTitle(x, y), titleWidth, titleHeight);
			}
		}
	}
	
    class MessageHandler extends Handler {
        public MessageHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            switch(msg.arg1){
	            case 1:
	            	BoardMoveLEFT();
	            	break;
	            case 2:
	            	BoardMoveRIGHT();
	            	break;
	            case 3:
	            	BoardMoveUP();
	            	break;
	            case 4:
	            	BoardMoveDOWN();
	            	break;
            }
        }
    }
}

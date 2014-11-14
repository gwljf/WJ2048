package com.AI.wj2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;

public class Board{
	public int score = 0;
	public Title[][] titles = new Title[4][4];
	public Map<Point,Integer> emptyPoints = new HashMap<Point, Integer>();
	public Context context;
	
	public Board(Context _context){
		context = _context;
		score = 0;
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				titles[x][y] = new Title(context);
				titles[x][y].setNum(0);
			}
		}
	}
	
	public void replay(){
		score = 0;
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				titles[x][y].setNum(0);
			}
		}
		emptyPoints.clear();
	}
	
	
	public void initEmptyPoints(){
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				emptyPoints.put(new Point(x,y), 0);
			}
		}
	}
	
	public Title getTitle(int x, int y){
		return titles[x][y];
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean reach2048(){
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				if(titles[x][y].getNum()==2048){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isFinished(){
		if(emptyPoints.size()!=0){
			return false;
		}
		if(!canSwipeUp() && !canSwipeDown() && !canSwipeLeft() && !canSwipeRight()){
			return true;
		}else{
			return false;
		}
	}
	
    public int move(Direction direction) {
        int points = 0;
        switch(direction){
	        case LEFT:
	        	points += swipeLeft();
	        	break;
	        case RIGHT:
	        	points += swipeRight();
	        	break;
	        case UP:
	        	points += swipeUp();
	        	break;
	        case DOWN:
	        	points += swipeDown();
	        	break;
        	default:
        		break;
        }
        score += points;
        return points;
    }
    
	public int swipeLeft(){
		int point = 0;
		for (int x = 0; x < 4; x++){
			int Index = 0;
			while(Index<3){
				boolean Indexupdated = false;
				for(int cur=Index+1; cur<4; cur++){
					if(titles[x][cur].getNum()!=0){
						if(titles[x][Index].getNum()==0){
							WJ2048.getInstance().setOrientation(" LEFT");
							titles[x][Index].setNum(titles[x][cur].getNum());
							titles[x][cur].setNum(0);
							titles[x][Index].invalidate();
							titles[x][cur].invalidate();
							emptyPoints.remove(new Point(x,Index));
							emptyPoints.put(new Point(x,cur), 0);
						}else{
							if(titles[x][cur].equals(titles[x][Index])){
								WJ2048.getInstance().setOrientation(" LEFT");
								titles[x][Index].setNum(titles[x][Index].getNum()<<1);
								WJ2048.getInstance().addScore(titles[x][Index].getNum());
								point = titles[x][Index].getNum();
								titles[x][cur].setNum(0);
								titles[x][Index].invalidate();
								titles[x][cur].invalidate();
								emptyPoints.put(new Point(x,cur),0);
								Index++;
								Indexupdated=true;
								break;
							}else{
								Index++;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index++;
				}
			}
		}
		return point;
	}
	

	public int swipeRight(){
		int point = 0;
		for (int x = 0; x < 4; x++) {
			int Index = 3;
			while(Index>0){
				boolean Indexupdated=false;
				for(int cur=Index-1; cur>=0; cur--){
					if(titles[x][cur].getNum()!=0){
						if(titles[x][Index].getNum()==0){
							WJ2048.getInstance().setOrientation(" RIGHT");
							titles[x][Index].setNum(titles[x][cur].getNum());
							titles[x][cur].setNum(0);
							titles[x][Index].invalidate();
							titles[x][cur].invalidate();
							emptyPoints.remove(new Point(x,Index));
							emptyPoints.put(new Point(x,cur), 0);
						}else{
							if(titles[x][cur].equals(titles[x][Index])){
								WJ2048.getInstance().setOrientation(" RIGHT");
								titles[x][Index].setNum(titles[x][Index].getNum()<<1);
								WJ2048.getInstance().addScore(titles[x][Index].getNum());
								point = titles[x][Index].getNum();
								titles[x][cur].setNum(0);
								titles[x][Index].invalidate();
								titles[x][cur].invalidate();
								emptyPoints.put(new Point(x,cur),0);
								Index--;
								Indexupdated=true;
								break;
							}else{
								Index--;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index--;
				}
			}
		}
		return point;
	}
	

	public int swipeUp(){
		int point = 0;
		for (int y = 0; y < 4; y++) {
			int Index = 0;
			while(Index<3){
				boolean Indexupdated=false;
				for(int cur=Index+1; cur<4; cur++){
					if(titles[cur][y].getNum()!=0){
						if(titles[Index][y].getNum()==0){
							WJ2048.getInstance().setOrientation(" UP");
							titles[Index][y].setNum(titles[cur][y].getNum());
							titles[cur][y].setNum(0);
							titles[Index][y].invalidate();
							titles[cur][y].invalidate();
							emptyPoints.remove(new Point(Index,y));
							emptyPoints.put(new Point(cur,y), 0);
						}else{
							if(titles[cur][y].equals(titles[Index][y])){
								WJ2048.getInstance().setOrientation(" UP");
								titles[Index][y].setNum(titles[Index][y].getNum()<<1);
								WJ2048.getInstance().addScore(titles[Index][y].getNum());
								point = titles[Index][y].getNum();
								titles[cur][y].setNum(0);
								titles[Index][y].invalidate();
								titles[cur][y].invalidate();
								emptyPoints.put(new Point(cur,y),0);
								Index++;
								Indexupdated=true;
								break;
							}else{
								Index++;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index++;
				}
			}
		}
		return point;
	}
	

	public int swipeDown(){
		int point = 0;
		for (int y = 0; y < 4; y++) {
			int Index = 3;
			while(Index>0){
				boolean Indexupdated=false;
				for(int cur=Index-1; cur>=0; cur--){
					if(titles[cur][y].getNum()!=0){
						if(titles[Index][y].getNum()==0){
							WJ2048.getInstance().setOrientation(" DOWN");
							titles[Index][y].setNum(titles[cur][y].getNum());
							titles[cur][y].setNum(0);
							titles[Index][y].invalidate();
							titles[cur][y].invalidate();
							emptyPoints.remove(new Point(Index,y));
							emptyPoints.put(new Point(cur,y), 0);
						}else{
							if(titles[cur][y].equals(titles[Index][y])){
								WJ2048.getInstance().setOrientation(" DOWN");
								titles[Index][y].setNum(titles[Index][y].getNum()<<1);
								WJ2048.getInstance().addScore(titles[Index][y].getNum());
								point = titles[Index][y].getNum();
								titles[cur][y].setNum(0);
								titles[Index][y].invalidate();
								titles[cur][y].invalidate();
								emptyPoints.put(new Point(cur,y),0);
								Index--;
								Indexupdated=true;
								break;
							}else{
								Index--;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index--;
				}
			}
		}
		return point;
	}
	
	public boolean canSwipeLeft(){
		int[][] board = new int[4][4];
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				board[x][y]=titles[x][y].getNum();
			}
		}
		for (int x = 0; x < 4; x++){
			int Index = 0;
			while(Index<3){
				boolean Indexupdated = false;
				for(int cur=Index+1; cur<4; cur++){
					if(board[x][cur]!=0){
						if(board[x][Index]==0){
							return true;
						}else{
							if(board[x][cur]==board[x][Index]){
								return true;
							}else{
								Index++;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index++;
				}
			}
		}
		return false;
	}
	
	public boolean canSwipeRight(){
		int[][] board = new int[4][4];
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				board[x][y]=titles[x][y].getNum();
			}
		}
		for (int x = 0; x < 4; x++) {
			int Index = 3;
			while(Index>0){
				boolean Indexupdated=false;
				for(int cur=Index-1; cur>=0; cur--){
					if(board[x][cur]!=0){
						if(board[x][Index]==0){
							return true;
						}else{
							if(board[x][cur]==board[x][Index]){
								return true;
							}else{
								Index--;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index--;
				}
			}
		}
		return false;
	}
	
	public boolean canSwipeUp(){
		int[][] board = new int[4][4];
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				board[x][y]=titles[x][y].getNum();
			}
		}
		for (int y = 0; y < 4; y++) {
			int Index = 0;
			while(Index<3){
				boolean Indexupdated=false;
				for(int cur=Index+1; cur<4; cur++){
					if(board[cur][y]!=0){
						if(board[Index][y]==0){
							return true;
						}else{
							if(board[cur][y]==board[Index][y]){
								return true;
							}else{
								Index++;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index++;
				}
			}
		}
		return false;
	}
	

	public boolean canSwipeDown(){
		int[][] board = new int[4][4];
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				board[x][y]=titles[x][y].getNum();
			}
		}
		for (int y = 0; y < 4; y++) {
			int Index = 3;
			while(Index>0){
				boolean Indexupdated=false;
				for(int cur=Index-1; cur>=0; cur--){
					if(board[cur][y]!=0){
						if(board[Index][y]==0){
							return true;
						}else{
							if(board[cur][y]==board[Index][y]){
								return true;
							}else{
								Index--;
								Indexupdated=true;
								break;
							}
						}
					}
				}
				if(!Indexupdated){
					Index--;
				}
			}
		}
		return false;
	}
	
	public void addRamdonNum(){
		Random random = new Random();
		List<Point> keys = new ArrayList<Point>(emptyPoints.keySet());
		Point randomKey = keys.get( random.nextInt(keys.size()) );
		Point point = randomKey;
		emptyPoints.remove(point);
		int number = Math.random()>0.1?2:4;
		titles[point.x][point.y].setNum(number);
	}

	
}

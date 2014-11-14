package com.AI.wj2048;

import java.util.ArrayList;
import java.util.List;


public class AIBoard {
	public int score = 0;
	public int[][] aiBoard = new int[4][4];
	
	public AIBoard(int[][] _board){
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				aiBoard[x][y]=_board[x][y];
			}
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
					if(aiBoard[x][cur]!=0){
						if(aiBoard[x][Index]==0){
							aiBoard[x][Index]=(aiBoard[x][cur]);
							aiBoard[x][cur]=(0);
						}else{
							if(aiBoard[x][cur]==(aiBoard[x][Index])){
								aiBoard[x][Index]=(aiBoard[x][Index]<<1);
								point = aiBoard[x][Index];
								aiBoard[x][cur]=(0);
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
					if(aiBoard[x][cur]!=0){
						if(aiBoard[x][Index]==0){
							aiBoard[x][Index]=(aiBoard[x][cur]);
							aiBoard[x][cur]=(0);
						}else{
							if(aiBoard[x][cur]==(aiBoard[x][Index])){
								aiBoard[x][Index]=(aiBoard[x][Index]<<1);
								point = aiBoard[x][Index];
								aiBoard[x][cur]=(0);
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
					if(aiBoard[cur][y]!=0){
						if(aiBoard[Index][y]==0){
							aiBoard[Index][y]=(aiBoard[cur][y]);
							aiBoard[cur][y]=(0);
						}else{
							if(aiBoard[cur][y]==(aiBoard[Index][y])){
								aiBoard[Index][y]=(aiBoard[Index][y]<<1);
								point = aiBoard[Index][y];
								aiBoard[cur][y]=(0);
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
					if(aiBoard[cur][y]!=0){
						if(aiBoard[Index][y]==0){
							aiBoard[Index][y]=(aiBoard[cur][y]);
							aiBoard[cur][y]=(0);
						}else{
							if(aiBoard[cur][y]==(aiBoard[Index][y])){
								aiBoard[Index][y]=(aiBoard[Index][y]<<1);
								point = aiBoard[Index][y];
								aiBoard[cur][y]=(0);
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
				board[x][y]=aiBoard[x][y];
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
				board[x][y]=aiBoard[x][y];
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
				board[x][y]=aiBoard[x][y];
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
				board[x][y]=aiBoard[x][y];
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
	
    public List<Integer> getEmptyCellIds() {
        List<Integer> cellList = new ArrayList<Integer>();
        
        for(int i=0;i<4;++i) {
            for(int j=0;j<4;++j) {
                if(aiBoard[i][j]==0) {
                    cellList.add(4*i+j);
                }
            }
        }
        
        return cellList;
    }
	
    public void setEmptyCell(int i, int j, int value) {
        if(aiBoard[i][j]==0) {
            aiBoard[i][j]=value;
        }
    }
}

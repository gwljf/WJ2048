package com.AI.wj2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AISolver {
	   /**
  * Player vs Computer enum class
  */
 public enum Player {
     /**
      * Computer
      */
     COMPUTER, 

     /**
      * User
      */
     USER
 }
 
 /**
  * Method that finds the best next move.
  * 
  * @param theBoard
  * @param depth
  * @return
  * @throws CloneNotSupportedException 
  */
 synchronized public static Direction findBestMove(Board theBoard, int depth){
     //Map<String, Object> result = minimax(theBoard, depth, Player.USER);
	 int[][] boardArray = new int[4][4];
	 for(int x=0; x<4; x++){
		 for(int y=0; y<4; y++){
			 boardArray[x][y]=theBoard.titles[x][y].getNum();
		 }
	 }
	 AIBoard aiboard = new AIBoard(boardArray);
     Map<String, Object> result = alphabeta(aiboard, depth, Player.USER);
     return (Direction)result.get("Direction");
 }
 
 
 private static Map<String, Object> alphabeta(AIBoard theBoard, int depth,Player player){
     Map<String, Object> result = new HashMap<String, Object>();
     
     Direction bestDirection = null;
     int bestScore = Integer.MIN_VALUE;
     if(depth==0) {
         bestScore=heuristicScore(theBoard);
     }
     else {
         if(player == Player.USER) {
        	 ArrayList<Direction> directionAL = new ArrayList<Direction>();
        	 directionAL.add(Direction.LEFT);
        	 directionAL.add(Direction.DOWN);
        	 directionAL.add(Direction.UP);
        	 directionAL.add(Direction.RIGHT);
        	 bestScore = Integer.MIN_VALUE;
             for(Direction direction : directionAL) {
            	 int[][] boardArray = new int[4][4];
            	 for(int x=0; x<4; x++){
            		 for(int y=0; y<4; y++){
            			 boardArray[x][y]=theBoard.aiBoard[x][y];
            		 }
            	 }
            	
            	System.out.println("direction-->"+direction);
             	AIBoard newBoard = new AIBoard(boardArray);
             	if(direction==Direction.UP && newBoard.canSwipeUp()){
             		 newBoard.move(direction);
                     Map<String, Object> currentResult = alphabeta(newBoard, depth-1, Player.COMPUTER);
                     int currentScore=((Number)currentResult.get("Score")).intValue();
                     if(currentScore>=bestScore) {
                    	 System.out.println("yes");
                         bestScore=currentScore;
                         bestDirection=direction;
                     }
                     System.out.println("curScore-->"+currentScore+"  direction-->"+direction);
             	}else if(direction==Direction.DOWN && newBoard.canSwipeDown()){
             		 newBoard.move(direction);
                     Map<String, Object> currentResult = alphabeta(newBoard, depth-1, Player.COMPUTER);
                     int currentScore=((Number)currentResult.get("Score")).intValue();
                     if(currentScore>=bestScore) {
                         bestScore=currentScore;
                         System.out.println("yes");
                         bestDirection=direction;
                     }
                     System.out.println("curScore-->"+currentScore+"  direction-->"+direction);
             	}else if(direction==Direction.LEFT && newBoard.canSwipeLeft()){
             		 newBoard.move(direction);
                     Map<String, Object> currentResult = alphabeta(newBoard, depth-1, Player.COMPUTER);
                     int currentScore=((Number)currentResult.get("Score")).intValue();
                     if(currentScore>=bestScore) {
                    	 bestScore=currentScore;
                    	 System.out.println("yes");
                         bestDirection=direction;
                     }
                     System.out.println("curScore-->"+currentScore+"  direction-->"+direction);
             	}else if(direction==Direction.RIGHT && newBoard.canSwipeRight()){
             		newBoard.move(direction);
                     Map<String, Object> currentResult = alphabeta(newBoard, depth-1,Player.COMPUTER);
                     int currentScore=((Number)currentResult.get("Score")).intValue();
                     if(currentScore>=bestScore) {
                    	 bestScore=currentScore;
                    	 System.out.println("yes");
                         bestDirection=direction;
                     }
                     System.out.println("curScore-->"+currentScore+"  direction-->"+direction);
             	}
             }
         }
         else {
             List<Integer> moves = theBoard.getEmptyCellIds();
             int[] possibleValues = {2,4};
             int i,j;
             for(Integer cellId : moves) {
                 i = cellId/4;
                 j = cellId%4;

                 for(int value : possibleValues) {
                	 int[][] boardArray = new int[4][4];
                	 for(int x=0; x<4; x++){
                		 for(int y=0; y<4; y++){
                			 boardArray[x][y]=theBoard.aiBoard[x][y];
                		 }
                	 }
                 	 AIBoard newBoard = new AIBoard(boardArray);
                     newBoard.setEmptyCell(i, j, value);
                     Map<String, Object> currentResult = alphabeta(newBoard, depth-1, Player.USER);
                 }
             }
         }
     }
     
     result.put("Score", bestScore);
     result.put("Direction", bestDirection);
     System.out.println("best score-->"+bestScore+"  best Direction-->"+bestDirection+"  "+player+"  depth-->"+depth);
     return result;
 }
 
 /**
  * Estimates a heuristic score by taking into account the real score, the
  * number of empty cells and the clustering score of the board.
  * 
  * @param actualScore
  * @param numberOfEmptyCells
  * @param clusteringScore
  * @return 
  */
// private static int heuristicScore(int actualScore, int numberOfEmptyCells, int clusteringScore) {
////      int score = (int) (actualScore+Math.log(actualScore)*numberOfEmptyCells -clusteringScore);
//     int score = (int) (actualScore+Math.log(actualScore)*numberOfEmptyCells -clusteringScore+100*numberOfEmptyCells);
//     return Math.max(score, Math.min(actualScore, 5));
// }
	 private static int heuristicScore(AIBoard theboard) {
	     int score = 0;
	     score = (int) (theboard.aiBoard[0][0]*Math.pow(0.5, 0)*10000+
	    		 theboard.aiBoard[0][1]*Math.pow(0.5, 0)*100+
	    		 theboard.aiBoard[0][2]*Math.pow(0.5, 1)*100+
	    		 theboard.aiBoard[0][3]*Math.pow(0.5, 2)*100+
	    		 theboard.aiBoard[1][3]*Math.pow(0.5, 3)*100+
	    		 theboard.aiBoard[1][2]*Math.pow(0.5, 4)*100+
	    		 theboard.aiBoard[1][1]*Math.pow(0.5, 5)*100+
	    		 theboard.aiBoard[1][0]*Math.pow(0.5, 6)*100+
	    		 theboard.aiBoard[2][0]*Math.pow(0.5, 7)*100+
	    		 theboard.aiBoard[2][1]*Math.pow(0.5, 8)*100+
	    		 theboard.aiBoard[2][2]*Math.pow(0.5, 9)*100+
	    		 theboard.aiBoard[2][3]*Math.pow(0.5, 10)*100+
	    		 theboard.aiBoard[3][3]*Math.pow(0.5, 11)*100+
	    		 theboard.aiBoard[3][2]*Math.pow(0.5, 12)*100+
	    		 theboard.aiBoard[3][1]*Math.pow(0.5, 13)*100+
	    		 theboard.aiBoard[3][0]*Math.pow(0.5, 14)*100);
	     return score;
	 }

}

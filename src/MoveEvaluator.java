

/**
 * This class will focus on the implementation of the whether the move picked by
 * a player is valid ( It must not contain any gui related component and
 * nothing of gui can be changed from here). you can define other methods 
 * to assist your self and manage your code.
 * 
 * You can make constructor of this class if you feel need for it.
 * 
 */
//package bomborman;


//import bomborman.Types.*;


public class MoveEvaluator {
    
    static public boolean isValidMove( MapBasicBlock player, Types.Move move){ //checks adjacent blocks if move is possible
    	
    	int playerR=0;
    	int playerC=0;
    	
    	if (move == Types.Move.RIGHT || move == Types.Move.DOWN){
    		playerR = (player.getPosition().getRow()-10) /50;
    		playerC = (player.getPosition().getColumn()-10) /50;
    	}
    	else if (move == Types.Move.LEFT || move == Types.Move.UP){
    		playerR = (player.getPosition().getRow()+20) /50;
    		playerC = (player.getPosition().getColumn()+20) /50;
    	}

    	
    	if (move == Types.Move.UP){
    		if (player.getPosition().getColumn() <=75){
    			return false;
    		}
    		if (RandomTest.m.map.map[playerR][playerC-1] == null){
    			return true;
    		}
    	}
    	else if(move == Types.Move.DOWN){
    		if (player.getPosition().getColumn() >=750){
    			return false;
    		}
    		if (RandomTest.m.map.map[playerR][playerC+1] == null){
    			return true;
    		}
    	}
    	else if(move == Types.Move.LEFT){
    		if (player.getPosition().getRow() <=75){
    			return false;
    		}
    		if (RandomTest.m.map.map[playerR-1][playerC] == null){
    			return true;
    		}
    	}
    	else if(move == Types.Move.RIGHT){
    		if (player.getPosition().getRow() >=750){
    			return false;
    		}
    		if (RandomTest.m.map.map[playerR+1][playerC] == null){
    			return true;
    		}
    	}
    	
    	else if(move == Types.Move.PLACE_BOMB){
    		if (RandomTest.m.bomb!=null){
    			System.out.println("Can't place bomb!");
    			return false;
    		}
    		return true;
    	}
    	return false;
    	
    	
    	
    	
    }
}
    

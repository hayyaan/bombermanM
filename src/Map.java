/*
 * 
 * This class will holde all the information of the map and will have approperiate
 * methods to perform diffrent tasks on map.
 */
//package bomborman;

import java.awt.*;

public class Map {
    
    private int rows, cols;
    public MapBasicBlock[][] map; 
    
//    Enemy e1;
    
    public Map(int _rows, int _cols){
        rows = _rows;
        cols = _cols;
        map = new MapBasicBlock[rows][cols];
        
        MapGeneration();
    }
    
    public int getRows(){
    	return rows;
    }
    
    public int getCols(){
    	return cols;
    }
    
    public MapBasicBlock[][] getMap(){
    	return map;
    }
    
    public void MapGeneration(){ // generated the blocks
    	Image uBlock = MapBasicBlock.loadImage("resources/unbreakBlock.png");
    	Image bBlock = MapBasicBlock.loadImage("resources/breakBlock.png");
    	
    	
    	for (int r=0;r<rows;r++){
    		for (int c=0;c<cols;c++){
    			if(r==0 || c==0 || r==rows-1 || c==cols-1){ //walls
    				map[r][c] = new UnbreakableBlock(new Position((r*50)+25,(c*50)+25),uBlock);
    			}
    			else if (r%2 == 0 && c%2==0){ //
    				map[r][c] = new UnbreakableBlock(new Position((r*50)+25,(c*50)+25),uBlock);
    			}
    			else if ((r%3 == 0 && c%5==0 ) || (r%2 == 0 && c%3==0 ) ||( r%7 == 0 && c%2==0 )){ //
    				map[r][c] = new BreakableBlock(new Position((r*50)+25,(c*50)+25),bBlock);
    			}
    			else {
    				map[r][c] = null;
    			}
    		}
    	}
    	
    	
    }
    
    public boolean checkEnd(){ //if all blocks cleared
    	for (int r=0;r<rows;r++){
    		for (int c=0;c<cols;c++){
    			if (map[r][c] == null){
    				continue;
    			}
    			if( map[r][c].isofType(Types.BlockType.BREAKABLE)){
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }
    
    public boolean checkEndDoor(Player player){
    	int playerR = (player.getPosition().getRow()) /50;
		int playerC = (player.getPosition().getColumn()) /50;

		
		if (playerR == RandomTest.m.endDoorR && playerC == RandomTest.m.endDoorC){
			RandomTest.m.gameEnd=true;
			return true;
		}
		return false;
    }
    
    
}

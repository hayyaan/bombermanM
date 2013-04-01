/*
 * This class will execute the move on the map and change the data and gui 
 * accordingly.
 * 
 * make constructor of this class or other methods if you feel need for it.
 * don't forget dividing your code into classes and methods is good standard
 * practice in professional world.
 */
//package bomborman;


//import bomborman.Types.*;


public class MoveExecutor{ // executes move
    
//	static int movement = 4;
    
    static public void executeMove( MapBasicBlock player, Types.Move move, int movement){
    	Position newPos = null;
    	if (move == Types.Move.UP){
    		newPos = new Position(player.getPosition().getRow(),player.getPosition().getColumn()-movement);
    	}
    	else if(move == Types.Move.DOWN){
    		newPos = new Position(player.getPosition().getRow(),player.getPosition().getColumn()+movement);
    	}
    	else if(move == Types.Move.LEFT){
    		newPos = new Position(player.getPosition().getRow()-movement,player.getPosition().getColumn());
    	}
    	else if(move == Types.Move.RIGHT){
    		newPos = new Position(player.getPosition().getRow()+movement,player.getPosition().getColumn());
    	}
    	else if(move == Types.Move.PLACE_BOMB){
    		int playerR=(int) Math.floor(player.getPosition().getRow() / 50);
        	int playerC=(int) Math.floor(player.getPosition().getColumn() / 50);
    		newPos = new Position(player.getPosition().getRow(),player.getPosition().getColumn());
        		
    			RandomTest.m.map.map[playerR][playerC] = new Bomb(newPos);
        		RandomTest.m.bomb = new Bomb(newPos);
//        		System.out.println("BOMB Planted!");
    	}
    	
    	if (newPos!=null)
    		player.setPosition(newPos);
    	
//		BomborMan.game.repaint();
    	RandomTest.m.repaint();

    }
    
    static public void explodeBomb(Bomb bomb){
    	
    	int bombR=bomb.getPosition().getRow() / 50;
    	int bombC=bomb.getPosition().getColumn() / 50;
    	
    	// System.out.println(bomb.getPosition().getRow());
    	// System.out.println(RandomTest.m.p1.getPosition().getRow());
    	
    	int bR = bomb.getPosition().getRow()+25;
    	int bC = bomb.getPosition().getColumn()+25;
    	
    	int pR = RandomTest.m.p1.getPosition().getRow();
    	int pC = RandomTest.m.p1.getPosition().getColumn();
    	
    	if (RandomTest.m.e1!=null){ //enemy collision
    		
    		int eR = RandomTest.m.e1.getPosition().getRow();
    		int eC = RandomTest.m.e1.getPosition().getColumn();
    		
    		if ( Math.abs((bR -eR))<=50 && Math.abs((bC -eC))<=50 ){
    			System.out.println("Enemy injured in blast radius!");
    			RandomTest.m.e1 = null;
    		}
    	}
        if (RandomTest.m.e2!=null){
            
            int eR = RandomTest.m.e2.getPosition().getRow();
            int eC = RandomTest.m.e2.getPosition().getColumn();
            
            if ( Math.abs((bR -eR))<=50 && Math.abs((bC -eC))<=50 ){
                System.out.println("Enemy injured in blast radius!");
                RandomTest.m.e2 = null;
            }
        }
    	
    	
    	if ( Math.abs((bR -pR))<=50 && Math.abs((bC -pC))<=50 ){ //check player collisions
    		System.out.println("Player injured in blast radius!");
			RandomTest.m.gameOver =true;
			return;
    	}
    	
    	Fire.placeFire(bomb.getPosition()); //place fire
    	
    	
    	
    	for (int r=-1;r<2;r++){
    		for (int c=-1;c<2;c++){
//    			System.out.println("Checking: " + (bombR+r) + " x " + (bombC+c));
    			if (RandomTest.m.map.map[bombR+r][bombC+c] == null){// || RandomTest.m.map.map[bombR+r][bombC+c].isofType(Types.BlockType.UNBREKABLE)){
    				continue;
    			}
    			if (r==-1 && c==-1 || r==-1 && c==1 || r==1 && c==-1 || r==1 && c==1){ //diagonals
    				continue;
    			}
    			else if ( RandomTest.m.map.map[bombR+r][bombC+c].isofType(Types.BlockType.BREAKABLE)){
    				RandomTest.m.map.map[bombR+r][bombC+c] = null;
    				break;
    			}
    		}
    	}
    	RandomTest.m.map.map[bombR][bombC] = null;
    	RandomTest.m.bomb = null;

    	RandomTest.m.repaint();
    	
    	
    }
    
}

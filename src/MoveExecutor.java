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
	static boolean powerUpPlace1 = false;
	static boolean powerUpPlace2 = false;
	static boolean powerUpPlace3 = false;
	static boolean powerUpPlace4 = false;
    
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
    			((Bomb)RandomTest.m.map.map[playerR][playerC]).timer = 400;
        		RandomTest.m.bombArray.add( (Bomb) RandomTest.m.map.map[playerR][playerC] );
        		RandomTest.m.bombOnScreen++;
//        		System.out.println("BOMB Planted!");
    	}
    	
    	if (newPos!=null)
    		player.setPosition(newPos);
    	
//		BomborMan.game.repaint();
    	RandomTest.m.repaint();

    }
    
    static public void explodeBomb(Bomb bomb,Player player){
    	
    	int bombR=bomb.getPosition().getRow() / 50;
    	int bombC=bomb.getPosition().getColumn() / 50;
    	
    	int range = 50;
    	if (player.powerUp!=null){
    		if (player.powerUp.powerUp.equals(Types.PowerUps.RANGE_UP)){
    			range=100;
    		}
    	}
    	System.out.println("range"+range);
    	
    	// System.out.println(bomb.getPosition().getRow());
    	// System.out.println(RandomTest.m.p1.getPosition().getRow());
    	
    	int bR = bomb.getPosition().getRow()+25;
    	int bC = bomb.getPosition().getColumn()+25;
    	
    	int pR = RandomTest.m.p1.getPosition().getRow();
    	int pC = RandomTest.m.p1.getPosition().getColumn();
    	
    	if (RandomTest.m.e1!=null){ //enemy collision
    		
    		int eR = RandomTest.m.e1.getPosition().getRow();
    		int eC = RandomTest.m.e1.getPosition().getColumn();
    		
    		if ( Math.abs((bR -eR))<=range*1.2 && Math.abs((bC -eC))<=range*1.2 ){
    			System.out.println("Enemy injured in blast radius!");
    			RandomTest.m.e1 = null;
    			RandomTest.m.enemyT1.stop();
    		}
    	}
        if (RandomTest.m.e2!=null){
            
            int eR = RandomTest.m.e2.getPosition().getRow();
            int eC = RandomTest.m.e2.getPosition().getColumn();
            
            if ( Math.abs((bR -eR))<=range*1.2 && Math.abs((bC -eC))<=range*1.2 ){
                System.out.println("Enemy injured in blast radius!");
                RandomTest.m.e2 = null;
                RandomTest.m.enemyT2.stop();
            }
        }
        if (RandomTest.m.e3!=null){
            
            int eR = RandomTest.m.e3.getPosition().getRow();
            int eC = RandomTest.m.e3.getPosition().getColumn();
            
            if ( Math.abs((bR -eR))<=range*1.2 && Math.abs((bC -eC))<=range*1.2 ){
                System.out.println("Enemy injured in blast radius!");
                RandomTest.m.e3 = null;
                RandomTest.m.enemyT3.stop();
            }
        }
        if (RandomTest.m.e4!=null){
            
            int eR = RandomTest.m.e4.getPosition().getRow();
            int eC = RandomTest.m.e4.getPosition().getColumn();
            
            if ( Math.abs((bR -eR))<=range*1.2 && Math.abs((bC -eC))<=range*1.2 ){
                System.out.println("Enemy injured in blast radius!");
                RandomTest.m.e4 = null;
                RandomTest.m.enemyT4.stop();
            }
        }
    	
    	
    	if ( Math.abs((bR -pR))<=range && Math.abs((bC -pC))<=range ){ //check player collisions
    		System.out.println("Player injured in blast radius!");
			RandomTest.m.gameOver =true;
			return;
    	}
    	
    	Fire.placeFire(bomb.getPosition(),(range==100)); //place fire
    	
    	
    	
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
//    	RandomTest.m.bombArray.remove(bomb);
    	RandomTest.m.map.map[bombR][bombC] = null;
    	RandomTest.m.bombOnScreen--;
//    	RandomTest.m.bomb = null;

    	
    	if (RandomTest.m.map.map[9][10]==null && powerUpPlace1==false){
    		RandomTest.m.map.map[9][10] = new PowerUp(new Position(9,10),MapBasicBlock.loadImage("resources/power1.gif"),Types.PowerUps.BOMBS_UP);
    		powerUpPlace1 = true;
    	}
    	
    	if (RandomTest.m.map.map[3][5]==null && powerUpPlace2==false){
    		RandomTest.m.map.map[3][5] = new PowerUp(new Position(3,5),MapBasicBlock.loadImage("resources/power2.gif"),Types.PowerUps.RANGE_UP);
    		powerUpPlace2 = true;
    	}
    	
    	if (RandomTest.m.map.map[10][3]==null && powerUpPlace3==false){
    		RandomTest.m.map.map[10][3] = new PowerUp(new Position(10,3),MapBasicBlock.loadImage("resources/power2.gif"),Types.PowerUps.RANGE_UP);
    		powerUpPlace3 = true;
    	}
    	
    	if (RandomTest.m.map.map[7][6]==null && powerUpPlace4==false){
    		RandomTest.m.map.map[7][6] = new PowerUp(new Position(7,6),MapBasicBlock.loadImage("resources/power1.gif"),Types.PowerUps.BOMBS_UP);
    		powerUpPlace4 = true;
    	}
    	
    	
    	
    	RandomTest.m.repaint();
    	
    	
    }

    static public void checkPowerUp(MapBasicBlock player){
    	int playerR=(int) Math.floor(player.getPosition().getRow() / 50);
    	int playerC=(int) Math.floor(player.getPosition().getColumn() / 50);
    	
    	
    	if (powerUpPlace1 == true){
    		if (playerR == 9 && playerC==10){
    			((Player) player).powerUp=(PowerUp)RandomTest.m.map.map[9][10];
    			RandomTest.m.map.map[9][10] = null;
    			((Player) player).powerUp.timer=1000;
    		
    		}
    	}
    	if (powerUpPlace2 == true){
    		if (playerR == 3 && playerC==5){
//    			System.out.println("hereeee");
    			((Player) player).powerUp=(PowerUp)RandomTest.m.map.map[3][5];
    			RandomTest.m.map.map[3][5] = null;
    			((Player) player).powerUp.timer=1000;
    		
    		}
    	}
    	if (powerUpPlace3 == true){
    		if (playerR == 10 && playerC==3){
    			((Player) player).powerUp=(PowerUp)RandomTest.m.map.map[10][3];
    			RandomTest.m.map.map[10][3] = null;
    			((Player) player).powerUp.timer=1000;
    		
    		}
    	}
    	if (powerUpPlace4 == true){
    		if (playerR == 7 && playerC==6){
    			((Player) player).powerUp=(PowerUp)RandomTest.m.map.map[7][6];
    			RandomTest.m.map.map[7][6] = null;
    			((Player) player).powerUp.timer=1000;
    		
    		}
    	}
    }
}

/*
 * their are alos enemies to the player that will keep on moving randomly for 
 * ( You are welcome to make them intelligent and give yourself a tough time ;) )
 * if the player touches them then player will die. When the die by the fire/explosion
 * they will also give a powerup but on random basis.
 */
//package bomborman;

//import bomborman.Types.Move;




public class Enemy extends MapBasicBlock{
	
	int direction;
    
    public Enemy(Position _position,int direct){
        
        super(Types.BlockType.ENEMY,_position,loadImage("resources/e1.gif"));
//        direction =-1;
        direction = direct;
    }
    
    public void moveEnemy(){ // enemy doesn't guess movement but just bounces back
    	int step = 1; //step
    	if (direction ==1){ //up
    		if (MoveEvaluator.isValidMove(this, Types.Move.UP)==true){
    			MoveExecutor.executeMove(this, Types.Move.UP,step);
    		}
    		else {
//    			
//    			if (MoveEvaluator.isValidMove(this, Types.Move.RIGHT)==true){
//    				direction=2;
//    			}
//    			else if (MoveEvaluator.isValidMove(this, Types.Move.LEFT)==true){
//    				direction=-2;
//    			}
//    			else {
    				direction =-1;
//    			}
    		}
    	}
    	else if (direction ==-1){ //down
    		if (MoveEvaluator.isValidMove(this, Types.Move.DOWN)==true){
    			MoveExecutor.executeMove(this, Types.Move.DOWN,step);
    		}
    		else {
//    			if (MoveEvaluator.isValidMove(this, Types.Move.LEFT)==true){
//    				direction=-2;
//    			}
//    			else if (MoveEvaluator.isValidMove(this, Types.Move.RIGHT)==true){
//    				direction=2;
//    			}
//    			else {
    				direction =1;
//    			}
    		}
    	}
    	else if (direction ==2){ //right
    		if (MoveEvaluator.isValidMove(this, Types.Move.RIGHT)==true){
    			MoveExecutor.executeMove(this, Types.Move.RIGHT,step);
    		}
    		else {
//    			if (MoveEvaluator.isValidMove(this, Types.Move.UP)==true){
//    				direction=1;
//    			}
//    			else if (MoveEvaluator.isValidMove(this, Types.Move.DOWN)==true){
//    				direction=-1;
//    			}
//    			else {
    				direction =-2;
//    			}
    		}
    	}
    	if (direction ==-2){ //left
    		if (MoveEvaluator.isValidMove(this, Types.Move.LEFT)==true){
    			MoveExecutor.executeMove(this, Types.Move.LEFT,step);
    		}
    		else {
//    			if (MoveEvaluator.isValidMove(this, Types.Move.DOWN)==true){
//    				direction=-1;
//    			}
//    			else if (MoveEvaluator.isValidMove(this, Types.Move.UP)==true){
//    				direction=1;
//    			}
//    			else {
    				direction =2;
//    			}
    		}
    	}
    }
    
    public void killPlayer(){ // check player collision
    		
    		int playerR = (RandomTest.m.p1.getPosition().getRow());
        	int playerC = (RandomTest.m.p1.getPosition().getColumn());
    		
    		int eR = this.getPosition().getRow();
    		int eC = this.getPosition().getColumn();
    		
    		
    		if ( Math.abs((playerR -eR))<=30 && Math.abs((playerC -eC))<=30 ){
    			System.out.println("Enemy killed you!");
    			RandomTest.m.gameOver=true;
    		}
    }
    
}

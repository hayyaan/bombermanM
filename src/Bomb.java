/*
 * This class will contain the implementation of bomb, you have to keep in mind
 * the following points.
 * 1- there is a time duration between the bomb is placed and it explodes
 * 2- if an other bomb is in the range of the bomb that has just exploded
 *    then the other one will also explode.
 * 3- the range of bomb can change depending upon the powerup of the player.
 * 4- You have to extend this assignment for multiplayer afterwards so you do 
 *    have a system to identify that who installed this bomb but for this part 
 *    you may leave this implemntation 
 */
//package bomborman;






public class Bomb extends MapBasicBlock {
	
	int timer;
	Sprite sprites;
	
	boolean fire;
	int fireTimer;
	int fireCounter;
	
	
    
    public Bomb(Position _position){
        super(Types.BlockType.BOMB,_position, loadImage("resources/bomb1.gif"));
        
        String spr[] = {"resources/bomb1.gif","resources/bomb2.gif","resources/bomb3.gif"};
        sprites = new Sprite(spr);
        timer =-1;
    }
    
}

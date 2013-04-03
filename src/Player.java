/*
 * this the player class, it will maintain the data of player and have 
 * approperiate methods.
 * 
 */
//package bomborman;

import java.awt.Image;
//import bomborman.Types.*;


public class Player extends MapBasicBlock {
    
    private boolean alive;
    private int playerID;
    int movement;
    int playerStep;
    Sprite sprites;
    PowerUp powerUp;
    
    public Player(){    	
        super(Types.BlockType.PLAYER,new Position(75,75),loadImage("resources/b11.gif"));
        alive = true;
        playerID++;
        movement=0;
        playerStep=5;
        String spr[] ={"resources/b11.gif","resources/b12.gif",
        				"resources/bu1.gif","resources/bu2.gif","resources/bu3.gif","resources/bu4.gif",
        				"resources/bd1.gif","resources/bd2.gif","resources/bd3.gif",
        				"resources/br1.gif","resources/br2.gif","resources/br3.gif","resources/br4.gif","resources/br5.gif","resources/br6.gif",
        				"resources/bl1.gif","resources/bl2.gif","resources/bl3.gif","resources/bl4.gif","resources/bl5.gif","resources/bl6.gif",
        				"resources/bdead1.gif","resources/bdead2.gif","resources/bdead3.gif","resources/bdead4.gif"};
        sprites = new Sprite(spr);
        powerUp = null;
    }
    
    
    
}

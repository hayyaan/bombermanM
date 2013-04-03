/*
 * Power ups are kind of bonuses or upgrade that palyer gets randomly when a 
 * breakble block is exploded by the explosion of bomb. Threre are three 
 * different types of power one increase your speed, the second one increases the 
 * range of the explosion of the bomb installed by the player and the third one 
 * increases the numbers of bombs a player can place/installed simultaneously.
 * 
 * If you want you can make three diffrent classes for each of the powerup.
 */
//package bomborman;

import java.awt.Image;
//import bomborman.Types.*;


// not used


public class PowerUp extends MapBasicBlock{
    
    Types.PowerUps powerUp;
    int timer;
    
    public PowerUp( Position _position, Image _image, Types.PowerUps _powerUp){
    	super( Types.BlockType.POWER_UP,_position, _image);
        
        powerUp = _powerUp;
        timer=-1;
    }
    
}

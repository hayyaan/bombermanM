/*
 * This is for breakable block of the map. This lock can be exploded by the 
 * explosion of bomb and it may contain the powerup that can be used once 
 * it's exploded. 
 */
//package bomborman;

import java.awt.Image;



public class BreakableBlock extends MapBasicBlock {
    
    public BreakableBlock(Position _position, Image _image){
        
        super( Types.BlockType.BREAKABLE,_position, _image);
    }
    
}

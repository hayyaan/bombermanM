/*
 * This is for unbreakable block of the map. This kind of block cannot be broken 
 * by any kind of explosion. 
 */
//package bomborman;

import java.awt.Image;

//import Types.BlockType;




public class UnbreakableBlock extends MapBasicBlock {
    
    public UnbreakableBlock(Position _position, Image _image){
        
        super( Types.BlockType.UNBREKABLE ,_position, _image);
    }
}

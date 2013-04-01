/*
 * This class is the basic bloack for all the elemnts of map we use this class in
 * order to keep all the different type of the objetc in the array of map in the Map class.
 * So every object that we have to put on the map must extend this class.
 
 */
//package bomborman;
/*
 * importing the the enumeration "BlockType" from the class Types 
 * that is defined in the packege bomborman
 */
//import bomborman.Types.BlockType;  
//import Types;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;




public class MapBasicBlock {
    
    private Types.BlockType blockType;
    public Position position;
    private Image image;
    private Graphics2D graphics2d;
    
    
    public MapBasicBlock( Types.BlockType _blockType, Position _position, Image _image){
    
        blockType = _blockType;
        position = _position;
        image = _image;
        graphics2d = ((BufferedImage) image).createGraphics();
    }
    
    public Graphics2D getGraphics(){
    	return graphics2d;
    }
    public Image getImage(){
    	return image;
    }
    
    public void setImage (Image i){
    	image =i;
    }
    
    
    
    /*
     * This function return the type of the Block on the map.
     */
    public Types.BlockType getBlockType(){
        return blockType;
    }
    
    public Position getPosition(){
        return position;
    }
    
    public void setPosition(Position newPosition){
        
        position = newPosition;
    }
    
    static public Image loadImage(String location){
    	Image img = null;
    	try{
    		img = ImageIO.read(new File(location));
    		
    	} catch(IOException e){
    		System.out.println("Image at "+location+" cannot be loaded");
    	}
    	return img;
    	
    }
    
    public boolean isofType( Types.BlockType _blockType ){
        
        if( blockType == _blockType ){
            return true;
        }else{
            return false;
        }
    }
}

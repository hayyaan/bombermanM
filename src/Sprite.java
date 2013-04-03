
import java.awt.Image;

public class Sprite { //handles sprites and all animations

	Image sprites[]; // array of the images
	int alternate; //keeps track of image in use
	
	Sprite(String[] args){
		sprites = new Image[args.length];
		for (int i=0;i<args.length;i++){
			sprites[i] = MapBasicBlock.loadImage(args[i]);
		}
		alternate =0;
	}
	
	public void animatePlayer(Player p){
		int movement = p.movement;
		if (movement==1){ //up
			if (alternate==0){
				p.setImage(sprites[2]);
				alternate = 1;
				return;
			}
			else if (alternate==1){
				p.setImage(sprites[3]);
				alternate = 2;
				return;
			}
			else if (alternate==2){
				p.setImage(sprites[4]);
				alternate = 3;
				return;
			}
			else {
				p.setImage(sprites[5]);
				alternate = 0;
				return;
			}	
		}
		else if (movement==-1){ //down
			if (alternate==0){
				p.setImage(sprites[6]);
				alternate = 1;
				return;
			}
			else if (alternate==1){
				p.setImage(sprites[7]);
				alternate = 2;
				return;
			}
			else {
				p.setImage(sprites[8]);
				alternate = 0;
				return;
			}	
		}
		else if (movement==2){ //right
			if (alternate==0){
				p.setImage(sprites[9]);
				alternate = 1;
				return;
			}
			else if (alternate==1){
				p.setImage(sprites[10]);
				alternate = 2;
				return;
			}
			else if (alternate==2){
				p.setImage(sprites[11]);
				alternate = 3;
				return;
			}
			else if (alternate==3){
				p.setImage(sprites[12]);
				alternate = 4;
				return;
			}
			else if (alternate==4){
				p.setImage(sprites[13]);
				alternate = 5;
				return;
			}
			else {
				p.setImage(sprites[14]);
				alternate = 0;
				return;
			}	
		}
		else if (movement==-2){ //left
			if (alternate==0){
				p.setImage(sprites[15]);
				alternate = 1;
				return;
			}
			else if (alternate==1){
				p.setImage(sprites[16]);
				alternate = 2;
				return;
			}
			else if (alternate==2){
				p.setImage(sprites[17]);
				alternate = 3;
				return;
			}
			else if (alternate==3){
				p.setImage(sprites[18]);
				alternate = 4;
				return;
			}
			else if (alternate==4){
				p.setImage(sprites[19]);
				alternate = 5;
				return;
			}
			else {
				p.setImage(sprites[20]);
				alternate = 0;
				return;
			}	
		}
		else if (movement==9){ // dead
//			try {
			p.setImage(sprites[21]);
			RandomTest.m.repaint();
			
//			Thread.sleep(100);
			
			p.setImage(sprites[22]);
			RandomTest.m.repaint();
			
//			Thread.sleep(100);
			
			p.setImage(sprites[23]);
			RandomTest.m.repaint();
			
//			Thread.sleep(100);
			
			p.setImage(sprites[24]);
			RandomTest.m.repaint();
			
//			Thread.sleep(100);
			
//			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}

	}
	
	public void animateBomb(Bomb bomb){
		int bR = bomb.getPosition().getRow() / 50;
		int bC = bomb.getPosition().getColumn() / 50;
		
		if (alternate ==0){
			RandomTest.m.map.map[bR][bC].setImage(sprites[1]);
			alternate = 1;
			return;
		}
		else if (alternate ==1){
			RandomTest.m.map.map[bR][bC].setImage(sprites[2]);
			alternate = 2;
			return;
			
		}
		else {
			RandomTest.m.map.map[bR][bC].setImage(sprites[0]);
			alternate =0;
			return;
		}
		
	}
	
	static public void animateFire(int step){
		for (int r=0;r<15;r++){
    		for (int c=0;c<15;c++){
    			if( RandomTest.m.map.map[r][c] == null){
    				continue;
    			}
    			else if ( RandomTest.m.map.map[r][c].isofType(Types.BlockType.FIRE)){
    				RandomTest.m.map.map[r][c].setImage(((Fire) RandomTest.m.map.map[r][c]).sprites.sprites[step]);
    			}
    		}
    	}
	}
}

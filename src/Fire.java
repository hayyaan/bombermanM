/*
 * The fire caused by explosion of bomb it stays for some duration of time if 
 * player comes at the place where there is fire then the player will die.
 */
//package bomborman;


public class Fire extends MapBasicBlock{
    
	Sprite sprites;
	
    public Fire(Position _position){ //fire block is in a static rotation
        
        super( Types.BlockType.FIRE,_position, MapBasicBlock.loadImage("resources/fire31.gif"));
        String spr[] ={"resources/fire31.gif","resources/fire32.gif",
				"resources/fire33.gif","resources/fire34.gif"};
        sprites = new Sprite(spr);
    }
    
    static void placeFire(Position bombPosition,boolean powered){ //places fire block
    	int bombR = bombPosition.getRow() / 50;
    	int bombC = bombPosition.getColumn() / 50;
    	
    	System.out.println("Bomb was at "+ bombR + " x " + bombC);
    	RandomTest.m.map.map[bombR][bombC] = new Fire(new Position(bombR*50 +25 , bombC*50 + 25));
    	
    	int rfind=-1;
    	int rend=1;
    	int cfind=-1;
    	int cend=1;
    	if (powered){
    		if (bombR>1){
    			rfind=-2;
    		}
    		if (bombR<14){
    			rend=2;
    		}
    		if (bombC>1){
    			cfind=-2;
    		}
    		if (bombC<14){
    			cend=2;
    		}
    	}
    	for (int r=rfind;r<=rend;r++){
    		for (int c=cfind;c<=cend;c++){
    			if (r==-1 && c==-1 || r==-1 && c==1 || r==1 && c==-1 || r==1 && c==1){ //diagonals
    				continue;
    			}
    			if (r==-2 && c==-2 || r==-2 && c==2 || r==2 && c==-2 || r==2 && c==2){ //diagonals
    				continue;
    			}
    			if (r==-2 && c==-1 || r==-2 && c==1 || r==2 && c==-1 || r==2 && c==1){ //diagonals
    				continue;
    			}
    			if (r==-1 && c==-2 || r==1 && c==-2 || r==-1 && c==2 || r==1 && c==2){ //diagonals
    				continue;
    			}
    			if (bombR+r > 14 || bombC+c > 14){
    				continue;
    			}
    			if (RandomTest.m.map.map[bombR+r][bombC+c]==null){
    				RandomTest.m.map.map[bombR+r][bombC+c] = new Fire(new Position((bombR+r)*50 +25 , (bombC+c)*50 + 25));
    			}
    		}
    	}
    	
    	
    }
}

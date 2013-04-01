/*
 * This class must have all the gui implementation of the game.
 */
//package bomborman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapGui extends JPanel implements KeyListener,ActionListener{
	static JPanel panel;
	
	Map map; //map
	Image bg; //background

	Player p1; //player
	
	Enemy e1; //enemy 1
	Enemy e2; //enemy 2
	
	MoveEvaluator moveEval; //class instantiation to run moveEvualtor function
	MoveExecutor moveExec; //^^
	Timer time = new Timer(8,this); //runs actionListener every 8ms
	
	int movement; //movement tracked, based on keyboard input
	int playerStep = 5; //increments in player's positions
	
	Bomb bomb; // bomb
	boolean bombToggle; //toggle for placing bomb
	int bombTimer; // bomb timer
	
	boolean fire; // fireToggle
	int fireTimer; // fire timer
	int fireCounter; // fire counter, used for sprites
	
	boolean gameEnd; // level cleared
	boolean gameOver; // game over
	

	public MapGui(){
		bg = MapBasicBlock.loadImage("resources/background.png");
		map = new Map(15,15);
		p1 = new Player();
		
		time.start();
		
		e1 = new Enemy(new Position((7*50)+25,(7*50)+25),2);
		e2 = new Enemy(new Position((3*50)+25,(9*50)+25),1);
		
		bomb=null;
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(true);	
	}
	
	public void actionPerformed(ActionEvent e){
		
//		System.out.println(bomb);
		
		
		if (movement ==1){
			MoveExecutor.executeMove(p1, Types.Move.UP,playerStep);
		}
		else if (movement==-1){
			MoveExecutor.executeMove(p1, Types.Move.DOWN,playerStep);
		}
		else if (movement ==2){
			MoveExecutor.executeMove(p1, Types.Move.RIGHT,playerStep);
		}
		else if (movement ==-2){
			MoveExecutor.executeMove(p1, Types.Move.LEFT,playerStep);
		}
		
		RandomTest.m.p1.sprites.animatePlayer(movement);
		
		
		
		if (e1 !=null){
			e1.moveEnemy();
			e1.killPlayer();
		}
		if (e2 !=null){
			e2.moveEnemy();
			e2.killPlayer();
		}
		
		
		if (bombToggle == true){
//			System.out.println("Hello");
			MoveExecutor.executeMove(p1, Types.Move.PLACE_BOMB,playerStep);
			bombTimer = 300;
			bombToggle = false;
		}
		
		if (bombTimer==1){
			//explosion
			MoveExecutor.explodeBomb(bomb);
			System.out.println("Explosion");
			bombTimer--;
			fireTimer = 20;
			fireCounter=0;
			fire = true;
		}
		
		else if (bombTimer>0){
			bombTimer--;
			if (bombTimer %8 ==0){
				bomb.sprites.animateBomb();
			}
		}
		
		
		if (fire ==true){
			fireTimer--;
			if (fireTimer % 8 ==0){
				if (fireCounter<3){
				Sprite.animateFire(fireCounter);
				fireCounter++;
				}
			}
		}
		
		if (fireTimer <0){
//			System.out.println("running");
			fire=false;
			for (int r=0;r<15;r++){
	    		for (int c=0;c<15;c++){
	    			if( RandomTest.m.map.map[r][c] == null){
	    				continue;
	    			}
	    			else if ( RandomTest.m.map.map[r][c].isofType(Types.BlockType.FIRE)){
	    				RandomTest.m.map.map[r][c]=null;
	    			}
	    		}
	    	}
			
		}
		
		if (map.checkEnd()){ //if all boxes cleared
			gameEnd = true;
			time.stop();
			System.out.println("Game Over!\n You won!");
		}
		
		if (gameOver==true){ //if player dies
			System.out.println("You died!");
			movement = 9;
			RandomTest.m.p1.sprites.animatePlayer(movement);
			time.stop();
//			e1 = null;
		}
		
		
		
		
		movement=0;
		
	}
	
	public void keyReleased(KeyEvent e){
		movement =0;
//		bomb = false;
	}
	
	public void keyTyped(KeyEvent e){
		movement =0;
//		bomb = false;
	}
	
	public void keyPressed(KeyEvent e){
//		System.out.println(e.getKeyCode());
		
		if (e.getKeyCode()==KeyEvent.VK_UP){
//			System.out.println("Pressed UP!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.UP)){
				movement = 1;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN){
//			System.out.println("Pressed DOWN!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.DOWN)){
				movement = -1;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT){
//			System.out.println("Pressed LEFT!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.LEFT)){
				movement = -2;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
//			System.out.println("Pressed RIGHT!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.RIGHT)){
				movement =2;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE){
//			System.out.println("Pressed SPACE!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.PLACE_BOMB)){			
				bombToggle =true;
//				System.out.println(bomb);
			}
		}
		
	}
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics g2d = (Graphics2D) g;
//		System.out.println("I drew!");
		
		g2d.drawImage(bg,0,0,bg.getWidth(null),bg.getHeight(null),null);
		
		
		for (int r=0;r<map.getRows();r++){
			for (int c=0;c<map.getCols();c++){
				if (map.map[r][c]==null){
					continue;
				}
				else{
				g2d.drawImage((map.map[r][c].getImage()) , (r*50) , (c*50) ,(map.map[r][c].getImage().getWidth(null)) ,(map.map[r][c].getImage().getHeight(null)),null);
				}
			}
		}
		
		
		g2d.drawImage(p1.getImage(),p1.getPosition().getRow()-25,p1.getPosition().getColumn()-25,p1.getImage().getWidth(null),p1.getImage().getHeight(null),null);
		

		
		if (e1 !=null){
			g2d.drawImage(e1.getImage(),e1.getPosition().getRow()-25,e1.getPosition().getColumn()-25,e1.getImage().getWidth(null),e1.getImage().getHeight(null),null);
		}
		if (e2 !=null){
			g2d.drawImage(e2.getImage(),e2.getPosition().getRow()-25,e2.getPosition().getColumn()-25,e2.getImage().getWidth(null),e1.getImage().getHeight(null),null);
			}
	}
	
	
}


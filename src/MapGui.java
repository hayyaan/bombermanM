/*
 * This class must have all the gui implementation of the game.
 */
//package bomborman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MapGui extends JPanel implements KeyListener,ActionListener{
	static JPanel panel;
	
	Map map; //map
	Image bg; //background

	Player p1; //player
	
	Enemy e1; //enemy 1
	Enemy e2; //enemy 2
	Enemy e3;
	Enemy e4;
	
	Thread enemyT1;
	Thread enemyT2;
	Thread enemyT3;
	Thread enemyT4;
	
	
	
	MoveEvaluator moveEval; //class instantiation to run moveEvualtor function
	MoveExecutor moveExec; //^^
	Timer time = new Timer(8,this); //runs actionListener every 8ms
	
//	int movement; //movement tracked, based on keyboard input
	// int playerStep = 5; //increments in player's positions
	
	ArrayList<Bomb> bombArray;
	int bombOnScreen;
	
	
//	Bomb bomb; // bomb
	boolean bombToggle; //toggle for placing bomb
//	int nBombs;
//	int bombTimer; // bomb timer
	
//	boolean fire; // fireToggle
//	int fireTimer; // fire timer
//	int fireCounter; // fire counter, used for sprites
	
	boolean endDoorPlace;
	int endDoorR;
	int endDoorC;
	

	
	boolean gameEnd; // level cleared
	boolean gameOver; // game over
	
	

	public MapGui(){
		bg = MapBasicBlock.loadImage("resources/background.png");
		map = new Map(15,15);
		p1 = new Player();
//		Thread playerT=new Thread(new PlayerThread(p1));
//		playerT.start();
		time.start();
		
		bombArray = new ArrayList<Bomb>();
//		bomb=null;
		e1 = new Enemy(new Position((7*50)+25,(7*50)+25),2);
		e2 = new Enemy(new Position((3*50)+25,(9*50)+25),1);
		e3 = new Enemy(new Position((5*50)+25,(9*50)+25),1);
		e4 = new Enemy(new Position((11*50)+25,(3*50)+25),1);
		
		enemyT1 = new Thread(new EnemyThread(e1));
		enemyT1.start();
		enemyT2 = new Thread(new EnemyThread(e2));
		enemyT2.start();
		enemyT3 = new Thread(new EnemyThread(e3));
		enemyT3.start();
		enemyT4 = new Thread(new EnemyThread(e4));
		enemyT4.start();
		
		
		
		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(true);	
	}
	
	public void actionPerformed(ActionEvent e){
		
//		System.out.println(bomb);
		
		
		 if (p1.movement ==1){
		 	MoveExecutor.executeMove(p1, Types.Move.UP,p1.playerStep);
		 }
		 else if (p1.movement==-1){
		 	MoveExecutor.executeMove(p1, Types.Move.DOWN,p1.playerStep);
		 }
		 else if (p1.movement ==2){
		 	MoveExecutor.executeMove(p1, Types.Move.RIGHT,p1.playerStep);
		 }
		 else if (p1.movement ==-2){
		 	MoveExecutor.executeMove(p1, Types.Move.LEFT,p1.playerStep);
		 }
		
		 p1.sprites.animatePlayer(p1.movement);
		
		
		
//		if (e1 !=null){
//			e1.moveEnemy();
//			e1.killPlayer();
//		}
//		if (e2 !=null){
//			e2.moveEnemy();
//			e2.killPlayer();
//		}
		
		if (bombToggle == true){
//			System.out.println("Hello");
			MoveExecutor.executeMove(p1, Types.Move.PLACE_BOMB,p1.playerStep);
			
			bombToggle = false;
		}
		
		for (int bi=0;bi<bombArray.size();bi++){
			Bomb bomb =bombArray.get(bi);
		if (bomb!=null){
			if (bomb.timer==1){
			//explosion
			MoveExecutor.explodeBomb(bomb,p1);
			System.out.println("Explosion");
			bomb.timer--;
			bomb.fireTimer = 20;
			bomb.fireCounter=0;
			bomb.fire = true;
			}
		
			else if (bomb.timer>0){
			bomb.timer--;
				if (bomb.timer %8 ==0){
				bomb.sprites.animateBomb(bomb);
				}
			}
		}
		
		
		
		if (bomb.fire ==true){
			bomb.fireTimer--;
			if (bomb.fireTimer % 8 ==0){
				if (bomb.fireCounter<3){
				Sprite.animateFire(bomb.fireCounter);
				bomb.fireCounter++;
				}
			}
		}
		
		if (bomb.fireTimer <0){
//			System.out.println("running");
			bomb.fire=false;
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
		
		}
		
 		if (p1.powerUp==null){
 			MoveExecutor.checkPowerUp(p1);
 		}
 		else{
 			p1.powerUp.timer--;
 			if(p1.powerUp.timer<=0){
 				System.out.println("expired");
 				p1.powerUp=null;
 			}
 		}
		
 		if (e1==null){
 		if (map.checkEndDoor(p1)){ //if all enemies cleared
 //			gameEnd = true;
 			time.stop();
 			System.out.println("Game Over!\n You won!");
 		}
 		}
 		
		if (e1 == null && e2==null && endDoorPlace==false){
			Image eDoor = MapBasicBlock.loadImage("resources/enddoor.gif");
			endDoorR=1;
			endDoorC=13;
			RandomTest.m.map.map[endDoorR][endDoorC] = new MapBasicBlock(Types.BlockType.EMPTY,new Position((endDoorR*50)+25,(endDoorC*50)+25),eDoor);
			endDoorPlace=true;
			System.out.println("End Door placed!");
		}
		
		if (gameOver==true){ //if player dies
			System.out.println("You died!");
			p1.movement = 9;
			RandomTest.m.p1.sprites.animatePlayer(p1.movement);
			time.stop();
			enemyT1.stop();
			enemyT2.stop();
			enemyT3.stop();
			enemyT4.stop();
//			e1 = null;
		}
		
		
		
		
		p1.movement=0;
//		this.repaint();
		
	}
	
	public void keyReleased(KeyEvent e){
		p1.movement =0;
//		bomb = false;
	}
	
	public void keyTyped(KeyEvent e){
		p1.movement =0;
//		bomb = false;
	}
	
	public void keyPressed(KeyEvent e){
//		System.out.println(e.getKeyCode());
		
		if (e.getKeyCode()==KeyEvent.VK_UP){
//			System.out.println("Pressed UP!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.UP)){
				p1.movement = 1;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN){
//			System.out.println("Pressed DOWN!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.DOWN)){
				p1.movement = -1;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT){
//			System.out.println("Pressed LEFT!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.LEFT)){
				p1.movement = -2;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
//			System.out.println("Pressed RIGHT!");
			if (MoveEvaluator.isValidMove(p1,Types.Move.RIGHT)){
				p1.movement =2;
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
			g2d.drawImage(e2.getImage(),e2.getPosition().getRow()-25,e2.getPosition().getColumn()-25,e2.getImage().getWidth(null),e2.getImage().getHeight(null),null);
		}
		if (e3 !=null){
			g2d.drawImage(e3.getImage(),e3.getPosition().getRow()-25,e3.getPosition().getColumn()-25,e3.getImage().getWidth(null),e3.getImage().getHeight(null),null);
		}
		if (e4 !=null){
			g2d.drawImage(e4.getImage(),e4.getPosition().getRow()-25,e4.getPosition().getColumn()-25,e4.getImage().getWidth(null),e4.getImage().getHeight(null),null);
		}
	}
	
	
}


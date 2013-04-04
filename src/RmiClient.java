import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RmiClient extends JPanel implements KeyListener{

		static JFrame frame;
	
		RmiInterface stub;
		ImageIcon icon;
//		Timer time = new Timer(1,this);
		
		RmiClient(){
			this.addKeyListener(this);
			this.setFocusable(true);
			this.requestFocus();
			this.setFocusTraversalKeysEnabled(true);
			this.setSize(765,795);
			rmiJoin();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			time.start();
			
		}
		
	public static void main(String[] args){
		frame = new JFrame();
		RmiClient game=new RmiClient();
		
		frame.add(game);
		frame.setSize(765,795);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		while (true){
			try {
				game.icon = game.stub.getServerImage();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.repaint();
		}
		
	}
	
	public void rmiJoin(){
		try{
			Registry registry = LocateRegistry.getRegistry();
			stub = (RmiInterface)registry.lookup("stub");
			stub.connected();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		try{
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP){
//			System.out.println("Pressed UP!");
			stub.moveClientUp();
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN){
//			System.out.println("Pressed DOWN!");
			stub.moveClientDown();
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT){
//			System.out.println("Pressed LEFT!");
			stub.moveClientLeft();
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
//			System.out.println("Pressed RIGHT!");
			stub.moveClientRight();
		}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE){
//			System.out.println("Pressed SPACE!");
			stub.plantClientBomb();
		}
		}catch (Exception exc){
			exc.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics g2d = (Graphics2D) g;
		if (icon==null){
			return;
		}
		icon.paintIcon(frame, g, 0, 0);
	}

	
	
}

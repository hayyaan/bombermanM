import java.awt.image.BufferedImage;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RmiServer implements RmiInterface{
	
	RandomTest game;
	boolean connectedState;
	
	public RmiServer(){
		rmiHost();
		game = new RandomTest();
		System.out.println("Waiting for Player 2 to join!");
		while (connectedState==false){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		game.run();
		
		
	}
	
	public static void main(String[] args){

		RmiServer main = new RmiServer();
	}
	
	public void rmiHost(){
		try{
	RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject(this,0);
	Registry registry = LocateRegistry.getRegistry();
	registry.rebind("stub", stub);
	System.out.println("Made the stub");
		}catch (Exception e){
		e.printStackTrace();
		}
	}

	@Override
	public void moveClientUp() throws RemoteException {
		// TODO Auto-generated method stub
//		game.m.p2.movement=1;
		if (MoveEvaluator.isValidMove(game.m.p2,Types.Move.UP)){
			game.m.p2.movement = 1;
		}
	}

	@Override
	public void moveClientDown() throws RemoteException {
		// TODO Auto-generated method stub
//		game.m.p2.movement=-1;
		if (MoveEvaluator.isValidMove(game.m.p2,Types.Move.DOWN)){
			game.m.p2.movement = -1;
		}
	}

	@Override
	public void moveClientLeft() throws RemoteException {
		// TODO Auto-generated method stub
//		game.m.p2.movement=-2;
		if (MoveEvaluator.isValidMove(game.m.p2,Types.Move.LEFT)){
			game.m.p2.movement = -2;
		}
	}

	@Override
	public void moveClientRight() throws RemoteException {
		// TODO Auto-generated method stub
//		game.m.p2.movement=2;
		if (MoveEvaluator.isValidMove(game.m.p2,Types.Move.RIGHT)){
			game.m.p2.movement = 2;
		}
	}
	
	@Override
	public void plantClientBomb() throws RemoteException {
		// TODO Auto-generated method stub
//		game.m.p2.movement=2;
		if (MoveEvaluator.isValidMove(game.m.p2,Types.Move.PLACE_BOMB)){
			game.m.p2.bombToggle = true;
		}
		
	}
	

	@Override
	public ImageIcon getServerImage() throws RemoteException {
		// TODO Auto-generated method stub
		BufferedImage img = new BufferedImage(game.m.getWidth(),game.m.getHeight(),BufferedImage.TYPE_INT_RGB);
		game.m.paint(img.getGraphics());
		ImageIcon icon = new ImageIcon(img);
		return icon;
	}

	@Override
	public void connected() throws RemoteException {
		// TODO Auto-generated method stub
		connectedState=true;
	}
}

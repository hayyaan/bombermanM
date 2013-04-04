import java.rmi.*;
import javax.swing.ImageIcon;

public interface RmiInterface extends Remote{
	
	public void connected() throws RemoteException;
	
	public void moveClientUp() throws RemoteException;
	public void moveClientDown() throws RemoteException;
	public void moveClientLeft() throws RemoteException;
	public void moveClientRight() throws RemoteException;
	public void plantClientBomb() throws RemoteException;
	
	public ImageIcon getServerImage() throws RemoteException;
	

}

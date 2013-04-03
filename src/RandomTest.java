import javax.swing.JFrame;


public class RandomTest { // class that runs the actual game
	
	static MapGui m;
	
	public static void main(String[] args){

	JFrame f =new JFrame();
	m=new MapGui();
	f.add(m);
	f.setSize(765,795);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	
	Thread sound = new Thread(new Music());
//	sound.start();
	
	
	
//	System.out.println("column: "+m.p1.getPosition().getColumn());
	
	
	
	}
}

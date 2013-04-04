import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JoinRmi extends JPanel{

	static JoinRmi m;
	static JFrame f;
	static JTextField field;
	
	public JoinRmi(){
		JLabel label = new JLabel("Enter IP Address:");
		this.add(label);
		field = new JTextField();
		this.add(field);
		field.setText("Enter Address");
		JButton button = new JButton("Start!");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("hello");
				String ip = field.getText();
				System.out.println(ip);
				String [] entry = {ip};
				RmiClient.main(entry);
				
				f.removeAll();
				f.setVisible(false);
			}
		});
		this.add(button);
		
	}
	
	public static void main(String[] args){

		f =new JFrame();
		m=new JoinRmi();
		f.add(m);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		f.setVisible(true);
		
		Thread sound = new Thread(new Music());
//		sound.start();
		
		
		
//		System.out.println("column: "+m.p1.getPosition().getColumn());
		
		
		
		}
}

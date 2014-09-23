import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginGUI extends JFrame implements ActionListener{

	ObjectOutputStream out;
	ObjectInputStream in;
	
	private JButton jbtStart;
	private JTextField txtName;
	
	public static Spieltisch tisch;
	
	public LoginGUI(ObjectOutputStream out, ObjectInputStream in){
		this.out = out;
		this.in = in;
		
		txtName = new JTextField();
		txtName.setSize(30, 10);
		jbtStart = new JButton("Start");
		
		jbtStart.addActionListener(this);
		this.setSize(200, 100);
		
		JPanel halter = new JPanel();
		halter.setLayout(new GridLayout(1,2));
		
		
		halter.add(txtName);
		halter.add(jbtStart);
		this.add(halter);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtStart){
			
			try{
				if(txtName.getText().length()> 0){
				Spieler spieler = new Spieler(txtName.getText());
				this.out.writeObject(spieler);
				
				setVisible(false);
				tisch = new Spieltisch(this.out, this.in);
				System.out.println(tisch);
				}
			}catch (java.io.IOException IOException){
				IOException.printStackTrace();
			}
			
			
		}
		
	}
	
	public Spieltisch getTisch(){
		return this.tisch;
	}
	
}

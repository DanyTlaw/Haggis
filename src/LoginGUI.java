import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.WindowEvent;


public class LoginGUI extends JFrame implements ActionListener{

	ObjectOutputStream out;
	ObjectInputStream in;
	
	private String name;
	
	private JButton jbtStart;
	private JTextField txtName;
	private JTextField txtPunkte;
	
	private JLabel background;
	private JLabel lblName;
	private JLabel lblPunkte;
	private JLabel lblHolder1;
	private JLabel lblHolder2;
	private JLabel lblHolder3;
	private JLabel lblHolder4;
	
	
	
	public String pfad = System.getProperty("user.dir") + "//images//";
	public Spieltisch tisch;
	
	
	
	//Konstruktor welche das LoginGUI erstellt
	public LoginGUI(ObjectOutputStream out, ObjectInputStream in){
		this.out = out;
		this.in = in;
		
		//setzt das Fenser auf eine 800 800 grösse
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.setTitle("Das Haggis Kartenspiel");

		getContentPane().setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3,3));
		inputPanel.setOpaque(false);

		

		
		Image bg = new ImageIcon(pfad + "haggisBack.jpg").getImage();
		ImageIcon iBg = new ImageIcon(bg.getScaledInstance(500, 400, Image.SCALE_DEFAULT)); 
			
		((JPanel)getContentPane()).setOpaque(false);
		background = new JLabel(iBg);
		background.setSize(500,400);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, iBg.getIconWidth(), iBg.getIconHeight());
		
		
		
		txtName = new JTextField();
		txtName.setSize(30, 5);
		txtPunkte = new JTextField();
		txtPunkte.setSize(10, 5);
		jbtStart = new JButton("Start");
		jbtStart.addActionListener(this);
		getRootPane().setDefaultButton(jbtStart);
		lblName = new JLabel("Spielername");
		lblName.setForeground(Color.WHITE);
		lblPunkte = new JLabel("Abgemachte Punkte");
		lblPunkte.setForeground(Color.WHITE);
		lblHolder1 = new JLabel(" ");
		lblHolder2 = new JLabel(" ");
		lblHolder3 = new JLabel(" ");
		lblHolder4 = new JLabel(" ");
		inputPanel.add(lblName);
		inputPanel.add(lblPunkte);
		inputPanel.add(lblHolder1);
		inputPanel.add(txtName);
		inputPanel.add(txtPunkte);
		inputPanel.add(jbtStart);
		inputPanel.add(lblHolder2);
		inputPanel.add(lblHolder3);
		inputPanel.add(lblHolder4);
		
		
		

		
	

		getContentPane().add(inputPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtStart){
			
			try{
				if(txtName.getText().length()> 0){
					name = txtName.getText();
					Spieler spieler = new Spieler(name);
					spieler.setSiegesPunkte(Integer.parseInt(txtPunkte.getText()));
					this.out.writeObject(spieler);
					setVisible(false);
					tisch = new Spieltisch(this.out, this.in, this.name);
				
				}
			}catch (java.io.IOException IOException){
				IOException.printStackTrace();
			}catch (NumberFormatException IOException){
				JOptionPane.showMessageDialog(rootPane, "Bitte gueltige Punktzahl eingeben.");
			}
			
			
		}
		
	}

	public Spieltisch getTisch(){
		return this.tisch;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	
	
}

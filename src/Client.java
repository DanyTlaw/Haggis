import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Client extends JFrame{

	private Image image;
	private ImageIcon icon;
	
	int bubeAnzahl = 1;
	int dameAnzahl = 1;
	int königAnzahl = 1;
	int handkarten = 14;
	int haggisAnzahl = 8;
	public Color background = new Color(255,255,255);
	String[] spielKarten = new String[14];
	
    int kartenBound = 0;
    int jokerKartenBound = 0;
	
	public JButton[] btnKarte = new JButton[14];
	public JButton[] jokerKarten = new JButton[3];
	public JLabel[] anzeigeKarten = new JLabel[8];
	public Boolean[] gedrucktJoker = new Boolean[3];
	public Boolean[] gedrucktHand = new Boolean[14];
	
	private Image imageRückseite;
	private ImageIcon rückseite;
	private Image jBube;
	private ImageIcon jBubeIcon;
	private Image jDame;
	private ImageIcon jDameIcon;
	private Image jKönig;
	private ImageIcon jKönigIcon;
	
	//Border erstellen
	public Border gedrucktBorder = new LineBorder(Color.BLUE, 2);
	
	public static void main(String[] args){
		Client c = new Client();
	}

	
	public Client(){
		
		clickHandler cHandler = new clickHandler();
		
		//Gibt dem Client ein BorderLayout
		getContentPane().setLayout(new BorderLayout());
		
		//JPanel für das ganze Frame wird erstellt für die HIntergrundfarbe
		JPanel mainPanel = new JPanel();
		
		setContentPane(mainPanel);
		/*
		 * DER NORTH TEIL DES JFRAMES
		 * */
		
		//JPanel welche alle informationen über den Gegner beinhaltet
		JPanel enemy = new JPanel();
		JPanel enemyKarten = new JPanel();
		
		enemy.setLayout(new BoxLayout(enemy, BoxLayout.X_AXIS));
		enemyKarten.setLayout(new GridLayout(2,3));
		
		//Erstellt einen Image in einem ImageIcon welches einem Label hinzugefügt wird
		image = new ImageIcon("C:\\workspace\\Haggis\\images\\icon.jpg").getImage();
		icon = new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_DEFAULT));	
		JLabel lblImage = new JLabel();
		lblImage.setIcon(icon);
		
		//JLabels welche die Karten des Gegner anzeigen
		JLabel lblBube = new JLabel("Bube: " + bubeAnzahl);
		JLabel lblDame = new JLabel("Dame: " + dameAnzahl);
		JLabel lblKönig = new JLabel("König: " + königAnzahl);
		JLabel lblHandkarten = new JLabel("HandKarten: " + handkarten);
		JLabel lblHolder = new JLabel();
		
		
		//Added alle Informationen dem JPanel enemyKarten
		enemyKarten.add(lblBube);
		enemyKarten.add(lblDame);
		enemyKarten.add(lblKönig);
		enemyKarten.add(lblHandkarten);
		enemyKarten.add(lblHolder);
		enemyKarten.add(lblHolder);		
		
		//Added alle Componente dem JPanel enemy
		enemy.setBorder(new EmptyBorder(0,425,0,0));
		enemy.add(lblImage);
		enemy.add(Box.createHorizontalStrut(20));
		enemy.add(enemyKarten);
		
		/*
		 * DER CENTER TEIL DES JFRAMES
		 * */
		
		JPanel haggis = new JPanel();
		JPanel spiel = new JPanel();
		
		spiel.setLayout(new BoxLayout(spiel, BoxLayout.X_AXIS));
		haggis.setLayout(new BoxLayout(haggis, BoxLayout.Y_AXIS));
		
		//Erstellt das JLabel mit der Anzahl Haggis Karten
		JLabel haggisKarten = new JLabel("Haggis: " + haggisAnzahl);
		
		//Erstellt einen Image in einem ImageIcon welches einem Label hinzugefügt wird
		imageRückseite = new ImageIcon("C:\\workspace\\Haggis\\images\\rueckseite.jpg").getImage();
		rückseite = new ImageIcon(imageRückseite.getScaledInstance(150, 200, Image.SCALE_DEFAULT));	
		JLabel lblHaggis = new JLabel();
		lblHaggis.setIcon(rückseite);
		
		//JLabel welches das Spielfeld ist
		JPanel spielfeld = new JPanel();
		spielfeld.setPreferredSize(new Dimension(900, 300));
		spielfeld.setMaximumSize(new Dimension(900, 300));
		spielfeld.setMinimumSize(new Dimension(900, 300));
		spielfeld.setLayout(new BoxLayout(spielfeld, BoxLayout.X_AXIS));
			
		Color bg = new Color(135,210,229);
		spielfeld.setOpaque(true);
		spielfeld.setBackground(bg);
		
		//Schleife welches 8 Labels in der Mitte erstell in der die Karten dann ausgepsielt werden
		spielfeld.add(Box.createHorizontalStrut(30));
		for(int i = 0;i<8;i++){
			JLabel anzeigeKarte = new JLabel();
			anzeigeKarte.setPreferredSize(new Dimension(100, 150));
			anzeigeKarte.setMaximumSize(new Dimension(100, 150));
			anzeigeKarte.setMinimumSize(new Dimension(100, 150));
			anzeigeKarte.setOpaque(true);
			anzeigeKarte.setBackground(Color.black);
			anzeigeKarten[i] = anzeigeKarte;
			spielfeld.add(anzeigeKarten[i]);
			spielfeld.add(Box.createHorizontalStrut(5));
		}
		
		haggis.add(haggisKarten);
		haggis.add(lblHaggis);
		
		spiel.add(haggis);
		spiel.add(spielfeld);
		
		
		/*
		 * DER SOUTH TEIL DES FRAMES
		 * */
		
		JPanel spielSteuerung = new JPanel();
		spielSteuerung.setLayout(new BoxLayout(spielSteuerung, BoxLayout.Y_AXIS));
		
		JPanel kartenSteuerung = new JPanel();
		kartenSteuerung.setLayout(new BoxLayout(kartenSteuerung, BoxLayout.X_AXIS));
		
		JPanel titelSteuerung = new JPanel();
		titelSteuerung.setLayout(new BoxLayout(titelSteuerung, BoxLayout.X_AXIS));
		
		JPanel linksTitel = new JPanel();
		linksTitel.setLayout(new BoxLayout(linksTitel, BoxLayout.Y_AXIS));	
		linksTitel.setPreferredSize(new Dimension(350,15));
		linksTitel.setMaximumSize(new Dimension(350,15));
		linksTitel.setMinimumSize(new Dimension(350,15));
		
		JPanel rechtsTitel = new JPanel();
		rechtsTitel.setLayout(new BoxLayout(rechtsTitel, BoxLayout.Y_AXIS));	
		rechtsTitel.setPreferredSize(new Dimension(500,15));
		rechtsTitel.setMaximumSize(new Dimension(500,15));
		rechtsTitel.setMinimumSize(new Dimension(500,15));
		
		JPanel linksSteuerung = new JPanel();
		linksSteuerung.setLayout(new BoxLayout(linksSteuerung, BoxLayout.Y_AXIS));	
		linksSteuerung.setPreferredSize(new Dimension(350,200));
		linksSteuerung.setMaximumSize(new Dimension(350,200));
		linksSteuerung.setMinimumSize(new Dimension(350,200));
		
		JPanel rechtsSteuerung = new JPanel();
		rechtsSteuerung.setLayout(new BoxLayout(rechtsSteuerung, BoxLayout.Y_AXIS));
		rechtsSteuerung.setPreferredSize(new Dimension(500,200));
		rechtsSteuerung.setMaximumSize(new Dimension(500,200));
		rechtsSteuerung.setMinimumSize(new Dimension(500,200));
		
		//Alle Container für das  Panel jokerkarten werden gemacht

		

		
		//Erstellt ein Label mit der Karte Bube
		jBube = new ImageIcon("C:\\workspace\\Haggis\\images\\Bube.jpg").getImage();
		jBubeIcon = new ImageIcon(jBube.getScaledInstance(100, 150, Image.SCALE_DEFAULT));
		JLabel lblIconBube = new JLabel();
		lblIconBube.setIcon(jBubeIcon);
		
		//Erstellt ein Label mit der Karte Bube
		jDame = new ImageIcon("C:\\workspace\\Haggis\\images\\Dame.jpg").getImage();
		jDameIcon = new ImageIcon(jDame.getScaledInstance(100, 150, Image.SCALE_DEFAULT));
		JLabel lblIconDame = new JLabel();
		lblIconDame.setIcon(jDameIcon);
		
		//Erstellt ein Label mit der Karte Bube
		jKönig = new ImageIcon("C:\\workspace\\Haggis\\images\\König.jpg").getImage();
		jKönigIcon = new ImageIcon(jKönig.getScaledInstance(100, 150, Image.SCALE_DEFAULT));
		JLabel lblIconKönig = new JLabel();
		lblIconKönig.setIcon(jKönigIcon);
				
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setAlignmentX(LEFT_ALIGNMENT);
		JButton jbtSpielen = new JButton("Spielen");
		JButton jbtPassen = new JButton("Passen");
		JButton jbtBeenden = new JButton("Beenden");
		
		
		buttons.add(jbtSpielen);
		buttons.setBorder(new EmptyBorder(0,0,0,10));
		buttons.add(jbtPassen);
		buttons.setBorder(new EmptyBorder(0,0,0,10));
		buttons.add(jbtBeenden);
		
		


		
		
		//Alle Container und Buttons für den Rechtssteuerung teil
		
		
		JLabel lbljokerkarten = new JLabel("Jokerkarten");
		lbljokerkarten.setBounds(jokerKartenBound, -50, 100, 150);

		
		
	
		JLabel lbleigeneKarten = new JLabel("Handkarten");
		lbleigeneKarten.setBounds(kartenBound, -50 ,100,150);
	
		JPanel jokerkarten = new JPanel();
		jokerkarten.setLayout(null);
		
		JPanel eigeneKartenButtons = new JPanel();
		eigeneKartenButtons.setLayout(null);
		
		//Generiert 14 Buttons und fügt sie dem Array hinzu	
		for(int i = 0;i<14;i++){
			JButton jbtKarte = new JButton();
			jbtKarte.setPreferredSize(new Dimension(100,150));
			jbtKarte.setMaximumSize(new Dimension(100,150));
			jbtKarte.setMinimumSize(new Dimension(100,150));
			jbtKarte.setBounds(kartenBound, 5, 100, 150);
			btnKarte[i] = jbtKarte;
			btnKarte[i].addActionListener(cHandler);
			gedrucktHand[i] = false;
			eigeneKartenButtons.add(btnKarte[i]);
			kartenBound+=30;
		}

		
		for(int i = 0; i<3;i++){
			JButton jokerKarte = new JButton();
			jokerKarte.setPreferredSize(new Dimension(100,150));
			jokerKarte.setMaximumSize(new Dimension(100,150));
			jokerKarte.setMinimumSize(new Dimension(100,150));
			jokerKarte.setBounds(jokerKartenBound, 5 ,100,150);
			jokerKarten[i] = jokerKarte;
			jokerKarten[i].addActionListener(cHandler);
			gedrucktJoker[i] = false;
			jokerkarten.add(jokerKarten[i]);
			jokerKartenBound+=100;
			
		}
		
		
		//Setzt den Hintergrund aller Componenten auf weiss
		enemyKarten.setBackground(background);
		haggis.setBackground(background);
		buttons.setBackground(background);
		jokerkarten.setBackground(background);
		eigeneKartenButtons.setBackground(background);
		linksTitel.setBackground(background);
		rechtsTitel.setBackground(background);
		kartenSteuerung.setBackground(background);
		linksSteuerung.setBackground(background);
		rechtsSteuerung.setBackground(background);
		enemy.setBackground(background);
		spiel.setBackground(background);
		spielSteuerung.setBackground(background);
		
		
		linksTitel.add(lbljokerkarten);
		rechtsTitel.add(lbleigeneKarten);
		titelSteuerung.add(linksTitel);
		titelSteuerung.add(rechtsTitel);
		
		rechtsSteuerung.add(eigeneKartenButtons);
		linksSteuerung.add(jokerkarten);
		linksSteuerung.add(buttons);
		kartenSteuerung.add(linksSteuerung);
		kartenSteuerung.add(rechtsSteuerung);
		
		spielSteuerung.add(titelSteuerung);
		spielSteuerung.add(kartenSteuerung);
		
		

		
		this.add(enemy,BorderLayout.NORTH);
		this.add(spiel, BorderLayout.CENTER);
		this.add(spielSteuerung, BorderLayout.SOUTH);
		
		this.setTitle("Haggis");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel.setBackground(background);
	
		this.setVisible(true);
		this.setSize(1100, 1000);
		
	}
	
	//Funktion welche den Boolean wert wechslet und so sieht ob die Karte gedruckt ist oder nicht 
	public boolean gedruckt(boolean gedruckt){
		if(gedruckt){
			gedruckt = false;
		}
		else if(!gedruckt){
			gedruckt = true;
		}
		return gedruckt;
	}

	
	/*
	 *HANDLER WELCHE DIE EINGEBANE IN DEM CLIENT BEARBEITET 
	 * 
	*/

	public class clickHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			//Handlet die gedruckten Karten und setzt ihnen einen Border oder nicht (JokerKarten)
			for(int i = 0;i<3;i++){
				if(e.getSource() == jokerKarten[i]){
					System.out.println(gedrucktJoker[i]);
					gedrucktJoker[i] = gedruckt(gedrucktJoker[i]);
					if(gedrucktJoker[i]){
						jokerKarten[i].setBorder(gedrucktBorder);
						
					}
					else if(!gedrucktJoker[i]){
						jokerKarten[i].setBorder(UIManager.getBorder("Button.border"));
						
					}
				}
			}
			
			//Handlet die gedruckten Karten und setzt ihnen einen Border oder nicht (Handkarten)
			for(int i = 0;i<14;i++){	
				if(e.getSource() == btnKarte[i]){
					
					gedrucktHand[i] = gedruckt(gedrucktHand[i]);
					if(gedrucktHand[i]){
						btnKarte[i].setBorder(gedrucktBorder);
						
					}
					else if(!gedrucktHand[i]){
						btnKarte[i].setBorder(UIManager.getBorder("Button.border"));
						
					}
				}
			}
		
		
		}
	}
}	



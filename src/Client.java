import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.lang.*;

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
	public JLabel[] anzeigeKarten = new JLabel[14];
	public Boolean[] gedrucktJoker = new Boolean[3];
	public Boolean[] gedrucktHand = new Boolean[14];
	
	public JButton jbtSpielen;
	public JButton jbtPassen;
	public JButton jbtBeenden;
	
	public ArrayList<Card> hand; 
	
	//ArrayListe für die Karten welche im SpielFeld sind
	public ArrayList<Card> feldKarten = new ArrayList<Card>();
	
	//ArrayListe für die Karten welche ausgepspielt werden
	public ArrayList<Card> gespielteKarten = new ArrayList<Card>();
	
	public String pfad = System.getProperty("user.dir") + "//images//";


	
	private Image imageRückseite;
	private ImageIcon rückseite;
	private Image jBube;
	private ImageIcon jBubeIcon;
	private Image jDame;
	private ImageIcon jDameIcon;
	private Image jKönig;
	private ImageIcon jKönigIcon;
	
	
	//ArrayList zum testen
	ArrayList<Card> test = new ArrayList<Card>();
	//Border erstellen
	public Border gedrucktBorder = new LineBorder(Color.BLUE, 2);
	
	public static void main(String[] args){
		Deck d = new Deck();
		d.aufteilen(2);
		Client c = new Client();
		c.setHand(d.getHandKarten1());
		c.ladetBilder(c.getHand());
		System.out.println(c.pfad);
		c.setVisible(true);
	}

	
	public Client(){
		
		clickHandler cHandler = new clickHandler();
		buttonHandler bHandler = new buttonHandler();
		
		//Gibt dem Client ein BorderLayout
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//JPanel f�r das ganze Frame wird erstellt f�r die HIntergrundfarbe
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		/***************************************************************************************
		NORTH TEIL DES FRAMES
		****************************************************************************************/
		
		//JPanel welche alle informationen �ber den Gegner beinhaltet
		JPanel enemy = new JPanel();
		JPanel enemyKarten = new JPanel();
		
		enemy.setLayout(new BoxLayout(enemy, BoxLayout.X_AXIS));
		enemyKarten.setLayout(new GridLayout(2,3));
		
		//Erstellt einen Image in einem ImageIcon welches einem Label hinzugef�gt wird
		image = new ImageIcon(pfad +"icon.jpg").getImage();
		icon = new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_DEFAULT));	
		JLabel lblImage = new JLabel();
		lblImage.setIcon(icon);
		
		//JLabels welche die Karten des Gegner anzeigen
		JLabel lblBube = new JLabel("Bube: " + bubeAnzahl);
		JLabel lblDame = new JLabel("Dame: " + dameAnzahl);
		JLabel lblKönig = new JLabel("K�nig: " + königAnzahl);
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
		enemy.setBorder(new EmptyBorder(0,425,10,0));
		enemy.add(lblImage);
		enemy.add(Box.createHorizontalStrut(20));
		enemy.add(enemyKarten);
		
		/***************************************************************************************
		CENTER TEIL DES FRAMES
		****************************************************************************************/
		JPanel spiel = new JPanel();
		JPanel haggis = new JPanel();
		JPanel spielfeld = new JPanel();
		
		
		spiel.setLayout(new BoxLayout(spiel, BoxLayout.X_AXIS));
		haggis.setLayout(new BoxLayout(haggis, BoxLayout.Y_AXIS));
		spielfeld.setLayout(new BoxLayout(spielfeld, BoxLayout.Y_AXIS));

		
		//Erstellt das JLabel mit der Anzahl Haggis Karten
		JLabel haggisKarten = new JLabel("Haggis: " + haggisAnzahl);
		
		//Erstellt einen Image in einem ImageIcon welches einem Label hinzugef�gt wird
		imageRückseite = new ImageIcon(pfad +"rueckseite.jpg").getImage();
		rückseite = new ImageIcon(imageRückseite.getScaledInstance(150, 200, Image.SCALE_DEFAULT));	
		JLabel lblHaggis = new JLabel();
		lblHaggis.setIcon(rückseite);
		
		//JPanel fuer den Oberen Teil des Spielfeld
		JPanel spielfeldoben = new JPanel();
		spielfeldoben.setPreferredSize(new Dimension(800, 170));
		spielfeldoben.setMaximumSize(new Dimension(800, 170));
		spielfeldoben.setMinimumSize(new Dimension(800, 170));
		spielfeldoben.setLayout(new BoxLayout(spielfeldoben, BoxLayout.X_AXIS));
			
		Color bg = new Color(135,210,229);
		spielfeldoben.setOpaque(true);
		spielfeldoben.setBackground(bg);
		
		//Schleife welches 7 Labels im oberen Spielfeld erstellt
		spielfeldoben.add(Box.createHorizontalStrut(30));
		for(int i = 0;i<7;i++){
			JLabel anzeigeKarte = new JLabel();
			anzeigeKarte.setPreferredSize(new Dimension(100, 150));
			anzeigeKarte.setMaximumSize(new Dimension(100, 150));
			anzeigeKarte.setMinimumSize(new Dimension(100, 150));
			anzeigeKarte.setOpaque(true);
			anzeigeKarte.setBackground(Color.black);
			anzeigeKarten[i] = anzeigeKarte;
			spielfeldoben.add(anzeigeKarten[i]);
			spielfeldoben.add(Box.createHorizontalStrut(5));
		}
		
		//JPanel fuer den unteren Teil des Spielfelds
		JPanel spielfelunten = new JPanel();
		spielfelunten.setPreferredSize(new Dimension(800, 170));
		spielfelunten.setMaximumSize(new Dimension(800, 170));
		spielfelunten.setMinimumSize(new Dimension(800, 170));
		spielfelunten.setLayout(new BoxLayout(spielfelunten, BoxLayout.X_AXIS));
					

		spielfelunten.setOpaque(true);
		spielfelunten.setBackground(bg);
				
		//Schleife welches 7 Labels im unteren Teil des Spielfelds erstellt
		spielfelunten.add(Box.createHorizontalStrut(30));
		for(int i = 7;i<14;i++){
			JLabel anzeigeKarte = new JLabel();
			anzeigeKarte.setPreferredSize(new Dimension(100, 150));
			anzeigeKarte.setMaximumSize(new Dimension(100, 150));
			anzeigeKarte.setMinimumSize(new Dimension(100, 150));
			anzeigeKarte.setOpaque(true);
			anzeigeKarte.setBackground(Color.black);
			anzeigeKarten[i] = anzeigeKarte;
			spielfelunten.add(anzeigeKarten[i]);
			spielfelunten.add(Box.createHorizontalStrut(5));
		}
		
		
		haggis.add(haggisKarten);
		haggis.add(lblHaggis);
		haggis.setBorder(new EmptyBorder(0,0,0,50));
		
		spielfeld.add(spielfeldoben);
		spielfeld.add(spielfelunten);
		
		spiel.add(haggis);
		
		spiel.add(spielfeld);
		
		
		
		/***************************************************************************************
		SOUTH TEIL DES FRAMES
		****************************************************************************************/
		
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
		
		//Alle Container f�r das  Panel jokerkarten werden gemacht		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setAlignmentX(LEFT_ALIGNMENT);
		
		

	
		jbtSpielen = new JButton("Spielen");
		jbtSpielen.addActionListener(bHandler);
		jbtPassen = new JButton("Passen");
		jbtBeenden = new JButton("Beenden");
		/*
		jbtSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				karteAuspielen();
			
			}
		});
		*/
		jbtSpielen.addActionListener(cHandler);
		buttons.add(jbtSpielen);
		buttons.setBorder(new EmptyBorder(0,0,0,10));
		buttons.add(jbtPassen);
		buttons.setBorder(new EmptyBorder(0,0,0,10));
		buttons.add(jbtBeenden);
		
		


		
		
		//Alle Container und Buttons f�r den Rechtssteuerung teil
		
		
		JLabel lbljokerkarten = new JLabel("Jokerkarten");
		lbljokerkarten.setBounds(jokerKartenBound, -50, 100, 150);
	
		JLabel lbleigeneKarten = new JLabel("Handkarten");
		lbleigeneKarten.setBounds(kartenBound, -50 ,100,150);
	
		JPanel jokerkarten = new JPanel();
		jokerkarten.setLayout(null);
		
		JPanel eigeneKartenButtons = new JPanel();
		eigeneKartenButtons.setLayout(null);
		
		//Generiert 14 Buttons und f�gt sie dem Array hinzu	
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
		titelSteuerung.setBackground(background);
		
		
		linksTitel.add(lbljokerkarten);
		rechtsTitel.add(lbleigeneKarten);
		titelSteuerung.add(linksTitel);
		titelSteuerung.add(rechtsTitel);
		titelSteuerung.setBorder(new EmptyBorder(10,0,0,0));
		
		rechtsSteuerung.add(eigeneKartenButtons);
		linksSteuerung.add(jokerkarten);
		linksSteuerung.add(buttons);
		kartenSteuerung.add(linksSteuerung);
		kartenSteuerung.add(rechtsSteuerung);
		
		spielSteuerung.add(titelSteuerung);
		spielSteuerung.add(kartenSteuerung);
		
		

		
		this.add(enemy);
		this.add(spiel);
		this.add(spielSteuerung);
		
		this.setTitle("Haggis");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel.setBackground(background);
	

		this.setSize(1100, 1000);
		
		
	}
	

	
	//Getters und Setters
	public void setHand(ArrayList<Card> hand){
		this.hand = hand;
	}
	
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	
	
	//Methode um eine Karte auszuspielen
	public void karteAuspielen(){
	int jokerWert = 1;
	String jokerFarbe = "";
		
		//Ist eine Karte angewählt wird sie der ArrayList gespielteKarte hinzugefügt (JokerKarten)
		for(int i = 0;i<3;i++){
			if(jokerKarten[i].getBorder() == gedrucktBorder){
				//Fordert den Spieler auf Falls eine Jokerkarte ausgewählt wurde eine Karte und eine Farbe einzugeben
				jokerWert = jokerWert(i);
				
				//Nur im dreispielermodus gebraucht
				jokerFarbe = jokerFarbe();
				
				
				
				//Diese zeile erstellt eine Copy der Karte in die kartenKontrolle		
				gespielteKarten.add(new Card(hand.get(i).getWert(),hand.get(i).getName(),hand.get(i).getBild(),hand.get(i).getPunkte(),hand.get(i).getFarbe(),hand.get(i).getJoker(),jokerWert,jokerFarbe));
				
				
				
			}
		}
		//Ist eine Karte angewählt wird sie der ArrayList gespielteKarte hinzugefügt (Hand Karten)
		for(int i = 3;i<17;i++){
			if(btnKarte[i-3].getBorder() == gedrucktBorder){
				//Diese zeile erstellt eine Copy der Karte in die kartenKontrolle


				gespielteKarten.add(new Card(hand.get(i).getWert(),hand.get(i).getName(),hand.get(i).getBild(),hand.get(i).getPunkte(),hand.get(i).getFarbe()));
	
			}
		}
		
		
		if(jokerWert==0 || jokerFarbe.equals("0")){
			
			JOptionPane.showMessageDialog (this, "Sie haben flasche Angaben zur Jokerkarte gemacht","Ung�ltige Werte",1);
			gespielteKarten.removeAll(gespielteKarten);
			
		}else{
			//Sortiert die FeldKarten nach grösse
			Collections.sort(gespielteKarten);
			
			
			//Wenn bereits Karten ausgespielt wurden, muss Stechlogik überprüft werden
			if(feldKarten.size()>0){
				
				System.out.println("feldkarten>0");
				
				//Wenn eine Einzelkarte gespielt wurde und sie höcher ist wie die bereits gespielte Karte, stich erfolgreich
				if(istEinzel(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					System.out.println("ist gestochen");
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
					
				}
								
				//Wenn die Karten ein Paar sind und sie höcher sind wie das bereits gespielte Paar, Stich erfolgreich
				else if(istPaar(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					System.out.println("gespielte Karte :" + gespielteKarten.get(0).getWert());
					System.out.println("feld Karte :" + feldKarten.get(0).getWert());
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Drillinge sind und sie höcher sind wie die bereits gespielten Drillinge, stich erfolgreich
				else if(istDrilling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Vierlinge sind und sie höcher sind wie die bereits gespielten Vierling, Stich erfolgriech
				else if(istVierling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Fünflinge sind und sie höcher sind wie die bereits gespielten F�nflinge Stich erfolgreich
				else if(istFünfling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Sechslinge sind und sie höcher sind wie die bereits gespielten Sechslinge, Stich erfolgreich
				else if(istSechsling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}		
				
				//Wenn die Karten Sieblinge sind und sie höcher sind wie die bereits gespielten Sieblinge, Stich erfolgreich
				else if(istSiebling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Achtlinge sind und sie höcher sind wie die bereits gespielten Achtlinge, Stich erfolgreich
				else if(istAchtling(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine dreier Strasse sind und sie höcher sind wie die bereits gespielte dreier Strasse, stich erfolgreich
				else if(istStrasseDrei(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine vierer Strasse sind und sie höcher sind wie die bereits gespielte vierer Strasse, stich erfolgreich
				else if(istStrasseVier(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine fünfer Strasse sind und sie höcher sind wie die bereits gespielte f�nfer Strasse, stich erfolgreich
				else if(istStrasseFünf(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine sechser Strasse sind und sie höcher sind wie die bereits gespielte sechser Strasse, stich erfolgreich
				else if(istStrasseSechs(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine siebner Strasse sind und sie höcher sind wie die bereits gespielte siebner Strasse, stich erfolgreich
				else if(istStrasseSieben(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine achter Strasse sind und sie höcher sind wie die bereits gespielte achter Strasse, stich erfolgriech
				else if(istStrasseAcht(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine neuner Strasse sind und sie höcher sind wie die bereits gespielte neuner Strasse, stich erfolgreich
				else if(istStrasseNeun(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine zehner Strasse sind und sie höcher sind wie die bereits gespielte zehner Strasse, stich erfolgriech
				else if(istStrasseZehn(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine elfer Strasse sind und sie höcher sind wie die bereits gespielte elfer Strasse, stich erfolgriech
				else if(istStrasseElf(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine zwölfer Strasse sind und sie höcher sind wie die bereits gespielte zw�lfer Strasse, stich erfolgreich
				else if(istStrasseZwölf(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Paar Strasse ist spiele und sie höcher ist wie die bereits gespielte Paar Strasse, stich erfolgreich
				else if(istPaarStrasse(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Drilling Strasse ist und sie höcher ist wie die bereits gespielte Drilling Strasse, stich erfolgreich
				else if(istDrillingStrasse(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Vierling Strasse ist und sie höcher ist wie die bereits gespielte Vierling Strasse, stich erfolgreich
				else if(istVierlingStrasse(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Fünfling Strasse ist und sie höcher ist wie die bereits gespielte F�nflng Strasse, Stich erfolgreich
				else if(istFünflingStrasse(gespielteKarten) && gespielteKarten.get(0).getWert() > feldKarten.get(0).getWert() && gespielteKarten.size() == feldKarten.size()){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn es keine gültige Kombination ist, wird dem Spieler eine Nachricht gesendet das der Zug ung�ltig ist
				else{
					JOptionPane.showMessageDialog (this, "Bitte Ueberpruefen Sie die Karten nochmals","Ung�ltige Zug",1);
					gespielteKarten.removeAll(gespielteKarten);
				}
					
			
				
			//Falls noch keine FeldKarten ausgespielt wurden muss nur die Ausspiellogik betrachtet werden	
			}else{
				//Alle Kontrollen werden durchgeführt ob es gültig auszuspielende Karten sind
				//Wenn die Karte eine einzelkarte ist dann Spiel sie aus
				if(istEinzel(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten ein Paar sind dann Spiel sie aus
				else if(istPaar(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Drillinge sind dann Spiele sie aus
				else if(istDrilling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Vierlinge sind dann Spiele sie aus
				else if(istVierling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Fuenflinge sind dann Spiele sie aus
				else if(istFünfling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Sechslinge sind dann Spiele sie aus
				else if(istSechsling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}		
				
				//Wenn die Karten Sieblinge sind dann Spiele sie aus
				else if(istSiebling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten Achtlinge sind dann Spiele sie aus
				else if(istAchtling(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine dreier Strasse sind spiele sie aus
				else if(istStrasseDrei(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine vierer Strasse sind spiele sie aus
				else if(istStrasseVier(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine fünfer Strasse sind spiele sie aus
				else if(istStrasseFünf(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine sechser Strasse sind spiele sie aus
				else if(istStrasseSechs(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine siebner Strasse sind spiele sie aus
				else if(istStrasseSieben(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine achter Strasse sind spiele sie aus
				else if(istStrasseAcht(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine neuner Strasse sind spiele sie aus
				else if(istStrasseNeun(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine zehner Strasse sind spiele sie aus
				else if(istStrasseZehn(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine elfer Strasse sind spiele sie aus
				else if(istStrasseElf(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine zwölfer Strasse sind spiele sie aus
				else if(istStrasseZwölf(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Paar Strasse ist spiele sie aus
				else if(istPaarStrasse(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Drilling Strasse ist spiele sie aus
				else if(istDrillingStrasse(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Vierling Strasse ist spiele sie aus
				else if(istVierlingStrasse(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn die Karten eine Fünfling Strasse ist spiele sie aus
				else if(istFünflingStrasse(gespielteKarten)){
					karteAnzeigen();
					kartenFeldKopieren(gespielteKarten);
				}
				
				//Wenn es keine gültige Kombination ist, wird dem Spieler eine Nachricht gesendet das der Zug ung�ltig ist
				else{
					JOptionPane.showMessageDialog (this, "Bitte überprüfen Sie die Karten nochmals","Ung�ltige Zug",1);
					gespielteKarten.removeAll(gespielteKarten);
				}
					
			}
			
			
		}
	}
	
	
	
	
	/***************************************************************************************
	Methoden welche zur Hilfe benutzt werden und oft aufgerufen werden
	****************************************************************************************/
	
	//Methode welche die gespielten Karten in die Feldkarten kopiert
	public void kartenFeldKopieren(ArrayList<Card> karten){
			
		for(int i = 0;i<karten.size();i++){
			
			if(feldKarten.size()<karten.size()){
				feldKarten.add(new Card(karten.get(i).getWert(),karten.get(i).getName(), karten.get(i).getBild(), karten.get(i).getPunkte(), karten.get(i).getFarbe(), karten.get(i).getJoker()));
			}else if(feldKarten.size()==karten.size()){
				feldKarten.set(i,new Card(karten.get(i).getWert(),karten.get(i).getName(), karten.get(i).getBild(), karten.get(i).getPunkte(), karten.get(i).getFarbe(), karten.get(i).getJoker()));
			}
		
		}
		
		gespielteKarten.removeAll(gespielteKarten);
		
	}
	
	//Methode welche die Karten in der Mitte anzeigt
	public void karteAnzeigen(){
		for(int i =0;i<gespielteKarten.size();i++){
			anzeigeKarten[i].setIcon(gespielteKarten.get(i).getBild());
		}
	}
	
	//Methode welche den Spieler auffordert einen Wert f�r den Joker einzugeben und diese zur�ck gibt
	public int jokerWert(int i){
		

		String str = JOptionPane.showInputDialog (null, "Wert für die Jokerkarte:");
		int karteWert = Integer.parseInt(str);
		
		//�berpr�ft um welche Jokerkarte es sich handelt und gibt falls n�tig die anweisung einen Richtigen Wert enzugeben = return 0
		if(i==0){
			if(karteWert > 11 || karteWert <2){
				JOptionPane.showMessageDialog (this, "Ein Bube kann nur den Wert zwischen 2 - 11 annehmen","Ung�ltiger Wert",1);
				return 0;
			}
		}
		else if(i==1){
			if(karteWert > 12 || karteWert <2){
				JOptionPane.showMessageDialog (this, "Eine Dame kann nur den Wert zwischen 2 - 12 annehmen","Ung�ltiger Wert",1);
				return 0;
			}
		}
		else if(i==2){
			if(karteWert > 13 && karteWert <2){
				JOptionPane.showMessageDialog (this, "Ein König kann nur den Wert zwischen 2 - 13 annehmen","Ung�ltiger Wert",1);
				return 0;
			}
		}
		return karteWert;
	}
	
	//Methode welche den Spieler auffordert eine Farbe für den Joker einzugeben und diese zurück gibt
	public String jokerFarbe(){
		
		String farbe = JOptionPane.showInputDialog (null, "Wert für die Jokerfarbe:");
		//überprüft ob die eingegeben farbe keine von den zulässigen ist und teilt die möglichkeiten dem Benutzer mit
		if(farbe.equals("rot") || farbe.equals("gruen") || farbe.equals("gelb") || farbe.equals("grau") || farbe.equals("orange")){
			return farbe;
		}
		JOptionPane.showMessageDialog (this, "Gültige Farben sind: rot, gelb, grau, gruen, orange","Ung�ltige Farbe",1);
		return "0";
	}
	
	//Methode welche den Wert und die Farbe einer Jokerkarte wechselt
	public void jokerWertWechsel(ArrayList<Card> karten){
		for(int i =0; i<karten.size();i++){
			if(karten.get(i).getJoker()){
				karten.get(i).setWert(karten.get(i).getJokerWert());
				karten.get(i).setFarbe(karten.get(i).getJokerFarbe());
			}
		}
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

	//Funktion welche die Bilder in die einzelnen Buttons ladet
	public void ladetBilder(ArrayList<Card> hand){
		
		
		//Diese ArrayList dient dazu die Karten an einen Spieler zu verteilen. 
		ArrayList<Card> spielerHand = new ArrayList<Card>();
		
		for(Card str: hand){
			Card copy = new Card(str.getWert(),str.getName(),str.getBild(),str.getPunkte(),str.getFarbe());
			spielerHand.add(copy);
		}
		
		//Diese for Schleife dient dazu dem Spieler die JokerKarten zu verteilen
		
		//Die Zähler Variable stellt die Anzahl Karten dar die in diesem Moment noch zu verteilen sind
		
		for(int i = 0; i<3; i++){
			
			if(spielerHand.get(i).getName().equals("bube")){
				
				jokerKarten[0].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());

			}
			
			else if(spielerHand.get(i).getName().equals("dame")){
				
				jokerKarten[1].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());

			}
			
			else if(spielerHand.get(i).getName().equals("koenig")){
				
				jokerKarten[2].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());

			}
			
		}
		

		for(int i = 3; i< 17; i++){
			
			if(spielerHand.get(i).getWert()==2){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());			

			}
			
			else if(spielerHand.get(i).getWert()==3){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			
			else if(spielerHand.get(i).getWert()==4){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==5){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==6){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==7){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==8){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==9){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==10){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==11){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==12){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			else if(spielerHand.get(i).getWert()==13){
				
				btnKarte[i-3].setIcon(spielerHand.get(i).getBild());
				spielerHand.set(i, new Card());
			}
			
		}
		
		
	}
	
	//Methode welche anhand der Reihenfolge der Farben des Sets die anderen Sets farblich sortiert
	public boolean karteFarbeSortieren(int set, ArrayList<Card> karten){
		
		int überprüfen = 0;
		int kartePosition = 0;
		String[] farben = new String[set]; //Dieser Array dient zur Vergleichsbasis der Farben
		Card[] temp = new Card[set]; // Hier werden die nächsten Karten des sets zwischengespeichert
		int karteErsetzen =set;

		//Diese Schleife nimmt jede Karte des Sets und fügt sie dem farben Array hinzu
		for(int i =0;i<set;i++){

			farben[i] = karten.get(i).getFarbe();
						
		}
		
		//Diese Schleife wird für jedes weitere Set durchgeführt (Drilling -1 = 2)
		for(int i = 0;i<karten.size()/set-1; i++){
			
			//Speichert das nächst grössere Set der Strasse in den temp Array
			for(int k = 0; k<set; k++){
				temp[k] = karten.get(kartePosition+set);
				kartePosition++;
			}
			
			//Die erste Karte in einem set wird mit jeder Karte des folgenden Sets verglichen dann die n�chsten		
			//F�r jede anzahl Karte in einem Set wird diese Schleife durchgeführt, weil alle verglichen werden m�ssen
			for(int j = 0; j<set; j++){
				
				//Diese Schleife überprüft die Farben und setzt die richtige Karte an die richtige Reihenfolge
				for(int k = 0;k<set;k++){
					if(farben[j].equals(temp[k].getFarbe())){
						karten.set(karteErsetzen, temp[k]);
						karteErsetzen++;
						überprüfen++;
					}
				}
			}
			
		}
		if(überprüfen == karten.size()-
				set){
			return true;
		}else{
			return false;
		}
		
	}
	
	//Methode welche überprüft ob es das gleiche Set ist (Paar, Drilling etc)
	public boolean istSet(ArrayList<Card> karten, int set){
		

		int bedingung = 0;
		
		int kartePosition = 0;	
		
		//Wenn eine Strasse aus Sets >1 zb Paar, Drilling etc besteht braucht es folgenge Logik
		if(set>1){
			
	
			
			//Es muss für jedes Set in der Strasse einmal das Set überprüft werden
			for(int i = 0;i<karten.size()/set;i++){
				
				//Für jedes einzelne Set muss die Anzahl Karten des set minus 1 überprüft werden bsp. Paar erste mit zweite Karte = 1 �berpr�fung
				for(int j = 0; j<set-1;j++){
					
					//Vergleicht zwei Karten innerhalb eines Sets
					if(karten.get(kartePosition).getWert() == karten.get(kartePosition+1).getWert()){
						bedingung++;
					}
				}
				kartePosition+=set;
			}
			
			//Die Bedingung wird durch eine Mathematische reihe getroffen
			if(bedingung==karten.size()/set*(set-1)){
				return true;
			}else{
				return false;
			}
			
		}else{

			for(int i = 0;i<karten.size()-1;i++){
				
				if(karten.get(i).getWert() == karten.get(i+1).getWert()){
					bedingung++;
				}
				
			}

			if(bedingung==karten.size()-1){				
				return true;
			}else{
				return false;
			}
		}
	}
	
	public boolean istStrasse(ArrayList<Card> karten, int set){
		
		int bedingungWert = 0;
		int bedingungFarbe = 0;
		int kartePosition = 0;
		
		//Wenn der Wert der drei Karten immer um eins höher ist
		for(int i = 0; i < karten.size()/set-1; i++){
			if(karten.get(kartePosition).getWert() == karten.get(kartePosition+set).getWert()-1){
				bedingungWert++;
				kartePosition+=set;
			}
		}
		if(set==1){
			for(int i = 0;i< karten.size()-1;i++){
				if(karten.get(i).getFarbe().equals(karten.get(i+1).getFarbe())){
					bedingungFarbe++;
				}
			}
		}
		if(bedingungWert==karten.size()/set-1){
			if(set>1){
				System.out.println("bedingungWert :" + bedingungWert);
				return true;
			}else{
				if((bedingungFarbe == karten.size()-1)){
					return true;
				}
			}
				
		}
		System.out.println("bedingungWert :" + bedingungWert);
		return false;
	}
	
	/***************************************************************************************
	Methoden welche kontrolliert ob die bei einem neuen Hand auszuspielenden Karten legitim 
	sind oder nicht, gibt einen true wert zurück	
	****************************************************************************************/
	//Methode zum überprüfen ob es eine einzelKarte ist
	public boolean istEinzel(ArrayList<Card> karten){
		
		//Ist nur eine Karte in den zu überprüfenden Karte ist es immer eine einzelne Karte
		if(karten.size()==1){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	//Methode zum überprüfen ob es ein Paar ist
	public boolean istPaar(ArrayList<Card> karten){
		
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Ein Paar ist nur möglich wenn es zwei Karten sind
		if(karten.size()==2){
			//Wenn der Wert beider Karten gleich ist dann ist es ein Paar
			if(istSet(karten,1)){
				return true;
			}else{
				return false;
			}
			
			
		}

		return false;
	}

	//Methode zum überprüfen ob es ein Drilling ist
	public boolean istDrilling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		
		//Ein Drilling ist nur möglich wenn es drei Karten sind
		if(karten.size()==3){
			//Wenn der Wert aller drei Karten gleich ist, ist es ein Drilling
			if(istSet(karten,1)){
				return true;
			}else{
				return false;
			}		
		}			
		return false;
	}

	//Methode zum überprüfen ob es ein Vierling ist
	public boolean istVierling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Ein Vierling ist nur möglich wenn es 4 Karten sind
		if(karten.size()==4){
			
			//Wenn alle 4 Karten gleich sind ist es ein Vierling
			if(istSet(karten,1)){
				return true;
			}else{
				return false;
			}
			
		}
	
		return false;
	}

	//Methode zum überprüfen ob es ein Fünfling ist
	public boolean istFünfling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Wenn es 5 Karten sind dann ist es ein Fünfling
		if(karten.size()==5){
			
			//Wenn alle 5 Karten gleich sind dann ist es ein Fünfling
			if(istSet(karten,1)){
				return true;
			}else{
				return false;
			}
		}
	
		
		return false;
	}
	
	//Methode zum überprüfen ob es ein Sechsling ist
	public boolean istSechsling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Wenn es 6 Karten sind dann ist es ein Sechsling
		if(karten.size()==6){
			
			//überprüfung ob alle Karten des Sets den gleichen Wert haben
			if(istSet(karten,1)){
				return true;
			}else {
				return false;
			}
			

		}	
		return false;
	}
	
	//Methode zum überprüfen ob es ein Siebling ist
	public boolean istSiebling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Wenn es 7 Karten sind dann ist es ein Siebling
		if(karten.size()==7){
			
			//überprüfung ob alle Karten des Sets den gleichen Wert haben
			if(istSet(karten,1)){
				return true;
			}else{
				return false;
			}
			
			
		}
			
		return false;
	}
	
	//Methode zum überprüfen ob es ein Achtling ist
	public boolean istAchtling(ArrayList<Card> karten){
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Wenn es 8 Karten sind dann ist es ein Achtling
		if(karten.size()==8){
			
			//überprüfung ob alle Karten des Sets den gleichen Wert haben
			if(istSet(karten,1)){
				return true;
			}else{
				return false;	
			}		
		}
		return false;
	}
	
	//Methode zum überprüfen ob es eine dreier Strasse ist
	public boolean istStrasseDrei(ArrayList<Card> karten){
		
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus drei Karten hat drei Karten =)
		if(karten.size()==3){
			
			if(istStrasse(karten, 1)){
				return true;
			}
						
		}
		
		return false;
	}

	//Methode zum überprüfen ob es eine vierer Strasse ist
	public boolean istStrasseVier(ArrayList<Card> karten){
		
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus vier Karten hat vier Karten =)
		if(karten.size()==4){
			if(istStrasse(karten, 1)){
				return true;
			}
			
		}
		
		return false;
		
	}
	
	//Methode zum überprüfen ob es einee fünfer Strasse ist
	public boolean istStrasseFünf(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus fünf Karten hat fünf Karten =)
		if(karten.size()==5){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine sechser Strasse ist
	public boolean istStrasseSechs(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus sechs Karten hat sechs Karten =)
		if(karten.size()==6){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine siebner Strasse ist
	public boolean istStrasseSieben(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus sieben Karten hat sieben Karten =)
		if(karten.size()==7){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine achter Strasse ist
	public boolean istStrasseAcht(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus acht Karten hat acht Karten =)
		if(karten.size()==8){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine neuner Strasse ist
	public boolean istStrasseNeun(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus neun Karten hat neun Karten =)
		if(karten.size()==9){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine zehner Strasse ist
	public boolean istStrasseZehn(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus zehn Karten hat zehn Karten =)
		if(karten.size()==10){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine elfer Strasse ist
	public boolean istStrasseElf(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus elf Karten hat elf Karten =)
		if(karten.size()==11){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}

	//Methode zum überprüfen ob es eine zwölfer Strasse ist
	public boolean istStrasseZwölf(ArrayList<Card> karten){
		//Sortiert die Karten neu sodass die Jokerkarten an den platz kommt wie ihr Wert eingegeben wurde
		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		//Eine Strasse aus zwölf Karten hat zwölf Karten =)
		if(karten.size()==12){
			
			if(istStrasse(karten, 1)){
				return true;
			}
		}
		
		return false;
	}
	
	//Methode zum überprüfen ob es eine Strasse mit Paaren ist
	public boolean istPaarStrasse(ArrayList<Card> karten){
		
		int kartePosition = 0;
		int überprüfen = 0; //Checkt ob es sich um Paare handelt
		boolean überprüfen4 = false; //Checkt ob die PaarStrasse farblich korrekt ist

		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		
		if(karten.size()>3){
			
			//Da es nur Paar sind sollte die ausgewählten Karten durch 2 teilbar sein
			if (karten.size()%2 == 0) { 
				

				
				//Wenn die letzte überprüfung stimmt geht es weiter
				if(istSet(karten,2)){
					System.out.println("istSet funktioniert");							
					//Wenn die letzte überprüfung stimmt geht es weiter
					if(istStrasse(karten,2)){
						
						System.out.println("istStrasse funktioniert");	
						//überprüfung damit Jokerkarten nicht eine bereits existierende karte kopieren k�nnen
						for(int i = 0;i < karten.size()/2; i++){
							if(karten.get(kartePosition).getFarbe() != karten.get(kartePosition+1).getFarbe()){
								überprüfen++;
								kartePosition+=2;
							}				
						}
					
						if(überprüfen== karten.size()/2){
							überprüfen4 = karteFarbeSortieren(2, karten);
							
							//Wenn die letzte überprüfung stimmt ist es eine zulässige Paarstrasse
							if(überprüfen4){
								return true;
							}
							
						}					
					}
				}
			}			
		}
		return false;
	}
	

	//Methode zum überprüfen ob es eine Drilling Strasse ist
	public boolean istDrillingStrasse(ArrayList<Card> karten){
		
		int kartePosition = 0;
		int überprüfen = 0; //Checkt ob es sich um Paare handelt
		boolean überprüfen4 = false; //Checkt ob die PaarStrasse farblich korrekt ist

		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		
		if(karten.size()>5){
			
			//Da es nur Paar sind sollte die ausgewählten Karten durch 2 teilbar sein
			if (karten.size()%3 == 0) { 
				

				
				//Wenn die letzte überprüfung stimmt geht es weiter
				if(istSet(karten,3)){
					System.out.println("istSet funktioniert");							
					//Wenn die letzte überprüfung stimmt geht es weiter
					if(istStrasse(karten,3)){
						
						System.out.println("istStrasse funktioniert");	
						//überprüfung damit Jokerkarten nicht eine bereits existierende karte kopieren k�nnen
						for(int i = 0;i < karten.size()/3; i++){
							if(karten.get(kartePosition).getFarbe() != karten.get(kartePosition+1).getFarbe()){
								überprüfen++;
								kartePosition+=3;
							}				
						}
					
						if(überprüfen== karten.size()/3){
							überprüfen4 = karteFarbeSortieren(3, karten);
							
							//Wenn die letzte überprüfung stimmt ist es eine zulüssige Paarstrasse
							if(überprüfen4){
								return true;
							}
							
						}					
					}
				}
			}			
		}
		return false;
	}

	//Methode zum überprüfen ob es eine VierlingStrasse ist
	public boolean istVierlingStrasse(ArrayList<Card> karten){
		
		int kartePosition = 0;
		int überprüfen = 0; //Checkt ob es sich um Paare handelt
		boolean überprüfen4 = false; //Checkt ob die PaarStrasse farblich korrekt ist

		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		
		if(karten.size()>7){
			
			//Da es nur Paar sind sollte die ausgewählten Karten durch 2 teilbar sein
			if (karten.size()%4 == 0) { 
				

				
				//Wenn die letzte überprüfung stimmt geht es weiter
				if(istSet(karten,4)){
					System.out.println("istSet funktioniert");							
					//Wenn die letzte überprüfung stimmt geht es weiter
					if(istStrasse(karten,4)){
						
						System.out.println("istStrasse funktioniert");	
						//überprüfung damit Jokerkarten nicht eine bereits existierende karte kopieren k�nnen
						for(int i = 0;i < karten.size()/4; i++){
							if(karten.get(kartePosition).getFarbe() != karten.get(kartePosition+1).getFarbe()){
								überprüfen++;
								kartePosition+=4;
							}				
						}
					
						if(überprüfen== karten.size()/4){
							überprüfen4 = karteFarbeSortieren(4, karten);
							
							//Wenn die letzte überprüfung stimmt ist es eine zulässige Paarstrasse
							if(überprüfen4){
								return true;
							}
							
						}					
					}
				}
			}			
		}
		return false;
	}

	//Methode zum überprüfen ob es eine Fünfling Strasse ist
	public boolean istFünflingStrasse(ArrayList<Card> karten){
		
		int kartePosition = 0;
		int überprüfen = 0; //Checkt ob es sich um Paare handelt
		boolean überprüfen4 = false; //Checkt ob die PaarStrasse farblich korrekt ist

		
		//Methode welche die Jokerwert in den Wert der Jokerkarte schreibt
		jokerWertWechsel(karten);

		//Karten neu sotieren nach ihrem Wert sodass die Jokerkarte an ihrem richtigen Platz ist
		Collections.sort(karten);
		
		
		if(karten.size()>9){
			
			//Da es nur Paar sind sollte die ausgewählten Karten durch 2 teilbar sein
			if (karten.size()%5 == 0) { 
				

				
				//Wenn die letzte überprüfung stimmt geht es weiter
				if(istSet(karten,5)){
					System.out.println("istSet funktioniert");							
					//Wenn die letzte überprüfung stimmt geht es weiter
					if(istStrasse(karten,5)){
						
						System.out.println("istStrasse funktioniert");	
						//überprüfung damit Jokerkarten nicht eine bereits existierende karte kopieren k�nnen
						for(int i = 0;i < karten.size()/5; i++){
							if(karten.get(kartePosition).getFarbe() != karten.get(kartePosition+1).getFarbe()){
								überprüfen++;
								kartePosition+=5;
							}				
						}
					
						if(überprüfen== karten.size()/5){
							überprüfen4 = karteFarbeSortieren(5, karten);
							
							//Wenn die letzte überprüfung stimmt ist es eine zulässige Paarstrasse
							if(überprüfen4){
								return true;
							}
							
						}					
					}
				}
			}			
		}
		return false;
	
	} 
	
	/***************************************************************************************
	Methoden welche kontrolliert ob die bei einem neuen Hand auszuspielenden Karten legitim 
	sind oder nicht, gibt einen true wert zurück	
	****************************************************************************************/
	
	
	
	/***************************************************************************************
	Handler welcher für das Anwählen von Karten zuständig ist
	****************************************************************************************/
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
	
	/***************************************************************************************
	Handler welcher für das klicken der Buttons zuständig ist
	****************************************************************************************/
	
	public class buttonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()== jbtSpielen){
				karteAuspielen();
			}
		}
		
	}
	
}	



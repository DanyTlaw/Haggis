import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Client {
	
	LoginGUI login;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Object inputObject;
	
	static int client_ID;
	public static Gameobjekt game;
	public static Chat chat;

	public static void main(String[] args){
		String hostName = "localhost";
		int portNummer = 50000;
		Client c = new Client(hostName, portNummer);
	}
	
	public Client(String hostName, int portNummer){
		init(hostName, portNummer);
		login = new LoginGUI(this.out, this.in);
		erhalteObjekt();
	}
	
	public void init(String hostName, int portNummer){
		try {
			socket = new Socket(hostName, portNummer);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
				}
		}
	
	public void erhalteObjekt(){
		// Erhalte
		try {
			while ((inputObject = in.readObject()) != null) {
				if (inputObject instanceof Gameobjekt) {
					game = (Gameobjekt) inputObject;
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(game.getSpielBeendet()){
						String resultat= "verloren";

						if(game.getSpieler(client_ID).getSieger()){
							resultat = "gewonnen";
						}
						
						
						JOptionPane.showMessageDialog (login.getTisch(),"Sie haben " + resultat ,"Ungueltige Farbe",JOptionPane.INFORMATION_MESSAGE);
					}
					
					//Wenn ein Spiel schon gestartet wurde wird das Gameobject hier nach der aufgaben ausführen
					if(!game.getNeueRunde()){
						ladetGegnerInfo();
						System.out.println(client_ID);
						
						
						
						//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
						if(game.getSpieler(client_ID).getAmZug()){
							login.getTisch().amZugButtons(true);																					
						}else{
							login.getTisch().amZugButtons(false);						
						}						
						if(game.getFeldkarten().size()>0){
							 login.getTisch().karteAnzeigen(game.getFeldkarten());							
						}else{
							login.getTisch().kartenFeldLoeschen();
						}

						
					}
					
					
					//Wenn die Spieler noch keine Handkarten haben werden neue Handkarten in ihre Handgeladen
					if(game.getNeueRunde()){
						
						System.out.println("Neues Spiel wird gestartet");
						
						//Ladet alle Karten Bilder
						login.getTisch().ladetBilder(game.getSpieler(client_ID).getHandKarten());
						login.getTisch().setHand(game.getSpieler(client_ID).getHandKarten());
						
						//setzt alle buttons auf visible
						login.getTisch().buttonsSichtbar();
						
						//Loescht alle Karten in der Mitte
						login.getTisch().kartenFeldLoeschen();
						
						//Setzt die Variable neugestartet auf false
						game.setNeueRunde(false);
						
						//Spieler eins darf anfangen die ist amZug variable wird true oder flase gesetzt
						if(this.client_ID==0){
							game.getSpieler(client_ID).setAmZug(true);
						}else{
							game.getSpieler(client_ID).setAmZug(false);
						}
						System.out.println("Spieler"+ client_ID +" :"+ game.getSpieler(client_ID).getAmZug());
						
						System.out.println("---------------------------------------------");
						
						//Wetten k�nnen abgeschlossen werden
						System.out.println("Wette kann getan werden");
						game.getSpieler(client_ID).setWette(login.getTisch().wetten());
						System.out.println("Die Wette ist: " + game.getSpieler(client_ID).getWette());
						game.getSpieler(client_ID).setGewettet(true);
						
						if(game.getSpieler(0).getGewettet() & game.getSpieler(1).getGewettet()){
							ladetGegnerInfo();
						}
						
						
						//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
						if(game.getSpieler(client_ID).getAmZug()){
							login.getTisch().amZugButtons(true);
						}						
					}
					
					
				} 
				
				else if(inputObject instanceof Chat){
					chat = (Chat) inputObject;
					
					if(login.getTisch().getTxtAChat().getText().equals("")){
						login.getTisch().getTxtAChat().setText(chat.getSpieler() +" : " +chat.getMessage());
					}else{
						login.getTisch().getTxtAChat().setText(login.getTisch().getTxtAChat().getText() + "\n" + chat.getSpieler() +" : " +chat.getMessage());
					}
					
					
					
					
				
				}
				
				// set Client_ID
				else if (inputObject instanceof Integer) {
					client_ID = (int) inputObject;
				} 
				
				
			}
		} catch (ClassNotFoundException | IOException cnfException) {
			cnfException.printStackTrace();
		}
	}
	
	//Methode welche die Informationen des Gegners ladet
	public void ladetGegnerInfo(){
	
		login.getTisch().haggis.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		login.getTisch().Haggisborder.setTitle("Haggis: " + game.getHaeggis().size());
		login.getTisch().haggis.setBorder(login.getTisch().Haggisborder);
		
		
		int anzahlKarten = 0;
		
		//Ladet alle Gegner Informationen
		if(client_ID == 0){
			login.getTisch().setGegnerInfos(game.getSpieler(1).hatBube(), game.getSpieler(1).hatDame(), game.getSpieler(1).hatKoenig());
			login.getTisch().setAnzahlKarten(game.getSpieler(1).getHandKarten().size()-3);
			login.getTisch().lblPunkteGegner.setText("Gegnerische Punktzahl : " +game.getSpieler(1).getPunkte());
			login.getTisch().lblPunkteEigen.setText("Punktzahl : " +game.getSpieler(0).getPunkte());
			login.getTisch().lblEigeneWetten.setText("Eigene Wetten: " +game.getSpieler(0).getWette());
			login.getTisch().lblGegnerWetten.setText("Gegner Wetten: " +game.getSpieler(1).getWette());
			
			
			for (int i = 3; i < game.getSpieler(1).getHandKarten().size(); i++){
				if(game.getSpieler(1).getHandKarten().get(i).getWert() != 0){
					anzahlKarten += 1;
					System.out.println(game.getSpieler(1).getHandKarten().get(i).getWert());
				}
			}
			
			login.getTisch().lblHandkarten.setText("Handkarten : " +anzahlKarten);
			anzahlKarten = 0;
		}
		else if(client_ID == 1){
			login.getTisch().setGegnerInfos(game.getSpieler(0).hatBube(), game.getSpieler(0).hatDame(), game.getSpieler(0).hatKoenig());
			login.getTisch().setAnzahlKarten(game.getSpieler(0).getHandKarten().size()-3);
			login.getTisch().lblPunkteGegner.setText("Gegnerische Punktzahl : " +game.getSpieler(0).getPunkte());
			login.getTisch().lblPunkteEigen.setText("Punktzahl : " +game.getSpieler(1).getPunkte());
			login.getTisch().lblEigeneWetten.setText("Eigene Wetten: " +game.getSpieler(1).getWette());
			login.getTisch().lblGegnerWetten.setText("Gegner Wetten: " +game.getSpieler(0).getWette());
			
			for (int i = 3; i < game.getSpieler(0).getHandKarten().size(); i++){
				if(game.getSpieler(0).getHandKarten().get(i).getWert() != 0){
					anzahlKarten += 1;
				}
			}
		
			login.getTisch().lblHandkarten.setText("Handkarten : " +anzahlKarten);
		}
		
	}
	
	
	}

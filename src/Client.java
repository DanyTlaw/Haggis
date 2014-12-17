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
	private String lblText;
	
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
						login.getTisch().jbtWetten.setEnabled(false);
						login.getTisch().jbtEingabe.setEnabled(false);
						login.getTisch().EigeneWetten.setEditable(false);
						login.getTisch().getTxtAEingabe().setEditable(false);
						login.getTisch().amZugButtons(false);
						String resultat = "verloren";
						String titel = "Viel Glueck beim naechsten Mal!";

						if(game.getSpieler(client_ID).getSieger()){
							resultat = "gewonnen";
							titel = "Herzlichen Glueckwunsch!";
						}
						
						
						JOptionPane.showMessageDialog (login.getTisch(),"Sie haben " + resultat ,titel,JOptionPane.INFORMATION_MESSAGE);
					}
					

					if(game.getWettenAbwicklung()){
						
						if(login.getTisch().getWette()){
													
							login.getTisch().EigeneWetten.setEditable(false);
							login.getTisch().jbtWetten.setEnabled(false);
							login.getTisch().setWette(false);
							
							
						}if(game.getSpieler(0).getGewettet() && game.getSpieler(1).getGewettet()){
							
							lblText = "Wette abgeschlossen, Spiel beginnt!";
							game.setRunde(game.getRunde()+1);
							login.getTisch().lblInfo.setText("<html><div style=\"text-align: center;\">" + lblText + "<br>Runde: " + game.getRunde() + "<br/>"  + "</html>");
							
							game.setWettenAbwicklung(false);
									
							//Spieler eins darf anfangen die ist amZug variable wird true oder flase gesetzt
							if(client_ID==0){
								game.getSpieler(client_ID).setAmZug(true);
								login.getTisch().EigeneWetten.setText("Ihre Wette: " + game.getSpieler(0).getWette());
								login.getTisch().GegnerWetten.setText("Gegner Wette: " + game.getSpieler(1).getWette());	
							}else{
								game.getSpieler(client_ID).setAmZug(false);
								login.getTisch().EigeneWetten.setText("Ihre Wette: " + game.getSpieler(1).getWette());
								login.getTisch().GegnerWetten.setText("Gegner Wette: " + game.getSpieler(0).getWette());
							}
							System.out.println("Spieler"+ client_ID +" :"+ game.getSpieler(client_ID).getAmZug());
							
							System.out.println("---------------------------------------------");		
							
							//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
							if(game.getSpieler(client_ID).getAmZug()){
								login.getTisch().amZugButtons(true);
								
							}	

						}
					}
					

					//Wenn ein Spiel schon gestartet wurde wird das Gameobject hier nach der aufgaben ausführen
					if(!game.getNeueRunde()){
											
						ladetGegnerInfo();
						
						if(game.getRunde()>1){
							login.getTisch().lblInfo.setText("<html><div style=\"text-align: center;\">" + "<br>Runde: " + game.getRunde() + "<br/>"  + "</html>");
						}
									
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
						
						
						lblText = "<html>Gegner gefunden <br>  Spiel startet sobald Wetten Platziert wurden!";
						login.getTisch().lblInfo.setText("<html><div style=\"text-align: center;\">" + lblText + "</html>");
						
						ladetGegnerInfo();
						
						
						login.getTisch().jbtWetten.setEnabled(true);
						login.getTisch().jbtEingabe.setEnabled(true);
						login.getTisch().EigeneWetten.setEditable(true);
						login.getTisch().getTxtAEingabe().setEditable(true);
						login.getTisch().EigeneWetten.setText("Platzieren Sie hier ihre Wette!");
						login.getTisch().GegnerWetten.setText("Gegner hat noch keine Wette Platziert");
						
						login.getTisch().amZugButtons(false);
						
						for(int i = 0; i < game.getSpielerList().size(); i++){
							game.getSpieler(i).setGewettet(false);
							game.getSpieler(i).setWette(0);
							game.getSpieler(i).setAmZug(false);
						}
							
						System.out.println("Spieler 0 Wette :" +game.getSpieler(0).getWette() + " " + game.getSpieler(0).getGewettet());
						System.out.println("Spieler 0 Wette :" +game.getSpieler(1).getWette() + " " + game.getSpieler(1).getGewettet());
						
						System.out.println("Neues Spiel wird gestartet");
						
						//Ladet alle Karten Bilder
						login.getTisch().ladetBilder(game.getSpieler(client_ID).getHandKarten());
						login.getTisch().setHand(game.getSpieler(client_ID).getHandKarten());
						
						//setzt alle buttons auf visible
						login.getTisch().buttonsSichtbar();
						
						//Loescht alle Karten in der Mitte
						game.getFeldkarten().clear();
						login.getTisch().kartenFeldLoeschen();
						
						game.setNeueRunde(false);
						game.setWettenAbwicklung(true);
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
			
			for (int i = 3; i < game.getSpieler(1).getHandKarten().size(); i++){
				if(game.getSpieler(1).getHandKarten().get(i).getWert() != 0){
					anzahlKarten += 1;
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
				
			for (int i = 3; i < game.getSpieler(0).getHandKarten().size(); i++){
				if(game.getSpieler(0).getHandKarten().get(i).getWert() != 0){
					anzahlKarten += 1;
				}
			}
		
			login.getTisch().lblHandkarten.setText("Handkarten : " +anzahlKarten);
		}
		
	}
	
	
	}

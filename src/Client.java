import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;


public class Client {
	
	LoginGUI login;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Object inputObject;
	
	static int client_ID;
	public static Gameobjekt game;
	

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
					//Wenn ein Spiel schon gestartet wurde wird das Gameobject hier nach der aufgaben ausführen
					if(!game.getNeueRunde()){
						ladetGegnerInfo();
						System.out.println(client_ID);
						//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
						if(game.getSpieler(client_ID).getAmZug()){
							login.getTisch().amZugButtons(true);
																					
						}else{
							login.getTisch().amZugButtons(false);						}
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
						
						ladetGegnerInfo();
						
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
						
						
						//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
						if(game.getSpieler(client_ID).getAmZug()){
							login.getTisch().amZugButtons(true);
						}						
					}
					
					
				} 
				
				else if(inputObject instanceof Chat){
					
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
		
		
		//Ladet alle Gegner Informationen
		if(client_ID == 0){
			login.getTisch().setGegnerInfos(game.getSpieler(1).hatBube(), game.getSpieler(1).hatDame(), game.getSpieler(1).hatKoenig());
			login.getTisch().setAnzahlKarten(game.getSpieler(1).getHandKarten().size()-3);
		}
		else if(client_ID == 1){
			login.getTisch().setGegnerInfos(game.getSpieler(0).hatBube(), game.getSpieler(0).hatDame(), game.getSpieler(0).hatKoenig());
			login.getTisch().setAnzahlKarten(game.getSpieler(0).getHandKarten().size()-3);
		}
		
	}
	
	
	}

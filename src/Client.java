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
	static Gameobjekt game;
	

	public static void main(String[] args){
		String hostName = "localhost";
		int portNummer = 50000;
		Client c = new Client(hostName, portNummer);
	}
	
	public Client(String hostName, int portNummer){
		init(hostName, portNummer);
		login = new LoginGUI(this.out, this.in);
		erhalteGameobjekt();
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
	
	public void erhalteGameobjekt(){
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
					
					//Wenn die Spieler noch keine Handkarten haben werden neue Handkarten in ihre Handgeladen
					if(login.getTisch().getNeuGestartet()){
						
						//Ladet alle Karten Bilder
						login.getTisch().ladetBilder(game.getSpieler(client_ID).getHandKarten());
						
						
						ladetGegnerInfo();
						
						//Setzt die Variable neugestartet auf false
						login.getTisch().setNeuGestartet(false);
						
						//Spieler eins darf anfangen die ist amZug variable wird true oder flase gesetzt
						if(this.client_ID==1){
							game.getSpieler(client_ID).setAmZug(true);
						}else{
							game.getSpieler(client_ID).setAmZug(false);
						}
						
						//Die Methode welche die Buttons disabled und enabled wird für beide Spieler aufgerufen
						if(game.getSpieler(client_ID).getAmZug()){
							login.getTisch().amZugButtons();
						}						
					}
					//Wenn ein Spiel schon gestartet wurde wird das Gameobject hier nach der aufgaben ausführen
					if(!login.getTisch().getNeuGestartet()){
						
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

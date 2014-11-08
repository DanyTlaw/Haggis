import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;


public class GameLoop extends Thread{

	//attributes
	private Socket socket = null;
	//static attributes
	static int client_ID = 0;
	static int user_ID = 0;
	public static ArrayList<Spieler> userlist = new ArrayList<Spieler>(2);
	public static ArrayList<ObjectOutputStream> outlist = new ArrayList<ObjectOutputStream>(2);

	public GameLoop(Socket socket){
		this.socket = socket;
	}
	
	
	public void run(){
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			outlist.add(out);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			Object inputObject;
			try {
				
				while ((inputObject = in.readObject()) != null) {
					//object from User
					if (inputObject instanceof Spieler){
						Spieler spieler = (Spieler) inputObject;
						spieler.setSpieler_ID(user_ID);
						userlist.add(spieler);
						user_ID++;
						out.writeObject(client_ID);
						client_ID++;
						System.out.println("erfolgreich verbunden");
						
						// creating Masterobject
						if (userlist.size() == 2) {

							Gameobjekt game = new Gameobjekt(userlist);
							//sleep - otherwise complications with Clients (update-problem)
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							// send Masterobject to Clients
							Iterator<ObjectOutputStream> i = outlist.iterator();
							while (i.hasNext()) {
								i.next().writeObject(game);

							}
						}
					}

					
					
					else if(inputObject instanceof Gameobjekt){

						Gameobjekt game = (Gameobjekt) inputObject;
						
						System.out.println("~~~~~~~~~~~~~~~~~JokerKarten Spieler 0 bevor zug ende~~~~~~~~~~~~~~~~~");
						for(int i = 0; i< 3; i++){
							System.out.println(game.getSpieler(0).getHandKarten().get(i).getPunkte());
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						
						System.out.println("~~~~~~~~~~~~~~~~~Handkarten Spieler 0 bevor zug ende~~~~~~~~~~~~~~~~~");
						for(int i = 3; i< game.getSpieler(0).getHandKarten().size(); i++){
							System.out.println(game.getSpieler(0).getHandKarten().get(i).getWert());
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						
						System.out.println("~~~~~~~~~~~~~~~~~JokerKarten Spieler 1 bevor zug ende~~~~~~~~~~~~~~~~~");
						for(int i = 0; i< 3; i++){
							System.out.println(game.getSpieler(1).getHandKarten().get(i).getPunkte());
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						
						System.out.println("~~~~~~~~~~~~~~~~~Handkarten Spieler 1 bevor zug ende~~~~~~~~~~~~~~~~~");
						for(int i = 3; i< game.getSpieler(1).getHandKarten().size(); i++){
							System.out.println(game.getSpieler(1).getHandKarten().get(i).getWert());
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

						
						//Wenn einer von beiden Spielern eine leeere Hand hat ist die Runde vorbei und die Logik wird angewandt
						if(game.getSpieler(0).leereHand()){
							System.out.println("leereHand Spieler 0");
							
							System.out.println("------------------JokerKarten Spieler 1------------------");
							for(int i = 0; i< 3; i++){
								System.out.println(game.getSpieler(1).getHandKarten().get(i).getPunkte());
							}
							System.out.println("---------------------------------------------------------");
							
							System.out.println("------------------Handkarten Spieler 1------------------");
							for(int i = 3; i< game.getSpieler(1).getHandKarten().size(); i++){
								System.out.println(game.getSpieler(1).getHandKarten().get(i).getWert());
							}
							System.out.println("---------------------------------------------------------");
							
							//Für jede handkarte die noch auf der gegnerischen Hand ist bekommt der Sieger der Runde 5 Punkte
							for(int i = 0; i< game.getSpieler(1).getHandKarten().size(); i++){
								if(game.getSpieler(1).getHandKarten().get(i).getWert()!=0){
									game.getSpieler(0).setPunkte(game.getSpieler(0).getPunkte()+5);
									System.out.println(game.getSpieler(0).getPunkte());
								}
							}
							
							//Added dem Sieger der Runde den Haggis hinzu
							game.getSpieler(0).addGewonneneKarten(game.getHaeggis());
							
							//Berchnet die Punkte der gewonenen Karten für beide Spieler
							game.getSpieler(0).setPunkte(game.getSpieler(0).berechnePunkte());
							game.getSpieler(1).setPunkte(game.getSpieler(1).berechnePunkte());
							
							System.out.println(game.getSpieler(0).getPunkte());
							
							//Wenn ein Spieler die abgemachten Punkte erreicht hat, hat er gewonnen
							if(game.getSpieler(0).getPunkte() >= game.getSpieler(0).getSiegesPunkte() && game.getSpieler(0).getPunkte() > game.getSpieler(1).getPunkte()){
								game.getSpieler(0).setSieger(true);
								game.setSpielBeendet(true);
							}else{
								game.setNeueRunde(true);
								game.erstelleDeck();
							}

						}
						else if(game.getSpieler(1).leereHand()){
							System.out.println("leereHand Spieler 1");
							
							//Für jede handkarte die noch auf der gegnerischen Hand ist bekommt der Sieger der Runde 5 Punkte
							for(int i = 0; i< game.getSpieler(0).getHandKarten().size(); i++){
								if(game.getSpieler(0).getHandKarten().get(i).getWert()!=0){
									game.getSpieler(1).setPunkte(game.getSpieler(1).getPunkte()+5);
									System.out.println(game.getSpieler(1).getPunkte());
								}
							}
							
							//Added dem Sieger der Runde den Haggis hinzu
							game.getSpieler(1).addGewonneneKarten(game.getHaeggis());
							
							//Berchnet die Punkte der gewonenen Karten für beide Spieler
							game.getSpieler(1).setPunkte(game.getSpieler(1).berechnePunkte());
							game.getSpieler(0).setPunkte(game.getSpieler(0).berechnePunkte());
							
							System.out.println(game.getSpieler(1).getPunkte());
							
							//Wenn ein Spieler die abgemachten Punkte erreicht hat, hat er gewonnen
							if(game.getSpieler(1).getPunkte() >= game.getSpieler(1).getSiegesPunkte() && game.getSpieler(1).getPunkte() > game.getSpieler(0).getPunkte()){
								game.getSpieler(1).setSieger(true);
								game.setSpielBeendet(true);
							}else{
								game.setNeueRunde(true);
								game.erstelleDeck();
							}

						}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					//Setzt die am zugvaribale fÃ¼r zwei spieler
					if(game.getSpielerList().size()==2){
						if(game.getSpieler(0).getAmZug()){
							game.getSpieler(0).setAmZug(false);		
							game.getSpieler(1).setAmZug(true);
						}else if(game.getSpieler(1).getAmZug()){
							game.getSpieler(0).setAmZug(true);
							game.getSpieler(1).setAmZug(false);
						}
					}


					//Uebergiebt dem nich passenden Spieler alle Karten da er der hÃ¶chste Stich hatte
					if(game.getSpieler(0).getPassen()){
						
						game.getSpieler(1).addGewonneneKarten(game.getAusgespielteKarten());
						game.getSpieler(0).setPassen(false);
						game.getAusgespielteKarten().removeAll(game.getAusgespielteKarten());
						game.getFeldkarten().removeAll(game.getFeldkarten());
					}
					else if(game.getSpieler(1).getPassen()){
						
						game.getSpieler(0).addGewonneneKarten(game.getAusgespielteKarten());
						game.getSpieler(1).setPassen(false);
						game.getAusgespielteKarten().removeAll(game.getAusgespielteKarten());
						game.getFeldkarten().removeAll(game.getFeldkarten());
					}
					
					
					
					Iterator<ObjectOutputStream> i = outlist.iterator();
					while (i.hasNext()) {
						i.next().writeObject(game);
						
					}
				}
					else if(inputObject instanceof Chat){
						Chat chat = (Chat) inputObject;
											
						
						System.out.println(chat.getMessage());
						Iterator<ObjectOutputStream> i = outlist.iterator();
						while (i.hasNext()) {
							i.next().writeObject(chat);
							
						}
						
					}
			}
					
					
					
				
			}catch (ClassNotFoundException cnfException) {
					cnfException.printStackTrace();
					} 
		}catch (IOException e) {
				e.printStackTrace();
		}
	}
}

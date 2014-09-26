import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


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
								System.out.println("gesendet game");
							}
						}
					}
					
					else if(inputObject instanceof Gameobjekt){

						Gameobjekt game = (Gameobjekt) inputObject;
						System.out.println("-------------------LEERE HAND---------------------");
						System.out.println(game.getSpieler(0).leereHand());
						System.out.println(game.getSpieler(1).leereHand());
						System.out.println("--------------------------------------------------");
						
						//Wenn einer von beiden Spielern eine leeere Hand hat ist die Runde vorbei und die Logik wird angewandt
						if(game.getSpieler(0).leereHand()){
							System.out.println("leereHand Spieler 0");
							game.getSpieler(0).addGewonneneKarten(game.getSpieler(1).getHandKarten());
							game.getSpieler(0).addGewonneneKarten(game.getHaeggis());
							game.setNeueRunde(true);
							System.out.println(game.getSpieler(0).berechnePunkte());
							game.erstelleDeck();
						}
						else if(game.getSpieler(1).leereHand()){
							System.out.println("leereHand Spieler 1");
							game.getSpieler(1).addGewonneneKarten(game.getSpieler(0).getHandKarten());
							game.getSpieler(1).addGewonneneKarten(game.getHaeggis());
							game.setNeueRunde(true);
							System.out.println(game.getSpieler(1).berechnePunkte());
							game.erstelleDeck();
						}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					//Setzt die am zugvaribale für zwei spieler
					if(game.getSpielerList().size()==2){
						if(game.getSpieler(0).getAmZug()){
							game.getSpieler(0).setAmZug(false);		
							game.getSpieler(1).setAmZug(true);
						}else if(game.getSpieler(1).getAmZug()){
							game.getSpieler(0).setAmZug(true);
							game.getSpieler(1).setAmZug(false);
						}
					}


					//Uebergiebt dem nich passenden Spieler alle Karten da er der höchste Stich hatte
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
						System.out.println("gesendet game");
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

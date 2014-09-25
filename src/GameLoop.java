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
						if(userlist.get(0).getAmZug()){
							game.getSpieler(0).setAmZug(false);
						}else{
							game.getSpieler(0).setAmZug(true);
						}
						if(userlist.get(1).getAmZug()){
							game.getSpieler(1).setAmZug(false);
						}else{
							game.getSpieler(1).setAmZug(true);
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

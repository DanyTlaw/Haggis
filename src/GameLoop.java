import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

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
	
	static Gameobjekt game;
	static int client_ID;
	
	
	
	public static void main(String[] args){
		String hostName = "localhost";
		int portNummer = 50000;
		Client c = new Client(hostName, portNummer);
		
		
		
	}
	
	public Client(String hostName, int portNummer){
		init(hostName, portNummer);
		login = new LoginGUI(this.out, this.in);
		erhalteObjektVomServer();
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
	
	public void erhalteObjektVomServer() {
		// receive the UserObject and do whatever the client has to do...
		try {
			while ((inputObject = in.readObject()) != null) {
				System.out.println("habe das gameobjekt erhalten");
				if (inputObject instanceof Gameobjekt) {
					game = (Gameobjekt) inputObject;
					System.out.println("Es ist enie Gameobject");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("ICH komme bis zur letzten aufruf");
					System.out.println(game.getSpieler(client_ID).getHandKarten().size());
					System.out.println("Hier ist das Tisch objekt" + login.getTisch());
					login.getTisch().ladetBilder(game.getSpieler(client_ID).getHandKarten());
				} 
				// set Client_ID
				else if (inputObject instanceof Integer) {
					client_ID = (int) inputObject;
					System.out.println("das ist meine ID" + client_ID);
				} 
				

			}
		} catch (ClassNotFoundException | IOException cnfException) {
			cnfException.printStackTrace();
		}
	}
	
}

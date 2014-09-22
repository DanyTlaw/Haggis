import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {

	LoginGUI login;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Object inputObject;
	
	
	
	
	
	public static void main(String[] args){
		String hostName = "localhost";
		int portNummer = 50000;
		Client c = new Client(hostName, portNummer);
		
		
		
	}
	
	public Client(String hostName, int portNummer){
		init(hostName, portNummer);
		LoginGUI login = new LoginGUI(this.out, this.in);
	
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
	
	
}

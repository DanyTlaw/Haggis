import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args){
		
		int portNumber = 50000;

		
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { // create ServerSocket
        	System.out.println("Waiting for clients..."); 
            while (GameLoop.userlist.size() < 2) { //check size userlist in class ServerThread

                new GameLoop(serverSocket.accept()).start(); // set up connection and start run method
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
	
		
	}
	
	
}

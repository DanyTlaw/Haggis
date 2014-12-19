import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

	public static ArrayList<GameLoop> userThread = new ArrayList<GameLoop>(2);
	
	public static void main(String[] args){
		
		int portNumber = 50000;
		
		//Hauptschlaufe
		while(true){
			try (ServerSocket serverSocket = new ServerSocket(portNumber)) { // create ServerSocket
        	System.out.println("Waiting for clients..."); 
         
        	//Schlaufe bis genuegend Clients angemeldet wurden
        	while (userThread.size() < 2) { //check size userlist in class ServerThread

            	GameLoop newClient = new GameLoop(serverSocket.accept());// set up connection and start run method
            	newClient.start();
            	userThread.add(newClient);
            }
            
            System.out.println("Alle clients angemeldet");
            
            //Schlaufe solange Spiel lauft(2 Clients angemeldet)
            while(checkConnections()){
            	System.out.println("Game lauft");
            	try {
					Thread.sleep(5000);
					System.out.println("Spiel aktualisiert!");
				}catch (InterruptedException e) {
					System.out.println("Server abgestürzt");
				}
            }
            
            //Wenn Spiel abgebrochen bestehende verbindungen zu clients werden unterbrochen
            for(int i = 0; i < userThread.size(); i++){
            	userThread.get(i).closedConnection();
            	System.out.println("Connection: " + i);
            }
            userThread.clear();
            //Server wird terminiert
            break;
            
            
		}catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
	        System.exit(-1);	
		}
	}			
}
	//Methode welche ueberprueft das Server und Client verbindung bestehen
	public static boolean checkConnections(){
		boolean connectionStatus = true;
		System.out.println("Checking Connections--------");
		
		 for(int i = 0; i < userThread.size(); i++){
         	if(!userThread.get(i).isAlive()){
         		connectionStatus = false;
         		break;
         	}
        }
		
		return connectionStatus;
	}
	
}

import java.io.Serializable;


public class Chat implements Serializable {

	private String message;
	private String spieler;
	public Chat(){
		
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setSpieler(String spieler){
		this.spieler = spieler;
	}
	
	public String getSpieler(){
		return this.spieler;
	}
	
}

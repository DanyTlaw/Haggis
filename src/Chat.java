import java.io.Serializable;


public class Chat implements Serializable {

	private String message;
	
	public Chat(){
		
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	
	
}

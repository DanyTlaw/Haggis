import java.io.Serializable;
import java.util.ArrayList;


public class Gameobjekt implements Serializable {

	public ArrayList<Spieler> spieler;
	
	
	
	
	public Gameobjekt(ArrayList<Spieler> spieler){
		
		this.spieler = spieler;
		
		Deck deck = new Deck();
		deck.aufteilen(spieler.size());
		
		for(int i=0;i<spieler.size();i++){
			this.spieler.get(i).setHandKarten(deck.getHand(i));
		}
		
		System.out.println("Jeder Spieler hat eine hand");
		
		System.out.println(spieler.get(0).getHandKarten().size());
		System.out.println(spieler.get(1).getHandKarten().size());
		
	}
	
	public Spieler getSpieler(int i){
		return this.spieler.get(i);
	}
	
}

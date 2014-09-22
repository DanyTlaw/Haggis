import java.io.Serializable;
import java.util.ArrayList;


public class Spieler implements Serializable {

	//Spieler informationen
	private String spielerName;
	private int spieler_ID;
	private ArrayList<Card> handKarten;
	private int punkte;
	private boolean amZug;
	
	
	
	public Spieler(String name) {
		this.spielerName = name;
		handKarten = new ArrayList<Card>();
	}
	
	//Getters und Setters
	public String getSpielerName() {
		return spielerName;
	}
	public void setSpielerName(String spielerName) {
		this.spielerName = spielerName;
	}
	public int getSpieler_ID() {
		return spieler_ID;
	}
	public void setSpieler_ID(int spieler_ID) {
		this.spieler_ID = spieler_ID;
	}
	public ArrayList<Card> getHandKarten() {
		return handKarten;
	}
	public void setHandKarten(ArrayList<Card> handKarten) {
		this.handKarten = handKarten;
	}
	public int getPunkte() {
		return punkte;
	}
	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}
	public boolean getAmZug() {
		return amZug;
	}
	public void setAmZug(boolean amZug) {
		this.amZug = amZug;
	}
	
	
	
	
}

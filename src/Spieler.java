import java.io.Serializable;
import java.util.ArrayList;


public class Spieler implements Serializable {

	/**
	 * 
	 */

	//Spieler informationen
	private String spielerName;
	private int spieler_ID;
	private ArrayList<Card> handKarten;
	private int punkte;
	private boolean amZug;
	private boolean passen = false;
	private int siegesPunkte;
	private boolean sieger = false;
	private int wette;
	private volatile boolean gewettet = false;
	
	public ArrayList<Card> gewonneneKarten = new ArrayList<Card>();
	
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
	
	public int getWette(){
		return this.wette;
	}
	
	public void setWette(int wette){
		this.wette = wette;
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
	
	public void setSiegesPunkte(int sPunkte){
		this.siegesPunkte = sPunkte;
	}
	
	public int getSiegesPunkte(){
		return this.siegesPunkte;
	}
	
	public void setSieger(boolean sieger){
		this.sieger = sieger;
	}
	
	public boolean getSieger(){
		return this.sieger;
	}
	public boolean getGewettet(){
			return this.gewettet;
	}
	public void setGewettet(boolean gewettet){
		this.gewettet = gewettet;
	}
	
	public ArrayList<Card> getGewonneneKarten(){
		return this.gewonneneKarten;
	}
	
	
	//Methode welche den gewonnen Karten immer neue hinzufügt
	public void addGewonneneKarten(ArrayList<Card> karten){
		
		for(int i= 0; i<karten.size();i++){	
			gewonneneKarten.add(new Card(karten.get(i).getPunkte()));

		}
	}
	
	//Gibt ein boolean zurück welcher sagt ob der Spieler noch ein Bube hat
	public int hatBube(){
		
		for(int i = 0; i<handKarten.size();i++){
			if(handKarten.get(i).getWert()==11){
				return 1;
			}
		}
		return 0;
		
	}
	//Gibt ein boolean zurück welcher sagt ob der Spieler noch ein Dame hat
	public int hatDame(){
		
		for(int i = 0; i<handKarten.size();i++){
			if(handKarten.get(i).getWert()==12){
				return 1;
			}
		}
		return 0;
		
	}
	
	//Gibt ein boolean zurück welcher sagt ob der Spieler noch ein Koenig hat
	public int hatKoenig(){
		
		for(int i = 0; i<handKarten.size();i++){
			if(handKarten.get(i).getWert()==13){
				return 1;
			}
		}
		return 0;
		
	}
	
	public boolean getPassen(){
		return this.passen;
	}
	
	public void setPassen(boolean passen){
		this.passen = passen;
	}
	
	//Rechnet alle Werte zusammen der Karten welche bei einer nicht vorhandenen Karte schon 0 gestzt worden ist beim auspielen
	public boolean leereHand(){
		int alleKarten= 0;
		for(int i = 0; i<17;i++){
			alleKarten+= handKarten.get(i).getWert();
		}
		if(alleKarten==0){
			return true;
		}
		return false;			
	}
	
	//Methode welche alle Punkte in den gewonnen Karten zusammenrechnet
	public int berechnePunkte(){
		
		for(int i = 0; i<gewonneneKarten.size();i++){
			punkte+=gewonneneKarten.get(i).getPunkte();
		}
		return punkte;
	}
	
}

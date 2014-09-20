import javax.swing.ImageIcon;

//Diese Klasse enth�lt die Logik einer Karte
public class Card implements Comparable<Card> {
	
	int wert;
	String name;
	ImageIcon bild;
	int punkte;
	String farbe;
	boolean joker;
	int jokerWert;
	String jokerFarbe;
	
	//Konstruktor	
	public Card(){
		
	}
	
	public Card(int wert, String name, ImageIcon bild, int punkte, String farbe){
		
		this.wert = wert;
		this.name = name;
		this.bild = bild;
		this.punkte = punkte;
		this.farbe = farbe;
	}
	public Card(int wert, String name, ImageIcon bild, int punkte, String farbe, boolean joker){
		
		this.wert = wert;
		this.name = name;
		this.bild = bild;
		this.punkte = punkte;
		this.farbe = farbe;
		this.joker =joker;
	}
	
	public Card(int wert, String name, ImageIcon bild, int punkte, String farbe, boolean joker, int jokerWert, String jokerFarbe){
		
		this.wert = wert;
		this.name = name;
		this.bild = bild;
		this.punkte = punkte;
		this.farbe = farbe;
		this.joker =joker;
		this.jokerWert = jokerWert;
		this.jokerFarbe = jokerFarbe;
	}
	
	//Getters und Setters
	public int getWert(){
		return this.wert;
	}
	
	public void setWert(int wert){
		this.wert = wert;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public ImageIcon getBild(){
		return this.bild;
	}
	
	public void setBild(ImageIcon bild){
		this.bild = bild;
	}
	public int getPunkte(){
		return this.punkte;
	}
	
	public void setPunkte(int punkte){
		this.punkte = punkte;
	}
	
	public String getFarbe(){
		return this.farbe;
	}
	
	public void setFarbe(String farbe){
		this.farbe = farbe;
	}
	
	public void setJoker(Boolean Joker){
		this.joker = Joker;
	}
	
	public boolean getJoker(){
		return this.joker;
	}
	
	public int getJokerWert(){
		return this.jokerWert;
	}
	
	public void setJokerWert(int jokerWert){
		this.jokerWert = jokerWert;
	}
	
	public String getJokerFarbe(){
		return this.jokerFarbe;
	}
	
	public void setJokerFarbe(String jfarbe){
		this.jokerFarbe = jfarbe;
	}
	
	@Override
	public int compareTo(Card compare) {
		int wert =  ((Card) compare).getWert();
		return this.wert - wert;
	}
	



	

	
}

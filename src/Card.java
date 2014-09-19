import javax.swing.ImageIcon;

//Diese Klasse enthält die Logik einer Karte
public class Card {
	
	int wert;
	String name;
	ImageIcon bild;
	int punkte;
	String farbe;
	
	
	//Konstruktor	
	public Card(int wert, String name, ImageIcon bild, int punkte, String farbe){
		
		this.wert = wert;
		this.name = name;
		this.bild = bild;
		this.punkte = punkte;
		this.farbe = farbe;
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
	
}

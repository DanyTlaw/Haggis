import javax.swing.ImageIcon;

//Diese Klasse enthält die Logik einer Karte
public class Card {
	
	int wert;
	String name;
	ImageIcon bild;
	
	
	//Konstruktor
	public Card(int wert, String name, ImageIcon bild){
		
		this.wert = wert;
		this.name = name;
		this.bild = bild;
		
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
	
}

import java.io.Serializable;
import java.util.ArrayList;


public class Gameobjekt implements Serializable {

	private ArrayList<Spieler> spieler;
	private ArrayList<Card> feldkarten = new ArrayList<Card>();
	private ArrayList<Card> ausgespielteKarten = new ArrayList<Card>();
	private ArrayList<Card> haeggis = new ArrayList<Card>();
	
	private boolean neueRunde;
	private boolean spielBeendet;
	private boolean bombe = false;
	private boolean wettenAbwicklung;
	private boolean rundenEnde = false;
	
	private int runde = 0;
	
	
	
	public Gameobjekt(ArrayList<Spieler> spieler){
		this.neueRunde = true;
		this.spieler = spieler;
		
		erstelleDeck();

	}
	
	public ArrayList<Spieler> getSpielerList(){
		return this.spieler;
	}
	
	public Spieler getSpieler(int i){
		return this.spieler.get(i);
	}
	
	public ArrayList<Card> getFeldkarten(){
		return this.feldkarten;
	}
	
	public void setFeldkarten(ArrayList<Card> feldkarten){
		this.feldkarten = feldkarten;
	}
	
	public void addAusgespielteKarten(ArrayList<Card> karten){
		
		for(int i= 0; i<karten.size();i++){	
			ausgespielteKarten.add(new Card(karten.get(i).getPunkte()));
		}
	}
	
	public ArrayList<Card> getAusgespielteKarten(){
		return this.ausgespielteKarten;
	}

	public ArrayList<Card> getHaeggis(){
		return this.haeggis;
	}
	
	public boolean getNeueRunde(){
		return this.neueRunde;
	}
	
	public void setNeueRunde(boolean neueRunde){
		this.neueRunde = neueRunde;
	}
	
	public void setSpielBeendet(boolean spielBeendet){
		this.spielBeendet = spielBeendet;
	}
	
	public boolean getSpielBeendet(){
		return this.spielBeendet;
	}
	
	public boolean getBombe(){
		return this.bombe;
	}
	
	public void setBombe(boolean bombe){
		this.bombe = bombe;
	}
	
	public void erstelleDeck(){
		
		System.out.println("Deck wird erstellt");
		Deck deck = new Deck();
		deck.aufteilen(spieler.size());
		
		for(int i=0;i<spieler.size();i++){
			this.spieler.get(i).setHandKarten(deck.getHand(i));
		}

		this.haeggis = deck.getHaeggis();
	}
	
	public boolean getWettenAbwicklung(){
		return this.wettenAbwicklung;
	}
	
	public void setWettenAbwicklung(boolean wettenAbwicklung) {
		this.wettenAbwicklung = wettenAbwicklung;
		
	}
	
	public void setRunde(int runde){
		this.runde = runde;
	}
	
	public int getRunde(){
		return this.runde;
	}
	
	public boolean getRundenEnde(){
		return this.rundenEnde;
	}
	
	public void setRundenEnde(boolean rundenEnde){
		this.rundenEnde = rundenEnde;
	}
	
}

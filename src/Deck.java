import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//Diese Klasse enthaelt die Logik eines Deckes
public class Deck {
	
	
	//Bilder fuer die Karten
	//Variablen fuer Graue Karten
	public Card grau2;
	public Card grau3;
	public Card grau4;
	public Card grau5;
	public Card grau6;
	public Card grau7;
	public Card grau8;
	public Card grau9;
	public Card grau10;
	
	//Variablen fuer Gelbe Karten
	public Card gelb2;
	public Card gelb3;
	public Card gelb4;
	public Card gelb5;
	public Card gelb6;
	public Card gelb7;
	public Card gelb8;
	public Card gelb9;
	public Card gelb10;
	
	//Variablen fuer orange Karten
	public Card orange2;
	public Card orange3;
	public Card orange4;
	public Card orange5;
	public Card orange6;
	public Card orange7;
	public Card orange8;
	public Card orange9;
	public Card orange10;	
	
	//Variablen fuer rote Karten
	public Card rot2;
	public Card rot3;
	public Card rot4;
	public Card rot5;
	public Card rot6;
	public Card rot7;
	public Card rot8;
	public Card rot9;
	public Card rot10;
	
	//Variablen fuer gruene Karten
	public Card gruen2;
	public Card gruen3;
	public Card gruen4;
	public Card gruen5;
	public Card gruen6;
	public Card gruen7;
	public Card gruen8;
	public Card gruen9;
	public Card gruen10;
	
	//JokerKarten	
	public Card bube1;	
	public Card dame1;
	public Card koenig1;
	
	private Card bube2;
	private Card dame2;
	private Card koenig2;
	
	private Card bube3;
	private Card dame3;
	private Card koenig3;

	public boolean spieler1 = true;
	
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	public ArrayList<Card> haeggis = new ArrayList<Card>();
	
	public ArrayList<ArrayList<Card>> haende = new ArrayList<ArrayList<Card>>(3);
	public ArrayList<Card> handKarten1 = new ArrayList<Card>();
	public ArrayList<Card> handKarten2 = new ArrayList<Card>();
	
	private int kartenBreite = 60;
	private int kartenHoehe = 100;
	
	
	//Konstruktor welche ein Deck mit allen 54 Karten vom Typ Card erstellt
	public Deck(){
		
		String pfad = System.getProperty("user.dir") + "//images//";
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 double width = screenSize.getWidth();
		 double height = screenSize.getHeight();
		
		if(height > 1000 && width > 1300){
			 kartenBreite = 100;
			 kartenHoehe = 150;
		}
		
		//Graue Icons
		Image imageGrau2 = new ImageIcon(pfad + "grau02.jpg").getImage();
		ImageIcon iconGrau2 = new ImageIcon(imageGrau2.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	

		Image imageGrau3 = new ImageIcon(pfad + "grau03.jpg").getImage();
		ImageIcon iconGrau3 = new ImageIcon(imageGrau3.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau4 = new ImageIcon(pfad + "grau04.jpg").getImage();
		ImageIcon iconGrau4 = new ImageIcon(imageGrau4.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau5 = new ImageIcon(pfad + "grau05.jpg").getImage();
		ImageIcon iconGrau5 = new ImageIcon(imageGrau5.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau6 = new ImageIcon(pfad + "grau06.jpg").getImage();
		ImageIcon iconGrau6 = new ImageIcon(imageGrau6.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau7 = new ImageIcon(pfad + "grau07.jpg").getImage();
		ImageIcon iconGrau7 = new ImageIcon(imageGrau7.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau8 = new ImageIcon(pfad + "grau08.jpg").getImage();
		ImageIcon iconGrau8 = new ImageIcon(imageGrau8.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau9 = new ImageIcon(pfad + "grau09.jpg").getImage();
		ImageIcon iconGrau9 = new ImageIcon(imageGrau9.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGrau10 = new ImageIcon(pfad + "grau10.jpg").getImage();
		ImageIcon iconGrau10 = new ImageIcon(imageGrau10.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		//Gelbe Icons
		Image imageGelb2 = new ImageIcon(pfad + "gelb02.jpg").getImage();
		ImageIcon iconGelb2 = new ImageIcon(imageGelb2.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));	
		
		Image imageGelb3 = new ImageIcon(pfad + "gelb03.jpg").getImage();
		ImageIcon iconGelb3 = new ImageIcon(imageGelb3.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb4 = new ImageIcon(pfad + "gelb04.jpg").getImage();
		ImageIcon iconGelb4 = new ImageIcon(imageGelb4.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb5 = new ImageIcon(pfad + "gelb05.jpg").getImage();
		ImageIcon iconGelb5 = new ImageIcon(imageGelb5.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb6 = new ImageIcon(pfad + "gelb06.jpg").getImage();
		ImageIcon iconGelb6 = new ImageIcon(imageGelb6.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb7 = new ImageIcon(pfad + "gelb07.jpg").getImage();
		ImageIcon iconGelb7 = new ImageIcon(imageGelb7.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb8 = new ImageIcon(pfad + "gelb08.jpg").getImage();
		ImageIcon iconGelb8 = new ImageIcon(imageGelb8.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb9 = new ImageIcon(pfad + "gelb09.jpg").getImage();
		ImageIcon iconGelb9 = new ImageIcon(imageGelb9.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGelb10 = new ImageIcon(pfad + "gelb10.jpg").getImage();
		ImageIcon iconGelb10 = new ImageIcon(imageGelb10.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		//Gruene Icons
		Image imageGruen2 = new ImageIcon(pfad + "gruen02.jpg").getImage();
		ImageIcon iconGruen2 = new ImageIcon(imageGruen2.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen3 = new ImageIcon(pfad + "gruen03.jpg").getImage();
		ImageIcon iconGruen3 = new ImageIcon(imageGruen3.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen4 = new ImageIcon(pfad + "gruen04.jpg").getImage();
		ImageIcon iconGruen4 = new ImageIcon(imageGruen4.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen5 = new ImageIcon(pfad + "gruen05.jpg").getImage();
		ImageIcon iconGruen5 = new ImageIcon(imageGruen5.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen6 = new ImageIcon(pfad + "gruen06.jpg").getImage();
		ImageIcon iconGruen6 = new ImageIcon(imageGruen6.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen7 = new ImageIcon(pfad + "gruen07.jpg").getImage();
		ImageIcon iconGruen7 = new ImageIcon(imageGruen7.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen8 = new ImageIcon(pfad + "gruen08.jpg").getImage();
		ImageIcon iconGruen8 = new ImageIcon(imageGruen8.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen9 = new ImageIcon(pfad + "gruen09.jpg").getImage();
		ImageIcon iconGruen9 = new ImageIcon(imageGruen9.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageGruen10 = new ImageIcon(pfad + "gruen10.jpg").getImage();
		ImageIcon iconGruen10 = new ImageIcon(imageGruen10.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		//Orange Icons
		Image imageOrange2 = new ImageIcon(pfad + "orange02.jpg").getImage();
		ImageIcon iconOrange2 = new ImageIcon(imageOrange2.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange3 = new ImageIcon(pfad + "orange03.jpg").getImage();
		ImageIcon iconOrange3 = new ImageIcon(imageOrange3.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange4 = new ImageIcon(pfad + "orange04.jpg").getImage();
		ImageIcon iconOrange4 = new ImageIcon(imageOrange4.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange5 = new ImageIcon(pfad + "orange05.jpg").getImage();
		ImageIcon iconOrange5 = new ImageIcon(imageOrange5.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange6 = new ImageIcon(pfad + "orange06.jpg").getImage();
		ImageIcon iconOrange6 = new ImageIcon(imageOrange6.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange7 = new ImageIcon(pfad + "orange07.jpg").getImage();
		ImageIcon iconOrange7 = new ImageIcon(imageOrange7.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange8 = new ImageIcon(pfad + "orange08.jpg").getImage();
		ImageIcon iconOrange8 = new ImageIcon(imageOrange8.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange9 = new ImageIcon(pfad + "orange09.jpg").getImage();
		ImageIcon iconOrange9 = new ImageIcon(imageOrange9.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageOrange10 = new ImageIcon(pfad + "orange10.jpg").getImage();
		ImageIcon iconOrange10 = new ImageIcon(imageOrange10.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		//Rote Icons
		Image imageRot2 = new ImageIcon(pfad + "rot02.jpg").getImage();
		ImageIcon iconRot2 = new ImageIcon(imageRot2.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot3 = new ImageIcon(pfad + "rot03.jpg").getImage();
		ImageIcon iconRot3 = new ImageIcon(imageRot3.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot4 = new ImageIcon(pfad + "rot04.jpg").getImage();
		ImageIcon iconRot4 = new ImageIcon(imageRot4.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot5 = new ImageIcon(pfad + "rot05.jpg").getImage();
		ImageIcon iconRot5 = new ImageIcon(imageRot5.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot6 = new ImageIcon(pfad + "rot06.jpg").getImage();
		ImageIcon iconRot6 = new ImageIcon(imageRot6.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot7 = new ImageIcon(pfad + "rot07.jpg").getImage();
		ImageIcon iconRot7 = new ImageIcon(imageRot7.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot8 = new ImageIcon(pfad + "rot08.jpg").getImage();
		ImageIcon iconRot8 = new ImageIcon(imageRot8.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot9 = new ImageIcon(pfad + "rot09.jpg").getImage();
		ImageIcon iconRot9 = new ImageIcon(imageRot9.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageRot10 = new ImageIcon(pfad + "rot10.jpg").getImage();
		ImageIcon iconRot10 = new ImageIcon(imageRot10.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		//Jokerkarten Icons
		Image imageBube = new ImageIcon(pfad + "Bube.jpg").getImage();
		ImageIcon iconBube = new ImageIcon(imageBube.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageDame = new ImageIcon(pfad + "Dame.jpg").getImage();
		ImageIcon iconDame = new ImageIcon(imageDame.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		Image imageKoenig = new ImageIcon(pfad + "Koenig.jpg").getImage();
		ImageIcon iconKoenig = new ImageIcon(imageKoenig.getScaledInstance(kartenBreite, kartenHoehe, Image.SCALE_DEFAULT));
		
		//Variablen fuer Graue Karten
		grau2 = new Card(2,"grau2",iconGrau2,0,"grau");
		grau3 = new Card(3, "grau3",iconGrau3,1,"grau");
		grau4 = new Card(4, "grau4",iconGrau4,0,"grau");
		grau5 = new Card(5, "grau5",iconGrau5,1,"grau");
		grau6 = new Card(6, "grau6",iconGrau6,0,"grau");
		grau7 = new Card(7, "grau7",iconGrau7,1,"grau");
		grau8 = new Card(8, "grau8",iconGrau7,0,"grau");
		grau9 = new Card(9, "grau9",iconGrau9,1,"grau");
		grau10 = new Card(10, "grau10",iconGrau10,0,"grau");
		
		//Variablen fuer Gelbe Karten
		gelb2 = new Card(2, "gelb2",iconGelb2,0,"gelb");
		gelb3 = new Card(3, "gelb3",iconGelb3,1,"gelb");
		gelb4 = new Card(4, "gelb4",iconGelb4,0,"gelb");
		gelb5 = new Card(5, "gelb5",iconGelb5,1,"gelb");
		gelb6 = new Card(6, "gelb6",iconGelb6,0,"gelb");
		gelb7 = new Card(7, "gelb7",iconGelb7,1,"gelb");
		gelb8 = new Card(8, "gelb8",iconGelb8,0,"gelb");
		gelb9 = new Card(9, "gelb9",iconGelb9,1,"gelb");
		gelb10 = new Card(10, "gelb10",iconGelb10,0,"gelb");
		
		//Variablen fuer orange Karten
		orange2 = new Card(2, "orange2",iconOrange2,0,"orange");
		orange3 = new Card(3, "orange3",iconOrange3,1,"orange");
		orange4 = new Card(4, "orange4",iconOrange4,0,"orange");
		orange5 = new Card(5, "orange5",iconOrange5,1,"orange");
		orange6 = new Card(6, "orange6",iconOrange6,0,"orange");
		orange7 = new Card(7, "orange7",iconOrange7,1,"orange");
		orange8 = new Card(8, "orange8",iconOrange8,0,"orange");
		orange9 = new Card(9, "orange9",iconOrange9,1,"orange");
		orange10 = new Card(10, "orange10",iconOrange10,0,"orange");	
		
		//Variablen fuer rote Karten
		rot2 = new Card(2, "rot2",iconRot2,0,"rot");
		rot3 = new Card(3, "rot3",iconRot3,1,"rot");
		rot4 = new Card(4, "rot4",iconRot4,0,"rot");
		rot5 = new Card(5, "rot5",iconRot5,1,"rot");
		rot6 = new Card(6, "rot6",iconRot6,0,"rot");
		rot7 = new Card(7, "rot7",iconRot7,1,"rot");
		rot8 = new Card(8, "rot8",iconRot8,0,"rot");
		rot9 = new Card(9, "rot9",iconRot9,1,"rot");
		rot10 = new Card(10, "rot10",iconRot10,0,"rot");
		
		//Variablen fuer gruene Karten
		gruen2 = new Card(2, "gruen2",iconGruen2,0,"gruen");
		gruen3 = new Card(3, "gruen3",iconGruen3,1,"gruen");
		gruen4 = new Card(4, "gruen4",iconGruen4,0,"gruen");
		gruen5 = new Card(5, "gruen5",iconGruen5,1,"gruen");
		gruen6 = new Card(6, "gruen6",iconGruen6,0,"gruen");
		gruen7 = new Card(7, "gruen7",iconGruen7,1,"gruen");
		gruen8 = new Card(8, "gruen8",iconGruen8,0,"gruen");
		gruen9 = new Card(9, "gruen9",iconGruen9,1,"gruen");
		gruen10 = new Card(10, "gruen10",iconGruen10,0,"gruen");
		
		//JokerKarten
		
		bube1 = new Card(11, "bube", iconBube,2,"",true);	
		dame1 = new Card(12, "dame", iconDame,3,"",true);	
		koenig1 = new Card(13, "koenig", iconKoenig,5,"",true);
		
		bube2 = new Card(11, "bube", iconBube,2,"",true);	
		dame2 = new Card(12, "dame", iconDame,3,"",true);	
		koenig2 = new Card(13, "koenig", iconKoenig,5,"",true);
		
		bube3 = new Card(11, "bube", iconBube,2,"",true);	
		dame3 = new Card(12, "dame", iconDame,3,"",true);	
		koenig3 = new Card(13, "koenig", iconKoenig,5,"",true);


		//F�gt alle Karten der ArrayList hinzu
		deck.add(grau2);
		deck.add(grau3);
		deck.add(grau4);
		deck.add(grau5);
		deck.add(grau6);
		deck.add(grau7);
		deck.add(grau8);
		deck.add(grau9);
		deck.add(grau10);
		
		deck.add(gelb2);
		deck.add(gelb3);
		deck.add(gelb4);
		deck.add(gelb5);
		deck.add(gelb6);
		deck.add(gelb7);
		deck.add(gelb8);
		deck.add(gelb9);
		deck.add(gelb10);
		
		deck.add(gruen2);
		deck.add(gruen3);
		deck.add(gruen4);
		deck.add(gruen5);
		deck.add(gruen6);
		deck.add(gruen7);
		deck.add(gruen8);
		deck.add(gruen9);
		deck.add(gruen10);
		
		deck.add(orange2);
		deck.add(orange3);
		deck.add(orange4);
		deck.add(orange5);
		deck.add(orange6);
		deck.add(orange7);
		deck.add(orange8);
		deck.add(orange9);
		deck.add(orange10);
		
		deck.add(rot2);
		deck.add(rot3);
		deck.add(rot4);
		deck.add(rot5);
		deck.add(rot6);
		deck.add(rot7);
		deck.add(rot8);
		deck.add(rot9);
		deck.add(rot10);
		
		deck.add(bube1); //46
		deck.add(bube2);
		deck.add(bube3);
		
		deck.add(dame1); //49
		deck.add(dame2);
		deck.add(dame3);
		
		deck.add(koenig1); //52
		deck.add(koenig2);
		deck.add(koenig3);
		
	}
	
	//Methode welche die Karten nach den Regeln auf zwei Spieler aufteilt
	public void aufteilen(int anzahl){
		
		
		
		
		
		//Zwei Spieler aufteilung
		if(anzahl==2){
			
			int j = 0;
			while(j < deck.size()){
				
				if(deck.get(j).getFarbe().equals("grau")){
					if(deck.get(j).getFarbe() != null){
					deck.remove(j);
					}
				}
				else{
					j++;
				}
			}
			
			//Schleife welche einen Buben entfernt
			for(int i =0;i<deck.size();i++){
				if(deck.get(i).getName().equals("bube")){
					deck.remove(i);
					break;
				}
			}
			//schleife welche eine Dame entfernt
			for(int i =0;i<deck.size();i++){
				if(deck.get(i).getName().equals("dame")){
					deck.remove(i);
					break;
				}
			}
			//Schleife welche einen Koenig entfernt
			for(int i =0;i<deck.size();i++){
				if(deck.get(i).getName().equals("koenig")){
					deck.remove(i);
					break;
				}
			}
			


			//While Schleife welche beiden Spielern einen Buben gibt
			int k = 0;
			while(true){
				if(spieler1){
					if(deck.get(k).getName().equals("bube")){
						handKarten1.add(deck.get(k));
						deck.remove(k);
						spieler1 = false;
						k--;
					}
				}
				else if(!spieler1){
					if(deck.get(k).getName().equals("bube")){
						handKarten2.add(deck.get(k));
						deck.remove(k);
						spieler1 = true;
						break;
					}
				}
				k++;
			}

			//While Schleife welche beiden Spielern eine Dame gibt
			k = 0;
			while(true){
				if(spieler1){
					if(deck.get(k).getName().equals("dame")){
						handKarten1.add(deck.get(k));
						deck.remove(k);
						spieler1 = false;
						k--;
					}
				}
				else if(!spieler1){
					if(deck.get(k).getName().equals("dame")){
						handKarten2.add(deck.get(k));
						deck.remove(k);
						spieler1 = true;
						break;
					}
				}
				k++;
			}
			
			//While Schleife welche beiden Spielern einen Koenig gibt
			k = 0;
			while(true){
				if(spieler1){
					if(deck.get(k).getName().equals("koenig")){
						handKarten1.add(deck.get(k));
						deck.remove(k);
						spieler1 = false;
						k--;
					}
				}
				else if(!spieler1){
					if(deck.get(k).getName().equals("koenig")){
						handKarten2.add(deck.get(k));
						deck.remove(k);
						spieler1 = true;
						break;
					}
				}
				k++;
			}
			
			
			//Schleife in der 14 mal eine Zufallszahl gemacht wird zwischen

			
			for(int i = 0; i<28;i++){
				//Variable welche dafuer sorgt das die Karten abwechselnd verteilt werden
				
				
				int decZufallZahl = (int) (Math.random()*deck.size()+1);
				int zufallsZahl = Math.round(decZufallZahl) - 1;
				System.out.println(zufallsZahl);
				if(spieler1){
					handKarten1.add(deck.get(zufallsZahl));
					deck.remove(zufallsZahl);
					spieler1 = false;
				}
				else if(!spieler1){
					handKarten2.add(deck.get(zufallsZahl));
					deck.remove(zufallsZahl);
					spieler1=true;
				}
				
			}
			System.out.println("-----------------------------HAGGIS-------------------------------");
			for(Card hKarte : deck){
				haeggis.add(hKarte);
				System.out.println(hKarte.getPunkte());
			}
			deck.removeAll(deck);
			
			haende.add(handKarten1);
			haende.add(handKarten2);
			
			
			System.out.println("------------------------------JokerKartenWert handkarten 1------------------------------");
			for (int i = 0; i < 3; i++){
				System.out.println(handKarten1.get(i).getWert());
			}
			
			System.out.println("------------------------------JokerKartenWert handkarten 2------------------------------");
			for (int i = 0; i < 3; i++){
				System.out.println(handKarten2.get(i).getWert());
			}

		}
		
		
		//Drei Spieler aufteilung
		else if(anzahl==3){
			
		}
	}
	
	//Getters und Setters
	public ArrayList<Card> getHandKarten1(){
		return this.handKarten1;
	}
	
	public void setHandKarten1(ArrayList<Card> handkarten){
		this.handKarten1 = handkarten;
	}
	
	public void setHandKarten2(ArrayList<Card> handkarten){
		this.handKarten2 = handkarten;
	}
	
	public ArrayList<Card> getHandKarten2(){
		return this.handKarten2;
	}
	
	public ArrayList<Card> getHand(int i){
		return this.haende.get(i);
	}
	
	public ArrayList<Card> getHaeggis(){
		return this.haeggis;
	}
	
}

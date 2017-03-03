package de.nemewesa.level;

import java.io.Serializable;

import de.nemewesa.app.App;
import de.nemewesa.app.Observer;
import de.nemewesa.app.Round;
import de.nemewesa.buildings.SpaceStation;
import de.nemewesa.character.Enemy;
import de.nemewesa.helper.Helper;
import de.nemewesa.modules.Defence;
import de.nemewesa.modules.*;

public class Planet implements Generetable, Observer, Serializable{
	
	public static Object resourceName;
	public Moon moon;
	public int citizen;
	public SpaceStation stationName;
	public Defence defenceName;
	public boolean captured = false;
	public String name;
	public Round round;
	public int storage;
	// size des planeten zwischen
	public int size;
	public Solarsystem parent;
	public SpaceStation spacestation = null;
	public String[] firstname = {"Ben", "Pe", "To", "Jau", "Ja", "Ter", "Masz", "Res", "Min", "Ro", "Sen", "Ta", "Bue", "Ur", "Ban", "Iak", "Dzi", "Ko", "Wi", "Cki"};
	public Path pathLeft;
	public Path pathRight;
	
/* 
 * wenn das raumschiff zb. 10 plaetze frei hat
 * laesst sich ueber den space festlegen wieviel man tragen kann
 * Name, value, amount, farm, space, grow(in steps), dropChance, storage, storagef
 * amount legt fest wieviel es MAXIMAL geben kann
 * farm ist zum arbeiten gedacht
 * 
 * storage legt fest wieviel maximal geladen werden kann
 * storagef dann zum arbeiten
 */
	//int value, int amount, int farm, int space, int grow, int dropchance, int storage, int storagef
	public Resource bronze = new Resource("Bronze", 1, 0, 0, 2, 8, 85, 0, 0);
	public Resource silver = new Resource("Silber", 2, 0, 0,  2, 10, 60, 0, 0);
	public Resource gold = new Resource("Gold", 4, 0, 0, 2, 12, 45, 0, 0);
	public Resource jewel= new Resource("Juwel", 8, 0, 0, 1, 14, 15, 0, 0);
	
	
	public Planet(String name, Solarsystem parent) {
		this.name = generateName();
		this.parent = parent;
		this.size = Helper.random(100, 1000);
		this.citizen = Helper.random(20, 80);
		this.round  = Round.getRoundInstance();
		this.round.registerObserver(this);
/*
 * Hier wird dem Planet mit einem W100 zufaellig zugewiesen ob dieser Planet ueberhaupt Rohstoffe hat
 * 		Wuerfel (1-100) unter 85: dieser Planet hat Bronze Vorkommen
 * 		Wuerfel (1-100) unter 60: dieser Planet hat Silber Vorkommen
 * 		Wuerfel (1-100) unter 45: dieser Planet hat Gold Vorkommen
 * 		Wuerfel (1-100) unter 15: dieser Planet hat Juwelen Vorkommen
 * 	
 * 		Sollte es eine Ressource auf dem Planeten geben, so wird wieder zufaellig entschieden wieviel Rohstoff 
 * 		vorhanden ist.
 * 
 * 		Bronze: mindestens 15 --- maximal 70
 * 		Silber: mindestens 10 --- maximal 60
 * 		Bronze: mindestens 5 --- maximal 50
 * 		Bronze: mindestens 1 --- maximal 25
 */
		if(Helper.random(0, 100)<bronze.dropRate) {
			this.bronze.amount = Helper.random(15, 70);
			this.bronze.farm = this.bronze.amount;
			this.bronze.storage = this.citizen*4;
		}
		if(Helper.random(0, 100)<silver.dropRate) {
			this.silver.amount = Helper.random(10, 60);
			this.silver.farm = this.silver.amount;
			this.silver.storage = this.citizen*4;
		}
		if(Helper.random(0, 100)<gold.dropRate) { 
			this.gold.amount = Helper.random(5, 50);
			this.gold.farm = this.gold.amount;
			this.gold.storage = this.citizen*4;
		}
		if(Helper.random(0, 100)<jewel.dropRate) {
			this.jewel.amount = Helper.random(1, 25);
			this.jewel.farm = this.jewel.amount;
			this.jewel.storage = this.citizen*4;
		}
	
		this.moon = new Moon(this.name + "s Mond", new Enemy(this.moon), this);
		
	}
	
/*
 * Hier wird nach jeder runde der Rohstoff neu generiert 
 * Die kriterien sind hier:
 * 			Bronze: alle 8 Runden
 * 			Silber:	alle 10 Runden
 * 			Gold:	alle 12 Runden
 * 			Juwel:	alle 14 Runden
 */
	public void generateResource() {
		if(this.bronze.farm<this.bronze.amount && round.getRound() % bronze.grow == 0) {
			bronze.farm +=1;					
		}
		if(this.silver.farm<this.silver.amount && round.getRound() % silver.grow == 0) {
			silver.farm +=1;					
		}
		if(this.gold.farm<this.gold.amount && round.getRound() % gold.grow == 0) {
			gold.farm +=1;					
		}
		if(this.bronze.farm<this.bronze.amount && round.getRound() % bronze.grow == 0) {
			bronze.farm +=1;					
		}
		if(this.jewel.farm<this.jewel.amount && round.getRound() % jewel.grow == 0) {
			jewel.farm +=1;					
		}
	}		
/*
 * Mit den einzelnen Methoden wird ein Rohstoff abgearbeitet und dann in dem storage "storagef" eingefuegt"
 * Es wird kontrolliert ob der jeweilige Rohstoff ueberhaupt vorhanden ist durch die "farm" variable
 * Ausserdem wird sichergestellt das man nicht mehr farmen kann als vorhanden
 * 
 * *****01.03******
 * der farm wird abgebaut * der aktuellen forschungstand
 */
	public void mineBronze() {
		if(this.bronze.farm > 0) {
			this.bronze.farm -= 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
			this.bronze.storagef += 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;	
		}
	}
	
	public void mineSilver() {
		if(this.silver.farm > 0) {
			this.silver.farm -= 1* App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
			this.silver.storagef += 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
		}
	}
	
	public void mineGold() {
		if(this.gold.farm > 0) {
			this.gold.farm -= 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
			this.gold.storagef += 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
		}
	}
	
	public void mineJewel() {
		if(this.jewel.farm > 0) {
			this.jewel.farm -= 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
			this.jewel.storagef += 1 * App.getAppInstance().getPlayer().getHomePlanet().spacestation.modulePoint;
		}
	}
	

/*
 * Mit income() wird (sobald der Planet eingenommen wurde) angewiesen mit jeden aufruf selbst zu farmen
 * Es wird kontrolliert ob die der farm vorhanden ist und nicht unter Null ist
 * Anschliessend wird ueberprueft ob das "leeere Lager" nicht dem Maximalen Lager (das moegliche Lager = einwohner x 4) ueberschreitet
 * dann wird ein viertel  der Einwohner jeweils ein Rohstoff (wenn vorhanden) abarbeiten
 * doch vorsicht ist geboten!!!!!
 * je wertvoller der rohstoff desto weniger kommt ins wirkliche lager an
 */
	public void income() {
//		if(captured = true) {
			if(this.bronze.farm>0) {
				if(this.bronze.storage > this.bronze.storagef) {
					this.bronze.farm -= (int)citizen/4;
					this.bronze.storagef += (int)citizen/4;
					if(this.bronze.farm<0) {
						this.bronze.farm = 0;
					}
				}
			}
			if(this.silver.farm>0) {
				if(this.silver.storage > this.silver.storagef) {
					this.silver.farm -= (int)citizen/5;
					this.silver.storagef += (int)citizen/4;
					if(this.silver.farm<0) {
						this.silver.farm = 0;
					}
				}
			}
			if(this.gold.farm>0) {
				if(this.gold.storage > this.gold.storagef) {
					this.gold.farm -= (int)citizen/6;
					this.gold.storagef += (int)citizen/4;
					if(this.gold.farm<0) {
						this.gold.farm = 0;
					}
				}
			}
			if(this.jewel.farm>0) {
				if(this.jewel.storage > this.jewel.storagef) {
					this.jewel.farm -= (int)citizen/7;
					this.jewel.storagef += (int)citizen/4;
					if(this.jewel.farm<0) {
						this.jewel.farm = 0;
					}
				}
			}
//			
//		}
	}
	
	public boolean pickupResource(Resource res, int amount){
		
		if(res.storagef >= amount){
			res.storagef -= amount; 
			return true;
		}
		
		return false;
	}
	
	public Planet getLeftNeighbouringPlanet(){
		
		if(this.parent.getPlanetIndex(this) > 0){
			return this.parent.getPlanet(
					this.parent.getPlanetIndex(this) -1);
		}
		return null;
	}
	
	public Planet getRightNeighbouringPlanet(){
		
		if(this.parent.getPlanetIndex(this) < (this.parent.getPlanets().size() - 1)){
			return this.parent.getPlanet(
					this.parent.getPlanetIndex(this) +1);
		}
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}
	public int getSize() {
		return this.size;
	}

	@Override
	public void generate(int element) {
		
		// PLANETEN MIT PFADEN VERKNUEPFEN
		
		// ERSTER PLANET VON LINKS ( LINKER PLANET )
		if(this.getLeftNeighbouringPlanet() == null){
			this.pathLeft = null;
			this.pathRight = new Path();
		}
		// MITTLERE PLANETEN
		else if(this.getLeftNeighbouringPlanet() != null && (this.parent.getPlanetIndex(this) < (this.parent.getPlanets().size() - 1))){
			this.pathLeft = this.getLeftNeighbouringPlanet().pathRight;
			this.pathRight = new Path();
		}
		// LETZER PLANET VON LINKS ( RECHTER PLANET )
		else{
			this.pathLeft = this.getLeftNeighbouringPlanet().pathRight;
			this.pathRight = null;
		}
		
	}

	@Override
	public void printChildren() {
		System.out.println("Linker Pfad: " + this.pathLeft);
		System.out.println("Rechter Pfad: " + this.pathRight);		
	}
/*
 * ZUFaeLLIGE PLANETENNAMEN ERSTELLUNG
 * Es wird eine erste Silbe zufaellig ausgesucht (zwischen 0 und der maximal laenge des array)
 * dann eine zweite silbe. Anschliessend wird ueberprueft ob diese beiden nicht gleich sind ("Benben", "Toto"...)
 * Sollte es der Fall sein, wird der erste Name wieder Random gesucht.
 * Wenn alles nach Wunsch laeuft, wird die zweite Silbe der ersten angehangen und klein geschrieben um ein neues
 * Wort (in diesem Fall Planetenname) zu erhalten.
 * Es gibt 20 Silben und 2 moegliche plaetze.. also 20 hoch 2 = 400
 */
	public String generateName() {
		String name1 = firstname[Helper.random(0, firstname.length)];
		String name2 = firstname[Helper.random(0, firstname.length)]; 
			while(name1.equals(name2)) {
			name1 = firstname[Helper.random(0, firstname.length)];
		}
		String name4 = name1+name2.toLowerCase();
		return name4;
	}	
	public static void defending()
	{
		//defencePower
	}

	@Override
	public void update(int round) {
		
		if(App.DEV_MODE)
			System.out.println(this.name + " lautet die Runde " + round + " ein.");
		
		generateResource();
		
	}

}

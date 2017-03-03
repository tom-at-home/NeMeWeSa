package de.nemewesa.buildings;

import java.io.Serializable;
import java.util.ArrayList;

import de.nemewesa.level.Planet;
import de.nemewesa.level.Resource;

public class SpaceStation implements Serializable{
	
	public String name;

	public ArrayList<String> warehouse;
	public int researchPoint;
	public int modulePoint;
	public int bank;
	public Planet parent;


	
	public SpaceStation(String name, Planet parent) {
		this.name = name;
		this.researchPoint = 1;
		this.bank = 0;
		this.modulePoint = 1;
		this.parent = parent;
		
	}
/*
 * wenn die Bank mehr als 25 einheiten hat kann man diese spenden und bekommt dafuer einen Erforschen punkt
 */
	public void researching() {
		if(this.bank >= 25) {
			this.bank -= 25;
			this.researchPoint +=1;	
		}			
	}
/*
 * wenn die bank mehr als 50 einheiten hat, werden diese abgezogen	und man bekommt einen modul punkt
 */
	public void produce() {
		if(this.bank >= 50) {
			this.bank -= 50;
			this.modulePoint += 1;
		}		
	}	
/*
 * hier werden die gesammelten Ressourcen gegen Einheiten zum einkaufen getauscht
 * Der Kurs setzt sich wie folgt zusammen:
 * 	Für 1 Bronze gibt es 	1 Einheit/en
 * 	Für 1 Silber gibt es  	2 Einheit/en
 *  Für 1 Gold gibt es 		4 Einheit/en
 *  Fuer 1 Juwel gibt es 	8 Einheit/en
 */
	public void exchangeBronze() {
		if(this.parent.bronze.storagef > 0) {
			this.bank += this.parent.bronze.storagef * this.parent.bronze.value;
			this.parent.bronze.storagef -= this.parent.bronze.storagef;
		}	
	}
	public void exchangeSilver() {
		if(this.parent.silver.storagef > 0) {
			this.bank += this.parent.silver.storagef * this.parent.silver.value;
			this.parent.silver.storagef -= this.parent.silver.storagef;
		}	
	}
	public void exchangeGold() {
		if(this.parent.gold.storagef > 0) {
			this.bank += this.parent.gold.storagef * this.parent.gold.value;
			this.parent.gold.storagef -= this.parent.gold.storagef;
		}	
	}
	public void exchangeJewel() {
		if(this.parent.jewel.storagef > 0) {
			this.bank += this.parent.jewel.storagef * this.parent.jewel.value;
			this.parent.jewel.storagef -= this.parent.jewel.storagef;
		}	
	}

	public Resource bronze = new Resource("Bronze",0);
	public Resource silver = new Resource("Silber",0);
	public Resource gold = new Resource("Gold", 0);
	public Resource jewel= new Resource("Juwel", 0);
	
}

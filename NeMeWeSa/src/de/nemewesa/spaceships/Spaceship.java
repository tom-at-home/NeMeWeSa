package de.nemewesa.spaceships;

import java.util.ArrayList;

import javax.annotation.Resource;

import de.nemewesa.character.Enemy;
import de.nemewesa.level.Moon;
import de.nemewesa.level.Planet;

public class Spaceship {
	
	public String shipName;
	public String shipType;
	public int shipHealth;
	public int shipStrange;
	public int shipFuel;
	public int currentCapacity;
	
	public Planet currentPlanet = null;
	
	public static ArrayList<Resource> stock; 
	public int maxCapacity;
	
	
	public Spaceship(String shipName, String shipType, int maxCapacity, int shipHealth, int shipStrange, int shipFuel, Planet currentPlanet, int currentCapacity) {
		this.shipName = shipName;
		this.shipType = shipType;
		this.shipHealth = shipHealth;
		this.shipStrange = shipStrange;
		this.shipFuel = shipFuel;
		this.maxCapacity = maxCapacity;
		this.currentPlanet = currentPlanet;
		this.currentCapacity = currentCapacity;
	}
	
	
		public Planet getLeftNeighbouringPlanet(){
			
			if(this.currentPlanet.parent.getPlanetIndex(this.currentPlanet) > 0){
				return this.currentPlanet.parent.getPlanet(
						this.currentPlanet.parent.getPlanetIndex(this.currentPlanet) -1);
			}
			return null;
		}
		
		public Planet getRightNeighbouringPlanet(){
			
			if(this.currentPlanet.parent.getPlanetIndex(this.currentPlanet) < (this.currentPlanet.parent.getPlanets().size() - 1)){
				return this.currentPlanet.parent.getPlanet(
						this.currentPlanet.parent.getPlanetIndex(this.currentPlanet) +1);
			}
			return null;
		}	

		public void move(Planet planet){
		
		// GEHE ZUM LINKEN NACHBARPLANETEN
		if(planet == this.currentPlanet.getLeftNeighbouringPlanet()){
		
		//	if(this.ap >= this.currentPlanet.pathLeft.distance){
			//	this.currentPlanet.pathLeft.enterPathShip(this);
			//	this.ap -= this.currentPlanet.pathLeft.distance;
				this.currentPlanet = planet;
			
		
		}
		// GEHE ZUM RECHTEN NACHBARPLANETEN
		else if(planet == this.currentPlanet.getRightNeighbouringPlanet()){
		
			//if(this.ap >= this.currentPlanet.pathRight.distance){
			//	this.currentPlanet.pathRight.enterPathShip(this);
			//	this.ap -= this.currentPlanet.pathRight.distance;
				this.currentPlanet = planet;
			
			
		}
		// PLANET AUSSER REICHWEITE
		else{
			System.out.println("Dieser Planet ist nicht erreichbar");
		}	
	}
	
	public int shipAttack(Enemy target)
	{
		return target.enemyHealth = target.enemyHealth - this.shipStrange;
	}
	
	// Zugehörigkeit zum Mond. 
	// Hier wird der Mond erobert
	public boolean capturing(Moon affiliation)
	{
		System.out.println("Glückwunsch! "+ affiliation.moonName + " wurde erobert!!!");
		return affiliation.captured = true;
				
	}
	
	
	

}

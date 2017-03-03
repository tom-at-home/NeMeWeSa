package de.nemewesa.spaceships;

import de.nemewesa.buildings.Storage;
import de.nemewesa.character.Enemy;
//github.com/NeMeWeSa/NeMeWeSa.git
import de.nemewesa.level.Moon;
import de.nemewesa.level.Planet;

public class Fighter extends Spaceship {
	
	public Fighter(String shipName, String shipType, int maxCapacity, int shipHealth, int shipStrange, int shipFuel, Storage storage, Planet currentPlanet, int currantCapacity)
	{
		super(shipName, shipType, shipHealth, maxCapacity, shipStrange, shipFuel, currentPlanet, currantCapacity);
	}
	
//	public Fighter(String shipName, String shipType, int shipHealth, int shipStrange, int shipFuel)
//	{
//		super( shipName,  shipType, int shipHealth, int shipStrange, int shipFuel);
//	}

	public int shipAttack(Enemy redAlien)
	{
		return redAlien.enemyHealth = redAlien.enemyHealth - this.shipStrange;
	}
	
	// Zugehörigkeit zum Mond. 
    // Hier wird der Mond erobert
	public boolean capturing(Moon affiliation)
	{
		System.out.println("Glückwunsch! "+ affiliation.moonName + " wurde erobert!!!");
		return affiliation.captured = true;					
	}
	
	public void die()
	{
		System.out.println(this.shipName + " ist zerstört!!!");
	}
}

package de.nemewesa.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;


public class Solarsystem implements Generetable, Serializable{
	
	public String name;
	public Sector parent;
	private ArrayList<Planet> planets = new ArrayList<>();
	
	public Solarsystem(String name, Sector parent){
		this.name = name;
		this.parent = parent;
	}
	
	public Planet getPlanet (int index) {
		return planets.get(index);
	}
	
	public int getPlanetIndex (Planet planet) {
		return planets.indexOf(planet);
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void generate(int element){

		String planetname = null;

		// Pfad unter Mac
		//String filename = "level/" + Level.level + "/planets/" + element + "/planets.txt";

		// Pfad unter Windows
		//String filename = "level\\" + Level.level + "\\planets\\" + element + "\\planets.txt";
		
		// Universeller Pfad
		File filename = new File("level" + File.separator + Level.level 
				+ File.separator + "planets" + File.separator + element + File.separator + "planets.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((planetname = br.readLine()) != null) {
				this.planets.add(new Planet(planetname, this));
			}
			
			for (int i = 0; i < planets.size(); i++) {
				planets.get(i).generate(i);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void printChildren() {
		
		for (Planet planet : planets) {
			System.out.println(planet.name + " : " + planet.parent.name);
			planet.printChildren();
		}
		
	}
 
}

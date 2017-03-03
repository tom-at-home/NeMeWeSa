package de.nemewesa.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Sector implements Generetable, Serializable{
	
	public String name;
	public Level parent;
	
	private ArrayList<Solarsystem> solarsystems = new ArrayList<>();
	
	public Sector(String name, Level parent){
		this.name = name;
		this.parent = parent;
	}
	

	public Solarsystem getSolarsystem (int index) {
		return solarsystems.get(index);
	}


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void generate(int element){

		String solarsystemname = null;

		// Pfad unter Mac
		//String filename = "level/" + Level.level + "/solarsystems/" + element + "/solarsystems.txt";

		// Pfad unter Windows
		//String filename = "level\\" + Level.level + "\\solarsystems\\" + element + "\\solarsystems.txt";

		// Universeller Pfad
		File filename = new File("level" + File.separator + Level.level 
				+ File.separator + "solarsystems" + File.separator + element + File.separator + "solarsystems.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((solarsystemname = br.readLine()) != null) {
				this.solarsystems.add(new Solarsystem(solarsystemname, this));
			}
			
			for (int i = 0; i < solarsystems.size(); i++) {
				solarsystems.get(i).generate(i);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	@Override
	public void printChildren(){
		
		for (Solarsystem solarsystem : solarsystems) {
			System.out.println(solarsystem.name + " : " + solarsystem.parent.name);
			solarsystem.printChildren();
		}
		
	}

}

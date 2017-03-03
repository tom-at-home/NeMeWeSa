package de.nemewesa.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable{

	public static int level;

	private ArrayList<Sector> sectors = new ArrayList<>();
	//private ArrayList<>


	public Level(int level){
		Level.level = level;
	}
	
	public Sector getSector(int index){
		return sectors.get(index);
	}


	public void generate(){

		String sectorname = null;

		// Pfad unter Mac
		//String filename = "level/" + Level.level + "/sectors/sectors.txt";

		// Pfad unter Windows
		//String filename = "level\\" + Level.level + "\\sectors\\sectors.txt";

		// Universeller Pfad
		File filename = new File("level" + File.separator + Level.level 
				+ File.separator + "sectors" + File.separator + "sectors.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((sectorname = br.readLine()) != null) {
				this.sectors.add(new Sector(sectorname, this));
			}
			
			for (int i = 0; i < sectors.size(); i++) {
				sectors.get(i).generate(i);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void printChildren(){
		
		for (Sector sector : sectors) {
			System.out.println(sector.name + " : " + sector.parent.getClass().getSimpleName());
			sector.printChildren();
		}
		
	}

}

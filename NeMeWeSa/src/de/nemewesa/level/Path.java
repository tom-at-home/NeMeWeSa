package de.nemewesa.level;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import de.nemewesa.character.Player;
import de.nemewesa.helper.Helper;

public class Path implements Serializable{

	//Asteroiden,die sich aufm Weg befinden
	public ArrayList<Asteroid>asteroids = new ArrayList<>();
	public Asteroid asteroid;
	public Player player;

	public Planet planet;
	public Planet planetW;
	public Planet planetE;

	//Laenge,Breite und Name des Weges
	public int distance;
	public int width;
	public int currPos;
	public String name;

	transient Scanner scan = new Scanner(System.in);

	public char[][] path;  //Der Weg als Array
	public int nblin, nbcol;
	public char tmp; //Spielerposition
	public String[] name0 ={"Las","Wus","Gas","Gis","Nos","Los","Min", "Ro", "Sen", "Ta", "Bue", "Ur", "Ban", "Iak","Sol","Fa","Man","Yuh"};

	//Konstruktor
	public Path(){
		this.name = generateName();

		this.width = Helper.random(3, 7);
		this.distance = Helper.random(1,9);

		nblin = width;  //Anzahl der Reihen
		nbcol = distance;  //Anzahl der Spalten

		path = new char[nblin][nbcol];
		for (int i=0; i<nblin; i++){
			for(int j=0; j<nbcol; j++){
				path[i][j] = ' ';
			}	
		}
	}
	
	public void init(){
		this.currPos = 1;
	}

	public String generateName(){
		String str1 = name0[Helper.random(0, name0.length)];
		String str2 = name0[Helper.random(0, name0.length)];
		String str3 = str1 + str2.toLowerCase();
		return str3;
	}


	public String getName(){
		return this.name;
	}

	public int getDistance(){
		return this.distance;
	}

	public int getWidth(){
		return this.width;
	}

	public Planet getPlanetW(){
		return this.planetW;
	}

	public Planet getPlanetE(){
		return this.planetE;
	}

	public void setPlanetW(Planet planetW){
		this.planetW = planetW;
	}

	public void setPlanetE(Planet planetE){
		this.planetE = planetW;
	}

	public void setDistance(){
		this.distance = Helper.random(7,15);
	}


	public void setWidth(){
		this.width = Helper.random(3,7);
	}

	public String toString(){
		return "Pfadname: "+this.name+"\nBreite = "+this.width+"\nLaenge = "+this.distance;
	}

	//Asteroiden aufm Weg hinzufuegen
	public void addAsteroid(){
		System.out.println("Willkommen auf dem Pfad zu ");
		System.out.println(toString()+"\n");

		for (int i=0; i<this.width; i++){
			for (int j=0; j<this.distance; j++){
				path[0][0] = 'P'; // setzt den Spieler(P) immer auf index [0][0].
				int random = Helper.random(0, 5);
				switch(random){
				case 0: path[i][j] = 'A'; break;
				case 1: path[i][j] = ' '; break;
				case 2: path[i][j] = ' '; break;
				case 3: path[i][j] = ' '; break;
				}
			}
		}
		System.out.println();
	}
	public void showPath(){
		System.out.print("-------------------------------------------------------\n");
		for (int i=0; i<nblin; i++){
			for(int j=0; j<nbcol; j++){

				System.out.print(" | "+path[i][j]);
			}
			System.out.println(" | ");
		}

		System.out.print("--------------------------------------------------------\n");
	}

	public void howToMove(){
		System.out.println();
		System.out.println("Vorsichtig Du bist der 'P' versuch so gut wie moeglich die Asteroiden zu vermeiden!!!");
		System.out.println("Zum bewegen:");
		System.out.println("8 > nach oben");
		System.out.println("2 > nach unten");
		System.out.println("4 > nach links");
		System.out.println("6 > nach rechts");
		System.out.println("Benutz am besten die numerische Tastatur.");

		while(this.currPos != this.distance){

			try{

				int m = scan.nextInt();

				if(m == 8 || m == 2 || m == 4 || m == 6 ){

					switch (m){
						case 8 :
							if(moveUp() == 2){
								System.out.println("Ohh Du bist mit einem Asteroiden zusammengestossen!!!");
								this.player.setAp(this.player.getAp() - 2);
								System.out.println("Deine Aktionspunkte sinken auf " + this.player.getAp());
							} break;
						case 2 :
							if (moveDown() == 2){
								System.out.println("Ohh Du bist mit einem Asteroiden zusammengestossen!!!");
								this.player.setAp(this.player.getAp() - 2);
								System.out.println("Deine Aktionspunkte sinken auf " + this.player.getAp());
							} break;
						case 4 :
							if (moveLeft() == 2){
								System.out.println("Ohh Du bist mit einem Asteroiden zusammengestossen!!!");
								this.player.setAp(this.player.getAp() - 2);
								System.out.println("Deine Aktionspunkte sinken auf " + this.player.getAp());
							} break;
						case 6 :
							if (moveRigth() == 2){
								System.out.println("Ohh Du bist mit einem Asteroiden zusammengestossen!!!");
								this.player.setAp(this.player.getAp() - 2);
								System.out.println("Deine Aktionspunkte sinken auf " + this.player.getAp());
							} break;
	
						default :  System.out.println("Falsche Taste!!!");

					}

				}
				else{
					System.out.println("Ungueltige Zahl!");
				}

			}
			catch(java.util.InputMismatchException exception){
				System.out.println("BITTE NUR ZAHLEN EINGEBEN");
				scan.next();
			}
		}

	}

	//sich auf dem Weg bewegen

	//Bewegen nach oben
	public int moveUp(){
		for (int i=0;i<nblin;i++){
			for (int j=0;j<nbcol;j++){
				if (path[i][j] =='P'){
					if (i-1>=0 && path[i-1][j] == ' '){
						tmp = path[i][j]; //Spieler speichern.
						path[i][j] = ' '; //alte Position verlassen.
						path[i-1][j] = tmp; //neu Position.
						showPath();
						return 1;  // ohne Kollision
					}
					if(path[i-1][j] == 'A'){
						tmp = path[i][j]; 
						path[i][j] = ' '; 
						path[i-1][j] = tmp;
						showPath();
						return 2;   // mit Kollision
					}
				}
			}
		}
		return 0;
	}


	//Bewegen nach recht
	public int moveRigth(){
		this.currPos++;
		for (int i=0;i<nblin;i++){
			for (int j=0;j<nbcol;j++){
				if (path[i][j] =='P'){
					if (j+1 <=nbcol && path[i][j+1] == ' '){
						tmp = path[i][j]; //Spieler speichern.
						path[i][j] = ' '; //alte Position verlassen.
						path[i][j+1] = tmp; //neu Position.
						showPath();
						return 1;
					}
					if(path[i][j+1] == 'A'){
						tmp = path[i][j]; 
						path[i][j] = ' '; 
						path[i][j+1] = tmp;
						showPath();
						return 2;
					}
				}
			}
		}
		return 0;

	}
	
	//Bewegen nach links
	public int moveLeft(){
		this.currPos--;
		for (int i=0;i<nblin;i++){
			for (int j=0;j<nbcol;j++){
				if (path[i][j] =='P'){

					if (j-1>=0 && path[i][j-1] == ' '){
						tmp = path[i][j]; //Spieler speichern.
						path[i][j] = ' '; //alte Position verlassen.
						path[i][j-1] = tmp; //neu Position.
						showPath();
						return 1;
					}
					if(path[i][j-1] == 'A'){
						tmp = path[i][j]; 
						path[i][j] = ' '; 
						path[i][j-1] = tmp; 

						showPath();
						return 2;
					}
				}	
			}

		}
		return 0;
	}

	//Bewegen nach unten
	public int moveDown(){
		for (int i=0;i<nblin;i++){
			for (int j=0;j<nbcol;j++){
				if (path[i][j] =='P'){

					if (path[i+1][j] == ' '){
						tmp = path[i][j]; //Spieler speichern.
						path[i][j] = ' '; //alte Position verlassen.
						path[i+1][j] = tmp; //neu Position.
						showPath();
						return 1;
					}
					if(i+1<=nblin && path[i+1][j] == 'A'){
						tmp = path[i][j]; 
						path[i][j] = ' '; 
						path[i+1][j] = tmp; 
						showPath();
						return 2;
					}
				}
			}
		}
		return 0;

	}

	public void enterPath(Player player){
		this.player = player;
		init();
		addAsteroid();
		showPath();
		howToMove();
	}

}
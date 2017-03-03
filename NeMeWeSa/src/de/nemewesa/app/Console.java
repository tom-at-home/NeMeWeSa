package de.nemewesa.app;

import java.io.File;
import java.util.Scanner;

import de.nemewesa.character.Player;
import de.nemewesa.level.Level;
import de.nemewesa.menu.Menu;
import de.nemewesa.menu.Menuitem;

public class Console {

	Scanner scanner = new Scanner(System.in);
	Menu menu;
	Player player;
	File playerFile = new File("level" + File.separator + Level.level 
			+ File.separator + "saves" + File.separator + "player.dat");
	
	public Console(Player player){
		this.player = player;
	}
	
	public void mainmenu(){
		
		menu = new Menu();
		
		menu.menuitems.add(
				
				new Menuitem("Aktionen"){
					public void execute(){
						actions();
					}});
		
//		menu.menuitems.add(
//				new Menuitem("Hilfe"){
//					public void execute(){
//						help();
//					}});
		
		menu.menuitems.add(
				new Menuitem("Spieler laden"){
					public void execute(){
						player.load(playerFile);
						player = App.getAppInstance().getPlayer();
						System.err.println("LADEN - AP: " + App.getAppInstance().getPlayer().getAp());
						mainmenu();
					}});
		
		menu.menuitems.add(
				new Menuitem("Spieler speichern"){
					public void execute(){
						player.save(playerFile);
						mainmenu();
					}});
		
		menu.menuitems.add(
				new Menuitem("Debug"){
					public void execute(){
						debug();
					}});
		
		menu.menuitems.add(
				new Menuitem("Logout"){
					public void execute(){
						logout();
					}});
		
		System.out.println("[Hauptmenu] Treffe eine Wahl > ");
		
		createMenu(menu);
		
		
	}	
public void farmOre(){
		
		menu = new Menu();		
		menu.menuitems.add(
				new Menuitem("Bronze \t| Vorhanden: " + player.getCurrentPlanet().bronze.farm +
						"\t| Im Lager: " + player.getCurrentPlanet().bronze.storagef){
					public void execute(){
						player.getCurrentPlanet().mineBronze();
						farmOre();
					}});
		
		menu.menuitems.add(
				new Menuitem("Silber \t| Vorhanden: " + player.getCurrentPlanet().silver.farm +
						"\t| Im Lager: " + player.getCurrentPlanet().silver.storagef){
					public void execute(){
						player.getCurrentPlanet().mineSilver();
						farmOre();
					}});			

		menu.menuitems.add(
				new Menuitem("Gold \t| Vorhanden: " + player.getCurrentPlanet().gold.farm +
						"\t| Im Lager: " + player.getCurrentPlanet().gold.storagef){
					public void execute(){
						player.getCurrentPlanet().mineGold();
						farmOre();
					}});	
		
		menu.menuitems.add(
				new Menuitem("Juwelen \t| Vorhanden: " + player.getCurrentPlanet().jewel.farm +
						"\t| Im Lager: " + player.getCurrentPlanet().jewel.storagef){
					public void execute(){
						player.getCurrentPlanet().mineJewel();
						farmOre();
					}});
		menu.menuitems.add(
				new Menuitem("Zurueck"){
					public void execute(){
						mainmenu();
					}});

		System.out.println("[Erze sammeln] Welche Erze moechtest Du sammeln " + player.getName() + "?");		
		createMenu(menu);

	}

	
	public void actions(){
		
		menu = new Menu();
		
		menu.menuitems.add(
				new Menuitem("Umsehen"){
					public void execute(){
						showEnvironment();
						actions();
					}});
		
		menu.menuitems.add(
				new Menuitem("Bauen"){
					public void execute(){
						build();
					}});
		
		menu.menuitems.add(
				new Menuitem("Erze sammeln"){
					public void execute(){
						farmOre();
					}});

		menu.menuitems.add(
				new Menuitem("Reisen"){
					public void execute(){
						move();
					}});
		
		menu.menuitems.add(
				new Menuitem("Runde beenden"){
					public void execute(){
						App.getAppInstance().forceNewRound();
						actions();
					}});
		
		menu.menuitems.add(
				new Menuitem("Hauptmenu"){
					public void execute(){
						mainmenu();
					}});	

		System.out.println("[Aktionen] Was moechtest Du tun " + player.getName() + "?");
		
		createMenu(menu);

	}
	
	public void showEnvironment(){
		player.showEnvironment();
	}
	
	public void build(){
		System.out.println("Baue...");
		actions();
	}
	
	public void help(){
		System.out.println("Helfe...");
		mainmenu();
	}
	
	public void debug(){
		
		menu = new Menu();
		
		menu.menuitems.add(
				new Menuitem("Umsehen"){
					public void execute(){
						showEnvironment();
						debug();
					}});
		
		menu.menuitems.add(
				new Menuitem("Dem Spieler AK abziehen"){
					public void execute(){
						player.setAp(player.getAp() - 2);
						debug();
					}});
		
		menu.menuitems.add(
				new Menuitem("Spieler laden"){
					public void execute(){
						player.load(playerFile);
						player = App.getAppInstance().getPlayer();
						System.err.println("LADEN - AP: " + App.getAppInstance().getPlayer().getAp());
						debug();
					}});
		
		menu.menuitems.add(
				new Menuitem("Spieler speichern"){
					public void execute(){
						player.save(playerFile);
						debug();
					}});
		
		menu.menuitems.add(
				new Menuitem("Hauptmenue"){
					public void execute(){
						mainmenu();
					}});
		
		System.out.println("[Debug] Treffe eine Wahl > ");
		
		createMenu(menu);
		
	}

	public Login login(){

		//scanner = new Scanner(System.in);
		System.out.println("[Login] Gebe bitte Deinen Loginnamen ein > ");
		String loginname = scanner.nextLine();
		System.out.println("[Login] Gebe bitte Dein Passwort ein > ");
		String pass = scanner.nextLine();	

		Login login = new Login(loginname, pass);
		return login;

	}
	
	public void logout(){
		System.out.println("[NeMeWeSa] " + player.getName() + " hat das Spiel verlassen.");
		App.getAppInstance().logout();
	}
	
	public void move(){
		
		menu = new Menu();
		
		if(player.getLeftNeighbouringPlanet() != null){
			menu.menuitems.add(
				new Menuitem(player.getLeftNeighbouringPlanet().name){
					public void execute(){
						player.move(player.getLeftNeighbouringPlanet());
						actions();
					}});
		}
		
		if(player.getRightNeighbouringPlanet() != null){
			menu.menuitems.add(
				new Menuitem(player.getRightNeighbouringPlanet().name){
					public void execute(){
						player.move(player.getRightNeighbouringPlanet());
						actions();
					}});
		}
		
		menu.menuitems.add(
			new Menuitem("Hauptmenu"){
				public void execute(){
					mainmenu();
				}});

		System.out.println("[Reisen] Zu welchen Nachbarplaneten moechtest Du reisen " + player.getName() + "?");
		
		createMenu(menu);
		
	}
	
	public void createMenu(Menu menu){
		
		int i = 0;
		
		for(Menuitem item : menu.menuitems){
			System.out.println("[Option] " + ++i + ". " + item.desc + " > ");
		}
		
		while(true){
			
			try
			{
				int decision = scanner.nextInt();
				if(decision >= 1 && decision <= menu.menuitems.size()){
					
					menu.menuitems.get(decision-1).execute();
					break;
					
				}
				else{
					System.out.println("UNGUELTIGE WAHL, BITTE NOCHMAL VERSUCHEN");
				}				
			}
			catch (java.util.InputMismatchException exception)
			{
				System.out.println("BITTE NUR ZAHLEN EINGEBEN");
				scanner.next();
			}
		}
		
	}

}
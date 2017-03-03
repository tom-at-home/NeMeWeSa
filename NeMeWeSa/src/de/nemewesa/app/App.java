package de.nemewesa.app;

import java.util.Timer;

import de.nemewesa.buildings.SpaceStation;
import de.nemewesa.buildings.Storage;
import de.nemewesa.character.Player;
import de.nemewesa.db.DB;
import de.nemewesa.level.Level;
import de.nemewesa.spaceships.Transporter;


public class App {
	
	// Legt fest, ob Statusinformationen angezeigt werden sollen.
	public static final boolean DEV_MODE = false;
	
	private Player player;  // TEST FROM IDE
	private Level level;
	private Round round = Round.getRoundInstance();
	private DB db = DB.getInstance();
	private Console console;
	private Login login;
	private String prefix = "[NeMeWeSa] ";
	private static final App app = new App();
	private Timer timer;

	// Singelton Pattern
	private App(){}
	
	public static void main(String[] args)  {
		app.init();
		//app.pathTest();
	}
	
	// Die Instanz kann nur ueber getInstance geholt werden 
	public static App getAppInstance(){
		return app;
	}
	
	public void init(){
		if(DEV_MODE)
			db.createUsersTable();
		
		//loginUser();
		createNewLevel(1);
		//createPlayer(login.name);
		createPlayer("");	
		createConsole(player);
		loginUser();
		
		player.setName(login.name);
		
		// Timeout fuer blockierende Spieler
		setTimer();
		
		console.mainmenu();	
		
		//runTests();
		
	}

	public void createNewLevel(int lev){
		level = new Level(lev);
		level.generate();
		if(DEV_MODE)
			level.printChildren();
	}
	
	public void createPlayer(String name){
		
		player = new Player(name);
		player.setCurrentPlanet(level.getSector(0).getSolarsystem(0).getPlanet(0));
		player.setHomePlanet(player.getCurrentPlanet());
		player.setHomeSolarsystem(level.getSector(0).getSolarsystem(0));
		player.addOwnership(player.getHomePlanet());
		
		player.getHomePlanet().spacestation = new SpaceStation("SST_001", player.getHomePlanet());

		System.out.println(prefix + "Willkommen im NeMeWeSa " + player.getName());
		
		if(DEV_MODE)	
			System.out.println(player);
	
	}
	
	public void createConsole(Player player){
		this.console = new Console(player);
	}
	
	public void loginUser(){
		
		if(login == null){
			while( true ){
				login = db.loginUser(console);
				if(login != null)
					break;
			}
			
			System.out.println("[Login] Willkommen " + login.name + ". Du bist nun angemeldet");
		}
		else{
			System.out.println("[Login] Du bist bereits angemeldet " + login.name);
		}
	}
	
	public void logout(){
		timer.cancel();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	private void setTimer(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new RoundTimer(), 500, 120000);
	}
	
	public void forceNewRound(){
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new RoundTimer(), 50, 120000);
	}

	public void runTests(){

//		try {
//			Thread.sleep(2000);
//		}catch( InterruptedException e) {}

	}
	
}

package de.nemewesa.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.nemewesa.app.App;
import de.nemewesa.app.Console;
import de.nemewesa.app.Login;

public class DB {
    
    private static final DB dbcontroller = new DB();
	
    private static Connection connection;
    
    File DB_PATH = new File("login" + File.separator + "login.db");
	
    //private static final String DB_PATH = "login" + File.separator + "login.db";
	
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Fehler beim Laden des JDBC-Treibers:\n" + e.getMessage());
        }
    }
 
	private DB(){
    }
    
    public static DB getInstance(){
        return dbcontroller;
    }
 
    public void initDBConnection() {
        try {
            if (connection != null)
                return;
            if(App.DEV_MODE)
            	System.out.println("Verbinde mit der Datenbank");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed() && App.DEV_MODE)
                System.out.println("...Verbindung wurde hergestellt.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed() && App.DEV_MODE)
                            System.out.println("Verbindung zur Datenbank wurde geschlossen");
                    }
                } catch (SQLException e) {
					System.out.println("Eine SQLException ist aufgetreten:\n" + e.getMessage());
                }
            }
        });
    }
	
    public void createUsersTable() {
        try {
        	initDBConnection();
            Statement stmt = connection.createStatement();
			
            stmt.execute("DROP TABLE IF EXISTS benutzer;");
            stmt.execute("CREATE TABLE benutzer (id INTEGER PRIMARY KEY, username CHAR(50), password CHAR(50),  logindate DATETIME );");
			
            stmt.execute("INSERT INTO benutzer (username, password, logindate  ) VALUES ('tom', 'start', date('now'))");
            stmt.execute("INSERT INTO benutzer (username, password, logindate  ) VALUES ('ben', 'start', date('now'))");
            stmt.execute("INSERT INTO benutzer (username, password, logindate  ) VALUES ('jaures', 'start', date('now'))");
            stmt.execute("INSERT INTO benutzer (username, password, logindate  ) VALUES ('peter', 'start', date('now'))");			
        } 
        catch (SQLException e) {
            System.out.println("Fehler in der Datenbank-Abfrage:\n" + e.getMessage());
        }
    }
    
    public void showAllUsers() {
        try {
        	initDBConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM benutzer;");
            while (rs.next()) {
				System.out.println("ID:"+ rs.getInt("id") + "\t");
                System.out.println("Username = " + rs.getString("username"));
                System.out.println("Password = " + rs.getString("password"));
                System.out.println("Letzter Login = " + rs.getString("logindate"));
                System.out.print("\n");
            }
            rs.close();			
        } 
        catch (SQLException e) {
            System.out.println("Fehler in der Datenbank-Abfrage:\n" + e.getMessage());    
        }
    }
    
    public Login loginUser(Console console) {
        
    	Login login = console.login();
    	
    	try {
        	        	
        	initDBConnection();
        	
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM benutzer WHERE username = '" + login.name + "';");

            if(rs.next()){
            	if(rs.getString("password").equals(login.pass)){
            		return login;
            	}
            	else{
                	System.out.println("[Login] Falsches Passwort");
                	login = null;
            	}
            }
            else{
            	System.out.println("[Login] Falscher Benutzername");
            	login = null;
            }      
            rs.close();
            
        } 
        catch (SQLException e) {
            System.out.println("Fehler in der Datenbank-Abfrage:\n" + e.getMessage());
        }
        
    	return login;
    	
    }
    
}
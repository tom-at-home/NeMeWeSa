package de.nemewesa.app;

import javax.swing.JDialog;
import javax.swing.JRootPane;

import de.nemewesa.character.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ReadSingleChar {
	
	//  Innere Klasse zum Zwischenspeichern der 
	//	gedrueckten Tastaturtaste
	class KeyPressClass {
		private char chr ;	
		public void setChar(char chr) {
			this.chr = chr;
		}	
		public char getChar( ){
			return this.chr;
		}
	}
	
	
	//  Innere Klasse zum Zwischenspeichern der 
	//	gedrueckten Tastaturtaste
	public char getCh() {  		
		final KeyPressClass kpc  = new KeyPressClass();
        final JDialog frame = new JDialog();  
        synchronized (frame) {  
			frame.setVisible(false);
            frame.setUndecorated(true);  
			

			
			
			// Einen (Eventlistener) für Tastatureingaben hinzufuegen
			//
            frame.addKeyListener(new KeyListener() {
                @Override 
                public void keyPressed(KeyEvent e) {  
                    synchronized (frame) {  
                        frame.setVisible(false);  
                        frame.dispose();  
                        frame.notify(); 
						kpc.setChar(e.getKeyChar());										
                    }  					
                }  
				
			
                @Override 
                public void keyReleased(KeyEvent e) {  
                }  
                @Override 
                public void keyTyped(KeyEvent e) {  
                }  
            });  
			
            frame.setVisible(true);
			
            try {  
                frame.wait();  
            } catch (InterruptedException e1) {  
            }
			
        }  
		
		return kpc.getChar();
    }
	
	
	// Anwendungsbeispiel
	public static void main(String[] args) {
		ReadSingleChar tk = new ReadSingleChar();
		while(true) {
			char chr = tk.getCh() ;
			if(chr == 'Q') {
				System.out.println("Exit");
				System.exit(0);
			}
			System.out.println("char: " +chr);
		}
	}
}
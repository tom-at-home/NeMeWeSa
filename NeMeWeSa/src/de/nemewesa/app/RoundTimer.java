package de.nemewesa.app;

import java.util.TimerTask;

public class RoundTimer extends TimerTask {

	private Round round = Round.getRoundInstance();
	
	@Override
	public void run() {
		
		//System.out.println("Es ist Zeit fuer eine neue Runde");
		this.round.setNewRound();	
	}

}

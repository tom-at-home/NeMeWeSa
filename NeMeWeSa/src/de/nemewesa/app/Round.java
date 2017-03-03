package de.nemewesa.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TimerTask;

public class Round implements Subject, Serializable{
	
	private static final Round roundInstance = new Round();
	private ArrayList<Observer> observers;
	
	private int round = 0;
	
	// Singelton Pattern
	private Round(){
		observers = new ArrayList<Observer>();
	}

	// Die Instanz kann nur ueber getInstance geholt werden 
	public static Round getRoundInstance(){
		return roundInstance;
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this.round);
		}
	}
	
	public void roundChanged() {
		notifyObservers();
	}
	
	public void setNewRound() {
		this.round += 1;
		roundChanged();
	}

	public int getRound() {
		return round;
	}

}

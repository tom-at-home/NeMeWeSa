package de.nemewesa.level;

import java.io.Serializable;

import de.nemewesa.character.Enemy;
import de.nemewesa.character.Player;

public class Moon implements Generetable, Serializable{
	
	public String moonName;	
	public Enemy enemy;
	public Planet parent;
	public boolean captured = false;
	
	public Moon(String moonName, Enemy enemy, Planet parent) {
		this.moonName = moonName;
		this.enemy = enemy;
		this.parent = parent;
	}
	//Kampfsystem
	public void victory() {
		if(this.enemy.enemyHealth<=0) {
			captured = true;
		}
	}
	//getter
	public Enemy getEnemy() {
		return enemy;
	}
	public Planet getParent() {
		return parent;
	}
	public boolean isCaptured() {
		return captured;
	}
	@Override
	public String getName() {
		return moonName;
	}
	@Override
	public void generate(int element) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void printChildren() {
		// TODO Auto-generated method stub
		
	}

}
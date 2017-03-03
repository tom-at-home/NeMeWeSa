package de.nemewesa.character;

import java.io.Serializable;

import de.nemewesa.helper.Helper;
import de.nemewesa.level.Moon;
import de.nemewesa.spaceships.Spaceship;

public class Enemy implements Serializable{
	
	public String[] enemyNames = {"Isiyah", "Hemtein", "Begkas", "Adajar", "Kaluru", "Begde", "Mahiani", "Zamzur", 
								 "Kulgul", "Qishin", "Najgul", "Najxu", "Mardram", "Altein", "Gioalie", "Eystar", 
								 "Qisuru", "Adagul", "Kulkas", "Nazetu"};
	public String name;
	public  int enemyHealth;
	public  int enemyAttack;
	public Moon parent;
	public  Object greenAlien;
	
	public Enemy(Moon parent) {
		this.name = enemyNames[Helper.random(0, enemyNames.length)];
		this.enemyHealth = Helper.random(25, 75);
		this.enemyAttack = Helper.random(3, 6);
		this.parent = parent;
	}
	public int enemyAttack(Spaceship target) {
		return target.shipHealth = target.shipHealth - this.enemyAttack;
	}
	// getter
	public String[] getEnemyNames() {
		return enemyNames;
	}
	public String getName() {
		return name;
	}
	public int getEnemyHealth() {
		return enemyHealth;
	}
	public int getEnemyAttack() {
		return enemyAttack;
	}
	public Moon getParent() {
		return parent;
	}
	public Object getGreenAlien() {
		return greenAlien;
	}

	
	

}
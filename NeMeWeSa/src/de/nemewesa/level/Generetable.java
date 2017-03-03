package de.nemewesa.level;

public interface Generetable {

	//public abstract void generate();

	public abstract String getName();
	
	public abstract void generate(int element);
	
	public abstract void printChildren();
	
}

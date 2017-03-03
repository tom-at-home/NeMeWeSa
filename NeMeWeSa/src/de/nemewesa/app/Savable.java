package de.nemewesa.app;
import java.io.Serializable;

public interface Savable extends Serializable{
	
	void save(String filename);
	
	void load(String filename);
	
}

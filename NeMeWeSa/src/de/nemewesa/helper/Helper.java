package de.nemewesa.helper;

public class Helper {
	
	public static int random(int initialVal, int rangeVal){
		
		int randomVal = 0;
		
		randomVal = (int)(Math.random() * rangeVal) + initialVal;
			
		return randomVal;
		
	}

}

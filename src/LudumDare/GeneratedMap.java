package LudumDare;

import java.util.ArrayList;

import org.newdawn.slick.Input;

public class GeneratedMap {
	
	/*
	 * The whole game is based on list of allowed keys
	 * If you are pressing the allowed key you are making one step
	 */
	
	// Array list of integers for list of keys (keys are integers)
	public ArrayList<Integer> list = new ArrayList<Integer>();
	
	public static int disallowedKey = Input.KEY_0; // Disallowed key to use 
	
	public GeneratedMap() { }
	
	// generating method
	public void generate() {
		list.clear(); // Clearing whole list to avoid bugs
		int num = Game.rand.nextInt(5*Game.level)+10; // Random number of "corridors"
		int maxLength = 0;
		for(int i=0; i<num; i++) {	// loop for every "corridor"			
			int key = randomKey(); // Getting random key
			maxLength = Game.rand.nextInt(6*Game.level/2)+1; 	// Random length of "corridor"
			for(int x=0; x<maxLength; x++)  list.add(key); // Adding length to "corridor"
		}
	}
	
	public int randomKey() {
		int toGenerate = 0;
		
		do {
			int waysNum = Game.rand.nextInt(4)+1;	// Generated number of availible 5 keys 
			switch(waysNum) {
				case 1: 
					toGenerate = Input.KEY_W;
					break;
				case 2: 
					toGenerate = Input.KEY_S;
					break;
				case 3: 
					toGenerate = Input.KEY_A;
					break;
				case 4: 
					toGenerate = Input.KEY_D;
					break;
			}
			System.out.println("Gen: "+toGenerate+" | "+disallowedKey);
		} while(toGenerate == disallowedKey); // If generated key equals disallowed key - repeat the loop
		
		disallowedKey = Game.switchKey(toGenerate); // Make new disallowed key ("reversed") from actual key
			
		return toGenerate;
	}
}

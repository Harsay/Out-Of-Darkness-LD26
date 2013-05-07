package LudumDare;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

// Sounds class to hold everything in one place
public class Sounds {
	
	private String loc = "data/snd/";	// Location to sounds

	public Sound step;	// step sound
	public Sound hurt;	// hurt sound
	
	public Sounds() throws SlickException {
		// Loading sounds
		step = new Sound(loc+"step.wav");
		hurt = new Sound(loc+"hurt.wav");
	}
	
}

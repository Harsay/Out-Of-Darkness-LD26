/*
 * Ludum Dare 26 Entry
 * Author: Harsay
 * Game: Out of darkness
 * 
 * Propably this code isn't wrote 100% properly but it should show how Slick2D game works.
 * 
 * This game was made in 9 hours + 3 hours to find bugs + 1 hour to comment it
 * 
 */

package LudumDare;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import LudumDare.States.GameOver;
import LudumDare.States.Menu;
import LudumDare.States.Play;

public class Game extends StateBasedGame {
	
	public static Random rand = new Random();				// For generating random numbers
	public static GeneratedMap map = new GeneratedMap(); 	// My own game map class
	
	public static Sounds snd;								// My own sounds class
	
	public static int level = 1;							// Game level set by player (default: 1)
	
	public enum States {									// Enum of states for game states IDs
		MENU,
		PLAY,
		GAMEOVER
	}

	public Game() {											
		super("Out of darkness | Ludum Dare 26");			// Window name
	}

	public static void main(String[] args) throws SlickException {
		snd =  new Sounds();	// Loading sounds
		
		// Window settings
		AppGameContainer c = new AppGameContainer(new Game()); 
		c.setAlwaysRender(true);			// To avoid rendering lags
		c.setShowFPS(false);				// Hide FPS
		c.setDisplayMode(1024, 768, false);	// 1024x768 window
		c.setMouseGrabbed(true);			// Hide mouse cursor
		c.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// This function loads game states
		addState(new Menu());
		addState(new Play());	
		addState(new GameOver());
	}
	
	// function to return "reversed" key (to avoid movement bugs)
	public static int switchKey(int key) {
		int toReturn = 0;
		switch(key) {
			case Input.KEY_A: toReturn = Input.KEY_D; break;
			case Input.KEY_D: toReturn = Input.KEY_A; break;
			case Input.KEY_W: toReturn = Input.KEY_S; break;
			case Input.KEY_S: toReturn = Input.KEY_W; break;
		}
		return toReturn;
	}
}
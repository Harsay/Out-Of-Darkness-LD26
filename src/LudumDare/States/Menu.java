package LudumDare.States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import LudumDare.Game;

public class Menu extends BasicGameState {
	
	// Images
	private Image logo;
	private Image play;
	private Image about;
	private Image exit;
	
	// Menu things
	private int menuPos = 0; // Position of pointer
	public static boolean menu[] = new boolean[3]; // Array of menu options
	private Rectangle menuPointer = new Rectangle(25, 0, 10, 10); // "pointer"
	
	public Menu() throws SlickException {
		// Loading images
		logo 	= new Image("data/gfx/menu/logo.png");
		play 	= new Image("data/gfx/menu/play.png");
		about 	= new Image("data/gfx/menu/about.png");
		exit 	= new Image("data/gfx/menu/exit.png");
	}
	
	// This method is called everytime when game enters to this state
	public void enter(GameContainer gc, StateBasedGame sbg) {
		gc.getInput().clearKeyPressedRecord(); // Sometimes key pressed in another state can affect other states
		menuPos = 0; 
		for(int i=0; i<3; i++) menu[i] = false;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(new Color(0, 0, 0)); // setting black background to avoid bugs with other states
		
		if(menu[0]) { // When play is true
			g.drawString("Set the level: "+Game.level, gc.getWidth()/2-80, gc.getHeight()/2-15);
			g.drawString("Use W or S to change level", gc.getWidth()/2-120, gc.getHeight()/2+15);
			g.drawString("Press enter when you have chosen", gc.getWidth()/2-140, gc.getHeight()/2+30);
		}
		else if(menu[1]) { // When about is true
			g.drawImage(about, 40, 300);
			g.drawString("Your goal is to escape from darkness. Sound and light can help you.", 40, 350);
			g.drawString("If you are away from quit the screen is darker. When close the screen is lighter.", 40, 365);
			g.drawString("Controls: W,S,A,D to move (1 press = 1 step) and ESC to return to menu", 40, 380);
			g.drawString("This game is made for Ludum Dare 26 compo - Theme minimalism. Remember to rate! :)", 40, 425);
			g.drawString("Press enter to return.", 40, 460);
		} 
		else { // When nothing from menu is true
			// Info about screen modes
			if(gc.isFullscreen()) g.drawString("Press P to disable full screen mode", 40, 738);
			else g.drawString("Press P to enable full screen mode", 40, 738);
			
			g.drawImage(logo, 25, 300);
			g.drawImage(play, 45, 400);
			g.drawImage(about, 40, 435);
			g.drawImage(exit, 45, 470);
			
			g.fill(menuPointer);
			g.draw(menuPointer);
		}	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput(); // Getting actual input
		
		if(menu[0]) { // When play is true
			if(input.isKeyPressed(Input.KEY_ENTER)) sbg.enterState(Game.States.PLAY.ordinal(), new FadeOutTransition(), new FadeInTransition()); // When enter is pressed change state to PLAY with nice transition :)
			if(input.isKeyPressed(Input.KEY_W)) Game.level++; // When W is pressed increase game level by 1
			if(input.isKeyPressed(Input.KEY_S)) { if(Game.level > 1) Game.level--; } // When S is pressed reduce game level by 1 if is higher than 1
		} 
		else if(menu[1]) { if(input.isKeyPressed(Input.KEY_ENTER)) menu[1] = false; } // When about is enabled and enter is pressed it returns to main menu
		else if(menu[2]) gc.exit(); // Exit when exit is enabled (cpt. Obvious)
		else { // When nothing is true show main menu
			if(input.isKeyPressed(Input.KEY_S)) { if(menuPos < 2) menuPos++; } // S pressed - pointer lower
			if(input.isKeyPressed(Input.KEY_W)) { if(menuPos > 0) menuPos--; } // W pressed - pointer higher
			if(input.isKeyPressed(Input.KEY_ENTER)) menu[menuPos] = true; // If enter is pressed. The selected option is true
			menuPointer.setY(400+10+35*menuPos); // Setting the position of "pointer"
			if(input.isKeyPressed(Input.KEY_P)) {
				if(!gc.isFullscreen())
					gc.setFullscreen(true);
				else 
					gc.setFullscreen(false);
			}
		}	
	}

	// This method returns this state own ID
	@Override
	public int getID() {
		return Game.States.MENU.ordinal();
	}
}
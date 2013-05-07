package LudumDare.States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import LudumDare.Game;
import LudumDare.Shadow;

public class Play extends BasicGameState {
	
	// Objects
	private Color hurtColor = new Color(255, 0, 0, 0);
	private Color color = new Color(255, 255, 255, 0);
	private Shadow shadow;
	
	// Booleans
	public boolean goBrighter = true;
	public boolean hurt = false;
	
	// Ints
	public int stepTime = 750;	// Time length of step
	public int steps = 0;		// Number of done steps
	
	// Float
	public float lightProportion = 0;	// How much white color should when making step
	
	public Play() throws SlickException {
		shadow = new Shadow(); // loading shadow 
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		gc.getInput().clearKeyPressedRecord();
		// Making standard values when game enters this state
		color.a = 0;	// alpha of white color
		steps = 0;		// Number of steps
		Game.map.generate(); // Generating new map
		lightProportion = 1.0f/Game.map.list.size(); // Counting light proportion
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Rendering rectangle with white color
		g.setColor(color);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		
		// Rendering rectangle with red color (hurt)
		g.setColor(hurtColor);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		
		// Rendering shadow
		shadow.render(g);
		
		g.setColor(new Color(255, 255, 255)); // setting color to normal
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(steps == Game.map.list.size()) { // if reached maximum number of steps
			sbg.enterState(Game.States.GAMEOVER.ordinal(), new FadeOutTransition(new Color(255, 255, 255)), new FadeInTransition(new Color(255, 255, 255)));
			return; // stop performing the method
		}
		
		if(stepTime <= 0) { // when step isn't "in doing" 
			hurtColor.a = 0; // hiding hurt color
			hurt = false; // hurt is false
			if(input.isKeyDown(Game.map.list.get(steps))) { 
				goBrighter = true; // make screen brighter 
				steps++; // add step
				stepTime = 750; // time of step
				Game.snd.step.play(); // play sound of step
			} 
			else if(steps > 0 && input.isKeyDown(Game.switchKey(Game.map.list.get(steps-1)))) { 
				goBrighter = false; // make screen darker
				steps--; // remove one step
				stepTime = 750; // step time
				Game.snd.step.play(); // play step sound
			}
			else if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_D)) {
				// When one of movement keys is pressed and it's wrong.
				hurtColor.a = 0.1f; // show hurt color
				hurt = true; // hurt is true
				stepTime = 750; // step time
				Game.snd.hurt.play(); // hurt sound play
			}
		} else { // if step is "in doing"
			stepTime -= delta; // remove delta from step time
			if(!hurt && goBrighter) {
				color.a += lightProportion*delta/750; // making screen brighter
			} else if(!hurt) {
				color.a -= lightProportion*delta/750; // making screen darker
			}
			
			// A little bit wrong made shadow moving but working ^_^
			if(stepTime > 500) {
				shadow.x = ((gc.getWidth()-shadow.getWidth())/2)+10;
			} 			
			else if(stepTime > 250) {
				shadow.x = ((gc.getWidth()-shadow.getWidth())/2)-10;
			}	
			else {
				shadow.x = (gc.getWidth()-shadow.getWidth())/2;
				shadow.y = (gc.getHeight()-shadow.getHeight())/2;
			}
			
		}

		// If escape is pressed - return to main menu
		if(input.isKeyPressed(Input.KEY_ESCAPE)) sbg.enterState(Game.States.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.States.PLAY.ordinal();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}
}
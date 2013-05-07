package LudumDare.States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import LudumDare.Game;

public class GameOver extends BasicGameState {
	
	// Image
	private Image congratz;
	
	public GameOver() throws SlickException {
		// Load images
		congratz = new Image("data/gfx/congratz.png");
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		gc.getInput().clearKeyPressedRecord();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(new Color(255, 255, 255)); // White background to nice "escape" effect ;p
		g.drawImage(congratz, gc.getWidth()/2-congratz.getWidth()/2, gc.getHeight()/2-congratz.getHeight()/2); // Drawing information about escape
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		// If enter is pressed return to main menu with nice transition.
		if(input.isKeyPressed(Input.KEY_ENTER)) sbg.enterState(Game.States.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.States.GAMEOVER.ordinal();
	}
}
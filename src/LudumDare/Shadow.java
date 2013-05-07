package LudumDare;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// "Shadow" class to have less code in play state.
public class Shadow extends Image {
	
	public float x; // x coords
	public float y; // y coords

	public Shadow() throws SlickException {
		super("data/gfx/shadow.png"); // Loading image
		x = (1024-getWidth())/2; // setting x coord
		y =	(768-getHeight())/2; // setting y coord
	}
	
	// method to "render" this "shadow".
	public void render(Graphics g) {
		g.drawImage(this, x, y);
	}
	
}

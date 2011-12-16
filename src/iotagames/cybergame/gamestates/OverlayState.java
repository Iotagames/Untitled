package iotagames.cybergame.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class OverlayState extends GameState {
	public boolean drawLower = true;
	public boolean updateLower = false;
	public GameState lower;
	
	public OverlayState(GameState lower, boolean drawLower, boolean updateLower) {
		this.drawLower = drawLower;
		this.updateLower = updateLower;
		this.lower = lower;
	}
	
	public OverlayState(GameState lower) {
		this(lower, true, false);
	}
	
	public void drawing(GameContainer gc, Graphics g) {
		if (drawLower) lower.drawing(gc, g);
		super.drawing(gc, g);
	}
	
	public void update(GameContainer gc, int delta) {
		if (updateLower) lower.update(gc, delta);
		super.update(gc, delta);
	}
}

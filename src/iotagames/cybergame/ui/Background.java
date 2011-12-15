package iotagames.cybergame.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Background extends Field {
	public Background(String nm,float xo,float yo) {
		super(nm,xo,yo);
	}
	
	public void draw(GameContainer gc, Graphics g) {
		image.draw(xpos,ypos,w,h,color);
	}
}
package iotagames.cybergame.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ScrollingBG extends Background {
	private Image flipImageY;
	private Image flipImageX;
	private int speed;
	private int direction;
	public float ox, oy;
	
	public ScrollingBG(String nm,float xo,float yo) {
		super(nm,xo,yo);
		direction=0;
		speed=10;
		ox=xpos; oy=ypos;
	}
	
	/** Stores flipped copies of the image.
	 * 
	 */
	public void loadImage(String nm) {
		super.loadImage(nm);
		flipImageX=image.getFlippedCopy(true,false);
		flipImageY=image.getFlippedCopy(false,true);
	}

	/** Move the images based on their direction and speed.
	 * If the x or y gets to end of the second image, reset
	 * the position back to the start of the first image.
	 */
	public void update(GameContainer gc, int delta) {
		super.update(gc,delta);
		if (direction==0||direction==2) {
			ypos+=speed*(delta/16.f);
			if (ypos>=oy+(getHeight()*2))
				ypos=oy;
		}
		if (direction==1||direction==2) {
			xpos+=speed*(delta/16.f);
			if (xpos>=ox+(getHeight()*2))
				xpos=ox;
		}
	}
	
	/** Draw the extra images based on the direction.
	 * 
	 */
	public void draw(GameContainer gc, Graphics g) {
		super.draw(gc, g);
		Color col=new Color(255,255,255,color.a*255);
		if (direction==0||direction==2) {
			flipImageY.draw( xpos,ypos+(getHeight()),
						scale,col );
			image.draw(	xpos,ypos+(getHeight()*2),
						scale,col );
			flipImageY.draw( xpos,ypos-(getHeight()),
						scale,col );
			image.draw(	xpos,ypos-(getHeight()*2),
						scale,col );
		}
		if (direction==1||direction==2) {
			flipImageX.draw( xpos+(getWidth()),ypos,
					scale,col );
			image.draw(	xpos+(getWidth()*2),ypos,
					scale,col );
			flipImageX.draw( xpos-(getWidth()),ypos,
					scale,col );
			image.draw(	xpos-(getWidth()*2),ypos,
					scale,col );
			
		}
	}
}

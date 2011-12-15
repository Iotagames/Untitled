package iotagames.cybergame.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ProgressBar extends Field {
	private int fill;
	private int max;
	private Field border;
	Rectangle rect;
	
	public ProgressBar(String name, String borderName, float x, float y, float w, float h, int f, int m) {
		super(name,x,y,w,h);
		setMax(m);
		setFill(f);
		setBorder(borderName,1);
	}
	
	public ProgressBar(String name, String borderName, float x, float y, float w, float h, int f) {
		super(name,x,y,w,h);
		setMax(f);
		setFill(f);
		setBorder(borderName,1);
	}

	public void draw(GameContainer gc, Graphics g) {
		float right = (float) (w*((float)fill/max));
		image=image.getSubImage(0,0,(int)right,(int)h);
		image.draw(xpos,ypos,right,h,color);
		if (border!=null) border.draw(gc, g);
		else g.draw(rect);
	}
	
	public void setBorder(String borderName,int offSet) {
		if(borderName!=null) border=new Field(borderName,xpos-(offSet/2),ypos+(offSet/2),w+offSet,h+offSet);
		else rect = new Rectangle(xpos,ypos,(int)w+1,(int)h+1);
	}
	
	public void fill(int f) {
		setFill(fill+f);
	}
	
	public void deplete(int f) {
		setFill(fill-f);
	}
	
	public void setFill(int f) {
		fill=f;
		if (fill>max) fill=max;
		if (fill<0) fill=0;
	}
	
	public void setMax(int m) {
		max=m;
		image=image.getScaledCopy((int)w,(int)h);
	}
	
	public int getMax() {return max;}
	public int getFill() {return fill;}
}

package iotagames.cybergame.ui;

import iotagames.cybergame.utilities.FontManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class Text extends Field {
	protected Font font = FontManager.defaultFont;
	protected String string;
	public boolean outlined = true;
	public boolean shadowed = true;
	
	public Text(String txt,float xo,float yo) {
		super(txt,xo,yo);
		this.string = txt;
		getHeight();
		getWidth();
	}
	
	public Text(Font fnt,String txt,float xo,float yo) {
		this(txt,xo,yo);
		font=fnt;
	}
	
	public void loadImage(String file) {}
	
	public void draw(GameContainer gc, Graphics g) {
		Color black;
		if (scale != 1) g.scale(scale, scale);
		if (color.a==1)
			black=Color.black;
		else
			black = new Color(0,0,0,(int)(color.a*255));
		if (outlined) {
			font.drawString(xpos+1,ypos,string,black);
			font.drawString(xpos,ypos+1,string,black);
			font.drawString(xpos-1,ypos,string,black);
			font.drawString(xpos,ypos-1,string,black);
		}
		if (shadowed) font.drawString(xpos+2,ypos+2,string,black);
		font.drawString(xpos,ypos,string,color);
		if (scale != 1) g.scale(1f/scale, 1f/scale);
	}
	
	public float getHeight() {
		float ho=font.getHeight(string);
		h=ho;
		return ho;
	}

	public int getSpace() {
		if (font instanceof UnicodeFont)
			return ((UnicodeFont) font).getSpaceWidth();
		else
			return font.getWidth(string);
	}
	
	public float getWidth() {
		float wo=font.getWidth(string);
		w=wo;
		return wo;
	}
	
	public void setFont(Font fnt) {
		font = fnt;
	}
	
	public void setText(String txt) {
		string=txt;
		getWidth();
		getHeight();
	}
	
	public String getText() {return string;}
	public String toString() {return string;}
}

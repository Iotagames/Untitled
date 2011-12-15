package iotagames.cybergame.ui;

import iotagames.cybergame.engine.ShmupGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class Text extends Field {
	protected Font font = ShmupGame.font;
	protected String string;
	
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
		Color black,white;
		
		if (color.a==1) {
			black=Color.black;
			white=Color.white;
		} else {
			black = new Color(0,0,0,color.a*255);
			white = new Color(255,255,255,color.a*255);
		}
		font.drawString(xpos+1,ypos,string,black);
		font.drawString(xpos,ypos+1,string,black);
		font.drawString(xpos-1,ypos,string,black);
		font.drawString(xpos,ypos-1,string,black);
		font.drawString(xpos+2,ypos+2,string,black);
		font.drawString(xpos,ypos,string,white);
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

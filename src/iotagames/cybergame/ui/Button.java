package iotagames.cybergame.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Button extends Field {
	private Text text;
	
	public Button(String file,String txt,float xo,float yo,float wo,float ho) {
		super(file,xo,yo,wo,ho);
		text = new Text(txt,xo,yo+image.getHeight()/4);
	}
	
	public Button(String file,String txt,float xo,float yo) {
		super(file,xo,yo);
		text = new Text(txt,xo,yo+image.getHeight()/4);
	}
	
	public Button(String file,float xo,float yo) {
		super(file,xo,yo);
		text = new Text("",xo,yo+image.getHeight()/4);
	}
	
	public Button(String file) {
		super(file);
		text = new Text("", 0, 0);
	}
	
	public void setText(String txt) {
		text.setText(txt);
	}
	
	public void update(GameContainer gc, int delta) {
		super.update(gc,delta);
		text.update(gc,delta);
	}
	
	public void centerText() {
		text.setPosition(xpos+getWidth()/2-text.getWidth()/2, ypos+getHeight()/4);
	}
	
	public void draw(GameContainer gc, Graphics g) {
		centerText();
		text.color.a = color.a;
		super.draw(gc, g);
		text.draw(gc, g);
	}
	
	public String toString() {
		return "Button: " + text.toString();
	}
}

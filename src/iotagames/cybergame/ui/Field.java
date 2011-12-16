package iotagames.cybergame.ui;

import java.util.ArrayList;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.events.EntityEvent;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Field extends Entity {
	protected float w;
	protected float h;
	protected boolean focused = false;
	protected Color color = new Color(255, 255, 255, 255);
	public ArrayList<EntityEvent> mouseover = new ArrayList<EntityEvent>();
	public ArrayList<EntityEvent> mouseout = new ArrayList<EntityEvent>();
	public ArrayList<EntityEvent> mousedown = new ArrayList<EntityEvent>();
	
	public Field(String nm,float xo,float yo,float wo,float ho) {
		super(nm,xo,yo);
		scale=1.0f;
		w=wo;
		h=ho;
	}
	
	public Field(String nm,float xo,float yo) {super(nm,xo,yo); init2();}
	public Field(String nm) {super(nm); init2();}
	
	public void init2() {
		scale=1.0f;
		hitboxScale = 1.0f;
		if (image!=null) 
			setSize(image.getWidth(),image.getHeight());
		else 
			setSize(1,1);
	}
	
	public boolean inputPressed(Input input, boolean mouseDown) {
		if (mouseDown ||input.isKeyPressed(Input.KEY_SPACE)
					  ||input.isKeyPressed(Input.KEY_ENTER)
					  ||input.isKeyPressed(Input.KEY_Z)) {
			onMouseDown();
			return true;
		}
		return false;
	}
	
	public boolean inputPrev(Input input) {
		return (input.isKeyPressed(Input.KEY_UP));
	}
	
	public boolean inputNext(Input input) {
		return (input.isKeyPressed(Input.KEY_DOWN));
	}
	
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		Input input = gc.getInput();
		if (boundingBox().contains(input.getMouseX(), input.getMouseY())) {
			if (!focused) onMouseOver();
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				onMouseDown();
		} else {
			if (focused) onMouseOut();
		}
	}
	
	public void draw(GameContainer gc, Graphics g) {
		color.a = focused ? 1 : 0.5f;
		image.draw(xpos,ypos,w,h,color);
	}
	
	public void setSize(float wo,float ho) {w=wo; h=ho;}
	
	public void onMouseDown() {
		for (EntityEvent e : mousedown)
			e.onEvent(this);
	}
	public void onMouseOver() {
		focus();
		for (EntityEvent e : mouseover)
			e.onEvent(this);
	}
	public void onMouseOut() {
		blur();
		for (EntityEvent e : mouseout)
			e.onEvent(this);
	}
	
	public void onPress() {}
	
	public boolean isFocused() {return focused;}
	public void focus() {focused=true;}
	public void blur() {focused=false;}
	
	public void setWidth(float wo) {w=wo;}
	public void setHeight(float ho) {h=ho;}
	public float getWidth() {return w;}
	public float getHeight() {return h;}
	public Rectangle boundingBox() {return new Rectangle(xpos,ypos,w,h);}
}

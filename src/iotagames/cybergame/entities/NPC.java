package iotagames.cybergame.entities;

import iotagames.cybergame.events.EntityEvent;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;


public class NPC extends Entity {
	public ArrayList<EntityEvent> behaviour = new ArrayList<EntityEvent>();
	
	public NPC(String imageRef, float xPos, float yPos) {
		super(imageRef, xPos, yPos);
	}
	
	public void update(GameContainer gc, int delta) {
		for (EntityEvent e : behaviour)
			e.onEvent(this);
		super.update(gc, delta);
	}
}

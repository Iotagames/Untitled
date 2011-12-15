package iotagames.cybergame.entities;

import iotagames.cybergame.events.EntityEvent;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

public class Enemy extends Entity {
	public ArrayList<EntityEvent> behaviour = new ArrayList<EntityEvent>();
	
	public Enemy(String imageRef, float xPos, float yPos) {
		super(imageRef, xPos, yPos);
	}
	
	public void update(GameContainer gc, int delta) {
		for (EntityEvent e : behaviour)
			e.onEvent(this);
		super.update(gc, delta);
	}
	
	public void onCollision(Entity other) {
		super.onCollision(other);
		if (other instanceof Bullet) {
			other.die();
			die();
		}
	}
    
    public void onCollision() {
    	// simple wall collision
    	xpos -= xSpeed;
    	ypos -= ySpeed;
    }
}

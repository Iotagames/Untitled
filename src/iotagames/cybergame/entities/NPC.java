package iotagames.cybergame.entities;

import iotagames.cybergame.events.CollisionEvent;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.GameStateManager;
import iotagames.cybergame.ui.DialogueBox;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;


public class NPC extends Entity {
	public ArrayList<EntityEvent> behaviour = new ArrayList<EntityEvent>();
	
	public NPC(String imageRef, float xPos, float yPos) {
		super(imageRef, xPos, yPos);
		canActivate = true;
	}

	public void addDialogue(final String[] dialogue) {
		addDialogue(GameStateManager.currentState(), dialogue, true);
	}

	public void addDialogue(final GameState current, final String[] dialogue) {
		addDialogue(current, dialogue, true);
	}
	
	public void addDialogue(final GameState current, final String[] dialogue, boolean clearActivity) {
		if (clearActivity) activity.clear();
	    activity.add(new CollisionEvent() {
	        public void onCollision(Entity npc, Entity player) {
	            GameStateManager.states.add(new DialogueBox(current, dialogue));    
	        }   
	    });
	}
	
	public void update(GameContainer gc, int delta) {
		for (EntityEvent e : behaviour)
			e.onEvent(this);
		super.update(gc, delta);
	}
}

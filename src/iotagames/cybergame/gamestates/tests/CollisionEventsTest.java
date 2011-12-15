package iotagames.cybergame.gamestates.tests;

import java.util.Date;

import org.newdawn.slick.SlickException;

import iotagames.cybergame.entities.Enemy;
import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.entities.Player;
import iotagames.cybergame.events.CollisionEvent;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.gamestates.GameState;

public class CollisionEventsTest extends GameState {
    Player guy = new Player("player", 300, 300);
    Enemy enemy = new Enemy("bug2", 400, 400);
    
	public CollisionEventsTest() throws SlickException {
		super();
		entities.clear();
		entities.add(guy);
	    camera.targetted = guy;
		entities.add(enemy);
		addTests();
	}
	
	private void addTests() {
        guy.collision.add(new CollisionEvent() {
			public void onCollision(Entity first, Entity second) {
	   			System.out.println(first + " has collided with " + second + " at " + new Date() + "!");
			}
        });
        enemy.behaviour.add(new EntityEvent() {
	   		public void onEvent(Entity entity) {
	   			if (entity.xSpeed == 0) 
	   				entity.xSpeed = -3;
	   		}
        });
        enemy.death.add(new EntityEvent() {
	   		public void onEvent(Entity entity) {
	   			System.out.println(entity + " has died at " + new Date() + "!");
	   		}
        });
	}
}

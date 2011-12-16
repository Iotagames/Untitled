package iotagames.cybergame.entities;

import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.GameStateManager;
import iotagames.cybergame.gamestates.TileMapState;
import iotagames.cybergame.utilities.Finder;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.util.pathfinding.Mover;

public class Enemy extends Entity implements Mover {
	public ArrayList<EntityEvent> behaviour = new ArrayList<EntityEvent>();
	public Entity target;
	public Finder finder;
	public float enemySpeed = 2;
	
	public Enemy(String imageRef, float xPos, float yPos) {
		super(imageRef, xPos, yPos);
	}
	
	private void initFinder() {
		GameState state = GameStateManager.currentState();
		if (state instanceof TileMapState) {
			TileMapState mapState = (TileMapState) state;
			target = mapState.player;
			finder = new Finder(mapState.getMap());
		}
	}
	
	public void update(GameContainer gc, int delta) {
		if (target == null) initFinder();
		else finder.findPath(this, target, enemySpeed);
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

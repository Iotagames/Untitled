package iotagames.cybergame.gamestates;

import java.awt.Point;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.entities.Player;
import iotagames.cybergame.utilities.TileMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class TileMapState extends GameState {
    protected TileMap map;
    public Player player;
    
    public TileMapState(String mapFile) {
       map = new TileMap(mapFile);
       map.owner = this;
    }

    public void update(GameContainer gc, int delta) {
    	control(gc, delta, gc.getInput());
        super.update(gc, delta);
    }
    
    private void control(GameContainer gc, int delta, Input input) {
    	if (input.isKeyPressed(Input.KEY_ESCAPE))
    		GameStateManager.states.add(new PauseMenu(this));
    }
    
    public void collision(GameContainer gc, int delta) {
    	super.collision(gc, delta);
        for (Entity e : entities) {
	        Point tile = map.collides(e);
	        if (tile != null) {
	        	e.onCollision();
	        	map.onCollision(e, tile);
	        }
        }
    }
    
    public TileMap getMap() {
    	return map;
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	map.draw(gc, g);
    	super.draw(gc, g);
    }
}
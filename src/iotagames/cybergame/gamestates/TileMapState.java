package iotagames.cybergame.gamestates;

import java.awt.Point;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.utilities.TileMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class TileMapState extends GameState {
    protected TileMap map;
    
    public TileMapState(String mapFile) {
       map = new TileMap(mapFile);
    }

    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
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
    
    public void draw(GameContainer gc, Graphics g) {
    	map.draw(gc, g);
    	super.draw(gc, g);
    }
}
package iotagames.cybergame.gamestates;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.utilities.Camera;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;  

public class GameState {
	public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> toDelete = new ArrayList<Entity>();
    public Camera camera;
    public boolean checkCollisions = true;
    
    public void update(GameContainer gc, int delta) {
        for (int i=0; i<entities.size(); ++i) {
            Entity e = entities.get(i);
            e.update(gc, delta);
            if (e.isDead()) toDelete.add(e);
        }  
        for (Entity e : toDelete)
            entities.remove(e);
        toDelete.clear();
        if (checkCollisions) collision(gc, delta);
    }
    
    public void collision(GameContainer gc, int delta) {
        for (int i=0; i<entities.size(); ++i) {
        	for (int j=i+1; j<entities.size(); ++j) {
        		Entity first = entities.get(i);
        		Entity second = entities.get(j);
        		if (first.collides(second))
        			first.onCollision(second);
        	}
        }
    }
    
    // Don't override
    public void drawing(GameContainer gc, Graphics g) {
        if (camera != null) camera.translate(gc, g);
        draw(gc, g);
        if (camera != null) camera.untranslate(gc, g);
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	for (int i=0; i<entities.size(); ++i)
            entities.get(i).draw(gc, g);
    }
}

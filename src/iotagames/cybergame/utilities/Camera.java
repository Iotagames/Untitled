package iotagames.cybergame.utilities;

import iotagames.cybergame.entities.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
    public Entity targetted;
    public float cameraX;
    public float cameraY;
    public float scale=1.5f;
    public boolean active=true;
    public boolean centered=true;
    
    public Camera() {}
    
    public Camera(Entity targetted) {
    	this.targetted = targetted;
    }

    public Camera(int cameraX, int cameraY) {
    	this.cameraX = cameraX;
    	this.cameraY = cameraY;
    	centered = false;
    }
    
    public void setCenter(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }
    
    public void translate(GameContainer gc, Graphics g) {
    	if (active) {
	        if (targetted != null) {
	            setCenter(targetted.getX(), targetted.getY());
	        }
	        if (centered)
	        	g.translate((-cameraX*scale) + (gc.getWidth()/2), (-cameraY*scale) + (gc.getHeight()/2));
	        else
	        	g.translate(-cameraX*scale, -cameraY*scale);
	        g.scale(scale, scale);
    	}
    }
    
    public void untranslate(GameContainer gc, Graphics g) {
    	if (active) {
    		if (centered)
    			g.translate((cameraX*scale) - (gc.getWidth()/2), (cameraY*scale) - (gc.getHeight()/2));
    		else
    			g.translate(cameraX*scale, cameraY*scale);
    	}
    }
}
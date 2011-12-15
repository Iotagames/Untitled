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
    
    public void setCenter(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }
    
    public void translate(GameContainer gc, Graphics g) {
    	if (active) {
	        if (targetted != null) {
	            setCenter(targetted.getX(), targetted.getY());
	        }
	        g.translate((-cameraX*scale) + (gc.getWidth()/2), (-cameraY*scale) + (gc.getHeight()/2));
	        g.scale(scale, scale);
    	}
    }
    
    public void untranslate(GameContainer gc, Graphics g) {
    	if (active)
    		g.translate((cameraX*scale) - (gc.getWidth()/2), (cameraY*scale) - (gc.getHeight()/2));
    }
}
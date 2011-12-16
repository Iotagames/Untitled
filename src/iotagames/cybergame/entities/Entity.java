package iotagames.cybergame.entities;

import iotagames.cybergame.events.CollisionEvent;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.utilities.ImageManager;
import iotagames.cybergame.utilities.PolarVector;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image; 
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;  

public class Entity
{
    protected static ImageManager images = ImageManager.getInstance();
    protected String imageRef="";
    protected Image image;
    protected boolean dead = false;
    public float scale=1;
    protected float xpos, ypos;
    public float xSpeed = 0;
    public float ySpeed = 0;
    boolean visible = true;
    public float hitboxScale = 0.75f;
    public ArrayList<CollisionEvent> collision = new ArrayList<CollisionEvent>();
    public ArrayList<EntityEvent> death = new ArrayList<EntityEvent>();
    
    public Entity(String imageRef, float xPos, float yPos) {
        loadImage(imageRef);
        this.xpos = xPos;
        this.ypos = yPos;
    }
    
    public Entity(String imageRef) {
    	this(imageRef, 0, 0);
    }
    
    public void loadImage(String imageRef) {
        this.imageRef = imageRef;
        image = images.get(imageRef);
        image.setCenterOfRotation(getWidth()/2, getHeight()/2);
    }
    
    public boolean getVisible()
    {
        return visible;
    }
    
    public float getX()
    {
        return xpos;
    }
    
    public float getY()
    {
        return ypos;
    }
    
    public Point tile(int tileSize) {
    	return new Point((int)(xpos/tileSize), (int)(ypos/tileSize));
    }
    
    public Vector2f getCenter() {
        return new Vector2f(xpos+(getWidth()/2f), ypos+(getHeight()/2f));
    }
    
    public Vector2f getPosition() {
    	return new Vector2f(xpos, ypos);
    }
    
    public void setPosition(float x, float y) {
    	xpos = x;
    	ypos = y;
    }
    
    public void setPosition(Vector2f pos) {
    	setPosition(pos.x, pos.y);
    }
    
    public void setSpeed(float xs, float ys) {
    	xSpeed = xs;
    	ySpeed = ys;
    }
    
    public void setSpeed(Vector2f speed) {
    	setSpeed(speed.x, speed.y);
    }
    
    public void setSpeed(PolarVector speed) {
    	setSpeed(speed.toCart());
    }
    
    public Vector2f getSpeed() {
    	return new Vector2f(xSpeed, ySpeed);
    }
    
    public void move(float x, float y) {
    	xpos += x;
    	ypos += y;
    }
    
    public void move(Vector2f pos) {
    	move(pos.x, pos.y);
    }
    
    public float getWidth() {
        if (image != null) {
            return image.getWidth() * scale;
        } else {
            return 0;
        }
    }
    
    public float getHeight() {
        if (image != null) {
            return image.getHeight() * scale;
        } else {
            return 0;
        }
    }
    
    public Vector2f getSize() {
    	return new Vector2f(getWidth(), getHeight());
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public void die() {
        dead = true;
        onDeath();
    }
    
    public void onDeath() {
    	for (EntityEvent e : death)
    		e.onEvent(this);
    }
    
    public void update(GameContainer gc, int delta) {
        xpos += xSpeed;
        ypos += ySpeed;
    }
    
    public void draw(GameContainer gc, Graphics g) {
        if (visible && image != null) {
            image.draw(xpos, ypos, scale);
        }
    }

    public Shape boundingBox() {
    	float wo = getWidth() * hitboxScale;
    	float ho = getHeight() * hitboxScale;
    	return new Rectangle(getCenter().x - (wo/2f), getCenter().y - (ho/2f), wo, ho);
    }
    
    public boolean collides(Rectangle rect) {
    	return boundingBox().intersects(rect);
    }
    
    public boolean collides(Entity other) {
    	if (other instanceof Bullet && !((Bullet) other).passesFilter(this))
    		return false;
    	return boundingBox().intersects(other.boundingBox());
    }
    
    // default cases
    public void onCollision() {
    	
    }
    
    // entity vs entity cases
    public void onCollision(Entity other) {
    	for (CollisionEvent e : collision)
    		e.onCollision(this, other);
    	for (CollisionEvent e : other.collision)
    		e.onCollision(other, this);
    	onCollision();
    	other.onCollision();
    }
    
    public String toString() {
    	return imageRef;
    }
}

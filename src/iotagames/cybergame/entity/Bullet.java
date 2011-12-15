package iotagames.cybergame.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.util.FastTrig;

public class Bullet extends Entity
{
    public float speed=0;
    public float angle=0;
    private int time=0;
    public int lifespan=500;
    public Entity owner;
    
    public Bullet(Entity owner, String imageRef, float xPos, float yPos, float speed, float angle, int lifespan)
    {
        super(imageRef, xPos, yPos);
        this.owner = owner;
        this.speed = speed*4;
        this.angle = angle;
        this.lifespan = lifespan;
        if (this.owner != null) {
            xpos += owner.getCenter().x - (getWidth()/2f);
            ypos += owner.getCenter().y - (getHeight()/2f);
        }
        visible = true;     
        hitboxScale = 0.75f;
    }
    
    public void update(GameContainer gc, int delta) {
        ++time;
        if (time>=lifespan) die();
        float radAngle = (float) Math.toRadians(angle+90);
        image.setRotation(angle+90);
        xpos += (float)(speed * FastTrig.cos(radAngle));
        ypos += (float)(speed * FastTrig.sin(radAngle));
    }
    
    public boolean passesFilter(Entity other) {
    	if (other instanceof Bullet)
    		return false;
    	if (other == owner)
    		return false;
    	if (other instanceof Bullet && ((Bullet)other).owner == owner)
    		return false;
    	return true;
    }
    
    public boolean collides(Entity other) {
    	if (!passesFilter(other))
    		return false;
    	return super.collides(other);
    }
    
    public void onCollision() {
    	die();
    }
    
    public void onCollision(Entity other) {
    	if (!(other instanceof Bullet)) {
    		other.onCollision(this);
    	}
    }
}

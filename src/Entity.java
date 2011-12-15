import java.awt.Point;

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
    float xpos, ypos;
    boolean visible = true;
    protected float hitboxScale = 0.5f;
    
    public Entity(String imageRef, float xPos, float yPos) {
        loadImage(imageRef);
        this.xpos = xPos;
        this.ypos = yPos;
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
    
    public boolean isDead() {
        return dead;
    }
    
    public void die() {
        dead = true;
    }
    
    public void update(GameContainer gc, int delta) { }
    
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
    	return boundingBox().intersects(other.boundingBox());
    }
    
    // default cases
    public void onCollision() {
    	
    }
    
    // entity vs entity cases
    public void onCollision(Entity other) {
    	onCollision();
    }
}

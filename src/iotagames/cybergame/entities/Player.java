package iotagames.cybergame.entities;

import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.GameStateManager;

import org.newdawn.slick.Animation; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image; 
import org.newdawn.slick.Input;  

public class Player extends Entity {
    private Animation sprite, up, down, left, right;
    private int cooldown = 0;
    
    public Player(String imageRef, float xPos, float yPos) {
        super(imageRef, xPos, yPos);
    }
    
    public void loadImage(String imageRef) {
        if (imageRef.equals("player")) {
            this.imageRef = imageRef;
            createAnimation();
        } else {
        	super.loadImage(imageRef);
        }
    }
    
    public void createAnimation() {
        Image [] movementUp = {images.get("player_ud2"), images.get("player_ud")};
        Image [] movementDown = {images.get("player_ud"), images.get("player_ud2")};
        Image [] movementLeft = {images.get("player_left"), images.get("player_left2")};
        Image [] movementRight = {images.get("player_right"), images.get("player_right2")};
        int [] duration = {150, 150};
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        sprite = right;
    }
    
    public void fire(Bullet bullet) {
        GameState current = GameStateManager.currentState();
        current.entities.add(bullet);
    }
    
    public void update(GameContainer gc, int delta) {
        control(gc.getInput(), delta);
        super.update(gc, delta);
        recover();
        if (!(xSpeed == 0 && ySpeed == 0))
        	updateSprite(delta);
    }
    
    public void recover() {
    	if (cooldown > 0) --cooldown;
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	if (sprite != null)
    		sprite.draw(xpos, ypos);
    }
    
    public void control(Input input, int delta) {
        float speed = 5;
        if (input.isKeyDown(Input.KEY_LEFT))
            xSpeed = -speed;
        else if (input.isKeyDown(Input.KEY_RIGHT))
            xSpeed = speed;
        else
            xSpeed = 0;

        if (input.isKeyDown(Input.KEY_DOWN))
            ySpeed = +speed;
        else if (input.isKeyDown(Input.KEY_UP))
            ySpeed = -speed;
        else
            ySpeed = 0;
        
        if (input.isKeyDown(Input.KEY_Z)) {
            if(canFire())
            {
            	fire(new Bullet(this, "bullet", xSpeed*2, ySpeed*2, 5, calculateAngle(), 1000));
            	cooldown = 10;
            }
         }
    }
    
    public void updateSprite(int delta) {
    	if (xSpeed < 0) sprite = left;
    	else if (xSpeed > 0) sprite = right;
    	if (ySpeed < 0) sprite = up;
    	else if (ySpeed > 0) sprite = down;
    	if (sprite != null) sprite.update(delta);
    }
    
    public boolean canFire() {
    	return cooldown == 0;
    }
    
    public float calculateAngle() {
    	float angle = 180;
        if (xSpeed == 0 && ySpeed == 0) {
	        if (sprite == left) {
	        	angle = 90;
	        } else if (sprite == right) {
	        	angle = 270;
	        } else if (sprite == up) {
	        	angle = 180;
	        } else {
	        	angle = 0;
	        }
        } else {
        	angle = (float) Math.toDegrees(Math.atan2(ySpeed, xSpeed)) + 270;
        }
        return angle;
    }
    
    public float getWidth() {
        if (sprite != null) {
            return sprite.getWidth() * scale;
        } else {
            return super.getWidth();
        }
    }
    
    public float getHeight() {
        if (sprite != null) {
            return sprite.getHeight() * scale;
        } else {
            return super.getHeight();
        }
    }
    
    public void onCollision() {
    	// simple wall collision
    	xpos -= xSpeed;
    	ypos -= ySpeed;
    }
} 
package iotagames.cybergame.entity;

import iotagames.cybergame.gamestate.GameState;
import iotagames.cybergame.gamestate.GameStateManager;

import org.newdawn.slick.Animation; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image; 
import org.newdawn.slick.Input;  


public class Player extends Entity {
    public float xSpeed = 0;
    public float ySpeed = 0;
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
        Image [] movementUp = {images.get("data/player_ud2.png"), images.get("data/player_ud.png")};
        Image [] movementDown = {images.get("data/player_ud.png"), images.get("data/player_ud2.png")};
        Image [] movementLeft = {images.get("data/player_left.png"), images.get("data/player_left2.png")};
        Image [] movementRight = {images.get("data/player_right.png"), images.get("data/player_right2.png")};
        int [] duration = {150, 150};
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        sprite = right;
    }
    
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        control(gc.getInput(), delta);   
        xpos += xSpeed;
        ypos += ySpeed;
        recover();
    }
    
    public void recover() {
    	if (cooldown > 0)
    		--cooldown;
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	if (sprite != null)
    		sprite.draw(xpos, ypos);
    }
    
    public void fire(Bullet bullet) {
        GameState current = GameStateManager.currentState();
        current.entities.add(bullet);
    }
    
    public void control(Input input, int delta) {
        float speed = 5;
        float angle = 180;
        if (input.isKeyDown(Input.KEY_LEFT)) {
            xSpeed = -speed;
            sprite = left;
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            xSpeed = speed;
            sprite = right;
        } else {
            xSpeed = 0;
        }

        if (input.isKeyDown(Input.KEY_DOWN)) {
            ySpeed = +speed;
            sprite = down;
        } else if (input.isKeyDown(Input.KEY_UP)) {
            ySpeed = -speed;
            sprite = up;
        } else {
            ySpeed = 0;
        }
        
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
        
        float bulletSpeed = (float)Math.sqrt((ySpeed*ySpeed)+(xSpeed*xSpeed)) + 5;
        
        if (input.isKeyDown(Input.KEY_Z)) {
            if(cooldown == 0)
            {
            	fire(new Bullet(this, "data/bullet.png", 0, 0, bulletSpeed, angle, 1000));
            	cooldown = 10;
            }
         }
        
        if (!(xSpeed == 0 && ySpeed == 0))
        	sprite.update(delta);
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
    //I
} 
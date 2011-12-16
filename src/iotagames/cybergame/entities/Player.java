package iotagames.cybergame.entities;

import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.GameStateManager;
import iotagames.cybergame.utilities.AnimationMap;

import org.newdawn.slick.Animation; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Input;  

public class Player extends Entity {
    private AnimationMap animations;
    private int cooldown = 0;
    private float bulletAngle = 270;
    
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
    	animations = new AnimationMap("right");
    	animations.put("up", "player", "ud2, ud");
    	animations.put("down", "player", "ud, ud2");
    	animations.put("left", "player", "left, left2");
    	animations.put("right", "player", "right, right2");
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
    	animations.draw(xpos, ypos, getWidth(), getHeight());
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
            ySpeed = speed;
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
    	if (xSpeed < 0) animations.set("left");
    	else if (xSpeed > 0) animations.set("right");
    	if (ySpeed < 0) animations.set("up");
    	else if (ySpeed > 0) animations.set("down");
    	animations.update(delta);
    }
    
    public boolean canFire() {
    	return cooldown == 0;
    }
    
    public float calculateAngle() {
        if (!(xSpeed == 0 && ySpeed == 0))
        	bulletAngle = (float) Math.toDegrees(Math.atan2(ySpeed, xSpeed)) + 270;
        return bulletAngle;
    }
    
    public float getWidth() {
    	Animation sprite = animations.get();
        if (sprite != null) {
            return sprite.getWidth() * scale;
        } else {
            return super.getWidth();
        }
    }
    
    public float getHeight() {
    	Animation sprite = animations.get();
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
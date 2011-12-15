package iotagames.cybergame.entity;



public class Enemy extends Entity {
	public Enemy(String imageRef, float xPos, float yPos) {
		super(imageRef, xPos, yPos);
	}
	
	public void onCollision(Entity other) {
		if (other instanceof Bullet) {
			System.out.println("hit by a bullet, testing a change");
			other.die();
			die();
		}
	}
}
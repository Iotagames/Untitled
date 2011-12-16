package iotagames.cybergame.utilities;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FastTrig;

public class PolarVector {
	public float radius=0f;
	public float angle=0f;
	
	public PolarVector(float radius, float angle) {
		this.radius = radius;
		this.angle = angle;
	}
	
	public PolarVector clone() {
		return new PolarVector(radius, angle);
	}
	
	public static PolarVector fromCart(Vector2f cart) {
		return fromCart(cart.x, cart.y);
	}
	
	public static PolarVector fromCart(float x, float y) {
		return new PolarVector((float)Math.sqrt((y*y)+(x*x)), (float)Math.toDegrees(Math.atan2(y,x)));
	}
	
	public static Vector2f toCart(PolarVector polar) {
		return toCart(polar.radius, polar.angle);
	}
	
	public static Vector2f toCart(float radius, float angle) {
		float radAngle = (float) Math.toRadians(angle);
		return new Vector2f((float)(radius*FastTrig.cos(radAngle)), (float)(radius*FastTrig.sin(radAngle)));
	}
	
	public Vector2f toCart() {
		return toCart(radius, angle);
	}
}
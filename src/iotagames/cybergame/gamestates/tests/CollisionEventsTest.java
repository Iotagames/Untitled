package iotagames.cybergame.gamestates.tests;

import java.awt.Point;
import java.text.DateFormat;
import java.util.Date;

import org.newdawn.slick.GameContainer;
import iotagames.cybergame.entities.Enemy;
import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.entities.Player;
import iotagames.cybergame.events.CollisionEvent;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.events.TileMapEvent;
import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.TileMapState;
import iotagames.cybergame.ui.Text;
import iotagames.cybergame.ui.TextField;
import iotagames.cybergame.utilities.TileMap;

public class CollisionEventsTest extends TileMapState {
    Player guy = new Player("player", 300, 300);
    Enemy enemy = new Enemy("bug2", 400, 300);
    Text info = new Text("", 10, 50);
    Text debug = new TextField("", 10, 550, 600, 100);
    boolean testDone = false;
    
	public CollisionEventsTest() {
		super("map2");
		entities.clear();
		entities.add(guy);
		entities.add(enemy);
		
		foreground = new GameState();
		foreground.entities.add(info);
		foreground.entities.add(debug);
		
		addTests();
	}
	
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		info.setText("Position: " + guy.getX() + "," + guy.getY());
	}
	
	private void addTests() {
		map.collision.add(new TileMapEvent() {
			public void onEvent(TileMap map, Entity entity, Point tile) {
	   			print(entity + " has collided with the tile (" + tile.x + "," + tile.y + ") at " + getTime() + "!");
			}
		});
        guy.collision.add(new CollisionEvent() {
			public void onCollision(Entity first, Entity second) {
	   			print(first + " has collided with " + second + " at " + getTime() + "!");
			}
        });
        enemy.collision.add(new CollisionEvent() {
	   		public void onCollision(Entity first, Entity second) {
	   			testDone = true;
	   		}
        });
        enemy.behaviour.add(new EntityEvent() {
	   		public void onEvent(Entity entity) {
	   			if (!testDone) entity.xSpeed = -3;
	   			else entity.xSpeed = 0;
	   		}
        });
        enemy.death.add(new EntityEvent() {
	   		public void onEvent(Entity entity) {
	   			print(entity + " has died at " + getTime() + "!");
	   		}
        });
	}
	
	public void print(String text) {
		debug.setText(text);
	}
	
	public String getTime() {
		return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date());
	}
}

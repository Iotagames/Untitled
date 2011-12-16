package iotagames.cybergame.gamestates.tests;

import java.awt.Point;
import java.text.DateFormat;
import java.util.Date;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

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
    Enemy enemy = new Enemy("bug2", 400, 300);
    Text info = new Text("", 10, 50);
    Text debug = new TextField("", 10, 550, 600, 100);
    boolean testDone = false;
    
	public CollisionEventsTest() {
		super("map2");
		entities.clear();
	    player = new Player("player", 300, 300);
		entities.add(player);
		entities.add(enemy);
		addEnemies(10);
		foreground = new GameState();
		foreground.entities.add(info);
		foreground.entities.add(debug);
		
		addTests();
	}
	
	public void addEnemies(int num) {
		for (int i=0; i<num; ++i) {
			Vector2f pos = new Vector2f(
					(float)(Math.random() * map.getTileSize() * (map.getWidthInTiles()-2) - map.getTileSize()), 
					(float)(Math.random() * map.getTileSize() * (map.getHeightInTiles()-2) - map.getTileSize()));
			
			if (!map.blocked(null, (int)(pos.x/map.getTileSize()), (int)(pos.y/map.getTileSize()))) {
				entities.add(new Enemy("bug2", pos.x, pos.y));
			}
		}
	}
	
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		info.setText("Position: " + player.getX() + "," + player.getY());
	}
	
	private void addTests() {
		map.collision.add(new TileMapEvent() {
			public void onEvent(TileMap map, Entity entity, Point tile) {
	   			print(entity + " has collided with the tile (" + tile.x + "," + tile.y + ") at " + getTime() + "!");
			}
		});
        player.collision.add(new CollisionEvent() {
			public void onCollision(Entity first, Entity second) {
	   			print(first + " has collided with " + second + " at " + getTime() + "!");
			}
        });
        enemy.collision.add(new CollisionEvent() {
	   		public void onCollision(Entity first, Entity second) {
	   			testDone = true;
	   		}
        });
        /*enemy.behaviour.add(new EntityEvent() {
	   		public void onEvent(Entity entity) {
	   			if (!testDone) entity.xSpeed = -3;
	   			else entity.xSpeed = 0;
	   		}
        });*/
        for (Entity e : entities) {
	        e.death.add(new EntityEvent() {
		   		public void onEvent(Entity entity) {
		   			print(entity + " has died at " + getTime() + "!");
		   		}
	        });
        }
	}
	
	public void print(String text) {
		debug.setText(text);
	}
	
	public String getTime() {
		return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date());
	}
}

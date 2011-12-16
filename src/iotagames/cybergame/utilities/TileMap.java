package iotagames.cybergame.utilities;

import java.awt.Point;
import java.util.ArrayList;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.events.TileMapEvent;
import iotagames.cybergame.gamestates.TileMapState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;


public class TileMap implements TileBasedMap {
    private TiledMap map;
    private boolean[][] blocked;
    private int size = 32;
    public static String directory = "data/";
    public ArrayList<TileMapEvent> collision = new ArrayList<TileMapEvent>();
    public TileMapState owner;
    
    public TileMap(String file) {
    	try {
			create(file);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }
    
	public TileMap(String file, int size) {
		this(file);
		this.size = size;
	}
	
	public void create(String file) throws SlickException {
		if (file.charAt(0) != '/')
			file = directory + file;
		if (!file.contains("."))
			file += ".tmx";
       map = new TiledMap(file);
       // build a collision map based on tile properties in the TileD map
       blocked = new boolean[map.getWidth()][map.getHeight()];

       for (int xAxis=0;xAxis<map.getWidth(); xAxis++)
       {
            for (int yAxis=0;yAxis<map.getHeight(); yAxis++)
            {
                int tileID = map.getTileId(xAxis, yAxis, 0);
                String value = map.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value))
                {
                    blocked[xAxis][yAxis] = true;
                }
            }
        }
	}
	
	public void draw(GameContainer gc, Graphics g) {
		map.render(0, 0);
	}
	
    public boolean collides(float x, float y)
    {
    	Point tile = tile(x, y);
    	if (tile.x > map.getHeight() || tile.y > map.getWidth() || tile.x < 0 || tile.y < 0)
    		return false;
        return blocked[tile.x][tile.y];
    }
	
    public Point collisionTile(float x, float y)
    {
    	Point tile = tile(x, y);
    	if (tile.x > map.getHeight() || tile.y > map.getWidth() || tile.x < 0 || tile.y < 0)
    		return null;
    	return (blocked[tile.x][tile.y]) ? tile : null;
    }
    
    public Point tile(float x, float y) {
        return new Point((int)(x/size), (int)(y/size));
    }
    
    public Point collides(Entity entity)
    {
    	Rectangle rect = (Rectangle) entity.boundingBox();
    	float x = rect.getX();
    	float y = rect.getY();
    	float w = rect.getWidth();
    	float h = rect.getHeight();
    	Point tile=null;
    	tile = collisionTile(x,y);
    	if (tile == null) tile = collisionTile(x+w,y);
    	if (tile == null) tile = collisionTile(x,y+h);
    	if (tile == null) tile = collisionTile(x+w,y+h);
    	return tile;
    }
    
    public void onCollision(Entity entity, Point tile) {
    	for (TileMapEvent e : collision)
    		e.onEvent(this, entity, tile);
    }
    
    public TiledMap getMap() {
    	return map;
    }

	public boolean blocked(PathFindingContext context, int x, int y) {
    	if (x > map.getHeight() || y > map.getWidth() || x < 0 || y < 0)
    		return false;
		if (owner != null) {
			for (Entity e : owner.entities) {
				Point tile = e.tile(size);
				Point dist = new Point(Math.abs(tile.x-x), Math.abs(tile.y-y));
				if (dist.x * dist.y == 1)
					return true;
			}
		}
		return blocked[x][y];
	}

	public float getCost(PathFindingContext context, int x, int y) {
		return 1;
	}

	public int getHeightInTiles() {
		return map.getHeight();
	}

	public int getWidthInTiles() {
		return map.getWidth();
	}

	public void pathFinderVisited(int x, int y) {
		
	}

	public int getTileSize() {
		return size;
	}
}

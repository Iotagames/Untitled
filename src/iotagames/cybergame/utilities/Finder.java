package iotagames.cybergame.utilities;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.GameStateManager;
import iotagames.cybergame.gamestates.TileMapState;

import java.awt.Point;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class Finder {
	public TileMap map;
	public PathFinder finder;
	
	public Finder(TileMap map) {
		this.map = map;
	}

	public void init() {
		GameState state = GameStateManager.currentState();
		if (state instanceof TileMapState) {
			TileMapState mapState = (TileMapState) state;
			map = mapState.getMap();
			finder = new AStarPathFinder(map, 50, true);
		}
	}
	
	public Path findPath(Entity entity, Entity target, float speed) {
		if (map != null) {
			Point targetTile = target.tile(map.getTileSize());
			Point tile = entity.tile(map.getTileSize());
			if (inMapBounds(map,tile) && inMapBounds(map,targetTile)) {
				if (finder == null) init();
				Path path = finder.findPath((Mover)entity, tile.x, tile.y, targetTile.x, targetTile.y);
				if (path != null && path.getLength() > 1 && speed != 0) {
					Path.Step step = path.getStep(1);
					int nextX = step.getX() - tile.x;
					int nextY = step.getY() - tile.y;
					PolarVector pv = PolarVector.fromCart(nextX, nextY);
					pv.radius = speed;
					entity.setSpeed(pv);
					return path;
				}
			}
		} else {
			init();
		}
		return null;
	}
	
	private boolean inMapBounds(TileMap map, Point tile) {
		return tile.x > 0 && tile.y > 0 && tile.x < map.getWidthInTiles() && tile.y < map.getHeightInTiles(); 
	}
}

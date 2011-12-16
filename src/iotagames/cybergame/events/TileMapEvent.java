package iotagames.cybergame.events;

import java.awt.Point;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.utilities.TileMap;

public interface TileMapEvent {
	public void onEvent(TileMap map, Entity entity, Point tile);
}

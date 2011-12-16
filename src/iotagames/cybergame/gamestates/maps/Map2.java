package iotagames.cybergame.gamestates.maps;

import iotagames.cybergame.entities.Player;
import iotagames.cybergame.gamestates.TileMapState;
import iotagames.cybergame.utilities.Camera;

public class Map2 extends TileMapState {
	public Map2() {
		super("map2");
	    player = new Player("player", 300, 300);
		entities.add(player);
		camera = new Camera(player);
	}
}
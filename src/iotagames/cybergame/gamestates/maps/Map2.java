package iotagames.cybergame.gamestates.maps;

import iotagames.cybergame.entities.Player;
import iotagames.cybergame.gamestates.TileMapState;
import iotagames.cybergame.utilities.Camera;

public class Map2 extends TileMapState {
    Player guy = new Player("player", 300, 300);
	
	public Map2() {
		super("map2");
		entities.add(guy);
		camera = new Camera(guy);
	}
}

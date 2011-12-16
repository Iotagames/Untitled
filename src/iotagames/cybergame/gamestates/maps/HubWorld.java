package iotagames.cybergame.gamestates.maps;

import iotagames.cybergame.entities.Enemy;
import iotagames.cybergame.entities.NPC;
import iotagames.cybergame.entities.Player;
import iotagames.cybergame.gamestates.TileMapState;
import iotagames.cybergame.utilities.Camera;

public class HubWorld extends TileMapState {
    Enemy enemy = new Enemy("bug2", 400, 400);
    NPC npc = new NPC("npc_ud", 256, 75);
    NPC npca = new NPC("npc_ud", 224, 75);
    NPC npcb = new NPC("npc_left", 96, 256);
    
	public HubWorld() {
		super("hubworld");
	    player = new Player("player", 300, 300);
		entities.add(player);
		entities.add(enemy);
		entities.add(npc);
		entities.add(npca);
		entities.add(npcb);
		camera = new Camera(player);
	}
}
package iotagames.cybergame.gamestates;

import iotagames.cybergame.entities.Enemy;
import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.entities.NPC;
import iotagames.cybergame.entities.Player;
import iotagames.cybergame.utilities.TileMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ShmupGameState extends GameState {
    Player guy = new Player("player", 300, 300);
    Enemy enemy = new Enemy("bug2", 400, 400);
    NPC npc = new NPC("npc_ud",256,75);
    NPC npca = new NPC("npc_ud",224,75);
    NPC npcb = new NPC("npc_left",96,256);
    TileMap map;
    
    public ShmupGameState() { this("hubworld"); }
    
    public ShmupGameState(String map) {
       this.map = new TileMap(map);
       entities.add(guy);
       entities.add(enemy);
       entities.add(npc);
       entities.add(npca);
       entities.add(npcb);
       camera.targetted = guy;
    }

    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
    }
    
    public void collision(GameContainer gc, int delta) {
    	super.collision(gc, delta);
        for (Entity e : entities)
	        if (map.collides(e))
	        	e.onCollision();
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	map.draw(gc, g);
    	super.draw(gc, g);
    }
}
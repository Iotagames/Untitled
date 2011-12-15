import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


// oh right, want to add another guy for entity vs entity collision?
// A non-moving guy for now is fine, you can do AI later.
// sure!
public class ShmupGameState extends GameState {
    Player guy = new Player("player", 300, 300);
    Enemy enemy = new Enemy("data/bug2.png", 400, 400);
    NPC npc = new NPC("data/npc_ud.png",256,75);
    NPC npca = new NPC("data/npc_ud.png",224,75);
    NPC npcb = new NPC("data/npc_left.png",96,256);
    TileMap map = new TileMap("data/hubworld.tmx");
    
    public ShmupGameState() throws SlickException {
       entities.add(guy);
       entities.add(enemy);
       entities.add(npc);
       entities.add(npca);
       entities.add(npcb);
       camera.targetted = guy;
    }

    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        
        // simple bubble check for now
        for (int i=0; i<entities.size(); ++i) {
        	for (int j=i+1; j<entities.size(); ++j) {
        		Entity first = entities.get(i);
        		Entity second = entities.get(j);
        		if (first.collides(second))
        			first.onCollision(second);
        	}
        }
        
        for (Entity e : entities) {
	        if (map.collides(e)) {
	        	e.onCollision();
	        }
        }
        // do extra game logic or collision logic, etc...
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	map.draw(gc, g);
    	super.draw(gc, g);
    }
}
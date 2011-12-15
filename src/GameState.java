import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;  

public class GameState {
	public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> toDelete = new ArrayList<Entity>();
    
    public void update(GameContainer gc, int delta) {
        for (int i=0; i<entities.size(); ++i) {
            Entity e = entities.get(i);
            e.update(gc, delta);
            if (e.isDead()) toDelete.add(e);
        }  
        for (Entity e : toDelete)
            entities.remove(e);
        toDelete.clear();
    }
    
    Camera camera = new Camera();
    
    // Don't override
    public void drawing(GameContainer gc, Graphics g) {
        camera.translate(gc, g);
        draw(gc, g);
        camera.untranslate(gc, g);
    }
    
    public void draw(GameContainer gc, Graphics g) {
    	for (int i=0; i<entities.size(); ++i)
            entities.get(i).draw(gc, g);
    }
}

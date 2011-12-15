import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class TileMap {
    private TiledMap map;
    //collision map
    private boolean[][] blocked;
    private int size = 32;
    
    public TileMap(String file) throws SlickException {
    	create(file);
    }
    
	public TileMap(String file, int size) throws SlickException {
		this(file);
		this.size = size;
	}
	
	public void create(String file) throws SlickException {
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
        int xBlock = (int)x / size;
        int yBlock = (int)y / size;
        return blocked[xBlock][yBlock];
    }
    
    public boolean collides(Entity entity)
    {
    	float x = entity.getX();
    	float y = entity.getY();
    	float w = entity.getWidth();
    	float h = entity.getHeight();
    	return collides(x,y) || collides(x+w,y) || collides(x,y+h) || collides(x+w,y+h);
    }
}

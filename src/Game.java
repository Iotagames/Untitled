import org.newdawn.slick.Animation; 
import org.newdawn.slick.AppGameContainer; 
import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image; 
import org.newdawn.slick.Input; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.tiled.TiledMap;  


public class Game extends BasicGame
{
     private TiledMap testMap;
     private Animation sprite, up, down, left, right;
     //Player spawn point
     private float x = 256f, y = 256f;

     //collision map
     private boolean[][] blocked;
     private static final int SIZE = 32;

     public Game()
     {
         super("Crazy");
     }

     public static void main(String [] arguments)
     {
         try
         {
             AppGameContainer app = new AppGameContainer(new ShmupGame());
             app.setDisplayMode(704, 576, false);
             app.setVSync(true);
             app.start();
         }
         catch (SlickException e)
         {
             e.printStackTrace();
         }
     }

     @Override
     public void init(GameContainer container) throws SlickException
     {
         Image [] movementUp = {new Image("data/player_ud2.png"), new Image("data/player_ud.png")};
         Image [] movementDown = {new Image("data/player_ud.png"), new Image("data/player_ud2.png")};
         Image [] movementLeft = {new Image("data/player_left.png"), new Image("data/player_left2.png")};
         Image [] movementRight = {new Image("data/player_right.png"), new Image("data/player_right2.png")};
         int [] duration = {150, 150};         testMap = new TiledMap("data/map2.tmx");

          /*
          * false variable means do not auto update the animation.
          * By setting it to false animation will update only when
          * the user presses a key.
          */
         up = new Animation(movementUp, duration, false);
         down = new Animation(movementDown, duration, false);
         left = new Animation(movementLeft, duration, false);
         right = new Animation(movementRight, duration, false);

         // Original orientation of the sprite. It will look right.
         sprite = right;

         // build a collision map based on tile properties in the TileD map
         blocked = new boolean[testMap.getWidth()][testMap.getHeight()];

        for (int xAxis=0;xAxis<testMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<testMap.getHeight(); yAxis++)
             {
                 int tileID = testMap.getTileId(xAxis, yAxis, 0);
                 String value = testMap.getTileProperty(tileID, "blocked", "false");
                 if ("true".equals(value))
                 {
                     blocked[xAxis][yAxis] = true;
                 }
             }
         }
     }

     @Override
     public void update(GameContainer container, int delta) throws SlickException
     {
         Input input = container.getInput();
         if (input.isKeyDown(Input.KEY_UP))
         {
             sprite = up;
             if (!isBlocked(x, y - delta * 0.1f))
             {
                 sprite.update(delta);
                 // The lower the delta the slowest the sprite will animate.
                 y -= delta * 0.1f;
             }
         }
         else if (input.isKeyDown(Input.KEY_DOWN))
         {
             sprite = down;
             if (!isBlocked(x, y + delta * 0.1f))
             {
                 sprite.update(delta);
                 y += delta * 0.1f;
             }
         }
         else if (input.isKeyDown(Input.KEY_LEFT))
         {
             sprite = left;
             if (!isBlocked(x - delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x -= delta * 0.1f;
             }
         }
         else if (input.isKeyDown(Input.KEY_RIGHT))
         {
             sprite = right;
             if (!isBlocked(x + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta * 0.1f;
             }
         }
         
     }

     public void render(GameContainer container, Graphics g) throws SlickException
     {
    	 testMap.render(0, 0);
    	 sprite.draw((int)x, (int)y);
     }

     private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return blocked[xBlock][yBlock];
     }
 } 
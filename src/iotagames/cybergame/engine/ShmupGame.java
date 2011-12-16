package iotagames.cybergame.engine;

import iotagames.cybergame.gamestates.*;
import iotagames.cybergame.utilities.FontManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

// init calls the entrypoint state, if we want to change it, do it here

public class ShmupGame extends BasicGame {
	
    public void init(GameContainer container) throws SlickException
    {
        Font trebuc = FontManager.put("trebuc_24_b", "trebuc", 24, true, false);
        container.setDefaultFont(trebuc);
        GameStateManager.init(new MainMenu(), container);
    }
    
    public ShmupGame() throws SlickException
    {
        super("Crazy");
    }

    public static void main(String [] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new ShmupGame());
            app.setDisplayMode(704, 600, false);
            app.setVSync(true);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    public void update(GameContainer container, int delta) throws SlickException
    {
        GameStateManager.updateCurrent(container, delta);
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
        GameStateManager.drawCurrent(container, g);
    }
}

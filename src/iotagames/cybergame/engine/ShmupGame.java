package iotagames.cybergame.engine;

import iotagames.cybergame.gamestate.GameStateManager;
import iotagames.cybergame.gamestate.ShmupGameState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

// init calls the entrypoint state, if we want to change it, do it here

public class ShmupGame extends BasicGame {
    public ShmupGame()
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

    public void init(GameContainer container) throws SlickException
    {
        GameStateManager.init(new ShmupGameState());
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

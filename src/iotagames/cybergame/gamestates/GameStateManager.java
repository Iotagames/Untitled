package iotagames.cybergame.gamestates;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;  


public class GameStateManager {
    public static ArrayList<GameState> states = new ArrayList<GameState>();
    private static GameState entryPoint;
	private static GameContainer container = null;
    
    public static void init(GameState entryPoint, GameContainer gc) {
    	GameStateManager.entryPoint = entryPoint;
        container = gc;
        states.add(entryPoint);
    }
    
    public static GameState currentState() {
    	if (states.size() == 0)
    		states.add(GameStateManager.entryPoint);
        return states.get(states.size()-1);
    }
    
    public static void updateCurrent(GameContainer gc, int delta) {
        currentState().update(gc, delta);
    }
    
    public static void drawCurrent(GameContainer gc, Graphics g) {
        currentState().drawing(gc, g);
        gc.getInput().clearKeyPressedRecord();
        gc.getInput().clearMousePressedRecord();
    }
	
	public static void toMainMenu() {
		while (!(currentState() instanceof MainMenu))
			back();
	}
	
	public static void back() {
		states.remove(states.size()-1);
	}

	public static void exit() {
		if (container != null) {
			container.exit();
		}
	}
}

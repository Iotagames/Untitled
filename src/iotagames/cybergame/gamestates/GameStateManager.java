package iotagames.cybergame.gamestates;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;  


public class GameStateManager {
    public static ArrayList<GameState> states = new ArrayList<GameState>();
    private static GameState entryPoint;
    
    public static void init(GameState entryPoint) {
    	GameStateManager.entryPoint = entryPoint;
        states.add(entryPoint);
    }
    
    public static GameState currentState() {
    	if (states.size() == 0)
    		return GameStateManager.entryPoint;
        return states.get(states.size()-1);
    }
    
    public static void updateCurrent(GameContainer gc, int delta) {
        currentState().update(gc, delta);
    }
    
    public static void drawCurrent(GameContainer gc, Graphics g) {
        currentState().drawing(gc, g);
    }
}

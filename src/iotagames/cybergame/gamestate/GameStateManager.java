package iotagames.cybergame.gamestate;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;  


public class GameStateManager {
    public static ArrayList<GameState> states = new ArrayList<GameState>();
    
    public static void init(GameState entryPoint) {
        states.add(entryPoint);
    }
    
    public static GameState currentState() {
        return states.get(states.size()-1);
    }
    
    public static void updateCurrent(GameContainer gc, int delta) {
        currentState().update(gc, delta);
    }
    
    public static void drawCurrent(GameContainer gc, Graphics g) {
        currentState().drawing(gc, g);
    }
}

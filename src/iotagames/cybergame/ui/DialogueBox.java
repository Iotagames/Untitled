package iotagames.cybergame.ui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import iotagames.cybergame.gamestates.GameState;
import iotagames.cybergame.gamestates.OverlayState;

// Should also have a way to put a box under the text.
// Use a Field for this, draw it before the current text block.

public class DialogueBox extends OverlayState {
    int current = 0;
    public ArrayList<TextField> textBlocks = new ArrayList<TextField>();
	private Field textbg;
	
    public DialogueBox(GameState lower) {
        super(lower);
        textbg = new Field("textbg.png",100,400,500,200);
    }
    
    public DialogueBox(GameState lower, String[] dialogue) {
        this(lower);
        add(dialogue);
    }
    
    public void add(String[] dialogue) {
        for (int i=0; i<dialogue.length; ++i)
            textBlocks.add(new TextField(dialogue[i],100,400,500,200));
    }
    
    public void draw(GameContainer gc, Graphics g) {
        super.draw(gc, g);
        textbg.draw(gc, g);
        if (current < textBlocks.size()) textBlocks.get(current).draw(gc, g);
    }
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        control(gc, delta, gc.getInput());
        if (current < textBlocks.size()) {
            textBlocks.get(current).update(gc, delta);
        } else {
            back();
        }
    }
    
    public void control(GameContainer gc, int delta, Input input) {
        if (input.isKeyPressed(Input.KEY_Z)) {
            ++current;
        }
    }
}
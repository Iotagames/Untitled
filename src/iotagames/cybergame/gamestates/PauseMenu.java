package iotagames.cybergame.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.ui.Button;

public class PauseMenu extends OverlayState {
	private GameContainer container;
	
	public PauseMenu(GameState lower) {
		super(lower);
		
		Button cont = new Button("button", "Continue", 50, 400);
		cont.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				back();
			}
		});

		Button menu = new Button("button", "Return to Menu", 50, 450);
		menu.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				menu();
			}
		});

		Button exit = new Button("button", "Exit Game", 50, 500);
		exit.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				exitGame();
			}
		});
		
		entities.add(cont);
		entities.add(menu);
		entities.add(exit);
	}
	
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		Input input = gc.getInput();
		if (container == null) container = gc;
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			back();
	}
}

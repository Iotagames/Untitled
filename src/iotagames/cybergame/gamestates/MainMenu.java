package iotagames.cybergame.gamestates;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.ui.Button;

public class MainMenu extends GameState {
	public MainMenu() {
		camera.active = false;
		
		Button map1 = new Button("button", "Hub World", 100, 100);
		map1.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				GameStateManager.states.add(new ShmupGameState("hubworld"));
			}
		});

		Button map2 = new Button("button", "Map 2", 100, 150);
		map2.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				GameStateManager.states.add(new ShmupGameState("map2"));
			}
		});
		
		entities.add(map1);
		entities.add(map2);
	}
}

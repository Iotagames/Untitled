package iotagames.cybergame.gamestates;

import iotagames.cybergame.entities.Entity;
import iotagames.cybergame.events.EntityEvent;
import iotagames.cybergame.gamestates.maps.HubWorld;
import iotagames.cybergame.gamestates.maps.Map2;
import iotagames.cybergame.gamestates.tests.CollisionEventsTest;
import iotagames.cybergame.ui.Button;

public class MainMenu extends GameState {
	public MainMenu() {
		Button map1 = new Button("button", "Hub World", 100, 100);
		map1.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				GameStateManager.states.add(new HubWorld());
			}
		});

		Button map2 = new Button("button", "Map 2", 100, 150);
		map2.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				GameStateManager.states.add(new Map2());
			}
		});

		Button map3 = new Button("button", "Collision Tests", 100, 200);
		map3.mousedown.add(new EntityEvent() {
			public void onEvent(Entity entity) {
				GameStateManager.states.add(new CollisionEventsTest());
			}
		});
		
		entities.add(map1);
		entities.add(map2);
		entities.add(map3);
	}
}

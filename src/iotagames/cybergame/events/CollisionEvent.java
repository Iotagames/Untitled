package iotagames.cybergame.events;

import iotagames.cybergame.entities.Entity;

public interface CollisionEvent {
    public void onCollision(Entity first, Entity second);
}

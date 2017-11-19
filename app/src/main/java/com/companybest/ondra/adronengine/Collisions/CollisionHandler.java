package com.companybest.ondra.adronengine.Collisions;

public interface CollisionHandler {
    /**
     * This event is fired when this CollisionHandler collides with
     * a CollisionBox object in the same CollisionSystem.
     *
     * @param c The CollisionBox object that this CollisionHandler collided with to trigger this event.
     */
    void onCollision(CollisionBox c);
}


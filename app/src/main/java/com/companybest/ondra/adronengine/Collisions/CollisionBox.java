package com.companybest.ondra.adronengine.Collisions;

import com.companybest.ondra.adronengine.Entity.Entity;
import com.companybest.ondra.adronengine.OpenGl.EntityComponents;

public interface CollisionBox  {
    /**
     * Should return the Transformation that is used to define the hit
     * box of this CollisionBox object.
     * @return A Transformation defining a hit box.
     */
    EntityComponents getBoxTransformation();

    /**
     * If this CollisionBox has a CollisionHandler to handle collision events
     * with other Collidables, it should be returned. Otherwise, return null.
     * @return This CollisionBox's CollisionHandler or null.
     */
    CollisionHandler getCollisionHandler();

    /**
     * May be used to return the Entity that this CollisionBox component belongs
     * to if needed. Otherwise, can return null.
     * @return This CollisionBox's parent Entity or null.
     */
    Entity getEntity();
}

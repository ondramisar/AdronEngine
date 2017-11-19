package com.companybest.ondra.adronengine;

import android.util.Log;

import com.companybest.ondra.adronengine.Collisions.CollisionBox;
import com.companybest.ondra.adronengine.Collisions.CollisionHandler;
import com.companybest.ondra.adronengine.Collisions.CollisionSystem;
import com.companybest.ondra.adronengine.Entity.Entity;
import com.companybest.ondra.adronengine.OpenGl.EntityComponents;
import com.companybest.ondra.adronengine.OpenGl.Sprite;
import com.companybest.ondra.adronengine.OpenGl.Texture;

/**
 * Created by Ondra on 08.11.2017.
 */

public class Obsticle extends Sprite implements CollisionHandler, CollisionBox {

    public Obsticle(float x, float y, Texture texture, CollisionSystem collisionSystem) {
        super(x, y, texture);
        collisionSystem.addEntity(this);
    }

    public Obsticle(float x, float y, float width, float height, Texture texture, CollisionSystem collisionSystem) {
        super(x, y, width, height, texture);
        collisionSystem.addEntity(this);
    }

    @Override
    public void onCollision(CollisionBox c) {
        Log.i("usernob", "COLLISION Obsticle");

    }

    @Override
    public EntityComponents getBoxTransformation() {
        return getEntityComponents();
    }

    @Override
    public CollisionHandler getCollisionHandler() {
        return this;
    }

    @Override
    public Entity getEntity() {
        return getOwner();
    }
}

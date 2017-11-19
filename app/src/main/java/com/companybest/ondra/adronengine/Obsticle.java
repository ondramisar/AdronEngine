package com.companybest.ondra.adronengine;

import android.util.Log;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.OpenGl.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;


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

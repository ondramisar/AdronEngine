package com.companybest.ondra.adronengine.Objects;

import android.util.Log;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;


public class Obsticle extends Sprite implements CollisionHandler, CollisionBox {

    public Obsticle(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public Obsticle(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }

    @Override
    public void onCollision(CollisionBox c) {
        Log.i("usernob", "COLLISION Obsticle");

    }

    @Override
    public EntityComponents getEntityComponentsForColision() {
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

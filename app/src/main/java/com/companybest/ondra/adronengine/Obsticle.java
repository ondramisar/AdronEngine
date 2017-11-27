package com.companybest.ondra.adronengine;

import android.util.Log;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.OpenGl.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;


public class Obsticle extends Sprite implements CollisionHandler, CollisionBox {

    public Obsticle(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public Obsticle(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }

    public Obsticle(float x, float y, int rows, int cols, int totalFrames, Engine engine, Texture texture) {
        super(x, y, rows, cols, totalFrames, engine, texture);
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

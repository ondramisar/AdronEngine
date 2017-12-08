package com.companybest.ondra.adronengine.Objects;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;

public class CrossyObsticle extends Sprite implements CollisionHandler,CollisionBox {

    public CrossyObsticle(float x, float y, float width, float height, Engine engine, Texture texture, CollisionSystem collisionSystem) {
        super(x, y, width, height, engine, texture);
        collisionSystem.addEntity(this);
    }

    public CrossyObsticle(float x, float y, float width, float height, int rows, int cols, Engine engine, Texture texture) {
        super(x, y, width, height, rows, cols, engine, texture);
    }

    @Override
    public void onCollision(CollisionBox c) {

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

package com.companybest.ondra.adronengine.Objects;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;



public class FallingObject extends Sprite implements CollisionBox,CollisionHandler{
    public FallingObject(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public FallingObject(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }



    @Override
    public void onCollision(CollisionBox c) {

    }

    @Override
    public EntityComponents getEntityComponentsForColision() {
        return getEntityComponentsForColision();
    }

    @Override
    public CollisionHandler getCollisionHandler() {
        return getCollisionHandler();
    }

    @Override
    public Entity getEntity() {
        return getOwner();
    }
}

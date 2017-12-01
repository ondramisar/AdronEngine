package com.companybest.ondra.adronengine.Objects;

import android.app.Activity;
import android.content.Intent;

import com.companybest.ondra.adron.BaseClasses.BasicAdrClass;
import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;
import com.companybest.ondra.adronengine.Main2Activity;


public class DodgePlayer extends Sprite implements CollisionHandler, CollisionBox {
    private Activity mActivity;

    public DodgePlayer(float x, float y, float width, float height, Engine engine, Texture texture, CollisionSystem collisionSystem, Activity activity) {
        super(x, y, width, height, engine, texture);
        collisionSystem.addEntity(this);
        this.mActivity = activity;
    }

    public DodgePlayer(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }

    public DodgePlayer(float x, float y, int rows, int cols, int totalFrames, Engine engine, Texture texture) {
        super(x, y, rows, cols, totalFrames, engine, texture);
    }

    @Override
    public void onCollision(CollisionBox c) {
        if (c.getEntity().getName().equals("Obsticle")){
            Intent i = new Intent(mActivity, Main2Activity.class);
            BasicAdrClass basicAdrClass = (BasicAdrClass) mActivity;
            basicAdrClass.adrStartActivity(mActivity, i);
        }
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

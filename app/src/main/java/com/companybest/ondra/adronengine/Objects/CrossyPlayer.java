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


public class CrossyPlayer extends Sprite implements CollisionBox,CollisionHandler{

        private Activity mActivity;
    public CrossyPlayer(float x, float y, float width, float height, Engine engine, Texture texture, CollisionSystem collisionSystem, Activity activity) {
        super(x, y, width, height, engine, texture);
        collisionSystem.addEntity(this);
        this.mActivity = activity;
    }


    @Override
    public void onCollision(CollisionBox c) {
        Intent i = new Intent(mActivity, Main2Activity.class);
        BasicAdrClass basicAdrClass = (BasicAdrClass) mActivity;
        basicAdrClass.adrStartActivity(mActivity,i);
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

package com.companybest.ondra.adronengine.Objects;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;
import com.companybest.ondra.adronengine.Main2Activity;
import com.companybest.ondra.adronengine.MainActivity;

import javax.microedition.khronos.opengles.GL10;


public class Bird extends Sprite implements CollisionHandler, CollisionBox {

    private Activity mContext;
    private int side;

    public Bird(float x, float y, Engine engine, Texture texture, CollisionSystem collisionSystem) {
        super(x, y, engine, texture);
        collisionSystem.addEntity(this);
    }

    public Bird(float x, float y, float width, float height, Engine engine, Texture texture, CollisionSystem collisionSystem, Activity context) {
        super(x, y, width, height,engine, texture);
        collisionSystem.addEntity(this);
        this.mContext = context;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public void draw(GL10 gl) {
        super.draw(gl);
    }

    public void jump() {
        setX(getX() + 10);
    }

    @Override
    public void onCollision(CollisionBox c) {
        Log.i("usernbi", "COLLISION BIRD");

       Intent myIntent = new Intent(mContext, Main2Activity.class);
       MainActivity mainActivity = (MainActivity) mContext;
       mainActivity.adrStartActivity(mContext,myIntent);
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

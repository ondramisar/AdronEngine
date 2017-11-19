package com.companybest.ondra.adronengine;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.companybest.ondra.adronengine.Collisions.CollisionBox;
import com.companybest.ondra.adronengine.Collisions.CollisionHandler;
import com.companybest.ondra.adronengine.Collisions.CollisionSystem;
import com.companybest.ondra.adronengine.Entity.Entity;
import com.companybest.ondra.adronengine.OpenGl.EntityComponents;
import com.companybest.ondra.adronengine.OpenGl.Sprite;
import com.companybest.ondra.adronengine.OpenGl.Texture;

import javax.microedition.khronos.opengles.GL10;


public class Bird extends Sprite implements CollisionHandler, CollisionBox {

    private Activity mContext;

    public Bird(float x, float y, Texture texture, CollisionSystem collisionSystem) {
        super(x, y, texture);
        collisionSystem.addEntity(this);
    }

    public Bird(float x, float y, float width, float height, Texture texture, CollisionSystem collisionSystem, Activity context) {
        super(x, y, width, height, texture);
        collisionSystem.addEntity(this);
        this.mContext = context;
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
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ((Activity) mContext).overridePendingTransition(0, 0);
        mContext.startActivity(myIntent);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(0, 0);
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

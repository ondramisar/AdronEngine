package com.companybest.ondra.adronengine.Objects;

import com.companybest.ondra.adron.Collisions.CollisionBox;
import com.companybest.ondra.adron.Collisions.CollisionHandler;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Ondra on 28.11.2017.
 */

public class ObsticlePair extends Sprite implements CollisionHandler, CollisionBox {
    // Constants
    public static final double SPEED = 1;  // Speed at which the flower moves (in grid units)

    private static final int Y_RANGE = 40;  // Range of random y values in grid units
    private static final int GAP = 40;      // Size of the gap between top and bottom flowers in grid units

    private static Random rand;
    Obsticle top;
    Obsticle bottom;
    // Variables
    private boolean passed;          // Indicates when the bug has passed the flower


    public ObsticlePair(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public ObsticlePair(float x, float y, Engine engine, Texture texture, CollisionSystem mCollisionSystem) {
        super(x, y, engine, texture);

        top = new Obsticle(0, 0, 16, 80, getEngine(), texture);
        bottom = new Obsticle(0, 0, 16, 80, getEngine(), texture);

        top.setY(GAP / 2 + top.getHeight() / 2);
        //top.transform.angle = 180;

        bottom.setY(-GAP / 2 - bottom.getHeight() / 2);
        //  bottom.transform.angle = 0;

        mCollisionSystem.addEntity(top);
        mCollisionSystem.addEntity(bottom);

        passed = true;

        if (rand == null) rand = new Random();

    }

    public ObsticlePair(float x, float y, int rows, int cols, int totalFrames, Engine engine, Texture texture) {
        super(x, y, rows, cols, totalFrames, engine, texture);
    }

    @Override
    public void draw(GL10 gl) {
        super.draw(gl);
        x += SPEED;

        // Passed the bug, increment score.
        if (x < getEngine().getWidth() / 2 && !passed) {
            //  ((GameRoom) getRoom()).incrementScore();
            passed = true;

        }

    }

    public void sendToRightEdge() {
        x = getEngine().getWidth() + top.getWidth();
        passed = false;
    }

    public void sendToLeftEdge() {
        x = -top.getWidth();
        passed = true;
    }

    public void randomizeY() {
        int offset = rand.nextInt(Y_RANGE);
        y = (getEngine().getHeight() - Y_RANGE) / 2 + offset;
    }

    @Override
    public void onCollision(CollisionBox c) {

    }

    @Override
    public EntityComponents getEntityComponentsForColision() {
        return null;
    }

    @Override
    public CollisionHandler getCollisionHandler() {
        return null;
    }

    @Override
    public Entity getEntity() {
        return null;
    }
}

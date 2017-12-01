package com.companybest.ondra.adron.Collisions;


import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.Entity.Scene;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class CollisionSystem extends Entity {

    private ArrayList<HitBox> hitBoxes;      // All of the hit boxes in this system.


    public CollisionSystem() {
        init();
    }

    /**
     * Create a new collision system.
     */
    public CollisionSystem(Engine engine) {
        super(engine);
        init();
    }

    private void init() {
        hitBoxes = new ArrayList<HitBox>();
    }

    public void showBoxes(boolean show, Scene scene) {
        if (show) {
            for (HitBox hitBox : hitBoxes) {
                hitBox.setAlpha(50);
                hitBox.setBlue(40);
                hitBox.setGreen(40);
                scene.addComponent(hitBox);
            }
        }
    }


    /**
     * Add each CollisionBox component of Entity e to this system.
     *
     * @param e an Entity with CollisionBox components.
     */
    public void addEntity(CollisionBox e) {
        if (e != null) {
            addCollidable(e);
        }

    }

    /**
     * Add a new CollisionBox to this collision system.
     *
     * @param c The CollisionBox to add to this collision system.
     */
    public void addCollidable(CollisionBox c) {
        hitBoxes.add(new HitBox(c, c.getEntity()));
    }


    @Override
    public void draw(GL10 gl10) {
        for (int i = 0; i < hitBoxes.size(); i++) {           // Update all the hit boxes.
            hitBoxes.get(i).update();
        }

        for (int i = 0; i < hitBoxes.size(); i++) {           // Find all the CollisionHandlers.
            HitBox h1 = hitBoxes.get(i);

            if (h1.c.getCollisionHandler() != null) {         // Is this hit box for a CollisionHandler?
                for (int j = 0; j < hitBoxes.size(); j++) {   // Compare hit box h1 to all other hit boxes.
                    HitBox h2 = hitBoxes.get(j);

                    if (h1.c != h2.c) {                       // Make sure it isn't the same CollisionBox!
                        boolean collided = (h1.x < h2.x + h2.w) && (h1.x + h1.w > h2.x) && (h1.y < h2.y + h2.h) && (h1.y + h1.h > h2.y);
                        if (collided) {
                            h1.c.getCollisionHandler().onCollision(h2.c);
                        }
                    }
                }
            }
        }
    }

    public static boolean checkPosition(CollisionBox c, double x, double y, double pWidth, double pHeight) {
        EntityComponents t = c.getEntityComponentsForColision();

        int x1 = (int) t.getX();
        int y1 = (int) t.getY();

        int width = (int) t.getWidth();
        int height = (int) t.getHeight();

        return (x < x1 + width) && (x + pWidth > x1) && (y < y1 + height) && (y + pHeight > y1);
    }
}

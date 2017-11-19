package com.companybest.ondra.adron.Entity;


import android.graphics.Point;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;


/**
 * Class for adding objects that will be drawn and updated
 */
public class Scene extends Entity {

    private ArrayList<Entity> mEntities;
    private Point mPoint;

    /**
     * basic creating
     */
    public Scene() {
        mEntities = new ArrayList<>();
        mPoint = new Point();
    }

    /**
     * ArrayList of Objects
     *
     * @param entities ArrayList of objects
     */
    public Scene(ArrayList<Entity> entities) {
        mEntities = entities;
    }

    @Override
    public void draw(GL10 gl10) {

        for (Entity entity : mEntities) {
            if (entity != null) {
                entity.draw(gl10);
            }
        }
    }

    /**
     * get a object from scene at position
     *
     * @param position
     * @return object
     */
    public Entity getObject(int position) {
        return mEntities.get(position);
    }

    /**
     * finds Entity by name
     * @param name to find entity by
     * @return
     */
    public Entity findEntity(String name) {
        for (Entity e : mEntities) {
            if (e.getName() != null && e.getName().equals(name))
                    return e;

        }
        return null;
    }

    /**
     * add Object to be drawn and updated
     *
     * @param entity adding object
     */
    public void addComponent(Entity entity) {
        mEntities.add(entity);
    }

    /**
     * gets the touch point
     *
     * @return
     */
    public Point getPoint() {
        return mPoint;
    }


    /**
     * @return ArrayList of Objects
     */
    public ArrayList<Entity> getEntities() {
        return mEntities;
    }
}

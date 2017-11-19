package com.companybest.ondra.adron.Entity;


import com.companybest.ondra.adron.OpenGl.EntityComponents;

import javax.microedition.khronos.opengles.GL10;

/**
 * basic Class for all Entities
 */
public abstract class Entity implements IEntity, EntityComponents {


    public boolean visible;

    public float x;
    public float y;

    private float width;
    private float height;

    private double mScale;

    private String mName;

    public Entity() {

    }

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mScale = 1;
    }

    @Override
    public void draw(GL10 gl10){

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setHeight(float h) {
        height = h;
    }

    public void setWidth(float w) {
        width = w;
    }


    /**
     * set the x position
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * set the y position
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Get the x position
     *
     * @return x float
     */
    public float getX() {
        return this.x;
    }

    /**
     * Get the y position
     *
     * @return y float
     */
    public float getY() {
        return this.y;
    }

    /**
     * Set position x and y
     * @param x
     * @param y
     */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the width
     *
     * @return width float
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Get the height
     *
     * @return height float
     */
    public float getHeight() {
        return this.height;
    }

    @Override
    public float getAngle() {
        return 0;
    }

    @Override
    public double getScale() {
        return mScale;
    }

    /**
     * Check if a point is inside an object
     *
     * @param x - x coordinate of the point
     * @param y - y coordinate of the point
     * @return true if its inside false otherwise
     */
    public boolean HitTest(float x, float y) {
        return (x > this.x && x < this.x + this.width) && (y > this.y && y < this.y + this.height);
    }

    public EntityComponents getEntityComponents(){
        return this;
    }

}

package com.companybest.ondra.adron.Input;


import com.companybest.ondra.adron.Entity.Entity;

/**
 * basic interface for touch listener
 */
public interface IOnTouchListener {

    /**
     * on press Listener
     * @param x
     * @param y
     */
    public void onPress(float x, float y);

    /**
     * on release Listener
     * @param x
     * @param y
     */
    public void onRelease(float x, float y);

    /**
     * on move Listener
     * @param x
     * @param y
     */
    public void onMove(float x, float y);

    /**
     * on press Listener with object
     * @param entity
     */
    public void onPress(Entity entity);

    /**
     * on release Listener with object
     * @param entity
     */
    public void onRelease(Entity entity);

    /**
     * on move Listener with object
     * @param entity
     */
    public void onMove(Entity entity);

    /**
     * on Release Outside Listener
     * @param entity
     */
    public void onReleaseOutside(Entity entity);
}

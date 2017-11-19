package com.companybest.ondra.adronengine.Rendering;


import com.companybest.ondra.adronengine.Entity.Scene;

/**
 * basic interface drawing and updating
 */
public interface IRenderUpdateScene {

    /**
     * sets up scene to be drawn
     * @return
     */
    Scene setUpScene();

    /**
     * updates the drawing scene
     * @param dt
     */
    void update(float dt);

}

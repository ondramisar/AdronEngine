package com.companybest.ondra.adron.Rendering;


import com.companybest.ondra.adron.Entity.Scene;

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

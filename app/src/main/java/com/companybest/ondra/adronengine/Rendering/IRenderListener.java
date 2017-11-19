package com.companybest.ondra.adronengine.Rendering;

import javax.microedition.khronos.opengles.GL10;

public interface IRenderListener {

    /**
     * called when surface is created
     * @param gl10
     */
    void onSurfaceCreated(GL10 gl10);

    /**
     * called when surface is changed
     * @param gl10
     * @param pWidth
     * @param pHeight
     */
    void onSurfaceChanged(GL10 gl10, final int pWidth, final int pHeight);

}


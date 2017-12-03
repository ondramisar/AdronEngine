package com.companybest.ondra.adron.Entity;


import com.companybest.ondra.adron.Engine.Engine;

import javax.microedition.khronos.opengles.GL10;


public class Camera extends Entity {

    // Camera variables
    private float camX;       // The X position of the camera.
    private float camY;       // The Y position of the camera.
    private double camZoom;    // The zoom level of the camera. 1 is default, 0> and <1 is zoomed in, >1 is zoomed out.
    private double cAnchorX;   // The zoom anchor X position. This position will stay in the same place when the camera is zoomed in/out.
    private double cAnchorY;   // The zoom anchor Y position.
    private float camLeft;     // The left edge of the camera
    private float camRight;    // The right edge of the camera
    private float camTop;      // The top edge of the camera
    private float camBottom;   // The bottom edge of the camera

    private Engine mEngine;


    /**
     * Default Constructor
     *
     * @param camX    X position of camera
     * @param camY    Y position of camera
     * @param camZoom Zoom of camera
     */
    public Camera(float camX, float camY, double camZoom, Engine engine) {
        this.camX = camX;
        this.camY = camY;
        this.camZoom = camZoom;
        this.mEngine = engine;
        setName("CAMERA");
    }

    /**
     * Constructor with setting the anchorX and anchorY
     *
     * @param camX     X position of camera
     * @param camY     Y position of camera
     * @param camZoom  Zoom of camera
     * @param cAnchorX
     * @param cAnchorY
     */
    public Camera(float camX, float camY, double camZoom, double cAnchorX, double cAnchorY, Engine engine) {
        this.camX = camX;
        this.camY = camY;
        this.camZoom = camZoom;
        this.cAnchorX = cAnchorX;
        this.cAnchorY = cAnchorY;
        this.mEngine = engine;
        setName("CAMERA");
    }

    public void setCameraAnchor(int x, int y) {
        cAnchorX = x;
        cAnchorY = y;
    }

    @Override
    public void draw(GL10 gl) {
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glOrthof(camLeft, camRight, camTop, camBottom, -1, 1);
    }

    public void update(double width, double height) {
        camLeft = (float) (camX * mEngine.getGridUnitX() + cAnchorX - width * camZoom * (cAnchorX / width));
        camRight = (float) (camX * mEngine.getGridUnitX() + cAnchorX + width * camZoom * ((width - cAnchorX) / width));
        camTop = (float) (camY * mEngine.getGridUnitY() + cAnchorY + height * camZoom * ((height - cAnchorY) / height));
        camBottom = (float) (camY * mEngine.getGridUnitY() + cAnchorY - height * camZoom * (cAnchorY / height));

    }

    public double getCamX() {
        return camX;
    }

    public void setCamX(float camX) {
        this.camX = camX;
    }

    public double getCamY() {
        return camY;
    }

    public void setCamY(float camY) {
        this.camY = camY;
    }

    /**
     * Get the coordinate of the left edge of the camera in grid units.
     */
    public float getCameraLeftEdge() {
        return camLeft / (float) getEngine().getGridUnitX();
    }

    /**
     * Get the coordinate of the right edge of the screen in grid units.
     */
    public float getCameraRightEdge() {
        return camRight / (float) getEngine().getGridUnitX();
    }

    /**
     * Get the coordinate of the bottom edge of the screen in grid units.
     */
    public float getCameraBottomEdge() {
        return camBottom / (float) getEngine().getGridUnitY();
    }

    /**
     * Get the coordinate of the top edge of the screen in grid units.
     */
    public float getCameraTopEdge() {
        return camTop / (float)  getEngine().getGridUnitY();
    }

}

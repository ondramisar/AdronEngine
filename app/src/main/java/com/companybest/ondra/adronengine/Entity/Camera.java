package com.companybest.ondra.adronengine.Entity;


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


    private double gridUnitX;     // Size of a unit on the grid on the X axis.
    private double gridUnitY;     // Size of a unit on the grid on the Y axis.

    /**
     * Default Constructor
     * @param camX X position of camera
     * @param camY Y position of camera
     * @param camZoom Zoom of camera
     */
    public Camera(float camX, float camY, double camZoom) {
        this.camX = camX;
        this.camY = camY;
        this.camZoom = camZoom;
        gridUnitX = 1;
        gridUnitY = 1;
        setName("CAMERA");
    }

    /**
     * Constructor with setting the anchorX and anchorY
     * @param camX X position of camera
     * @param camY Y position of camera
     * @param camZoom Zoom of camera
     * @param cAnchorX
     * @param cAnchorY
     */
    public Camera(float camX, float camY, double camZoom, double cAnchorX, double cAnchorY) {
        this.camX = camX;
        this.camY = camY;
        this.camZoom = camZoom;
        this.cAnchorX = cAnchorX;
        this.cAnchorY = cAnchorY;
        gridUnitX = 1;
        gridUnitY = 1;
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

    public void update(int width, int height) {
        camLeft = (float) (camX * gridUnitX + cAnchorX - width * camZoom * (cAnchorX / width));
        camRight = (float) (camX * gridUnitX + cAnchorX + width * camZoom * ((width - cAnchorX) / width));
        camTop = (float) (camY * gridUnitY + cAnchorY + height * camZoom * ((height - cAnchorY) / height));
        camBottom = (float) (camY * gridUnitY + cAnchorY - height * camZoom * (cAnchorY / height));
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
}

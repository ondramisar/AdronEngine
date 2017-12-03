package com.companybest.ondra.adron.Engine;


import com.companybest.ondra.adron.Entity.Camera;
import com.companybest.ondra.adron.Entity.Scene;
import com.companybest.ondra.adron.OpenGl.TextureLibrary;
import com.companybest.ondra.adron.Rendering.IRenderUpdateScene;

import javax.microedition.khronos.opengles.GL10;


/**
 * Class for Drawing and updating your scene
 * Setting up of engine function
 */
public class Engine {

    private IRenderUpdateScene mIRenderUpdateScene;
    private Scene mScene;
    private TextureLibrary mTextureLibrary;
    private Camera mCamera;

    private boolean isCameraActive;

    private double gridUnitX;     // Size of a unit on the grid on the X axis.
    private double gridUnitY;     // Size of a unit on the grid on the Y axis.


    private double gridWidth;     // The number of grid units that fit in the view width
    private double gridHeight;    // The number of grid units that fit in the view height

    private double viewWidth;     // The number of grid units that fit in the view width
    private double viewHeight;    // The number of grid units that fit in the view height

    private boolean fpsOutput;

    /**
     * Basic Constructor
     *
     * @param IRenderUpdateScene draw and update methods form your activity
     */
    public Engine(IRenderUpdateScene IRenderUpdateScene, TextureLibrary textureLibrary) {
        this.mIRenderUpdateScene = IRenderUpdateScene;
        this.mTextureLibrary = textureLibrary;
        this.isCameraActive = false;
        gridUnitX = 1;
        gridUnitY = 1;
        fpsOutput = false;
    }

    /**
     * Constructor with camera
     *
     * @param IRenderUpdateScene draw and update methods form your activity
     */
    public Engine(IRenderUpdateScene IRenderUpdateScene, TextureLibrary textureLibrary, boolean isCameraActive) {
        this.mIRenderUpdateScene = IRenderUpdateScene;
        this.mTextureLibrary = textureLibrary;
        this.isCameraActive = isCameraActive;
        gridUnitX = 1;
        gridUnitY = 1;
        fpsOutput = false;
    }

    /**
     * Method that will be drawn in renderer
     *
     * @param gl10
     * @param dt
     */
    public void onDrawFrame(GL10 gl10, float dt) {
        this.mScene.draw(gl10);
        this.mIRenderUpdateScene.update(dt);
    }

    public void setUpScene() {
        mScene = this.mIRenderUpdateScene.setUpScene();
        if (isCameraActive)
            mCamera = (Camera) mScene.findEntity("CAMERA");
    }

    public boolean isCameraActive() {
        return isCameraActive;
    }

    public void setCameraActive(boolean cameraActive) {
        isCameraActive = cameraActive;
    }

    public boolean isFpsOutput() {
        return fpsOutput;
    }

    public void setFpsOutput(boolean fpsOutput) {
        this.fpsOutput = fpsOutput;
    }

    public int getWidth() {
        if (gridWidth != 0)
            return (int) gridWidth;

        return (int) viewWidth;
    }

    public int getHeight() {
        if (gridHeight != 0)
            return (int) gridHeight;

        return (int) viewHeight;
    }

    /**
     * @return get a drawing scene
     */
    public Scene getScene() {
        return mScene;
    }

    /**
     * get the texture library
     *
     * @return
     */
    public TextureLibrary getTextureLibrary() {
        return mTextureLibrary;
    }

    public void setTextureLibrary(TextureLibrary textureLibrary) {
        mTextureLibrary = textureLibrary;
    }

    public Camera getCamera() {
        return mCamera;
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
    }


    public double getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(double viewWidth) {
        this.viewWidth = viewWidth;
    }

    public double getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(double viewHeight) {
        this.viewHeight = viewHeight;
    }

    /**
     * Set the grid unit size in pixels for the X axis to some absolute value.
     *
     * @param unit size of one unit in pixels.
     */
    public void setGridUnitX(double unit) {
        gridUnitX = unit;
        gridWidth = viewWidth / gridUnitX;
    }

    /**
     * Set the grid unit size in pixels for the Y axis to some absolute value.
     *
     * @param unit size of one unit in pixels
     */
    public void setGridUnitY(double unit) {
        gridUnitY = unit;
        gridHeight = viewHeight / gridUnitY;
    }

    /**
     * Set the grid unit size for the X axis by how many units should equal the width of the
     * room.
     *
     * @param unitsPerRoomWidth Number of units that fit in the view width.
     */
    public void setGridWidth(double unitsPerRoomWidth) {
        setGridUnitX(viewWidth / unitsPerRoomWidth);
    }

    /**
     * Set the grid unit size for the Y axis by how many units should equal the height of the
     * view.
     *
     * @param unitsPerRoomHeight Number of units that fit in the view height.
     */
    public void setGridHeight(double unitsPerRoomHeight) {
        setGridUnitY(viewHeight / unitsPerRoomHeight);
    }

    /**
     * Returns the size of one grid unit in the X axis.
     *
     * @return the size of one grid unit in the X axis.
     */
    public double getGridUnitX() {
        return gridUnitX;
    }

    /**
     * Returns the size of one grid unit in the Y axis.
     *
     * @return the size of one grid unit in the Y axis.
     */
    public double getGridUnitY() {
        return gridUnitY;
    }


}

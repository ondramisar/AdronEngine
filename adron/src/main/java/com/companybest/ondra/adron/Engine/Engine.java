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

    private int mWidth;
    private int mHeight;

    /**
     * Basic Constructor
     * @param IRenderUpdateScene draw and update methods form your activity
     */
    public Engine(IRenderUpdateScene IRenderUpdateScene, TextureLibrary textureLibrary) {
        this.mIRenderUpdateScene = IRenderUpdateScene;
        this.mTextureLibrary = textureLibrary;
        this.isCameraActive = false;
    }

    /**
     * Constructor with camera
     * @param IRenderUpdateScene draw and update methods form your activity
     */
    public Engine(IRenderUpdateScene IRenderUpdateScene, TextureLibrary textureLibrary, boolean isCameraActive) {
        this.mIRenderUpdateScene = IRenderUpdateScene;
        this.mTextureLibrary = textureLibrary;
        this.isCameraActive = isCameraActive;
    }

    /**
     * Method that will be drawn in renderer
     * @param gl10
     * @param dt
     */
    public void onDrawFrame(GL10 gl10, float dt){
        this.mScene.draw(gl10);
        this.mIRenderUpdateScene.update(dt);
    }

    public void setUpScene(){
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

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    /**
     *
     * @return get a drawing scene
     */
    public Scene getScene() {
        return mScene;
    }

    /**
     * get the texture library
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
}

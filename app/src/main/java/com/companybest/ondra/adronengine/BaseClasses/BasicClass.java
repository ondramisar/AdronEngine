package com.companybest.ondra.adronengine.BaseClasses;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.companybest.ondra.adronengine.Engine.Engine;
import com.companybest.ondra.adronengine.Entity.Scene;
import com.companybest.ondra.adronengine.Input.IOnTouchListener;
import com.companybest.ondra.adronengine.Input.Touch;
import com.companybest.ondra.adronengine.OpenGl.TextureLibrary;
import com.companybest.ondra.adronengine.Rendering.AdrGlSurfaceView;
import com.companybest.ondra.adronengine.Rendering.IRenderListener;
import com.companybest.ondra.adronengine.Rendering.IRenderUpdateScene;

import javax.microedition.khronos.opengles.GL10;


public abstract class BasicClass extends AppCompatActivity implements IRenderUpdateScene, IOnTouchListener, IRenderListener {

    private AdrGlSurfaceView mGlSurfaceView;
    private Engine mEngine;
    private Touch mTouch;


    public void setUpEngine(Engine engine, Context context, IOnTouchListener onTouchListener){
        this.mTouch = new Touch(onTouchListener);
        this.mEngine = engine;
        setUpView(new AdrGlSurfaceView(context), mTouch);
    }

    public void setUpEngine(Engine engine, IOnTouchListener onTouchListener, AdrGlSurfaceView adrGlSurfaceView){
        this.mTouch = new Touch(onTouchListener);
        this.mEngine = engine;
        setUpView(adrGlSurfaceView, mTouch);
    }

    private void setUpView(AdrGlSurfaceView adrGlSurfaceView, Touch touch){
        this.mGlSurfaceView = adrGlSurfaceView;
        this.mGlSurfaceView.setRenderer(this.mEngine, this);
        this.mGlSurfaceView.setOnTouchListener(touch);
    }

    public Engine getEngine() {
        return mEngine;
    }

    public int getWidth() {
        return  mEngine.getWidth();
    }

    public int getHeight() {
        return mEngine.getHeight();
    }

    public Scene getScene(){
        return mEngine.getScene();
    }

    public Touch getTouch() {
        return mTouch;
    }

    public TextureLibrary getTextureLibrary(){
        return mEngine.getTextureLibrary();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        mEngine.setWidth(pWidth);
        mEngine.setHeight(pHeight);
    }
}

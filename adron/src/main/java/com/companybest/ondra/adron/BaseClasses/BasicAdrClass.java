package com.companybest.ondra.adron.BaseClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;

import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Camera;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.Scene;
import com.companybest.ondra.adron.Input.IOnTouchListener;
import com.companybest.ondra.adron.Input.Touch;
import com.companybest.ondra.adron.OpenGl.TextureLibrary;
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView;
import com.companybest.ondra.adron.Rendering.IRenderListener;
import com.companybest.ondra.adron.Rendering.IRenderUpdateScene;

import javax.microedition.khronos.opengles.GL10;


public abstract class BasicAdrClass extends AppCompatActivity implements IRenderUpdateScene, IOnTouchListener, IRenderListener {

    private AdrGlSurfaceView mGlSurfaceView;
    private Engine mEngine;
    private Touch mTouch;


    public void setUpEngine(Engine engine, IOnTouchListener onTouchListener, Context context) {
        this.mTouch = new Touch(onTouchListener);
        this.mEngine = engine;
        setUpView(new AdrGlSurfaceView(context), mTouch);
    }

    public void setUpEngine(Engine engine, IOnTouchListener onTouchListener, AdrGlSurfaceView adrGlSurfaceView) {
        this.mTouch = new Touch(onTouchListener);
        this.mEngine = engine;
        setUpView(adrGlSurfaceView, mTouch);
    }

    private void setUpView(AdrGlSurfaceView adrGlSurfaceView, Touch touch) {
        this.mGlSurfaceView = adrGlSurfaceView;
        this.mGlSurfaceView.setRenderer(this.mEngine, this);
        this.mGlSurfaceView.setOnTouchListener(touch);
    }

    public void adrStartActivity(Activity activity, Intent intent) {
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.overridePendingTransition(0, 0);
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(0, 0);
    }

    public Engine getEngine() {
        return mEngine;
    }

    public int getWidth() {
        return mEngine.getWidth();
    }

    public int getHeight() {
        return mEngine.getHeight();
    }

    public Scene getScene() {
        return mEngine.getScene();
    }

    public Camera getCamera() {
        return mEngine.getCamera();
    }

    public Touch getTouch() {
        return mTouch;
    }

    public TextureLibrary getTextureLibrary() {
        return mEngine.getTextureLibrary();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        mEngine.setViewWidth(pWidth);
        mEngine.setViewHeight(pHeight);
    }

    @Override
    public void onPress(float x, float y) {

    }

    @Override
    public void onRelease(float x, float y) {

    }

    @Override
    public void onMove(float x, float y) {

    }

    @Override
    public void onPress(Entity entity) {

    }

    @Override
    public void onRelease(Entity entity) {

    }

    @Override
    public void onMove(Entity entity) {

    }

    @Override
    public void onReleaseOutside(Entity entity) {

    }
}


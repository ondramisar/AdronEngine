package com.companybest.ondra.adronengine.Rendering;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.companybest.ondra.adronengine.Engine.Engine;
import com.companybest.ondra.adronengine.Entity.Scene;

/**
 * Open gl Surface view
 */
public class AdrGlSurfaceView extends GLSurfaceView {

    private AdrGlRenderer mAdrGlRenderer;
    private Engine mEngine;


    public AdrGlSurfaceView(Context context) {
        super(context);
    }

    public AdrGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * sets up the renderer
     * @param pEngine
     */
    public void setRenderer(Engine pEngine, IRenderListener renderListener){
        this.mEngine = pEngine;
        this.mAdrGlRenderer = new AdrGlRenderer(pEngine, renderListener);
        this.setRenderer(mAdrGlRenderer);
    }

    public Engine getEngine() {
        return mEngine;
    }

    public Scene getScene(){
        return mEngine.getScene();
    }
}

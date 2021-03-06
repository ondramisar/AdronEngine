package com.companybest.ondra.adron.Rendering;

import android.opengl.GLSurfaceView;
import android.os.SystemClock;
import android.util.Log;

import com.companybest.ondra.adron.Engine.Engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Open gl Render Class
 */
public class AdrGlRenderer implements GLSurfaceView.Renderer {

    // Constants
    private static final int FRAME_DROP_THRES = 30;

    // Variables
    public static final long OPTIMAL_FPS = 60;        // The optimal speed that the game will run
    private float OPTIMAL_TIME = 1000 / OPTIMAL_FPS;  // Optimal time for a frame to take
    private float averageDelta = OPTIMAL_TIME;        // Average amount of time a frame takes
    private long lastTime;                            // Time the last frame took
    private int frames = 0;

    private double low;   // The lowest FPS
    private double high;  // The highest FPS


    private Engine mEngine;
    private IRenderListener mRenderListener;

    /**
     * engine to be rendered
     *
     * @param pEngine
     */
    public AdrGlRenderer(Engine pEngine, IRenderListener renderListener) {
        this.mEngine = pEngine;
        this.mRenderListener = renderListener;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        mRenderListener.onSurfaceCreated(gl);

        mEngine.getTextureLibrary().LoadTextures(gl);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);    // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glAlphaFunc(GL10.GL_GREATER, 0.1f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mRenderListener.onSurfaceChanged(gl, width, height);

        mEngine.setUpScene();
        if (mEngine.isCameraActive() && mEngine.getCamera() != null)
            mEngine.getCamera().update(width, height);


        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix

        gl.glOrthof(0, width, height, 0, 0, 1f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        long now = SystemClock.uptimeMillis();         // Current time
        long timeElapsed = (long) OPTIMAL_TIME;        // Amount of time the frame took

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();

        if (lastTime > 0) {
            timeElapsed = now - lastTime;                  // The amount of time the last frame took
        }

        if (frames < OPTIMAL_FPS) {
            frames++;
            averageDelta = (timeElapsed + averageDelta * (frames - 1)) / frames;     // Update the average amount of time a frame takes
        } else {
            averageDelta = (timeElapsed + averageDelta * (OPTIMAL_FPS - 1)) / OPTIMAL_FPS;           // Update the average amount of time a frame takes
        }

        mEngine.getTextureLibrary().LoadTextures(gl10);

        // draws and updated the scene from engine
        mEngine.onDrawFrame(gl10, averageDelta);


        if (mEngine.isCameraActive() && mEngine.getCamera() != null)
            mEngine.getCamera().update(mEngine.getViewWidth(), mEngine.getViewHeight());

        lastTime = now;

        if (mEngine.isFpsOutput() && frames % 60 == 0) {
            double fps = (double) 1000 / (double) averageDelta;

            if (1000.0 / timeElapsed < low || low == -1) {
                low = 1000.0 / timeElapsed;
            }

            if (1000.0 / timeElapsed > high || high == -1) {
                high = 1000.0 / timeElapsed;
            }

            if (1000.0 / timeElapsed < FRAME_DROP_THRES) {
                Log.d("fps", "FRAME DROPPED. FPS: " + (1000.0 / timeElapsed));
            }

            if (SystemClock.uptimeMillis() % 100 <= 10) {
                Log.d("fps", "FPS: " + fps + "    LOW: " + low + "    HIGH: " + high); // Show FPS in logcat
            }
        }

    }

}

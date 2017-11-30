package com.companybest.ondra.adron.Input;


import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView;


/**
 * class for handling touch event
 */
//TODO using multi Touch
public class Touch implements View.OnTouchListener {

    private int numOfTouches;
    private boolean isBeingTouch;

    private IOnTouchListener mOnTouchListener;
    private Point mPoint;

    /**
     * @param onTouchListener interface implemented in activity
     */
    public Touch(IOnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
        numOfTouches = 0;
        isBeingTouch = false;
        this.mPoint = new Point();
    }

    /**
     * Handle touch events.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Variables
        numOfTouches = event.getPointerCount();            // The current number of touches
        int index = event.getActionIndex();              // The finger that is touching the screen

        if (v instanceof AdrGlSurfaceView) {
            AdrGlSurfaceView view = (AdrGlSurfaceView) v;

            float x = (float) (event.getX() / view.getEngine().getGridUnitX());
            float y = (float) (event.getY() / view.getEngine().getGridUnitY());

            setX((int) x);
            setY((int) y);

            switch (event.getActionMasked() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    isBeingTouch = true;
                    DespatchPress(x, y, view);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    isBeingTouch = true;
                    DespatchMove(x, y, view);
                    return true;

                case MotionEvent.ACTION_UP:
                    isBeingTouch = false;
                    DespatchRelease(x, y, view);

                    return true;

                case MotionEvent.ACTION_POINTER_DOWN:

                    return true;

                case MotionEvent.ACTION_POINTER_UP:

                    return true;
            }
        }

        return false;
    }

    /**
     * Determine if any fingers, styli, etc are currently touching the screen.
     *
     * @return true if any pointers are currently touching the screen, false otherwise.
     */
    public boolean held() {
        if (isBeingTouch) {
            return true;
        }

        return false;
    }

    /**
     * Handle the Press Event
     *
     * @param x
     * @param y
     */
    public void HandlePress(float x, float y, AdrGlSurfaceView view) {
        view.getScene().getPoint().x = (int) x;
        view.getScene().getPoint().y = (int) y;

        for (Entity obj : view.getScene().getEntities()) {
            if (obj.visible)
                if (obj.HitTest(x, y)) {
                    mOnTouchListener.onPress(obj);
                }
        }
        mOnTouchListener.onPress(x, y);

    }


    /**
     * Handle the Release Event
     *
     * @param x
     * @param y
     */
    public void HandleRelease(float x, float y, AdrGlSurfaceView view) {

        view.getScene().getPoint().x = (int) x;
        view.getScene().getPoint().y = (int) y;


        for (Entity obj : view.getScene().getEntities()) {
            if (obj.visible) {
                if (obj.HitTest(x, y)) {
                    mOnTouchListener.onRelease(obj);
                } else {
                    mOnTouchListener.onReleaseOutside(obj);
                }
            }
        }
        mOnTouchListener.onRelease(x, y);
    }


    /**
     * Handle the Move Event
     *
     * @param x
     * @param y
     */
    public void HandleMove(float x, float y, AdrGlSurfaceView view) {
        view.getScene().getPoint().x = (int) x;
        view.getScene().getPoint().y = (int) y;

        for (Entity obj : view.getScene().getEntities()) {
            if (obj.visible)
                if (obj.HitTest(x, y)) {
                    mOnTouchListener.onMove(obj);
                }

        }
        mOnTouchListener.onMove(x, y);
    }

    /**
     * Despatch the on Press event
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void DespatchPress(float x, float y, AdrGlSurfaceView view) {
        if (view.getScene() != null) {
            HandlePress(x, y, view);
        }
    }

    /**
     * Despatch the on Release event
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void DespatchRelease(float x, float y, AdrGlSurfaceView view) {
        if (view.getScene() != null) {
            HandleRelease(x, y, view);
        }
    }

    /**
     * Despatch the on Move event
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void DespatchMove(float x, float y, AdrGlSurfaceView view) {
        if (view.getScene() != null) {
            HandleMove(x, y, view);
        }
    }

    /**
     * @return position x
     */
    public int getX() {
        return mPoint.x;
    }

    /**
     * @return position y
     */
    public int getY() {
        return mPoint.y;
    }


    private void setX(int x) {
        mPoint.x = x;
    }

    private void setY(int y) {
        mPoint.y = y;
    }


}

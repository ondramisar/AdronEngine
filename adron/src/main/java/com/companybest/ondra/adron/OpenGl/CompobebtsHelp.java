package com.companybest.ondra.adron.OpenGl;


import com.companybest.ondra.adron.Entity.EntityComponents;

public class CompobebtsHelp implements EntityComponents {

    public float x = 0;
    public float y = 0;
    public float width = 0;
    public float height = 0;
    public float angle = 0;
    public float scale = 1;

    // public Transformation parent = null;

    public CompobebtsHelp() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        angle = 0;
        scale = 1;
    }

    /**
     * Determine if the area defined by this Transform would be visible on the
     * screen in the specified Room.
     *
     * @param room The Room to use to determine if this Transform is on the screen.
     * @return true if this Transform appears on the screen, false otherwise.
     */
  /*  public boolean onScreen(Room room) {
        double width = Math.abs(this.width);

        double screenLeft = room.getCameraLeftEdge();
        double screenRight = room.getCameraRightEdge();
        double screenTop = room.getCameraTopEdge();
        double screenBottom = room.getCameraBottomEdge();

        if (followCamera) {
            screenLeft = 0;
            screenRight = room.getWidth();
            screenBottom = 0;
            screenTop = room.getHeight();
        }

        if (x > -width / 2 + screenLeft && x < width / 2 + screenRight) {
            if (y > -height / 2 + screenBottom && y < height / 2 + screenTop) {
                return true;
            }
        }

        return false;
    }*/

    /**
     * Returns the real X position after all parent transformations have been
     * applied.
     *
     * @return The real X position after all parent transformations have been
     * applied.
     */
    public double getRealX() {
        return getRealX(this);
    }

    /**
     * Returns the real Y position after all parent transformations have been
     * applied.
     *
     * @return The real Y position after all parent transformations have been
     * applied.
     */
    public double getRealY() {
        return getRealY(this);
    }

    /**
     * Returns the real angle after all parent transformations have been
     * applied.
     *
     * @return The real angle after all parent transformations have been
     * applied.
     */
    public double getRealAngle() {
        return getRealAngle(this);
    }

    /**
     * Returns the real scale after all parent transformations have been
     * applied.
     *
     * @return The real scale after all parent transformations have been
     * applied.
     */
    public double getRealScale() {
        return getRealScale(this);
    }

    /**
     * Returns the real visibility after all parent transformations have been
     * applied.
     *
     * @return The real visibility after all parent transformations have been
     * applied.
     */
/*    public boolean getRealVisibility() {
        return getRealVisibility(this);
    }
*/
    /* TRANSFORMATION METHODS */
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public double getScale() {
        return scale;
    }


	/* STATIC UTILITY METHODS */

    /**
     * Returns the real X position of t after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to find the real X position
     * @return The real X position of t after all parent transformations have been
     * applied.
     */
    public static double getRealX(EntityComponents t) {

        double x = t.getX();
        double y = t.getY();

        double cos = Math.cos(Math.toRadians(t.getAngle()));
        double sin = Math.sin(Math.toRadians(t.getAngle()));

        x *= t.getScale();
        y *= t.getScale();

        double oX = x;
        double oY = y;

        x = oX * cos - oY * sin;

        x += t.getX();
        y += t.getY();


        return x;
    }

    /**
     * Returns the real Y position of t after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to find the real Y position
     * @return The real Y position of t after all parent transformations have been
     * applied.
     */
    public static double getRealY(EntityComponents t) {
        double x = t.getX();
        double y = t.getY();

        double cos = Math.cos(Math.toRadians(t.getAngle()));
        double sin = Math.sin(Math.toRadians(t.getAngle()));

        x *= t.getScale();
        y *= t.getScale();

        double oX = x;
        double oY = y;

        y = oX * sin + oY * cos;

        x += t.getX();
        y += t.getY();


        return y;
    }

    /**
     * Returns the real angle of t after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to find the real angle
     * @return The real angle of t after all parent transformations have been
     * applied.
     */
    public static double getRealAngle(EntityComponents t) {
        double angle = t.getAngle();

        angle += t.getAngle();


        return angle;
    }

    /**
     * Returns the real scale of t after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to find the real scale
     * @return The real scale of t after all parent transformations have been
     * applied.
     */
    public static double getRealScale(EntityComponents t) {

        double scale = t.getScale();

        scale *= t.getScale();


        return scale;
    }

    /**
     * Returns the real visibility of t after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to find the real visibility
     * @return The real visibility of t after all parent transformations have been
     * applied.
     */
  /*  public static boolean getRealVisibility(EntityComponents t) {
        EntityComponents parent = t;

         if (!parent.getVisibility()) {
                return false;
            }


        return t.getVisibility();
    }*/

    /**
     * Returns whether t should follow the camera after all parent transformations have been
     * applied.
     *
     * @param t The Transformation for which to determine if it should follow the camera
     * @return True if t should follow the camera after all parent transformations have been
     * applied, false otherwise.
     */
 /*   public static boolean getRealShouldFollowCamera(EntityComponents t) {
        if (t.shouldFollowCamera()) return true;

        EntityComponents parent = t.getParent();

        while (parent != null) {
            if (parent.shouldFollowCamera()) {
                return true;
            }

            parent = parent.getParent();
        }

        return false;
    }*/
}

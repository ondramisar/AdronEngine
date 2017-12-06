package com.companybest.ondra.adron.OpenGl;


import com.companybest.ondra.adron.Entity.Entity;

public class Alignment {

    public static final int LEFT_TOP = 2;

    /**
     * get the vertices for a Sprite with respect to the origin and the current orientation
     *
     * @param sprite Sprite to get the position details
     * @param origin int origin value
     * @return vertices
     */
    public static float[] getVertices(Entity sprite, int origin) {

        if (origin == LEFT_TOP) {
            float vertices[] = new float[12];

            float x;
            float y;
            float height;
            float width;

            x = (int) sprite.getX();
            y = (int) sprite.getY();
            height = (int) sprite.getHeight();
            width = (int) sprite.getWidth();

            x *= sprite.getEngine().getGridUnitX();
            y *= sprite.getEngine().getGridUnitY();

            height *= sprite.getEngine().getGridUnitY();
            width *= sprite.getEngine().getGridUnitX();

            float angle = sprite.getAngle();

            if (angle != 0) {
                // Do rotating of cordinets
                float cx = x + (width / 2);
                float cy = y - (height / 2);

                // Top left
                vertices[0] = rotateX(angle, x, y - height, cx, cy);
                vertices[1] = rotateY(angle, x, y - height, cx, cy);
                vertices[2] = 0.0f;
                // Bottom left
                vertices[3] = rotateX(angle, x, y, cx, cy);
                vertices[4] = rotateY(angle, x, y, cx, cy);
                vertices[5] = 0.0f;
                // Bottom right
                vertices[6] = rotateX(angle, x + width, y, cx, cy);
                vertices[7] = rotateY(angle, x + width, y, cx, cy);
                vertices[8] = 0.0f;
                // Top right
                vertices[9] = rotateX(angle, x + width, y - height, cx, cy);
                vertices[10] = rotateY(angle, x + width, y - height, cx, cy);
                vertices[11] = 0.0f;

            } else {
                // Top left
                vertices[0] = x;
                vertices[1] = y - height;
                vertices[2] = 0.0f;
                // Bottom left
                vertices[3] = x;
                vertices[4] = y;
                vertices[5] = 0.0f;
                // Bottom right
                vertices[6] = x + width;
                vertices[7] = y;
                vertices[8] = 0.0f;
                // Top right
                vertices[9] = x + width;
                vertices[10] = y - height;
                vertices[11] = 0.0f;

            }
            return vertices;

        }
        return null;
    }

    private static float rotateX(float angle, float x, float y,float cx, float cy) {
        float tempX = x - cx;
        float tempY = y - cy;

        double rotatedX =  ((tempX * Math.cos(Math.toRadians(angle))) - (tempY * Math.sin(Math.toRadians(angle))));
        return (float) (rotatedX + cx);
    }

    private static float rotateY(float angle, float x, float y, float cx, float cy) {
        float tempX = x - cx;
        float tempY = y - cy;
        double rotatedY =  ((tempX * Math.sin(Math.toRadians(angle))) + (tempY * Math.cos(Math.toRadians(angle))));
        return (float) (rotatedY + cy);
    }

    public static float[] getTextureCordinates(Sprite sprite) {
        float r = Math.round((sprite.currentFrame - 1) / sprite.columns) + 1;
        float c = (sprite.currentFrame - 1) % sprite.columns + 1;
        float vertices[] = new float[8];

        vertices[0] = (c - 1) / sprite.columns;
        vertices[1] = (r - 1) / sprite.rows;
        vertices[2] = (c - 1) / sprite.columns;
        vertices[3] = r / sprite.rows;
        vertices[4] = c / sprite.columns;
        vertices[5] = r / sprite.rows;
        vertices[6] = c / sprite.columns;
        vertices[7] = (r - 1) / sprite.rows;
        return vertices;
    }

}
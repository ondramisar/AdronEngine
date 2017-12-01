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

        float x = 0;
        float y = 0;
        float height = 0;
        float width = 0;

       if (origin == LEFT_TOP) {
            float vertices[] = new float[12];

            x = (int) sprite.getX();
            y = (int) sprite.getY();
            height = (int) sprite.getHeight();
            width = (int) sprite.getWidth();

            x *= sprite.getEngine().getGridUnitX();
            y *= sprite.getEngine().getGridUnitY();

            height *= sprite.getEngine().getGridUnitY();
            width *= sprite.getEngine().getGridUnitX();

            vertices[0] = x;
            vertices[1] = y - height;
            vertices[2] = 0.0f;
            vertices[3] = x;
            vertices[4] = y;
            vertices[5] = 0.0f;
            vertices[6] = x + width;
            vertices[7] = y;
            vertices[8] = 0.0f;
            vertices[9] = x + width;
            vertices[10] = y - height;
            vertices[11] = 0.0f;
            return vertices;

        }
        return null;
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
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

            float angle = sprite.getAngle();

            if (angle != 0) {
                double sin;
                double cos;

                cos = Math.cos(Math.toRadians(angle + 180));
                sin = Math.sin(Math.toRadians(angle + 180));

        /*        vertices[0] = (float) ((x - (x - width / 2)) * cos - (y - (y - height / 2)) * sin + x);   // Bottom Left X
                vertices[1] = (float) ((x - (x - width / 2)) * sin + (y - (y - height / 2)) * cos + y);   // Bottom Left Y
                vertices[2] = (float) ((x - (x - width / 2)) * cos - (y - (y + height / 2)) * sin + x);   // Top Left X
                vertices[3] = (float) ((x - (x - width / 2)) * sin + (y - (y + height / 2)) * cos + y);   // Top Left Y
                vertices[4] = (float) ((x - (x + width / 2)) * cos - (y - (y - height / 2)) * sin + x);   // Bottom Right X
                vertices[5] = (float) ((x - (x + width / 2)) * sin + (y - (y - height / 2)) * cos + y);   // Bottom Right Y
                vertices[6] = (float) ((x - (x + width / 2)) * cos - (y - (y + height / 2)) * sin + x);   // Top Right X
                vertices[7] = (float) ((x - (x + width / 2)) * sin + (y - (y + height / 2)) * cos + y);   // Top Right Y
*/

                //TODO rotation
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
            } else {
                //TODO follow the camera
                /*
                x += sprite.getEngine().getCamera().getCameraLeftEdge();
                y += sprite.getEngine().getCamera().getCameraBottomEdge();*/
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
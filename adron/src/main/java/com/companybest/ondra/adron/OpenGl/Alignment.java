package com.companybest.ondra.adron.OpenGl;


//TODO Clean up
public class Alignment {

    public static final int CENTRE = 1;
    public static final int LEFT_TOP = 2;
    public static final int LEFT_BOTTOM = 0;

    /**
     * get the vertices for a Sprite with respect to the origin and the current orientation
     *
     * @param sprite Sprite to get the position details
     * @param origin int origin value
     * @return vertices
     */
    public static float[] getVertices(Sprite sprite, int origin) {

        float x = 0;
        float y = 0;
        float height = 0;
        float width = 0;

        if (origin == LEFT_BOTTOM) {
            float vertices[] = new float[8];
            /*{
                    sprite.getX(), sprite.getY() - sprite.getHeight(), 0.0f,  // 0, Top Left
                    sprite.getX(), sprite.getY(), 0.0f,  // 1, Bottom Left
                    sprite.getX() + sprite.getWidth(), sprite.getY(), 0.0f,  // 2, Bottom Right
                    sprite.getX() + sprite.getWidth(), sprite.getY() - sprite.getHeight(), 0.0f,  // 3, Top Right
            };*/
            return vertices;
        } else if (origin == LEFT_TOP) {
            float vertices[] = new float[12];
          /*  float vertices[] = {
                    sprite.getX(), sprite.getY(), 0.0f,  // 0, Top Left
                    sprite.getX(), sprite.getY() + sprite.getHeight(), 0.0f,  // 1, Bottom Left
                    sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(), 0.0f,  // 2, Bottom Right
                    sprite.getX() + sprite.getWidth(), sprite.getY(), 0.0f,  // 3, Top Right
            };*/

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
/*
            vertices[0] = sprite.getX();
            vertices[1] = sprite.getY() - sprite.getHeight();
            vertices[2] = 0.0f;
            vertices[3] = sprite.getX();
            vertices[4] = sprite.getY();
            vertices[5] = 0.0f;
            vertices[6] = sprite.getX() + sprite.getWidth();
            vertices[7] = sprite.getY();
            vertices[8] = 0.0f;
            vertices[9] = sprite.getX() + sprite.getWidth();
            vertices[10] = sprite.getY() - sprite.getHeight();
            vertices[11] = 0.0f;
*/
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

        /*
        // Data
        float leftX;   // Left X coordinate of the frame on the graphic sheet
        float rightX;  // Right X coordinate
        float topY;    // Top Y coordinate
        float bottomY; // Bottom Y coordinate

        float x = sprite.getX();
        float y = sprite.getY();
        float gWidth = sprite.getWidth();
        float gHeight = sprite.getHeight();

        float height;
        float width;


        height = 1;
        width = 1;


        leftX = x;
        rightX = x + gWidth;
        topY = y;
        bottomY = y + gHeight;

        leftX += 1f / (gWidth * width * 100f);      // Prevent other parts of the graphic from "spilling over" the edges.
        rightX -= 1f / (gWidth * width * 100f);
        topY += 1f / (gHeight * height * 100f);
        bottomY -= 1f / (gHeight * height * 100f);*/



		/*if(Configurations.orientation == Configurations.LANDSCAPE)
        {

			float r = Math.round((sprite.currentFrame-1)/sprite.coloumns)+1;
			float c = (sprite.currentFrame-1)%sprite.coloumns + 1;
			float vertices[] = {

					(float)(c/sprite.coloumns) ,(float) ((r-1)/sprite.rows),  // 0, Top Left
					(float)((c-1)/sprite.coloumns) ,(float) ((r-1)/sprite.rows),   // 1, Bottom Left
					(float) ((c-1)/sprite.coloumns) ,(float) (r/sprite.rows),  // 2, Bottom Right
					(float) ( c/sprite.coloumns),(float)(r/sprite.rows) // 3, Top Right
			};
			return vertices;
		}
		else
		{
			float vertices[] = {

					  0 , 0,  // 0, Top Left
					  0 , 1,   // 1, Bottom Left
				      1 , 1,  // 2, Bottom Right
				      1 , 0 // 3, Top Right
			};
			return vertices;
		}*/


    }

}
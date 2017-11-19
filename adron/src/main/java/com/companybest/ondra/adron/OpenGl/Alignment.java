package com.companybest.ondra.adron.OpenGl;

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
        if (origin == LEFT_BOTTOM) {
            float vertices[] = {
                    sprite.getX(), sprite.getY() - sprite.getHeight(), 0.0f,  // 0, Top Left
                    sprite.getX(), sprite.getY(), 0.0f,  // 1, Bottom Left
                    sprite.getX() + sprite.getWidth(), sprite.getY(), 0.0f,  // 2, Bottom Right
                    sprite.getX() + sprite.getWidth(), sprite.getY() - sprite.getHeight(), 0.0f,  // 3, Top Right
            };
            return vertices;
        } else if (origin == LEFT_TOP) {
            float vertices[] = {
                    sprite.getX(), sprite.getY(), 0.0f,  // 0, Top Left
                    sprite.getX(), sprite.getY() + sprite.getHeight(), 0.0f,  // 1, Bottom Left
                    sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(), 0.0f,  // 2, Bottom Right
                    sprite.getX() + sprite.getWidth(), sprite.getY(), 0.0f,  // 3, Top Right
            };
            return vertices;
        }
        return null;
    }


    public static float[] getTextureCordinates(Sprite sprite) {
        float r = Math.round((sprite.currentFrame - 1) / sprite.columns) + 1;
        float c = (sprite.currentFrame - 1) % sprite.columns + 1;
        float vertices[] = {
                (c - 1) / sprite.columns, (r - 1) / sprite.rows,
                (c - 1) / sprite.columns, r / sprite.rows,
                c / sprite.columns, r / sprite.rows,
                c / sprite.columns, (r - 1) / sprite.rows
        };
        return vertices;

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
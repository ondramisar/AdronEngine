package com.companybest.ondra.adronengine;

import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;

/**
 * Created by Ondra on 20.11.2017.
 */

public class Background extends Sprite{


    public Background(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    public Background(float x, float y, Texture texture) {
        super(x, y, texture);
    }

    public Background(float x, float y, int rows, int cols, int totalFrames, Texture texture) {
        super(x, y, rows, cols, totalFrames, texture);
    }
}

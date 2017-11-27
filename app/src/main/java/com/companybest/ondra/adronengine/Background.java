package com.companybest.ondra.adronengine;

import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.OpenGl.Sprite;
import com.companybest.ondra.adron.OpenGl.Texture;

/**
 * Created by Ondra on 20.11.2017.
 */

public class Background extends Sprite{
    public Background(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public Background(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }

    public Background(float x, float y, int rows, int cols, int totalFrames, Engine engine, Texture texture) {
        super(x, y, rows, cols, totalFrames, engine, texture);
    }
}

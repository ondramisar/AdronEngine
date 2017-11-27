package com.companybest.ondra.adron.OpenGl;

import com.companybest.ondra.adron.Engine.Engine;

public class Graphic extends Sprite {

    public Graphic(float x, float y, float width, float height, Engine engine, Texture texture) {
        super(x, y, width, height, engine, texture);
    }

    public Graphic(float x, float y, Engine engine, Texture texture) {
        super(x, y, engine, texture);
    }

    public Graphic(float x, float y, int rows, int cols, int totalFrames, Engine engine, Texture texture) {
        super(x, y, rows, cols, totalFrames, engine, texture);
    }
}

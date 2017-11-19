package com.companybest.ondra.adronengine.OpenGl;

public class Graphic extends Sprite {

    public Graphic(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    public Graphic(float x, float y, int rows, int coloumns, int totalFrames, Texture texture) {
        super(x, y, rows, coloumns, totalFrames, texture);
    }


    public Graphic(float x, float y, Texture texture) {
        super(x, y, texture);
    }


}

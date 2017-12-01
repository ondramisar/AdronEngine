package com.companybest.ondra.adron.Collisions;

import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.EntityComponents;
import com.companybest.ondra.adron.OpenGl.Alignment;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.GL_BLEND;
import static javax.microedition.khronos.opengles.GL10.GL_ONE_MINUS_SRC_ALPHA;
import static javax.microedition.khronos.opengles.GL10.GL_SRC_ALPHA;


public class HitBox extends Entity {
    public CollisionBox c;    // The collidable from which this hit box was generated.

    public float x; // The x position on the cell grid of the upper left corner of this box.
    public float y; // The y position on the cell grid of the upper left corner of this box.
    public float w; // The width in cells on the cell grid of this box.
    public float h; // The width in cells on the cell grid of this box.

    private short[] indices = {0, 1, 2, 0, 3, 2};

    private FloatBuffer vertexBuffer;

    private ShortBuffer indexBuffer;
    private FloatBuffer mTextureBuffer;
    float[] textureCoordinates;

    private float red = 100;
    private float green = 100;
    private float blue = 100;
    private float alpha = 100;

    private Entity owner;


    /**
     * Generates a new hit box.
     *
     * @param c
     */
    public HitBox(CollisionBox c, Entity entity) {
        this.owner = entity;
        this.c = c;
        setEngine(owner.getEngine());
    }

    /**
     * Refreshes this hit box to reflect changes in c's position, dimensions, and scale.
     */
    public void update() {
        EntityComponents t = c.getEntityComponentsForColision();

        x = t.getX() + t.getWidth() / 8;
        y = t.getY() - t.getHeight() / 10;
        w = t.getWidth() - t.getWidth() / 5;
        h = t.getHeight() - t.getHeight() / 5;
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
    }

    /**
     * same as draw in a Sprite but doesn't render a texture
     * @param gl
     */
    @Override
    public void draw(GL10 gl) {
        textureCoordinates = Alignment.getVertices(this, Alignment.LEFT_TOP);
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());

        mTextureBuffer = byteBuf.asFloatBuffer();
        mTextureBuffer.put(textureCoordinates);
        mTextureBuffer.position(0);

        float vertices[] = Alignment.getVertices(this, Alignment.LEFT_TOP);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());

        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());

        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        gl.glColor4f(red / 100, green / 100, blue / 100, alpha / 100);

        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);


        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    public void setRGB(String col) {
        try {
            int hash = Integer.decode(col);
            setRGB(hash);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(col + " is not an integer.");
        }
    }

    public void setRGBA(String col) {
        try {
            int hash = Integer.decode(col);
            setRGBA(hash);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(col + " is not an integer.");
        }
    }

    public void setRGB(int hash) {
        int r = (hash & 0xFF0000) >> 16;
        int g = (hash & 0x00FF00) >> 8;
        int b = hash & 0x0000FF;
        red = r / 255 * 100;
        green = g / 255 * 100;
        blue = b / 255 * 100;
    }

    public void setRGBA(int hash) {
        int r = (hash & 0xFF000000) >> 32;
        int g = (hash & 0x00FF0000) >> 16;
        int b = (hash & 0x0000FF00) >> 8;
        int a = hash & 0x000000FF;
        red = r / 255 * 100;
        green = g / 255 * 100;
        blue = b / 255 * 100;
        alpha = a / 255 * 100;
    }

    /**
     * @return the red
     */
    public float getRed() {
        return red;
    }

    /**
     * @param red the red to set
     */
    public void setRed(float red) {
        this.red = red;
    }

    /**
     * @return the green
     */
    public float getGreen() {
        return green;
    }

    /**
     * @param green the green to set
     */
    public void setGreen(float green) {
        this.green = green;
    }

    /**
     * @return the blue
     */
    public float getBlue() {
        return blue;
    }

    /**
     * @param blue the blue to set
     */
    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
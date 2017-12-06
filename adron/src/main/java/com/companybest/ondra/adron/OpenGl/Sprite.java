package com.companybest.ondra.adron.OpenGl;

import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.GL_BLEND;
import static javax.microedition.khronos.opengles.GL10.GL_ONE_MINUS_SRC_ALPHA;
import static javax.microedition.khronos.opengles.GL10.GL_SRC_ALPHA;

public class Sprite extends Entity {

    // Variables for animation
    public int currentFrame;
    public int rows;
    public int columns;

    private double fps = 0;
    private int start = 0;
    private int end = 0;
    private int gameFramesPassed = 0;

    private int timesPlayed = 0;
    private int loop = 0;
    private boolean animFinished = false;

    private boolean visible;


    /**
     * name of the sprite to identify
     */
    public String name;

    /**
     * unique id of each sprite
     */
    public int id;

    int spriteId;

    /**
     * texture
     */
    private Texture texture;

    /**
     * Alpha determines the transparency of the sprite takes values from 0-100
     * default is 100
     */
    public float alpha;

    //Color variables
    private float red = 100;
    private float green = 100;
    private float blue = 100;


    short[] indices = {0, 1, 2, 0, 3, 2};

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;
    private FloatBuffer mTextureBuffer;
    float[] textureCoordinates;

    /**
     * Create new Sprite
     *
     * @param x       x position
     * @param y       y position
     * @param texture texture of the Sprite
     */
    public Sprite(float x, float y, Engine engine, Texture texture) {

        super(x, y, texture.getWidth(), texture.getHeight(), engine);
        this.texture = texture;
        Initialize();

    }

    /**
     * Create new Sprite
     *
     * @param x       x position
     * @param y       y position
     * @param width   width of the Sprite
     * @param height  heigt of the Sprite
     * @param texture texture of the Sprite
     */
    public Sprite(float x, float y, float width, float height, Engine engine, Texture texture) {

        super(x, y, width, height, engine);
        this.texture = texture;
        Initialize();

    }

    /**
     * Create new Sprite with animation
     *
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @param texture
     */
    public Sprite(float x, float y, int rows, int cols, Engine engine, Texture texture) {
        super(x, y, texture.getWidth(), texture.getHeight(), engine);
        Initialize();
        this.rows = rows;
        this.columns = cols;
        this.texture = texture;
    }

    /**
     * Create new Sprite with animation
     *
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @param texture
     */
    public Sprite(float x, float y, float width, float height, int rows, int cols, Engine engine, Texture texture) {
        super(x, y, width, height, engine);
        Initialize();
        this.rows = rows;
        this.columns = cols;
        this.texture = texture;
    }

    /**
     * Initialize the values
     */
    private void Initialize() {
        this.id = spriteId++;
        currentFrame = 1;
        this.visible = true;
        setAngle(0);
        this.alpha = 100;
        this.rows = 1;
        this.columns = 1;
    }

    /**
     * start animating
     *
     * @param fps
     * @param start
     * @param end
     */
    public void animate(double fps, int start, int end) {
        animate(fps, start, end, 0);
    }

    /**
     * start animation with number of loops to be done
     *
     * @param fps
     * @param start
     * @param end
     * @param loop
     */
    public void animate(double fps, int start, int end, int loop) {
        if (this.fps != fps || this.start != start || this.end != end || this.loop != loop) {
            timesPlayed = 0;
        }
        this.fps = fps;
        this.start = start;
        this.end = end;
        this.loop = loop;

        if (fps == 0) {
            stopAnimation(start);
        }
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
     * Stop the animation. The object will display the most recently shown frame
     */
    public void stopAnimation() {
        stopAnimation(currentFrame);
    }

    /**
     * Stop the current animation and set the frame to display.
     *
     * @param frame
     */
    public void stopAnimation(int frame) {
        fps = 0;
        this.currentFrame = frame;
        this.start = frame;
        this.end = frame;
        this.loop = 0;
    }

    /**
     * To draw the Sprite to the screen
     */
    @Override
    public void draw(GL10 gl) {
        if (!visible) {
            return;
        }

        animFinished = false;

        if (fps > 0 && ((loop > 0 && timesPlayed <= loop) || loop == 0)) {    // Should animate if: fps > 0 and we've looped less than the max loops OR max loops is 0 so we should loop forever.
            if (start <= end) {                                               // Loop forward (start frame is less than end frame)
                if (currentFrame < start || currentFrame > end) {
                    currentFrame = start;
                }
            } else {                                                          // Loop backwards (start frame is greater than end frame)
                if (currentFrame > start || currentFrame < end) {                           // Go back to the start frame if frame is out of animation range
                    currentFrame = start;
                }
            }

            if (gameFramesPassed >= 60 / fps) {    // Determine if we should go to the next frame
                if (start <= end)
                    currentFrame++;                      // Animating forwards, increase frame
                else
                    currentFrame--;                                   // Animating backwards, decrease frame
                gameFramesPassed = 0;                           // Reset counter
            } else {
                gameFramesPassed++;                             // Increase counter
            }

            if (start <= end) {             // If animating forwards
                if (currentFrame > end) {          // Reached the end of the animation
                    currentFrame = start;          // Go back to start of the animation
                    animFinished = true;    // The animation finished.
                    timesPlayed++;          // Increment times played.
                }
            } else {                        // If animating backwards
                if (currentFrame < end) {          // Reached end of the animation
                    animFinished = true;    // The animation finished.
                    currentFrame = start;          // Go back to the start of the animation
                    timesPlayed++;          // Increment times played.
                }
            }
        } else {                   // Should not animate anymore.
            animFinished = true;   // Animation over.
        }

        if (timesPlayed >= loop && loop > 0) { // Done looping
            currentFrame = end;
        }


        textureCoordinates = Alignment.getTextureCordinates(this);
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

        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        if (texture != null) {
            if (texture.getId() != -1 && mTextureBuffer != null) {
                gl.glEnable(GL10.GL_TEXTURE_2D);
                gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
                gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.getId());
            }
        }

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        //gl.glPushMatrix();
       // gl.glRotatef(getAngle(), getX()- getWidth() /2, getY() - getHeight() / 2, 0);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
     //   gl.glPopMatrix();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        if (texture != null) {
            if (texture.getId() != -1 && mTextureBuffer != null) {
                gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            }
        }

        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    /**
     * Set the name of the sprite
     *
     * @param name - String
     */
    public void SetName(String name) {
        this.name = name;
    }


    /**
     * Check if a point is inside an object
     *
     * @param x - x coordinate of the point
     * @param y - y coordinate of the point
     * @return true if its inside false otherwise
     */
    public boolean HitTest(float x, float y) {
        return super.HitTest(x, y);
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


    public Sprite getOwner() {
        return this;
    }
}



package com.companybest.ondra.adron.OpenGl;

import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Entity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Sprite extends Entity {

    public int currentFrame;
    public int totalFrames;
    public int rows;
    public int columns;

    private boolean visible;

    public boolean isPlaying;

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


    private int origin = 0;

    /**
     * Alpha determines the transparency of the sprite takes values from 0-100
     * default is 100
     */
    public float alpha;

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
     * @param width   width of the Sprite
     * @param height  heigt of the Sprite
     * @param texture texture of the Sprite
     */
    public Sprite(float x, float y, float width, float height, Engine engine, Texture texture) {

        super(x, y, width, height,engine );
        this.texture = texture;
        Initialize();

    }

    /**
     * Create new Sprite
     *
     * @param x       x position
     * @param y       y position
     * @param texture texture of the Sprite
     */
    public Sprite(float x, float y,Engine engine, Texture texture) {

        super(x, y, texture.getWidth(), texture.getHeight(), engine);
        this.texture = texture;
        Initialize();

    }

    /**
     * Create new Sprite with animation
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @param totalFrames
     * @param texture
     */
    public Sprite(float x, float y, int rows, int cols, int totalFrames, Engine engine,  Texture texture) {
        super(x, y, texture.getWidth() / cols, texture.getHeight() / rows, engine);

        Initialize();

        this.rows = rows;
        this.columns = cols;

        if (totalFrames > 0)
            isPlaying = true;

        this.texture = texture;
        this.totalFrames = totalFrames;

    }

    /**
     * Initialize the values
     */
    private void Initialize() {
        this.id = spriteId++;
        currentFrame = 1;
        isPlaying = false;
        this.visible = true;
        this.alpha = 100;
        this.rows = 1;
        this.columns = 1;
    }

    /**
     * play animation
     */
    public void play() {
        isPlaying = true;
    }

    /**
     * stop animation
     */
    public void stop() {
        isPlaying = false;
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
     * @param frame
     */
    public void gotoFrame(int frame) {
        if (frame <= totalFrames)
            currentFrame = frame;
    }

    /**
     * To draw the Sprite to the screen
     */
    @Override
    public void draw(GL10 gl) {
        if (!visible) {
            return;
        }
        if (isPlaying) {
            currentFrame++;
            if (currentFrame > totalFrames)
                currentFrame = 1;
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

        if (texture != null) {
            if (texture.getId() != -1 && mTextureBuffer != null) {
                gl.glEnable(GL10.GL_TEXTURE_2D);
                gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
                gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.getId());
            }
        }
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
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        if (texture.getId() != -1 && mTextureBuffer != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }

        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    /**
     * set the texture of the Sprite
     *
     * @param texture Texture
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
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
     * Handle the Move Event
     *
     * @param x
     * @param y
     */
    public void HandleMove(float x, float y) {
        onMove(x, y);
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


    public void onMove(float x, float y) {

    }

    public void ReleaseOutside(float x, float y) {

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

    public Sprite getOwner(){
        return this;
    }
}



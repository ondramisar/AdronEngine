package com.companybest.ondra.adronengine.OpenGl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.companybest.ondra.adronengine.Entity.Entity;

import javax.microedition.khronos.opengles.GL10;

public class Texture extends Entity {

    private int cleanupsTilRemoval;  // Number of cleanups  this Graphic should go through
    private boolean shouldBeLoaded;  // indicates this Graphic should be loaded
    private boolean shouldRemove;    // indicates this Graphic should be removed from the TextureLibrary
    private boolean isLoaded;   // indicates if this graphic is loaded
    public boolean persistent;  // Indicates whether this graphic can be during a cleanup


    private int mTextureId = -1;

    public boolean mShouldLoadTexture;

    // The bitmap we want to load as a texture.
    private Bitmap mBitmap;
    private float width;
    private float height;

    /**
     * Texture creating with just drawable
     * @param context
     * @param drawable
     * @param textureLibrary
     */
    public Texture(Context context, int drawable, TextureLibrary textureLibrary) {
        BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
        mBitmapOptions.inScaled = true;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        cleanupsTilRemoval = 2;
        persistent = false;
        setWidth(mBitmap.getWidth());
        setHeight(mBitmap.getHeight());

        textureLibrary.insert(this);
    }

    /**
     * Texture creating with bitmap
     * @param bitmap
     * @param textureLibrary
     */
    public Texture(Bitmap bitmap, TextureLibrary textureLibrary) {
        this.mBitmap = bitmap;
        cleanupsTilRemoval = 2;
        persistent = false;
        setWidth(mBitmap.getWidth());
        setHeight(mBitmap.getHeight());
        textureLibrary.insert(this);
    }


    public Bitmap getBitmap() {
        return mBitmap;
    }

    /**
     * Set the bitmap to load into a texture.
     *
     * @param bitmap The bitmap Image
     */
    public void loadBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    /**
     * get the texture ID
     *
     * @return mTextureId - Texture Id
     */
    public int getId() {
        return mTextureId;
    }

    public void setId(int id){
        mTextureId = id;
    }

    /**
     * Loads the texture.
     *
     * @param gl
     */
    public void loadGLTexture(GL10 gl) {
        if (mBitmap == null) {
            System.out.println("Texture not loaded yet");
            return;
        }
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        mTextureId = textures[0];

        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        // release the bitmap
        mBitmap.recycle();

        loaded();
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }


    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    public void load() {
        shouldBeLoaded = true;
    }

    public void unload() {
        shouldBeLoaded = false;
    }

    public void indicateUsed(int cleanupsTilRemoval) {
        this.cleanupsTilRemoval = cleanupsTilRemoval;
    }

    public void cleanup() {
        if (cleanupsTilRemoval == 0) {
            remove();
        } else if (!persistent) {
            cleanupsTilRemoval--;
        }
    }

    public void forceCleanup() {
        cleanupsTilRemoval = 0;
        remove();
    }


    public void remove() {
        shouldRemove = true;
    }


    public boolean isLoaded() {
        return isLoaded;
    }


    public boolean shouldLoad() {
        return shouldBeLoaded && !isLoaded();
    }


    public boolean shouldUnload() {
        return !shouldBeLoaded && isLoaded();
    }


    public boolean shouldRemove() {
        return shouldRemove;
    }


    public void finished() {
        isLoaded = shouldBeLoaded;
    }

    public void loaded() {
        isLoaded = true;
    }


    public void deleted() {
        isLoaded = false;
    }


    public void removed() {
        shouldRemove = false;
    }



}

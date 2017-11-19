package com.companybest.ondra.adronengine.OpenGl;

import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class TextureLibrary {
    private static ArrayList<Texture> textures;
    private int numGFX;
    private int cleanupsTilRemoval;
    private int maxGraphic;


    public TextureLibrary() {
        textures = new ArrayList<>();
        numGFX = 0;
        maxGraphic = 50;
        cleanupsTilRemoval = 2;
    }

    /**
     * insert a texture to the texture library
     *
     * @param texture Texture - the texture to be inserted
     */
    public void insert(Texture texture) {
        numGFX++;

        if (numGFX >= maxGraphic) {                           // Hit max graphics
            cleanUp();                              // Try to get rid of some that haven't been used recently

            if (numGFX >= maxGraphic) {
                maxGraphic += 1;
            }
        }

        texture.indicateUsed(cleanupsTilRemoval);
        texture.load();
        textures.add(texture);
    }


    /**
     * Load all the textures
     *
     * @param gl
     */
    public void LoadTextures(GL10 gl) {

        int sampleSize = 1;
        boolean success = false;
        boolean changed = false;

        do {
            try {
                for (Texture texture : textures) {
                    if (texture != null) {
                        if (texture.shouldLoad()) {
                            texture.loadGLTexture(gl);
                            changed = true;
                        } else if (texture.shouldUnload()) {
                            unloadGraphic(gl, texture);
                            changed = true;
                        } else if (texture.shouldRemove()) {
                            unloadGraphic(gl, texture);
                            textures.remove(texture);
                            numGFX--;
                            changed = true;
                        }
                    }
                }
                success = true;
            } catch (OutOfMemoryError e) {
                sampleSize++;
                Log.e("BobEngine", "Not enough memory. Retrying in sample size " + Integer.toString(sampleSize));
                success = false;
            }
        } while (!success);  // Try again

        if (changed) gl.glFinish();
    }

    public void cleanUp() {
        for (Texture texture : textures) {
            if (texture != null) {
                texture.cleanup();
            }
        }
    }

    private void unloadGraphic(GL10 gl, Texture texture) {
        int[] tex = {texture.getId()};
        gl.glDeleteTextures(1, tex, 0);
        texture.deleted();
    }
}

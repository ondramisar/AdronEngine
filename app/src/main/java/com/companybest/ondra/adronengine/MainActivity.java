package com.companybest.ondra.adronengine;

import android.os.Bundle;

import com.companybest.ondra.adron.BaseClasses.BasicAdrClass;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Camera;
import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.Entity.Scene;
import com.companybest.ondra.adron.OpenGl.Texture;
import com.companybest.ondra.adron.OpenGl.TextureLibrary;
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView;
import com.companybest.ondra.adronengine.Objects.Bird;
import com.companybest.ondra.adronengine.Objects.Obsticle;
import com.companybest.ondra.adronengine.Objects.ObsticlePair;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends BasicAdrClass {

    TextureLibrary textureLibrary;
    Camera camera;
    Bird bird;
    Obsticle mObsticleTop1;
    Obsticle mObsticlebottom1;
    Obsticle mObsticleTop2;
    Obsticle mObsticleBottom2;
    ObsticlePair obsticlePair;
    private double vy;
    private final double ACC = .4;      // The acceleration of gravity. Determines how fast the bug falls.
    private final double JUMP_V = 10.2;  // Velocity to give the bug to make it jump.
    private final int SPACE = 90;       // The space between each flower in grid units

    CollisionSystem mCollisionSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureLibrary = new TextureLibrary();

        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.my_view);
        setUpEngine(new Engine(this, textureLibrary, true), this, adrGlSurfaceView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textureLibrary.cleanUp();
    }


    @Override
    public Scene setUpScene() {
        Scene scene = new Scene();

        //  TileMapData t = TMXLoader.readTMX("lvl1.tmx", this);

        //   Bitmap mapImage = TMXLoader.createBitmap(t, this, 0, t.layers.size());

        // Background background = new Background(0 , 0, new Texture(mapImage, getTextureLibrary()));


        camera = new Camera(0, 0, 1, 0, 0, getEngine());
        mCollisionSystem = new CollisionSystem();

        bird = new Bird(100, getHeight() / 2, 50, 50, getEngine(), new Texture(getApplicationContext(), R.drawable.ic_launcher, getTextureLibrary()), mCollisionSystem, this);
        scene.addComponent(bird);

        int height = getHeight();

        Random r = new Random();
        int y1 = r.nextInt(50);
        int y4 = r.nextInt(50);
        int y2 = r.nextInt(getHeight() - getHeight() / 2) + getHeight() / 2;
        int y3 = r.nextInt(getHeight() - getHeight() / 2) + getHeight() / 2;


    /*    mObsticleTop1 = new Obsticle(getWidth(), y1, 50, 600,getEngine(), new Texture(getApplicationContext(), R.drawable.pipe, getTextureLibrary()), mCollisionSystem);
        mObsticleTop2 = new Obsticle(getWidth() - 70, y4, 50, 600,getEngine(), new Texture(getApplicationContext(), R.drawable.pipe, getTextureLibrary()), mCollisionSystem);


        mObsticlebottom1 = new Obsticle(getWidth(), getHeight(), 50, 200,getEngine(), new Texture(getApplicationContext(), R.drawable.pipe, getTextureLibrary()), mCollisionSystem);
        mObsticleBottom2 = new Obsticle(getWidth() - 70, getHeight(), 50, 200,getEngine(), new Texture(getApplicationContext(), R.drawable.pipe, getTextureLibrary()), mCollisionSystem);

        scene.addComponent(mObsticleTop1);
        scene.addComponent(mObsticlebottom1);
        scene.addComponent(mObsticleTop2);
        scene.addComponent(mObsticleBottom2);*/

        obsticlePair = new ObsticlePair(0, 0, getEngine(), new Texture(getApplicationContext(), R.drawable.pipe, getTextureLibrary()), mCollisionSystem);
        scene.addComponent(obsticlePair);
        obsticlePair.sendToLeftEdge();
        scene.addComponent(mCollisionSystem);
        scene.addComponent(camera);


        //  scene.addComponent(background);

        return scene;
    }

    @Override
    public void update(float dt) {
        vy += ACC;
        bird.setY((float) (bird.getY() + vy));

        Random r = new Random();

        if (bird.getY() > getHeight()) {
            bird.setY(10);
        }
    /*    if (distance >= SPACE) {

            // Move the next flowers off the right side of the screen
            flowers[currentFlower].sendToRightEdge();

            // Random y positions
            flowers[currentFlower].randomizeY();

            // Increment to the next flower.
            currentFlower++;
            if (currentFlower == NUM_FLOW) currentFlower = 0;

            distance = 0;
        }*/


      /*  float speed = 1.f;

        mObsticleTop1.setX(mObsticleTop1.getX() - speed);

        if (mObsticleTop1.getX() < 0) {

            int y1 = r.nextInt(400) - 550;
            mObsticleTop1.setX(getWidth() + 10);
            mObsticleTop1.setY(y1);
        }

        mObsticlebottom1.setX(mObsticlebottom1.getX() - speed);

        if (mObsticlebottom1.getX() < 0) {
            int y2 = r.nextInt((getHeight() - 400) - (getHeight() / 2 + 200)) + (getHeight() / 2 + 200);
            mObsticlebottom1.setX(getWidth() + 10);
            mObsticlebottom1.setY(y2);
        }

        mObsticleTop2.setX(mObsticleTop2.getX() - speed);

        if (mObsticleTop2.getX() < 0) {
            int y4 = r.nextInt(400) - 550;
            mObsticleTop2.setX(getWidth() + 10);
            mObsticleTop2.setY(y4);
        }

        mObsticleBottom2.setX(mObsticleBottom2.getX() - speed);

        if (mObsticleBottom2.getX() < 0) {
            int y3 = r.nextInt((getHeight() - 400) - (getHeight() / 2 + 200)) + (getHeight() / 2 + 200);
            mObsticleBottom2.setX(getWidth() + 10);
            mObsticleBottom2.setY(y3);
        }*/

    }


    @Override
    public void onSurfaceCreated(GL10 gl10) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight);
        getEngine().setGridHeight(800);
        getEngine().setGridUnitX(getEngine().getGridUnitY());
    }

    @Override
    public void onPress(float x, float y) {
        if (vy > 10) {
            vy = JUMP_V;
        }

        vy -= JUMP_V;
        //bird.setY((float) (bird.getY() - vy));
    }

    @Override
    public void onRelease(float x, float y) {

    }

    @Override
    public void onMove(float x, float y) {

    }

    @Override
    public void onPress(Entity entity) {

    }

    @Override
    public void onRelease(Entity entity) {

    }

    @Override
    public void onMove(Entity entity) {

    }

    @Override
    public void onReleaseOutside(Entity entity) {

    }
}

package com.companybest.ondra.adronengine;

import android.os.Bundle;

import com.companybest.ondra.adron.BaseClasses.BasicAdrClass;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Camera;
import com.companybest.ondra.adron.Entity.Scene;
import com.companybest.ondra.adron.OpenGl.Texture;
import com.companybest.ondra.adron.OpenGl.TextureLibrary;
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView;
import com.companybest.ondra.adronengine.Objects.CrossyObsticle;
import com.companybest.ondra.adronengine.Objects.CrossyPlayer;

import java.math.BigDecimal;

import javax.microedition.khronos.opengles.GL10;

public class CrossyActivity extends BasicAdrClass {

    private CrossyPlayer crossyPlayer;
    private CrossyObsticle[] mObsticles = new CrossyObsticle[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossy);

        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.corssy_view);
        Engine engine = new Engine(this, new TextureLibrary(), true);
        setUpEngine(engine, this, adrGlSurfaceView);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight);
        getEngine().setGridHeight(200);
        getEngine().setGridUnitX(getEngine().getGridUnitY());
    }

    @Override
    public Scene setUpScene() {
        Scene scene = new Scene();

        Camera camera = new Camera(0, 0, 1, getWidth() / 2, getHeight() / 2, getEngine());
        scene.addComponent(camera);
        CollisionSystem collisionSystem = new CollisionSystem(getEngine());
        scene.addComponent(collisionSystem);
        Texture texture = new Texture(getApplicationContext(), R.drawable.ic_launcher, getTextureLibrary());
        crossyPlayer = new CrossyPlayer(getWidth() / 2 - 10, getHeight() - 20, 20, 20, getEngine(), texture, collisionSystem, this);
        scene.addComponent(crossyPlayer);

        for (int i = 0; i < mObsticles.length; i++) {
            int x = (int) (Math.random() * (getWidth() - 10) + 10);
            mObsticles[i] = new CrossyObsticle(x, 0 - (i * 20), 20, 20, getEngine(), texture, collisionSystem);
            scene.addComponent(mObsticles[i]);
        }
        collisionSystem.showBoxes(true, scene);

        return scene;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void onPress(float x, float y) {
        super.onPress(x, y);

        for (CrossyObsticle crossyObsticle : mObsticles) {
            if (crossyObsticle.getY() > getHeight() + 20) {
                int xPos = (int) (Math.random() * (getWidth() - 10) + 10);
                crossyObsticle.setY(-20);
                crossyObsticle.setX(xPos);
            } else {
                crossyObsticle.setY(crossyObsticle.getY() + 20);
            }
        }
    }

    @Override
    public void onGestureRight() {
        super.onGestureRight();
        if (!(crossyPlayer.getX() > getWidth() - 20)) {
            float goTo = crossyPlayer.getX() + 20f;
            while (crossyPlayer.getX() != goTo) {
                crossyPlayer.setX(round(crossyPlayer.getX() + 0.1f, 2));
            }
        }
    }

    @Override
    public void onGestureLeft() {
        super.onGestureLeft();
        if (!(crossyPlayer.getX() < 20)) {
            float goTo = crossyPlayer.getX() - 20f;
            while (crossyPlayer.getX() != goTo) {
                crossyPlayer.setX(round(crossyPlayer.getX() - 0.1f, 2));
            }
        }
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}

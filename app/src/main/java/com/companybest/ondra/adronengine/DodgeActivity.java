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
import com.companybest.ondra.adronengine.Objects.DodgePlayer;

import javax.microedition.khronos.opengles.GL10;

public class DodgeActivity extends BasicAdrClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodge);

        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.dodgeView);
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
    public void onSurfaceCreated(GL10 gl10) {
        super.onSurfaceCreated(gl10);
    }

    @Override
    public Scene setUpScene() {
        Scene scene = new Scene();

        CollisionSystem collisionSystem = new CollisionSystem(getEngine(), scene);

        Camera camera = new Camera(0, 0, 1, getWidth() / 2, getHeight() / 2, getEngine());
        DodgePlayer dodgePlayer = new DodgePlayer(getWidth() / 2 - 15, getHeight() - 50, 30, 30, getEngine(), new Texture(getApplicationContext(), R.drawable.ic_launcher, getTextureLibrary()), collisionSystem);
        dodgePlayer.setName("Player");
        scene.addComponent(collisionSystem);
        scene.addComponent(dodgePlayer);
        scene.addComponent(camera);
        collisionSystem.showBoxes(true);
        return scene;
    }

    @Override
    public void update(float dt) {
        DodgePlayer dodgePlayer = (DodgePlayer) getScene().findEntity("Player");

    }

    @Override
    public void onPress(float x, float y) {
        DodgePlayer dodgePlayer = (DodgePlayer) getScene().findEntity("Player");
        if (x > getWidth() / 2) {
            if (!(dodgePlayer.getX() + getWidth() / 3 > getWidth()))
                dodgePlayer.setX(dodgePlayer.getX() + getWidth() / 3);
        }

        if (x < getWidth() / 2) {
            if (!(dodgePlayer.getX() - getWidth() / 3 < 0))
                dodgePlayer.setX(dodgePlayer.getX() - getWidth() / 3);
        }
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

package com.companybest.ondra.adronengine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class TapMiniGameActivity extends BasicAdrClass {

    TextView count;
    int couter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_mini_game);

        count = findViewById(R.id.count);

        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.minigamesurface);
        Engine engine = new Engine(this, new TextureLibrary(), true);
        setUpEngine(engine, this, adrGlSurfaceView);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight);
        getEngine().setGridHeight(400);
        getEngine().setGridUnitX(getEngine().getGridUnitY());
    }

    @Override
    public Scene setUpScene() {
        Scene scene = new Scene();

        CollisionSystem collisionSystem = new CollisionSystem();

        Camera camera = new Camera(0, 0, 1, 0, 0, getEngine());


        Random r = new Random();
        int y = r.nextInt(getHeight());
        int x = r.nextInt(getWidth());

        int side = r.nextInt(4);
        Bird bird = new Bird(x, y, 35 , 35,getEngine(), new Texture(getApplicationContext(), R.drawable.ic_launcher, getTextureLibrary()), collisionSystem, this);
        bird.setName("BIRD");
        bird.setSide(side);

        scene.addComponent(camera);
        scene.addComponent(collisionSystem);
        scene.addComponent(bird);
        return scene;
    }

    @Override
    public void update(float dt) {
        Bird bird = (Bird) getScene().findEntity("BIRD");


        if (bird.getSide() == 0) {
            bird.setX(bird.getX() + 1f);
        } else if (bird.getSide() == 1) {
            bird.setX(bird.getX() - 1f);
        } else if (bird.getSide() == 2) {
            bird.setY(bird.getY() + 1f);
        } else if (bird.getSide() == 3) {
            bird.setY(bird.getY() - 1f);
        }

        if (bird.getX() < 0 || bird.getX() > getWidth() || bird.getY() < 0 || bird.getY() > getHeight()) {

            Intent myIntent = new Intent(this, Main2Activity.class);
            adrStartActivity(this, myIntent);
        }
    }

    @Override
    public void onPress(float x, float y) {
        Bird bird = (Bird) getScene().findEntity("BIRD");
        if (x < bird.getX() + bird.getWidth() && (x + bird.getWidth()  > bird.getX()) && (y < bird.getY() + bird.getHeight()) && (y + bird.getHeight() > bird.getY())) {
            couter += 1;
            Random r = new Random();
            int yNew = r.nextInt(getHeight());
            int xNew = r.nextInt(getWidth());

            int side = r.nextInt(4);
            if (xNew < 0)
                xNew = 0;

            if (yNew < 0)
                yNew = 0;

            bird.setPosition(xNew, yNew);
            bird.setSide(side);
            count.setText(String.valueOf(couter));
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

package com.companybest.ondra.adronengine;

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
import com.companybest.ondra.adronengine.Objects.DodgePlayer;

import javax.microedition.khronos.opengles.GL10;

public class DodgeActivity extends BasicAdrClass {

    float firstPosition;
    float secondPosition;
    float thirdPosition;
    float speed;

    TextView score;
    int scoreNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodge);
        score = findViewById(R.id.score_dodge);
        scoreNum = 0;
        score.setText(String.valueOf(scoreNum));
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
        secondPosition = getWidth() / 2 - 15;
        thirdPosition = secondPosition +  getWidth() / 3;
        firstPosition = secondPosition - getWidth() / 3;
        speed = 2;


        Texture texture = new Texture(getApplicationContext(), R.drawable.ic_launcher, getTextureLibrary());
        CollisionSystem collisionSystem = new CollisionSystem(getEngine());

        Camera camera = new Camera(0, 0, 1, getWidth() / 2, getHeight() / 2, getEngine());
        DodgePlayer dodgePlayer = new DodgePlayer(getWidth() / 2 - 15, getHeight() - 50, 30, 30, getEngine(), texture, collisionSystem,this);
        dodgePlayer.setName("Player");


        DodgePlayer obsticle = new DodgePlayer(getWidth() / 2 - 15, -50, 30, 30, getEngine(), texture, collisionSystem, this);
        obsticle.setName("Obsticle");

        scene.addComponent(collisionSystem);
        scene.addComponent(dodgePlayer);
        scene.addComponent(obsticle);
        scene.addComponent(camera);
        collisionSystem.showBoxes(true, scene);
        return scene;
    }

    @Override
    public void update(float dt) {
        DodgePlayer obsticle = (DodgePlayer) getScene().findEntity("Obsticle");

        obsticle.setY(obsticle.getY() + speed);
        if (obsticle.getY() > getHeight()){
            scoreNum += 1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    score.setText(String.valueOf(scoreNum));
                }
            });
            speed += 0.1;
            obsticle.setY(-20);
            int i = (int) (Math.random() * 120 + 30);
            if (i < 60 && i > 30) {
                obsticle.setX(firstPosition);
            } else if (i < 90 && i > 60) {
                obsticle.setX(secondPosition);
            } else if (i < 120 && i > 90) {
                obsticle.setX(thirdPosition);
            }
        }
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

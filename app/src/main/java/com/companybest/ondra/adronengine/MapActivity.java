package com.companybest.ondra.adronengine;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.companybest.ondra.adron.BaseClasses.BasicAdrClass;
import com.companybest.ondra.adron.Collisions.CollisionSystem;
import com.companybest.ondra.adron.Engine.Engine;
import com.companybest.ondra.adron.Entity.Camera;
import com.companybest.ondra.adron.Entity.Scene;
import com.companybest.ondra.adron.MapLoader.TMXLoader;
import com.companybest.ondra.adron.MapLoader.TileMapData;
import com.companybest.ondra.adron.OpenGl.Texture;
import com.companybest.ondra.adron.OpenGl.TextureLibrary;
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView;
import com.companybest.ondra.adronengine.Objects.Background;
import com.companybest.ondra.adronengine.Objects.DodgePlayer;

import javax.microedition.khronos.opengles.GL10;

public class MapActivity extends BasicAdrClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.map);
        Engine engine = new Engine(this, new TextureLibrary(), true);
        engine.setFpsOutput(true);
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

        //  TileMapData t = TMXLoader.readTMX("lvl1.tmx", this);
        TileMapData t = TMXLoader.readTMX("lvl1.tmx", this);

        Bitmap mapImage = TMXLoader.createBitmap(t, this, 0, t.layers.size());



        Texture map = new Texture(mapImage, getTextureLibrary());
        Background background = new Background(0, getHeight(), map.getWidth(), getHeight(), getEngine(), map);
        scene.addComponent(background);
        background.setName("Bac");

        Texture texture = new Texture(getApplicationContext(), R.drawable.figure, getTextureLibrary());
        CollisionSystem collisionSystem = new CollisionSystem(getEngine());

        Camera camera = new Camera(0, 0, 1, getWidth() / 2, getHeight() / 2, getEngine());
        DodgePlayer dodgePlayer = new DodgePlayer(getWidth() / 2 - 15, getHeight() - 50, 30, 30, 1, 5, getEngine(), texture, collisionSystem, this);
        dodgePlayer.setName("Player");
        dodgePlayer.animate(10, 1, 6);

        scene.addComponent(collisionSystem);
        scene.addComponent(dodgePlayer);
        scene.addComponent(camera);
        collisionSystem.showBoxes(true, scene);
        return scene;
    }

    @Override
    public void update(float dt) {
        DodgePlayer dodgePlayer = (DodgePlayer) getScene().findEntity("Player");
        getCamera().setCamX(dodgePlayer.getX() - getWidth() /2);

        Log.i("usern", String.valueOf(getWidth()));

        if (getTouch().held()){
            dodgePlayer.animate(15,1,5);
            if (getTouch().getX() > getWidth() /2) {
                dodgePlayer.setX(dodgePlayer.getX() + 1f);
            //    Log.i("usern", String.valueOf(dodgePlayer.getX()));
            }

            if (getTouch().getX() < getWidth() /2) {
                dodgePlayer.setX(dodgePlayer.getX() - 1f);
              //  Log.i("usern", String.valueOf(dodgePlayer.getX()));
            }
        } else {
            dodgePlayer.stopAnimation();
        }
    }

    @Override
    public void onPress(float x, float y) {

    }

}

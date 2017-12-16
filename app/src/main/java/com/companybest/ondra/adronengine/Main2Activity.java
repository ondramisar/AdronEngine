package com.companybest.ondra.adronengine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.restart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        Button minigame = findViewById(R.id.mini_game);
        minigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TapMiniGameActivity.class);
                startActivity(i);
            }
        });

        Button dodgeGame = findViewById(R.id.dodge_game);
        dodgeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DodgeActivity.class);
                startActivity(i);
            }
        });
        Button map = findViewById(R.id.map_button);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(i);
            }
        });
        Button crossy = findViewById(R.id.crossy_button);
        crossy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CrossyActivity.class);
                startActivity(i);
            }
        });

        Button camerapreview = findViewById(R.id.camera_preview_button);
        camerapreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CameraPreview.class);
                startActivity(i);
            }
        });

        Button kotlinTry = findViewById(R.id.kotlin_try_button);
        kotlinTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TestKotlinActivity.class);
                startActivity(i);
            }
        });
    }
}

package com.companybest.ondra.adronengine

import android.os.Bundle
import com.companybest.ondra.adron.BaseClasses.BasicAdrClass
import com.companybest.ondra.adron.Engine.Engine
import com.companybest.ondra.adron.Entity.Camera
import com.companybest.ondra.adron.Entity.Scene
import com.companybest.ondra.adron.OpenGl.Texture
import com.companybest.ondra.adron.OpenGl.TextureLibrary
import com.companybest.ondra.adron.Rendering.AdrGlSurfaceView
import com.companybest.ondra.adronengine.Objects.CrossyPlayer
import javax.microedition.khronos.opengles.GL10

class TestKotlinActivity : BasicAdrClass() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_test_activity)

        val adrView = findViewById<AdrGlSurfaceView>(R.id.kotlin_text_view)
        val engine = Engine(this, TextureLibrary(), true)
        setUpEngine(engine, this, adrView)
    }

    override fun setUpScene(): Scene {
        val scene = Scene()
        val camera = Camera(0f, 0f, 1.0, (width / 2).toDouble(), (height / 2).toDouble(), engine)
        scene.addComponent(camera)

        val texture = Texture(applicationContext, R.drawable.ic_launcher, textureLibrary)
        val player = CrossyPlayer((width / 2).toFloat(), (height / 2).toFloat(), 20F, 20F, engine, texture, this)
        scene.addComponent(player)
        return scene
    }

    override fun update(dt: Float) {
    }

    override fun onSurfaceChanged(gl10: GL10?, pWidth: Int, pHeight: Int) {
        super.onSurfaceChanged(gl10, pWidth, pHeight)
        engine.setGridHeight(200.0)
        engine.gridUnitX = engine.gridUnitY
    }


}
# AdronEngine
AdronEngine is android 2d game engine. 
Right now in a activ development.

### Installing

Right now you can add Adron to your project by only downloading this project, but gradle compile version is comming soon.


## Use

Complete documentation here [Wiki](https://github.com/ondramisar/AdronEngine)

### Start
To basicly start using AdronEngine your class needs to extend BasicAdrClass. BasicAdrClass is subclass of Activity so you can use all normal things in android activity

```
public class YourActivity extends BasicAdrClass{
...
}
```

### Override methods from BasicAdrClass
Here all the override methods that you can now use and explanation
```
public class YourActivity extends BasicAdrClass{

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight); // Called when OpenGl surface is changed
                                                       // Here you will most likely set up your grid height and width
    }

    @Override
    public void onSurfaceCreated(GL10 gl10) {
        super.onSurfaceCreated(gl10); // Called when OpenGl is created 
    }

    @Override
    public Scene setUpScene() {
                                      // Set ups the scene that will be rendered
                                      // Here you will create your scene from variety of components and objects
        return null;
    }

    @Override
    public void update(float dt) {  
                                      // Here you can update your components from scene
    }

    // Click and Touch methods
    @Override
    public void onPress(float x, float y) {
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
```

### Example of Basic Set up

There are two main ways to set up engine
#### 1. use AdrGlSurface from your android layout
#### 2. let the Engine create his own AdrGlSurface

```
public class YourActivity extends BasicAdrClass{
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodge);

        // FIRST WAY

        // with AdrGlSurfaceView from layout --> in layout you can create it the same way
        AdrGlSurfaceView adrGlSurfaceView = findViewById(R.id.your_layout_surface_view);
        
        // First Parametr is your Activity 
        // Second is the texture library that will be used
        // Third parametr is optional if you want a camera in you engine
        Engine engine = new Engine(this, new TextureLibrary(), true);
        
        // First Parametr is the Engine
        // Second Touch Listener
        // Your AdrGlSurcafeView
        setUpEngine(engine, this, adrGlSurfaceView);
        
        
        // SECOND WAY
        
        setUpEngine(engine,getApplicationContext, this);
    }
}
```

### Seting up Grid Height and width
Is best to set up your grid Height and with so you game can look same on all devices

```
public class YourActivity extends BasicAdrClass{
  @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight);
        getEngine().setGridHeight(200);                         // Firstly set grid height --> set ups GridUnitY too
        getEngine().setGridUnitX(getEngine().getGridUnitY());   // setGridUnitX from existing Y
    }
}
```

### Seting up scene
#### 1.Scene is the main component that will render your objects

```
public class YourActivity extends BasicAdrClass{
  @Override
    public Scene setUpScene() {
        Scene scene = new Scene();
        
        // Adding you components and obejcts here 
        
        return scene;
    }
}
```



## Authors

* **Ondřej Misař ** - *Initial work* - [AdronEngine](https://github.com/ondramisar/AdronEngine)

## License

This project is licensed under the Apache 2

## Acknowledgments

